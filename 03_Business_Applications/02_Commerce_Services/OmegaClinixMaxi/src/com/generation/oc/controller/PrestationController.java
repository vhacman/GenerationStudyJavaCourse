package com.generation.oc.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.generation.library.Console;
import com.generation.oc.context.Context;
import com.generation.oc.model.entities.HealthService;
import com.generation.oc.model.entities.Patient;
import com.generation.oc.model.entities.Prestation;
import com.generation.oc.model.repository.HealthServiceRepository;
import com.generation.oc.model.repository.PatientRepository;
import com.generation.oc.model.repository.PrestationRepository;
import com.generation.oc.view.Views;

/**
 * Controller per la gestione delle prestazioni
 * TODO completare metodi mancanti (2, 4, 6, 8, 10, 12)
 * TODO implementare cancellazione prestazione per id (no passato, chiedere conferma)
 * TODO implementare stampa HTML prestazione (con paziente e servizio)
 * TODO implementare stampa HTML prestazioni di oggi (con logo)
 */
public class PrestationController
{
	static HealthServiceRepository	healthServiceRepo	= Context.getDependency(HealthServiceRepository.class);
	static PatientRepository		patientRepo			= Context.getDependency(PatientRepository.class);
	static PrestationRepository		prestationRepo		= Context.getDependency(PrestationRepository.class);

