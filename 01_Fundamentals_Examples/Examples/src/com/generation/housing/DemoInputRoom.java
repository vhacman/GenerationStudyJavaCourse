package com.generation.housing;

import com.generation.library.Console;

/**
 * Classe di dimostrazione per l'input e il calcolo dell'area di una stanza.
 * Questa classe permette all'utente di inserire le dimensioni di una stanza
 * tramite console e visualizza l'area calcolata.
 */
public class DemoInputRoom
{
    /**
     * Metodo principale che esegue il programma.
     *
     * Flusso di esecuzione:
     * 1. Crea un oggetto Room vuoto
     * 2. Richiede all'utente di inserire le dimensioni dei due lati
     * 3. Valida i dati inseriti
     * 4. Calcola e visualizza l'area della stanza oppure un messaggio di errore
     *
     * @param args Argomenti da linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // Crea una nuova stanza utilizzando il costruttore vuoto
        Room r = new Room();

        // Richiede e legge la lunghezza del primo lato
        Console.print("Inserire side1: ");
        r.side1 = Console.readDouble();

        // Richiede e legge la lunghezza del secondo lato
        Console.print("Inserire side2: ");
        r.side2 = Console.readDouble();

        // Valida i dati e calcola l'area oppure mostra un messaggio di errore
        // Utilizza l'operatore ternario per decidere cosa visualizzare:
        // - Se isValid() ritorna true: mostra l'area calcolata
        // - Se isValid() ritorna false: mostra "dati non validi"
        Console.print(r.isValid() ? "Area: " + r.getArea() + " m²" : "Errore: dati non validi");
    }
}