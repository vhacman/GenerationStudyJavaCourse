package com.generation.demo;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo che illustra operazioni di riduzione su array di oggetti Person.
 * Mostra come trovare il massimo, il minimo e applicare filtri prima della riduzione.
 */
public class Demo004ListeEVettori
{
    /**
     * Metodo principale che esegue le operazioni di riduzione su array di persone.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // Creo un array di oggetti Person con varie persone
        Person[] people =
        {
            new Person("Simone", "Cacialli", "1999-11-25", "Umano"),
            new Person("Simona", "Cacialli", "1999-11-25", "Umano"),
            new Person("Simonetta", "Cacialli", "1999-11-25", "Umano"),
            new Person("Bryton", "Kengne Kamte", "2003-07-07", "Umano"),
            new Person("Roberto", "Bernasconi", "1993-08-29", "Umano"),
            new Person("Ferdinando", "Primerano", "0400-08-29", "Vampiro")
        };
        trovaEStampaPersonaPiuVecchia(people);
        trovaEStampaPersonaPiuGiovane(people);
        trovaEStampaUmanoPiuVecchio(people);
    }

    /**
     * Trova e stampa la persona più vecchia nell'array.
     *
     * @param people array di persone
     */
    private static void trovaEStampaPersonaPiuVecchia(Person[] people)
    {
        // RIDUZIONE: il più vecchio
        int maxAge = 0;         // Parto da 0 come età massima
        Person oldest = null;   // Qui salverò la persona più vecchia che troverò
        // Scorro tutte le persone nell'array
        for(Person p : people)
            // Se questa persona ha più anni del massimo che ho trovato finora
            if(p.getAge() > maxAge)
            {
                maxAge = p.getAge();                    // Aggiorno il massimo con l'età di questa persona
                oldest = p;                             // Salvo questa persona come la più vecchia
            }
        System.out.println("La persona più vecchia è " + oldest.firstName);
        System.out.println("Età " + oldest.getAge());
    }

    /**
     * Trova e stampa la persona più giovane nell'array.
     *
     * @param people array di persone
     */
    private static void trovaEStampaPersonaPiuGiovane(Person[] people)
    {
        // Qui invece di partire da 0, parto dal valore MASSIMO possibile
        // Così qualsiasi età che trovo sarà più piccola di questo
        int minAge = Integer.MAX_VALUE;         // Integer.MAX_VALUE = 2147483647 (il numero più grande che un int può contenere)
        Person youngest = null;
        // Scorro di nuovo tutte le persone
        for(Person p : people)
            // Se questa persona ha MENO anni del minimo trovato finora
            if(p.getAge() < minAge)
            {
                // Aggiorno il minimo con l'età di questa persona
                minAge = p.getAge();
                // Salvo questa persona come la più giovane
                youngest = p;
            }
        System.out.println("\nLa persona più giovane è " + youngest.firstName);
        System.out.println("Età " + youngest.getAge());
    }

    /**
     * Trova e stampa l'umano più vecchio nell'array (escludendo vampiri).
     * Applica prima un filtro per selezionare solo gli umani, poi trova il massimo.
     *
     * @param people array di persone
     */
    private static void trovaEStampaUmanoPiuVecchio(Person[] people)
    {
        // Qui faccio un doppio passaggio: prima filtro, poi cerco il massimo

        // Passo 1: FILTRO per creare lista di umani
        // Creo una lista vuota dove metterò solo gli umani
        List<Person> humans = new ArrayList<>();
        // Scorro tutte le persone
        for(Person p : people)
            // Se la specie di questa persona è "Umano"
            // Uso equals() perché devo confrontare stringhe (NON usare == con le stringhe!)
            if(p.species.equals("Umano"))
                humans.add(p);                        // Aggiungo questa persona alla lista degli umani
        System.out.println("\nNumero di umani: " + humans.size());                // size() mi dice quanti elementi ci sono nella lista
        // Passo 2: trovare il più vecchio nella lista filtrata degli umani
        int maxAgeHuman = 0;
        Person oldestHuman = null;
        // Ora scorro SOLO la lista degli umani (non più tutto l'array originale!)
        for(Person p : humans)
            // Controllo se questa persona è più vecchia del massimo trovato finora
            if(p.getAge() > maxAgeHuman)
            {
                maxAgeHuman = p.getAge();                // Aggiorno l'età massima tra gli umani
                oldestHuman = p;                         // Salvo questa persona come l'umano più vecchio
            }
        System.out.println("\nL'umano più vecchio è " + oldestHuman.firstName);
        System.out.println("Età " + oldestHuman.getAge());
    }
}
