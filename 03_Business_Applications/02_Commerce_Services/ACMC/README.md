<div align="center">

# ACMC — Sistema di Gestione Associazioni

**Enterprise Association Member and Contribution Management**

</div>

---

Sistema professionale per la gestione di realtà no-profit, basato su architettura a layer e pattern di design moderni.

---

## 1. Architettura e Dependency Injection

Il progetto ACMC si basa su un'architettura che **centralizza la gestione delle dipendenze** attraverso una classe chiamata `Context`. Questa classe funziona come un **contenitore IoC** (Inversion of Control) che si occupa di creare e fornire tutti i componenti necessari all'applicazione.

### 1.1 Meccanismo di Autowiring

Quando l'applicazione si avvia, il blocco statico del Context:
- Configura la **connessione al database**
- Registra tutte le **dipendenze principali**
- Fornisce un meccanismo di **autowiring** che risolve le dipendenze in base al loro tipo

**Vantaggio chiave:** I vari componenti dell'applicazione non devono preoccuparsi di come vengono create le loro dipendenze: chiedono semplicemente al Context di fornirgliele.

```java
// Esempio di risoluzione dipendenze
MemberRepository memberRepo = Context.getInstance(MemberRepository.class);
```

### 1.2 Separazione delle Responsabilità

L'architettura implementa il **Repository Pattern** per l'astrazione totale del layer di persistenza:
- Il resto dell'applicazione dialoga con le **interfacce** (`MemberRepository`, `DonationRepository`, `ExpenseRepository`)
- Permette di passare da SQLite a database in-memory senza modificare la business logic
- Facilita la sostituzione con Mock per i test (**Liskov Substitution Principle**)
- Garantisce contratti chiari: ogni metodo specifica cosa restituisce e quali eccezioni può lanciare

**Convenzione importante:** I metodi che restituiscono liste **non restituiscono mai null**; se non ci sono risultati, restituiscono una **lista vuota**.

---

## 2. Utility per la Validazione degli Input

### 2.1 InputValidator Pattern

La classe `InputValidator` raccoglie tutta la logica necessaria per validare gli input che arrivano dalla console, evitando di duplicare questo codice in ogni controller.

Il pattern utilizzato è quello del **ciclo infinito con ritorno anticipato**:
```java
while(true) {
    // Acquisisce input
    // Se valido → return immediatamente
    // Se non valido → richiede di nuovo
}
```

Questo approccio è più pulito rispetto all'uso di **flag booleani** per controllare l'uscita dal ciclo.

### 2.2 Conversione BigDecimal - Formato Italiano

In Italia scriviamo i numeri con il **punto come separatore delle migliaia** e la **virgola per i decimali**:
- Input italiano: `1.000,50`
- BigDecimal accetta: `1000.50` (formato americano)

La classe **`BigDecimalUtil`** si occupa di questa conversione:
- Rimuove i punti delle migliaia
- Sostituisce la virgola con il punto

```java
// Conversione da formato italiano a BigDecimal
BigDecimal amount = BigDecimalUtil.parseItalian("1.000,50");  // → 1000.50
```

### 2.3 Confronto BigDecimal

* Non usare mai `equals()` per confrontare BigDecimal!


`compareTo()` restituisce:
- **-1** se il primo valore è minore
- **0** se sono uguali
- **1** se il primo valore è maggiore

### 2.4 Gestione Date - Pattern ISO 8601

Il pattern `yyyy-MM-dd` segue lo standard ISO 8601. ** È case-sensitive:**
- `y` minuscola → anno
- `M` maiuscola → mese
- `d` minuscola → giorno

```java
LocalDate date = LocalDate.parse("2026-01-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
```

### 2.5 Enum - valueOf() e Conversione

Gli enum in Java hanno un metodo `valueOf()` che converte una stringa nella corrispondente costante dell'enum.

** Attenzione:**
- È **case-sensitive**
- Lancia `IllegalArgumentException` se la stringa non corrisponde
- Va sempre usato dentro un **blocco try-catch**


**Distinzione importante tra `name()` e `toString()`:**
- `name()` → Restituisce il nome esatto della costante (es. "BRONZE") → Usare per il database
- `toString()` → Può essere sovrascritto per una rappresentazione leggibile (es. "Bronzo") → Usare per l'interfaccia utente

---

## 3. I Controller

### 3.1 Main come Orchestratore

