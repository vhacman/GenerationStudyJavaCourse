package com.generation.nsmpi.model.entities;

import java.util.ArrayList;
import java.util.List;
import com.generation.library.Entity;

/**
 * Ambulatorio in cui vengono erogati servizi medici.
 * Definisce descrizione e piano di localizzazione (da -10 a +10).
 */
public class ServiceRoom extends Entity
{
	private String  description;  // ambulatorio ottico, ambulatorio gastrointerologo
	private int     floor;        // da -10 a +10
	// in quale palazzo? possibilmente da implementare

	public String  getDescription()                    { return description;              }
	public int     getFloor()                          { return floor;                    }
	public void    setDescription(String description)  { this.description = description;  }
	public void    setFloor(int floor)                 { this.floor = floor;              }

	/**
	 * Valida l'ambulatorio verificando descrizione presente e piano nell'intervallo [-10, +10].
	 * Implementa il contratto astratto definito da Entity.
	 *
	 * @return Lista di errori (vuota se valido)
	 */
	@Override
	public List<String> getErrors()
	{
		/*
		 * Polimorfismo e Implementazione del Contratto:
		 *      * Entity (Classe Astratta)    →  Definisce il contratto getErrors()
		 *      * ServiceRoom (Classe Concreta) →  Fornisce l'implementazione specifica
		 *
		 * ServiceRoom implementa le regole di validazione specifiche per gli ambulatori:
		 * - description obbligatoria (campo identificativo dell'ambulatorio)
		 * - floor deve essere compreso tra -10 e +10 (business rule del dominio)
		 *
		 * Grazie al polimorfismo, un riferimento di tipo Entity può invocare getErrors()
		 * su un oggetto ServiceRoom, ottenendo il comportamento specifico dell'ambulatorio.
		 * Principio LSP: ServiceRoom può sostituire Entity rispettando il contratto.
		 */
		List<String> errors = new ArrayList<>();

		if (isMissing(description))
			errors.add("Missing value for field Description");
		if (floor < -10 || floor > 10)
			errors.add("Invalid value for field floor " + floor);

		return errors;
	}

	/**
	 * Rappresentazione testuale dell'ambulatorio.
	 *
	 * @return Stringa formattata con description e floor
	 */
	@Override
	public String toString()
	{
		return "ServiceRoom [description=" + description + ", floor=" + floor + "]";
	}
}
