package com.generation.pokedex.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.generation.pokedex.contex.Context;
import com.generation.pokedex.model.entities.Pokemon;
import com.generation.pokedex.model.entities.PokemonType;

/**
 * Caricare e scrivere pokemon
 */
public class PokemonRepository 
{
	Connection connection = (Connection) Context.getDependency(Connection.class);

	
	public void insert(Pokemon p)
	{
		try
		{
			// COMANDO SQL
			PreparedStatement sqlCmd = 
				connection.prepareStatement("insert into pokemon(name, primarytypeid) values(?,?);");
			sqlCmd.setString(1, p.getName());
			sqlCmd.setInt(2, p.getPrimary().getId());
			sqlCmd.execute(); // ho scritto sul db.
			sqlCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Pokemon findById(int id)
	{
		PokemonTypeRepository pokemonTypeRepo = 
				(PokemonTypeRepository) Context.getDependency(PokemonTypeRepository.class);
		
		try
		{
			// comando di lettura
			String sql = "select * from pokemon where id = "+id;
			Statement readSQL = connection.createStatement();
			// riga o righe lette dal db
			ResultSet row = readSQL.executeQuery(sql); // eseguo la lettura ricavo una riga
			if(row.next()) // ho trovato qualcosa da leggere
			{
				// uso i dati della riga per creare un pokemontype
				Pokemon res = new Pokemon();
				res.setId(row.getInt("id"));
				res.setName(row.getString("name"));
				int primaryTypeId = row.getInt("primarytypeid");
				// quando salvo non salvo nel pokemon tutto il tipo
				// salvo solo l'id del tipo
				// quindi il tipo va ricaricato
				PokemonType primaryType = pokemonTypeRepo.findById(primaryTypeId);
				res.setPrimary(primaryType); // ho caricato il primary type
				// e l'ho collegato al pokemon
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

	public void insert(List<Pokemon> pokemons) 
	{
		for(Pokemon p:pokemons)
			insert(p); // richiamo l'insert del singolo
	}
	

}
