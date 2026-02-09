<div align="center">

# ACMC2ORM — Custom ORM Framework

**Advanced Object-Relational Mapping System for Association Management**

</div>

---

Evoluzione del progetto ACMC che implementa un **framework ORM custom** dimostrando i principi e pattern utilizzati nei tool professionali come Hibernate, ma con pieno controllo e trasparenza educativa.

---

## 1. Panoramica del Progetto

ACMC2ORM è un sistema gestionale per associazioni no-profit che implementa da zero un layer di **Object-Relational Mapping**. A differenza di framework come Hibernate o JPA, questo progetto costruisce manualmente tutti i meccanismi ORM per scopi didattici.

### Caratteristiche Principali

- **Custom ORM Layer** con mapping automatico entità-database
- **Generic Repository Pattern** con parametri di tipo per operazioni CRUD type-safe
- **Pluggable Caching Strategies** (FullCache e PartialCache intercambiabili)
- **Bidirectional Entity Relationships** gestione automatica relazioni parent-child
- **Dependency Injection Container** per gestione centralizzata dipendenze
- **Circular Dependency Prevention** gestione intelligente caricamento relazioni

---

## 2. Architettura e Pattern

### 2.1 Custom ORM - Come Funziona

Il sistema implementa un ORM completo che mappa automaticamente:
- Righe del database → Oggetti Java
- Oggetti Java → Comandi SQL INSERT/UPDATE
- Relazioni 1-to-many → Collection in memoria

**Processo di mapping automatico:**
```java
// Invece di scrivere manualmente ogni volta:
// String sql = "SELECT * FROM member WHERE id = ?";
// ResultSet rs = ...;
// Member m = new Member(rs.getString("firstName"), rs.getString("lastName"), ...);

// L'ORM fa tutto automaticamente:
Member m = memberRepo.findById(123);  // Mapping automatico!
```

### 2.2 Generic Repository Pattern

Il cuore del sistema è `SQLEntityRepository<X extends Entity>`:

```java
public abstract class SQLEntityRepository<X extends Entity>
    implements EntityRepository<X> {

    // Metodi concreti implementati UNA VOLTA per tutte le entità
    public List<X> findAll() { ... }
    public X findById(int id) { ... }
    public X insert(X entity) { ... }
    public X update(X entity) { ... }

    // Template methods - ogni repository implementa solo questi
    protected abstract X rowToX(ResultSet rs);
    protected abstract String getInsertCmd();
    protected abstract String getUpdateCmd();
}
```

**Vantaggi:**
- **Zero duplicazione codice** CRUD tra diverse entità
- **Type-safety completa** grazie ai generics
- **Estensione facile** - nuova entità richiede solo 3 metodi

### 2.3 Pluggable Caching Architecture

Il sistema supporta **due strategie di cache intercambiabili**:

#### FullCache Strategy
```java
public class FullCacheSQLEntityRepository<X extends Entity> {
    private Map<Integer, X> cache = new HashMap<>();

    // Al primo findAll(), carica TUTTA la tabella
    // Tutte le query successive usano la cache
}
```
- **Pro:** Zero query al DB dopo il primo caricamento
- **Cons:** Usa più memoria
- **Ideale per:** Tabelle piccole e statiche (enum, configurazioni)

#### PartialCache Strategy
```java
public class PartialCacheSQLEntityRepository<X extends Entity> {
    private static final int MAX_SIZE = 100;
    private Map<Integer, X> cache = new LinkedHashMap<>();

    // Cache LRU con dimensione limitata
    // Rimuove le entità più vecchie quando piena
}
```
- **Pro:** Memoria limitata e controllata
- **Cons:** Possibili query al DB per cache miss
- **Ideale per:** Tabelle grandi con locality of reference

**Sincronizzazione automatica:** Entrambe le cache si aggiornano automaticamente su INSERT/UPDATE/DELETE.

### 2.4 Bidirectional Entity Relationships

Il sistema gestisce relazioni bidirezionali automatiche:

```java
// Member.java (Parent)
public class Member extends Entity {
    private List<Donation> donations = new ArrayList<>();

    public void addDonation(Donation d) {
        d.member = this;           // ← Parent reference nel child
        donations.add(d);          // ← Child nella collection del parent
    }
}

// Donation.java (Child)
public class Donation extends Entity {
    public Member member;          // ← Riferimento al parent
}
```

