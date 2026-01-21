package com.generation.library.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.generation.library.Console;

/**
 * Factory per la creazione di connessioni al database.
 * Implementa il pattern Singleton per garantire una singola connessione.
 */
public class ConnectionFactory
{

	static Connection	connection = null;

	/**
	 * Crea o restituisce la connessione al database.
	 * @return l'istanza di Connection
	 */
	public static Connection make()
	{
		/*
		 ******************************************
		 * Implementa il pattern Singleton: crea
		 * la connessione solo se non esiste già.
		 * Carica il driver JDBC SQLite e stabilisce
		 * la connessione al database hotel.db
		 ******************************************
		 */
		if(connection==null)
		{
			try
			{
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			}
			catch(Exception e)
			{
				Console.print("Cannot create connection");
				e.printStackTrace();
				System.exit(-1);
			}
		}
		return connection;
	}

}
