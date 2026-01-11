# NSMI - National Social Medicine Institute

Sistema di gestione anagrafe nazionale per persone, pazienti e medici.

## Descrizione

NSMI implementa un sistema di gestione anagrafe con gerarchia di ereditarietà. Person è la classe base, mentre Patient e Doctor sono sottoclassi specializzate. Include validazione avanzata degli stipendi medici con range per specializzazione.

## Struttura del Progetto

```
NSMI/
├── src/
│   └── com/generation/nsmi/
│       ├── controller/
│       │   └── Main.java
│       ├── model/
│       │   ├── entities/
│       │   │   ├── Person.java
│       │   │   ├── Patient.java
│       │   │   ├── Doctor.java
│       │   │   ├── SpecialtyType.java
│       │   │   └── InsuranceType.java
│       │   └── repository/
│       │       └── PersonRepository.java
│       └── view/
│           └── PersonView.java
└── archive/
└── print/
```

## Classi Principali

### Model (Gerarchia di Ereditarietà)

**Person.java** - Classe base
- Attributi: id, firstName, lastName, SSN, dateOfBirth
- Metodi comuni: `isValid()`, `toString()`
- Method overloading: `setDob()` con 3 signature diverse

**Patient.java** - Sottoclasse di Person
- Attributi aggiuntivi: history, insuranceType
- Override: `toString()` personalizzato

**Doctor.java** - Sottoclasse di Person
- Attributi aggiuntivi: salary, specialization
- Validazione stipendio contro range della specializzazione
- Metodi ausiliari: `getSalaryInfo()`, `isValidSalaryForSpecialty()`, `toDetailedString()`

### Enumerazioni

**SpecialtyType.java** - Enum per specializzazioni mediche
- Valori: CARDIOLOGIST, NEUROLOGIST, PEDIATRICIAN, ecc.
- Ogni specializzazione ha range salariali (min/max)

**InsuranceType.java** - Enum per tipi di assicurazione
- Valori: FULL, PARTIAL, NONE

### Repository
- `PersonRepository.java` - Persistenza dati su file

### View
- `PersonView.java` - Renderizzazione dati con template

### Controller
- `Main.java` - Controller principale per caricamento/salvataggio persone

## Funzionalità Implementate

1. **Gestione Persone**
   - Caricamento persona per codice fiscale
   - Salvataggio nuova persona generica

2. **Gestione Pazienti**
   - Salvataggio nuovo paziente con storia clinica
   - Gestione tipo assicurazione (FULL, PARTIAL, NONE)

3. **Gestione Medici**
   - Salvataggio nuovo medico con specializzazione
   - Validazione stipendio contro range della specializzazione
   - Calcolo percentuale stipendio nel range (junior/intermedio/senior)

4. **Method Overloading**
   - `setDob(LocalDate dob)` - accetta LocalDate
   - `setDob(int year, int month, int day)` - accetta 3 interi
   - `setDob(String dobString)` - accetta String formato ISO

5. **Method Override**
   - `toString()` personalizzato per ogni sottoclasse
   - Utilizzo di `super.toString()` per riutilizzo codice

## Pattern Architetturali Utilizzati

- **Inheritance (Ereditarietà)**: Person -> Patient, Person -> Doctor con relazione IS-A
- **Polymorphism (Polimorfismo)**:
  - **Overloading**: `setDob()` con 3 signature diverse
  - **Override**: `toString()` ridefinito in sottoclassi
- **super() keyword**: Chiamata costruttore superclasse
- **super.toString()**: Riutilizzo del `toString()` della superclasse
- **Enum Pattern**: SpecialtyType e InsuranceType come enum con attributi
- **Validazione nel Setter**: `Doctor.setSalary()` valida range
- **Repository Pattern**: PersonRepository per persistenza

## Esecuzione

```bash
cd NSMI
javac -d bin src/com/generation/nsmi/**/*.java
java -cp bin com.generation.nsmi.controller.Main
```

## Concetti Java Dimostrati

### Gerarchia di Ereditarietà
```
Person (classe base)
├── Patient (sottoclasse)
│   └── Attributi: history, insuranceType
└── Doctor (sottoclasse)
    └── Attributi: salary, specialization
```

### Method Overloading
```java
// 3 modi diversi per impostare la data di nascita
public void setDob(LocalDate dob) { ... }
public void setDob(int year, int month, int day) { ... }
public void setDob(String dobString) { ... }
```

### Method Override
```java
@Override
public String toString() {
    return super.toString() + ", Specialty: " + specialization;
}
```

### Enum con Attributi e Metodi
```java
public enum SpecialtyType {
    CARDIOLOGIST(80000, 150000),
    NEUROLOGIST(90000, 180000);

    private final double minSalary;
    private final double maxSalary;

    public boolean isValidSalary(double salary) {
        return salary >= minSalary && salary <= maxSalary;
    }
}
```

### Validazione con Range
```java
public void setSalary(double salary) {
    if (!specialization.isValidSalary(salary)) {
        throw new IllegalArgumentException("Stipendio fuori range");
    }
    this.salary = salary;
}
```

## File Chiave

- `Person.java:1` - Classe base con method overloading
- `Doctor.java:1` - Sottoclasse con validazione avanzata
- `SpecialtyType.java:1` - Enum con range salariali
- `Main.java:1` - Controller principale

## Relazioni IS-A

- Patient IS-A Person: un paziente è una persona con storia clinica
- Doctor IS-A Person: un medico è una persona con specializzazione e stipendio
