package com.generation.cp.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import com.generation.cp.context.Context;
import com.generation.cp.model.entities.Event;
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
		 * Dependency Injection + Command Pattern
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
				case "1":
				case "setorder":
					Console.print("Inserire ordine (title, price, date):");
					eventRepo.setOrder(Console.readString());
				break;

				case "2":
				case "findall":
					Console.print(eventRepo.findAll());
				break;
				
				case "3":
				case "insert":
					insertEvent();
				break;
				
				case "4":
				case "updatepriceforEvent":
					updatePriceForEvent();
				break;

				case "5":
				case "deleteEvent":
					deleteEvent();
				break;

				case "help":
					Console.print("Comandi disponibili:");
					Console.print("- setorder: Imposta criterio di ordinamento");
					Console.print("- findall: Visualizza tutti gli eventi");
					Console.print("- insert: Inserire un nuovo evento");
					Console.print("- updatepriceforEvent: Aggiornare il prezzo di un evento");
					Console.print("- deleteEvent: Cancellare un evento");
					Console.print("- QUIT: Termina l'applicazione");
				break;

				default:
					if(!cmd.equals("QUIT"))
						Console.print("Comando non riconosciuto. Digita 'help' per assistenza.");
			}

		} while(!cmd.equals("QUIT"));

		Console.print("Arrivederci!");
	}


	/**
	 * Inserisce un nuovo evento nel repository.
	 * Richiede all'utente di inserire i dati dell'evento tramite console.
	 */
	private static void insertEvent()
	{
		try
		{
			Console.print("Inserire titolo:");
			String title = Console.readString();

			Console.print("Inserire descrizione:");
			String description = Console.readString();

			Console.print("Inserire data (formato: YYYY-MM-DD):");
			LocalDate date = LocalDate.parse(Console.readString());

			Console.print("Inserire prezzo:");
			int price = Console.readInt();

			// Creo l'evento con id temporaneo (0), verrà assegnato dal database
			Event newEvent = new Event(0, title, description, date, price);

			// Valido l'evento prima di inserirlo
			if(!newEvent.isValid())
			{
				Console.print("Errori di validazione:");
				Console.print(newEvent.getErrors());
				return;
			}

			// Inserisco nel repository
			Event inserted = eventRepo.insert(newEvent);
			Console.print("Evento inserito con successo!");
			Console.print(inserted);
		}
		catch(SQLException e)
		{
			Console.print("Errore durante l'inserimento: " + e.getMessage());
		}
		catch(Exception e)
		{
			Console.print("Errore: " + e.getMessage());
		}
	}


	/**
	 * Aggiorna il prezzo di un evento esistente.
	 * Richiede l'id dell'evento e il nuovo prezzo.
	 */
	private static void updatePriceForEvent()
	{
		try
		{
			Console.print("Inserire ID evento da aggiornare:");
			int id = Console.readInt();

			// Recupero l'evento esistente
			Event event = eventRepo.findById(id);

			if(event == null)
			{
				Console.print("Evento con ID " + id + " non trovato.");
				return;
			}

			Console.print("Evento trovato: " + event);
			Console.print("Inserire nuovo prezzo:");
			int newPrice = Console.readInt();

			// Aggiorno il prezzo
			event.setPrice(newPrice);

			// Valido l'evento
			if(!event.isValid())
			{
				Console.print("Errori di validazione:");
				Console.print(event.getErrors());
				return;
			}

			// Salvo l'aggiornamento
			Event updated = eventRepo.update(event);
			Console.print("Prezzo aggiornato con successo!");
			Console.print(updated);
		}
		catch(SQLException e)
		{
			Console.print("Errore durante l'aggiornamento: " + e.getMessage());
		}
		catch(Exception e)
		{
			Console.print("Errore: " + e.getMessage());
		}
	}


	/**
	 * Cancella un evento dal repository.
	 * Richiede l'id dell'evento da eliminare.
	 */
	private static void deleteEvent()
	{
		try
		{
			Console.print("Inserire ID evento da cancellare:");
			int id = Console.readInt();

			// Recupero l'evento per mostrarlo prima della cancellazione
			Event event = eventRepo.findById(id);

			if(event == null)
			{
				Console.print("Evento con ID " + id + " non trovato.");
				return;
			}

			Console.print("Evento da cancellare: " + event);
			Console.print("Confermare cancellazione? (SI/NO):");
			String confirm = Console.readString();

			if(!confirm.equalsIgnoreCase("SI"))
			{
				Console.print("Cancellazione annullata.");
				return;
			}

			// Cancello l'evento
			boolean success = eventRepo.delete(id);

			if(success)
				Console.print("Evento cancellato con successo!");
			else
				Console.print("Errore durante la cancellazione.");
		}
		catch(SQLException e)
		{
			Console.print("Errore durante la cancellazione: " + e.getMessage());
		}
		catch(Exception e)
		{
			Console.print("Errore: " + e.getMessage());
		}
	}

}
