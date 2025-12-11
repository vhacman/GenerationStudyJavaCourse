package com.generation.library;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Console 
{

	private static Scanner keyboard = new Scanner(System.in);
	
	/**
	 * Questo metodo (sottoprogramma) stampa un messaggio in Console.
	 * @param x
	 */
	public static void print(Object x)
	{
	    System.out.println(x);
	}

	
	
	/**
	 * Questo metodo (sottoprogramma) aspetta che l'utente inserisca una linea di testo e prema invio.
	 * La linea di testo viene spesso (non sempre) salvata in una variabile
	 * @return
	 */
	public static String readString()
	{
	    return keyboard.nextLine();
	}
	

	/**
	 * Questo metodo (sottoprogramma) aspetta che l'utente inserisca un numero intero e prema invio.
	 * @return
	 */
	public static int readInt()
	{
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
	 * Questo metodo (sottoprogramma) aspetta che l'utente inserisca un numero  e prema invio.
	 * @return
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
	
	

}
