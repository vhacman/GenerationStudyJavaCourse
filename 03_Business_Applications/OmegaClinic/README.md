# OmegaClinic - Sistema Gestionale Clinica

**Healthcare Management System per gestione pazienti, servizi sanitari e prestazioni cliniche**

Sistema completo per la gestione di una clinica medica che traccia pazienti, catalogo servizi sanitari e prestazioni erogate, con particolare attenzione alla persistenza dati e gestione relazioni tra entità.

---

## 1. Panoramica del Progetto

OmegaClinic è un'applicazione Java che simula un sistema informativo clinico (CIS - Clinical Information System) per gestire:
- **Anagrafica pazienti** con dati sensibili (nome, cognome, SSN, data di nascita)
- **Catalogo servizi sanitari** disponibili con tariffario
- **Prestazioni cliniche** erogate ai pazienti con tracciabilità temporale e finanziaria

Il sistema implementa un'architettura pulita con separazione tra logica di business, accesso dati e modello di dominio.

---

## 2. Caratteristiche Principali

### 2.1 Gestione Pazienti
- Registrazione anagrafica completa
- Validazione dati obbligatori (nome, cognome, SSN, data di nascita)
- Calcolo automatico spese annuali tramite `getExpenses(year)`
- Tracciamento storico prestazioni associate

### 2.2 Catalogo Servizi Sanitari
- Definizione servizi medici disponibili (es. "Visita Cardiologica", "ECG")
- Gestione tariffario con validazione prezzi non negativi
- Riutilizzo servizi per multiple prestazioni

### 2.3 Registrazione Prestazioni
- Tracciamento erogazione servizi ai pazienti
- Data di erogazione (LocalDate)
- Costo specifico per prestazione (permette override del prezzo catalogo)
- Relazioni bidirezionali: Patient ↔ Prestation ↔ HealthService

### 2.4 Persistenza Dati
- Database SQLite embedded (omegaclinic.db)
- Repository pattern con interfacce e implementazioni SQL
- CRUD completo su tutte le entità
- Prepared statements per prevenzione SQL injection

---

## 3. Architettura e Pattern

### 3.1 Repository Pattern

Il sistema separa completamente la logica di accesso ai dati dal business logic:

```java
// Interface - definisce il contratto
public interface PatientRepository extends EntityRepository<Patient> {
    Patient findById(int id);
    Patient findById(int id, boolean complete);  // Lazy/Eager loading
    Patient findByIdNaked(int id);               // Solo dati base
}

// Implementation - dettagli SQL nascosti
public class SQLPatientRepository
    extends SQLEntityRepository<Patient>
    implements PatientRepository {
    // Implementazione concreta con JDBC
}
```

**Vantaggi:**
- Codice business indipendente dal database
- Testing facile con mock repositories
- Cambio database trasparente

### 3.2 Lazy/Eager Loading Strategy

Il sistema implementa caricamento flessibile delle relazioni:

```java
// NAKED: Solo dati del paziente
Patient p = patientRepo.findByIdNaked(1);
// p.prestations è vuoto

// COMPLETE: Paziente + prestazioni + servizi
Patient p = patientRepo.findById(1, true);
// p.prestations è popolato con tutti i dati correlati
```

**Previene problemi comuni:**
- Cicli infiniti (Prestation → Patient → Prestations → ...)
- N+1 queries
- Over-fetching di dati non necessari

### 3.3 Dependency Injection Container

La classe `Context` implementa un IoC container custom:

```java
public class Context {
    static List<Object> dependencies = new ArrayList<>();

    static {
        // Inizializzazione automatica all'avvio
        dependencies.add(ConnectionFactory.getConnection(...));
        dependencies.add(new SQLPatientRepository());
        dependencies.add(new SQLHealthServiceRepository());
        dependencies.add(new SQLPrestationRepository());
    }

    public static <T> T getDependency(Class<T> dependencyNeeded) {
        // Autowiring by type con reflection
        for(Object o : dependencies) {
            if(dependencyNeeded.isAssignableFrom(o.getClass())) {
                return dependencyNeeded.cast(o);
            }
        }
        throw new RuntimeException("Dependency not found: " +
                                  dependencyNeeded.getName());
    }
}
```

