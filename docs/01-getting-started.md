# Getting Started

This guide will help you set up and run the Java projects in this repository.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE (Integrated Development Environment) such as:
  - Eclipse
  - IntelliJ IDEA
  - VS Code with Java extensions

## How to Run

### Using an IDE

1. Import the project into your IDE
2. Navigate to the desired Java file
3. Right-click and select "Run As" → "Java Application"

### Project Organization

Each project follows a standard Maven/Gradle-like structure:

```
ProjectName/
├── src/
│   └── com/
│       └── generation/
│           ├── main/        # Main entry points
│           ├── model/       # Entity classes
│           ├── view/        # View layer
│           ├── service/     # Business logic
│           └── library/     # Utility classes
├── template/                # Text and HTML templates
├── print/                   # Generated output files
└── assets/                  # Additional resources
```

## Common Library Classes

Most projects use shared utility classes from `com.generation.library`:

- **Console.java** - Console input/output helper
  - `readInt()` - Read integer from user
  - `readString()` - Read string from user
  - `readIntBetween(min, max)` - Read integer within range
  - `print()` - Print to console

- **FileReader.java** - Read files from disk
- **FileWriter.java** - Write files to disk
- **Template.java** - Load and process HTML/text templates

## Example: Running a Simple Program

1. Navigate to `01_Fundamentals_Examples/Examples/src/com/generation/demo/`
2. Open `HelloWorld.java`
3. Right-click → Run As → Java Application
4. View output in console

## Example: Running an Advanced Project

1. Navigate to `03_Business_Applications/JavaBank/`
2. Open `src/com/generation/ba/main/Main.java`
3. Right-click → Run As → Java Application
4. Follow the interactive menu system
5. Generated files will appear in the `print/` directory

## Troubleshooting

### Common Issues

**Issue:** "Cannot find symbol Console"
- **Solution:** Ensure the `library` package is in your classpath

**Issue:** Template files not found
- **Solution:** Check that `template/` and `print/` directories exist in the project root

**Issue:** File not saved
- **Solution:** Verify write permissions on the `print/` directory

## Next Steps

- Explore [Fundamentals](02-fundamentals/) to start with basic concepts
- Check the [Learning Path](05-learning-path.md) for a recommended progression
- Browse [Business Applications](03-business-applications/) for complete projects
