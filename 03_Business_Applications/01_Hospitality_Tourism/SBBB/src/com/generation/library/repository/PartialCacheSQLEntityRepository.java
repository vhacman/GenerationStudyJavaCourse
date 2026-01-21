package com.generation.library.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.generation.library.Entity;
import com.generation.library.cache.EntityCache;
import com.generation.library.cache.PartialCache;

/**
 * Repository SQL con cache parziale.
 * Mantiene in memoria solo un numero limitato di entità recentemente accedute.
 * @param <X> il tipo di entità gestita dal repository
 */
public abstract class PartialCacheSQLEntityRepository<X extends Entity> extends SQLEntityRepository<X>
{

	EntityCache<X>	cache;


	public PartialCacheSQLEntityRepository(String table, Connection connection, int maxSize)
	{
		/*
		 ******************************************
		 * Chiama il costruttore della superclasse
		 * e inizializza una cache parziale vuota
		 * con dimensione massima specificata
		 ******************************************
		 */
		super(table, connection);
		cache = new PartialCache<X>(maxSize);
	}


	/**
	 * Cerca un'entità per id, prima nella cache poi nel database.
	 * @param id l'identificativo da cercare
	 * @return l'entità trovata o null
	 */
	@Override
	public X findById(int id)
	{
		/*
		 ******************************************
		 * Cerca prima nella cache per evitare
		 * query al database. Se non trovato, interroga
		 * il database e memorizza il risultato
		 * nella cache per accessi futuri
		 ******************************************
		 */
		X cached = cache.findById(id);
		if(cached!=null)
			return cached;

		X res = super.findById(id);

		if(res!=null)
			cache.insert(res);
		return res;
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
		 * Inserisce nel database e aggiunge alla
		 * cache per renderla disponibile per
		 * accessi futuri immediati
		 ******************************************
		 */
		X res = super.insert(x);
		cache.insert(res);
		return res;
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
		 * la versione in cache se presente
		 ******************************************
		 */
		x = super.update(x);
		cache.update(x);
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
		 * Recupera l'entità, la elimina dal database
		 * e rimuove dalla cache se presente
		 ******************************************
		 */
		X x = findById(id);
		boolean success = super.delete(id);
		if(success)
			cache.delete(x);
		return success;
	}



}
