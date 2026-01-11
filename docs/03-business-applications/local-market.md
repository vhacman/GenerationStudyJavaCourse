# LocalMarket

Sistema di gestione di un mercato locale che permette di tracciare prodotti, produttori e lotti di merce.

## Descrizione

LocalMarket è un'applicazione che gestisce l'inventario di un mercato locale con funzionalità di approvazione/revoca lotti, gestione prezzi e stati dei fornitori (attivi/bannati). I dati vengono persistiti su file system.

## Struttura del Progetto

```
LocalMarket/
├── src/
│   └── com/generation/lm/
│       ├── controller/
│       │   └── Main.java
│       ├── model/
│       │   ├── entities/
│       │   │   ├── Product.java
│       │   │   ├── Producer.java
│       │   │   ├── Batch.java
│       │   │   └── BatchStatus.java
│       │   └── repository/
│       │       ├── ProductRepository.java
│       │       ├── ProducerRepository.java
│       │       └── BatchRepository.java
│       └── view/
│           └── BatchView.java
└── print/
└── archive/
```

## Classi Principali

### Model (Entità)
- `Product.java` - Rappresenta un prodotto (nome, descrizione, prezzo unitario)
- `Producer.java` - Rappresenta un fornitore (nome legale, indirizzo, stato attivo/bannato)
- `Batch.java` - Rappresenta un lotto di merce (quantità, data, stato)
- `BatchStatus.java` - Enum per gli stati del batch (APPROVATO, REVOCATO, ecc.)

### Repository (Persistenza)
- `ProductRepository.java` - Gestione CRUD prodotti su file
- `ProducerRepository.java` - Gestione CRUD fornitori su file
- `BatchRepository.java` - Gestione CRUD lotti su file

### View
- `BatchView.java` - Renderizzazione dati con template HTML

### Controller
- `Main.java` - Controller principale con menu interattivo

## Funzionalità Implementate

1. **Gestione Produttori**
   - Inserimento/modifica produttori
   - Ban/Unban fornitori
   - Cambio indirizzo produttore

2. **Gestione Prodotti**
   - Inserimento/modifica prodotti
   - Cambio prezzo con validazione
   - Validazione descrizione

3. **Gestione Lotti**
   - Creazione lotti con approvazione/revoca
   - Caricamento batch personalizzato con prezzo unitario custom
   - Stampa scheda HTML del batch con dati correlati

4. **Validazione**
   - Validazione dati prima del salvataggio
   - Metodo `isValid()` nelle entità per garantire integrità dati

## Pattern Architetturali Utilizzati

- **MVC (Model-View-Controller)**: Main come controller, entità come model, BatchView come view
- **Repository Pattern**: Classi repository per l'accesso ai dati (file system)
- **DAO (Data Access Object)**: Repository implementano il pattern DAO con metodi `save()` e `load()`
- **Template Method**: Utilizzo di template per la renderizzazione HTML

## Esecuzione

```bash
cd LocalMarket
javac -d bin src/com/generation/lm/**/*.java
java -cp bin com.generation.lm.controller.Main
```

## Concetti Java Dimostrati

- Separazione delle responsabilità (MVC)
- File I/O per persistenza dati
- Enum per stati e costanti
- Validazione dati nelle entità
- Repository pattern per accesso dati
- Template-based rendering

## File Chiave

- `Main.java:1` - Point of entry dell'applicazione
- `Product.java:1` - Entità prodotto con validazione
- `ProductRepository.java:1` - Persistenza prodotti
