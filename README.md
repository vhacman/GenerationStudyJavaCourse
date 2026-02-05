 

# 🎓 Corso Java - Generation Italy

Questa repository contiene tutti i miei progetti, esercizi ed esempi di programmazione Java sviluppati durante il bootcamp Full Stack Java Developer di Generation Italy.

## 📁 Struttura della Repository

I progetti sono organizzati in 7 categorie tematiche:

```text
GenerationStudyJavaCourse/
├── 📚 00_Libraries/                  # Framework e librerie riutilizzabili
├── 📘 01_Fundamentals_Examples/      # Concetti base, strutture dati e OOP
├── 🚆 02_Tickets_Transportation/     # Sistemi di biglietteria e trasporti
├── 💼 03_Business_Applications/      # Applicazioni business e gestionali
├── 🎯 04_Exercises_Practice/         # Esercizi extra e pratica
├── 🌱 05_Spring/                     # Spring Boot, JPA e Database
├── 🗄️  06_Database - MySql/          # Corso fondamentali MySQL (DDL, DML, query)
└── 🎨 07_FrontEnd - FullStack/       # Applicazioni web HTML/CSS/JavaScript

```

---

### 📚 Librerie e Framework (00_Libraries/)

<details>
<summary><b>Clicca per vedere tutte le librerie</b></summary>

