<div align="center">

# Generation Italy — Java Full Stack Developer

**Repository completa del percorso formativo: dai fondamenti Java fino a Spring Boot, Angular e MySQL**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.2-6DB33F?style=flat&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?style=flat&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![SQLite](https://img.shields.io/badge/SQLite-3.x-003B57?style=flat&logo=sqlite&logoColor=white)](https://www.sqlite.org/)
[![JUnit5](https://img.shields.io/badge/JUnit-5-25A162?style=flat&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Angular](https://img.shields.io/badge/Angular-21-DD0031?style=flat&logo=angular&logoColor=white)](https://angular.dev/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.9-3178C6?style=flat&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=flat&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

</div>

---

## Panoramica

Questa repository raccoglie **90+ progetti** sviluppati durante il bootcamp Generation Italy, organizzati in un percorso progressivo: dalla sintassi base di Java fino alla realizzazione di applicazioni web full-stack con Spring Boot e Angular.

```
GenerationStudyJavaCourse/
├── 00_Libraries/                         Framework e librerie riutilizzabili
├── 01_Fundamentals_Examples/             Fondamenti Java e OOP (12 moduli)
├── 02_Tickets_Transportation/            Sistemi di biglietteria e trasporti
├── 03_Business_Applications/             43 applicazioni gestionali
├── 04_Exercises_Practice/                Esercizi supplementari
├── 05_Spring & FrontEnd - FullStack/     Spring Boot, JPA, Frontend
├── 06_Database - MySql/                  Fondamenti SQL e query avanzate
├── 07_Angular/                           Progetti Angular 21 (8 applicazioni)
├── 08_FullStack - JS - Angular/          FullStack Node.js + Express + SQLite + Angular
└── docs/                                 Documentazione di supporto
```

---

## 00 — Librerie

| Progetto | Descrizione |
|----------|-------------|
| [GenerationLibrary](00_Libraries/GenerationLibrary/) | Framework enterprise: Repository Pattern generico, caching Full/Partial, JDBC, I/O utilities |

---

## 01 — Fondamenti Java

12 moduli didattici che coprono l'intero percorso OOP.

| Modulo | Argomento |
|--------|-----------|
| [SequenzaESelezione](01_Fundamentals_Examples/SequenzaESelezione/) | `if/else`, `switch`, operatore ternario |
| [While](01_Fundamentals_Examples/While/) | Cicli `while` e `do-while` |
| [Modulo4](01_Fundamentals_Examples/Modulo4/) | Cicli `for`, `for-each` |
| [Modulo 6](01_Fundamentals_Examples/Modulo%206/) | Classi, oggetti, costruttori |
| [Modulo7](01_Fundamentals_Examples/Modulo7Incapsulamento/) | Incapsulamento, getter/setter |
| [Modulo8](01_Fundamentals_Examples/Modulo8Ereditariet%C3%A0/) | Ereditarietà, `extends`, `super` |
| [Modulo9](01_Fundamentals_Examples/Modulo9Astrazione/) | Classi astratte |
| [Modulo10](01_Fundamentals_Examples/Modulo10/) | Interfacce, polimorfismo |
| [Modulo11](01_Fundamentals_Examples/Modulo11/) | Lambda expressions, interfacce funzionali |
| [AlgoritmiDemo](01_Fundamentals_Examples/AlgoritmiDemo%20-%20Strutture%20Dati/) | Array, ArrayList, HashSet, HashMap |
| [Examples](01_Fundamentals_Examples/Examples/) | Esempi di sintassi base |
| [Recap](01_Fundamentals_Examples/Recap/) | Lezioni di ripasso |

> **[Percorso completo e dettagli](01_Fundamentals_Examples/)**

---

## 02 — Tickets & Transportation

10 progetti per sistemi di biglietteria e trasporti, con evoluzione progressiva del progetto MilanoLeccoTrains (v1 → v4).

| Progetto | Descrizione | Livello |
|----------|-------------|---------|
| BrianzaTaxi / BrianzaTaxiService | Prenotazione taxi con tariffazione | Base → Intermedio |
| BrianzaTrains / BrianzaTrainsObjects | Biglietteria treni | Intermedio → Avanzato |
| DiscotecaTicket | Biglietti con restrizioni età | Base |
| MLTrains → MilanoLeccoTrains v2-v4 | Evoluzione gestione treni | Base → Avanzato |
| MonzaMetro | Biglietteria metropolitana | Intermedio |
| JavaBus / JavItaAirline | Autobus e compagnia aerea | Avanzato |

> **[Dettagli completi](02_Tickets_Transportation/)**

---

## 03 — Business Applications

**43 progetti** organizzati in 4 settori:

| Settore | Progetti | Highlights |
|---------|:--------:|------------|
| [Hospitality & Tourism](03_Business_Applications/01_Hospitality_Tourism/) | 8 | B&B, hotel, agenzie viaggi, ville storiche |
| [Commerce & Services](03_Business_Applications/02_Commerce_Services/) | 14 | Banche, cliniche, ORM custom, concessionarie |
| [Transportation & Logistics](03_Business_Applications/03_Transportation_Logistics/) | 12 | Taxi, treni, metropolitana, compagnie aeree |
| [Education & Culture](03_Business_Applications/04_Education_Culture/) | 9 | Scuole, biblioteche, musei, PokeDex |

### Progetti di punta

| Progetto | Pattern e tecnologie |
|----------|---------------------|
| [ACMC](03_Business_Applications/02_Commerce_Services/ACMC/) | IoC Container custom, MVC, Reflection API, Repository, SOLID |
| [ACMC2ORM](03_Business_Applications/02_Commerce_Services/ACMC2ORM/) | ORM custom, Generic Repository, Full/Partial Cache, Relazioni bidirezionali |
| [BikeWorld](03_Business_Applications/02_Commerce_Services/BikeWorld/) | State Pattern, Chain of Responsibility, ETL/CSV Import |
| [Carroponte](03_Business_Applications/02_Commerce_Services/Carroponte/) | Command Pattern, Caching multi-livello, XOR Cipher |
| [OmegaClinic](03_Business_Applications/02_Commerce_Services/OmegaClinic/) | Repository, Lazy/Eager Loading, JDBC, BigDecimal |
| [OmegaClinicGEHENNA](03_Business_Applications/02_Commerce_Services/OmegaClinicGEHENNA/) | Scheduling algorithm, Smart Calendar, Comparator avanzati |

> **[Dettagli completi](03_Business_Applications/)**

---

## 04 — Exercises & Practice

| Progetto | Descrizione | Concetti |
|----------|-------------|----------|
| ChristmasTime | Progetto natalizio | Classi, Collections |
| ExtraLesson 1-2 | Lezioni di approfondimento | Sintassi avanzata, Refactoring |
| Taxes | Calcolo imposte | BigDecimal, Enum, Validazione |

> **[Dettagli completi](04_Exercises_Practice/)**

---

## 05 — Spring Boot & Frontend

Applicazioni web full-stack:

| Progetto | Descrizione | Stack |
|----------|-------------|-------|
| [Emergency](05_Spring%20%26%20FrontEnd%20-%20FullStack/Emergency/README.md) | Pronto soccorso ospedaliero | Spring Boot, JPA, Thymeleaf, REST API |
| [Dinner](05_Spring%20%26%20FrontEnd%20-%20FullStack/Dinner/README.md) | Gestione ordini ristorante | Spring Boot, JPA, Thymeleaf, REST API |
| [Ticket](05_Spring%20%26%20FrontEnd%20-%20FullStack/Ticket/README.md) | Sistema segnalazioni/ticket | Spring Boot, JPA, Thymeleaf, REST API |
| [Vault](05_Spring%20%26%20FrontEnd%20-%20FullStack/vault/README.md) | Membership Vault-Tec (tema Fallout) | Spring Boot, JPA, REST API |
| [GenSchool](05_Spring%20%26%20FrontEnd%20-%20FullStack/GenSchool/README.md) | Gestione lezioni scolastiche | Spring Boot, JPA, REST API |
| [PC Configurator](05_Spring%20%26%20FrontEnd%20-%20FullStack/pcconfigurator/README.md) | Configuratore PC personalizzati | Spring Boot, JPA, REST API |
| [Sanction](05_Spring%20%26%20FrontEnd%20-%20FullStack/sanction/README.md) | Sistema gestione sanzioni amministrative | Spring Boot, JPA, DTO Pattern, REST API |
| [Hotel Booking](05_Spring%20%26%20FrontEnd%20-%20FullStack/hotelboolking/README.md) | Prenotazioni alberghiere | Spring Boot, JPA, Thymeleaf |
| [FoodDelivery](05_Spring%20%26%20FrontEnd%20-%20FullStack/fooddelivery/README.md) | Consegne a domicilio con assegnazione rider | Spring Boot, JPA, MapStruct, Lombok |
| [JavaEat v01](05_Spring%20%26%20FrontEnd%20-%20FullStack/javaeat_v01/README.md) | Piattaforma food delivery | Spring Boot, JPA, DTO Pattern |
| [JavaEat v02](05_Spring%20%26%20FrontEnd%20-%20FullStack/javaeat_v02/README.md) | Food delivery refactored (M:N) | Spring Boot, JPA |
| [Product](05_Spring%20%26%20FrontEnd%20-%20FullStack/product/README.md) | Catalogo prodotti e recensioni | Spring Boot, JPA, Validation |
| [WebClinic](05_Spring%20%26%20FrontEnd%20-%20FullStack/webclinic/README.md) | Gestionale clinica medica | Spring Boot, JPA, REST API |
| [FrontEnd](05_Spring%20%26%20FrontEnd%20-%20FullStack/FrontEnd%20-%20FullStack/README.md) | Progetti HTML/CSS/JS | HTML5, CSS3, JavaScript ES6+ |

> **[Dettagli completi](05_Spring%20%26%20FrontEnd%20-%20FullStack/)**

---

## 06 — Database MySQL

7 script SQL progressivi: dalla creazione tabelle alle query aggregate con `GROUP BY`, `AVG`, `COUNT`.

| Script | Contenuto |
|--------|-----------|
| `01_create_table.sql` | `CREATE TABLE` — Tabella PEOPLE |
| `02_insert_10_people.sql` | `INSERT` — Inserimento iniziale |
| `03_update_roles.sql` | `UPDATE` — Aggiornamento ruoli |
| `04_delete_minors.sql` | `DELETE` — Eliminazione condizionale |
| `05_insert_10_more.sql` | `INSERT` — Inserimento aggiuntivo |
| `06_select_queries.sql` | `SELECT`, `WHERE`, `ORDER BY`, `LIKE` |
| `07_aggregate_queries.sql` | `GROUP BY`, `AVG`, `MIN`, `MAX`, `COUNT` |

> **[Dettagli completi](06_Database%20-%20MySql/)**

---

## 07 — Angular

8 progetti Angular 21 con Standalone Components, Signals, Two-way Binding e TypeScript.

| Progetto | Descrizione | Concetti chiave |
|----------|-------------|-----------------|
| [AngularHotel](07_Angular/AngularHotel/README.md) | Calcolatore costo prenotazione alberghiera | Signals, Computed Signals, Interface |
| [BMICalculator](07_Angular/BMICalculator/README.md) | Calcolatore indice di massa corporea | WritableSignal, Computed Signals, DecimalPipe |
| [Diet](07_Angular/Diet/README.md) | Tracker macronutrienti con calcolo calorie | Signals, Computed Signals |
| [DR](07_Angular/DR/README.md) | Form registrazione piatto: metodi vs Computed Signals | Two-way Binding, `[(ngModel)]`, FormsModule, Computed Signals |
| [HouseCalculator](07_Angular/HouseCalculator/README.md) | Calcolatore prezzo immobiliare (Signals) | signal(), Computed Signals |
| [HouseCalculatorBinding](07_Angular/HouseCalculatorBinding/README.md) | Calcolatore prezzo immobiliare (Two-way Binding) | `[(ngModel)]`, FormsModule, metodi ordinari |
| [Ippoterapia](07_Angular/Ippoterapia/README.md) | Ricevute ippoterapia con agevolazioni fragili | WritableSignal, Interface |
| [LezioneExtra](07_Angular/LezioneExtra/README.md) | Multi-app: BMI, Fitness, Food, House | Two-way Binding, FormsModule, `@for`/`@empty`, W3.CSS |

> **[Dettagli completi](07_Angular/)**

---

## 08 — FullStack JS + Angular

Applicazioni full-stack con backend Node.js/Express.js, database SQLite e frontend Angular 21.

| Progetto | Descrizione | Stack |
|----------|-------------|-------|
| [Carroponte](08_FullStack%20-%20JS%20-%20Angular/carroponte/README.md) | Gestione spettacoli teatrali/eventi | Express.js, SQLite, Angular, HttpClient |
| [Sadder](08_FullStack%20-%20JS%20-%20Angular/sadder/README.md) | Gestione persone con coordinate canvas | Express.js, SQLite, Angular, HttpClient, Observable |

> **[Dettagli completi](08_FullStack%20-%20JS%20-%20Angular/)**

---

## Stack Tecnologico

| Area | Tecnologie |
|------|------------|
| **Linguaggio** | Java 21, TypeScript 5.9, JavaScript ES6+ |
| **Framework** | Spring Boot 4.0.2, Spring Data JPA, Hibernate, Express.js |
| **Frontend** | Angular 21, Thymeleaf, HTML5, CSS3, W3.CSS |
| **Database** | MySQL 8.x, SQLite 3.x, JDBC, better-sqlite3 |
| **Build** | Maven, Angular CLI, npm |
| **Mapping** | MapStruct 1.6.3 |
| **Testing** | JUnit 5 (Jupiter), Vitest |
| **IDE** | Eclipse, IntelliJ IDEA, VS Code |

## Design Patterns

`Repository` · `MVC` · `Dependency Injection (IoC)` · `Factory` · `Strategy` · `State` · `Command` · `Chain of Responsibility` · `Template Method` · `Composite` · `ORM custom` · `Reflection` · `Lazy/Eager Loading` · `ETL` · `Signals/Computed` · `Standalone Components` · `Service (Angular)` · `Observable/subscribe`

## Statistiche

| Categoria | Quantità |
|-----------|:--------:|
| Framework/Librerie | 1 |
| Moduli fondamentali | 12 |
| Trasporti & Biglietteria | 10 |
| Business Applications | 43 |
| Esercizi & Pratica | 4 |
| Spring Boot Web App | 13 |
| Frontend | 3 |
| Script SQL | 7 |
| Angular | 8 |
| FullStack JS + Angular | 2 |
| **Totale** | **103+** |

---

<div align="center">

### Autore

**Hacman Viorica Gabriela**
Generation Italy — Java Full Stack Developer

[![LinkedIn](https://img.shields.io/badge/LinkedIn-viorica--gabriela--hacman-0A66C2?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/viorica-gabriela-hacman-63a412267/)
[![GitHub](https://img.shields.io/badge/GitHub-vhacman-181717?style=flat&logo=github&logoColor=white)](https://github.com/vhacman)

</div>
