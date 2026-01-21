package com.generation.library.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.generation.library.Entity;
import com.generation.library.cache.EntityCache;
import com.generation.library.cache.FullCache;

/**
 * Repository SQL con cache completa.
 * Carica tutti i dati in memoria all'inizializzazione e opera sulla cache invece che sul database.
 * @param <X> il tipo di entità gestita dal repository
 */
public abstract class FullCacheSQLEntityRepository<X extends Entity> extends SQLEntityRepository<X>
{

	EntityCache<X>	cache;

	public FullCacheSQLEntityRepository(String table, Connection connection)
	{
		/*
		 ******************************************
		 * Chiama il costruttore della superclasse
		 * e inizializza la cache completa caricando
		 * tutti i dati dal database tramite findAll
		 * della classe padre
		 ******************************************
		 */
		super(table, connection);
		cache = new FullCache<X>(super.findAll());
	}

	/**
	 * Cerca un'entità per id nella cache.
	 * @param id l'identificativo da cercare
	 * @return l'entità trovata o null
	 */
	@Override
	public X findById(int id)
	{
		/*
		 ******************************************
		 * Interroga la cache in memoria invece
		 * di eseguire una query al database
		 ******************************************
		 */
		return cache.findById(id);
	}

	/**
	 * Restituisce tutte le entità dalla cache.
	 * @return lista completa di entità
	 */
	@Override
	public List<X> findAll()
	{
		/*
		 ******************************************
		 * Restituisce il contenuto completo della
		 * cache senza toccare il database
		 ******************************************
		 */
		return cache.findAll();
	}

	/**
	 * Inserisce una nuova entità nel database e nella cache.
	 * @param x l'entità da inserire
	 * @return l'entità con id assegnato
	 * @throws SQLException se l'inserimento fallisce
	 */
	@Override
	public X insert(X x) throws SQLException
	{
		/*
		 ******************************************
		 * Esegue l'insert sul database chiamando
		 * il metodo della superclasse e aggiorna
		 * anche la cache per mantenere la sincronizzazione
		 ******************************************
		 */
		x = super.insert(x);
		cache.insert(x);
		return x;
	}

	/**
	 * Cancella un'entità dal database e dalla cache.
	 * @param id l'identificativo dell'entità da cancellare
	 * @return true se la cancellazione ha successo
	 * @throws SQLException in caso di errore
	 */
	@Override
	public boolean delete(int id) throws SQLException
	{
		/*
		 ******************************************
		 * Recupera l'entità dalla cache, la elimina
		 * dal database e, se l'operazione ha successo,
		 * la rimuove anche dalla cache
		 ******************************************
		 */
		X x = findById(id);
		boolean success = super.delete(id);

		if(success)
			cache.delete(x);

		return success;
	}


	/**
	 * Aggiorna un'entità nel database e nella cache.
	 * @param x la nuova versione dell'entità
	 * @return l'entità aggiornata
	 * @throws SQLException in caso di errore
	 */
	@Override
	public X update(X x) throws SQLException
	{
		/*
		 ******************************************
		 * Esegue l'update sul database e aggiorna
		 * anche la cache per mantenere consistenza
		 * tra memoria e persistenza
		 ******************************************
		 */
		x = super.update(x);
		cache.update(x);
		return x;
	}

}
