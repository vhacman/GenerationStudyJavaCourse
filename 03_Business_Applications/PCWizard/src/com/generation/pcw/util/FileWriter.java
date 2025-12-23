package com.generation.pcw.util;

import java.io.File;
import java.io.IOException;

/**
 * Tool per scrivere su un file. Un oggetto di tipo FileWriter permette di scrivere su un file
 * @author FP80
 *
 */
public class FileWriter 
{
	String filename;
	java.io.FileWriter adaptee;
	
	/**
	 * Cancello il file filepath
	 * @param filepath
	 */
	public static boolean delete(String filepath)
	{
		File file = new File(filepath);
		return file.delete();
	}
		
	public static void writeTo(String filename, String content)
	{
		FileWriter writer = new FileWriter(filename);
		writer.print(content);
		writer.close();
		
	}
	
	/**
	 * creo uno strumento per scrivere su file
	 * @param filename il file da aprire
	 * @throws IOException 
	 */
	public FileWriter(String filename) 
	{
		try
		{
			this.filename = filename;
		    adaptee = new java.io.FileWriter(filename);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Scrivo una riga sul file. Non � ancora scritta, bisogna usare "close" per scrivere.
	 * @throws IOException 
	 */
	public void print(Object o)  
	{
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
	 * Termino il lavoro sul file.
	 * @throws IOException 
	 */
	public void close() 
	{
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
