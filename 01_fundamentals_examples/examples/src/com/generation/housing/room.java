package com.generation.housing;

import com.generation.library.*;

/**
 * Rappresenta una stanza con dimensioni e caratteristiche specifiche.
 * Ogni stanza è definita da due lati (lunghezza e larghezza) e può essere
 * classificata come esterna o interna.
 */
public class Room
{
    // ========== PROPRIETÀ DI ISTANZA ==========
    // Le proprietà appartengono agli oggetti, non alla classe.
    // La classe è solo il modello da cui vengono creati gli oggetti.
    
    /** Lunghezza del primo lato della stanza (in metri) */
    double side1;
    
    /** Lunghezza del secondo lato della stanza (in metri) */
    double side2;
    
    /** Indica se la stanza ha pareti esterne (true) o è completamente interna (false) */
    boolean external;

    // ========== COSTRUTTORI ==========
    
    /**
     * Costruttore vuoto (default).
     * Crea una stanza con valori predefiniti (0.0 per i lati, false per external).
     * 
     * NOTA: Questo costruttore deve essere dichiarato esplicitamente perché
     * quando si scrive un costruttore parametrico, il costruttore vuoto
     * non viene più fornito automaticamente dal compilatore.
     */
    public Room() {}
    
    /**
     * Costruttore con parametri per inizializzare i lati della stanza.
     * 
     * Il costruttore ha lo stesso nome della classe e un return implicito
     * dello stesso tipo della classe. Non è necessario scrivere "return",
     * il costruttore restituisce automaticamente l'oggetto Room creato.
     * 
     * @param side1 Lunghezza del primo lato della stanza
     * @param side2 Lunghezza del secondo lato della stanza
     */
    public Room(double side1, double side2)
    {
        // this.side1 --> si riferisce alla proprietà dell'oggetto
        // side1 --> si riferisce al parametro ricevuto
        
        // "this" rappresenta l'oggetto che sto costruendo in questo momento.
        // Nasce nel momento in cui viene chiamato il costruttore.
        this.side1 = side1;
        this.side2 = side2;
        // external rimane al valore predefinito (false)
    }

    /**
     * Costruttore completo con tutti i parametri.
     * Permette di specificare anche se la stanza è esterna o interna.
     * 
     * @param side1 Lunghezza del primo lato della stanza
     * @param side2 Lunghezza del secondo lato della stanza
     * @param external True se la stanza ha pareti esterne, false altrimenti
     */
    public Room(double side1, double side2, boolean external)
    {
        this.side1 = side1;
        this.side2 = side2;
        this.external = external;
    }

    // ========== METODI (COMPORTAMENTO) ==========
    
    /**
     * Calcola l'area della stanza moltiplicando i due lati.
     * 
     * @return L'area della stanza in metri quadrati
     */
    public double getArea()
    {
        return side1 * side2;
    }

    /**
     * Verifica se le dimensioni della stanza sono valide.
     * Una stanza è considerata valida se entrambi i lati hanno
     * una lunghezza maggiore di zero.
     * 
     * @return true se entrambi i lati sono maggiori di 0, false altrimenti
     */
    public boolean isValid()
    {
        return side1 > 0 && side2 > 0;
    }
}