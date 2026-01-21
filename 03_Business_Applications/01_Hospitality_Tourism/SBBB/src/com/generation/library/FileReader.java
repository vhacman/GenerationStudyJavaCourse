package com.generation.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe per generare oggetti che leggono da file.
 * Un FileReader è uno strumento per leggere da file, così come Console è uno strumento che legge da Console.
 * @author FP80
 */
public class FileReader
{
	String			filename;
	private Scanner	reader;


	/**
	 * Legge l'intero contenuto di un file.
	 * @param filename il percorso del file da leggere
	 * @return il contenuto completo del file come stringa
	 */
	public static String readAll(String filename)
	{
		/*
		 ******************************************
		 * Crea un FileReader temporaneo e legge
		 * tutte le righe del file concatenandole
		 * con carattere di newline
		 ******************************************
		 */
		try
		{
			String res = "";
			FileReader reader = new FileReader(filename);
			while(reader.hasNext())
				res+=reader.readString()+"\n";
			reader.close();
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Console.print("Non riesco a caricare "+filename);
			return "";
		}
	}

	/**
	 * Crea uno strumento per leggere da file.
	 * @param filename il file da cui leggere
	 * @throws FileNotFoundException se il file non esiste
	 */
	public FileReader(String filename) throws FileNotFoundException
	{
		/*
		 ******************************************
		 * Inizializza lo Scanner collegandolo
		 * al file specificato per poterlo leggere
		 * riga per riga
		 ******************************************
		 */
		this.filename = filename;
		reader = new Scanner(new File(filename));
	}

	/**
	 * Legge un intero da file.
	 * @return un numero intero
	 */
	public int readInt()
	{
		/*
		 ******************************************
		 * Legge una riga come stringa e la
		 * converte in un numero intero
		 ******************************************
		 */
		return Integer.parseInt(readString());
	}

	/**
	 * Legge una riga da file.
	 * @return la stringa letta
	 */
	public String readString()
	{
		/*
		 ******************************************
		 * Utilizza lo Scanner per leggere
		 * la prossima riga completa dal file
		 ******************************************
		 */
		return reader.nextLine();
	}

	/**
	 * Legge un numero decimale da file.
	 * @return il numero decimale letto
	 */
	public double readDouble()
	{
		/*
		 ******************************************
		 * Legge una riga come stringa e la
		 * converte in un numero decimale double
		 ******************************************
		 */
		return Double.parseDouble(readString());
	}

	/**
	 * Verifica se ci sono altre righe da leggere.
	 * @return true se ci sono ancora righe disponibili
	 */
	public boolean hasNext()
	{
		/*
		 ******************************************
		 * Controlla tramite lo Scanner se il file
		 * ha ancora righe da leggere
		 ******************************************
		 */
		return reader.hasNextLine();
	}

	/**
	 * Chiude il FileReader e rilascia le risorse.
	 */
	public void close()
	{
		/*
		 ******************************************
		 * Chiude lo Scanner interno liberando
		 * le risorse di sistema associate al file
		 ******************************************
		 */
		reader.close();
	}
}
