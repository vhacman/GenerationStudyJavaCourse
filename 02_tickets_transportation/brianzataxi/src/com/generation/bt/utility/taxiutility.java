package com.generation.bt.utility;
import com.generation.library.Console;

//CLASSE CON METODI UTILI PER CREARE BIGLIETTO TAXI
public class TaxiUtility
{
	/**
     * METODO: askMinutes()
     * SCOPO: Chiede all'utente di inserire i minuti della corsa
     * FUNZIONAMENTO: Legge un numero intero dalla console. Se il numero è <= 0, 
     *                chiede di reinserirlo finché non è valido (> 0)
     * INPUT: Nessuno (legge da console)
     * OUTPUT: Ritorna un intero positivo (minuti della corsa)
     */
    public static int askMinutes() {
        int minutes;

        Console.print("Inserire i minuti della corsa: ");
        minutes = Console.readInt();

        while (minutes <= 0)
        {
            Console.print("Errore: i minuti devono essere maggiori di 0!");
            Console.print("Inserire i minuti della corsa: ");
            minutes = Console.readInt();
        }

        return minutes;
    }
    
    /**
     * METODO: askLevel()
     * SCOPO: Chiede all'utente di inserire la classe del taxi (1 o 2)
     * FUNZIONAMENTO: Legge un numero intero dalla console. Se il numero non è 1 o 2, 
     *                chiede di reinserirlo finché non è valido
     * INPUT: Nessuno (legge da console)
     * OUTPUT: Ritorna 1 (prima classe) o 2 (seconda classe)
     */
    public static int askLevel() {
        int level;

        Console.print("Inserire la classe (1 o 2): ");
        level = Console.readInt();

        while (level != 1 && level != 2)
        {
            Console.print("Errore: la classe deve essere 1 o 2!");
            Console.print("Inserire la classe (1 o 2): ");
            level = Console.readInt();
        }
        return level;
    }
    
    /**
     * METODO: askHour()
     * SCOPO: Chiede all'utente di inserire l'ora di partenza (formato 24h)
     * FUNZIONAMENTO: Legge un numero intero dalla console. Se il numero è < 0 o > 23, 
     *                chiede di reinserirlo finché non è valido
     * INPUT: Nessuno (legge da console)
     * OUTPUT: Ritorna un intero tra 0 e 23 (ore in formato 24h)
     */
    public static int askHour() {
        int hour;

        Console.print("Inserire l'ora della prenotazione (0-23): ");
        hour = Console.readInt();

        while (hour < 0 || hour > 23)
        {
            Console.print("Errore: l'ora deve essere compresa tra 0 e 23!");
            Console.print("Inserire l'ora della prenotazione (0-23): ");
            hour = Console.readInt();
        }

        return hour;
    }

    
	/**
	 * METODO: askMinutesStart()
	 * SCOPO: Chiede all'utente di inserire i minuti di partenza (0-59)
	 * FUNZIONAMENTO: Legge un numero intero dalla console. Se il numero è < 0 o > 59, 
	 *                chiede di reinserirlo finché non è valido
	 * INPUT: Nessuno (legge da console)
	 * OUTPUT: Ritorna un intero tra 0 e 59 (minuti di partenza)
	 */
	public static int askMinutesStart() {
	    int minutes;

	    Console.print("Inserire i minuti di partenza (0-59): ");
	    minutes = Console.readInt();

	    while (minutes < 0 || minutes > 59)
	    {
	        Console.print("Errore: i minuti devono essere compresi tra 0 e 59!");
	        Console.print("Inserire i minuti di partenza (0-59): ");
	        minutes = Console.readInt();
	    }

	    return minutes;
	}

	
	/**
	 * METODO: askHourArrival()
	 * SCOPO: Chiede all'utente di inserire l'ora di arrivo (formato 24h)
	 * FUNZIONAMENTO: Legge un numero intero dalla console. Se il numero è < 0 o > 23, 
	 *                chiede di reinserirlo finché non è valido
	 * INPUT: Nessuno (legge da console)
	 * OUTPUT: Ritorna un intero tra 0 e 23 (ore di arrivo)
	 */
	public static int askHourArrival() {
	    int hour;

	    Console.print("Inserire l'ora di arrivo (0-23): ");
	    hour = Console.readInt();

	    while (hour < 0 || hour > 23)
	    {
	        Console.print("Errore: l'ora deve essere compresa tra 0 e 23!");
	        Console.print("Inserire l'ora di arrivo (0-23): ");
	        hour = Console.readInt();
	    }

	    return hour;
	}

	
	/**
	 * METODO: askMinutesArrival()
	 * SCOPO: Chiede all'utente di inserire i minuti di arrivo (0-59)
	 * FUNZIONAMENTO: Legge un numero intero dalla console. Se il numero è < 0 o > 59, 
	 *                chiede di reinserirlo finché non è valido
	 * INPUT: Nessuno (legge da console)
	 * OUTPUT: Ritorna un intero tra 0 e 59 (minuti di arrivo)
	 */
	public static int askMinutesArrival() {
	    int minutes;

	    Console.print("Inserire i minuti di arrivo (0-59): ");
	    minutes = Console.readInt();

	    while (minutes < 0 || minutes > 59)
	    {
	        Console.print("Errore: i minuti devono essere compresi tra 0 e 59!");
	        Console.print("Inserire i minuti di arrivo (0-59): ");
	        minutes = Console.readInt();
	    }

	    return minutes;
	}

	
    /**
     * METODO: calculatePrice()
     * SCOPO: Calcola il prezzo totale della corsa in base a durata, classe e ora
     * FUNZIONAMENTO: 
     *   1. Se classe = 1 (prima classe): tariffa = 0.50€ al minuto
     *   2. Se classe = 2 (seconda classe): tariffa = 0.30€ al minuto
     *   3. Moltiplica minuti × tariffa per ottenere il prezzo base
     *   4. Se l'ora è prima delle 5:00 (orario notturno): aggiunge supplemento di 10€
     * INPUT: minutes (durata), level (classe), hour (ora di partenza)
     * OUTPUT: Ritorna il prezzo finale in euro (double)
     */
    public static double	calculatePrice(int minutes, int level, int hour) {
        
    	double	pricePerMinute;
        double	price;

        if (level == 1) {
            pricePerMinute = 0.5;
        }
        else {
            pricePerMinute = 0.3;
        }

        price = minutes * pricePerMinute;
        if (hour < 5) {
            price += 10;
        }
        return price;
    }

    /**
     * METODO: printTicket()
     * SCOPO: Stampa il prezzo della corsa sulla console
     * FUNZIONAMENTO: Stampa un messaggio formattato con il prezzo (es: "Il costo della corsa è: €6.90")
     * INPUT: price (prezzo della corsa)
     * OUTPUT: Nessuno (stampa sulla console)
     */
    public static void printTicket(double price) {
        Console.print("Il costo della corsa è: €" + price);
    }
}