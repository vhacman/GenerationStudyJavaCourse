# NSMPI - Sistema Gestionale Sanitario con Polimorfismo e Interfacce

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Advanced-green?style=flat-square)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-MVC%20%7C%20Factory%20%7C%20Strategy-blue?style=flat-square)

Sistema completo di gestione sanitaria che implementa polimorfismo avanzato, interfacce, pattern architetturali (MVC, Factory, Strategy) e visualizzazioni dinamiche basate su ruoli.

---

## 🏗️ Architettura

### Struttura Completa

```
NSMPI/
├── src/
│   └── com/generation/
│       ├── library/                      # Libreria condivisa
│       │   ├── Entity.java               # Classe astratta base
│       │   ├── Console.java              # I/O console
│       │   ├── FileReader.java           # Lettura file
│       │   ├── FileWriter.java           # Scrittura file
│       │   └── Template.java             # Template loading
│       │
│       └── nsmpi/
│           ├── model/entities/           # MODEL - Entità di dominio
│           │   ├── Person.java           # Classe astratta persona
│           │   ├── Patient.java          # Paziente
│           │   ├── Doctor.java           # Medico
│           │   ├── MedicalService.java   # Servizio medico
│           │   ├── ServiceRoom.java      # Ambulatorio
│           │   ├── Gender.java           # Enum genere
│           │   └── Specialty.java        # Enum specializzazioni
│           │
│           ├── view/                     # VIEW - Presentazione
│           │   ├── PatientView.java              # Interfaccia view
│           │   ├── FullPatientView.java          # View completa
│           │   ├── AnonymousPatientView.java     # View anonimizzata
│           │   ├── ClerkPatientView.java         # View amministrativa
│           │   └── PatientViewFactory.java       # Factory view
│           │
│           ├── etl/                      # ETL - Estrazione dati
│           │   ├── PatientExtractor.java         # Interfaccia estrattore
│           │   ├── DummyPatientExtractor.java    # Implementazione dummy
│           │   └── PatientExtractorFactory.java  # Factory estrattori
│           │
│           ├── demo/                     # CONTROLLER - Demo
│           │   ├── DemoPatientView.java
│           │   └── DemoPatientExtractor.java
│           │
│           └── test/                     # Unit test
│               ├── PatientTest.java
│               ├── DoctorTest.java
│               ├── ServiceRoomTest.java
│               └── MedicalServiceTest.java
│
├── template/                             # Template rendering
│   ├── patientTemplateFULL.txt
│   ├── patientTemplateForClerk.txt
│   └── patientTemplateForExternal.txt
│
└── TestData/                             # Dati di test
    ├── patient.txt
    └── doctor.txt
```

### Diagramma Architetturale

```
┌─────────────────────────────────────────────────────────┐
│                      VIEW LAYER                         │
├─────────────────────────────────────────────────────────┤
│ PatientView (Interface)                                 │
│  ├── FullPatientView (Strategy: dati completi)          │
│  ├── AnonymousPatientView (Strategy: anonimizzata)      │
│  └── ClerkPatientView (Strategy: dati minimi)           │
│                                                         │
│ PatientViewFactory (Factory Pattern)                    │
└─────────────────────────────────────────────────────────┘
                        ▲
                        │ uses
                        │
┌─────────────────────────────────────────────────────────┐
│                     MODEL LAYER                         │
├─────────────────────────────────────────────────────────┤
│ Entity (Abstract, Template Method)                      │
│  ├── Person (Abstract)                                  │
│  │   ├── Patient                                        │
│  │   └── Doctor                                         │
│  ├── MedicalService                                     │
│  └── ServiceRoom                                        │
│                                                         │
│ Enums: Gender, Specialty                                │
└─────────────────────────────────────────────────────────┘
                        ▲
                        │ uses
                        │
┌─────────────────────────────────────────────────────────┐
│                      ETL LAYER                          │
├─────────────────────────────────────────────────────────┤
│ PatientExtractor (Interface)                            │
│  └── DummyPatientExtractor                              │
│                                                         │
│ PatientExtractorFactory (Factory Pattern)               │
└─────────────────────────────────────────────────────────┘
```

