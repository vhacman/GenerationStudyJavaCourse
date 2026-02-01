# Private Lessons - Sistema di Gestione Lezioni Private

Un sistema completo per la gestione di lezioni private che mette in pratica pattern enterprise e la Reflection API di Java. Sviluppato durante il corso Generation Italy, questo progetto rappresenta un'evoluzione significativa rispetto ai progetti precedenti, introducendo concetti avanzati come dependency injection, template rendering dinamico e strategie di caricamento dati.

## Indice

- [Il Problema](#il-problema)
- [Scelte di Design](#scelte-di-design)
- [Architettura del Sistema](#architettura-del-sistema)
- [Come Funziona](#come-funziona)
- [Setup e Utilizzo](#setup-e-utilizzo)
- [Struttura Database](#struttura-database)

## Il Problema

Il sistema deve gestire tre tipologie di utenti (Studenti, Insegnanti, Amministratori) con workflow completamente diversi tra loro:

- Gli **studenti** cercano insegnanti per materia, prenotano lezioni e tengono traccia delle spese
- Gli **insegnanti** gestiscono il calendario e calcolano i guadagni per periodi personalizzati
- Gli **amministratori** creano utenti, controllano lo stato degli account e generano report sui guadagni

La sfida principale: evitare la duplicazione del codice mantenendo al contempo logiche separate per ogni ruolo.

## Scelte di Design

### 1. ReflectionView - Template Rendering Dinamico

**Il problema**: Creare un sistema di template che funzioni con qualsiasi entità senza dover scrivere codice specifico per ognuna.

**La soluzione**: Ho implementato `ReflectionView` che usa la Reflection API di Java per scoprire automaticamente i getter di un'entità e mappare i valori ai placeholder nei template.

```java
// src/com/generation/pl/view/ReflectionView.java
public String render(Entity entity) {
    String result = content;
    Method[] methods = entity.getClass().getMethods();

    for (Method m : methods) {
        if (m.getName().startsWith("get")) {
            String fieldName = m.getName().substring(3).toLowerCase();
            String placeholder = "[" + fieldName + "]";
            Object value = m.invoke(entity);
            result = result.replace(placeholder, value.toString());
        }
    }
    return result;
}
```

**Perché questa scelta**:
- **Zero duplicazione**: Un'unica classe gestisce il rendering di Student, Teacher, Lesson, Admin
- **Manutenibilità**: Aggiungo un campo a un'entità? Il template lo riconosce automaticamente
- **Apprendimento**: Mi ha permesso di capire come funziona la Reflection API in un contesto reale

**Il trade-off**: La Reflection è più lenta dell'accesso diretto, ma ho ottimizzato caricando i template **una sola volta** nel costruttore invece di leggerli dal disco ogni volta.

### 2. Context - Dependency Injection "Fatta in Casa"

**Il problema**: Evitare che ogni controller crei le proprie istanze dei repository, causando connessioni multiple al database e sprechi di memoria.

**La soluzione**: Ho creato un container IoC (Inversion of Control) statico che inizializza tutte le dipendenze una volta sola e le fornisce ai controller tramite generics.

```java
// src/com/generation/context/Context.java
public class Context {
    private static List<Object> dependencies = new ArrayList<>();

    static {
        Connection connection = ConnectionFactory.make("pl.db");
        dependencies.add(connection);
        dependencies.add(new MD5PasswordHasher());
        dependencies.add(new TeacherRepositorySQL(...));
        dependencies.add(new StudentRepositorySQL(...));
        dependencies.add(new LessonRepositorySQL(...));
        dependencies.add(new AdminRepositorySQL(...));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getDependency(Class<T> type) {
        for (Object dep : dependencies) {
            if (type.isInstance(dep)) {
                return (T) dep;
    }
        }
        return null;
    }
}
```

**Perché questa scelta**:
- **Singleton Pattern**: Una sola connessione al database per tutta l'applicazione
- **Type Safety**: I generics garantiscono che ricevo l'oggetto del tipo corretto
- **Centralizzazione**: Modifico le dipendenze in un solo punto
- **Semplicità**: Più semplice di Spring ma stesso principio

**Il vantaggio nascosto**: Posso facilmente sostituire `TeacherRepositorySQL` con `DemoTeacherRepository` per i test senza toccare il codice dei controller.

### 3. Eager vs Lazy Loading - Il Problema N+1

**Il problema classico**: Se carico 100 studenti e per ognuno carico le sue lezioni, faccio 101 query (1 per gli studenti + 100 per le lezioni di ognuno). Questo è il famoso problema **N+1**.

**La mia soluzione**: Ho implementato un flag `complete` che decide la strategia di caricamento.

**Perché questa scelta**:
- **Lazy loading** (`complete=false`): Query veloce quando non servono le relazioni (es. login)
- **Eager loading** (`complete=true`): Carico tutto in **2 query** invece di N+1 (studenti + lezioni)
- **Flessibilità**: Scelgo la strategia in base al contesto

**Il trade-off**: L'eager loading usa più memoria (carica anche lezioni di studenti che non mi servono), ma evita il problema N+1 che rallenterebbe molto il sistema.

### 4. ViewFactory - Pattern Factory per i Template

**Il problema**: Ogni controller deve sapere quale template usare in base al formato (TXT/HTML) e al tipo di entità (Student/Teacher/Lesson).

**La soluzione**: Un factory centralizzato che crea tutte le istanze di ReflectionView come singleton e fornisce metodi comodi per il rendering.

**Perché questa scelta**:
- **DRY**: Non ripeto il caricamento dei template in ogni controller
- **Memory efficient**: Ogni template esiste una volta sola in memoria
- **API pulita**: `ViewFactory.renderLessonsListTXT(lessons)` è molto più chiaro di costruire manualmente le view

### 5. Password Security - Separazione di Responsabilità

**Il problema**: Le password non devono MAI essere salvate in chiaro e non devono essere modificabili tramite update normali.

**La mia soluzione**:

1. **Interface-based hashing** - Posso cambiare algoritmo senza toccare il codice


2. **Metodo dedicato per cambio password** - La password NON è inclusa in `getUpdateCmd()`


3. **Password expiration per Admin** - Ogni 14 giorni devono cambiarla

**Perché queste scelte**:
- **Sicurezza**: Impedisco modifiche accidentali della password
- **Auditability**: So sempre quando è stata cambiata
- **Flessibilità**: Domani posso passare a BCrypt cambiando solo `MD5PasswordHasher`

### 6. CSV per le Materie - Denormalizzazione Pratica

**Il problema**: Un insegnante può insegnare più materie (relazione many-to-many). La soluzione "corretta" sarebbe una tabella `teacher_subjects` di join.

**La mia soluzione**: Salvo le materie come CSV nel campo `subjectsCSV` della tabella teachers: `"JAVA,SQL,PYTHON"`.



**Perché questa scelta**:
- **Semplicità**: Evito le JOIN nelle query più comuni
- **Performance**: `SELECT * FROM teachers WHERE subjectsCSV LIKE '%JAVA%'` è veloce
- **Pragmatismo**: Il numero di materie è limitato (enum), non ho milioni di combinazioni

**Il trade-off**: Non posso fare query complesse tipo "trova insegnanti che insegnano JAVA ma non SQL" senza parsing della stringa. Ma nel mio caso d'uso non serve.

### 7. FileExporter - Organizzazione Automatica

**Il problema**: I report devono essere salvati in cartelle organizzate per formato (txt/html) e ruolo (admin/teachers/students).


**Perché questa scelta**:
- **Organizzazione**: Non mi perdo tra centinaia di file
- **Automazione**: Le directory si creano da sole
- **Consistenza**: La struttura è sempre la stessa

## Architettura del Sistema

### Layer dell'Applicazione

```
┌──────────────────────────────────────────────┐
│           PRESENTATION LAYER                 │
│  Controllers (AdminMain, TeacherMain, etc.)  │  ← Menu e workflow utente
└────────────────┬─────────────────────────────┘
                 │
                 ├─────────────┐
                 │             │
┌────────────────▼───┐  ┌──────▼──────────────┐
│   BUSINESS LAYER   │  │    VIEW LAYER       │
│  Entities + Logic  │  │  ReflectionView     │  ← Template rendering
│  (Student, etc.)   │  │  ViewFactory        │
└────────────────┬───┘  └─────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│           DATA ACCESS LAYER                  │
│  Repository Interfaces + SQL Implementations │  ← Abstraction del DB
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│           PERSISTENCE LAYER                  │
│  SQLite Database (pl.db)                     │  ← Dati persistenti
└──────────────────────────────────────────────┘
```

### Pattern Implementati

1. **MVC (Model-View-Controller)**: Separazione netta tra logica, presentazione e dati
2. **Repository Pattern**: Astrazione dell'accesso ai dati
3. **Factory Pattern**: `ViewFactory` centralizza la creazione dei template
4. **Dependency Injection**: `Context` fornisce le dipendenze
5. **Strategy Pattern**: Eager/Lazy loading come strategie intercambiabili
6. **Template Method**: `SQLEntityRepository<T>` definisce il template delle operazioni CRUD

## Come Funziona

### 1. Workflow Studente - Prenotare una Lezione

```
1. Login → StudentRepositorySQL.findWhere("email=? AND password=?")
   ↓
2. Cerca insegnanti → TeacherRepositorySQL.findWhere("subjectsCSV LIKE '%JAVA%'")
   ↓
3. Visualizza lista → ViewFactory.renderTeachersListTXT(teachers)
   ↓
4. Prenota lezione → LessonRepositorySQL.save(newLesson)
   ↓
5. Genera ricevuta → ViewFactory.renderLessonReceiptHTML(lesson)
   ↓
6. Salva file → FileExporter.save(receipt, "html", "lessons", "ricevuta_" + id + ".html")
```

### 2. Workflow Insegnante - Calcolo Guadagni

```
1. Login → TeacherRepositorySQL.findById(teacherId, complete=false)
   ↓
2. Input date → Console.readLine("Start date: ") / Console.readLine("End date: ")
   ↓
3. Calcolo guadagni → LessonRepository.calculateEarningsByTeacherIdAndPeriod(id, start, end)
   Query SQL: SELECT SUM(price) FROM lessons
              WHERE teacherId=? AND date BETWEEN ? AND ?
   ↓
4. Rendering report → ViewFactory.renderTeacherEarningsTXT(teacher, earnings, start, end)
   ↓
5. Export doppio → FileExporter.save(..., "txt", ...) + FileExporter.save(..., "html", ...)
```

### 3. Workflow Admin - Creazione Utente

```
1. Login + check expiration → admin.needsPasswordChange()
   ↓ se true
2. Cambio password forzato → AdminRepositorySQL.changePassword(...)
   ↓
3. Menu admin → Scelta "Create Teacher"
   ↓
4. Raccolta dati → Collect.collectTeacherData() (DRY: riusa collectUserBaseData())
   ↓
5. Validazione → teacher.isValid() [controlla email, SSN, ecc.]
   ↓
6. Hash password → MD5PasswordHasher.hash(password)
   ↓
7. Salvataggio → TeacherRepositorySQL.save(teacher)
```

### 4. Sistema di Template - Come Funziona Internamente

**Template di partenza** (`template/html/lessons/lesson_row.html`):
```html
<tr>
    <td>[id]</td>
    <td>[date]</td>
    <td>[hour]</td>
    <td>[teacher.firstname] [teacher.lastname]</td>
    <td>[subject]</td>
    <td>€ [price]</td>
</tr>
```

**Processo di rendering**:
```java
// 1. ReflectionView carica il template (UNA VOLTA nel costruttore)
private String content = Template.read("template/html/lessons/lesson_row.html");

// 2. Il controller chiede il rendering
String html = ViewFactory.renderLessonRowHTML(lesson);

// 3. ReflectionView usa reflection per trovare i getter
Method[] methods = lesson.getClass().getMethods();
// → getId(), getDate(), getHour(), getTeacher(), getSubject(), getPrice()

// 4. Per ogni metodo sostituisce il placeholder
for (Method m : methods) {
    if (m.getName().equals("getId")) {
        String value = m.invoke(lesson); // es. "42"
        content = content.replace("[id]", value);
    }
    if (m.getName().equals("getTeacher")) {
        Teacher teacher = (Teacher) m.invoke(lesson);
        content = content.replace("[teacher.firstname]", teacher.getFirstname());
        content = content.replace("[teacher.lastname]", teacher.getLastname());
    }
    // ... altri campi
}

// 5. Risultato finale
<tr>
    <td>42</td>
    <td>2025-02-15</td>
    <td>14:00</td>
    <td>Marco Rossi</td>
    <td>JAVA</td>
    <td>€ 50.00</td>
</tr>
```

**Il vantaggio**: Aggiungo il campo `duration` a `Lesson`? Basta aggiungere `[duration]` nel template, nessuna modifica al codice Java.

## Setup e Utilizzo

### Requisiti

- Java 11+
- SQLite JDBC Driver (incluso in `lib/`)
- Generation Library (framework custom del corso)

### Prima Configurazione

Al primo avvio, se non esistono amministratori, il sistema entra in modalità setup:

```
=== PRIMA CONFIGURAZIONE ===
Nessun amministratore trovato nel sistema.
Crea il primo account admin.

Nome: Mario
Cognome: Rossi
Email: admin@privatelessons.com
SSN: 123456789
Password: ********
Conferma password: ********

Primo admin creato con successo!
```

### Compilazione ed Esecuzione

```bash
# Compila
javac -cp ".:lib/*" -d bin src/com/generation/**/*.java

# Esegui
java -cp ".:lib/*:bin" com.generation.pl.controller.Main
```

### Funzionalità Principali

**Studenti**:
- Cerca insegnanti per materia (es. `JAVA`, `SQL`)
- Prenota lezioni indicando data e ora
- Visualizza storico lezioni e spesa totale
- Genera ricevute in formato HTML

**Insegnanti**:
- Visualizza tutte le lezioni (passate e future)
- Controlla calendario della prossima settimana
- Calcola guadagni per periodo personalizzato
- Esporta report in TXT e HTML

**Amministratori**:
- Crea e gestisce utenti (studenti, insegnanti, altri admin)
- Cambia stato utenti (ACTIVE, INACTIVE, SUSPENDED)
- Visualizza guadagni insegnanti ultimi 30 giorni
- Traccia guadagni per materia
- Password expiration automatica ogni 14 giorni

## Struttura Database

### Schema Principale

```sql
-- Tabella base per tutti gli utenti
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,  -- MD5 hash
    ssn TEXT UNIQUE NOT NULL,
    dob TEXT,
    status TEXT DEFAULT 'ACTIVE'  -- ACTIVE, INACTIVE, SUSPENDED
);

-- Estensione per insegnanti
CREATE TABLE teachers (
    id INTEGER PRIMARY KEY,
    bio TEXT,
    pricePerLesson REAL NOT NULL,
    subjectsCSV TEXT NOT NULL,  -- "JAVA,SQL,PYTHON"
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Estensione per studenti (eredita solo da users)
CREATE TABLE students (
    id INTEGER PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Estensione per admin
CREATE TABLE admins (
    id INTEGER PRIMARY KEY,
    dateLastPasswordChange TEXT,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Lezioni
CREATE TABLE lessons (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    teacherId INTEGER NOT NULL,
    studentId INTEGER NOT NULL,
    date TEXT NOT NULL,
    hour TEXT NOT NULL,
    price REAL NOT NULL,
    subject TEXT NOT NULL,
    FOREIGN KEY (teacherId) REFERENCES teachers(id),
    FOREIGN KEY (studentId) REFERENCES students(id)
);
```

### Enum Supportati

**Subject**: `JAVA`, `PYTHON`, `JAVASCRIPT`, `SQL`, `HTML`, `CSS`, `REACT`, `ANGULAR`, `SPRING`, `NODEJS`

**UserStatus**: `ACTIVE`, `INACTIVE`, `SUSPENDED`

## Struttura Progetto

```
PrivateLessons/
├── src/com/generation/
│   ├── context/
│   │   └── Context.java                    # IoC Container
│   └── pl/
│       ├── controller/
│       │   ├── Main.java                    # Entry point
│       │   ├── AdminMain.java               # Menu admin
│       │   ├── TeacherMain.java             # Menu insegnanti
│       │   ├── StudentMain.java             # Menu studenti
│       │   └── utils/
│       │       ├── Collect.java             # Input utente (DRY)
│       │       └── FileExporter.java        # Export organizzato
│       ├── model/
│       │   ├── entities/
│       │   │   ├── User.java                # Classe base
│       │   │   ├── Admin.java               # + password expiration
│       │   │   ├── Teacher.java             # + bio, price, subjects
│       │   │   ├── Student.java             # + lessons list
│       │   │   ├── Lesson.java              # + teacher, student refs
│       │   │   ├── Subject.java (enum)
│       │   │   └── UserStatus.java (enum)
│       │   └── repository/
│       │       ├── interfaces/
│       │       │   ├── AdminRepository.java
│       │       │   ├── TeacherRepository.java
│       │       │   ├── StudentRepository.java
│       │       │   └── LessonRepository.java
│       │       └── SQLRepository/
│       │           ├── AdminRepositorySQL.java
│       │           ├── TeacherRepositorySQL.java   # + eager loading
│       │           ├── StudentRepositorySQL.java   # + eager loading
│       │           └── LessonRepositorySQL.java    # + earnings queries
│       ├── view/
│       │   ├── ReflectionView.java          # Template engine (reflection)
│       │   ├── ViewController.java
│       │   └── ViewFactory.java             # Template singleton factory
│       └── security/
│           ├── PasswordHasher.java          # Interface
│           └── MD5PasswordHasher.java       # Implementazione
├── template/
│   ├── txt/                                 # Template testo
│   │   ├── menu/ admin/ teacher/ student/ lessons/
│   └── html/                                # Template HTML + CSS
│       ├── admin/ teacher/ student/ lessons/
├── print/                                   # Output generato
│   ├── txt/
│   │   ├── admin/ teachers/ students/
│   └── html/
│       ├── admin/ teachers/ students/ lessons/
├── lib/
│   ├── sqlite-jdbc-3.x.x.jar
│   └── generation-library.jar              # Framework custom corso
├── pl.db                                    # Database SQLite
└── README.md
```

## Cosa Ho Imparato

1. **Reflection API**: Come usarla per creare soluzioni generiche e riutilizzabili
2. **Dependency Injection**: Il principio IoC senza framework esterni
3. **Strategie di Caricamento**: Differenza tra eager e lazy loading e quando usarli
4. **Denormalizzazione Pratica**: A volte violare le regole di normalizzazione migliora le performance
5. **Separazione di Responsabilità**: Ogni classe ha un compito preciso
6. **Template Pattern**: Come separare la struttura dal contenuto
7. **Security Best Practices**: Hash delle password, expiration policy, metodi dedicati
8. **Performance Optimization**: Caching dei template, singleton per le connessioni

## Tecnologie

- **Java 11** - Linguaggio principale
- **SQLite** - Database embedded
- **JDBC** - Connettività database
- **Reflection API** - Rendering dinamico
- **Generation Library** - Framework MVC custom
- **MD5** - Hashing password (nota: in produzione userei BCrypt)

---

**Sviluppato da Viorica Gabriela Hacman**
Progetto Business Applications - Generation Italy Java Course

*Questo progetto rappresenta un punto di svolta nel mio percorso di apprendimento Java, dove ho iniziato a pensare in termini di pattern enterprise e soluzioni scalabili anziché solo "far funzionare il codice".*

