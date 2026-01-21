package com.generation.gbb.controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.generation.gbb.etl.GuestExtractor;
import com.generation.gbb.etl.GuestExtractorFactory;
import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.repository.SQLGuestRepository;
import com.generation.gbb.repository.interfaces.GuestRepository;
import com.generation.gbb.view.EntityViewBasic;
import com.generation.gbb.view.ViewFactory;
import com.generation.library.Console;

/**
 * Service layer per la gestione delle operazioni sugli ospiti.
 * Coordina interazione tra Controller, Repository e View.
 * 
 * Pattern → Service Layer (Livello di Servizio):
 * Controller → GuestService → GuestRepository
 * Separa logica di business dalla persistenza
 * Centralizza operazioni CRUD e validazione
 */
public class GuestService
{
	private GuestRepository guestRepo;

	/**
	 * Costruttore con dependency injection del repository.
	 * 
	 * @param guestRepo Repository per accesso ai dati degli ospiti
	 */
	public GuestService(GuestRepository guestRepo)
	{
		this.guestRepo = guestRepo;
	}

	/**
	 * Inserisce un nuovo ospite richiedendo dati da console.
	 * Valida i dati e salva nel repository.
	 */
	public void insertGuestFromConsole()
	{
		Guest g = new Guest();
		try
		{
			Console.print("Inserire nome:");
			g.setFirstName(Console.readString());
			Console.print("Inserire cognome:");
			g.setLastName(Console.readString());
			//fortificazione del codice - hardening 
			try
			{
				Console.print("Inserire data di nascita (YYYY-MM-DD):");
				g.setDob(LocalDate.parse(Console.readString()));
			}
			catch (DateTimeParseException e)
			{
				Console.print("data non valida");
				return;
			}
			Console.print("Inserire città:");
			g.setCity(Console.readString());
			Console.print("Inserire indirizzo:");
			g.setAddress(Console.readString());
			Console.print("Inserire codice fiscale:");
			g.setSsn(Console.readString());

			guestRepo.insert(g);
			Console.print("Guest inserito con successo!");
		}
		catch(RuntimeException e)
		{
			Console.print("Salvataggio non riuscito");
			Console.print(e.getMessage());
			for (String error : g.getErrors())
				Console.print(error);
		}
	}

	/**
	 * Importa ospiti da file esterno (CSV, JSON, etc).
	 * Utilizza Factory Pattern per selezionare l'extractor appropriato.
	 */
	public void importGuests()
	{
		Console.print("Inserire nome del file coi guest da importare");
		String filename = Console.readString();
		GuestExtractor extractor = GuestExtractorFactory.make(filename);

		if(extractor==null)
		{
			Console.print("Formato di file non supportato");
			return;
		}

		try
		{
			List<Guest> loaded = extractor.extractFrom(filename);
			int nSuccesses = 0;
			int nFailures = 0;

			for(Guest g : loaded)
			{
				try
				{
					Console.print("Provo a importare " + g.getFirstName() + " " + g.getLastName());
					Guest inserted = guestRepo.insert(g);
					Console.print("Salvato con successo con id " + inserted.getId());
					nSuccesses++;
				}
				catch(RuntimeException e)
				{
					nFailures++;
					Console.print("Inserimento fallito " + e.getMessage());
				}
			}

			Console.print("Numero successo " + nSuccesses);
			Console.print("Numero fallimenti " + nFailures);
		}
		catch(FileNotFoundException e)
		{
			Console.print("File non trovato");
		}
	}

	/**
	 * Mostra lista di tutti gli ospiti usando vista summary.
	 * @param format Formato output: "txt" o "html"
	 */
	public void guestList(String format)
	{
		List<Guest> all = guestRepo.findAll();
		EntityViewBasic<Guest> view = ViewFactory.makeView("guest", "summary", format);
		Console.print(view.render(all));
	}

	/**
	 * Overload con formato TXT di default.
	 */
	public void guestList()
	{
		guestList("txt");
	}

	/**
	 * Cerca e visualizza ospite per ID.
	 * @param format Formato output: "txt" o "html"
	 */
	public void findGuestById(String format)
	{
		try
		{
			Console.print("Inserire id ospite:");
			int id = Console.readInt();
			Guest g = guestRepo.findById(id);
	
			if (g == null)
				Console.print("Non trovato");
			else
			{
				EntityViewBasic<Guest> view = ViewFactory.makeView("guest", "full", format);
				Console.print(view.render(g));
			}
		}
		catch (NumberFormatException e)
		{
			Console.print("Id non valido. Inserire un valore numerico");
		}
	}

	/**
	 * 
	 * Overload con formato TXT di default.
	 */
	public void findGuestById()
	{
		findGuestById("txt");
	}

