package com.generation.pokedex.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.pokedex.contex.Context;
import com.generation.pokedex.model.entities.PokemonType;

public class PokemonTypeRepository 
{
	Connection connection = (Connection) Context.getDependency(Connection.class);
	
	/**
	 * insert vuol dire inserire un nuovo tipo
	 * operazione C: create
	 * @param type
	 */
	public void insert(PokemonType type)
	{
		// signor Context, ho bisogno di un oggetto di tipo Connection
		// Connection.class è letteralmente il tipo della connessione

		// devo preparare un comando sql da inviare al database
		// il database non parla Java, parla SQL e separa il concetto di insert
		// dal concetto di update
		try
		{
			// COMANDO SQL
			PreparedStatement sqlCmd = 
				connection.prepareStatement("insert into pokemontype(name, description) values(?,?);");
			sqlCmd.setString(1, type.getName());
			sqlCmd.setString(2, type.getDescription());
			sqlCmd.execute(); // ho scritto sul db.
			sqlCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void update(PokemonType type)
	{
		try
		{
			// COMANDO SQL
			String sql = "update pokemontype set name=?, description=? where id=?";
			PreparedStatement sqlCmd = 
				connection.prepareStatement(sql);
			sqlCmd.setString(1, type.getName());
			sqlCmd.setString(2, type.getDescription());
			sqlCmd.setInt(3, type.getId());
			sqlCmd.execute(); // ho scritto sul db.
			sqlCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Entra un id ed esce un pokemontype se lo trovo
	 * null se non lo trovo
	 * operazione di lettura: R
	 * @param id
	 * @return
	 */
	public PokemonType findById(int id)
	{
		try
		{
			// comando di lettura
			String sql = "select * from PokemonType where id = "+id;
			Statement readSQL = connection.createStatement();
			// riga o righe lette dal db
			ResultSet row = readSQL.executeQuery(sql); // eseguo la lettura ricavo una riga
			if(row.next()) // ho trovato qualcosa da leggere
			{
				// uso i dati della riga per creare un pokemontype
				PokemonType res = new PokemonType();
				res.setId(row.getInt("id"));
				res.setName(row.getString("name"));
				res.setDescription(row.getString("description"));
				readSQL.close();
				row.close();
				return res;
			}
			else
			{
				readSQL.close();
				row.close();
				return null;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
	/**
	 * ultimo comando, cancellazione: Delete
	 */
	public void delete(int id)
	{
		try
		{
			String sql = "delete from pokemontype where id="+id;
			Statement deleteCmd = connection.createStatement();
			deleteCmd.execute(sql);
			deleteCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
