package com.generation.pl.view;

import java.util.List;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Teacher;
import com.generation.library.Template;
import com.generation.pl.model.entities.Lesson;

/**
 * Factory per la gestione centralizzata di tutte le view dell'applicazione.
 * 
 * Cosa fa: Crea e fornisce i template corretti in base a cosa devo visualizzare
 * (studente/insegnante/lezione), in quale formato (txt/html) e per quale scopo
 * (dettaglio/riga/card/receipt).
 * 
 * Pattern usato: Factory + Singleton (le view sono static final, create una sola volta)
 */
public class ViewFactory
{
    // ========== TEMPLATE TXT ==========
    // STUDENT VIEWS - Template per visualizzare studenti in formato testo
    private static final ReflectionView studentDetailTxt = new ReflectionView("template/txt/student/student_detail.txt");
    private static final ReflectionView studentRowTxt = new ReflectionView("template/txt/student/student_row.txt");    
    // TEACHER VIEWS - Template per visualizzare insegnanti in formato testo
    private static final ReflectionView teacherDetailTxt = new ReflectionView("template/txt/teacher/teacher_detail.txt");
    private static final ReflectionView teacherRowTxt = new ReflectionView("template/txt/teacher/teacher_row.txt");    
    // LESSON VIEWS - Template per visualizzare lezioni in formato testo
    private static final ReflectionView lessonDetailTxt = new ReflectionView("template/txt/lessons/lesson_detail.txt");
    private static final ReflectionView lessonReceiptTxt = new ReflectionView("template/txt/lessons/lesson_receipt.txt");
    private static final ReflectionView lessonRowTxt = new ReflectionView("template/txt/lessons/lesson_row.txt");

    // ========== TEMPLATE HTML ==========
    // STUDENT VIEWS - Template per visualizzare studenti in HTML
    private static final ReflectionView studentRowHtml = new ReflectionView("template/html/student/student_row.html");
    private static final ReflectionView studentWelcomeCardHtml = new ReflectionView("template/html/student/student_welcome_card.html");
    // TEACHER VIEWS - Template per visualizzare insegnanti in HTML
    private static final ReflectionView teacherCardHtml = new ReflectionView("template/html/teacher/teacher_card.html");
    private static final ReflectionView teacherRowHtml = new ReflectionView("template/html/teacher/teacher_row.html");
    // LESSON VIEWS - Template per visualizzare lezioni in HTML
    private static final ReflectionView lessonReceiptHtml = new ReflectionView("template/html/lessons/lesson_receipt.html");
    private static final ReflectionView lessonRowHtml = new ReflectionView("template/html/lessons/lesson_row.html");
    
    /**
     * Factory method: restituisce la view corretta in base ai parametri.
     * 
     * Uso: invece di chiamare direttamente studentDetailTxt, chiamo 
     * make("student", "txt", "detail") e lui mi dà quella giusta.
     * Vantaggio: codice più flessibile e centralizzato.
     *
     * @param entity tipo di entità ("student", "teacher", "lesson")
     * @param format formato di output ("txt", "html")
     * @param purpose scopo della view ("detail", "welcome", "card", "receipt", "row")
     * @return l'istanza di ReflectionView appropriata
     * @throws IllegalArgumentException se la combinazione richiesta non esiste
     */
    public static ReflectionView make(String entity, String format, String purpose)
    {
        // Albero di decisione: prima controllo l'entità, poi il formato, poi lo scopo
        if (entity.equals("student"))
        {
            if (format.equals("txt"))
            {
                if (purpose.equals("detail")) 
                    return studentDetailTxt;
                if (purpose.equals("row")) 
                    return studentRowTxt;
            }
            else if (format.equals("html"))
            {
                if (purpose.equals("welcome")) 
                    return studentWelcomeCardHtml;
                if (purpose.equals("row")) 
                    return studentRowHtml;
            }
        }
        else if (entity.equals("teacher"))
        {
            if (format.equals("txt"))
            {
                if (purpose.equals("detail")) 
                    return teacherDetailTxt;
                if (purpose.equals("row")) 
                    return teacherRowTxt;
            }
            else if (format.equals("html"))
            {
                if (purpose.equals("card")) 
                    return teacherCardHtml;
                if (purpose.equals("row")) 
                    return teacherRowHtml;
            }
        }
        else if (entity.equals("lesson"))
        {
            if (format.equals("txt"))
            {
                if (purpose.equals("detail")) 
                    return lessonDetailTxt;
                if (purpose.equals("receipt")) 
                    return lessonReceiptTxt;
                if (purpose.equals("row")) 
                    return lessonRowTxt;
            }
            else if (format.equals("html"))
            {
                if (purpose.equals("receipt"))
                    return lessonReceiptHtml;
                if (purpose.equals("row")) 
                    return lessonRowHtml;
            }
        }
        // Se arrivo qui significa che la combinazione richiesta non è gestita
        throw new IllegalArgumentException("Combinazione entity/format/purpose non supportata: " 
            + entity + " / " + format + " / " + purpose);
    }
    
