# GenerationLibrary - Repository Pattern Framework

Framework riutilizzabile per la gestione di entità con pattern Repository, Template Method e JDBC. Fornisce una base generica per operazioni CRUD su database SQLite con validazione incorporata.

## Architettura

```
Entity (Abstract Base Class)
   ↓ extends
Car, Pet, Plant (Concrete Entities)
   ↓ uses
SQLEntityRepository<T> (Generic Abstract Repository)
   ↓ implements
SQLCarRepository, SQLPetRepository, SQLPlantRepository
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

Ogni entità concreta implementa la propria logica di validazione:

```java
public class Car extends Entity {
    private String model, plate;
    private double price;

    @Override
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();
        if (isMissing(model)) errors.add("Modello mancante");
        if (isMissing(plate)) errors.add("Targa mancante");
        if (price <= 0) errors.add("Prezzo deve essere positivo");
        return errors;
    }
}
```

### 2. Repository Pattern con Generics
`SQLEntityRepository<X extends Entity>` fornisce implementazione CRUD generica:

```java
public abstract class SQLEntityRepository<X extends Entity> {
    protected String tableName;
    protected Connection connection;

    // Metodi CRUD implementati (comuni a tutte le entità)
    public List<X> findAll() { /* SQL SELECT */ }
    public X findById(int id) { /* SQL SELECT WHERE id */ }
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

Sottoclassi concrete implementano solo il mapping specifico:

```java
public class SQLCarRepository extends SQLEntityRepository<Car> {
    public SQLCarRepository(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected Car rowToX(ResultSet rs) throws SQLException {
        return new Car(rs.getInt("id"), rs.getString("model"),
                       rs.getString("plate"), rs.getDouble("price"));
    }

    @Override
    protected PreparedStatement getInsertCmd(Car car) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "INSERT INTO car (model, plate, price) VALUES (?, ?, ?)"
        );
        ps.setString(1, car.getModel());
        ps.setString(2, car.getPlate());
        ps.setDouble(3, car.getPrice());
        return ps;
    }

    @Override
    protected PreparedStatement getUpdateCmd(Car car) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "UPDATE car SET model=?, plate=?, price=? WHERE id=?"
        );
        ps.setString(1, car.getModel());
        ps.setString(2, car.getPlate());
        ps.setDouble(3, car.getPrice());
        ps.setInt(4, car.getId());
        return ps;
    }
}
```

## Entità Implementate

### Car (Automobile)
- **Attributi**: `model` (String), `plate` (String), `price` (double)
- **Validazione**: model non vuoto, plate non vuoto, price > 0
- **Database**: `car.db` con tabella `car`

### Pet (Animale Domestico)
- **Attributi**: `name` (String), `species` (String)
- **Validazione**: name non vuoto, species non vuoto
- **Database**: `pet.db` con tabella `pet`

### Plant (Pianta)
- **Attributi**: `species` (String), `length` (int), `cost` (double)
- **Validazione**: species non vuoto, length > 0, cost > 0
- **Database**: `plant.db` con tabella `plant`

## Struttura Directory

```
GenerationLibrary/
├── src/
│   └── com/generation/library/
│       ├── Entity.java                    # Classe base astratta
│       ├── model/entities/
│       │   ├── Car.java                   # Entità Car + validazione
│       │   ├── Pet.java                   # Entità Pet + validazione
│       │   └── Plant.java                 # Entità Plant + validazione
│       ├── repository/
│       │   ├── SQLEntityRepository.java   # Repository generico astratto
│       │   ├── SQLCarRepository.java      # Repository concreto Car
│       │   ├── SQLPetRepository.java      # Repository concreto Pet
│       │   └── SQLPlantRepository.java    # Repository concreto Plant
│       ├── demo/repository/
│       │   ├── DemoCarRepository.java     # Demo CRUD Car
│       │   ├── DemoPetRepository.java     # Demo CRUD Pet
│       │   └── DemoPlantRepository.java   # Demo CRUD Plant
│       └── test/
│           ├── SQLCarRepositoryTest.java  # JUnit 5 test Car
│           ├── PetRepositoryTest.java     # JUnit 5 test Pet
│           └── PlantRepositoryTest.java   # JUnit 5 test Plant
├── bin/                                   # Classi compilate
├── car.db                                 # Database SQLite Car
├── pet.db                                 # Database SQLite Pet
└── plant.db                               # Database SQLite Plant
```

## Operazioni CRUD Disponibili

Ogni repository implementa i seguenti metodi:

```java
// Ricerca
List<X> findAll()                          // Tutti i record
X findById(int id)                         // Ricerca per ID
List<X> findWhere(String condition)        // Ricerca con condizione SQL custom

// Inserimento
void insert(X entity)                      // Insert con validazione automatica

// Aggiornamento
void update(X entity)                      // Update con validazione automatica

// Eliminazione
void delete(int id)                        // Delete per ID

// Utility
int getNewId()                             // Recupera ultimo ID auto-increment
```

## Esempio di Utilizzo

```java
// 1. Connessione al database
Connection connection = DriverManager.getConnection("jdbc:sqlite:car.db");

// 2. Creazione repository
SQLCarRepository carRepo = new SQLCarRepository("car", connection);

// 3. Inserimento con validazione automatica
Car newCar = new Car(0, "Tesla Model 3", "AB123CD", 45000.0);
if (newCar.isValid()) {
    carRepo.insert(newCar);  // ID assegnato automaticamente
    System.out.println("Auto inserita con ID: " + newCar.getId());
} else {
    System.out.println("Errori: " + newCar.getErrors());
}

// 4. Ricerca
List<Car> allCars = carRepo.findAll();
Car car = carRepo.findById(1);

// 5. Aggiornamento
car.setPrice(42000.0);
if (car.isValid()) {
    carRepo.update(car);
}

// 6. Eliminazione
carRepo.delete(1);

// 7. Chiusura connessione
connection.close();
```

## Test Unitari (JUnit 5)

Pattern di test comune:

```java
public class SQLCarRepositoryTest {
    private Connection connection;
    private SQLCarRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:car.db");
        repository = new SQLCarRepository("car", connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    @Test
    void testInsert() throws SQLException {
        Car car = new Car(0, "Fiat 500", "XY789ZW", 15000.0);
        assertTrue(car.isValid());

        repository.insert(car);
        assertTrue(car.getId() > 0);  // ID assegnato

        Car found = repository.findById(car.getId());
        assertEquals("Fiat 500", found.getModel());
    }
}
```

## Compilazione ed Esecuzione

```bash
# Compilazione
javac -d bin src/com/generation/library/**/*.java

# Esecuzione demo
java -cp bin com.generation.library.demo.repository.DemoCarRepository

# Esecuzione test JUnit
javac -cp bin:junit-jupiter.jar src/com/generation/library/test/*.java
java -cp bin:junit-jupiter.jar org.junit.platform.console.ConsoleLauncher \
     --scan-classpath
```

## Tecnologie e Concetti

### Design Patterns
- **Template Method**: Validazione gerarchica con hook methods
- **Repository Pattern**: Astrazione accesso dati
- **Generics (Parametric Polymorphism)**: Type-safety e riuso codice
- **Bounded Type Parameter**: `<X extends Entity>` garantisce contratto comune

### Java Features
- **OOP Avanzato**: Ereditarietà, Polimorfismo, Incapsulamento
- **Abstract Classes**: Contratto + implementazione parziale
- **Generics**: Repository parametrizzato su tipo Entity
- **JDBC**: Connection, PreparedStatement, ResultSet
- **Collections**: ArrayList, List
- **Exception Handling**: SQLException gestito nei repository

### Database
- **SQLite**: Database embedded leggero
- **JDBC Driver**: `org.sqlite.JDBC` (sqlite-jdbc-3.51.1.0.jar)
- **Auto-increment**: Gestione automatica ID tramite `ROWID`

## Vantaggi del Framework

1. **Riusabilità**: Aggiungere nuove entità richiede solo:
   - Estendere `Entity` con validazione
   - Estendere `SQLEntityRepository<NewEntity>`
   - Implementare 3 metodi (rowToX, getInsertCmd, getUpdateCmd)

2. **Type-Safety**: Generics impediscono errori di tipo a compile-time

3. **Validazione Centralizzata**: Logica di validazione nell'entità stessa

4. **Testabilità**: Pattern Repository facilita mock e test

5. **Manutenibilità**: Modifiche CRUD comuni in un solo punto

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
        if (year < 1000 || year > LocalDate.now().getYear())
            errors.add("Anno non valido");
        return errors;
    }
}

// 2. Repository concreto
public class SQLBookRepository extends SQLEntityRepository<Book> {
    // Implementare rowToX, getInsertCmd, getUpdateCmd
}

// 3. Demo e test
public class DemoBookRepository { /* ... */ }
public class BookRepositoryTest { /* ... */ }
```

## Configurazione Tecnica

- **JDK**: JavaSE-21
- **Database**: SQLite 3.x
- **JDBC Driver**: sqlite-jdbc-3.51.1.0.jar
- **Testing**: JUnit Jupiter 5
- **IDE**: Eclipse / IntelliJ compatible

**Progetto didattico Generation Italy** - Framework riutilizzabile che dimostra Template Method, Repository Pattern con Generics, JDBC e validazione enterprise in Java.
