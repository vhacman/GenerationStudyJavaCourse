# BikeWorld - Sistema Gestionale Concessionaria Moto

Sistema di gestione per concessionaria di motociclette che gestisce l'intero workflow dalle operazioni di importazione quotidiana fino alla consegna al cliente finale.

## Descrizione

BikeWorld è un'applicazione Java che simula la gestione di una concessionaria di motociclette. Il sistema gestisce il ciclo di vita completo di ogni moto, dall'arrivo presso la sede, passando per le fasi di controllo e lavaggio, fino alla consegna finale al cliente.

### Caratteristiche Principali

- **Gestione Workflow Moto**: Tracciamento dello stato di ogni motocicletta attraverso le diverse fasi di lavorazione
- **Importazione CSV Automatica**: Sistema ETL per l'importazione giornaliera delle moto in arrivo
- **Repository Pattern**: Astrazione del layer di persistenza con implementazione SQL
- **Dependency Injection**: Gestione delle dipendenze tramite IoC Container personalizzato
- **State Pattern**: Gestione type-safe degli stati attraverso enum
- **Chain of Responsibility**: Workflow automatico di avanzamento stato

## Architettura

### Pattern Implementati

#### 1. Repository Pattern
```java
public interface BikeRepository {
    List<Bike> findAll();
    Bike findById(int id);
    Bike findByPlate(String plate);
    List<Bike> findProcessing();
    Bike insert(Bike bike) throws SQLException;
    Bike update(Bike bike) throws SQLException;
    boolean delete(int id) throws SQLException;
}
```

Il pattern Repository astrae completamente il layer di persistenza, permettendo di:
- Cambiare facilmente l'implementazione del database
- Testare il codice con mock/stub
- Centralizzare la logica di accesso ai dati

#### 2. State Pattern + Chain of Responsibility
```java
public enum BikeStatus {
    DELIVERED   (null, 4),
    CLEANING    (BikeStatus.DELIVERED, 3),
    CHECKUP     (BikeStatus.CLEANING, 2),
    ARRIVED     (BikeStatus.CHECKUP, 1);

    public BikeStatus getNext() {
        return next != null ? next : this;
    }
}
```

Ogni stato conosce il proprio successore, creando una catena immutabile che rappresenta il workflow di lavorazione.

#### 3. Dependency Injection Container
```java
public class Context {
    static List<Object> dependencies = new ArrayList<Object>();

    public static <T> T getDependency(Class<T> dependencyNeeded) {
        // Autowiring by type
    }
}
```

Il container IoC gestisce la creazione e l'iniezione delle dipendenze, implementando l'Inversion of Control.

#### 4. ETL Pattern (Extract-Transform-Load)
```java
public class BikeDailyExtractor {
    public List<Bike> extractFrom(String filename)
        throws FileNotFoundException {
        // CSV parsing and transformation
    }
}
```

## Workflow Stati Moto

```
ARRIVED → CHECKUP → CLEANING → DELIVERED
   ↓         ↓         ↓          ↓
Arrivo   Controllo  Lavaggio  Consegna
```

### Descrizione Stati

1. **ARRIVED** - Moto appena arrivata dalla bisarca
2. **CHECKUP** - Moto in fase di controllo qualità e verifica tecnica
3. **CLEANING** - Moto in fase di lavaggio e preparazione estetica
4. **DELIVERED** - Moto consegnata al cliente (stato finale)

## Struttura del Progetto

```
BikeWorld/
├── src/
│   └── com/generation/bw/
│       ├── controller/
│       │   └── Main.java                  # Entry point e UI console
│       ├── model/
│       │   └── entities/
│       │       ├── Bike.java              # Entity principale
│       │       ├── BikeStatus.java        # Enum stati workflow
│       │       ├── BikeTest.java          # Unit test Bike
│       │       └── BikeStatusTest.java    # Unit test BikeStatus
│       ├── repository/
│       │   ├── BikeRepository.java        # Interfaccia repository
│       │   └── SQLBikeRepository.java     # Implementazione SQL
│       ├── extractor/
│       │   ├── BikeDailyExtractor.java    # ETL per CSV
│       │   └── BikeDailyExtractorTest.java
│       └── context/
│           └── Context.java                # DI Container
├── bike.db                                 # Database SQLite
├── 20260122.csv                           # File importazione giornaliera
└── README.md
```

## Tecnologie Utilizzate

