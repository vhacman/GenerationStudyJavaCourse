package com.generation.view;

import com.generation.mh.model.entities.Booking;
import com.generation.util.Console;
import com.generation.util.FileWriter;
import com.generation.util.Template;

/**
 * Classe di visualizzazione per la gestione dell'interfaccia utente del Monster Hotel
 */
public class HotelView
{
	// ========== METODI STATICI ==========

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
}