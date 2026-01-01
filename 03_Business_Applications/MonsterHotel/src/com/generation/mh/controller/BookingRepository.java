package com.generation.mh.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.mh.model.dto.StatisticsData;
import com.generation.mh.model.entities.Booking;
import com.generation.mh.model.entities.RoomType;
import com.generation.mh.model.entities.Species;
import com.generation.util.FileReader;
import com.generation.util.FileWriter;

/**
 * Repository per la gestione della persistenza e accesso ai dati delle prenotazioni.
 * Gestisce il salvataggio/caricamento su file e le operazioni CRUD in memoria.
 */
public class BookingRepository
{
	private List<Booking> bookings;
	private int nextId;
	private static final String SAVE_FILE = "data/bookings.txt";

	/**
	 * Crea un nuovo repository vuoto per le prenotazioni.
	 * Inizializza la lista delle prenotazioni e imposta il contatore ID a 1.
	 *
	 * COSA FA (dal punto di vista esterno):
	 * - Prepara un repository pronto all'uso per gestire prenotazioni
	 * - Garantisce che la prima prenotazione avrà ID = 1
	 */
	public BookingRepository()
	{
		this.bookings = new ArrayList<>();
		this.nextId = 1;
	}

	/**
	 * Aggiunge una nuova prenotazione al repository in memoria.
	 *
	 * COSA FA:
	 * - Riceve una prenotazione già creata e validata
	 * - La memorizza nel repository per poterla recuperare successivamente
	 * - NON salva su file (per quello usa saveBookings())
	 *
	 * @param booking La prenotazione da aggiungere (deve essere già validata)
	 */
	public void addBooking(Booking booking)
	{
		bookings.add(booking);
	}

	/**
	 * Restituisce tutte le prenotazioni presenti nel repository.
	 *
	 * COSA FA :
	 * - Fornisce accesso a tutte le prenotazioni memorizzate
	 * - Restituisce una COPIA della lista (modifiche alla lista ritornata non affettano il repository)
	 * - Se non ci sono prenotazioni, ritorna lista vuota (mai null)
	 *
	 * @return Lista contenente tutte le prenotazioni (copia difensiva)
	 */
	public List<Booking> getAllBookings()
	{
		return new ArrayList<>(bookings); // Ritorna copia per sicurezza
	}

	/**
	 * Cerca e restituisce una prenotazione specifica tramite il suo ID.
	 *
	 * COSA FA  :
	 * - Riceve un numero ID da cercare
	 * - Scorre tutte le prenotazioni finché non trova quella con l'ID corrispondente
	 * - Se trova la prenotazione, la restituisce
	 * - Se NON trova nulla, ritorna null
	 *
	 * @param id Identificativo univoco della prenotazione da cercare
	 * @return La prenotazione trovata, oppure null se non esiste
	 */
	public Booking getBookingById(int id)
	{
		for (Booking b : bookings)
		{
			if (b.getId() == id)
			{
				return b;
			}
		}
		return null;
	}

	/**
	 * Verifica se il repository è vuoto (senza prenotazioni).
	 *
	 * COSA FA  :
	 * - Controlla se ci sono prenotazioni memorizzate
	 * - Utile prima di fare operazioni che richiedono dati (es. statistiche, salvataggio)
	 *
	 * @return true se non ci sono prenotazioni, false se ce ne sono almeno una
	 */
	public boolean isEmpty()
	{
		return bookings.isEmpty();
	}

	/**
	 * Conta quante prenotazioni sono attualmente memorizzate.
	 *
	 * COSA FA  :
	 * - Restituisce il numero totale di prenotazioni presenti
	 * - Utile per mostrare statistiche o conteggi all'utente
	 *
	 * @return Numero intero di prenotazioni (0 se vuoto)
	 */
	public int getBookingsCount()
	{
		return bookings.size();
	}

	/**
	 * Fornisce il prossimo ID che sarà assegnato alla nuova prenotazione.
	 *
	 * COSA FA :
	 * - Restituisce l'ID che dovrebbe essere usato per la prossima prenotazione
	 * - Garantisce che ogni prenotazione abbia un ID univoco
	 * - NON incrementa il contatore (solo consultazione)
	 *
	 * @return Il prossimo ID disponibile
	 */
	public int getNextId() { return nextId; }

