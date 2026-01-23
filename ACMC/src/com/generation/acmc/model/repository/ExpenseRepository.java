package com.generation.acmc.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.acmc.model.entities.Expense;

/**
 * Interfaccia per la gestione delle spese nel sistema.
 * Fornisce metodi per creare, leggere, aggiornare ed eliminare le spese.
 */
public interface ExpenseRepository
{
     /**
     * Inserisce una nuova spesa nel sistema.
     * 
     * @param expense spesa da inserire
     * @return spesa inserita, con eventuale ID generato
     * @throws SQLException se si verifica un errore durante l'inserimento
     */
    Expense insert(Expense expense) throws SQLException;

    /**
     * Aggiorna i dati di una spesa esistente.
     * 
     * @param expense spesa da aggiornare (deve avere un ID valido)
     * @return spesa aggiornata
     * @throws SQLException se si verifica un errore durante l'aggiornamento
     */
    Expense update(Expense expense) throws SQLException;

    /**
     * Elimina una spesa dal sistema in base al suo ID.
     * 
     * @param id identificativo della spesa da eliminare
     * @return true se l'eliminazione è avvenuta, false se la spesa non esiste
     * @throws SQLException se si verifica un errore durante l'eliminazione
     */
    boolean delete(int id) throws SQLException;
}
