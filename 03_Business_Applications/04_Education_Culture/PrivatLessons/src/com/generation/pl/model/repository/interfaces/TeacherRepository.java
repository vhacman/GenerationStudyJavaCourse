package com.generation.pl.model.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

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
     * Recupera tutti i Teacher dal database
     * @return lista di tutti i Teacher presenti
     * @throws SQLException se ci sono errori nella query
     */
    List<Teacher> 		findAll() throws SQLException;
    
    /**
     * Cerca un Teacher specifico tramite ID
     * @param id identificativo univoco del Teacher
     * @return oggetto Teacher trovato, null se non esiste
     * @throws SQLException se ci sono errori nella query
     */
    Teacher 			findById(int id) throws SQLException;

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
     * Cerca Teacher che soddisfano una condizione WHERE personalizzata
     * @param condition condizione SQL da applicare (es. "status='ACTIVE'")
     * @return lista di Teacher che soddisfano la condizione
     * @throws SQLException se ci sono errori nella query
     */
    List<Teacher> 		findWhere(String condition) throws SQLException;
    
    /**
     * Cerca tutti i Teacher che insegnano una specifica materia
     * @param subject nome della materia da cercare (es. "Java", "English")
     * @return lista di Teacher che insegnano quella materia
     * @throws SQLException se ci sono errori nella query
     */
    List<Teacher> 		findTeacherBySubject(String subject) throws SQLException;
    
    /**
     * Inserisce un nuovo Teacher nel database
     * @param teacher oggetto Teacher da inserire
     * @return Teacher inserito con ID generato dal database
     * @throws SQLException se ci sono errori nell'inserimento
     */
    Teacher 			insert(Teacher teacher) throws SQLException;
    /**
     * Aggiorna i dati di un Teacher esistente
     * @param teacher oggetto Teacher con i nuovi dati
     * @return Teacher aggiornato
     * @throws SQLException se ci sono errori nell'aggiornamento
     */
    Teacher 			update(Teacher teacher) throws SQLException;
    /**
     * Elimina un Teacher dal database tramite ID
     * @param id identificativo univoco del Teacher da eliminare
     * @return true se l'eliminazione ha avuto successo, false altrimenti
     * @throws SQLException se ci sono errori nell'eliminazione
     */
    boolean 			delete(int id) throws SQLException;
    public void 		changeStatus(int teacherID, UserStatus newStatus) throws SQLException;

    
}
