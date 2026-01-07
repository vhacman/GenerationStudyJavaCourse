package com.generation.vr.view;

import java.util.ArrayList;
import java.util.Map;
import com.generation.util.Console;
import com.generation.util.FileWriter;
import com.generation.util.Template;
import com.generation.vr.model.dto.StatisticsData;
import com.generation.vr.model.entities.WeekendTrip;

/**
 * View Layer: gestisce tutta la visualizzazione (niente business logic)
 * Principio MVC: questa classe sa solo MOSTRARE dati, non calcolarli
 */
public class WeekendTripView
{
	private String	 filename;

	public WeekendTripView(String filename){ this.filename = filename; }


	public String		render(WeekendTrip writer) {
		return Template.load(filename)
						.replace("[name]", writer.getName())
						.replace("[surname]", writer.getSurname())
						.replace("[date]", writer.getDate().toString())
						.replace("[saturday]", writer.getSaturdayVisit().toString())
						.replace("[sunday]", writer.getSundayVisit().toString())
						.replace("[hotel]", writer.getAffiliatedHotel().toString())
						.replace("[tourguide]", writer.getTourGuide() != null ? writer.getTourGuide().getName() : "N/A")
						.replace("[rating]", getStars(writer.getTourGuide().getRating()))
						.replace("[totalprice]", writer.getFormattedTotalPrice() + " €");
	}

	public static void showMenu() { 
		String menu = Template.load("template/menu.txt");
		Console.print(menu != null && !menu.isEmpty() ? menu : "Menu non trovato");
	}

	/**
	 * Visualizza l'elenco completo di tutti i trip
	 * Mostra il conteggio totale e poi itera per visualizzare ogni singolo trip
	 * 
	 * @param trips lista di WeekendTrip da visualizzare
	 */
	public static void showAllTrips(ArrayList<WeekendTrip> trips) {
		Console.print("\n=== ELENCO TRIPS (" + trips.size() + " totali) ===\n");
		for (WeekendTrip trip : trips)
			showTrip(trip);
	}
	
	/**
	 * Visualizza i dettagli completi di un singolo trip
	 * Mostra: ID, cliente, data, attrazioni, hotel, guida e costo totale
	 * Formattato con separatori grafici per leggibilità
	 * 
	 * @param trip il WeekendTrip da visualizzare
	 */
	public static void showTrip(WeekendTrip trip) {
		Console.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		Console.print("Trip ID: " + trip.getId());
		Console.print("Cliente: " + trip.getName() + " " + trip.getSurname());
		Console.print("Data viaggio: " + trip.getDate());
		Console.print("Attrazione Sabato: " + trip.getSaturdayVisit().getName());
		Console.print("Attrazione Domenica: " + trip.getSundayVisit().getName());
		Console.print("Hotel: " + trip.getAffiliatedHotel().getName());
		Console.print("Guida: " + trip.getTourGuide().getName() + " " + getStars(trip.getTourGuide().getRating()));
		Console.print("COSTO TOTALE: " + trip.getFormattedTotalPrice() + " €");
		Console.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
	}

	/**
	 * Visualizza il risultato dell'operazione di salvataggio su file
	 * Mostra un messaggio di successo con il numero di trip salvati,
	 * oppure un messaggio di errore se il salvataggio è fallito
	 * 
	 * @param success true se il salvataggio è riuscito, false altrimenti
	 * @param count numero di trip salvati
	 */
	public static void showSaveResult(boolean success, int count)
	{
		if (success)
			Console.print("\n " + count + " trip salvati con successo!\n");
		else
			Console.print("\n Errore: nessun trip da salvare.\n");
	}

	/**
	 * Visualizza il risultato dell'operazione di caricamento da file
	 * Gestisce tre casi: successo (count > 0), file vuoto (count = 0), errore (count < 0)
	 * 
	 * @param count numero di trip caricati, o -1 in caso di errore
	 */
	public static void showLoadResult(int count)
	{
		if (count > 0)
			Console.print("\n " + count + " trip caricati!\n");
		else if (count == 0)
			Console.print("\n Nessun trip trovato nel file.\n");
		else
			Console.print("\n Errore durante il caricamento.\n");
	}

