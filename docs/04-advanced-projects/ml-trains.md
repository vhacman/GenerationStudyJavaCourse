# ML Trains

**Location:** `02_Tickets_Transportation/MLTrains`

## Overview

ML Trains is a train ticketing system with support for multiple journey bookings in a single session. The application manages validated station selection, calculates fares based on distance and class, and generates HTML receipts for each ticket. The project combines input validation loops, switch statement routing, and template-based file generation in an iterative workflow.

## Key Features

- Validation of station selection (Milano, Monza, Osnago, Lecco)
- Prevention of same-station departure and arrival
- Nested switch statements for kilometer distance mapping
- Class-based pricing (1st class €0.20/km, 2nd class €0.15/km)
- Multiple ticket generation in single session
- Template-based HTML receipt generation
- Do-while loops for input validation and session management
- Detailed ticket information in outputs

## Technologies Used

- Advanced control structures (do-while loops, nested switches)
- Input validation with repetition
- String comparison and validation
- Distance lookup using switch statements
- Conditional pricing logic
- Template pattern for file generation
- File I/O with FileWriter
- String replacement for template population

## Learning Objectives

Students learn to implement complex validation workflows with nested conditions, use switch statements for distance mapping, apply different pricing based on class selection, manage iterative ticket creation, populate HTML templates dynamically, and save files with meaningful names. The project demonstrates multi-ticket transaction handling and input validation patterns.
