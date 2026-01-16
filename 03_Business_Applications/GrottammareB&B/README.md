# 🏖️ Grottammare B&B - Sistema Gestionale Completo

Sistema gestionale console-based per Bed & Breakfast che implementa architettura enterprise con **MVC completo (Model-View-Controller)**, **Repository Pattern**, **Factory Pattern per Dependency Injection**, **Service Layer**, **View Pattern** ed **ETL Pattern**. Include validazione avanzata con regex per Codice Fiscale italiano, gestione spese con import CSV e database SQLite.

---

## 📋 Indice

- [Caratteristiche Principali](#-caratteristiche-principali)
- [Architettura Software](#-architettura-software)
- [Design Patterns Implementati](#-design-patterns-implementati)
- [Modello Dati](#-modello-dati)
- [Tecnologie Utilizzate](#️-tecnologie-utilizzate)
- [Struttura Progetto](#-struttura-progetto)
- [Installazione ed Esecuzione](#-installazione-ed-esecuzione)
- [Funzionalità](#-funzionalità)
- [Test](#-test)
- [Database](#-database)
- [Estendibilità](#-estendibilità)

---

## ✨ Caratteristiche Principali

- 🏨 **Gestione Ospiti** con validazione Codice Fiscale italiano e controllo età maggiorenne
- 🛏️ **Gestione Camere** con pricing e ricerca avanzata
- 💰 **Gestione Spese** con categorizzazione e import da CSV
- 🗄️ **Database SQLite** per persistenza dati
- 📊 **Report e Statistiche** con aggregazioni per categoria
- 🧪 **Test Unitari** completi con JUnit 5
- 🏗️ **Architettura Enterprise** scalabile e manutenibile

---

## 🏛️ Architettura Software

L'applicazione segue il paradigma **MVC** con separazione chiara dei ruoli e implementa pattern enterprise:

```
┌──────────────────────────────────────────────────────────────┐
│                    Main (Controller)                          │
│              Dependency Injection via Factory                 │
└───────────────────────┬──────────────────────────────────────┘
                        │
        ┌───────────────┼───────────────┐
        │               │               │
        ▼               ▼               ▼
  ┌─────────┐    ┌──────────┐   ┌──────────────┐
  │ Guest   │    │  Room    │   │   Expense    │
  │ Service │    │ Service  │   │   Service    │
  └────┬────┘    └────┬─────┘   └──────┬───────┘
       │              │                 │
       │              │                 │
       ▼              ▼                 ▼
  ┌─────────┐    ┌──────────┐   ┌──────────────┐
  │ Guest   │    │  Room    │   │   Expense    │
  │Repository    │Repository│   │  Repository  │
  └────┬────┘    └────┬─────┘   └──────┬───────┘
       │              │                 │
       └──────────────┴─────────────────┘
                      │
                      ▼
            ┌──────────────────┐
            │   SQLite Database │
            │  grottammare.db   │
            └──────────────────┘

Presentation Layer (View):
MenuView, GuestView, RoomView, ExpenseView → Template rendering

ETL Layer (CSV Import):
ExpenseExtractorCSV → ExpenseService → ExpenseRepository → DB
GuestExtractorCSV   → GuestService   → GuestRepository   → DB
```

### Livelli di Astrazione

| Layer | Componenti | Responsabilità |
|-------|-----------|----------------|
| **Controller** | `Main.java` | Orchestrazione menu interattivo e routing comandi |
| **View Layer** | `*View.java`, `MenuView.java` | Rendering output, template, presentation logic |
| **Service Layer** | `*Service.java` | Business logic, validazione flusso, coordinamento |
| **Model** | `Guest.java`, `Room.java`, `Expense.java` | Entità con validazione incorporata |
| **Repository** | Interfacce + Impl. SQL/Dummy | Astrazione persistenza, query database |
| **ETL Layer** | `*Extractor.java` | Importazione dati da CSV |
| **Factory** | `*Factory.java` | Creazione oggetti configurabile (DI) |
| **Persistence** | `ConnectionFactory.java` | Gestione connessioni database (Singleton) |

---

## 🎨 Design Patterns Implementati

### 1. Repository Pattern

Interfacce che astraggono completamente l'accesso ai dati:

```java
public interface GuestRepository {
    List<Guest> findAll();
    Guest findById(int id);
    Guest findBySSN(String ssn);     // Codice Fiscale
    List<Guest> findBySurnameContaining(String part);
    List<Guest> findByCity(String city);
    void insert(Guest guest);
    void update(Guest guest);
    void delete(int id);
}

public interface ExpenseRepository {
    List<Expense> findAll();
    Expense findById(int id);
    List<Expense> findByCategory(ExpenseCategory category);
    List<Expense> findByDateRange(LocalDate from, LocalDate to);
    void insert(Expense expense);
    double getTotalByCategory(ExpenseCategory category);
    double getGrandTotal();
}
```

**Vantaggi:**
- ✅ Testability (possibilità di mock)
- ✅ Estendibilità (sostituzione implementazioni)
- ✅ Inversione delle dipendenze
- ✅ Separazione logica business da persistenza

### 2. Factory Pattern (Dependency Injection)

Factory per creazione centralizzata e configurabile delle dipendenze:

```java
public class GuestRepositoryFactory {
    public static GuestRepository make() {
        return new SQLGuestRepository();  // Configurabile
    }
}

public class ExpenseExtractorFactory {
    public static ExpenseExtractor make() {
        return new ExpenseExtractorCSV();  // CSV, JSON, XML...
    }
}
```

**Vantaggi:**
- ✅ Configurazione centralizzata
- ✅ Sostituzione implementazioni senza modificare codice client
- ✅ Dependency Injection manuale

### 3. ETL Pattern (Extract-Transform-Load)

Pattern per import dati da fonti eterogenee:

```java
public interface ExpenseExtractor {
    List<Expense> extractFrom(String source) throws FileNotFoundException;
}

public class ExpenseExtractorCSV implements ExpenseExtractor {
    @Override
    public List<Expense> extractFrom(String filename) throws FileNotFoundException {
        // EXTRACT: Legge file CSV
        // TRANSFORM: Parsifica e converte in oggetti Expense
        // LOAD: (delegato al Service) Inserisce nel repository
        List<Expense> expenses = new ArrayList<>();
        FileReader reader = new FileReader(filename);

        while(reader.hasNext()) {
            String line = reader.readString();
            Expense expense = parseLineToExpense(line);
            if(expense.isValid()) {
                expenses.add(expense);
            }
        }
        return expenses;
    }
}
```

**Formato CSV supportato:**
```csv
date,description,value,category
2025-01-10,Fornitura alimentari,250.00,FOOD
2025-01-11,Bolletta elettrica,180.50,SERVICES
2025-01-12,Stipendio receptionist,1500.00,SALARIES
```

### 4. Service Layer Pattern

Layer intermedio per business logic:

```java
public class ExpenseService {
    private ExpenseRepository repository;
    private ExpenseExtractor extractor;

    public void importFromCSV(String filename) throws FileNotFoundException {
        List<Expense> expenses = extractor.extractFrom(filename);
        for(Expense expense : expenses) {
            if(expense.isValid()) {
                repository.insert(expense);
            } else {
                logValidationErrors(expense);
            }
        }
    }

    public double calculateTotalExpenses() {
        return repository.getGrandTotal();
    }

    public Map<ExpenseCategory, Double> getTotalsByCategory() {
        // Business logic per aggregazioni
    }
}
```

### 5. Singleton Pattern

Gestione unica connessione database:

```java
public class ConnectionFactory {
    private static Connection instance = null;

    private ConnectionFactory() {} // Costruttore privato

    public static Connection getInstance() {
        if(instance == null) {
            instance = DriverManager.getConnection("jdbc:sqlite:grottammare.db");
        }
        return instance;
    }
}
```

### 6. Template Method Pattern

Validazione gerarchica nelle entità:

```java
public abstract class Entity {
    public final boolean isValid() {
        return getErrors().isEmpty();
    }

    public abstract List<String> getErrors(); // Template method
}

public class Guest extends Entity {
    @Override
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();
        // Step 1: Mandatory fields
        if (isMissing(firstName)) errors.add("Nome mancante");
        // Step 2: Format validation
        if (!hasValidSSN()) errors.add("CF non valido");
        // Step 3: Business rules
        if (getAge() < 18) errors.add("Età minima 18 anni");
        return errors;
    }
}
```

### 7. View Pattern (Presentation Layer)

Separazione logica di presentazione con classi View astratte:

```java
// Classe astratta base per tutte le view
public abstract class GuestView {
    protected String template;
    protected String templateContent;

    public GuestView(String template) {
        this.template = template;
        this.templateContent = Template.load(template);
    }

    public abstract String render(Guest guest);

    public String render(List<Guest> guests) {
        String result = "";
        for(Guest guest : guests)
            result += render(guest) + "\n";
        return result;
    }
}

// Implementazione concreta
public class GuestViewTxtBasic extends GuestView {
    @Override
    public String render(Guest guest) {
        return Template.render(templateContent, guest);
    }
}

// Factory per View
public class GuestViewFactory {
    public static GuestView make() {
        return new GuestViewTxtBasic("guestTemplate.txt");
    }
}
```

**Vantaggi:**
- ✅ Separazione Model-View-Controller completa
- ✅ Template intercambiabili (txt, HTML, JSON)
- ✅ Facilita testing della logica di presentazione
- ✅ View configurabili via Factory

---

## 💾 Modello Dati

### Guest (Ospite)

```java
class Guest extends Entity {
    private int id;
    private String firstName;      // Nome
    private String lastName;       // Cognome
    private String ssn;            // Codice Fiscale (16 caratteri)
    private LocalDate dateOfBirth; // Data di nascita
    private String address;        // Indirizzo
    private String city;           // Città
    private String phoneNumber;    // Telefono

    // Validazione:
    // - CF italiano: [A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]
    // - Età >= 18 anni
    // - Tutti i campi obbligatori
}
```

**Tabella SQLite:**
```sql
CREATE TABLE guests (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    ssn TEXT NOT NULL UNIQUE,
    date_of_birth TEXT NOT NULL,
    address TEXT NOT NULL,
    city TEXT NOT NULL,
    phone_number TEXT
);
```

### Room (Camera)

```java
class Room extends Entity {
    private int id;
    private String name;           // Nome camera
    private String description;    // Descrizione
    private double pricePerNight;  // Tariffa per notte

    // Validazione:
    // - Nome e descrizione obbligatori
    // - Prezzo > 0
}
```

**Tabella SQLite:**
```sql
CREATE TABLE rooms (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    price_per_night REAL NOT NULL
);
```

### Expense (Spesa)

```java
class Expense extends Entity {
    private int id;
    private LocalDate date;        // Data spesa
    private String description;    // Descrizione
    private double value;          // Importo
    private ExpenseCategory category; // Categoria

    // Validazione:
    // - Data obbligatoria
    // - Valore > 0
    // - Categoria valida (enum)
}

enum ExpenseCategory {
    FOOD("Alimentari"),
    SERVICES("Servizi e Utilities"),
    SALARIES("Stipendi");

    private final String description;
}
```

**Tabella SQLite:**
```sql
CREATE TABLE expenses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date TEXT NOT NULL,
    description TEXT NOT NULL,
    value REAL NOT NULL,
    category TEXT NOT NULL
);
```

---

## 🛠️ Tecnologie Utilizzate

### Core Java
- **Java SE 17+** - Linguaggio base
- **OOP Avanzato** - Ereditarietà, Polimorfismo, Incapsulamento, Astrazione
- **Collections Framework** - ArrayList, List, Map
- **LocalDate API** - Gestione date moderne
- **Regex** - Validazione pattern (Codice Fiscale)
- **Exception Handling** - Gestione robusta errori

### Database
- **SQLite** - Database embedded relazionale
- **JDBC** - Java Database Connectivity
- **SQL** - Query e DDL

### Testing
- **JUnit 5** - Framework testing
- **Unit Testing** - Test validazione, ETL, repository

### Design Patterns
- MVC, Repository, Factory, Service Layer, ETL, Singleton, Template Method, View Pattern (Presentation)

---

## 📂 Struttura Progetto

```
GrottammareB&B/
├── src/
│   ├── com/generation/library/       # Libreria utility riutilizzabile
│   │   ├── Console.java              # I/O console
│   │   ├── Entity.java               # Classe base validazione
│   │   ├── FileReader.java           # Lettura file
│   │   ├── FileWriter.java           # Scrittura file
│   │   └── Template.java             # Template rendering
│   │
│   └── com/generation/gbb/           # Package principale applicazione
│       ├── controller/               # Layer controller e service
│       │   ├── Main.java             # Entry point, menu principale
│       │   ├── GuestService.java     # Business logic ospiti
│       │   ├── RoomService.java      # Business logic camere
│       │   └── ExpenseService.java   # Business logic spese
│       │
│       ├── view/                     # Layer presentation (MVC)
│       │   ├── MenuView.java                 # Menu principale applicazione
│       │   ├── GuestView.java                # View astratta ospiti
│       │   ├── GuestViewTxtBasic.java        # Implementazione txt ospiti
│       │   ├── GuestViewFactory.java         # Factory view ospiti
│       │   ├── RoomView.java                 # View astratta camere
│       │   ├── RoomViewTxtBasic.java         # Implementazione txt camere
│       │   ├── RoomViewFactory.java          # Factory view camere
│       │   ├── ExpenseView.java              # View astratta spese
│       │   ├── ExpenseViewTxtBasic.java      # Implementazione txt spese
│       │   └── ExpenseViewFactory.java       # Factory view spese
│       │
│       ├── model/                    # Layer modello (entità + database)
│       │   ├── entities/             # Entità business
│       │   │   ├── Guest.java                # Entità ospite
│       │   │   ├── Room.java                 # Entità camera
│       │   │   ├── Expense.java              # Entità spesa
│       │   │   └── ExpenseCategory.java      # Enum categorie spese
│       │   │
│       │   └── database/             # Layer database
│       │       └── ConnectionFactory.java    # Singleton connessione DB
│       │
│       ├── repository/               # Layer persistenza
│       │   ├── GuestRepository.java          # Interfaccia repository ospiti
│       │   ├── SQLGuestRepository.java       # Implementazione SQL ospiti
│       │   ├── DummyGuestRepository.java     # Implementazione in-memory ospiti
│       │   ├── RoomRepository.java           # Interfaccia repository camere
│       │   ├── SQLRoomRepository.java        # Implementazione SQL camere
│       │   ├── DummyRoomRepository.java      # Implementazione in-memory camere
│       │   ├── ExpenseRepository.java        # Interfaccia repository spese
│       │   ├── SQLExpenseRepository.java     # Implementazione SQL spese
│       │   ├── DummyExpenseRepository.java   # Implementazione in-memory spese
│       │   ├── GuestRepositoryFactory.java   # Factory DI ospiti
│       │   ├── RoomRepositoryFactory.java    # Factory DI camere
│       │   └── ExpenseRepositoryFactory.java # Factory DI spese
│       │
│       ├── etl/                      # Layer ETL (import dati)
│       │   ├── ExpenseExtractor.java         # Interfaccia ETL spese
│       │   ├── ExpenseExtractorCSV.java      # Implementazione CSV spese
│       │   ├── ExpenseExtractorFactory.java  # Factory DI extractor spese
│       │   ├── GuestExtractor.java           # Interfaccia ETL ospiti
│       │   ├── GuestExtractorCSV.java        # Implementazione CSV ospiti
│       │   └── GuestExtractorFactory.java    # Factory DI extractor ospiti
│       │
│       ├── test/                     # Layer testing
│       │   ├── GuestTest.java                # Test validazione ospiti
│       │   ├── GuestExtractorCSVTest.java    # Test import CSV ospiti
│       │   ├── ExpenseExtractorCSVTest.java  # Test import CSV spese
│       │   └── TestConnectionFactory.java    # Test connessione DB
│       │
│       └── demo/                     # Programmi dimostrativi
│           └── InsertGuestDemo.java          # Demo insert ospite
│
├── importData/                       # Directory dati import
│   ├── expenses.csv                  # File CSV spese
│   └── guesthotelcentrale.csv        # File CSV ospiti
│
├── template/                         # Template rendering output
│
├── queries/                          # Query SQL di esempio
│
├── grottammare.db                    # Database SQLite
│
├── .classpath                        # Eclipse classpath
├── .project                          # Eclipse project
└── README.md                         # Questo file
```

---

## 🚀 Installazione ed Esecuzione

### Prerequisiti

- **Java JDK 17+** installato
- **SQLite** (incluso nel JDBC driver)
- **JUnit 5** per eseguire i test
- **Eclipse IDE** (opzionale, configurazione inclusa)

### Compilazione

```bash
# Compilazione di tutti i file Java
cd GrottammareB&B/src
javac -d ../bin com/generation/library/*.java com/generation/gbb/**/*.java

# Oppure da Eclipse: Build Automatically attivo
```

### Esecuzione

```bash
# Esecuzione applicazione principale
cd GrottammareB&B
java -cp bin com.generation.gbb.controller.Main

# Oppure da Eclipse: Run As > Java Application (Main.java)
```

### Setup Database

Il database SQLite viene creato automaticamente all'avvio. Schema:

```sql
-- Tabella ospiti
CREATE TABLE IF NOT EXISTS guests (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    ssn TEXT NOT NULL UNIQUE,
    date_of_birth TEXT NOT NULL,
    address TEXT NOT NULL,
    city TEXT NOT NULL,
    phone_number TEXT
);

-- Tabella camere
CREATE TABLE IF NOT EXISTS rooms (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    price_per_night REAL NOT NULL
);

-- Tabella spese
CREATE TABLE IF NOT EXISTS expenses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date TEXT NOT NULL,
    description TEXT NOT NULL,
    value REAL NOT NULL,
    category TEXT NOT NULL
);
```

### Import Dati Iniziali

```bash
# I file CSV nella directory importData/ vengono importati automaticamente
# all'avvio dell'applicazione se le tabelle sono vuote

importData/expenses.csv              # Spese di esempio
importData/guesthotelcentrale.csv    # Ospiti di esempio
```

---

## 🎯 Funzionalità

### Menu Principale

```
=== GROTTAMMARE B&B - GESTIONE ===

1. Gestione Ospiti
2. Gestione Camere
3. Gestione Spese
4. Report e Statistiche
Q. Esci

Scelta: _
```

### 1. Gestione Ospiti

- **Visualizza tutti gli ospiti** - Lista completa con dettagli
- **Cerca per ID** - Ricerca puntuale
- **Cerca per Codice Fiscale** - Validazione e ricerca CF
- **Cerca per cognome** - Ricerca parziale case-insensitive
- **Cerca per città** - Filtra per provenienza
- **Inserisci nuovo ospite** - Form con validazione completa
- **Aggiorna ospite** - Modifica dati esistenti
- **Elimina ospite** - Cancellazione con conferma

**Validazioni:**
- Codice Fiscale italiano valido (regex 16 caratteri)
- Età >= 18 anni
- Tutti i campi obbligatori compilati

### 2. Gestione Camere

- **Visualizza tutte le camere** - Catalogo camere
- **Cerca per ID** - Dettaglio singola camera
- **Cerca per nome/descrizione** - Ricerca testuale
- **Cerca per prezzo massimo** - Filtra per budget
- **Inserisci nuova camera** - Creazione camera
- **Aggiorna camera** - Modifica dettagli e tariffe
- **Elimina camera** - Rimozione camera

### 3. Gestione Spese

- **Visualizza tutte le spese** - Registro completo
- **Cerca per categoria** - FOOD / SERVICES / SALARIES
- **Cerca per intervallo date** - Range temporale
- **Inserisci nuova spesa** - Registrazione spesa
- **Importa da CSV** - Caricamento massivo
- **Totale per categoria** - Aggregazione
- **Totale generale** - Somma globale

**Formato CSV:**
```csv
date,description,value,category
2025-01-10,Fornitura alimentari,250.00,FOOD
2025-01-11,Bolletta elettrica,180.50,SERVICES
```

### 4. Report e Statistiche

- Totale spese per categoria
- Totale generale spese
- Statistiche occupazione (future)
- Report mensili/annuali (future)

---

## 🧪 Test

### Esecuzione Test

```bash
# Compilazione test
cd GrottammareB&B/src
javac -cp .:../lib/junit-5.jar -d ../bin com/generation/gbb/test/*.java

# Esecuzione singolo test
java -cp ../bin:../lib/junit-5.jar org.junit.platform.console.ConsoleLauncher \
     --select-class com.generation.gbb.test.GuestTest

# Esecuzione tutti i test
java -cp ../bin:../lib/junit-5.jar org.junit.platform.console.ConsoleLauncher \
     --scan-classpath

# Oppure da Eclipse: Run As > JUnit Test
```

### Test Disponibili

#### GuestTest.java
```java
@Test void testValidazioneSSN()          // Regex CF italiano
@Test void testValidazioneEta()          // Calcolo età >=18
@Test void testValidazioneCompleta()     // Tutti i controlli
@Test void testCampiobbligatori()        // Nome, cognome, etc.
```

#### ExpenseExtractorCSVTest.java
```java
@Test void testImportCSV()               // Lettura file CSV
@Test void testParsingValido()           // Conversione corretta
@Test void testValidazioneSpese()        // Controllo entità valide
@Test void testCategorieEnum()           // Mappatura categorie
@Test void testValoriNegativi()          // Edge case importi negativi
```

#### GuestExtractorCSVTest.java
```java
@Test void testImportGuestiCSV()         // Import anagrafica
@Test void testValidazioneCF()           // Verifica CF da file
@Test void testEtaMinorenne()            // Rigetto minori di 18 anni
```

#### TestConnectionFactory.java
```java
@Test void testSingletonInstance()       // Pattern Singleton
@Test void testDatabaseConnection()      // Connettività DB
@Test void testCreateTables()            // Creazione schema
```

**Coverage:** Validazione campi, edge cases, pattern, database operations.

---

## 🗄️ Database

### Schema SQLite

Il database `grottammare.db` viene creato automaticamente con schema completo.

### Query di Esempio

```sql
-- Tutti gli ospiti maggiorenni
SELECT * FROM guests
WHERE date('now') - date(date_of_birth) >= 18;

-- Spese per categoria
SELECT category, SUM(value) as total
FROM expenses
GROUP BY category;

-- Camere sotto i 100€
SELECT * FROM rooms
WHERE price_per_night < 100.0;

-- Ospiti da Milano
SELECT * FROM guests
WHERE city LIKE '%Milano%';

-- Spese di gennaio 2025
SELECT * FROM expenses
WHERE date BETWEEN '2025-01-01' AND '2025-01-31';
```

### Tool Database Consigliati

- **DB Browser for SQLite** - GUI completa
- **SQLite Studio** - Client leggero
- **DBeaver** - Universal database tool

```bash
# Accesso via CLI
sqlite3 grottammare.db

# Query di test
sqlite> .tables
sqlite> SELECT COUNT(*) FROM guests;
sqlite> .schema guests
```

---

## 🔧 Validazione Dati

### Codice Fiscale Italiano

**Pattern Regex:** `[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]`

Struttura 16 caratteri:
1. **6 lettere** - Consonanti cognome (3) + nome (3)
2. **2 cifre** - Anno nascita (YY)
3. **1 lettera** - Mese nascita (A=Gen, B=Feb, ..., L=Dic)
4. **2 cifre** - Giorno nascita + genere (M: 01-31, F: 41-71)
5. **4 caratteri** - Codice comune + carattere controllo

Esempio valido: `RSSMRA85T10A562S`

### Validazione Età

```java
public int getAge() {
    return Period.between(dateOfBirth, LocalDate.now()).getYears();
}

public boolean isMaggiorenne() {
    return getAge() >= 18;
}
```

### Validazione Spese

- **Data:** Formato ISO-8601 (yyyy-MM-dd), non futura
- **Valore:** Numero positivo > 0
- **Categoria:** Enum valido (FOOD, SERVICES, SALARIES)
- **Descrizione:** Non vuota

---

## 🚀 Estendibilità

### Repository Layer

Il design permette facilmente sostituzione implementazioni:

```java
// Factory configurabile per diverse implementazioni
public class GuestRepositoryFactory {
    public static GuestRepository make() {
        return new SQLGuestRepository();      // SQLite (produzione)
        // return new DummyGuestRepository(); // In-memory (testing)
        // return new MySQLGuestRepository(); // MySQL
        // return new MongoGuestRepository(); // MongoDB
        // return new RestGuestRepository();  // REST API
    }
}
```

**Implementazioni disponibili:**
- ✅ **SQLGuestRepository** - Persistenza SQLite con JDBC
- ✅ **DummyGuestRepository** - In-memory per testing/demo
- ✅ **SQLRoomRepository** - Persistenza camere SQLite
- ✅ **DummyRoomRepository** - In-memory camere
- ✅ **SQLExpenseRepository** - Persistenza spese SQLite
- ✅ **DummyExpenseRepository** - In-memory spese

**Implementazioni possibili future:**
- 🔄 MySQL / PostgreSQL
- 🔄 JPA / Hibernate
- 🔄 MongoDB (NoSQL)
- 🔄 REST API (microservizi)
- 🔄 Redis (cache)

### View Layer

View estensibili a diversi formati di output:

```java
// Factory per diverse implementazioni di view
public class GuestViewFactory {
    public static GuestView make() {
        return new GuestViewTxtBasic("guestTemplate.txt");  // Console text
        // return new GuestViewHTML("guestTemplate.html");  // HTML output
        // return new GuestViewJSON();                      // JSON API
        // return new GuestViewXML();                       // XML export
        // return new GuestViewPDF();                       // PDF report
    }
}
```

**Implementazioni possibili:**
- ✅ TxtBasic - Output console testuale (implementato)
- 🔄 HTML - Pagine web
- 🔄 JSON - REST API response
- 🔄 XML - Export dati
- 🔄 PDF - Report stampabili
- 🔄 Excel - Fogli di calcolo

### ETL Layer

Extractor estensibile a diverse fonti:

```java
// Nuovi formati supportabili senza modificare Service
- ExpenseExtractorJSON   // Import da JSON
- ExpenseExtractorXML    // Import da XML
- ExpenseExtractorXLSX   // Import da Excel
- ExpenseExtractorAPI    // Import da REST API
- ExpenseExtractorJDBC   // Migrazione database
```

Tutte le modifiche avvengono tramite **Factory** senza toccare `Main` o `Service`.

### Nuove Funzionalità Pianificate

- 📅 **Prenotazioni** - Sistema booking camere con date
- 💳 **Pagamenti** - Tracciamento pagamenti ospiti
- 📊 **Dashboard Analytics** - Grafici e KPI
- 📧 **Notifiche Email** - Alert e conferme
- 🔐 **Autenticazione** - Login multi-utente
- 🌐 **Web Interface** - Frontend Spring Boot
- 📱 **Mobile App** - Companion app gestione

---

## 📚 Concetti Java Dimostrati

### OOP Avanzato
- ✅ Ereditarietà (`Entity` base class)
- ✅ Polimorfismo (interfacce `Repository`, `Extractor`)
- ✅ Incapsulamento (private fields, getter/setter)
- ✅ Astrazione (interfacce business layer)

### Collections Framework
- ArrayList per liste dinamiche
- List per astrazione collezioni
- Map per aggregazioni (future)

### Date/Time API
- LocalDate per date moderne
- Period per calcolo età
- DateTimeFormatter per parsing

### Exception Handling
- FileNotFoundException per file CSV
- SQLException per errori database
- Custom validation errors

### Design Patterns
- MVC, Repository, Factory, Service Layer, ETL, Singleton, Template Method, View Pattern

### JDBC & Database
- Connection management
- PreparedStatement (SQL injection prevention)
- ResultSet manipulation
- Transaction handling (future)

---

## 👨‍💻 Autore

**Viorica Gabriela Hacman**
- 🎓 Generation Italy - Java Full Stack Developer Bootcamp
- 💼 [LinkedIn](https://www.linkedin.com/in/viorica-gabriela-hacman-63a412267/)
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

## 📝 Note di Sviluppo

Questo progetto è stato sviluppato come **esercizio didattico avanzato** nel contesto del bootcamp Generation Italy. Dimostra:

- Architettura enterprise scalabile
- Separazione delle responsabilità
- Principi SOLID
- Design patterns reali
- Testability e manutenibilità

---

## 🙏 Ringraziamenti

- **Generation Italy** per il percorso formativo
- **Docenti e mentor** per guida tecnica
- **Compagni di corso** per collaborazione

---

## 📄 Licenza

📚 **Uso Educativo** - Progetto didattico Generation Italy.

---

<div align="center">

**Grottammare B&B Management System**
*Enterprise Java Application with MVC, Repository Pattern & SQLite*

Sviluppato con ❤️ durante Generation Italy Bootcamp 2025

</div>