	/**
	 * Visualizza le statistiche aggregate di tutti i trip
	 * Mostra: totale trips, ricavi (totale e medio), conteggi per hotel/attrazioni/guide
	 *
	 * === MAP.ENTRY E ITERAZIONE SULLE HASHMAP  - approfondimento teoria ===
	 * 
	 * Cos'è Map.Entry:
	 * Map.Entry è un'interfaccia che rappresenta una singola coppia chiave-valore dentro una HashMap.
	 * Ogni HashMap è composta da tante Entry (coppie chiave-valore).
	 * 
	 * Struttura interna della HashMap:
	 * hotelCounts = {
	 *     "GRAND_HOTEL" -> 5,      // Questa è una Entry
	 *     "HOTEL_ROMA" -> 3,       // Questa è un'altra Entry
	 *     "COLOSSEUM_INN" -> 2      
	 * }
	 * 
	 * Come funziona l'iterazione:
	 * 1. entrySet() converte la HashMap in un Set di Entry (insieme di coppie chiave-valore)
	 * 2. Il for-each itera su ogni Entry del Set
	 * 3. Per ogni Entry, possiamo accedere a:
	 *    - entry.getKey() → la chiave (String, es. "GRAND_HOTEL")
	 *    - entry.getValue() → il valore (Integer, es. 5)
	 * 
	 * Esempio pratico  
	 * 
	 * Iterazione 1:
	 *   entry.getKey() = "GRAND_HOTEL"
	 *   entry.getValue() = 5
	 *   Stampa: "GRAND_HOTEL: 5 trips"
	 * 
	 * Iterazione 2:
	 *   entry.getKey() = "HOTEL_ROMA"
	 *   entry.getValue() = 3
	 *   Stampa: "HOTEL_ROMA: 3 trips"
	 * 
	 * Iterazione 3:
	 *   entry.getKey() = "COLOSSEUM_INN"
	 *   entry.getValue() = 2
	 *   Stampa: "COLOSSEUM_INN: 2 trips"
	 * 
	 * 
	 * Perché  Map.Entry con entrySet():
	 * - Efficienza: accesso diretto a chiave E valore in un solo passaggio
	 * - Best practice: è il modo standard e raccomandato per iterare su HashMap
	 *
	 * @param stats oggetto StatisticsData contenente tutti i dati aggregati
	 */
	public static void showStatistics(StatisticsData stats)
	{
		Console.print("\n╔════════════════════════════════════════╗");
		Console.print("║         STATISTICHE TRIPS              ║");
		Console.print("╚════════════════════════════════════════╝\n");

		Console.print("TOTALE TRIPS: " + stats.totalTrips);
		Console.print("RICAVI TOTALI: " + stats.totalRevenue.setScale(2, java.math.RoundingMode.HALF_UP) + " €");
		Console.print("RICAVO MEDIO: " + stats.getAverageRevenue() + " €\n");

		Console.print("--- HOTEL ---");
		for (Map.Entry<String, Integer> entry : stats.hotelCounts.entrySet())
			Console.print(entry.getKey() + ": " + entry.getValue() + " trips");

		Console.print("\n--- ATTRAZIONI SABATO ---");
		for (Map.Entry<String, Integer> entry : stats.saturdayAttractionCounts.entrySet())
			Console.print(entry.getKey() + ": " + entry.getValue() + " visite");

		Console.print("\n--- ATTRAZIONI DOMENICA ---");
		for (Map.Entry<String, Integer> entry : stats.sundayAttractionCounts.entrySet())
			Console.print(entry.getKey() + ": " + entry.getValue() + " visite");

		Console.print("\n--- GUIDE TURISTICHE ---");
		for (Map.Entry<String, Integer> entry : stats.guideCounts.entrySet())
			Console.print(entry.getKey() + ": " + entry.getValue() + " trips");

		Console.print("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
	}

	/**
	 * Genera file HTML di una prenotazione
	 *
	 * @param booking La prenotazione da convertire in HTML
	 */
	// Genera file HTML del trip
	public static void printHTML(WeekendTrip trip) {
		String errors = trip.validate();
		if (!errors.isEmpty()) {
			Console.print("Errore: Trip non valido.\n" + errors);
			return;
		}
		try {
			String filename = "print/trip_" + trip.getId() + ".html";
			FileWriter writer = new FileWriter(filename);
			writer.print(trip.generateHTML());
			writer.close();
			Console.print("File HTML generato: " + filename);
		} catch (Exception e) {
			Console.print("Errore generazione HTML: " + e.getMessage());
		}
	}

	/**
	 * Helper per generare una stringa di stelle (★) in base al rating
	 *
	 * Funzionamento:
	 * 1. Crea un StringBuilder vuoto (contenitore mutabile per costruire la stringa)
	 * 2. Loop da 0 a rating-1 (es. rating=3 → loop 3 volte)
	 * 3. Ogni iterazione aggiunge una stella (★) al StringBuilder
	 * 4. Converte il StringBuilder in String e la restituisce
	 *
	 * @param rating numero di stelle da generare (tipicamente 1-5)
	 * @return stringa contenente il numero corrispondente di stelle
	 */
	private static String getStars(int rating)
	{
		StringBuilder stars = new StringBuilder();
		for (int i = 0; i < rating; i++)
			stars.append("★");
		return stars.toString();
	}
}