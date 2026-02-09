
# 🎓 Corso Java - Generation Italy

Questa repository contiene tutti i miei progetti, esercizi ed esempi di programmazione Java sviluppati durante il bootcamp Full Stack Java Developer di Generation Italy.

## 📁 Struttura della Repository

I progetti sono organizzati in 6 categorie tematiche:

```
GenerationStudyJavaCourse/
├── 📚 00_Libraries/                         # Framework e librerie riutilizzabili
├── 📘 01_Fundamentals_Examples/             # Concetti base, strutture dati e OOP
├── 🚆 02_Tickets_Transportation/            # Sistemi di biglietteria e trasporti
├── 💼 03_Business_Applications/             # Applicazioni business e gestionali
├── 🎯 04_Exercises_Practice/                # Esercizi extra e pratica
├── 🌱 05_Spring & FrontEnd - FullStack/     # Spring Boot, JPA, Database e Frontend
└── 🗄️  06_Database - MySql/                 # Corso fondamentali MySQL (DDL, DML, query)
```

---

### 📚 Librerie e Framework (00_Libraries/)

| Libreria | Descrizione | Pattern Implementati |
| --- | --- | --- |
| [GenerationLibrary](00_Libraries/GenerationLibrary/README.md) | Framework Repository Pattern con Generici | Template Method, Repository Pattern, Generics, JDBC |

**GenerationLibrary** fornisce:

- Classe base `Entity` con validazione incorporata
- Repository generico `SQLEntityRepository<T extends Entity>` per operazioni CRUD
- Implementazioni concrete: Car, Pet, Plant
- Test JUnit 5 completi
- Database SQLite embedded

---

### 📘 Fondamentali (01_Fundamentals_Examples/)

#### Strutture Dati e Collections

- **[AlgoritmiDemo - Strutture Dati](01_Fundamentals_Examples/AlgoritmiDemo%20-%20Strutture%20Dati/README.md)** - Array, ArrayList, Set, HashMap e algoritmi base
  - 9 demo complete su strutture dati fondamentali
  - Pattern comuni: Counting, Grouping, Filtering, Deduplication
  - Caso d'uso reale: Hotel Transilvania (gestione disponibilità con Map)
  - Algoritmi di ricerca, filtraggio e conteggio

#### Programmazione Base e OOP

- [docs/02-fundamentals/examples.md](docs/02-fundamentals/examples.md) - Sintassi base Java
- [docs/02-fundamentals/sequenza-selezione.md](docs/02-fundamentals/sequenza-selezione.md) - Istruzioni condizionali
- [docs/02-fundamentals/while.md](docs/02-fundamentals/while.md) - Iterazione con while
- [docs/02-fundamentals/modulo4.md](docs/02-fundamentals/modulo4.md) - For loops e iterazioni
- [docs/02-fundamentals/modulo6.md](docs/02-fundamentals/modulo6.md) - Classi e oggetti
- [docs/02-fundamentals/modulo7.md](docs/02-fundamentals/modulo7.md) - Getter, setter, visibilità
- [docs/02-fundamentals/modulo8.md](docs/02-fundamentals/modulo8.md) - Extends e super
- [docs/02-fundamentals/modulo9.md](docs/02-fundamentals/modulo9.md) - Classi astratte
- [docs/02-fundamentals/modulo10.md](docs/02-fundamentals/modulo10.md) - Implements
- [01_Fundamentals_Examples/Modulo11/README.md](01_Fundamentals_Examples/Modulo11/README.md) - Lambda expressions, Stream API
- [docs/02-fundamentals/recap.md](docs/02-fundamentals/recap.md) - Riepilogo concetti

---

### 🚆 Trasporti e Biglietteria (02_Tickets_Transportation/)

| Progetto | Descrizione | Tecnologie |
| --- | --- | --- |
| [docs/04-advanced-projects/brianza-taxi.md](docs/04-advanced-projects/brianza-taxi.md) | Sistema di prenotazione taxi | OOP, Collections |
| [docs/04-advanced-projects/brianza-taxi-service.md](docs/04-advanced-projects/brianza-taxi-service.md) | Servizio taxi con calcolo tariffe | Inheritance, Polymorphism |
| [docs/04-advanced-projects/brianza-trains.md](docs/04-advanced-projects/brianza-trains.md) | Sistema biglietti treno | Classes, Methods |
| [docs/04-advanced-projects/discoteca-ticket.md](docs/04-advanced-projects/discoteca-ticket.md) | Biglietti eventi con restrizioni età | Validation, Date API |
| [docs/04-advanced-projects/ml-trains.md](docs/04-advanced-projects/ml-trains.md) | Gestione treni Milano-Lecco | OOP |
| [docs/04-advanced-projects/milano-lecco-trains2.md](docs/04-advanced-projects/milano-lecco-trains2.md) | Sistema treni v2 | Enhanced OOP |
| [docs/04-advanced-projects/milano-lecco-trains3.md](docs/04-advanced-projects/milano-lecco-trains3.md) | Sistema treni v3 | Advanced Features |
| [docs/04-advanced-projects/milano-lecco-trains4.md](docs/04-advanced-projects/milano-lecco-trains4.md) | Sistema treni completo | Abstract Classes, Enums |
| [docs/04-advanced-projects/monza-metro.md](docs/04-advanced-projects/monza-metro.md) | Biglietteria metropolitana | BigDecimal, Pricing |

