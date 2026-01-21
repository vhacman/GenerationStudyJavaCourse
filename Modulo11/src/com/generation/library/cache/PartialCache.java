package com.generation.library.cache;

import java.util.ArrayList;
import java.util.List;


import com.generation.library.Entity;

public class PartialCache<X extends Entity> implements EntityCache<X>
{

	// quanti elementi tengo in memoria
	// prima implementazione con una Lista
	int maxSize;
	List<X> content = new ArrayList<X>();
	// attenzione: inserisco un elemento per volta
	// e lo inserisco fin tanto che ho spazio
	
	public PartialCache(int size)
	{
		this.maxSize = size;
	}
	
	
	public boolean contains(X x)
	{
		return content.contains(x);
	}
	
	// ricordiamoci di equals dopo...
	public void removeElement(X x)
	{
		content.remove(x);
	}
	
	public void addElement(X x)
	{
		// ho aggiunto il nuovo elemento
		content.add(x);
		if(content.size()>maxSize)
			content.remove(0); // rimuovo il primo elemento
	}
	
	public X findById(int id)
	{
		// non sono sicuro di trovarlo. NON HO LA LISTA DI TUTTE LE ENTITA'
		for(X x:content)
			if(x.getId()==id)
				return x;
		
		return null;
	}


	@Override
	public List<X> findAll() 
	{
		return content;
	}


	@Override
	public void insert(X x) 
	{
		content.add(x);
		if(content.size()>maxSize)
			content.remove(0);
	}


	@Override
	public void delete(X x) 
	{
		content.remove(x);
	}
	
	
	
	
	
}
