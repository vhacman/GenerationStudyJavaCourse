package com.generation.lmdb.model.entities;

/**
 * Modello per tutti i produttori
 */
public class Producer extends Entity
{
	protected String 	legalName, address, history;
	protected boolean 	active; // attivo o non attivo, non bannato o bannato

	// Getter e Setter
	public String 	getLegalName() 					{ return legalName; }
	public void 	setLegalName(String legalName) 	{ this.legalName = legalName; }
	public String 	getAddress() 					{ return address; }
	public void 	setAddress(String address) 		{ this.address = address; }
	public String 	getHistory() 					{ return history; }
	public void 	setHistory(String history) 		{ this.history = history; }
	public boolean 	isActive() 						{ return active; }
	public void 	setActive(boolean active) 		{ this.active = active; }

	@Override
	public boolean isValid() {
		return !legalName.isEmpty() 	&&
				!legalName.isBlank() 	&&
				!address.isEmpty()		&&
				!address.isBlank() 		&&
				history 	!= null		&&
				address 	!= null 	&&
				legalName 	!= null;
	}
	
	@Override
	public String toString() {
		return "Producer [legalName=" + legalName + ", address=" + address + ", history=" + history + ", active="
				+ active + "]";
	}
}
