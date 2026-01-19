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
AssetService    ←→ AssetRepository  ←→ Asset (Entity + Validazione)
   ↑                    ↑                      ↑
In-Memory Implementation + ETL (CSV Import) + Caching (Total/Partial)
```

**Livelli di astrazione:**
- **Controller** (`Main.java`): Orchestrazione menu interattivo e I/O console
- **Service Layer** (`*Service.java`): Business logic e validazione flusso
- **Model** (`Guest.java`, `Room.java`, `Expense.java`, `Asset.java`): Entità con validazione incorporata
- **Repository** (Interfacce + SQL/Dummy impl.): Astrazione persistenza con caching
- **Cache Layer** (`TotalCache.java`, `PartialCache.java`): Ottimizzazione accesso dati
- **ETL Layer** (`*Extractor.java`): Importazione dati da CSV
- **Factory** (`*RepositoryFactory.java`, `*ExtractorFactory.java`): Creazione oggetti configurabile
- **Profiling** (`ProfilingMonitor.java`): Monitoraggio performance query

## Design Patterns Implementati

### 1. Repository Pattern
Interfacce `GuestRepository`, `RoomRepository`, `ExpenseRepository` e `AssetRepository` astraggono l'accesso dati:

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

public interface AssetRepository {
    List<Asset> findAll();
    Asset findById(int id);
    Asset insert(Asset asset);
    Asset update(Asset asset);
    boolean delete(int id);
    void initTable();  // Database initialization
}
```

**Vantaggi**: Testability (mock), estendibilità (JDBC, JPA), inversione dipendenze.

**Implementazioni**:
- `DummyGuestRepository`, `DummyRoomRepository`, `DummyExpenseRepository`: In-memory per testing
- `SQLAssetRepository`: Persistenza SQLite con TotalCache integrata

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

### 5. Cache Pattern (Ottimizzazione Performance)
Due strategie di caching implementate con Generics:

#### TotalCache<X extends Entity>
Cache completa che memorizza l'intera tabella in memoria:

```java
public class TotalCache<X extends Entity> {
    List<X> content = new ArrayList<>();

    public X findById(int id) {
        // Ricerca in memoria O(n) - nessuna query DB
        for(X x : content)
            if(x.getId() == id)
                return x;
        return null;
    }

    public List<X> findAll() {
        return content;  // Ritorna tutto dalla cache
    }
}
```

**Utilizzo in SQLAssetRepository**:
```java
public class SQLAssetRepository implements AssetRepository {
    TotalCache<Asset> cache = new TotalCache<>();

    @Override
    public List<Asset> findAll() {
        // Prima chiamata: query DB + popola cache
        if(cache.getContent().isEmpty())
            cache.setContent(findWhere("1=1"));
        // Chiamate successive: 0 query DB
        return cache.getContent();
    }

    @Override
    public Asset findById(int id) {
        if(cache.getContent().isEmpty())
            findAll();  // Popola cache
        return cache.findById(id);  // Accesso immediato
    }
}
```

#### PartialCache<X extends Entity>
Cache parziale con politica FIFO (First In First Out):

```java
public class PartialCache<X extends Entity> {
    int maxSize;  // Limite elementi in cache
    List<X> content = new ArrayList<>();

    public void addElement(X x) {
        // Evita duplicati
        if(contains(x)) return;

        content.add(x);  // Aggiungi in coda

        // Rimuovi elemento più vecchio se superi maxSize
        if(content.size() > maxSize)
            content.remove(0);  // FIFO: rimuovi dalla testa
    }

    public X findById(int id) {
        // Ricerca solo negli elementi cached
        for(X x : content)
            if(x.getId() == id)
                return x;
        return null;  // Cache Miss
    }
}
```

**Strategie di Cache**:

| Tipo | TotalCache | PartialCache |
|------|------------|--------------|
| **Capacità** | Intera tabella | N elementi (configurable) |
| **Politica** | Lazy-loading | FIFO (Circular Buffer) |
| **Hit Rate** | 100% dopo 1° caricamento | Dipende da maxSize e pattern accesso |
| **Memoria** | O(n) - tutte le righe | O(maxSize) - controllata |
| **Use Case** | Tabelle piccole/statiche | Tabelle grandi con hot data |

