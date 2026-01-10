package com.generation.library;



import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * CONCETTI TEORICI - CLASSE DI UTILITÀ (UTILITY CLASS)
 *
 * Console è una UTILITY CLASS (classe di utilità).
 * Fornisce metodi STATICI per facilitare l'input/output da console.
 *
 * CARATTERISTICHE DELLE UTILITY CLASS:
 * - Tutti i metodi sono STATIC
 * - Non serve creare un'istanza: Console.print(...) NON new Console().print(...)
 * - Sono come "scorciatoie" per operazioni comuni
 *
 * PERCHÉ STATIC?
 * - Non ha senso avere più "Console" diverse
 * - L'input/output è sempre lo stesso (System.in / System.out)
 * - Possiamo chiamare i metodi direttamente: Console.readInt()
 *
 * COSA FA QUESTA CLASSE:
 * - Semplifica la lettura da tastiera
 * - Gestisce automaticamente gli errori di parsing
 * - Fornisce validazione dell'input
 */

public class Console
{

	/*
	 * CONCETTI TEORICI - VARARGS (ARGOMENTI VARIABILI)
	 *
	 * String...values → VARARGS (Variable Arguments)
	 * Permette di passare un numero VARIABILE di parametri dello stesso tipo.
	 *
	 * ESEMPI DI UTILIZZO:
	 * readStringBetween("Scegli: ", "Errore!", "A", "B", "C")
	 * readStringBetween("Scegli: ", "Errore!", "SI", "NO")
	 * readStringBetween("Scegli: ", "Errore!") // 0 valori
	 *
	 * INTERNAMENTE:
	 * String...values viene trattato come String[] values (array)
	 * Java converte automaticamente i parametri in un array
	 *
	 * SINTASSI:
	 * - I varargs DEVONO essere l'ULTIMO parametro
	 * - Puoi avere SOLO UN varargs per metodo
	 * - VALIDO: metodo(String msg, String...values)
	 * - NON VALIDO: metodo(String...values, String msg)
	 */

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
	
	/*
	 * CONCETTI TEORICI - SCANNER E ATTRIBUTI STATIC
	 *
	 * Scanner keyboard → oggetto per leggere l'input da tastiera
	 *
	 * private static:
	 * - static → UNA SOLA istanza condivisa da tutti i metodi della classe
	 * - private → solo i metodi di Console possono usarlo
	 *
	 * PERCHÉ UNA SOLA Scanner?
	 * - Scanner si collega a System.in (input standard = tastiera)
	 * - C'è UNA SOLA tastiera, quindi serve UNO Scanner
	 * - Se creiamo più Scanner sullo stesso input, creano conflitti
	 *
	 * new Scanner(System.in):
	 * - System.in → lo stream di input standard (tastiera)
	 * - Scanner → wrapper che facilita la lettura
	 *
	 * INIZIALIZZAZIONE:
	 * - Questo attributo viene inizializzato SUBITO quando la classe viene caricata
	 * - Prima che qualsiasi metodo venga chiamato
	 */
	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * Questo metodo (sottoprogramma) stampa un messaggio in Console.
	 * @param x
	 */
	public static void print(Object x)
	{
		/*
		 * POLIMORFISMO - Object come parametro
		 *
		 * Object x → può ricevere QUALSIASI tipo
		 * - print("ciao") → x è String
		 * - print(123) → x è Integer (autoboxing da int)
		 * - print(product) → x è Product
		 *
		 * System.out.println(x):
		 * - System.out → output standard (console)
		 * - println() → print line (stampa e va a capo)
		 * - Automaticamente chiama x.toString() per convertire in stringa
		 */
	    System.out.println(x);
	}

	/**
	 * Questo metodo (sottoprogramma) aspetta che l'utente inserisca una linea di testo e prema invio.
	 * La linea di testo viene spesso (non sempre) salvata in una variabile
	 * @return
	 */
	public static String readString()
	{
		/*
		 * SCANNER - nextLine()
		 *
		 * keyboard.nextLine() → legge UNA RIGA COMPLETA fino a ENTER
		 * - Aspetta che l'utente prema ENTER
		 * - Ritorna tutto quello che l'utente ha scritto (come String)
		 * - INCLUDE gli spazi
		 *
		 * ESEMPIO:
		 * Utente scrive: "Ciao mondo" [ENTER]
		 * → ritorna "Ciao mondo"
		 *
		 * DIFFERENZA CON next():
		 * - next() → legge UNA PAROLA (si ferma allo spazio)
		 * - nextLine() → legge TUTTA LA RIGA (fino a ENTER)
		 */
	    return keyboard.nextLine();
	}


	/*
	 * CONCETTI TEORICI - PARSING E CONVERSIONE TIPI
	 *
	 * Problema: Scanner legge SEMPRE stringhe
	 * Se l'utente scrive "123", Scanner legge la STRING "123"
	 * Ma noi vogliamo l'INT 123
	 *
	 * Soluzione: PARSING (convertire String → int)
	 */

	/**
	 * Questo metodo (sottoprogramma) aspetta che l'utente inserisca un numero intero e prema invio.
	 * @return
	 */
	public static int readInt()
	{
	    try
	    {
	    	/*
	    	 * INTEGER.parseInt()
	    	 *
	    	 * Integer.parseInt(String) → converte String → int
	    	 *
	    	 * ESEMPI:
	    	 * Integer.parseInt("123") → 123 ✓
	    	 * Integer.parseInt("0") → 0 ✓
	    	 * Integer.parseInt("-50") → -50 ✓
	    	 *
	    	 * ERRORI:
	    	 * Integer.parseInt("abc") → NumberFormatException ✗
	    	 * Integer.parseInt("12.5") → NumberFormatException ✗
	    	 * Integer.parseInt("") → NumberFormatException ✗
	    	 *
	    	 * PROCESSO:
	    	 * 1. keyboard.nextLine() → legge la stringa dall'utente
	    	 * 2. Integer.parseInt() → converte in int
	    	 * 3. return → ritorna il numero
	    	 */
	    	return Integer.parseInt(keyboard.nextLine());
	    }
	    catch(NumberFormatException e)
	    {
	    	/*
	    	 * GESTIONE ERRORI - RE-THROW
	    	 *
	    	 * Se parseInt() fallisce, lancia NumberFormatException
	    	 * Noi la CATTURIAMO (catch) e poi la RI-LANCIAMO (throw) con un messaggio custom
	    	 *
	    	 * PERCHÉ?
	    	 * - Il messaggio originale è tecnico: "For input string: "abc""
	    	 * - Il nostro messaggio è più chiaro per l'utente
	    	 *
	    	 * throw new NumberFormatException(...):
	    	 * - Crea una NUOVA eccezione con il nostro messaggio
	    	 * - La LANCIA (il metodo termina, l'errore si propaga)
	    	 * - Chi ha chiamato readInt() deve gestire l'errore
	    	 */
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