---

## 📊 Modello di Dominio

### Entity (Classe Astratta Base)

**Percorso:** `com.generation.library.Entity`

```java
public abstract class Entity {
    protected int id;

    // Template Method Pattern
    public abstract List<String> getErrors();

    public boolean isValid() {
        return getErrors().isEmpty();
    }

    // Utility methods
    protected boolean notMissing(String s) {
        return s != null && !s.isBlank();
    }

    protected boolean isMissing(String s) {
        return s == null || s.isBlank();
    }
}
```

**Responsabilità:**
- Definisce il contratto di validazione per tutte le entità
- Implementa Template Method pattern: `isValid()` delega a `getErrors()`
- Fornisce metodi di utilità per validazione

**Pattern:**
- **Template Method:** `isValid()` chiama `getErrors()` (implementato dalle sottoclassi)

---

### Gender (Enum)

**Percorso:** `com.generation.nsmpi.model.entities.Gender`

```java
public enum Gender {
    M,  // Male
    F,  // Female
    N;  // Non-binary
}
```

**Scopo:** Type-safety per il genere di una persona.

---

### Specialty (Enum)

**Percorso:** `com.generation.nsmpi.model.entities.Specialty`

```java
public enum Specialty {
    INTERNAL_MEDICINE("Internal Medicine"),
    CARDIOLOGY("Cardiology"),
    GENERAL_SURGERY("General Surgery"),
    PEDIATRICS("Pediatrics"),
    GYNECOLOGY_OBSTETRICS("Gynecology and Obstetrics"),
    ORTHOPEDICS_TRAUMA("Orthopedics and Traumatology"),
    EMERGENCY_MEDICINE("Emergency Medicine"),
    ANESTHESIOLOGY("Anesthesiology and Intensive Care"),
    NEUROLOGY("Neurology"),
    PSYCHIATRY("Psychiatry");

    private final String displayName;

    Specialty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
```

**Caratteristiche:**
- Enum con attributi (display name)
- Type-safety per specializzazioni mediche
- Internazionalizzazione facilitata

---

### Person (Classe Astratta)

**Percorso:** `com.generation.nsmpi.model.entities.Person`

```java
public abstract class Person extends Entity {
    protected String firstName;
    protected String lastName;
    protected LocalDate dob;  // Data di nascita
    protected Gender gender;

    public Person() { }

    public Person(String firstName, String lastName,
                  LocalDate dob, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
    }

    @Override
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();

        if (isMissing(firstName))
            errors.add("Missing value for field First Name");
        if (isMissing(lastName))
            errors.add("Missing value for field Last Name");
        if (dob == null)
            errors.add("Missing value for field Date of Birth");
        if (gender == null)
            errors.add("Missing value for field Gender");

        return errors;
    }

    // Getter e setter...
}
```

**Caratteristiche:**
- Estende `Entity`
- Implementa `getErrors()` con validazione anagrafica base
- Usa `LocalDate` per date
- Usa `Gender` enum per type-safety

---

### Patient (Paziente)

**Percorso:** `com.generation.nsmpi.model.entities.Patient`

```java
public class Patient extends Person {
    protected String history;           // Anamnesi clinica
    protected List<String> allergies;   // Lista allergie

    public Patient() {
        this.allergies = new ArrayList<>();
    }

    public Patient(String firstName, String lastName,
                   LocalDate dob, Gender gender, String history) {
        super(firstName, lastName, dob, gender);
        this.history = history;
        this.allergies = new ArrayList<>();
    }

    // Gestione allergie
    public void addAllergy(String allergy) {
        if (allergy != null && !allergy.isBlank()) {
            allergies.add(allergy);
        }
    }

    public void removeAllergy(String allergy) {
        allergies.remove(allergy);
    }

    @Override
    public List<String> getErrors() {
        List<String> errors = super.getErrors();  // Chiama Person.getErrors()

        if (isMissing(history))
            errors.add("Missing value for field history");

        return errors;
    }

    // Getter e setter...
}
```

