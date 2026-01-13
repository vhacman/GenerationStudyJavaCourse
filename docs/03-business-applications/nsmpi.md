# NSMPI - Sistema Gestionale Sanitario con Polimorfismo e Interfacce

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Advanced-green?style=flat-square)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-MVC%20%7C%20Factory%20%7C%20Strategy-blue?style=flat-square)

Sistema completo di gestione sanitaria che implementa polimorfismo avanzato, interfacce e pattern architetturali (MVC, Factory, Strategy, ETL) con visualizzazioni dinamiche basate su ruoli.

---

## 📋 Indice

- [Panoramica](#-panoramica)
- [Architettura](#️-architettura)
- [Entità del Dominio](#-entità-del-dominio)
- [Design Patterns](#-design-patterns)
- [Funzionalità Principali](#-funzionalità-principali)
- [Esecuzione](#️-esecuzione)
- [Concetti OOP](#-concetti-oop-avanzati)

---

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
│  │   ├── Patient (history, allergies)                                  │
│  │   └── Doctor (specialties, salary)                                  │
│  ├── MedicalService (description, price)                                │
│  └── ServiceRoom (description, floor)                                   │
│                                                                         │
│ Enums: Gender (M, F, N), Specialty (CARDIOLOGY, PEDIATRICS, ...)       │
└─────────────────────────────────────────────────────────────────────────┘
                                ▲
                                │
┌─────────────────────────────────────────────────────────────────────────┐
│                            ETL LAYER                                    │
├─────────────────────────────────────────────────────────────────────────┤
│ PatientExtractor (Interface)         │  DoctorExtractor (Interface)    │
│  └── DummyPatientExtractor           │   └── DummyDoctorExtractor      │
│                                      │                                  │
│ PatientExtractorFactory              │  DoctorExtractorFactory         │
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

## 📊 Entità del Dominio

### Gerarchia delle Classi

```
Entity (abstract)
 ├── Person (abstract)
 │    ├── Patient
 │    └── Doctor
 ├── MedicalService
 └── ServiceRoom
```

### Entity (Classe Base)

**Responsabilità**: Fornisce validazione comune e gestione ID

**Metodi chiave**:
- `abstract List<String> getErrors()` - Template method per validazione
- `boolean isValid()` - Verifica validità (delega a getErrors)

### Person (Classe Astratta)

**Attributi comuni**: firstName, lastName, dob, gender
**Validazione**: Controllo campi obbligatori

### Patient

**Attributi specifici**:
- `String history` - Anamnesi clinica
- `List<String> allergies` - Lista allergie

**Metodi**: `addAllergy()`, `removeAllergy()`

### Doctor

**Attributi specifici**:
- `List<Specialty> specialties` - Specializzazioni (enum)
- `int salary` - Stipendio

**Metodi**: `addSpecialty()`, `removeSpecialty()`, `hasSpecialty()`

**Defensive Copying**: Getter e setter restituiscono copie delle liste

### Specialty (Enum)

Enum type-safe per specializzazioni mediche:
```
CARDIOLOGY, PEDIATRICS, NEUROLOGY, GENERAL_SURGERY,
EMERGENCY_MEDICINE, INTERNAL_MEDICINE, GYNECOLOGY_OBSTETRICS,
ORTHOPEDICS_TRAUMA, ANESTHESIOLOGY, PSYCHIATRY
```

**Vantaggi**: Compile-time safety, prevenzione typo, autocomplete IDE

---

## 🎨 Design Patterns

### 1. Factory Pattern

**Problema**: Creazione di oggetti diversi basata su parametri runtime
**Soluzione**: Centralizzare la logica di creazione

**Implementazione**:
```java
// PatientViewFactory
PatientView view = PatientViewFactory.make("doctor");  // FullPatientView
view = PatientViewFactory.make("clerk");               // ClerkPatientView

// DoctorViewFactory
DoctorView doctorView = DoctorViewFactory.make("full");       // FullDoctorView
doctorView = DoctorViewFactory.make("financial");             // FinancialDoctorView
```

**Benefici**:
- ✅ Decoupling tra client e implementazioni
- ✅ Facile aggiungere nuove view
- ✅ Singleton pattern (istanze statiche)

### 2. Strategy Pattern

**Problema**: Diversi algoritmi di rendering per la stessa entità
**Soluzione**: Interfaccia comune con implementazioni intercambiabili

**Interfacce**:
```java
public interface PatientView {
    String render(Patient p);
}

public interface DoctorView {
    String render(Doctor d);
}
```

**Strategie Patient**:
- `FullPatientView` - Tutti i dati (medici)
- `AnonymousPatientView` - Dati anonimizzati (studenti, ricercatori)
- `ClerkPatientView` - Solo anagrafica (amministrazione)

**Strategie Doctor**:
- `FullDoctorView` - Dati completi (HR, amministrazione)
- `FinancialDoctorView` - Solo dati finanziari (reparto paghe)

**Benefici**:
- ✅ Stesso metodo, comportamenti diversi
- ✅ Cambio strategia a runtime
- ✅ Open/Closed principle

### 3. Template Method Pattern

**Problema**: Validazione gerarchica con logica comune
**Soluzione**: Metodo template che chiama hook methods

**Implementazione**:
```java
// Entity.java (template method)
public boolean isValid() {
    return getErrors().isEmpty();  // Template
}
public abstract List<String> getErrors();  // Hook method

// Person.java (override)
public List<String> getErrors() {
    List<String> errors = new ArrayList<>();
    // Validazione base Person
    return errors;
}

// Patient.java (estende validazione)
public List<String> getErrors() {
    List<String> errors = super.getErrors();  // Riusa logica parent
    // Aggiunge validazione specifica Patient
    return errors;
}
```

### 4. ETL Pattern

**Problema**: Estrazione dati da diverse sorgenti
**Soluzione**: Interfaccia astratta per estrattori

**Interfacce**:
```java
public interface PatientExtractor {
    List<Patient> getPatientsFromFile(String filename);
}

public interface DoctorExtractor {
    List<Doctor> getDoctorsFromFile(String filename);
}
```

**Implementazioni attuali**: `DummyPatientExtractor`, `DummyDoctorExtractor`
**Estensioni future**: CSV, XML, JSON, Database extractor

---

## ⚙️ Funzionalità Principali

### Gestione Pazienti

**View disponibili**:

| View | Ruolo | Dati Visibili |
|------|-------|---------------|
| `FullPatientView` | Medico | Tutti (anagrafica, storia clinica, allergie) |
| `AnonymousPatientView` | Studente/Ricercatore | Anno nascita, sesso, storia, allergie (no nome) |
| `ClerkPatientView` | Amministrazione | Solo anagrafica (ID, nome, cognome, data nascita) |

**Template**: I template usano placeholder come `[firstName]`, `[history]`, `[allergies]`

### Gestione Medici

**View disponibili**:

| View | Ruolo | Dati Visibili |
|------|-------|---------------|
| `FullDoctorView` | HR/Amministrazione | Anagrafica + specialità + salario |
| `FinancialDoctorView` | Reparto Paghe | Solo ID, nome, cognome, salario |

**Specialty Enum**: Type-safe, previene errori di digitazione

### Template Engine

**Funzionamento**:
1. Carica template da file `.txt`
2. Sostituisce placeholder con valori reali
3. Gestisce liste (allergie, specialità) con join

**Esempio template**:
```
╭──────────────────────────────────────────╮
│        🩺  SCHEDA MEDICO COMPLETA        │
╰──────────────────────────────────────────╯

  ANAGRAFICA
  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  🆔 ID:              [id]
  👤 Nome:            [firstName] [lastName]
  💰 Salario:         [salary] €
  🏥 Specialità:      [specialties]
```

---

## ▶️ Esecuzione

### Demo Patient View

```bash
cd 03_Business_Applications/NSMPI
javac -d bin src/com/generation/**/*.java
java -cp bin com.generation.nsmpi.demo.DemoPatientView
```

**Output**: Richiede il ruolo (doctor, student, clerk) e renderizza il paziente

### Demo Patient Extractor

```bash
java -cp bin com.generation.nsmpi.demo.DemoPatientExtractor
```

**Funzionalità**: Estrae lista pazienti e li renderizza con vista completa

### Demo Doctor Extractor

```bash
java -cp bin com.generation.nsmpi.demo.DemoDoctorExtractor
```

**Funzionalità**: Estrae lista medici e li renderizza con vista completa

### Demo Doctor View

```bash
java -cp bin com.generation.nsmpi.view.DemoDoctor
```

**Funzionalità**: Mostra rendering con vista completa e finanziaria

---

## 🧪 Testing

### Validazione Entità

**PatientTest**: Verifica validazione campi obbligatori
```java
Patient p = new Patient();
assert(p.getErrors().size() == 5);  // Tutti i campi mancanti

p.setFirstName("Mario");
p.setLastName("Rossi");
// ... set altri campi
assert(p.isValid());  // true quando tutti i campi sono validi
```

**DoctorTest**: Verifica validazione medico e specialità
```java
Doctor d = new Doctor();
assert(!d.isValid());  // Specialità mancante

d.addSpecialty(Specialty.CARDIOLOGY);
d.setSalary(50000);
// ... set anagrafica
assert(d.isValid());
```

---

## 💡 Concetti OOP Avanzati

### Polimorfismo per Interfaccia

```java
PatientView view = PatientViewFactory.make(role);
String output = view.render(patient);  // Implementazione dipende da role
```

Stesso riferimento (`PatientView`), comportamenti diversi basati su runtime type.

### Polimorfismo per Ereditarietà

```java
List<Entity> entities = Arrays.asList(
    new Patient(...),
    new Doctor(...),
    new MedicalService(...)
);

for (Entity entity : entities) {
    System.out.println(entity.isValid());    // Polimorfico
    System.out.println(entity.getErrors());  // Implementazione specifica
}
```

### Defensive Copying

**Problema**: Proteggere stato interno da modifiche esterne

```java
// Doctor.java
public List<Specialty> getSpecialties() {
    return new ArrayList<>(specialties);  // Copia defensiva
}

public void setSpecialties(List<Specialty> specialties) {
    this.specialties = new ArrayList<>(specialties);  // Copia defensiva
}
```

**Beneficio**: Immutabilità apparente, controllo completo sulla lista interna

### Type-Safety con Enum

**Prima** (String - propenso a errori):
```java
doctor.setSpecialty("CARDILOGY");  // Typo! Runtime error
```

**Dopo** (Enum - compile-time safety):
```java
doctor.addSpecialty(Specialty.CARDIOLOGY);  // IDE autocomplete, compile-time check
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

## 🎯 Obiettivi Didattici

Questo progetto dimostra:

✅ **Polimorfismo** - Interfacce con implementazioni multiple
✅ **Incapsulamento** - Defensive copying, validazione interna
✅ **Ereditarietà** - Gerarchia Entity → Person → Patient/Doctor
✅ **Astrazione** - Interfacce, classi astratte
✅ **Design Patterns** - Factory, Strategy, Template Method
✅ **SOLID Principles** - Codice manutenibile ed estensibile
✅ **Separazione delle Responsabilità** - MVC, layer ben definiti

---

**Progetto didattico** sviluppato durante il bootcamp Generation Italy per apprendere OOP avanzato e design patterns in Java.