Il `Main` dell'applicazione funziona come un **semplice orchestratore che non contiene logica**:
- Mostra il menu principale
- Instrада le scelte dell'utente verso i controller specializzati
- **I menu sono caricati da file esterni**, il che permette di:
  - Modificarli senza ricompilare il codice
  - Facilitare eventuali traduzioni in altre lingue (i18n)

### 3.2 Controller Specializzati

Ogni controller è specializzato in un'area specifica:

| Controller | Responsabilità |
|-----------|-----------------|
| **MemberController** | Gestione soci (registrazione, ricerca, modifica livello) |
| **DonationController** | Gestione donazioni (registrazione, ricerca per periodo/socio) |
| **ExpenseController** | Gestione spese (registrazione, calcolo margini profitto) |
| **ReportController** | Report e stampe (liste HTML, carte benvenuto/promozione) |

**Dependency Injection:** Le dipendenze vengono iniettate tramite il Context, così i controller **non sono accoppiati** alle implementazioni specifiche dei repository.

### 3.3 Validation in Entity

Le **entità stesse contengono la logica di validazione** dei propri dati:
- Un oggetto `Donation` sa quali sono le regole che lo rendono valido
- Può verificarle autonomamente tramite il metodo `getErrors()`
- Questo pattern si chiama **Validation in Entity**

---

## 4. Le Entità e gli Enum

### 4.1 MembershipLevel - Gerarchia dei Livelli

L'enum `MembershipLevel` definisce i livelli di appartenenza all'associazione in **ordine gerarchico**:

```
BRONZE  →  SILVER  →  GOLD  →  GRAY  →  BANNED
```

L'ordine in cui le costanti sono dichiarate **determina la loro posizione nella gerarchia**.

L'enum contiene anche metodi utili:
- `isActive()` → Verifica se un livello permette l'accesso al sistema
- `getNextLevel()` → Ottiene il livello successivo nella progressione

### 4.2 Entity - Struttura e Validazione

Ogni entity (Member, Donation, Expense) è un **POJO con logica di validazione interna**:
- Contiene i dati relativi all'entità
- Espone getter per l'accesso ai campi
- Implementa il metodo `getErrors()` che restituisce una lista di messaggi di errore

---

## 5. I Repository

### 5.1 Contratto Repository

Le interfacce dei repository definiscono un **contratto chiaro** per le operazioni sui dati:
- Ogni metodo specifica cosa restituisce
- Ogni metodo specifica quali eccezioni può lanciare
- **Convenzione:** I metodi che restituiscono liste non restituiscono mai `null` → restituiscono una **lista vuota**

```java
public interface MemberRepository {
    Member findById(int id);
    List<Member> findAll();
    void save(Member member);
    void update(Member member);
    void delete(int id);
}
```

### 5.2 Implementazione SQL - PreparedStatement

Le implementazioni SQL dei repository usano **PreparedStatement** per eseguire le query:

**Vantaggi:**
- **Previene gli attacchi di SQL injection**
- Offre **prestazioni migliori** rispetto alla concatenazione di stringhe
- I parametri vengono indicati con `?` nel SQL
- Poi associati ai valori tramite i metodi `setXXX()` nell'ordine corretto


### 5.3 Bridge Relazionale-Oggettivo

Il metodo `rowToX` presente in ogni repository SQL è il **ponte tra il mondo relazionale del database** e quello **ad oggetti di Java**:
- Legge i valori dalle colonne del `ResultSet`
- Li usa per costruire l'oggetto corrispondente


### 5.4 Gestione Date - Conversione JDBC

**LocalDate di Java e Date di JDBC non sono direttamente compatibili:**
- **Per salvare:** `Date.valueOf(localDate)` converte LocalDate in Date
- **Per leggere:** `.toLocalDate()` sull'oggetto Date ottenuto dal database

**SQLite in particolare:** salva le date come stringhe nel formato `YYYY-MM-DD`.

```java
// Salvare
stmt.setDate(1, Date.valueOf(LocalDate.now()));

// Leggere
LocalDate date = rs.getDate("dob").toLocalDate();
```

---

## 6. Il Layer delle View

### 6.1 Due Approcci Complementari

Il progetto usa **due approcci diversi** per gestire le view:

1. **Lambda Expression nel ViewController** → View con parametri dinamici
2. **Reflection API nella ReflectionView** → View semplici e automatiche

### 6.2 Reflection API - Come Funziona