---

### 💼 Applicazioni Business (03_Business_Applications/)

La sezione business è organizzata in 4 sottocategorie tematiche:

#### 🏨 [01_Hospitality_Tourism](03_Business_Applications/01_Hospitality_Tourism/) - Ospitalità e Turismo

[GrottammareB&B](03_Business_Applications/01_Hospitality_Tourism/GrottammareB&B/) • [LeccoB&B](03_Business_Applications/01_Hospitality_Tourism/LeccoB&B/) • [MonsterHotel](03_Business_Applications/01_Hospitality_Tourism/MonsterHotel/) • [ProlocoLakeComo](03_Business_Applications/01_Hospitality_Tourism/ProlocoLakeComo/) • [SBBB](03_Business_Applications/01_Hospitality_Tourism/SBBB/) • [SBHotel](03_Business_Applications/01_Hospitality_Tourism/SBHotel/) • [VacanzeRomane](03_Business_Applications/01_Hospitality_Tourism/VacanzeRomane/) • [VillaMelzi](03_Business_Applications/01_Hospitality_Tourism/VillaMelzi/)

#### 🛒 [02_Commerce_Services](03_Business_Applications/02_Commerce_Services/) - Commercio e Servizi

**Progetti Base:**
[JavaBank](03_Business_Applications/02_Commerce_Services/JavaBank/) • [LocalMarket](03_Business_Applications/02_Commerce_Services/LocalMarket/) • [LocalMarketDB](03_Business_Applications/02_Commerce_Services/LocalMarketDB/) • [PCWizard](03_Business_Applications/02_Commerce_Services/PCWizard/) • [PrintLabel](03_Business_Applications/02_Commerce_Services/PrintLabel/) • [PrivateClinic](03_Business_Applications/02_Commerce_Services/PrivateClinic/) • [RepairShop](03_Business_Applications/02_Commerce_Services/RepairShop/)

**Progetti Enterprise (High Complexity):**
- **[ACMC](03_Business_Applications/02_Commerce_Services/ACMC/)** - Gestione Associazioni (DI, IoC Container, MVC, Repository Pattern, Reflection)
- **[ACMC2ORM](03_Business_Applications/02_Commerce_Services/ACMC2ORM/)** - Custom ORM Framework (Generic Repository, FullCache/PartialCache, Bidirectional Relations)
- **[BikeWorld](03_Business_Applications/02_Commerce_Services/BikeWorld/)** - Concessionaria Moto (State Pattern, Chain of Responsibility, ETL, CSV Import)
- **[Carroponte](03_Business_Applications/02_Commerce_Services/Carroponte/)** - Gestione Eventi (Command Pattern, Caching, IoC Container)
- **[OmegaClinic](03_Business_Applications/02_Commerce_Services/OmegaClinic/)** - Sistema Gestionale Clinica (Healthcare Management, Repository Pattern, Lazy/Eager Loading)
- **[OmegaClinicGEHENNA](03_Business_Applications/02_Commerce_Services/OmegaClinicGEHENNA/)** - Clinical Scheduling System (Smart Calendar, Scheduling Algorithms, LinkedHashMap)
- **[OmegaClinixMaxi](03_Business_Applications/02_Commerce_Services/OmegaClinixMaxi/)** - Advanced Healthcare Management (Enhanced Clinical System with Database Integration)

#### 🚆 [03_Transportation_Logistics](03_Business_Applications/03_Transportation_Logistics/) - Trasporti e Logistica

