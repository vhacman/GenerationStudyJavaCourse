package com.generation.library;

import java.io.File;
import java.io.IOException;

/**
 * Wrapper semplificato per scrivere file di testo.
 * Nasconde la complessità di java.io.FileWriter dietro un'API più semplice.
 */
public class FileWriter
{

	String                  filename;
	java.io.FileWriter      adaptee;    // Pattern Adapter: adatta java.io.FileWriter


	/**
	 * Elimina un file dal filesystem.
	 *
	 * @param filepath Path del file da eliminare
	 * @return true se eliminato con successo
	 */
	public static boolean delete(String filepath)
	{
		/*
		 * Operazione statica su file:
		 * → File.delete() restituisce boolean
		 * → Non lancia eccezione, solo true/false
		 */
		File file = new File(filepath);
		return file.delete();
	}


	/**
	 * Utility statica per scrivere contenuto in un file (sovrascrive).
	 *
	 * @param filename Path del file di destinazione
	 * @param content Contenuto da scrivere
	 */
	public static void writeTo(String filename, String content)
	{
		/*
		 * One-shot write:
		 * → Apre, scrive, chiude in un'unica chiamata
		 * → Semplifica scritture singole
		 */
		FileWriter writer = new FileWriter(filename);
		writer.print(content);
		writer.close();
	}


	/**
	 * Costruttore che apre/crea un file per la scrittura.
	 *
	 * @param filename Path del file da creare/aprire
	 */
	public FileWriter(String filename)
	{
		/*
		 * Adapter Pattern:
		 * → Wrappa java.io.FileWriter
		 * → Converte checked exceptions in unchecked
		 * → Semplifica l'API per il client
		 */
		try
		{
			this.filename = filename;
			adaptee       = new java.io.FileWriter(filename);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}


	/**
	 * Scrive un oggetto sul file (usa toString()).
	 *
	 * @param o Oggetto da scrivere
	 */
	public void print(Object o)
	{
		/*
		 * Buffered write:
		 * → Dati non scritti immediatamente su disco
		 * → Necessario chiamare close() per flush
		 */
		try
		{
			adaptee.write(o.toString());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}


	/**
	 * Chiude il file e forza la scrittura su disco.
	 */
	public void close()
	{
		/*
		 * Flush + Close:
		 * → Scrive buffer su disco
		 * → Rilascia risorse del sistema operativo
		 */
		try
		{
			adaptee.close();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

}
