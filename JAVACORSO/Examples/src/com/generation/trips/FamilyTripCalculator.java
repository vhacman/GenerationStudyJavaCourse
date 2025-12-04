package com.generation.trips;
import com.generation.library.Console;

/*
 * Abstract: una famiglia deve pianificare le vacanze in una città europea. 
 * Dormiranno nella stessa stanza, ma devono prenotare un biglietto aereo per ogni membro della famiglia. 
 * Devono anche stabilire un budget giornaliero per le spese, uguale per ogni membro della famiglia, 
 * e specificare il nome della città in cui andare, il nome e l'indirizzo dell'hotel.
 * Variabili di input: numero di persone, costo biglietto aereo per persona, numero di notti, budget
 * giornalieri, nome della città, nome dell'hotel, indirizzo dell'hotel. Di che tipi devono essere?
 * Variabili di output: un riepilogo della prenotazione che presenti tutti i dati inseriti,
 * oltre al costo totale della vacanza e al costo medio per persona.
 *
 * 
 */
public class FamilyTripCalculator
{
	//user input 
    private int		 	numPeople;                  // Numero di persone della famiglia (tipo: int)
    private double		flightCostPerPerson;     	// Costo del biglietto aereo per persona in euro (tipo: double)
    private int			numNights;                  // Numero di notti di soggiorno (tipo: int)
    private double 		dailyBudgetPerPerson;    	// Budget giornaliero per persona in euro (tipo: double)
    private String 		cityName;               	// Nome della città di destinazione (tipo: String)
    private String 		hotelName;               	// Nome dell'hotel (tipo: String)
    private String 		hotelAddress;            	// Indirizzo dell'hotel (tipo: String)
    
    // Queste variabili contengono i risultati dei calcoli
    private double		totalFlightCost;         	// Costo totale di tutti i biglietti aerei (numPeople * flightCostPerPerson)
    private double 		totalDailyExpenses;      	// Costo totale delle spese giornaliere (numPeople * dailyBudgetPerPerson * numNights)
    private double 		totalVacationCost;       	// Costo totale della vacanza (totalFlightCost + totalDailyExpenses)
    private double 		averageCostPerPerson;    	// Costo medio per persona (totalVacationCost / numPeople)
    
    /* Costruttore della classe FamilyTripCalculator
     * Quando viene creato un oggetto di questa classe, vengono eseguiti i tre metodi principali
     */
    public FamilyTripCalculator()
    {
        readInput();          // Legge i dati inseriti dall'utente
        calculateCosts();     // Calcola i costi totali e medi
        printSummary();       // Stampa il riepilogo della prenotazione
    }
    
    /**
     * Metodo readInput()
     * legge tutti i dati di input dall'utente
     * Utilizza i metodi della classe Console per stampare messaggi e leggere i valori
     */
    private void readInput()
    {
        Console.print("========== FAMILY VACATION PLANNER ==========\n");
        Console.print("Enter number of family members: ");
        numPeople = Console.readInt();
        Console.print("Enter flight cost per person (€): ");
        flightCostPerPerson = Console.readDouble();
        Console.print("Enter number of nights: ");
        numNights = Console.readInt();
        Console.print("Enter daily budget per person (€): ");
        dailyBudgetPerPerson = Console.readDouble();
        Console.print("Enter city name: ");
        cityName = Console.readString();
        Console.print("Enter hotel name: ");
        hotelName = Console.readString();
        Console.print("Enter hotel address: ");
        hotelAddress = Console.readString();
    }
    
    /**
     * Metodo calculateCosts():
     * calcola tutti i costi della vacanza
     * Esegue le operazioni matematiche necessarie per ottenere i totali
     */
    private void calculateCosts()
    {
        totalFlightCost = numPeople * flightCostPerPerson;
        totalDailyExpenses = numPeople * dailyBudgetPerPerson * numNights;
        totalVacationCost = totalFlightCost + totalDailyExpenses;
        averageCostPerPerson = totalVacationCost / numPeople;
    }
    
    /**
     * Metodo printSummary(): stampa il riepilogo completo della prenotazione
     * Mostra tutti i dati inseriti e i costi calcolati in formato ordinato
     */
    private void printSummary()
    {
        Console.print("\n========== VACATION BOOKING SUMMARY ==========\n");
        Console.print("Destination: " + cityName);
        Console.print("Hotel: " + hotelName);
        Console.print("Address: " + hotelAddress);
        Console.print("");
        Console.print("Number of travelers: " + numPeople);
        Console.print("Number of nights: " + numNights);
        Console.print("Flight cost per person: €" + flightCostPerPerson);
        Console.print("Daily budget per person: €" + dailyBudgetPerPerson);
        Console.print(""); 
        Console.print("--- COST BREAKDOWN ---");
        Console.print("Total flight cost: €" + totalFlightCost);
        Console.print("Total daily expenses: €" + totalDailyExpenses);
        Console.print("TOTAL VACATION COST: €" + totalVacationCost);
        Console.print("Average cost per person: €" + averageCostPerPerson);
        Console.print("");
        Console.print("=============================================");
    }
    
    /**
     * Metodo main: punto di ingresso dell'applicazione
     * Viene eseguito quando il programma viene avviato
     * Crea una nuova istanza (oggetto) della classe FamilyTripCalculator
     * Il costruttore esegue automaticamente tutti i metodi della classe
     */
    public static void main(String[] args)
    {
        // Crea un nuovo oggetto FamilyTripCalculator
        // Questo chiama automaticamente il costruttore che esegue:
        new FamilyTripCalculator();
    }
}