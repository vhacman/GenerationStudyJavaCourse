package com.generation.demo;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo che illustra operazioni di filtro su liste.
 * Mostra come filtrare elementi da una collezione in base a criteri specifici.
 */
public class Demo004Liste
{
    /**
     * Metodo principale che esegue le operazioni di filtro sulle liste.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // Creo una lista di stringhe per memorizzare i nomi
        List<String> names = new ArrayList<String>();
        // Aggiungo vari nomi alla lista
        names.add("Lorenzo");
        names.add("Simone");
        names.add("Ferdinando");
        names.add("Giovanni");
        names.add("Jojo");
        names.add("Moussom");
        names.add("Andrea");
        names.add("Annibale");
        filtraEStampaNomiCorti(names);
        filtraEStampaNomiConVocaleConOR(names);
        filtraEStampaNomiConVocaleConArray(names);
    }

    /**
     * Filtra e stampa i nomi corti (lunghezza minore o uguale a 6 caratteri).
     *
     * @param names lista di nomi da filtrare
     */
    private static void filtraEStampaNomiCorti(List<String> names)
    {
        // Creo una nuova lista vuota dove metterò solo i nomi corti
        List<String> shortNames = new ArrayList<String>();
        // Esempio di FILTRO
        // Cosa vuol dire filtro:
        // - Partire da un insieme A1 di elementi di tipo a
        // - Arrivare a un insieme A2 di elementi di tipo a
        // - Con A2 <= A1. A2 è un sottoinsieme, potenzialmente coincidente di A1
        // Scorro tutti i nomi della lista originale
        for(String name : names)
            // Se il nome ha 6 caratteri o meno, lo aggiungo alla lista dei nomi corti
            if(name.length() <= 6)
                shortNames.add(name);
        // Stampo i nomi corti che ho trovato
        System.out.println("Nomi corti (<=6 caratteri):");
        System.out.println(shortNames);
        // Output: [Simone, Jojo, Andrea]
    }

    /**
     * Filtra e stampa i nomi che iniziano con vocale usando operatore OR.
     *
     * @param names lista di nomi da filtrare
     */
    private static void filtraEStampaNomiConVocaleConOR(List<String> names)
    {
        System.out.println("\nMetodo 1 - Con operatore OR:");
        // Creo una nuova lista per i nomi che iniziano con una vocale
        List<String> vowelNames1 = new ArrayList<String>();
        // Scorro di nuovo tutti i nomi
        for(String name : names)
        {
            // Prendo solo la prima lettera del nome (charAt mi dà il carattere in posizione 0)
            char firstChar = name.charAt(0);
            // Controllo se la prima lettera è una delle vocali maiuscole
            // Uso || (OR) per dire "se è A OPPURE E OPPURE I OPPURE O OPPURE U"
            if(firstChar == 'A' || firstChar == 'E' || firstChar == 'I' || firstChar == 'O' || firstChar == 'U')
                // Se inizia con una vocale, aggiungo il nome alla lista
                vowelNames1.add(name);
        }
        // Stampo i nomi che iniziano con vocale
        System.out.println(vowelNames1);    // Output: [Andrea, Annibale]
    }

    /**
     * Filtra e stampa i nomi che iniziano con vocale usando un array di vocali.
     * Questo metodo è più scalabile del metodo OR se ci sono molte vocali da controllare.
     *
     * @param names lista di nomi da filtrare
     */
    private static void filtraEStampaNomiConVocaleConArray(List<String> names)
    {
        System.out.println("\nMetodo 2 - Con array di vocali:");
        // Invece di scrivere tutte le vocali con ||, le metto in un array
        // Così posso controllare con un ciclo se la lettera è tra queste
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        // Creo un'altra lista vuota per i nomi con vocale (metodo alternativo)
        List<String> vowelNames2 = new ArrayList<String>();
        // Scorro ancora tutti i nomi della lista originale
        for(String name : names)
        {
            // Prendo la prima lettera del nome
            char firstChar = name.charAt(0);
            // Ora scorro l'array delle vocali per vedere se c'è un match
            for(char vowel : vowels)
            {
                // Se la prima lettera del nome è uguale alla vocale che sto controllando
                if(firstChar == vowel)
                {
                    // Aggiungo il nome alla lista
                    vowelNames2.add(name);
                    // break serve per uscire subito dal ciclo delle vocali
                    // perché ho già trovato che inizia con vocale, non serve controllare le altre
                    break;
                }
            }
        }
        // Stampo di nuovo i nomi che iniziano con vocale (stesso risultato di prima)
        System.out.println(vowelNames2);        // Output: [Andrea, Annibale]
    }
}
