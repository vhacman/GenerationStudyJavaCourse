package com.generation.gbb.DUMMY;

import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.repository.interfaces.GuestRepository;

/**
 * In-memory repository for guest management. Uses mockData list to simulate
 * persistence without database.
 */
public class DummyGuestRepository implements GuestRepository
{

	List<Guest> mockData = new ArrayList<>();

	/**
	 * Initializes repository with test data. Populates mockData with 13 fictional
	 * guests to simulate initial dataset.
	 */
	public DummyGuestRepository()
	{
		mockData.add(new Guest(1, "Carlo", "Rossi", "ABCDEF99C11K345L", "1999-02-08", "Via dei crisantesimi", "Livorno"));
		mockData.add(new Guest(2, "Giulia", "Bianchi", "BNCHGL95A41F205Z", "1995-01-01", "Piazza Garibaldi 12", "Livorno"));
		mockData.add(new Guest(3, "Marco", "Ferrari", "FRRMRC88M15H501U", "1988-08-15", "Via Roma 45", "Roma"));
		mockData.add(new Guest(4, "Francesca", "Rizzo", "RZZFNC92P50L219X", "1992-09-10", "Corso Vittorio Emanuele 110","Napoli"));
		mockData.add(new Guest(5, "Alessandro", "Esposito", "SPSLSN80T20G273V", "1980-12-20", "Via Toledo 5", "Palermo"));
		mockData.add(new Guest(6, "Valentina", "Moretti", "MRTVNT85H61I449A", "1985-06-21", "Via Dante 22", "Verona"));
		mockData.add(new Guest(7, "Lorenzo", "Galli", "GLLLNZ77S03A794K", "1977-11-03", "Viale Europa 89", "Bologna"));
		mockData.add(new Guest(8, "Elena", "Conti", "CNTLNE90L48F839O", "1990-07-08", "Via dei Mille 3", "Firenze"));
		mockData.add(new Guest(9, "Matteo", "Bruno", "BRNMTT82B14L219S", "1982-02-14", "Via Mazzini 15", "Bari"));
		mockData.add(new Guest(10, "Sofia", "Marini", "MRNSFO98E52H501N", "1998-05-12", "Via Appia Nuova 300", "Bari"));
		mockData.add(new Guest(11, "Riccardo", "Lombardi", "LMBRCR75R25F205P", "1975-10-25", "Via Lagrange 42", "Catania"));
		mockData.add(new Guest(12, "Chiara", "Barbieri", "BRBCHR93M60G273C", "1993-08-20", "Via Libertà 150", "Verona"));
		mockData.add(new Guest(13, "Gabriele", "Serra", "SRRGRL84A10D612E", "1984-01-10", "Via Indipendenza 8", "Messina"));
	}

	/**
	 * Retrieves all guests from repository.
	 *
	 * @return Complete list of guests in memory
	 */
	@Override
	public List<Guest> findAll()
	{
		return mockData;
	}

	/**
	 * Finds guest by unique identifier. Iterates through list until ID match is
	 * found.
	 *
	 * @param id Guest numeric identifier
	 * @return Found guest, null if not exists
	 */
	@Override
	public Guest findById(int id)
	{
		for (Guest g : mockData)
		{
			if (g.getId() == id)
				return g;
		}
		return null;
	}

	/**
	 * Finds guest by Tax Code. Case-sensitive Tax Code comparison.
	 *
	 * @param ssn Guest Tax Code
	 * @return Found guest, null if not exists or ssn null
	 */
	@Override
	public Guest findBySSN(String ssn)
	{
		if (ssn == null)
			return null;
		for (Guest g : mockData)
		{
			if (g.getSsn().equals(ssn))
				return g;
		}
		return null;
	}

	/**
	 * Finds guests whose surname contains specified string. Case-insensitive
	 * partial match search.
	 *
	 * @param part String to search in surname
	 * @return List of matching guests, empty list if no results
	 */
	@Override
	public List<Guest> findBySurnameContaining(String part)
	{
		List<Guest> result = new ArrayList<Guest>();
		if (part == null || part.isEmpty())
			return result;

		for (Guest g : mockData)
		{
			if (g.getLastName().toLowerCase().contains(part.toLowerCase()))
				result.add(g);
		}
		return result;
	}

	/**
	 * Finds guests residing in specific city. Case-insensitive exact match search.
	 *
	 * @param city City name to search
	 * @return List of guests from city, empty list if no results
	 */
	@Override
	public List<Guest> findByCity(String city)
	{
		List<Guest> result = new ArrayList<Guest>();
		if (city == null || city.isEmpty())
			return result;

		for (Guest g : mockData)
		{
			if (g.getCity().equalsIgnoreCase(city))
				result.add(g);
		}
		return result;
	}

	/**
	 * Inserts new guest into repository. Auto-generates incremental ID and
	 * validates data. Ensures uniqueness of both object equality and SSN business
	 * key.
	 *
	 * @param newGuest Guest to insert with complete data
	 * @return Inserted guest with assigned ID
	 * @throws RuntimeException if validation fails, guest is null, has previous ID,
	 *                          is duplicate, or has duplicate SSN
	 */
	@Override
	public Guest insert(Guest newGuest)
	{
		if (newGuest == null)
			throw new RuntimeException("Cannot insert null guest");
		if (!newGuest.isValid())
			throw new RuntimeException("Invalid guest data");
		if (newGuest.getId() != 0)
			throw new RuntimeException("Cannot save a guest with previous id");
		if (mockData.contains(newGuest))
			throw new RuntimeException("Duplicate guest");
		if (findBySSN(newGuest.getSsn()) != null)
			throw new RuntimeException("Duplicate SSN");
		newGuest.setId(newId());
		mockData.add(newGuest);
		return newGuest;
	}

	/**
	 * Computes a new id based on the existing ones. Reduces the List<Guest> to a
	 * single integer representing the first free id.
	 *
	 * @return Next available id (current maximum + 1)
	 */
	private int newId()
	{
		int max = 0;
		// Iterate over all guests and keep track of the highest id found
		for (Guest g : mockData)
			if (g.getId() >= max)
				max = g.getId();
		// The new id is the next integer after the current maximum
		return max + 1;
	}

	/**
	 * Updates an existing guest in the repository.
	 * Copies all mutable fields from the provided version into the stored one.
	 *
	 * @param newVersion Guest containing updated data; must have a valid existing id
	 * @return The updated guest instance
	 * @throws RuntimeException if guest does not exist or data is invalid
	 */
	@Override
	public Guest update(Guest newVersion)
	{
	    Guest oldVersion = findById(newVersion.getId());
	    if (oldVersion == null)
	        throw new RuntimeException("Cannot update non existing guest");
	    if (!newVersion.isValid())
	        throw new RuntimeException("Invalid guest");

	    oldVersion.setFirstName(newVersion.getFirstName());
	    oldVersion.setLastName(newVersion.getLastName());
	    oldVersion.setDob(newVersion.getDob());
	    oldVersion.setCity(newVersion.getCity());
	    oldVersion.setAddress(newVersion.getAddress());
	    oldVersion.setSsn(newVersion.getSsn());

	    return oldVersion;
		
	}

	/**
	 * Deletes a guest from the repository by ID.
	 * Performs lookup first, then removes if found.
	 * 
	 * @param id The unique identifier of the guest to delete
	 * @return true if guest was found and deleted, false if guest doesn't exist
	 */
	@Override
	public boolean delete(int id)
	{
		Guest guest = findById(id);
		if(guest == null)
			return false;
		mockData.remove(guest);
		return true;
	}

}
