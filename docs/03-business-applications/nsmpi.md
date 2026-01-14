# NSMPI - Sistema Gestionale Sanitario con Polimorfismo e Interfacce

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Advanced-green?style=flat-square)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-MVC%20%7C%20Factory%20%7C%20Strategy-blue?style=flat-square)

Sistema completo di gestione sanitaria che implementa polimorfismo avanzato, interfacce e pattern architetturali (MVC, Factory, Strategy, ETL) con visualizzazioni dinamiche basate su ruoli.


## 🎯 Panoramica

NSMPI è un sistema gestionale per strutture sanitarie che dimostra l'applicazione di principi OOP avanzati e design patterns per gestire medici, pazienti e servizi sanitari con diverse modalità di visualizzazione basate sui ruoli utente.

### Caratteristiche Chiave

✅ **Polimorfismo** - Interfacce comuni con implementazioni multiple
✅ **Factory Pattern** - Creazione centralizzata degli oggetti
✅ **Strategy Pattern** - Algoritmi di rendering intercambiabili
✅ **Template Method** - Validazione gerarchica delle entità
✅ **ETL Pattern** - Estrazione dati modulare e estensibile
✅ **Type-Safety** - Uso di enum per specialità e generi

---

## 🏗️ Architettura

### Diagramma Completo

```
┌─────────────────────────────────────────────────────────────────────────┐
│                            VIEW LAYER                                   │
├─────────────────────────────────────────────────────────────────────────┤
│ PatientView (Interface)              │  DoctorView (Interface)          │
│  ├── FullPatientView                 │   ├── FullDoctorView             │
│  ├── AnonymousPatientView            │   └── FinancialDoctorView        │
│  └── ClerkPatientView                │                                  │
│                                      │                                  │
│ PatientViewFactory                   │  DoctorViewFactory               │
└─────────────────────────────────────────────────────────────────────────┘
                                ▲
                                │
┌─────────────────────────────────────────────────────────────────────────┐
│                           MODEL LAYER                                   │
├─────────────────────────────────────────────────────────────────────────┤
│ Entity (Abstract, Template Method)                                      │
│  ├── Person (Abstract)                                                  │
│  │   ├── Patient (history, allergies)                                   │
│  │   └── Doctor (specialties, salary)                                   │
│  ├── MedicalService (description, price)                                │
│  └── ServiceRoom (description, floor)                                   │
│                                                                         │
│ Enums: Gender (M, F, N), Specialty (CARDIOLOGY, PEDIATRICS, ...)        │
└─────────────────────────────────────────────────────────────────────────┘
                                ▲
                                │
┌─────────────────────────────────────────────────────────────────────────┐
│                            ETL LAYER                                    │
├─────────────────────────────────────────────────────────────────────────┤
│ PatientExtractor (Interface)         │  DoctorExtractor (Interface)     │
│  └── DummyPatientExtractor           │   └── DummyDoctorExtractor       │
│                                      │                                  │
│ PatientExtractorFactory              │  DoctorExtractorFactory          │
└─────────────────────────────────────────────────────────────────────────┘
```

### Struttura Directory

```
NSMPI/
├── src/com/generation/
│   ├── library/                 # Componenti riutilizzabili
│   │   ├── Entity.java          # Classe base con validazione
│   │   ├── Template.java        # Template engine
│   │   └── Console.java         # I/O utilities
│   │
│   └── nsmpi/
│       ├── model/entities/      # Entità del dominio
│       │   ├── Person.java      # Classe astratta base
│       │   ├── Patient.java     # Paziente
│       │   ├── Doctor.java      # Medico
│       │   ├── Gender.java      # Enum genere
│       │   └── Specialty.java   # Enum specialità mediche
│       │
│       ├── view/                # Layer presentazione
│       │   ├── PatientView.java          # Interfaccia
│       │   ├── FullPatientView.java      # Strategia completa
│       │   ├── AnonymousPatientView.java # Strategia anonima
│       │   ├── ClerkPatientView.java     # Strategia clerk
│       │   ├── DoctorView.java           # Interfaccia
│       │   ├── FullDoctorView.java       # Strategia completa
│       │   └── FinancialDoctorView.java  # Strategia finanziaria
│       │
│       ├── etl/                 # Estrazione dati
│       │   ├── PatientExtractor.java
│       │   ├── DoctorExtractor.java
│       │   └── *Factory.java
│       │
│       ├── listexamples/        # Esempi operazioni su liste
│       │   ├── PatientListExamples.java  # Esercizi guidati
│       │   └── DoctorListExamples.java   # Implementazioni complete
│       │
│       ├── demo/                # Applicazioni demo
│       └── test/                # Unit tests
│
├── template/                    # Template di rendering
│   ├── patientTemplate*.txt
│   └── doctorTemplate*.txt
│
└── TestData/                    # Dati di test
```

---

## 🎓 Principi SOLID

| Principio | Implementazione |
|-----------|-----------------|
| **S** - Single Responsibility | `Entity`: validazione, `Person`: anagrafica, `Patient`: storia clinica |
| **O** - Open/Closed | Interfacce `PatientView`, `DoctorView` aperte a nuove implementazioni |
| **L** - Liskov Substitution | `FullPatientView` sostituibile con `AnonymousPatientView` senza breaking |
| **I** - Interface Segregation | Interfacce piccole: `PatientView` ha solo `render()` |
| **D** - Dependency Inversion | Dipendenza da astrazioni (`PatientView`) non concretizzazioni |


---

## 📚 Tecnologie

- **Java 8+** - LocalDate, Stream API
- **OOP Avanzato** - Interfacce, classi astratte, polimorfismo
- **Design Patterns** - Factory, Strategy, Template Method, ETL
- **Enum** - Type-safe constants
- **Collections** - ArrayList, List, defensive copying

---

**Progetto didattico** sviluppato durante il bootcamp Generation Italy per apprendere OOP avanzato e design patterns in Java.




