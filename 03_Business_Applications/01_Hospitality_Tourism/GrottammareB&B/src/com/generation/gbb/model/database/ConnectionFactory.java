package com.generation.gbb.model.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.generation.library.Console;

/**
 * Factory per la gestione della connessione al database SQLite.
 * Implementa il pattern Singleton per garantire una singola istanza
 * della connessione durante il ciclo di vita dell'applicazione.
 */
public class ConnectionFactory 
{
    static Connection connection = null;
    
    /**
     * Crea o restituisce l'istanza esistente della connessione al database.
     * Utilizza lazy initialization per creare la connessione solo quando necessaria.
     * 
     * @return l'istanza singleton della connessione al database SQLite
     */
    public static Connection make()
    {
        if(connection == null)
        {
            try
            {
                /*
                 * Relazione tra Pattern e Principi OOP:
                 * 
                 * Singleton Pattern → Garantisce istanza unica
                 * Factory Pattern → Delega creazione a DriverManager
                 * Astrazione → Connection nasconde dettagli implementativi del DB
                 * Incapsulamento → Logica di connessione isolata in questa classe
                 * 
                 * Il metodo coordina due factory:
                 * 1. ConnectionFactory (questa classe) → gestisce il ciclo di vita
                 * 2. DriverManager.getConnection() → crea l'implementazione specifica
                 * 
                 * Class.forName() verifica il caricamento del driver JDBC prima
                 * della connessione, garantendo fail-fast in caso di dipendenze mancanti.
                 */
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:grottammare.db");	
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
