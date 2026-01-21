package com.generation.gbb.view;

import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.model.entities.Room;
import com.generation.gbb.model.entities.Expense;
import com.generation.gbb.model.entities.Trip;

/**
 * Factory unica per creare tutte le view del progetto.
 * Invece di avere 4 factory separate (GuestViewFactory, RoomViewFactory, ecc.)
 * ne ho una sola che gestisce tutti i tipi di entità [web:1][web:3].
 * Supporta rendering in formato TXT e HTML.
 */
public class ViewFactory
{
	// ===== GUEST VIEWS =====
	
	/**
	 * View per mostrare un Guest in formato "summary" (sintesi) TXT.
	 * Questa variabile è:
	 * - static: condivisa da tutte le istanze, esiste una sola volta in memoria
	 * - final: non può essere riassegnata dopo l'inizializzazione
	 * 
	 * La inizializzo subito quando la classe viene caricata [web:3].
	 */
	private static final EntityViewBasic<Guest> 		guestSummaryTxtView = new EntityViewBasic<>(
		// PRIMO PARAMETRO: il path del template da caricare
		"template/txt/guestsummary.txt",
		
		// SECONDO PARAMETRO: una lambda che è una BiFunction<String, Guest, String>
		// Questa lambda dice "come trasformare un Guest in una stringa" [web:6][web:9]
		(content, g) -> content  // "content" = template caricato, "g" = oggetto Guest
			// Prendo il template e sostituisco tutti i placeholder con i dati reali
			.replace("[id]",        g.getId() + "")           // Sostituisco [id] con l'ID del guest
			.replace("[firstname]", g.getFirstName())         // Sostituisco [firstname] con il nome
			.replace("[lastname]",  g.getLastName())          // E così via...
			.replace("[ssn]",       g.getSsn())
			.replace("[dob]",       g.getDob() + "")
			.replace("[city]",      g.getCity())
			.replace("[address]",   g.getAddress())
		// La lambda restituisce automaticamente la stringa risultante dai .replace()
	);
	
	/**
	 * View per mostrare un Guest in formato "full" (completo) TXT.
	 * Stessa logica di sopra, ma con un template diverso.
	 * La lambda è identica perché i campi da sostituire sono gli stessi.
	 */
	private static final EntityViewBasic<Guest> 		guestFullTxtView = new EntityViewBasic<>(
		"template/txt/fullguestview.txt",  // Template diverso (più dettagliato)
		// Lambda identica a quella di summary
		(content, g) -> content
			.replace("[id]",        g.getId() + "")
			.replace("[firstname]", g.getFirstName())
			.replace("[lastname]",  g.getLastName())
			.replace("[ssn]",       g.getSsn())
			.replace("[dob]",       g.getDob() + "")
			.replace("[city]",      g.getCity())
			.replace("[address]",   g.getAddress())
	);
	
	/**
	 * View per mostrare un Guest in formato "summary" HTML.
	 * Stesso comportamento ma genera output HTML invece di TXT.
	 */
	private static final EntityViewBasic<Guest> 		guestSummaryHtmlView = new EntityViewBasic<>(
		"template/html/guestsummary.html",
		(content, g) -> content
			.replace("[id]",        g.getId() + "")
			.replace("[firstname]", g.getFirstName())
			.replace("[lastname]",  g.getLastName())
			.replace("[ssn]",       g.getSsn())
			.replace("[dob]",       g.getDob() + "")
			.replace("[city]",      g.getCity())
			.replace("[address]",   g.getAddress())
	);
	
	/**
	 * View per mostrare un Guest in formato "full" HTML.
	 */
	private static final EntityViewBasic<Guest> 		guestFullHtmlView = new EntityViewBasic<>(
		"template/html/fullguestview.html",
		(content, g) -> content
			.replace("[id]",        g.getId() + "")
			.replace("[firstname]", g.getFirstName())
			.replace("[lastname]",  g.getLastName())
			.replace("[ssn]",       g.getSsn())
			.replace("[dob]",       g.getDob() + "")
			.replace("[city]",      g.getCity())
			.replace("[address]",   g.getAddress())
	);
	
	// ===== ROOM VIEWS =====
	
	/**
	 * View summary per le stanze in formato TXT.
	 * Stessa struttura: template + lambda che dice come renderizzare una Room.
	 */
	private static final EntityViewBasic<Room> 		roomSummaryTxtView = new EntityViewBasic<>(
		"template/txt/roomsummary.txt",
		// Questa volta la lambda prende (content, r) dove "r" è un oggetto Room
		(content, r) -> content
			.replace("[id]",          r.getId() + "")
			.replace("[name]",        r.getName())
			.replace("[description]", r.getDescription())
			.replace("[size]",        r.getSize() + "")
			.replace("[floor]",       r.getFloor() + "")
			.replace("[price]",       r.getPrice() + "")
	);
	
