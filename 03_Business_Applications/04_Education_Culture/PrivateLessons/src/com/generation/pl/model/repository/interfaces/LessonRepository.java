package com.generation.pl.model.repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import com.generation.pl.model.entities.Lesson;

// Interface che definisce le operazioni CRUD per le Lesson
public interface LessonRepository
{
    /**
     * Recupera tutte le Lesson dal database
     * @return lista di tutte le Lesson presenti
     */
    List<Lesson> 	findAll();
    /**
     * Cerca Lesson che soddisfano una condizione WHERE personalizzata
     * @param condition condizione SQL da applicare (es. "teacher_id=5")
     * @return lista di Lesson che soddisfano la condizione
     */
    List<Lesson> 	findWhere(String condition);
    /**
     * Cerca una Lesson specifica tramite ID
     * @param id identificativo univoco della Lesson
     * @return oggetto Lesson trovato, null se non esiste
     */
    Lesson 			findById(int id);
    /**
     * Inserisce una nuova Lesson nel database
     * @param lesson oggetto Lesson da inserire
     * @return Lesson inserita con ID generato dal database
     * @throws SQLException se ci sono errori nell'inserimento
     */
    Lesson 			insert(Lesson lesson) throws SQLException;
    /**
     * Aggiorna i dati di una Lesson esistente
     * @param lesson oggetto Lesson con i nuovi dati
     * @return Lesson aggiornata
     * @throws SQLException se ci sono errori nell'aggiornamento
     */
    Lesson 			update(Lesson lesson) throws SQLException;
    /**
     * Elimina una Lesson dal database tramite ID
     * @param id identificativo univoco della Lesson da eliminare
     * @return true se l'eliminazione ha avuto successo, false altrimenti
     * @throws SQLException se ci sono errori nell'eliminazione
     */
    boolean 		delete(int id) throws SQLException;
    

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
