package com.generation.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Wrapper semplificato per leggere file di testo.
 * Fornisce un'API più semplice di Scanner/File per lettura da file.
 */
public class FileReader
{

	String   filename;
	private Scanner  reader;


	/**
	 * Legge l'intero contenuto di un file come stringa unica.
	 *
	 * @param filename Path del file da leggere
	 * @return Contenuto completo del file, stringa vuota in caso di errore
	 */
	public static String readAll(String filename)
	{
		/*
		 * Utility statica per lettura completa:
		 * → Apre file, legge tutto, chiude
		 * → Gestisce eccezioni internamente
		 */
		try
		{
			String     res    = "";
			FileReader reader = new FileReader(filename);

			while(reader.hasNext())
				res += reader.readString() + "\n";

			reader.close();
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Console.print("Non riesco a caricare " + filename);
			return "";
		}
	}


	/**
	 * Costruttore che apre un file per la lettura.
	 *
	 * @param filename Path del file da aprire
	 * @throws FileNotFoundException Se il file non esiste
	 */
	public FileReader(String filename) throws FileNotFoundException
	{
		/*
		 * Inizializzazione con Scanner:
		 * → Scanner su File per lettura riga per riga
		 * → Eccezione propagata al chiamante
		 */
		this.filename = filename;
		reader        = new Scanner(new File(filename));
	}


	/**
	 * Legge un intero dalla riga corrente.
	 * @return Valore intero letto
	 */
	public int readInt() { return Integer.parseInt(readString()); }


	/**
	 * Legge una riga completa dal file.
	 * @return Stringa letta
	 */
	public String readString() { return reader.nextLine(); }


	/**
	 * Legge un numero decimale dalla riga corrente.
	 * @return Valore double letto
	 */
	public double readDouble() { return Double.parseDouble(readString()); }


	/**
	 * Verifica se ci sono altre righe da leggere.
	 * @return true se ci sono righe disponibili
	 */
	public boolean hasNext() { return reader.hasNextLine(); }


	/**
	 * Chiude il file e rilascia le risorse.
	 */
	public void close() { reader.close(); }

}
