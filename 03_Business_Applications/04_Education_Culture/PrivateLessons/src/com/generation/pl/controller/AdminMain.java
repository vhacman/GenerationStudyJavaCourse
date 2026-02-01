package com.generation.pl.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import com.generation.context.Context;
import com.generation.library.*;
import com.generation.pl.controller.utils.Collect;
import com.generation.pl.controller.utils.FileExporter;
import com.generation.pl.controller.utils.InputValidator;
import com.generation.pl.model.entities.Admin;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Subject;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.User;
import com.generation.pl.model.entities.UserStatus;
import com.generation.pl.view.ViewController;
import com.generation.pl.view.ViewFactory;
import com.generation.pl.model.repository.SQLRepository.AdminRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.LessonRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.StudentRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.TeacherRepositorySQL;
import com.generation.pl.model.repository.interfaces.AdminRepository;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.StudentRepository;
import com.generation.pl.model.repository.interfaces.TeacherRepository;

/**
 * Main controller class for admin operations in the platform.
 * Handles login, user management (insert, status changes), earnings reports, and password changes.
 */
public class AdminMain
{
	private static AdminRepository 		adminRepo = Context.getDependency(AdminRepositorySQL.class);
	private static TeacherRepository 	teacherRepo = Context.getDependency(TeacherRepositorySQL.class);
	private static StudentRepository 	studentRepo = Context.getDependency(StudentRepositorySQL.class);
	private static LessonRepository 	lessonRepo = Context.getDependency(LessonRepositorySQL.class);

	Admin loggedUser;

	/**
	 * Entry point for admin session.
	 * 
	 * Flow:
	 * 1. Executes login with max 3 attempts
	 * 2. If login fails, terminates the session
	 * 3. Checks if password has expired (more than 2 weeks old)
	 * 4. If expired, forces password change before accessing menu
	 * 5. Displays main menu and processes user choices in a loop until quit
	 * 
	 * The menu supports both numeric input (1-9) and text commands (e.g. "insertteacher")
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
		if (loggedUser.needsPasswordChange())
		{
			Console.print("\nYou must change your password before proceeding.");
			if (!changePassword(loggedUser, true))
			{
				Console.print("Operation cancelled.");
				return;
			}
		}
		String choice = "";
		while (!choice.equalsIgnoreCase("Q"))
		{
			displayMenu();
			choice = Console.readString().trim();
			switch (choice.toLowerCase())
			{
				case "1":
				case "insertteacher":
					insertTeacher();
					break;
				case "2":
				case "insertstudent":
					insertStudent();
					break;
				case "3":
				case "insertadmin":
					AdminMain.insertAdmin(false);
					break;
				case "4":
				case "changestatusteacher":
					changeStatusUser("teacher");
					break;
				case "5":
				case "changestatusstudent":
					changeStatusUser("student");
					break;
				case "6":
				case "changestatusadmin":
					changeStatusUser("admin");
					break;
				case "7":
				case "teacherearnings":
					teacherEarnings();
					break;
				case "8":
				case "subjectearnings":
					subjectEarnings();
					break;
				case "9":
				case "changepassword":
					changePassword(loggedUser, false);
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
	 * Displays the admin menu by loading it from template file.
	 * 
	 * Loads menu content from "template/txt/menu/admin_menu.txt".
	 * If file is not found or empty, prints error message.
	 */
	private static void displayMenu()
	{
		String menu = Template.load("template/txt/menu/admin_menu.txt");
		if (menu == null || menu.isEmpty())
			Console.print("Menu not found");
		else
			Console.print(menu);
	}

