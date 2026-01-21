package com.generation.cp.controller;

import com.generation.cp.context.Context;
import com.generation.cp.model.repository.EventRepository;
import com.generation.library.Console;

/**
 * Classe principale per l'applicazione di gestione eventi Carroponte.
 *
 * Fornisce un'interfaccia a linea di comando per interagire con
 * il repository degli eventi (visualizzazione e ordinamento).
 */
public class Main
{

	static EventRepository eventRepo = Context.getDependency(EventRepository.class);


	/**
	 * Entry point dell'applicazione.
	 *
	 * @param args Argomenti da linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * RIFLESSIONE ARCHITETTURALE - Dependency Injection + Command Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Context (IoC) → Main (Controller) → EventRepository (Model)
		 *
		 * Questa applicazione dimostra diversi pattern architetturali:
		 *
		 * 1. DEPENDENCY INJECTION:
		 *    → eventRepo viene ottenuto da Context, non creato con 'new'
		 *    → Main dipende dall'interfaccia EventRepository, non dall'implementazione
		 *    → Cambiare repository richiede solo modifica in Context
		 *
		 * 2. COMMAND PATTERN (semplificato):
		 *    → Ogni stringa comando ("findall", "setorder") rappresenta un'azione
		 *    → Lo switch associa comandi a comportamenti
		 *    → Facile estendere con nuovi comandi
		 *
		 * 3. MVC (Model-View-Controller) semplificato:
		 *    → Model: EventRepository gestisce i dati
		 *    → View: Console fornisce I/O
		 *    → Controller: Main coordina tra Model e View
		 *
		 * Vantaggi dell'architettura:
		 * → Loose Coupling: Main non conosce SQLEventRepository
		 * → Testabilità: eventRepo può essere sostituito con un mock
		 * → Manutenibilità: Cambiare database non richiede modifiche a Main
		 * → Estensibilità: Nuovi comandi possono essere aggiunti facilmente
		 *
		 * Principi SOLID applicati:
		 * → Dependency Inversion: Main dipende da abstrazioni (EventRepository)
		 * → Open/Closed: Aperto a nuovi comandi, chiuso a modifiche esistenti
		 * → Single Responsibility: Main si occupa solo di coordinare, non di persistenza
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */

		String cmd;

		do
		{
			Console.print("Inserire comando");
			cmd = Console.readString();

			switch(cmd)
			{
				case "setorder":
					Console.print("Inserire ordine (title, price, date):");
					eventRepo.setOrder(Console.readString());
				break;

				case "findall":
					Console.print(eventRepo.findAll());
				break;

				case "help":
					Console.print("Comandi disponibili:");
					Console.print("- setorder: Imposta criterio di ordinamento");
					Console.print("- findall: Visualizza tutti gli eventi");
					Console.print("- QUIT: Termina l'applicazione");
				break;

				default:
					if(!cmd.equals("QUIT"))
						Console.print("Comando non riconosciuto. Digita 'help' per assistenza.");
			}

		} while(!cmd.equals("QUIT"));

		Console.print("Arrivederci!");
	}

}
