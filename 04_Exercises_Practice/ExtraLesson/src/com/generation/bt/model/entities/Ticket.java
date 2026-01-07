package com.generation.bt.model.entities;


import java.time.LocalDateTime;

/**
 * In Java io posso creare nuovi tipi di variabile
 * In Java posso mettere assieme tipi già esistenti per crearne di nuovi
 * un biglietto è fatto da:
 * - codice
 * - data e ora del biglietto 
 * - level (classe)
 * - partenza
 * - destinazione
 * - km 
 * 
 * 
 * il biglietto contiene a sua volta 6 variabili: id, time, level, km, from e to
 * e contiene un SOTTOPROGRAMMA Che partendo da quelle variabili riesce a calcolare il proprio prezzo
 */
public class Ticket 
{
	// questi sono ATTRIBUTI. Questa classe che si chiama Ticket
	// NON ha il main. NON è una classe di avvio.
	// questa classe serve a definire un nuovo tipo di variabile
	// che si chiama "Ticket" e che è composta da altre variabili
	
	public String			id;			// codice del biglietto
	public LocalDateTime	time;	// data e ora del biglietto
	public int				level;			// classe del biglietto
	public int				km;				// km
	public String			from, to;		// partenza e destinazione
	
	/**
	 * Questo è un SOTTOPROGRAMMA che si chiama getPrice() e che produce un numero intero
	 * public double getPrice()
	 * public => usabile da chiunque
	 * double => produce un numero double
	 * getPrice() => nome del sottoprogramma
	 * @return
	 */
	public double getPrice()
	{
		// questo si chiama operatore ternario... studiatelo sul libro..
		// ? -->  se da true allora moltipila 0.2 per i km, else --> : --> moltipilca km per 0.15
		double res = 	level == 1 ? 0.2 * km : 0.15 * km;
		return res >= 1.7 ? res : 1.7;
		
	}
	
	/**
	 * questo è un altro sottoprogramma
	 * che produce o true o false
	 * produce true se il livello è 1 o 2 e se i km sono > 0
	 * 
	 * @return
	 */
	public boolean	isValid()
	{
		//			     or
		return (level == 1 || level == 2) && km > 0;
	}
	
	/**
	 * un altro sottoprogramma
	 * questo sottoprogramma produce una String
	 * 
	 */
	public String toString()
	{
		return	"Ticket " + id 		+ "\n" +
				"Da "	  + from	+" a " + to   +	"\n" +
				"Classe " + level 	+"\n"		 +
				"Km"	  + km			+"\n"		      +
				"Price "  + getPrice()	+" euro";
	}
	
}