**Caratteristiche:**
- Estende `Person` aggiungendo storia clinica e allergie
- Estende validazione con `super.getErrors()`
- Gestione lista allergie con add/remove
- Composizione: contiene `List<String>` per allergie

**Costruttore da Stringhe:**
```java
public Patient(int id, String firstName, String lastName,
               String dob, String gender, String history,
               String allergies) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = LocalDate.parse(dob);
    this.gender = Gender.valueOf(gender);
    this.history = history;

    // Parsing allergie (formato: "allergy1, allergy2, ...")
    if (allergies != null && !allergies.isBlank()) {
        String[] allergyArray = allergies.split(",");
        for (String allergy : allergyArray) {
            this.allergies.add(allergy.trim());
        }
    }
}
```

---

### Doctor (Medico)

**Percorso:** `com.generation.nsmpi.model.entities.Doctor`

```java
public class Doctor extends Person {
    protected List<Specialty> specialties;  // Enum!
    protected int salary;

    public Doctor() {
        this.specialties = new ArrayList<>();
        this.salary = 0;
    }

    // Defensive Copy Pattern
    public List<Specialty> getSpecialties() {
        return new ArrayList<>(specialties);  // Copia defensiva
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = (specialties != null)
            ? new ArrayList<>(specialties)
            : new ArrayList<>();
    }

    // Gestione specialità
    public void addSpecialty(Specialty specialty) {
        if (specialty != null && !specialties.contains(specialty)) {
            specialties.add(specialty);
        }
    }

    public boolean hasSpecialty(Specialty specialty) {
        return specialties.contains(specialty);
    }

    @Override
    public List<String> getErrors() {
        List<String> errors = super.getErrors();

        if (specialties.isEmpty())
            errors.add("Doctor must have at least one specialty");
        if (salary < 0)
            errors.add("Salary must be non-negative: " + salary);

        return errors;
    }
}
```

**Caratteristiche:**
- Usa `Specialty` enum per type-safety
- **Defensive Copy Pattern:** Protegge la lista interna
- Validazione: almeno una specialità, salary >= 0
- Metodi helper per gestire specialità

---

### MedicalService (Servizio Medico)

**Percorso:** `com.generation.nsmpi.model.entities.MedicalService`

```java
public class MedicalService extends Entity {
    private String description;  // Tipo servizio (visita, vaccinazione, etc.)
    private int price;          // Prezzo

    @Override
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();

        if (price < 0)
            errors.add("Invalid value for price");
        if (isMissing(description))
            errors.add("Missing value for field description");

        return errors;
    }

    // Getter e setter...
}
```

---

### ServiceRoom (Ambulatorio)

**Percorso:** `com.generation.nsmpi.model.entities.ServiceRoom`

```java
public class ServiceRoom extends Entity {
    private String description;  // Tipo ambulatorio
    private int floor;          // Piano [-10, +10]

    @Override
    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();

        if (isMissing(description))
            errors.add("Missing value for field Description");
        if (floor < -10 || floor > 10)
            errors.add("Invalid value for field floor " + floor);

        return errors;
    }

    // Getter e setter...
}
```

**Business Rule:** Piano limitato a [-10, +10].

---

## 🎨 Strato View

### PatientView (Interfaccia)

**Percorso:** `com.generation.nsmpi.view.PatientView`

```java
public interface PatientView {
    /**
     * Renderizza un paziente in formato stringa
     * @param p Paziente da renderizzare
     * @return Stringa formattata secondo il template
     */
    String render(Patient p);
}
```

**Responsabilità:**
- Definisce il contratto per renderizzare pazienti
- Permette diverse implementazioni per diversi ruoli

---

### FullPatientView (View Completa)

**Percorso:** `com.generation.nsmpi.view.FullPatientView`

