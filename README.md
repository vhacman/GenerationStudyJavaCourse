# Java Course - Generation Study

This repository contains Java programming examples and exercises from the Generation Study Java Course.

## 📚 Course Materials
**Course Notes & Documentation:** [Access my Drive folder with all course notes and materials](https://drive.google.com/drive/folders/1f54Eu_EK2zw2XcMK-9qJH4ZErLXtV8Rj?hl=it)

## Project Structure

The projects are organized into 4 thematic categories:

```
GenerationStudyJavaCourse/
├── 01_Fundamentals_Examples/    # Basic concepts, lessons, and control structures
│   ├── Examples/
│   ├── Modulo4/
│   ├── Modulo 6/
│   ├── Modulo7Incapsulamento/
│   ├── Recap/
│   ├── SequenzaESelezione/
│   └── While/
├── 02_Tickets_Transportation/   # Ticketing and transportation systems
│   ├── BrianzaTaxi/
│   ├── BrianzaTaxiService/
│   ├── BrianzaTrains/
│   ├── DiscotecaTicket/
│   ├── MilanoLeccoTrains2/
│   ├── MilanoLeccoTrains3/
│   ├── MilanoLeccoTrains4/
│   ├── MLTrains/
│   └── MonzaMetro/
├── 03_Business_Applications/    # Business and management applications
│   ├── BrianzaTrainsObjects/
│   ├── DeveloperCandidatura/
│   ├── JavaBus/
│   ├── JavItaAirline/
│   ├── LeccoB&B/
│   ├── MuseumTicket/
│   ├── PrintLabel/
│   ├── ProlocoLakeComo/
│   ├── RepairShop/
│   ├── SBHotel/
│   └── VillaMelzi/
└── 04_Exercises_Practice/       # Extra exercises and practice
    ├── ChristmasTime/
    ├── ExtraLesson/
    ├── ExtraLesson2/
    └── Taxes/
```

### Detailed Structure

```
01_Fundamentals_Examples/
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
02_Tickets_Transportation/
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
03_Business_Applications/
├── BrianzaTrainsObjects/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   ├── main/      # Main controller (MVC pattern)
│   │           │   │   └── Main.java
│   │           │   ├── test/      # Testing layer
│   │           │   │   └── TicketTest.java
│   │           │   └── view/      # View layer (TicketView)
│   │           │       └── TicketView.java
│   │           ├── model/
│   │           │   └── entities/  # Entity classes (Ticket)
│   │           │       └── Ticket.java
│   │           └── library/       # Shared utility classes
│   ├── template/                  # Text and HTML templates
│   └── print/                     # Generated ticket files
├── DeveloperCandidatura/
│   ├── src/
│   │   └── com/
│   │       ├── generatio/         # Note: package name typo
│   │       │   └── main/          # Job application scoring system
│   │       │       ├── Application.java
│   │       │       └── Main.java
│   │       └── generation/
│   │           └── library/       # Shared utility classes
│   ├── archive/                   # Saved application files
│   └── print/                     # HTML template
│       └── template.html
├── JavaBank/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── ba/
│   │           │   ├── main/      # Main controller with menu system
│   │           │   │   └── Main.java
│   │           │   ├── model/
│   │           │   │   └── entities/ # Entity classes
│   │           │   │       ├── BankAccount.java   # Account entity with BigDecimal
│   │           │   │       ├── Client.java        # Client entity with SSN
│   │           │   │       ├── Config.java        # Configuration settings
│   │           │   │       └── Country.java       # Country enum for date formatting
│   │           │   ├── service/   # Service layer
│   │           │   │   ├── AccountService.java    # Account management service
│   │           │   │   ├── SsnService.java        # SSN validation service
│   │           │   │   └── UserInputService.java  # Input handling service
│   │           │   ├── view/      # View layer
│   │           │   │   └── BankView.java
│   │           │   ├── demo/      # Demo files
│   │           │   │   └── ssn.txt
│   │           │   └── test/      # Testing layer
│   │           └── library/       # Shared utility classes
│   ├── template/                  # Text and HTML templates
│   │   ├── template.html          # HTML account statement template
│   │   ├── template.txt           # Text account statement template
│   │   ├── menu.txt               # Main menu template
│   │   └── internalMenu.txt       # Internal operations menu
│   └── print/                     # Generated account statements
├── JavaBus/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── jb/
│   │           │   ├── main/      # Main controller (MVC pattern)
│   │           │   │   └── Main.java
│   │           │   ├── model/
│   │           │   │   └── entities/ # Ticket entity with 3-class system
│   │           │   │       └── Ticket.java
│   │           │   ├── test/      # Unit testing setup
│   │           │   │   └── TicketTest.java
│   │           │   └── view/      # View layer (TicketView)
│   │           │       └── TicketView.java
│   │           └── library/       # Shared utility classes
│   ├── template/                  # Text template
│   │   └── template.txt
│   └── print/                     # HTML template
│       └── template.html
├── JavItaAirline/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── jia/
│   │           │   ├── main/      # Main controller
│   │           │   │   └── Main.java
│   │           │   ├── demo/      # Demo programs
│   │           │   │   └── JavaitaAirplaneDemo.java
│   │           │   ├── test/      # JUnit testing
│   │           │   │   └── JavaitaTest.java
│   │           │   ├── model/
│   │           │   │   └── entities/ # Entity classes with enums
│   │           │   │       ├── AirlineTicket.java
│   │           │   │       ├── MembershipType.java (enum)
│   │           │   │       └── ServiceClass.java (enum)
│   │           │   └── view/      # View layer
│   │           │       └── JavaitaView.java
│   │           └── library/       # Shared utility classes
│   └── template/                  # Templates directory
├── LeccoB&B/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── lbb/
│   │           │   ├── main/      # Main controller
│   │           │   │   └── Main.java
│   │           │   ├── demo/      # Demo programs
│   │           │   │   ├── DemoBooking.java
│   │           │   │   └── LocalDateDemo.java
│   │           │   ├── model/
│   │           │   │   └── entities/ # Entity classes with enums
│   │           │   │       ├── Booking.java
│   │           │   │       ├── MembershipType.java (enum)
│   │           │   │       └── RoomType.java (enum)
│   │           │   └── view/      # View layer
│   │           │       └── BookingView.java
│   │           └── library/       # Shared utility classes
│   └── template/                  # HTML and TXT templates
│       ├── template.html
│       └── template.txt
├── MuseumTicket/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── museum/
│   │           │   └── main/      # Museum ticket generator
│   │           │       └── MuseumGenerator.java
│   │           ├── mg/
│   │           │   └── utility/   # Museum calculation utilities
│   │           │       └── MGUtility.java
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
│       └── template.html
├── PrintLabel/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           └── library/
│   │               ├── food/      # Food label generator
│   │               │   └── PrintFoodLabel.java
│   │               ├── housing/   # Housing label generator
│   │               │   └── PrintHousingLabel.java
│   │               ├── Console.java
│   │               ├── FileReader.java
│   │               ├── FileWriter.java
│   │               └── Template.java
│   └── print/                     # Output directory and HTML templates
│       ├── template.html          # Food label template
│       └── templateHousing.html   # Housing label template
├── ProlocoLakeComo/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── lcp/
│   │           │   └── main/      # Tourist guide application
│   │           │       └── TouristGuideApplication.java
│   │           └── library/       # Shared utility classes
│   └── archive/                   # Saved application files
├── RepairShop/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── rs/
│   │           │   ├── main/      # Main controller
│   │           │   │   └── Main.java
│   │           │   ├── demo/      # Demo programs
│   │           │   │   ├── DemoRepair.java
│   │           │   │   └── DemoRepairView.java
│   │           │   ├── test/      # JUnit testing
│   │           │   │   └── RepairTest.java
│   │           │   ├── model/
│   │           │   │   └── entities/ # Entity classes
│   │           │   │       └── Repair.java
│   │           │   └── view/      # View layer
│   │           │       └── RepairView.java
│   │           └── library/       # Shared utility classes
│   └── template/                  # Multiple templates
│       ├── menu.txt               # Main menu
│       ├── repairPreView.txt      # Text preview template
│       ├── clientView.html        # Client invoice template
│       ├── repair_1.html          # Sample repair 1
│       ├── repair_2.html          # Sample repair 2
│       └── repair_3.html          # Sample repair 3
├── SBHotel/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── sbh/
│   │           │   └── main/      # Hotel management system
│   │           │       ├── RoomManagement.java      # Room booking system
│   │           │       └── RoomCleaningOrder.java   # Cleaning service scheduler
│   │           └── library/       # Shared utility classes
│   ├── assets/                    # Templates and resources
│   │   ├── template/              # HTML templates
│   │   │   ├── booking.html       # Booking confirmation template
│   │   │   ├── cancellationTemplate.html # Cancellation voucher template
│   │   │   └── cleaningTemplate.html     # Cleaning order template
│   │   ├── logo.txt               # Hotel logo
│   │   ├── menu.txt               # Main menu
│   │   └── rooms.txt              # Room list
│   └── print/                     # Generated documents
└── VillaMelzi/
    ├── src/
    │   └── com/
    │       └── generation/
    │           ├── bt/
    │           │   └── main/      # Villa Melzi ticket system
    │           │       └── VillaMenzi.java (note: typo in filename)
    │           └── library/       # Shared utility classes

04_Exercises_Practice/
├── ChristmasTime/
    ├── src/
    │   └── com/
    │       └── generation/
    │           ├── xmas/          # Christmas-themed exercises
    │           │   ├── PresentListV1-V5.java # Present list iterations (loop exercises)
    │           │   ├── SetteEMezzo.java      # Italian card game (7.5)
    │           │   ├── RegaloNonno.java      # Grandpa's gift calculator
    │           │   ├── PrintBetweenAandB.java # Print numbers in range
    │           │   └── PrintDueADueFraAeB.java # Print pairs in range
    │           └── library/       # Shared utility classes
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
└── Taxes/
    ├── src/
    │   └── com/
    │       └── generation/
    │           ├── bt/
    │           │   └── main/      # Business tax calculator
    │           └── library/       # Shared utility classes

01_Fundamentals_Examples/ (continued)
├── Modulo4/
│   └── src/
│       └── com/
│           └── generation/
│               ├── exercises/     # Loop exercises (21-24)
│               └── library/       # Shared utility classes
├── Modulo 6/
│   └── src/
│       └── com/
│           └── generation/
│               ├── modulo6/
│               │   └── main/      # OOP exercises (Person, House classes)
│               └── library/       # Shared utility classes
├── Modulo7Incapsulamento/
│   └── src/
│       └── com/
│           └── generation/
│               ├── lessons/       # Encapsulation lessons (Person class)
│               └── library/       # Shared utility classes
├── Recap/
│   └── src/
│       └── com/
│           └── generation/
│               ├── lessons/       # Review lessons (001-007)
│               └── library/       # Shared utility classes
├── SequenzaESelezione/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── checker/       # Age and height validation
│   │           └── library/       # Shared utility classes
└── While/
    └── src/
        └── com/
            └── generation/
                └── library/       # Shared utility classes

02_Tickets_Transportation/ (continued)
├── MLTrains/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── bt/
│   │           │   └── main/      # Train ticket system (Milano-Como line)
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and HTML templates
├── MilanoLeccoTrains2/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── ml/
│   │           │   └── main/      # Train ticket system (Milano-Lecco line)
│   │           ├── lessons/       # Programming lessons (001-003)
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and templates
├── MilanoLeccoTrains3/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── ml/
│   │           │   └── main/      # Enhanced train ticket system with discounts
│   │           └── library/       # Shared utility classes (including Console2)
│   └── print/                     # Output directory and templates
├── MilanoLeccoTrains4/
│   ├── src/
│   │   └── com/
│   │       └── generation/
│   │           ├── trains/
│   │           │   └── service/   # Service-oriented architecture
│   │           │       ├── Main.java         # Entry point with menu system
│   │           │       ├── Station.java      # Station entity and validation
│   │           │       ├── Ticket.java       # Ticket entity
│   │           │       ├── TicketInput.java  # Input handling service
│   │           │       └── TicketPrinter.java # Output formatting service
│   │           └── library/       # Shared utility classes
│   └── print/                     # Output directory and templates
└── MonzaMetro/
    ├── src/
    │   └── com/
    │       └── generation/
    │           ├── bt/
    │           │   └── main/      # Metro ticket system main entry point
    │           ├── ticketGenerator/
    │           │   ├── Ticket.java        # Ticket entity class
    │           │   ├── Passenger.java     # Passenger entity class
    │           │   └── TicketService.java # Service layer for ticket operations
    │           └── library/       # Shared utility classes
    └── print/                     # Output directory and HTML templates
        └── templateGenerale.html  # Metro ticket template
```

## Category Overview

### 01_Fundamentals_Examples
Basic Java programming concepts, lessons, and control structures including:
- **Examples**: Demo programs covering geometry, food calculations, housing, and trips
- **Modulo4**: Loop exercises (21-24)
- **Modulo 6**: Object-oriented programming fundamentals (Person and House classes)
- **Modulo7Incapsulamento**: Encapsulation principles with private fields and getters/setters
- **Recap**: Review lessons (001-007) covering fundamentals
- **SequenzaESelezione**: Age and height validation with conditional statements
- **While**: Loop-based exercises

### 02_Tickets_Transportation
Ticketing and transportation systems with progressive complexity:
- **BrianzaTaxi**: Basic taxi receipt generator
- **BrianzaTaxiService**: Enhanced taxi system with VIP management and service layer
- **BrianzaTrains**: Train ticket generator
- **DiscotecaTicket**: Nightclub entrance validation system
- **MLTrains**: Milano-Como train line ticketing
- **MilanoLeccoTrains2**: First iteration of Milano-Lecco line with distance calculation
- **MilanoLeccoTrains3**: Enhanced with passenger info and discount system
- **MilanoLeccoTrains4**: Advanced OOP architecture with service layer design
- **MonzaMetro**: Metro ticket system with age-based pricing

### 03_Business_Applications
Business and management applications:
- **BrianzaTrainsObjects**: OOP train ticketing system demonstrating MVC pattern with entity classes
- **DeveloperCandidatura**: Job application scoring system for developers
- **JavaBank**: Advanced banking system with account management, SSN validation, BigDecimal operations, and multi-client support
- **JavaBus**: Bus ticketing system with MVC architecture and three-class pricing model
- **JavItaAirline**: Airline ticket system with service classes, membership discounts, and JUnit testing
- **LeccoB&B**: Bed & Breakfast booking system with room types, membership discounts, and age verification
- **MuseumTicket**: Museum admission with demographic-based pricing
- **PrintLabel**: HTML label generator for food and housing
- **ProlocoLakeComo**: Tourist guide application with province-based scoring
- **RepairShop**: Repair shop management system with pricing calculation and HTML invoice generation
- **SBHotel**: Comprehensive hotel management with booking, cancellation, and cleaning services
- **VillaMelzi**: Villa museum ticket system with complex discount rules

### 04_Exercises_Practice
Extra exercises and practice programs:
- **ChristmasTime**: Holiday-themed exercises including card game (Sette e Mezzo)
- **ExtraLesson**: Advanced ticket management with entity classes
- **ExtraLesson2**: Caesar cipher encryption/decryption
- **Taxes**: Business tax calculator with startup incentives

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

Located in: `02_Tickets_Transportation/BrianzaTaxi/src/com/generation/bt/`

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

Located in: `02_Tickets_Transportation/BrianzaTrains/src/com/generation/bt/main/`

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

Located in: `03_Business_Applications/MuseumTicket/src/com/generation/museum/main/`

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

Located in: `02_Tickets_Transportation/BrianzaTaxiService/src/com/generation/bt/main/`

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

Located in: `03_Business_Applications/DeveloperCandidatura/src/com/generation/main/`

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

Located in: `03_Business_Applications/ProlocoLakeComo/src/com/generation/lcp/main/`

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

Located in: `02_Tickets_Transportation/DiscotecaTicket/src/com/generatio/bt/main/`

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

Located in: `03_Business_Applications/VillaMelzi/src/com/generation/bt/main/`

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

Located in: `04_Exercises_Practice/Taxes/src/com/generation/bt/main/`

### BrianzaTrainsObjects Project

Object-oriented train ticketing system demonstrating the MVC (Model-View-Controller) design pattern.

**Main Program:** `Main.java`

**Architecture:**

This project showcases professional software architecture following the MVC pattern:

**1. Model Layer** (`Ticket.java` entity):
- **Entity Class Pattern**: Represents a train ticket with attributes and behavior
- **Public Attributes**: id, km (kilometers), level (class)
- **Class Constants**:
  - `PRICEPERKMFIRSTCLASS = 0.2` (€0.20 per km)
  - `PRICEPERKMSECONDCLASS = 0.1` (€0.10 per km)
- **Business Methods**:
  - `getPrice()` - Calculates ticket price using ternary operator for class selection
  - `isValid()` - Validates ticket data (id > 0, km > 0, level = 1 or 2)
- **Encapsulation**: Separates data from presentation logic

**2. View Layer** (`TicketView.java`):
- **Template-Based Rendering**: Uses external templates for output formatting
- **Dual Format Support**:
  - Text format (template.txt) for console display
  - HTML format (template.html) for file output
- **render() Method**:
  - Loads template from file
  - Replaces placeholders: [id], [km], [level], [price]
  - Returns formatted string
- **Separation of Concerns**: View only handles presentation, no business logic

**3. Controller Layer** (`Main.java`):
- **User Interaction Management**: Handles input/output flow
- **askTicket() Method**:
  - Input collection with do-while loop
  - Validation using `isValid()` method
  - Continues until valid data is entered
- **Main Flow**:
  1. Collect ticket data
  2. Create two views (TXT for preview, HTML for saving)
  3. Display text preview in console
  4. Optional HTML file save (user confirmation)
  5. Dynamic filename using ticket ID

**Key Features:**
- **MVC Architecture**: Clear separation between Model, View, and Controller
- **Template System**: Reusable templates for different output formats
- **Input Validation**: Multi-level validation with user feedback
- **User Experience**: Preview before save, optional file generation
- **Code Reusability**: View class can be instantiated multiple times with different templates

**Advanced Concepts Demonstrated:**
- MVC design pattern implementation
- Entity class design with validation methods
- Template-based view rendering
- Separation of concerns principle
- Object-oriented encapsulation
- Static constants for configuration
- Method chaining for template replacement
- User-controlled workflow

Located in: `03_Business_Applications/BrianzaTrainsObjects/src/com/generation/`

### JavaBus Project

Bus ticketing system demonstrating MVC architecture with three-class pricing model and GreenCard discount system.

**Main Program:** `Main.java`

**Architecture:**

This project implements a complete bus ticketing system following MVC (Model-View-Controller) principles:

**1. Model Layer** (`Ticket.java` entity):
- **Entity Class Pattern**: Represents a bus ticket with attributes and behavior
- **Public Attributes**: id, km (kilometers), level (class), greenCard (discount card)
- **Class Constants**:
  - `PRICEPERKMFIRSTCLASS = 0.2` (€0.20 per km)
  - `PRICEPERKMSECONDCLASS = 0.1` (€0.10 per km)
  - `PRICEPERKMTHIRDCLASS = 0.05` (€0.05 per km)
- **Business Methods**:
  - `getPrice()` - Calculates ticket price based on class selection
  - **GreenCard System**: 50% discount if greenCard is true
  - Switch statement for class-based pricing
  - `isValid()` - Validates ticket data (id > 0, km > 0, level = 1/2/3)
- **Constructors**:
  - Default constructor
  - Parameterized constructor for all fields
- **Encapsulation**: Combines data with business logic

**2. View Layer** (`TicketView.java`):
- **Template-Based Rendering**: Uses external templates for output formatting
- **Dual Format Support**:
  - Text format (template.txt) for console display
  - HTML format (template.html) for file output
- **render() Method**:
  - Loads template from file using Template utility
  - Replaces placeholders: [id], [km], [level], [price]
  - Returns formatted string with ticket information
- **Separation of Concerns**: View only handles presentation, no business logic
- **Method Chaining**: Template replacement using fluent interface

**3. Controller Layer** (`Main.java`):
- **User Interaction Management**: Handles input/output flow
- **askTicket() Method**:
  - Input collection with do-while loop
  - Range validation using `Console.readIntBetween()`
  - Validation ranges: id (0-1000), km (1-1000), level (1-2)
  - Continues until valid data using `isValid()` method
- **Main Flow**:
  1. Collect ticket data from user
  2. Create two views (TXT for preview, HTML for saving)
  3. Display text preview in console
  4. Optional HTML file save with user confirmation
  5. Dynamic filename using ticket ID: `print/{id}_biglietto.html`

**4. Test Layer** (`TicketTest.java`):
- Testing framework setup for unit testing

**Key Features:**
- **MVC Architecture**: Clear separation between Model, View, and Controller
- **Three-Class System**: First, second, and third class with different pricing
- **GreenCard Discount**: Automatic 50% discount for cardholders
- **Template System**: Reusable templates for different output formats
- **Input Validation**: Multi-level validation with range checking and user feedback
- **User Experience**: Preview before save, optional file generation
- **Code Reusability**: View class can be instantiated multiple times with different templates

**Advanced Concepts Demonstrated:**
- MVC design pattern implementation
- Entity class design with validation and business logic methods
- Template-based view rendering
- Separation of concerns principle
- Object-oriented encapsulation
- Static constants for configuration
- Method chaining for template replacement
- User-controlled workflow with confirmation
- Switch statement for pricing logic
- Conditional discount application

**Package Structure:**
- `com.generation.jb.main` - Application entry point and controller
- `com.generation.jb.model.entities` - Ticket entity with business logic
- `com.generation.jb.view` - Presentation layer (TicketView)
- `com.generation.jb.test` - Testing layer (TicketTest)
- `com.generation.library` - Shared utilities (Console, FileReader, FileWriter, Template)

Located in: `03_Business_Applications/JavaBus/src/com/generation/`

### SequenzaESelezione Project

Age and height validation system for ride or activity access control.

**Main Program:** `AgeAndHeightChecker.java`

Features:
- Dual validation: age AND height requirements
- Minimum age: 13 years
- Minimum height: 120 cm
- Nested if statements for combined validation
- Access granted only when both conditions are met

Located in: `01_Fundamentals_Examples/SequenzaESelezione/src/com/generation/checker/`

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

Located in: `04_Exercises_Practice/ExtraLesson/src/com/generation/bt/main/`

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

Located in: `04_Exercises_Practice/ExtraLesson2/src/com/generation/cyphar/cesar/`

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

Located in: `02_Tickets_Transportation/MLTrains/src/com/generation/bt/main/`

### MilanoLeccoTrains2 Project

First iteration of the Milano-Lecco train ticketing system with basic functionality.

**Main Program:** `TicketManagement.java`

**Railway Line:** Milano → Monza → Osnago → Lecco

**Core Features:**
- **Station Selection System**:
  - 4 stations: Milano, Monza, Osnago, Lecco
  - Case-insensitive station validation
  - Prevents selecting same station for departure and arrival
  - Multi-case switch statement for validation
- **Distance Calculation**:
  - Automatic distance calculation based on station pair
  - Linear route: 15km intervals between consecutive stations
  - Total line length: 45km (Milano to Lecco)
  - Distance matrix implementation for all station combinations
- **Class-Based Pricing**:
  - 1st Class: €0.20 per km
  - 2nd Class: €0.15 per km
  - Dynamic price calculation based on distance and class
- **Input Validation**:
  - Station validation with error feedback
  - Class validation (only 1 or 2 accepted)
  - Prevents invalid station combinations
- **Multi-Ticket Management**:
  - Do-while loop for continuous ticket creation
  - User-controlled session termination
- **Programming Lessons Included**:
  - Lesson001HelloWorld.java
  - Lesson002MetodoStampaPersonalizzata.java
  - Lesson003Geometry.java

**Key Concepts Demonstrated:**
- Switch expressions with multi-case labels (Java 14+ syntax)
- Case-insensitive string comparison
- Method decomposition (askStation, askLevel, calculateDistance)
- Distance calculation algorithms
- Input validation patterns
- Loop-based menu systems

Located in: `02_Tickets_Transportation/MilanoLeccoTrains2/src/com/generation/ml/main/`

### MilanoLeccoTrains3 Project

Enhanced version of MilanoLeccoTrains2 with passenger information, discount system, and date validation.

**Main Program:** `TicketManagement.java`

**New Features:**

**1. Passenger Information Collection:**
- Full name input with non-empty validation
- Age input with range validation (1-120 years)
- GreenCard status (discount card system)

**2. Advanced Date/Time Management:**
- **Date Input System** (`askDate()` method):
  - Day validation (1-31)
  - Month validation (1-12)
  - Year validation (2025-2125)
  - Complete date validation with `isValidDate()` method
  - Month-specific day limits using switch statement
  - Leap year calculation: `(year % 4 == 0) ? 29 : 28`
  - Prevents invalid dates (e.g., February 30th)
- **Time Input System** (`askHour()` method):
  - Hour validation (0-23)
  - Minutes validation (0-59)
  - Formatted time output (HH:MM)

**3. Discount System** (`calculatePrice()` method):
- **Senior Discount**: Over 75 years → FREE travel (€0.00)
- **GreenCard Discount**: 20% reduction on total price
- Cascading discount logic (seniors get free, others can use GreenCard)

**4. Enhanced Output:**
- **Dual Output Format**:
  - HTML file generation (template.html)
  - Console text output (template.txt)
- **Template Placeholders**:
  - [fullName], [departure], [arrival], [age]
  - [level] (converted to "Prima"/"Seconda")
  - [km], [price], [date], [hour]
  - [greenCard] (converted to "Sì"/"No")
- File naming: `print_biglietto.html`

**5. Advanced Console Utilities:**
- Uses enhanced `Console2.java` library
- `askNotEmptyString()` - prevents empty string input
- `readIntBetween()` - validates integer ranges
- Improved error messaging

**Key Improvements:**
- Complete passenger profile management
- Complex date validation logic
- Discount policy implementation
- Dual-format output (HTML + Text)
- Business rule enforcement (age-based pricing)
- Enhanced input validation with range checking
- Template system with multiple placeholders

**Technical Concepts:**
- Leap year algorithm
- Nested validation logic
- Ternary operators for conversions
- String concatenation for date formatting
- Boolean to string conversion for display
- Method chaining in template replacement

Located in: `02_Tickets_Transportation/MilanoLeccoTrains3/src/com/generation/ml/main/`

### MilanoLeccoTrains4 Project

Most advanced iteration with full object-oriented architecture and service layer design.

**Main Program:** `Main.java`

**Architecture:**

This project demonstrates professional software architecture with separation of concerns:

**1. Menu-Driven Main Application** (`Main.java`):
- **Welcome System**: Decorative ASCII art welcome message
- **Main Menu Loop**:
  - Option 1: Buy ticket
  - Option 2: View system information
  - Option 3: Exit
- **Template-Based UI**: Menu loaded from external text files
- **Post-Purchase Options**:
  - View ticket in console
  - Save ticket to HTML
  - Return to main menu

**2. Station Entity Class** (`Station.java`):
- **Value Object Pattern**: Represents a railway station
- **Built-in Validation**: Constructor and setter validate station names
- **Constants Definition**:
  - `MILANO`, `MONZA`, `OSNAGO`, `LECCO` as public static final
  - Prevents "magic strings" throughout codebase
- **Case-Insensitive Handling**: All names stored in lowercase
- **Validation Loop**: Refuses to accept invalid stations
- **Encapsulation**: Private name field with public getter/setter
- **Defensive Programming**: Double-check validation in multiple places

**3. Ticket Entity Class** (`Ticket.java`):
- Complete ticket data model
- Stores all passenger and journey information
- Encapsulated with getters/setters

**4. TicketInput Service** (`TicketInput.java`):
- **Input Collection Service**: `collectTicketData()` method
- Separates user interaction from business logic
- Creates and returns populated Ticket objects
- Delegates to Station class for validation

**5. TicketPrinter Service** (`TicketPrinter.java`):
- **Output Formatting Service**: Handles all ticket display
- **Dual Output Support**:
  - `printToConsole()` - formatted console display
  - `printToHTML()` - HTML file generation
- Template-based rendering
- Responsible for all presentation logic

**Key Features:**

**System Information Display:**
- Available stations list
- Pricing information per class
- Complete discount policy explanation
- Distance matrix between all stations
- Class descriptions and benefits

**Discount System:**
- GreenCard: 20% discount
- Over 75: FREE travel
- Clear policy communication to users

**Professional Design Patterns:**
- **Separation of Concerns**: Each class has single responsibility
- **Service Layer**: Business logic separated from presentation
- **Entity Objects**: Data models as dedicated classes
- **Value Objects**: Station as immutable-style validated object
- **Template Method**: External templates for UI elements
- **Menu-Driven Architecture**: User-friendly navigation
- **Input Validation**: Multi-level validation (Station class, Console methods)

**Code Quality:**
- **Extensive Documentation**: Detailed JavaDoc comments explaining design decisions
- **Constants Usage**: Eliminates magic values
- **Error Handling**: Graceful handling of invalid inputs
- **User Confirmation**: Purchase confirmation before completion
- **Modular Design**: Easy to extend with new features

**Advanced Concepts:**
- Object-oriented design principles (SOLID)
- Design patterns (Value Object, Service Layer)
- Architecture planning and organization
- Code documentation best practices
- User experience design
- Defensive programming techniques

Located in: `02_Tickets_Transportation/MIlanoLeccoTrains4/src/com/generation/trains/service/`

### Modulo 6 Project

Object-oriented programming fundamentals with entity class design and implementation.

**Main Programs:** `Main.java` and `MainHouse.java`

**Entity Classes:**

**1. Person Class:**
- **Attributes**:
  - `name` - Person's first name
  - `surname` - Person's last name
  - `dateOfBirth` - Birth date in ISO format (YYYY-MM-DD)
  - `gender` - Gender identification
- **Constructor**:
  - Default constructor initializing all fields to empty strings
  - Demonstrates proper object initialization
- **Methods**:
  - `toString()` - Returns formatted person information
  - String concatenation for display

**2. House Class:**
- **Attributes**:
  - `address` - Property address
  - `area` - Property area in square meters (MQ)
  - `spm` - Price per square meter (soldi per metro)
- **Constructor**:
  - Default constructor initializing strings to empty and numbers to 0
- **Business Methods**:
  - `getPrice()` - Calculates total property price (area × spm)
  - Returns calculated value, not stored value
- **Display Method**:
  - `toString()` - Returns formatted house information with calculated price
  - Demonstrates combining attributes with computed values

**Main Programs:**

**Main.java - Person Demo:**
- Creates two Person objects (p1, p2)
- Direct attribute assignment (public fields)
- Demonstrates object instantiation with `new` keyword
- Shows multiple independent objects from same class
- Uses `toString()` for formatted output

**MainHouse.java - House Demo:**
- Creates House object
- Sets property details (address, area, price per sqm)
- Demonstrates method call for price calculation
- Shows `toString()` with computed values

**Key Concepts Demonstrated:**
- **Class Definition**: Blueprint for objects with attributes and methods
- **Object Instantiation**: Creating instances with `new` keyword
- **Constructors**: Initializing object state
- **Encapsulation Basics**: Grouping data and methods
- **toString() Override**: Custom string representation
- **Business Logic Methods**: Methods that compute values (getPrice)
- **Multiple Objects**: Creating and managing multiple instances
- **Direct Field Access**: Public fields without getters/setters (basic approach)

**OOP Principles Introduced:**
- Class as template, object as instance
- State (attributes) and behavior (methods)
- Constructor role in object creation
- Method return values
- Object-to-string conversion
- Calculated vs stored properties

Located in: `01_Fundamentals_Examples/Modulo 6/src/com/generation/modulo6/main/`

### Modulo7Incapsulamento Project

Object-oriented programming with focus on **encapsulation** - one of the four pillars of OOP.

**Main Programs:** `Main.java` and `Person.java` entity class

**Encapsulation Concepts Demonstrated:**

**Person Class - Proper Encapsulation:**
- **Private Attributes**:
  - `private String name` - Protected first name
  - `private String surname` - Protected last name
  - `private String dateOfBirth` - Protected birth date
  - `private String gender` - Protected gender
- **Constructors**:
  - No-argument constructor (initializes with empty strings)
  - Parameterized constructor (accepts all fields)
- **Getter Methods with Null-Safety**:
  - `getName()`, `getSurname()`, `getDateOfBirth()`, `getGender()`
  - All getters return "UNKNOWN" instead of null for safe handling
  - Example: `return name == null ? "UNKNOWN" : name;`
- **Setter Methods with Validation**:
  - `setName()`, `setSurname()`, `setDateOfBirth()`, `setGender()`
  - All setters handle null values defensively
  - Example: `this.name = name == null ? "UNKNOWN" : name;`
- **toString() Method**:
  - Returns formatted string representation
  - Demonstrates custom object display

**Main.java - Demonstration:**
Creates four Person objects:
```java
Person p1 = new Person("Gabriela", "Hacman", "21-10-1998", "F");
Person p2 = new Person("Valerio", "Corallini", "02-23-1999", "M");
Person p3 = new Person("Pippo", "Disney", null, "M");
Person p4 = new Person("Pluto", "Disney", null, "M");
```
- Shows proper object instantiation
- Demonstrates null handling (p3 and p4 have null dateOfBirth)
- Displays all objects using Console.print()

**Key Principles:**
- **Data Hiding**: Private fields prevent direct external access
- **Controlled Access**: Public getters/setters control data flow
- **Defensive Programming**: Null-safety checks prevent NullPointerException
- **Encapsulation Benefits**: Internal representation can change without affecting external code

**Advanced Concepts:**
- Ternary operators for concise null checks
- Constructor overloading (multiple constructors)
- Method chaining potential
- Object state management

Located in: `01_Fundamentals_Examples/Modulo7Incapsulamento/src/com/generation/lessons/`

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

Located in: `01_Fundamentals_Examples/Modulo4/src/com/generation/exercises/`

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

Located in: `02_Tickets_Transportation/MonzaMetro/src/com/generation/`

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

Located in: `01_Fundamentals_Examples/Recap/src/com/generation/lessons/`

### ChristmasTime Project

Christmas-themed programming exercises focusing on loops, arrays, and game logic.

**Programs:**

**PresentListV1-V5.java** - Evolution of a Christmas present list manager:
- Version iterations demonstrating progressive complexity
- V5 Features:
  - Input number of presents to buy
  - For-loop iteration for data collection
  - Present name and cost tracking
  - Automatic total calculation
  - Formatted summary output
- Demonstrates loop structures and string concatenation

**SetteEMezzo.java** - Italian card game "Sette e Mezzo" (7.5):
- Card game simulation with rules:
  - Cards 1-7: face value points
  - Cards 8-10 (Donna, Cavallo, Re): 0.5 points each
  - Goal: Get as close to 7.5 without going over
- Features:
  - Random card generation using `Math.random()`
  - Do-while loop for continuous play
  - Ternary operator for card value calculation
  - Score tracking and bust detection
  - User interaction for "hit or stand" decisions

**RegaloNonno.java** - Grandpa's Gift Calculator:
- Gift budget calculator

**PrintBetweenAandB.java** - Number Range Printer:
- Prints all numbers between two values A and B

**PrintDueADueFraAeB.java** - Pair Range Printer:
- Prints pairs of consecutive numbers between A and B

Located in: `04_Exercises_Practice/ChristmasTime/src/com/generation/xmas/`

### SBHotel Project

Comprehensive hotel management system with booking, cancellation, and cleaning service functionalities.

**Main Program:** `RoomManagement.java`

**Core Features:**

**1. Room Booking System** (`bookRoom()` method):
- Guest Management:
  - Support for 1-4 guests per booking
  - Full name collection for each guest
  - Guest list generation for confirmation
- Stay Duration:
  - 1-7 nights booking range
  - Per-night pricing calculation
- Room Selection:
  - 4 available rooms with different pricing tiers:
    - Room 1: €100/night
    - Room 2: €80/night
    - Room 3: €70/night
    - Room 4: €220/night (premium)
- Extra Services:
  - Shuttle service option (€20 per person, one-time)
  - Service cost automatically calculated and added to total
- Booking Confirmation:
  - Unique booking code generation
  - HTML booking document with complete details
  - Automatic total cost calculation (room + services)
  - Current date stamping using `LocalDate.now()`
  - File saved as `print/[code].html`

**2. Cancellation System** (`cancelBooking()` method):
- Refund Policy:
  - Maximum refund: 2 nights only
  - Calculation based on original room rate
  - Partial refund for longer stays
- Cancellation Voucher:
  - HTML document generation
  - Shows: original booking, nights booked, nights refunded
  - Refund amount calculation
  - File saved as `print/CANCEL_[code].html`

**3. Cleaning Service Scheduler** (`RoomCleaningOrder.java`):
- Date Validation System:
  - Separate validation for day (1-31), month (1-12), year (current or future)
  - Nested do-while loops for input validation
  - Month-specific day limits using switch statement:
    - 31 days: Jan, Mar, May, Jul, Aug, Oct, Dec
    - 30 days: Apr, Jun, Sep, Nov
    - February: Leap year calculation with nested ternary operators
- Date Logic:
  - Prevents past date selection using `LocalDate.isBefore()`
  - Validates day-month-year combination
  - Ensures cleaning date is present or future
- Time Management:
  - Cleaning hour selection (13:00-21:00 only)
  - Input validation with do-while loop
- Staff Assignment:
  - Cleaner name input with empty string validation
  - Mandatory field enforcement
- Document Generation:
  - Date formatting with `DateTimeFormatter` ("dd/MM/yyyy" pattern)
  - HTML cleaning order with all details
  - Dynamic filename: `print/CLEAN_Room[X]_[date].html`

**4. Menu System:**
- Interactive menu with 5 options:
  1. Book a room
  2. Cancel booking
  3. View room availability
  4. Order cleaning service
  5. Exit
- Template-based display loading from `assets/` directory
- Switch statement for command routing
- Do-while loop for continuous operation until exit

**Template System:**
- Asset files in `assets/` directory:
  - `logo.txt` - Hotel branding displayed at startup
  - `menu.txt` - Main menu options
  - `rooms.txt` - Room availability list
- HTML templates in `assets/template/`:
  - `booking.html` - Booking confirmation format
  - `cancellationTemplate.html` - Refund voucher format
  - `cleaningTemplate.html` - Cleaning order format
- Dynamic placeholder replacement using `.replace()` method

**Advanced Concepts Demonstrated:**
- Method decomposition and organization
- Input validation with multiple strategies
- Complex date handling and leap year calculation
- Nested ternary operators for conditional logic
- Template-based document generation
- File I/O operations
- Menu-driven architecture
- Business rule implementation (refund limits, pricing tiers)

Located in: `03_Business_Applications/SBHotel/src/com/generation/sbh/main/`

### JavItaAirline Project

Airline ticket management system with service classes, membership tiers, and comprehensive unit testing.

**Main Program:** `Main.java` with demo and test components

**Architecture:**

**1. Entity Classes:**

**AirlineTicket.java** - Core business entity:
- **Attributes**:
  - `String id` - Unique ticket identifier
  - `int km` - Flight distance in kilometers
  - `ServiceClass serviceClass` - Service tier (BASIC/SILVER/GOLD)
  - `MembershipType membershipType` - Customer membership level
  - `LocalDate date` - Flight date
  - `LocalTime start` - Departure time
  - `LocalTime end` - Arrival time
- **Business Methods**:
  - `getPrice()` - Calculates ticket price with membership discount
    - Formula: `basePrice = km × serviceClass.pricePerKm`
    - Discount: `discountAmount = basePrice × membershipType.discount`
    - Final: `basePrice - discountAmount`
  - `getDuration()` - Returns flight duration (hours and minutes)
  - `isValid()` - Validates all ticket data (dates, times, distance, ID)

**ServiceClass Enum** - Three service tiers:
- `BASIC` - €0.10 per km (economy)
- `SILVER` - €0.20 per km (business)
- `GOLD` - €0.50 per km (first class)

**MembershipType Enum** - Customer loyalty levels:
- `NONE` - 0% discount
- `SILVER` - 20% discount
- `GOLD` - 30% discount

**2. Demo Programs:**

**JavaitaAirplaneDemo.java** - Interactive ticket creation:
- Prompts for all ticket details
- Uses Console utility for validated input
- Demonstrates LocalDate and LocalTime usage
- Shows real-time price calculation
- Example flow: ID → distance → service class → membership → dates/times

**3. Unit Testing (JUnit Jupiter):**

**JavaitaTest.java** - Three comprehensive tests:
- `testGetPrice()` - Verifies pricing calculation (100 km BASIC = €10.00)
- `testGetDuration()` - Tests duration calculation (10:00 to 15:00 = 5 hours)
- `testIsValid()` - Validates complete ticket data

**4. Pricing System:**

Dynamic pricing formula:
```
Base Price = Distance (km) × Service Class Rate
Final Price = Base Price × (1 - Membership Discount)
```

**Examples:**
- 100 km, BASIC, no membership: 100 × 0.1 × 1.0 = €10.00
- 100 km, SILVER, SILVER membership: 100 × 0.2 × 0.8 = €16.00
- 100 km, GOLD, GOLD membership: 100 × 0.5 × 0.7 = €35.00

**Key Features:**
- **Three-Tier Service**: Economy, business, and first-class options
- **Loyalty Program**: Up to 30% discount for GOLD members
- **Time Management**: Modern Java 8+ LocalDate/LocalTime API
- **Comprehensive Validation**: Multi-field validation before ticket creation
- **Unit Testing**: JUnit-based test coverage for critical methods
- **Enum Type Safety**: Prevents invalid service/membership values
- **Duration Calculation**: Automatic flight duration computation

**Advanced Concepts:**
- Enum with attributes (pricePerKm, discount)
- Modern date/time API (java.time package)
- JUnit 5 testing framework
- Business logic in entity classes
- Calculated properties vs stored values
- Type-safe constants using enums

Located in: `03_Business_Applications/JavItaAirline/src/com/generation/jia/`

### LeccoB&B Project

Bed & Breakfast booking management system with room types, membership discounts, and automated document generation.

**Main Program:** `Main.java` with menu-driven interface

**Architecture:**

**1. Entity Classes:**

**Booking.java** - Core booking entity:
- **Attributes**:
  - `String id` - Unique booking identifier
  - `String firstName`, `lastName` - Guest information
  - `LocalDate checkIn`, `checkOut` - Stay dates
  - `RoomType roomType` - Room category selection
  - `MembershipType membershipType` - Membership level
  - `LocalDate dob` - Guest date of birth (for age verification)
- **Business Methods**:
  - `getNights()` - Calculates stay duration using `ChronoUnit.DAYS`
  - `getBasicPrice()` - Base cost: nights × room rate
  - `getDiscount()` - Discount amount based on membership
  - `getFinalPrice()` - Base price minus discount
  - `isValid()` - Comprehensive validation including 18+ age check

**RoomType Enum** - Four room categories:
- `BASIC` - €50/night (budget)
- `MIDDLE` - €70/night (standard)
- `SUPERIOR` - €100/night (deluxe)
- `SUITE` - €200/night (luxury)

**MembershipType Enum** - Two loyalty tiers:
- `NONE` - 0% discount
- `SILVER` - 10% discount
- `GOLD` - 20% discount

**2. View Layer:**

**BookingView.java** - Template-based rendering:
- `render()` - Loads template and replaces placeholders
- Supports multiple formats (HTML and TXT)
- Placeholders: [id], [firstName], [lastName], [roomType], [membershipType], [Check In], [Check Out], [nights], [price], [discount]

**3. Menu System:**

**Main.java** - Three main options:
1. **Insert Booking** - Creates new reservation with HTML generation
2. **View Preview** - Displays TXT preview of last booking
3. **Exit** - Gracefully exits

**4. Validation Rules:**

The `isValid()` method enforces:
- Non-zero booking ID
- Valid check-in and check-out dates
- Check-out must be in the future
- Stay duration: 1-30 nights
- Room type must be selected
- Non-blank first and last names
- Date of birth provided
- **Guest must be 18+ years old** (age verification using `ChronoUnit.YEARS`)

**5. Templates:**

**template.html** - Professional HTML confirmation:
- Modern purple gradient design
- Responsive layout (mobile-friendly)
- Sections: booking ID, guest info, stay details, price breakdown
- Print-optimized CSS
- Contact information footer

**template.txt** - ASCII art formatted confirmation:
- Box-drawing borders
- Console-friendly preview
- Same information structure as HTML

**6. Pricing Formula:**

```
Basic Price = Nights × Room Cost/Night
Discount = Basic Price × Membership Discount %
Final Price = Basic Price - Discount
```

**7. Demo Programs:**

**DemoBooking.java** - Standalone booking demonstration:
- Sample: 2025-12-20 to 2025-12-23 (3 nights)
- SUPERIOR room: €100/night = €300 base
- GOLD membership: 20% discount (€60)
- Final price: €240

**LocalDateDemo.java** - Date API educational program:
- LocalDate creation and manipulation
- Date arithmetic with ChronoUnit
- Age calculation examples
- Days/years between dates

**Key Features:**
- **Four Room Types**: Budget to luxury accommodation options
- **Membership Discounts**: SILVER 10%, GOLD 20% savings
- **Age Verification**: Enforces 18+ requirement for bookings
- **Duration Limits**: 1-30 night stay restrictions
- **Dual Output**: HTML file + text preview
- **Date Validation**: Future dates only, proper date handling
- **Professional Templates**: Modern, print-ready booking confirmations
- **Comprehensive Validation**: Multi-level data integrity checks

**Advanced Concepts:**
- Modern Java 8+ date/time API (LocalDate, ChronoUnit)
- Template-based rendering system
- Business rule validation (age, duration limits)
- Enum with attributes for type-safe configuration
- File generation with dynamic naming (booking_{id}.html)
- ASCII art for console UI
- Separation of concerns (Model-View pattern)

Located in: `03_Business_Applications/LeccoB&B/src/com/generation/lbb/`

### JavaBank Project

Advanced banking system with account management, SSN validation, precise financial calculations, and multi-client support.

**Main Program:** `Main.java` with dual menu system

**Architecture:**

This project demonstrates a professional banking application with service-oriented architecture and financial data handling best practices.

**1. Entity Classes:**

**BankAccount.java** - Core banking entity:
- **Attributes**:
  - `long id` - Unique account identifier
  - `BigDecimal balance` - Account balance (using BigDecimal for precise monetary calculations)
  - `Client client` - Associated client object
- **Financial Operations**:
  - `deposit(int euro, int cents)` - Adds funds to account with euro and cent precision
  - `withdrawl(int euro, int cents)` - Subtracts funds from account
  - `setBalance(String balanceValue)` - Sets balance from string representation
  - Uses BigDecimal to avoid floating-point rounding errors in financial calculations
- **Key Concept**: NEVER use `double` for financial data - BigDecimal ensures exact decimal arithmetic

**Client.java** - Customer information entity:
- **Attributes**:
  - `String name` - Client's first name
  - `String surname` - Client's last name
  - `String ssn` - Social Security Number (Italian Codice Fiscale format)
  - `LocalDate dob` - Date of birth
- **Validation Methods**:
  - `setName(String)` - Returns boolean, validates non-null/non-blank
  - `setSurname(String)` - Returns boolean, validates non-null/non-blank
  - Input sanitization with `.trim()` to remove whitespace
- **Date Handling**:
  - `setDob(int day, int month, int year)` - Set date from components
  - `setDob(String date)` - Parse ISO-8601 date string
  - `getDob()` - Returns formatted date based on configured country
- **Internationalization**: Date format adapts to country settings (Config.COUNTRY)

**Country.java** - Enum for international date formats:
- `ITALY` - dd/MM/yyyy format
- `USA` - MM/dd/yyyy format
- `UK` - dd/MM/yyyy format
- `FRANCE` - dd/MM/yyyy format
- `GERMANY` - dd.MM.yyyy format
- `UNIVERSAL` - yyyy-MM-dd (ISO-8601)
- Each enum value stores its dateFormat string

**Config.java** - Application configuration settings:
- Static configuration for country-specific behavior
- Centralizes application settings

**2. Service Layer:**

**AccountService.java** - Multi-client account management:
- Manages multiple bank accounts (ArrayList or similar)
- `addAccount(BankAccount)` - Registers new account
- `getAllAccounts()` - Returns list of all accounts
- `getCurrentAccount()` - Returns currently selected account
- `getAccountCount()` - Returns total number of registered clients
- `isEmpty()` - Checks if any accounts exist
- Service pattern for centralized account operations

**SsnService.java** - Italian SSN (Codice Fiscale) validation:
- **Validation Rules** (16-character format: RSSMRA85T10A562S):
  - `checkLength(String)` - Must be exactly 16 characters
  - `checkAlphaNum(String)` - Only letters and digits allowed
  - `checkAlpha(String)` - Letters at positions: 0-5, 8, 11, 15
  - `checkDigits(String)` - Digits at positions: 6-7, 9-10, 12-14
- **Main Method**:
  - `validateSSN(String)` - Combines all validation checks
  - Case-insensitive (converts to uppercase)
  - Trims whitespace before validation
- **Italian Codice Fiscale Format**:
  - Positions 0-5: Surname and name letters
  - Positions 6-7: Birth year (last 2 digits)
  - Position 8: Birth month letter
  - Positions 9-10: Birth day
  - Position 11: Birth place letter
  - Positions 12-14: Municipality code
  - Position 15: Check character

**UserInputService.java** - Centralized input handling:
- `requestId()` - Prompts for account ID
- `requestName()` - Prompts for first name with validation
- `requestSurname()` - Prompts for last name with validation
- `requestDateOfBirth()` - Prompts for date with format validation
- `requestValidSSN()` - Prompts for SSN with continuous validation loop
- `requestInitialBalance()` - Prompts for starting account balance
- `requestAmount(String type)` - Prompts for transaction amount (returns [euro, cents])
- Centralizes all user input logic for consistency

**3. View Layer:**

**BankView.java** - Template-based rendering:
- `showMainMenu()` - Displays main menu and returns choice
- `showInternalMenu()` - Displays operations menu and returns choice
- `render(BankAccount)` - Generates account statement from template
- Supports dual formats (TXT for console, HTML for file)
- Template placeholders: [id], [name], [surname], [ssn], [dob], [balance]

**4. Menu System:**

**Main Menu** (`menu.txt`):
1. **Insert New Client** - Register new bank account
2. **Display Client** - View account details and access operations menu
3. **Make Deposit** - Add funds to account
4. **Make Withdrawal** - Remove funds from account
5. **Exit** - Close application

**Internal Menu** (`internalMenu.txt`) - Accessed after viewing client:
1. **Make Deposit** - Add funds
2. **Make Withdrawal** - Remove funds
3. **Show Balance** - View current balance
4. **Return to Main Menu** - Go back

**5. Key Features:**

**Financial Precision:**
- **BigDecimal Usage**: Ensures exact decimal arithmetic for money
- **Euro and Cents Separation**: Handles currency in two integer parts
- **No Floating-Point Errors**: Avoids 0.1 + 0.2 = 0.30000000000000004 problem

**SSN Validation:**
- Complete Italian Codice Fiscale format validation
- Multi-step validation (length, characters, positions)
- Real-world document verification logic

**Multi-Client Support:**
- AccountService manages multiple accounts
- Client selection and switching capability
- Account count tracking

**International Date Formatting:**
- Country-specific date display formats
- Enum-based configuration
- Supports 6 different regions

**Input Validation:**
- Return-value validation (boolean returns from setters)
- Continuous validation loops until valid input
- Input sanitization (trim, uppercase)

**Dual Output System:**
- Console text preview
- HTML file generation for printing
- Template-based rendering for consistency

**6. Workflow:**

1. User launches application
2. Main menu displays 5 options
3. User selects "Insert New Client"
4. System prompts for: ID, name, surname, date of birth, SSN, initial balance
5. SSN validation occurs in real-time with specific error messages
6. Client successfully registered
7. User selects "Display Client"
8. Console shows formatted account details
9. System offers HTML save option
10. Internal menu appears with transaction options
11. User can deposit/withdraw/view balance
12. BigDecimal ensures precise calculations
13. Return to main menu or exit

**7. Advanced Concepts Demonstrated:**

- **BigDecimal for Financial Data**: Industry best practice for monetary calculations
- **Service Layer Pattern**: Separation of business logic from presentation
- **Input Validation Service**: Centralized input handling
- **Enum with Attributes**: Type-safe configuration with associated data
- **SSN Validation Algorithm**: Complex multi-step validation logic
- **Template-Based Views**: Reusable presentation layer
- **Multi-Entity Architecture**: BankAccount contains Client (composition)
- **Return-Value Validation**: Boolean returns indicate success/failure
- **Internationalization Support**: Country-specific date formatting
- **Menu-Driven Architecture**: Nested menu system with state management
- **Character-Level Validation**: charAt() with Character class methods
- **String Manipulation**: trim(), toUpperCase(), isBlank()
- **Loop-Based Validation**: Do-while loops ensuring valid input
- **Modern Date API**: LocalDate for date handling
- **Defensive Programming**: Null checks and input sanitization

**8. TODO Implementation Notes:**

The project includes detailed TODO comments for implementing AccountService throughout Main.java:
- Converting single-account to multi-account architecture
- Updating all methods to use AccountService instead of static account variable
- Adding client selection functionality
- Supporting multiple simultaneous clients

Located in: `03_Business_Applications/JavaBank/src/com/generation/ba/`

### RepairShop Project

Repair shop management system with pricing calculation, labor tracking, and automated HTML invoice generation.

**Main Program:** `Main.java` with menu-driven interface

**Architecture:**

**1. Entity Model:**

**Repair.java** - Core repair job entity:
- **Attributes**:
  - `String id` - Unique repair identifier
  - `String client` - Customer name
  - `String phone` - Contact phone number
  - `String fix` - Problem description/repair needed
  - `double materialPartsCost` - Parts and materials cost (euros)
  - `double hour` - Labor hours required
  - `double price` - Final negotiated price
  - `String totalRepairsDone` - Cumulative repair count
- **Business Methods**:
  - `getEstimatedPrice()` - Calculates estimate: materials + (hours × €50)
  - `isValid()` - Validates all required fields and positive values

**2. View Layer:**

**RepairView.java** - Template-based rendering:
- `render(Repair repair)` - Loads template and replaces placeholders
- Supports multiple formats (text and HTML)
- Placeholders: [id], [client], [phone], [fix], [materialPartsCost], [hour], [exprice] (estimated), [price] (final)

**3. Menu System:**

**Main.java** - Three main options:
1. **Add New Repair** - Creates repair with preview and HTML generation
2. **View Repair List** - Shows repair info and total count
3. **Exit** - Closes application

**4. Key Methods:**

**askRepair(Repair r)** - Interactive repair creation:
- Prompts for: ID, client name, phone, problem description, material costs, labor hours
- Validates all inputs
- If valid: generates text preview and HTML file

**printRepairList(Repair r)** - Displays repair information and statistics

**printPreviewTXT(Repair r)** - Console preview using repairPreView.txt template

**printClientViewHTML(Repair r)** - Generates HTML invoice file (repair_{ID}.html)

**5. Templates:**

**menu.txt** - Main menu display:
```
=== SISTEMA GESTIONE RIPARAZIONI ===
1. Aggiungi nuova riparazione
2. Lista Riparazioni effettuate
3. Esci
```

**repairPreView.txt** - Text preview format:
```
CLIENTE: [client]
ID: [id]
TELEFONO: [phone]
PROBLEMA: [fix]
Ore lavoro: [hour] ore
Prezzo stimato: €[exprice]
```

**clientView.html** - Professional HTML invoice:
- Clean Arial font styling
- Repair details section
- Cost breakdown
- Client-facing professional format

**6. Pricing System:**

**Hourly Labor Rate**: Fixed at **€50/hour**

**Pricing Formula**:
```
Estimated Price = Material Cost + (Hours × €50)
Final Price = Negotiated (can differ from estimated)
```

**Example**:
- Materials: €300
- Labor: 2 hours
- Estimated: €300 + (2 × €50) = €400
- Final: €350 (negotiated)

**7. Workflow:**

1. User selects "Add new repair"
2. System prompts for all repair details
3. System validates data (hour >= 1, cost >= 0, non-empty fields)
4. If valid:
   - Text preview displayed in console
   - User prompted for final negotiated price
   - HTML invoice generated: repair_{ID}.html
   - Confirmation message shown
5. User can view repair list or exit

**8. Validation Rules:**

The `isValid()` method checks:
- Client name, phone, and fix description are non-empty
- Material costs >= 0
- Labor hours >= 1

**9. Demo Programs:**

**DemoRepair.java** - Pricing demonstration:
- Shows €400 estimated vs €350 final price
- Demonstrates price negotiation concept

**DemoRepairView.java** - Template rendering demo:
- Shows text preview generation
- Demonstrates HTML output file creation

**10. Testing:**

**RepairTest.java** - JUnit test class:
- `getEstimatedPrice()` - Tests €400 total (€300 materials + 2 hours)
- `isValid()` - Validates with/without phone number

**Key Features:**
- **Fixed Hourly Rate**: €50/hour standard labor charge
- **Material Tracking**: Separate parts cost calculation
- **Price Negotiation**: Estimated vs final price flexibility
- **Dual Output**: Console preview + HTML invoice
- **Template System**: Flexible content generation
- **File Generation**: Automatic invoice creation (repair_{ID}.html)
- **Input Validation**: Comprehensive data integrity checks
- **Menu Navigation**: User-friendly interface
- **Testing Coverage**: JUnit tests for critical methods

**Advanced Concepts:**
- MVC architecture (Model-View-Controller)
- Template-based document generation
- Business logic in entity classes (getEstimatedPrice)
- Separation of concerns (entity, view, controller)
- File I/O operations
- Input validation patterns
- Menu-driven application design

Located in: `03_Business_Applications/RepairShop/src/com/generation/rs/`

### While Project

Utility project with shared library classes for loop-based exercises.

Located in: `01_Fundamentals_Examples/While/src/com/generation/library/`

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

Located in: `03_Business_Applications/PrintLabel/src/com/generation/library/food/PrintFoodLabel.java`

### PrintHousingLabel
Generates HTML real estate property labels:
- Input: city, address, image URL, room/bathroom/balcony dimensions, price per square meter
- Calculates:
  - Individual area for each space (room, bathroom, balcony)
  - Total internal area (room + bathroom)
  - Total property price (balcony counted at 50% value)
- Uses HTML template system to generate formatted output
- Saves result as HTML file in `print/` directory

Located in: `03_Business_Applications/PrintLabel/src/com/generation/library/housing/PrintHousingLabel.java`

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
- Switch statements with multiple cases
- Ternary operators (simple and nested)
- Boolean logic and comparisons
- Loop structures:
  - Do-while loops with user-controlled exit
  - For loops with counters and iteration
  - While loops for conditional repetition
  - Nested loops for complex validation
  - Loop increment patterns (i++, i+=2)
- Menu-driven program design with switch statements

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
  - Null-safety in getters/setters
  - Defensive programming techniques
  - Data hiding and controlled access
- Constructors (default and parameterized)
- Object methods and behavior
- Service layer architecture
- Separation of concerns
- MVC (Model-View-Controller) design pattern
- Template-based view rendering
- Static constants and class-level attributes
- Enum types with attributes (type-safe constants)
- Business logic in entity classes
- Calculated properties vs stored values

### Advanced Topics
- Date and time handling (LocalDate, LocalDateTime, LocalTime)
- Date formatting with DateTimeFormatter and custom patterns
- Leap year calculation with nested ternary operators
- Date validation and comparison (isBefore, isAfter)
- Duration calculation with ChronoUnit (DAYS, YEARS)
- Age verification and calculation
- Type casting (char to int conversions, explicit casting for averages)
- Complex business logic implementation
- Multi-criteria scoring algorithms
- User management systems
- VIP/loyalty programs
- Membership discount systems (tiered pricing)
- Data persistence and statistics collection
- Modular architecture design
- Caesar cipher cryptography
- Game logic implementation (card games, scoring systems)
- Random number generation with Math.random()
- JUnit testing framework (JUnit Jupiter)
- Unit test design and implementation
- Test-driven development concepts
- **Financial Data Handling**:
  - **BigDecimal for monetary calculations** (avoiding floating-point errors)
  - Precise decimal arithmetic for banking operations
  - Euro and cents separation in currency handling
  - Industry best practices for financial applications
- **Advanced String Validation**:
  - Character-level validation with charAt()
  - Position-specific validation (SSN/Codice Fiscale format)
  - Multi-step validation algorithms
  - Character class methods (isLetter, isDigit, isLetterOrDigit)
- **Internationalization (i18n)**:
  - Country-specific date formatting
  - Enum-based configuration for regional settings
  - Locale-aware data presentation
- **Service Layer Architecture**:
  - Centralized business logic in service classes
  - Input validation services
  - Document validation services (SSN)
  - Account management services
- Mathematical operations:
  - Maximum value calculation
  - Average calculation with proper casting
  - Sum accumulation
  - Modulo operator for even/odd detection
  - Counter and accumulator patterns
  - Price calculation with discounts
  - Labor cost estimation (hourly rates)

### Software Engineering Practices
- Code organization and structure
- Reusable utility classes
- Input validation with multiple strategies:
  - Range validation (min-max checks)
  - Empty string validation
  - Date validation (day-month-year combinations)
  - Business rule validation (refund limits, booking constraints)
  - **SSN/Document validation** (Italian Codice Fiscale format)
  - **Return-value validation** (boolean returns indicating success/failure)
  - **Character position validation** (specific format enforcement)
- Error handling and edge cases
- User experience design
- Formatted output and reporting
- Template-driven document generation
- Method decomposition and single responsibility principle
- **Financial application best practices** (BigDecimal for money, no double/float)

## Author
Generation Study Course Student - Hacman Viorica Gabriela

## License

Educational use only
