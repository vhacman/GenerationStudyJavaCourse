package com.generation.library.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.generation.library.Console;

public class ConnectionFactory 
{

static Connection connection = null;
	
	/**
	 * Qui dentro userò un'altra factory
	 * che deriva dalla mia libreria
	 * @return
	 */
	public static Connection make()
	{
		if(connection==null)
		{
			try
			{
				// serve a vedere se abbiamo caricato la libreria giusta... va fatto prima di creare la connessione
				Class.forName("org.sqlite.JDBC");
				//										  jdbc=> libreria che stiamo usando
				//										  sqlite=> il tipo del nostro database
				//										  grottammare.db => il database che stiamo usando
				//						   getConnection è una factory a sua volta, sceglierà la connection adatta a noi
				connection = DriverManager.getConnection("jdbc:sqlite:hotel.db");	
			}
			catch(Exception e) 
			{
				Console.print("Cannot create connection");
				e.printStackTrace();
				System.exit(-1); // AMMAZZO IL PROGRAMMA
			}
		}
		return connection;
	}
	
}
