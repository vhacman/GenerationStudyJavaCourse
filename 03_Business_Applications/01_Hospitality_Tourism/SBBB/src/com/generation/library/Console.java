package com.generation.library;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Console
{

	private static Scanner	keyboard = new Scanner(System.in);

	/**
	 * Stampa un messaggio in Console.
	 * @param x l'oggetto da stampare
	 */
	public static void print(Object x)
	{
		/*
		 ******************************************
		 * Stampa l'oggetto passato come parametro
		 * sulla console utilizzando System.out
		 ******************************************
		 */
		System.out.println(x);
	}



	/**
	 * Legge una stringa dalla console.
	 * @return la stringa letta dall'utente
	 */
	public static String readString()
	{
		/*
		 ******************************************
		 * Legge una riga completa di testo inserita
		 * dall'utente tramite lo Scanner keyboard
		 ******************************************
		 */
		return keyboard.nextLine();
	}


	/**
	 * Legge un numero intero dalla console.
	 * @return il numero intero inserito
	 */
	public static int readInt()
	{
		/*
		 ******************************************
		 * Tenta di convertire l'input dell'utente
		 * in un intero. Se fallisce, lancia una
		 * eccezione con messaggio personalizzato
		 ******************************************
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
	 * Legge un numero decimale dalla console.
	 * @return il numero decimale inserito
	 */
	public static double readDouble()
	{
		/*
		 ******************************************
		 * Tenta di convertire l'input dell'utente
		 * in un double. Se fallisce, lancia una
		 * eccezione con messaggio personalizzato
		 ******************************************
		 */
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
	 * Richiede una stringa non vuota all'utente.
	 * @param msg messaggio da mostrare all'utente
	 * @param errMsg messaggio di errore in caso di input vuoto
	 * @return la stringa non vuota inserita
	 */
	public static String askNotEmpty(String msg, String errMsg)
	{
		/*
		 ******************************************
		 * Cicla finché l'utente non inserisce
		 * una stringa valida (non vuota e non blank)
		 ******************************************
		 */
		String res = null;
		while(res==null || res.isBlank())
		{
			Console.print(msg);
			res = Console.readString();
			if(res.isBlank())
				Console.print(res);
		}
		return res;
	}



	/**
	 * Richiede un intero compreso in un range all'utente.
	 * @param msg messaggio da mostrare all'utente
	 * @param errorMsg messaggio di errore in caso di valore fuori range
	 * @param min valore minimo accettabile
	 * @param max valore massimo accettabile
	 * @return l'intero inserito compreso tra min e max
	 */
	public static int askIntBetween(String msg, String errorMsg, int min, int max )
	{
		/*
		 ******************************************
		 * Continua a chiedere all'utente un numero
		 * finché non viene inserito un valore
		 * compreso tra min e max (inclusi)
		 ******************************************
		 */
		int res = min -1;
		while(res<min || res>max)
		{
			Console.print(msg);
			res = Console.readInt();
			if(res<min || res>max)
				Console.print(errorMsg);
		}
		return res;
	}



}
