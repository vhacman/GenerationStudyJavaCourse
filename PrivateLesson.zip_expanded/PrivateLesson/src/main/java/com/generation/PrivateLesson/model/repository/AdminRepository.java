package com.generation.PrivateLesson.model.repository;

import java.sql.SQLException;

import com.generation.PrivateLesson.model.entities.Admin;
import com.generation.PrivateLesson.model.entities.UserStatus;

// Interface che definisce le operazioni CRUD per gli Admin
public interface AdminRepository implements JPARepository<Admin, Integer>
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
     * Verifica se esiste almeno un Admin nel database
     * @return true se è presente almeno un Admin, false se la tabella è vuota
     * @throws SQLException se ci sono errori nella query
     */
    boolean existsAnyAdmin() throws SQLException;
    
    public void changeStatus(int adminId, UserStatus newStatus) throws SQLException;


}
