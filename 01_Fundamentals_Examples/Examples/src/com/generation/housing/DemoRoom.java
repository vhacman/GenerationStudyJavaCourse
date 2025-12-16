package com.generation.housing;

import com.generation.library.Console;

/**
 * Classe di dimostrazione per la creazione e l'utilizzo di oggetti Room.
 * Mostra tre diversi modi di istanziare una stanza:
 * 1. Costruttore vuoto con assegnazione diretta delle proprietà
 * 2. Costruttore con due parametri (side1, side2)
 * 3. Costruttore completo con tre parametri (side1, side2, external)
 */
public class DemoRoom
{
    /**
     * Metodo principale che esegue il programma.
     * Crea tre stanze diverse utilizzando i vari costruttori disponibili
     * e visualizza l'area di ciascuna stanza.
     * 
     * @param args Argomenti da linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        // ========== PRIMA STANZA ==========
        // Utilizzo del costruttore vuoto seguito da assegnazione diretta
        Room r1 = new Room();
        r1.side1 = 10;      // Imposta il primo lato a 10 metri
        r1.side2 = 5;       // Imposta il secondo lato a 5 metri
        r1.external = true; // Indica che la stanza ha pareti esterne
        
        // Visualizza l'area della prima stanza (10 * 5 = 50 m²)
        Console.print("Area stanza 1: " + r1.getArea() + " m²");

        // ========== SECONDA STANZA ==========
        // Utilizzo del costruttore con due parametri (side1, side2)
        // external rimane al valore predefinito (false)
        Room r2 = new Room(5, 6);
        
        // Visualizza l'area della seconda stanza (5 * 6 = 30 m²)
        Console.print("Area stanza 2: " + r2.getArea() + " m²");

        // ========== TERZA STANZA ==========
        // Utilizzo del costruttore completo con tutti e tre i parametri
        Room r3 = new Room(6, 7, true);
        
        // Visualizza l'area della terza stanza (6 * 7 = 42 m²)
        Console.print("Area stanza 3: " + r3.getArea() + " m²");
    }
}