    // ========== METODI PER LISTE COMPLETE (header + rows + footer) ==========
    /**
     * Renderizza una lista completa di studenti in formato TXT.
     * Pattern: cicla sulla lista, crea una riga per ogni studente, 
     * poi inserisce tutte le righe nel template wrapper.
     */
    public static String renderStudentsListTXT(List<Student> students)
    {
        StringBuilder rows = new StringBuilder();
        // Creo una riga TXT per ogni studente e le accumulo in rows
        for(Student student : students)
            rows.append(studentRowTxt.render(student));
        // Carico il template completo e sostituisco i placeholder
        return Template.load("template/txt/student/students_list.txt")
            .replace("[studentRows]", rows.toString())
            .replace("[totalStudents]", "" + students.size());
    }

    /**
     * Versione HTML del metodo precedente.
     * Stessa logica, template diversi.
     */
    public static String renderStudentsListHTML(List<Student> students)
    {
        StringBuilder rows = new StringBuilder();
        for(Student student : students)
            rows.append(studentRowHtml.render(student));
        return Template.load("template/html/student/students_list.html")
            .replace("[studentRows]", rows.toString())
            .replace("[totalStudents]", "" + students.size());
    }

    /**
     * Renderizza lista insegnanti in TXT.
     * Stesso pattern di renderStudentsListTXT ma per Teacher.
     */
    public static String renderTeachersListTXT(List<Teacher> teachers)
    {
        StringBuilder rows = new StringBuilder();
        for (Teacher teacher : teachers)
            rows.append(teacherRowTxt.render(teacher));
        return Template.load("template/txt/teacher/teachers_list.txt")
            .replace("[teacherRows]", rows.toString())
            .replace("[totalTeachers]", "" + teachers.size());
    }

    /**
     * Versione HTML per lista insegnanti.
     */
    public static String renderTeachersListHTML(List<Teacher> teachers)
    {
        StringBuilder rows = new StringBuilder();
        for (Teacher teacher : teachers)
            rows.append(teacherRowHtml.render(teacher));        
        return Template.load("template/html/teachers_list.html")
            .replace("[teacherRows]", rows.toString())
            .replace("[totalTeachers]", "" + teachers.size());
    }

    /**
     * Renderizza lista lezioni in TXT.
     */
    public static String renderLessonsListTXT(List<Lesson> lessons)
    {
        StringBuilder rows = new StringBuilder();
        for (Lesson lesson : lessons)
            rows.append(lessonRowTxt.render(lesson));        
        return Template.load("template/txt/lessons_list.txt")
            .replace("[lessonRows]", rows.toString())
            .replace("[totalLessons]", "" + lessons.size());
    }

    /**
     * Renderizza lista lezioni in HTML.
     */
    public static String renderLessonsListHTML(List<Lesson> lessons)
    {
        StringBuilder rows = new StringBuilder();
        for (Lesson lesson : lessons)
            rows.append(lessonRowHtml.render(lesson));        
        return Template.load("template/html/lessons_list.html")
            .replace("[lessonRows]", rows.toString())
            .replace("[totalLessons]", "" + lessons.size());
    }
}
