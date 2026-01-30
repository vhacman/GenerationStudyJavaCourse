package com.generation.pl.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.repository.SQLRepository.LessonRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.StudentRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.TeacherRepositorySQL;
import com.generation.pl.model.repository.interfaces.LessonRepository;
import com.generation.pl.model.repository.interfaces.StudentRepository;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.pl.view.ViewController;

public class StudentMain
{
    private static StudentRepository studentRepo = Context.getDependency(StudentRepositorySQL.class);
    private static TeacherRepository teacherRepo = Context.getDependency(TeacherRepositorySQL.class);
    private static LessonRepository  lessonRepo  = Context.getDependency(LessonRepositorySQL.class);

    private Student loggedUser;

    public void run()
    {
        // Login con max 3 tentativi
        loggedUser = login();
        if (loggedUser == null)
        {
            Console.print("Login fallito. Arrivederci.");
            return;
        }
        Console.print("Benvenuto " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
        // Loop menu principale
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
     * Visualizza il menu student caricandolo da template
     */
    private static void displayMenu()
    {
    	String menu = Template.load("template/txt/menu/student_menu.txt");
    		if (menu == null || menu.isEmpty())
				Console.print("Menu non trovato");
        	else
        		Console.print(menu);
    }

    /**
     * Login con massimo 3 tentativi
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
                    Console.print("Dati non validi");

                count++;
            }

            // Se login fallito dopo 3 tentativi
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
     * Cerca teacher per materia e stampa i risultati usando ViewController
     */
    private void searchTeacher()
    {
        try
        {
            Console.print("\n=== RICERCA TEACHER PER MATERIA ===");
            Console.print("Inserisci materia da cercare:");
            Console.print("Materie disponibili: JAVA, SQL, PROGRAMMING, MATH, ENGLISH, COMPUTERSCIENCE");
            String subject = Console.readString().trim().toUpperCase();
            // Cerca teacher che insegnano quella materia
            List<Teacher> teachers = teacherRepo.findTeacherBySubject(subject);
            if (teachers.isEmpty())
            {
                Console.print("Nessun teacher trovato per la materia: " + subject);
                return;
            }
            Console.print("\n=== TEACHER TROVATI ===\n");
            // Usa ViewController.teacherBasicView per renderizzare ogni teacher
            for (Teacher teacher : teachers)
            {
                String output = ViewController.teacherBasicView.render(teacher);
                Console.print(output);
                // Stampa anche la bio se presente
                if (teacher.getBio() != null && !teacher.getBio().isEmpty())
                    Console.print("  Bio: " + teacher.getBio() + "\n");
            }
            Console.print("\nTotale teacher trovati: " + teachers.size());
        }
        catch (SQLException e)
        {
            Console.print("Errore durante la ricerca: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void bookLesson()
    {
        try
        {
            Console.print("\n=== PRENOTA LEZIONE ===");
            // Chiedi ID teacher
            Console.print("Inserisci ID del teacher:");
            int teacherId = Console.readInt();
            // Recupera teacher dal database
            Teacher teacher = teacherRepo.findById(teacherId, false);
            if (teacher == null)
            {
                Console.print("Teacher non trovato!");
                return;
            }
            // Usa teacherBasicView per mostrare il teacher selezionato
            Console.print("\nTeacher selezionato:");
            Console.print(ViewController.teacherBasicView.render(teacher));
            // Chiedi data
            Console.print("Inserisci data lezione (formato YYYY-MM-DD):");
            String dateString = Console.readString();
            LocalDate lessonDate = LocalDate.parse(dateString);
            // *** VALIDAZIONE CRITICA ***
            LocalDate minimumDate = LocalDate.now().plusDays(1);
            if (lessonDate.isBefore(minimumDate))
            {
                Console.print("ERRORE: Le prenotazioni devono essere fatte almeno 1 giorno prima!");
                Console.print("Data minima accettata: " + minimumDate);
                return;
            }
            // Chiedi ora
            Console.print("Inserisci ora lezione (9-18):");
            int hour = Console.readInt();
            if (hour < 9 || hour > 18)
            {
                Console.print("ERRORE: L'ora deve essere tra 9 e 18!");
                return;
            }
            // Crea la lezione
            Lesson lesson = new Lesson();
            lesson.setDate(lessonDate);
            lesson.setHour(hour);
            lesson.setStudent(loggedUser);
            lesson.setTeacher(teacher);
            lesson.setPrice(teacher.getPricePerLesson());
            // Valida la lezione
            if (!lesson.isValid())
            {
                Console.print("Errore: dati della lezione non validi!");
                for (String error : lesson.getErrors())
                    Console.print("- " + error);
                return;
            }
            // Inserisci nel database
            Lesson insertedLesson = lessonRepo.insert(lesson);
            // Usa lessonBasicView per mostrare la lezione prenotata
            Console.print("\n Lezione prenotata con successo!");
            Console.print(ViewController.lessonBasicView.render(insertedLesson));
        }
        catch (SQLException e)
        {
            Console.print("Errore durante la prenotazione: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Console.print("Errore formato dati: " + e.getMessage());
        }
    }
 
    private void printHistory()
    {
        try
        {
            Console.print("\n=== STORICO LEZIONI ===\n");
            // Recupera le lezioni dello studente
            List<Lesson> lessons = lessonRepo.findWhere("studentid = " + loggedUser.getId());
            if (lessons.isEmpty())
            {
                Console.print("Nessuna lezione trovata.");
                return;
            }
            int totalSpent = 0;
            // Usa lessonBasicView per stampare ogni lezione
            for (Lesson lesson : lessons)
            {
                Console.print(ViewController.lessonBasicView.render(lesson));
                totalSpent += lesson.getPrice();
            }
            Console.print("==============================================");
            Console.print("Totale lezioni: " + lessons.size());
            Console.print("TOTALE SPESO: €" + totalSpent);
        }
        catch (SQLException e)
        {
            Console.print("Errore durante il recupero dello storico: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Genera una ricevuta HTML per una lezione specifica usando ViewController
     */
    private void generateReceipt()
    {
        try
        {
            Console.print("\n=== GENERA RICEVUTA ===");
            Console.print("Inserisci ID della lezione:");
            int lessonId = Console.readInt();
            // Recupera la lezione
            Lesson lesson = lessonRepo.findById(lessonId);
            if (lesson == null)
            {
                Console.print("Lezione non trovata!");
                return;
            }
            // Verifica che la lezione appartenga allo studente loggato
            if (lesson.getStudent().getId() != loggedUser.getId())
            {
                Console.print("ERRORE: Non puoi generare ricevuta per lezioni di altri studenti!");
                return;
            }
            // Usa ViewController per generare l'HTML della ricevuta
            Teacher teacher = lesson.getTeacher();
            String studentName = loggedUser.getFirstName() + " " + loggedUser.getLastName();
            String teacherName = teacher.getFirstName() + " " + teacher.getLastName();
            String html = ViewController.renderLessonReceipt(lesson, studentName, teacherName);
            String filename = "ricevuta_lesson_" + lessonId + ".html";
            FileWriter.writeTo(filename, html);
            Console.print("\n Ricevuta generata con successo!");
            Console.print("File salvato: " + filename);
        }
        catch (SQLException e)
        {
            Console.print("Errore database: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Console.print("Errore: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gestisce il cambio password dello Student loggato
     */
    private void changePassword()
    {
        Console.print("\n=== CAMBIO PASSWORD ===");
        Console.print("Inserire vecchia password:");
        String oldPw = Console.readString();
        Console.print("Inserire nuova password:");
        String newPw = Console.readString();
        Console.print("Confermare nuova password:");
        String confirmPw = Console.readString();
        // Validazione: le due password devono coincidere
        if (!newPw.equals(confirmPw))
        {
            Console.print("ERRORE: Le password non coincidono!");
            return;
        }
        try
        {
            // Tenta di cambiare la password nel database
            studentRepo.changePassword(loggedUser.getId(), oldPw, newPw);
            Console.print("Password modificata con successo!");
        }
        catch (SQLException e)
        {
            // Mostra l'errore se la vecchia password è sbagliata o le password sono uguali
            Console.print("ERRORE: " + e.getMessage());
        }
    }
}
