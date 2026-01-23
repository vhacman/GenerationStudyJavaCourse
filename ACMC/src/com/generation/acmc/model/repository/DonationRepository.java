package com.generation.acmc.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.acmc.model.entities.Donation;

/**
 * Interfaccia per la gestione delle donazioni nel sistema.
 * Fornisce metodi per creare, leggere, aggiornare ed eliminare le donazioni.
 */
public interface DonationRepository
{
    /**
     * Inserisce una nuova donazione nel sistema.
     * 
     * @param donation donazione da inserire
     * @return donazione inserita, con eventuale ID generato
     * @throws SQLException se si verifica un errore durante l'inserimento
     */
    Donation insert(Donation donation) throws SQLException;

    /**
     * Aggiorna i dati di una donazione esistente.
     * 
     * @param donation donazione da aggiornare (deve avere un ID valido)
     * @return donazione aggiornata
     * @throws SQLException se si verifica un errore durante l'aggiornamento
     */
    Donation update(Donation donation) throws SQLException;

    /**
     * Elimina una donazione dal sistema in base al suo ID.
     *
     * @param id identificativo della donazione da eliminare
     * @return true se l'eliminazione è avvenuta, false se la donazione non esiste
     * @throws SQLException se si verifica un errore durante l'eliminazione
     */
    boolean delete(int id) throws SQLException;

    /**
     * Restituisce tutte le donazioni effettuate da un socio specifico.
     *
     * @param memberId identificativo del socio
     * @return lista delle donazioni del socio, mai null (può essere vuota)
     */
    List<Donation> findByMemberId(int memberId);

    /**
     * Restituisce le donazioni effettuate da un socio nell'ultimo anno.
     *
     * @param memberId identificativo del socio
     * @return lista delle donazioni del socio nell'ultimo anno, mai null (può essere vuota)
     */
    List<Donation> findByMemberIdLastYear(int memberId);
    
}

