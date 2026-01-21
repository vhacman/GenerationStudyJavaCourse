package com.generation.nsmpi.model.entities;

import com.generation.library.Console;

/**
 * Demo per l'inserimento e validazione di un servizio medico.
 * Raccoglie i dati da console e verifica la validità dell'entità MedicalService.
 */
public class DemoInsertMedicalService
{

	public static void main(String[] args)
	{
		MedicalService  m           = new MedicalService();

		Console.print("=== Inserimento Servizio Medico ===\n");
		Console.print("Inserisci la descrizione del servizio: ");
		String          description = Console.readString();
		m.setDescription(description);

		Console.print("Inserisci il prezzo: ");
		int             price       = Console.readInt();
		m.setPrice(price);

		Console.print("\n=== Risultato Validazione ===\n");

		if (m.isValid())
		{
			Console.print(" Servizio medico valido!\n");
			Console.print(m.toString());
		}
		else
		{
			Console.print(" Servizio medico non valido. Errori rilevati:\n");
			Console.print("\n--- Stampa con foreach ---\n");
			for (String error : m.getErrors())
			{
				Console.print("  - " + error + "\n");
			}

			Console.print("\n--- Stampa con for normale ---\n");
			for (int i = 0; i < m.getErrors().size(); i++)
			{
				String error = m.getErrors().get(i);
				Console.print("  - " + error + "\n");
			}
		}
	}

}
