package com.generation.mh.model.entities;

/**
 * Enum che rappresenta le diverse specie di clienti del Monster Hotel.
 * Ogni specie ha caratteristiche uniche, vincoli specifici e costi extra.
 */
public enum Species
{    
    // ========== DEFINIZIONE SPECIE ==========
    /**
     * Vampiro - Creature della notte che rifuggono la luce del sole
     * VINCOLO: Possono stare SOLO su piani negativi (sotterranei)
     * EXTRA: Nessuno
     */
    VAMPIRE
    (
        0.0,      // extraCostPercentage - nessun extra percentuale
        0.0,      // flatExtraCost - nessun costo fisso extra
        "🧛",     // emoji
        "Vampiro" // nome italiano
    ),
    
    /**
     * Licantropo - Esseri che si trasformano con la luna piena
     * VINCOLO: Devono avere almeno una stanza DOPPIA (non possono stare in SINGOLA)
     * EXTRA: Nessuno
     */
    WEREWOLF
    (
        0.0,
        0.0,
        "🐺",
        "Licantropo"
    ),
    
    /**
     * Sirena - Creature marine che necessitano di acqua
     * VINCOLO: Nessuno specifico sul piano o tipo stanza
     * EXTRA: +50% sul totale (necessità di piscina/vasca speciale)
     */
    MERMAID
    (
        50.0,     // +50% sul totale
        0.0,
        "🧜",
        "Sirena"
    ),
    
    /**
     * Homo Sapiens - Umani comuni (e sospetti)
     * VINCOLO: Nessuno
     * EXTRA: +100€ fissi (non ci fidiamo di loro)
     */
    HOMO_SAPIENS
    (
        0.0,
        100.0,    // +100€ fissi
        "👤",
        "Homo Sapiens"
    );
    
    // ========== ATTRIBUTI ==========
    private final double extraCostPercentage;
    private final double extraCostFlat;
    private final String emoji;
    private final String displayName;
    
    /**
     * Costruttore dell'enum Species
     * 
     * @param extraCostPercentage Percentuale extra sul totale (0-100)
     * @param flatExtraCost Costo fisso extra in euro
     * @param emoji Emoji della specie
     * @param displayName Nome visualizzabile in italiano
     */
    Species(double extraCostPercentage, double extraCostFlat, String emoji, String displayName) {
        this.extraCostPercentage = extraCostPercentage;
        this.extraCostFlat = extraCostFlat;
        this.emoji = emoji;
        this.displayName = displayName;
    }
    
    // ========== GETTERS ==========
    public double getExtraCostPercentage() { return extraCostPercentage; }
    public double getFlatExtraCost() { return extraCostFlat; }
    public String getEmoji() { return emoji;}
    public String getDisplayName() { return displayName; }
    
    // ========== METODI DI VALIDAZIONE ==========  
    /**
     * Verifica se la specie può stare su un determinato piano
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.isValid() per validare la prenotazione
     * - Nel BookingWizard.build() prima di creare la prenotazione
     * - In un'interfaccia utente per filtrare piani disponibili
     * @param floor Numero del piano (negativo = sotterraneo, positivo = sopra terra)
     * @return true se la specie può stare su quel piano
     */
    public boolean canStayOnFloor(int floor)
    {
        switch (this) {
            case VAMPIRE:
                // I vampiri possono stare SOLO su piani negativi (sotterranei)
                return floor < 0;
            
            case WEREWOLF:
            case MERMAID:
            case HOMO_SAPIENS:
            default:
                // Tutte le altre specie possono stare su qualsiasi piano
                return true;
        }
    }
    
    /**
     * Verifica se la specie può stare in un determinato tipo di stanza
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.isValid() per validare la prenotazione
     * - Nel BookingWizard.build() prima di creare la prenotazione
     * - In un'interfaccia utente per mostrare solo stanze compatibili
     * 
     * @param roomType Tipo di stanza
     * @return true se la specie può prenotare quel tipo di stanza
     */
    public boolean canStayInRoomType(RoomType roomType)
    {
        switch (this) {
            case WEREWOLF:
                // I licantropi NON possono stare in stanze SINGOLE
                // Hanno bisogno di spazio per la trasformazione
                return roomType != RoomType.SINGOLA;
            
            case VAMPIRE:
            case MERMAID:
            case HOMO_SAPIENS:
            default:
                // Tutte le altre specie possono stare in qualsiasi tipo di stanza
                return true;
        }
    }
    
