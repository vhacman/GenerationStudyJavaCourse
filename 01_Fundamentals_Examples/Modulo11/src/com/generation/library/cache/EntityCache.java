package com.generation.library.cache;

import java.util.List;

import com.generation.library.Entity;

/**
 * Contratto per tutte le cache
 */
public interface EntityCache<X extends Entity>
{

	/**
	 * Mi restituisce il contenuto della cache
	 * tutti gli elementi
	 * @return
	 */
	List<X> findAll();
	
	/**
	 * Mi restituisce un elemento dato il suo id
	 * @param id
	 * @return
	 */
	X findById(int id);
	
	/**
	 * Inserisce un elemento nella cache
	 * @param x
	 */
	void insert(X x);	
	
	/**
	 * Rimuove un elemento dalla cache
	 * @param x
	 */
	void delete(X x);
		
	/**
	 * True se contengo X
	 * false se non lo contengo
	 * @param x
	 * @return
	 */
	boolean contains(X x);
	
	
	default void update(X newVersion)
	{
		X oldVersion = findById(newVersion.getId()); // elemento con lo stesso id
		delete(oldVersion);
		insert(newVersion);
	}
	
	
	
	
}
