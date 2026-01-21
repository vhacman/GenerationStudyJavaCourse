package com.generation.nsmpi.model.entities;

import com.generation.library.Console;

/**
 * Demo per l'inserimento e validazione di un ambulatorio.
 * Raccoglie i dati da console e verifica la validità dell'entità ServiceRoom.
 */
public class DemoInsertServiceRoom
{

	public static void main(String[] args)
	{
		ServiceRoom  room        = new ServiceRoom();

		Console.print("=== Inserimento Ambulatorio ===\n");
		Console.print("Inserisci la descrizione dell'ambulatorio: ");
		String       description = Console.readString();
		room.setDescription(description);

		Console.print("Inserisci il piano (da -10 a +10): ");
		int          floor       = Console.readInt();
		room.setFloor(floor);

		Console.print("\n=== Risultato Validazione ===\n");

		if (room.isValid())
		{
			Console.print(" Ambulatorio valido!\n");
			Console.print(room.toString());
		}
		else
		{
			Console.print(" Ambulatorio non valido. Errori rilevati:\n");
			Console.print("\n--- Stampa con foreach ---\n");
			for (String error : room.getErrors())
			{
				Console.print("  - " + error + "\n");
			}

			Console.print("\n--- Stampa con for normale ---\n");
			for (int i = 0; i < room.getErrors().size(); i++)
			{
				String error = room.getErrors().get(i);
				Console.print("  - " + error + "\n");
			}
		}
	}

}
