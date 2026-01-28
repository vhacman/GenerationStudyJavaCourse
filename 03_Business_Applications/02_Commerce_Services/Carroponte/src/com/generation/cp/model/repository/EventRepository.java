package com.generation.cp.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.cp.model.entities.Event;

/**
 * Repository per la gestione degli eventi.
 * Definisce il contratto per operazioni CRUD e ordinamento.
 *
 * Questo contratto rappresenta il pattern Repository che separa
 * la logica di accesso ai dati dalla logica di business.
 */
public interface EventRepository
{

	/**
	 * Recupera tutti gli eventi dal repository.
	 *
	 * @return Lista di tutti gli eventi
	 */
	List<Event> findAll();


	/**
	 * Recupera un evento tramite il suo identificativo.
	 *
	 * @param id Identificativo dell'evento
	 * @return L'evento corrispondente, null se non trovato
	 */
	Event findById(int id);


	/**
	 * Inserisce un nuovo evento nel repository.
	 *
	 * @param event Evento da inserire
	 * @return L'evento inserito con l'id assegnato
	 * @throws SQLException Se l'inserimento fallisce
	 */
	Event insert(Event event) throws SQLException;


	/**
	 * Aggiorna un evento esistente nel repository.
	 *
	 * @param event Evento con i dati aggiornati
	 * @return L'evento aggiornato
	 * @throws SQLException Se l'aggiornamento fallisce
	 */
	Event update(Event event) throws SQLException;


	/**
	 * Elimina un evento dal repository.
	 *
	 * @param id Identificativo dell'evento da eliminare
	 * @return true se l'eliminazione è avvenuta con successo, false altrimenti
	 * @throws SQLException Se l'eliminazione fallisce
	 */
	boolean delete(int id) throws SQLException;


	/**
	 * Imposta il criterio di ordinamento per i risultati delle query.
	 *
	 * @param field Campo per cui ordinare (es. "title", "price", "date")
	 */
	void setOrder(String field);

}
