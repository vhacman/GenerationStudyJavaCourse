package com.generation.PrivateLesson.model.repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.pl.model.entities.Lesson;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.UserStatus;

// Interface che definisce le operazioni CRUD per i Teacher
public interface TeacherRepository
{
    /**
     * Esegue il login verificando email e password hashata
     * @param email indirizzo email del teacher
     * @param plainPassword password in chiaro inserita dall'utente
     * @return oggetto Teacher se le credenziali sono corrette, null altrimenti
     * @throws SQLException se ci sono errori nella query al database
     */
    Teacher 			login(String email, String plainPassword) throws SQLException;
    /**
     * Cambia la password di un Teacher dopo aver verificato quella vecchia
     * @param id identificativo univoco del Teacher
     * @param oldPassword password vecchia in chiaro
     * @param newPassword password nuova in chiaro
     * @throws SQLException se le password sono uguali o quella vecchia non corrisponde
     */
    void 				changePassword(int id, String oldPassword, String newPassword) throws SQLException;
    

    /**
     * Recupera un Teacher tramite ID, con opzione per caricare o meno
     * le entità associate (lezioni).
     *
     * @param id l'identificativo univoco del teacher da cercare
     * @param complete true per caricare anche le lezioni associate, false per i soli dati base
     * @return l'oggetto Teacher richiesto, con o senza relazioni caricate
     * @throws SQLException se si verifica un errore durante l'accesso al database
     */
    Teacher 			findById(int id, boolean complete) throws SQLException;
    
     
    
    /**
     * Cerca tutti i Teacher che insegnano una specifica materia
     * @param subject nome della materia da cercare (es. "Java", "English")
     * @return lista di Teacher che insegnano quella materia
     * @throws SQLException se ci sono errori nella query
     */
    List<Teacher> 		findTeacherBySubject(String subject) throws SQLException;
    
    
    public void 		changeStatus(int teacherID, UserStatus newStatus) throws SQLException;
	int                 calculateEarningsByTeacherIdAndPeriod(int id, LocalDate startDate, LocalDate endDate) throws SQLException ;
	List<Lesson> 		findLessonsByTeacherId(int id) throws SQLException;
	List<Lesson> 		findNextWeekLessonsByTeacherId(int id) throws SQLException;

    
}