	/**
	 * View full per le stanze in formato TXT.
	 */
	private static final EntityViewBasic<Room> 		roomFullTxtView = new EntityViewBasic<>(
		"template/txt/fullroomview.txt",
		(content, r) -> content
			.replace("[id]",          r.getId() + "")
			.replace("[name]",        r.getName())
			.replace("[description]", r.getDescription())
			.replace("[size]",        r.getSize() + "")
			.replace("[floor]",       r.getFloor() + "")
			.replace("[price]",       r.getPrice() + "")
	);
	
	/**
	 * View summary per le stanze in formato HTML.
	 */
	private static final EntityViewBasic<Room> 			roomSummaryHtmlView = new EntityViewBasic<>(
		"template/html/roomsummary.html",
		(content, r) -> content
			.replace("[id]",          r.getId() + "")
			.replace("[name]",        r.getName())
			.replace("[description]", r.getDescription())
			.replace("[size]",        r.getSize() + "")
			.replace("[floor]",       r.getFloor() + "")
			.replace("[price]",       r.getPrice() + "")
	);
	
	/**
	 * View full per le stanze in formato HTML.
	 */
	private static final EntityViewBasic<Room> 				roomFullHtmlView = new EntityViewBasic<>(
		"template/html/fullroomview.html",
		(content, r) -> content
			.replace("[id]",          r.getId() + "")
			.replace("[name]",        r.getName())
			.replace("[description]", r.getDescription())
			.replace("[size]",        r.getSize() + "")
			.replace("[floor]",       r.getFloor() + "")
			.replace("[price]",       r.getPrice() + "")
	);
	
	// ===== EXPENSE VIEWS =====
	
	/**
	 * View per le spese in formato summary TXT.
	 * Lambda con (content, e) dove "e" è un oggetto Expense.
	 */
	private static final EntityViewBasic<Expense> 		expenseSummaryTxtView = new EntityViewBasic<>(
		"template/txt/expensesummary.txt",
		(content, e) -> content
			.replace("[id]",          e.getId() + "")
			.replace("[date]",        e.getDate() + "")        // La data va convertita in stringa
			.replace("[description]", e.getDescription())
			.replace("[value]",       e.getValue() + "")
			.replace("[category]",    e.getCategory() + "")
	);
	
	/**
	 * View per le spese in formato full TXT.
	 */
	private static final EntityViewBasic<Expense> 			expenseFullTxtView = new EntityViewBasic<>(
		"template/txt/fullexpenseview.txt",
		(content, e) -> content
			.replace("[id]",          e.getId() + "")
			.replace("[date]",        e.getDate() + "")
			.replace("[description]", e.getDescription())
			.replace("[value]",       e.getValue() + "")
			.replace("[category]",    e.getCategory() + "")
	);
	
	/**
	 * View per le spese in formato summary HTML.
	 */
	private static final EntityViewBasic<Expense> 			expenseSummaryHtmlView = new EntityViewBasic<>(
		"template/html/expensesummary.html",
		(content, e) -> content
			.replace("[id]",          e.getId() + "")
			.replace("[date]",        e.getDate() + "")
			.replace("[description]", e.getDescription())
			.replace("[value]",       e.getValue() + "")
			.replace("[category]",    e.getCategory() + "")
	);
	
	/**
	 * View per le spese in formato full HTML.
	 */
	private static final EntityViewBasic<Expense> 			expenseFullHtmlView = new EntityViewBasic<>(
		"template/html/fullexpenseview.html",
		(content, e) -> content
			.replace("[id]",          e.getId() + "")
			.replace("[date]",        e.getDate() + "")
			.replace("[description]", e.getDescription())
			.replace("[value]",       e.getValue() + "")
			.replace("[category]",    e.getCategory() + "")
	);
	
	// ===== TRIP VIEWS =====
	
	/**
	 * View per i viaggi in formato summary TXT.
	 * Lambda con (content, t) dove "t" è un oggetto Trip.
	 */
	private static final EntityViewBasic<Trip> 			tripSummaryTxtView = new EntityViewBasic<>(
		"template/txt/tripsummary.txt",
		(content, t) -> content
			.replace("[id]",     t.getId() + "")
			.replace("[city]",   t.getCity())
			.replace("[date]",   t.getDate().toString())  // LocalDate -> String
			.replace("[review]", t.getReview())
			.replace("[score]",  t.getScore() + "")
	);
	
