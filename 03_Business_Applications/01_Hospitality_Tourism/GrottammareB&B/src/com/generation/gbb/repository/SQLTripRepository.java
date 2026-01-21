package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Trip;
import com.generation.gbb.repository.interfaces.TripRepository;

/**
 * Implementazione SQL del repository per la gestione dei viaggi. Utilizza JDBC per l'accesso
 * ai dati e implementa il pattern Repository.
 */
public class SQLTripRepository implements TripRepository
{
	Connection connection = ConnectionFactory.make();

	/**
	 * Verifica se la tabella trip esiste nel database.
	 */
	private void checkTable()
	{
		try
		{
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getTables(null, null, "trip", null);

			if (!rs.next())
			{
				throw new RuntimeException("Tabella 'trip' non trovata. Eseguire comando 35 (CREATETABLETRIP)");
			}

			rs.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException("Errore verifica tabella trip");
		}
	}

	/**
	 * Recupera tutti i viaggi dal database.
	 * 
	 * @return lista contenente tutti i viaggi presenti nel database
	 */
	@Override
	public List<Trip> findAll()
	{
		checkTable();

		try
		{
			String				sqlString				= "SELECT * FROM Trip";
			PreparedStatement	readCmdFromsqlString	= connection.prepareStatement(sqlString);
			List<Trip>			result					= new ArrayList<>();
			ResultSet			tripRows				= readCmdFromsqlString.executeQuery();

			while (tripRows.next())
			{
				Trip intoTripObject = new Trip();
				intoTripObject.setId(tripRows.getInt("id"));
				intoTripObject.setCity(tripRows.getString("city"));
				intoTripObject.setDate(LocalDate.parse(tripRows.getString("date")));
				intoTripObject.setReview(tripRows.getString("review"));
				intoTripObject.setScore(tripRows.getInt("score"));

				result.add(intoTripObject);
			}

			readCmdFromsqlString.close();
			tripRows.close();
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading trips");
		}
	}

	/**
	 * Cerca un viaggio specifico tramite identificativo.
	 * 
	 * @param id l'identificativo univoco del viaggio
	 * @return l'oggetto Trip se trovato, null altrimenti
	 */
	@Override
	public Trip findById(int id)
	{
		checkTable();

		try
		{
			String				sqlString				= "SELECT * FROM Trip WHERE id = ?";
			PreparedStatement	readCmdFromSQLString	= connection.prepareStatement(sqlString);
			readCmdFromSQLString.setInt(1, id);

			ResultSet	tripRow	= readCmdFromSQLString.executeQuery();
			Trip		result	= null;

			if (tripRow.next())
			{
				result = new Trip();
				result.setId(tripRow.getInt("id"));
				result.setCity(tripRow.getString("city"));
				result.setDate(LocalDate.parse(tripRow.getString("date")));
				result.setReview(tripRow.getString("review"));
				result.setScore(tripRow.getInt("score"));
			}

			readCmdFromSQLString.close();
			tripRow.close();

			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading trip by id");
		}
	}

	/**
	 * Cerca viaggi per città (ricerca case-insensitive).
	 * 
	 * @param city la città da cercare
	 * @return lista di viaggi nella città specificata
	 */
	@Override
	public List<Trip> findByCity(String city)
	{
		checkTable();

		if (city == null || city.trim().isEmpty())
		{
			throw new IllegalArgumentException("La città da cercare non può essere vuota");
		}

		List<Trip> result = new ArrayList<>();
		for (Trip trip : findAll())
		{
			if (trip.getCity().toLowerCase().contains(city.toLowerCase()))
				result.add(trip);
		}
		return result;
	}

	/**
	 * Cerca viaggi in base all'anno.
	 * 
	 * @param year l'anno da cercare
	 * @return lista di viaggi nell'anno specificato
	 */
	@Override
	public List<Trip> findByYear(int year)
	{
		checkTable();

		int currentYear = LocalDate.now().getYear();

		if (year > currentYear)
		{
			throw new IllegalArgumentException("Impossibile cercare viaggi in un anno futuro");
		}

		if (year < 1900)
		{
			throw new IllegalArgumentException("Anno non valido: " + year);
		}

		List<Trip> result = new ArrayList<>();
		for (Trip trip : findAll())
		{
			if (trip.getDate().getYear() == year)
				result.add(trip);
		}
		return result;
	}

