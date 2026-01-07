package com.generation.vr.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import com.generation.util.Console;
import com.generation.util.Template;
import com.generation.vr.model.dto.StatisticsData;
import com.generation.vr.model.entities.Attraction;
import com.generation.vr.model.entities.Hotel;
import com.generation.vr.model.entities.TourGuide;
import com.generation.vr.model.entities.WeekendTrip;
import com.generation.vr.view.WeekendTripView;

public class WeekendTripService
{
	private WeekendTripRepository	repository;

	public WeekendTripService(WeekendTripRepository repository) {
		this.repository = repository;
	}

	/**
	 * Crea un nuovo WeekendTrip attraverso un wizard
	 * Guida l'utente step-by-step nella creazione di un viaggio completo:
	 * 1. Dati personali (nome e cognome)
	 * 2. Attrazioni per sabato e domenica
	 * 3. Hotel
	 * 4. Guida turistica
	 * 5. Data del viaggio
	 * 6. Validazione e salvataggio
	 */
	public void createNewTrip() {
		// Carica e mostra il template di intestazione
		Console.print(Template.load("template/label_prenotazione.txt"));
		WeekendTrip	trip;
		// Crea un nuovo oggetto trip e assegna l'ID progressivo
		trip = new WeekendTrip();
		trip.setId(repository.getNextId());
		// Esegue tutti gli step del wizard in sequenza
		askPersonalData(trip);		// Step 1: nome e cognome
		askAttractions(trip);		// Step 2: attrazioni per sabato e domenica
		askHotel(trip);				// Step 3: hotel
		askTourGuide(trip);			// Step 4: guida turistica
		askDate(trip);				// Step 5: data del viaggio
		validateAndSave(trip);		// Step finale: validazione e salvataggio
	}

	/**
	 * Mostra tutti i trip presenti nel repository
	 * Se il repository è vuoto, mostra un messaggio appropriato
	 * Altrimenti recupera tutti i trip e li passa alla View per la visualizzazione
	 */
	public void showAllTrips()
	{
		Console.print(Template.load("template/label_visualizza.txt"));

		ArrayList<WeekendTrip>	trips;
		
		if (repository.isEmpty()) {
			Console.print("Nessun trip presente nel sistema.\n");
			return;
		}
		trips = repository.getAllTrips();
		WeekendTripView.showAllTrips(trips);
	}

	/**
	 * Cerca e mostra un trip specifico tramite ID
	 * Chiede all'utente l'ID del trip da cercare
	 * Valida che l'ID sia nel range corretto (da 1 all'ultimo ID assegnato)
	 * Se trovato mostra il trip, altrimenti mostra un messaggio di errore
	 */
	public void searchTripById()
	{
		Console.print(Template.load("template/label_cerca.txt"));
		int				id;
		WeekendTrip		trip;
		
		if (repository.isEmpty()) {
			Console.print("Nessun trip presente nel sistema.\n");
			return;
		}
		id = Console.readIntBetween("Inserisci l'ID del trip da cercare: ", "ID non valido!", 1, repository.getNextId() - 1);
		trip = repository.getTripById(id);
		if (trip != null)
			WeekendTripView.showTrip(trip);
		else
			Console.print("Trip con ID " + id + " non trovato.\n");
	}

	/**
	 * Salva tutti i trip su file
	 * Esegue il salvataggio tramite il repository
	 * Delega alla View la visualizzazione del risultato (successo/fallimento e numero di trip salvati)
	 */
	public void saveTrips()
	{
		boolean 	success = repository.saveTrips();
		int 		count = repository.getTripsCount();
		WeekendTripView.showSaveResult(success, count);
	}
	
	/**
	 * Carica i trip da file
	 * Esegue il caricamento tramite il repository
	 * Delega alla View la visualizzazione del risultato (numero di trip caricati)
	 */
	public void loadTrips()
	{
		int 	count = repository.loadTrips();
		WeekendTripView.showLoadResult(count);
	}