	/**
	 * Incrementa il contatore degli ID per la prossima prenotazione.
	 *
	 * COSA FA:
	 * - Da chiamare DOPO aver creato con successo una prenotazione
	 * - Prepara il sistema per assegnare un nuovo ID univoco alla prossima prenotazione
	 * - Se non viene chiamato, due prenotazioni potrebbero avere lo stesso ID!
	 */
	public void incrementNextId() { nextId++;}

	/**
	 * // =========== PERSISTENZA ===================
	 * Salva permanentemente tutte le prenotazioni in memoria su file di testo.
	 *
	 * COSA FA:
	 * - Prende TUTTE le prenotazioni attualmente in memoria
	 * - Le scrive nel file "data/bookings.txt" in formato testo delimitato da pipe (|)
	 * - Restituisce true se il salvataggio ha successo, false altrimenti
	 * - Se la lista è vuota, restituisce false
	 *
	 * QUANDO USARLO:
	 * - Prima di chiudere l'applicazione
	 * - Quando l'utente sceglie "Salva" dal menu
	 * - Per preservare i dati tra diverse esecuzioni del programma
	 *
	 * @return true se salvato con successo, false se lista vuota o errore
	 */
	public boolean saveBookings()
	{
		if (bookings.isEmpty())
		{
			return false;
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
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Carica le prenotazioni salvate dal file di testo nella memoria.
	 *
	 * COSA FA:
	 * - Legge il file "data/bookings.txt"
	 * - CANCELLA tutte le prenotazioni attualmente in memoria (svuota la lista)
	 * - Ricostruisce ogni prenotazione dai dati del file
	 * - Aggiorna il contatore nextId basandosi sull'ID più alto trovato
	 * - Restituisce il numero di prenotazioni caricate, oppure -1 in caso di errore
	 *
	 * QUANDO USARLO:
	 * - All'avvio dell'applicazione per recuperare dati precedenti
	 * - Quando l'utente sceglie "Carica" dal menu
	 * - Per ripristinare i dati salvati in precedenza
	 *
	 * ATTENZIONE:
	 * - Sovrascrive COMPLETAMENTE i dati in memoria con quelli del file
	 * - Se hai prenotazioni non salvate, le perderai!
	 *
	 * @return Numero di prenotazioni caricate, oppure -1 se errore
	 */
	public int loadBookings()
	{
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
			return bookings.size();
		}
		catch (Exception e)
		{
			return -1;
		}
	}

	/**
	 * // =========== STATISTICHE ===================
	 * Calcola e restituisce le statistiche di tutte le prenotazioni.
	 *
	 * COSA FA:
	 * - Scorre TUTTE le prenotazioni in memoria
	 * - Calcola e conta varie metriche:
	 *   • Totale prenotazioni
	 *   • Distribuzione per specie (vampiri, licantropi, sirene, umani)
	 *   • Distribuzione per tipo stanza (singola, doppia, suite)
	 *   • Ricavi totali
	 *   • Notti totali prenotate
	 *   • Numero servizi navetta richiesti
	 * - Restituisce un oggetto StatisticsData con tutti i dati
	 * - Se non ci sono prenotazioni, restituisce null
	 *
	 * QUANDO USARLO:
	 * - Quando l'utente sceglie "Statistiche" dal menu
	 * - Per avere dati grezzi da elaborare/visualizzare
	 * - Per report e analisi dati
	 *
	 * @return Oggetto StatisticsData con tutte le metriche, oppure null se lista vuota
	 */
	public StatisticsData getStatistics()
	{
		if (bookings.isEmpty()){ return null;}
		StatisticsData stats = new StatisticsData();
		stats.totalBookings = bookings.size();
		for (Booking b : bookings)
		{
			switch (b.getSpecies())
			{
				case VAMPIRE:
					stats.vampireCount++;
					break;
				case WEREWOLF:
					stats.werewolfCount++;
					break;
				case MERMAID:
					stats.mermaidCount++;
					break;
				case HOMO_SAPIENS:
					stats.humanCount++;
					break;
			}
			switch (b.getRoomType())
			{
				case SINGOLA:
					stats.singolaCount++;
					break;
				case DOPPIA:
					stats.doppiaCount++;
					break;
				case SUITE:
					stats.suiteCount++;
					break;
			}
			stats.totalRevenue += b.calculateTotalCost();
			stats.totalNights += b.getNumberOfNights();
			if (b.isTransportService())
				stats.transportCount++;
		}
		return stats;
	}
}