```java
class FullPatientView implements PatientView {
    protected String filename;  // Path del template

    public FullPatientView(String filename) {
        this.filename = filename;
    }

    @Override
    public String render(Patient p) {
        String res = Template.load(filename);

        // Sostituisce placeholders
        res = res.replace("[id]", String.valueOf(p.getId()))
                 .replace("[firstName]", p.getFirstName())
                 .replace("[lastName]", p.getLastName())
                 .replace("[dob]", p.getDob().toString())
                 .replace("[gender]", p.getGender().toString())
                 .replace("[history]", p.getHistory());

        // Allergie
        String allergiesString = String.join(", ", p.getAllergies());
        res = res.replace("[allergies]", allergiesString);

        return res;
    }
}
```

**Template:** `template/patientTemplateFULL.txt`

```
╭──────────────────────────────────────────────────────────╮
│                🩺  CARTELLA CLINICA PAZIENTE             │
╰──────────────────────────────────────────────────────────╯

  ANAGRAFICA
  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  👤 Nome:            [firstName]
  👤 Cognome:         [lastName]
  📅 Data di Nascita: [dob]
  ⚧  Sesso:           [gender]

  📜 STORIA CLINICA
  ──────────────────────────────────────────────────────────
  [history]

  ⚠️ ALLERGIE E REAZIONI
  ──────────────────────────────────────────────────────────
  [allergies]
```

**Uso:** Per medici e personale clinico (dati completi).

---

### AnonymousPatientView (View Anonimizzata)

**Percorso:** `com.generation.nsmpi.view.AnonymousPatientView`

```java
class AnonymousPatientView extends FullPatientView {
    public AnonymousPatientView(String filename) {
        super(filename);
    }

    @Override
    public String render(Patient p) {
        String res = Template.load(filename);

        // SOLO anno di nascita (privacy!)
        res = res.replace("[year]", String.valueOf(p.getDob().getYear()))
                 .replace("[gender]", p.getGender().toString())
                 .replace("[history]", p.getHistory());

        String allergiesString = String.join(", ", p.getAllergies());
        res = res.replace("[allergies]", allergiesString);

        return res;
    }
}
```

**Template:** `template/patientTemplateForExternal.txt`

```
╔══════════════════════════════════════════════════════════╗
║                📋 SCHEDA PAZIENTE ANONIMA                ║
╚══════════════════════════════════════════════════════════╝

  DATI GENERALI
  ──────────────────────────────────────────────────────────
  📅 Anno di nascita:  [year]
  👤 Sesso:            [gender]

  🩺 STORIA CLINICA
  ──────────────────────────────────────────────────────────
  [history]

  ⚠️ ALLERGIE E INTOLLERANZE
  ──────────────────────────────────────────────────────────
  [allergies]
```

**Uso:** Per studenti, ricercatori (privacy-preserving).

---

### ClerkPatientView (View Amministrativa)

**Percorso:** `com.generation.nsmpi.view.ClerkPatientView`

```java
class ClerkPatientView extends FullPatientView {
    public ClerkPatientView(String filename) {
        super(filename);
    }

    @Override
    public String render(Patient p) {
        String res = Template.load(filename);

        // Solo anagrafica
        res = res.replace("[id]", String.valueOf(p.getId()))
                 .replace("[firstName]", p.getFirstName())
                 .replace("[lastName]", p.getLastName())
                 .replace("[dob]", p.getDob().toString());

        return res;
    }
}
```

**Template:** `template/patientTemplateForClerk.txt`

```
============================================================
              🏥 SCHEDA ANAGRAFICA PAZIENTE 🏥
============================================================

  IDENTIFICATIVO:  [id]
  ----------------------------------------------------------

  NOMINATIVO
  ──────────────────────────────────────────────────────────
  Nome:            [firstName]
  Cognome:         [lastName]

  DATI PERSONALI
  ──────────────────────────────────────────────────────────
  Data di Nascita: [dob]

============================================================
```

**Uso:** Per staff amministrativo (dati minimi necessari).

---

### PatientViewFactory (Factory Pattern)

**Percorso:** `com.generation.nsmpi.view.PatientViewFactory`

