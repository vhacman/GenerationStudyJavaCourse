package com.generation.pl.controller;

import java.sql.SQLException;
import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.library.Template;
import com.generation.pl.model.repository.SQLRepository.AdminRepositorySQL;
import com.generation.pl.model.repository.interfaces.AdminRepository;

/**
 * Main entry point for the Platform Learning (PL) application.
 * 
 * Application architecture:
 * - Multi-user system with three user types: Student, Teacher, Admin
 * - Each user type has dedicated controller (StudentMain, TeacherMain, AdminMain)
 * - Centralized menu system for user type selection
 * - First-run setup for admin account creation
 */
public class Main
{
    /**
     * Application entry point - executed when program starts.
     * 
     * Startup flow:
     * 
     * PHASE 1: First-run admin setup
     * 1. Retrieves AdminRepository from IoC container (Context)
     * 2. Checks if any admin exists using adminRepo.existsAnyAdmin()
     * 3. If no admin found (first run or empty database):
     *    - Calls AdminMain.insertAdmin(true) with isFirst=true flag
     * 4. If SQLException occurs during check:
     *    - Displays error message
     *    - Returns from main (terminates application)
     *    - Prevents running with database connection issues
     * 
     * PHASE 2: Main menu loop
     * 1. Enters infinite loop controlled by 'exit' boolean flag
     * 2. Loads main menu template 
     * 3. Displays menu to console
     * 4. Reads user choice and normalizes (trim + lowercase)
     * 
     * PHASE 3: User type selection (switch statement)
     * 
     * Case "1" or "student":
     * - Creates new StudentMain instance
     * - Calls run()
     * 
     * Case "2" or "teacher":
     * - Creates new TeacherMain instance
     * - Calls run() 
     * 
     * Case "3" or "admin":
     * - Creates new AdminMain instance
     * - Calls run() 
     * 
     * Case "0", "exit", or "quit":
     * - Displays goodbye message
     * - Sets exit flag to true
     * - Loop terminates
     * - main() method ends
     * Default case:
     * - Handles invalid input
     * - Displays error message
     * - Loop continues (gives user another chance)
     * Why separate Main classes?
     * - Single Responsibility Principle: Each controller handles one user type
     * - Separation of Concerns: Different business logic for each user type
     * - Maintainability: Changes to student logic don't affect teacher/admin
     * - Security: Each controller has appropriate repository access
     * 
     * Error handling strategy:
     * - Database errors during startup: Return from main (prevent running with broken DB)
     * - Invalid menu choices: Continue loop (user-friendly, allows retry)
     * - Login failures in controllers: Return to main menu (handled by each controller)
     * 
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args)
    {
        try
        {
            AdminRepository adminRepo = Context.getDependency(AdminRepositorySQL.class);
            if (!adminRepo.existsAnyAdmin())
            {
                AdminMain.insertAdmin(true);
            }
        }
        catch (SQLException e)
        {
            Console.print("Error checking admin: " + e.getMessage());
            return;
        }        
        boolean exit = false;
        while (!exit)
        {            
            String menuContent = Template.load("template/txt/menu/main_menu.txt");
            Console.print(menuContent);            
            String choice = Console.readString().trim().toLowerCase();            
            switch(choice)
            {
                case "1":
                case "student":
                    new StudentMain().run();
                    break;
                case "2":
                case "teacher":
                    new TeacherMain().run();
                    break;
                case "3":
                case "admin":
                    new AdminMain().run();
                    break;
                case "0":
                case "exit":
                case "quit":
                    Console.print("Goodbye!");
                    exit = true;
                    break;
                default:
                    Console.print("Invalid option. Try again.");
            }
        }
    }
}