- **Java 8+**: Lambda expressions, functional interfaces
- **JDBC**: Database connectivity
- **SQLite**: Database embedded
- **GenerationLibrary**: Framework custom con Entity base e utilities
- **JUnit 5**: Testing

## Funzionalità

### Menu Principale

```
========== BIKE MANAGEMENT SYSTEM ==========
1. Inserisci nuova moto manualmente
2. Avanza stato moto
3. Elenco moto in lavorazione
4. Dettaglio moto (per ID o targa)
5. Importa file giornaliero CSV
0. Esci
============================================
```

### 1. Inserimento Manuale Moto
Permette di inserire una nuova moto fornendo:
- Brand (marca)
- Model (modello)
- Plate (targa)
- Power (cilindrata in cc)
- Cost (costo di acquisto)
- Work (costo di lavorazione)
- Price (prezzo di vendita)

**Validazioni**:
- Brand, model e plate non possono essere vuoti
- Power deve essere > 0
- Cost deve essere > 0
- Work deve essere ≥ 0
- Price deve essere > 0 e ≥ (cost + work)

### 2. Avanzamento Stato
Permette di far avanzare una moto allo stato successivo nel workflow.
- Richiede l'ID della moto
- Avanza automaticamente allo stato successivo
- Previene l'avanzamento se già nello stato finale (DELIVERED)

### 3. Elenco Moto in Lavorazione
Visualizza tutte le moto che non sono ancora state consegnate (status != DELIVERED).

### 4. Dettaglio Moto
Visualizza informazioni complete su una moto specifica cercando per:
- ID (identificativo numerico)
- Targa (license plate)

### 5. Importazione CSV Giornaliera
Importa automaticamente le moto dal file CSV del giorno corrente (formato: YYYYMMDD.csv).

**Formato CSV**:
```csv
Brand,Model,Plate,Power,Cost,Work,Price
Ducati,Panigale V4,AB123CD,1103,25000,2000,32000
Honda,CBR1000RR,EF456GH,998,18000,1500,24000
```

## Entity: Bike

### Attributi
- `id`: Identificativo univoco (gestito dal database)
- `brand`: Marca della moto
- `model`: Modello
- `plate`: Targa
- `power`: Cilindrata in cc
- `cost`: Costo di acquisto
- `work`: Costo di lavorazione
- `price`: Prezzo di vendita
- `status`: Stato corrente nel workflow (BikeStatus)

### Metodi Principali
- `advance()`: Fa avanzare la moto allo stato successivo
- `getErrors()`: Restituisce lista errori di validazione
- `toString()`: Rappresentazione testuale completa

## Esempio di Utilizzo

```java
// Creazione e inserimento manuale
Bike bike = new Bike(
    "Yamaha",
    "MT-07",
    "XY789ZW",
    689,
    8000,
    800,
    11000,
    BikeStatus.ARRIVED
);

// Validazione
List<String> errors = bike.getErrors();
if (errors.isEmpty()) {
    bike = bikeRepo.insert(bike);
}

// Avanzamento workflow
bike.advance();  // ARRIVED → CHECKUP
bikeRepo.update(bike);

bike.advance();  // CHECKUP → CLEANING
bikeRepo.update(bike);

bike.advance();  // CLEANING → DELIVERED
bikeRepo.update(bike);
```

## Query SQL Implementate

### FindAll
```sql
SELECT * FROM bike
```

### FindById
```sql
SELECT * FROM bike WHERE id = ?
```

### FindByPlate
```sql
SELECT * FROM bike WHERE plate = ?
```

### FindProcessing
```sql
SELECT * FROM bike WHERE status_order < 4 ORDER BY id DESC
```
Restituisce tutte le moto non ancora consegnate, ordinate per ID decrescente (più recenti prima).

### Insert
```sql
INSERT INTO bike (brand, model, plate, power, cost, work, price, status, status_order)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
```

### Update
```sql
UPDATE bike SET brand=?, model=?, plate=?, power=?, cost=?, work=?,
               price=?, status=?, status_order=?
WHERE id=?
```

### Delete
```sql
DELETE FROM bike WHERE id = ?
```

## Database Schema

```sql
CREATE TABLE bike (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    plate TEXT NOT NULL UNIQUE,
    power INTEGER NOT NULL,
    cost INTEGER NOT NULL,
    work INTEGER NOT NULL,
    price INTEGER NOT NULL,
    status TEXT NOT NULL,
    status_order INTEGER NOT NULL
);
```

