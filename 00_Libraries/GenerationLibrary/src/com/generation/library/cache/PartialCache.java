package com.generation.library.cache;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * Cache parziale con capacità limitata (LRU-like).
 * Mantiene solo gli ultimi N elementi, rimuovendo i più vecchi.
 */
public class PartialCache<X extends Entity> implements EntityCache<X>
{

	int       maxSize;     // Capacità massima della cache
	List<X>   content = new ArrayList<X>();


	/**
	 * Costruttore con dimensione massima.
	 * @param size Numero massimo di elementi da mantenere
	 */
	public PartialCache(int size)
	{
		/*
		 * LRU (Least Recently Used) semplificato:
		 * → Mantiene solo maxSize elementi
		 * → Rimuove i più vecchi quando piena
		 */
		this.maxSize = size;
	}


	/**
	 * Verifica se contiene un'entità.
	 * @param x Entità da cercare
	 * @return true se presente
	 */
	public boolean contains(X x) { return content.contains(x); }


	/**
	 * Rimuove un elemento dalla cache.
	 * @param x Entità da rimuovere
	 */
	public void removeElement(X x) { content.remove(x); }


	/**
	 * Aggiunge un elemento, rimuovendo il più vecchio se necessario.
	 * @param x Entità da aggiungere
	 */
	public void addElement(X x)
	{
		/*
		 * Eviction policy:
		 * → Aggiunge nuovo elemento
		 * → Se supera maxSize, rimuove il primo (più vecchio)
		 */
		content.add(x);

		if(content.size() > maxSize)
			content.remove(0);    // Rimuove il più vecchio (FIFO)
	}


	/**
	 * Cerca per ID (potrebbe non trovare, cache parziale).
	 * @param id ID da cercare
	 * @return Entità se presente, null altrimenti
	 */
	public X findById(int id)
	{
		/*
		 * Cache miss possibile:
		 * → Non garantisce presenza di tutti i dati
		 * → Ricerca lineare O(n)
		 */
		for(X x : content)
		{
			if(x.getId() == id)
				return x;
		}

		return null;
	}


	@Override
	public List<X> findAll() { return content; }


	@Override
	public void insert(X x)
	{
		/*
		 * Insert con eviction automatica
		 */
		content.add(x);

		if(content.size() > maxSize)
			content.remove(0);
	}


	@Override
	public void delete(X x) { content.remove(x); }

}