**Consistenza garantita:** Impossibile avere referenze unilaterali inconsistenti.

### 2.5 Dependency Injection Container

La classe `Context` funziona come un **IoC Container custom**:

```java
public class Context {
    static List<Object> dependencies = new ArrayList<>();

    static {
        // Inizializzazione al caricamento classe
        dependencies.add(ConnectionFactory.getConnection(...));
        dependencies.add(new MemberRepositorySQL());
        dependencies.add(new DonationRepositorySQL());
    }

    public static <T> T getDependency(Class<T> dependencyNeeded) {
        // Autowiring by type usando reflection
        for(Object o : dependencies) {
            if(dependencyNeeded.isAssignableFrom(o.getClass())) {
                return dependencyNeeded.cast(o);
            }
        }
        throw new RuntimeException("Dependency not found!");
    }
}
```

**Benefici:**
- Accoppiamento lasco tra componenti
- Testing facile (dependency mocking)
- Gestione centralizzata lifecycle

### 2.6 Circular Dependency Prevention

Il sistema previene ricorsione infinita nel caricamento relazioni:

```java
// In MemberRepositorySQL
public Member findById(int id, boolean complete) {
    if(!complete) {
        return findByIdNaked(id);  // Solo dati base
    }

    Member m = findByIdNaked(id);

    // Carica donations MA dice a DonationRepo di NON ricaricare Member!
    List<Donation> donations = donationRepo.findByMemberId(id, false);
    donations.forEach(m::addDonation);

    return m;
}
```

**Senza questo meccanismo:**
```
findById(Member 1, complete=true)
  → findDonations(Member 1)
    → findMember(1, complete=true)  // LOOP!
      → findDonations(Member 1)
        → ...STACK OVERFLOW
```

---

## 3. Entità del Dominio

### 3.1 Member (Socio)
Rappresenta un membro dell'associazione con:
- Dati anagrafici (firstName, lastName, dob, email, ssn)
- Stato membership (MemberStatus enum: BANNED, NONE, BRONZE, SILVER, GOLD, GRAY)
- Collection di donazioni associate

**Logica business:**
```java
// Calcolo automatico stato in base a donazioni ultimo anno
public MemberStatus getCalculatedStatus() {
    return MemberStatus.getByThreshold(getLastYearDonations());
}
```

### 3.2 Donation (Donazione)
Traccia le donazioni dei soci:
- Importo (BigDecimal)
- Data donazione (LocalDate)
- Riferimento al Member donatore

### 3.3 Book (Libro)
Gestisce catalogo libri:
- Titolo e autore
- ISBN per identificazione univoca
- Collection di recensioni associate

### 3.4 Review (Recensione)
Recensioni dei libri:
- Punteggio (1-5 stelle)
- Testo recensione
- Riferimento al Book recensito

### 3.5 MemberStatus (Enum)
```java
public enum MemberStatus {
    BANNED  (-1, "Bannato"),
    NONE    (0,  "Nessuno"),
    BRONZE  (100, "Bronzo"),
    SILVER  (500, "Argento"),
    GOLD    (1000, "Oro"),
    GRAY    (5000, "Grigio");

    private int threshold;  // Importo minimo donazioni annuali

    public static MemberStatus getByThreshold(BigDecimal amount) {
        // Trova lo status appropriato in base all'importo
    }
}
```

---

## 4. Procedure Automatiche (Controller)

### 4.1 ProceduraAggiornamentoStatoAutomatico
Aggiorna automaticamente lo stato dei membri in base alle donazioni:

```java
public void execute() {
    List<Member> members = memberRepo.findAll();

    for(Member m : members) {
        MemberStatus calculated = m.getCalculatedStatus();

        if(m.getStatus() != calculated) {
            System.out.println(m.getFirstName() + ": " +
                              m.getStatus() + " → " + calculated);
            m.setStatus(calculated);
            memberRepo.update(m);
        }
    }
}
```

### 4.2 RiepilogoVersamentiUltimoAnnoSocioPerSocio
Genera report dettagliato donazioni per socio:

