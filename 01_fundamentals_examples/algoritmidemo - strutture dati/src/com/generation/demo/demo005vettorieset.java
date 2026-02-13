package com.generation.demo;
import java.util.HashSet;
import java.util.Set;

/**
 * Demo che illustra l'uso di Set per trovare massimi multipli.
 * Un Set è un insieme NON ORDINATO e che NON AMMETTE RIPETIZIONI.
 * Perfetto quando voglio memorizzare elementi unici senza duplicati.
 */
public class Demo005VettoriESet
{
    /**
     * Metodo principale che esegue le operazioni con Set.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // Array di persone con altezza
        Person[] people =
        {
            new Person("Giovanni", 175),
            new Person("Bryton", 172),
            new Person("Carlo", 174),
            new Person("Adriano", 192),
            new Person("Andrea", 192)
        };
        trovaEStampaPersonePiuAlte(people);
        // Array di test con nome, cognome, data di nascita e specie
        Person[] peopleWithAge =
        {
            new Person("Simone", "Cacialli", "1999-01-10", "Umano"),
            new Person("Bryton", "Kengne", "2003-07-07", "Umano"),
            new Person("Roberto", "Bernasconi", "1993-08-29", "Umano"),
            new Person("Ferdinando", "Primerano", "0400-08-29", "Vampiro"),
            new Person("Luigi", "Rossi", "1993-08-29", "Umano"),
            new Person("Andrea", "Verdi", "1998-05-15", "Umano")
        };
        trovaEStampaUmaniPiuVecchi(peopleWithAge);
    }

    /**
     * Trova e stampa tutte le persone con l'altezza massima.
     *
     * @param people array di persone
     */
    private static void trovaEStampaPersonePiuAlte(Person[] people)
    {
        // Chiamo il metodo per ottenere le persone più alte
        // Può restituire più di una persona se hanno la stessa altezza massima
        Set<Person> tallest = getTallest(people);
        System.out.println("Le persone più alte:");
        for(Person p : tallest)
            System.out.println(p);
    }

    /**
     * Trova e stampa tutti gli umani con l'età massima (escludendo vampiri).
     *
     * @param peopleWithAge array di persone con età
     */
    private static void trovaEStampaUmaniPiuVecchi(Person[] peopleWithAge)
    {
        // Chiamo il metodo per ottenere gli umani più vecchi
        Set<Person> oldest = getOldest(peopleWithAge);
        System.out.println("\nGli umani più vecchi sono:");
        for(Person p : oldest)
            System.out.println(p);
    }

    /**
     * Trova tutte le persone con l'altezza massima.
     * Restituisce un Set perché potrebbero esserci più persone con la stessa altezza massima.
     *
     * @param people array di persone
     * @return Set di persone con altezza massima
     */
    private static Set<Person> getTallest(Person[] people)
    {
        // STEP 1: Trovare l'altezza massima
        int max = 0; // Parto da 0
        for(Person p : people)
            // Se questa persona è più alta del massimo trovato finora
            if(p.height > max)
                max = p.height; // Aggiorno l'altezza massima
        // STEP 2: Creare un Set vuoto per il risultato
        // HashSet implementa l'interfaccia Set
        Set<Person> res = new HashSet<Person>();
        // Scorro di nuovo tutte le persone
        for(Person p : people)
            // Se l'altezza di questa persona è ESATTAMENTE uguale al massimo
            if(p.height == max)
                res.add(p); // Aggiungo la persona al Set
        // Restituisco il Set con tutte le persone più alte
        // (potrebbero essere più di una se hanno la stessa altezza)
        return res;
    }

    /**
     * Trova tutti gli umani con l'età massima (escludendo i vampiri).
     * Restituisce un Set perché potrebbero esserci più persone con la stessa età massima.
     *
     * @param peopleWithAge array di persone
     * @return Set di umani con età massima
     */
    private static Set<Person> getOldest(Person[] peopleWithAge)
    {
        // STEP 1: Trovare l'età massima tra SOLO gli umani
        int maxAge = 0;
        for(Person p : peopleWithAge)
            // Controllo DUE condizioni insieme:
            // 1. La persona deve essere umana (NO vampiri!)
            // 2. La sua età deve essere maggiore del massimo trovato finora
            if(p.species.equals("Umano") && p.getAge() > maxAge)
                maxAge = p.getAge(); // Aggiorno l'età massima
        // STEP 2: Creare un Set con tutte le persone che hanno quell'età massima
        Set<Person> res = new HashSet<>();
        // Scorro di nuovo tutte le persone
        for(Person p : peopleWithAge)
            // Controllo DUE condizioni insieme:
            // 1. La persona deve essere umana
            // 2. La sua età deve essere ESATTAMENTE uguale al massimo trovato
            if(p.species.equals("Umano") && p.getAge() == maxAge)
                res.add(p); // Aggiungo la persona al Set risultato
        return res; // Restituisco il Set con tutti gli umani più vecchi
    }
}
