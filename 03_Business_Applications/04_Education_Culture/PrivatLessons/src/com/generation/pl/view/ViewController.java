package com.generation.pl.view;

import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Student; 
import com.generation.library.Template;
import com.generation.library.view.EntityView;

public class ViewController
{
    // ========== RICEVUTA LEZIONE HTML (StudentMain punto 4) ==========

    /**
     * Crea view per ricevuta lezione HTML con parametri studentName e teacherName.
     * USATO IN: StudentMain.generateReceipt()
     */
    public static EntityView<Lesson> createLessonReceiptViewHTML(String studentName, String teacherName)
    {
        return lesson -> Template.load("template/html/lessons/lesson_recipt.html")
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
													        student.getId()				+ " - " + 
													        student.getFirstName() 		+ " " 	+ student.getLastName() +  " (" +
													        student.getEmail() 			+ ") [" + student.getDob() 		+ "]\n"	;
    
    /**
     * View inline per teacher: usata per stampare lista teacher trovati.
     * USATO IN: StudentMain.searchTeacher() - punto 1
     * ESEMPIO OUTPUT: "1 - Prof. Maria Conti (maria.conti@email.com) - €35/h - [MATH, SQL]"
     */
    public static EntityView<Teacher> teacherBasicView = teacher ->
													        teacher.getId() 				+ " - Prof. " 	+ 
													        teacher.getFirstName() 			+ " " 			+
													        teacher.getLastName() 			+ " (" 			+
													        teacher.getEmail() 				+ ") - €" 		+ 
													        teacher.getPricePerLesson() 	+ "/h - [" 		+
													        teacher.getSubjectsCSV() 		+ "]\n"			;

    /**
     * View inline per lezioni: usata per stampare storico lezioni.
     * USATO IN:
     * - StudentMain.printHistory() - punto 3
     * - TeacherMain.printLessonHistory() - punto 1
     * - TeacherMain.printNextWeekLessons() - punto 2
     * ESEMPIO OUTPUT: "Lezione #1 - 2026-02-01 ore 10 - €35"
     */
    public static EntityView<Lesson> lessonBasicView = lesson ->
												        "Lezione #" 		+ 
											        	lesson.getId() 		+ " - " 	+ 
												        lesson.getDate() 	+ " ore " 	+
												        lesson.getHour() 	+ " - €" 	+ 
												        lesson.getPrice() 	+ "\n"		;
									        
}
