package com.generation.pl.model.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.generation.pl.model.entities.Admin;
import com.generation.pl.model.entities.UserStatus;

// Interface che definisce le operazioni CRUD per gli Admin
public interface AdminRepository
{
    /**
     * Esegue il login verificando email e password hashata
     * @param email indirizzo email dell'admin
     * @param plainPassword password in chiaro inserita dall'utente
     * @return oggetto Admin se le credenziali sono corrette, null altrimenti
     * @throws SQLException se ci sono errori nella query al database
     */
    Admin 			login(String email, String plainPassword) throws SQLException;
    /**
     * Cambia la password di un Admin dopo aver verificato quella vecchia
     * @param id identificativo univoco dell'Admin
     * @param oldPassword password vecchia in chiaro
     * @param newPassword password nuova in chiaro
     * @throws SQLException se le password sono uguali o quella vecchia non corrisponde
     */
    void 			changePassword(int id, String oldPassword, String newPassword) throws SQLException;
    /**
     * Recupera tutti gli Admin dal database
     * @return lista di tutti gli Admin presenti
     * @throws SQLException se ci sono errori nella query
     */
    List<Admin> 	findAll() throws SQLException;
    /**
     * Cerca Admin che soddisfano una condizione WHERE personalizzata
     * @param condition condizione SQL da applicare (es. "status='ACTIVE'")
     * @return lista di Admin che soddisfano la condizione
     * @throws SQLException se ci sono errori nella query
     */
    List<Admin> 	findWhere(String condition) throws SQLException;
    /**
     * Cerca un Admin specifico tramite ID
     * @param id identificativo univoco dell'Admin
     * @return oggetto Admin trovato, null se non esiste
     * @throws SQLException se ci sono errori nella query
     */
    Admin 			findById(int id) throws SQLException;
    /**
     * Inserisce un nuovo Admin nel database
     * @param admin oggetto Admin da inserire
     * @return Admin inserito con ID generato dal database
     * @throws SQLException se ci sono errori nell'inserimento
     */
    Admin 			insert(Admin admin) throws SQLException;
    /**
     * Aggiorna i dati di un Admin esistente
     * @param admin oggetto Admin con i nuovi dati
     * @return Admin aggiornato
     * @throws SQLException se ci sono errori nell'aggiornamento
     */
    Admin 			update(Admin admin) throws SQLException;
    /**
     * Elimina un Admin dal database tramite ID
     * @param id identificativo univoco dell'Admin da eliminare
     * @return true se l'eliminazione ha avuto successo, false altrimenti
     * @throws SQLException se ci sono errori nell'eliminazione
     */
    boolean 		delete(int id) throws SQLException;
    
    /**
     * Verifica se esiste almeno un Admin nel database
     * @return true se è presente almeno un Admin, false se la tabella è vuota
     * @throws SQLException se ci sono errori nella query
     */
    boolean existsAnyAdmin() throws SQLException;
    
    public void changeStatus(int adminId, UserStatus newStatus) throws SQLException;


}
