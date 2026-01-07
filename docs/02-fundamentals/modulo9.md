# Modulo 9 - Abstraction (Astrazione)

**Location:** `01_Fundamentals_Examples/Modulo9Astrazione/`

## Overview

Explores **Abstraction**, the third pillar of Object-Oriented Programming (OOP).

## What is Abstraction?

Abstraction is hiding complex implementation details and showing only essential features.

## Key Concepts

### Abstract Classes
- Cannot be instantiated directly
- Can contain abstract methods (no implementation)
- Can contain concrete methods (with implementation)

```java
public abstract class Animal {
    abstract void makeSound();  // No implementation

    void sleep() {  // Has implementation
        System.out.println("Sleeping...");
    }
}
```

### Abstract Methods
- Declared without implementation
- Must be implemented by child classes

### Interfaces
- 100% abstract (Java 7 and below)
- Multiple inheritance possible
- Defines a contract

```java
public interface Flyable {
    void fly();
}
```

## Benefits

- Forces structure on subclasses
- Achieves loose coupling
- Supports polymorphism
- Defines clear contracts

## Skills Learned

- Creating abstract classes
- Implementing abstract methods
- Using interfaces
- Understanding abstraction principles

## Next Steps

Apply all four OOP pillars in real projects:
- Business Applications
- Advanced Projects
