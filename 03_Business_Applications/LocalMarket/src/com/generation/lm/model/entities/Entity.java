package com.generation.lm.model.entities;

/**
 * Base per tutte le altre entità
 */
public class Entity
{
	// protected è un livello di visibilità
	protected int id;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	// TODO: Implementare metodo isValid() per validare l'entità
	// Ogni classe figlia deve fare override di questo metodo
	// per verificare che tutti i campi obbligatori siano presenti e validi
	public boolean isValid() {return id > 0;}
}
