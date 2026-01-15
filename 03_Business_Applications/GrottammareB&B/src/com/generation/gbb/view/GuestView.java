package com.generation.gbb.view;

import java.util.List;

import com.generation.gbb.model.entities.Guest;
import com.generation.library.Template;

/**
 * Io qui sto dicendo di cosa ho bisogno
 * ma sto anche fornendo una PARTE dell'implementazione
 */
public abstract class GuestView 
{
	protected String template;
	protected String templateContent;
	
	public GuestView(String template)
	{
		this.template = template;
		this.templateContent = Template.load(template);
	}
	
	/**
	 * Questo metodo voglio che venga sovracritto
	 * @param g
	 * @return
	 */
	public abstract String render(Guest g);
	
	// overload: c'è un metodo render che renderizza un solo guest, e uno che renderizza una lista di guest
	public String render(List<Guest> guests)
	{
		String res = "";
		for(Guest guest:guests)
			res+=render(guest)+"\n";		// il render della lista richiama il render del singolo elemento
		// per ogni elemento della lista	
		return res;
	}
	
	
}
