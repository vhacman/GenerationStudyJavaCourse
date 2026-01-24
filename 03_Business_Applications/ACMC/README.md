
# 🏛️ ACMC - Sistema di Gestione Associazioni

**Enterprise Association Member and Contribution Management**

Sistema professionale per la gestione di realtà no-profit, basato su architettura a layer e pattern di design moderni.

---

## 1. Architettura del Sistema

Il progetto si fonda su una netta separazione delle responsabilità per garantire manutenibilità e scalabilità:

* **1.1 Dependency Injection (IoC Container):** Gestione centralizzata delle istanze tramite la classe `Context`. Questo permette di risolvere le dipendenze in modo type-safe ed evita l'accoppiamento forte tra le classi.
* **1.2 Repository Pattern:** Totale astrazione del layer di persistenza. Il resto dell'applicazione dialoga con le interfacce (`MemberRepository`), permettendo di passare da SQLite a database in-memory senza cambiare una riga di logica di business.
* **1.3 MVC Pattern:** Suddivisione tra Modelli (Dati), View (Rendering HTML/Console) e Controller (Logica di coordinamento).

---

## 2. Funzionalità Core

### 2.1 Gestione Anagrafica Soci

Supporta un sistema di profilazione avanzato con i seguenti livelli di membership:

1. **BRONZE:** Socio base.
2. **SILVER:** Socio sostenitore.
3. **GOLD:** Socio premium con privilegi.
4. **GRAY:** Livello speciale riservato ai fondatori/amministratori.
5. **BANNED:** Accesso revocato.

### 2.2 Gestione Finanziaria

* **Donazioni:** Tracciamento degli ingressi con associazione forzata al socio e validazione temporale (non sono ammesse date future).
* **Spese:** Monitoraggio dei costi associativi per la generazione del bilancio.

### 2.3 Reporting e Output

Il sistema non si limita alla console, ma genera documenti pronti all'uso:

* **Schede Socio:** Report dettagliati in formato HTML.
* **Welcome Pack:** Lettere di benvenuto generate via template per i nuovi iscritti.
* **Promozioni:** Documentazione automatica al passaggio di livello (es. da Bronze a Silver).

---

## 3. Struttura del Progetto (Package)

```text
com.generation.acmc/
├── 1. controller/       # Orchestrazione e routing dei menu
├── 2. model/
│   ├── entities/       # POJO con logica di validazione interna
│   └── repository/     # Interfacce e implementazioni SQL/In-Memory
├── 3. view/             # Logica di rendering (HTML/Text)
├── 4. context/          # Inversion of Control (IoC) Container
└── 5. util/             # Validatori e utility (BigDecimal, Date)

```

---

## 4. Flusso Operativo (Esempio: Inserimento Donazione)

Per garantire l'integrità dei dati, il sistema segue questo workflow rigoroso:

1. **Lookup:** Verifica esistenza del socio nel database tramite `findById`.
2. **Input:** Acquisizione dell'importo tramite `InputValidator` (protezione contro formati errati).
3. **Entity Building:** Istanziazione dell'oggetto `Donation`.
4. **Validation:** Chiamata al metodo `.getErrors()` dell'entità per verificare i vincoli di business.
5. **Persistence:** Esecuzione della query tramite `DonationRepository`.
6. **Feedback:** Notifica all'utente dell'avvenuta operazione con ID generato.

---

## 5. Tecnologie Utilizzate

| Componente | Tecnologia | Motivazione |
| --- | --- | --- |
| **Linguaggio** | Java 11+ | Supporto a Stream API e LocalDate. |
| **Database** | SQLite + JDBC | Persistenza affidabile senza setup server complessi. |
| **Finanza** | `BigDecimal` | Calcoli monetari ad alta precisione (evita errori di arrotondamento dei `double`). |
| **UI** | Template-driven | Separazione dei testi dei menu dal codice sorgente. |

---

## 6. Principi SOLID Dimostrati

* **S (Single Responsibility):** Ogni controller gestisce un unico dominio.
* **O (Open/Closed):** Nuovi tipi di report possono essere aggiunti senza modificare i controller esistenti.
* **L (Liskov Substitution):** Le implementazioni SQL possono essere sostituite da Mock per i test.
* **I (Interface Segregation):** Repository divisi per entità, non un unico "God Repository".
* **D (Dependency Inversion):** I controller dipendono dalle interfacce Repository, non dalle classi concrete.

---

**Cosa vorresti approfondire ora?**

* Posso aiutarti a generare una **guida tecnica** specifica per l'implementazione del `Context` (IoC).
* Posso creare uno **schema SQL** dettagliato per le tabelle del database.
* Oppure possiamo scrivere insieme una **unit test suite** per la validazione delle entità.
