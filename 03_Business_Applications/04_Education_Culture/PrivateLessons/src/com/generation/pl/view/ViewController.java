package com.generation.pl.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.generation.library.Template;
import com.generation.library.view.EntityView;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Teacher;

public class ViewController
{
    // ========== RICEVUTA LEZIONE HTML (StudentMain punto 4) ==========

    /**
     * Crea view per ricevuta lezione HTML con parametri studentName e teacherName.
     * USATO IN: StudentMain.generateReceipt()
     */
    public static EntityView<Lesson> createLessonReceiptViewHTML(String studentName, String teacherName)
    {
        return lesson -> Template.load("template/html/lessons/lesson_receipt.html")
            .replace("[id]",          "" + lesson.getId())
            .replace("[studentName]", studentName)
            .replace("[teacherName]", teacherName)
            .replace("[date]",        "" + lesson.getDate())
            .replace("[hour]",        "" + lesson.getHour())
            .replace("[price]",       "" + lesson.getPrice());
    }
    
    /**
     * Shortcut per renderizzare ricevuta lezione HTML.
     * 
     * Cosa fa: Metodo di convenienza che combina creazione view + rendering in un solo passo.
     * Invece di scrivere createLessonReceiptViewHTML(...).render(...) ogni volta,
     * basta chiamare questo metodo.
     * 
     * USATO IN: StudentMain.generateReceipt()
     */
    public static String renderLessonReceipt(Lesson lesson, String studentName, String teacherName)
    {
        return createLessonReceiptViewHTML(studentName, teacherName).render(lesson);
    }

    public static EntityView<Lesson> createLessonReceiptViewTXT(String studentName, String teacherName)
    {
        return lesson -> Template.load("template/txt/lesson_receipt.txt")
            .replace("[id]",          "" + lesson.getId())
            .replace("[studentName]", studentName)
            .replace("[teacherName]", teacherName)
            .replace("[date]",        "" + lesson.getDate())
            .replace("[hour]",        "" + lesson.getHour())
            .replace("[price]",       "" + lesson.getPrice());
    }

    // ========== VIEW BASIC PER LOG CONSOLE ==========

    public static EntityView<Student> studentBasicView = student ->
        student.getId()             + " - " + 
        student.getFirstName()      + " "   + student.getLastName() +  " (" +
        student.getEmail()          + ") [" + student.getDob()      + "]\n";
    
    /**
     * View inline per teacher: usata per stampare lista teacher trovati.
     * USATO IN: StudentMain.searchTeacher() - punto 1
     * ESEMPIO OUTPUT: "1 - Prof. Maria Conti (maria.conti@email.com) - €35/h - [MATH, SQL]"
     */
    public static EntityView<Teacher> teacherBasicView = teacher ->
        teacher.getId()                 + " - Prof. "  + 
        teacher.getFirstName()          + " "          +
        teacher.getLastName()           + " ("         +
        teacher.getEmail()              + ") - "      + 
        teacher.getPricePerLesson()     + "/h - ["     +
        teacher.getSubjectsCSV()        + "]\n";

    /**
     * View inline per lezioni: usata per stampare storico lezioni.
     * USATO IN:
     * - StudentMain.printHistory() - punto 3
     * - TeacherMain.printLessonHistory() - punto 1
     * - TeacherMain.printNextWeekLessons() - punto 2
     * ESEMPIO OUTPUT: "Lezione #1 - 2026-02-01 ore 10 - €35"
     */
    public static EntityView<Lesson> lessonBasicView = lesson ->
        "Lezione #"         + 
        lesson.getId()      + " - "     + 
        lesson.getDate()    + " at: "   +
        lesson.getHour()    + " - "    + 
        lesson.getPrice()   + "\n";
    
    // ========== EARNINGS REPORTS ==========
    
    /**
     * Renderizza earnings report in TXT per teacher.
     * USATO IN: TeacherMain.printEarnings()
     */
    public static String renderEarningsReport(Teacher teacher, LocalDate startDate, LocalDate endDate, int earnings) 
    {
        return Template.load("template/txt/teacher/earnings_report.txt")
            .replace("[teacherName]", teacher.getFirstName() + " " + teacher.getLastName())
            .replace("[startDate]", startDate.toString())
            .replace("[endDate]", endDate.toString())
            .replace("[totalEarnings]", "€" + earnings);
    }
    
    /**
     * Renderizza earnings report in HTML per teacher.
     * Usa il template esistente: template/html/teachers/earnings_report.html
     * USATO IN: TeacherMain.printEarnings()
     */
    public static String renderEarningsReportHTML(Teacher teacher, LocalDate startDate, LocalDate endDate, int earnings)
    {
        // Timestamp corrente per footer
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        
        String teacherName = teacher.getFirstName() + " " + teacher.getLastName();
        
        return Template.load("template/html/teacher/earnings_report.html")
            .replace("[teacherName]", teacherName)
            .replace("[startDate]", startDate.format(dateFormat))
            .replace("[endDate]", endDate.format(dateFormat))
            .replace("[totalEarnings]", "" + earnings)
            .replace("[date]", now.format(dateFormat))
            .replace("[time]", now.format(timeFormat));
    }
}
