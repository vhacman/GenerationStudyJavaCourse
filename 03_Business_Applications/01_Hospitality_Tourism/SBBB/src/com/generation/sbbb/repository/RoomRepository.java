package com.generation.sbbb.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.sbbb.model.entities.Room;

/**
 * Contratto per il repository delle camere.
 * Definisce le operazioni CRUD per le camere dell'hotel.
 */
public interface RoomRepository
{

	/**
	 * Restituisce tutte le camere disponibili.
	 * @return lista completa di camere
	 */
	List<Room> findAll();

	/**
	 * Cerca una camera per identificativo.
	 * @param id l'identificativo della camera
	 * @return la camera trovata o null
	 */
	Room findById(int id);

	/**
	 * Inserisce una nuova camera nell'archivio.
	 * @param x la camera da inserire (senza id)
	 * @return la camera inserita con id assegnato
	 * @throws SQLException se l'inserimento fallisce
	 */
	Room insert(Room x) throws SQLException;

	/**
	 * Aggiorna una camera esistente.
	 * @param x la nuova versione della camera
	 * @return la camera modificata
	 * @throws SQLException se l'aggiornamento fallisce
	 */
	Room update(Room x) throws SQLException;

	/**
	 * Cancella una camera dall'archivio.
	 * @param id l'identificativo della camera da cancellare
	 * @return true se la cancellazione ha successo, false altrimenti
	 * @throws SQLException in caso di errore
	 */
	boolean delete(int id) throws SQLException;


}
