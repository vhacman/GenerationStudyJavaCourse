<div align="center">

# Carroponte — Sistema di Gestione Eventi

**Architettura enterprise con Command Pattern, Caching e IoC Container**

</div>

---

Sistema di gestione eventi con architettura avanzata che implementa pattern di design moderni e best practices di sviluppo enterprise.

## Descrizione

Carroponte è un'applicazione di gestione eventi (concerti, sagre, spettacoli) che dimostra l'implementazione di pattern architetturali avanzati come Dependency Injection, Repository Pattern, Command Pattern e caching multi-livello.

## Caratteristiche Principali

### Architettura
- **Dependency Injection Container**: Gestione centralizzata delle dipendenze tramite Context (IoC)
- **Repository Pattern**: Astrazione del layer di persistenza con supporto generics
- **Command Pattern**: Interfaccia a linea di comando estensibile
- **MVC Pattern**: Separazione tra Model, View e Controller
- **Caching Strategy**: Implementazione di cache full e partial per ottimizzazione prestazioni

### Funzionalità
- Visualizzazione eventi con ordinamento dinamico
- Ordinamento per titolo, prezzo e data
- Persistenza su database SQLite
- Sistema di cache per ottimizzazione query
- Cifratura XOR per sicurezza dati
- Validazione entità con feedback dettagliato
- Performance monitoring integrato

## Struttura del Progetto

```
Carroponte/
├── src/com/generation/
│   ├── cp/                           # Package specifico Carroponte
│   │   ├── context/                  # Dependency Injection Container
│   │   │   ├── Context.java          # IoC Container
│   │   │   └── ContextTest.java      # Test del container
│   │   ├── controller/               # Layer Controller (MVC)
│   │   │   └── Main.java             # Entry point applicazione
│   │   ├── cypher/                   # Sistema cifratura
│   │   │   ├── Cypher.java           # Interfaccia cifratura
│   │   │   └── XORCypher.java        # Implementazione XOR
│   │   └── model/                    # Layer Model (MVC)
│   │       ├── entities/             # Entità di dominio
│   │       │   ├── Event.java        # Classe Event
│   │       │   ├── CompareByPriceAscending.java
│   │       │   └── DemoOrdinamentoEventi.java
│   │       └── repository/           # Repository Pattern
│   │           ├── EventRepository.java       # Interfaccia repository
│   │           └── SQLEventRepository.java    # Implementazione SQL
│   └── library/                      # Framework riutilizzabile
│       ├── cache/                    # Sistema caching
│       │   ├── EntityCache.java      # Interfaccia cache
│       │   ├── FullCache.java        # Cache completa
│       │   └── PartialCache.java     # Cache parziale
│       ├── database/                 # Database utilities
│       │   └── ConnectionFactory.java
│       ├── repository/               # Repository generici
│       │   ├── SQLEntityRepository.java
│       │   ├── FullCacheSQLEntityRepository.java
│       │   └── PartialCacheSQLEntityRepository.java
│       ├── view/                     # View utilities e demo
│       │   ├── EntityView.java       # Rendering entità
│       │   ├── LambdaDemo.java       # Demo espressioni lambda
│       │   └── Nerd.java             # Demo interfacce funzionali
│       ├── profiling/                # Performance monitoring
│       │   └── ProfilingMonitor.java
│       ├── Entity.java               # Classe base entità
│       ├── Console.java              # I/O console
│       ├── FileReader.java           # Lettura file
│       ├── FileWriter.java           # Scrittura file
│       └── Template.java             # Template loading
└── carroponte.db                     # Database SQLite
```

## Pattern Implementati

### 1. Dependency Injection (IoC Container)
Il `Context` gestisce il ciclo di vita delle dipendenze:
```java
EventRepository eventRepo = Context.getDependency(EventRepository.class);
```
- Registrazione centralizzata delle dipendenze
- Type-safe dependency resolution con generics
- Fail-fast initialization
- Thread-safe per design

### 2. Repository Pattern
Astrazione completa del layer di persistenza:
```java
public interface EventRepository {
    List<Event> findAll();
    void setOrder(String orderBy);
}
```
- Separazione logica business da persistenza
- Facilità di testing con mock repositories
- Supporto per multiple implementazioni (SQL, NoSQL, in-memory)

