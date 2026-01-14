package com.generation.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe per generare oggetti che leggeranno da file. <br />
 * Un FileReader � uno strumento per leggere da file, cos� come Console � uno strumento che legge da Console.
 * @author FP80
 *
 */
public class FileReader
{
	String         filename;
	private Scanner reader;
	
	
	public static String readAll(String filename)
	{
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
	 * creo uno strumento per leggere da file. 
	 * @param filename il file da cui leggere
	 * @throws FileNotFoundException 
	 */
	public FileReader(String filename) throws FileNotFoundException
	{
		this.filename = filename;
		reader = new Scanner(new File(filename));
	}

	/**
	 * Sottoprogramma per leggere un intero da file. Permette di leggere SOLO numeri interi
	 * @return un numero intero
	 */
	public int readInt()
	{
		return Integer.parseInt(readString());
	}

	/**
	 * Sottoprogramma per leggere una riga da file.
	 * @return
	 */
	public String readString()
	{
		return reader.nextLine();
	}

	/**
	 * Sottoprogramma per leggere un numero qualunque, anche con la virgola, da file. Usare il punto invece della virgola!
	 * @return
	 */
	public double readDouble()
	{
		return Double.parseDouble(readString());
	}

	public boolean hasNext()
	{
		return reader.hasNextLine();
	}

	public void close()
	{
		reader.close();
	}
}
