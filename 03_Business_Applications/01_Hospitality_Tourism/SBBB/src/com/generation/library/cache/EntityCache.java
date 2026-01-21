package com.generation.library.cache;

import java.util.List;

import com.generation.library.Entity;

/**
 * Contratto per tutte le cache di entità.
 * Definisce le operazioni standard per gestire elementi in cache.
 */
public interface EntityCache<X extends Entity>
{

	/**
	 * Restituisce tutti gli elementi presenti in cache.
	 * @return lista completa degli elementi
	 */
	List<X> findAll();

	/**
	 * Restituisce un elemento dato il suo id.
	 * @param id l'identificativo dell'elemento
	 * @return l'elemento trovato o null
	 */
	X findById(int id);

	/**
	 * Inserisce un elemento nella cache.
	 * @param x l'elemento da inserire
	 */
	void insert(X x);

	/**
	 * Rimuove un elemento dalla cache.
	 * @param x l'elemento da rimuovere
	 */
	void delete(X x);

	/**
	 * Verifica se la cache contiene un elemento.
	 * @param x l'elemento da cercare
	 * @return true se presente, false altrimenti
	 */
	boolean contains(X x);


	/**
	 * Aggiorna un elemento già presente in cache.
	 * @param newVersion la nuova versione dell'elemento
	 */
	default void update(X newVersion)
	{
		/*
		 ******************************************
		 * Rimuove la vecchia versione dell'elemento
		 * e inserisce la nuova versione aggiornata
		 ******************************************
		 */
		X oldVersion = findById(newVersion.getId());
		delete(oldVersion);
		insert(newVersion);
	}




}
