package com.generation.library;

public class Template
{

	/**
	 * Carica il contenuto di un file template.
	 * @param filename il percorso del file da caricare
	 * @return il contenuto del file come stringa
	 */
	public static String load(String filename)
	{
		/*
		 ******************************************
		 * Utilizza un FileReader per leggere tutte
		 * le righe del file e concatenarle in una
		 * unica stringa con caratteri newline
		 ******************************************
		 */
		try
		{
			String res = "";
			FileReader reader = new FileReader(filename);
			while(reader.hasNext())
				res+=reader.readString()+"\n";
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}


}
