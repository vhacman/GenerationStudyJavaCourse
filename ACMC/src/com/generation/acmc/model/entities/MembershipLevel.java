package com.generation.acmc.model.entities;

/**
 * Livelli di membership del sistema con ordine gerarchico predefinito.
 * L'ordine di dichiarazione determina la priorità: BRONZE (basso) → GRAY (alto).
 * BANNED è posizionato per ultimo per essere gestito separatamente nei filtri.
 */
public enum MembershipLevel 
{ 
    BRONZE, 
    SILVER, 
    GOLD, 
    GRAY, 
    BANNED;
  
    /**
     * Verifica se il livello è attivo (esclude BANNED).
     * 
     * @return true se il membro può accedere al sistema
     */
    public boolean isActive() { return this != BANNED; }
    
    /**
     * Ottiene il livello successivo nella gerarchia (es. SILVER → GOLD).
     * 
     * @return livello successivo o GRAY se già al massimo
     */
    public MembershipLevel nextLevel()
    {
        return switch (this) {
            case BRONZE -> SILVER;
            case SILVER -> GOLD;
            case GOLD, GRAY -> GRAY;
            case BANNED -> BANNED;
        };
    }
    
    /**
     * Rappresentazione leggibile del livello.
     * Usa questo metodo quando si mostra il livello all’utente.
     * 
     * Per il salvataggio in DB: usa name() (es. "BRONZE", "GOLD")
     * Per la UI: usa toString() (es. "Bronzo", "Oro")
     */
    @Override
    public String toString()
    {
        return switch(this) {
            case BRONZE -> "Bronzo";
            case SILVER -> "Argento";
            case GOLD   -> "Oro";
            case GRAY   -> "Grigio";
            case BANNED -> "Banned";
        };
    }
}
