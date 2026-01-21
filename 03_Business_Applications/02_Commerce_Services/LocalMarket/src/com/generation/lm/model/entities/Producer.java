package com.generation.lm.model.entities;

/**
 * Modello per tutti i produttori
 */
public class Producer extends Entity
{
	// voglio poter BANNARE un fornitore.
	// e se lo voglio bannare lo metterò in History
	protected String 	legalName, address, history;
	protected boolean 	active; // attivo o non attivo, non bannato o bannato
	
	public String getLegalName() 
	{
		return legalName;
	}
	
	public void setLegalName(String legalName) 
	{
		this.legalName = legalName;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getHistory() 
	{
		return history;
	}
	
	public void setHistory(String history) 
	{
		this.history = history;
	}
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}

	// TODO: Override isValid() per Producer
	// Verificare che:
	// - id > 0
	// - legalName non sia null o vuoto
	// - address non sia null o vuoto
	@Override
	public boolean isValid() {
		if(!super.isValid())
			return false;
		return !legalName.isEmpty() 	&&
				!legalName.isBlank() 	&&
				!address.isEmpty()		&&
				!address.isBlank() 		&&
				history 	!= null		&&
				address 	!= null 	&&
				legalName 	!= null;
	}
}
