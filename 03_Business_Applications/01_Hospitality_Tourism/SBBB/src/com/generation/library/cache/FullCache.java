package com.generation.library.cache;

import java.util.List;

import com.generation.library.Entity;

/**
 * Cache completa per entità.
 * Memorizza tutti i dati di un'intera tabella.
 * Utile per piccole tabelle che cambiano di rado.
 * FullCache è un tipo parametrizzato (generico).
 * @param <X> il tipo di entità da memorizzare
 */
public class FullCache<X extends Entity> implements EntityCache<X>
{

	List<X>	content;

	public FullCache() {}

	public FullCache(List<X> content)
	{
		/*
		 ******************************************
		 * Inizializza la cache con un contenuto
		 * predefinito proveniente dal database
		 ******************************************
		 */
		this.content = content;
	}

	/**
	 * Cerca un'entità per id nella cache.
	 * @param id l'identificativo da cercare
	 * @return l'entità trovata o null
	 */
	public X findById(int id)
	{
		/*
		 ******************************************
		 * Scorre tutti gli elementi nella cache
		 * e restituisce quello con l'id corrispondente
		 ******************************************
		 */
		for(X x:content)
			if(x.getId()==id)
				return x;

		return null;
	}

	/**
	 * Restituisce l'intero contenuto della cache.
	 * @return tutti gli elementi memorizzati
	 */
	public List<X> findAll()
	{
		/*
		 ******************************************
		 * Restituisce la lista completa senza
		 * filtrare né modificare gli elementi
		 ******************************************
		 */
		return content;
	}

	@Override
	public void insert(X x)
	{
		/*
		 ******************************************
		 * Aggiunge un nuovo elemento alla lista
		 * degli elementi memorizzati in cache
		 ******************************************
		 */
		content.add(x);
	}

	@Override
	public void delete(X x)
	{
		/*
		 ******************************************
		 * Rimuove l'elemento specificato dalla
		 * lista utilizzando il metodo remove
		 ******************************************
		 */
		content.remove(x);
	}

	@Override
	public boolean contains(X x)
	{
		/*
		 ******************************************
		 * Verifica se l'elemento è presente nella
		 * lista utilizzando il metodo contains
		 ******************************************
		 */
		return content.contains(x);
	}

}
