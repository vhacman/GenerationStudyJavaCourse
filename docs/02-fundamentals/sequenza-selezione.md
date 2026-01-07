# Sequenza e Selezione

**Location:** `01_Fundamentals_Examples/SequenzaESelezione/`

## Overview

Age and height validation system for ride or activity access control.

## Main Program

**AgeAndHeightChecker.java**

## Features

- Dual validation: age AND height requirements
- Minimum age: 13 years
- Minimum height: 120 cm
- Nested if statements for combined validation
- Access granted only when both conditions are met

## Example Logic

```java
if (age >= 13) {
    if (height >= 120) {
        System.out.println("Access granted!");
    } else {
        System.out.println("Height too low");
    }
} else {
    System.out.println("Age too young");
}
```

## Key Concepts

- Sequential execution
- Selection statements (if/else)
- Nested conditionals
- Boolean conditions
- Combined validation logic
- AND logic implementation

## Real-World Application

Similar to:
- Theme park ride restrictions
- Activity safety requirements
- Access control systems
