package com.generation.mh.model.entities;

import com.generation.util.Console;

/**
 * Enum che rappresenta i tipi di stanza disponibili al Monster Hotel.
 * Ogni tipo ha una capacità massima e un prezzo per notte.
 */
public enum RoomType
{
    // ========== DEFINIZIONE TIPI STANZA ==========  
    /**
     * Stanza SINGOLA - Intima e accogliente
     * Capacità: 1 ospite
     * Prezzo: €50 per notte
     */
    SINGOLA(
        1,      // maxGuests
        50.0,   // pricePerNight
        "🛏️",   // emoji
        "Singola"
    ),
    
    /**
     * Stanza DOPPIA - Spaziosa e confortevole
     * Capacità: 3 ospiti
     * Prezzo: €100 per notte
     */
    DOPPIA(
        3,
        100.0,
        "🛏️🛏️",
        "Doppia"
    ),
    
    /**
     * SUITE - Lusso e spazio per tutta la famiglia mostruosa
     * Capacità: 5 ospiti
     * Prezzo: €300 per notte
     */
    SUITE(
        5,
        300.0,
        "👑",
        "Suite"
    );
	
    // ========== ATTRIBUTI ==========
	private final int maxGuests; // Numero massimo di ospiti per questo tipo di stanza
	private final double pricePerNight; //Prezzo per notte in euro
	private final String emoji; //Emoji rappresentativa del tipo di stanza
	private final String displayName;
	
	 /**
     * Costruttore dell'enum RoomType
     * 
     * @param maxGuests Capacità massima ospiti
     * @param pricePerNight Prezzo per notte in euro
     * @param emoji Emoji rappresentativa
     * @param displayName Nome italiano
     */
	private RoomType(int maxGuests, double pricePerNight, String emoji, String displayName)
	{
		this.maxGuests = maxGuests;
		this.pricePerNight = pricePerNight;
		this.emoji = emoji;
		this.displayName = displayName;
	}
	
	// ================ GETTERS ========================= 
	public int getMaxGuests() { return maxGuests; }
	public double getPricePerNight() { return pricePerNight;}
	public String getEmoji() { return emoji;}
	public String getDisplayName() { return displayName;}
	
    // ========== METODI DI CALCOLO ==========

	
	 /**
     * Calcola il costo totale della stanza per un dato numero di notti
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.getRoomCost()
     * - Nel metodo Booking.calculateTotalCost()
     * - In preventivi e quotazioni
     * @param nights Numero di notti
     * @return Costo totale stanza
     */
	public double getTotalRoomCost(int nights)
	{
		return pricePerNight * (nights < 0 ? 0 : nights);
	}
    // ========== METODI DI VALIDAZIONE ==========

	
    /**
     * Verifica se la stanza può ospitare un certo numero di persone
     * 
     * DOVE SI USA:
     * - In validazioni future se aggiungi numero ospiti a Booking
     * - In sistemi di filtro/ricerca stanze
     * 
     * @param guests Numero di ospiti
     * @return true se la stanza è capiente
     */
	public boolean canAccomodate(int guests)
	{
		return guests > 0 && guests <= maxGuests;
	}
    // ========== METODI DI UTILITÀ ==========

	public String getDescription()
	{
	    String guestLabel = maxGuests == 1 ? "Ospite" : "Ospiti";
	    
	    return emoji + " " + displayName + 
	           " - Capacità: " + maxGuests + " " + guestLabel + 
	           " - €" + pricePerNight + "/notte";
	}
	
	
    // ========== OVERRIDE TOSTRING ==========
    @Override
    public String toString() { return emoji + " " + displayName; }
	
}