La **Reflection** è la capacità di Java di **ispezionare e manipolare le classi a runtime**:
- Invece di chiamare direttamente i metodi di un oggetto
- È possibile **scoprire dinamicamente quali metodi esistono**
- Invocarli e ottenere i risultati

Nel contesto delle view, permette di **mappare automaticamente i getter di un'entità** ai placeholder di un template:

**Processo automatico:**
1. Se un'entità ha un metodo `getFirstName()`
2. La Reflection lo trova e lo invoca
3. Sostituisce il placeholder `[firstname]` nel template con il valore ottenuto

```java
// REFLECTION: invece di chiamare direttamente member.getFirstName()
Method method = /* trovato via getMethods() */;
Object value = method.invoke(member);  // Esegue dinamicamente getFirstName()
template = template.replace("[firstname]", value.toString());
```

**Vantaggi:**
- Aggiungere un nuovo getter all'Entity lo rende **automaticamente disponibile** nei template
- **Zero boilerplate** per view standard

**Limitazioni:**
- Non gestisce parametri extra (es. oldLevel, newLevel per promotion card)
-  **I placeholder nei template DEVONO essere in lowercase:**
  - `[firstname]`  CORRETTO
  - `[firstName]`  ERRATO

### 6.3 ReflectionView - Template Caching

`ReflectionView` carica il template **una sola volta** nel costruttore e lo riutilizza per ogni rendering:

```java
// Template letto dal disco UNA VOLTA
public ReflectionView(String templatePath) {
    this.template = Template.load(templatePath);  // Caching
}

// Riutilizzo per molte entità
public String render(Entity entity) {
    String output = template;  // Copia cached
    // Reflection + sostituzione placeholder
    return output;
}
```

**Benefit:** Evita letture ripetute dal disco quando si renderizzano liste con 100+ elementi.

### 6.4 ViewFactory - Factory Pattern

`ViewFactory` applica il **Factory Pattern** per centralizzare la creazione delle view:
- Tutte le istanze di `ReflectionView` vengono create come **campi statici**
- Quando la classe viene caricata, sono **pronte all'uso** senza dover ricrearle ogni volta
- Il metodo `make()` decide quale view restituire in base ai parametri ricevuti


### 6.5 Composite Pattern per Liste

Per liste che richiedono un **template composto** (es. elenco dei membri Gray), si combina un approccio che utilizza **due template**:

1. **Template per singola riga:** `gray_member_row.html` → Contiene placeholder `[id]`, `[firstname]`, `[lastname]`, ecc.
2. **Template wrapper:** `gray_members_list.html` → Contiene header, footer e placeholder `[memberRows]`


### 6.6 Lambda Expression per View Dinamiche

Le **lambda expression** tornano utili quando servono view con **parametri dinamici** che non sono getter dell'entità.

Esempio: La **carta di promozione** richiede il livello precedente e quello nuovo, informazioni che non appartengono direttamente all'oggetto Member.

```java
// ViewController con Lambda
public static String renderPromotionCard(Member member, 
                                         MembershipLevel oldLevel, 
                                         MembershipLevel newLevel) {
    return template
        .replace("[firstname]", member.getFirstName())
        .replace("[lastname]", member.getLastName())
        .replace("[oldLevel]", oldLevel.toString())
        .replace("[newLevel]", newLevel.toString());
}
```

---

## 7. Concetti Trasversali

### 7.1 BigDecimal - Immutabilità

`BigDecimal` è una **classe immutabile**, il che significa che i metodi come `add()` e `subtract()` **non modificano l'oggetto originale** ma **restituiscono un nuovo oggetto** con il risultato.

** Bisogna sempre **riassegnare il valore:**

### 7.2 Method Reference - Sintassi Concisa

Il **method reference** indicato con `::` è un'alternativa **più concisa** alle lambda expression quando esiste già un metodo che fa quello che serve.

```java
// Lambda esplicita
donations.forEach(d -> this.printDonation(d));

// Method reference (più conciso e performante)
donations.forEach(this::printDonation);
```

Scrivere `donations.forEach(this::printDonation)` è equivalente a `donations.forEach(d -> this.printDonation(d))` ma risulta **più leggibile** e viene compilato in modo **leggermente più efficiente**.

### 7.3 Gestione Eccezioni

La gestione delle eccezioni deve garantire che **l'applicazione non si blocchi mai** in modo imprevisto:
- Gli errori vengono **catturati**
- Viene mostrato un **messaggio appropriato** all'utente
- L'applicazione **continua a funzionare** normalmente

