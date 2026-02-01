package com.generation.pl.controller.utils;

import java.sql.SQLException;
import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.pl.model.entities.Admin;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.UserStatus;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.pl.view.ViewController;

/**
 * Utility class for collecting user input data from console.
 * Centralizes data collection logic for all user types (Admin, Teacher, Student).
 * Uses InputValidator for validation and Console for I/O operations.
 */
public class Collect 
{
    /**
     * Collects base user data common to all user types (first name, last name, SSN, email).
     * This method follows DRY principle by avoiding code duplication across
     * collectTeacherData(), collectStudentData(), and collectAdminData()
     *
     * @param entityType Entity type name for display (e.g. "Student", "Teacher", "Admin")
     * @return String array with [firstName, lastName, ssn, email] in this order
     */
    public static String[] collectUserBaseData(String entityType)
    {
        Console.print("\n=== INSERT " + entityType.toUpperCase() + " ===\n");
        String name 	= InputValidator.readRequiredInput("Enter first name:", "First name cannot be empty!");
        String lastname = InputValidator.readRequiredInput("Enter last name:", "Last name cannot be empty!");
        String ssn 		= InputValidator.readSSN();
        String email 	= InputValidator.readEmail();
        return new String[] {name, lastname, ssn, email};
    }

    /**
     * Collects login credentials (email and password) for authentication.
     * 
     * Note: This method does NOT validate input format because:
     * - Email validation happens during registration, not login
     * - Password validation happens on server side (hash comparison)
     * - Invalid credentials will be caught by repository.login() method
     * - This approach allows users to attempt login even with typos
     * 
     * Used exclusively by login() methods in AdminMain, TeacherMain, StudentMain
     *
     * @return String array with [email, password] in this order
     */
    public static String[] collectLoginCredentials()
    {
        Console.print("Enter email:");
        String 	email = Console.readString();
        Console.print("Enter password:");
        String 	password = Console.readString();
        return new String[]{email, password};
    }

    /**
     * Collects complete Teacher data with validation.
     * 
     * Collection flow:
     * 1. Calls collectUserBaseData("Teacher") to get [firstName, lastName, ssn, email]
     * 2. Prompts for password twice with confirmation match validation
     * 3. Collects bio (required field, cannot be empty)
     * 4. Collects price per lesson with minimum value validation (>= 20 EUR)
     * 5. Collects subjects as CSV string (e.g. "JAVA,SQL,MATH") with enum validation
     * 6. Sets default status to ACTIVE (new teachers start active)
     * 7. Creates and populates Teacher entity with all collected data
     * 8. Returns Teacher object ready for validation and insertion
     * 
     * Note: Password will be hashed by repository before database insertion
     *
     * @return Teacher object with all validated data, ready for insertion
     */
    public static Teacher collectTeacherData()
    {
        Teacher 	teacher 		= new Teacher();
        String[] 	baseData 		= collectUserBaseData("Teacher");
        String 		password 		= InputValidator.readPasswordWithConfirmation(); 
        String 		bio 			= InputValidator.readRequiredInput("Bio:", "Bio cannot be empty!"); 
        int 		pricePerLesson	= InputValidator.readIntWithMin("Price per lesson (minimum 20):", 20); 
        String 		subjects		= InputValidator.readSubjectsCSV(); 
        teacher.setFirstName(baseData[0]);
        teacher.setLastName(baseData[1]);
        teacher.setSsn(baseData[2]);
        teacher.setEmail(baseData[3]);
        teacher.setPassword(password);
        teacher.setBio(bio);
        teacher.setPricePerLesson(pricePerLesson);
        teacher.setSubjects(subjects);
        teacher.setStatus(UserStatus.ACTIVE); 
        return teacher;
    }

