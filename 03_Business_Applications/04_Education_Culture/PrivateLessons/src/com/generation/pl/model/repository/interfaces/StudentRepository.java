package com.generation.pl.model.repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.UserStatus;


public interface StudentRepository
{
    /**
     * Esegue il login verificando email e password hashata
     * @param email indirizzo email dello student
     * @param plainPassword password in chiaro inserita dall'utente
     * @return oggetto Student se le credenziali sono corrette, null altrimenti
     * @throws SQLException se ci sono errori nella query al database
     */
    Student 		login(String email, String plainPassword) throws SQLException;
    /**
     * Cambia la password di uno Student dopo aver verificato quella vecchia
     * @param id identificativo univoco dello Student
     * @param oldPassword password vecchia in chiaro
     * @param newPassword password nuova in chiaro
     * @throws SQLException se le password sono uguali o quella vecchia non corrisponde
     */
    void 			changePassword(int id, String oldPassword, String newPassword) throws SQLException;
    /**
     * Recupera tutti gli Student dal database
     * @return lista di tutti gli Student presenti
     * @throws SQLException se ci sono errori nella query
     */
    List<Student> 	findAll() throws SQLException;
    /**
     * Cerca Student che soddisfano una condizione WHERE personalizzata
     * @param condition condizione SQL da applicare (es. "status='ACTIVE'")
     * @return lista di Student che soddisfano la condizione
     * @throws SQLException se ci sono errori nella query
     */
    List<Student> 	findWhere(String condition) throws SQLException;
    /**
     * Cerca uno Student specifico tramite ID
     * @param id identificativo univoco dello Student
     * @return oggetto Student trovato, null se non esiste
     * @throws SQLException se ci sono errori nella query
     */
    Student 		findById(int id) throws SQLException;

    /**
     * Recupera uno Student tramite ID, con opzione per caricare o meno
     * le entità associate (lezioni).
     *
     * @param id l'identificativo univoco dello student da cercare
     * @param complete true per caricare anche le lezioni associate, false per i soli dati base
     * @return l'oggetto Student richiesto, con o senza relazioni caricate
     * @throws SQLException se si verifica un errore durante l'accesso al database
     */
    Student 		findById(int id, boolean complete) throws SQLException;
    /**
     * Inserisce un nuovo Student nel database
     * @param student oggetto Student da inserire
     * @return Student inserito con ID generato dal database
     * @throws SQLException se ci sono errori nell'inserimento
     */
    Student 		insert(Student student) throws SQLException;
    /**
     * Aggiorna i dati di uno Student esistente
     * @param student oggetto Student con i nuovi dati
     * @return Student aggiornato
     * @throws SQLException se ci sono errori nell'aggiornamento
     */
    Student 		update(Student student) throws SQLException;
    /**
     * Elimina uno Student dal database tramite ID
     * @param id identificativo univoco dello Student da eliminare
     * @return true se l'eliminazione ha avuto successo, false altrimenti
     * @throws SQLException se ci sono errori nell'eliminazione
     */
    boolean 		delete(int id) throws SQLException;
    public void 	changeStatus(int studentID, UserStatus newStatus) throws SQLException;

    
}
