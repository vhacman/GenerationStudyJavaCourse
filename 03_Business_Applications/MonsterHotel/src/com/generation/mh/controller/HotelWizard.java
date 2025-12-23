package com.generation.mh.controller;

import com.generation.view.*;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import com.generation.mh.model.entities.Booking;
import com.generation.mh.model.entities.RoomType;
import com.generation.mh.model.entities.Species;
import com.generation.util.Console;
import com.generation.util.FileReader;
import com.generation.util.FileWriter;
import com.generation.util.Template;
/**
 * Classe principale che gestisce il sistema di prenotazioni del Monster Hotel
 */
public class HotelWizard
{
	// =========== ATTRIBUTI =====================
	private static List<Booking> bookings = new ArrayList<>();
	private static final String SAVE_FILE = "data/bookings.txt";
	private static int nextId = 1;
	
	
	public static void main(String[] args)
	{
		boolean running = true;
		
		Console.print("Inizializzazione Monster Hotel System...");
		Console.print("Sistema pronto");
		while (running)
		{
			HotelView.showMenu();
			
			int choice = Console.readIntBetween("Inserisci la tua scelta:", "Scelta non valida! riprova", 0, 7);
			
			switch (choice)
			{
			case 1:
				createNewBooking();
				break;
			case 2:
				showAllBookings();
				break;
			case 3:
				searchBookingById();
				break;
			case 4:
				saveBookings();
				break;
			case 5:
				loadBookings();
				break;
			case 6:
				generateHTML();
				break;
			case 7:
				showStatics();
				break;
			case 0:
				running = false;
				Console.print("Grazie per aver visitato il Monster Hotel");
				Console.print("Che l notte ti protegga... Arrivederci\n");
				break;
			}
		}
	}

	/**
	 * Genera file HTML di una prenotazione
	 */
	private static void generateHTML()
	{
		Console.print("\nGenera HTML Prenotazione");
		Console.print("Inserisci l'ID della prenotazione: ");
		int 	id = Console.readInt();
		
		Booking found = null;
		for (Booking b : bookings)
		{
			if (b.getId() == id)
			{
				found = b;
				break;
			}
		}
		
		if (found == null)
		{
			Console.print("Prenotazione non trovata!\n");
			return;
		}
		
		HotelView.printHTML(found);
	}

	private static void createNewBooking()
	{
		String label = Template.load("template/label_prenotazione.txt");
		Console.print(label);
		Booking booking = new Booking();
		booking.setId(nextId);
		
		//set Nome
		String firstName = Console.askNotEmptyString("Nome del cliente: ",  "Il nome non può essere vuoto! Riprova: ");
		booking.setFirstName(firstName);
		
		//Set Cognome
		String lastName = Console.askNotEmptyString("Cognome del cliente: ", "Il cognome non può essere vuoto! Riprova: ");
		booking.setLastName(lastName);
	
		//Set data di nascita
		Console.print("Data di nascita (formato YYYY-MM-DD");
		Console.print("Esempio: 1431-11-08 per Vlad Tepes - detto DRACULA");
		String dobString = Console.readString();
		LocalDate dob = LocalDate.parse(dobString);
		booking.setDob(dob);
		
		//set specie
		Console.print("\n Seleziona la specie: ");
		Console.print("   [1] " + Species.VAMPIRE + " - " + Species.VAMPIRE.getConstraints());
		Console.print("   [2] " + Species.WEREWOLF + " - " + Species.WEREWOLF.getConstraints());
		Console.print("   [3] " + Species.MERMAID + " - " + Species.MERMAID.getConstraints());
		Console.print("   [4] " + Species.HOMO_SAPIENS + " - " + Species.HOMO_SAPIENS.getConstraints());
		int speciesChoice = Console.readIntBetween("Scelta: ", "Specie non valida, riprova: ", 1, 4);
		switch (speciesChoice)
		{
			case 1:
				booking.setSpecies(Species.VAMPIRE);
				break;
			case 2:
				booking.setSpecies(Species.WEREWOLF);
				break;
			case 3:
				booking.setSpecies(Species.MERMAID);
				break;
			case 4:
				booking.setSpecies(Species.HOMO_SAPIENS);
				break;
		}
		
		// Tipo stanza
	    Console.print("\n Seleziona il tipo di stanza:");
	    Console.print("   [1] " + RoomType.SINGOLA.getDescription());
	    Console.print("   [2] " + RoomType.DOPPIA.getDescription());
	    Console.print("   [3] " + RoomType.SUITE.getDescription());
	    
	    int roomChoice = Console.readIntBetween("Scelta: ", "Tipo di stanza non valido, riprova!", 1, 3);
	    switch (roomChoice)
	    {
	    	case 1:
	    		booking.setRoomType(RoomType.SINGOLA);
	    		break;
	    	case 2:
	    		booking.setRoomType(RoomType.DOPPIA);
	    		break;
	    	case 3:
	    		booking.setRoomType(RoomType.SUITE);
	    		break;
	    }
	    
	    //numero piano
	    
	    Console.print("\n Inserisci il piano della stanza:");
	    int floor = Console.readInt();
	    booking.setFloor(floor);
	    
	    // date
	    
	    Console.print("\n Data di arrivo (formato: YYYY-MM-DD):");
	    String	arrivalDateString = Console.readString();
	    LocalDate arrivalDate = LocalDate.parse(arrivalDateString);
	    booking.setArrivalDate(arrivalDate);
	    
	    Console.print("\n Data di partenza (formato: YYYY-MM-DD):");
	    String	departureDateString = Console.readString();
	    LocalDate departureDate = LocalDate.parse(departureDateString);
	    booking.setDepartureDate(departureDate);
	    
	    //SERVIZIO NAVETTA
	    
	    String transport = Console.readStringBetween("Desideri il servizio navetta? (€100) [si/no]", 
	    												"Risposta non valida: scrivi 'si' o 'no' ", "si", "no");
	    booking.setTransportService(transport.equalsIgnoreCase("si"));
	    
	    //VALIDAZIONE
	    Console.print("Validazione prenotazione in corso...");
	    String errors = booking.validate();
	    if(errors.isEmpty())
	    {
	     	nextId++;
	     	Console.print("PRENOTAZIONE CREATA CON SUCCESSO");
	        Console.print("═══════════════════════════════════════");
	        Console.print(booking.toString());
	        Console.print("Costo totale: €" + booking.calculateTotalCost());
	        Console.print("═══════════════════════════════════════");
	    }
	    else
	    {
	    	Console.print("ERRORE NELLA PRENOTAZIONE");
	    	Console.print(errors);
	    	Console.print("Prenotazione NON salvata \n");
	    }	    
	}
	
