package com.generation.demo;

/**
 * Demo che illustra le operazioni base sugli array (vettori) in Java.
 * Mostra come iterare un array in ordine normale, inverso e parziale.
 */
public class Demo001Vettori
{
    /**
     * Metodo principale che esegue tutte le dimostrazioni sugli array.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // Creo un array di 6 altezze in centimetri
        int[] heights = {170, 170, 165, 180, 195, 190};
        stampaInOrdine(heights);
        stampaInOrdineInverso(heights);
        stampaUltimaMetaDaIndice(heights, 3);
        stampaUltimaMetaDivisa(heights);
    }

    /**
     * Stampa gli elementi dell'array in ordine normale (dall'inizio alla fine).
     *
     * @param heights array di altezze da stampare
     */
    private static void stampaInOrdine(int[] heights)
    {
        System.out.println("Stampa in ordine");
        // Ciclo dall'inizio alla fine: parte da 0 e arriva a 5
        for(int i = 0; i < heights.length; i++)
            System.out.println(heights[i]); // Stampa: 170, 170, 165, 180, 195, 190
    }

    /**
     * Stampa gli elementi dell'array in ordine inverso (dalla fine all'inizio).
     *
     * @param heights array di altezze da stampare
     */
    private static void stampaInOrdineInverso(int[] heights)
    {
        System.out.println("Stampa in ordine inverso");
        // Ciclo al contrario: parto dall'ultimo indice e vado fino a 0
        for(int i = heights.length - 1; i >= 0; i--)
            System.out.println(heights[i]); // Stampa: 190, 195, 180, 165, 170, 170
    }

    /**
     * Stampa l'ultima metà del vettore partendo da un indice specifico.
     *
     * @param heights array di altezze da stampare
     * @param startIndex indice da cui iniziare la stampa
     */
    private static void stampaUltimaMetaDaIndice(int[] heights, int startIndex)
    {
        System.out.println("Stampa ultima metà del vettore");
        System.out.println("Partendo da indice " + startIndex);
        // Inizio da posizione specificata e vado fino alla fine
        for(int i = startIndex; i < heights.length; i++)
            System.out.println(heights[i]); // Stampa: 180, 195, 190 (3 elementi)
    }

    /**
     * Stampa l'ultima metà del vettore calcolando automaticamente l'indice di metà.
     *
     * @param heights array di altezze da stampare
     */
    private static void stampaUltimaMetaDivisa(int[] heights)
    {
        System.out.println("Dividendo l'array a metà");
        // Divido la lunghezza per 2: 6/2=3, quindi parto da indice 3
        // È lo stesso risultato di sopra, ma calcolato automaticamente
        for(int i = heights.length / 2; i < heights.length; i++)
            System.out.println(heights[i]); // Stampa: 180, 195, 190 (stessi 3 elementi)
    }
}