    /**
     * Collects complete Student data with validation.
     * 
     * Collection flow:
     * 1. Calls collectUserBaseData("Student") to get [firstName, lastName, ssn, email]
     * 2. Prompts for password twice with confirmation match validation
     * 3. Collects date of birth with age validation (must be between 6-100 years old)
     * 4. Converts LocalDate to String format for database storage (ISO-8601: yyyy-MM-dd)
     * 5. Sets default status to ACTIVE (new students start active)
     * 6. Creates and populates Student entity with all collected data
     * 7. Returns Student object ready for validation and insertion
     * 
     * Note: Password will be hashed by repository before database insertion
     *
     * @return Student object with all validated data, ready for insertion
     */
    public static Student collectStudentData()
    { 
        String[] 	baseData 	= collectUserBaseData("Student");
        String 		password 	= InputValidator.readPasswordWithConfirmation(); 
        LocalDate 	dob 		= InputValidator.readDateOfBirth();      
        Student 	student 	= new Student();
        student.setFirstName(baseData[0]);
        student.setLastName(baseData[1]);
        student.setSsn(baseData[2]);
        student.setEmail(baseData[3]);
        student.setPassword(password);
        student.setDob(dob.toString());
        student.setStatus(UserStatus.ACTIVE);
        return student;
    }

    /**
     * Collects complete Admin data with validation.
     * 
     * Collection flow:
     * 1. Calls collectUserBaseData("Admin") to get [firstName, lastName, ssn, email]
     * 2. Prompts for password twice with confirmation match validation
     * 3. Creates Admin entity and populates all fields
     * 4. Sets default status to ACTIVE (new admins start active)
     * 5. Sets dateLastPasswordChange to current date (LocalDate.now())
     *    - This ensures new admin won't be forced to change password immediately
     *    - Password expiration check compares this date with current date minus 2 weeks
     * 6. Converts LocalDate to String for database storage (ISO-8601 format)
     * 7. Returns Admin object ready for validation and insertion
     * 
     * Note: Password will be hashed by repository before database insertion
     * 
     * Special case: If this is the first admin (isFirst=true in AdminMain.insertAdmin),
     * the application will exit after successful insertion, requiring restart to login
     *
     * @return Admin object with all validated data, ready for insertion
     */
    public static Admin collectAdminData()
    {
        String[] 	baseData = collectUserBaseData("Admin");
        String 		password = InputValidator.readPasswordWithConfirmation();
        Admin 		admin = new Admin();
        admin.setFirstName(baseData[0]);
        admin.setLastName(baseData[1]);
        admin.setSsn(baseData[2]);
        admin.setEmail(baseData[3]);
        admin.setPassword(password);
        admin.setStatus(UserStatus.ACTIVE);
        admin.setDateLastPasswordChange(LocalDate.now().toString());
        return admin;
    }
    
    /**
     * Collects lesson booking data with validation.
     * 
     * Collection flow:
     * 1. Prompts for teacher ID and retrieves teacher from database
     * 2. Displays selected teacher using ViewController.teacherBasicView
     * 3. Collects lesson date with validation (must be at least 1 day in advance)
     * 4. Collects lesson hour with range validation (9-18)
     * 5. Returns all collected data in Object array for lesson creation
     * 
     * This method follows the same pattern as collectTeacherData(), collectStudentData()
     * by centralizing input collection logic with validation
     * 
     * @param teacherRepo Repository to fetch teacher by ID
     * @return Object array with [Teacher, LocalDate, Integer hour] or null if validation fails
     * @throws SQLException if database error occurs during teacher retrieval
     */
    public static Object[] collectLessonBookingData(TeacherRepository teacherRepo) throws SQLException
    {
        Console.print("Enter teacher ID:");
        int teacherId = Console.readInt();
        Teacher teacher = teacherRepo.findById(teacherId, false);
        if (teacher == null)
        {
            Console.print("Teacher not found!");
            return null;
        }
        Console.print("\nSelected teacher:");
        Console.print(ViewController.teacherBasicView.render(teacher));
        Console.print("Enter lesson date (format YYYY-MM-DD):");
        String dateString = Console.readString();
        LocalDate lessonDate = LocalDate.parse(dateString);
        LocalDate minimumDate = LocalDate.now().plusDays(1);
        if (lessonDate.isBefore(minimumDate))
        {
            Console.print("ERROR: Bookings must be made at least 1 day in advance!");
            Console.print("Minimum accepted date: " + minimumDate);
            return null;
        }
        Console.print("Enter lesson hour (9-18):");
        int hour = Console.readInt();
        if (hour < 9 || hour > 18)
        {
            Console.print("ERROR: Hour must be between 9 and 18!");
            return null;
        }
        return new Object[] {teacher, lessonDate, hour};
    }

}
