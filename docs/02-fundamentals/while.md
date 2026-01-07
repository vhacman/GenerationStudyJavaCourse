# While Loops

**Location:** `01_Fundamentals_Examples/While/`

## Overview

Utility project with shared library classes for loop-based exercises.

## Purpose

This project provides common utility classes used across multiple loop exercises and examples.

## Key Concepts

- While loop structure
- Loop conditions
- Loop control variables
- Sentinel values
- Infinite loop prevention

## While Loop Structure

```java
while (condition) {
    // Code to repeat
    // Update condition variable
}
```

## Common Patterns

### Counter-Controlled Loop
```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}
```

### Sentinel-Controlled Loop
```java
String input = "";
while (!input.equals("quit")) {
    input = Console.readString("Enter command: ");
}
```

### Validation Loop
```java
int age = -1;
while (age < 0 || age > 120) {
    age = Console.readInt("Enter age: ");
}
```

## Related Projects

- Modulo 4 - Contains while loop exercises
- Loop comparison exercises (for vs while)
