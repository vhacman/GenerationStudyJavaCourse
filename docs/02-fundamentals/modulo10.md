# Modulo 10 - Polimorfismo e Interfacce

![Java OOP](https://img.shields.io/badge/Java-OOP_Advanced-ED8B00?style=flat-square&logo=java)
![Difficulty](https://img.shields.io/badge/Livello-Intermedio-yellow?style=flat-square)

Sistema di gestione scolastica che dimostra polimorfismo, interfacce e ereditarietà multipla a livelli.

## 📋 Indice

- [Panoramica](#-panoramica)
- [Struttura del Progetto](#-struttura-del-progetto)
- [Concetti OOP Trattati](#-concetti-oop-trattati)
- [Gerarchia delle Classi](#-gerarchia-delle-classi)
- [Implementazione](#-implementazione)
- [Esecuzione](#-esecuzione)
- [Pattern e Principi](#-pattern-e-principi)

---

## 🎯 Panoramica

Il Modulo 10 è un esempio completo di programmazione orientata agli oggetti che implementa:

- **Ereditarietà a 4 livelli** (interfaccia → abstract → abstract → concrete)
- **Polimorfismo** attraverso liste generiche e casting dinamico
- **Interfacce** come contratti per la validazione
- **Classi astratte** che definiscono comportamenti comuni
- **Template Method Pattern** per la validazione

### Obiettivi di Apprendimento

- ✅ Comprendere il polimorfismo in Java
- ✅ Utilizzare interfacce per definire contratti
- ✅ Implementare classi astratte con metodi astratti
- ✅ Applicare l'ereditarietà a più livelli
- ✅ Gestire il casting e instanceof
- ✅ Implementare null safety

---

## 📁 Struttura del Progetto

```
Modulo10/
├── src/
│   └── com/generation/
│       ├── library/
│       │   └── Console.java              # Utilità I/O
│       └── school/
│           ├── Main.java                 # Entry point
│           ├── Validable.java            # Interfaccia validazione
│           ├── Person.java               # Classe astratta base
│           ├── Employee.java             # Classe astratta dipendente
│           └── Teacher.java              # Classe concreta insegnante
└── bin/
```

### Package Principali

- **`com.generation.library`** - Libreria utilità per input/output
- **`com.generation.school`** - Gerarchia di classi per la gestione scolastica

---

## 💡 Concetti OOP Trattati

### A. Polimorfismo

#### 1. **Polimorfismo di Sottotipo** (Subtype Polymorphism)
```java
ArrayList<Person> people = new ArrayList<>();
people.add(new Teacher(...));  // Teacher IS-A Person

// Late binding: chiama l'implementazione di Teacher
for (Person person : people) {
    System.out.println(person.getCost());  // Chiama Teacher.getCost()
}
```

#### 2. **Polimorfismo Parametrico** (Generics)
```java
ArrayList<Person> people = new ArrayList<>();
ArrayList<Teacher> teachers = new ArrayList<>();
```

#### 3. **Late Binding** (Dynamic Dispatch)
```java
Person p = new Teacher(...);
p.getCost();  // Risolve il metodo di Teacher a runtime
```

### B. Interfacce

L'interfaccia `Validable` definisce il contratto per la validazione:

```java
public interface Validable {
    boolean isValid();
}
```

**Caratteristiche:**
- Definisce un contratto che tutte le classi devono rispettare
- Implementata da `Person` e automaticamente ereditata dalle sottoclassi
- Permette polimorfismo basato su contratto

### C. Ereditarietà a Catena

```
Validable (interface)
    ↑ implements
Person (abstract class)
    ↑ extends
Employee (abstract class)
    ↑ extends
Teacher (concrete class)
```

### D. Classi Astratte

- **`Person`**: Classe astratta con metodo astratto `getCost()`
- **`Employee`**: Classe astratta con metodo astratto `getYearlyRetribution()`
- **`Teacher`**: Classe concreta che implementa tutti i metodi astratti

### E. instanceof e Casting

```java
if (person instanceof Teacher) {
    Teacher teacher = (Teacher) person;  // Cast sicuro
    System.out.println(teacher.getSubject());
}
```

---

## 🏗️ Gerarchia delle Classi

```
┌─────────────────────────────────────────┐
│        <<interface>>                    │
│         Validable                       │
│  ─────────────────────────────────────  │
│  + isValid(): boolean                   │
└─────────────────────────────────────────┘
         ↑ implements
         │
┌─────────────────────────────────────────┐
│      <<abstract>>                       │
│         Person                          │
│  ─────────────────────────────────────  │
│  # name: String                         │
│  # surname: String                      │
│  # dateOfBirth: String                  │
│  # gender: String                       │
│  ─────────────────────────────────────  │
│  + getName(): String                    │
│  + getSurname(): String                 │
│  + toString(): String                   │
│  + getCost(): int <<abstract>>          │
└─────────────────────────────────────────┘
         ↑ extends
         │
┌─────────────────────────────────────────┐
│      <<abstract>>                       │
│        Employee                         │
│  ─────────────────────────────────────  │
│  # salary: double                       │
│  ─────────────────────────────────────  │
│  + getSalary(): double                  │
│  + equals(Object): boolean              │
│  + getCost(): int                       │
│  + getYearlyRetribution(): double       │
│    <<abstract>>                         │
└─────────────────────────────────────────┘
         ↑ extends
         │
┌─────────────────────────────────────────┐
│      <<concrete>>                       │
│        Teacher                          │
│  ─────────────────────────────────────  │
│  # subject: String                      │
│  # yearsOfExperience: int               │
│  ─────────────────────────────────────  │
│  + getSubject(): String                 │
│  + getYearlyRetribution(): double       │
│  + isValid(): boolean                   │
│  + toString(): String                   │
└─────────────────────────────────────────┘
```

---

## 🔧 Implementazione

### 1. Interfaccia Validable

```java
package com.generation.school;

public interface Validable {
    boolean isValid();
}
```

**Scopo:** Definire un contratto per la validazione di oggetti.

---

### 2. Classe Astratta Person

```java
public abstract class Person implements Validable {
    private String name;
    private String surname;
    private String dateOfBirth;
    private String gender;

    // Getter con null safety
    public String getName() {
        return name == null ? "UNKNOWN" : name;
    }

    // Setter con null protection
    public void setName(String name) {
        this.name = name == null ? "UNKNOWN" : name;
    }

    // Metodo astratto - forza le sottoclassi a implementarlo
    public abstract int getCost();

    @Override
    public String toString() {
        return getName() + " " + getSurname() + " "
             + getDateOfBirth() + " " + getGender();
    }
}
```

**Caratteristiche:**
- Implementa l'interfaccia `Validable`
- Getter/setter con null safety (restituiscono "UNKNOWN" se null)
- Metodo astratto `getCost()` che le sottoclassi devono implementare
- Override di `toString()` per rappresentazione testuale

---

### 3. Classe Astratta Employee

```java
public abstract class Employee extends Person {
    protected double salary;

    public Employee() {
        this.salary = 0.0;
    }

    public Employee(String name, String surname, String dateOfBirth,
                    String gender, double salary) {
        setName(name);
        setSurname(surname);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        this.salary = salary;
    }

    // Metodo astratto che sottoclassi devono implementare
    public abstract double getYearlyRetribution();

    @Override
    public int getCost() {
        // Costo = 2 anni di stipendio
        return (int)(getYearlyRetribution() * 2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee other = (Employee) obj;
        return this.getName().equals(other.getName()) &&
               this.getSurname().equals(other.getSurname()) &&
               this.getDateOfBirth().equals(other.getDateOfBirth()) &&
               this.getGender().equals(other.getGender()) &&
               this.salary == other.salary;
    }
}
```

**Caratteristiche:**
- Estende `Person` e aggiunge `salary`
- Implementa il metodo astratto `getCost()` da `Person`
- Definisce il nuovo metodo astratto `getYearlyRetribution()`
- Override del metodo `equals()` per confronto tra oggetti
- Campo `salary` è `protected` per essere accessibile dalle sottoclassi

---

### 4. Classe Concreta Teacher

```java
public class Teacher extends Employee {
    protected String subject;
    protected int yearsOfExperience;

    public Teacher() {
        this.subject = "";
        this.yearsOfExperience = 0;
    }

    public Teacher(String name, String surname, String dateOfBirth,
                   String gender, double salary, String subject,
                   int yearsOfExperience) {
        super(name, surname, dateOfBirth, gender, salary);
        this.subject = subject;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public double getYearlyRetribution() {
        return salary * 12;  // Stipendio annuale
    }

    @Override
    public boolean isValid() {
        return subject != null &&
               !subject.isBlank() &&
               !subject.isEmpty() &&
               salary >= 1100;  // Validazione: stipendio minimo
    }

    @Override
    public String toString() {
        return super.toString() + " Subject: " + getSubject()
             + " Experience: " + yearsOfExperience + " years";
    }
}
```

**Caratteristiche:**
- Classe concreta (può essere istanziata)
- Implementa `getYearlyRetribution()` da `Employee`
- Implementa `isValid()` da `Validable`
- Override di `toString()` concatenando con `super.toString()`
- Validazione: materia non vuota e stipendio >= 1100

---

### 5. Main - Polimorfismo in Azione

```java
public class Main {
    public static void main(String[] args) {
        // 1. POLIMORFISMO: Lista di Person contiene Teacher
        ArrayList<Person> people = new ArrayList<>();

        people.add(new Teacher("Mario", "Rossi", "01/01/1980", "M",
                              2000.0, "Matematica", 10));
        people.add(new Teacher("Laura", "Bianchi", "15/03/1985", "F",
                              2200.0, "Italiano", 8));

        // 2. FILTERING: Estrae solo insegnanti
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Person person : people) {
            if (person instanceof Teacher) {
                teachers.add((Teacher) person);
            }
        }

        // 3. Estrazione dati specifici
        ArrayList<String> teacherNames = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherNames.add(teacher.getName() + " " + teacher.getSurname());
        }

        // 4. Calcolo aggregato
        double totalYearlyRetribution = 0.0;
        for (Teacher teacher : teachers) {
            totalYearlyRetribution += teacher.getYearlyRetribution();
        }

        // 5. Output
        Console.print("Numero totale di persone: " + people.size());
        Console.print("Numero di insegnanti: " + teachers.size());
        Console.print("Totale retribuzioni annue: " + totalYearlyRetribution);
    }
}
```

**Concetti Applicati:**
- **Polimorfismo di tipo:** `ArrayList<Person>` contiene oggetti `Teacher`
- **instanceof:** Verifica il tipo reale di un oggetto
- **Casting:** Conversione sicura da `Person` a `Teacher`
- **Iterazione polimorfica:** Cicli che operano su tipi base
- **Late binding:** Le chiamate a metodi usano l'implementazione della classe reale

---

## ▶️ Esecuzione

### Prerequisiti

- Java JDK 8 o superiore
- IDE (Eclipse, IntelliJ IDEA) o compilatore Java da riga di comando

### Compilazione ed Esecuzione

```bash
# Da riga di comando
cd Modulo10/src
javac com/generation/school/*.java com/generation/library/*.java
java com.generation.school.Main

# Con Eclipse/IntelliJ
# Aprire il progetto e eseguire Main.java
```

### Output Atteso

```
=== ESERCIZIO #35 ===

Numero totale di persone: 3
Numero di insegnanti trovati: 3

Elenco insegnanti:
- Mario Rossi
- Laura Bianchi
- Giovanni Verdi

Dettagli insegnanti:
Mario Rossi 01/01/1980 M Salary: 2000.0 Subject: Matematica Experience: 10 years
  Retribuzione annua: 24000.0 EUR
Laura Bianchi 15/03/1985 F Salary: 2200.0 Italiano Experience: 8 years
  Retribuzione annua: 26400.0 EUR
Giovanni Verdi 20/06/1975 M Salary: 2500.0 Subject: Storia Experience: 15 years
  Retribuzione annua: 30000.0 EUR

Totale retribuzioni annue: 80400.0 EUR
```

---

## 🎨 Pattern e Principi

### Design Patterns

#### 1. **Template Method Pattern**
```java
// In Person (metodo generico)
public abstract int getCost();

// In Employee (implementazione parziale)
public int getCost() {
    return (int)(getYearlyRetribution() * 2);
}

// In Teacher (implementazione completa)
public double getYearlyRetribution() {
    return salary * 12;
}
```

#### 2. **Null Object Pattern**
```java
public String getName() {
    return name == null ? "UNKNOWN" : name;
}
```

### Principi SOLID

#### **S - Single Responsibility Principle**
- `Person`: Gestisce anagrafica base
- `Employee`: Gestisce stipendio e retribuzione
- `Teacher`: Gestisce materia ed esperienza

#### **O - Open/Closed Principle**
- `Person` è aperta all'estensione (`Employee`, `Teacher`)
- `Person` è chiusa alla modifica (interfaccia stabile)

#### **L - Liskov Substitution Principle**
```java
Person p = new Teacher(...);  // Teacher sostituisce Person
p.getCost();  // Funziona correttamente
```

#### **I - Interface Segregation Principle**
- `Validable` contiene solo `isValid()` - interfaccia minima

#### **D - Dependency Inversion Principle**
```java
// Dipendenza da astrazione (Person), non da concretizzazione (Teacher)
List<Person> people = new ArrayList<>();
people.add(new Teacher(...));
```

---

## 📚 Concetti Chiave

### Ereditarietà vs Composizione

**Ereditarietà usata in questo modulo:**
```
Teacher IS-A Employee
Employee IS-A Person
Person IS-A Validable (implementa interfaccia)
```

**Quando usare l'ereditarietà:**
- Relazione "IS-A" (è un tipo di)
- Comportamento condiviso tra classi correlate
- Gerarchia naturale del dominio

### Classi Astratte vs Interfacce

| Caratteristica | Classe Astratta | Interfaccia |
|----------------|-----------------|-------------|
| **Metodi** | Concreti + Astratti | Solo astratti (Java <8) |
| **Attributi** | Sì | Solo costanti (public static final) |
| **Ereditarietà** | Singola (extends) | Multipla (implements) |
| **Costruttori** | Sì | No |
| **Quando usare** | Codice condiviso | Contratto/Comportamento |

**Nel progetto:**
- `Person`, `Employee`: Classi astratte (condividono codice)
- `Validable`: Interfaccia (definisce contratto)

### Polimorfismo: Benefici

1. **Flessibilità:** Stessa variabile può contenere diversi tipi
2. **Estensibilità:** Facile aggiungere nuove classi senza modificare codice esistente
3. **Manutenibilità:** Codice generico che lavora con astrazioni
4. **Riusabilità:** Stesso codice lavora con diverse implementazioni

---

## 🎯 Esercizi Proposti

### Esercizio 1: Aggiungere Student
Creare una classe `Student` che estende `Person` con attributi:
- `String studentId`
- `double gpa` (Grade Point Average)
- Implementare `getCost()` - costo basato su borsa di studio
- Implementare `isValid()` - GPA tra 0 e 4.0

### Esercizio 2: Polimorfismo con Multiple Classi
Aggiungere `Student` alla lista `people` nel `Main` e:
- Filtrare per tipo (insegnanti vs studenti)
- Calcolare costo totale di tutta la scuola
- Stampare statistiche per tipo

### Esercizio 3: Nuova Interfaccia
Creare interfaccia `Printable` con metodo `String print()`:
- Farla implementare da tutte le classi
- Creare metodo generico che stampa qualsiasi `Printable`

---

## 🔗 Risorse Aggiuntive

### Documentazione Java
- [Inheritance (Oracle Docs)](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
- [Polymorphism (Oracle Docs)](https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html)
- [Abstract Classes (Oracle Docs)](https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)
- [Interfaces (Oracle Docs)](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)

### Concetti Correlati
- [SOLID Principles](https://www.baeldung.com/solid-principles)
- [Design Patterns in Java](https://refactoring.guru/design-patterns/java)

---

## 📝 Note Importanti

### Null Safety
Il progetto implementa null safety nei getter/setter:
```java
public String getName() {
    return name == null ? "UNKNOWN" : name;
}
```

**Vantaggio:** Previene `NullPointerException` e fornisce valori di default.

### Late Binding
Java usa il late binding (dynamic dispatch) per risolvere le chiamate ai metodi a runtime:
```java
Person p = new Teacher(...);
p.getCost();  // Chiama Teacher.getCost() (via Employee)
```

Il tipo dichiarato è `Person`, ma il tipo reale è `Teacher`, quindi viene chiamato il metodo di `Teacher`.

### Casting Sicuro
Sempre verificare con `instanceof` prima di fare casting:
```java
if (person instanceof Teacher) {
    Teacher teacher = (Teacher) person;  // Sicuro
}
```

---

## 🏆 Conclusioni

Il Modulo 10 dimostra:

1. **Ereditarietà a 4 livelli** - Interfaccia → Abstract → Abstract → Concrete
2. **Polimorfismo pratico** - Liste generiche, casting, late binding
3. **Separazione di responsabilità** - Ogni classe ha un ruolo chiaro
4. **Design pattern** - Template Method, Null Object
5. **Principi SOLID** - Applicati in modo naturale
6. **Null safety** - Defensive programming

È un esempio completo di come i concetti OOP lavorano insieme nella pratica Java reale.

---

**[⬅️ Torna all'indice](../../README.md)** | **[📚 Altri moduli](../../README.md#-fondamentali-01_fundamentals_examples)**
