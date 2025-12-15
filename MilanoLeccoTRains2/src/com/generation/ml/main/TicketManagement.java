package com.generation.ml.main;

import com.generation.library.*;

/*
 * ╔═══════════════════════════════════════════════════════════════╗
 * ║          SISTEMA DI BIGLIETTERIA FERROVIARIA                  ║
 * ║                Milano Lecco Trains                            ║
 * ╚═══════════════════════════════════════════════════════════════╝
 * 
 *    Linea ferroviaria: Milano → Monza → Osnago → Lecco
 * 
 *     Funzionalità:
 *     - Selezione stazione di partenza e arrivo
 *     - Scelta della classe (1 o 2)
 *     - Calcolo automatico di distanza e prezzo
 *     - Gestione di biglietti multipli
 */
public class TicketManagement
{
    /**
     * Metodo principale del programma.
     * Gestisce il loop per l'inserimento di più biglietti.
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
    
    /*
     * ╔═══════════════════════════════════════════════════════════╗
     * ║  GESTIONE SINGOLO BIGLIETTO                               ║
     * ╚═══════════════════════════════════════════════════════════╝
     * 
     * Questo metodo:
     * 1. Chiede partenza (qualsiasi stazione valida)
     * 2. Chiede arrivo (stazione diversa da partenza)
     * 3. Chiede classe (1 o 2)
     * 4. Calcola km e prezzo
     * 5. Stampa il riepilogo
     */
    /**
     * Gestisce l'inserimento completo di un singolo biglietto.
     * Richiede all'utente partenza, destinazione e classe,
     * calcola distanza e prezzo, e stampa il riepilogo.
     */
    private static void printTicket()
    {
    	String 	departure, arrival;
        int 	level;
        double 	price;
        int		km;

        Console.print("Inserire partenza: ");
        departure = askStation("");

        Console.print("Inserire Destinazione: ");
        arrival = askStation(departure);

        Console.print("Inserire classe (1 o 2): ");
        level = askLevel();

        // Calcola distanza e prezzo
        km = calculateDistance(departure, arrival);
        price = (level == 1 ? 0.2 : 0.15) * km;
        
        // Stampa
        Console.print("Tratta: " + departure + " → " + arrival + " livello: " + level + " km " + km + " price " + price);
    }

    /*
     * ┌─────────────────────────────────────────────────────────┐
     * │  CHIEDI STAZIONE                                        │
     * └─────────────────────────────────────────────────────────┘
     * 
     * Stazioni valide: Milano, Lecco, Osnago, Monza
     * Confronto: case-insensitive (milano = Milano = MILANO)
     */
    /**
     * Chiede all'utente di inserire una stazione valida.
     * Continua a richiedere l'input finché non viene inserita
     * una stazione valida e diversa da quella da escludere.
     * 
     * @param exclude La stazione da escludere (stringa vuota se nessuna esclusione).
     *                Usato per evitare che partenza = arrivo.
     * @return La stazione valida inserita dall'utente
     */
    private static String askStation(String exclude)
    {
        String station;
        boolean valid;

        do
        {
            station = Console.readString();
            String stationLower = station.toLowerCase();

            switch(stationLower)
            {
                case "milano", "lecco", "osnago", "monza":
                    if (!exclude.isEmpty() && stationLower.equals(exclude.toLowerCase()))
                    {
                        Console.print("Stazione già selezionata. Riprova: ");
                        valid = false;
                    }
                    else
                    {
                        valid = true;
                    }
                    break;

                default:
                    Console.print("Stazione non valida. Riprova: ");
                    valid = false;
            }
        } while (!valid);

        return station;
    }

    /*
     * ┌─────────────────────────────────────────────────────────┐
     * │  CHIEDI CLASSE                                          │
     * └─────────────────────────────────────────────────────────┘
     * 
     * Classe 1: Prima classe (più costosa, €0.20/km)
     * Classe 2: Seconda classe (standard, €0.15/km)
     */
    /**
     * Chiede all'utente di inserire una classe valida (1 o 2).
     * Continua a richiedere l'input finché non viene inserito
     * un valore valido.
     * 
     * @return La classe inserita (1 o 2)
     */
    private static int askLevel()
    {
        int res;

        do
        {
            res = Console.readInt();
            if (res != 1 && res != 2)
            {
                Console.print("Non valido. Inserire 1 o 2: ");
            }
        } while (res != 1 && res != 2);

        return res;
    }

    /*
     * ┌─────────────────────────────────────────────────────────┐
     * │  CALCOLA DISTANZA                                       │
     * └─────────────────────────────────────────────────────────┘
     * 
     *  Mappa della linea ferroviaria:
     * 
     *     Milano ──15km── Monza ──15km── Osnago ──15km── Lecco
     *        0              15             30              45
     * 
     *  Tabella delle distanze:
     *  ┌─────────┬────────┬───────┬─────────┬────────┐
     *  │ Da/A    │ Milano │ Monza │ Osnago  │ Lecco  │
     *  ├─────────┼────────┼───────┼─────────┼────────┤
     *  │ Milano  │   -    │  15   │   30    │   45   │
     *  │ Monza   │  15    │   -   │   15    │   30   │
     *  │ Osnago  │  30    │  15   │    -    │   15   │
     *  │ Lecco   │  45    │  30   │   15    │    -   │
     *  └─────────┴────────┴───────┴─────────┴────────┘
     */
    /**
     * Calcola la distanza in chilometri tra due stazioni.
     * Utilizza una mappa predefinita delle distanze sulla linea
     * ferroviaria Milano-Lecco.
     * 
     * @param from La stazione di partenza
     * @param to La stazione di arrivo
     * @return La distanza in chilometri tra le due stazioni
     */
    private static int calculateDistance(String from, String to)
    {
        String fromLower = from.toLowerCase();
        String toLower = to.toLowerCase();

        // Mappa delle distanze tra stazioni (km)
        // Linea: Milano (0) → Monza (15) → Osnago (30) → Lecco (45)

        // Milano → altre stazioni
        if (fromLower.equals("milano"))
        {
            switch(toLower)
            {
                case "monza": return 15;
                case "osnago": return 30;
                case "lecco": return 45;
            }
        }
        // Monza → altre stazioni
        else if (fromLower.equals("monza"))
        {
            switch(toLower)
            {
                case "milano": return 15;
                case "osnago": return 15;
                case "lecco": return 30;
            }
        }
        // Osnago → altre stazioni
        else if (fromLower.equals("osnago"))
        {
            switch(toLower)
            {
                case "milano": return 30;
                case "monza": return 15;
                case "lecco": return 15;
            }
        }
        // Lecco → altre stazioni
        else if (fromLower.equals("lecco"))
        {
            switch(toLower)
            {
                case "milano": return 45;
                case "monza": return 30;
                case "osnago": return 15;
            }
        }

        return 0;  // Fallback
    }

}