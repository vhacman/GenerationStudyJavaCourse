package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Room;
import com.generation.gbb.repository.interfaces.RoomRepository;
import com.generation.library.Console;

/**
 * Implementazione SQL del repository per la gestione delle stanze.
 * Utilizza JDBC per l'accesso ai dati e implementa il pattern Repository.
 *
 * RIFERIMENTO TEMPLATE: SQLGuestRepository.java
 */
public class SQLRoomRepository implements RoomRepository
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
			ResultSet rs = meta.getTables(null, null, "room", null);

			if (!rs.next())
			{
				throw new RuntimeException("Tabella 'room' non trovata. Eseguire comando 33 (CREATETABLEROOM)");
			}

			rs.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException("Errore verifica tabella room");
		}
	}
	
	/**
	 * Recupera tutte le stanze dal database.
	 * @return lista contenente tutte le stanze presenti nel database
	 * RIFERIMENTO: SQLGuestRepository.java:29-91
	 */
	@Override
	public List<Room> findAll()
	{
		checkTable();
		try
		{
			String  			sqlString 				= "select * from room";
			PreparedStatement 	readCmdFromsqlString 	= connection.prepareStatement(sqlString);			
			List<Room>			result					= new ArrayList<>();	
			ResultSet			roomRows				= readCmdFromsqlString.executeQuery();
			while(roomRows.next())
			{
				Room intoRoomObject = new Room();
				intoRoomObject.setId(roomRows.getInt("id"));
				intoRoomObject.setName(roomRows.getString("name"));
				intoRoomObject.setDescription(roomRows.getString("description"));
				intoRoomObject.setSize(roomRows.getDouble("size"));
				intoRoomObject.setFloor(roomRows.getInt("floor"));
				intoRoomObject.setPrice(roomRows.getDouble("price"));
				
				//aggiungo nuovo oggetto creato alla lista da restituire.
				result.add(intoRoomObject);
			}
			//chiudo tutto quello aperto
			readCmdFromsqlString.close();
			roomRows.close();
			return result;			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading");
		}
	}

	/**
	 * Cerca una stanza specifica tramite identificativo.
	 * @param id l'identificativo univoco della stanza
	 * @return l'oggetto Room se trovato, null altrimenti
	 * RIFERIMENTO: SQLGuestRepository.java:100-142
	 */
	@Override
	public Room findById(int id)
	{	
		checkTable();

		try
	    {
	        // Query parametrizzata: il "?" è un placeholder sicuro
	        String              sqlString               = "select * from Room where id = ?";
	        
	        // PreparedStatement: compila la query e previene SQL Injection
	        PreparedStatement   readCmdFromSQLString    = connection.prepareStatement(sqlString);

	        // Binding: sostituisce il "?" con il valore reale
	        readCmdFromSQLString.setInt(1, id);

	        // Esecuzione: restituisce un cursore sui risultati
	        ResultSet           roomRow                 = readCmdFromSQLString.executeQuery();
	        Room                result                  = null;

	        // Mapping ResultSet → Entity
	        // next() sposta il cursore e ritorna true se esiste una riga
	        if(roomRow.next())
	        {
	            result = new Room();
	            result.setId(roomRow.getInt("id"));
	            result.setName(roomRow.getString("name"));
	            result.setDescription(roomRow.getString("description"));
	            result.setSize(roomRow.getDouble("size"));
	            result.setFloor(roomRow.getInt("floor"));
	            result.setPrice(roomRow.getDouble("price"));
	        }
	        
	        // Rilascio risorse JDBC (importante per evitare memory leak)
	        readCmdFromSQLString.close();
	        roomRow.close();
	        
	        return result;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error reading");
	    }
	}

	/**
	 * Cerca stanze il cui nome O descrizione contiene una determinata stringa.
	 * @param part la porzione di testo da cercare
	 * @return lista di stanze con nome o descrizione corrispondente
	 * @throws IllegalArgumentException se la stringa di ricerca è null o vuota
	 */
	@Override
	public List<Room> findByNameOrDescriptionContaining(String part)
	{
		checkTable();

		// VALIDAZIONE: stringa di ricerca non può essere null o vuota
		if (part == null || part.trim().isEmpty())
		{
			throw new IllegalArgumentException("La stringa di ricerca non può essere vuota");
		}

		List<Room> 			result = new ArrayList<>();
		for (Room room : findAll())
		{
			if(room.getName().contains(part) || room.getDescription().contains(part))
				result.add(room);
		}
		return result;
	}

	/**
	 * Cerca stanze con prezzo inferiore al budget specificato.
	 * @param budget il prezzo massimo
	 * @return lista di stanze con prezzo inferiore
	 * @throws IllegalArgumentException se il budget non è positivo
	 * RIFERIMENTO: SQLGuestRepository.java:196-211
	 */
	@Override
	public List<Room> findByPriceLesserThan(int budget)
	{
		checkTable();

		// VALIDAZIONE: budget deve essere positivo
		if (budget <= 0)
		{
			throw new IllegalArgumentException("Il budget deve essere maggiore di zero");
		}

		List<Room>			result = new ArrayList<>();
		for(Room room : findAll())
		{
			if(room.getPrice() < budget)
				result.add(room);
		}
		return result;
	}

	/**
	 * Inserisce una nuova stanza nel database.
	 * @param newRoom l'oggetto Room da persistere
	 * @return l'oggetto Room con l'id generato dal database
	 * @throws RuntimeException se la stanza non è valida o ha già un id
	 * @throws IllegalArgumentException se i campi obbligatori non sono validi
	 * RIFERIMENTO: SQLGuestRepository.java:220-266
	 */
	@Override
	public Room insert(Room newRoom)
	{
		checkTable();

		// VALIDAZIONE: nome non può essere vuoto
		if (newRoom.getName() == null || newRoom.getName().trim().isEmpty())
		{
			throw new IllegalArgumentException("Il nome della stanza non può essere vuoto");
		}

		// VALIDAZIONE: dimensione deve essere positiva
		if (newRoom.getSize() <= 0)
		{
			throw new IllegalArgumentException("La dimensione deve essere maggiore di zero");
		}

		// VALIDAZIONE: piano deve essere valido (0-6)
		if (newRoom.getFloor() < 0 || newRoom.getFloor() > 6)
		{
			throw new IllegalArgumentException("Il piano deve essere compreso tra 0 e 6");
		}

		// VALIDAZIONE: prezzo deve essere positivo
		if (newRoom.getPrice() <= 0)
		{
			throw new IllegalArgumentException("Il prezzo deve essere maggiore di zero");
		}

		if(!newRoom.isValid())
			throw new RuntimeException("Invalid room");
		if(newRoom.getId() != 0)
			throw new RuntimeException("Cannot save a room with a previous id");

		try
		{
			 String				sqlString         	= 
					 "INSERT INTO room (name, description, size, floor, price) VALUES (?,?,?,?,?)";
			 
			 PreparedStatement   insertCmd   		= connection.prepareStatement(sqlString);
			 insertCmd.setString(1, newRoom.getName());
			 insertCmd.setString(2, newRoom.getDescription());
			 insertCmd.setDouble(3, newRoom.getSize());
			 insertCmd.setInt(4, newRoom.getFloor());
			 insertCmd.setDouble(5, newRoom.getPrice());
			 
			 insertCmd.execute();
			 insertCmd.close();
			 
			 newRoom.setId(getNewId());
			 return newRoom;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error saving");
		}
	}

	/**
	 * Recupera l'ultimo ID generato dal database.
	 * Metodo PRIVATE di supporto per sincronizzare l'id dopo un inserimento.
	 * @return l'ID massimo presente nella tabella room
	 */
	private int getNewId()
	{
		try
	    {
	        // Recupera il valore massimo della colonna id
	        // "as m" crea un alias per leggere facilmente il risultato
	        PreparedStatement   readCmd = connection.prepareStatement("SELECT MAX(id) AS m FROM room");
	        ResultSet           rs      = readCmd.executeQuery();
	        
	        // Legge il valore dalla colonna con alias "m"
	        int                 res     = rs.getInt("m");
	        
	        // Rilascio risorse JDBC
	        readCmd.close();
	        rs.close();
	        
	        return res;
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error reading");
	    }
	}
	
	
	/**
	 * Aggiorna i dati di una stanza esistente.
	 * @param newVersion l'oggetto Room con i dati aggiornati
	 * @return l'oggetto Room aggiornato
	 * @throws IllegalArgumentException se la room non è valida o l'ID non esiste
	 */
	@Override
	public Room update(Room newVersion)
	{
		checkTable();

		// VALIDAZIONE: la room deve essere valida
		if (!newVersion.isValid())
		{
			throw new IllegalArgumentException("Invalid room data");
		}

		// VALIDAZIONE: deve avere un ID valido per l'update
		if (newVersion.getId() <= 0)
		{
			throw new IllegalArgumentException("Cannot update a room without a valid id");
		}

		// VALIDAZIONE: verifica che l'ID esista nel database
		Room existingRoom = findById(newVersion.getId());
		if (existingRoom == null)
		{
			throw new IllegalArgumentException("Room con ID " + newVersion.getId() + " non trovata nel database");
		}

		try
		{									//AGGIORNA tabella ROOM e SET ai campi per ex: 'name'='?' --> al campo name
																											//il valore del placeholder(vedi riga 234
			String sqlString = "UPDATE Room SET name=?, description=?, size=?, floor=?, price=? WHERE id=?";
			PreparedStatement 	updateCmd = connection.prepareStatement(sqlString);
			
			updateCmd.setString(1, newVersion.getName());
			updateCmd.setString(2, newVersion.getDescription());
			updateCmd.setDouble(3, newVersion.getSize());
			updateCmd.setInt(4, newVersion.getFloor());
			updateCmd.setDouble(5, newVersion.getPrice());
			updateCmd.setInt(6, newVersion.getId());
			
			updateCmd.execute();
			updateCmd.close();
			return newVersion;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error saving");
		}
	}

	/**
	 * Elimina una stanza dal database.
	 * @param id l'identificativo della stanza da eliminare
	 * @return true se l'eliminazione ha successo, false altrimenti
	 * @throws IllegalArgumentException se l'ID non è valido o non esiste
	 * RIFERIMENTO: SQLGuestRepository.java:354-382
	 */
	@Override
	public boolean delete(int id)
	{
		checkTable();

		// VALIDAZIONE: ID deve essere positivo
		if (id <= 0)
		{
			throw new IllegalArgumentException("ID non valido: " + id);
		}

		// VALIDAZIONE: verifica che l'ID esista
		Room existingRoom = findById(id);
		if (existingRoom == null)
		{
			throw new IllegalArgumentException("Room con ID " + id + " non trovata nel database");
		}

		try
		{
			String				sqlString = "DELETE from Room WHERE id = ?";

			PreparedStatement deleteCmd = connection.prepareStatement(sqlString);
			deleteCmd.setInt(1, id);
			deleteCmd.execute();
			deleteCmd.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * Crea la tabella room nel database se non esiste già.
	 * Utilizza la connessione esistente del repository.
	 */
	public void initTable()
	{
		try
		{
			String createTableSQL = "CREATE TABLE IF NOT EXISTS room (" +
									"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
									"name VARCHAR(100) NOT NULL, " +
									"description TEXT, " +
									"size REAL NOT NULL, " +
									"floor INTEGER NOT NULL, " +
									"price REAL NOT NULL)";

			PreparedStatement statement = connection.prepareStatement(createTableSQL);
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Errore nella creazione della tabella room");
		}
	}

}
