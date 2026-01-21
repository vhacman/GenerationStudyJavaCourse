# GenerationLibrary - Enterprise Framework per Java

Framework riutilizzabile per lo sviluppo di applicazioni Java con pattern architetturali enterprise. Fornisce:
- **Repository Pattern** generico con supporto JDBC
- **Caching** (full/partial) per ottimizzazione performance
- **Database utilities** per gestione connessioni e profiling
- **View utilities** per interfacce utente
- **I/O utilities** (Console, FileReader, FileWriter, Template)

## Architettura

```
Entity (Abstract Base Class)
   ↓
SQLEntityRepository<X extends Entity>
   ├── FullCacheSQLEntityRepository<X>    (Caching completo)
   └── PartialCacheSQLEntityRepository<X> (Caching parziale)

Supporto:
   ├── database/    (Gestione connessioni e profiling)
   ├── cache/       (Sistema di caching)
   ├── view/        (Utilities per interfacce)
   └── I/O          (Console, FileReader, FileWriter, Template)
```

## Design Patterns Implementati

### 1. Template Method Pattern
La classe astratta `Entity` definisce l'algoritmo di validazione generale:

```java
public abstract class Entity {
    protected int id;

    // Template method - definisce l'algoritmo
    public boolean isValid() {
        return getErrors().isEmpty();
    }

    // Hook method - delegato alle sottoclassi
    public abstract List<String> getErrors();

    // Utility methods comuni
    protected boolean notMissing(String s) { return s != null && !s.isBlank(); }
    protected boolean isMissing(String s) { return s == null || s.isBlank(); }
}
```

### 2. Repository Pattern con Generics
`SQLEntityRepository<X extends Entity>` fornisce implementazione CRUD generica:

```java
public abstract class SQLEntityRepository<X extends Entity> {
    protected Connection connection;
    private String table;

    // Metodi CRUD implementati (comuni a tutte le entità)
    public List<X> findAll() { /* SQL SELECT */ }
    public X findById(int id) { /* SQL SELECT WHERE id */ }
    public List<X> findWhere(String condition) { /* SELECT con WHERE custom */ }
    public void insert(X entity) { /* SQL INSERT con validazione */ }
    public void update(X entity) { /* SQL UPDATE con validazione */ }
    public void delete(int id) { /* SQL DELETE */ }
    protected int getNewId() { /* Auto-increment retrieval */ }

    // Hook methods - delegati alle sottoclassi concrete
    protected abstract X rowToX(ResultSet rs) throws SQLException;
    protected abstract PreparedStatement getInsertCmd(X x) throws SQLException;
    protected abstract PreparedStatement getUpdateCmd(X x) throws SQLException;
}
```

### 3. Caching Pattern
Il framework fornisce due strategie di caching:

#### Full Cache
```java
public abstract class FullCacheSQLEntityRepository<X extends Entity>
    extends SQLEntityRepository<X> {
    // Cache completa: tutte le entità in memoria
    // Ottimale per tabelle piccole con accessi frequenti
}
```

#### Partial Cache
```java
public abstract class PartialCacheSQLEntityRepository<X extends Entity>
    extends SQLEntityRepository<X> {
    // Cache parziale: solo entità accedute di recente
    // Ottimale per tabelle grandi
}
```

## Componenti Principali

### Core
- **Entity**: Classe base astratta per tutte le entità del dominio
- **SQLEntityRepository**: Repository generico con operazioni CRUD via JDBC

### Caching
- **FullCacheSQLEntityRepository**: Caching completo in memoria
- **PartialCacheSQLEntityRepository**: Caching LRU per entità frequenti

### Database
- **Utilità di connessione**: Gestione pool connessioni
- **Profiling**: Monitoraggio performance query SQL

### View
- **Utilities per interfacce**: Helper per UI e presentazione dati

### I/O
- **Console**: Input/output da console con validazione
- **FileReader**: Lettura file con gestione encoding
- **FileWriter**: Scrittura file thread-safe
- **Template**: Sistema di templating per generazione testo

## Struttura Directory

```
GenerationLibrary/
├── src/com/generation/library/
│   ├── Entity.java                              # Classe base astratta
│   │
│   ├── repository/
│   │   ├── SQLEntityRepository.java             # Repository generico base
│   │   ├── FullCacheSQLEntityRepository.java    # Repository con cache completa
│   │   └── PartialCacheSQLEntityRepository.java # Repository con cache parziale
│   │
│   ├── cache/                                   # Sistema di caching
│   │   └── [implementazioni cache]
│   │
│   ├── database/                                # Gestione database
│   │   └── [connection pool, profiling]
│   │
│   ├── view/                                    # Utilities per UI
│   │   └── [helper per presentazione]
│   │
│   ├── Console.java                             # I/O console
│   ├── FileReader.java                          # Lettura file
│   ├── FileWriter.java                          # Scrittura file
│   └── Template.java                            # Sistema templating
│
└── bin/                                         # Classi compilate
```

