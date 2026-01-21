package com.generation.pokedex.demo;

import com.generation.library.Console;
import com.generation.pokedex.contex.Context;
import com.generation.pokedex.model.entities.Pokemon;
import com.generation.pokedex.model.entities.PokemonType;
import com.generation.pokedex.model.repository.PokemonRepository;
import com.generation.pokedex.model.repository.PokemonTypeRepository;

public class DemoInserimentoPokemon 
{

	public static void main(String[] args) 
	{
		PokemonTypeRepository 	pokemonTypeRepo = (PokemonTypeRepository) Context.getDependency(PokemonTypeRepository.class);
		PokemonRepository 		pokemonRepo 	= (PokemonRepository) Context.getDependency(PokemonRepository.class);
		
		Pokemon res = new Pokemon();
		Console.print("Inserire nome nuovo pokemon");
		res.setName(Console.readString());
		Console.print("Inserire id tipologia primaria");
		int 			primaryTypeId 	= Console.readInt();
		// dall'id ricavo l'oggetto caricando dal database
		PokemonType 	primaryType 	= pokemonTypeRepo.findById(primaryTypeId);
		if(primaryType==null)
		{
			Console.print("Tipo primario non trovato");
			return;
		}
		res.setPrimary(primaryType); // dopo averla caricata la devo collegare		
		pokemonRepo.insert(res);		
		Console.print("Salvato con successo");	
	}
}
