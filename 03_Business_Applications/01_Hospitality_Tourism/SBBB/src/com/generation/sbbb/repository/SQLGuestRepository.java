package com.generation.sbbb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.generation.library.repository.PartialCacheSQLEntityRepository;
import com.generation.sbbb.model.entities.Guest;

/**
 * Implementazione SQL del repository degli ospiti.
 * Utilizza cache parziale per ottimizzare le performance.
 * Estende PartialCacheSQLEntityRepository e implementa GuestRepository.
 */
public class SQLGuestRepository extends PartialCacheSQLEntityRepository<Guest> implements GuestRepository
{

	public SQLGuestRepository(String table, Connection connection, int maxSize)
	{
		/*
		 ******************************************
		 * Inizializza il repository chiamando il
		 * costruttore della superclasse con tabella,
		 * connessione e dimensione massima della cache
		 ******************************************
		 */
		super(table, connection, maxSize);
	}

	/**
	 * Cerca un ospite per codice fiscale.
	 * @param ssn il codice fiscale da cercare
	 * @return l'ospite trovato o null
	 */
	@Override
	public Guest findBySSN(String ssn)
	{
		/*
		 ******************************************
		 * Utilizza findWhere con condizione SQL
		 * sul campo ssn e restituisce il primo risultato
		 ******************************************
		 */
		List<Guest> matches = findWhere("ssn='"+ssn+"'");
		return matches.size()>0 ? matches.get(0) : null;
	}

	/**
	 * Cerca ospiti il cui cognome contiene una stringa.
	 * @param part la stringa da cercare
	 * @return lista di ospiti trovati
	 */
	@Override
	public List<Guest> findByLastNameContaining(String part)
	{
		/*
		 ******************************************
		 * Utilizza l'operatore LIKE di SQL per
		 * cercare cognomi che contengono la stringa
		 ******************************************
		 */
		return findWhere("lastName like '%"+part+"%'");
	}

	/**
	 * Cerca ospiti per città.
	 * @param city la città da cercare
	 * @return lista di ospiti trovati
	 */
	@Override
	public List<Guest> findByCity(String city)
	{
		/*
		 ******************************************
		 * Filtra gli ospiti per città utilizzando
		 * una condizione WHERE esatta
		 ******************************************
		 */
		return findWhere("city='"+city+"'");
	}

	/**
	 * Crea il comando SQL di update per un ospite.
	 * @param newVersion la nuova versione dell'ospite
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getUpdateCmd(Guest newVersion) throws SQLException
	{
		/*
		 ******************************************
		 * TODO: Implementare la query SQL UPDATE
		 * con i campi dell'ospite
		 ******************************************
		 */
		return null;
	}

	/**
	 * Crea il comando SQL di insert per un ospite.
	 * @param x l'ospite da inserire
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getInsertCmd(Guest x) throws SQLException
	{
		/*
		 ******************************************
		 * TODO: Implementare la query SQL INSERT
		 * con i campi dell'ospite
		 ******************************************
		 */
		return null;
	}

	/**
	 * Trasforma una riga del database in un oggetto Guest.
	 * @param rows il ResultSet posizionato sulla riga
	 * @return l'ospite creato dalla riga
	 * @throws SQLException in caso di errore
	 */
	@Override
	public Guest rowToX(ResultSet rows) throws SQLException
	{
		/*
		 ******************************************
		 * TODO: Implementare la mappatura da
		 * ResultSet a oggetto Guest
		 ******************************************
		 */
		return null;
	}




}
