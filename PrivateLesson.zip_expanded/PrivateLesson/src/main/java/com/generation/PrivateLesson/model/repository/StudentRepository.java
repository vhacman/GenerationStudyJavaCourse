package com.generation.PrivateLesson.model.repository;

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
     

    
}
