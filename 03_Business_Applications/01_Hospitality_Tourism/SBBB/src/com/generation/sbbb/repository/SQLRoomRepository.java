package com.generation.sbbb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.generation.library.repository.FullCacheSQLEntityRepository;
import com.generation.sbbb.model.entities.Room;

/**
 * Implementazione SQL del repository delle camere.
 * Utilizza cache completa per ottimizzare le performance.
 * Estende FullCacheSQLEntityRepository e implementa RoomRepository.
 */
public class SQLRoomRepository extends FullCacheSQLEntityRepository<Room> implements RoomRepository
{

	public SQLRoomRepository(String table, Connection connection)
	{
		/*
		 ******************************************
		 * Inizializza il repository chiamando il
		 * costruttore della superclasse che carica
		 * automaticamente tutte le camere in cache
		 ******************************************
		 */
		super(table, connection);
	}

	/**
	 * Crea il comando SQL di update per una camera.
	 * @param newVersion la nuova versione della camera
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getUpdateCmd(Room newVersion) throws SQLException
	{
		/*
		 ******************************************
		 * Prepara la query UPDATE con i campi
		 * name, description e price della camera
		 ******************************************
		 */
		String sql = "update Room set name=?,description=?, price=? where id=?)";
		PreparedStatement updateCmd = connection.prepareStatement(sql);
		updateCmd.setString(1, newVersion.getName());
		updateCmd.setString(2, newVersion.getDescription());
		updateCmd.setInt(3, newVersion.getPrice());
		updateCmd.setInt(4, newVersion.getId());
		return updateCmd;
	}

	/**
	 * Crea il comando SQL di insert per una camera.
	 * @param newRoom la camera da inserire
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getInsertCmd(Room newRoom) throws SQLException
	{
		/*
		 ******************************************
		 * Prepara la query INSERT con i campi
		 * name, description e price della nuova camera
		 ******************************************
		 */
		String sql = "insert into Room(name,description,price) values (?,?,?)";
		PreparedStatement insertCmd = connection.prepareStatement(sql);
		insertCmd.setString(1, newRoom.getName());
		insertCmd.setString(2, newRoom.getDescription());
		insertCmd.setInt(3, newRoom.getPrice());
		return insertCmd;
	}

	/**
	 * Trasforma una riga del database in un oggetto Room.
	 * @param row il ResultSet posizionato sulla riga
	 * @return la camera creata dalla riga
	 * @throws SQLException in caso di errore
	 */
	@Override
	public Room rowToX(ResultSet row) throws SQLException
	{
		/*
		 ******************************************
		 * Estrae i dati dalla riga del ResultSet
		 * e crea un oggetto Room popolato con
		 * id, nome, descrizione e prezzo
		 ******************************************
		 */
		Room res = new Room();
		res.setId(row.getInt("id"));
		res.setName(row.getString("name"));
		res.setDescription(row.getString("description"));
		res.setPrice(row.getInt("price"));
		return res;
	}



}
