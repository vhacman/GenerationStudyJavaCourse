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
└── PrintLabel/
    ├── src/
    │   └── com/
    │       └── generation/
    │           └── library/
    │               ├── food/      # Food label generator
    │               ├── housing/   # Housing label generator
    │               ├── Console.java
    │               ├── FileReader.java
    │               ├── FileWriter.java
    │               └── Template.java
    └── print/                     # Output directory and HTML templates
        ├── template.html          # Food label template
        └── templateHousing.html   # Housing label template
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

### Key Features of PrintLabel Project
- **File I/O Operations**: Reading templates and writing output files
- **Template System**: Load HTML templates and dynamically replace placeholders
- **String Manipulation**: Using `.replace()` method for template processing
- **Organized Output**: All generated labels saved in dedicated `print/` directory
- **Reusable Components**: `Console`, `FileReader`, `FileWriter`, and `Template` utility classes

## Learning Topics

This repository covers fundamental Java concepts including:
- Variables and data types
- Console input/output operations
- File I/O operations (reading from and writing to files)
- Arithmetic operations
- String manipulation and template processing
- Package organization
- Basic program structure
- HTML template system and dynamic content generation
- Exception handling (try-catch blocks)

## Author
Generation Study Course Student - Hacman Viorica Gabriela

## License

Educational use only
