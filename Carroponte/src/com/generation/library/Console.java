package com.generation.library;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utility class per operazioni di input/output su console.
 * Semplifica l'uso di Scanner per lettura da tastiera.
 */
public class Console
{

	private static Scanner  keyboard = new Scanner(System.in);


	/**
	 * Stampa un oggetto sulla console.
	 * @param x Oggetto da stampare
	 */
	public static void print(Object x) { System.out.println(x); }


	/**
	 * Legge una linea di testo dall'utente.
	 * @return Stringa inserita dall'utente
	 */
	public static String readString() { return keyboard.nextLine(); }


	/**
	 * Legge un numero intero dall'utente.
	 * @return Numero intero inserito
	 * @throws NumberFormatException Se l'input non è un numero valido
	 */
	public static int readInt()
	{
		/*
		 * Parse con gestione errori:
		 * → Converte String in int
		 * → Lancia eccezione con messaggio chiaro
		 */
		try
		{
			return Integer.parseInt(keyboard.nextLine());
		}
		catch(NumberFormatException e)
		{
			throw new NumberFormatException("Il valore inserito non è un numero.");
		}
	}


	/**
	 * Legge un numero decimale dall'utente.
	 * @return Numero decimale inserito
	 * @throws NumberFormatException Se l'input non è un numero valido
	 */
	public static double readDouble()
	{
		try
		{
			return Double.parseDouble(keyboard.nextLine());
		}
		catch(NumberFormatException e)
		{
			throw new NumberFormatException("Il valore inserito non è un numero.");
		}
	}


	/**
	 * Richiede all'utente una stringa non vuota.
	 *
	 * @param msg Messaggio da mostrare
	 * @param errMsg Messaggio di errore (attualmente non utilizzato)
	 * @return Stringa non vuota inserita dall'utente
	 */
	public static String askNotEmpty(String msg, String errMsg)
	{
		/*
		 * Loop di validazione:
		 * → Continua a chiedere finché non riceve input valido
		 * → Garantisce stringa non blank
		 */
		String res = null;

		while(res == null || res.isBlank())
		{
			Console.print(msg);
			res = Console.readString();

			if(res.isBlank())
				Console.print(res);
		}

		return res;
	}


	/**
	 * Richiede un intero compreso in un range.
	 *
	 * @param msg Messaggio da mostrare
	 * @param errorMsg Messaggio di errore se fuori range
	 * @param min Valore minimo (incluso)
	 * @param max Valore massimo (incluso)
	 * @return Intero valido nel range [min, max]
	 */
	public static int askIntBetween(String msg, String errorMsg, int min, int max)
	{
		/*
		 * Loop di validazione con range:
		 * → Inizializza fuori range per entrare nel loop
		 * → Continua finché input è valido
		 */
		int res = min - 1;

		while(res < min || res > max)
		{
			Console.print(msg);
			res = Console.readInt();

			if(res < min || res > max)
				Console.print(errorMsg);
		}

		return res;
	}

}
