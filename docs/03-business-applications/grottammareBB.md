# Grottammare B&B - Sistema Gestionale Completo

Sistema console-based per la gestione completa di un Bed & Breakfast che implementa architettura MVC, Repository Pattern, Factory per Dependency Injection, Service Layer ed ETL Pattern. Include validazione dati con regex per Codice Fiscale italiano, gestione spese con import CSV e controllo età maggiorenne.

## Architettura Software

L'applicazione segue il paradigma MVC con separazione chiara dei ruoli:

```
Main (Controller)
   ↓ Dependency Injection (Factory Pattern)
GuestService    ←→ GuestRepository ←→ Guest (Entity + Validazione)
RoomService     ←→ RoomRepository  ←→ Room  (Entity + Validazione)
ExpenseService  ←→ ExpenseRepository ←→ Expense (Entity + Validazione)
   ↑                    ↑
In-Memory Implementation + ETL (CSV Import)
```

**Livelli di astrazione:**
- **Controller** (`Main.java`): Orchestrazione menu interattivo e I/O console
- **Service Layer** (`*Service.java`): Business logic e validazione flusso
- **Model** (`Guest.java`, `Room.java`, `Expense.java`): Entità con validazione incorporata
- **Repository** (Interfacce + Dummy impl.): Astrazione persistenza
- **ETL Layer** (`*Extractor.java`): Importazione dati da CSV
- **Factory** (`*RepositoryFactory.java`, `*ExtractorFactory.java`): Creazione oggetti configurabile[5][6][8][9][1]

## Design Patterns Implementati

### 1. Repository Pattern
Interfacce `GuestRepository`, `RoomRepository` e `ExpenseRepository` astraggono l'accesso dati:

```java
public interface GuestRepository {
    List<Guest> findAll();
    Guest findById(int id);
    Guest findBySSN(String ssn);     // Codice Fiscale
    List<Guest> findBySurnameContaining(String part);
    List<Guest> findByCity(String city);
    // CRUD operations (insert, update, delete)
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

**Vantaggi**: Testability (mock), estendibilità (JDBC, JPA), inversione dipendenze.

### 2. Factory Pattern
```java
public class GuestRepositoryFactory {
    public static GuestRepository make() {
        return new DummyGuestRepository();  // Configurabile
    }
}

public class ExpenseExtractorFactory {
    public static ExpenseExtractor make() {
        return new ExpenseExtractorCSV();  // Configurabile: CSV, JSON, XML, etc.
    }
}
```
Permette sostituzione implementazioni senza modificare `Main` o `Service`.

### 3. ETL Pattern (Extract-Transform-Load)
Interfaccia `ExpenseExtractor` astrae l'estrazione dati da diverse fonti:

```java
public interface ExpenseExtractor {
    List<Expense> extractFrom(String source) throws FileNotFoundException;
}

public class ExpenseExtractorCSV implements ExpenseExtractor {
    @Override
    public List<Expense> extractFrom(String filename) throws FileNotFoundException {
        // Legge file CSV, parsifica righe, converte in oggetti Expense
        // Format: date,description,value,category
        FileReader reader = new FileReader(filename);
        while(reader.hasNext()) {
            String line = reader.readString();
            Expense e = convertToObject(line);  // Parsing + validazione
            result.add(e);
        }
        return result;
    }
}
```

**Vantaggi**: Disaccoppiamento source dati, estensibile a JSON/XML/API, riuso della logica di parsing.[ETL]

### 4. Service Layer Pattern
Layer intermedio tra Controller e Repository per business logic:

```java
public class ExpenseService {
    private ExpenseRepository repository;
    private ExpenseExtractor extractor;

    public void importFromCSV(String filename) throws FileNotFoundException {
        List<Expense> expenses = extractor.extractFrom(filename);
        for(Expense e : expenses) {
            if(e.isValid()) {
                repository.insert(e);
            }
        }
    }

    public double calculateTotalExpenses() {
        return repository.getGrandTotal();
    }
}
```

**Vantaggi**: Separazione responsabilità, riutilizzabilità business logic, transazionalità futura.[Service]

### 5. Template Method (Validazione)
Classe base `Entity` (ereditata) definisce algoritmo validazione:

```java
@Override
public List<String> getErrors() {
    List<String> errors = new ArrayList<>();
    // Template steps: mandatory fields → format validation → business rules
    if (isMissing(firstName)) errors.add("Nome mancante");
    if (!hasValidSSN()) errors.add("Codice Fiscale non valido");
    if (getAge() < 18) errors.add("L'ospite deve essere maggiorenne");
    return errors;
}
```
`isValid()` = `getErrors().isEmpty()`

## Modello Dati

### Guest (Ospite)
```java
class Guest extends Entity {
    String firstName, lastName, ssn;        // Anagrafica
    LocalDate dateOfBirth;                  // Data nascita
    String address, city, phoneNumber;      // Contatti
    // + validazione CF italiano e età ≥18
}
```

### Room (Camera)
```java
class Room extends Entity {
    String name, description;               // Identificazione
    double pricePerNight;                   // Tariffa
    // + validazione campi obbligatori
}
```

### Expense (Spesa) - NUOVA ENTITÀ
```java
class Expense extends Entity {
    LocalDate date;                         // Data spesa
    String description;                     // Descrizione
    double value;                           // Importo
    ExpenseCategory category;               // FOOD | SERVICES | SALARIES

    // Validazione: date obbligatoria, value > 0, category valida
}

