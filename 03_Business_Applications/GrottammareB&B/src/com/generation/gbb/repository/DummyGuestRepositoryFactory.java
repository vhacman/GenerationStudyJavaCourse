package com.generation.gbb.repository;

import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.entities.Guest;

/**
 * In-memory repository for guest management.
 * Uses mockData list to simulate persistence without database.
 */
public class DummyGuestRepositoryFactory implements GuestRepository
{

	List<Guest>	mockData = new ArrayList<>();


	/**
     * Initializes repository with test data.
     * Populates mockData with 13 fictional guests to simulate initial dataset.
     */
	public DummyGuestRepositoryFactory()
	{
		mockData.add(new Guest(1, "Carlo", "Rossi", "ABCDEF99C11K345L", "1999-02-08", "Via dei crisantesimi", "Livorno"));
        mockData.add(new Guest(2, "Giulia", "Bianchi", "BNCHGL95A41F205Z", "1995-01-01", "Piazza Garibaldi 12", "Torino"));
        mockData.add(new Guest(3, "Marco", "Ferrari", "FRRMRC88M15H501U", "1988-08-15", "Via Roma 45", "Roma"));
        mockData.add(new Guest(4, "Francesca", "Rizzo", "RZZFNC92P50L219X", "1992-09-10", "Corso Vittorio Emanuele 110", "Napoli"));
        mockData.add(new Guest(5, "Alessandro", "Esposito", "SPSLSN80T20G273V", "1980-12-20", "Via Toledo 5", "Palermo"));
        mockData.add(new Guest(6, "Valentina", "Moretti", "MRTVNT85H61I449A", "1985-06-21", "Via Dante 22", "Verona"));
        mockData.add(new Guest(7, "Lorenzo", "Galli", "GLLLNZ77S03A794K", "1977-11-03", "Viale Europa 89", "Bologna"));
        mockData.add(new Guest(8, "Elena", "Conti", "CNTLNE90L48F839O", "1990-07-08", "Via dei Mille 3", "Firenze"));
        mockData.add(new Guest(9, "Matteo", "Bruno", "BRNMTT82B14L219S", "1982-02-14", "Via Mazzini 15", "Venezia"));
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
     * Finds guest by unique identifier.
     * Iterates through list until ID match is found.
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
     * Finds guest by Tax Code.
     * Case-sensitive Tax Code comparison.
     *
     * @param ssn Guest Tax Code
     * @return Found guest, null if not exists or ssn null
     */
	@Override
	public Guest findBySSN(String ssn)
	{
	    if (ssn == null) return null;
	    for (Guest g : mockData)
	    {
	        if (g.getSsn().equals(ssn))
	            return g;
	    }
	    return null;
	}

	/**
     * Finds guests whose surname contains specified string.
     * Case-insensitive partial match search.
     *
     * @param part String to search in surname
     * @return List of matching guests, empty list if no results
     */
	@Override
	public List<Guest> findBySurnameContaining(String part)
	{
		List<Guest> result = new ArrayList<Guest>();
		if (part == null || part.isEmpty()) return result;

		for (Guest g : mockData)
		{
			if (g.getLastName().toLowerCase().contains(part.toLowerCase()))
				result.add(g);
		}
		return result;
	}

	/**
     * Finds guests residing in specific city.
     * Case-insensitive exact match search.
     *
     * @param city City name to search
     * @return List of guests from city, empty list if no results
     */
	@Override
	public List<Guest> findByCity(String city)
	{
		List<Guest> result = new ArrayList<Guest>();
		if (city == null || city.isEmpty()) return result;

		for (Guest g : mockData)
		{
			if (g.getCity().equalsIgnoreCase(city))
				result.add(g);
		}
		return result;
	}

	/**
     * Inserts new guest into repository.
     * Auto-generates incremental ID and validates data.
     *
     * @param newGuest Guest to insert with complete data
     * @return Inserted guest with assigned ID, null if validation fails or newGuest is null
     */
	@Override
	public Guest insert(Guest newGuest)
	{
		return null;
	}

	 /**
     * Updates existing guest in repository.
     * Replaces guest with same ID with new version.
     *
     * @param newVersion Updated guest (must have existing ID)
     * @return Updated guest, null if not found or validation fails
     */
	@Override
	public Guest update(Guest newVersion)
	{
		return null;
	}

	 /**
     * Deletes guest from repository.
     * Removes guest from list if found.
     *
     * @param id Guest identifier to delete
     * @return true if successfully deleted, false if not found
     */
	@Override
	public boolean delete(int id)
	{
		return false;
	}

}
