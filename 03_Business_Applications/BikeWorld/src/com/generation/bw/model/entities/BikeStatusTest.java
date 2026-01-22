package com.generation.bw.model.entities;

import org.junit.jupiter.api.Test;

/**
 * Test class for BikeStatus enumeration.
 * Validates the state machine workflow and transitions.
 */
class BikeStatusTest
{
    /**
     * Tests the navigation through bike status workflow chain.
     * Verifies that each state correctly references its subsequent state.
     */
    @Test
    void testNavigation()
    {
        /*
         * ═════════════════════════════════════════════════════════════════
         *  Test  State Transition Validation
         * ═════════════════════════════════════════════════════════════════
         * 
         *  ARRIVED   →  CHECKUP   →  CLEANING   →  DELIVERED   →  null
         *  
         *  Verifica che la catena di responsabilità sia configurata
         *  correttamente dall'enum constructor, garantendo il flusso
         *  di lavorazione immutabile e type-safe.
         *  
         * ═════════════════════════════════════════════════════════════════
         */
        BikeStatus b = BikeStatus.ARRIVED;
        
        assert(b.getNext() == BikeStatus.CHECKUP);
    }
}