	/**
	 * Comando numero 1. Algoritmo di inserimento di una nuova prestazione
	 * con lettura e selezione del cliente prima.
	 * Verifichiamo anche di avere spazio nella giornata per farlo. A tale scopo
	 * verifichiamo di avere meno di otto prestazioni in quella giornata.
	 * Se ne abbiamo meno di otto la accettiamo, altrimenti no.
	 * Gestione degli errori...
	 */
	public static void insertPrestation()
	{
		try
		{
			Prestation			p = new Prestation();
			Console.print("Inserire data");
			p.setDate(LocalDate.parse(Console.readString()));
			List<Prestation>	prestationsOnThatDate = prestationRepo.findByDate(p.getDate());
			// REFACTORING: corretto bug - se abbiamo GIÀ 8 prestazioni (>=8), rifiutiamo la nona
			if (prestationsOnThatDate.size() >= 8)
			{
				Console.print("Data piena");
				return;
			}
			Patient				patient = PatientController.choosePatient();
			if (patient == null)		return;
			HealthService		service = HealthServiceController.chooseHealthService();
			if (service == null)		return;
			p.setPrice(service.getPrice());
			p.setPatient(patient);
			p.setHealthService(service);
			p = prestationRepo.insert(p);
			Console.print("Salvata con id " + p);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (DateTimeParseException e)
		{
			Console.print("Data non valida");
		}
	}

	/**
	 * Inserisce una prestazione d'emergenza nel sistema.
	 * Il prezzo viene triplicato rispetto al servizio normale.
	 * Non viene effettuato il controllo della capacità giornaliera.
	 *
	 * @throws SQLException se si verifica un errore durante l'inserimento nel database
	 */
	public static void insertEmergencyPrestation()
	{
		try
		{
			Patient				emergencyPatient = PatientController.choosePatient();
			Console.print(emergencyPatient == null ? "Operazione annullata: nessun paziente selezionato" : "");
			if(emergencyPatient == null)	return;
			HealthService		service = HealthServiceController.chooseHealthService();
			Console.print(service == null ? "Operazione annullata: nessun servizio selezionato" : "");
			if(service == null)				return;
			// Creo la prestazione e imposto la data odierna
			Prestation			emergencyPrestation = new Prestation();
			emergencyPrestation.setDate(LocalDate.now());
			emergencyPrestation.setPatient(emergencyPatient);
			emergencyPrestation.setHealthService(service);
			emergencyPrestation.setPrice(service.getPrice() * 3);
			// Salvo nel database senza controllo capacità
			emergencyPrestation = prestationRepo.insert(emergencyPrestation);
			Console.print("Prestazione d'emergenza inserita con successo");
			Console.print("Prezzo applicato: " + emergencyPrestation.getPrice());
		}
		catch (SQLException e)
		{
			Console.print("Errore durante l'inserimento della prestazione");
			e.printStackTrace();
		}
	}

	/**
	 * Algoritmo di filtro e riduzione
	 * Carico le prestazioni di una certa data, le stampo e calcolo la somma del loro prezzo
	 */
	public static void printByDate()
	{
		try
		{
			Console.print("Inserire data di ricerca");
			LocalDate			date	= LocalDate.parse(Console.readString());
			List<Prestation>	matches	= prestationRepo.findByDate(date);
			if (matches.size() == 0)
			{
				Console.print("Nessuna prestazione in questa data");
				return;
			}
			Console.print(Views.PRESTATIONVIEW.render(matches));
			int					sum = 0;
			for (Prestation m : matches)
				sum += m.getPrice();
			Console.print("Eseguite " + matches.size() + " prestazioni per un totale di " + sum + " euro");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (DateTimeParseException e)
		{
			Console.print("Data non valida");
		}
	}

	/**
	 * Filtro e riduzione per le prestazioni odierne.
	 * 1. Filtra tutte le prestazioni di oggi
	 * 2. Le ordina per ID crescente
	 * 3. Calcola il guadagno totale previsto
	 *
	 * @throws SQLException se si verifica un errore durante la lettura dal database
	 */
	public static void printPrestationsForToday()
	{
		try
		{
			// Prendo la data odierna
			LocalDate			today = LocalDate.now();
			// FILTRO: recupero solo le prestazioni di oggi dal database
			List<Prestation>	matches = prestationRepo.findByDate(today);
			// Se non ci sono prestazioni, stampo messaggio e esco
			if (matches.size() == 0)
			{
				Console.print("Nessuna prestazione per oggi");
				return;
			}
			/**
			 * ORDINAMENTO: due modi per ordinare per ID
			 *
			 * METODO 1 - Lambda expression:
			 * matches.sort((p1, p2) -> Integer.compare(p1.getId(), p2.getId()));
			 *
			 * METODO 2 - Method reference (più conciso):
			 * matches.sort(Comparator.comparingInt(Prestation::getId));
			 *
			 * Entrambi ordinano in modo crescente per ID
			 */
			// Ordina la lista matches per ID crescente usando il getter getId() di Prestation
			matches.sort(Comparator.comparingInt(Prestation::getId));
			// Stampo l'elenco delle prestazioni ordinate
			Console.print(Views.PRESTATIONVIEW.render(matches));
			// RIDUZIONE: calcolo la somma totale dei prezzi
			int		sum = 0;
			for (Prestation m : matches)
				sum += m.getPrice();
			// Stampo il riepilogo: numero prestazioni e guadagno totale
			Console.print("Eseguite " + matches.size() + " prestazioni per un totale di " + sum + " euro");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**Filtro e riduzione
	 * Calcolo il totale guadagnato per un periodo da-a
	 * Stampo il guadagno totale e il guadagno giornaliero medio*/
	public static void printIncomeByPeriod()
	{
		try
		{
			Console.print("Inserire data iniziale per il periodo");
			LocalDate			from = LocalDate.parse(Console.readString());
			Console.print("Inserire data finale per il periodo");
			LocalDate			to = LocalDate.parse(Console.readString());
			List<Prestation>	matches = prestationRepo.findByPeriod(from, to);
			if (matches.size() == 0)
			{
				Console.print("Nessuna prestazione nel periodo " + from + " " + to);
				return;
			}
			Console.print(Views.PRESTATIONVIEW.render(matches));
			double				sum = 0;
			for (Prestation p : matches)
				sum += p.getPrice();
			// REFACTORING: corretto bug - aggiungo +1 per includere ENTRAMBE le date estreme
			// Es: tra 2025-03-01 e 2025-03-15 ci sono 15 giorni (inclusi primo e ultimo)
			// ChronoUnit.DAYS.between calcola la differenza, quindi aggiungo 1
			long				days = ChronoUnit.DAYS.between(from, to) + 1;
			Console.print("Guadagno totale " + sum + " euro per " + days + " giorni");
			Console.print("Guadagno medio giornaliero " + (sum / days) + " euro ");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (DateTimeParseException e)
		{
			Console.print("Data non valida");
		}
	}

	/**
	 * Calcola il guadagno totale e medio giornaliero per un anno specificato dall'utente.
	 * 1. Chiede all'utente di inserire un anno
	 * 2. Filtra tutte le prestazioni dell'anno
	 * 3. Calcola il guadagno totale e la media giornaliera
	 *
	 * @throws SQLException se si verifica un errore durante la lettura dal database
	 * @throws NumberFormatException se l'input dell'utente non è un numero valido
	 */
	public static void printIncomeByYear()
	{
		try
		{
			// Chiedo all'utente di inserire l'anno
			Console.print("Inserire anno");
			int year = Integer.parseInt(Console.readString());
			// Creo l'intervallo: dal 1 gennaio al 31 dicembre dell'anno inserito
			LocalDate from = LocalDate.of(year, 1, 1);
			LocalDate to = LocalDate.of(year, 12, 31);
			// FILTRO: recupero tutte le prestazioni nel periodo specificato
			List<Prestation> matches = prestationRepo.findByPeriod(from, to);
			// Se non ci sono prestazioni nell'anno, stampo messaggio e esco
			if (matches.size() == 0)
			{
				Console.print("Nessuna prestazione nell'anno " + year);
				return;
			}
			// Stampo l'elenco delle prestazioni trovate
			Console.print(Views.PRESTATIONVIEW.render(matches));
			// RIDUZIONE: calcolo la somma totale dei prezzi
			double sum = 0;
			for (Prestation p : matches)
				sum += p.getPrice();
			// Ottengo il numero TOTALE di giorni dell'anno (365 o 366 se bisestile)
			// Uso lengthOfYear() invece di ChronoUnit perché voglio la media su TUTTO l'anno
			int days = from.lengthOfYear();
			// Stampo il guadagno totale
			Console.print("Guadagno totale " + sum + " € per " + days + " giorni");
			// Calcolo e stampo la media giornaliera dividendo il totale per i giorni
			Console.print("Guadagno medio giornaliero " + (sum / days) + " €");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (NumberFormatException e)
		{
			// Gestisco il caso in cui l'utente inserisce un valore non numerico
			Console.print("Anno non valido");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo 8 - Caricamento di oggetto figlio con due padri.
	 * Seleziona una prestazione tramite ID e la stampa con i dettagli completi.
	 * Include le informazioni del paziente (padre 1) e del servizio sanitario (padre 2).
	 *
	 * @throws SQLException se si verifica un errore durante la lettura dal database
	 * @throws NumberFormatException se l'input dell'utente non è un numero valido
	 */
	public static void printPrestationById()
	{
		try
		{
			Console.print("Inserire ID della prestazione");
			int id = Integer.parseInt(Console.readString());
			// Recupero la prestazione dal database tramite ID
			Prestation prestation = prestationRepo.findById(id);
			// Se non esiste, stampo errore e esco
			if (prestation == null)
			{
				Console.print("Prestazione non trovata con ID: " + id);
				return;
			}
			Console.print("===== DETTAGLI PRESTAZIONE =====");
			Console.print(Views.PRESTATIONVIEW.render(prestation));
			Console.print("\n=== DETTAGLI SERVIZIO ===");
			HealthService service = prestation.getHealthService();
			Console.print(Views.HEALTHSERVICERECAPVIEW.render(service));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (NumberFormatException e)
		{
			Console.print("ID non valido");
		}
	}

	/**
	 * Metodo 9 - Calcolo guadagno giorno per giorno in un periodo
	 *
	 * Questo metodo implementa un algoritmo di FILTRO, ORDINAMENTO e RIDUZIONE:
	 * 1. Filtra le prestazioni per un periodo specificato dall'utente
	 * 2. Ordina le prestazioni per data
	 * 3. Raggruppa (riduce) le prestazioni per data, sommando i guadagni dello stesso giorno
	 * 4. Stampa il guadagno dettagliato giorno per giorno
	 *
	 * Uso di LinkedHashMap: mantiene l'ordine di inserimento, quindi se le prestazioni
	 * sono ordinate per data, la mappa manterrà le date in ordine cronologico
	 */
	public static void printEarningDayByDay()
	{
		try
		{
			Console.print("Inserire data iniziale per il periodo");
			LocalDate from = LocalDate.parse(Console.readString());
			Console.print("Inserire data finale per il periodo");
			LocalDate to = LocalDate.parse(Console.readString());
			// STEP 2: FILTRO - recupera tutte le prestazioni nel periodo specificato
			List<Prestation> matches = prestationRepo.findByPeriod(from, to);
			if (matches.size() == 0)
			{
				Console.print("Nessuna prestazione nel periodo " + from + " " + to);
				return;
			}
			// ORDINAMENTO - ordino le prestazioni per data crescente
			// Cra un Comparator con lambda expression che confronta le date
			// p1.getDate().compareTo(p2.getDate()) ritorna:
			// - negativo se p1 è prima di p2
			// - zero se hanno la stessa data
			// - positivo se p1 è dopo p2
			Comparator<Prestation> comparator = (p1, p2) -> p1.getDate().compareTo(p2.getDate());
			matches.sort(comparator);
			// STEP 4: RIDUZIONE - crea una mappa per raggruppare i guadagni per data
			// Usa LinkedHashMap per mantenere l'ordine di inserimento (importante!)
			// Chiave (LocalDate) = la data della prestazione
			// Valore (Integer) = somma totale dei guadagni in quella data
			Map<LocalDate, Integer> incomeMap = new LinkedHashMap<LocalDate, Integer>();
			// Itero su tutte le prestazioni ordinate per data
			for (Prestation p : matches)
			{
				// Estraggo la data della prestazione corrente
				LocalDate d = p.getDate();
				// Se questa data è già nella mappa, ACCUMULO il prezzo a quello esistente
				if (incomeMap.containsKey(d))
					incomeMap.put(d, incomeMap.get(d) + p.getPrice());
				// Altrimenti è la prima prestazione di questa data, INIZIALIZZO con il prezzo
				else
					incomeMap.put(d, p.getPrice());
			}
			Console.print("Guadagni giorno per giorno nel periodo " + from + " " + to);
			// Itero sulla mappa (che mantiene l'ordine cronologico grazie a LinkedHashMap)
			for (LocalDate d : incomeMap.keySet())
				Console.print(d + "\t" + incomeMap.get(d) + " €");
		}
		catch (SQLException e)
		{
			// Gestione errore database
			e.printStackTrace();
		}
		catch (DateTimeParseException e)
		{
			// Gestione errore formato data non valido
			Console.print("Data non valida");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo 10 - Calcolo guadagno totale per un singolo tipo di servizio
	 *
	 * A differenza di printEarningDayByDay che stampa tutti i giorni,
	 * questo metodo chiede il NOME DI UN SERVIZIO e calcola il guadagno totale
	 * generato da QUEL SERVIZIO SPECIFICO in tutta la storia del database.
	 *
	 * Algoritmo:
	 * 1. Chiede all'utente il nome del servizio da cercare
	 * 2. Carica TUTTE le prestazioni dal database
	 * 3. Filtra solo quelle con il servizio cercato
	 * 4. Somma tutti i prezzi delle prestazioni filtrate
	 * 5. Stampa il totale
	 */
	public static void printEarningByServiceName()
	{
		try
		{
			// STEP 1: INPUT - chiedo all'utente quale servizio vuole analizzare
			Console.print("Inserire nome del servizio da cercare:");
			String searchServiceName = Console.readString();
			// STEP 2: Carico TUTTE le prestazioni dal database
			List<Prestation> allPrestations = prestationRepo.findAll();
			// Controllo se ci sono prestazioni nel database
			if (allPrestations.size() == 0)
			{
				Console.print("Nessuna prestazione presente nel database");
				return;
			}
			// STEP 3: FILTRO e RIDUZIONE - sommo solo i prezzi del servizio cercato
			int totalEarning = 0;  // Variabile accumulatore
			int count = 0;         // Contatore prestazioni trovate
			// Itero su tutte le prestazioni
			for (Prestation p : allPrestations)
			{
				// Estraggo il nome del servizio dalla prestazione corrente
				String serviceName = p.getHealthService().getName();
				// Se il nome del servizio CORRISPONDE a quello cercato
				// (uso equalsIgnoreCase per ignorare maiuscole/minuscole)
				if (serviceName.equalsIgnoreCase(searchServiceName))
				{
					totalEarning += p.getPrice();  // Accumulo il prezzo
					count++;                       // Incremento il contatore
				}
			}
			// STEP 4: OUTPUT - stampo il risultato
			if (count == 0)
				Console.print("Nessuna prestazione trovata per il servizio: " + searchServiceName);
			else
			{
				Console.print("Servizio: " + searchServiceName);
				Console.print("Numero prestazioni: " + count);
				Console.print("Guadagno totale: " + totalEarning + " €");
			}
		}
		catch (SQLException e)
		{
			Console.print("Errore durante il recupero delle prestazioni dal database");
			e.printStackTrace();
		}
	}

	/**
	 * Filtro e riduzione, da list a set
	 * 1 - carico tutti le prestazioni di un periodo
	 * 2 - estraggo tutti i pazienti e li metto in un set
	 * 3 - il set elimina le ripetizioni
	 * ATTENZIONE: perché funzioni DOVETE sovrascrivere equals e hashCode in Patient
	 */
	public static void printDistinctPatientsForPeriod()
	{
		try
		{
			Console.print("Inserire data iniziale per il periodo");
			LocalDate			from = LocalDate.parse(Console.readString());
			Console.print("Inserire data finale per il periodo");
			LocalDate			to = LocalDate.parse(Console.readString());
			List<Prestation>	matches = prestationRepo.findByPeriod(from, to);
			if (matches.size() == 0)
			{
				Console.print("Nessuna prestazione nel periodo " + from + " " + to);
				return;
			}
			Set<Patient>		distinctPatients = new HashSet<Patient>();
			for (Prestation p : matches)
				distinctPatients.add(p.getPatient());
			Console.print(Views.PATIENTRECAPVIEW.render(distinctPatients));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (DateTimeParseException e)
		{
			Console.print("Data non valida");
		}
	}

	/**
	 * Metodo 12 - Filtra e riduce da List a Set per ottenere servizi distinti.
	 * Simile a printDistinctPatientsForPeriod ma per i servizi sanitari.
	 *
	 * Algoritmo:
	 * 1. Chiede all'utente un periodo (data inizio e fine)
	 * 2. Carica tutte le prestazioni in quel periodo
	 * 3. Estrae tutti i servizi e li inserisce in un Set
	 * 4. Il Set elimina automaticamente le ripetizioni (servizi duplicati)
	 * 5. Stampa i servizi distinti utilizzati nel periodo
	 *
	 * IMPORTANTE: perché il Set elimini correttamente i duplicati, è necessario
	 * sovrascrivere equals() e hashCode() nella classe HealthService.
	 * Due HealthService sono considerati uguali se hanno lo stesso ID.
	 *
	 * @throws SQLException se si verifica un errore durante la lettura dal database
	 * @throws DateTimeParseException se le date inserite non sono valide
	 */
	public static void printDistinctServiceForPeriod()
	{
		try
		{
			Console.print("Inserire data iniziale per il periodo");
			LocalDate from = LocalDate.parse(Console.readString());
			Console.print("Inserire data finale per il periodo");
			LocalDate to = LocalDate.parse(Console.readString());
			//FILTRO --> recupero tutte le prestazioni nel periodo
			List<Prestation> matches = prestationRepo.findByPeriod(from, to);
			//controllo se ci sono prestazioni nel periodo
			if(matches.size() == 0)
			{
				Console.print("Nessuna prestazione nel periodo " + from + " ");
				return;
			}
			//RIDUZIONE --> da List<Prestation> a Set<HealthService>
			// uso hashset per eliminare automaticamente i servizi duplicati, se
			//una prestazione usa "Surgery" 10 volte, nel Set apparirà
			// UNA SOLA VOLTA ATTENZIONE: funziona solo se HealthService ha equals() e hashCode() sovrascritti!
			Set<HealthService> distinctServices = new HashSet<HealthService>();
			for(Prestation p : matches)
				distinctServices.add(p.getHealthService());
			Console.print("\nServizi distinti utilizzati nel periodo " + from + "-->" + to + ":");
			Console.print("\nNumero totale servizi diversi: " + distinctServices.size());
			Console.print("\n" + Views.HEALTHSERVICERECAPVIEW.render(distinctServices));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * TODO: Cancellazione prestazione per ID con controlli
	 */
	public static void			deletePrestation()
	{
		try
		{
			Console.print("Inserire ID della prestazione da cancellare: ");
			int 			id = Integer.parseInt(Console.readString());
			Prestation 		prestation = prestationRepo.findById(id);
			Console.print(prestation == null ? "Prestazione non trovata" : "");
			if(prestation == null)
				return; 
			LocalDate 		prestDate = prestation.getDate();
			Console.print(prestDate == null ? "Impossibile cancellare prestazioni passate" : "");
			if(prestDate.isBefore(LocalDate.now()))
				return;
			// VISUALIZZAZIONE DETTAGLI
	        Console.print("=== PRESTAZIONE DA CANCELLARE ===");
	        Console.print("Data: " + prestDate);
	        Patient 		patient = prestation.getPatient();
	        HealthService service = prestation.getHealthService();
	        if (patient != null)
	        	Console.print("Paziente: " + patient.getFirstName() + "" + patient.getLastName());
	        if (service != null)
	        	Console.print("Servizio: " + service.getName());
	        Console.print("Prezzo: " + prestation.getPrice() + " €");
	        //CONFERMA CANCELLAZIONE
	        Console.print("Confermare cancellazione? Attenzione, non si può revocare (S/N)");
	        String 		answer = Console.readString().trim().toLowerCase();	        
	        if(!"s".equals(answer))
	        {
	        	Console.print("Cancellazione annullata");
	        	return; 
	        }
	        // CANCELLAZIONE
	        prestationRepo.delete(id);
	        Console.print("Prestazione cancellata con successo");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * TODO: Stampa scheda HTML di una prestazione
	 *
	 * PSEUDOCODICE:
	 * 1. INPUT ID:
	 *    - Chiedere: "Inserire ID della prestazione"
	 *    - Leggere l'ID
	 *
	 * 2. CARICAMENTO PRESTAZIONE:
	 *    - Cercare usando prestationRepo.findById(id)
	 *    - Se null: stampare "Prestazione non trovata" e uscire
	 *
	 * 3. GENERAZIONE HTML:
	 * 4. SALVATAGGIO FILE:
	 *    - Creare file "prestation_[id].html"
	 *    - Scrivere l'HTML nel file usando FileWriter
	 *    - Stampare: "File salvato: prestation_[id].html"
	 *
	 * 5. GESTIONE ECCEZIONI:
	 *    - IOException: stampare errore scrittura file
	 *    - SQLException: stampare errore database
	 */
	public static void printPrestationHTML()
	{
		// TODO: implementare secondo pseudocodice
	}

	/**
	 * TODO: Stampa HTML prestazioni previste per oggi con logo
	 *
	 * PSEUDOCODICE:
	 * 1. CARICAMENTO PRESTAZIONI ODIERNE:
	 *    - Ottenere data odierna: LocalDate.now()
	 *    - Caricare prestazioni: prestationRepo.findByDate(today)
	 *    - Se vuoto: stampare "Nessuna prestazione per oggi" e uscire
	 *
	 * 2. ORDINAMENTO:
	 *    - Ordinare prestazioni per ID crescente
	 *    - Usare: matches.sort(Comparator.comparingInt(Prestation::getId))
	 *
	 * 3. GENERAZIONE HTML CON LOGO
	 *
	 * 4. CALCOLO TOTALI:
	 *    - Contare numero prestazioni
	 *    - Sommare tutti i prezzi per guadagno totale
	 *
	 * 5. SALVATAGGIO FILE:
	 *    - Nome file: "prestations_today_[YYYY-MM-DD].html"
	 *    - Scrivere HTML nel file
	 *    - Stampare: "File salvato: prestations_today_[data].html"
	 *
	 * 6. GESTIONE ECCEZIONI:
	 *    - IOException: stampare errore scrittura file
	 *    - SQLException: stampare errore database
	 */
	public static void printTodayPrestationsHTML()
	{
		// TODO: implementare secondo pseudocodice
	}
}
