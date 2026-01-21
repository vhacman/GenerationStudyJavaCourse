package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.cache.PartialCache;
import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.profiling.ProfilingMonitor;
import com.generation.gbb.repository.interfaces.GuestRepository;

/**
 * Implementazione SQL del repository per la gestione degli ospiti.
 * Utilizza JDBC per l'accesso ai dati e implementa il pattern Repository
 * per astrarre la logica di persistenza dal resto dell'applicazione.
 */
public class SQLGuestRepository implements GuestRepository
{
	Connection 				connection = ConnectionFactory.make();
	PartialCache<Guest> 	cache = new PartialCache<Guest>(1000);

	/**
	 * Verifica se la tabella guest esiste nel database.
	 */
	private void checkTable()
	{
		try
		{
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getTables(null, null, "guest", null);

			if (!rs.next())
			{
				throw new RuntimeException("Tabella 'guest' non trovata. Eseguire comando 32 (CREATETABLEGUEST)");
			}

			rs.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException("Errore verifica tabella guest");
		}
	}

	public SQLGuestRepository()
	{
	}

	/**
	 * Recupera tutti gli ospiti dal database.
	 *
	 * @return lista contenente tutti gli ospiti presenti nel database
	 */
	@Override
	public List<Guest> findAll()
	{
		//proposizione che è semore vera, va bene anche id >= 0
		return findWhere("1=1");
	}

	/**
	 * Cerca un ospite specifico tramite identificativo.
	 * Utilizza la cache parziale per ridurre le query al database.
	 *
	 * @param id l'identificativo univoco dell'ospite
	 * @return l'oggetto Guest se trovato, null altrimenti
	 */
	@Override
	public Guest findById(int id)
	{
		Guest cached = cache.findById(id);
		if (cached != null)
			return cached;

		List<Guest> results = findWhere("id = " + id);
		if (!results.isEmpty())
		{
			Guest found = results.get(0);
			cache.addElement(found);
			return found;
		}
		return null;
	}

	/**
	 * Cerca un ospite tramite codice fiscale.
	 * Aggiunge il risultato alla cache se trovato.
	 *
	 * @param ssn il codice fiscale dell'ospite
	 * @return l'oggetto Guest se trovato, null altrimenti
	 */
	@Override
	public Guest findBySSN(String ssn)
	{
		List<Guest> results = findWhere("ssn = '" + ssn + "'");
		if (!results.isEmpty())
		{
			Guest found = results.get(0);
			cache.addElement(found);
			return found;
		}
		return null;
	}

	/**
	 * Cerca ospiti il cui cognome contiene una determinata stringa.
	 * Aggiunge i risultati alla cache per ottimizzare ricerche successive.
	 *
	 * @param part la porzione di testo da cercare nel cognome
	 * @return lista di ospiti con cognome corrispondente
	 */
	@Override
	public List<Guest> findBySurnameContaining(String part)
	{
		List<Guest> matches = findWhere("lastName LIKE '%" + part + "%'");
		for(Guest g : matches)
			cache.addElement(g);
		return matches;
	}

	/**
	 * Cerca ospiti residenti in una determinata città.
	 * Aggiunge i risultati alla cache per ottimizzare ricerche successive.
	 *
	 * @param city il nome della città
	 * @return lista di ospiti residenti nella città specificata
	 */
	@Override
	public List<Guest> findByCity(String city)
	{
		List<Guest> matches = findWhere("city LIKE '%" + city + "%'");
		for(Guest g : matches)
			cache.addElement(g);
		return matches;
	}

	/**
	 * Converte una riga del ResultSet in un oggetto Guest.
	 *
	 * @param guestRow la riga del ResultSet contenente i dati del guest
	 * @return oggetto Guest popolato con i dati della riga
	 * @throws SQLException se si verifica un errore nell'accesso ai dati
	 */
	private Guest rowToGuest(ResultSet guestRow) throws SQLException
	{
		Guest result = new Guest();

		result.setId(guestRow.getInt("id"));
		result.setFirstName(guestRow.getString("firstName"));
		result.setLastName(guestRow.getString("lastName"));
		result.setDob(LocalDate.parse(guestRow.getString("dob")));
		result.setSsn(guestRow.getString("ssn"));
		result.setCity(guestRow.getString("city"));
		result.setAddress(guestRow.getString("address"));

		return result;
	}

	/**
	 * Metodo generico per recuperare ospiti con una condizione WHERE personalizzata.
	 *
	 * @param condition la condizione SQL da applicare (senza la parola chiave WHERE)
	 * @return lista di ospiti che soddisfano la condizione
	 */
	private List<Guest> findWhere(String condition)
	{
		checkTable();

		try
		{
			String 				sql 		= "SELECT * FROM guest WHERE " + condition;
			PreparedStatement 	readCmd 	= connection.prepareStatement(sql);
			List<Guest> 		res 		= new ArrayList<Guest>();
			ResultSet 			guestRows	= readCmd.executeQuery();
			ProfilingMonitor.queryNumber++;

			while(guestRows.next())
				res.add(rowToGuest(guestRows));

			ProfilingMonitor.rowsLoaded += res.size();
			readCmd.close();
			guestRows.close();
			return res;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading");
		}
	}

