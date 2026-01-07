package com.generation.vr.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.generation.util.FileReader;
import com.generation.util.FileWriter;
import com.generation.vr.model.dto.StatisticsData;
import com.generation.vr.model.entities.Attraction;
import com.generation.vr.model.entities.Hotel;
import com.generation.vr.model.entities.TourGuide;
import com.generation.vr.model.entities.WeekendTrip;

/**
 * Repository Pattern: gestisce la persistenza e le operazioni CRUD sui WeekendTrip.
 * Astrae il modo in cui i dati vengono salvati (file, database, API).
 * 
 * Responsabilità:
 * - Operazioni CRUD (Create, Read, Update, Delete) sui trip
 * - Gestione ID progressivi
 * - Persistenza su file (salvataggio/caricamento)
 * - Calcolo statistiche aggregate
 * 
 * Applica il principio di Single Responsibility: questa classe cambia solo se cambia
 * il modo di salvare/caricare i dati.
 */
public class WeekendTripRepository
{
	private ArrayList<WeekendTrip> 	trips;
	private int 					nextId;
	private static final String 	DATA_FILE = "data/trips.txt";

	public WeekendTripRepository() {
		this.trips = new ArrayList<>();
		this.nextId = 1;
	}

	// ========== OPERAZIONI CRUD ==========
	/**
	 * Aggiunge un trip al repository
	 * Il trip deve avere già un ID assegnato
	 */
	public void addTrip(WeekendTrip trip) {
		trips.add(trip);
	}

	/**
	 * Restituisce tutti i trip presenti nel repository
	 * Ritorna una copia difensiva per mantenere l'incapsulamento
	 * Questo previene che codice esterno possa modificare la lista interna
	 */
	public ArrayList<WeekendTrip> getAllTrips() {
		return new ArrayList<>(trips);
	}

	/**
	 * L'enhanced for loop (anche chiamato "for-each") è una sintassi semplificata introdotta 
	 * in Java 5 per iterare sugli elementi di collezioni e array.
	 * Cerca un trip specifico tramite ID
	 * Restituisce il trip se trovato, null altrimenti
	 */
	public WeekendTrip getTripById(int id)
	{
		//Per ogni WeekendTrip chiamato trip dentro trips
		for (WeekendTrip trip : trips)
		{
			if (trip.getId() == id)
				return trip;
		}
		return null;
	}

	/**
	 * Verifica se il repository contiene almeno un trip
	 */
	public boolean isEmpty() {
		return trips.isEmpty();
	}

	/**
	 * Restituisce il numero totale di trip nel repository
	 */ 
	public int getTripsCount() {
		return trips.size();
	}

	/**
	 * Restituisce il prossimo ID disponibile per un nuovo trip
	 */ 
	public int getNextId() {
		return nextId;
	}

	/**
	 * Incrementa il contatore degli ID dopo aver creato un nuovo trip
	 */
	public void incrementNextId() {
		nextId++;
	}