	/**
	 * Mostra statistiche aggregate sulle prenotazioni
	 */
	private static void showStatics()
	{
		Console.print("\n═══════════════════════════════════════");
		Console.print("       📊 STATISTICHE MONSTER HOTEL");
		Console.print("═══════════════════════════════════════");

		if (bookings.isEmpty())
		{
			Console.print("Nessuna prenotazione presente!");
			Console.print("═══════════════════════════════════════\n");
			return;
		}

		// Statistiche base
		Console.print("Totale prenotazioni: " + bookings.size());

		// Conteggio per specie
		int vampireCount = 0;
		int werewolfCount = 0;
		int mermaidCount = 0;
		int humanCount = 0;

		// Conteggio per tipo stanza
		int singolaCount = 0;
		int doppiaCount = 0;
		int suiteCount = 0;

		// Calcoli finanziari
		double totalRevenue = 0.0;
		int totalNights = 0;
		int transportCount = 0;

		// Iterazione su tutte le prenotazioni
		for (Booking b : bookings)
		{
			// Conteggio specie
			switch (b.getSpecies())
			{
				case VAMPIRE:
					vampireCount++;
					break;
				case WEREWOLF:
					werewolfCount++;
					break;
				case MERMAID:
					mermaidCount++;
					break;
				case HOMO_SAPIENS:
					humanCount++;
					break;
			}

			// Conteggio tipo stanza
			switch (b.getRoomType())
			{
				case SINGOLA:
					singolaCount++;
					break;
				case DOPPIA:
					doppiaCount++;
					break;
				case SUITE:
					suiteCount++;
					break;
			}

			// Calcoli finanziari
			totalRevenue += b.calculateTotalCost();
			totalNights += b.getNumberOfNights();
			if (b.isTransportService())
				transportCount++;
		}

		Console.print("\n--- DISTRIBUZIONE PER SPECIE ---");
		Console.print("🧛 Vampiri: " + vampireCount);
		Console.print("🐺 Licantropi: " + werewolfCount);
		Console.print("🧜 Sirene: " + mermaidCount);
		Console.print("👤 Homo Sapiens: " + humanCount);

		Console.print("\n--- DISTRIBUZIONE PER TIPO STANZA ---");
		Console.print("🛏️  Singole: " + singolaCount);
		Console.print("🛏️🛏️  Doppie: " + doppiaCount);
		Console.print("👑 Suite: " + suiteCount);

		Console.print("\n--- STATISTICHE FINANZIARIE ---");
		Console.print("Ricavi totali: €" + String.format("%.2f", totalRevenue));
		Console.print("Media per prenotazione: €" + String.format("%.2f", totalRevenue / bookings.size()));
		Console.print("Totale notti prenotate: " + totalNights);
		Console.print("Servizi navetta richiesti: " + transportCount);

		Console.print("═══════════════════════════════════════\n");
	}
	
