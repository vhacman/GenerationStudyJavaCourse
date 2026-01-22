package com.generation.bw.model.entities;

import org.junit.jupiter.api.Test;

class BikeTest
{
	
	 @Test
	    void testStatusAdvancement()
	    {
	        Bike b = new Bike();
	        b.setStatus(BikeStatus.ARRIVED);
	        
	        // il metodo advance manda avanti la moto nella sua pipeline
	        // la manda allo stato successivo
	        b.advance(); // metodo void
	        
	        assert(b.getStatus()==BikeStatus.CHECKUP);
	        
	        b.advance(); // cleaning
	        b.advance(); // delivered
	        b.advance(); // ancora delivered perché più che delivered non può essere
	        
	        assert(b.getStatus()==BikeStatus.DELIVERED);
	    }
	
}
