package com.generation.lbb.main;

import java.time.LocalDate;

import com.generation.lbb.model.entities.Booking;
import com.generation.lbb.model.entities.MembershipType;
import com.generation.lbb.model.entities.RoomType;
import com.generation.lbb.view.BookingView;
import com.generation.library.Console;
import com.generation.library.FileWriter;

public class Main
{

	public static void main(String[] args)
	{
		boolean goOn = true;
		Booking lastBooking = null;

		do
		{
			// Mostra il menu
			Console.print("\n=== SISTEMA GESTIONE PRENOTAZIONI ===\n");
			Console.print("1. Inserire prenotazione\n");
			Console.print("2. Visualizza anteprima prenotazione (TXT)\n");
			Console.print("3. Esci\n");
			Console.print("Scegli un'opzione: ");

			int choice = Console.readInt();

			switch(choice)
			{
				case 1:
					// Inserisce una nuova prenotazione e genera HTML
					lastBooking = askBooking();
					if (lastBooking.isValid())
					{
						printClientViewHTML(lastBooking);
						Console.print("\n\nPrenotazione inserita con successo!\n");
					}
					else
					{
						Console.print("\n\nERRORE: Prenotazione non valida. Controlla i dati inseriti.\n");
					}
					break;

				case 2:
					// Visualizza anteprima dell'ultima prenotazione inserita
					if (lastBooking != null && lastBooking.isValid())
					{
						printPreviewTXT(lastBooking);
					}
					else if (lastBooking == null)
					{
						Console.print("\n\nERRORE: Nessuna prenotazione disponibile. Inserire prima una prenotazione (opzione 1).\n");
					}
					else
					{
						Console.print("\n\nERRORE: L'ultima prenotazione non è valida.\n");
					}
					break;

				case 3:
					// Esce dal programma
					goOn = exitMenu();
					break;

				default:
					// Opzione non valida
					Console.print("Opzione non valida. Riprova.\n");
					break;
			}

		} while(goOn);
	}


	private static Booking askBooking()
	{
		// Crea oggetto prenotazione vuoto
		Booking b = new Booking();

		Console.print("Inserire id: ");
		b.id = Console.readInt();

		// INPUT Nome
		Console.print("Inserire Nome: ");
		b.firstName = Console.readString();

		// INPUT Cognome
		Console.print("Inserire Cognome: ");
		b.lastName = Console.readString();

		// INPUT CHECK-IN
		Console.print("Inserire Check In in formato YYYY-MM-DD: ");
		// Converte stringa in LocalDate (es: "2025-12-20")
		b.checkIn = LocalDate.parse(Console.readString());

		// INPUT DATA DI NASCITA
		Console.print("Inserire data di nascita in formato YYYY-MM-DD: ");
		// Converte stringa in LocalDate (es: "2025-12-20")
		b.dob = LocalDate.parse(Console.readString());

		// INPUT CHECK-OUT
		Console.print("Inserire Check Out in formato YYYY-MM-DD: ");
		b.checkOut = LocalDate.parse(Console.readString());

		// INPUT TIPO CAMERA (enum, non stringa!)
		Console.print("Inserire tipo di stanza fra BASIC, MIDDLE, SUPERIOR, SUITE: ");
		// valueOf() converte la stringa nell'enum corrispondente
		b.roomType = RoomType.valueOf(Console.readString());

		// Mostra prezzo base (notti × costo camera)
		Console.print("Costo senza sconti applicati: " + b.getBasicPrice() + " €\n");

		// INPUT MEMBERSHIP (livello abbonamento)
		Console.print("Inserire livello di Membership (NONE, SILVER, GOLD): ");
		b.membershipType = MembershipType.valueOf(Console.readString());

		// Mostra percentuale di sconto
		Console.print("Lei ha uno sconto di: " + b.getDiscount() + " €\n");

		// Calcola e mostra numero di notti
		System.out.println("Le resterà: " + b.getNights() + " notti");

		// Mostra prezzo finale con sconto applicato
		Console.print("Lei paga: " + b.getFinalPrice() + " €");

		return b;
	}

	private static boolean exitMenu()
	{
		Console.print("\n--- USCITA DAL PROGRAMMA ---\n");
		Console.print("Grazie per aver utilizzato il sistema di prenotazione!\n");
		Console.print("Arrivederci!\n");
		return false;
	}

	private static void printClientViewHTML(Booking b)
	{
		BookingView clientView = new BookingView("template/template.html");
		String filename = "booking_" + b.id + ".html";
		FileWriter.writeTo(filename, clientView.render(b));
		Console.print("\n✓ File HTML generato con successo: " + filename);
		Console.print("\n✓ Prenotazione salvata!");
	}

	private static void printPreviewTXT(Booking b)
	{
		BookingView viewPreview = new BookingView("template/template.txt");
		Console.print("\n--- PREVIEW PRENOTAZIONE ---\n");
		Console.print(viewPreview.render(b));
		Console.print("\n✓ Anteprima generata con successo!");
	}

}