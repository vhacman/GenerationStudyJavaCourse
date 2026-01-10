package com.generation.pokedex.demo;

import com.generation.pokedex.contex.Context;
import com.generation.pokedex.model.entities.PokemonType;
import com.generation.pokedex.model.repository.PokemonTypeRepository;

public class DemoPokemonTypeRepository 
{

	public static void main(String[] args) 
	{
		PokemonTypeRepository pokemonTypeRepo = 
				(PokemonTypeRepository) Context.getDependency(PokemonTypeRepository.class);
		
		// demo di insert: create
		PokemonType type = new PokemonType();
		type.setName("Fire");
		type.setDescription("Roba che sputa fuoco");
		
		pokemonTypeRepo.insert(type); // lo salvo sul database
		
		// demo di caricamento (read) e modifica sul db (update)
		
		PokemonType loaded = pokemonTypeRepo.findById(1);
		
		System.out.println(loaded);
		
		loaded.setDescription("Electricity pokemon are the first you meet");
		
		pokemonTypeRepo.update(loaded);

		loaded = pokemonTypeRepo.findById(1);
		
		System.out.println(loaded);
		
	}

}
