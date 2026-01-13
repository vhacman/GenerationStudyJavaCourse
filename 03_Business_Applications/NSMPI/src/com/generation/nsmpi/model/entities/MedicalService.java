package com.generation.nsmpi.model.entities;

import java.util.ArrayList;
import java.util.List;
import com.generation.library.Entity;

/**
 * Servizio medico erogabile (visita generale, vaccinazioni, esami, etc.).
 * Definisce descrizione e prezzo del servizio.
 */
public class MedicalService extends Entity
{
	private String  description;
	private int     price;



	public MedicalService() {}

	/**
	 * Inizializza un servizio medico con descrizione e prezzo.
	 *
	 * @param description Descrizione del servizio
	 * @param price Prezzo in unità monetarie (>= 0)
	 */
	public MedicalService(String description, int price)
	{
		super();
		this.description = description;
		this.price       = price;
	}

	public String  getDescription()                    { return description;              }
	public void    setDescription(String description)  { this.description = description;  }
	public int     getPrice()                          { return price;                    }
	public void    setPrice(int price)                 { this.price = price;              }

	/**
	 * Valida il servizio verificando prezzo positivo e descrizione presente.
	 *
	 * @return Lista di errori (vuota se valido)
	 */
	@Override
	public List<String> getErrors()
	{
		/*
		 * Relazione tra astrazione e implementazione:
		 *
		 *   Interfaccia  →  Classe
		 *   Contratto    →  Fornitore
		 *   Supertipo    →  Sottotipo
		 *   Astratto     →  Concreto
		 *
		 * Questa classe (sottotipo concreto) soddisfa il contratto
		 * definito dall'interfaccia (supertipo astratto).
		 */
		List<String> errors = new ArrayList<>();

		if (price < 0)
			errors.add("Invalid value for price");
		if (isMissing(description))
			errors.add("Missing value for field description");

		return errors;
	}

	@Override
	public String toString()
	{
		return "MedicalService [description=" + description + ", price=" + price + "]";
	}

}
