# Private Lessons Management System

A comprehensive Java-based platform for managing private lessons, teachers, students, and administrative operations with advanced reporting capabilities.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Database Schema](#database-schema)
- [Installation](#installation)
- [Usage](#usage)
- [User Roles](#user-roles)
- [Report System](#report-system)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

Private Lessons is a complete management system designed to streamline the organization of educational services. The platform manages three types of users (Students, Teachers, and Administrators) with dedicated interfaces and functionalities for each role.

### Key Highlights

- **Multi-role system** with distinct permissions and capabilities
- **Advanced reporting** with both TXT and HTML export formats
- **Template-based views** using reflection for dynamic rendering
- **Secure authentication** with password hashing (MD5) and expiration policies
- **SQLite database** with complete CRUD operations
- **Earnings tracking** with customizable date ranges
- **Subject-based teacher search** for students

## ✨ Features

### For Students
- 🔍 Search teachers by subject
- 📅 Book lessons with preferred teachers
- 📊 View personal lesson history
- 💰 Track total expenses
- 🧾 Generate lesson receipts (HTML)
- 📁 Export data in TXT/HTML formats

### For Teachers
- 📋 View all lessons (past and upcoming)
- 📆 Check next week's schedule
- 💵 Calculate earnings by custom date range
- 📈 Generate earnings reports (TXT/HTML)
- 👥 Manage lesson bookings

### For Administrators
- 👤 Create and manage teachers, students, and admins
- 🔄 Change user status (ACTIVE, INACTIVE, BANNED)
- 📊 View teacher earnings (last 30 days)
- 📚 Track earnings by subject
- 👥 View complete user lists
- 🔐 Enforce password change policies (2-week expiration)

## 🏗️ System Architecture

### Design Patterns

- **MVC Pattern**: Clear separation between Model, View, and Controller
- **Repository Pattern**: Abstraction layer for data access
- **Factory Pattern**: ViewFactory for centralized template management
- **Dependency Injection**: Context-based dependency management
- **Reflection API**: Dynamic entity rendering in views

### Core Components

```
┌─────────────────┐
│   Controllers   │  ← User interaction logic
└────────┬────────┘
         │
┌────────▼────────┐
│      Model      │  ← Business logic & entities
│   (Entities)    │
└────────┬────────┘
         │
┌────────▼────────┐
│  Repositories   │  ← Data access layer
│   (SQL/Demo)    │
└────────┬────────┘
         │
┌────────▼────────┐
│    Database     │  ← SQLite persistence
│   (pl.db)       │
└─────────────────┘
```

## 🗄️ Database Schema

### Main Tables

**users**
- Common fields for all user types (id, firstName, lastName, email, password, ssn, status)

**teachers**
- Extends users with: bio, pricePerLesson, subjectsCSV

**students**
- Extends users (inherits all fields from users table)

**admins**
- Extends users with: dateLastPasswordChange

**lessons**
- id, teacherId, studentId, date, hour, price
- Foreign keys to teachers and students

### Subject Enum
Supported subjects: JAVA, PYTHON, JAVASCRIPT, SQL, HTML, CSS, REACT, ANGULAR, SPRING, NODEJS

## 📦 Installation

### Prerequisites

- Java JDK 11 or higher
- SQLite JDBC driver (included in `lib/`)
- Generation Library (custom framework)

### Setup Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/private-lessons.git
   cd private-lessons
   ```

2. **Verify dependencies**
   ```bash
   ls lib/
   # Should show: sqlite-jdbc-*.jar, generation-library.jar
   ```

3. **Compile the project**
   ```bash
   javac -cp ".:lib/*" -d bin src/com/generation/pl/**/*.java
   ```

4. **Initialize the database**
   - The database `pl.db` will be created automatically on first run
   - Or use the provided database file if available

5. **Run the application**
   ```bash
   java -cp ".:lib/*:bin" com.generation.pl.controller.Main
   ```

## 🚀 Usage

### First Time Setup

On first launch, if no administrators exist, you'll be prompted to create the first admin account:

```
=== FIRST ADMIN SETUP ===
No administrators found in the system.
Please create the first admin account.

Enter first name: John
Enter last name: Doe
Enter email: admin@privatelessons.com
Enter SSN: 123456789
Enter password: ********
Confirm password: ********

First admin created successfully!
You can now restart the application and login.
```

### Main Menu

```
╔════════════════════════════════════════╗
║     PRIVATE LESSONS MANAGEMENT         ║
╚════════════════════════════════════════╝

1. Login as STUDENT
2. Login as TEACHER
3. Login as ADMIN
0. Exit

Choose option:
```

### Example Workflow

**Student Booking a Lesson:**
1. Login as student
2. Search for teacher by subject (e.g., JAVA)
3. View available teachers with prices
4. Book a lesson by entering teacher ID, date, and hour
5. Generate receipt for the booked lesson

**Teacher Checking Earnings:**
1. Login as teacher
2. Select "Calculate Earnings"
3. Enter date range (e.g., 2025-01-01 to 2025-12-31)
4. View earnings summary
5. Reports saved automatically in `print/txt/teachers/` and `print/html/teachers/`

## 👥 User Roles

### Student
- **Primary Goal**: Find and book lessons with qualified teachers
- **Key Actions**: Search, book, view history, generate receipts
- **Reports**: Lesson history, expense tracking

### Teacher
- **Primary Goal**: Manage lesson schedule and track earnings
- **Key Actions**: View lessons, check schedule, calculate earnings
- **Reports**: Lesson history, next week schedule, earnings by period

### Administrator
- **Primary Goal**: Oversee platform operations and user management
- **Key Actions**: Create users, change status, view earnings analytics
- **Reports**: Teacher earnings, subject earnings, user lists
- **Security**: Password expiration (14 days), forced password changes

## 📊 Report System

All reports are automatically saved in both **TXT** and **HTML** formats for maximum flexibility.

### Output Directory Structure

```
print/
├── txt/
│   ├── admin/
│   │   ├── all_teachers.txt
│   │   ├── teacher_earnings_{id}.txt
│   │   └── subject_earnings_{subject}.txt
│   ├── teachers/
│   │   ├── history_{id}.txt
│   │   ├── nextweek_{id}.txt
│   │   └── earnings_{id}.txt
│   └── students/
│       ├── teachers_{subject}.txt
│       └── history_{id}.txt
├── html/
│   ├── admin/
│   │   ├── all_teachers.html
│   │   ├── teacher_earnings_{id}.html
│   │   └── subject_earnings_{subject}.html
│   ├── teachers/
│   │   ├── history_{id}.html
│   │   ├── nextweek_{id}.html
│   │   └── earnings_{id}.html
│   ├── students/
│   │   ├── teachers_{subject}.html
│   │   ├── history_{id}.html
│   │   └── student_lesson_history_{id}.html
│   └── lessons/
│       └── ricevuta_lesson_{id}.html
```

### Report Templates

Templates are stored in `template/` directory and use placeholder replacement:

- **TXT Templates**: Plain text with `[placeholder]` syntax
- **HTML Templates**: Styled HTML5 with modern CSS
- **Reflection-based rendering**: Automatic getter mapping to placeholders

## 🛠️ Technologies

### Core Technologies
- **Java 11+**: Main programming language
- **SQLite**: Embedded database
- **JDBC**: Database connectivity
- **Reflection API**: Dynamic template rendering

### Custom Framework
- **Generation Library**: Custom MVC framework
  - `Entity` base class for all models
  - `Template` engine for view rendering
  - `Console` utility for I/O operations
  - `Context` for dependency injection

### Security
- **MD5 Password Hashing**: Secure password storage
- **Password Expiration**: 14-day policy for admins
- **Session Management**: Login attempt limits (3 max)
- **Status-based Access**: ACTIVE, INACTIVE, BANNED user states

## 📁 Project Structure

```
PrivateLessons/
├── src/
│   └── com/generation/
│       ├── context/
│       │   └── Context.java
│       └── pl/
│           ├── controller/
│           │   ├── Main.java
│           │   ├── AdminMain.java
│           │   ├── TeacherMain.java
│           │   ├── StudentMain.java
│           │   └── utils/
│           │       ├── Collect.java
│           │       ├── FileExporter.java
│           │       └── InputValidator.java
│           ├── model/
│           │   ├── entities/
│           │   │   ├── User.java
│           │   │   ├── Admin.java
│           │   │   ├── Teacher.java
│           │   │   ├── Student.java
│           │   │   ├── Lesson.java
│           │   │   ├── Subject.java (enum)
│           │   │   └── UserStatus.java (enum)
│           │   └── repository/
│           │       ├── interfaces/
│           │       │   ├── AdminRepository.java
│           │       │   ├── TeacherRepository.java
│           │       │   ├── StudentRepository.java
│           │       │   └── LessonRepository.java
│           │       └── SQLRepository/
│           │           ├── AdminRepositorySQL.java
│           │           ├── TeacherRepositorySQL.java
│           │           ├── StudentRepositorySQL.java
│           │           └── LessonRepositorySQL.java
│           ├── view/
│           │   ├── ReflectionView.java
│           │   ├── ViewController.java
│           │   └── ViewFactory.java
│           └── security/
│               ├── PasswordHasher.java
│               └── MD5PasswordHasher.java
├── template/
│   ├── txt/
│   │   ├── menu/
│   │   ├── admin/
│   │   ├── teacher/
│   │   ├── student/
│   │   └── lessons/
│   └── html/
│       ├── admin/
│       ├── teacher/
│       ├── student/
│       └── lessons/
├── print/
│   ├── txt/
│   └── html/
├── lib/
│   ├── sqlite-jdbc-*.jar
│   └── generation-library.jar
├── pl.db
└── README.md
```

## 🔒 Security Features

### Password Security
- **MD5 Hashing**: All passwords stored as hashed values
- **Password Confirmation**: Double-entry verification on creation
- **Expiration Policy**: 14-day forced change for admins
- **Old Password Verification**: Required for password changes

### Access Control
- **Role-based Access**: Distinct menus and permissions per role
- **Status Management**: ACTIVE/INACTIVE/BANNED user states
- **Session Security**: Login attempt limits
- **Data Isolation**: Users can only access their own data

### Input Validation
- **Email Format**: Regex validation for email addresses
- **SSN Format**: 9-digit numeric validation
- **Date Format**: ISO 8601 (YYYY-MM-DD) validation
- **Subject Validation**: Enum-based subject verification

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style
- Follow Java naming conventions
- Use meaningful variable names
- Add JavaDoc comments for public methods
- Keep methods focused and concise

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Viorica Gabriela Hacman**

- GitHub: [@vhacman](https://github.com/vhacman)
- Project: Generation Italy Java Course - Business Applications Module

## 🙏 Acknowledgments

- Generation Italy for the Java development course
- Custom Generation Library framework
- SQLite team for the embedded database
- All contributors and testers

## 📞 Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Contact the author directly
- Check the documentation in the `docs/` folder

---

**Built with ☕ and passion for education**
