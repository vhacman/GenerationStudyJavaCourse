package com.generation.jb.test;

import org.junit.jupiter.api.Test;

import com.generation.jb.model.entities.Ticket;

public class TicketTest
{
	@Test
	void	testGetPrice()
	{
		// ========== ARRANGE ==========
        // Creo un biglietto di prima classe e imposto i dati di prova
		Ticket t = new Ticket();
		t.km = 100; // 100 km
		t.level = 1; // Prima classe
		t.greenCard = false; // senza greenCard


		// ========== ALTRO MODO ARRANGE ==========
        // Creo un biglietto di prima classe e imposto i dati di prova
		//Ticket t = new Ticket(100, 1, false);


		// ========== ACT ==========
        // Calcolo il prezzo del biglietto
		double price = t.getPrice();

        // ========== ASSERT ==========
        // Verifico che il prezzo sia corretto (100 km × €0.20 = €20.00)
		assert(price == 20.0);

		Ticket q = new Ticket(100, 2, false, 0);
		double priceQ = q.getPrice();
		assert(priceQ == 10.0);

		Ticket z = new Ticket(100, 3, false, 1);
		double priceZ = z.getPrice();
		assert(priceZ == 5.0);
		
	}
	
	@Test
	void	testIsValid()
	{
		// ========== ARRANGE ==========
        // Creo un biglietto di prima classe e imposto i dati di prova
		Ticket t = new Ticket();
		t.km = 100;
		t.level = 1;
		t.id = 2;
		
		// ========== ACT & ASSERT ==========
		// Verifico che il biglietto sia valido
		
		assert(t.isValid());
		
		//Test con km 0 (non valido)
		Ticket q = new Ticket();
		q.km = 0;
		q.level = 1;
		t.id = 1;
		assert(!q.isValid());
		
		//Test con km negativi (non valido)
		Ticket z = new Ticket();
		z.km = -42;
		z.level = 2;
		z.id = 1;
		assert(!z.isValid());
		
		//Test con level > 3 °(non valido)
		Ticket w = new Ticket();
		w.km = 100;
		w.level = 4;
		w.id = 1;
		
		//Test con level == 0 ( non valido)
		Ticket v = new Ticket();
		v.km = 100;
		v.level = 0;
		v.id = 1;
		
		
		Ticket u = new Ticket(50, 3, false, 1);
		assert(u.isValid());
	}
}
