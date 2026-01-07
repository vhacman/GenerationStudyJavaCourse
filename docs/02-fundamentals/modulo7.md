# Modulo 7 - Encapsulation

**Location:** `01_Fundamentals_Examples/Modulo7Incapsulamento/`

## Overview

Explores **Encapsulation**, one of the four pillars of Object-Oriented Programming (OOP).

## Main Program

**Main.java** with enhanced **Person.java** entity class

## Encapsulation Principles

### What is Encapsulation?

Encapsulation is the practice of:
1. Hiding internal object state (private fields)
2. Providing controlled access through public methods (getters/setters)
3. Protecting data integrity through validation

### Benefits

- **Data Hiding** - Prevents direct external access to fields
- **Controlled Access** - Public methods control data flow
- **Validation** - Setters can validate data before storing
- **Flexibility** - Internal representation can change without affecting external code
- **Null Safety** - Defensive programming prevents null errors

## Person Class Implementation

### Private Attributes

```java
private String name;
private String surname;
private String dateOfBirth;
private String gender;
```

### Constructors

**No-argument constructor:**
```java
public Person() {
    // Initializes with empty strings
}
```

**Parameterized constructor:**
```java
public Person(String name, String surname, String dateOfBirth, String gender) {
    this.name = name;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
}
```

### Getter Methods (with Null-Safety)

```java
public String getName() {
    return name == null ? "UNKNOWN" : name;
}

public String getSurname() {
    return surname == null ? "UNKNOWN" : surname;
}
```

**Why null-safety?**
- Prevents NullPointerException
- Returns "UNKNOWN" instead of null
- Safer for string operations

### Setter Methods (with Validation)

```java
public void setName(String name) {
    this.name = name == null ? "UNKNOWN" : name;
}

public void setSurname(String surname) {
    this.surname = surname == null ? "UNKNOWN" : surname;
}
```

**Why validation?**
- Handles null values defensively
- Ensures data integrity
- Prevents invalid state

### toString() Method

```java
@Override
public String toString() {
    return "Person: " + getName() + " " + getSurname() +
           ", DOB: " + getDateOfBirth() + ", Gender: " + getGender();
}
```

## Demonstration Code

**Main.java:**
```java
Person p1 = new Person("Gabriela", "Hacman", "21-10-1998", "F");
Person p2 = new Person("Valerio", "Corallini", "02-23-1999", "M");
Person p3 = new Person("Pippo", "Disney", null, "M"); // null dateOfBirth
Person p4 = new Person("Pluto", "Disney", null, "M");

// Accessing through getters
String name = p1.getName();  // Returns "Gabriela"
String dob = p3.getDateOfBirth(); // Returns "UNKNOWN"
```

## Key Concepts

### Access Modifiers

- **private** - Accessible only within the class
- **public** - Accessible from anywhere

### Defensive Programming

Using ternary operators for null checks:
```java
return name == null ? "UNKNOWN" : name;
```

### Constructor Overloading

Multiple constructors with different parameters:
- Default (no arguments)
- Parameterized (with all fields)

### this Keyword

Refers to current object instance:
```java
this.name = name;  // Assigns parameter to field
```

## Comparison: Before vs After Encapsulation

### Modulo 6 (Direct Access)
```java
Person p = new Person();
p.name = "Gabriela";  // Direct field access
String n = p.name;    // Direct field access
```

### Modulo 7 (Encapsulated)
```java
Person p = new Person();
p.setName("Gabriela");  // Controlled through setter
String n = p.getName(); // Controlled through getter
```

## Skills Learned

- Private field declaration
- Public getter/setter methods
- Null-safety techniques
- Ternary operators for validation
- Constructor overloading
- Defensive programming
- Data hiding principles
- Controlled access patterns

## Next Steps

After mastering encapsulation:
- **Modulo 8** - Inheritance (extending classes)
- **Modulo 9** - Abstraction (abstract classes and interfaces)
