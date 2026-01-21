
package com.generation.mh.view;

import com.generation.mh.model.dto.StatisticsData;
import com.generation.mh.model.entities.Booking;
import com.generation.util.Console;
import com.generation.util.FileWriter;
import com.generation.util.Template;

/**
 * Classe di visualizzazione per la gestione dell'interfaccia utente del Monster Hotel
 */
public class HotelView
{
	/**
	 * Mostra il menu principale del Monster Hotel
	 */
	public static void showMenu()
	{
		String menu = Template.load("template/menu.txt");
		if (menu == null || menu.isEmpty())
		{
			Console.print("Menu non trovato");
		}
		else
		{
			Console.print(menu);
		}
	}

	/**
	 * Genera file HTML di una prenotazione
	 *
	 * @param booking La prenotazione da convertire in HTML
	 */
	public static void printHTML(Booking booking)
	{
		String errors = booking.validate();
		if (!errors.isEmpty())
		{
			Console.print("Errore: La prenotazione non è valida.");
			Console.print(errors);
			return;
		}

		try
		{
			String html = booking.generateHTML();
			String filename = "print/booking_" + booking.getId() + ".html";

			// Crea directory se non esiste
			java.io.File dir = new java.io.File("print");
			if (!dir.exists())
			{
				dir.mkdirs();
			}

			FileWriter writer = new FileWriter(filename);
			writer.print(html);
			writer.close();

			Console.print("File HTML generato con successo: " + filename);
		}
		catch (Exception e)
		{
			Console.print("Errore durante la generazione del file HTML: " + e.getMessage());
		}
	}

	/**
	 * Mostra il risultato dell'operazione di salvataggio prenotazioni.
	 *
	 * @param success true se il salvataggio è riuscito, false altrimenti
	 * @param count Numero di prenotazioni salvate (se success=true)
	 */
	public static void showSaveResult(boolean success, int count)
	{
		Console.print("\nSalvataggio prenotazioni nella cripta...");
		if (success)
		{
			Console.print(count + " prenotazioni salvate con successo!\n");
		}
		else
		{
			if (count == 0)
			{
				Console.print("Nessuna prenotazione da salvare!\n");
			}
			else
			{
				Console.print("Errore durante il salvataggio delle prenotazioni.\n");
			}
		}
	}

	/**
	 * Mostra il risultato dell'operazione di caricamento prenotazioni.
	 *
	 * @param count Numero di prenotazioni caricate, -1 se errore
	 */
	public static void showLoadResult(int count)
	{
		Console.print("\nApertura della cripta...");
		if (count >= 0)
		{
			Console.print(count + " prenotazioni caricate con successo!\n");
		}
		else
		{
			Console.print("Errore durante il caricamento.");
			Console.print("(Il file potrebbe non esistere ancora)\n");
		}
	}

	/**
	 * Mostra le statistiche aggregate dell'hotel in formato formattato.
	 *
	 * @param stats Oggetto contenente tutti i dati statistici, null se nessuna prenotazione
	 */
	public static void showStatistics(StatisticsData stats)
	{
		Console.print("\n═══════════════════════════════════════");
		Console.print("         STATISTICHE MONSTER HOTEL");
		Console.print("═══════════════════════════════════════");

		if (stats == null)
		{
			Console.print("Nessuna prenotazione presente!");
			Console.print("═══════════════════════════════════════\n");
			return;
		}

		// Statistiche base
		Console.print("Totale prenotazioni: " + stats.totalBookings);

		// Distribuzione per specie
		Console.print("\n--- DISTRIBUZIONE PER SPECIE ---");
		Console.print("🧛 Vampiri: " + stats.vampireCount);
		Console.print("🐺 Licantropi: " + stats.werewolfCount);
		Console.print("🧜 Sirene: " + stats.mermaidCount);
		Console.print("👤 Homo Sapiens: " + stats.humanCount);

		// Distribuzione per tipo stanza
		Console.print("\n--- DISTRIBUZIONE PER TIPO STANZA ---");
		Console.print("🛏️  Singole: " + stats.singolaCount);
		Console.print("🛏️🛏️  Doppie: " + stats.doppiaCount);
		Console.print("👑 Suite: " + stats.suiteCount);

		// Statistiche finanziarie
		Console.print("\n--- STATISTICHE FINANZIARIE ---");
		Console.print("Ricavi totali: €" + String.format("%.2f", stats.totalRevenue));
		Console.print("Media per prenotazione: €" + String.format("%.2f", stats.getAverageRevenue()));
		Console.print("Totale notti prenotate: " + stats.totalNights);
		Console.print("Servizi navetta richiesti: " + stats.transportCount);

		Console.print("═══════════════════════════════════════\n");
	}
}