	/**
	 * Mostra le statistiche aggregate di tutti i trip
	 * Calcola e visualizza dati come: numero totale trip, incasso totale,
	 * attrazione più visitata, hotel più prenotato, guida più richiesta, ecc.
	 * Se non ci sono trip nel sistema, mostra un messaggio appropriato
	 */
	public void showStatistics()
	{
		StatisticsData		stats;

		if (repository.isEmpty()) {
			Console.print("Nessun trip presente. Impossibile calcolare statistiche.\n");
			return;
		}
		stats = repository.getStatistics();
		WeekendTripView.showStatistics(stats);
	}

	/**
	 * Genera file HTML per un trip specifico
	 * Chiede l'ID del trip, lo cerca nel repository
	 * Se trovato, genera il file HTML tramite la View
	 * Se non trovato, mostra un messaggio di errore
	 */
	public void generateHTML()
	{
		WeekendTrip		trip;
		int				id;

		if (repository.isEmpty()) {
			Console.print("Nessun trip presente nel sistema.\n");
			return;
		}
		Console.print("\n=== GENERA HTML TRIP ===");
		id = Console.readIntBetween("Inserisci l'ID del trip: ", "ID non valido!", 1, repository.getNextId() - 1);
		trip = repository.getTripById(id);
		if (trip != null)
			WeekendTripView.printHTML(trip);
		else
			Console.print("Trip con ID " + id + " non trovato.\n");
	}

	/**
	 * Step 1: Raccolta dati personali
	 * Chiede nome e cognome dell'utente
	 * Valida che entrambi i campi non siano vuoti
	 * Imposta i dati raccolti nell'oggetto trip
	 */
	private void askPersonalData(WeekendTrip trip)
	{
		Console.print("\n=== DATI PERSONALI ===");
		String 		name;
		String	 	surname;
		
		name = Console.askNotEmptyString("Nome: ", "Il nome non può essere vuoto!");
		surname = Console.askNotEmptyString("Cognome: ", "Il cognome non può essere vuoto!");
		trip.setName(name);
		trip.setSurname(surname);
	}

	/**
	 * Step 2: Selezione attrazioni per sabato e domenica
	 * Chiede due attrazioni separate per i due giorni del weekend
	 * Utilizza il metodo helper askAttraction per mostrare il menu di scelta
	 */
	private void askAttractions(WeekendTrip trip)
	{
		Console.print("\n=== ATTRAZIONI ===");
		trip.setSaturdayVisit(askAttraction("Scegli l'attrazione per SABATO:"));
		trip.setSundayVisit(askAttraction("Scegli l'attrazione per DOMENICA:"));
	}

	/**
	 * Helper per selezionare un'attrazione con menu numerato
	 * Mostra tutte le attrazioni disponibili con nome, prezzo e orari
	 * Permette all'utente di scegliere tramite numero (1-based)
	 * Restituisce l'attrazione selezionata (convertendo l'indice da 1-based a 0-based)
	 */
	private Attraction askAttraction(String message)
	{
		Console.print("\n" + message);
		
		//.values() restituisce un array di enum --> bisogna iterare sulla "lista" di attrazioni
		Attraction[]		attractions = Attraction.values();
		int					choice;
		//PER i DA 0 A lunghezza(attrazioni) - 1 FARE
		for (int i = 0; i < attractions.length; i++)
		{
			Console.print((i + 1) + ". " + attractions[i].getName() + " - " +
						  attractions[i].getFormattedPrice() + " € (Orari: " +
						  attractions[i].getOpeningTime() + " - " +
						  attractions[i].getClosingTime() + ")");
		}

		choice = Console.readIntBetween( "Scegli (1-" + attractions.length + "): ", "Scelta non valida!", 1, attractions.length);

		//  // Restituisce l'attrazione selezionata (convertendo da 1-based a 0-based)
	    //RITORNA attrazioni[scelta - 1]
		return (attractions[choice - 1]);
	}

