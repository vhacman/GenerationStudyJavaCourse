package com.generation.gbb.repository;

import java.util.List;
import com.generation.gbb.model.entities.Guest;

/**
 * Interfaccia per la gestione della persistenza degli ospiti.
 * Definisce i metodi CRUD per l'entità Guest.
 */
public interface GuestRepository
{
	// ==================== READ ====================

	/**
	 * R - Recupera tutti gli ospiti presenti nel sistema.
	 *
	 * @return Lista di tutti gli ospiti
	 */
	List<Guest> 		findAll();

	/**
	 * R - Recupera un ospite tramite il suo identificativo.
	 *
	 * @return Ospite trovato, null se non esiste
	 */
	Guest 				findById(int id);

	/**
	 * R - Recupera un ospite tramite il suo codice fiscale.
	 *
	 * @param ssn Codice fiscale dell'ospite da cercare
	 * @return Ospite trovato, null se non esiste
	 */
	Guest 				findBySSN(String ssn);

	/**
	 * R - Recupera tutti gli ospiti il cui cognome contiene la stringa specificata.
	 *
	 * @param part Parte del cognome da cercare
	 * @return Lista di ospiti con cognome contenente la stringa, lista vuota se nessun match
	 */
	List<Guest> 		findBySurnameContaining(String part);

	/**
	 * R - Recupera tutti gli ospiti residenti in una specifica città.
	 *
	 * @param city Nome della città da cercare
	 * @return Lista di ospiti residenti nella città, lista vuota se nessun match
	 */
	List<Guest> 		findByCity(String city);

	// ==================== CREATE ====================

	/**
	 * C - Inserisce un nuovo ospite nel sistema.
	 *
	 * @param newGuest Ospite da inserire (con tutti i dati obbligatori)
	 * @return Ospite inserito con ID assegnato, null se l'inserimento fallisce
	 */
	Guest 				insert(Guest newGuest);

	// ==================== UPDATE ====================

	/**
	 * U - Aggiorna i dati di un ospite esistente.
	 *
	 * @param newVersion Ospite con dati aggiornati (deve avere un ID valido)
	 * @return Ospite aggiornato, null se l'aggiornamento fallisce o l'ospite non esiste
	 */
	Guest				update(Guest newVersion);

	// ==================== DELETE ====================

	/**
	 * D - Elimina un ospite dal sistema tramite il suo identificativo.
	 *
	 * @param id Identificativo dell'ospite da eliminare
	 * @return true se l'eliminazione è avvenuta con successo, false altrimenti
	 */
	boolean				delete(int id);
}