	/**
	 * Inserisce un nuovo ospite nel database.
	 *
	 * @param newGuest l'oggetto Guest da persistere
	 * @return l'oggetto Guest con l'id generato dal database
	 * @throws RuntimeException se l'ospite non è valido o ha già un id
	 */
	@Override
	public Guest insert(Guest newGuest)
	{
		checkTable();

		if(!newGuest.isValid())
			throw new RuntimeException("Invalid guest: " + newGuest.getErrors());

		if(newGuest.getId() != 0)
			throw new RuntimeException("Cannot save a guest with a previous id");

		try
		{
			String sql = "INSERT INTO guest (firstName, lastName, ssn, dob, city, address) VALUES (?,?,?,?,?,?)";

			PreparedStatement insertCmd = connection.prepareStatement(sql);
			insertCmd.setString(1, newGuest.getFirstName());
			insertCmd.setString(2, newGuest.getLastName());
			insertCmd.setString(3, newGuest.getSsn());
			insertCmd.setString(4, newGuest.getDob() + "");
			insertCmd.setString(5, newGuest.getCity());
			insertCmd.setString(6, newGuest.getAddress());
			insertCmd.execute();
			ProfilingMonitor.queryNumber++;
			ProfilingMonitor.rowsLoaded++;
			insertCmd.close();

			int res = getNewId();
			newGuest.setId(res);

			cache.addElement(newGuest);

			return newGuest;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error saving");
		}
	}

	/**
	 * Recupera l'ultimo ID generato dal database.
	 * Metodo di supporto per sincronizzare l'id dopo un inserimento.
	 *
	 * @return l'ID massimo presente nella tabella Guest
	 */
	private int getNewId()
	{
		try
		{
			PreparedStatement readCmd = connection.prepareStatement("SELECT MAX(id) AS m FROM guest");
			ResultSet rs = readCmd.executeQuery();
			ProfilingMonitor.queryNumber++;
			ProfilingMonitor.rowsLoaded++;
			int res = rs.getInt("m");
			readCmd.close();
			rs.close();
			return res;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading");
		}
	}

	/**
	 * Aggiorna i dati di un ospite esistente.
	 * Rimuove la vecchia versione dalla cache e aggiunge quella nuova.
	 *
	 * @param newVersion l'oggetto Guest con i dati aggiornati
	 * @return l'oggetto Guest aggiornato
	 */
	@Override
	public Guest update(Guest newVersion)
	{
		checkTable();

		if (!newVersion.isValid())
			throw new IllegalArgumentException("Invalid guest data: " + newVersion.getErrors());

		if (newVersion.getId() <= 0)
			throw new IllegalArgumentException("Cannot update a guest without a valid id");

		Guest oldVersion = findById(newVersion.getId());
		if (oldVersion == null)
			throw new IllegalArgumentException("Guest con ID " + newVersion.getId() + " non trovato nel database");

		try
		{
			String sql = "UPDATE guest SET firstName=?, lastName=?, ssn=?, dob=?, city=?, address=? WHERE id=?";

			PreparedStatement updateCmd = connection.prepareStatement(sql);
			updateCmd.setString(1, newVersion.getFirstName());
			updateCmd.setString(2, newVersion.getLastName());
			updateCmd.setString(3, newVersion.getSsn());
			updateCmd.setString(4, newVersion.getDob() + "");
			updateCmd.setString(5, newVersion.getCity());
			updateCmd.setString(6, newVersion.getAddress());
			updateCmd.setInt(7, newVersion.getId());
			updateCmd.execute();
			ProfilingMonitor.queryNumber++;
			ProfilingMonitor.rowsLoaded++;
			updateCmd.close();

			// Sincronizza la cache: rimuovi la vecchia versione SOLO se presente
			if(cache.contains(oldVersion))
			{
				cache.removeElement(oldVersion);
				cache.addElement(newVersion);
			}
			else
			{
				// Se non era in cache, aggiungila per ottimizzare accessi futuri
				cache.addElement(newVersion);
			}

			return newVersion;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error saving");
		}
	}

	/**
	 * Elimina un ospite dal database.
	 * Controlla prima la cache, poi il database.
	 * Rimuove l'elemento dalla cache se presente.
	 *
	 * @param id l'identificativo dell'ospite da eliminare
	 * @return true se l'eliminazione ha successo, false altrimenti
	 */
	@Override
	public boolean delete(int id)
	{
		checkTable();

		// Cerca prima nella cache o nel database
		Guest toDelete = findById(id);

		if(toDelete == null)
			throw new IllegalArgumentException("Guest con ID " + id + " non trovato nel database");

		try
		{
			PreparedStatement deleteCmd = connection.prepareStatement("DELETE FROM guest WHERE id = ?");
			deleteCmd.setInt(1, id);
			deleteCmd.execute();
			ProfilingMonitor.queryNumber++;
			ProfilingMonitor.rowsLoaded++;
			deleteCmd.close();

			// Rimuovi dalla cache SOLO se presente (evita operazioni inutili)
			if(cache.contains(toDelete))
				cache.removeElement(toDelete);

			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}

	/**
	 * Crea la tabella guest nel database se non esiste già.
	 * Utilizza la connessione esistente del repository.
	 */
	public void initTable()
	{
		try
		{
			String createTableSQL = "CREATE TABLE IF NOT EXISTS guest (" +
									"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
									"firstName VARCHAR(100) NOT NULL, " +
									"lastName VARCHAR(100) NOT NULL, " +
									"ssn VARCHAR(16) NOT NULL UNIQUE, " +
									"dob VARCHAR(10) NOT NULL, " +
									"address VARCHAR(200) NOT NULL, " +
									"city VARCHAR(100) NOT NULL)";

			PreparedStatement statement = connection.prepareStatement(createTableSQL);
			statement.executeUpdate();
			ProfilingMonitor.queryNumber++;
			ProfilingMonitor.rowsLoaded++;
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Errore nella creazione della tabella guest");
		}
	}
}
