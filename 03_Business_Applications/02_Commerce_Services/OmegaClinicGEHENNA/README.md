# OmegaClinicGEHENNA - Advanced Clinical Scheduling System

**Sistema Avanzato di Gestione Clinica con Calendario Intelligente**

Versione evoluta di OmegaClinic che implementa un sistema di **scheduling automatico** per la gestione appuntamenti clinici con algoritmo di ricerca slot disponibili, organizzazione prestazioni in calendario e tracking finanziario avanzato.

---

## 1. Panoramica del Progetto

OmegaClinicGEHENNA rappresenta la versione "completa" e "sfidante" del sistema gestionale clinico, con l'aggiunta di funzionalità avanzate di **calendario intelligente** e **ricerca automatica appuntamenti**.

Il nome **GEHENNA** (dall'ebraico "Ge Hinnom", metafora per un luogo difficile/complesso) indica l'elevato livello di complessità implementativa, incorporando:
- **Algoritmi di scheduling** con ricerca slot disponibili
- **Strutture dati avanzate** (LinkedHashMap, custom Comparator)
- **Gestione calendario** con orari clinici (9-12, 14-17, pausa pranzo)
- **Lambda expressions** e **functional programming**
- **Sorting automatico** prestazioni per data/ora

---

## 2. Caratteristiche Principali

### 2.1 Gestione Pazienti
- Anagrafica completa (nome, cognome, SSN, data di nascita)
- Calcolo automatico spese annuali con `getExpenses(year)`
- Relazioni bidirezionali con prestazioni

### 2.2 Servizi Sanitari
- Catalogo servizi con tariffario
- Validazione prezzi non negativi
- Riutilizzo servizi per multiple prestazioni

### 2.3 Prestazioni Cliniche (Prestation)
- Tracciamento data **e ora** erogazione (novità rispetto a OmegaClinic base)
- Slot orari precisi (hour: 9-12, 14-17)
- Override costo per prestazione singola
- **equals/hashCode** basati su date+hour per prevenire double-booking

### 2.4 Calendario Intelligente (PrestationCalendar) ⭐
**Feature principale del progetto:**

```java
public class PrestationCalendar {
    // Organizza prestazioni per data con LinkedHashMap
    private LinkedHashMap<LocalDate, List<Prestation>> calendar;

    // Trova prossimo slot libero automaticamente
    public int findNextHour(LocalDate date);

    // Ottiene tutte le ore occupate per una data
    public List<Integer> getOccupiedHours(LocalDate date);

    // Aggiunge prestazione con sorting automatico
    public void add(Prestation p);
}
```

**Algoritmo di ricerca slot:**
1. Orari disponibili: 9-12 (mattina), 14-17 (pomeriggio)
2. Pausa pranzo: 13:00 (sempre esclusa)
3. Ricerca sequenziale primo slot libero
4. Se giorno pieno → restituisce -1

---

## 3. Architettura e Pattern

### 3.1 Repository Pattern con Eager/Lazy Loading

```java
public interface PatientRepository extends EntityRepository<Patient> {
    // Lazy loading - solo dati base
    Patient findByIdNaked(int id);

    // Eager loading configurabile
    Patient findById(int id, boolean complete);
}
```

**Strategia implementata:**
- `complete = false` → carica solo Patient (veloce)
- `complete = true` → carica Patient + Prestations + HealthServices (completo)

**Previene:**
- Cicli infiniti di caricamento
- N+1 query problem
- Over-fetching

### 3.2 Dependency Injection Container

```java
public class Context {
    static List<Object> dependencies = new ArrayList<>();

    static {
        // Autowiring automatico al caricamento classe
        dependencies.add(new SQLPatientRepository());
        dependencies.add(new SQLPrestationRepository());
        dependencies.add(new SQLHealthServiceRepository());
    }

    public static <T> T getDependency(Class<T> dependencyNeeded) {
        // Reflection-based type matching
        for(Object o : dependencies) {
            if(dependencyNeeded.isAssignableFrom(o.getClass())) {
                return dependencyNeeded.cast(o);
            }
        }
        throw new RuntimeException("Dependency not found");
    }
}
```

### 3.3 Custom Sorting con Comparator

Le prestazioni nel calendario sono **automaticamente ordinate**:

```java
// In PrestationCalendar.add()
prestations.sort(Comparator
    .comparing(Prestation::getDate)          // Prima per data
    .thenComparingInt(Prestation::getHour)   // Poi per ora
);
```

**Lambda expression equivalente:**
```java
prestations.sort((p1, p2) -> {
    int dateCompare = p1.getDate().compareTo(p2.getDate());
    if(dateCompare != 0) return dateCompare;
    return Integer.compare(p1.getHour(), p2.getHour());
});
```

### 3.4 LinkedHashMap per Calendario

**Scelta della struttura dati:**
```java
private LinkedHashMap<LocalDate, List<Prestation>> calendar;
```

**Perché LinkedHashMap e non HashMap?**
- LinkedHashMap **mantiene l'ordine di inserimento**
- Essenziale per visualizzare calendario cronologicamente
- Performance O(1) per accesso come HashMap standard

---

## 4. Entità del Dominio

### 4.1 Patient (Paziente)

```java
public class Patient extends Entity {
    private String firstName;
    private String lastName;
    private String ssn;              // Social Security Number
    private LocalDate dob;           // Date of Birth
    private List<Prestation> prestations;

    // Business logic - calcolo spese annuali
    public BigDecimal getExpenses(int year) {
        return prestations.stream()
            .filter(p -> p.getDate().getYear() == year)
            .map(Prestation::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
```

### 4.2 HealthService (Servizio Sanitario)

```java
public class HealthService extends Entity {
    private String name;             // "Visita Cardiologica"
    private BigDecimal price;        // Tariffa standard
}
```

### 4.3 Prestation (Prestazione con Orario)

```java
public class Prestation extends Entity {
    private LocalDate date;          // Giorno prestazione
    private int hour;                // Ora prestazione (9-12, 14-17)
    private BigDecimal price;        // Costo effettivo
    private Patient patient;
    private HealthService healthService;

    // Equals/HashCode per prevenire double-booking
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Prestation)) return false;
        Prestation p = (Prestation)o;
        return this.date.equals(p.date) && this.hour == p.hour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, hour);
    }
}
```

**Due prestazioni sono uguali se hanno stessa data E stessa ora** → impossibile prenotare stesso slot due volte.

### 4.4 PrestationCalendar (Calendario)

```java
public class PrestationCalendar {
    private LinkedHashMap<LocalDate, List<Prestation>> calendar;

    public PrestationCalendar(List<Prestation> prestations) {
        calendar = new LinkedHashMap<>();

        // Popola calendario raggruppando per data
        for(Prestation p : prestations) {
            calendar.computeIfAbsent(p.getDate(),
                k -> new ArrayList<>()).add(p);
        }

        // Ordina prestazioni in ogni giornata
        calendar.values().forEach(list ->
            list.sort(Comparator
                .comparing(Prestation::getDate)
                .thenComparingInt(Prestation::getHour))
        );
    }

    // Trova prossimo slot disponibile
    public int findNextHour(LocalDate date) {
        List<Integer> occupied = getOccupiedHours(date);

        // Orari mattina (9-12)
        for(int h = 9; h <= 12; h++) {
            if(!occupied.contains(h)) return h;
        }

        // Orari pomeriggio (14-17), skip pranzo (13)
        for(int h = 14; h <= 17; h++) {
            if(!occupied.contains(h)) return h;
        }

        return -1;  // Giorno pieno
    }

    // Ottiene ore occupate per data specifica
    public List<Integer> getOccupiedHours(LocalDate date) {
        return calendar.getOrDefault(date, Collections.emptyList())
            .stream()
            .map(Prestation::getHour)
            .collect(Collectors.toList());
    }
}
```

---

## 5. Struttura del Progetto

```text
OmegaClinicGEHENNA/
├── src/
│   └── com/generation/oc/
│       ├── controller/
│       │   └── Main.java                    # Demo calendario intelligente
│       ├── context/
│       │   └── Context.java                 # DI Container
│       ├── model/
│       │   ├── entities/
│       │   │   ├── Patient.java            # Paziente
│       │   │   ├── HealthService.java      # Servizio sanitario
│       │   │   ├── Prestation.java         # Prestazione con hour
│       │   │   └── PrestationCalendar.java # ⭐ Calendario intelligente
│       │   └── repository/
│       │       ├── PatientRepository.java           # Interface
│       │       ├── SQLPatientRepository.java        # SQL Implementation
│       │       ├── HealthServiceRepository.java     # Interface
│       │       ├── SQLHealthServiceRepository.java  # SQL Implementation
│       │       ├── PrestationRepository.java        # Interface
│       │       ├── SQLPrestationRepository.java     # SQL Implementation
│       │       └── TestGlobale.java                 # JUnit tests
│       └── view/
│           └── (future views)
│
└── README.md
```

---

## 6. Database Schema

```sql
-- Patient table
CREATE TABLE patient (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    ssn TEXT NOT NULL UNIQUE,
    dob TEXT NOT NULL              -- ISO 8601: YYYY-MM-DD
);

-- HealthService table
CREATE TABLE healthservice (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    price REAL NOT NULL CHECK(price >= 0)
);

-- Prestation table (con campo HOUR)
CREATE TABLE prestation (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date TEXT NOT NULL,            -- ISO 8601: YYYY-MM-DD
    hour INTEGER NOT NULL,         -- ⭐ Slot orario (9-12, 14-17)
    price REAL NOT NULL,
    patient_id INTEGER NOT NULL,
    healthservice_id INTEGER NOT NULL,
    FOREIGN KEY(patient_id) REFERENCES patient(id),
    FOREIGN KEY(healthservice_id) REFERENCES healthservice(id),
    UNIQUE(date, hour)             -- ⭐ Previene double-booking a DB level
);
```

---

## 7. Esempio di Utilizzo

### 7.1 Scenario: Prenotazione Appuntamento Automatica

```java
// 1. Carica tutte le prestazioni esistenti
PrestationRepository prestRepo =
    Context.getDependency(PrestationRepository.class);
List<Prestation> allPrestations = prestRepo.findAll();

// 2. Crea calendario intelligente
PrestationCalendar calendar = new PrestationCalendar(allPrestations);

// 3. Trova prossimo slot disponibile per oggi
LocalDate today = LocalDate.now();
int nextSlot = calendar.findNextHour(today);

if(nextSlot == -1) {
    System.out.println("Nessuno slot disponibile oggi!");
} else {
    System.out.println("Prossimo appuntamento disponibile: " +
                      today + " alle ore " + nextSlot + ":00");
}

// 4. Visualizza ore occupate oggi
List<Integer> occupied = calendar.getOccupiedHours(today);
System.out.println("Ore già prenotate: " + occupied);
// Output esempio: [9, 10, 14, 15, 17]

// 5. Prenota nuovo appuntamento
Patient patient = patientRepo.findById(1);
HealthService service = serviceRepo.findById(1);

Prestation newPrestation = new Prestation(
    today,
    nextSlot,  // Usa slot trovato automaticamente
    service.getPrice(),
    patient,
    service
);
prestRepo.insert(newPrestation);

// 6. Aggiorna calendario
calendar.add(newPrestation);  // Sorting automatico
```

### 7.2 Visualizzazione Calendario Settimanale

```java
LocalDate startDate = LocalDate.now();

for(int i = 0; i < 7; i++) {
    LocalDate date = startDate.plusDays(i);
    System.out.println("\n=== " + date.getDayOfWeek() + " " + date + " ===");

    List<Integer> occupied = calendar.getOccupiedHours(date);

    if(occupied.isEmpty()) {
        System.out.println("  Nessun appuntamento");
    } else {
        System.out.println("  Ore prenotate: " + occupied);
        int nextFree = calendar.findNextHour(date);
        if(nextFree != -1) {
            System.out.println("  Prossimo slot libero: " + nextFree + ":00");
        } else {
            System.out.println("  Giornata completa");
        }
    }
}
```

Output esempio:
```
=== LUNEDI 2025-01-27 ===
  Ore prenotate: [9, 10, 14]
  Prossimo slot libero: 11:00

=== MARTEDI 2025-01-28 ===
  Nessun appuntamento
  Prossimo slot libero: 9:00

=== MERCOLEDI 2025-01-29 ===
  Ore prenotate: [9, 10, 11, 12, 14, 15, 16, 17]
  Giornata completa
```

---

## 8. Algoritmi e Complessità

### 8.1 Algoritmo findNextHour()

```java
public int findNextHour(LocalDate date) {
    List<Integer> occupied = getOccupiedHours(date);

    // O(4) - check mattina
    for(int h = 9; h <= 12; h++) {
        if(!occupied.contains(h)) return h;  // O(n) per contains
    }

    // O(4) - check pomeriggio
    for(int h = 14; h <= 17; h++) {
        if(!occupied.contains(h)) return h;
    }

    return -1;
}
```

**Complessità:**
- Tempo: O(n) dove n = numero prestazioni nella giornata (max 8)
- Spazio: O(n) per lista occupied

**Ottimizzazione possibile:** Usare `HashSet<Integer>` per occupied → contains() diventa O(1).

### 8.2 Sorting con Comparator

```java
list.sort(Comparator
    .comparing(Prestation::getDate)
    .thenComparingInt(Prestation::getHour)
);
```

**Complessità:**
- Tempo: O(n log n) - Timsort (default Java)
- Spazio: O(n) - array temporaneo per merge

### 8.3 Calendar Population

```java
for(Prestation p : prestations) {
    calendar.computeIfAbsent(p.getDate(), k -> new ArrayList<>()).add(p);
}
```

**Complessità:**
- Tempo: O(n) - una passata su tutte le prestazioni
- Spazio: O(n) - tutte le prestazioni in memoria organizzate

---

## 9. Principi SOLID Implementati

| Principio | Implementazione |
|-----------|-----------------|
| **S (Single Responsibility)** | PrestationCalendar si occupa SOLO di scheduling, non persistenza |
| **O (Open/Closed)** | Nuovi algoritmi di scheduling possono estendere senza modificare esistente |
| **L (Liskov Substitution)** | Repository intercambiabili con mock per testing |
| **I (Interface Segregation)** | Repository separati per Patient/Prestation/HealthService |
| **D (Dependency Inversion)** | Controller dipende da interfacce Repository, non implementazioni |

---

## 10. Tecnologie Utilizzate

| Componente | Tecnologia | Dettagli |
|-----------|-----------|----------|
| **Linguaggio** | Java 8+ | Lambda, Stream API, LocalDate, Comparator |
| **Database** | SQLite | JDBC embedded |
| **Collections** | LinkedHashMap, ArrayList | Ordinamento e grouping |
| **Functional** | Comparator, Lambda | Sorting dichiarativo |
| **Testing** | JUnit 5 | Jupiter API |
| **Design Patterns** | Repository, DI, Strategy | Architettura pulita |

---

## 11. Pattern di Design Utilizzati

1. **Repository Pattern** → Astrazione persistenza
2. **Dependency Injection** → IoC Container custom
3. **Strategy Pattern** → Lazy/Eager loading configurabile
4. **Template Method** → SQLEntityRepository base class
5. **Data Transfer Object** → Entity per DB mapping
6. **Factory Pattern** → ConnectionFactory
7. **Composite Pattern** → Calendar contiene collezioni di Prestations
8. **Strategy Pattern (Comparator)** → Sorting algorithm pluggable

---

## 12. Differenze con OmegaClinic Base

| Feature | OmegaClinic | OmegaClinicGEHENNA |
|---------|-------------|---------------------|
| **Slot Orari** | Solo data prestazione | Data + hour precisa |
| **Calendario** | Assente | PrestationCalendar con LinkedHashMap |
| **Scheduling** | Manuale | Algoritmo automatico findNextHour() |
| **Double-booking** | Possibile | Prevenuto con equals() + DB UNIQUE |
| **Sorting** | Non implementato | Automatico con Comparator |
| **Complessità** | Media | Alta (GEHENNA = challenging) |
| **Use Case** | Base tracking | Sistema prenotazioni reale |

---

## 13. Testing

### Test Principali in TestGlobale.java

```java
@Test
public void testCalendarScheduling() {
    // Carica tutte le prestazioni
    List<Prestation> prests = prestRepo.findAll();
    PrestationCalendar calendar = new PrestationCalendar(prests);

    LocalDate today = LocalDate.of(2025, 1, 27);

    // Test occupied hours
    List<Integer> occupied = calendar.getOccupiedHours(today);
    assertTrue(occupied.contains(9));
    assertTrue(occupied.contains(14));

    // Test next available slot
    int nextSlot = calendar.findNextHour(today);
    assertTrue(nextSlot >= 9 && nextSlot <= 17);
    assertFalse(occupied.contains(nextSlot));

    // Test full day
    LocalDate fullDay = LocalDate.of(2025, 1, 29);
    assertEquals(-1, calendar.findNextHour(fullDay));
}

@Test
public void testDoubleBookingPrevention() {
    Prestation p1 = new Prestation(LocalDate.now(), 10, ...);
    Prestation p2 = new Prestation(LocalDate.now(), 10, ...);

    // Equals dovrebbe rilevare conflitto
    assertEquals(p1, p2);  // Stessa data e ora

    // DB UNIQUE constraint dovrebbe bloccare insert
    prestRepo.insert(p1);
    assertThrows(SQLException.class, () -> prestRepo.insert(p2));
}
```

---

## 14. Possibili Estensioni Future

- **UI Calendario Visuale**: Interfaccia grafica tipo Google Calendar
- **Notifiche Automatiche**: Email/SMS reminder appuntamenti
- **Recurring Appointments**: Visite periodiche automatiche
- **Medici Multipli**: Scheduling per più professionisti
- **Sala/Ambulatorio**: Gestione risorse multiple
- **Cancellazioni**: Gestione disdette con penali
- **Waitlist**: Lista attesa per slot cancellati
- **Analytics**: Report occupazione/revenue per slot orario

---

## Autore

**Viorica Gabriela Hacman**
- 🎓 Generation Italy - Java Full Stack Developer Bootcamp
- 📧 hacmanvioricagabriela@gmail.com

## Licenza

Progetto educativo - Generation Italy

---

## Note Finali

**OmegaClinicGEHENNA** rappresenta un'evoluzione significativa rispetto al sistema base, introducendo concetti avanzati di:
- **Algoritmi di scheduling** realistici
- **Strutture dati appropriate** (LinkedHashMap per ordinamento)
- **Functional programming** (lambda, Comparator)
- **Business logic complessa** (calcolo disponibilità, prevenzione conflitti)

Il nome "GEHENNA" riflette accuratamente la sfida implementativa: un sistema di scheduling clinico funzionante richiede attenzione a dettagli come orari lavorativi, pause, conflitti e performance. Questo progetto dimostra padronanza di Java avanzato e capacità di modellare domini complessi del mondo reale.