```java
public void execute() {
    memberRepo.findAll().forEach(member -> {
        System.out.println("\n=== " + member.getFirstName() + " ===");

        member.getDonations().stream()
            .filter(d -> d.getDate().getYear() == LocalDate.now().getYear())
            .forEach(d -> System.out.println("  " + d.getDate() +
                                            ": " + d.getAmount() + "€"));

        System.out.println("TOTALE: " + member.getLastYearDonations() + "€");
    });
}
```

---

## 5. Struttura del Progetto

```text
ACMC2ORM/
├── src/
│   ├── com/generation/acmc2/           # Codice applicativo
│   │   ├── context/
│   │   │   └── Context.java            # DI Container
│   │   ├── controller/
│   │   │   ├── ProceduraAggiornamentoStatoAutomatico.java
│   │   │   └── RiepilogoVersamentiUltimoAnnoSocioPerSocio.java
│   │   ├── model/entities/
│   │   │   ├── Member.java
│   │   │   ├── Donation.java
│   │   │   ├── Book.java
│   │   │   ├── Review.java
│   │   │   └── MemberStatus.java
│   │   └── repository/
│   │       ├── MemberRepository.java           # Interface
│   │       ├── MemberRepositorySQL.java        # Implementation
│   │       ├── DonationRepository.java
│   │       ├── DonationRepositorySQL.java
│   │       ├── BookRepository.java
│   │       ├── BookRepositorySQL.java
│   │       └── Test*.java                      # JUnit tests
│   │
│   └── com/generation/library/         # Framework riutilizzabile
│       ├── Entity.java                 # Classe base entità
│       ├── Console.java                # Utility I/O
│       ├── cache/
│       │   ├── EntityCache.java                # Interface cache
│       │   ├── FullCache.java                  # Full table caching
│       │   └── PartialCache.java               # LRU caching
│       ├── database/
│       │   └── ConnectionFactory.java          # JDBC pooling
│       ├── profiling/
│       │   └── ProfilingMonitor.java          # Performance tracking
│       └── repository/
│           ├── EntityRepository.java           # Generic interface
│           ├── SQLEntityRepository.java        # Base implementation
│           ├── FullCacheSQLEntityRepository.java
│           └── PartialCacheSQLEntityRepository.java
│
├── acmc.db                             # SQLite database
├── schema.sql                          # Database schema
└── README.md
```

---

## 6. Database Schema

```sql
-- Member table
CREATE TABLE member (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    dob TEXT NOT NULL,
    email TEXT,
    ssn TEXT UNIQUE,
    status TEXT NOT NULL,
    CHECK(status IN ('BANNED','NONE','BRONZE','SILVER','GOLD','GRAY'))
);

-- Donation table
CREATE TABLE donation (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    amount REAL NOT NULL,
    date TEXT NOT NULL,
    member_id INTEGER NOT NULL,
    FOREIGN KEY(member_id) REFERENCES member(id)
);

-- Book table
CREATE TABLE book (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    isbn TEXT UNIQUE
);

-- Review table
CREATE TABLE review (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    score INTEGER NOT NULL CHECK(score BETWEEN 1 AND 5),
    text TEXT,
    book_id INTEGER NOT NULL,
    FOREIGN KEY(book_id) REFERENCES book(id)
);
```

---

## 7. Principi SOLID Implementati

| Principio | Implementazione |
|-----------|-----------------|
| **S - Single Responsibility** | Ogni repository gestisce una sola entità; cache separate dalla logica business |
| **O - Open/Closed** | Nuove entità estendono SQLEntityRepository senza modificarlo |
| **L - Liskov Substitution** | FullCache e PartialCache intercambiabili trasparentemente |
| **I - Interface Segregation** | Repository divisi per entità (non un unico "God Repository") |
| **D - Dependency Inversion** | Controller dipendono da interfacce Repository, non implementazioni |

---

## 8. Differenze con ACMC Originale

| Aspetto | ACMC | ACMC2ORM |
|---------|------|----------|
| **Astrazione DB** | Query dirette sparse nel codice | Layer ORM completo |
| **Mapping** | Manuale row-to-object ogni volta | Automatico via Template Method |
| **Relazioni** | Join manuali | Caricamento automatico relazioni |
| **Caching** | Assente o ad-hoc | Strategia pluggable con sincronizzazione |
| **Riutilizzo** | Codice specifico ACMC | Framework `library` riutilizzabile |
| **Type Safety** | Operazioni basate su String | Generics `<X extends Entity>` |
| **Testing** | Test unitari base | Test con cache-aware assertions |

