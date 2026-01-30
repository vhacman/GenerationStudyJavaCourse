package com.generation.pl.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.context.Context;
import com.generation.library.Console;
import com.generation.library.Template;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.repository.interfaces.TeacherRepository;
import com.generation.pl.view.ViewFactory;
import com.generation.pl.view.ViewController;

public class TeacherMain 
{
    TeacherRepository teacherRepo = Context.getDependency(TeacherRepository.class);
    Teacher loggedUser;
    
    public void run() 
    {
        loggedUser = login();
        if (loggedUser == null)
        {
            Console.print("Login fallito. Arrivederci.");
            return;
        }
        
        // Usa view per messaggio di benvenuto personalizzato
        String welcomeCard = ViewFactory.make("teacher", "html", "card").render(loggedUser);
        Console.print(welcomeCard);
        
        boolean exit = false;
        while(!exit)
        {
            // Carica menu da template
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
                    Console.print("Arrivederci!");
                    exit = true;
                    break;
                default:
                    Console.print("Opzione non valida. Riprova.");
            }
        }
    }
    
    /**
     * Stampa lo storico completo delle lezioni del teacher loggato
     */
    private void printLessonHistory()
    {
        try
        {
            Console.print("\n=== STORICO LEZIONI ===");
            List<Lesson> lessons = teacherRepo.findLessonsByTeacherId(loggedUser.getId());
            
            if (lessons.isEmpty())
            {
                Console.print("Nessuna lezione trovata.");
                return;
            }
            
            // Usa ViewFactory per renderizzare la lista completa in TXT
            String lessonsList = ViewFactory.renderLessonsListTXT(lessons);
            Console.print(lessonsList);
        }
        catch (SQLException e)
        {
            Console.print("Errore nel recupero delle lezioni: " + e.getMessage());
        }
    }
    
    /**
     * Stampa le lezioni della prossima settimana
     */
    private void printNextWeekLessons()
    {
        try
        {
            Console.print("\n=== LEZIONI PROSSIMA SETTIMANA ===");
            List<Lesson> lessons = teacherRepo.findNextWeekLessonsByTeacherId(loggedUser.getId());
            
            if (lessons.isEmpty())
            {
                Console.print("Nessuna lezione programmata per la prossima settimana.");
                return;
            }
            
            // Usa ViewController per view base inline
            Console.print("Hai " + lessons.size() + " lezione/i in programma:\n");
            for (Lesson lesson : lessons)
            {
                Console.print(ViewController.lessonBasicView.render(lesson));
            }
        }
        catch (SQLException e)
        {
            Console.print("Errore nel recupero delle lezioni: " + e.getMessage());
        }
    }
    
    /**
     * Calcola e stampa i guadagni in un periodo specifico
     */
    private void printEarnings()
    {
        try
        {
            Console.print("\n=== CALCOLO GUADAGNI ===");
            Console.print("Inserire data inizio (YYYY-MM-DD):");
            String startDateStr = Console.readString().trim();
            LocalDate startDate = LocalDate.parse(startDateStr);
            
            Console.print("Inserire data fine (YYYY-MM-DD):");
            String endDateStr = Console.readString().trim();
            LocalDate endDate = LocalDate.parse(endDateStr);
            
            int earnings = teacherRepo.calculateEarningsByTeacherIdAndPeriod(
                loggedUser.getId(), 
                startDate, 
                endDate
            );
            
            // Usa template per visualizzare i guadagni
            String earningsReport = Template.load("template/txt/teacher/earnings_report.txt")
                .replace("[teacherName]", loggedUser.getFirstName() + " " + loggedUser.getLastName())
                .replace("[startDate]", startDateStr)
                .replace("[endDate]", endDateStr)
                .replace("[totalEarnings]", "€" + earnings);
            
            Console.print(earningsReport);
        }
        catch (SQLException e)
        {
            Console.print("Errore nel calcolo dei guadagni: " + e.getMessage());
        }
        catch (Exception e)
        {
            Console.print("Formato data non valido. Usa YYYY-MM-DD");
        }
    }
    
    /**
     * Gestisce il cambio password del Teacher loggato
     */
    private void changePassword() 
    {
        Console.print("\n=== CAMBIO PASSWORD ===");
        Console.print("Inserire vecchia password:");
        String oldPw = Console.readString();
        
        String newPw = Collect.collectPasswordWithConfirmation();
        
        try
        {
            teacherRepo.changePassword(loggedUser.getId(), oldPw, newPw);
            Console.print("✓ Password modificata con successo!");
        }
        catch(SQLException e)
        {
            Console.print("✗ " + e.getMessage());
        }
    }
    
    /**
     * Gestisce il processo di login con massimo 3 tentativi
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
                    Console.print("Dati non validi");
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