	/**
	 * View per i viaggi in formato full TXT.
	 */
	private static final EntityViewBasic<Trip> 			tripFullTxtView = new EntityViewBasic<>(
		"template/txt/fulltripview.txt",
		(content, t) -> content
			.replace("[id]",     t.getId() + "")
			.replace("[city]",   t.getCity())
			.replace("[date]",   t.getDate().toString())
			.replace("[review]", t.getReview())
			.replace("[score]",  t.getScore() + "")
	);
	
	/**
	 * View per i viaggi in formato summary HTML.
	 */
	private static final EntityViewBasic<Trip> 			tripSummaryHtmlView = new EntityViewBasic<>(
		"template/html/tripsummary.html",
		(content, t) -> content
			.replace("[id]",     t.getId() + "")
			.replace("[city]",   t.getCity())
			.replace("[date]",   t.getDate().toString())
			.replace("[review]", t.getReview())
			.replace("[score]",  t.getScore() + "")
	);
	
	/**
	 * View per i viaggi in formato full HTML.
	 */
	private static final EntityViewBasic<Trip> 			tripFullHtmlView = new EntityViewBasic<>(
		"template/html/fulltripview.html",
		(content, t) -> content
			.replace("[id]",     t.getId() + "")
			.replace("[city]",   t.getCity())
			.replace("[date]",   t.getDate().toString())
			.replace("[review]", t.getReview())
			.replace("[score]",  t.getScore() + "")
	);
	
	// ===== METODO UNICO FACTORY =====
	/**
	 * Metodo factory universale per creare view [web:1][web:2].
	 * Questo è il metodo principale che permette di ottenere qualsiasi view
	 * specificando 3 parametri invece di chiamare 4 metodi diversi.
	 * 
	 * @param entity Tipo di entità: "guest", "room", "expense", "trip"
	 * @param type   Livello dettaglio: "full" o "summary"
	 * @param format Formato output: "html" o "txt"
	 * @return La view richiesta
	 * 
	 * COME FUNZIONA:
	 * - Uso equalsIgnoreCase per confrontare le stringhe ignorando maiuscole/minuscole
	 * - Uso uno switch per scegliere l'entità giusta
	 * - Per ogni entità, controllo prima il tipo (full o summary)
	 * - Poi controllo il formato (html o txt)
	 * - Restituisco la view statica già creata sopra
	 * 
	 * @SuppressWarnings("unchecked") serve perché sto facendo un cast generico
	 * che Java non può verificare a compile-time, ma so che è sicuro.
	 */
	@SuppressWarnings("unchecked")
	public static <T> EntityViewBasic<T> makeView(String entity, String type, String format)
	{
		String entityLower = entity.toLowerCase();
		
		switch(entityLower)
		{
			case "guest":
				// Controllo se voglio la versione full o summary
				if (type.equalsIgnoreCase("full"))
				{
					// Ho scelto full, ora controllo il formato
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) guestFullHtmlView;
					else
						return (EntityViewBasic<T>) guestFullTxtView;
				}
				else
				{
					// Ho scelto summary, ora controllo il formato
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) guestSummaryHtmlView;
					else
						return (EntityViewBasic<T>) guestSummaryTxtView;
				}
				
			case "room":
				if (type.equalsIgnoreCase("full"))
				{
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) roomFullHtmlView;
					else
						return (EntityViewBasic<T>) roomFullTxtView;
				}
				else
				{
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) roomSummaryHtmlView;
					else
						return (EntityViewBasic<T>) roomSummaryTxtView;
				}
				
			case "expense":
				if (type.equalsIgnoreCase("full"))
				{
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) expenseFullHtmlView;
					else
						return (EntityViewBasic<T>) expenseFullTxtView;
				}
				else
				{
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) expenseSummaryHtmlView;
					else
						return (EntityViewBasic<T>) expenseSummaryTxtView;
				}
				
			case "trip":
				if (type.equalsIgnoreCase("full"))
				{
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) tripFullHtmlView;
					else
						return (EntityViewBasic<T>) tripFullTxtView;
				}
				else
				{
					if (format.equalsIgnoreCase("html"))
						return (EntityViewBasic<T>) tripSummaryHtmlView;
					else
						return (EntityViewBasic<T>) tripSummaryTxtView;
				}
				
			default:
				throw new IllegalArgumentException("Tipo entità non valido: " + entity);
		}
	}


	
	/**
	 * Metodo factory con formato default TXT.
	 * Questo è un overload che chiama il metodo principale con "txt" come default.
	 * Utile quando non serve specificare il formato ogni volta [web:3].
	 * 
	 * @param entity Tipo di entità
	 * @param type   Livello dettaglio
	 * @return La view richiesta in formato TXT
	 */
	public static <T> EntityViewBasic<T> makeView(String entity, String type)
	{
		return makeView(entity, type, "txt");
	}
}