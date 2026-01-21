package com.generation.library;

/**
 * Utility per caricare template da file.
 * Semplice wrapper su FileReader per caricare template di testo.
 */
public class Template
{

	/**
	 * Carica un template da file.
	 *
	 * @param filename Path del file template
	 * @return Contenuto del template, stringa vuota in caso di errore
	 */
	public static String load(String filename)
	{
		/*
		 * Template loading:
		 * → Legge file riga per riga
		 * → Concatena con newline
		 * → Gestisce errori restituendo stringa vuota
		 */
		try
		{
			String     res    = "";
			FileReader reader = new FileReader(filename);

			while(reader.hasNext())
				res += reader.readString() + "\n";

			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

}