	/**
	 * Carica le prenotazioni da file
	 */
	private static void loadBookings()
	{
		Console.print("\nApertura della cripta...");
		
		try
		{
			FileReader reader = new FileReader(SAVE_FILE);
			bookings.clear();
			int maxId = 0;
			
			while (reader.hasNext())
			{
				String 		line = reader.readString();
				String[] 	parts = line.split("\\|");
				
				int 		id = Integer.parseInt(parts[0]);
				String 		firstName = parts[1];
				String 		lastName = parts[2];
				LocalDate 	dob = LocalDate.parse(parts[3]);
				Species 	species = Species.valueOf(parts[4]);
				RoomType 	roomType = RoomType.valueOf(parts[5]);
				int 		floor = Integer.parseInt(parts[6]);
				LocalDate 	arrivalDate = LocalDate.parse(parts[7]);
				LocalDate 	departureDate = LocalDate.parse(parts[8]);
				boolean		transport = Boolean.parseBoolean(parts[9]);
				
				Booking 	b = new Booking(id, firstName, lastName, dob, species, 
									   roomType, floor, arrivalDate, departureDate, transport);
				bookings.add(b);
				
				if (id > maxId)
					maxId = id;
			}
			
			reader.close();
			nextId = maxId + 1;
			Console.print(bookings.size() + " prenotazioni caricate con successo!\n");
		}
		catch (Exception e)
		{
			Console.print("Errore durante il caricamento: " + e.getMessage());
			Console.print("(Il file potrebbe non esistere ancora)\n");
		}
	}

	private static void saveBookings()
	{
		Console.print("\nSalvataggio prenotazioni nella cripta...");
		if (bookings.isEmpty())
		{
			Console.print("Nessuna prenotazione da salvare!\n");
			return;
		}
		
		try 
		{
			FileWriter writer = new FileWriter(SAVE_FILE);
			
			for (Booking b : bookings)
			{
				writer.print(b.getId() + "|");
				writer.print(b.getFirstName() + "|");
				writer.print(b.getLastName() + "|");
				writer.print(b.getDob() + "|");
				writer.print(b.getSpecies().name() + "|");
				writer.print(b.getRoomType().name() + "|");
				writer.print(b.getFloor() + "|");
				writer.print(b.getArrivalDate() + "|");
				writer.print(b.getDepartureDate() + "|");
				writer.print(b.isTransportService() + "\n");
			}
			writer.close();
			Console.print(bookings.size() + "Prenotazione salvata con successo!\n");
			
		}
		catch (Exception e)
		{
			Console.print("Errore durante il salvataggio: " + e.getMessage() + "\n");
		}		
	}

	private static void searchBookingById()
	{
		String label = Template.load("template/label_cerca.txt");
		Console.print(label);
		
		Console.print("Inserisci ID prenotazione: ");
		int searchId = Console.readInt();
		Booking found = null;
		for (Booking b : bookings)
		{
			if(b.getId() == searchId)
			{
				found = b;
				break;
			}
		}
		
		if (found != null)
		{
			Console.print("\nPRENOTAZIONE TROVATA:");
			Console.print("═══════════════════════════════════════");
			Console.print(found.toString());
			Console.print("Costo totale: €" + found.calculateTotalCost());
			Console.print("═══════════════════════════════════════\n");
		}
		else
		{
			Console.print("\nPrenotazione #" + searchId + " non trovata!");
			Console.print("Forse è stata cancellata... o non è mai esistita!\n");
		}
	}

	/**
	 * Visualizza tutte le prenotazioni
	 */
	private static void showAllBookings()
	{
		String label = Template.load("template/label_visualizza.txt");
		Console.print(label);
		if(bookings.isEmpty())
		{
			Console.print("Nessuna prenotazione trovata!");
			return ;
		}
		Console.print("Totale prenotazioni: " + bookings.size() + "\n");
		for (Booking b : bookings)
		{
			Console.print("─────────────────────────────────────────");
			Console.print(b.toString());
			Console.print("Totale: €" + b.calculateTotalCost());
		}
		Console.print("─────────────────────────────────────────");
	}

}