[BrianzaTaxi](03_Business_Applications/03_Transportation_Logistics/BrianzaTaxi/) • [BrianzaTaxiService](03_Business_Applications/03_Transportation_Logistics/BrianzaTaxiService/) • [BrianzaTrains](03_Business_Applications/03_Transportation_Logistics/BrianzaTrains/) • [BrianzaTrainsObjects](03_Business_Applications/03_Transportation_Logistics/BrianzaTrainsObjects/) • [DiscotecaTicket](03_Business_Applications/03_Transportation_Logistics/DiscotecaTicket/) • [JavaBus](03_Business_Applications/03_Transportation_Logistics/JavaBus/) • [JavItaAirline](03_Business_Applications/03_Transportation_Logistics/JavItaAirline/) • [MilanoLeccoTRains2](03_Business_Applications/03_Transportation_Logistics/MilanoLeccoTRains2/) • [MilanoLeccoTrains3](03_Business_Applications/03_Transportation_Logistics/MilanoLeccoTrains3/) • [MIlanoLeccoTrains4](03_Business_Applications/03_Transportation_Logistics/MIlanoLeccoTrains4/) • [MLTrains](03_Business_Applications/03_Transportation_Logistics/MLTrains/) • [MonzaMetro](03_Business_Applications/03_Transportation_Logistics/MonzaMetro/)

#### 🎓 [04_Education_Culture](03_Business_Applications/04_Education_Culture/) - Educazione e Cultura

[DeveloperCandidatura](03_Business_Applications/04_Education_Culture/DeveloperCandidatura/) • [MuseumTicket](03_Business_Applications/04_Education_Culture/MuseumTicket/) • [NerdLibrary](03_Business_Applications/04_Education_Culture/NerdLibrary/) • [NSMI](03_Business_Applications/04_Education_Culture/NSMI/) • [NSMPI](03_Business_Applications/04_Education_Culture/NSMPI/) • [PokeDex](03_Business_Applications/04_Education_Culture/PokeDex/) • [PrivateLessons](03_Business_Applications/04_Education_Culture/PrivateLessons/) • [SchoolManagement_v1](03_Business_Applications/04_Education_Culture/SchoolManagement_v1/) • [SchoolManagement_v2](03_Business_Applications/04_Education_Culture/SchoolManagement_v2/)

---

### 🎯 Esercizi e Pratica (04_Exercises_Practice/)

| Progetto | Descrizione |
| --- | --- |
| [docs/04-advanced-projects/christmas-time.md](docs/04-advanced-projects/christmas-time.md) | Progetto a tema natalizio |
| [docs/04-advanced-projects/extra-lesson.md](docs/04-advanced-projects/extra-lesson.md) | Lezione extra di approfondimento |
| [docs/04-advanced-projects/extra-lesson2.md](docs/04-advanced-projects/extra-lesson2.md) | Seconda lezione extra |
| [docs/04-advanced-projects/taxes.md](docs/04-advanced-projects/taxes.md) | Calcolo tasse e imposte |

---

### 🌱 Progetti Spring Boot (05_Spring & FrontEnd - FullStack/)

| Progetto | Descrizione | Tecnologie |
| --- | --- | --- |
| **[Emergency](05_Spring%20&%20FrontEnd%20-%20FullStack/Emergency/README.md)** | Sistema gestione pronto soccorso ospedaliero | Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL |
| **[Dinner](05_Spring%20&%20FrontEnd%20-%20FullStack/Dinner/README.md)** | Sistema di gestione ordini ristorante | Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL |
| **[Ticket](05_Spring%20&%20FrontEnd%20-%20FullStack/Ticket/README.md)** | Sistema di gestione ticket e segnalazioni | Spring Boot 4.0.2, Spring Data JPA, Thymeleaf, MySQL |
| **[Vault](05_Spring%20&%20FrontEnd%20-%20FullStack/vault/README.md)** | Sistema gestione membership Vault-Tec (Fallout) | Spring Boot 4.0.2, Spring Data JPA, MySQL |

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

---

### 🗄️ Database MySQL (06_Database - MySql/)

Materiale didattico sul corso di fondamentali MySQL, organizzato in una sequenza progressiva di script SQL:

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
| **TOTALE** | **78+ progetti e moduli** | **Full Stack** |

**Business Applications - Dettaglio:**
- 🏨 Hospitality & Tourism: 8 progetti
- 🛒 Commerce & Services: 14 progetti (7 base + 7 enterprise)
- 🚆 Transportation & Logistics: 12 progetti
- 🎓 Education & Culture: 9 progetti

**Spring Boot Projects:**
- 🌱 Spring Boot & JPA: 4 progetti (Emergency, Dinner, Ticket, Vault)

**Database:**
- 🗄️ MySQL: 7 script SQL coprenti DDL, DML, SELECT e query aggregate

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**

- 🎓 Studentessa Generation Italy - Java Full Stack Developer
- 💼 [LinkedIn](https://www.linkedin.com/in/viorica-gabriela-hacman-63a412267/)
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">
Sviluppato con dedizione durante il percorso Full Stack Java
</div>
