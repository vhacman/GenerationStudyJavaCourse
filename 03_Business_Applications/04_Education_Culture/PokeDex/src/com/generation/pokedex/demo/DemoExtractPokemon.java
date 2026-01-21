package com.generation.pokedex.demo;

import java.io.FileNotFoundException;

import com.generation.library.Console;
import com.generation.pokedex.contex.Context;
import com.generation.pokedex.model.entities.Pokemon;
import com.generation.pokedex.model.etl.PokemonExtractorCSV;
import com.generation.pokedex.model.repository.PokemonRepository;

public class DemoExtractPokemon 
{

	public static void main(String[] args)
	{
		PokemonExtractorCSV extractor;

		PokemonRepository pokeRepo = (PokemonRepository)
				Context.getDependency(PokemonRepository.class);

		String continua;

		do
		{
			try
			{
				Console.print("Inserire nome file da importare");
				String filename = Console.readString();
				extractor = new PokemonExtractorCSV(filename);


				Console.print("File trovato");
				Console.print("Linee del file "+extractor.getRowCount());

				Console.print("Pokemon estraibili:");
				for(Pokemon p:extractor.getContent())
					Console.print(p);
				if(extractor.getConversionErrors().size()>0)
				{
					Console.print("Errori di conversione:");
					for(String error:extractor.getConversionErrors())
						Console.print(error);
				}

				Console.print("Procedere a importare "+extractor.getContent().size()+" pokemon?");
				if(Console.readString().equals("S"))
				{
					pokeRepo.insert(extractor.getContent());
					Console.print("Importati");
				}

			}
			catch(FileNotFoundException e)
			{
				Console.print("File non trovato");
			}

			Console.print("Vuoi estrarre altri Pokemon? (S/N)");
			continua = Console.readString();

		} while(continua.equalsIgnoreCase("S"));

		Console.print("Programma terminato");

	}

}