```java
public class PatientViewFactory {
    // Singleton instances
    static PatientView fullViewTXT =
        new FullPatientView("template/patientTemplateFULL.txt");

    static PatientView anonymousViewTXT =
        new AnonymousPatientView("template/patientTemplateForExternal.txt");

    static PatientView clerkViewTXT =
        new ClerkPatientView("template/patientTemplateForClerk.txt");

    public static PatientView make(String role) {
        switch (role) {
            case "doctor":
                return fullViewTXT;
            case "student":
            case "teacher":
                return anonymousViewTXT;
            case "clerk":
                return clerkViewTXT;
            default:
                return null;
        }
    }
}
```

**Pattern:**
- **Factory Pattern:** Centralizza creazione view
- **Singleton:** Istanze statiche (no duplicazione)
- **Strategy Selection:** Sceglie strategia basata su ruolo

**Utilizzo:**
```java
PatientView view = PatientViewFactory.make("doctor");
String rendered = view.render(patient);
```

---

## 📥 Strato ETL

### PatientExtractor (Interfaccia)

**Percorso:** `com.generation.nsmpi.etl.PatientExtractor`

```java
public interface PatientExtractor {
    /**
     * Estrae lista di pazienti da file
     * @param filename Path del file sorgente
     * @return Lista di pazienti
     */
    List<Patient> getPatientsFromFile(String filename);
}
```

**Responsabilità:**
- Definisce contratto generico per estrazione dati
- Permette diverse implementazioni (CSV, XML, JSON, DB)

---

### DummyPatientExtractor

**Percorso:** `com.generation.nsmpi.etl.DummyPatientExtractor`

```java
public class DummyPatientExtractor implements PatientExtractor {
    @Override
    public List<Patient> getPatientsFromFile(String filename) {
        List<Patient> res = new ArrayList<>();

        // ID, NOME, COGNOME, NASCITA, SEX, STORIA, ALLERGIE
        res.add(new Patient(1, "Vlad", "Tepes", "1920-01-01", "M",
                "Brutto carattere", "Allergia al sangue"));
        res.add(new Patient(2, "Leonardo", "Da Vinci", "1452-04-15", "M",
                "Sindrome del genio, dorme poco", "Polvere, Noia"));
        res.add(new Patient(3, "Marie", "Curie", "1867-11-07", "F",
                "Esposizione prolungata a materiali radianti", "Nessuna"));
        // ... altri pazienti ...

        return res;
    }
}
```

**Caratteristiche:**
- Implementazione dummy (ignora filename)
- Dati hardcoded per scopi didattici
- 13 pazienti storici con malattie creative

---

### PatientExtractorFactory

**Percorso:** `com.generation.nsmpi.etl.PatientExtractorFactory`

```java
public class PatientExtractorFactory {
    static PatientExtractor dummy = new DummyPatientExtractor();

    public static PatientExtractor make(String filename) {
        return dummy;  // Al momento ritorna sempre dummy
    }
}
```

**Estensibile a:**
- `CSVPatientExtractor`
- `XMLPatientExtractor`
- `JSONPatientExtractor`
- `DBPatientExtractor`

---

## 🎭 Pattern Implementati

### 1. MVC (Model-View-Controller)

```
MODEL (model/entities/)
  ├── Entity, Person, Patient, Doctor
  ├── MedicalService, ServiceRoom
  └── Gender, Specialty (Enums)

VIEW (view/)
  ├── PatientView (interfaccia)
  ├── FullPatientView, AnonymousPatientView, ClerkPatientView
  └── PatientViewFactory

CONTROLLER (demo/)
  ├── DemoPatientView
  └── DemoPatientExtractor
```

---

### 2. Factory Pattern

```java
// View Factory
PatientView view = PatientViewFactory.make("doctor");

// Extractor Factory
PatientExtractor extractor = PatientExtractorFactory.make("csv");
```

**Benefici:**
- Decoupling creazione da utilizzo
- Facile aggiungere nuove implementazioni
- Centralizzazione logica di selezione

---

### 3. Strategy Pattern

```java
public interface PatientView {
    String render(Patient p);
}

// Diverse strategie di rendering
class FullPatientView implements PatientView { ... }
class AnonymousPatientView extends FullPatientView { ... }
class ClerkPatientView extends FullPatientView { ... }
```

