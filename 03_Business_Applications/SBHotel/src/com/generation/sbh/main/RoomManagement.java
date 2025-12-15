package com.generation.sbh.main;

import java.time.LocalDate;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

/**
 * Sistema di gestione prenotazioni per hotel
 * Permette di prenotare camere, annullare prenotazioni e visualizzare le stanze disponibili
 */
public class RoomManagement
{

	public static void main(String[] args)
	{
		// Percorsi dei file template
		final String LOGOPATH = "assets/logo.txt";
		final String MENUPATH = "assets/menu.txt";
		final String ROOMPATH = "assets/rooms.txt";

		// Caricamento dei template da file
		final String LOGO = Template.load(LOGOPATH);
		final String MENU = Template.load(MENUPATH);
		final String ROOMLIST = Template.load(ROOMPATH);

		// Stampa il logo dell'hotel all'avvio
		Console.print(LOGO);

		String cmd; // Comando scelto dall'utente (1-4)
		
		// Loop principale del programma - continua finché l'utente non sceglie di uscire
		do
		{
			Console.print(MENU); // Mostra il menu delle opzioni
			cmd = Console.readString(); // Legge la scelta dell'utente
			
			// Gestisce le diverse opzioni del menu
			switch(cmd)
			{
				case "1":
					bookRoom();
				break;
				case "2":
					cancelBooking();
				break;
				case "3":
					Console.print(ROOMLIST);
				break;
				case "4":
					RoomCleaningOrder.orderCleaningService();
				break;
				case "5":
					Console.print("Goodbye cruel world it's over, walk on by");
				break;
				default:
					Console.print("Comando non riconosciuto");
			}

		} while (!cmd.equals("5"));
	}


	/**
	 * Gestisce la prenotazione di una camera
	 * Raccoglie informazioni su ospiti, durata, servizi extra e genera un documento HTML di conferma
	 */
	private static void bookRoom()
	{
		Console.print("Per quante persone devo prenotare?");
		int n = Console.readInt();

		if(n < 1 || n > 4)
		{
			Console.print("Valori ammissibili fra 1 e 4");
			return;
		}

		// STEP 2: Raccolta nomi degli ospiti
		String guests = "";
		for(int i = 0; i < n; i++)
		{
			Console.print("Inserire nome completo persona "+(i+1));
			String fullName = Console.readString();
			guests += fullName + "\n"; 
		}

		// STEP 3: Raccolta numero di notti
		Console.print("Per quante notti?");
		int nights = Console.readInt();

		if(nights < 1 || nights > 7)
		{
			Console.print("Numero notti deve essere tra 1 e 7");
			return; 
		}

		// STEP 4: Richiesta servizio navetta
		Console.print("Vuole il servizio navetta? (20 euro a persona) [s/n]");
		String wantsShuttle = Console.readString();
		
		// Calcolo costo navetta (20€ a persona, una tantum)
		int shuttleCost = 0;
		String shuttleInfo = "No";
		
		if(wantsShuttle.equals("s"))
		{
			shuttleCost = n * 20; // Moltiplica 20€ per numero di persone
			shuttleInfo = "Sì - € " + shuttleCost;
		}

		// STEP 5: Scelta della stanza
		Console.print("Che stanza devo prenotare?");
		int nRoom = Console.readInt();

		if(nRoom < 1 || nRoom > 4)
		{
			Console.print("Stanza valide da 1 a 4");
			return; // Esce se la stanza non esiste
		}

		// STEP 6: Calcolo prezzo in base alla stanza scelta
		int pricePerNight = 220; // Prezzo di default per stanza 4
		switch(nRoom)
		{
			case 1:
				pricePerNight = 100; // Stanza 1: 100€ a notte
			break;
			case 2:
				pricePerNight = 80; // Stanza 2: 80€ a notte
			break;
			case 3:
				pricePerNight = 70; // Stanza 3: 70€ a notte
			break;
		}

		// STEP 7: Calcoli finali
		int roomTotal = pricePerNight * nights;
		int totalCost = roomTotal + shuttleCost;
		final LocalDate today = LocalDate.now();

		// STEP 8: Generazione codice prenotazione univoco
		Console.print("Inserire codice prenotazione");
		String code = Console.readString();

		String printTemplate = Template.load("assets/template/booking.html");
		printTemplate = printTemplate
				.replace("[code]", code) // Codice prenotazione
				.replace("[nRoom]", nRoom+"") // Numero stanza (notare: [nRoom] nel template)
				.replace("[pricePerNight]", pricePerNight+"") // Prezzo per notte
				.replace("[nights]", nights+"") // Numero di notti
				.replace("[roomTotal]", roomTotal+"") // Totale camera
				.replace("[shuttle]", shuttleInfo) // Info navetta
				.replace("[totalCost]", totalCost+"") // Totale finale
				.replace("[guests]", guests) // Lista ospiti (senza <br/>, usa \n)
				.replace("[guests_count]", n+"") // Numero di ospiti
				.replace("[date]", today+""); // Data prenotazione

		String filename = "print/" + code + ".html";
		FileWriter.writeTo(filename, printTemplate);
		Console.print("Prenotazione salvata in " + filename);
	}


	/**
	 * Gestisce l'annullamento di una prenotazione
	 * Calcola il rimborso (massimo 2 notti) e genera un buono HTML nominativo
	 */
	private static void cancelBooking()
	{
	    // Chiedi codice prenotazione
	    Console.print("Inserire codice della prenotazione da annullare:");
	    String code = Console.readString();
	    
	    // Chiedi in che stanza era
	    Console.print("Inserire numero di stanza:");
	    int roomNumber = Console.readInt();
	    
	    if(roomNumber < 1 || roomNumber > 4)
	    {
	        Console.print("Stanza valide da 1 a 4");
	        return;
	    }   
	    Console.print("Inserire numero di notti prenotate:");
	    int nights = Console.readInt();
	    
	    if(nights < 1 || nights > 7)
	    {
	        Console.print("Numero notti deve essere tra 1 e 7");
	        return;
	    }
	    
	    // Calcola prezzo per notte
	    int pricePerNight = 220;
	    switch(roomNumber)
	    {
	        case 1:
	            pricePerNight = 100;
	        break;
	        case 2:
	            pricePerNight = 80;
	        break;
	        case 3:
	            pricePerNight = 70;
	        break;
	    }
	    
	    // Rimborso massimo 2 notti
	    int nightsRefunded = nights > 2 ? 2 : nights;
	    int refundAmount = pricePerNight * nightsRefunded;
	    
	    final LocalDate today = LocalDate.now();
	    
	    // Carica template e sostituisci
	    String cancelTemplate = Template.load("assets/template/cancellationTemplate.html");
	    cancelTemplate = cancelTemplate
	            .replace("[code]", code)
	            .replace("[room]", roomNumber+"")
	            .replace("[nightsBooked]", nights+"")
	            .replace("[nightsRefunded]", nightsRefunded+"")
	            .replace("[refundAmount]", refundAmount+"")
	            .replace("[date]", today+"");
	    String filename = "print/CANCEL_" + code + ".html";
	    FileWriter.writeTo(filename, cancelTemplate);
	    Console.print("Buono di rimborso salvato in " + filename);
	}
}