package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Room;

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
	 * Recupera tutte le stanze dal database.
	 * @return lista contenente tutte le stanze presenti nel database
	 * RIFERIMENTO: SQLGuestRepository.java:29-91
	 */
	@Override
	public List<Room> findAll()
	{
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
	 */
	@Override
	public List<Room> findByNameOrDescriptionContaining(String part)
	{
		
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
	 * RIFERIMENTO: SQLGuestRepository.java:196-211
	 */
	@Override
	public List<Room> findByPriceLesserThan(int budget)
	{
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
	 * RIFERIMENTO: SQLGuestRepository.java:220-266
	 */
	@Override
	public Room insert(Room newRoom)
	{
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
	 */
	@Override
	public Room update(Room newVersion)
	{
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
	 * RIFERIMENTO: SQLGuestRepository.java:354-382
	 */
	@Override
	public boolean delete(int id) 
	{
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


}
