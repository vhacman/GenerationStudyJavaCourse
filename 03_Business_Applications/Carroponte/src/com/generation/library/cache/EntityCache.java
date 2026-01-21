package com.generation.library.cache;

import java.util.List;

import com.generation.library.Entity;

/**
 * Contratto generico per le implementazioni di cache.
 * Definisce operazioni CRUD base per entità in memoria.
 */
public interface EntityCache<X extends Entity>
{

	/**
	 * Restituisce tutti gli elementi presenti nella cache.
	 * @return Lista completa delle entità in cache
	 */
	List<X> findAll();


	/**
	 * Cerca un'entità per ID.
	 * @param id Identificativo dell'entità
	 * @return Entità trovata, null se non presente
	 */
	X findById(int id);


	/**
	 * Inserisce un elemento nella cache.
	 * @param x Entità da inserire
	 */
	void insert(X x);


	/**
	 * Rimuove un elemento dalla cache.
	 * @param x Entità da rimuovere
	 */
	void delete(X x);


	/**
	 * Verifica se la cache contiene un'entità.
	 * @param x Entità da cercare
	 * @return true se presente
	 */
	boolean contains(X x);


	/**
	 * Aggiorna un'entità nella cache (metodo default).
	 *
	 * @param newVersion Nuova versione dell'entità
	 */
	default void update(X newVersion)
	{
		/*
		 * Pattern: Delete + Insert
		 * → Trova la vecchia versione per ID
		 * → Rimuove la vecchia
		 * → Inserisce la nuova
		 */
		X oldVersion = findById(newVersion.getId());
		delete(oldVersion);
		insert(newVersion);
	}

}
