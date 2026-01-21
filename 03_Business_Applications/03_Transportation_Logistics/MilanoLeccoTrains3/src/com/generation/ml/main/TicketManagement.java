package com.generation.ml.main;

import com.generation.library.*;

/*
 * 1 - Chiedere nome, cognome ed età, possessore di greenCard(si/no)--> askPersonalInformatio();
 * 
 * 2 - Chiedere Data, ora di partenza --> askDate();
 * 												askHour();
 * 
 * 3 - Alcuni clienti hanno uno sconto:
 * 
 * 
 * 			calculatePrice(String from, String to, int age);
 * 
 * 
 * 	  			Over 70 --> gratis
 *	  			Carta Green --> per i clienti => 20% di sconto --> calculateDiscount(); (?) 
 * 
 * 
 * 4 - Stampare HTML  --> printHtml();
 */

/*
 * Sistema di gestione biglietteria ferroviaria Milano-Lecco.
 * Gestisce vendita biglietti con calcolo automatico di distanze e prezzi.
 */
public class TicketManagement
{
    /**
     * Questo metodo (sottoprogramma) rappresenta il punto di ingresso del programma.
     * Gestisce il loop per l'inserimento di più biglietti fino a quando l'utente decide di uscire.
     * 
     * @param args Argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args)
    {        
    	Console.print("=== BIGLIETTERIA FERROVIARIA ===\n");
   		Console.print("Benvenuto a Milano Lecco Trains");
       	String	goOn;
       	
       	do 
       	{
       		printTicket();
       		Console.print("Vuoi inserire un altro biglietto? ");
       		goOn = Console.readString();
       	} while (goOn.equalsIgnoreCase("s"));
       	Console.print("Ciao, ciao");
    }
    
    /**
     * Questo metodo (sottoprogramma) gestisce l'inserimento e la stampa di un singolo biglietto.
     * Acquisisce i dati necessari dall'utente, calcola distanza e prezzo, e stampa il riepilogo.
     * @param vuoto -> Non riceve input
     * @return vuoto --> non produce OUTPUT
     */
    private static void		printTicket()
    {
    	String	fullName;
        String 	departure, arrival;
        int 	level;
        double 	price;
        int		km;
        String	date;
        String	hour;
        int		age;
        boolean	greenCard;

        fullName = Console.askNotEmptyString("Inserire nome", "Il nome non può essere vuoto"); 
        
        age = Console.readIntBetween("Inserire età: ", "L'età deve essere un numero valido", 1, 120);
        
        Console.print("Inserire partenza: ");
        departure = askStation("");

        Console.print("Inserire Destinazione: ");
        arrival = askStation(departure);

        Console.print("Inserire classe (1 o 2): ");
        level = Console.readIntBetween("Inserire classe: ", "La classe deve essere un numero valido (1 - 2)", 1, 2);
        
        Console.print("Data Partenza: ");
        date = askDate();

        Console.print("Inserire ora di partenza:");
        hour = askHour();
                       
        km = calculateDistance(departure, arrival);
        
        Console.print("Sei possessore di Green Card? ");
        greenCard = Console.readString().equalsIgnoreCase("S");
        
        price = calculatePrice(km, level, age, greenCard);
           
        // Genera HTML
        String template = Template.load("print/template.html");
        template = template
            .replace("[fullName]", fullName)
            .replace("[departure]", departure)
            .replace("[arrival]", arrival)
            .replace("[age]", age + "")
            .replace("[level]", level == 1 ? "Prima" : "Seconda")
            .replace("[km]", km + "")
            .replace("[price]", String.format("%.2f", price))
            .replace("[date]", date)
            .replace("[hour]", hour)
            .replace("[greenCard]", greenCard == true ? "Sì" : "No");

        String	stampaTestuale = Template.load("print/template.txt");
        
        stampaTestuale = stampaTestuale
                .replace("[fullName]", fullName)
                .replace("[departure]", departure)
                .replace("[arrival]", arrival)
                .replace("[age]", age + "")
                .replace("[level]", level == 1 ? "Prima" : "Seconda")
                .replace("[km]", km + "")
                .replace("[price]", String.format("%.2f", price))
                .replace("[date]", date)
                .replace("[hour]", hour)
            	.replace("[greenCard]", greenCard == true ? "Sì" : "No");

        
        // Crea nome file con timestamp
        String filename = "print_biglietto" + ".html";
        
        FileWriter.writeTo(filename, template);
        Console.print("Biglietto salvato in " + filename + "\n");
        Console.print(stampaTestuale);
    }

    private static double	calculatePrice(int km, int level, int age, boolean greenCard)
    {
    	double price = (level == 1 ? 0.2 : 0.15) * km;
    	 
    	if (age > 75)
    		return 0;
    	
    	if(greenCard)
    		price = price * 0.8;
    	return price;
	}