	/**
	 * Handles admin login with maximum 3 attempts and password expiration check.
	 * 
	 * Process:
	 * 1. Allows up to 3 login attempts
	 * 2. For each attempt, collects email and password from console
	 * 3. Verifies credentials against database using adminRepo.login()
	 * 4. If successful, checks if password was changed in last 2 weeks
	 * 5. If password expired, forces mandatory password change
	 * 6. Returns Admin object if successful, null if failed or cancelled
	 * 
	 * @return Admin object if login successful, null otherwise
	 */
	private Admin login()
	{
		try
		{
			int 	count = 0;
			Admin 	res = null;            
			while (res == null && count < 3)
			{
				String[] credentials = Collect.collectLoginCredentials();
				res = adminRepo.login(credentials[0], credentials[1]);                
				if (res == null)
					Console.print("Invalid credentials");                
				count++;
			}           
			if (res == null)
				return null;
			LocalDate lastChange = res.getDateLastPasswordChange();
			LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);
			if(lastChange.isBefore(twoWeeksAgo))
			{
				Console.print("Password expired! You must change it before accessing!");            	
				if (!changePassword(res, true))
				{
					Console.print("Login cancelled");
					return null;
				}
			}
			return res;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Inserts a new teacher into the system after collecting and validating data.
	 * 
	 * Steps:
	 * 1. Collects teacher data from console using Collect.collectTeacherData()
	 * 2. Validates all teacher fields using teacher.isValid()
	 * 3. If invalid, displays all validation errors and aborts insertion
	 * 4. If valid, inserts teacher into database via teacherRepo.insert()
	 * 5. Displays success message with basic teacher info using ViewController
	 * 6. Catches and displays any SQL errors during insertion
	 */
	private void insertTeacher()
	{
		try
		{
			Teacher teacher = Collect.collectTeacherData();
			if (!teacher.isValid())
			{
				Console.print("Error: invalid teacher data!");
				for (String error : teacher.getErrors())
					Console.print("- " + error);
				return;
			}
			Teacher inserted = teacherRepo.insert(teacher);
			Console.print("Teacher inserted successfully!");
			Console.print(ViewController.teacherBasicView.render(inserted));
		}
		catch (SQLException e)
		{
			Console.print("Error inserting teacher: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts a new student into the system after collecting and validating data.
	 * 
	 * Steps:
	 * 1. Collects student data from console using Collect.collectStudentData()
	 * 2. Validates all student fields using student.isValid()
	 * 3. If invalid, displays all validation errors and aborts insertion
	 * 4. If valid, inserts student into database via studentRepo.insert()
	 * 5. Displays success message with basic student info using ViewController
	 * 6. Catches and displays any SQL errors during insertion
	 */
	private void insertStudent()
	{
		try
		{
			Student student = Collect.collectStudentData();
			if (!student.isValid())
			{
				Console.print("Error: invalid student data!");
				for (String error : student.getErrors())
					Console.print("- " + error);
				return;
			}
			Student insertedStudent = studentRepo.insert(student);
			Console.print("Student inserted successfully!");
			Console.print(ViewController.studentBasicView.render(insertedStudent));
		}
		catch (SQLException e)
		{
			Console.print("Error inserting student: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Changes the status of a user (teacher, student, or admin).
	 * 
	 * Process:
	 * 1. Determines which type of user based on userType parameter
	 * 2. Prompts for user ID based on type
	 * 3. Searches for user in corresponding repository (teacherRepo, studentRepo, or adminRepo)
	 * 4. If user not found, displays error and exits
	 * 5. If found, displays current user info using appropriate view
	 * 6. Calls changeStatus() to handle the actual status update
	 * 7. Catches and displays any SQL errors
	 * 
	 * @param userType the type of user ("teacher", "student", or "admin")
	 */
	private void changeStatusUser(String userType)
	{
		try
		{
			User user = null;
			String entityType = "";
			if (userType.equalsIgnoreCase("teacher"))
			{
				Console.print("\n=== CHANGE TEACHER STATUS ===");
				Console.print("Enter Teacher ID:");
				int id = Console.readInt();
				user = teacherRepo.findById(id, false);
				entityType = "Teacher";
			}
			else if (userType.equalsIgnoreCase("student"))
			{
				Console.print("\n=== CHANGE STUDENT STATUS ===");
				Console.print("Enter Student ID:");
				int id = Console.readInt();
				user = studentRepo.findById(id, false);
				entityType = "Student";
			}
			else if (userType.equalsIgnoreCase("admin"))
			{
				Console.print("\n=== CHANGE ADMIN STATUS ===");
				Console.print("Enter Admin ID:");
				int id = Console.readInt();
				user = adminRepo.findById(id);
				entityType = "Admin";
			}
			else
			{
				Console.print("Invalid user type!");
				return;
			}

			if (user == null)
			{
				Console.print(entityType + " not found!");
				return;
			}
			Console.print("\n" + entityType + " found:");
			if (user instanceof Teacher)
				Console.print(ViewController.teacherBasicView.render((Teacher)user));
			else if (user instanceof Student)
				Console.print(ViewController.studentBasicView.render((Student)user));
			changeStatus(user);
		}
		catch (SQLException e)
		{
			Console.print("Error: " + e.getMessage());
		}
	}

	/**
	 * Unified method to change the status of any user (Admin, Teacher, or Student).
	 * 
	 * Logic:
	 * 1. Determines user type using instanceof checks
	 * 2. If type not supported, displays error and exits
	 * 3. Displays current user info (ID, name, current status)
	 * 4. Prompts for new status using InputValidator.readUserStatus()
	 * 5. Validates that new status is different from current status
	 * 6. If unchanged, displays message and exits without database call
	 * 7. If changed, calls appropriate repository's changeStatus() method
	 * 8. Updates status in database and displays success message
	 * 9. Catches and displays any SQL errors
	 * 
	 * @param user the user whose status needs to be changed
	 */
	private void changeStatus(User user)
	{
		try
		{
			String entityType;
			if (user instanceof Admin)
				entityType = "Admin";
			else if (user instanceof Teacher)
				entityType = "Teacher";
			else if (user instanceof Student)
				entityType = "Student";
			else
			{
				Console.print("User type not supported");
				return;
			}	        
			Console.print(" === CHANGE STATUS === ");
			Console.print("ID: " + user.getId());
			Console.print("Name: " + user.getFirstName() + " " + user.getLastName());
			Console.print("Current status: " + user.getStatus());	        
			UserStatus newStatus = InputValidator.readUserStatus();	        
			if (user.getStatus() == newStatus)
			{
				Console.print("No changes: status was already " + newStatus);
				return;
			}	        
			if (user instanceof Admin) 
				adminRepo.changeStatus(user.getId(), newStatus);
			else if (user instanceof Teacher) 
				teacherRepo.changeStatus(user.getId(), newStatus);
			else if (user instanceof Student)
				studentRepo.changeStatus(user.getId(), newStatus);	        
			Console.print(entityType + " status updated successfully to " + newStatus);
		}
		catch (SQLException e)
		{
			Console.print("Error changing status: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays teacher earnings for the last 30 days.
	 * 
	 * Workflow:
	 * 1. Retrieves and displays all teachers from database using teacherRepo.findAll()
	 * 2. Shows formatted list with separators and total count
	 * 3. Prompts user to enter teacher ID (0 to exit)
	 * 4. If 0, exits the function immediately
	 * 5. Searches for teacher by ID using teacherRepo.findById()
	 * 6. If teacher not found, displays error and exits
	 * 7. Calculates earnings for last 30 days using lessonRepo.calculateEarningsByTeacherLast30Days()
	 * 8. Displays teacher name and earnings in EUR
	 * 9. Optionally shows complete teacher profile if user confirms (y/n)
	 * 10. Uses ViewFactory to render detailed teacher view if requested
	 * 11. Catches and displays any SQL errors
	 */
	private void teacherEarnings()
	{
	    try
	    {
	        Console.print("\n=== TEACHER EARNINGS (last 30 days) ===");
	        List<Teacher> allTeachers = teacherRepo.findAll();
	        String teachersListTxt = ViewFactory.renderTeachersListTXT(allTeachers);
	        Console.print(teachersListTxt);
	        String listFilenameTxt = "all_teachers.txt";
	        FileExporter.save(teachersListTxt, "txt", "admin", listFilenameTxt);
	        String teachersListHtml = ViewFactory.renderTeachersListHTML(allTeachers);
	        String listFilenameHtml = "all_teachers.html";
	        FileExporter.save(teachersListHtml, "html", "admin", listFilenameHtml);
	        Console.print("\nEnter Teacher ID to view earnings (0 to exit):");
	        int teacherId = Console.readInt();
	        if (teacherId == 0)
	            return;
	        Teacher teacher = teacherRepo.findById(teacherId, false);
	        if (teacher == null)
	        {
	            Console.print("Teacher not found!");
	            return;
	        }
	        int earnings = lessonRepo.calculateEarningsByTeacherLast30Days(teacherId);
	        Console.print("\n=== TEACHER EARNINGS ===");
	        Console.print("Teacher: " + teacher.getFirstName() + " " + teacher.getLastName());
	        Console.print("Earnings last 30 days: EUR " + earnings);
	        Console.print("\nDo you want to see the complete teacher profile? (y/n)");
	        String answer = Console.readString().trim().toLowerCase();
	        if (answer.equals("s") || answer.equals("si") || answer.equals("y") || answer.equals("yes"))
	        {
	            String teacherDetail = ViewFactory.make("teacher", "txt", "detail").render(teacher);
	            Console.print("\n=== COMPLETE TEACHER PROFILE ===");
	            Console.print(teacherDetail);
	        }
	        String txt = ViewFactory.renderTeacherEarningsReportTXT(teacher, earnings);
	        String reportFilenameTxt = "teacher_earnings_" + teacherId + ".txt";
	        FileExporter.save(txt, "txt", "admin", reportFilenameTxt);
	        Console.print("\n TXT saved: print/txt/admin/" + reportFilenameTxt);
	        String html = ViewFactory.renderTeacherEarningsReportHTML(teacher, earnings);
	        String reportFilenameHtml = "teacher_earnings_" + teacherId + ".html";
	        FileExporter.save(html, "html", "admin", reportFilenameHtml);
	        Console.print("HTML saved: print/html/admin/" + reportFilenameHtml);
	    }
	    catch (SQLException e)
	    {
	        Console.print("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	/**
	 * Displays earnings by subject for the last 30 days.
	 * 
	 * Steps:
	 * 1. Displays list of all available subjects from Subject enum
	 * 2. Shows each subject with numbered index for reference
	 * 3. Prompts user to enter subject name (e.g. JAVA, MATH, SQL)
	 * 4. Converts input to uppercase for case-insensitive matching
	 * 5. Validates input against Subject enum using valueOf()
	 * 6. If invalid subject, displays error and exits (catches IllegalArgumentException)
	 * 7. If valid, calculates total earnings for that subject in last 30 days
	 * 8. Uses lessonRepo.calculateEarningsBySubjectLast30Days() for calculation
	 * 9. Displays subject name and total earnings in EUR
	 * 10. Catches and displays any SQL errors
	 */
	private void subjectEarnings()
	{
	    try
	    {
	        Console.print("\n=== EARNINGS BY SUBJECT (last 30 days) ===");
	        Console.print("\n=== AVAILABLE SUBJECTS ===");
	        Subject[] subjects = Subject.values();	        
	        for (int i = 0; i < subjects.length; i++)
	            Console.print((i + 1) + ". " + subjects[i].name());	        
	        Console.print("\nEnter subject name (e.g. JAVA, MATH, SQL):");
	        String subjectInput = Console.readString().trim().toUpperCase();	        
	        Subject selectedSubject = null;
	        try
	        {
	            selectedSubject = Subject.valueOf(subjectInput);
	        }
	        catch (IllegalArgumentException e)
	        {
	            Console.print("Invalid subject! Choose from those listed above.");
	            return;
	        }	        
	        int earnings = lessonRepo.calculateEarningsBySubjectLast30Days(subjectInput);
	        Console.print("\n=== RESULT ===");
	        Console.print("Subject: " + selectedSubject.name());
	        Console.print("Total earnings last 30 days: EUR " + earnings);
	        String txt = ViewFactory.renderSubjectEarningsReportTXT(selectedSubject.name(), earnings);
	        String filenameTxt = "subject_earnings_" + selectedSubject.name() + ".txt";
	        FileExporter.save(txt, "txt", "admin", filenameTxt);
	        Console.print("\n TXT saved: print/txt/admin/" + filenameTxt);
	        String html = ViewFactory.renderSubjectEarningsReportHTML(selectedSubject.name(), earnings);
	        String filenameHtml = "subject_earnings_" + selectedSubject.name() + ".html";
	        FileExporter.save(html, "html", "admin", filenameHtml);
	        Console.print("HTML saved: print/html/admin/" + filenameHtml);
	    }
	    catch (SQLException e)
	    {
	        Console.print("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	/**
	 * Handles password change for the logged admin.
	 * 
	 * Process:
	 * 1. Displays appropriate header based on isForced flag (mandatory vs voluntary)
	 * 2. Prompts for current password for security verification
	 * 3. Collects new password with confirmation using InputValidator
	 * 4. Calls adminRepo.changePassword() which:
	 *    - Verifies old password matches current hashed password
	 *    - Hashes new password
	 *    - Updates password in database
	 * 5. If successful, updates dateLastPasswordChange to current date
	 * 6. Returns true if change successful, false if error or cancelled
	 * 7. Catches SQLException and displays error message
	 * 
	 * Note: If isForced=true and change fails, login will be cancelled
	 * 
	 * @param admin the admin user changing password
	 * @param isForced true if password change is mandatory, false if voluntary
	 * @return true if password change was successful, false otherwise
	 * @throws SQLException if there are errors updating the password in the database
	 */
	private boolean changePassword(Admin admin, boolean isForced) 
	{
		if (isForced)
			Console.print("\n=== MANDATORY PASSWORD CHANGE ===");
		else
			Console.print("\n=== CHANGE PASSWORD ===");	    
		Console.print("Enter current password:");
		String oldPw = Console.readString();	    
		String newPw = InputValidator.readPasswordWithConfirmation();	    
		try
		{
			adminRepo.changePassword(admin.getId(), oldPw, newPw);	
			admin.setDateLastPasswordChange(LocalDate.now());	        
			Console.print("Change completed successfully");
			return true;
		}
		catch (SQLException e)
		{
			Console.print(e.getMessage());
			return false;
		}
	}

	/**
	 * Inserts a new Admin into the system.
	 * 
	 * Behavior differs based on isFirst parameter:
	 * 
	 * If isFirst = true (first admin setup):
	 * 1. Displays special "FIRST ADMIN SETUP" header
	 * 2. Explains that no administrators exist in system
	 * 3. Collects admin data from console
	 * 4. Validates all fields
	 * 5. If invalid, displays errors and calls System.exit(1) to terminate app
	 * 6. If valid, inserts admin and displays success message with credentials
	 * 7. Calls System.exit(0) to gracefully terminate (user must restart to login)
	 * 
	 * If isFirst = false (regular admin insertion):
	 * 1. Collects admin data from console
	 * 2. Validates all fields
	 * 3. If invalid, displays errors and returns (does not exit app)
	 * 4. If valid, inserts admin and displays success message
	 * 5. Returns to admin menu
	 * 
	 * In both cases, catches SQLException and handles errors appropriately
	 * 
	 * @param isFirst true if this is the first admin in the system, false otherwise
	 */
	public static void insertAdmin(boolean isFirst)
	{
		try
		{
			if (isFirst)
			{
				Console.print("\n=== FIRST ADMIN SETUP ===");
				Console.print("No administrators found in the system.");
				Console.print("Please create the first admin account.\n");
			}
			Admin admin = Collect.collectAdminData();
			if (!admin.isValid())
			{
				Console.print("Error: invalid admin data!");
				for (String error : admin.getErrors())
					Console.print("- " + error);
				if (isFirst)
					System.exit(1);
				return;
			}
			adminRepo.insert(admin);
			if (isFirst)
			{
				Console.print("\nFirst admin created successfully!");
				Console.print("You can now restart the application and login.");
				Console.print("Email: " + admin.getEmail());
				Console.print("Name: " + admin.getFirstName() + " " + admin.getLastName());
				System.exit(0);
			}
			else
				Console.print("Admin inserted successfully!");
		}
		catch (SQLException e)
		{
			Console.print("Error inserting admin: " + e.getMessage());
			e.printStackTrace();
			if (isFirst)
				System.exit(1);
		}
	}
}