    // ========== METODI DI CALCOLO COSTI ==========  
    /**
     * Calcola il costo extra specifico per questa specie
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.getSpeciesExtraCost()
     * - Nel metodo Booking.calculateTotalCost()
     * - Nel metodo Booking.toHTML() per mostrare dettaglio costi
     * - In preventivi e quotazioni
     * 
     * ESEMPIO D'USO:
     * double baseCost = getRoomCost() + getTransportCost();
     * double extraCost = species.calculateExtraCost(baseCost);
     * 
     * @param baseCost Costo base della prenotazione (stanza + navetta)
     * @return Costo extra da aggiungere
     */
    public double calculateExtraCost(double baseCost)
    {
        double percentageExtra = baseCost * (extraCostPercentage / 100.0);
        double totalExtra = percentageExtra + extraCostFlat;
        return totalExtra;
    }
    
    /**
     * Calcola il costo totale includendo gli extra della specie
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.calculateTotalCost() come implementazione principale
     * - Per calcolare il totale finale della prenotazione
     * 
     * ESEMPIO D'USO:
     * double baseCost = getRoomCost() + getTransportCost();
     * double total = species.calculateTotalCost(baseCost);
     * 
     * @param baseCost Costo base della prenotazione
     * @return Costo totale (base + extra)
     */ 
    public double calculateTotalCost(double baseCost) { return baseCost + calculateExtraCost(baseCost); }
    
    
    /**
     * Ottiene una descrizione dei vincoli di questa specie
     * 
     * DOVE SI USA:
     * - In un menu di selezione specie per mostrare info all'utente
     * - Nel metodo toString() di Booking per info complete
     * - In schermate di help/tutorial
     * @return Stringa con i vincoli
     */
    public String	getConstraints()
    {
    	switch (this)
    	{
    	case VAMPIRE:
    		return "Può stare SOLO su piani negativi(sotterranei)";
    	case WEREWOLF:
    		return "Deve avere almeno una stanza DOPPIA (no SINGOLA)";
    	case MERMAID:
    		return "Necessita di piscina";
    	case HOMO_SAPIENS:
    		return "Nessun vincolo particolare";
    	default:
    		return "";
    	}
    }
    
    
    /**
     * Ottiene una descrizione dei costi extra di questa specie
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.toHTML() per mostrare dettaglio extra costi
     * - In preventivi e quotazioni prima della prenotazione
     * - In schermate di riepilogo costi
     * - Nel toString() di Booking
     * @return Stringa con i costi extra
     */
    
    public String getExtraCostDescription()
    {
    	if (extraCostPercentage > 0 && extraCostFlat > 0)
    		return "+" + extraCostPercentage + "% sul totale + €" + extraCostFlat + " fissi";
    	else if (extraCostPercentage > 0)
    		return "+" + extraCostPercentage + "% sul totale";
    	else if (extraCostFlat > 0)
    		return "+ €" + extraCostFlat + " fissi";
    	else
    		return "Nessun costo extra";
    }
    
    /**
     * Restituisce un messaggio di errore specifico quando il piano non è valido
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.isValid() per dare feedback dettagliato
     * - Nelle eccezioni BookingValidationException per messaggi chiari
     * 
     * @param floor Piano che si voleva prenotare
     * @return Messaggio di errore
     */
    public String getFloorErrorMessage(int floor)
    {
        switch (this) {
            case VAMPIRE:
                return "I vampiri  possono stare solo su piani NEGATIVI";
            
            case WEREWOLF:
            case MERMAID:
            case HOMO_SAPIENS:
            default:
                // Tutte le altre specie possono stare su qualsiasi piano
                return "";
        }
    }
   
    /**
     * Restituisce un messaggio di errore specifico quando il tipo stanza non è valido
     * 
     * DOVE SI USA:
     * - Nel metodo Booking.isValid() per dare feedback dettagliato
     * - Nelle eccezioni BookingValidationException per messaggi chiari
     * 
     * @param roomType Tipo di stanza che si voleva prenotare
     * @return Messaggio di errore
     */
    public String getRoomTypeErrorMessage(RoomType roomType)
    {
        switch (this) {
            case WEREWOLF:
            	return "I licantropi necessitano di spazio! Stanza SINGOLA non permessa";
            
            case VAMPIRE:
            case MERMAID:
            case HOMO_SAPIENS:
            default:
                // Tutte le altre specie possono stare in qualsiasi tipo di stanza
                return "";
        }
    }
    
    // ========== OVERRIDE TOSTRING ==========
    
    @Override
    public String toString() {
    	return emoji + " " + displayName;
    }                
}