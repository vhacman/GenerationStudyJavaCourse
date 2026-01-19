package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.cache.TotalCache;
import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Room;
import com.generation.gbb.profiling.ProfilingMonitor;
import com.generation.gbb.repository.interfaces.RoomRepository;

/**
 * Implementazione SQL del repository per la gestione delle stanze. Utilizza JDBC per l'accesso
 * ai dati e implementa il pattern Repository. RIFERIMENTO TEMPLATE: SQLGuestRepository.java
 */
public class SQLRoomRepository implements RoomRepository
{
	Connection				connection	= ConnectionFactory.make();
	//full caching.
	TotalCache<Room>		cache		= new TotalCache<Room>();
	
	public SQLRoomRepository()
	{
		List<Room> content = findAll();
		cache.setContent(content);
	}
	
	/**
	 * Verifica se la tabella room esiste nel database.
	 */
	private void checkTable()
	{
		try
		{
			DatabaseMetaData	meta	= connection.getMetaData();
			ResultSet			rs		= meta.getTables(null, null, "room", null);
			
			if (!rs.next()) 
				throw new RuntimeException("Tabella 'room' non trovata. Eseguire comando 33 (CREATETABLEROOM)");
			
			rs.close();
		} 
		catch (SQLException e)
		{
			throw new RuntimeException("Errore verifica tabella room");
		}
	}
	
	/**
	 * Recupera tutte le stanze dal database.
	 * 
	 * @return lista contenente tutte le stanze presenti nel database RIFERIMENTO:
	 *         SQLGuestRepository.java:29-91
	 */
	@Override
	public List<Room> findAll()
	{
		checkTable();

		/**
		 * La prima volta viene eseguito
		 * dalla seconda volta in poi potrebbe non essere
		 * eseguito ma mi caricherà il content della cache.
		 */
		if(cache.getContent() != null && !cache.getContent().isEmpty())
			return cache.getContent();
		
		try
		{
			String				sqlString				= "select * from room";
			PreparedStatement	readCmdFromsqlString	= connection.prepareStatement(sqlString);
			List<Room>			result					= new ArrayList<>();
			ResultSet			roomRows				= readCmdFromsqlString.executeQuery();

			ProfilingMonitor.rowsLoaded += result.size();
			ProfilingMonitor.queryNumber++;

			while (roomRows.next())
			{
				Room intoRoomObject = rowToRoom(roomRows);
				result.add(intoRoomObject);
			}

			readCmdFromsqlString.close();
			roomRows.close();

			return result;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading");
		}
	}
	
	@Override
	public Room findById(int id)
	{
		checkTable();
		return cache.findById(id);
	}
	
	/**
	 * Mappa una riga del ResultSet in un oggetto Room.
	 * 
	 * @param roomRow il ResultSet posizionato sulla riga corrente
	 * @return un oggetto Room popolato con i dati della riga
	 * @throws SQLException se si verifica un errore durante la lettura
	 */
	private Room rowToRoom(ResultSet roomRow) throws SQLException
	{
		Room result = new Room();
		
		result.setId(roomRow.getInt("id"));
		result.setName(roomRow.getString("name"));
		result.setDescription(roomRow.getString("description"));
		result.setSize(roomRow.getDouble("size"));
		result.setFloor(roomRow.getInt("floor"));
		result.setPrice(roomRow.getDouble("price"));
		
		return result;
	}
	
	/**
	 * Cerca stanze il cui nome O descrizione contiene una determinata stringa.
	 * 
	 * @param part la porzione di testo da cercare
	 * @return lista di stanze con nome o descrizione corrispondente
	 * @throws IllegalArgumentException se la stringa di ricerca è null o vuota
	 */
	@Override
	public List<Room> findByNameOrDescriptionContaining(String part)
	{
		checkTable();
		
		if (part == null || part.trim().isEmpty())
			throw new IllegalArgumentException("La stringa di ricerca non può essere vuota");
		
		List<Room> result = new ArrayList<>();
		
		for (Room room : findAll())
			if (room.getName().contains(part) || room.getDescription().contains(part))
				result.add(room);
		
		return result;
	}
	
	/**
	 * Cerca stanze con prezzo inferiore al budget specificato.
	 * 
	 * @param budget il prezzo massimo
	 * @return lista di stanze con prezzo inferiore
	 * @throws IllegalArgumentException se il budget non è positivo
	 */
	@Override
	public List<Room> findByPriceLesserThan(int budget)
	{
		checkTable();
		
		if (budget <= 0)
			throw new IllegalArgumentException("Il budget deve essere maggiore di zero");
		
		List<Room> result = new ArrayList<>();
		
		for (Room room : findAll())
			if (room.getPrice() < budget)
				result.add(room);
		
		return result;
	}
	
