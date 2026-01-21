package com.generation.gbb.controller;

import java.util.List;

import com.generation.gbb.model.entities.Trip;
import com.generation.gbb.view.EntityViewBasic;
import com.generation.gbb.view.ViewFactory;
import com.generation.gbb.repository.SQLTripRepository;
import com.generation.gbb.repository.interfaces.TripRepository;
import com.generation.library.Console;

/**
 * Service class for managing Trip operations.
 * Handles user interaction and business logic for trips.
 */
public class TripService
{
	private TripRepository tripRepo;

	public TripService(TripRepository tripRepo)
	{
		this.tripRepo = tripRepo;
	}

	/**
	 * Inserts a new trip by reading data from console.
	 * Validates data before saving.
	 */
	public void insertTripFromConsole()
	{
		Trip t = new Trip();
		try
		{
			Console.print("Inserire città:");
			t.setCity(Console.readString());
			Console.print("Inserire data viaggio (formato YYYY-MM-DD):");
			t.setDate(Console.readString());
			Console.print("Inserire recensione:");
			t.setReview(Console.readString());
			Console.print("Inserire punteggio (0-10):");
			t.setScore(Console.readInt());

			tripRepo.insert(t);
			Console.print("Viaggio inserito con successo!");
		}
		catch(RuntimeException e)
		{
			Console.print("Salvataggio non riuscito");
			Console.print(e.getMessage());
			for (String error : t.getErrors())
				Console.print(error);
		}
	}

	/**
	 * Deletes a trip by ID.
	 * Prompts for trip ID, displays details, and confirms deletion.
	 */
	public void deleteTrip()
	{
		Console.print("Inserire ID viaggio da eliminare:");
		int id = Console.readInt();
		Trip t = tripRepo.findById(id);

		if (t == null)
		{
			Console.print("Viaggio non trovato");
			return;
		}

		EntityViewBasic<Trip> view = ViewFactory.makeView("trip", "full", "txt");
		Console.print(view.render(t));
		Console.print("Si vuole cancellare questo viaggio? (S/N)");

		if (Console.readString().equalsIgnoreCase("S"))
		{
			boolean result = tripRepo.delete(id);
			Console.print(result ? "Viaggio cancellato" : "Viaggio non trovato");
		}
	}

	/**
	 * Finds and displays trips by city.
	 * Case-insensitive search.
	 */
	public void findTripsByCity()
	{
		Console.print("Inserire città da cercare:");
		String city = Console.readString().trim();
		List<Trip> found = tripRepo.findByCity(city);

		if (found.isEmpty())
			Console.print("Nessun viaggio trovato per la città '" + city + "'");
		else
		{
			Console.print("\n=== " + found.size() + " Viaggi Trovati per '" + city + "' ===");
			EntityViewBasic<Trip> view = ViewFactory.makeView("trip", "summary", "txt");
			Console.print(view.render(found));
		}
	}

	/**
	 * Finds and displays a trip by ID.
	 * @param format Formato output: "txt" o "html"
	 */
	public void findTripById(String format)
	{
		Console.print("Inserire ID viaggio:");
		int id = Console.readInt();
		Trip t = tripRepo.findById(id);

		if (t == null)
			Console.print("Viaggio non trovato");
		else
		{
			EntityViewBasic<Trip> view = ViewFactory.makeView("trip", "full", format);
			Console.print(view.render(t));
		}
	}

	/**
	 * Overload con formato TXT di default.
	 */
	public void findTripById()
	{
		findTripById("txt");
	}

	/**
	 * Displays all trips in the system.
	 * @param format Formato output: "txt" o "html"
	 */
	public void tripList(String format)
	{
		List<Trip> all = tripRepo.findAll();
		Console.print("\n=== Lista Viaggi (" + all.size() + ") ===");

		if (all.isEmpty())
			Console.print("Nessun viaggio presente");
		else
		{
			EntityViewBasic<Trip> view = ViewFactory.makeView("trip", "summary", format);
			Console.print(view.render(all));
		}
	}

	/**
	 * Overload con formato TXT di default.
	 */
	public void tripList()
	{
		tripList("txt");
	}

	/**
	 * Finds and displays trips by year.
	 */
	public void findTripsByYear()
	{
		Console.print("Inserire anno da cercare:");
		int year = Console.readInt();
		List<Trip> found = tripRepo.findByYear(year);

		if (found.isEmpty())
			Console.print("Nessun viaggio trovato per l'anno " + year);
		else
		{
			Console.print("\n=== " + found.size() + " Viaggi nell'anno " + year + " ===");
			EntityViewBasic<Trip> view = ViewFactory.makeView("trip", "summary", "txt");
			Console.print(view.render(found));
		}
	}

	/**
	 * Crea la tabella trip nel database.
	 * Delega l'operazione DDL al repository.
	 */
	public void createTable()
	{
		try
		{
			if (tripRepo instanceof SQLTripRepository)
			{
				((SQLTripRepository) tripRepo).initTable();
				Console.print("Tabella 'trip' creata con successo!");
			}
			else
			{
				Console.print("Creazione tabella disponibile solo con SQL repository");
			}
		}
		catch (RuntimeException e)
		{
			Console.print("Errore nella creazione della tabella trip");
			Console.print(e.getMessage());
		}
	}
}