	/*
     * @param : vuoto (i parametri vanno dal chiamante al chiamato
     * 
     * @return : una stringa con la data valida
     * int day, int month, int year --> dati inseriti dall'utente che diventerà una stringa
     * */
    private static String	askDate()
    {
    	int			day, month, year;	
    	boolean		isValid;
    	do
    	{
    		day = Console.readIntBetween("Inserisci giorno di partenza", "Il giorno non è valido", 1, 31);
    		month = Console.readIntBetween("Inserisci mese di partenza", "Il mese non è valido", 1, 12);
    		year = Console.readIntBetween("Inserisci anno di partenza", "Il anno non è valido", 2025, 2125);  		
    		isValid = isValidDate(day, month, year);   		
    		if (isValid == false)
    			Console.print("Errore: qualcosa è andato storto, reinserire i dati: ");
    		
    	} while (!isValid);
    	String	strDate = day + "/" + month + "/" + year;
		return strDate;
	}

    /*
     * 
     * Metodo per decidere se un dato giorno, mese e anno sono validi.
     * Controllo su max 12 mesi, 
     * @param : int day, int month, int year (giorno, mese e anno)
     * @return boolean isValid (il giorno è corretto)
     * 
     * 
     * */
    public static boolean isValidDate (int day, int month, int year) 
    {
       	int	maxDay = 31;
        	
       	if (month < 1 || month > 12)
       		return false;       	
       	switch (month)
       	{
       		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
       			maxDay = 31;
       		break;
       		case 4: case 6: case 9: case 11:
       			maxDay = 30;
       		break;
       		case 2:
       			maxDay = (year % 4 == 0) ? 29 : 28; // ANNO BISESTILE
       		break;
       	}       	
       	if(day > maxDay)
       		return false;       	
       	return true;
    	
    }
    
    /**
     * Questo metodo chiede all'utente di inserire un orario valido.
     * Richiede ora e minuti, verifica la validità dell'orario
     * e continua a richiedere i dati finché non viene inserito un orario corretto.
     * 
     * @return Una stringa con l'orario valido nel formato "ora:minuti"
     */
    private static String askHour()
    {
        int hour, minutes;
        hour = Console.readIntBetween("Inserire ora di partenza: ", "L'ora deve essere tra 0 e 23", 0, 23);
        minutes = Console.readIntBetween("Inserire minuti di partenza: ", "I minuti deve essere tra 0 e 59", 0, 59);
        String dTime = hour + ":" + minutes;
        return dTime;
    }

    
	/**
     * Questo metodo (sottoprogramma) chiede all'utente di inserire una stazione valida.
     * Valida l'input e continua a richiedere finché non viene inserita una stazione corretta.
     * Le stazioni valide sono: Milano, Lecco, Osnago, Monza (case-insensitive).
     * 
     * @param exclude La stazione da escludere (stringa vuota se nessuna esclusione)
     * @return La stazione valida inserita dall'utente
     */
    private static String askStation(String exclude)
    {
        String	station;
        boolean valid;

        do
        {
            station = Console.readString();
            String stationLower = station.toLowerCase();

            switch(stationLower)
            {
                case "milano", "lecco", "osnago", "monza":
                    if (!exclude.isEmpty() && stationLower.equals(exclude.toLowerCase())) {
                        Console.print("Stazione già selezionata. Riprova: ");
                        valid = false;
                    }
                    else
                        valid = true;
                    break;
                default:
                    Console.print("Stazione non valida. Riprova: ");
                    valid = false;
            }
        } while (!valid);
        return station;
    }


    /**
     * Questo metodo (sottoprogramma) calcola la distanza in chilometri tra due stazioni.
     * Utilizza una mappa predefinita della linea ferroviaria Milano-Lecco.
     * Mappa: Milano(0) ──15km── Monza(15) ──15km── Osnago(30) ──15km── Lecco(45).
     * 
     * @param from La stazione di partenza
     * @param to La stazione di arrivo
     * @return La distanza in chilometri tra le due stazioni
     */
    private static int calculateDistance(String from, String to)
    {
        String	fromLower = from.toLowerCase();
        String	toLower = to.toLowerCase();
        if (fromLower.equals("milano"))
        {
            switch(toLower) {
                case "monza": return 15;
                case "osnago": return 30;
                case "lecco": return 45;
            }
        }
        else if (fromLower.equals("monza"))
        {
            switch(toLower){
                case "milano": return 15;
                case "osnago": return 15;
                case "lecco": return 30;
            }
        }
        else if (fromLower.equals("osnago"))
        {
            switch(toLower) {
                case "milano": return 30;
                case "monza": return 15;
                case "lecco": return 15;
            }
        }
        else if (fromLower.equals("lecco"))
        {
            switch(toLower) {
                case "milano": return 45;
                case "monza": return 30;
                case "osnago": return 15;
            }
        }
        return (0);
    }
}