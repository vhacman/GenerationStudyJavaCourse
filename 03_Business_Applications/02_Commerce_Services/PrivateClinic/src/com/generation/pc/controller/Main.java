package com.generation.pc.controller;

import java.sql.SQLException;
import java.util.List;

import com.generation.library.Console;
import com.generation.library.profiling.ProfilingMonitor;
import com.generation.pc.model.entity.HealthService;
import com.generation.pc.model.entity.Patient;
import com.generation.pc.model.repository.HealthServiceRepository;
import com.generation.pc.model.repository.HealthServiceRepositoryFactory;
import com.generation.pc.model.repository.PatientRepository;
import com.generation.pc.model.repository.PatientRepositoryFactory;

/*
 * ==================================================================================
 * TEORIA: Classe Main - Entry Point dell'applicazione
 * ==================================================================================
 * DEFINIZIONE:
 * Main è il controller dell'applicazione, responsabile di coordinare l'interazione
 * tra l'utente (via console), il model (entità) e la persistenza (repository).
 *
 * ARCHITETTURA MVC (Model-View-Controller):
 * - Model: Patient, HealthService (rappresentano i dati e la business logic)
 * - View: Console (input/output utente)
 * - Controller: Main (coordina model e view, gestisce il flusso dell'applicazione)
 *
 * SCOPO:
 * 1. Presentare un menu all'utente
 * 2. Raccogliere input
 * 3. Chiamare i metodi appropriati dei repository
 * 4. Mostrare i risultati all'utente
 *
 * PERCHÉ LO FACCIAMO:
 * Separare le responsabilità (Separation of Concerns):
 * - Main NON sa come funziona il database (responsabilità del repository)
 * - Repository NON sa come interagire con l'utente (responsabilità del controller)
 * - Entity NON sa né di database né di UI (solo dati e validazione)
 *
 * Questo rende il codice:
 * - Più manutenibile: possiamo cambiare il database senza toccare Main
 * - Più testabile: possiamo testare i repository senza UI
 * - Più riutilizzabile: possiamo usare le entità in contesti diversi (web, mobile)
 */
public class Main
{
	/*
	 * ==================================================================================
	 * TEORIA: Campi static - stato condiviso a livello di classe
	 * ==================================================================================
	 * Questi repository sono static perché:
	 * 1. Devono essere accessibili da tutti i metodi static (come printMenu, viewAllPatients)
	 * 2. Non vogliamo creare più istanze (Singleton pattern delle factory)
	 * 3. Rappresentano risorse condivise (connessione database + cache)
	 *
	 * NOTA:
	 * In un'applicazione web multi-thread questo approccio NON sarebbe sicuro.
	 * Per applicazioni CLI single-thread come questa va bene.
	 */
	static PatientRepository		patientRepo;
	static HealthServiceRepository	serviceRepo;

