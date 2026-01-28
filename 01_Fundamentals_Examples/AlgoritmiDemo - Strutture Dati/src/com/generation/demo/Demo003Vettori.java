package com.generation.demo;

/**
 * Demo che illustra operazioni di riduzione sommando array elemento per elemento.
 * Mostra diversi approcci per trovare il massimo tra lunghezze di array.
 */
public class Demo003Vettori
{
    /**
     * Metodo principale che esegue le operazioni di somma tra array.
     *
     * @param args argomenti della linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // Array di lunghezze diverse
        int[] a = {3, 5, 10, 2, 1, 10, 1, 3};  // 8 elementi
        int[] b = {2, 5, 7, 2, 3};              // 5 elementi
        sommaEStampaDueArray(a, b);
        // RIDUZIONE da tre array a uno solo
        int[] c = {6, 7, 1, 10, 1};  // 5 elementi
        sommaEStampaTreArrayConTernario(a, b, c);
        sommaEStampaTreArrayConMathMax(a, b, c);
        sommaEStampaTreArrayConLoop(a, b, c);
    }

    /**
     * Somma due array elemento per elemento e stampa il risultato.
     *
     * @param a primo array
     * @param b secondo array
     */
    private static void sommaEStampaDueArray(int[] a, int[] b)
    {
        // Chiamo il metodo per sommare a+b
        int[] res = sumVectors(a, b);
        // Risultato: [5, 10, 17, 4, 4, 10, 1, 3]
        // res[0] = 3+2=5, res[1] = 5+5=10, ..., res[5] = 10+0=10
        // RIDUZIONE: parto da due array e ne ottengo uno solo
        System.out.println("Somma di due array:");
        for(int n : res)
            System.out.println(n);
    }

    /**
     * Somma tre array usando operatore ternario e stampa il risultato.
     *
     * @param a primo array
     * @param b secondo array
     * @param c terzo array
     */
    private static void sommaEStampaTreArrayConTernario(int[] a, int[] b, int[] c)
    {
        System.out.println("\nMetodo con ternario:");
        // Sommo a+b+c usando ternari annidati
        int[] res = sumVectorsTernary(a, b, c);
        for(int n : res)
            System.out.println(n);
    }

    /**
     * Somma tre array usando Math.max e stampa il risultato.
     *
     * @param a primo array
     * @param b secondo array
     * @param c terzo array
     */
    private static void sommaEStampaTreArrayConMathMax(int[] a, int[] b, int[] c)
    {
        System.out.println("\nMetodo con Math.max:");
        // Sommo a+b+c usando Math.max (più leggibile)
        int[] res = sumVectorsMathMax(a, b, c);
        for(int n : res)
            System.out.println(n);
    }

    /**
     * Somma tre array usando un ciclo for e stampa il risultato.
     *
     * @param a primo array
     * @param b secondo array
     * @param c terzo array
     */
    private static void sommaEStampaTreArrayConLoop(int[] a, int[] b, int[] c)
    {
        System.out.println("\nMetodo con ciclo for:");
        int[] res = sumVectorsLoop(a, b, c);
        for(int n : res)
            System.out.println(n);
    }

    /**
     * Somma DUE array elemento per elemento.
     * Se un array è più corto, i valori mancanti vengono considerati 0.
     *
     * @param a primo array
     * @param b secondo array
     * @return nuovo array con la somma elemento per elemento
     */
    private static int[] sumVectors(int[] a, int[] b)
    {
        // Trovo il più lungo tra i due array usando l'operatore ternario
        // Sintassi: condizione ? valore_se_true : valore_se_false
        int maxLength = a.length > b.length ? a.length : b.length;
        // Creo un nuovo array con la dimensione del più lungo
        // Quando creo un array devo specificarne la lunghezza
        int[] res = new int[maxLength];
        // Ciclo per ogni posizione del risultato
        for(int i = 0; i < maxLength; i++)
            // Se l'array ha un elemento a quell'indice lo uso, altrimenti metto 0
            // Ternario annidato: controllo se ogni array ha quell'indice
            res[i] = (a.length > i ? a[i] : 0) + (b.length > i ? b[i] : 0);
        return res;
    }

    /**
     * Somma TRE array usando operatore ternario annidato per trovare il massimo.
     * Se un array è più corto, i valori mancanti vengono considerati 0.
     *
     * @param a primo array
     * @param b secondo array
     * @param c terzo array
     * @return nuovo array con la somma elemento per elemento
     */
    private static int[] sumVectorsTernary(int[] a, int[] b, int[] c)
    {
        // Trovo il massimo tra tre valori con ternari annidati
        // Prima confronto a e b, poi il risultato con c
        int maxLength = a.length > b.length ?
                                                            (a.length > c.length ?
                                                                                                a.length : c.length)
                                                            : (b.length > c.length
                                                                                            ? b.length : c.length);

        int[] res = new int[maxLength];
        // Sommo elemento per elemento da tutti e tre gli array
        for(int i = 0; i < maxLength; i++)
            res[i] = (a.length > i ? a[i] : 0) + (b.length > i ? b[i] : 0) + (c.length > i ? c[i] : 0);
        return res;
    }

    /**
     * Somma TRE array usando Math.max (più leggibile del ternario).
     * Se un array è più corto, i valori mancanti vengono considerati 0.
     *
     * @param a primo array
     * @param b secondo array
     * @param c terzo array
     * @return nuovo array con la somma elemento per elemento
     */
    private static int[] sumVectorsMathMax(int[] a, int[] b, int[] c)
    {
        // Math.max() trova il massimo tra due valori
        // Annido Math.max per confrontare tre valori
        int maxLength = Math.max(Math.max(a.length, b.length), c.length);
        int[] res = new int[maxLength];
        // Stessa logica di somma elemento per elemento
        for(int i = 0; i < maxLength; i++)
            res[i] = (a.length > i ? a[i] : 0) + (b.length > i ? b[i] : 0) + (c.length > i ? c[i] : 0);
        return res;
    }

    /**
     * Somma TRE array usando ciclo for per trovare il massimo.
     * Se un array è più corto, i valori mancanti vengono considerati 0.
     *
     * @param a primo array
     * @param b secondo array
     * @param c terzo array
     * @return nuovo array con la somma elemento per elemento
     */
    private static int[] sumVectorsLoop(int[] a, int[] b, int[] c)
    {
        // Trovo il massimo iterando su un array di lunghezze
        int max = 0;
        int[] lengths = {a.length, b.length, c.length};
        for(int length : lengths)
            if(length > max)
                max = length;
        int[] res = new int[max];
        // Stessa logica di somma elemento per elemento
        for(int i = 0; i < max; i++)
            res[i] = (a.length > i ? a[i] : 0) +
                     (b.length > i ? b[i] : 0) +
                     (c.length > i ? c[i] : 0);
        return res;
    }
}
