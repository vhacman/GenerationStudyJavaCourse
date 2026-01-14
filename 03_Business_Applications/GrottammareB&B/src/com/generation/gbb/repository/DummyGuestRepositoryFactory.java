package com.generation.gbb.repository;

import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.entities.Guest;

/**
 * Repository in-memory per la gestione degli ospiti.
 * Utilizza una lista mockData per simulare la persistenza senza database.
 */
public class DummyGuestRepositoryFactory implements GuestRepository
{

	List<Guest>	mockData = new ArrayList<>();


	/**
	 * Inizializza il repository con dati di test.
	 * Popola mockData con 13 ospiti fittizi per simulare un dataset iniziale.
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
	 * Recupera tutti gli ospiti dal repository.
	 *
	 * @return Lista completa degli ospiti in memoria
	 */
	@Override
	public List<Guest> findAll()
	{
		return mockData;
	}

	/**
	 * Cerca un ospite tramite identificativo univoco.
	 * Scorre la lista fino a trovare corrispondenza con l'ID.
	 *
	 * @param id Identificativo numerico dell'ospite
	 * @return Ospite trovato, null se non esiste
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
	 * Cerca un ospite tramite codice fiscale.
	 * Confronto case-sensitive del codice fiscale.
	 *
	 * @param ssn Codice fiscale dell'ospite
	 * @return Ospite trovato, null se non esiste o ssn nullo
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
	 * Cerca ospiti il cui cognome contiene la stringa specificata.
	 * Ricerca case-insensitive per match parziali.
	 *
	 * @param part Stringa da cercare nel cognome
	 * @return Lista di ospiti con cognome matching, lista vuota se nessun risultato
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
	 * Cerca ospiti residenti in una specifica città.
	 * Ricerca case-insensitive per match esatti.
	 *
	 * @param city Nome della città da cercare
	 * @return Lista di ospiti residenti nella città, lista vuota se nessun risultato
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
	 * Inserisce un nuovo ospite nel repository.
	 * Genera automaticamente l'ID incrementale e valida i dati.
	 *
	 * @param newGuest Ospite da inserire con dati completi
	 * @return Ospite inserito con ID assegnato, null se validazione fallisce
	 */
	@Override
	public Guest insert(Guest newGuest)
	{
		return null;
	}

	/**
	 * Aggiorna un ospite esistente nel repository.
	 * Sostituisce l'ospite con lo stesso ID con la nuova versione.
	 *
	 * @param newVersion Ospite aggiornato (deve avere ID esistente)
	 * @return Ospite aggiornato, null se non trovato o validazione fallisce
	 */
	@Override
	public Guest update(Guest newVersion)
	{
		return null;
	}

	/**
	 * Elimina un ospite dal repository.
	 * Rimuove l'ospite dalla lista se trovato.
	 *
	 * @param id Identificativo dell'ospite da eliminare
	 * @return true se eliminato con successo, false se non trovato
	 */
	@Override
	public boolean delete(int id)
	{
		return false;
	}

}
