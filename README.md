# Java Course - Generation Study

This repository contains Java programming examples and exercises from the Generation Study Java Course.

## 📚 Course Materials
**Course Notes & Documentation:** [Access my Drive folder with all course notes and materials](https://drive.google.com/drive/folders/1f54Eu_EK2zw2XcMK-9qJH4ZErLXtV8Rj?hl=it)

## Project Structure

```
JAVACORSO/
├── Examples/
│   └── src/
│       └── com/
│           └── generation/
│               ├── demo/          # Basic demo programs
│               ├── food/          # Food and nutrition calculators
│               ├── geometry/      # Geometric calculations
│               ├── housing/       # Real estate calculations
│               ├── trips/         # Travel planning calculators
│               └── library/       # Utility classes
├── PrintLabel/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           └── library/
│   │               ├── food/      # Food label generator
│   │               ├── housing/   # Housing label generator
│   │               ├── Console.java
│   │               ├── FileReader.java
│   │               ├── FileWriter.java
│   │               └── Template.java
│   └── print/                     # Output directory and HTML templates
│       ├── template.html          # Food label template
│       └── templateHousing.html   # Housing label template
├── BrianzaTaxi/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   ├── main/      # Taxi receipt generator
│   │           │   └── utility/   # Taxi calculation utilities
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
├── BrianzaTaxiService/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   ├── main/      # Enhanced taxi receipt app
│   │           │   ├── model/     # Data models (Ticket)
│   │           │   └── service/   # Service layer classes
│   │           ├── user/
│   │           │   └── service/   # User management
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
├── BrianzaTrains/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   └── main/      # Train ticket generator
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
├── MuseumTicket/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── museum/
│   │           │   └── main/      # Museum ticket generator
│   │           ├── mg/
│   │           │   └── utility/   # Museum calculation utilities
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
├── DeveloperCandidatura/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── main/          # Job application scoring system
│   │           └── library/       # Shared utility classes
├── ProlocoLakeComo/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── lcp/
│   │           │   └── main/      # Tourist guide application
│   │           └── library/       # Shared utility classes
│   └── archive/                   # Saved applications
├── DiscotecaTicket/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   └── main/      # Nightclub ticket system
│   │           └── library/       # Shared utility classes
├── VillaMelzi/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   └── main/      # Villa Melzi ticket system
│   │           └── library/       # Shared utility classes
├── Taxes/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   └── main/      # Business tax calculator
│   │           └── library/       # Shared utility classes
├── SequenzaESelezione/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── checker/       # Age and height validation
│   │           └── library/       # Shared utility classes
├── ExtraLesson/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   ├── main/      # Ticket management system
│   │           │   └── model/
│   │           │       └── entities/ # Ticket entity
│   │           └── library/       # Shared utility classes
│   └── ticket/                    # Generated ticket files
├── ExtraLesson2/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── cyphar/
│   │           │   └── cesar/     # Caesar cipher implementation
│   │           └── library/       # Shared utility classes
├── MLTrains/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   └── main/      # Train ticket system (Milano-Como line)
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
├── Modulo4/
│   └── src/
│       └── com/
│           └── generation/
│               ├── exercises/     # Loop exercises (21-24)
│               └── library/       # Shared utility classes
├── MonzaMetro/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   └── main/      # Metro ticket system main entry point
│   │           ├── ticketGenerator/
│   │           │   ├── Ticket.java        # Ticket entity class
│   │           │   ├── Passenger.java     # Passenger entity class
│   │           │   └── TicketService.java # Service layer for ticket operations
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
│       └── templateGenerale.html  # Metro ticket template
├── Recap/
│   └── src/
│       └── com/
│           └── generation/
│               ├── lessons/       # Review lessons (001-005)
│               └── library/       # Shared utility classes
└── While/
    └── src/
        └── com/
            └── generation/
                └── library/       # Shared utility classes
```

## Modules

### Demo (`com.generation.demo`)
Basic introductory programs:
- `HelloWorld.java` - Classic "Hello World" program
- `HelloMe.java` - Personalized greeting program

### Food (`com.generation.food`)
Nutrition-related calculators:
- `NeedCalculator.java` - Calculates daily caloric and protein needs based on weight and macronutrient intake
- `PrintFoodLabel.java` - Food labeling utilities
- `FoodAnalyzer.java` - Food analysis tools

### Geometry (`com.generation.geometry`)
Mathematical calculation programs:
- `SquareCalculator.java` - Square area and perimeter calculations
- `RectangleCalculator.java` - Rectangle area and perimeter calculations

### Housing (`com.generation.housing`)
Real estate calculation tools:
- `PrintHouseLabel.java` - Calculates and displays property information including area and price based on room dimensions

### Trips (`com.generation.trips`)
Travel planning calculators:
- `FamilyTripCalculator.java` - Family vacation planner that calculates total costs including flights, accommodation, and daily budgets

### Library (`com.generation.library`)
Utility classes:
- `Console.java` - Console input/output helper class
- `FileReader.java` - File reading utility
- `FileWriter.java` - File writing utility
- `Template.java` - HTML template loader and processor

## How to Run
### Using an IDE

1. Import the project into your IDE
2. Navigate to the desired Java file
3. Right-click and select "Run As" → "Java Application"

## Example Programs

### NeedCalculator
Calculates nutritional coverage based on:
- Weight in kg
- Carbohydrates, proteins, and fats consumed (in grams)
- Outputs caloric and protein coverage percentages

Formula:
- Caloric need: 150 calories per kg of body weight
- Protein need: 1.5 grams per kg of body weight

### PrintHouseLabel
Calculates property pricing based on:
- Room dimensions
- Bathroom dimensions
- Balcony dimensions
- Square meter price
- Outputs total property price (balcony area counted at 50% value)

### FamilyTripCalculator
Plans family vacations with complete cost breakdown:
- Number of travelers
- Flight costs per person
- Number of nights
- Daily budget per person
- Destination city and hotel details
- Outputs total vacation cost and average cost per person

## Advanced Projects

These projects demonstrate file I/O operations and HTML template processing to generate formatted labels and receipts.

### BrianzaTaxi Project

A taxi receipt generator that calculates fares and generates HTML receipts.

**Main Program:** `PrintTaxiReceipt.java`

Generates HTML taxi receipts based on:
- Trip duration in minutes
- Service level (standard or premium)
- Departure time (hour and minute)
- Arrival time (hour and minute)
- Calculates total fare based on time-based pricing
- Uses HTML template system to generate formatted receipt
- Saves result as HTML file in `print/` directory

**Utility Classes:**
- `TaxiUtility.java` - Helper methods for input validation and price calculation

Located in: `JAVACORSO/BrianzaTaxi/src/com/generation/bt/`

### BrianzaTrains Project

A train ticket generator that calculates fares based on distance and service class.

**Main Program:** `PrintTicket.java`

Generates train tickets with:
- Distance in kilometers
- Service class (1st or 2nd class)
- Passenger information (name, surname, age)
- Departure and arrival times
- Pricing logic:
  - 1st class: €0.20 per km
  - 2nd class: €0.15 per km
  - Minimum fare: €1.70
- Displays formatted ticket with emoji graphics

Located in: `JAVACORSO/BrianzaTrains/src/com/generation/bt/main/`

### MuseumTicket Project

A museum ticket generator that calculates admission prices based on visitor demographics.

**Main Program:** `MuseumGenerator.java`

Generates HTML museum tickets based on:
- Visitor's full name
- Age
- Annual income (RAL - Reddito Annuo Lordo)
- Ticket class/category choice
- Calculates pricing based on demographic factors
- Uses HTML template system to generate formatted ticket
- Saves result as HTML file in `print/` directory

**Utility Classes:**
- `MGUtility.java` - Helper methods for input validation and price calculation

Located in: `JAVACORSO/MuseumTicket/src/com/generation/museum/main/`

### BrianzaTaxiService Project

An enhanced version of the BrianzaTaxi project with service-oriented architecture and VIP user management.

**Main Program:** `PrintTaxiReceipt.java`

Features:
- **User Management System**: Optional user registration with complete personal data
- **Service Layer Architecture**: Separated concerns with dedicated service classes:
  - `InputService.java` - Handles all user input operations
  - `PriceService.java` - Price calculation logic with VIP discounts
  - `UserService.java` - User management and ride tracking
  - `TemplateService.java` - HTML template processing
- **VIP System**: Users become VIP after 10 rides with 20% discount
- **Ride History**: Tracks number of rides and total spending per user
- **Flexible User Data**: Option for quick registration (name, surname, phone) or complete data entry
- **Enhanced Ticket Model**: Improved ticket representation with detailed information

Located in: `JAVACORSO/BrianzaTaxiService/src/com/generation/bt/main/`

### DeveloperCandidatura Project

A job application scoring system for back-end developer positions in the Lazio region.

**Main Program:** `Main.java` with `Application.java` class

**Application Class Features:**
- Candidate data management (name, surname, birth year, province, education, experience)
- Automatic age calculation using `LocalDate`
- Complex scoring algorithm based on multiple criteria:
  - **Province Bonus**: Roma/Latina (+20 points), Other Lazio provinces (+10 points)
  - **Education**: Diploma (+20 points), Degree (+30 points)
  - **Degree Subject**: Computer Science/Engineering (+30), Mathematics/Physics (+20), Statistics/Logic (+10)
  - **Experience**: +10 points per year of experience
  - **Travel Availability**: +20 points if available
  - **Java Knowledge**: +20 points if knows Java, +10 if not
- Formatted output with complete candidate summary and final score
- Object-oriented design with encapsulation (getters/setters)

Located in: `JAVACORSO/DeveloperCandidatura/src/com/generatio/main/`

### ProlocoLakeComo Project

Tourist guide application system for Lake Como with scoring-based candidate selection.

**Main Program:** `TouristGuideApplication.java`

Features:
- Age validation (only candidates between 18-50 years old)
- Province-based scoring:
  - CO/LC provinces: +30 points
  - VA/BG/MB provinces: +20 points
  - MI/MN/BR/SO provinces: +10 points
- Education level scoring: HS (+20), College (+30)
- Subject bonus: Tourism subject (+20 points)
- Application persistence: Saves candidate data to text files in `archive/` directory
- File naming with candidate number
- Formatted output with complete candidate information and score

Located in: `JAVACORSO/ProlocoLakeComo/src/com/generation/lcp/main/`

### DiscotecaTicket Project

Nightclub entrance validation and pricing system with age and gender-based rules.

**Main Program:** `DiscotecaTicket.java`

Features:
- Age verification (minimum 18 years old)
- Gender-based pricing using ternary operator:
  - Male (M/m): 18 euro
  - Female (F/f): 10 euro
- Entrance denied for underage users
- Demonstrates conditional statements and character comparison

Located in: `JAVACORSO/DiscotecaTicket/src/com/generatio/bt/main/`

### VillaMelzi Project

Villa Melzi museum ticket system with complex pricing logic based on residency, age, and student status.

**Main Program:** `VillaMenzi.java` (note: typo in filename)

**Pricing Rules:**
- **Free Entry**: Bellagio or Civenna residents (0 euro)
- **Provincial Discount**: Como, Lecco, Varese, or Bergamo residents (fixed 5 euro, non-cumulative)
- **Base Price**: 10 euro with cumulative discounts:
  - 70+ years: -2 euro (special 70th anniversary promotion)
  - 65+ years OR under 7 years: -1 euro (not cumulative with 70+ discount)
  - University students: -1 euro
- **Data Collection**: Saves visitor statistics to file for analysis
- **Validation**: Prevents negative prices

Located in: `JAVACORSO/VillaMelzi/src/com/generation/bt/main/`

### Taxes Project

Business tax calculator with startup discount incentives.

**Main Program:** `Taxes.java`

Features:
- Revenue-based tax calculation
- Tax exemption for revenue under 20,000 euro
- Standard tax rate: 20% on revenue exceeding 20,000 euro
- Young business discount: 20% reduction for businesses open less than 5 years
- Demonstrates nested conditional statements
- Clear output formatting with intermediate calculations

Located in: `JAVACORSO/Taxes/src/com/generation/bt/main/`

### SequenzaESelezione Project

Age and height validation system for ride or activity access control.

**Main Program:** `AgeAndHeightChecker.java`

Features:
- Dual validation: age AND height requirements
- Minimum age: 13 years
- Minimum height: 120 cm
- Nested if statements for combined validation
- Access granted only when both conditions are met

Located in: `JAVACORSO/SequenzaESelezione/src/com/generation/checker/`

### ExtraLesson Project

Advanced ticket management system demonstrating object-oriented programming with entity classes.

**Main Program:** `TicketManagement.java` with `Ticket.java` entity

**Key Concepts:**
- **Entity Class Pattern**: `Ticket.java` represents a data model with attributes and methods
- **Automatic Timestamp**: Uses `LocalDateTime.now()` for ticket creation time
- **Object Methods**: Ticket validates itself with `isValid()` method
- **Loop Structure**: Do-while loop for continuous ticket creation
- **Template System**: HTML template processing with placeholder replacement
- **File Naming**: Dynamic filename generation based on ticket ID
- **User Confirmation**: Asks before saving to file
- Demonstrates separation between data (Ticket) and operations (TicketManagement)

Located in: `JAVACORSO/ExtraLesson/src/com/generation/bt/main/`

### ExtraLesson2 Project

Caesar cipher implementation for text encryption and decryption.

**Main Program:** `Main.java` with `CaesarCypher.java` utility class

**Features:**
- **Cipher Method**: Encrypts text by shifting characters by K positions
- **Decipher Method**: Decrypts text by reversing the shift
- **Character Manipulation**: Uses char-to-int conversion and arithmetic operations
- **String Building**: Concatenates characters to build result strings
- Demonstrates:
  - Type casting between char and int
  - For loops for string iteration
  - Method design for encryption/decryption

Located in: `JAVACORSO/ExtraLesson2/src/com/generation/cyphar/cesar/`

### MLTrains Project

A train ticket generator for the Milano-Como railway line with HTML template integration.

**Main Program:** `Main.java` with `PrintTicket.java`

**Features:**
- Train ticket booking system for Milano-Como line
- Distance-based fare calculation
- Service class selection (1st or 2nd class)
- HTML template system for ticket generation
- Departure and arrival time tracking
- Passenger information management
- Formatted ticket output with professional layout

Located in: `JAVACORSO/MLTrains/src/com/generation/bt/main/`

### Modulo4 Project

Loop exercises collection demonstrating iterative programming concepts.

**Main Program:** `Exercises21to24.java` with menu system

**Exercise 21 - Maximum and Average:**
- Input multiple integers
- Calculate and display the largest number
- Calculate and display the average
- Do-while loop with user-controlled exit

**Exercise 22 - Bill Payment Manager:**
- Input number of bills to pay
- For each bill: collect description and amount
- Display complete bill list with individual amounts
- Calculate and show total amount due

**Exercise 23 - Even Numbers (For Loop):**
- Display all even numbers below 1000
- Uses for loop with modulo operator

**Exercise 023 - Even Numbers (While Loop):**
- Same as Exercise 23 but using while loop
- Demonstrates different loop approaches

**Exercise 24 - Odd-Even Pairs:**
- Display pairs of consecutive odd-even numbers below 1000
- Format: 1-2, 3-4, 5-6, etc.
- Uses loop with increment by 2

**Additional Program:** `EsercizioLezione.java`
- Interactive number summation with do-while loop
- Displays partial sums during input
- Demonstrates user-controlled iteration

Located in: `JAVACORSO/Modulo4/src/com/generation/exercises/`

### MonzaMetro Project

Metro ticket generation system with object-oriented design and HTML template integration.

**Main Program:** `Main.java`

**Features:**
- **Object-Oriented Architecture**: Separated entity classes (Passenger, Ticket) and service layer (TicketService)
- **Passenger Management**: Complete passenger information collection (name, surname, age)
- **Age-Based Pricing System**:
  - Children (0-10 years): €1.30
  - Adults (11-69 years): €1.90
  - Seniors (70+ years): Free (€0.00)
- **Ticket Information**:
  - Unique random ticket ID generation (1-999999)
  - Departure station selection
  - Departure date and time (custom selection)
  - Check-in timestamp (automatic current time)
- **Dual Output System**:
  - Console formatted ticket display with borders and sections
  - HTML file generation using template system
- **Date and Time Handling**: Uses `LocalDateTime` with custom formatters
- **Template Processing**: Dynamic HTML generation with placeholder replacement
- **Input Validation**: Age validation and error handling

**Classes:**
- `Ticket.java` - Entity class representing a metro ticket with all attributes and getters/setters
- `Passenger.java` - Entity class for passenger information with `toString()` override
- `TicketService.java` - Service layer with static methods for:
  - Personal information collection
  - Age verification and price calculation
  - Departure details (station, date, time)
  - Ticket ID generation
  - Check-in timestamp creation
  - Console and HTML output formatting

Located in: `JAVACORSO/MonzaMetro/src/com/generation/`

### Recap Project

Review lessons covering fundamental Java programming concepts.

**Lesson 001 - HelloWorld:**
- Basic program structure
- Output statements
- First Java program

**Lesson 002 - PrimeEspressioni:**
- Variables and data types
- Arithmetic expressions
- Basic calculations

**Lesson 003 - EsempiSelezioni:**
- Conditional statements (if/else)
- Boolean logic
- Decision making

**Lesson 004 - EsempioCiclo:**
- Loop structures introduction
- Basic iteration concepts

**Lesson 005 - EsempioRettangolo:**
- Rectangle area calculator
- Input validation with do-while loops
- Data validation (positive values only)
- Practical use of loops for input validation

Located in: `JAVACORSO/Recap/src/com/generation/lessons/`

### While Project

Utility project with shared library classes for loop-based exercises.

Located in: `JAVACORSO/While/src/com/generation/library/`

## PrintLabel Project

A more advanced project that demonstrates file I/O operations and HTML template processing to generate formatted labels.

### PrintFoodLabel
Generates HTML food nutrition labels:
- Input: food name, image URL, macronutrients (carbs, proteins, fats, saturated fats)
- Calculates total calories using standard conversion rates:
  - Carbohydrates: 4 calories per gram
  - Proteins: 4 calories per gram
  - Fats: 9 calories per gram
- Loads HTML template from file
- Replaces placeholders with actual values
- Outputs formatted HTML label to file in `print/` directory

Located in: `JAVACORSO/PrintLabel/src/com/generation/library/food/PrintFoodLabel.java`

### PrintHousingLabel
Generates HTML real estate property labels:
- Input: city, address, image URL, room/bathroom/balcony dimensions, price per square meter
- Calculates:
  - Individual area for each space (room, bathroom, balcony)
  - Total internal area (room + bathroom)
  - Total property price (balcony counted at 50% value)
- Uses HTML template system to generate formatted output
- Saves result as HTML file in `print/` directory

Located in: `JAVACORSO/PrintLabel/src/com/generation/library/housing/PrintHousingLabel.java`

### Key Features of Advanced Projects
- **File I/O Operations**: Reading templates and writing output files
- **Template System**: Load HTML templates and dynamically replace placeholders
- **String Manipulation**: Using `.replace()` method for template processing
- **Organized Output**: All generated labels/receipts saved in dedicated `print/` directory
- **Reusable Components**: `Console`, `FileReader`, `FileWriter`, and `Template` utility classes
- **Input Validation**: Ensuring data quality and handling edge cases
- **Business Logic**: Implementing real-world pricing calculations and rules
- **Formatted Output**: Both console-based formatted output and HTML generation
- **Modular Design**: Separation of concerns with utility classes for business logic

## Learning Topics

This repository covers fundamental and advanced Java concepts including:

### Basic Concepts
- Variables and data types (int, String, char, boolean, double)
- Console input/output operations
- Arithmetic operations and calculations
- Basic program structure
- Package organization

### Control Structures
- Conditional statements (if/else)
- Nested conditionals
- Switch statements
- Ternary operators
- Boolean logic and comparisons
- Loop structures:
  - Do-while loops with user-controlled exit
  - For loops with counters and iteration
  - While loops for conditional repetition
  - Loop increment patterns (i++, i+=2)
- Menu-driven program design

### String Manipulation
- String methods (charAt, equalsIgnoreCase, contains, replace)
- String concatenation
- Template processing and placeholder replacement

### File I/O Operations
- Reading from files (FileReader)
- Writing to files (FileWriter)
- HTML template system and dynamic content generation
- File naming and path management
- Exception handling (try-catch blocks)

### Object-Oriented Programming
- Class design and implementation
- Entity classes and data models
- Encapsulation (private fields with getters/setters)
- Constructors
- Object methods and behavior
- Service layer architecture
- Separation of concerns

### Advanced Topics
- Date and time handling (LocalDate, LocalDateTime)
- Type casting (char to int conversions, explicit casting for averages)
- Complex business logic implementation
- Multi-criteria scoring algorithms
- User management systems
- VIP/loyalty programs
- Data persistence and statistics collection
- Modular architecture design
- Caesar cipher cryptography
- Mathematical operations:
  - Maximum value calculation
  - Average calculation with proper casting
  - Sum accumulation
  - Modulo operator for even/odd detection
  - Counter and accumulator patterns

### Software Engineering Practices
- Code organization and structure
- Reusable utility classes
- Input validation
- Error handling
- User experience design
- Formatted output and reporting

## Author
Generation Study Course Student - Hacman Viorica Gabriela

## License

Educational use only
