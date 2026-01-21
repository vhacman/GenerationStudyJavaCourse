package com.generation.trains.service;

import com.generation.library.Console;
import com.generation.library.Template;

/**
 * Classe principale del sistema di biglietteria ferroviaria.
 * 
 * Questa classe rappresenta il punto di ingresso (entry point) dell'applicazione
 * e coordina tutte le funzionalità del sistema attraverso un menu testuale.
 * 
 * ARCHITETTURA:
 * - Utilizza un pattern Menu-Driven per l'interazione utente
 * - Delega le responsabilità specifiche ad altre classi (TicketInput, TicketPrinter)
 * - Mantiene la logica di presentazione separata dalla logica di business
 * 
 * FUNZIONALITÀ PRINCIPALI:
 * 1. Acquisto di biglietti ferroviari
 * 2. Visualizzazione informazioni sul sistema
 * 3. Gestione del ciclo di vita dell'applicazione
 */
public class Main
{
	
	/**
	 * Metodo principale (entry point) dell'applicazione.
	 * 
	 * FLUSSO DI ESECUZIONE:
	 * 1. Mostra il messaggio di benvenuto
	 * 2. Entra nel loop principale del menu
	 * 3. Continua finché l'utente non sceglie di uscire
	 * 
	 * Il ciclo while garantisce che l'applicazione rimanga attiva
	 * permettendo all'utente di eseguire multiple operazioni prima di uscire.
	 * 
	 * @param args argomenti da linea di comando (non utilizzati in questa applicazione)
	 */
	public static void main(String[] args)
	{
		// FASE INIZIALE: Presenta l'applicazione all'utente
		showWelcomeMessage();
		
		// VARIABILE DI CONTROLLO DEL LOOP PRINCIPALE
		// goOn = true: l'applicazione continua a girare
		// goOn = false: l'applicazione termina
		boolean goOn = true;
		while (goOn)
		{
			// STEP 1: Caricamento e visualizzazione del menu principale
			// Utilizziamo un template esterno per separare la presentazione dalla logica
						// Vantaggi: facile modificare il menu senza toccare il codice Java
			String	menu = Template.load("print/menuPrincipale.txt");
			Console.print(menu);
			
			int choice = Console.readIntBetween("\nInserisci la tua scelta: ","Errore: inserisci un numero tra 1 e 3.", 1, 3);
			
			switch (choice)
			{
				case 1:
					// Acquisto biglietto
					buyTicket();
					break;
					
				case 2:
					// Mostra informazioni
					showSystemInfo();
					break;
					
				case 3:
					String exitMessage = Template.load("print/exitMessage.txt");
					Console.print(exitMessage);
					goOn = false;
					break;
			}
		}
	}
	
	/**
	 * Visualizza un messaggio di benvenuto decorativo all'avvio del sistema.
	 * 
	 * Questo metodo migliora l'esperienza utente fornendo:
	 * - Un'intestazione visivamente accattivante
	 * - Identificazione chiara del sistema
	 * - Un messaggio di benvenuto cordiale
	 * 
	 */
	private static void showWelcomeMessage()
	{
		Console.print("\n╔════════════════════════════════════════╗");
		Console.print("║                                        ║");
		Console.print("║   SISTEMA DI BIGLIETTERIA FERROVIARIA  ║");
		Console.print("║                                        ║");
		Console.print("║        Benvenuto nel nostro sistema    ║");
		Console.print("║                                        ║");
		Console.print("╚════════════════════════════════════════╝\n");
	}
	
	/**
	 * Gestisce il processo di acquisto di un biglietto
	 */
	private static void buyTicket()
	{
		// FASE 1: RACCOLTA DATI DEL BIGLIETTO
		// Delega a TicketInput la responsabilità di:
		// - Interagire con l'utente
		// - Validare gli input
		// - Creare l'oggetto Ticket
		Ticket ticket = TicketInput.collectTicketData();
		
		
		// Conferma acquisto
		String conferma = Console.readStringBetween( "Confermi l'acquisto? (si/no): ", "Errore: inserisci 'si' o 'no'.", "si", "no", "SI", "NO", "Si", "No" );
		
		if (conferma.equalsIgnoreCase("si"))
		{
			Console.print("\n✓ Biglietto acquistato con successo!\n");
			
			// Creazione del TicketPrinter associato a questo biglietto specifico
			// Il printer sarà responsabile di tutte le operazioni di output
			TicketPrinter printer = new TicketPrinter(ticket);
			
			boolean continuaStampa = true;
			
			while (continuaStampa)
			{
				Console.print("\n=== OPZIONI BIGLIETTO ===");
				Console.print("1 - Visualizza biglietto a console");
				Console.print("2 - Salva biglietto in HTML");
				Console.print("3 - Torna al menu principale");
				
				int sceltaStampa = Console.readIntBetween( "\nInserisci la tua scelta: ", "Errore: inserisci un numero tra 1 e 3.", 1, 3);
				
				switch (sceltaStampa)
				{
					case 1:
						Console.print("\n");
						printer.printToConsole();
						break;
						
					case 2:
						printer.printToHTML();
						break;
						
					case 3:
						Console.print("\nRitorno al menu principale...");
						continuaStampa = false;
						break;
				}
			}
		}
		else
		{
			Console.print("\nAcquisto annullato.");
		}
	}
	
	/**
	 * Visualizza informazioni dettagliate sul sistema di biglietteria.
	 * 
	 * Questo metodo fornisce una guida di riferimento per l'utente con:
	 * - Elenco delle stazioni disponibili nel network
	 * - Tariffe per classe di viaggio
	 * - Politiche di sconto applicate
	 * - Distanze tra tutte le coppie di stazioni
	 * - Descrizione delle classi di viaggio disponibili
	 * 
	 * SCOPO:
	 * - Aiutare l'utente a prendere decisioni informate
	 * - Fornire trasparenza sui costi e le distanze
	 * - Ridurre confusione durante il processo di acquisto
	 * 
	 * NOTA: In un sistema reale, queste informazioni potrebbero essere
	 * caricate dinamicamente da un database invece di essere hardcoded.
	 */
	private static void showSystemInfo()
	{
		Console.print("\n╔════════════════════════════════════════════════════╗");
		Console.print("║        INFORMAZIONI SUL SISTEMA                    ║");
		Console.print("╚════════════════════════════════════════════════════╝");
		Console.print("\nSTAZIONI DISPONIBILI:");
		Console.print("  • Milano");
		Console.print("  • Monza");
		Console.print("  • Osnago");
		Console.print("  • Lecco");
		
		Console.print("\nTARIFFE:");
		Console.print("  • Prima Classe: 0.20 €/km");
		Console.print("  • Seconda Classe: 0.15 €/km");
		
		Console.print("\nSCONTI:");
		Console.print("  • Carta Verde: -20% sul prezzo totale");
		Console.print("  • Over 75: VIAGGIO GRATUITO");
		
		Console.print("\nDISTANZE TRA LE STAZIONI:");
		Console.print("  Milano ↔ Monza:   15 km");
		Console.print("  Milano ↔ Osnago:  30 km");
		Console.print("  Milano ↔ Lecco:   45 km");
		Console.print("  Monza ↔ Osnago:   15 km");
		Console.print("  Monza ↔ Lecco:    30 km");
		Console.print("  Osnago ↔ Lecco:   15 km");
		
		Console.print("\nCLASSI DISPONIBILI:");
		Console.print("  • Prima Classe: maggiore comfort e servizi");
		Console.print("  • Seconda Classe: standard e conveniente");
		Console.print("");
	}
}