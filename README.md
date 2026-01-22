# 🎓 Corso Java - Generation Italy


Questa repository contiene tutti i miei progetti, esercizi ed esempi di programmazione Java sviluppati durante il bootcamp Full Stack Java Developer di Generation Italy.


## 📁 Struttura della Repository

I progetti sono organizzati in 5 categorie tematiche:

```
GenerationStudyJavaCourse/
├── 📚 00_Libraries/                  # Framework e librerie riutilizzabili
├── 📘 01_Fundamentals_Examples/      # Concetti base e strutture di controllo
├── 🚆 02_Tickets_Transportation/     # Sistemi di biglietteria e trasporti
├── 💼 03_Business_Applications/      # Applicazioni business e gestionali
└── 🎯 04_Exercises_Practice/         # Esercizi extra e pratica
```

***
### 📚 Librerie e Framework (00_Libraries/)

<details>
<summary><b>Clicca per vedere tutte le librerie</b></summary>

| Libreria | Descrizione | Pattern Implementati |
|----------|-------------|----------------------|
| [GenerationLibrary](00_Libraries/GenerationLibrary/README.md) | Framework Repository Pattern con Generics | Template Method, Repository Pattern, Generics, JDBC |

**GenerationLibrary** fornisce:
- Classe base `Entity` con validazione incorporata
- Repository generico `SQLEntityRepository<T extends Entity>` per operazioni CRUD
- Implementazioni concrete: Car, Pet, Plant
- Test JUnit 5 completi
- Database SQLite embedded

</details>

### 📘 Fondamentali (01_Fundamentals_Examples/)

<details>
<summary><b>Clicca per vedere tutti i moduli fondamentali</b></summary>

- [Modulo Esempi Base](docs/02-fundamentals/examples.md)
- [Modulo 4 - Cicli e Iterazioni](docs/02-fundamentals/modulo4.md)
- [Modulo 6 - Basi OOP](docs/02-fundamentals/modulo6.md)
- [Modulo 7 - Incapsulamento](docs/02-fundamentals/modulo7.md)
- [Modulo 8 - Ereditarietà](docs/02-fundamentals/modulo8.md)
- [Modulo 9 - Astrazione](docs/02-fundamentals/modulo9.md)
- [Modulo 10 - Polimorfismo e Interfacce](docs/02-fundamentals/modulo10.md)
- [Modulo 11 - Interfacce Funzionali e Lambda](01_Fundamentals_Examples/Modulo11/README.md)
- [Recap - Lezioni di Ripasso](docs/02-fundamentals/recap.md)
- [Sequenza e Selezione](docs/02-fundamentals/sequenza-selezione.md)
- [Cicli While](docs/02-fundamentals/while.md)

</details>

### 🚆 Trasporti e Biglietteria (02_Tickets_Transportation/)

<details>
<summary><b>Clicca per vedere tutti i progetti di trasporto</b></summary>

| Progetto | Descrizione | Tecnologie |
|----------|-------------|------------|
| [BrianzaTaxi](docs/04-advanced-projects/brianza-taxi.md) | Sistema di prenotazione taxi | OOP, Collections |
| [BrianzaTaxiService](docs/04-advanced-projects/brianza-taxi-service.md) | Servizio taxi con calcolo tariffe | Inheritance, Polymorphism |
| [BrianzaTrains](docs/04-advanced-projects/brianza-trains.md) | Sistema biglietti treno | Classes, Methods |
| [DiscotecaTicket](docs/04-advanced-projects/discoteca-ticket.md) | Biglietti eventi con restrizioni età | Validation, Date API |
| [MLTrains](docs/04-advanced-projects/ml-trains.md) | Gestione treni Milano-Lecco | OOP |
| [MilanoLeccoTrains2](docs/04-advanced-projects/milano-lecco-trains2.md) | Sistema treni v2 | Enhanced OOP |
| [MilanoLeccoTrains3](docs/04-advanced-projects/milano-lecco-trains3.md) | Sistema treni v3 | Advanced Features |
| [MilanoLeccoTrains4](docs/04-advanced-projects/milano-lecco-trains4.md) | Sistema treni completo | Abstract Classes, Enums |
| [MonzaMetro](docs/04-advanced-projects/monza-metro.md) | Biglietteria metropolitana | BigDecimal, Pricing |

</details>

### 💼 Applicazioni Business (03_Business_Applications/)

<details>
<summary><b>Clicca per vedere tutte le applicazioni business</b></summary>

La sezione business è organizzata in 4 sottocategorie tematiche:

#### 🏨 [01_Hospitality_Tourism](03_Business_Applications/01_Hospitality_Tourism/) - Ospitalità e Turismo
[GrottammareB&B](03_Business_Applications/01_Hospitality_Tourism/GrottammareB&B/README.md) • [LeccoB&B](03_Business_Applications/01_Hospitality_Tourism/LeccoB&B/) • [MonsterHotel](03_Business_Applications/01_Hospitality_Tourism/MonsterHotel/) • [ProlocoLakeComo](03_Business_Applications/01_Hospitality_Tourism/ProlocoLakeComo/) • [SBBB](03_Business_Applications/01_Hospitality_Tourism/SBBB/) • [SBHotel](03_Business_Applications/01_Hospitality_Tourism/SBHotel/) • [VacanzeRomane](03_Business_Applications/01_Hospitality_Tourism/VacanzeRomane/) • [VillaMelzi](03_Business_Applications/01_Hospitality_Tourism/VillaMelzi/)