**Benefici:**
- Accoppiamento lasco tra componenti
- Configurazione centralizzata
- Testing semplificato

### 3.4 Template Method Pattern

La classe base `SQLEntityRepository` definisce lo skeleton degli algoritmi:

```java
public abstract class SQLEntityRepository<X extends Entity>
    implements EntityRepository<X> {

    // Metodi concreti - implementati UNA VOLTA
    public List<X> findAll() {
        ResultSet rs = executeQuery("SELECT * FROM " + getTableName());
        List<X> results = new ArrayList<>();
        while(rs.next()) {
            results.add(rowToX(rs));  // Template method call
        }
        return results;
    }

    // Template methods - implementati dalle sottoclassi
    protected abstract X rowToX(ResultSet rs);
    protected abstract String getTableName();
    protected abstract String getInsertCmd();
}
```

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

    // Business logic
    public BigDecimal getExpenses(int year) {
        return prestations.stream()
            .filter(p -> p.getDate().getYear() == year)
            .map(Prestation::getCost)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
```

**Validazioni:**
- firstName, lastName, ssn obbligatori
- dob (data di nascita) obbligatoria
- ssn univoco nel sistema

### 4.2 HealthService (Servizio Sanitario)

```java
public class HealthService extends Entity {
    private String name;             // "Visita Cardiologica"
    private BigDecimal price;        // Tariffa base del servizio
}
```

**Validazioni:**
- name obbligatorio
- price >= 0 (servizi gratuiti permessi)

### 4.3 Prestation (Prestazione Clinica)

```java
public class Prestation extends Entity {
    private LocalDate date;          // Data erogazione
    private BigDecimal cost;         // Costo specifico (può differire da HealthService.price)
    private Patient patient;         // Paziente che ha ricevuto il servizio
    private HealthService healthService;  // Servizio erogato
}
```

**Caratteristiche:**
- Collega patient e healthService
- Permette override del costo (es. sconti, promozioni)
- Tracciabilità temporale completa

---

## 5. Struttura del Progetto

```text
OmegaClinic/
├── src/
│   └── com/generation/oc/
│       ├── context/
│       │   └── Context.java                    # DI Container
│       ├── controller/
│       │   └── (future controllers)
│       ├── model/
│       │   ├── entities/
│       │   │   ├── Patient.java               # Entità paziente
│       │   │   ├── HealthService.java         # Entità servizio sanitario
│       │   │   └── Prestation.java            # Entità prestazione
│       │   └── repository/
│       │       ├── PatientRepository.java           # Interface
│       │       ├── SQLPatientRepository.java        # SQL Implementation
│       │       ├── HealthServiceRepository.java     # Interface
│       │       ├── SQLHealthServiceRepository.java  # SQL Implementation
│       │       ├── PrestationRepository.java        # Interface
│       │       ├── SQLPrestationRepository.java     # SQL Implementation
│       │       └── TestGlobale.java                 # JUnit test suite
│       └── view/
│           └── (future views)
│
├── template/                       # Template HTML per report
├── omegaclinic.db                  # SQLite database
├── create                          # SQL schema definition
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

-- Prestation table
CREATE TABLE prestation (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date TEXT NOT NULL,            -- ISO 8601: YYYY-MM-DD
    cost REAL NOT NULL,
    patient_id INTEGER NOT NULL,
    healthservice_id INTEGER NOT NULL,
    FOREIGN KEY(patient_id) REFERENCES patient(id),
    FOREIGN KEY(healthservice_id) REFERENCES healthservice(id)
);
```

**Relazioni:**
- Patient 1 → * Prestation (one-to-many)
- HealthService 1 → * Prestation (one-to-many)

---

## 7. Esempio di Utilizzo

### 7.1 Scenario: Registrazione Visita Paziente

```java
// 1. Ottieni repositories dal Context
PatientRepository patientRepo =
    Context.getDependency(PatientRepository.class);
HealthServiceRepository serviceRepo =
    Context.getDependency(HealthServiceRepository.class);
PrestationRepository prestationRepo =
    Context.getDependency(PrestationRepository.class);

// 2. Registra nuovo paziente
Patient giovanni = new Patient(
    "Giovanni",
    "Rossi",
    "RSSGNN80A01H501U",
    LocalDate.of(1980, 1, 1)
);
giovanni = patientRepo.insert(giovanni);

// 3. Crea servizio sanitario
HealthService visitaCardio = new HealthService(
    "Visita Cardiologica",
    new BigDecimal("80.00")
);
visitaCardio = serviceRepo.insert(visitaCardio);

// 4. Registra prestazione erogata
Prestation prestazione = new Prestation(
    LocalDate.now(),
    new BigDecimal("80.00"),  // Tariffa piena
    giovanni,
    visitaCardio
);
prestazione = prestationRepo.insert(prestazione);

// 5. Calcola spesa annuale paziente
BigDecimal spesa2025 = giovanni.getExpenses(2025);
System.out.println("Spesa totale 2025: " + spesa2025 + "€");
```

### 7.2 Testing (da TestGlobale.java)

```java
@Test
public void testPatientWithPrestations() {
    // Carica paziente con tutte le prestazioni
    Patient p = patientRepo.findById(1, true);

    assertNotNull(p);
    assertNotNull(p.getPrestations());
    assertEquals(3, p.getPrestations().size());

    // Verifica relazioni bidirezionali
    p.getPrestations().forEach(prestation -> {
        assertEquals(p, prestation.getPatient());
        assertNotNull(prestation.getHealthService());
    });

    // Verifica calcolo spese
    BigDecimal expenses2025 = p.getExpenses(2025);
    assertEquals(new BigDecimal("2000.00"), expenses2025);
}
```

---

## 8. Query Principali Implementate

### PatientRepository

```java
// Find by ID con lazy loading
SELECT * FROM patient WHERE id = ?

// Find by ID con eager loading (+ prestations)
SELECT * FROM patient WHERE id = ?
SELECT * FROM prestation WHERE patient_id = ?
SELECT * FROM healthservice WHERE id = ?  // Per ogni prestation

// Find all
SELECT * FROM patient

// Insert
INSERT INTO patient (firstName, lastName, ssn, dob)
VALUES (?, ?, ?, ?)

// Update
UPDATE patient
SET firstName=?, lastName=?, ssn=?, dob=?
WHERE id=?
```

### PrestationRepository

```java
// Find by patient ID
SELECT * FROM prestation WHERE patient_id = ?

// Find with relationships
SELECT p.*, hs.name, hs.price, pt.firstName, pt.lastName
FROM prestation p
JOIN healthservice hs ON p.healthservice_id = hs.id
JOIN patient pt ON p.patient_id = pt.id
WHERE p.id = ?
```

---

## 9. Principi SOLID Implementati

| Principio | Implementazione |
|-----------|-----------------|
| **S (Single Responsibility)** | Ogni repository gestisce una sola entità; entity con validazione propria |
| **O (Open/Closed)** | Nuove entità estendono SQLEntityRepository senza modificare la base |
| **L (Liskov Substitution)** | SQLPatientRepository sostituibile con mock per testing |
| **I (Interface Segregation)** | Repository separati (non un unico repository generico sovraccarico) |
| **D (Dependency Inversion)** | Dipendenza da interfacce (PatientRepository), non implementazioni |

---

## 10. Tecnologie Utilizzate

| Componente | Tecnologia | Versione/Dettagli |
|-----------|-----------|-------------------|
| **Linguaggio** | Java SE | 21+ |
| **Database** | SQLite | Embedded database |
| **JDBC Driver** | sqlite-jdbc | 3.51.1.0 |
| **Testing** | JUnit | 5 (Jupiter) |
| **External Lib** | generation.library.jar | Entity base class + utilities |
| **Date/Time** | LocalDate | ISO 8601 format |
| **Finanza** | BigDecimal | Calcoli monetari precisi |

---

## 11. Pattern di Design Utilizzati

1. **Repository Pattern** → Astrazione layer di persistenza
2. **Template Method Pattern** → SQLEntityRepository skeleton
3. **Dependency Injection** → IoC Container in Context
4. **Strategy Pattern** → Lazy vs Eager loading intercambiabile
5. **Data Transfer Object (DTO)** → Entity per mapping DB-oggetti
6. **Factory Pattern** → ConnectionFactory per JDBC
7. **Facade Pattern** → EntityRepository nasconde complessità SQL

---

## 12. Gestione Errori

### Validazione Entity

```java
public List<String> getErrors() {
    List<String> errors = new ArrayList<>();

    if(firstName == null || firstName.isBlank())
        errors.add("First name is required");

    if(ssn == null || ssn.isBlank())
        errors.add("SSN is required");

    if(dob == null)
        errors.add("Date of birth is required");

    return errors;
}
```

### SQL Exception Handling

```java
try {
    patient = patientRepo.insert(patient);
} catch (SQLException e) {
    System.err.println("Database error: " + e.getMessage());
    e.printStackTrace();
}
```

### SQL Injection Prevention

```java
// ✅ CORRETTO - Prepared Statement
PreparedStatement ps = conn.prepareStatement(
    "SELECT * FROM patient WHERE ssn = ?"
);
ps.setString(1, ssnInput);  // Parametro escapato automaticamente

// ❌ VULNERABILE - String concatenation
// String sql = "SELECT * FROM patient WHERE ssn = '" + ssnInput + "'";
```

---

## 13. Testing

Il progetto include test completi in `TestGlobale.java`:

### Test Coverage
- ✅ CRUD operations su tutte le entità
- ✅ Relationship loading (lazy e eager)
- ✅ Business logic (getExpenses calculation)
- ✅ Bidirectional references consistency
- ✅ Data validation

### Esempio Test

```java
@Test
public void testExpensesCalculation() {
    Patient p = patientRepo.findById(1, true);

    // Verifica calcolo spese 2025
    BigDecimal expenses2025 = p.getExpenses(2025);
    assertEquals(new BigDecimal("2000.00"), expenses2025);

    // Verifica filtro anno corretto
    BigDecimal expenses2024 = p.getExpenses(2024);
    assertEquals(BigDecimal.ZERO, expenses2024);
}
```

---

## 14. Possibili Estensioni Future

- **Sistema di Autenticazione**: Login medici e amministrativi
- **Gestione Appuntamenti**: Calendario prenotazioni
- **Report Avanzati**: Export PDF fatture/ricevute
- **Dashboard Analytics**: Statistiche pazienti/revenue
- **Integration con POS**: Pagamenti elettronici
- **GDPR Compliance**: Anonimizzazione dati sensibili
- **Multi-clinica**: Supporto network cliniche

---

## Autore

**Viorica Gabriela Hacman**
- 🎓 Generation Italy - Java Full Stack Developer Bootcamp
- 📧 hacmanvioricagabriela@gmail.com

## Licenza

Progetto educativo - Generation Italy

---

## Note Tecniche

### Gestione Date SQLite
SQLite salva le date come stringhe in formato ISO 8601 (`YYYY-MM-DD`):
```java
// Java → SQLite
stmt.setString(1, localDate.toString());  // "2025-01-27"

// SQLite → Java
LocalDate date = LocalDate.parse(rs.getString("dob"));
```

### BigDecimal per Finanza
**MAI usare double/float per valori monetari!**
```java
// ✅ CORRETTO
BigDecimal total = price1.add(price2);

// ❌ ERRATO - precisione persa
// double total = 0.1 + 0.2;  // = 0.30000000000000004
```

### Convenzione Nomi Database
- Tabelle: `lowercase` (patient, healthservice)
- Colonne: `camelCase` (firstName, patient_id)
- Foreign keys: `<table>_id` (patient_id, healthservice_id)
