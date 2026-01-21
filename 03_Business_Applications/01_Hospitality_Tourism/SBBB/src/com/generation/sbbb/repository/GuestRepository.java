package com.generation.sbbb.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.sbbb.model.entities.Guest;

/**
 * Contratto per il repository degli ospiti.
 * Define le operazioni CRUD e le query specifiche per gli ospiti.
 */
public interface GuestRepository
{



	/**
	 * Restituisce tutti gli ospiti presenti nell'archivio.
	 * @return lista completa di ospiti
	 */
	List<Guest> findAll();

	/**
	 * Cerca un ospite per identificativo.
	 * @param id l'identificativo dell'ospite
	 * @return l'ospite trovato o null
	 */
	Guest findById(int id);

	/**
	 * Cerca un ospite per codice fiscale.
	 * @param ssn il codice fiscale da cercare
	 * @return l'ospite trovato o null
	 */
	Guest findBySSN(String ssn);


	/**
	 * Cerca tutti gli ospiti il cui cognome contiene la stringa specificata.
	 * @param part la stringa da cercare nel cognome
	 * @return lista di ospiti trovati
	 */
	List<Guest> findByLastNameContaining(String part);



	/**
	 * Cerca tutti gli ospiti di una determinata città.
	 * @param city la città da cercare
	 * @return lista di ospiti trovati
	 */
	List<Guest> findByCity(String city);

	/**
	 * Inserisce un nuovo ospite nell'archivio.
	 * @param newGuest l'ospite da inserire (senza id)
	 * @return l'ospite inserito con id assegnato
	 * @throws SQLException se l'inserimento fallisce
	 */
	Guest insert(Guest newGuest) throws SQLException;

	/**
	 * Aggiorna un ospite esistente.
	 * @param newVersion la nuova versione dell'ospite
	 * @return l'ospite modificato
	 * @throws SQLException se l'aggiornamento fallisce
	 */
	Guest update(Guest newVersion) throws SQLException;



	/**
	 * Cancella un ospite dall'archivio.
	 * @param id l'identificativo dell'ospite da cancellare
	 * @return true se la cancellazione ha successo, false altrimenti
	 * @throws SQLException in caso di errore
	 */
	boolean delete(int id) throws SQLException;


}
