package com.generation.pokedex.contex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import com.generation.pokedex.model.repository.PokemonRepository;
import com.generation.pokedex.model.repository.PokemonTypeRepository;

/**
 * In un progetto serio se cominciate a creare oggetti
 * in ogni main, in ogni sottoprogramma, è finita
 * io definisco gli oggetti che mi servono
 * in più o meno tutto il programma
 * e li riunisco in un grosso cesto degli attrezzi
 * questo cesto viene detto Context
 * chi ha bisogno di qualcosa lo chiede a lui.
 * 
 * Un oggetto di cui ho bisogno viene detto DIPENDENZA (Dependency)
 * le altre parti del programma NON creano le proprie dipendenze
 * ma le chiedono a Context
 * di modo da non dover mai crearle localmente (e potenzialmente duplicarle)
 * di modo da centralizzare il processo di creazione e gestione.
 * 
 * Questa classe è un supermercato. Il resto del programma verrà qui a fare compere. 
 */
public class Context 
{

	// Per ora, a tutto il mio sistema serve UNA connessione al database
	// al mondo servirà una connessione, che creerò usando la libreria sqlite-jdbc
	
	private static List<Object> dependencies = new ArrayList<Object>();
	
	//blocco statico --> costruttore della classe, non dell oggetto, eseguito quando la classe è caricata in memoria
	//e viene utilizzato per eseguire inizializzazione delle variabili statiche. 
	//variabili staiche --> variabili della classe e non dell'oggetto. 
	static
	{
		// regalo del diavolo: che è sta roba? Si chiama blocco
		// di inizializzazione statica per le variabili di classe
		try
		{
			//connessione 
			Class.forName("org.sqlite.JDBC");
			                                           //libreria : tipo di database : nome del file
			Connection c = DriverManager.getConnection("jdbc:sqlite:pokedex.db");	
			//due tabelle. 
			//aggiunto connessione alla lista delle dipendenze --> adesso la lista di dipendenze contiene la connessione
			dependencies.add(c);
			dependencies.add(new PokemonTypeRepository());
			dependencies.add(new PokemonRepository());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static Object getDependency(Class dependencyNeeded)
	{
		// signor Context, ho bisogno di un oggetto di un certo tipo
		// cerchi nel suo archivio e veda se ha quello che mi serve
		
		for(Object o:dependencies)
			if(dependencyNeeded.isAssignableFrom(o.getClass()))
				return o;
			
		// non lo ho trovato, genero una eccezione. NON LA GESTISCO, la genero.
		throw new RuntimeException("Dipendenza insoddisfatta per "+dependencyNeeded);
	}
	
	
}
