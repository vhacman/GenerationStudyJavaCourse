package com.generation.library;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Console 
{

	/**
	 * Legge un numero intero dall'utente assicurandosi che il valore inserito
	 * sia compreso in un intervallo specificato (min e max inclusi).
	 * Il metodo continua a richiedere l'input finché l'utente non inserisce
	 * un numero valido all'interno dell'intervallo.
	 * 
	 * @param msg Il messaggio da visualizzare all'utente per richiedere l'input
	 * @param errMsg Il messaggio di errore da visualizzare quando l'input non è valido o fuori dall'intervallo
	 * @param int min
	 * @param int max
	 * @return Il numero intero inserito dall'utente che è compreso nell'intervallo [min, max]
	 */
	public static int readIntBetween(String msg, String errMsg, int min, int max)
	{
		int res;
		
		do
		{
			Console.print(msg);
			res = Console.readInt();
			if (res < min || res > max)
				Console.print(errMsg);
		} while (res < min || res > max);
		return res;
	}
	
	
	/** 
	 * @param msg Il messaggio da visualizzare all'utente per richiedere l'input
	 * @param errMsg Il messaggio di errore da visualizzare quando l'input non è valido
	 * @param values Lista variabile di stringhe che rappresentano i valori accettati
	 * @return La stringa inserita dall'utente che corrisponde a uno dei valori validi
	 * 
	 * 
	 * Il ... indica che puoi passare 0, 1, 2, 3... infinite stringhe come parametri.
	 */
	public static String readStringBetween(String msg, String errMsg, String...values)
	{
		//Converte l'array values in una Lista
		//Perché le liste hanno il metodo .contains() che è comodo per verificare se un elemento esiste
	    List<String> v = Arrays.asList(values);
	    String res = "";
	    do
	    {
	        Console.print(msg);              // Stampa il messaggio iniziale
	        res = Console.readString();      // Legge l'input dell'utente
	        
	        if(!v.contains(res))             // Se la risposta NON è nella lista
	            Console.print(errMsg);       // Stampa il messaggio di errore
	            
	    } while(!v.contains(res));           // Continua finché la risposta NON è valida
	    
	    return res;
	}
	
	/**
	 * 
	 * @param message Il messaggio da visualizzare all'utente per richiedere l'input
	 * @param errorMessage Il messaggio di errore da visualizzare quando l'input è vuoto
	 * @return Una stringa non vuota inserita dall'utente
	 */
	public static String askNotEmptyString(String message, String errorMessage)
    {
        String res = "";
        do
        {
            Console.print(message);
            res = Console.readString();
            if(res.isBlank())
                Console.print(errorMessage);
        }while(res.isBlank());
        return res;
    }
	
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
