# PokeDex

Sistema di gestione Pokedex basato su database SQLite.

## Descrizione

PokeDex è un'applicazione che gestisce pokemon (razze) e tipi di pokemon utilizzando database SQLite. Dimostra pattern di Dependency Injection con Context, relazioni foreign key tra tabelle e lazy loading di oggetti correlati.

## Struttura del Progetto

```
PokeDex/
├── src/
│   └── com/generation/pokedex/
│       ├── contex/
│       │   └── Context.java
│       ├── model/
│       │   ├── entities/
│       │   │   ├── Entity.java
│       │   │   ├── Pokemon.java
│       │   │   └── PokemonType.java
│       │   └── repository/
│       │       ├── PokemonRepository.java
│       │       └── PokemonTypeRepository.java
│       └── controller/
│           ├── DemoCaricamentoPokemon.java
│           └── DemoInserimentoPokemon.java
└── database/
    └── pokedex.db
```

## Classi Principali

### Context (Dependency Injection)
**Context.java** - Container DI con pattern Singleton
- Gestisce connessione SQLite centralizzata
- Fornisce istanze di repository tramite `getDependency()`
- Garantisce una sola connessione al database

### Model - Entità

**Entity.java** - Classe base astratta
- Attributo: id
- Metodi comuni per tutte le entità

**Pokemon.java** - Entità rappresentante una razza pokemon
- Attributi: name, primaryType, secondaryType (opzionale)
- Relazione con PokemonType tramite foreign key

**PokemonType.java** - Entità tipo pokemon
- Attributi: name, description
- Esempi: Water, Fire, Grass, Electric

### Repository (Data Access)

**PokemonRepository.java** - Accesso DB per pokemon
- `insert(Pokemon pokemon)` - Inserimento nuovo pokemon
- `findById(int id)` - Caricamento pokemon con lazy loading del tipo
- Gestione relazioni foreign key

**PokemonTypeRepository.java** - Accesso DB per tipi pokemon
- `findById(int id)` - Caricamento tipo pokemon
- Supporto per caricamento automatico da altri repository

### Controller (Demo)
- `DemoCaricamentoPokemon.java` - Esempio di caricamento
- `DemoInserimentoPokemon.java` - Esempio di inserimento

## Funzionalità Implementate

1. **Connessione Database**
   - Connessione centralizzata a database SQLite tramite Context
   - Gestione automatica del driver JDBC

2. **Gestione Pokemon**
   - Inserimento pokemon con name e type ID
   - Caricamento pokemon con lazy loading del tipo associato
   - Ricerca pokemon per ID

3. **Relazioni Database**
   - Gestione relazioni foreign key (pokemon.primarytypeid -> pokemontype.id)
   - Caricamento automatico tipo associato quando si carica un pokemon
   - Supporto per tipo secondario opzionale

4. **Validazione**
   - Validazione dati pokemon tramite `isValid()`
   - Controllo integrità referenziale

## Pattern Architetturali Utilizzati

- **Dependency Injection (DI)**: Context come container centralizzato per dipendenze
- **Singleton Pattern**: Una sola istanza di Context e connessione DB
- **Repository Pattern**: PokemonRepository e PokemonTypeRepository per accesso dati
- **DAO (Data Access Object)**: Repository implementano DAO pattern
- **Lazy Loading**: Tipo pokemon caricato on-demand in `findById()`
- **Factory Method**: `Context.getDependency()` per ottenere istanze repository
- **Service Locator**: Context funge anche da service locator

## Esecuzione

```bash
cd PokeDex
javac -d bin src/com/generation/pokedex/**/*.java
java -cp bin:lib/sqlite-jdbc.jar com.generation.pokedex.controller.DemoInserimentoPokemon
```

## Concetti Java Dimostrati

### Dependency Injection con Context
```java
public class Context {
    private static Context instance;
    private Connection connection;

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public <T> T getDependency(Class<T> clazz) {
        if (clazz == PokemonRepository.class) {
            return (T) new PokemonRepository(connection);
        }
        // ...
    }
}
```

### Lazy Loading di Relazioni
```java
public Pokemon findById(int id) {
    // Carica pokemon dal database
    Pokemon pokemon = ...;

    // Lazy loading del tipo primario
    PokemonTypeRepository typeRepo = Context.getInstance()
        .getDependency(PokemonTypeRepository.class);
    PokemonType primaryType = typeRepo.findById(pokemon.getPrimaryTypeId());
    pokemon.setPrimaryType(primaryType);

    return pokemon;
}
```

### Repository con JDBC
```java
public void insert(Pokemon pokemon) {
    String sql = "INSERT INTO pokemon (name, primarytypeid, secondarytypeid) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, pokemon.getName());
        stmt.setInt(2, pokemon.getPrimaryType().getId());
        if (pokemon.getSecondaryType() != null) {
            stmt.setInt(3, pokemon.getSecondaryType().getId());
        } else {
            stmt.setNull(3, java.sql.Types.INTEGER);
        }
        stmt.executeUpdate();
    }
}
```

### Ereditarietà da Entity
```java
public abstract class Entity {
    protected int id;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}

public class Pokemon extends Entity {
    private String name;
    private PokemonType primaryType;
    // ...
}
```

## File Chiave

- `Context.java:1` - Dependency Injection container con Singleton
- `Pokemon.java:1` - Entità con relazioni foreign key
- `PokemonRepository.java:1` - Repository con lazy loading
- `DemoInserimentoPokemon.java:1` - Demo inserimento

## Schema Database

```sql
CREATE TABLE pokemontype (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT
);

CREATE TABLE pokemon (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    primarytypeid INTEGER NOT NULL,
    secondarytypeid INTEGER,
    FOREIGN KEY (primarytypeid) REFERENCES pokemontype(id),
    FOREIGN KEY (secondarytypeid) REFERENCES pokemontype(id)
);
```

## Relazioni tra Entità

```
PokemonType (1) <---FK--- (N) Pokemon
    |                           |
    id  <--- primarytypeid  --- name
    |    <--- secondarytypeid
    name
    description
```

Ogni Pokemon ha:
- Un tipo primario obbligatorio (es: Pikachu -> Electric)
- Un tipo secondario opzionale (es: Charizard -> Fire/Flying)

## Vantaggi del Pattern DI

1. **Centralizzazione**: Una sola connessione DB gestita da Context
2. **Testabilità**: Facile sostituire repository con mock per test
3. **Manutenibilità**: Cambio configurazione DB in un solo punto
4. **Riuso**: Repository condivisi tra diversi controller
5. **Lazy Initialization**: Risorse create solo quando necessarie