---

## 8. Struttura del Progetto (Package)

```text
com.generation.acmc/
├── 1. controller/           # Orchestrazione e routing dei menu
│   ├── Main.java            # Entry point - orchestratore senza logica
│   ├── MemberController     # Gestione soci
│   ├── DonationController   # Gestione donazioni
│   ├── ExpenseController    # Gestione spese
│   ├── ReportController     # Report e stampe
│   └── InputValidator       # Validazione centralizzata input console
├── 2. model/
│   ├── entities/            # POJO con logica di validazione interna
│   │   ├── Member           # Entity socio
│   │   ├── Donation         # Entity donazione
│   │   ├── Expense          # Entity spesa
│   │   └── MembershipLevel  # Enum livelli (BRONZE → GRAY)
│   └── repository/          # Interfacce e implementazioni SQL
│       ├── MemberRepository         # Interface CRUD soci
│       ├── SQLMemberRepository      # Implementazione SQL
│       ├── DonationRepository       # Interface CRUD donazioni
│       ├── SQLDonationRepository    # Implementazione SQL
│       ├── ExpenseRepository        # Interface CRUD spese
│       └── SQLExpenseRepository     # Implementazione SQL
├── 3. view/                 # Logica di rendering (HTML/Text)
│   ├── ViewFactory          # Factory Pattern + Reflection (view semplici)
│   ├── ViewController       # Lambda Expression (view complesse)
│   └── ReflectionView       # Rendering automatico tramite Reflection API
├── 4. context/              # Inversion of Control (IoC) Container
│   └── Context              # Dependency Injection e autowiring
└── 5. utils/                # Validatori e utility
    └── BigDecimalUtil       # Conversione formato italiano ↔ US
```

---

## 9. Principi SOLID Dimostrati

| Principio | Implementazione |
|-----------|-----------------|
| **S (Single Responsibility)** | Ogni controller gestisce un unico dominio (Member, Donation, Expense) |
| **O (Open/Closed)** | Nuovi tipi di report possono essere aggiunti senza modificare i controller |
| **L (Liskov Substitution)** | Implementazioni SQL possono essere sostituite da Mock per i test |
| **I (Interface Segregation)** | Repository divisi per entità, non un unico "God Repository" |
| **D (Dependency Inversion)** | Controller dipendono dalle interfacce Repository, non dalle classi concrete |

---

## 10. Tecnologie Utilizzate

| Componente | Tecnologia | Dettagli |
|-----------|-----------|---------|
| **Linguaggio** | Java 21 | Stream API, LocalDate, Lambda Expression, Method Reference (::) |
| **Database** | SQLite + JDBC | Persistenza con PreparedStatement, date come stringhe YYYY-MM-DD |
| **Finanza** | BigDecimal | Immutabile: `total = total.add(amount)`. Sempre `compareTo()` per confronti |
| **Date** | LocalDate + DateTimeFormatter | Pattern ISO 8601 `yyyy-MM-dd`. Conversione JDBC: `Date.valueOf()` e `.toLocalDate()` |
| **Enum** | MembershipLevel | `name()` per DB, `toString()` per UI. `valueOf()` è case-sensitive |
| **UI** | Template-driven | Menu e template caricati da file (modificabili senza ricompilare, i18n-ready) |
| **Reflection** | Java Reflection API | `getMethods()` + `method.invoke()` per rendering automatico |
| **Lambda** | Functional Interface | Closure per parametri esterni, Method Reference per concisione |

---

## 11. Pattern di Design Utilizzati

1. **Dependency Injection (IoC Container)** → Context centralizzato
2. **Repository Pattern** → Astrazione persistenza
3. **MVC Pattern** → Model, View, Controller separati
4. **Factory Pattern** → ViewFactory
5. **Reflection Pattern** → Rendering automatico
6. **Composite Pattern** → Liste complesse (template wrapper + row template)
7. **Builder Pattern** → ViewController
8. **Lazy Initialization** → ViewFactory con campi statici
9. **Early Return Pattern** → Validazione e controllo flusso
10. **Strategy Pattern** → Validation in Entity

---
## Autore

**Viorica Gabriela Hacman**
- 🎓 Generation Italy - Java Full Stack Developer Bootcamp
- 📧 hacmanvioricagabriela@gmail.com

## Licenza

Progetto educativo - Generation Italy

---

<div align="center">

[Torna a Commerce & Services](../README.md) · [README principale](../../../../README.md)

</div>