## Design Patterns in Dettaglio

### 1. Repository Pattern
**Problema**: Accoppiamento stretto tra business logic e database
**Soluzione**: Interfaccia che astrae le operazioni CRUD
**Benefici**:
- Testing semplificato (mock repositories)
- Cambio database trasparente
- Centralizzazione query

### 2. State Pattern
**Problema**: Gestione complessa degli stati con switch/if
**Soluzione**: Enum con comportamenti incapsulati
**Benefici**:
- Type-safe (impossibile stato invalido)
- Codice più leggibile
- Facile estensione workflow

### 3. Chain of Responsibility
**Problema**: Workflow sequenziale hardcoded
**Soluzione**: Ogni stato conosce il successore
**Benefici**:
- Workflow configurabile
- Disaccoppiamento logica business
- Facile aggiunta stati intermedi

### 4. Dependency Injection
**Problema**: Dipendenze create manualmente
**Soluzione**: IoC Container che gestisce lifecycle
**Benefici**:
- Testability (easy mocking)
- Configurazione centralizzata
- Loose coupling

### 5. ETL Pattern
**Problema**: Import dati da fonti esterne
**Soluzione**: Extractor dedicato per CSV
**Benefici**:
- Separazione concerns
- Riusabilità
- Error handling centralizzato

## Estensioni Possibili

### Funzionalità Aggiuntive
- [ ] Ricerca avanzata per marca/modello
- [ ] Statistiche vendite per periodo
- [ ] Gestione utenti e permessi
- [ ] Notifiche email al cambio stato
- [ ] Dashboard con KPI
- [ ] Export report PDF/Excel
- [ ] Integrazione API REST

### Miglioramenti Tecnici
- [ ] Transazioni database
- [ ] Connection pooling
- [ ] Logging strutturato (SLF4J)
- [ ] Validazione Bean Validation (JSR-303)
- [ ] Migration tool (Flyway/Liquibase)
- [ ] Spring Boot integration
- [ ] REST API con Spring MVC

## Testing

### Unit Test Implementati
- `BikeTest`: Test validazione entity
- `BikeStatusTest`: Test workflow stati
- `BikeDailyExtractorTest`: Test import CSV

### Coverage
- Entity validation: 100%
- State transitions: 100%
- CSV parsing: 100%
- Repository methods: Copertura parziale

## Concetti Java Utilizzati

### OOP Avanzato
- Interfacce per astrazione
- Enum con metodi
- Ereditarietà (Entity)
- Polimorfismo

### Java 8+
- Lambda expressions
- Functional interfaces
- Stream API (in views)
- Optional (potenziale)

### JDBC
- Prepared statements (SQL injection prevention)
- ResultSet mapping
- Transaction management (base)
- AutoCloseable resources

### Design Principles
- SOLID principles
- DRY (Don't Repeat Yourself)
- Separation of Concerns
- Dependency Inversion

## Gestione Errori

### Validazione Entity
```java
List<String> errors = bike.getErrors();
if (!errors.isEmpty()) {
    errors.forEach(e -> Console.print("- " + e + "\n"));
}
```

### SQL Exceptions
```java
try {
    bike = bikeRepo.insert(bike);
} catch (SQLException e) {
    Console.print("Errore database: " + e.getMessage());
}
```

### File Not Found (CSV Import)
```java
try {
    List<Bike> loaded = extractor.extractFrom(filename);
} catch (FileNotFoundException e) {
    Console.print("Nessun file da caricare oggi");
}
```

## Note di Implementazione

### Gestione Stato Finale
Il metodo `getNext()` in `BikeStatus` restituisce se stesso quando è nello stato DELIVERED:
```java
public BikeStatus getNext() {
    return next != null ? next : this;
}
```
Questo previene errori nella logica di avanzamento.

### Autowiring by Type
Il container DI usa reflection per l'autowiring:
```java
if (dependencyNeeded.isAssignableFrom(o.getClass())) {
    return dependencyNeeded.cast(o);
}
```

### CSV Parsing Robusto
Il sistema gestisce:
- File mancanti (FileNotFoundException)
- File corrotti (RuntimeException)
- Righe invalide (validazione entity)

## Autore

**Viorica Gabriela Hacman**
- 🎓 Generation Italy - Java Full Stack Developer Bootcamp
- 📧 hacmanvioricagabriela@gmail.com

## Licenza

Progetto educativo - Generation Italy