	/**
	 * Step 3: Selezione hotel
	 * Mostra tutti gli hotel disponibili con nome, indirizzo e prezzo a notte
	 * Permette all'utente di scegliere tramite numero (1-based)
	 * Imposta l'hotel selezionato nell'oggetto trip
	 */
	private void askHotel(WeekendTrip trip)
	{
		Console.print("\n=== HOTEL ===");
		
		//  // Ottiene tutti gli hotel disponibili come array-->   hotels[] <- Hotel.values()
		Hotel[]		hotels;
		int			choice;
		
		hotels = Hotel.values();
		for (int i = 0; i < hotels.length; i++)
		{
			Console.print((i + 1) + ". " + hotels[i].getName() + " - " +
						  hotels[i].getAddress() + " (" +
						  hotels[i].getFormattedPrice() + " € a notte)");
		}
		choice = Console.readIntBetween("Scegli (1-" + hotels.length + "): ", "Scelta non valida!", 1, hotels.length);
		// Imposta l'hotel selezionato nel viaggio (convertendo da 1-based a 0-based)
		trip.setAffiliatedHotel(hotels[choice - 1]);
	}

	/**
	 * Step 4: Selezione guida turistica
	 * Mostra tutte le guide disponibili con nome e rating in stelle
	 * Il rating viene visualizzato con caratteri stella (★) ripetuti
	 * Permette all'utente di scegliere tramite numero (1-based)
	 * Imposta la guida selezionata nell'oggetto trip
	 */
	private void askTourGuide(WeekendTrip trip)
	{
		Console.print("\n=== GUIDA TURISTICA ===");
		TourGuide[]		guides;
		String			stars = "";
		int				choice; 
		
		guides = TourGuide.values();
		for (int i = 0; i < guides.length; i++) {
	        stars = "★".repeat(guides[i].getRating());
	        Console.print((i + 1) + ". " + guides[i].getName() + " - " + stars);
	    }
		choice = Console.readIntBetween("Scegli (1-" + guides.length + "): ", "Scelta non valida!", 1, guides.length);
		trip.setTourGuide(guides[choice - 1]);
	}

	/**
	 * Step 5: Inserimento data con validazione
	 * Chiede una data nel formato YYYY-MM-DD (es. 2026-06-15)
	 * Gestisce le eccezioni di formato non valido (DateTimeParseException)
	 * Verifica che la data sia nel futuro (dopo oggi)
	 * Continua a chiedere fino a quando non viene inserita una data valida
	 * Imposta la data validata nell'oggetto trip
	 */
	private void askDate(WeekendTrip trip)
	{
		Console.print("\n=== DATA DEL VIAGGIO ===");
		LocalDate 		date = null;

		while (date == null) {
			try {
				Console.print("Inserisci la data (formato: YYYY-MM-DD, es. 2026-06-15): ");
				String dateStr = Console.readString();
				date = LocalDate.parse(dateStr);
				if (!date.isAfter(LocalDate.now())) {
					Console.print("La data deve essere nel futuro!");
					date = null;
				}
			}
			catch (DateTimeParseException e) {
				Console.print("Formato data non valido! Usa il formato YYYY-MM-DD\n");
			}
		}
		trip.setDate(date);
	}

	/**
	 * Step finale: Validazione e salvataggio
	 * Esegue la validazione completa del trip chiamando il metodo validate()
	 * Se la validazione ha successo (nessun errore):
	 *   - Aggiunge il trip al repository
	 *   - Incrementa il contatore degli ID
	 *   - Mostra messaggio di successo con ID e costo totale
	 * Se la validazione fallisce:
	 *   - Mostra tutti gli errori trovati
	 *   - Non salva il trip
	 */
	private void validateAndSave(WeekendTrip trip)
	{
		String		errors;
		
		errors = trip.validate();
		if (errors.isEmpty())
		{
			repository.addTrip(trip);
			repository.incrementNextId();
			Console.print("\n✓ Trip creato e salvato con successo! ID: " + trip.getId());
			Console.print("Costo totale: " + trip.getFormattedTotalPrice() + " €\n");
		}
		else
		{
			Console.print("\n✗ ERRORI DI VALIDAZIONE:");
			Console.print(errors);
			Console.print("Trip NON salvato.\n");
		}
	}
}
