package com.generation.sbbb.controller;

import java.sql.SQLException;
import java.util.List;

import com.generation.library.Console;
import com.generation.library.profiling.ProfilingMonitor;
import com.generation.sbbb.model.entities.Room;
import com.generation.sbbb.repository.RoomRepository;
import com.generation.sbbb.repository.RoomRepositoryFactory;

/**
 * Controller principale dell'applicazione San Benedetto B&B.
 * Gestisce il menu principale e coordina le operazioni CRUD sulle camere.
 */
public class Main
{

	static RoomRepository	roomRepo = RoomRepositoryFactory.make();

	/**
	 * Entry point dell'applicazione.
	 * @param args argomenti da linea di comando
	 */
	public static void main(String[] args)
	{
		/*
		 ******************************************
		 * Ciclo principale del menu dell'applicazione.
		 * Presenta le opzioni disponibili all'utente
		 * e gestisce i comandi fino al comando di uscita
		 ******************************************
		 */
		Console.print("Benvenuti a San Benedetto");
		String cmd;
		do
		{
			Console.print("Inserire comando");
			cmd = Console.readString();
			switch(cmd)
			{
				case "NEWROOM":
					newRoomFromConsole();
				break;
				case "VIEWROOMS":
					viewAllRooms();
				break;
				case "UPDATEROOM":
					updateRoomFromConsole();
				break;
				case "DELETEROOM":
					deleteRoomFromConsole();
				break;
				case "PROFILING":
					printProfiling();
				break;
				case "RESETPROFILING":
					ProfilingMonitor.init();
				break;
				case "Q":
					Console.print("Bye bye");
				break;
				default:
					Console.print("Non riconosciuto");
			}
		}while(!cmd.equals("Q"));
	}

	/**
	 * Stampa le statistiche di profiling.
	 */
	private static void printProfiling()
	{
		/*
		 ******************************************
		 * Mostra il numero di query eseguite e
		 * il numero totale di righe processate
		 * dal sistema di profiling
		 ******************************************
		 */
		Console.print("=== PROFILING STATISTICS ===");
		Console.print("Query eseguite: " + ProfilingMonitor.queryNumber);
		Console.print("Righe processate: " + ProfilingMonitor.rowsNumbers);
		Console.print("===========================");
	}

	/**
	 * Cancella una camera esistente.
	 */
	private static void deleteRoomFromConsole()
	{
		/*
		 ******************************************
		 * Richiede l'id della camera da cancellare,
		 * verifica che esista e procede con la
		 * cancellazione gestendo eventuali errori
		 ******************************************
		 */
		Console.print("Inserire id della camera da cancellare");
		int id = Console.readInt();

		Room room = roomRepo.findById(id);
		if(room == null)
		{
			Console.print("Camera non trovata");
			return;
		}

		Console.print("Sei sicuro di voler cancellare la camera: " + room.getName() + "? (S/N)");
		String conferma = Console.readString();

		if(conferma.equalsIgnoreCase("S"))
		{
			try
			{
				boolean success = roomRepo.delete(id);
				if(success)
					Console.print("Camera cancellata con successo");
				else
					Console.print("Errore durante la cancellazione");
			}
			catch(SQLException e)
			{
				Console.print("Errore di cancellazione");
				Console.print(e.getMessage());
			}
		}
		else
		{
			Console.print("Operazione annullata");
		}
	}

	/**
	 * Aggiorna una camera esistente.
	 */
	private static void updateRoomFromConsole()
	{
		/*
		 ******************************************
		 * Richiede l'id della camera da aggiornare,
		 * mostra i dati attuali e permette di
		 * modificarli salvando le modifiche nel repository
		 ******************************************
		 */
		Console.print("Inserire id della camera da modificare");
		int id = Console.readInt();

		Room room = roomRepo.findById(id);
		if(room == null)
		{
			Console.print("Camera non trovata");
			return;
		}

		Console.print("Dati attuali: " + room.toString());
		Console.print("Inserire nuovo nome (lasciare vuoto per non modificare)");
		String newName = Console.readString();
		if(!newName.isBlank())
			room.setName(newName);

		Console.print("Inserire nuova descrizione (lasciare vuoto per non modificare)");
		String newDesc = Console.readString();
		if(!newDesc.isBlank())
			room.setDescription(newDesc);

		Console.print("Inserire nuovo prezzo (0 per non modificare)");
		int newPrice = Console.readInt();
		if(newPrice > 0)
			room.setPrice(newPrice);

		try
		{
			roomRepo.update(room);
			Console.print("Camera aggiornata con successo");
		}
		catch(SQLException e)
		{
			Console.print("Errore di aggiornamento");
			Console.print(e.getMessage());
		}
	}

	/**
	 * Visualizza tutte le camere presenti.
	 */
	private static void viewAllRooms()
	{
		/*
		 ******************************************
		 * Recupera tutte le camere dal repository
		 * e le visualizza in formato tabellare
		 * con id, nome, descrizione e prezzo
		 ******************************************
		 */
		List<Room> rooms = roomRepo.findAll();

		if(rooms.isEmpty())
		{
			Console.print("Nessuna camera presente");
			return;
		}

		Console.print("=== LISTA CAMERE ===");
		Console.print(String.format("%-5s | %-20s | %-40s | %10s", "ID", "Nome", "Descrizione", "Prezzo"));
		Console.print("--------------------------------------------------------------------------------");

		for(Room room : rooms)
		{
			Console.print(String.format("%-5d | %-20s | %-40s | %10d €",
				room.getId(),
				room.getName(),
				room.getDescription(),
				room.getPrice()));
		}

		Console.print("===================");
		Console.print("Totale camere: " + rooms.size());
	}

	/**
	 * Crea una nuova camera da input console.
	 */
	private static void newRoomFromConsole()
	{
		/*
		 ******************************************
		 * Richiede all'utente i dati della nuova
		 * camera (nome, descrizione, prezzo) e
		 * tenta di salvarla nel repository gestendo
		 * eventuali errori di validazione
		 ******************************************
		 */
		Room r = new Room();
		Console.print("Inserire nome");
		r.setName(Console.readString());
		Console.print("Inserire descrizione");
		r.setDescription(Console.readString());
		Console.print("Inserire prezzo");
		r.setPrice(Console.readInt());

		try
		{
			roomRepo.insert(r);
			Console.print("Inserito con id "+r.getId());
		}
		catch(SQLException e)
		{
			Console.print("Errore di salvataggio");
			Console.print(e.getMessage());
		}
	}

}