---

## 9. Tecnologie Utilizzate

| Componente | Tecnologia | Dettagli |
|-----------|-----------|---------|
| **Linguaggio** | Java 21 | Generics, Reflection, Lambda, Stream API |
| **Database** | SQLite + JDBC | sqlite-jdbc-3.51.1.0.jar |
| **Testing** | JUnit 5 | Jupiter API per unit tests |
| **Patterns** | ORM, Repository, DI, Strategy, Template Method | Pattern multipli combinati |
| **Date/Time** | LocalDate API | ISO 8601 format (yyyy-MM-dd) |
| **Finanza** | BigDecimal | Calcoli precisi donazioni |

---

## 10. Pattern di Design Utilizzati

1. **ORM Pattern** → Mapping automatico oggetti-relazionale
2. **Generic Repository Pattern** → CRUD type-safe con parametri di tipo
3. **Template Method Pattern** → SQLEntityRepository definisce skeleton
4. **Strategy Pattern** → FullCache vs PartialCache intercambiabili
5. **Decorator Pattern** → Cache wrapping attorno a repository base
6. **Dependency Injection** → IoC Container con autowiring
7. **Factory Pattern** → ConnectionFactory per JDBC
8. **Lazy Loading** → Caricamento relazioni on-demand con flag `complete`
9. **Bidirectional Association** → Referenze parent↔child consistenti
10. **Profiling Pattern** → ProfilingMonitor per tracking performance

---

## 11. Testing

Il progetto include test completi per:

- **Repository CRUD operations** (insert, update, delete, findAll)
- **Relationship loading** (lazy vs eager)
- **Cache synchronization** (insert/update invalida cache)
- **Circular dependency prevention** (no stack overflow)
- **Business logic** (status calculation, donations filtering)

Esempio test:
```java
@Test
public void testFindByIdWithRelations() {
    Member m = memberRepo.findById(1, true);  // complete=true

    assertNotNull(m.getDonations());
    assertTrue(m.getDonations().size() > 0);

    // Verifica bidirectional reference
    m.getDonations().forEach(d ->
        assertEquals(m, d.getMember())
    );
}
```

---

## 12. Performance e Monitoring

Il sistema include `ProfilingMonitor` per tracciare:
- Numero totale query eseguite
- Numero righe processate
- Efficacia cache (hit/miss ratio)

```java
public class ProfilingMonitor {
    public static int queryNumber = 0;
    public static int rowsNumbers = 0;

    // Integrato in SQLEntityRepository
    public List<X> findAll() {
        queryNumber++;  // Tracking automatico
        // ...
    }
}
```

---

## 13. Concetti Avanzati Dimostrati

### SQL Injection Prevention
```java
PreparedStatement ps = conn.prepareStatement(
    "INSERT INTO member (firstName, lastName, ...) VALUES (?, ?, ...)"
);
ps.setString(1, member.getFirstName());  // Parametrizzato!
```

### Type-Safe Generics
```java
public interface EntityRepository<X extends Entity> {
    List<X> findAll();      // Ritorna List<Member>, non List<Object>
    X insert(X entity);     // Compile-time type checking
}
```

### Reflection per Autowiring
```java
if(dependencyNeeded.isAssignableFrom(o.getClass())) {
    return dependencyNeeded.cast(o);  // Cast sicuro via reflection
}
```

---

## Autore

**Viorica Gabriela Hacman**
- 🎓 Generation Italy - Java Full Stack Developer Bootcamp
- 📧 hacmanvioricagabriela@gmail.com

## Licenza

Progetto educativo - Generation Italy

---

## Note Finali

Questo progetto dimostra come costruire un ORM da zero, fornendo comprensione profonda dei meccanismi interni di framework come Hibernate. L'approccio didattico privilegia la trasparenza e la comprensione rispetto alla complessità di soluzioni enterprise-ready.

---

<div align="center">

[Torna a Commerce & Services](../README.md) · [README principale](../../../../README.md)

</div>
