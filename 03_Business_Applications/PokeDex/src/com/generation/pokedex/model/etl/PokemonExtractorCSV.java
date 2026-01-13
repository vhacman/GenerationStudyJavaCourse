package com.generation.pokedex.model.etl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.FileReader;
import com.generation.pokedex.contex.Context;
import com.generation.pokedex.model.entities.Pokemon;
import com.generation.pokedex.model.entities.PokemonType;
import com.generation.pokedex.model.repository.PokemonTypeRepository;

/**
 * Questa classe genera estrattori di Pokemon
 * da file csv
 * 
 * 
 */
public class PokemonExtractorCSV 
{
	PokemonTypeRepository typeRepo = (PokemonTypeRepository)
			Context.getDependency(PokemonTypeRepository.class);
	
	String separator=",";	// separatore del file
	String filename; 	// file da cui voglio caricare
	
	List<String> lines = new ArrayList<String>(); // linee del file
	
	/**
	 * Se il file manca io non lo gestirò... non farò try catch
	 * io comunicherò che il file non esiste, dando una eccezione
	 * @param s
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public PokemonExtractorCSV(String filename, String s) throws FileNotFoundException
	{
		this.separator = s;
		this.filename = filename;
		loadFile();
	}

	public PokemonExtractorCSV(String filename) throws FileNotFoundException
	{
		this.filename = filename;
		loadFile();
	}

	
	/**
	 * Cosa vuol dire throws? Io non gestisco la mancanza del file
	 * io la comunico a chi mi chiama
	 * @throws FileNotFoundException
	 */
	private void loadFile() throws FileNotFoundException
	{
		// questa parte è EXTRACT... estrarre le righe da un file
		// riga calda: potrebbe darmi eccezione e uccidere prima il metodo
		FileReader reader = new FileReader(filename);
		// finchè ho righe
		while(reader.hasNext())
			lines.add(reader.readString()); // leggo tutte le righe e le metto in una lista
		
		reader.close();
	}
	
	public int getRowCount()
	{
		return lines.size();
	}
	
	public List<String> getConversionErrors()
	{
		// prendo una linea per volta
		// la converto in un pokemon
		// la carico sul db
		// "per ogni linea nella lista di linee"
		//
		List<String> res = new ArrayList<String>();
		String line;
		for(int i=0;i<lines.size();i++)
		{
			line = lines.get(i); // linea i-esima
			try
			{
				lineToPokemon(line);
			}
			catch(RuntimeException e)
			{
				res.add("Errore di conversione per line "+line+ ":" +e.getMessage());
			}
		}
		return res;
	}

	
	public List<Pokemon> getContent()
	{
		// prendo una linea per volta
		// la converto in un pokemon
		// la carico sul db
		// "per ogni linea nella lista di linee"
		//
		List<Pokemon> res = new ArrayList<Pokemon>();
		for(int i=0;i<lines.size();i++)
		{
			String line = lines.get(i); // linea i-esima
			try
			{
				Pokemon newPokemon = lineToPokemon(line);
				res.add(newPokemon); // sono riuscito a convertire la linea
			}
			catch(RuntimeException e)
			{
				// non ci sono riuscito... skippo
			}
		}
		return res;
	}

	private Pokemon lineToPokemon(String line) 
	{
		// prendo la linea e la spezzo in base al separatore
		String[] parts = line.split(separator); //
		//Bulbasaur,Lucertola,4
		// parts[0] => Bulbasaur
		// parts[1] => Lucertola
		// parts[2] => 4
		
		Pokemon res = new Pokemon();
		res.setName(parts[0]);
		// scarto la description perché non la ho...
		// se parts[2] non è un intero darà eccezione e il metodo andrà in crash
		int primaryCategoryId = Integer.parseInt(parts[2]); 
		PokemonType type = typeRepo.findById(primaryCategoryId);
		if(type==null)
			throw new RuntimeException("Category not found");
			// genero una eccezione
		
		res.setPrimary(type);
		
		return res;
	}
		

	
	
	

}
