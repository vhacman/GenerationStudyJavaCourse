package com.generation.gbb.model.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.generation.library.Console;

/**
 * L'interfaccia esiste già!
 * E' nella libreria che abbiamo importato
 * e si chiama Connection
 */
public class ConnectionFactory 
{

	static Connection connection = null;
	
	/**
	 * Crea o restituisce l'istanza singleton della connessione al database SQLite.
	 * Implementa il pattern Singleton per garantire una singola connessione attiva.
	 * Utilizza il DriverManager come Factory Method per ottenere la connessione appropriata.
	 *
	 * @return l'istanza unica di Connection al database grottammare.db
	 */
	public static Connection make()
	{
		/*
		 * ******************************************************************************
		 * Relazione tra Design Pattern e Principi OOP in questo metodo:
		 *
		 *      Singleton Pattern  →  Garantisce una sola istanza di Connection
		 *      - Verifica se connection == null prima di creare una nuova istanza
		 *      - Riutilizza la stessa connessione per tutte le richieste successive
		 *      - Evita spreco di risorse mantenendo un'unica connessione attiva
		 *
		 *      Factory Method Pattern  →  Delega la creazione a DriverManager
		 *      - DriverManager.getConnection() è una factory che crea l'implementazione
		 *        corretta di Connection in base al protocollo JDBC specificato
		 *      - ConnectionFactory nasconde la complessità della creazione
		 *      - Il client riceve un'interfaccia Connection senza conoscere l'implementazione
		 *
		 *      Astrazione  →  Connection (interfaccia JDBC standard)
		 *      Implementazione  →  SQLite JDBC Driver (org.sqlite.JDBC)
		 *
		 * Questa classe applica il principio di Incapsulamento nascondendo i dettagli
		 * della connessione al database e fornendo un punto di accesso centralizzato.
		 * ******************************************************************************
		 */
		if(connection==null)
		{
			try
			{
				// Carica il driver JDBC SQLite nella JVM
				Class.forName("org.sqlite.JDBC");

				// Crea la connessione utilizzando DriverManager come Factory Method
				// jdbc:sqlite:grottammare.db indica il protocollo e il percorso del database
				connection = DriverManager.getConnection("jdbc:sqlite:grottammare.db");
			}
			catch(Exception e)
			{
				Console.print("Cannot create connection");
				e.printStackTrace();
				System.exit(-1); // Termina l'applicazione in caso di errore critico
			}
		}
		return connection;
	}
	
	
	
}
