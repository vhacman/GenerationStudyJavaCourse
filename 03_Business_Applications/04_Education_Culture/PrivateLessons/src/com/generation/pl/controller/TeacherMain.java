package com.generation.pl.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.library.Template;
import com.generation.pl.controller.utils.Collect;
import com.generation.pl.controller.utils.FileExporter;
import com.generation.pl.controller.utils.InputValidator;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.pl.view.ViewFactory;
import com.generation.pl.view.ViewController;

/**
 * Main controller for teacher operations in the platform.
 * Manages teacher login, lesson history viewing, schedule management, and earnings calculation.
 * Handles authentication, menu navigation, and coordinates between repositories and views.
 */
public class TeacherMain 
{
    TeacherRepository teacherRepo = Context.getDependency(TeacherRepository.class);
    Teacher loggedUser;
    
    /**
     * Main entry point for teacher session.
     * 
     * Process:
     * 1. Authenticates teacher via login() method (max 3 attempts)
     * 2. If authentication fails, terminates session
     * 3. Displays menu and processes commands until logout/quit
     * 4. Supports both numeric commands (1-4) and text aliases (history, nextweek, earnings, etc.)
     * 
     * Menu loop continues until user enters "0", "q", or "quit".
     */
    public void run() 
    {
        loggedUser = login();
        if (loggedUser == null)
        {
            Console.print("Login failed. Goodbye.");
            return;
        }        
        boolean exit = false;
        while(!exit)
        {
            String menuContent = Template.load("template/txt/menu/teacher_menu.txt");
            Console.print(menuContent);
            
            String choice = Console.readString().trim().toLowerCase();
            
            switch(choice)
            {
                case "1":
                case "history":
                    printLessonHistory();
                    break;
                case "2":
                case "nextweek":
                    printNextWeekLessons();
                    break;
                case "3":
                case "earnings":
                    printEarnings();
                    break;
                case "4":
                case "changepassword":
                    changePassword();
                    break;
                case "0":
                case "q":
                case "quit":
                    Console.print("Goodbye!");
                    exit = true;
                    break;
                default:
                    Console.print("Invalid option. Try again.");
            }
        }
    }
    
    /**
     * Displays complete lesson history for logged teacher.
     * 
     * Query and display logic:
     * 1. Retrieves all lessons where teacherid matches logged user
     * 2. Uses ViewFactory.renderLessonsListTXT for formatted output (handles multiple lessons in single template)
     * 3. Shows all past and future lessons with student details, dates, and prices
     * 
     * Returns early if no lessons found for the teacher.
     */
    /**
     * Displays complete lesson history for logged teacher.
     * 
     * Query and display logic:
     * 1. Retrieves all lessons where teacherid matches logged user
     * 2. Uses ViewFactory for formatted output in both TXT and HTML
     * 3. Saves both TXT and HTML versions to print/ directory
     * 4. Shows all past and future lessons with student details, dates, and prices
     * 
     * Returns early if no lessons found for the teacher.
     */
    private void printLessonHistory()
    {
        try
        {
            Console.print("\n=== LESSON HISTORY ===");
            List<Lesson> lessons = teacherRepo.findLessonsByTeacherId(loggedUser.getId());            
            if (lessons.isEmpty())
            {
                Console.print("No lessons found.");
                return;
            }            
            // Console output (TXT)
            String lessonsList = ViewFactory.renderLessonsListTXT(lessons);
            Console.print(lessonsList);            
            // Save TXT version
            String filenameTxt = "history_" + loggedUser.getId() + ".txt";
            FileExporter.save(lessonsList, "txt", "teachers", filenameTxt);
            Console.print("\n TXT saved: print/txt/teachers/" + filenameTxt);            
            // Generate and save HTML version
            String lessonsListHtml = ViewFactory.renderLessonsListHTML(lessons);
            String filenameHtml = "history_" + loggedUser.getId() + ".html";
            FileExporter.save(lessonsListHtml, "html", "teachers", filenameHtml);
            Console.print("HTML saved: print/html/teachers/" + filenameHtml);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Console.print("Error retrieving lessons: " + e.getMessage());
        }
    }


    
    /**
     * Displays lessons scheduled for the next 7 days.
     *
     * Schedule query logic:
     * 1. Queries lessons where date is between current date and current date + 7 days
     * 2. Filters by logged teacher's ID
     * 3. Uses ViewFactory for formatted output in both TXT and HTML
     * 4. Saves both TXT and HTML versions to print/ directory
     * 5. Shows count of upcoming lessons for quick overview
     *
     * Useful for weekly planning and schedule management.
     */
    private void printNextWeekLessons()
    {
        try
        {
            Console.print("\\n=== NEXT WEEK LESSONS ===");
            List<Lesson> lessons = teacherRepo.findNextWeekLessonsByTeacherId(loggedUser.getId());
            if (lessons.isEmpty())
            {
                Console.print("No lessons scheduled for next week.");
                return;
            }
            String lessonsList = ViewFactory.renderLessonsListTXT(lessons);
            Console.print(lessonsList);
            String filenameTxt = "nextweek_" + loggedUser.getId() + ".txt";
            FileExporter.save(lessonsList, "txt", "teachers", filenameTxt);
            Console.print("\n TXT saved: print/txt/teachers/" + filenameTxt);
            String lessonsListHtml = ViewFactory.renderLessonsListHTML(lessons);
            String filenameHtml = "nextweek_" + loggedUser.getId() + ".html";
            FileExporter.save(lessonsListHtml, "html", "teachers", filenameHtml);
            Console.print("HTML saved: print/html/teachers/" + filenameHtml);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Console.print("Error retrieving lessons: " + e.getMessage());
        }
    }
    