| Libreria | Descrizione | Pattern Implementati |
| --- | --- | --- |
| [GenerationLibrary](https://www.google.com/search?q=00_Libraries/GenerationLibrary/README.md) | Framework Repository Pattern con Generics | Template Method, Repository Pattern, Generics, JDBC |

**GenerationLibrary** fornisce:

* Classe base `Entity` con validazione incorporata
* Repository generico `SQLEntityRepository<T extends Entity>` per operazioni CRUD
* Implementazioni concrete: Car, Pet, Plant
* Test JUnit 5 completi
* Database SQLite embedded

</details>

### 📘 Fondamentali (01_Fundamentals_Examples/)

<details>
<summary><b>Clicca per vedere tutti i moduli fondamentali</b></summary>

#### Strutture Dati e Collections

* **[AlgoritmiDemo - Strutture Dati](01_Fundamentals_Examples/AlgoritmiDemo%20-%20Strutture%20Dati/README.md)** - Array, ArrayList, Set, HashMap e algoritmi base
  - 9 demo complete su strutture dati fondamentali
  - Pattern comuni: Counting, Grouping, Filtering, Deduplication
  - Caso d'uso reale: Hotel Transilvania (gestione disponibilità con Map)
  - Algoritmi di ricerca, filtraggio e conteggio

#### Programmazione Base e OOP

* [Modulo Esempi Base](https://www.google.com/search?q=docs/02-fundamentals/examples.md) - Sintassi base Java
* [Sequenza e Selezione](https://www.google.com/search?q=docs/02-fundamentals/sequenza-selezione.md) - Istruzioni condizionali
* [Cicli While](https://www.google.com/search?q=docs/02-fundamentals/while.md) - Iterazione con while
* [Modulo 4 - Cicli e Iterazioni](https://www.google.com/search?q=docs/02-fundamentals/modulo4.md) - For loops e iterazioni
* [Modulo 6 - Basi OOP](https://www.google.com/search?q=docs/02-fundamentals/modulo6.md) - Classi e oggetti
* [Modulo 7 - Incapsulamento](https://www.google.com/search?q=docs/02-fundamentals/modulo7.md) - Getter, setter, visibilità
* [Modulo 8 - Ereditarietà](https://www.google.com/search?q=docs/02-fundamentals/modulo8.md) - Extends e super
* [Modulo 9 - Astrazione](https://www.google.com/search?q=docs/02-fundamentals/modulo9.md) - Classi astratte
* [Modulo 10 - Polimorfismo e Interfacce](https://www.google.com/search?q=docs/02-fundamentals/modulo10.md) - Implements
* [Modulo 11 - Interfacce Funzionali e Lambda](https://www.google.com/search?q=01_Fundamentals_Examples/Modulo11/README.md) - Lambda expressions, Stream API
* [Recap - Lezioni di Ripasso](https://www.google.com/search?q=docs/02-fundamentals/recap.md) - Riepilogo concetti

</details>

### 🚆 Trasporti e Biglietteria (02_Tickets_Transportation/)

<details>
<summary><b>Clicca per vedere tutti i progetti di trasporto</b></summary>

| Progetto | Descrizione | Tecnologie |
| --- | --- | --- |
| [BrianzaTaxi](https://www.google.com/search?q=docs/04-advanced-projects/brianza-taxi.md) | Sistema di prenotazione taxi | OOP, Collections |
| [BrianzaTaxiService](https://www.google.com/search?q=docs/04-advanced-projects/brianza-taxi-service.md) | Servizio taxi con calcolo tariffe | Inheritance, Polymorphism |
| [BrianzaTrains](https://www.google.com/search?q=docs/04-advanced-projects/brianza-trains.md) | Sistema biglietti treno | Classes, Methods |
| [DiscotecaTicket](https://www.google.com/search?q=docs/04-advanced-projects/discoteca-ticket.md) | Biglietti eventi con restrizioni età | Validation, Date API |
| [MLTrains](https://www.google.com/search?q=docs/04-advanced-projects/ml-trains.md) | Gestione treni Milano-Lecco | OOP |
| [MilanoLeccoTrains2](https://www.google.com/search?q=docs/04-advanced-projects/milano-lecco-trains2.md) | Sistema treni v2 | Enhanced OOP |
| [MilanoLeccoTrains3](https://www.google.com/search?q=docs/04-advanced-projects/milano-lecco-trains3.md) | Sistema treni v3 | Advanced Features |
| [MilanoLeccoTrains4](https://www.google.com/search?q=docs/04-advanced-projects/milano-lecco-trains4.md) | Sistema treni completo | Abstract Classes, Enums |
| [MonzaMetro](https://www.google.com/search?q=docs/04-advanced-projects/monza-metro.md) | Biglietteria metropolitana | BigDecimal, Pricing |

</details>

### 💼 Applicazioni Business (03_Business_Applications/)

<details>
<summary><b>Clicca per vedere tutte le applicazioni business</b></summary>

La sezione business è organizzata in 4 sottocategorie tematiche:

#### 🏨 [01_Hospitality_Tourism](03_Business_Applications/01_Hospitality_Tourism/) - Ospitalità e Turismo

[GrottammareB&B](03_Business_Applications/01_Hospitality_Tourism/GrottammareB&B/) • [LeccoB&B](03_Business_Applications/01_Hospitality_Tourism/LeccoB&B/) • [MonsterHotel](03_Business_Applications/01_Hospitality_Tourism/MonsterHotel/) • [ProlocoLakeComo](03_Business_Applications/01_Hospitality_Tourism/ProlocoLakeComo/) • [SBBB](03_Business_Applications/01_Hospitality_Tourism/SBBB/) • [SBHotel](03_Business_Applications/01_Hospitality_Tourism/SBHotel/) • [VacanzeRomane](03_Business_Applications/01_Hospitality_Tourism/VacanzeRomane/) • [VillaMelzi](03_Business_Applications/01_Hospitality_Tourism/VillaMelzi/)

#### 🛒 [02_Commerce_Services](03_Business_Applications/02_Commerce_Services/) - Commercio e Servizi

**Progetti Base:**
[JavaBank](03_Business_Applications/02_Commerce_Services/JavaBank/) • [LocalMarket](03_Business_Applications/02_Commerce_Services/LocalMarket/) • [LocalMarketDB](03_Business_Applications/02_Commerce_Services/LocalMarketDB/) • [PCWizard](03_Business_Applications/02_Commerce_Services/PCWizard/) • [PrintLabel](03_Business_Applications/02_Commerce_Services/PrintLabel/) • [PrivateClinic](03_Business_Applications/02_Commerce_Services/PrivateClinic/) • [RepairShop](03_Business_Applications/02_Commerce_Services/RepairShop/)

**Progetti Enterprise (High Complexity):**
* **[ACMC](03_Business_Applications/02_Commerce_Services/ACMC/)** - Gestione Associazioni (DI, IoC Container, MVC, Repository Pattern, Reflection)
* **[ACMC2ORM](03_Business_Applications/02_Commerce_Services/ACMC2ORM/)** - Custom ORM Framework (Generic Repository, FullCache/PartialCache, Bidirectional Relations)
* **[BikeWorld](03_Business_Applications/02_Commerce_Services/BikeWorld/)** - Concessionaria Moto (State Pattern, Chain of Responsibility, ETL, CSV Import)
* **[Carroponte](03_Business_Applications/02_Commerce_Services/Carroponte/)** - Gestione Eventi (Command Pattern, Caching, IoC Container)
* **[OmegaClinic](03_Business_Applications/02_Commerce_Services/OmegaClinic/)** - Sistema Gestionale Clinica (Healthcare Management, Repository Pattern, Lazy/Eager Loading)
* **[OmegaClinicGEHENNA](03_Business_Applications/02_Commerce_Services/OmegaClinicGEHENNA/)** - Clinical Scheduling System (Smart Calendar, Scheduling Algorithms, LinkedHashMap)
* **[OmegaClinixMaxi](03_Business_Applications/02_Commerce_Services/OmegaClinixMaxi/)** - Advanced Healthcare Management (Enhanced Clinical System with Database Integration)

#### 🚆 [03_Transportation_Logistics](03_Business_Applications/03_Transportation_Logistics/) - Trasporti e Logistica

[BrianzaTaxi](03_Business_Applications/03_Transportation_Logistics/BrianzaTaxi/) • [BrianzaTaxiService](03_Business_Applications/03_Transportation_Logistics/BrianzaTaxiService/) • [BrianzaTrains](03_Business_Applications/03_Transportation_Logistics/BrianzaTrains/) • [BrianzaTrainsObjects](03_Business_Applications/03_Transportation_Logistics/BrianzaTrainsObjects/) • [DiscotecaTicket](03_Business_Applications/03_Transportation_Logistics/DiscotecaTicket/) • [JavaBus](03_Business_Applications/03_Transportation_Logistics/JavaBus/) • [JavItaAirline](03_Business_Applications/03_Transportation_Logistics/JavItaAirline/) • [MilanoLeccoTRains2](03_Business_Applications/03_Transportation_Logistics/MilanoLeccoTRains2/) • [MilanoLeccoTrains3](03_Business_Applications/03_Transportation_Logistics/MilanoLeccoTrains3/) • [MIlanoLeccoTrains4](03_Business_Applications/03_Transportation_Logistics/MIlanoLeccoTrains4/) • [MLTrains](03_Business_Applications/03_Transportation_Logistics/MLTrains/) • [MonzaMetro](03_Business_Applications/03_Transportation_Logistics/MonzaMetro/)

#### 🎓 [04_Education_Culture](03_Business_Applications/04_Education_Culture/) - Educazione e Cultura

[DeveloperCandidatura](03_Business_Applications/04_Education_Culture/DeveloperCandidatura/) • [MuseumTicket](03_Business_Applications/04_Education_Culture/MuseumTicket/) • [NerdLibrary](03_Business_Applications/04_Education_Culture/NerdLibrary/) • [NSMI](03_Business_Applications/04_Education_Culture/NSMI/) • [NSMPI](03_Business_Applications/04_Education_Culture/NSMPI/) • [PokeDex](03_Business_Applications/04_Education_Culture/PokeDex/) • [PrivateLessons](03_Business_Applications/04_Education_Culture/PrivateLessons/) • [SchoolManagement_v1](03_Business_Applications/04_Education_Culture/SchoolManagement_v1/) • [SchoolManagement_v2](03_Business_Applications/04_Education_Culture/SchoolManagement_v2/)


</details>

### 🎯 Esercizi e Pratica (04_Exercises_Practice/)

<details>
<summary><b>Clicca per vedere tutti gli esercizi</b></summary>

* [ChristmasTime](https://www.google.com/search?q=docs/04-advanced-projects/christmas-time.md) - Progetto a tema natalizio
* [ExtraLesson](https://www.google.com/search?q=docs/04-advanced-projects/extra-lesson.md) - Lezione extra di approfondimento
* [ExtraLesson2](https://www.google.com/search?q=docs/04-advanced-projects/extra-lesson2.md) - Seconda lezione extra
* [Taxes](https://www.google.com/search?q=docs/04-advanced-projects/taxes.md) - Calcolo tasse e imposte

</details>

### 🌱 Progetti Spring Boot (05_Spring/)

<details>
<summary><b>Clicca per vedere i progetti Spring</b></summary>

| Progetto | Descrizione | Tecnologie |
| --- | --- | --- |
| **[Emergency](05_Spring/Emergency/README.md)** | Sistema gestione pronto soccorso ospedaliero | Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL |
| **[Dinner](05_Spring/Dinner/README.md)** | Sistema di gestione ordini ristorante | Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL |
| **[Ticket](05_Spring/Ticket/README.md)** | Sistema di gestione ticket e segnalazioni | Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL |
| **[Vault](05_Spring/vault/README.md)** | Sistema gestione membership Vault-Tec (Fallout) | Spring Boot 4.0.2, Spring Data JPA, MySQL |

**Caratteristiche comuni:**
- Controller MVC con Thymeleaf per interfacce web
- REST API per integrazione sistemi esterni (JSON)
- Repository Pattern con Spring Data JPA
- Entità JPA con annotazioni (@Entity, @Id, @GeneratedValue)
- Dependency Injection con Spring IoC Container
- Database MySQL con Hibernate

**Pattern Implementati:**
- MVC Pattern (Model-View-Controller)
- Repository Pattern
- RESTful API Design
- IoC (Inversion of Control)
- ORM (Object-Relational Mapping)

</details>

### 🗄️ Database MySQL (06_Database - MySql/)

<details>
<summary><b>Clicca per vedere il contenuto del corso MySQL</b></summary>

Materiale didattico sul corso di fondamentali MySQL, organizzato in una sequenza progressiva di script SQL (`Mod:01-02-03-04/`):

| File | Descrizione |
| --- | --- |
| `01_create_table.sql` | Creazione della tabella `PEOPLE` (id, firstname, lastname, dob, city, role, salary, gender) |
| `02_insert_10_people.sql` | Inserimento dei primi 10 records con 3 città, 3 minori e ruolo default EXPLORER |
| `03_update_roles.sql` | Aggiornamento ruoli: ids 1-5 → RESEARCHER, ids 6-10 → FOREST GUARDIAN |
| `04_delete_minors.sql` | Eliminazione dei minori (età < 18) usando calcolo DATEDIFF |
| `05_insert_10_more.sql` | Inserimento di altri 10 records (ids 11-20) con ruoli e salari variati |
| `06_select_queries.sql` | 6 query SELECT progressive: filtri per città, età, salario, ordinamento, LIKE e concatenazione campi |
| `07_aggregate_queries.sql` | 5 query aggregate: AVG/MIN/MAX salary raggruppate per role e gender, con filtri e ordinamento |

**Concetti trattati:** DDL (CREATE TABLE), DML (INSERT, UPDATE, DELETE), SELECT con WHERE, ORDER BY, LIKE, GROUP BY, HAVING, funzioni aggregate (AVG, MIN, MAX, COUNT), DATEDIFF, CONCAT.

</details>

### 🎨 Frontend - FullStack (07_FrontEnd - FullStack/)

<details>
<summary><b>Clicca per vedere i progetti frontend</b></summary>

Applicazioni web didattiche sviluppate con HTML, CSS e JavaScript vanilla:

#### Applicazioni Standalone

| Applicazione | File | Descrizione |
| --- | --- | --- |
| Calcolatore BMI | `bmi.html` | Calcola il BMI (peso/altezza²) con validazione form e classificazione (Sottopeso / Normopeso / Sovrappeso / Obeso) |
| Registrazione Cibi | `food.html` | Calcola le calorie da macronutrienti (carboidrati×4, proteine×4, grassi×9) con form e styling CSS |

#### Gioco di Carte (`cards/`)

Implementazione di un gioco di carte italiano a 40 carte con architettura MVC in JavaScript:

| File | Ruolo | Descrizione |
| --- | --- | --- |
| `game.html` | View | Pagina principale: carica moduli JS e CSS, contiene pulsanti draw/stop e area di visualizzazione della mano |
| `js/Card.js` | Model | Classe `Card`: rappresenta una carta con valore, seme e calcolo del game value (1-7 per figure, 0.5 per 8-10) |
| `js/Deck.js` | Model | Classe `Deck`: mazza da 40 carte (10 valori × 4 semi: denari/coppe/spade/bastoni), con shuffle e draw |
| `js/Hand.js` | Model | Classe `Hand`: gestisce la mano del giocatore, calcola lo score e verifica se si può continuare (score ≤ 7.5) |
| `js/Controller.js` | Controller | Singleton `Controller`: orchestra il flusso di gioco, aggiorna il DOM, gestisce stato (playerHand, pcHand, deck) |
| `css/gen.css` | Style | Styling delle carte: layout inline-block, immagini 50×50px, hover effect |
| `images/` | Assets | 4 immagini dei semi italiani: bastoni, coppe, denari, spade |

**Concetti trattati:** HTML5 semantico, CSS selettori e pseudo-classi, JavaScript classi (ES6), Singleton pattern, manipolazione DOM, template literals, gestione eventi.

</details>

---

## 📊 Statistiche Progetti

| Categoria | Numero Progetti / Moduli | Livello |
| --- | --- | --- |
| 📚 Librerie | 1 framework | Avanzato |
| 📘 Fondamentali | 14 moduli | Base → Avanzato |
| 🚆 Trasporti | 9 progetti | Intermedio |
| 💼 Business | **43 progetti** (8+14+12+9) | Avanzato |
| 🎯 Esercizi | 4 progetti | Vario |
| 🌱 Spring | **4 progetti** | Spring Boot |
| 🗄️ Database MySQL | 7 script SQL | Intermedio |
| 🎨 Frontend | 3 applicazioni web | Base → Intermedio |
| **TOTALE** | **81+ progetti e moduli** | **Full Stack** |

**Business Applications - Dettaglio:**
- 🏨 Hospitality & Tourism: 8 progetti
- 🛒 Commerce & Services: 14 progetti (7 base + 7 enterprise)
- 🚆 Transportation & Logistics: 12 progetti
- 🎓 Education & Culture: 9 progetti

**Spring Boot Projects:**
- 🌱 Spring Boot & JPA: 4 progetti (Emergency, Dinner, Ticket, Vault)

**Database & Frontend:**
- 🗄️ MySQL: 7 script SQL coprenti DDL, DML, SELECT e query aggregate
- 🎨 Frontend: Calcolatore BMI, Registrazione Cibi, Gioco di Carte (40 carte, architettura MVC)

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**

* 🎓 Studentessa Generation Italy - Java Full Stack Developer
* 💼 [LinkedIn](https://www.linkedin.com/in/viorica-gabriela-hacman-63a412267/)
* 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">
Sviluppato con dedizione durante il percorso Full Stack Java  
</div>