**Vantaggi**:
- **Performance**: Riduzione drastica query DB (0 query dopo warm-up per TotalCache)
- **Scalabilità**: PartialCache controlla memoria su dataset grandi
- **Generics**: Riutilizzabili per qualsiasi Entity (`TotalCache<Guest>`, `PartialCache<Room>`)
- **Sincronizzazione**: Insert/Update/Delete mantengono cache allineata

**ProfilingMonitor**: Classe utility per misurare impatto cache:
```java
public class ProfilingMonitor {
    public static int queryNumber = 0;  // Conta query DB
    public static int rowsLoaded = 0;   // Conta righe caricate
}
```

### 6. Template Method (Validazione)
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

### Expense (Spesa)
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

### Asset (Bene/Risorsa)
```java
class Asset extends Entity {
    String name;                            // Nome asset
    String description;                     // Descrizione
    int cost;                               // Costo di acquisto
    int value;                              // Valore corrente

    // Validazione: name e description obbligatori, cost >= 0, value >= 0
    // Override equals() per confronto logico
}
```

**Database**: Tabella `asset` in SQLite con auto-increment ID
**Caching**: TotalCache integrata in SQLAssetRepository per zero-query reads dopo warm-up

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

**Asset:**
```
OBBLIGATORI: nome, descrizione
COSTO: >= 0 (non negativo)
VALORE: >= 0 (non negativo)
EQUALS: Override per confronto logico (name, description, cost, value)
```

## Repository Implementations

**Dummy Repository** (in-memory):
- 13 ospiti con CF validi reali italiani
- 4 stanze con pricing progressivo (Economy → Suite)
- Spese importate da CSV con aggregazioni per categoria
- Ricerca case-insensitive su cognome/città e nome/descrizione

**SQL Repository** (SQLite + JDBC):
- `SQLAssetRepository`: Persistenza con TotalCache integrata
- Tabella `asset` con auto-increment ID
- Sincronizzazione cache su Insert/Update/Delete
- ProfilingMonitor per misurare performance

**Metodi di Ricerca Implementati:**
```
Guest:   findAll(), findById(), findBySSN(), findBySurnameContaining(), findByCity()
Room:    findAll(), findById(), findByNameOrDescriptionContaining(), findByPriceLesserThan()
Expense: findAll(), findById(), findByCategory(), findByDateRange(), getTotalByCategory(), getGrandTotal()
Asset:   findAll(), findById(), findByName(), findByCostLessThan(), findByValueGreaterThan()
         insert(), update(), delete(), initTable()
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

### Test Unitari ETL
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

## Gestione Asset (Beni/Risorse)

### Funzionalità Asset Management
L'entità **Asset** rappresenta beni e risorse del B&B (mobili, elettrodomestici, attrezzature) con:

**Operazioni CRUD complete**:
```java
// Create - Inserimento con validazione
Asset laptop = new Asset(0, "Laptop Dell", "Portatile reception", 800, 750);
assetRepository.insert(laptop);  // ID auto-generato

// Read - Ricerca
List<Asset> all = assetRepository.findAll();  // 0 query dopo warm-up (TotalCache)
Asset found = assetRepository.findById(1);    // Accesso immediato da cache

// Update - Aggiornamento valore (deprezzamento)
laptop.setValue(700);
assetRepository.update(laptop);  // Sincronizza cache automaticamente

// Delete - Dismissione asset
assetRepository.delete(1);  // Rimuove da DB e cache
```

**Ricerche specializzate**:
```java
// Asset per nome
List<Asset> tvs = assetRepository.findByName("TV Samsung");

// Asset con costo inferiore a soglia
List<Asset> cheap = assetRepository.findByCostLessThan(500);

// Asset con valore superiore a soglia (high-value items)
List<Asset> valuable = assetRepository.findByValueGreaterThan(1000);
```

**Database Integration**:
- Tabella `asset` creata automaticamente con `initTable()`
- Schema: `id (PK), name, description, cost, value`
- JDBC con PreparedStatement per SQL injection prevention
- Auto-increment ID gestito via `MAX(id)`

### Performance Optimization con TotalCache

**Prima chiamata (Cold Start)**:
```java
List<Asset> assets = assetRepository.findAll();
// Query DB: SELECT * FROM asset WHERE 1=1
// Popola cache: cache.setContent(result)
// ProfilingMonitor: queryNumber++, rowsLoaded += N
```

**Chiamate successive (Warm State)**:
```java
List<Asset> assets = assetRepository.findAll();
// Query DB: 0 (ritorna da cache)
// Performance: O(1) invece di O(n) query time

