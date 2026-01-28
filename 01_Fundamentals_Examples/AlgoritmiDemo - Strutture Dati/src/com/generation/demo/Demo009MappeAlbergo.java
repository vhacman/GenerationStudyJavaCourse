package com.generation.demo;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo che illustra l'uso delle Map per gestire disponibilità di stanze in un hotel.
 * Simula l'Hotel Transilvania con stanze per diverse specie di ospiti.
 */
public class Demo009MappeAlbergo
{
    /**
     * Metodo principale che gestisce l'analisi di disponibilità stanze per specie.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        System.out.println("=== HOTEL TRANSILVANIA - DISPONIBILITÀ STANZE ===\n");
        // STEP 1: Mappa delle stanze disponibili per specie
        Map<String, Integer> stanzeDisponibiliPerSpecie = creaMappaStanzeDisponibili();
        // STEP 2: Array degli ospiti
        Person[] guests =
        {
            new Person("Lorenzo", "Millauro", "vampiro"),
            new Person("Carlo", "M", "umano"),
            new Person("Chiara", "Foniciello", "umano"),
            new Person("Ferdinando", "Primerano", "lycan"),
            new Person("Fernando", "Felipez", "lycan"),    // lycan: 2° ospite
            new Person("Dead", "Joe", "zombi")
        };
        // STEP 3: Calcola la mappa delle stanze RICHIESTE
        Map<String, Integer> stanzeRichieste = contaOspitiPerSpecie(guests);
        // STEP 4: CONFRONTO tra stanze richieste e stanze disponibili
        confrontaDisponibilitaStanze(stanzeRichieste, stanzeDisponibiliPerSpecie);
        stampaRiepilogoStanzeRichieste(stanzeRichieste);
    }

    /**
     * Crea una mappa con le stanze disponibili per specie.
     * SPECIE (String) -> NUMERO STANZE DISPONIBILI (Integer)
     *
     * @return mappa specie -> stanze disponibili
     */
    private static Map<String, Integer> creaMappaStanzeDisponibili()
    {
        // Welcome to Hotel Transilvania
        Map<String, Integer> stanzeDisponibiliPerSpecie = new HashMap<String, Integer>();
        stanzeDisponibiliPerSpecie.put("vampiro", 3);
        stanzeDisponibiliPerSpecie.put("umano", 3);
        stanzeDisponibiliPerSpecie.put("lycan", 1);
        stanzeDisponibiliPerSpecie.put("ghost", 10);
        return stanzeDisponibiliPerSpecie;
    }

    /**
     * Conta quanti ospiti ci sono per ogni specie.
     * SPECIE (String) -> NUMERO DI OSPITI (Integer)
     *
     * @param guests array di ospiti
     * @return mappa specie -> numero di ospiti
     */
    private static Map<String, Integer> contaOspitiPerSpecie(Person[] guests)
    {
        Map<String, Integer> stanzeRichieste = new HashMap<String, Integer>();
        for(Person guest : guests)
        {
            String specie = guest.species; // Isolo la CHIAVE (specie dell'ospite)
            // Verifico se ho già contato ospiti di questa specie
            if(stanzeRichieste.containsKey(specie))
            {
                // La specie esiste già: incremento il contatore
                int oldValue = stanzeRichieste.get(specie);
                int newValue = oldValue + 1;
                stanzeRichieste.put(specie, newValue);
            }
            else
                // La specie NON esiste ancora: la aggiungo con valore 1
                stanzeRichieste.put(specie, 1);
        }        // Risultato stanzeRichieste:
        // {"vampiro"=1, "umano"=2, "lycan"=2, "zombi"=1}
        return stanzeRichieste;
    }

    /**
     * Confronta le stanze richieste con le stanze disponibili e stampa il risultato.
     *
     * @param stanzeRichieste mappa specie -> numero di ospiti
     * @param stanzeDisponibiliPerSpecie mappa specie -> stanze disponibili
     */
    private static void confrontaDisponibilitaStanze(Map<String, Integer> stanzeRichieste, Map<String, Integer> stanzeDisponibiliPerSpecie)
    {
        // Ciclo attraverso tutte le specie che hanno fatto richiesta
        for(String specie : stanzeRichieste.keySet())
        {
            // Prendo il numero di stanze richieste per questa specie
            int richieste = stanzeRichieste.get(specie);
            // Prendo il numero di stanze disponibili
            // Se la specie non è nella mappa disponibili, uso 0 come default
            int disponibili = stanzeDisponibiliPerSpecie.containsKey(specie) ?
                              stanzeDisponibiliPerSpecie.get(specie) : 0;
            // Confronto richieste vs disponibili
            if(richieste <= disponibili)
                System.out.println("Riusciamo a ospitare " + specie + " (richieste: " + richieste + ", disponibili: " + disponibili + ")");
            else
                System.out.println("NON riusciamo a ospitare " + specie + ", mancano " + (richieste - disponibili) + " stanze" +
                																	" (richieste: " + richieste + ", disponibili: " + disponibili + ")");
        }
    }

    /**
     * Stampa un riepilogo delle stanze richieste per specie.
     *
     * @param stanzeRichieste mappa specie -> numero di stanze richieste
     */
    private static void stampaRiepilogoStanzeRichieste(Map<String, Integer> stanzeRichieste)
    {
        System.out.println("\n=== RIEPILOGO STANZE RICHIESTE ===");
        for(String specie : stanzeRichieste.keySet())
            System.out.println(specie + ": " + stanzeRichieste.get(specie) + " stanze");
    }
}