    /**
     * Calculates and displays total earnings for a custom date range.
     * 
     * Earnings calculation process:
     * 1. Prompts for start date and end date (format: YYYY-MM-DD)
     * 2. Parses dates using LocalDate.parse() (throws exception if invalid format)
     * 3. Queries all lessons in date range for logged teacher
     * 4. Sums lesson prices to calculate total earnings
     * 5. Displays report 
     */
    private void printEarnings()
    {
        try
        {
            Console.print("\n=== EARNINGS CALCULATION ===");
            Console.print("Enter start date (YYYY-MM-DD):");
            String startDateStr = Console.readString().trim();
            LocalDate startDate = LocalDate.parse(startDateStr);            
            Console.print("Enter end date (YYYY-MM-DD):");
            String endDateStr = Console.readString().trim();
            LocalDate endDate = LocalDate.parse(endDateStr);            
            int earnings = teacherRepo.calculateEarningsByTeacherIdAndPeriod(loggedUser.getId(), startDate, endDate);            
            String earningsReport = ViewController.renderEarningsReport(loggedUser, startDate, endDate, earnings);            
            Console.print(earningsReport);            
            String filename = "earnings_" + loggedUser.getId() + ".txt";
            FileExporter.save(earningsReport, "txt", "teachers", filename);            
            Console.print("\n File saved: print/txt/teachers/" + filename);
            // Generate and save HTML version
            String earningsReportHtml = ViewController.renderEarningsReportHTML(loggedUser, startDate, endDate, earnings);
            String filenameHtml = "earnings_" + loggedUser.getId() + ".html";
            FileExporter.save(earningsReportHtml, "html", "teachers", filenameHtml);
            Console.print(" HTML saved: print/html/teachers/" + filenameHtml);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Console.print("Error calculating earnings: " + e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Console.print("Invalid date format. Use YYYY-MM-DD");
        }
    }

    
    /**
     * Handles password change for logged teacher.
     * 
     * Change password workflow:
     * 1. Collects old password (for verification)
     * 2. Uses InputValidator.readPasswordWithConfirmation() for new password (ensures min 8 chars + confirmation match)
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
        String newPw = InputValidator.readPasswordWithConfirmation();        
        try
        {
            teacherRepo.changePassword(loggedUser.getId(), oldPw, newPw);
            Console.print("Password changed successfully!");
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
            Console.print("✗ " + e.getMessage());
        }
    }
    
    /**
     * Handles teacher login with maximum 3 attempts.
     * 
     * Authentication process:
     * 1. Collects credentials (email/SSN and password) via Collect helper
     * 2. Validates against database using hashed password comparison (repository handles hashing)
     * 3. If credentials invalid, increments attempt counter and loops
     * 4. After 3 failed attempts, returns null and session terminates
     * 
     * Security: Passwords are never stored or compared in plain text.
     * 
     * @return Teacher object if authentication successful, null if failed after 3 attempts
     */
    private Teacher login() 
    {
        try
        {
            int count = 0;
            Teacher res = null;
            
            while(res == null && count < 3)
            {
                String[] credentials = Collect.collectLoginCredentials();
                res = teacherRepo.login(credentials[0], credentials[1]);                
                if (res == null)
                    Console.print("Invalid credentials");
                count++;
            }
            
            return res;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
