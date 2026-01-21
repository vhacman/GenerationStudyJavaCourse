package com.generation.library.cache;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * TotalCache -> cache per tutte le entità
 * che memorizza i dati di una intera tabella
 * utile per piccole tabelle
 * che cambiano di rado
 * total cache è un tipo PARAMETRIZZATO
 * o se preferite un tipo GENERICO
 * TotalCache lavora sul tipo di oggetto X
 * e di X so soltanto che estende entity
 */
public class FullCache<X extends Entity> implements EntityCache<X>
{
	
	// content contiene le righe della tabella
	// trasformate in entità
	// potrei doverle modificare... DOPO averle caricate
	List<X> content;
	
	public FullCache() {}
	
	public FullCache(List<X> content)
	{
		this.content = content;
	}
	
	public X findById(int id)
	{
		for(X x:content)
			if(x.getId()==id)
				return x;
	
		return null;
	}
	
	public List<X> findAll()
	{
		return content;
	}

	@Override
	public void insert(X x) 
	{
		content.add(x);
	}

	@Override
	public void delete(X x) 
	{
		content.remove(x);
	}

	@Override
	public boolean contains(X x) 
	{
		return content.contains(x);
	}

}
