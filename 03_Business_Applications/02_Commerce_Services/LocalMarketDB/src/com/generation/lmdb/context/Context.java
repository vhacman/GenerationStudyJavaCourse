package com.generation.lmdb.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import com.generation.lmdb.model.repository.BatchRepository;
import com.generation.lmdb.model.repository.ProducerRepository;
import com.generation.lmdb.model.repository.ProductRepository;

/**

 * Context è un CONTENITORE CENTRALE per le dipendenze dell'applicazione.
 * Implementa il pattern DEPENDENCY INJECTION (iniezione delle dipendenze).
 *
 * PROBLEMA CHE RISOLVE:
 * Senza Context, ogni volta che creo un Repository dovrei:
 * 1. Creare una nuova Connection al database
 * 2. Questo spreca risorse (troppe connessioni aperte)
 * 3. Ogni Repository avrebbe la sua Connection separata
 *
 * CON Context:
 * - UNA SOLA Connection condivisa da tutti i Repository
 * - UNA SOLA istanza di ogni Repository
 * - Tutti prendono le dipendenze dallo stesso "magazzino centrale"
 *
 * PATTERN SINGLETON:
 * - "static List<Object> dependencies" → esiste UNA SOLA volta per tutta l'applicazione
 * - Non importa quante volte chiami Context.getDependency(), ottieni sempre LO STESSO oggetto
 *
 * VANTAGGI:
 * 1. Risparmio di memoria (oggetti condivisi)
 * 2. Connessione al database centralizzata
 * 3. Facile cambiare implementazione (basta modificare il blocco static)
 * 
 * Il fatto che la lista sia statica e che gli oggetti vengano 
 * creati una sola volta rende Context un esempio di Singleton.
 */

public class 	Context
{
	/**
	 * private → solo Context può accedere a questa lista
	 * static → appartiene alla CLASSE, non all'oggetto
	 *          esiste UNA SOLA copia condivisa da tutto il programma
	 *
	 * List<Object> → può contenere qualsiasi tipo di oggetto
	 * - Connection
	 * - ProductRepository
	 * - ProducerRepository
	 * - BatchRepository
	 *
	 * ArrayList → implementazione concreta di List
	 * È come un array dinamico che può crescere
	 */
	private static 	List<Object> dependencies = new ArrayList<Object>();

	/*
	 * BLOCCO STATIC (STATIC INITIALIZER)
	 *
	 * Questo blocco viene eseguito UNA SOLA VOLTA quando la classe Context
	 * viene caricata in memoria per la prima volta.
	 *
	 * QUANDO VIENE ESEGUITO:
	 * - La prima volta che si fa riferimento a Context (es: Context.getDependency(...))
	 * - PRIMA di qualsiasi altro codice
	 * - NON serve chiamare nessun metodo, si esegue AUTOMATICAMENTE
	 *
	 * COSA FA:
	 * 1. Carica il driver JDBC per SQLite
	 * 2. Crea la connessione al database
	 * 3. Crea le istanze dei Repository
	 * 4. Le aggiunge alla lista dependencies
	 */
	static
	{
		try
		{
			/**
			 * CARICAMENTO DRIVER JDBC
			 *
			 * Class.forName("org.sqlite.JDBC") → carica la classe del driver SQLite
			 * JDBC = Java Database Connectivity (libreria standard per database)
			 *
			 * Questo dice a Java: "usa la libreria SQLite per parlare col database"
			 * Se il driver non è presente → ClassNotFoundException
			 */
			Class.forName("org.sqlite.JDBC");

			/**
			 * CREAZIONE CONNESSIONE AL DATABASE
			 *
			 * DriverManager.getConnection("jdbc:sqlite:market.db")
			 *
			 * FORMATO URL: "jdbc:sqlite:market.db"
			 * - jdbc: → protocollo Java Database Connectivity
			 * - sqlite: → tipo di database (SQLite)
			 * - market.db → nome del file database
			 *
			 * Se market.db non esiste, SQLite lo CREA automaticamente
			 * Se esiste, si COLLEGA al database esistente
			 *
			 * Connection c → oggetto che rappresenta la connessione attiva al database
			 */
			                                           //libreria : tipo di database : nome del file
			Connection 	c = DriverManager.getConnection("jdbc:sqlite:market.db");

			/*
			 * POPOLAMENTO DELLA LISTA DEPENDENCIES
			 *
			 * .add() → aggiunge un oggetto alla lista
			 * L'ORDINE non importa, getDependency cerca per TIPO non per posizione
			 *
			 * IMPORTANTE: creiamo UNA SOLA istanza di ogni Repository
			 * Tutti i punti del programma useranno QUESTE istanze condivise
			 */
			dependencies.add(c);
			dependencies.add(new BatchRepository());
			dependencies.add(new ProductRepository());
			dependencies.add(new ProducerRepository());

		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 *
	 * Questo metodo cerca nella lista dependencies un oggetto del tipo richiesto.
	 *
	 * ESEMPIO DI UTILIZZO:
	 * Context.getDependency(ProductRepository.class)
	 * → cerca nella lista un oggetto di tipo ProductRepository
	 * → ritorna quell'oggetto
	 *
	 * COME FUNZIONA:
	 * 1. Riceve un Class (tipo richiesto, es: ProductRepository.class)
	 * 2. Scorre tutta la lista dependencies
	 * 3. Per ogni oggetto, controlla se è del tipo richiesto
	 * 4. Se trova un match, ritorna quell'oggetto
	 * 5. Se non trova nulla, lancia un'eccezione
	 */
	public static Object getDependency(Class dependencyNeeded)
	{
		/*
		 * FOR-EACH LOOP
		 *
		 * for(Object o : dependencies)
		 *
		 * SINTASSI: for(Tipo variabile : collezione)
		 * Significa: "per ogni Object o dentro la lista dependencies, fai..."
		 *
		 * È equivalente a:
		 * for(int i = 0; i < dependencies.size(); i++) {
		 *     Object o = dependencies.get(i);
		 *     ...
		 * }
		 *
		 */
		for(Object o:dependencies)
			if(dependencyNeeded.isAssignableFrom(o.getClass()))
				return o;
		throw new RuntimeException("Dipendenza insoddisfatta per "+dependencyNeeded);
	}


}