enum ExpenseCategory {
    FOOD("Alimentari"),
    SERVICES("Servizi e Utilities"),
    SALARIES("Stipendi");
}
```

## Validazione Dati

### Codice Fiscale Italiano
Regex pattern: `[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]`
- 6 lettere (cognome+nome)
- 2 cifre (anno nascita)
- 1 lettera (mese: A=Gennaio...L=Dicembre)
- 2 cifre (giorno + genere)
- 4 caratteri (codice catasto + controllo)[10][2]

### Regole Business

**Guest:**
```
OBBLIGATORI: nome, cognome, CF, data nascita, indirizzo, città
ETÀ: ≥ 18 anni (LocalDate.now() - DOB)
SSN: 16 caratteri, formato italiano valido
```

**Expense:**
```
OBBLIGATORI: data, descrizione, categoria
VALORE: > 0 (importo positivo)
CATEGORIA: enum ExpenseCategory (FOOD, SERVICES, SALARIES)
DATA: formato ISO 8601 (yyyy-MM-dd) per import CSV
```

## Repository Implementations

**Dummy Repository** (in-memory):
- 13 ospiti con CF validi reali italiani
- 4 stanze con pricing progressivo (Economy → Suite)
- Spese importate da CSV con aggregazioni per categoria
- Ricerca case-insensitive su cognome/città e nome/descrizione[4][7]

**Metodi di Ricerca Implementati:**
```
Guest:   findAll(), findById(), findBySSN(), findBySurnameContaining(), findByCity()
Room:    findAll(), findById(), findByNameOrDescriptionContaining(), findByPriceLesserThan()
Expense: findAll(), findById(), findByCategory(), findByDateRange(), getTotalByCategory(), getGrandTotal()
```

## Funzionalità ETL - Import CSV

### Formato File Spese
```csv
2025-01-10,Fornitura alimentari,250,FOOD
2025-01-11,Bolletta elettrica,180,SERVICES
2025-01-12,Stipendio receptionist,1500,SALARIES
```

**Schema:** `date,description,value,category`

### Processo di Import
1. **Extract**: `ExpenseExtractorCSV` legge file linea per linea
2. **Transform**: Parsing e conversione in oggetti `Expense` validati
3. **Load**: `ExpenseService` inserisce solo spese valide nel repository
4. **Validation**: Controllo formato data, valore positivo, categoria valida

### Test Unitari
```java
@Test
public void testExpenseExtractorCSV() throws FileNotFoundException {
    ExpenseExtractor extractor = new ExpenseExtractorCSV();
    List<Expense> expenses = extractor.extractFrom("importData/expenses.csv");

    assertTrue(expenses.size() > 0);
    assertTrue(expenses.get(0).isValid());
    assertEquals(ExpenseCategory.FOOD, expenses.get(0).getCategory());
}
```

## Flusso Applicativo

1. **Bootstrap**: Factory crea repository e extractor, Service layer inizializzato
2. **Import CSV**: ExpenseService carica spese da file CSV all'avvio
3. **Menu Loop**: `do-while` fino a "Q", switch-case comandi multipli (numeri + testo)
4. **Business Operations**: Service layer orchestra operazioni tra repository
5. **Ricerca**: Chiamata repository via service → rendering console formattato
6. **Reporting**: Aggregazioni spese per categoria, totali, statistiche[1]

## Test Unitari

### GuestTest.java (JUnit 5)
```java
@Test void testValidazioneSSN()     // Regex CF italiano
@Test void testValidazioneEta()      // Calcolo età ≥18y
@Test void testValidazioneCompleta() // Tutti i controlli
```

### ExpenseExtractorCSVTest.java (JUnit 5)
```java
@Test void testImportCSV()           // Lettura file CSV
@Test void testParsingValido()       // Conversione corretta dati
@Test void testValidazioneSpese()    // Controllo entità valide
@Test void testCategorieEnum()       // Mappatura categorie
```

### GuestExtractorCSVTest.java (JUnit 5)
```java
@Test void testImportGuestiCSV()     // Import anagrafica ospiti
@Test void testValidazioneCF()       // Verifica CF da file
```

Coverage: validazione incrementale campi, edge cases (minorenne, CF malformato, importi negativi, categorie invalide)[10]



Tutte le modifiche avvengono tramite Factory senza toccare `Main` o `Service`.

## Compilazione ed Esecuzione

```bash
# Compilazione
javac -d . com/generation/gbb/**/*.java com/generation/library/*.java

# Esecuzione
java com.generation.gbb.controller.Main

# Test JUnit
javac -cp .:junit.jar com/generation/gbb/test/*.java
java -cp .:junit.jar org.junit.runner.JUnitCore com.generation.gbb.test.ExpenseExtractorCSVTest
```

### Struttura Directory Richiesta
```
GrottammareB&B/
├── src/
│   ├── com/generation/gbb/
│   └── com/generation/library/
├── importData/
│   ├── expenses.csv       # File spese da importare
│   └── guests.csv         # File ospiti (opzionale)
└── template/              # Template rendering (future)
```

## Tecnologie e Concetti

### Design Patterns
✅ **MVC** - Separazione Controller/Model/View
✅ **Repository** - Astrazione accesso dati
✅ **Factory** - Dependency Injection configurabile
✅ **Service Layer** - Business logic centralizzata
✅ **ETL** - Extract-Transform-Load per import dati
✅ **Template Method** - Validazione gerarchica

### Java Features
- **OOP Avanzato**: Ereditarietà, Polimorfismo, Incapsulamento
- **Enum**: Type-safe constants (ExpenseCategory)
- **LocalDate API**: Date manipulation moderna
- **Regex**: Validazione pattern complessi (CF)
- **Collections**: ArrayList, List, Stream potenziali
- **Exception Handling**: FileNotFoundException, validazione robusta

**Progetto didattico Generation Italy** - Dimostrazione completa di Repository Pattern, Service Layer, ETL Pattern, Dependency Injection, MVC e validazione enterprise in Java.

