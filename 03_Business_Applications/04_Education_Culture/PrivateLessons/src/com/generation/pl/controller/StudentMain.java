package com.generation.pl.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.library.Template;
import com.generation.pl.controller.utils.Collect;
import com.generation.pl.controller.utils.FileExporter;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Subject;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.repository.SQLRepository.LessonRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.StudentRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.TeacherRepositorySQL;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.StudentRepository;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.pl.view.ViewController;
import com.generation.pl.view.ViewFactory;

/**
 * Main controller for student operations in the platform.
 * Manages student login, lesson booking, teacher search, history viewing, and receipt generation.
 * Handles authentication, menu navigation, and coordinates between repositories and views.
 */
public class StudentMain
{
    private static StudentRepository studentRepo = Context.getDependency(StudentRepositorySQL.class);
    private static TeacherRepository teacherRepo = Context.getDependency(TeacherRepositorySQL.class);
    private static LessonRepository  lessonRepo  = Context.getDependency(LessonRepositorySQL.class);

    private Student loggedUser;

    /**
     * Main entry point for student session.
     * 
     * Process:
     * 1. Authenticates student via login() method (max 3 attempts)
     * 2. If authentication fails, terminates session
     * 3. Displays menu and processes commands until logout/quit
     * 4. Supports both numeric commands (1-5) and text aliases (searchteacher, booklesson, etc.)
     * 
     * Menu loop continues until user enters "0" or "Q" for quit.
     */
    public void run()
    {
        loggedUser = login();
        if (loggedUser == null)
        {
            Console.print("Login failed. Goodbye.");
            return;
        }
        Console.print("Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
        String choice = "";
        while (!choice.equalsIgnoreCase("Q"))
        {
            displayMenu();
            choice = Console.readString().trim();

            switch (choice.toLowerCase())
            {
                case "1":
                case "searchteacher":
                    searchTeacher();
                    break;
                case "2":
                case "booklesson":
                    bookLesson();
                    break;
                case "3":
                case "history":
                    printHistory();
                    break;
                case "4":
                case "receipt":
                    generateReceipt();
                    break;
                case "5":
                case "changepassword":
                    changePassword();
                    break;
                case "0":
                    Console.print("Goodbye!");
                    choice = "Q";
                    break;
                default:
                    Console.print("Invalid option. Try again.");
                    break;
            }
        }
    }

    /**
     * Loads and displays student menu from template file.
     * Reads menu content from template/txt/menu/student_menu.txt.
     * Displays error message if template file is not found or empty.
     */
    private static void displayMenu()
    {
    	String menu = Template.load("template/txt/menu/student_menu.txt");
		if (menu == null || menu.isEmpty())
			Console.print("Menu not found");
    	else
    		Console.print(menu);
    }

    /**
     * Handles student login with maximum 3 attempts.
     * 
     * Authentication process:
     * 1. Collects credentials (email/SSN and password) via Collect helper
     * 2. Validates against database using hashed password comparison (repository handles hashing)
     * 3. If credentials invalid, increments attempt counter and loops
     * 4. After 3 failed attempts, returns null and session terminates
     * 
     * Security: Passwords are never stored or compared in plain text.
     * 
     * @return Student object if authentication successful, null if failed after 3 attempts
     */
    private Student login()
    {
        try
        {
            int     count = 0;
            Student res   = null;
            while (res == null && count < 3)
            {
                String[] credentials = Collect.collectLoginCredentials();
                res = studentRepo.login(credentials[0], credentials[1]);

                if (res == null)
                    Console.print("Invalid credentials");
                count++;
            }
            if (res == null)
                return null;
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Searches for teachers by subject and displays results.
     *
     * Search mechanism:
     * 1. Prompts for subject name (case-insensitive, normalized to uppercase)
     * 2. Queries database using LIKE operator on teachers' CSV subjects field
     * 3. Displays each teacher with ID, name, subjects, price per lesson
     * 4. Appends bio if available (optional field)
     * 5. Shows total count of teachers found
     * 6. Saves both TXT and HTML versions to print/ directory
     *
     * Uses ViewController.teacherBasicView for consistent formatting.
     */
    private void searchTeacher()
    {
        try
        {
            Console.print("\\n=== SEARCH TEACHER BY SUBJECT ===");
            Console.print("Enter subject to search:");

            // Generate available subjects from enum
            StringBuilder availableSubjects = new StringBuilder("Available subjects: ");
            Subject[] subjects = Subject.values();
            for (int i = 0; i < subjects.length; i++)
            {
                availableSubjects.append(subjects[i].name());
                if (i < subjects.length - 1)
                    availableSubjects.append(", ");
            }
            Console.print(availableSubjects.toString());
            String subject = Console.readString().trim().toUpperCase();
            List<Teacher> teachers = teacherRepo.findTeacherBySubject(subject);
            if (teachers.isEmpty())
            {
                Console.print("No teacher found for subject: " + subject);
                return;
            }
            String teachersListTxt = ViewFactory.renderTeachersListTXT(teachers);
            Console.print(teachersListTxt);
            String filenameTxt = "teachers_" + subject + ".txt";
            FileExporter.save(teachersListTxt, "txt", "students", filenameTxt);
            Console.print("\n TXT saved: print/txt/students/" + filenameTxt);
            String teachersListHtml = ViewFactory.renderTeachersListHTML(teachers);
            String filenameHtml = "teachers_" + subject + ".html";
            FileExporter.save(teachersListHtml, "html", "students", filenameHtml);
            Console.print("HTML saved: print/html/students/" + filenameHtml);
        }
        catch (SQLException e)
        {
            Console.print("Error during search: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Books a new lesson with a teacher.
     * 
     * Booking workflow:
     * 1. Collects booking data via Collect.collectLessonBookingData (teacher ID, date, hour)
     * 2. Validates teacher existence and data format
     * 3. Creates Lesson entity and validates business rules (date constraints, hour range)
     * 4. Inserts lesson into database if all validations pass
     * 
     * Delegates to createAndSaveLesson() for entity creation and persistence.
     */
    private void bookLesson()
    {
        try
        {
            Console.print("\\n=== BOOK LESSON ===");
            Object[] bookingData = Collect.collectLessonBookingData(teacherRepo);
            if (bookingData == null)
                return;
            createAndSaveLesson(bookingData);
        }
        catch (SQLException e)
        {
            Console.print("Error during booking: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Console.print("Data format error: " + e.getMessage());
        }
    }

    /**
     * Creates Lesson entity from booking data and persists to database.
     * 
     * Entity creation logic:
     * 1. Extracts teacher, date, and hour from Object array
     * 2. Creates new Lesson with logged student, selected teacher, and teacher's price
     * 3. Validates lesson data using entity's isValid() method
     * 4. If validation fails, displays all error messages and aborts
     * 5. If valid, inserts into database and displays confirmation with lesson details
     * 
     * @param data Object array containing [Teacher, LocalDate, Integer hour]
     * @throws SQLException if database insertion fails
     */
    private void createAndSaveLesson(Object[] data) throws SQLException
    {
        Teacher teacher = (Teacher) data[0];
        LocalDate lessonDate = (LocalDate) data[1];
        int hour = (int) data[2];
        Lesson lesson = new Lesson();
        lesson.setDate(lessonDate);
        lesson.setHour(hour);
        lesson.setStudent(loggedUser);
        lesson.setTeacher(teacher);
        lesson.setPrice(teacher.getPricePerLesson());
        if (!lesson.isValid())
        {
            Console.print("Error: invalid lesson data!");
            for (String error : lesson.getErrors())
                Console.print("- " + error);
            return;
        }        
        Lesson insertedLesson = lessonRepo.insert(lesson);
        Console.print("\nLesson booked successfully!");
        Console.print(ViewController.lessonBasicView.render(insertedLesson));
    }
    
    /**
     * Displays lesson history for logged student with financial summary.
     *
     * Query and display logic:
     * 1. Retrieves all lessons where studentid matches logged user
     * 2. Displays lessons using ViewFactory formatted output
     * 3. Calculates total amount spent by summing lesson prices
     * 4. Shows summary with total lesson count and total spent
     * 5. Saves both TXT and HTML versions to print/ directory
     *
     * Returns early if no lessons found for the student.
     */
    private void printHistory()
    {
        Console.print("\n=== LESSON HISTORY ===\n");
        List<Lesson> lessons = lessonRepo.findWhere("studentid = " + loggedUser.getId());
        if (lessons.isEmpty())
        {
            Console.print("No lessons found.");
            return;
        }

        // Console output (TXT)
        String lessonsList = ViewFactory.renderLessonsListTXT(lessons);
        Console.print(lessonsList);

        int totalSpent = 0;
        for (Lesson lesson : lessons)
            totalSpent += lesson.getPrice();
        Console.print("TOTAL SPENT: €" + totalSpent);
        String filenameTxt = "history_" + loggedUser.getId() + ".txt";
        FileExporter.save(lessonsList, "txt", "students", filenameTxt);
        Console.print("\n TXT saved: print/txt/students/" + filenameTxt);
        String studentName = loggedUser.getFirstName() + " " + loggedUser.getLastName();
        String html = ViewFactory.renderStudentLessonHistoryHTML(lessons, studentName);
        String filenameHtml = "history_" + loggedUser.getId() + ".html";
        FileExporter.save(html, "html", "students", filenameHtml);
        Console.print("HTML saved: print/html/students/" + filenameHtml);
    }


    /**
     * Generates HTML receipt for a specific lesson.
     * 
     * Receipt generation process:
     * 1. Prompts for lesson ID and retrieves from database
     * 2. Security check: Verifies lesson belongs to logged student (prevents unauthorized access)
     * 3. Generates HTML receipt using ViewController.renderLessonReceipt template
     * 4. Writes HTML to file with format: ricevuta_lesson_[ID].html
     * 
     * Security: Students can only generate receipts for their own lessons.
     * Uses teacher's price at time of booking (stored in lesson record).
     */
    private void generateReceipt()
    {
        try
        {
            Console.print("\n=== GENERATE RECEIPT ===");
            Console.print("Enter lesson ID:");
            int lessonId = Console.readInt();
            Lesson lesson = lessonRepo.findById(lessonId);
            if (lesson == null)
            {
                Console.print("Lesson not found!");
                return;
            }
            if (lesson.getStudent().getId() != loggedUser.getId())
            {
                Console.print("ERROR: You cannot generate receipt for other students' lessons!");
                return;
            }
            Teacher teacher = lesson.getTeacher();
            String studentName = loggedUser.getFirstName() + " " + loggedUser.getLastName();
            String teacherName = teacher.getFirstName() + " " + teacher.getLastName();
            String html = ViewController.renderLessonReceipt(lesson, studentName, teacherName);
            String filename = "ricevuta_lesson_" + lessonId + ".html";
            FileExporter.save(html, "html", "lessons", filename);
            Console.print("\nReceipt generated successfully!");
            Console.print("File saved: print/html/lessons/" + filename);
        }
        catch (Exception e)
        {
            Console.print("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles password change for logged student.
     * 
     * Change password workflow:
     * 1. Collects old password (for verification), new password, and confirmation
     * 2. Client-side validation: Checks that new password and confirmation match
     * 3. Calls repository.changePassword() which verifies old password (hashed comparison)
     * 4. Repository updates password to hashed version of new password
     * 
     * Security: Old password verification prevents unauthorized password changes.
     * SQLException thrown if old password is incorrect.
     */
    private void changePassword()
    {
        Console.print("\\n=== CHANGE PASSWORD ===");
        Console.print("Enter old password:");
        String oldPw = Console.readString();
        Console.print("Enter new password:");
        String newPw = Console.readString();
        Console.print("Confirm new password:");
        String confirmPw = Console.readString();
        if (!newPw.equals(confirmPw))
        {
            Console.print("ERROR: Passwords do not match!");
            return;
        }
        try
        {
            studentRepo.changePassword(loggedUser.getId(), oldPw, newPw);
            Console.print("Password changed successfully!");
        }
        catch (SQLException e)
        {
            Console.print("ERROR: " + e.getMessage());
        }
    }
}