Asset asset = assetRepository.findById(5);
// Query DB: 0 (ricerca in cache.content)
// Performance: O(n) ricerca lineare in memoria vs O(log n) B-tree DB
```

**Sincronizzazione Cache**:
```java
// Insert: aggiunge alla cache dopo DB insert
cache.getContent().add(newAsset);

// Update: sostituisce versione in cache
cache.getContent().remove(oldVersion);
cache.getContent().add(newVersion);

// Delete: rimuove da cache
cache.getContent().remove(toDelete);
```

**Metriche ProfilingMonitor**:
```java
ProfilingMonitor.reset();  // Azzera contatori
assetRepository.findAll(); // 1 query
assetRepository.findById(1);  // 0 query (cache hit)
assetRepository.findById(2);  // 0 query (cache hit)

System.out.println("Query: " + ProfilingMonitor.queryNumber);  // Output: 1
System.out.println("Rows: " + ProfilingMonitor.rowsLoaded);    // Output: N
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

### AssetRepositoryTest.java (JUnit 5)
```java
@Test void testInsertAsset()         // Insert con validazione e ID auto-increment
@Test void testFindById()            // Ricerca con TotalCache
@Test void testUpdateAsset()         // Update con sincronizzazione cache
@Test void testDeleteAsset()         // Delete con rimozione da cache
@Test void testCachePerformance()    // Verifica 0 query dopo warm-up
```

Coverage: validazione incrementale campi, edge cases (minorenne, CF malformato, importi negativi, categorie invalide, costi/valori negativi), performance caching



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
│   │   ├── model/entities/        # Guest, Room, Expense, Asset
│   │   ├── repository/            # SQL/Dummy implementations
│   │   ├── repository/interfaces/ # Repository interfaces
│   │   ├── repository/factory/    # Repository factories
│   │   ├── cache/                 # TotalCache, PartialCache
│   │   ├── profiling/             # ProfilingMonitor
│   │   ├── controller/            # Main
│   │   └── test/                  # JUnit tests
│   └── com/generation/library/    # Entity base class
├── importData/
│   ├── expenses.csv               # File spese da importare
│   └── guests.csv                 # File ospiti (opzionale)
├── grottammare.db                 # Database SQLite (asset table)
└── template/                      # Template rendering (future)
```

## Tecnologie e Concetti

### Design Patterns
✅ **MVC** - Separazione Controller/Model/View
✅ **Repository** - Astrazione accesso dati
✅ **Factory** - Dependency Injection configurabile
✅ **Service Layer** - Business logic centralizzata
✅ **ETL** - Extract-Transform-Load per import dati
✅ **Cache Pattern** - TotalCache (full table) e PartialCache (FIFO)
✅ **Template Method** - Validazione gerarchica
✅ **Singleton** - AssetRepositoryFactory con cache condivisa

### Java Features
- **OOP Avanzato**: Ereditarietà, Polimorfismo, Incapsulamento
- **Generics con Bounded Types**: `<X extends Entity>` per TotalCache e PartialCache
- **Enum**: Type-safe constants (ExpenseCategory)
- **LocalDate API**: Date manipulation moderna
- **Regex**: Validazione pattern complessi (CF)
- **Collections**: ArrayList, List per cache e repository
- **JDBC**: Connection, PreparedStatement, ResultSet per SQLAssetRepository
- **Exception Handling**: FileNotFoundException, SQLException, validazione robusta
- **Override equals()**: Confronto logico in Asset

### Database
- **SQLite**: Database embedded leggero per asset persistence
- **JDBC Driver**: org.sqlite.JDBC
- **Auto-increment**: ID gestito con `INTEGER PRIMARY KEY AUTOINCREMENT`
- **SQL Injection Prevention**: PreparedStatement con parametri

### Performance
- **Caching Strategy**: TotalCache elimina query ripetute (0 query dopo warm-up)
- **Lazy Loading**: Cache popolata solo alla prima chiamata
- **Cache Synchronization**: Insert/Update/Delete mantengono cache allineata
- **Profiling**: ProfilingMonitor traccia queryNumber e rowsLoaded

**Progetto didattico Generation Italy** - Dimostrazione completa di Repository Pattern, Service Layer, ETL Pattern, Cache Pattern (Total/Partial), Dependency Injection, MVC, JDBC, Generics e validazione enterprise in Java.

