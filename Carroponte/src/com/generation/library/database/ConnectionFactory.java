package com.generation.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory per connessioni database SQLite con connection pooling.
 * Implementa pattern Singleton per riutilizzare connessioni esistenti.
 */
public class ConnectionFactory
{

	static Map<String, Connection>  connections = new HashMap<String, Connection>();


	/**
	 * Crea o restituisce una connessione esistente al database.
	 *
	 * @param dbName Nome del file database (es. "carroponte.db")
	 * @return Connessione al database
	 * @throws Exception Se il database non è accessibile
	 */
	public static Connection make(String dbName) throws Exception
	{
		/*
		 * Connection Pooling semplificato:
		 * → Se connessione già esiste, riutilizza (evita overhead)
		 * → Altrimenti crea nuova connessione e la registra
		 * → Pattern: Check-then-Act (non thread-safe in versione attuale)
		 */

		// Riutilizzo connessione esistente
		if(connections.containsKey(dbName))
			return connections.get(dbName);

		// Carica driver JDBC per SQLite
		Class.forName("org.sqlite.JDBC");

		// Factory Method: DriverManager sceglie l'implementazione corretta
		// jdbc:sqlite:dbName → SQLite connection
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

		// Registra per riutilizzo futuro
		connections.put(dbName, connection);

		return connection;
	}

}