	/*
	 * ==================================================================================
	 * TEORIA: Metodo main - Entry Point del programma Java
	 * ==================================================================================
	 * DEFINIZIONE:
	 * main(String[] args) è il punto di ingresso di ogni applicazione Java.
	 * La JVM (Java Virtual Machine) cerca questo metodo per avviare l'esecuzione.
	 *
	 * FIRMA OBBLIGATORIA:
	 * - public: deve essere accessibile dalla JVM
	 * - static: la JVM lo chiama senza istanziare la classe
	 * - void: non restituisce valori
	 * - String[] args: array di argomenti da riga di comando
	 *
	 * PERCHÉ LO FACCIAMO:
	 * È lo standard Java. Senza questo metodo, il programma non può essere eseguito.
	 */
	public static void main(String[] args)
	{
		/*
		 * ==================================================================================
		 * SETUP: Inizializzazione dell'applicazione
		 * ==================================================================================
		 */
		// Inizializza il monitor di profiling per tracciare query e performance
		ProfilingMonitor.init();

		// Ottiene i repository tramite le factory (pattern Factory + Singleton)
		patientRepo = PatientRepositoryFactory.make();
		serviceRepo = HealthServiceRepositoryFactory.make();

		/*
		 * ==================================================================================
		 * TEORIA: Loop del menu principale (Event Loop pattern)
		 * ==================================================================================
		 * DEFINIZIONE:
		 * Un loop infinito che:
		 * 1. Mostra il menu
		 * 2. Legge l'input dell'utente
		 * 3. Esegue l'azione scelta
		 * 4. Ripete fino a quando l'utente sceglie di uscire
		 *
		 * SCOPO:
		 * Mantenere il programma attivo e reattivo agli input dell'utente.
		 *
		 * PERCHÉ LO FACCIAMO:
		 * Le applicazioni CLI interactive necessitano di un loop per continuare
		 * a rispondere agli input. Senza questo loop, il programma terminerebbe subito.
		 */
		// Menu loop
		boolean running = true;
		while (running)
		{
			printMenu();
			Console.print("Scegli un'opzione: ");
			int choice = Console.readInt();

			/*
			 * ==================================================================================
			 * TEORIA: try-catch (Gestione delle eccezioni)
			 * ==================================================================================
			 * DEFINIZIONE:
			 * try-catch permette di gestire errori runtime (eccezioni) senza far crashare il programma.
			 *
			 * SCOPO:
			 * - Intercettare errori (es. SQLException, NumberFormatException)
			 * - Fornire feedback all'utente
			 * - Continuare l'esecuzione invece di terminare bruscamente
			 *
			 * PERCHÉ LO FACCIAMO:
			 * Le operazioni di database possono fallire per molti motivi:
			 * - Violazione di vincoli (es. SSN duplicato)
			 * - Problemi di connessione
			 * - Errori di sintassi SQL
			 * Con try-catch, l'applicazione continua a funzionare anche se un'operazione fallisce.
			 *
			 * FLUSSO:
			 * 1. Esegue il codice nel blocco try
			 * 2. Se si verifica un'eccezione, salta al blocco catch
			 * 3. Gestisce l'errore (stampa messaggio, logging, etc.)
			 * 4. Continua l'esecuzione dopo il catch
			 */
			try
			{
				/*
				 * ==================================================================================
				 * TEORIA: switch-case (Controllo di flusso multi-branch)
				 * ==================================================================================
				 * DEFINIZIONE:
				 * switch valuta un'espressione e salta al case corrispondente.
				 *
				 * SCOPO:
				 * Gestire menu con molte opzioni in modo più leggibile di tanti if-else.
				 *
				 * SINTASSI:
				 * switch (variabile) {
				 *     case valore1: codice; break;
				 *     case valore2: codice; break;
				 *     default: codice per casi non previsti;
				 * }
				 *
				 * BREAK:
				 * Senza break, l'esecuzione "cade" nel case successivo (fall-through).
				 *
				 * PERCHÉ LO FACCIAMO:
				 * Per un menu con 13 opzioni, switch è più chiaro e manutenibile di:
				 * if (choice == 1) ... else if (choice == 2) ... else if (choice == 3) ...
				 */
				switch (choice)
				{
					case 1:		viewAllPatients();		break;
					case 2:		insertPatient();		break;
					case 3:		updatePatient();		break;
					case 4:		deletePatient();		break;
					case 5:		searchBySSN();			break;
					case 6:		searchByCity();			break;
					case 7:		searchByLastName();		break;
					case 8:		viewAllServices();		break;
					case 9:		insertService();		break;
					case 10:	updateServicePrice();	break;
					case 11:	deleteService();		break;
					case 12:	searchByType();			break;
					case 13:	printProfiling();		break;
					case 0:
						running = false;  // Imposta il flag a false per uscire dal loop
						Console.print("Arrivederci!");
						break;
					default:
						Console.print("Opzione non valida!");
				}
			}
			catch (Exception e)
			{
				/*
				 * Gestione errori:
				 * - e.getMessage(): ottiene il messaggio descrittivo dell'eccezione
				 * - e.printStackTrace(): stampa lo stack trace completo per debugging
				 */
				Console.print("Errore: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static void printMenu()
	{
		Console.print("\n=== PRIVATE CLINIC MANAGEMENT ===");
		Console.print("[1]  Visualizza tutti i pazienti");
		Console.print("[2]  Inserisci nuovo paziente");
		Console.print("[3]  Modifica paziente (city/address)");
		Console.print("[4]  Cancella paziente");
		Console.print("[5]  Cerca paziente per SSN");
		Console.print("[6]  Cerca pazienti per città");
		Console.print("[7]  Cerca pazienti per cognome");
		Console.print("[8]  Visualizza tutti i servizi");
		Console.print("[9]  Inserisci nuovo servizio");
		Console.print("[10] Modifica prezzo servizio");
		Console.print("[11] Cancella servizio");
		Console.print("[12] Cerca servizi per tipo");
		Console.print("[13] Stampa statistiche profiling");
		Console.print("[0]  Esci");
		Console.print("==================================");
	}

	// ========== PATIENT OPERATIONS ==========

	/*
	 * ==================================================================================
	 * TEORIA: Visualizzazione di una lista di entità
	 * ==================================================================================
	 */
	private static void viewAllPatients()
	{
		/*
		 * TEORIA: Chiamata al repository per ottenere tutti i pazienti
		 * findAll() restituisce List<Patient>, una collezione di tutti i pazienti nel database.
		 */
		List<Patient> patients = patientRepo.findAll();

		/*
		 * TEORIA: isEmpty() - verifica se una collezione è vuota
		 * Restituisce true se la lista non contiene elementi, false altrimenti.
		 * Alternativa: patients.size() == 0 (meno idiomatico)
		 *
		 * TEORIA: return - uscita anticipata dal metodo
		 * Esce dal metodo senza eseguire il codice successivo.
		 * Utile per evitare nesting eccessivo (invece di else { tutto il resto }).
		 */
		if (patients.isEmpty())
		{
			Console.print("Nessun paziente presente.");
			return;
		}

		Console.print("\n=== LISTA PAZIENTI ===");

		/*
		 * TEORIA: Enhanced for loop (for-each)
		 * DEFINIZIONE:
		 * for (Tipo elemento : collezione) { ... }
		 * Itera su ogni elemento della collezione senza gestire indici.
		 *
		 * SCOPO:
		 * Sintassi più semplice e leggibile rispetto al for tradizionale quando
		 * non serve l'indice.
		 *
		 * EQUIVALENTE FOR TRADIZIONALE:
		 * for (int i = 0; i < patients.size(); i++) {
		 *     Patient p = patients.get(i);
		 *     ...
		 * }
		 *
		 * PERCHÉ LO FACCIAMO:
		 * - Più conciso e leggibile
		 * - Meno propenso a errori (no IndexOutOfBoundsException)
		 * - Funziona con qualsiasi Iterable (List, Set, array, etc.)
		 */
		for (Patient p : patients)
			Console.print("ID: " + p.getId() +
			              " | Nome: " + p.getFirstName() +
			              " | Cognome: " + p.getLastName() +
			              " | SSN: " + p.getSsn() +
			              " | Città: " + p.getCity() +
			              " | Indirizzo: " + p.getAddress() +
			              " | Data Nascita: " + p.getDob());
	}

	/*
	 * ==================================================================================
	 * TEORIA: Inserimento di una nuova entità con validazione
	 * ==================================================================================
	 */
	private static void insertPatient() throws SQLException
	{
		Console.print("\n=== INSERIMENTO NUOVO PAZIENTE ===");

		/*
		 * TEORIA: Raccolta input utente
		 * Console.readString() legge una linea di testo dallo standard input.
		 * Ogni campo viene letto separatamente e memorizzato in una variabile locale.
		 */
		Console.print("Nome: ");
		String firstName = Console.readString();
		Console.print("Cognome: ");
		String lastName = Console.readString();
		Console.print("Codice Fiscale: ");
		String ssn = Console.readString();
		Console.print("Città: ");
		String city = Console.readString();
		Console.print("Indirizzo: ");
		String address = Console.readString();
		Console.print("Data di nascita (YYYY-MM-DD): ");
		String dob = Console.readString();

		/*
		 * TEORIA: Creazione e popolamento di un'entità
		 * Usiamo il costruttore no-args e i setter invece del costruttore parametrizzato
		 * perché è più flessibile (possiamo popolare solo alcuni campi).
		 */
		Patient p = new Patient();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setSsn(ssn);
		p.setCity(city);
		p.setAddress(address);
		p.setDob(dob);  // setDob(String) fa il parsing LocalDate.parse()

		/*
		 * TEORIA: Validazione client-side prima del database
		 * DEFINIZIONE:
		 * isValid() verifica se l'entità rispetta tutte le regole di business.
		 * Restituisce true se getErrors().isEmpty(), false altrimenti.
		 *
		 * SCOPO:
		 * Validare PRIMA di tentare l'insert nel database per:
		 * 1. Fornire feedback immediato all'utente
		 * 2. Evitare SQLException inutili
		 * 3. Risparmiare round-trip al database
		 *
		 * PERCHÉ LO FACCIAMO:
		 * Se proviamo a inserire dati invalidi direttamente nel database:
		 * - Potrebbe fallire con SQLException generica (poco chiara)
		 * - Spreco di risorse (query + rollback)
		 * - Feedback meno user-friendly
		 *
		 * Con validazione client-side:
		 * - Errori chiari PRIMA di andare al database
		 * - Possibilità di mostrare TUTTI gli errori insieme (non uno alla volta)
		 * - Migliore user experience
		 *
		 * OPERATORE LOGICO NOT (!):
		 * !p.isValid() significa "se il paziente NON è valido"
		 * Equivalente a: p.isValid() == false
		 */
		if (!p.isValid())
		{
			Console.print("Errori di validazione:");
			// Mostra tutti gli errori di validazione all'utente
			for (String error : p.getErrors())
				Console.print("  - " + error);
			return;  // Esce senza fare l'insert
		}

		/*
		 * TEORIA: Persistenza nel database e recupero dell'id generato
		 * patientRepo.insert(p) fa:
		 * 1. Esegue INSERT INTO patients (...) VALUES (...)
		 * 2. Recupera l'id auto-generato dal database
		 * 3. Imposta l'id sull'oggetto Patient
		 * 4. Restituisce lo stesso oggetto (ora con id popolato)
		 *
		 * PERCHÉ RESTITUIRE L'OGGETTO:
		 * Così possiamo accedere all'id generato. La classe base del repository
		 * gestisce il recupero dell'id (tramite query separata o meccanismo specifico).
		 */
		Patient inserted = patientRepo.insert(p);
		Console.print("Paziente inserito con ID: " + inserted.getId());
	}

	/*
	 * ==================================================================================
	 * TEORIA: Update condizionale con null check e input opzionale
	 * ==================================================================================
	 */
	private static void updatePatient() throws SQLException
	{
		Console.print("\n=== MODIFICA PAZIENTE (city/address) ===");

		// Legge l'ID del paziente da modificare
		Console.print("ID del paziente da modificare: ");
		int id = Console.readInt();

		/*
		 * TEORIA: Verifica dell'esistenza dell'entità prima della modifica
		 * findById() restituisce null se non trova il paziente.
		 * Dobbiamo verificare prima di procedere per evitare NullPointerException.
		 */
		Patient p = patientRepo.findById(id);

		/*
		 * TEORIA: Null check - verifica se un oggetto è null
		 * DEFINIZIONE:
		 * In Java, null rappresenta l'assenza di un oggetto.
		 * Tentare di chiamare metodi su null causa NullPointerException.
		 *
		 * SCOPO:
		 * Verificare che l'oggetto esista prima di usarlo.
		 *
		 * PERCHÉ LO FACCIAMO:
		 * Se l'utente inserisce un ID inesistente (es. 9999), findById() restituisce null.
		 * Senza questo check, p.getCity() causerebbe un crash.
		 */
		if (p == null)
		{
			Console.print("Paziente non trovato!");
			return;
		}

		/*
		 * toString() viene chiamato implicitamente quando stampiamo un oggetto.
		 * Console.print("Paziente: " + p) → Console.print("Paziente: " + p.toString())
		 */
		Console.print("Paziente corrente: " + p);
		Console.print("Premi INVIO per mantenere il valore corrente");

		/*
		 * TEORIA: Update opzionale - lasciare valori invariati se l'input è vuoto
		 * Mostriamo il valore corrente tra [ ] per suggerire all'utente cosa verrà mantenuto.
		 */
		Console.print("Nuova città [" + p.getCity() + "]: ");
		String newCity = Console.readString();
		Console.print("Nuovo indirizzo [" + p.getAddress() + "]: ");
		String newAddress = Console.readString();

		/*
		 * TEORIA: isBlank() vs isEmpty()
		 * DEFINIZIONE:
		 * - isEmpty(): true se la lunghezza è 0 (solo stringhe vuote "")
		 * - isBlank(): true se la stringa è vuota O contiene solo whitespace ("  ", "\t", etc.)
		 *
		 * SCOPO:
		 * isBlank() è più robusto perché considera anche spazi, tab, newline come "vuoto".
		 *
		 * ESEMPIO:
		 * "".isEmpty() → true   |  "".isBlank() → true
		 * "  ".isEmpty() → false  |  "  ".isBlank() → true
		 *
		 * PERCHÉ LO FACCIAMO:
		 * Aggiorniamo solo i campi per cui l'utente ha fornito un valore.
		 * Se l'utente preme solo INVIO, la stringa sarà "" (vuota) e non aggiorniamo il campo.
		 */
		if (!newCity.isBlank())
			p.setCity(newCity);
		if (!newAddress.isBlank())
			p.setAddress(newAddress);

		/*
		 * TEORIA: Update nel database
		 * patientRepo.update(p) esegue:
		 * UPDATE patients SET firstName=?, lastName=?, ... WHERE id=?
		 * con tutti i campi di p, anche quelli non modificati.
		 */
		patientRepo.update(p);
		Console.print("Paziente aggiornato con successo!");
	}

	/*
	 * ==================================================================================
	 * TEORIA: Delete con conferma utente (pattern di sicurezza)
	 * ==================================================================================
	 */
	private static void deletePatient() throws SQLException
	{
		Console.print("\n=== CANCELLA PAZIENTE ===");

		Console.print("ID del paziente da cancellare: ");
		int id = Console.readInt();
		Patient p = patientRepo.findById(id);

		// Verifica che il paziente esista prima di procedere
		if (p == null)
		{
			Console.print("Paziente non trovato!");
			return;
		}

		/*
		 * TEORIA: Conferma dell'azione distruttiva
		 * DEFINIZIONE:
		 * Prima di eseguire operazioni distruttive (DELETE, DROP, TRUNCATE),
		 * è buona pratica chiedere conferma all'utente.
		 *
		 * SCOPO:
		 * - Prevenire cancellazioni accidentali
		 * - Mostrare all'utente cosa sta per essere cancellato
		 * - Dare la possibilità di annullare l'operazione
		 *
		 * PERCHÉ LO FACCIAMO:
		 * La cancellazione è un'operazione irreversibile. Senza conferma,
		 * un errore di digitazione dell'ID potrebbe cancellare il record sbagliato.
		 */
		Console.print("Paziente: " + p);
		Console.print("Confermi cancellazione? (S/N): ");
		String confirm = Console.readString();

		/*
		 * TEORIA: equalsIgnoreCase() - confronto stringhe case-insensitive
		 * DEFINIZIONE:
		 * equalsIgnoreCase() confronta due stringhe ignorando maiuscole/minuscole.
		 *
		 * DIFFERENZA CON equals():
		 * "S".equals("s")           → false
		 * "S".equalsIgnoreCase("s") → true
		 *
		 * SCOPO:
		 * Accettare sia "S" che "s" come conferma, rendendo l'interfaccia
		 * più user-friendly.
		 *
		 * TEORIA: Tipo di ritorno boolean
		 * delete() restituisce boolean:
		 * - true se la cancellazione è riuscita
		 * - false se è fallita (es. record non trovato, vincoli di integrità)
		 */
		if (confirm.equalsIgnoreCase("S"))
		{
			boolean success = patientRepo.delete(id);
			if (success)
				Console.print("Paziente cancellato con successo!");
			else
				Console.print("Errore durante la cancellazione.");
		}
		else
			Console.print("Cancellazione annullata.");
	}

	/*
	 * ==================================================================================
	 * TEORIA: Ricerca per chiave univoca (SSN)
	 * ==================================================================================
	 */
	private static void searchBySSN()
	{
		Console.print("\n=== CERCA PAZIENTE PER SSN ===");

		Console.print("Codice Fiscale: ");
		String ssn = Console.readString();

		/*
		 * TEORIA: Ricerca per chiave univoca
		 * findBySSN() restituisce:
		 * - Un singolo Patient se trovato
		 * - null se non esiste nessun paziente con quel SSN
		 *
		 * DIFFERENZA CON findById():
		 * - findById() usa la chiave primaria (id)
		 * - findBySSN() usa una chiave alternativa univoca (ssn)
		 *
		 * Entrambi restituiscono al massimo UN risultato (o null).
		 */
		Patient p = patientRepo.findBySSN(ssn);

		if (p == null)
			Console.print("Nessun paziente trovato con SSN: " + ssn);
		else
			Console.print(p);  // toString() viene chiamato implicitamente
	}

	/*
	 * ==================================================================================
	 * TEORIA: Ricerca per criterio non univoco (può restituire più risultati)
	 * ==================================================================================
	 */
	private static void searchByCity()
	{
		Console.print("\n=== CERCA PAZIENTI PER CITTÀ ===");

		Console.print("Città: ");
		String city = Console.readString();

		/*
		 * TEORIA: Query che restituisce multipli risultati
		 * findByCity() restituisce List<Patient> perché:
		 * - Più pazienti possono vivere nella stessa città
		 * - La lista può essere vuota se nessun paziente vive lì
		 * - La lista NON è mai null (sempre un oggetto List, anche se vuoto)
		 *
		 * BEST PRACTICE:
		 * Quando un metodo restituisce una collezione, dovrebbe restituire
		 * una collezione vuota invece di null (evita NullPointerException).
		 */
		List<Patient> patients = patientRepo.findByCity(city);

		if (patients.isEmpty())
			Console.print("Nessun paziente trovato nella città: " + city);
		else
		{
			/*
			 * TEORIA: size() - ottenere la dimensione di una collezione
			 * patients.size() restituisce il numero di elementi nella lista.
			 * È equivalente a COUNT(*) in SQL.
			 */
			Console.print("Trovati " + patients.size() + " pazienti:");
			for (Patient p : patients)
				Console.print(p);
		}
	}

	/*
	 * ==================================================================================
	 * TEORIA: Ricerca parziale con pattern matching (LIKE)
	 * ==================================================================================
	 */
	private static void searchByLastName()
	{
		Console.print("\n=== CERCA PAZIENTI PER COGNOME ===");

		Console.print("Parte del cognome: ");
		String part = Console.readString();

		/*
		 * TEORIA: Pattern matching con LIKE
		 * findByLastNameContaining() esegue una query SQL:
		 * SELECT * FROM patients WHERE lastName LIKE '%part%'
		 *
		 * SCOPO:
		 * Permettere ricerche parziali, utili quando:
		 * - Non si ricorda il cognome completo
		 * - Si vuole trovare cognomi simili
		 * - Si cerca un gruppo di cognomi con radice comune
		 *
		 * ESEMPI:
		 * Input "Rossi" → trova "Rossi", "DeRossi", "Rossini"
		 * Input "ross" → trova "Rossi", "Ross", "Grossi"
		 */
		List<Patient> patients = patientRepo.findByLastNameContaining(part);

		if (patients.isEmpty())
			Console.print("Nessun paziente trovato con cognome contenente: " + part);
		else
		{
			Console.print("Trovati " + patients.size() + " pazienti:");
			for (Patient p : patients)
				Console.print(p);
		}
	}

	// ========== HEALTH SERVICE OPERATIONS ==========

	/*
	 * ==================================================================================
	 * TEORIA: Visualizzazione lista servizi sanitari
	 * ==================================================================================
	 * Questo metodo segue lo stesso pattern di viewAllPatients():
	 * 1. Recupera tutti i record dal database
	 * 2. Verifica se la lista è vuota
	 * 3. Mostra intestazione tabellare
	 * 4. Itera e visualizza ogni elemento formattato
	 */
	private static void viewAllServices()
	{
		List<HealthService> services = serviceRepo.findAll();
		if (services.isEmpty())
		{
			Console.print("Nessun servizio presente.");
			return;
		}

		Console.print("\n=== LISTA SERVIZI SANITARI ===");

		/*
		 * TEORIA: Concatenazione di stringhe con operatore +
		 * DEFINIZIONE:
		 * L'operatore + concatena (unisce) stringhe e altri tipi di dati.
		 *
		 * CONVERSIONE AUTOMATICA:
		 * Java converte automaticamente i tipi primitivi (int, double, boolean)
		 * in String quando vengono concatenati con una stringa.
		 *
		 * ESEMPI:
		 * "ID: " + 5           → "ID: 5"
		 * "Prezzo: €" + 100    → "Prezzo: €100"
		 * "Nome: " + s.getName() → "Nome: Mario"
		 *
		 * VANTAGGI:
		 * - Sintassi semplice e intuitiva
		 * - Facile da leggere
		 * - Non richiede conoscenza dei formati %s, %d, etc.
		 *
		 * SVANTAGGI:
		 * - Non permette allineamento tabellare (padding, larghezza fissa)
		 * - Output non allineato se i dati hanno lunghezze diverse
		 *
		 * QUANDO USARLA:
		 * - Per output semplice non tabellare
		 * - Per messaggi e notifiche
		 * - Quando non serve allineamento preciso
		 */
		for (HealthService s : services)
			Console.print("ID: " + s.getId() +
			              " | Descrizione: " + s.getDescription() +
			              " | Tipo: " + s.getType() +
			              " | Prezzo: €" + s.getPrice());
	}

	/*
	 * ==================================================================================
	 * TEORIA: Inserimento servizio sanitario con validazione
	 * ==================================================================================
	 * Pattern identico a insertPatient() ma per HealthService:
	 * 1. Raccolta input
	 * 2. Creazione e popolamento entità
	 * 3. Validazione client-side
	 * 4. Persistenza nel database se valido
	 */
	private static void insertService() throws SQLException
	{
		Console.print("\n=== INSERIMENTO NUOVO SERVIZIO ===");

		Console.print("Descrizione: ");
		String description = Console.readString();
		Console.print("Tipo: ");
		String type = Console.readString();
		Console.print("Prezzo (€): ");
		int price = Console.readInt();

		// Creazione entità con costruttore no-args + setter
		HealthService s = new HealthService();
		s.setDescription(description);
		s.setType(type);
		s.setPrice(price);

		// Validazione prima di persistere
		if (!s.isValid())
		{
			Console.print("Errori di validazione:");
			for (String error : s.getErrors())
				Console.print("  - " + error);
			return;
		}

		// Insert nel database e recupero dell'id generato
		HealthService inserted = serviceRepo.insert(s);
		Console.print("Servizio inserito con ID: " + inserted.getId());
	}

	/*
	 * ==================================================================================
	 * TEORIA: Update parziale - modifica solo un campo
	 * ==================================================================================
	 */
	private static void updateServicePrice() throws SQLException
	{
		Console.print("\n=== MODIFICA PREZZO SERVIZIO ===");

		Console.print("ID del servizio da modificare: ");
		int id = Console.readInt();
		HealthService s = serviceRepo.findById(id);

		// Null check per verificare esistenza
		if (s == null)
		{
			Console.print("Servizio non trovato!");
			return;
		}

		Console.print("Servizio corrente: " + s);

		/*
		 * TEORIA: Update mirato di un solo campo
		 * A differenza di updatePatient() che permette di modificare più campi
		 * opzionalmente, qui modifichiamo SOLO il prezzo.
		 *
		 * NOTA:
		 * Anche se modifichiamo solo il prezzo, l'UPDATE SQL aggiornerà
		 * tutti i campi (description, type, price). Questo è il comportamento
		 * standard dell'ORM: UPDATE health_services SET description=?, type=?, price=? WHERE id=?
		 *
		 * ALTERNATIVA PIÙ EFFICIENTE (non implementata qui):
		 * UPDATE health_services SET price=? WHERE id=?
		 * (aggiorna solo il campo modificato)
		 */
		Console.print("Nuovo prezzo (€): ");
		int newPrice = Console.readInt();

		s.setPrice(newPrice);

		/*
		 * Validazione dopo la modifica
		 * getErrors() verificherà che price > 0 (regola di business)
		 */
		if (!s.isValid())
		{
			Console.print("Errore: il prezzo deve essere maggiore di zero!");
			return;
		}

		serviceRepo.update(s);
		Console.print("Servizio aggiornato con successo!");
	}

	/*
	 * ==================================================================================
	 * TEORIA: Delete con conferma - pattern identico a deletePatient()
	 * ==================================================================================
	 */
	private static void deleteService() throws SQLException
	{
		Console.print("\n=== CANCELLA SERVIZIO ===");

		Console.print("ID del servizio da cancellare: ");
		int id = Console.readInt();
		HealthService s = serviceRepo.findById(id);

		if (s == null)
		{
			Console.print("Servizio non trovato!");
			return;
		}

		// Mostra il servizio prima di chiedere conferma
		Console.print("Servizio: " + s);
		Console.print("Confermi cancellazione? (S/N): ");
		String confirm = Console.readString();

		if (confirm.equalsIgnoreCase("S"))
		{
			boolean success = serviceRepo.delete(id);
			if (success)
				Console.print("Servizio cancellato con successo!");
			else
				Console.print("Errore durante la cancellazione.");
		}
		else
			Console.print("Cancellazione annullata.");
	}

	/*
	 * ==================================================================================
	 * TEORIA: Ricerca per tipo (criterio non univoco)
	 * ==================================================================================
	 */
	private static void searchByType()
	{
		Console.print("\n=== CERCA SERVIZI PER TIPO ===");

		Console.print("Tipo: ");
		String type = Console.readString();

		/*
		 * TEORIA: Query per categoria/classificazione
		 * findByType() è simile a findByCity() per i pazienti:
		 * - Cerca tutti i servizi di un certo tipo
		 * - Restituisce List<HealthService> (può essere vuota)
		 * - Query SQL: SELECT * FROM health_services WHERE type=?
		 *
		 * ESEMPI DI TIPI:
		 * - "Visita" → visita specialistica, visita generica
		 * - "Esame" → esami di laboratorio, diagnostica
		 * - "Chirurgia" → interventi chirurgici
		 * - "Terapia" → fisioterapia, radioterapia
		 */
		List<HealthService> services = serviceRepo.findByType(type);

		if (services.isEmpty())
			Console.print("Nessun servizio trovato del tipo: " + type);
		else
		{
			Console.print("Trovati " + services.size() + " servizi:");
			for (HealthService s : services)
				Console.print(s);
		}
	}

	// ========== PROFILING ==========

	/*
	 * ==================================================================================
	 * TEORIA: Profiling e Performance Monitoring
	 * ==================================================================================
	 * DEFINIZIONE:
	 * Il profiling è il processo di misurazione delle performance di un'applicazione
	 * per identificare bottleneck e ottimizzare il codice.
	 *
	 * SCOPO:
	 * Raccogliere metriche su:
	 * - Numero di query eseguite
	 * - Numero di righe lette dal database
	 * - Media di righe per query (efficienza delle query)
	 *
	 * PERCHÉ LO FACCIAMO:
	 * - Identificare query inefficienti (es. N+1 problem)
	 * - Verificare l'efficacia della cache
	 * - Ottimizzare le performance del database
	 *
	 * ESEMPIO:
	 * Se media righe/query è molto alta → probabilmente leggiamo più dati del necessario
	 * Se numero query è molto alto → probabilmente manca caching o stiamo facendo N+1 queries
	 */
	private static void printProfiling()
	{
		Console.print("\n=== STATISTICHE PROFILING ===");

		// ProfilingMonitor è un campo static della libreria che traccia le metriche
		Console.print("Numero di query eseguite: " + ProfilingMonitor.queryNumber);
		Console.print("Numero di righe lette: " + ProfilingMonitor.rowsNumbers);

		/*
		 * TEORIA: Operatore ternario per prevenire divisione per zero
		 * SINTASSI:
		 * condizione ? valoreSeVero : valoreSeFalso
		 *
		 * CASTING:
		 * (double) converte int a double per ottenere un risultato decimale.
		 * Senza cast: 10 / 3 = 3 (divisione intera)
		 * Con cast: (double) 10 / 3 = 3.333... (divisione decimale)
		 *
		 * PERCHÉ LO FACCIAMO:
		 * Se queryNumber è 0, la divisione causerebbe ArithmeticException (divisione per zero).
		 * L'operatore ternario previene questo restituendo 0 se non ci sono query.
		 */
		Console.print("Media righe per query: " +
				(ProfilingMonitor.queryNumber > 0 ?
						(double) ProfilingMonitor.rowsNumbers / ProfilingMonitor.queryNumber : 0));
	}
}
