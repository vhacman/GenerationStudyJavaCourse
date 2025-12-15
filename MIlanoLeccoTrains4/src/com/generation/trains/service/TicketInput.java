package com.generation.trains.service;

import com.generation.library.Console;

/**
 * Classe di utilità per la raccolta dei dati del biglietto ferroviario.
 * Gestisce l'interazione con l'utente attraverso la console per acquisire
 * tutte le informazioni necessarie alla creazione di un biglietto.
 * 
 * Questa classe segue il pattern di un "Input Handler" separando la logica
 * di raccolta dati dalla logica di business del biglietto stesso.
 */
public class TicketInput
{
	/**
	 * Metodo statico che coordina l'intero processo di raccolta dati per un biglietto.
	 * 
	 * Il metodo guida l'utente attraverso una serie di domande per raccogliere:
	 * - Dati anagrafici del passeggero (nome, età)
	 * - Informazioni sulla tratta (partenza, arrivo)
	 * - Preferenze di viaggio (classe, carta verde)
	 * - Dettagli temporali (data, orario)
	 * 
	 * Ogni input viene validato per garantire la correttezza dei dati prima
	 * di procedere con la creazione dell'oggetto Ticket.
	 * 
	 * @return un oggetto Ticket completamente inizializzato con tutti i dati raccolti.
	 *         La distanza e il prezzo vengono calcolati automaticamente dal costruttore
	 *         di Ticket in base ai dati forniti.
	 */
	public static Ticket collectTicketData()
	{
		// ===== FASE 1: RACCOLTA DATI ANAGRAFICI =====
		
		// Richiesta del nome completo del passeggero
		// Utilizziamo askNotEmptyString per garantire che l'utente inserisca almeno un carattere
		// Il metodo continuerà a chiedere l'input finché non viene fornita una stringa valida
		String fullName = Console.askNotEmptyString(
			"Inserisci il nome completo del passeggero: ", 
			"Errore: il nome non può essere vuoto. Riprova."
		);
		
		// Richiesta dell'età del passeggero
		// readIntBetween assicura che l'età sia un numero intero compreso tra 0 e 120
		// Questo previene valori negativi o irrealisticamente alti
		// L'età è fondamentale per applicare eventuali sconti (es. over 75 viaggiano gratis)
		int age = Console.readIntBetween(
			"Inserisci l'età del passeggero: ", 
			"Errore: età non valida. Inserisci un valore tra 0 e 120.", 
			0, 
			120
		);
		
		// ===== FASE 2: RACCOLTA DATI SULLA TRATTA =====
		
		// Mostriamo all'utente le stazioni disponibili nel sistema
		// Questo aiuta l'utente a sapere quali valori può inserire
		Console.print("\nStazioni disponibili: Milano, Monza, Osnago, Lecco");
		
		// Richiesta della stazione di partenza
		// Non effettuiamo controlli sulla validità del nome in questa fase
		// La validazione effettiva avverrà quando creeremo l'oggetto Station
		String departure = Console.askNotEmptyString(
			"Inserisci la stazione di PARTENZA: ", 
			"Errore: la stazione non può essere vuota."
		);

		// Creazione dell'oggetto Station per la partenza
		// Questo oggetto incapsulerà la logica relativa alla stazione
		// (ad esempio, la validazione del nome e la gestione delle distanze)
		Station departureStation = new Station(departure);

		// Richiesta della stazione di arrivo con validazione aggiuntiva
		// IMPORTANTE: dobbiamo assicurarci che la stazione di arrivo sia diversa da quella di partenza
		String arrival;
		do
		{
			// Chiediamo la stazione di arrivo
			arrival = Console.askNotEmptyString(
				"Inserisci la stazione di ARRIVO: ", 
				"Errore: la stazione non può essere vuota."
			);

			// Controllo logico: partenza e arrivo devono essere diverse
			// equalsIgnoreCase permette confronto case-insensitive (Milano = milano = MILANO)
			if (arrival.equalsIgnoreCase(departure))
				Console.print("Errore: la stazione di arrivo deve essere diversa da quella di partenza.");
				
		} while (arrival.equalsIgnoreCase(departure)); // Continua a chiedere finché le stazioni sono uguali

		// Creazione dell'oggetto Station per l'arrivo
		Station arrivalStation = new Station(arrival);

		// ===== FASE 3: RACCOLTA PREFERENZE DI VIAGGIO =====
		
		// Richiesta della classe di viaggio
		// 1 = Prima Classe (più costosa: 0.20 €/km)
		// 2 = Seconda Classe (economica: 0.15 €/km)
		// Mostriamo un mini-menu per rendere chiara la scelta all'utente
		int level = Console.readIntBetween(
			"\nScegli la classe:\n1 - Prima Classe\n2 - Seconda Classe\nInserisci scelta: ", 
			"Errore: inserisci 1 per Prima Classe o 2 per Seconda Classe.", 
			1, 
			2
		);

		// ===== FASE 4: RACCOLTA DETTAGLI TEMPORALI =====
		
		// Richiesta della data del viaggio
		// Formato atteso: GG/MM/AAAA (es. 15/12/2025)
		// NOTA: in questa versione non validiamo il formato della data
		// Una versione migliorata potrebbe usare LocalDate per validazione robusta
		String date = Console.askNotEmptyString(
			"\nInserisci la data del viaggio (formato: GG/MM/AAAA): ", 
			"Errore: la data non può essere vuota."
		);

		// Richiesta dell'orario del viaggio
		// Formato atteso: HH:MM (es. 14:30)
		// Anche qui, una versione più robusta potrebbe validare il formato orario
		String hour = Console.askNotEmptyString(
			"Inserisci l'orario del viaggio (formato: HH:MM): ", 
			"Errore: l'orario non può essere vuoto."
		);

		// ===== FASE 5: RACCOLTA INFORMAZIONI SU SCONTI =====
		
		// Richiesta possesso della Carta Verde
		// La Carta Verde offre uno sconto del 20% sul prezzo finale
		// readStringBetween accetta multiple varianti della risposta (si/SI/Si/no/NO/No)
		// per rendere l'input più user-friendly
		String greenCardChoice = Console.readStringBetween(
			"\nPossiedi la carta verde? (si/no): ", 
			"Errore: inserisci 'si' o 'no'.", 
			"si", "no", "SI", "NO", "Si", "No"
		);
		
		// Conversione della risposta testuale in booleano
		// equalsIgnoreCase garantisce che "si", "SI", "Si" siano tutti validi
		boolean greenCard = greenCardChoice.equalsIgnoreCase("si");

		// ===== FASE 6: CREAZIONE DEL BIGLIETTO =====
		
		// Creazione dell'oggetto Ticket con tutti i dati raccolti
		// NOTA IMPORTANTE: passiamo 0 per distance e 0.0 per price
		// Questi valori verranno automaticamente calcolati dal costruttore di Ticket
		// in base a:
		// - distance: calcolata dalla classe Station in base alle stazioni di partenza/arrivo
		// - price: calcolata in base a distanza, classe (level), età e possesso carta verde
		Ticket ticket = new Ticket(
			fullName,                      // Nome del passeggero
			age,                           // Età (importante per sconti over 75)
			departureStation.getName(),    // Nome stazione di partenza (normalizzato)
			arrivalStation.getName(),      // Nome stazione di arrivo (normalizzato)
			level,                         // Classe di viaggio (1 o 2)
			date,                          // Data del viaggio
			hour,                          // Orario del viaggio
			greenCard,                     // Possesso carta verde (true/false)
			0,                             // Distance - VERRÀ CALCOLATO dal costruttore
			0.0                            // Price - VERRÀ CALCOLATO dal costruttore
		);

		// Conferma visiva all'utente che il biglietto è stato creato con successo
		Console.print("\n=== BIGLIETTO CREATO CON SUCCESSO ===\n");
		
		// Restituiamo l'oggetto Ticket completamente inizializzato al chiamante
		// (tipicamente il metodo buyTicket() nella classe Main)
		return ticket;
	}
}