# Modulo 6 - Object-Oriented Programming Basics

**Location:** `01_Fundamentals_Examples/Modulo 6/`

## Overview

Introduction to Object-Oriented Programming with entity class design and implementation.

## Main Programs

- **Main.java** - Person class demonstration
- **MainHouse.java** - House class demonstration

## Entity Classes

### Person Class

**Attributes:**
- `name` - Person's first name
- `surname` - Person's last name
- `dateOfBirth` - Birth date (YYYY-MM-DD format)
- `gender` - Gender identification

**Methods:**
- Constructor: Initializes all fields to empty strings
- `toString()` - Returns formatted person information

**Example:**
```java
Person p1 = new Person();
p1.name = "Gabriela";
p1.surname = "Hacman";
p1.dateOfBirth = "1998-10-21";
p1.gender = "F";
```

### House Class

**Attributes:**
- `address` - Property address
- `area` - Property area in square meters
- `spm` - Price per square meter

**Methods:**
- Constructor: Initializes fields (strings to empty, numbers to 0)
- `getPrice()` - Calculates total property price (area × spm)
- `toString()` - Returns formatted house information with price

**Example:**
```java
House h1 = new House();
h1.address = "Via Roma 123";
h1.area = 100;
h1.spm = 2000;
double price = h1.getPrice(); // Returns 200000
```

## Key OOP Concepts

### Classes and Objects
- **Class** - Blueprint/template for objects
- **Object** - Instance created from a class
- **Instantiation** - Creating objects with `new` keyword

### Attributes and Methods
- **Attributes** - Object state (data/fields)
- **Methods** - Object behavior (functions)
- **Business Logic Methods** - Methods that compute values (e.g., getPrice)

### Constructors
- Special method for initializing object state
- Called automatically when object is created
- Same name as class

### toString() Method
- Custom string representation of object
- Called automatically when printing object
- Override default Object.toString()

## Demonstration Code

**Main.java:**
```java
Person p1 = new Person();
p1.name = "Gabriela";
Person p2 = new Person();
p2.name = "Valerio";
```

**MainHouse.java:**
```java
House h1 = new House();
h1.area = 100;
h1.spm = 2000;
System.out.println(h1.toString());
```

## Skills Learned

- Class definition
- Object instantiation
- Attribute access (direct field access)
- Method implementation
- Constructor usage
- Calculated vs stored properties
- Multiple objects from same class

## Next Steps

After mastering these basics, proceed to:
- **Modulo 7** - Encapsulation with private fields
- **Modulo 8** - Inheritance
- **Modulo 9** - Abstraction
