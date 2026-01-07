# Modulo 8 - Inheritance (Ereditarietà)

**Location:** `01_Fundamentals_Examples/Modulo8Ereditarietà/`

## Overview

Explores **Inheritance**, the second pillar of Object-Oriented Programming (OOP).

## What is Inheritance?

Inheritance allows a class to inherit attributes and methods from another class:
- **Parent Class** (superclass) - The class being inherited from
- **Child Class** (subclass) - The class that inherits

## Key Concepts

### Extends Keyword
```java
public class Student extends Person {
    // Student inherits all Person attributes and methods
}
```

### Benefits of Inheritance

- **Code Reusability** - Don't repeat code
- **Hierarchical Classification** - Model real-world relationships
- **Polymorphism** - Objects can take multiple forms
- **Method Overriding** - Child can modify parent behavior

### super Keyword

- Calls parent class constructor
- Accesses parent class methods

```java
public Student(String name, String surname) {
    super(name, surname);  // Calls Person constructor
}
```

## Skills Learned

- Creating class hierarchies
- Extending classes with `extends`
- Using `super` keyword
- Method overriding
- Understanding IS-A relationships

## Next Steps

- **Modulo 9** - Abstraction
- Advanced OOP projects