	/**
	 * Inserisce un nuovo viaggio nel database.
	 * 
	 * @param newTrip l'oggetto Trip da persistere
	 * @return l'oggetto Trip con l'id generato dal database
	 */
	@Override
	public Trip insert(Trip newTrip)
	{
		checkTable();

		if (newTrip.getDate().isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("Data viaggio non può essere nel futuro");
		}

		if (newTrip.getScore() < 0 || newTrip.getScore() > 10)
		{
			throw new IllegalArgumentException("Score deve essere tra 0 e 10");
		}

		if (!newTrip.isValid())
			throw new RuntimeException("Invalid trip");

		if (newTrip.getId() != 0)
			throw new RuntimeException("Cannot save a trip with a previous id");

		if (newTrip.getCity() == null || newTrip.getCity().trim().isEmpty())
		{
			throw new IllegalArgumentException("La città non può essere vuota");
		}

		if (newTrip.getReview() == null || newTrip.getReview().trim().isEmpty())
		{
			throw new IllegalArgumentException("La review non può essere vuota");
		}

		try
		{
			String				sqlString	= "INSERT INTO trip (city, date, review, score) VALUES (?,?,?,?)";
			PreparedStatement	insertCmd	= connection.prepareStatement(sqlString);
			insertCmd.setString(1, newTrip.getCity());
			insertCmd.setString(2, newTrip.getDate().toString());
			insertCmd.setString(3, newTrip.getReview());
			insertCmd.setInt(4, newTrip.getScore());

			insertCmd.execute();
			insertCmd.close();

			newTrip.setId(getNewId());
			return newTrip;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error saving trip");
		}
	}

	/**
	 * Recupera l'ultimo ID generato dal database.
	 */
	private int getNewId()
	{
		try
		{
			PreparedStatement	readCmd	= connection.prepareStatement("SELECT MAX(id) AS m FROM trip");
			ResultSet			rs		= readCmd.executeQuery();
			int					res		= rs.getInt("m");

			readCmd.close();
			rs.close();

			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading max id");
		}
	}

	/**
	 * Aggiorna i dati di un viaggio esistente.
	 * 
	 * @param newVersion l'oggetto Trip con i dati aggiornati
	 * @return l'oggetto Trip aggiornato
	 */
	@Override
	public Trip update(Trip newVersion)
	{
		checkTable();

		if (newVersion.getDate().isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("Data viaggio non può essere nel futuro");
		}

		if (!newVersion.isValid())
		{
			throw new IllegalArgumentException("Invalid trip data");
		}

		if (newVersion.getId() <= 0)
		{
			throw new IllegalArgumentException("Cannot update a trip without a valid id");
		}

		Trip existingTrip = findById(newVersion.getId());
		if (existingTrip == null)
		{
			throw new IllegalArgumentException("Trip con ID " + newVersion.getId() + " non trovato");
		}

		try
		{
			String				sqlString	= "UPDATE trip SET city=?, date=?, review=?, score=? WHERE id=?";
			PreparedStatement	updateCmd	= connection.prepareStatement(sqlString);

			updateCmd.setString(1, newVersion.getCity());
			updateCmd.setString(2, newVersion.getDate().toString());
			updateCmd.setString(3, newVersion.getReview());
			updateCmd.setInt(4, newVersion.getScore());
			updateCmd.setInt(5, newVersion.getId());

			updateCmd.execute();
			updateCmd.close();
			return newVersion;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error updating trip");
		}
	}

	/**
	 * Elimina un viaggio dal database.
	 * 
	 * @param id l'identificativo del viaggio da eliminare
	 * @return true se l'eliminazione ha successo, false altrimenti
	 */
	@Override
	public boolean delete(int id)
	{
		checkTable();

		if (id <= 0)
		{
			throw new IllegalArgumentException("ID non valido: " + id);
		}

		Trip existingTrip = findById(id);
		if (existingTrip == null)
		{
			throw new IllegalArgumentException("Trip con ID " + id + " non trovato");
		}

		try
		{
			String				sqlString	= "DELETE from trip WHERE id = ?";
			PreparedStatement	deleteCmd	= connection.prepareStatement(sqlString);
			deleteCmd.setInt(1, id);
			deleteCmd.execute();
			deleteCmd.close();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Crea la tabella trip nel database se non esiste già.
	 */
	public void initTable()
	{
		try
		{
			String createTableSQL = "CREATE TABLE IF NOT EXISTS trip ("
									+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
									+ "city VARCHAR(100) NOT NULL, "
									+ "date DATE NOT NULL, "
									+ "review TEXT NOT NULL, "
									+ "score INTEGER NOT NULL CHECK (score >= 0 AND score <= 10))";

			PreparedStatement statement = connection.prepareStatement(createTableSQL);
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Errore nella creazione della tabella trip");
		}
	}
}