## Operazioni CRUD Disponibili

Ogni repository implementa i seguenti metodi:

```java
// Ricerca
List<X> findAll()                          // Tutti i record
X findById(int id)                         // Ricerca per ID
List<X> findWhere(String condition)        // Ricerca con condizione WHERE custom

// Inserimento
void insert(X entity)                      // Insert con validazione automatica

// Aggiornamento
void update(X entity)                      // Update con validazione automatica

// Eliminazione
void delete(int id)                        // Delete per ID

// Utility
int getNewId()                             // Recupera ultimo ID auto-increment
```

## Utilities I/O

### Console
```java
// Input con validazione
String input = Console.readString("Inserisci nome: ");
int number = Console.readInt("Inserisci età: ");
```

### FileReader/FileWriter
```java
// Lettura file
String content = FileReader.read("path/to/file.txt");

// Scrittura file
FileWriter.write("path/to/file.txt", content);
```

### Template
```java
// Sistema di templating
Template template = new Template("template.txt");
template.set("nome", "Mario");
template.set("cognome", "Rossi");
String output = template.render();
```

## Esempio di Utilizzo

### Repository Base
```java
// 1. Definisci la tua entità
public class Product extends Entity {
    private String name;
    private double price;

    @Override
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();
        if (isMissing(name)) errors.add("Nome mancante");
        if (price <= 0) errors.add("Prezzo non valido");
        return errors;
    }
}

// 2. Crea repository concreto
public class SQLProductRepository extends SQLEntityRepository<Product> {
    public SQLProductRepository(String table, Connection connection) {
        super(connection, table);
    }

    @Override
    protected Product rowToX(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        return p;
    }

    @Override
    protected PreparedStatement getInsertCmd(Product p) throws SQLException {
        PreparedStatement cmd = connection.prepareStatement(
            "INSERT INTO product (name, price) VALUES (?, ?)"
        );
        cmd.setString(1, p.getName());
        cmd.setDouble(2, p.getPrice());
        return cmd;
    }

    @Override
    protected PreparedStatement getUpdateCmd(Product p) throws SQLException {
        PreparedStatement cmd = connection.prepareStatement(
            "UPDATE product SET name=?, price=? WHERE id=?"
        );
        cmd.setString(1, p.getName());
        cmd.setDouble(2, p.getPrice());
        cmd.setInt(3, p.getId());
        return cmd;
    }
}

// 3. Utilizzo
Connection connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
SQLProductRepository repo = new SQLProductRepository("product", connection);

Product p = new Product("Laptop", 899.99);
if (p.isValid()) {
    repo.insert(p);
    System.out.println("Prodotto inserito con ID: " + p.getId());
}

List<Product> products = repo.findAll();
Product found = repo.findById(1);
repo.delete(1);
```

### Repository con Cache
```java
// Per tabelle piccole con accessi frequenti
public class CachedProductRepository extends FullCacheSQLEntityRepository<Product> {
    // Implementa i metodi astratti come sopra
}

// Per tabelle grandi
public class PartialCachedProductRepository extends PartialCacheSQLEntityRepository<Product> {
    // Implementa i metodi astratti come sopra
}
```

## Test Unitari (JUnit 5)

Pattern di test comune:

```java
public class SQLProductRepositoryTest {
    private Connection connection;
    private SQLProductRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        // Crea schema di test
        repository = new SQLProductRepository("product", connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    @Test
    void testInsert() throws SQLException {
        Product p = new Product("Mouse", 25.99);
        assertTrue(p.isValid());

        repository.insert(p);
        assertTrue(p.getId() > 0);

        Product found = repository.findById(p.getId());
        assertEquals("Mouse", found.getName());
        assertEquals(25.99, found.getPrice(), 0.01);
    }

    @Test
    void testValidation() {
        Product invalid = new Product("", -10);
        assertFalse(invalid.isValid());
        assertEquals(2, invalid.getErrors().size());
    }
}
```

## Compilazione ed Esecuzione

```bash
# Compilazione completa
javac -d bin src/com/generation/library/**/*.java

# Compilazione con dipendenze esterne
javac -cp lib/sqlite-jdbc.jar -d bin src/com/generation/library/**/*.java

# Esecuzione con classpath
java -cp bin:lib/sqlite-jdbc.jar com.your.package.Main

# Creazione JAR
jar cvf generation-library.jar -C bin .
```