**Beneficio:** Stesso metodo (`render`), algoritmi diversi.

---

### 4. Template Method Pattern

```java
// In Entity
public boolean isValid() {
    return getErrors().isEmpty();  // Template method
}

public abstract List<String> getErrors();  // Hook method

// In Person
public List<String> getErrors() {
    List<String> errors = new ArrayList<>();
    // Validazione base...
    return errors;
}

// In Patient
public List<String> getErrors() {
    List<String> errors = super.getErrors();  // Riusa validazione base
    // Validazione specifica...
    return errors;
}
```

---

### 5. Defensive Copy Pattern

```java
// In Doctor
public List<Specialty> getSpecialties() {
    return new ArrayList<>(specialties);  // Copia defensiva
}

public void setSpecialties(List<Specialty> specialties) {
    this.specialties = new ArrayList<>(specialties);  // Copia defensiva
}
```

**Beneficio:** Protegge lo stato interno da modifiche esterne.

---

### 6. Repository Pattern (Parziale)

```java
public interface PatientExtractor {
    List<Patient> getPatientsFromFile(String filename);
}

// Data access layer - estensibile a:
class CSVPatientExtractor implements PatientExtractor { ... }
class XMLPatientExtractor implements PatientExtractor { ... }
class DBPatientExtractor implements PatientExtractor { ... }
```

---

## 🔄 Polimorfismo

### A. Polimorfismo per Ereditarietà

```
Entity (astratta)
  ├── Person (astratta) → Patient, Doctor
  ├── MedicalService
  └── ServiceRoom
```

**Esempio:**
```java
List<Entity> entities = new ArrayList<>();
entities.add(new Patient(...));
entities.add(new Doctor(...));
entities.add(new MedicalService(...));

for (Entity entity : entities) {
    System.out.println(entity.isValid());     // Polimorfico!
    System.out.println(entity.getErrors());   // Implementazione specifica
}
```

---

### B. Polimorfismo per Interfaccia

```java
PatientView view = PatientViewFactory.make(role);
String rendered = view.render(patient);

// La stessa variabile può puntare a:
// - FullPatientView
// - AnonymousPatientView
// - ClerkPatientView
```

---

### C. Esempio Completo: Validazione Polimorfica

```java
public void validateEntities(List<Entity> entities) {
    for (Entity entity : entities) {
        if (!entity.isValid()) {
            System.out.println("Errori in: " + entity);
            for (String error : entity.getErrors()) {
                System.out.println("  - " + error);
            }
        }
    }
}

// Utilizzo
validateEntities(Arrays.asList(
    new Patient(...),
    new Doctor(...),
    new MedicalService(...),
    new ServiceRoom(...)
));
```

**Output:**
```
Errori in: Patient [id=0, valido=false]
  - Missing value for field First Name
  - Missing value for field history

Errori in: Doctor [id=0, valido=false]
  - Missing value for field First Name
  - Doctor must have at least one specialty
  - Salary must be non-negative: -5000
```

---

### D. Esempio: Renderizzazione Polimorfica

```java
Patient vlad = new Patient(...);

// Stesso paziente, view diverse
PatientView doctorView = PatientViewFactory.make("doctor");
System.out.println(doctorView.render(vlad));  // Dati completi

PatientView studentView = PatientViewFactory.make("student");
System.out.println(studentView.render(vlad));  // Anonimizzata

PatientView clerkView = PatientViewFactory.make("clerk");
System.out.println(clerkView.render(vlad));  // Solo anagrafica
```

---

## ▶️ Esecuzione

### DemoPatientView

**File:** `com.generation.nsmpi.demo.DemoPatientView`

```java
public class DemoPatientView {
    public static void main(String[] args) {
        Patient p = new Patient();
        p.setId(1);
        p.setFirstName("Vlad");
        p.setLastName("Țepeș");
        p.setDob(LocalDate.parse("1920-01-01"));
        p.setGender(Gender.M);
        p.setHistory("Grave emofilia");
        p.addAllergy("Verdure");
        p.addAllergy("Mezzaluna");

        Console.print("Quale è il tuo ruolo? (doctor, teacher, student, clerk)");
        String role = Console.readString();

        PatientView view = PatientViewFactory.make(role);
        Console.print(view.render(p));
    }
}
```

