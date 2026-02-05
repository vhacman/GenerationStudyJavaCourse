package com.generation.PrivateLesson.model.repository;


import java.sql.SQLException;
import java.util.List;
import com.generation.pl.model.entities.Lesson;

// Interface che definisce le operazioni CRUD per le Lesson
public interface LessonRepository
{ 
     
    /**
     * Calcola il guadagno totale di un teacher negli ultimi 30 giorni
     * @param teacherId ID del teacher
     * @return Somma dei prezzi delle lezioni completate negli ultimi 30 giorni
     * @throws SQLException se si verifica un errore nel database
     */
    int calculateEarningsByTeacherLast30Days(int teacherId) throws SQLException;
    
    /**
     * Calcola il guadagno totale per una materia negli ultimi 30 giorni
     * @param subject Nome della materia
     * @return Somma dei prezzi delle lezioni per quella materia negli ultimi 30 giorni
     * @throws SQLException se si verifica un errore nel database
     */
    int calculateEarningsBySubjectLast30Days(String subject) throws SQLException;
    
}
