package com.generation.pokedex.demo;

import com.generation.library.Console;
import com.generation.pokedex.contex.Context;
import com.generation.pokedex.model.entities.Pokemon;
import com.generation.pokedex.model.repository.PokemonRepository;

public class DemoCaricamentoPokemon 
{

	public static void main(String[] args) 
	{
		PokemonRepository pokemonRepo = 
				(PokemonRepository) Context.getDependency(PokemonRepository.class);
		
		Pokemon rattoelettrico = pokemonRepo.findById(1);
		
		Console.print(rattoelettrico.getId());
		Console.print(rattoelettrico.getName());
		Console.print(rattoelettrico.getPrimary());
		// fare in modo che vengano salvate anche le tipologie secondarie
		// fare le viste del caso
		// permettere di modificare nome e descrizione dei pokemon
		// o dei tipi
		// mettere tutto assieme in un bel main
		
	}

}
