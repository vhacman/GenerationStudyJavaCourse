package com.generation.sbbb.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * Rappresenta una camera dell'hotel.
 * Contiene informazioni su nome, descrizione e prezzo della camera.
 */
public class Room extends Entity
{

	String	name, description;
	int		price;

	/**
	 * Valida i dati della camera.
	 * @return lista di errori di validazione
	 */
	@Override
	public List<String> getErrors()
	{
		/*
		 ******************************************
		 * Verifica che tutti i campi obbligatori
		 * siano presenti e validi. Aggiunge un
		 * messaggio di errore per ogni campo invalido
		 ******************************************
		 */
		List<String> res = new ArrayList<String>();

		if(price<0)
			res.add("Invalid value for price");
		if(isMissing(description))
			res.add("Missing value for description");
		if(isMissing(name))
			res.add("Missing value for name");

		return res;
	}

	@Override
	public String toString()
	{
		/*
		 ******************************************
		 * Restituisce una rappresentazione testuale
		 * della camera con tutti i suoi attributi
		 ******************************************
		 */
		return "Room [name=" + name + ", description=" + description + ", price=" + price + "]";
	}




	public String  getName()                      { return name; }
	public void    setName(String name)           { this.name = name; }
	public String  getDescription()               { return description; }
	public void    setDescription(String description) { this.description = description; }
	public int     getPrice()                     { return price; }
	public void    setPrice(int price)            { this.price = price; }

}
