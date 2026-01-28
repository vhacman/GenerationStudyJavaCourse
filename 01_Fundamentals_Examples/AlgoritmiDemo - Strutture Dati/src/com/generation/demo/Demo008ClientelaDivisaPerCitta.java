package com.generation.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo che illustra l'uso delle Map (HashMap) per contare occorrenze e confrontare disponibilità.
 * Una MAPPA è una struttura dati CHIAVE -> VALORE dove ogni chiave è unica.
 */
public class Demo008ClientelaDivisaPerCitta
{
    /**
     * Metodo principale che gestisce l'analisi di disponibilità per città.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // STEP 1: Creo una mappa con i posti disponibili per città
        Map<String, Integer> mappaPostiDisponibili = creaMappaPostiDisponibili();
        // get(chiave): recupera il valore associato alla chiave
        System.out.println("Posti disponibili a Milano: " + mappaPostiDisponibili.get("Milano"));
        // Output: 5
        // STEP 2: Array di studenti
        Person[] students =
        {
            new Person("Antonio", "Agrigento"),
            new Person("Alessio", "Torino"),
            new Person("Piero", "Roma"),
            new Person("Gabriela", "Ladispoli"),
            new Person("Giovanni", "Nettuno"),
            new Person("Simone", "Roma"),      // Roma: 2° studente
            new Person("Adriano", "Roma"),     // Roma: 3° studente
            new Person("Jojo", "Bologna")
        };
        // STEP 3: CONTEGGIO - Creo una mappa per contare quanti studenti per città
        Map<String, Integer> mappaPostiRichiesti = contaStudentiPerCitta(students);
        // STEP 4: CONFRONTO tra posti richiesti e posti disponibili
        confrontaDisponibilita(mappaPostiRichiesti, mappaPostiDisponibili);
    }

    /**
     * Crea una mappa con i posti disponibili per città.
     * CITTÀ (String) -> POSTI DISPONIBILI (Integer)
     *
     * @return mappa città -> posti disponibili
     */
    private static Map<String, Integer> creaMappaPostiDisponibili()
    {
        Map<String, Integer> mappaPostiDisponibili = new HashMap<String, Integer>();
        // put(chiave, valore): inserisce o aggiorna un valore nella mappa
        mappaPostiDisponibili.put("Milano", 5);
        mappaPostiDisponibili.put("Torino", 3);
        mappaPostiDisponibili.put("Roma", 2);
        mappaPostiDisponibili.put("Palermo", 3);
        return mappaPostiDisponibili;
    }

    /**
     * Conta quanti studenti ci sono per ogni città.
     * CITTÀ (String) -> NUMERO DI STUDENTI (Integer)
     *
     * @param students array di studenti
     * @return mappa città -> numero di studenti
     */
    private static Map<String, Integer> contaStudentiPerCitta(Person[] students)
    {
        Map<String, Integer> mappaPostiRichiesti = new HashMap<String, Integer>();
        // Ciclo attraverso tutti gli studenti
        for(Person p : students)
        {
            String city = p.city; // Isolo la CHIAVE (città dello studente)
            // Devo chiedermi: ho già questa città nella mappa?
            // containsKey(chiave): restituisce true se la chiave esiste nella mappa
            if(mappaPostiRichiesti.containsKey(city))
            {
                // La città esiste già: incremento il contatore
                int oldValue = mappaPostiRichiesti.get(city); // Prendo il valore attuale
                int newValue = oldValue + 1;                    // Lo incremento
                mappaPostiRichiesti.put(city, newValue);       // Aggiorno la mappa
            }
            else
                // La città NON esiste ancora: la aggiungo con valore 1
                mappaPostiRichiesti.put(city, 1);
        }
        // Risultato mappaPostiRichiesti:
        // {"Roma"=3, "Nettuno"=1, "Ladispoli"=1, "Torino"=1, "Bologna"=1, "Agrigento"=1}
        return mappaPostiRichiesti;
    }

    /**
     * Confronta i posti richiesti con i posti disponibili e stampa il risultato.
     *
     * @param mappaPostiRichiesti mappa città -> numero di studenti
     * @param mappaPostiDisponibili mappa città -> posti disponibili
     */
    private static void confrontaDisponibilita(Map<String, Integer> mappaPostiRichiesti,
                                               Map<String, Integer> mappaPostiDisponibili)
    {
        System.out.println("\n--- ANALISI DISPONIBILITÀ ---");
        // keySet(): restituisce un Set con tutte le CHIAVI della mappa
        for(String city : mappaPostiRichiesti.keySet())
        {
            // Prendo il numero di posti richiesti per questa città
            int richiesti = mappaPostiRichiesti.get(city);
            // Per Roma: richiesti = 3
            // Prendo il numero di posti disponibili
            // Se la città non è nella mappa disponibili, uso 0 come default
            int disponibili = mappaPostiDisponibili.containsKey(city) ?
                              mappaPostiDisponibili.get(city) : 0;
            // Per Roma: disponibili = 2
            // Per Agrigento: disponibili = 0 (non presente nella mappa)
            // Confronto richiesti vs disponibili
            if(richiesti <= disponibili)
                System.out.println("Riusciamo a servire " + city +   " (richiesti: " + richiesti + ", disponibili: " + disponibili + ")");
            else
                System.out.println("Non riusciamo a servire " + city +  ", mancano " + (richiesti - disponibili) + " posti" +
                										" (richiesti: " + richiesti + ", disponibili: " + disponibili + ")");
        }
    }
}