### 3. Template Method Pattern
La classe base `Entity` definisce il contratto per la validazione:
```java
public abstract List<String> getErrors();
public boolean isValid() { return getErrors().isEmpty(); }
```

### 4. Caching Strategy Pattern
Tre strategie di caching implementate:
- **No Cache**: Query diretta al database
- **Full Cache**: Cache di tutte le entità in memoria
- **Partial Cache**: Cache selettiva basata su query

### 5. Command Pattern
Interfaccia CLI estensibile:
```java
switch(cmd) {
    case "setorder": /* gestione comando */
    case "findall": /* gestione comando */
}
```

## Tecnologie Utilizzate

- **Java**: Linguaggio principale
- **JDBC**: Connessione database
- **SQLite**: Database embedded
- **Java Generics**: Type safety per repository
- **Java Reflection**: Type checking nel DI container
- **Lambda Expressions**: Demo interfacce funzionali
- **LocalDate API**: Gestione date

## Principi SOLID Applicati

1. **Single Responsibility**: Ogni classe ha una responsabilità ben definita
2. **Open/Closed**: Architettura aperta a estensioni, chiusa a modifiche
3. **Liskov Substitution**: Le implementazioni sono intercambiabili
4. **Interface Segregation**: Interfacce specifiche e focalizzate
5. **Dependency Inversion**: Dipendenze su astrazioni, non su implementazioni

## Comandi Disponibili

| Comando | Descrizione |
|---------|-------------|
| `findall` | Visualizza tutti gli eventi con l'ordinamento corrente |
| `setorder` | Imposta criterio di ordinamento (title, price, date) |
| `help` | Mostra lista comandi disponibili |
| `QUIT` | Termina l'applicazione |

## Esempio di Utilizzo

```
Inserire comando
> help
Comandi disponibili:
- setorder: Imposta criterio di ordinamento
- findall: Visualizza tutti gli eventi
- QUIT: Termina l'applicazione

Inserire comando
> setorder
Inserire ordine (title, price, date):
> price

Inserire comando
> findall
Event [id+1 title=Concerto Rock, description=Band live, date=2024-06-15, price=25]
Event [id+2 title=Sagra Estiva, description=Eventi gastronomici, date=2024-07-20, price=0]
Event [id+3 title=Teatro Classico, description=Spettacolo serale, date=2024-08-10, price=35]
```

## Concetti Avanzati Dimostrati

### Interfacce Funzionali e Lambda
Il progetto include demo di espressioni lambda e interfacce funzionali nella classe `LambdaDemo` e `Nerd`.

### Performance Monitoring
Il `ProfilingMonitor` permette di tracciare i tempi di esecuzione delle operazioni:
```java
ProfilingMonitor.start("operationName");
// operazione da monitorare
ProfilingMonitor.stop("operationName");
```

### Cifratura Dati
Sistema di cifratura XOR per protezione dati sensibili:
```java
XORCypher cypher = Context.getDependency(XORCypher.class);
String encrypted = cypher.encrypt(plainText);
String decrypted = cypher.decrypt(encrypted);
```

## Vantaggi Architetturali

1. **Testabilità**: Dependency Injection facilita unit testing con mock objects
2. **Manutenibilità**: Separazione chiara delle responsabilità
3. **Estensibilità**: Facile aggiungere nuove implementazioni
4. **Performance**: Caching strategy per ottimizzazione query
5. **Type Safety**: Uso estensivo di generics per evitare errori runtime
6. **Scalabilità**: Architettura pronta per crescita applicazione

## Note Tecniche

- Il database viene creato automaticamente se non esiste
- La connessione al database è gestita dal `ConnectionFactory`
- Il sistema di validazione è integrato nelle entità
- Il context viene inizializzato al primo utilizzo (lazy loading)
- Gli errori di configurazione causano terminazione immediata (fail-fast)

## Competenze Dimostrate

- Architettura a layer (MVC)
- Design patterns enterprise
- Dependency Injection
- Generic programming
- Database programming (JDBC)
- Interfacce funzionali
- Performance optimization
- Error handling robusto
- Clean code principles

---

<div align="center">

[Torna a Commerce & Services](../README.md) · [README principale](../../../../README.md)

</div>
