package com.generation.pl.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.library.Template;
import com.generation.pl.model.entities.Admin;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.User;
import com.generation.pl.model.entities.UserStatus;
import com.generation.pl.model.repository.SQLRepository.AdminRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.LessonRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.StudentRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.TeacherRepositorySQL;
import com.generation.pl.model.repository.interfaces.AdminRepository;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.StudentRepository;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.pl.view.ViewController;

public class AdminMain
{
	private static AdminRepository 		adminRepo = Context.getDependency(AdminRepositorySQL.class);
	private static TeacherRepository 	teacherRepo = Context.getDependency(TeacherRepositorySQL.class);
	private static StudentRepository 	studentRepo = Context.getDependency(StudentRepositorySQL.class);
	private static LessonRepository 	lessonRepo = Context.getDependency(LessonRepositorySQL.class);

	Admin loggedUser;

	public void run()
	{
		loggedUser = login();
		if (loggedUser == null)
		{
			Console.print("Login fallito. Arrivederci.");
			return;
		}
		Console.print("Benvenuto " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
		// Controllo cambio password obbligatorio
		if (loggedUser.needsPasswordChange())
		{
			Console.print("\nDevi cambiare la password prima di procedere.");
			if (!changePassword(loggedUser, true))
			{
				Console.print("Operazione annullata.");
				return;
			}
		}
		// Loop menu principale
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
				case "q":
					Console.print("Arrivederci!");
					break;
				default:
					Console.print("Opzione non valida. Riprova.");
					break;
			}
		}
	}

	/**
	 * Visualizza il menu admin caricandolo da template
	 */
	private static void displayMenu()
	{
		String menu = Template.load("template/txt/menu/admin_menu.txt");
		if (menu == null || menu.isEmpty())
			Console.print("Menu non trovato");
		else
			Console.print(menu);
	}

	/**
     * Login con massimo 3 tentativi
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
                    Console.print("Dati non validi");                
                count++;
            }           
            // Se login fallito dopo 3 tentativi
            if (res == null)
                return null;
         // CONTROLLO SCADENZA PASSWORD (2 settimane)
            LocalDate lastChange = res.getDateLastPasswordChange();
            LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);
            if(lastChange.isBefore(twoWeeksAgo))
            {
            	Console.print("⚠️ Password scaduta! Devi cambiarla prima di accedere! ⚠️");            	
            	if (!changePassword(res, true))
            		Console.print("Login annullato"); return null;     
            }
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }	
    
	private void insertTeacher()
	{
		try
		{
			Teacher teacher = Collect.collectTeacherData();
	        if (!teacher.isValid())
	        {
	            Console.print("Errore: dati del professore non validi!");
	            for (String error : teacher.getErrors())
	                Console.print("- " + error);
	            return;
	        }
			Teacher inserted = teacherRepo.insert(teacher);
			Console.print("Professore inserito con successo!");
			Console.print(ViewController.teacherBasicView.render(inserted));
		}
		catch (SQLException e)
		{
			Console.print("Errore durante l'inserimento del teacher: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void insertStudent()
	{
	    try
	    {
	    	Student student = Collect.collectStudentData();
	        if (!student.isValid())
	        {
	            Console.print("Errore: dati dello studente non validi!");
	            for (String error : student.getErrors())
	                Console.print("- " + error);
	            return;
	        }
	        Student insertedStudent = studentRepo.insert(student);
	        Console.print("Student inserito con successo!");
	        Console.print(ViewController.studentBasicView.render(insertedStudent));
	    }
	    catch (SQLException e)
	    {
	        Console.print("Errore durante l'inserimento dello studente: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	/**
	 * Cambia lo status di un utente in base al tipo specificato
	 * @param userType "teacher", "student" o "admin"
	 */
	private void changeStatusUser(String userType)
	{
		try
		{
			User user = null;
			String entityType = "";
			// Trova l'utente in base al tipo (flag)
			if (userType.equalsIgnoreCase("teacher"))
			{
				Console.print("\n=== CAMBIO STATUS TEACHER ===");
				Console.print("Inserisci ID Teacher:");
				int id = Console.readInt();

				user = teacherRepo.findById(id, false);
				entityType = "Teacher";
			}
			else if (userType.equalsIgnoreCase("student"))
			{
				Console.print("\n=== CAMBIO STATUS STUDENT ===");
				Console.print("Inserisci ID Student:");
				int id = Console.readInt();

				user = studentRepo.findById(id, false);
				entityType = "Student";
			}
			else if (userType.equalsIgnoreCase("admin"))
			{
				Console.print("\n=== CAMBIO STATUS ADMIN ===");
				Console.print("Inserisci ID Admin:");
				int id = Console.readInt();

				user = adminRepo.findById(id);
				entityType = "Admin";
			}
			else
			{
				Console.print("Tipo di utente non valido!");
				return;
			}

			if (user == null)
			{
				Console.print(entityType + " non trovato!");
				return;
			}
			Console.print("\n" + entityType + " trovato:");
			if (user instanceof Teacher)
				Console.print(ViewController.teacherBasicView.render((Teacher)user));
			else if (user instanceof Student)
				Console.print(ViewController.studentBasicView.render((Student)user));
			changeStatus(user);
		}
		catch (SQLException e)
		{
			Console.print("Errore: " + e.getMessage());
		}
	}

	/**
	 * Metodo unificato per cambiare lo status di un utente
	 */
	private void changeStatus(User user)
	{
	    try
	    {
	        String entityType;
	        // Identifichiamo il tipo di entità per i messaggi a video
	        if (user instanceof Admin)
	            entityType = "Admin";
	        else if (user instanceof Teacher)
	            entityType = "Teacher";
	        else if (user instanceof Student)
	            entityType = "Student";
	        else
	        {
	            Console.print("Tipo di user non supportato");
	            return;
	        }	        
	        Console.print(" === CAMBIO STATUS === ");
	        Console.print("ID: " + user.getId());
	        Console.print("Nome: " + user.getFirstName() + " " + user.getLastName());
	        Console.print("Status attuale: " + user.getStatus());	        
	        UserStatus newStatus = Collect.collectStatus();	        
	        //VALIDAZIONE PRIMA DELLA CHIAMATA AL REPOSITORY
	        if (user.getStatus() == newStatus)
	        {
	            Console.print("Nessuna modifica: lo status era già " + newStatus);
	            return;
	        }	        
	        // Chiamata al repository corretto in base all'istanza
	        if (user instanceof Admin) 
	            adminRepo.changeStatus(user.getId(), newStatus);
	        else if (user instanceof Teacher) 
	            teacherRepo.changeStatus(user.getId(), newStatus);
	        else if (user instanceof Student)
	            studentRepo.changeStatus(user.getId(), newStatus);	        
	        Console.print("Status di " + entityType + " aggiornato con successo a " + newStatus);
	    }
	    catch (SQLException e)
	    {
	        Console.print("Errore durante il cambio status: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	private void teacherEarnings()
	{
		try
		{
			Console.print("\n=== GUADAGNI TEACHER (ultimi 30 giorni) ===");
			Console.print("Inserisci ID Teacher:");
			int teacherId = Console.readInt();
			Teacher teacher = teacherRepo.findById(teacherId, false);
			if (teacher == null)
			{
				Console.print("Teacher non trovato!");
				return;
			}
			Console.print("\nTeacher:");
			Console.print(ViewController.teacherBasicView.render(teacher));
			int earnings = lessonRepo.calculateEarningsByTeacherLast30Days(teacherId);
			Console.print("Guadagni totali ultimi 30 giorni: €" + earnings);
		}
		catch (SQLException e)
		{
			Console.print("Errore: " + e.getMessage());
		}
	}

	private void subjectEarnings()
	{
		try
		{
			Console.print("\n=== GUADAGNI PER MATERIA (ultimi 30 giorni) ===");
			Console.print("Inserisci nome materia:");
			String subject = Console.readString().trim();

			int earnings = lessonRepo.calculateEarningsBySubjectLast30Days(subject);
			Console.print("\nMateria: " + subject);
			Console.print("Guadagni totali ultimi 30 giorni: €" + earnings);
		}
		catch (SQLException e)
		{
			Console.print("Errore: " + e.getMessage());
		}
	}
	
	/**
	 * Gestisce il cambio password dell'Admin loggato
	 */
	private boolean changePassword(Admin admin, boolean isForced) 
	{
	    if (isForced)
	        Console.print("\n=== CAMBIO PASSWORD OBBLIGATORIO ===");
	    else
	        Console.print("\n=== CAMBIO PASSWORD ===");	    
	    Console.print("Inserire password attuale:");
	    String oldPw = Console.readString();	    
	    String newPw = Collect.collectPasswordWithConfirmation();	    
	    try
	    {
	        // Tenta di cambiare la password nel database
	        adminRepo.changePassword(admin.getId(), oldPw, newPw);	        
	        // Aggiorna la data di ultimo cambio password
	        admin.setDateLastPasswordChange(LocalDate.now());	        
	        Console.print("Modifica eseguita con successo");
	        return true;
	    }
	    catch (SQLException e)
	    {
	        // Mostra l'errore se la vecchia password è sbagliata o le password sono uguali
	        Console.print(e.getMessage());
	        return false;
	    }
	}

	/**
	 * Inserisce un nuovo Admin nel sistema
	 * @param isFirst true se è il primo admin del sistema, false altrimenti
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
	            Console.print("Errore: dati dell'admin non validi!");
	            for (String error : admin.getErrors())
	                Console.print("- " + error);
	            if (isFirst)
	                System.exit(1);
	            return;
	        }
	        adminRepo.insert(admin);
	        if (isFirst)
	        {
	            Console.print("\nPrimo admin creato con successo!");
	            Console.print("Ora puoi riavviare l'applicazione e fare il login.");
	            Console.print("Email: " + admin.getEmail());
	            Console.print("Nome: " + admin.getFirstName() + " " + admin.getLastName());
	            System.exit(0);
	        }
	        else
	            Console.print("Admin inserito con successo!");
	    }
	    catch (SQLException e)
	    {
	        Console.print("Errore durante l'inserimento dell'admin: " + e.getMessage());
	        e.printStackTrace();
	        if (isFirst)
	            System.exit(1);
	    }
	}



}
