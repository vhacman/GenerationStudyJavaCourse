package com.generation.nsmpi.model.entities;

import com.generation.library.Console;

/**
 * Demo per l'inserimento e validazione di un paziente.
 * Raccoglie i dati da console e verifica la validità dell'entità Patient.
 */
public class DemoInsertPatient
{

	public static void main(String[] args)
	{
		Patient  p         = new Patient();

		Console.print("=== Inserimento Paziente ===\n");
		Console.print("Inserisci il nome: ");
		String   firstName = Console.readString();
		p.setFirstName(firstName);

		Console.print("Inserisci il cognome: ");
		String   lastName  = Console.readString();
		p.setLastName(lastName);

		Console.print("Inserisci la data di nascita (formato YYYY-MM-DD): ");
		String   dob       = Console.readString();
		p.setDob(dob);

		Console.print("Inserisci il genere (M/F/N): ");
		String   gender    = Console.readString();
		p.setGender(gender);

		Console.print("Inserisci l'anamnesi clinica: ");
		String   history   = Console.readString();
		p.setHistory(history);

		String   risposta;
		do
		{
			Console.print("\nVuoi inserire un'allergia? (s/n): ");
			risposta = Console.readString().toLowerCase();

			if (risposta.equals("s"))
			{
				Console.print("Inserisci l'allergia: ");
				String allergy = Console.readString();
				p.addAllergy(allergy);
				Console.print("Allergia aggiunta: " + allergy + "\n");
			}

		} while (risposta.equals("s"));

		Console.print("\n=== Risultato Validazione ===\n");

		if (p.isValid())
		{
			Console.print(" Paziente valido!\n");
			Console.print(p.toString());
		}
		else
		{
			Console.print(" Paziente non valido. Errori rilevati:\n");
			Console.print("\n--- Stampa con foreach ---\n");
			for (String error : p.getErrors())
			{
				Console.print("  - " + error + "\n");
			}

			Console.print("\n--- Stampa con for normale ---\n");
			for (int i = 0; i < p.getErrors().size(); i++)
			{
				String error = p.getErrors().get(i);
				Console.print("  - " + error + "\n");
			}
		}
	}

}
