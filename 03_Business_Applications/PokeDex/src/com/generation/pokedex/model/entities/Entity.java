package com.generation.pokedex.model.entities;

/**
 * Ogni entity CONCRETA corrisponderà a una tabella del DB
 * Cosa vuol dire abstract?
 * Regalo del diavolo... per ora la scrivo e non la spiego...
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
	
	public boolean notBlank(String s)
	{
		return s!=null && !s.isBlank();
	}
	
	public abstract boolean isValid(); // questo metodo DOVRA' essere overridato
	// io qui lo dichiaro e le sottoclassi dovranno sovrascriverlo
	
	

}