**Esecuzione:**
```bash
javac com/generation/nsmpi/demo/DemoPatientView.java
java com.generation.nsmpi.demo.DemoPatientView
```

**Output (role="doctor"):**
```
╭──────────────────────────────────────────────────────────╮
│                🩺  CARTELLA CLINICA PAZIENTE             │
╰──────────────────────────────────────────────────────────╯

  ANAGRAFICA
  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  👤 Nome:            Vlad
  👤 Cognome:         Țepeș
  📅 Data di Nascita: 1920-01-01
  ⚧  Sesso:           M

  📜 STORIA CLINICA
  ──────────────────────────────────────────────────────────
  Grave emofilia

  ⚠️ ALLERGIE E REAZIONI
  ──────────────────────────────────────────────────────────
  Verdure, Mezzaluna
```

---

### DemoPatientExtractor

**File:** `com.generation.nsmpi.demo.DemoPatientExtractor`

```java
public class DemoPatientExtractor {
    public static void main(String[] args) {
        PatientExtractor extractor = PatientExtractorFactory.make("csv");
        PatientView doctorView = PatientViewFactory.make("doctor");

        List<Patient> patients = extractor.getPatientsFromFile("testdata/patients.csv");

        for (Patient patient : patients) {
            String rendered = doctorView.render(patient);
            Console.print(rendered);
        }
    }
}
```

---

## 🧪 Testing

### PatientTest

**File:** `com.generation.nsmpi.test.PatientTest`

```java
@Test
void testGetErrors() {
    Patient p = new Patient();

    // Paziente vuoto: 5 errori
    assert(p.getErrors().size() == 5);
    assert(!p.isValid());

    p.setFirstName("Mario");
    assert(p.getErrors().size() == 4);

    p.setLastName("Rossi");
    p.setDob(LocalDate.of(1980, 5, 15));
    p.setGender(Gender.M);
    assert(p.getErrors().size() == 1);  // Manca history

    p.setHistory("Nessuna allergia conosciuta");
    assert(p.getErrors().size() == 0);
    assert(p.isValid());
}
```

---

### DoctorTest

```java
@Test
void testGetErrors() {
    Doctor d = new Doctor();

    d.setSalary(-5000);
    assert(d.getErrors().size() == 6);  // 4 (Person) + 2 (Doctor)

    d.setFirstName("Giovanni");
    d.setLastName("Bianchi");
    d.setDob(LocalDate.of(1970, 8, 25));
    d.setGender(Gender.M);
    assert(d.getErrors().size() == 2);  // Specialties + salary

    d.addSpecialty(Specialty.CARDIOLOGY);
    assert(d.getErrors().size() == 1);  // Solo salary

    d.setSalary(50000);
    assert(d.getErrors().size() == 0);
    assert(d.isValid());
}
```

---

## 🎯 Principi SOLID

### S - Single Responsibility

- `Entity`: Gestisce id e validazione base
- `Person`: Gestisce anagrafica
- `Patient`: Gestisce storia clinica e allergie
- `PatientView`: Gestisce rendering
- `PatientExtractor`: Gestisce estrazione dati

### O - Open/Closed

- `Entity` aperta all'estensione, chiusa alla modifica
- `PatientView` aperta a nuove implementazioni
- `PatientExtractor` aperto a nuovi estrattori

### L - Liskov Substitution

```java
Entity e = new Patient(...);
e.isValid();  // OK

PatientView v = new AnonymousPatientView(...);
v.render(patient);  // OK
```

### I - Interface Segregation

- `PatientView`: Solo `render()`
- `PatientExtractor`: Solo `getPatientsFromFile()`

### D - Dependency Inversion

```java
// Dipendenza da astrazione (interfaccia)
PatientView view = PatientViewFactory.make(role);

// Non da concretizzazione
// FullPatientView view = new FullPatientView(...);  ❌
```

---


