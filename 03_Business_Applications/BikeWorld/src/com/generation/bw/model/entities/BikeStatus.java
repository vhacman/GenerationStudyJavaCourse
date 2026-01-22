package com.generation.bw.model.entities;

/**
 * Enumeration representing the lifecycle states of a bike in the system.
 * Each state contains a reference to its next state in the workflow.
 */
public enum BikeStatus 
{
    DELIVERED   (null, 4),
    CLEANING    (BikeStatus.DELIVERED, 3),      // in fase di lavaggio
    CHECKUP     (BikeStatus.CLEANING, 2),       // in fase di controllo
    ARRIVED     (BikeStatus.CHECKUP, 1);        // appena scesa dalla bisarca

    BikeStatus              next;
	int             		order;


	/**
     * Constructs a BikeStatus with its subsequent state in the workflow.
     * 
     * @param next the next status in the processing chain, or null if final
     */
    private BikeStatus(BikeStatus next, int order) 
    { 
        /*
         * ═════════════════════════════════════════════════════════════════
         *  State Machine + Chain of Responsibility
         * ═════════════════════════════════════════════════════════════════
         * 
         *  Enum        →  State Pattern (type-safe states)
         *  next field  →  Chain of Responsibility (workflow sequence)
         *  
         *  Ogni stato conosce il proprio successore, creando una catena
         *  immutabile che rappresenta il flusso di lavorazione.
         *  
         * ═════════════════════════════════════════════════════════════════
         */
        this.next = next; 
        this.order = order;
    }

    /**
     * Returns the next status in the bike processing workflow.
     * 
     * @return the subsequent BikeStatus, or null if this is the final state
     */
    public BikeStatus 	getNext()                         { return next != null ? next : this; }
    public int 			getOrder()						  { return order; }

    
}
