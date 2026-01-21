package com.generation.mh.controller;

import java.time.LocalDate;

import com.generation.mh.model.dto.StatisticsData;
import com.generation.mh.model.entities.Booking;
import com.generation.mh.model.entities.RoomType;
import com.generation.mh.model.entities.Species;
import com.generation.mh.view.HotelView;
import com.generation.util.Console;
import com.generation.util.Template;

/**
 * Service per la logica di business delle prenotazioni.
 * Gestisce la creazione interattiva, validazione e generazione report.
 */
public class BookingService
{
	private BookingRepository repository;
	
	public BookingService(BookingRepository repository)
	{
		this.repository = repository;
	}

	/**
	 * Avvia il wizard interattivo per creare una nuova prenotazione passo-passo.
	 *
	 * COSA FA:
	 * - Mostra un'interfaccia testuale guidata (wizard) all'utente
	 * - Chiede TUTTI i dati necessari per una prenotazione:
	 *   • Nome e cognome del cliente
	 *   • Data di nascita
	 *   • Specie (vampiro, licantropo, sirena, umano)
	 *   • Tipo di stanza (singola, doppia, suite)
	 *   • Piano della stanza
	 *   • Date di arrivo e partenza
	 *   • Servizio navetta (si/no)
	 * - Gestisce errori di formato (es. date invalide) chiedendo di riprovare
	 * - Mostra avvisi preventivi (es. vincoli per vampiri)
	 * - VALIDA la prenotazione secondo le regole di business
	 * - Se valida: salva nel repository, incrementa ID, mostra conferma con riepilogo
	 * - Se non valida: mostra errori e NON salva nulla
	 *
	 * QUANDO USARLO:
	 * - Quando l'utente sceglie "Nuova Prenotazione" dal menu
	 * - Per creare manualmente una prenotazione con tutti i controlli
	 */
	public void createNewBooking()
	{
		String label = Template.load("template/label_prenotazione.txt");
		Console.print(label);

		Booking booking = new Booking();
		booking.setId(repository.getNextId());

		askPersonalData(booking);
		askSpecies(booking);
		askRoomDetails(booking);
		askStayDates(booking);
		validateAndSave(booking);
	}

	/**
	 * Chiede i dati anagrafici del cliente (nome, cognome, data di nascita).
	 *
	 * @param booking La prenotazione da popolare
	 */
	private void askPersonalData(Booking booking)
	{
		String firstName = Console.askNotEmptyString("Nome del cliente: ", "Il nome non può essere vuoto! Riprova: ");
		booking.setFirstName(firstName);
		String lastName = Console.askNotEmptyString("Cognome del cliente: ", "Il cognome non può essere vuoto! Riprova: ");
		booking.setLastName(lastName);
		Console.print("Data di nascita (formato YYYY-MM-DD");
		Console.print("Esempio: 1431-11-08 per Vlad Tepes - detto DRACULA");
		LocalDate dob = null;
		while (dob == null)
		{
			try
			{
				String dobString = Console.readString();
				dob = LocalDate.parse(dobString);
			}
			catch (Exception e)
			{
				Console.print("Formato data non valido! Usa YYYY-MM-DD. Riprova: ");
			}
		}
		booking.setDob(dob);
	}

	/**
	 * Chiede la selezione della specie del cliente.
	 *
	 * @param booking La prenotazione da popolare
	 */
	private void askSpecies(Booking booking)
	{
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
	}

	/**
	 * Chiede tipo di stanza e piano.
	 *
	 * @param booking La prenotazione da popolare
	 */
	private void askRoomDetails(Booking booking)
	{
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

		// Numero piano
		Console.print("\n Inserisci il piano della stanza:");
		if (booking.getSpecies() == Species.VAMPIRE)
		{
			Console.print("⚠️  ATTENZIONE: I vampiri possono stare SOLO su piani negativi (es: -1, -2, -3)");
		}
		int floor = Console.readInt();
		booking.setFloor(floor);
	}

	/**
	 * Chiede le date di soggiorno e il servizio navetta.
	 *
	 * @param booking La prenotazione da popolare
	 */
	private void askStayDates(Booking booking)
	{
		// Data arrivo
		Console.print("\n Data di arrivo (formato: YYYY-MM-DD):");
		LocalDate arrivalDate = null;
		while (arrivalDate == null)
		{
			try
			{
				String arrivalDateString = Console.readString();
				arrivalDate = LocalDate.parse(arrivalDateString);
			}
			catch (Exception e)
			{
				Console.print("Formato data non valido! Usa YYYY-MM-DD. Riprova: ");
			}
		}
		booking.setArrivalDate(arrivalDate);
		Console.print("\n Data di partenza (formato: YYYY-MM-DD):");
		LocalDate departureDate = null;
		while (departureDate == null)
		{
			try
			{
				String departureDateString = Console.readString();
				departureDate = LocalDate.parse(departureDateString);
			}
			catch (Exception e)
			{
				Console.print("Formato data non valido! Usa YYYY-MM-DD. Riprova: ");
			}
		}
		booking.setDepartureDate(departureDate);
		String transport = Console.readStringBetween("Desideri il servizio navetta? (€100) [si/no]",
													"Risposta non valida: scrivi 'si' o 'no' ", "si", "no");
		booking.setTransportService(transport.equalsIgnoreCase("si"));
	}