#### 🛒 [02_Commerce_Services](03_Business_Applications/02_Commerce_Services/) - Commercio e Servizi
[JavaBank](03_Business_Applications/02_Commerce_Services/JavaBank/) • [LocalMarket](03_Business_Applications/02_Commerce_Services/LocalMarket/) • [LocalMarketDB](03_Business_Applications/02_Commerce_Services/LocalMarketDB/) • [PCWizard](03_Business_Applications/02_Commerce_Services/PCWizard/) • [PrintLabel](03_Business_Applications/02_Commerce_Services/PrintLabel/) • [PrivateClinic](03_Business_Applications/02_Commerce_Services/PrivateClinic/) • [RepairShop](03_Business_Applications/02_Commerce_Services/RepairShop/)

#### 🚆 [03_Transportation_Logistics](03_Business_Applications/03_Transportation_Logistics/) - Trasporti e Logistica
[BrianzaTaxi](03_Business_Applications/03_Transportation_Logistics/BrianzaTaxi/) • [BrianzaTaxiService](03_Business_Applications/03_Transportation_Logistics/BrianzaTaxiService/) • [BrianzaTrains](03_Business_Applications/03_Transportation_Logistics/BrianzaTrains/) • [BrianzaTrainsObjects](03_Business_Applications/03_Transportation_Logistics/BrianzaTrainsObjects/) • [DiscotecaTicket](03_Business_Applications/03_Transportation_Logistics/DiscotecaTicket/) • [JavaBus](03_Business_Applications/03_Transportation_Logistics/JavaBus/) • [JavItaAirline](03_Business_Applications/03_Transportation_Logistics/JavItaAirline/) • [MilanoLeccoTRains2](03_Business_Applications/03_Transportation_Logistics/MilanoLeccoTRains2/) • [MilanoLeccoTrains3](03_Business_Applications/03_Transportation_Logistics/MilanoLeccoTrains3/) • [MIlanoLeccoTrains4](03_Business_Applications/03_Transportation_Logistics/MIlanoLeccoTrains4/) • [MLTrains](03_Business_Applications/03_Transportation_Logistics/MLTrains/) • [MonzaMetro](03_Business_Applications/03_Transportation_Logistics/MonzaMetro/)

#### 🎓 [04_Education_Culture](03_Business_Applications/04_Education_Culture/) - Educazione e Cultura
[DeveloperCandidatura](03_Business_Applications/04_Education_Culture/DeveloperCandidatura/) • [MuseumTicket](03_Business_Applications/04_Education_Culture/MuseumTicket/) • [NerdLibrary](03_Business_Applications/04_Education_Culture/NerdLibrary/) • [NSMI](03_Business_Applications/04_Education_Culture/NSMI/) • [NSMPI](03_Business_Applications/04_Education_Culture/NSMPI/) • [PokeDex](03_Business_Applications/04_Education_Culture/PokeDex/) • [SchoolManagement_v1](03_Business_Applications/04_Education_Culture/SchoolManagement_v1/) • [SchoolManagement_v2](03_Business_Applications/04_Education_Culture/SchoolManagement_v2/)

#### 🏍️ Altri Progetti  
- **[BikeWorld](03_Business_Applications/BikeWorld/README.md)** - Concessionaria Moto (Repository Pattern, State Pattern, Chain of Responsibility, ETL, JDBC, SQLite)
- **[Carroponte](03_Business_Applications/Carroponte/README.md)** - Gestione Eventi (DI, Repository Pattern, Command Pattern, Caching, IoC Container)

</details>

### 🎯 Esercizi e Pratica (04_Exercises_Practice/)

<details>
<summary><b>Clicca per vedere tutti gli esercizi</b></summary>

- [ChristmasTime](docs/04-advanced-projects/christmas-time.md) - Progetto a tema natalizio
- [ExtraLesson](docs/04-advanced-projects/extra-lesson.md) - Lezione extra di approfondimento
- [ExtraLesson2](docs/04-advanced-projects/extra-lesson2.md) - Seconda lezione extra
- [Taxes](docs/04-advanced-projects/taxes.md) - Calcolo tasse e imposte

</details>

***

## 📊 Statistiche Progetti

| Categoria | Numero Progetti | Livello |
|-----------|----------------|---------|
| 📚 Librerie | 1 framework | Avanzato |
| 📘 Fondamentali | 12 moduli | Principiante |
| 🚆 Trasporti | 9 progetti | Intermedio |
| 💼 Business | **39 progetti** | Intermedio/Avanzato |
| ┣━ 🏨 Hospitality & Tourism | 8 progetti | Intermedio/Avanzato |
| ┣━ 🛒 Commerce & Services | 7 progetti | Intermedio/Avanzato |
| ┣━ 🚆 Transportation & Logistics | 12 progetti | Intermedio |
| ┣━ 🎓 Education & Culture | 8 progetti | Intermedio |
| ┗━ 🏍️ Progetti - altro | 2 progetti | Avanzato |
| 🎯 Esercizi | 4 progetti | Vario |
| **TOTALE** | **65 progetti** | **Completo** |

***

## 🛠️ Tecnologie Utilizzate

<div align="center">











</div>

***

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Studentessa Generation Italy - Java Full Stack Developer Bootcamp
- 💼 [LinkedIn](https://www.linkedin.com/in/viorica-gabriela-hacman-63a412267/)
- 📧 Email: [hacmanvioricagabriela@gmail.com](mailto:hacmanvioricagabriela@gmail.com)
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

***

## 📝 Licenza

📚 **Uso Educativo**

Questo repository è stato creato per scopi educativi come parte del bootcamp Generation Italy.
Sentiti libero di esplorare, imparare e trarre ispirazione, ma rispetta il lavoro degli altri studenti.

***

## 🙏 Ringraziamenti

Un ringraziamento speciale a:
- **Generation Italy** per l'opportunità di apprendimento
- **I docenti e mentor** per la guida e il supporto
- **I compagni di corso** per la collaborazione e lo scambio di conoscenze

***

<div align="center">

[

</div>