	//PERSISTENZA
	/**
	 * Salva tutti i trip su file in formato pipe-delimited
	 * Formato: id|name|surname|date|saturdayVisit|sundayVisit|hotel|tourGuidE
	 * Esempio: 1|Mario|Rossi|2026-06-15|COLOSSEO|FONTANA_DI_TREVI|GRAND_HOTEL|MARCO_BIANCHI
	 * 
	 * Restituisce true se il salvataggio ha successo, false altrimenti
	 * Restituisce false anche se non ci sono trip da salvare
	 */
	public boolean saveTrips()
	{
		if (trips.isEmpty())
			return false;
		try
		{
			FileWriter		fwrite = new FileWriter(DATA_FILE);
			for (WeekendTrip trip : trips)
			{
				String line = trip.getId() + "|" +
							  trip.getName() + "|" +
							  trip.getSurname() + "|" +
							  trip.getDate() + "|" +
							  trip.getSaturdayVisit().name() + "|" +
							  trip.getSundayVisit().name() + "|" +
							  trip.getAffiliatedHotel().name() + "|" +
							  trip.getTourGuide().name() + "\n";

				fwrite.print(line);
			}
			fwrite.close();
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	/**
	 * Carica i trip dal file di persistenza
	 * Ricostruisce gli oggetti WeekendTrip dal formato pipe-delimited
	 * Aggiorna nextId in base all'ID massimo trovato
	 * 
	 * @return numero di trip caricati, o -1 se si verifica un errore
	 */
	public int loadTrips()
	{
		try
		{
			int 		maxId = 0;
			int 		count = 0;
			FileReader 	fr = new FileReader(DATA_FILE);
			trips.clear();

			while (fr.hasNext())
			{
				WeekendTrip trip;		
				String 		line = fr.readString();
				String[] 	parts = line.split("\\|");

				if (parts.length == 8)
				{
					int			id = Integer.parseInt(parts[0]);
					String 		name = parts[1];
					String 		surname = parts[2];
					LocalDate 	date = LocalDate.parse(parts[3]);
					Attraction 	saturday = Attraction.valueOf(parts[4]);
					Attraction 	sunday = Attraction.valueOf(parts[5]);
					Hotel 		hotel = Hotel.valueOf(parts[6]);
					TourGuide 	guide = TourGuide.valueOf(parts[7]);

					trip = new WeekendTrip(name, surname, date, saturday, sunday, hotel, guide);
					trip.setId(id);
					trips.add(trip);
					if (id > maxId)
						maxId = id;
					count++;
				}
			}
			fr.close();
			nextId = maxId + 1;
			return count;
		}
		catch (Exception e)
		{
			return -1;
		}
	}
	/**
	 * Calcola le statistiche aggregate su tutti i trip
	 *
	 * Raccoglie: numero totale trip, ricavo totale, conteggi per hotel/attrazioni/guide
	 * 
	 * === ALGORITMO DI CONTEGGIO CON HASHMAP ===
	 * Per ogni trip, il metodo usa il pattern getOrDefault() per incrementare i contatori:
	 * 
	 * Esempio con hotelCounts:
	 * 1. Estrae il nome dell'hotel dal trip corrente
	 * 2. getOrDefault(hotelName, 0) → cerca nella HashMap:
	 *    - Se l'hotel esiste già: ritorna il conteggio attuale
	 *    - Se l'hotel NON esiste: ritorna 0 (valore di default)
	 * 3. Incrementa il valore di 1
	 * 4. put(hotelName, nuovoValore) → salva/aggiorna nella HashMap
	 * 
	 * Flusso pratico:
	 * Trip 1 (GRAND_HOTEL): getOrDefault("GRAND_HOTEL", 0) → 0, put("GRAND_HOTEL", 1)
	 * Trip 2 (GRAND_HOTEL): getOrDefault("GRAND_HOTEL", 0) → 1, put("GRAND_HOTEL", 2)
	 * Trip 3 (HOTEL_ROMA):  getOrDefault("HOTEL_ROMA", 0) → 0, put("HOTEL_ROMA", 1)
	 * 
	 * Risultato: hotelCounts = {"GRAND_HOTEL": 2, "HOTEL_ROMA": 1}
	 * 
	 * Lo stesso pattern si applica a:
	 * - saturdayAttractionCounts: conta le visite del sabato per ogni attrazione
	 * - sundayAttractionCounts: conta le visite della domenica per ogni attrazione
	 * - guideCounts: conta le richieste per ogni guida turistica
	 * 
	 * Vantaggi di questo approccio:
	 * - Non serve conoscere in anticipo quali hotel/attrazioni/guide esistono
	 * - Aggiornamento automatico dei contatori
	 * - Complessità O(1) per ogni inserimento (molto efficiente)
	 *
	 * @return oggetto StatisticsData contenente tutte le statistiche calcolate
	 */
	public StatisticsData getStatistics()
	{
		StatisticsData	stats;
		String 			hotelName;
		String 			saturdayAttr;
		String 			sundayAttr;
		String 			guideName;

		stats = new StatisticsData();
		for (WeekendTrip trip : trips)
		{
			hotelName = trip.getAffiliatedHotel().name();
			stats.hotelCounts.put(hotelName, stats.hotelCounts.getOrDefault(hotelName, 0) + 1);

			saturdayAttr = trip.getSaturdayVisit().name();
			stats.saturdayAttractionCounts.put(saturdayAttr, stats.saturdayAttractionCounts.getOrDefault(saturdayAttr, 0) + 1);

			sundayAttr = trip.getSundayVisit().name();
			stats.sundayAttractionCounts.put(sundayAttr, stats.sundayAttractionCounts.getOrDefault(sundayAttr, 0) + 1);

			guideName = trip.getTourGuide().name();
			stats.guideCounts.put(guideName, stats.guideCounts.getOrDefault(guideName, 0) + 1);

			stats.totalRevenue = stats.totalRevenue.add(trip.getTotalPrice());
			stats.totalTrips++;
		}
		return stats;
	}
}