	/**
	 * Valida la prenotazione e la salva se corretta, altrimenti mostra gli errori.
	 *
	 * @param booking La prenotazione da validare e salvare
	 */
	private void validateAndSave(Booking booking)
	{
		Console.print("Validazione prenotazione in corso...");
		String errors = booking.validate();

		if (errors.isEmpty())
		{
			repository.addBooking(booking);
			repository.incrementNextId();
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
	 * Mostra l'elenco completo di tutte le prenotazioni presenti nel sistema.
	 *
	 * COSA FA:
	 * - Carica il template grafico di visualizzazione
	 * - Controlla se ci sono prenotazioni nel repository
	 * - Se vuoto: mostra messaggio "Nessuna prenotazione trovata"
	 * - Se ci sono prenotazioni:
	 *   • Mostra il totale prenotazioni
	 *   • Per ogni prenotazione mostra: ID, cliente, specie, tipo stanza, date, costo
	 *   • Formatta tutto con separatori grafici
	 *
	 * QUANDO USARLO:
	 * - Quando l'utente sceglie "Elenco Prenotazioni" dal menu
	 * - Per vedere rapidamente tutte le prenotazioni attive
	 * - Per avere una panoramica generale
	 */
	public void showAllBookings()
	{
		String label = Template.load("template/label_visualizza.txt");
		Console.print(label);

		if (repository.isEmpty())
		{
			Console.print("Nessuna prenotazione trovata!");
			return;
		}

		Console.print("Totale prenotazioni: " + repository.getBookingsCount() + "\n");
		for (Booking b : repository.getAllBookings())
		{
			Console.print("─────────────────────────────────────────");
			Console.print(b.toString());
			Console.print("Totale: €" + b.calculateTotalCost());
		}
		Console.print("─────────────────────────────────────────");
	}

	/**
	 * Cerca una prenotazione specifica tramite ID e ne mostra i dettagli.
	 *
	 * COSA FA:
	 * - Carica il template grafico di ricerca
	 * - Chiede all'utente di inserire un ID numerico
	 * - Cerca la prenotazione nel repository
	 * - Se trovata:
	 *   • Mostra messaggio "PRENOTAZIONE TROVATA"
	 *   • Visualizza tutti i dettagli: cliente, specie, stanza, date, costo totale
	 *   • Formatta con separatori grafici
	 * - Se NON trovata:
	 *   • Mostra messaggio "Prenotazione #X non trovata"
	 *   • Suggerisce che potrebbe essere stata cancellata
	 *
	 * QUANDO USARLO:
	 * - Quando l'utente sceglie "Cerca Prenotazione" dal menu
	 * - Per trovare velocemente una prenotazione specifica
	 * - Per verificare l'esistenza di una prenotazione
	 */
	public void searchBookingById()
	{
		String label = Template.load("template/label_cerca.txt");
		Console.print(label);

		Console.print("Inserisci ID prenotazione: ");
		int searchId = Console.readInt();
		Booking found = repository.getBookingById(searchId);

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
	 * Genera un documento HTML stampabile per una prenotazione specifica.
	 *
	 * COSA FA:
	 * - Chiede all'utente l'ID della prenotazione da stampare
	 * - Cerca la prenotazione nel repository
	 * - Se NON trovata: mostra messaggio di errore e termina
	 * - Se trovata:
	 *   • Delega a HotelView la generazione del file HTML
	 *   • Crea un file "print/booking_X.html" (dove X è l'ID)
	 *   • Il file contiene tutti i dettagli formattati in HTML
	 *   • Mostra messaggio di conferma con il percorso del file
	 *
	 * QUANDO USARLO:
	 * - Quando l'utente sceglie "Genera HTML" dal menu
	 * - Per creare un documento stampabile/condivisibile
	 * - Per avere una versione formattata della prenotazione
	 */
	public void generateHTML()
	{
		Console.print("\nGenera HTML Prenotazione");
		Console.print("Inserisci l'ID della prenotazione: ");
		int id = Console.readInt();

		Booking found = repository.getBookingById(id);

		if (found == null)
		{
			Console.print("Prenotazione non trovata!\n");
			return;
		}

		HotelView.printHTML(found);
	}

	/**
	 * Salva le prenotazioni su file e mostra il risultato all'utente.
	 *
	 * COSA FA (dal punto di vista esterno):
	 * - Verifica prima se ci sono prenotazioni da salvare
	 * - Chiama il repository per salvare i dati
	 * - Delega a HotelView la visualizzazione del risultato (successo/errore)
	 */
	public void saveBookings()
	{
		int count = repository.getBookingsCount();

		// Se lista vuota, passa count=0 e success=false
		if (count == 0)
		{
			HotelView.showSaveResult(false, 0);
			return;
		}

		// Altrimenti tenta il salvataggio
		boolean success = repository.saveBookings();
		HotelView.showSaveResult(success, count);
	}

	/**
	 * Carica le prenotazioni da file e mostra il risultato all'utente.
	 *
	 * COSA FA:
	 * - Chiama il repository per caricare i dati
	 * - Delega a HotelView la visualizzazione del risultato (numero caricato/errore)
	 */
	public void loadBookings()
	{
		int count = repository.loadBookings();
		HotelView.showLoadResult(count);
	}

	/**
	 * Calcola e mostra le statistiche dell'hotel.
	 *
	 * COSA FA:
	 * - Chiama il repository per ottenere i dati statistici
	 * - Delega a HotelView la visualizzazione formattata
	 */
	public void showStatistics()
	{
		StatisticsData stats = repository.getStatistics();
		HotelView.showStatistics(stats);
	}
}
