package com.generation.model.entities;



/**
 * Entity che rappresenta un biglietto ferroviario.
 * Gestisce il calcolo del prezzo in base alla distanza percorsa
 * e alla classe di viaggio scelta (prima o seconda classe).
 */
public class Ticket
{
    // ========== STATO DI CLASSE ==========
    private final static double PRICEPERKMFIRSTCLASS = 0.2;
    private final static double PRICEPERKMSECONDCLASS = 0.1;

    // ========== PROPRIETÀ DI OGGETTO (STATO) ==========
    public int id;
    public int km;
    public int level;


    // ========== COSTRUTTORI ==========
   /**
     * Costruttore vuoto che crea un biglietto con valori predefiniti.
     * Tutti gli attributi vengono inizializzati a 0.
     */
    public Ticket() {}
    
    // ========== METODI DI OGGETTO (COMPORTAMENTO) ==========
    
    /**
     * Calcola il prezzo del biglietto in base ai chilometri e alla classe.
     * Utilizza le costanti di classe per determinare la tariffa appropriata.
     * Nota: L'oggetto vede la propria classe (può accedere alle costanti static)
     * e può accedere ai propri attributi di istanza.
     * 
     * @return Il prezzo totale del biglietto in euro
     */
    public double getPrice()
    {
        return km * (level == 1 ? PRICEPERKMFIRSTCLASS : PRICEPERKMSECONDCLASS);
    }
    
    /**
     * Verifica se il biglietto è valido controllando che i chilometri siano positivi
     * e che il livello sia 1 (prima classe) o 2 (seconda classe).
     * 
     * @return true se km > 0 e level è 1 o 2, false altrimenti
     */
    public boolean isValid()
    {
    	return (id > 0 && km > 0 && (level == 1 || level == 2));
    }
}