	/**
	 * Cerca ospite per Codice Fiscale.
	 */
	public void findGuestBySSN()
	{
		Console.print("Inserire Codice Fiscale:");
		String ssn = Console.readString().trim();
		Guest guest = guestRepo.findBySSN(ssn);

		if (guest == null)
			Console.print("Non trovato");
		else
		{
			EntityViewBasic<Guest> view = ViewFactory.makeView("guest", "full", "txt");
			Console.print(view.render(guest));
		}
	}

	/**
	 * Cerca ospiti il cui cognome contiene la stringa specificata.
	 */
	public void findGuestBySurnameContaining()
	{
		Console.print("Inserire parte del cognome:");
		String part = Console.readString().trim();
		List<Guest> found = guestRepo.findBySurnameContaining(part);

		if (found.isEmpty())
			Console.print("Nessun ospite trovato con cognome contenente '" + part + "'");
		else
		{
			Console.print("\n=== " + found.size() + " Ospiti Trovati ===");
			EntityViewBasic<Guest> view = ViewFactory.makeView("guest", "summary", "txt");
			Console.print(view.render(found));
		}
	}

	/**
	 * Cerca ospiti per città di residenza.
	 */
	public void findGuestByCity()
	{
		Console.print("Inserire nome città:");
		String city = Console.readString().trim();
		List<Guest> foundByCity = guestRepo.findByCity(city);

		if (foundByCity.isEmpty())
			Console.print("Nessun ospite trovato nella città '" + city + "'");
		else
		{
			Console.print("\n=== " + foundByCity.size() + " Ospiti Trovati ===");
			EntityViewBasic<Guest> view = ViewFactory.makeView("guest", "summary", "txt");
			Console.print(view.render(foundByCity));
		}
	}

	/**
	 * Aggiorna dati di un ospite esistente da console.
	 * Mostra dati attuali e permette modifica campo per campo.
	 */
	public void updateGuestFromConsole()
	{
		Console.print("Inserire ID ospite da modificare:");
		int id = Console.readInt();
		Guest g = guestRepo.findById(id);

		if (g == null)
		{
			Console.print("Ospite non trovato");
			return;
		}

		EntityViewBasic<Guest> view = ViewFactory.makeView("guest", "full", "txt");
		Console.print(view.render(g));

		try
		{
			Console.print("Nuovo nome (invio per mantenere):");
			String firstName = Console.readString();
			if (!firstName.trim().isEmpty()) g.setFirstName(firstName);

			Console.print("Nuovo cognome (invio per mantenere):");
			String lastName = Console.readString();
			if (!lastName.trim().isEmpty()) g.setLastName(lastName);

			Console.print("Nuova data di nascita YYYY-MM-DD (invio per mantenere):");
			String dob = Console.readString();
			if (!dob.trim().isEmpty()) g.setDob(LocalDate.parse(dob));

			Console.print("Nuova città (invio per mantenere):");
			String city = Console.readString();
			if (!city.trim().isEmpty()) g.setCity(city);

			Console.print("Nuovo indirizzo (invio per mantenere):");
			String address = Console.readString();
			if (!address.trim().isEmpty()) g.setAddress(address);

			Console.print("Nuovo codice fiscale (invio per mantenere):");
			String ssn = Console.readString();
			if (!ssn.trim().isEmpty()) g.setSsn(ssn);

			guestRepo.update(g);
			Console.print("Ospite aggiornato con successo!");
		}
		catch(RuntimeException e)
		{
			Console.print("Aggiornamento non riuscito");
			Console.print(e.getMessage());
			for (String error : g.getErrors())
				Console.print(error);
		}
	}

	/**
	 * Elimina un ospite previo conferma.
	 */
	public void deleteGuest()
	{
		Console.print("Inserire id dell'ospite da eliminare");
		int id = Console.readInt();
		Guest g = guestRepo.findById(id);

		if (g == null)
		{
			Console.print("Id non trovato");
			return;
		}

		EntityViewBasic<Guest> guestViewFull = ViewFactory.makeView("guest", "full", "txt");
		Console.print(guestViewFull.render(g));
		Console.print("Si vuole cancellare questo ospite?");

		if (Console.readString().equals("S"))
		{
			boolean result = guestRepo.delete(id);
			Console.print(result ? "Cancellato" : "Ospite non trovato");
		}
	}

	/**
	 * Stampa l'elenco univoco delle città degli ospiti.
	 * Utilizza Set per eliminare duplicati.
	 */
	public void printClientCities()
	{
		Set<String> cities = new HashSet<>();

		for (Guest guest : guestRepo.findAll())
		{
			if (!cities.contains(guest.getCity()))
				cities.add(guest.getCity());
		}

		Console.print(cities);
	}

	/**
	 * Crea la tabella trip nel database.
	 * Delega l'operazione DDL al repository.
	 */
	public void createTable()
	{
		try
		{
			if (guestRepo instanceof SQLGuestRepository)
			{
				((SQLGuestRepository) guestRepo).initTable();
				Console.print("Tabella 'guest' creata con successo!");
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
