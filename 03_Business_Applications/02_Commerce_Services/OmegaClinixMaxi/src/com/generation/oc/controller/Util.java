package com.generation.oc.controller;

/**
 * Utility varie per il controller
 *
 * TODO aggiungere utility per validazione date
 * TODO aggiungere utility per validazione codice fiscale
 */
public class Util
{
	/**
	 * Verifica se una stringa può essere convertita in un intero
	 * @param s stringa da verificare
	 * @return true se la stringa è un intero valido, false altrimenti
	 */
	public static boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
}
