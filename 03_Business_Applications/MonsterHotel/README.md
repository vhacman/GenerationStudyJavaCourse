# 🏰 Monster Hotel - Sistema di Gestione Prenotazioni

> *"Where Nightmares Get a Good Night's Sleep"*

Un sistema di gestione prenotazioni per un hotel dedicato a creature soprannaturali, sviluppato in Java puro seguendo il pattern architetturale **MVC** (Model-View-Controller).

---

## 📋 Indice

1. [Panoramica del Progetto](#-panoramica-del-progetto)
2. [Architettura Software](#-architettura-software)
3. [Strumenti Java Utilizzati](#-strumenti-java-utilizzati)
4. [Logica di Business](#-logica-di-business)
5. [Struttura del Progetto](#-struttura-del-progetto)
6. [Pattern e Principi Applicati](#-pattern-e-principi-applicati)
7. [Funzionalità Implementate](#-funzionalità-implementate)
8. [Come Eseguire](#-come-eseguire)

---

## 🎯 Panoramica del Progetto

### Descrizione

Monster Hotel è un'applicazione console Java che permette di gestire prenotazioni per un hotel specializzato in ospiti soprannaturali. Ogni specie (Vampiri, Licantropi, Sirene, Umani) ha **vincoli specifici** e **costi extra** differenti.

### Obiettivi Didattici

- Applicare il **pattern MVC** per separare responsabilità
- Utilizzare **Enum** per modellare dati complessi
- Implementare **persistenza su file** (serializzazione testuale)
- Gestire **validazione** con regole di business
- Applicare principi **SOLID** (Single Responsibility, Open/Closed)
- Utilizzare **DTO** (Data Transfer Object) per trasferimento dati

---

## 🏗️ Architettura Software

Il progetto segue il pattern **MVC** (Model-View-Controller) per separare le responsabilità:

```
┌─────────────────────────────────────────────┐
│         HotelWizard (Controller)            │
│         - Coordina il flusso                │
│         - Gestisce il menu principale       │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│        BookingService (Business Logic)      │
│        - Wizard creazione prenotazioni      │
│        - Orchestrazione tra Data e View     │
└──────┬────────────────────────┬─────────────┘
       │                        │
       ▼                        ▼
┌──────────────────┐    ┌──────────────────┐
│  BookingRepo     │    │   HotelView      │
│  (DATA LAYER)    │    │   (VIEW LAYER)   │
│                  │    │                  │
│ - CRUD           │    │ - Console I/O    │
│ - Persistenza    │    │ - Formattazione  │
│ - Calcoli stats  │    │ - Template       │
│ - NO UI!         │    │ - HTML export    │
└──────────────────┘    └──────────────────┘
       │
       ▼
┌──────────────────┐
│ Model (Entities) │
│ - Booking        │
│ - Species (Enum) │
│ - RoomType (Enum)│
└──────────────────┘
```

### Separazione delle Responsabilità

| Layer | Responsabilità | NON fa |
|-------|----------------|--------|
| **Controller** | Coordina flusso, gestisce menu | Business logic, I/O diretto |
| **Service** | Logica business, validazione | Persistenza, rendering UI |
| **Repository** | CRUD, file I/O, calcoli dati | Visualizzazione (Console.print) |
| **View** | Visualizzazione, formattazione | Calcoli, persistenza |
| **Model** | Entità business, validazione | I/O, coordinamento |

---

## 🛠️ Strumenti Java Utilizzati

### 1. **Enum (Enumerazioni)**

**Dove**: `Species.java`, `RoomType.java`

**Perché**: Gli Enum in Java non sono semplici costanti, ma **classi complete** che possono contenere:
- Attributi (campi)
- Metodi
- Costruttori
- Logica di business

```java
public enum Species {
    VAMPIRE(0.0, 0.0, "🧛", "Vampiro"),
    MERMAID(50.0, 0.0, "🧜", "Sirena");

    private final double extraCostPercentage;
    private final double extraCostFlat;

    // Metodi business
    public double calculateExtraCost(double baseCost) { ... }
    public boolean canStayOnFloor(int floor) { ... }
}
```

**Vantaggi**:
- **Type safety**: impossibile passare valori non validi
- **Logica incapsulata**: ogni specie "sa" calcolare il proprio costo
- **Manutenibilità**: aggiungere una specie = aggiungere una costante enum
- **Leggibilità**: `Species.VAMPIRE` invece di costanti magiche come `"VAMPIRE"`

---

### 2. **LocalDate (java.time)**

**Dove**: `Booking.java` (arrivalDate, departureDate, dob)

**Perché**: Sostituisce le vecchie classi `Date` e `Calendar` con API moderna:

```java
LocalDate arrivalDate = LocalDate.parse("2025-01-15");
LocalDate departureDate = LocalDate.parse("2025-01-20");
long nights = ChronoUnit.DAYS.between(arrivalDate, departureDate); // 5 notti
```

**Vantaggi**:
- **Immutabile**: thread-safe, no modifiche accidentali
- **Calcoli facili**: `between()`, `isBefore()`, `isAfter()`
- **Parsing sicuro**: formato ISO-8601 standard
- **No timezone**: gestisce solo date (giorno/mese/anno)

---

### 3. **ArrayList e List<T>**

**Dove**: `BookingRepository.java`

**Perché**: Struttura dati dinamica per memorizzare prenotazioni:

```java
private List<Booking> bookings = new ArrayList<>();
```

**Vantaggi**:
- **Dimensione dinamica**: cresce automaticamente
- **Type safety** con generics `<Booking>`
- **Interfaccia List**: posso cambiare implementazione (LinkedList, etc)
- **Metodi utili**: `add()`, `isEmpty()`, `size()`, `clear()`

**Copia difensiva** nel getter:
```java
public List<Booking> getAllBookings() {
    return new ArrayList<>(bookings); // Copia, non riferimento originale
}
```
Previene modifiche esterne alla lista interna.

---

### 4. **Try-Catch (Gestione Eccezioni)**

**Dove**: Input date, salvataggio/caricamento file

**Perché**: Gestire errori senza crashare l'applicazione:

```java
while (dob == null) {
    try {
        String dobString = Console.readString();
        dob = LocalDate.parse(dobString);  // Può lanciare DateTimeParseException
    } catch (Exception e) {
        Console.print("Formato data non valido! Riprova: ");
    }
}
```

**Vantaggi**:
- **Robustezza**: il programma non crasha per input errato
- **UX migliorata**: l'utente può riprovare invece di riavviare
- **Gestione I/O**: file mancanti/corrotti non bloccano l'app

---

### 5. **String.split() e Parsing**

**Dove**: `BookingRepository.loadBookings()`

**Perché**: Deserializzare dati da file di testo:

```java
String line = "1|Mario|Rossi|1990-05-10|VAMPIRE|SINGOLA|-1|2025-01-15|2025-01-20|true";
String[] parts = line.split("\\|");  // Array di stringhe

int id = Integer.parseInt(parts[0]);           // "1" → 1
Species species = Species.valueOf(parts[4]);   // "VAMPIRE" → Species.VAMPIRE
LocalDate dob = LocalDate.parse(parts[3]);     // "1990-05-10" → LocalDate
```

**Vantaggi**:
- **Formato semplice**: facile da leggere/debug
- **Parsing robusto**: conversioni type-safe
- **valueOf()**: converte stringa in Enum automaticamente

---

### 6. **Switch Expression**

**Dove**: Selezione specie, tipo stanza, conteggi statistiche

**Perché**: Gestire scelte multiple in modo pulito:

```java
switch (speciesChoice) {
    case 1:
        booking.setSpecies(Species.VAMPIRE);
        break;
    case 2:
        booking.setSpecies(Species.WEREWOLF);
        break;
}
```

**Vantaggi**:
- **Leggibilità**: chiaro quali sono i casi
- **Sicurezza**: il compilatore avvisa se manca un caso (con enum)
- **Performance**: più efficiente di if-else multipli

---

### 7. **Static Methods (Metodi Statici)**

**Dove**: `HotelView` (tutti i metodi sono static)

**Perché**: Utility class senza stato:

```java
public static void showMenu() { ... }
public static void showStatistics(StatisticsData stats) { ... }
```

**Vantaggi**:
- **No istanza necessaria**: `HotelView.showMenu()` direttamente
- **Stateless**: nessuno stato condiviso (thread-safe)
- **Utility pattern**: metodi helper raggruppati logicamente

---

### 8. **String.format() e printf**

**Dove**: Visualizzazione prezzi, statistiche

**Perché**: Formattare numeri decimali:

```java
String.format("%.2f", 123.456)  // "123.46"
Console.print("Totale: €" + String.format("%.2f", totalRevenue));
```

**Vantaggi**:
- **Precisione**: controllo decimali
- **Leggibilità**: prezzi sempre con 2 decimali
- **Professionalità**: output formattato correttamente

---

### 9. **Costruttori Multipli**

**Dove**: `Booking.java`

**Perché**: Flessibilità nella creazione oggetti:

```java
public Booking() {}  // Costruttore vuoto per creazione step-by-step

public Booking(int id, String firstName, ...) {  // Costruttore completo per caricamento da file
    this.id = id;
    this.firstName = firstName;
    // ...
}
```

**Vantaggi**:
- **Costruttore vuoto**: per wizard interattivo (setter passo-passo)
- **Costruttore completo**: per deserializzazione da file (tutti i dati subito)

---

### 10. **Template Method Pattern (implicito)**

**Dove**: `BookingService.createNewBooking()`

**Perché**: Definire uno skeleton algorithm:

```java
public void createNewBooking() {
    askPersonalData(booking);    // Step 1
    askSpecies(booking);          // Step 2
    askRoomDetails(booking);      // Step 3
    askStayDates(booking);        // Step 4
    validateAndSave(booking);     // Step 5
}
```

**Vantaggi**:
- **Leggibilità**: il flusso è chiaro a colpo d'occhio
- **Manutenibilità**: modificare uno step non tocca gli altri
- **Testabilità**: ogni step è un metodo privato testabile

---

## 💼 Logica di Business

### Regole di Validazione

#### 1. **Vincoli per Specie**

| Specie | Vincolo Piano | Vincolo Stanza | Costo Extra |
|--------|--------------|----------------|-------------|
| 🧛 **Vampiro** | Solo piani negativi (sotterraneo) | Qualsiasi | Nessuno |
| 🐺 **Licantropo** | Qualsiasi | NO singola (serve spazio) | Nessuno |
| 🧜 **Sirena** | Qualsiasi | Qualsiasi | +50% (piscina) |
| 👤 **Homo Sapiens** | Qualsiasi | Qualsiasi | +€100 (sospetto) |

**Implementazione**:
```java
// In Species.java
public boolean canStayOnFloor(int floor) {
    return this == VAMPIRE ? floor < 0 : true;
}

public double calculateExtraCost(double baseCost) {
    return baseCost * (extraCostPercentage / 100.0) + extraCostFlat;
}
```

#### 2. **Validazione Date**

```java
// In Booking.validate()
if (!departureDate.isAfter(arrivalDate))
    errors += "La data di partenza deve essere successiva all'arrivo\n";

if (arrivalDate.isBefore(LocalDate.now()))
    errors += "La data di arrivo non può essere nel passato\n";
```

#### 3. **Calcolo Costi**

**Formula totale**:
```
Costo Totale = (Costo Stanza + Costo Navetta) + Extra Specie
```

**Esempio**:
- Stanza DOPPIA: €100/notte × 3 notti = €300
- Navetta: €100
- **Base**: €400
- Extra Sirena (+50%): €400 × 0.5 = €200
- **TOTALE**: €600

**Implementazione**:
```java
public double calculateTotalCost() {
    double roomCost = roomType.getTotalRoomCost(getNumberOfNights());
    double transportCost = transportService ? 100.0 : 0.0;
    double baseCost = roomCost + transportCost;
    return species.calculateTotalCost(baseCost);
}
```

---

### Persistenza Dati

**Formato file** (`data/bookings.txt`):
```
1|Mario|Rossi|1990-05-10|VAMPIRE|SINGOLA|-1|2025-01-15|2025-01-20|true
2|Laura|Bianchi|1985-03-20|MERMAID|SUITE|3|2025-02-01|2025-02-05|false
```

**Struttura**:
```
ID|Nome|Cognome|DataNascita|Specie|TipoStanza|Piano|DataArrivo|DataPartenza|Navetta
```

**Vantaggi**:
- **Leggibile**: formato testo semplice
- **Parsabile**: split su pipe `|`
- **Versionabile**: può essere tracciato con Git
- **Debug facile**: apribile con editor testo

---

## 📁 Struttura del Progetto

```
MonsterHotel/
├── src/
│   └── com/generation/mh/
│       ├── controller/
│       │   ├── HotelWizard.java         # Controller principale (main)
│       │   ├── BookingService.java      # Logica business
│       │   └── BookingRepository.java   # Persistenza e CRUD
│       ├── model/
│       │   ├── entities/
│       │   │   ├── Booking.java         # Entità prenotazione
│       │   │   ├── Species.java         # Enum specie
│       │   │   └── RoomType.java        # Enum tipo stanza
│       │   └── dto/
│       │       └── StatisticsData.java  # DTO statistiche
│       └── view/
│           └── HotelView.java           # Layer visualizzazione
├── template/
│   ├── menu.txt                         # Template menu ASCII
│   ├── label_prenotazione.txt
│   ├── label_visualizza.txt
│   ├── label_cerca.txt
│   └── template.html                    # Template export HTML
├── data/
│   └── bookings.txt                     # File persistenza
├── print/
│   └── booking_*.html                   # HTML generati
└── README.md                            # Questa documentazione
```

---

## 🎨 Pattern e Principi Applicati

### 1. **MVC (Model-View-Controller)**

**Separazione netta** tra livelli:
- **Model**: `Booking`, `Species`, `RoomType` (entità + logica dominio)
- **View**: `HotelView` (SOLO visualizzazione)
- **Controller**: `HotelWizard`, `BookingService` (coordinamento)

### 2. **Repository Pattern**

`BookingRepository` astrae la persistenza:
- Il Service non sa se i dati sono su file, database, API
- Facile cambiare storage senza toccare business logic

### 3. **DTO (Data Transfer Object)**

`StatisticsData` trasferisce dati tra layer:
- **NO logica business**: solo campi pubblici
- **NO dipendenze**: separato da Entity
- **Serializzabile**: facile da convertire in JSON/XML

### 4. **Single Responsibility Principle (SOLID)**

Ogni classe ha **UNA sola ragione per cambiare**:
- `BookingRepository`: cambia se modifica formato file
- `BookingService`: cambia se modifica workflow creazione
- `HotelView`: cambia se modifica UI/formattazione
- `Booking`: cambia se modifica struttura prenotazione

### 5. **Open/Closed Principle**

**Aggiungere nuove specie** senza modificare codice esistente:
```java
// Basta aggiungere una nuova costante enum!
public enum Species {
    VAMPIRE(...),
    WEREWOLF(...),
    ZOMBIE(20.0, 50.0, "🧟", "Zombie")  // ← NUOVA SPECIE
}
```

### 6. **Defensive Copying**

```java
public List<Booking> getAllBookings() {
    return new ArrayList<>(bookings);  // Copia, non riferimento
}
```
Previene modifiche esterne alla lista interna.

---

## ⚙️ Funzionalità Implementate

### Menu Principale

```
╔══════════════════════════════════════════════════════════════╗
║            MONSTER HOTEL BOOKING SYSTEM                      ║
║      "Where Nightmares Get a Good Night's Sleep"             ║
╠══════════════════════════════════════════════════════════════╣
║   [1]  Nuova Prenotazione        - Accogli un nuovo ospite   ║
║   [2]  Elenco Prenotazioni       - Visualizza gli alloggiati ║
║   [3]  Cerca Prenotazione        - Ricerca per ID            ║
║   [4]  Salva Prenotazioni        - Sigilla nella cripta      ║
║   [5]  Carica Prenotazioni       - Evoca dal passato         ║
║   [6]  Genera HTML               - Documento per i mortali   ║
║   [7]  Statistiche Hotel         - Analisi infestazioni      ║
║   [0]  Esci                      - Ritorna nell'oscurita'    ║
╚══════════════════════════════════════════════════════════════╝
```

### 1. Nuova Prenotazione
- Wizard interattivo step-by-step
- Validazione input (date, formati)
- Avvisi vincoli specie (vampiri, licantropi)
- Calcolo automatico costi
- Validazione regole business

### 2. Elenco Prenotazioni
- Mostra tutte le prenotazioni
- Dettagli: ID, cliente, specie, stanza, date, costo
- Formattazione grafica

### 3. Cerca Prenotazione
- Ricerca per ID
- Visualizzazione dettagli completi
- Gestione "non trovata"

### 4. Salva Prenotazioni
- Persistenza su file di testo
- Formato pipe-delimited
- Gestione errori I/O

### 5. Carica Prenotazioni
- Caricamento da file
- Parsing robusto
- Aggiornamento ID counter
- Gestione file mancante

### 6. Genera HTML
- Export prenotazione in HTML
- Template personalizzabile
- Creazione automatica directory `print/`
- File nominato `booking_X.html`

### 7. Statistiche Hotel
- **Distribuzione per specie**: conteggi vampiri, licantropi, etc
- **Distribuzione per stanza**: singole, doppie, suite
- **Metriche finanziarie**:
  - Ricavi totali
  - Media per prenotazione
  - Notti totali
  - Servizi navetta richiesti

---

## 🚀 Come Eseguire

### Prerequisiti
- **Java JDK 8+** installato
- **Terminal/Console** per eseguire comandi

### Compilazione

```bash
# Dalla directory del progetto
javac -d bin -sourcepath src src/com/generation/mh/controller/HotelWizard.java
```

### Esecuzione

```bash
java -cp bin com.generation.mh.controller.HotelWizard
```

### Esempio Flusso Completo

```bash
# 1. Compila
javac -d bin -sourcepath src src/com/generation/mh/controller/*.java

# 2. Esegui
java -cp bin com.generation.mh.controller.HotelWizard

# 3. Usa il menu:
#    [1] Crea prenotazione
#    [4] Salva su file
#    [0] Esci

# 4. Riavvia e carica:
java -cp bin com.generation.mh.controller.HotelWizard
#    [5] Carica prenotazioni
#    [7] Visualizza statistiche
```

---

## 📊 Statistiche Progetto

| Metrica | Valore |
|---------|--------|
| **Classi totali** | 8 |
| **Righe di codice** | ~1200 |
| **Metodi pubblici** | 35 |
| **Pattern applicati** | 5 (MVC, Repository, DTO, Template Method, Defensive Copy) |
| **Enum utilizzati** | 2 (Species, RoomType) |
| **File di configurazione** | 4 template txt + 1 HTML |

---

## 🎓 Concetti Chiave Appresi

### Java Core
✅ Enum avanzati con logica business
✅ LocalDate e API moderne java.time
✅ Gestione eccezioni robusta
✅ ArrayList e generics
✅ String manipulation (split, format)
✅ File I/O con serializzazione custom
✅ Switch statements
✅ Metodi statici vs istanza

### Design Patterns
✅ MVC (Model-View-Controller)
✅ Repository Pattern
✅ DTO (Data Transfer Object)
✅ Template Method
✅ Defensive Copying

### Principi SOLID
✅ Single Responsibility Principle
✅ Open/Closed Principle
✅ Dependency Inversion (via interfacce implicite)

### Best Practices
✅ Separazione responsabilità (layer)
✅ Validazione centralizzata
✅ Gestione errori graceful
✅ Commenti Javadoc completi
✅ Naming conventions Java
✅ Copia difensiva collezioni

---

## 🔮 Possibili Evoluzioni Future

1. **Persistenza Database**
   - Sostituire file txt con JDBC/Hibernate
   - Repository già astratto, facile switch

2. **API REST**
   - Esporre funzionalità via HTTP
   - JSON invece di Console

3. **Interfaccia Grafica**
   - Swing/JavaFX
   - View già separato, facile integrare

4. **Testing**
   - JUnit per unit test
   - Repository e Service già testabili

5. **Funzionalità Aggiuntive**
   - Cancellazione prenotazioni
   - Modifica prenotazioni esistenti
   - Gestione pagamenti
   - Sistema di notifiche

---

## 👨‍💻 Autore

Progetto sviluppato come compito per le vacanze - Generation Italy Java Course

**Data**: Gennaio 2026
**Corso**: Business Applications - Java Programming
**Tema**: Sistema gestionale con pattern MVC e logica business complessa

---

## 📄 Licenza

Progetto didattico - Tutti i diritti riservati per scopi educativi.

---

**Monster Hotel** - *Dove anche gli incubi meritano un buon sonno!* 🦇🌙
