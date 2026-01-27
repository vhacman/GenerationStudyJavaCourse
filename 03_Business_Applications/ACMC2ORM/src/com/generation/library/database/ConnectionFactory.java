package com.generation.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Ogni Factory ha un certo Product
 * Product è il tipo formale che devo produrre
 * La Factory produrrà un Product o sottotipo di Product
 * 
 * In questo caso Product = Connection
 */
public class ConnectionFactory 
{

	static Map<String, Connection> connections = new HashMap<String,Connection>();
	
	/**
	 * Qui dentro userò un'altra factory
	 * che deriva dalla mia libreria
	 * 1 - dbName = file a cui mi connetto
	 * 2 - gli errori non li gestisco li propago
	 * Tu mi hai chiesto di aprire un file
	 * se non lo trovo o ho un errore
	 * io lo propago
	 * @return
	 */
	public static Connection make(String dbName) throws Exception
	{
		
		if(connections.containsKey(dbName))
			return connections.get(dbName);
		// get(key)=> restituisce il valore
		
		// serve a vedere se abbiamo caricato la libreria giusta... va fatto prima di creare la connessione
		Class.forName("org.sqlite.JDBC");
		//										  jdbc=> libreria che stiamo usando
		//										  sqlite=> il tipo del nostro database
		//										  grottammare.db => il database che stiamo usando
		//						   getConnection è una factory a sua volta, sceglierà la connection adatta a noi
		Connection connection = DriverManager.getConnection("jdbc:sqlite:"+dbName);	
		
		// non avevo quella chiave, né la sua connessione
		// la inserisco nella mappa
		connections.put(dbName, connection);
		return connection;
	}
	
	
	
	
}