	/**
	 * Inserisce una nuova stanza nel database.
	 * 
	 * @param newRoom l'oggetto Room da persistere
	 * @return l'oggetto Room con l'id generato dal database
	 * @throws RuntimeException se la stanza non è valida o ha già un id
	 * @throws IllegalArgumentException se i campi obbligatori non sono validi
	 */
	@Override
	public Room insert(Room newRoom)
	{
		checkTable();
		
		//devo aggiornare la cache. 
		
		if (!newRoom.isValid())
			throw new RuntimeException("Invalid room");
		if (newRoom.getId() != 0)
			throw new RuntimeException("Cannot save a room with a previous id");
		try
		{
			String				sqlString	= "INSERT INTO room (name, description, size, floor, price) VALUES (?,?,?,?,?)";
			PreparedStatement	insertCmd	= connection.prepareStatement(sqlString);
			
			insertCmd.setString(1, newRoom.getName());
			insertCmd.setString(2, newRoom.getDescription());
			insertCmd.setDouble(3, newRoom.getSize());
			insertCmd.setInt(4, newRoom.getFloor());
			insertCmd.setDouble(5, newRoom.getPrice());
			insertCmd.execute();
			
			ProfilingMonitor.queryNumber++;
			insertCmd.close();
			
			newRoom.setId(getNewId());
			//aggiungo la stanza alla cache
			cache.getContent().add(newRoom);
			return newRoom;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error saving");
		}
	}
	
	/**
	 * Recupera l'ultimo ID generato dal database.
	 */
	private int getNewId()
	{
		try
		{
			PreparedStatement	readCmd	= connection.prepareStatement("SELECT MAX(id) AS m FROM room");
			ResultSet			rs		= readCmd.executeQuery();
			
			ProfilingMonitor.queryNumber++;
			
			int res = rs.getInt("m");	
			readCmd.close();
			rs.close();
			
			return res;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error reading");
		}
	}
	
	/**
	 * Aggiorna i dati di una stanza esistente.
	 */
	@Override
	public Room update(Room newVersion)
	{
		checkTable();
		
		if (!newVersion.isValid())
			throw new IllegalArgumentException("Invalid room data");
		
		if (newVersion.getId() <= 0)
			throw new IllegalArgumentException("Cannot update a room without a valid id");
		
		Room oldVersion = findById(newVersion.getId());
		
		if (oldVersion == null)
			throw new IllegalArgumentException("Room con ID " + newVersion.getId() + " non trovata nel database");
		
		try
		{
			String				sqlString	= "UPDATE Room SET name=?, description=?, size=?, floor=?, price=? WHERE id=?";
			PreparedStatement	updateCmd	= connection.prepareStatement(sqlString);
			
			updateCmd.setString(1, newVersion.getName());
			updateCmd.setString(2, newVersion.getDescription());
			updateCmd.setDouble(3, newVersion.getSize());
			updateCmd.setInt(4, newVersion.getFloor());
			updateCmd.setDouble(5, newVersion.getPrice());
			updateCmd.setInt(6, newVersion.getId());
			updateCmd.execute();
			
			ProfilingMonitor.queryNumber++;
			
			//rimuovo la vecchia version e 
			//aggiungo la nuova versione
			cache.getContent().remove(oldVersion);
			cache.getContent().add(newVersion);
			updateCmd.close();
			
			return newVersion;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error saving");
		}
	}
	
	/**
	 * Elimina una stanza dal database.
	 */
	@Override
	public boolean delete(int id)
	{
		checkTable();
		
		if (findById(id) == null)
			throw new IllegalArgumentException("Room con ID " + id + " non trovata nel database");
		
		try
		{
			Room old = findById(id);
			String				sqlString	= "DELETE from Room WHERE id = ?";
			PreparedStatement	deleteCmd	= connection.prepareStatement(sqlString);
			
			deleteCmd.setInt(1, id);
			deleteCmd.execute();
			
			ProfilingMonitor.queryNumber++;
			//stiamo sincronizzando la cache con il database
			cache.getContent().remove(old); //aggiorno la cache
			deleteCmd.close();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Crea la tabella room nel database se non esiste già.
	 */
	public void initTable()
	{
		try
		{
			String				createTableSQL	= "CREATE TABLE IF NOT EXISTS room ("
													+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
													+ "name VARCHAR(100) NOT NULL, "
													+ "description TEXT, "
													+ "size REAL NOT NULL, "
													+ "floor INTEGER NOT NULL, "
													+ "price REAL NOT NULL)";
			PreparedStatement	statement		= connection.prepareStatement(createTableSQL);
			
			statement.executeUpdate();
			
			ProfilingMonitor.queryNumber++;
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Errore nella creazione della tabella room");
		}
	}
}
