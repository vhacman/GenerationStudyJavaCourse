package com.generation.library;

import java.util.List;

/**
 * Base per tutte le altre entità
 * questa classe definisce una base di stato
 * e una base di comportamento per tutte le entità
 */
public abstract class Entity 
{
	protected int id;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	
	/*
	 * List<String> : insieme ordinato di stringhe a dimensione variabile
	 * con duplicati ammessi
	 * 
	 */
	public abstract List<String> getErrors();
	
	/**
	 * Restituisce 0 se non ho errori
	 * @return
	 */
	public boolean isValid()
	{
		return getErrors().isEmpty();
	}
	
	/**
	 * Metodo protetto, NON pubblico
	 * restituisce true se la stringa non è null
	 * né vuota
	 * @param s
	 * @return
	 */
	protected boolean notMissing(String s)
	{
		return s!=null && !s.isBlank();
	}
	
	protected boolean isMissing(String s)
	{
		return s==null || s.isBlank();
	}
	
}
