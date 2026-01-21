package com.generation.library.cache;

import java.util.ArrayList;
import java.util.List;


import com.generation.library.Entity;

/**
 * Cache parziale per entità.
 * Memorizza solo un numero limitato di elementi più recenti.
 * Implementa una strategia FIFO (First In First Out).
 * @param <X> il tipo di entità da memorizzare
 */
public class PartialCache<X extends Entity> implements EntityCache<X>
{

	int			maxSize;
	List<X>		content = new ArrayList<X>();


	public PartialCache(int size)
	{
		/*
		 ******************************************
		 * Inizializza la cache con una dimensione
		 * massima specificata. Gli elementi vengono
		 * inseriti uno per volta fino a riempire
		 * la cache completamente
		 ******************************************
		 */
		this.maxSize = size;
	}


	/**
	 * Verifica se la cache contiene un elemento.
	 * @param x l'elemento da cercare
	 * @return true se presente, false altrimenti
	 */
	public boolean contains(X x)
	{
		/*
		 ******************************************
		 * Utilizza il metodo contains della lista
		 * per verificare la presenza dell'elemento
		 ******************************************
		 */
		return content.contains(x);
	}

	/**
	 * Rimuove un elemento dalla cache.
	 * @param x l'elemento da rimuovere
	 */
	public void removeElement(X x)
	{
		/*
		 ******************************************
		 * Rimuove l'elemento specificato dalla
		 * lista, liberando spazio nella cache
		 ******************************************
		 */
		content.remove(x);
	}

	/**
	 * Aggiunge un elemento alla cache.
	 * @param x l'elemento da aggiungere
	 */
	public void addElement(X x)
	{
		/*
		 ******************************************
		 * Aggiunge il nuovo elemento alla cache.
		 * Se la dimensione supera il limite,
		 * rimuove automaticamente l'elemento più
		 * vecchio (strategia FIFO)
		 ******************************************
		 */
		content.add(x);
		if(content.size()>maxSize)
			content.remove(0);
	}

	/**
	 * Cerca un elemento per id nella cache.
	 * @param id l'identificativo da cercare
	 * @return l'elemento trovato o null
	 */
	public X findById(int id)
	{
		/*
		 ******************************************
		 * Non è garantito trovare l'elemento poiché
		 * la cache contiene solo una porzione
		 * limitata degli elementi totali
		 ******************************************
		 */
		for(X x:content)
			if(x.getId()==id)
				return x;

		return null;
	}


	@Override
	public List<X> findAll()
	{
		/*
		 ******************************************
		 * Restituisce tutti gli elementi presenti
		 * in cache, che sono solo una parte del totale
		 ******************************************
		 */
		return content;
	}


	@Override
	public void insert(X x)
	{
		/*
		 ******************************************
		 * Aggiunge l'elemento e rimuove il più
		 * vecchio se si supera la dimensione massima
		 ******************************************
		 */
		content.add(x);
		if(content.size()>maxSize)
			content.remove(0);
	}


	@Override
	public void delete(X x)
	{
		/*
		 ******************************************
		 * Rimuove l'elemento specificato dalla cache
		 ******************************************
		 */
		content.remove(x);
	}




}