## Tecnologie e Concetti

### Design Patterns
- **Template Method**: Validazione gerarchica con hook methods
- **Repository Pattern**: Astrazione accesso dati
- **Caching Pattern**: Full cache e partial cache (LRU)
- **Generics (Parametric Polymorphism)**: Type-safety e riuso codice
- **Bounded Type Parameter**: `<X extends Entity>` garantisce contratto comune

### Java Features
- **OOP Avanzato**: Ereditarietà, Polimorfismo, Incapsulamento
- **Abstract Classes**: Contratto + implementazione parziale
- **Generics**: Repository parametrizzato su tipo Entity
- **JDBC**: Connection, PreparedStatement, ResultSet
- **Collections**: ArrayList, List, Map (cache)
- **Exception Handling**: SQLException gestito nei repository
- **I/O Streams**: FileReader, FileWriter con encoding

### Database
- **SQLite**: Database embedded leggero
- **JDBC Driver**: `org.sqlite.JDBC`
- **Auto-increment**: Gestione automatica ID
- **Connection Pool**: Gestione efficiente connessioni
- **Query Profiling**: Monitoraggio performance

## Vantaggi del Framework

1. **Riusabilità**: Aggiungere nuove entità richiede solo:
   - Estendere `Entity` con validazione
   - Estendere `SQLEntityRepository<NewEntity>` (o varianti con cache)
   - Implementare 3 metodi (rowToX, getInsertCmd, getUpdateCmd)

2. **Performance**:
   - Caching full/partial per ridurre query database
   - Query profiling per identificare bottleneck
   - Connection pooling per ottimizzare risorse

3. **Type-Safety**: Generics impediscono errori di tipo a compile-time

4. **Validazione Centralizzata**: Logica di validazione nell'entità stessa

5. **Testabilità**: Pattern Repository facilita mock e test

6. **Manutenibilità**: Modifiche CRUD comuni in un solo punto

7. **Utilities Complete**: Console, File I/O, Template engine pronti all'uso

## Estensione: Aggiungere Nuova Entità

Per aggiungere un'entità `Book`:

```java
// 1. Entità con validazione
public class Book extends Entity {
    private String title, author, isbn;
    private int year;

    @Override
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();
        if (isMissing(title)) errors.add("Titolo mancante");
        if (isMissing(author)) errors.add("Autore mancante");
        if (isMissing(isbn)) errors.add("ISBN mancante");
        if (year < 1000 || year > LocalDate.now().getYear())
            errors.add("Anno non valido");
        return errors;
    }

    // Getters e Setters
}

// 2. Repository base (senza cache)
public class SQLBookRepository extends SQLEntityRepository<Book> {
    // Implementare rowToX, getInsertCmd, getUpdateCmd
}

// 3. Repository con cache (opzionale)
public class CachedBookRepository extends FullCacheSQLEntityRepository<Book> {
    // Implementare rowToX, getInsertCmd, getUpdateCmd
    // La cache è gestita automaticamente dalla superclasse
}

// 4. Test
public class BookRepositoryTest { /* ... */ }
```

## Scelta della Strategia di Caching

| Repository | Quando usare |
|-----------|-------------|
| `SQLEntityRepository` | Tabelle grandi, accessi rari |
| `FullCacheSQLEntityRepository` | Tabelle piccole (<1000 record), letture frequenti |
| `PartialCacheSQLEntityRepository` | Tabelle medie, accesso a subset frequente (es. utente corrente) |

## Configurazione Tecnica

- **JDK**: JavaSE-21
- **Database**: SQLite 3.x (supporto per altri DBMS via JDBC)
- **JDBC Driver**: sqlite-jdbc (verificare versione in lib/)
- **Testing**: JUnit Jupiter 5 (opzionale)
- **IDE**: Eclipse / IntelliJ IDEA / VS Code compatible

## Dipendenze

```xml
<!-- Maven example -->
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.42.0.0</version>
</dependency>
```

## Changelog

### v2.0 (2026-01-21)
- ✨ Aggiunto sistema di caching (Full/Partial)
- ✨ Utilities database (profiling, connection pool)
- ✨ View utilities per interfacce
- ✨ I/O utilities (Console, FileReader, FileWriter, Template)
- 🔧 Refactoring Entity e SQLEntityRepository (codice più conciso)
- 🗑️ Rimossi esempi specifici (Car, Pet, Plant) - focus su framework generico

### v1.0
- 🎉 Release iniziale con Repository Pattern e Template Method

---

**Progetto didattico Generation Italy** - Framework enterprise riutilizzabile che dimostra Template Method, Repository Pattern con Generics, Caching, JDBC e validazione in Java.
