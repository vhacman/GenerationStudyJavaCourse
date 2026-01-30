 

# 🎓 Corso Java - Generation Italy

Questa repository contiene tutti i miei progetti, esercizi ed esempi di programmazione Java sviluppati durante il bootcamp Full Stack Java Developer di Generation Italy.

## 📁 Struttura della Repository

I progetti sono organizzati in 5 categorie tematiche:

```text
GenerationStudyJavaCourse/
├── 📚 00_Libraries/                  # Framework e librerie riutilizzabili
├── 📘 01_Fundamentals_Examples/      # Concetti base, strutture dati e OOP
├── 🚆 02_Tickets_Transportation/     # Sistemi di biglietteria e trasporti
├── 💼 03_Business_Applications/      # Applicazioni business e gestionali
└── 🎯 04_Exercises_Practice/         # Esercizi extra e pratica

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

[DeveloperCandidatura](03_Business_Applications/04_Education_Culture/DeveloperCandidatura/) • [MuseumTicket](03_Business_Applications/04_Education_Culture/MuseumTicket/) • [NerdLibrary](03_Business_Applications/04_Education_Culture/NerdLibrary/) • [NSMI](03_Business_Applications/04_Education_Culture/NSMI/) • [NSMPI](03_Business_Applications/04_Education_Culture/NSMPI/) • [PokeDex](03_Business_Applications/04_Education_Culture/PokeDex/) • [SchoolManagement_v1](03_Business_Applications/04_Education_Culture/SchoolManagement_v1/) • [SchoolManagement_v2](03_Business_Applications/04_Education_Culture/SchoolManagement_v2/)


</details>

### 🎯 Esercizi e Pratica (04_Exercises_Practice/)

<details>
<summary><b>Clicca per vedere tutti gli esercizi</b></summary>

* [ChristmasTime](https://www.google.com/search?q=docs/04-advanced-projects/christmas-time.md) - Progetto a tema natalizio
* [ExtraLesson](https://www.google.com/search?q=docs/04-advanced-projects/extra-lesson.md) - Lezione extra di approfondimento
* [ExtraLesson2](https://www.google.com/search?q=docs/04-advanced-projects/extra-lesson2.md) - Seconda lezione extra
* [Taxes](https://www.google.com/search?q=docs/04-advanced-projects/taxes.md) - Calcolo tasse e imposte

</details>

---

## 📊 Statistiche Progetti

| Categoria | Numero Progetti | Livello |
| --- | --- | --- |
| 📚 Librerie | 1 framework | Avanzato |
| 📘 Fondamentali | 13 moduli | Base |
| 🚆 Trasporti | 9 progetti | Intermedio |
| 💼 Business | **43 progetti** (8+14+12+8) | Avanzato |
| 🎯 Esercizi | 4 progetti | Vario |
| **TOTALE** | **70 progetti** | **Completo** |

**Business Applications - Dettaglio:**
- 🏨 Hospitality & Tourism: 8 progetti
- 🛒 Commerce & Services: 14 progetti (7 base + 7 enterprise)
- 🚆 Transportation & Logistics: 12 progetti
- 🎓 Education & Culture: 8 progetti

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
