# LocalMarketDB

Versione avanzata di LocalMarket che utilizza database SQLite invece di file system.

## Descrizione

LocalMarketDB è l'evoluzione di LocalMarket con persistenza su database relazionale SQLite. Implementa una architettura a livelli ben definita con Dependency Injection e utilizza JDBC per l'accesso ai dati.

## Struttura del Progetto

```
LocalMarketDB/
├── src/
│   └── com/generation/lmdb/
│       ├── context/
│       │   └── Context.java
│       ├── controller/
│       │   ├── Main.java
│       │   ├── BatchController.java
│       │   ├── ProductController.java
│       │   └── ProducerController.java
│       └── model/
│           ├── entities/
│           │   ├── Product.java
│           │   ├── Producer.java
│           │   └── Batch.java
│           └── repository/
│               ├── ProductRepository.java
│               ├── ProducerRepository.java
│               └── BatchRepository.java
└── database/
    └── localmarket.db
```

## Classi Principali

### Context (Dependency Injection)
- `Context.java` - Container DI con pattern Singleton, gestisce connessione DB e repository

### Controller (Service Layer)
- `Main.java` - Controller principale con routing
- `BatchController.java` - Logica business per batch
- `ProductController.java` - Logica business per prodotti
- `ProducerController.java` - Logica business per produttori

### Model (Entità)
- `Product.java` - Entità prodotto
- `Producer.java` - Entità produttore
- `Batch.java` - Entità batch

### Repository (Data Access)
- `ProductRepository.java` - Accesso DB per prodotti (insert, findById, update)
- `ProducerRepository.java` - Accesso DB per produttori
- `BatchRepository.java` - Accesso DB per lotti

## Funzionalità Implementate

1. **CRUD Completo**
   - Operazioni Create, Read, Update su database SQLite
   - Prepared Statements per sicurezza SQL Injection
   - Try-with-resources per gestione corretta delle risorse

2. **Operazioni Batch**
   - Aggiunta batch
   - Revoca/approvazione batch
   - Caricamento da database

3. **Operazioni Prodotti**
   - Creazione prodotto
   - Cambio prezzo
   - Cambio descrizione

4. **Operazioni Produttori**
   - Aggiunta produttore
   - Cambio indirizzo
   - Ban/Unban

## Pattern Architetturali Utilizzati

- **Dependency Injection (DI)**: Context implementa DI pattern per centralizzare le dipendenze
- **Singleton Pattern**: Una sola istanza di Context e una sola connessione DB
- **Repository Pattern**: Repository per astrazione accesso dati
- **DAO Pattern**: Repository fungono da DAO
- **Service Layer**: Controller fungono da service layer tra view e repository
- **Factory Method**: `Context.getDependency()` come factory per recuperare oggetti

## Esecuzione

```bash
cd LocalMarketDB
javac -d bin src/com/generation/lmdb/**/*.java
java -cp bin:lib/sqlite-jdbc.jar com.generation.lmdb.controller.Main
```

## Concetti Java Dimostrati

- **JDBC**: Connessione e operazioni su database SQLite
- **Dependency Injection**: Gestione centralizzata delle dipendenze
- **Singleton Pattern**: Istanza unica per risorse condivise
- **Prepared Statements**: Sicurezza contro SQL Injection
- **Try-with-resources**: Gestione automatica chiusura risorse
- **Service Layer**: Separazione logica business da accesso dati
- **Repository/DAO**: Astrazione accesso ai dati

## File Chiave

- `Context.java:1` - Dependency Injection container
- `Main.java:1` - Controller principale
- `ProductRepository.java:1` - Repository con JDBC

## Differenze rispetto a LocalMarket

| Aspetto | LocalMarket | LocalMarketDB |
|---------|------------|---------------|
| **Persistenza** | File System | SQLite Database |
| **Dependency Injection** | No | Si (Context) |
| **Controller** | Main singolo | Main + Controller specializzati |
| **Connessione** | File I/O | JDBC |
| **Sicurezza** | Validazione base | Prepared Statements |
