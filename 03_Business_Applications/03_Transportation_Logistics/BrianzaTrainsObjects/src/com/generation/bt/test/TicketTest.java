package com.generation.bt.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.generation.model.entities.Ticket;

class TicketTest
{
    @Test
    void testPriceFirstClass()
    {
        // ========== ARRANGE ==========
        // Creo un biglietto di prima classe e imposto i dati di prova
        Ticket t = new Ticket();
        t.km = 100;        // 100 chilometri
        t.level = 1;       // Prima classe

        // ========== ACT ==========
        // Calcolo il prezzo del biglietto
        double price = t.getPrice();

        // ========== ASSERT ==========
        // Verifico che il prezzo sia corretto (100 km × €0.20 = €20.00)
        assert(price == 20.0);
    }

    @Test
    void testPriceSecondClass()
    {
        // ========== ARRANGE ==========
        // Creo un biglietto di seconda classe e imposto i dati di prova
        Ticket t = new Ticket();
        t.km = 100;        // 100 chilometri
        t.level = 2;       // Seconda classe

        // ========== ACT ==========
        // Calcolo il prezzo del biglietto
        double price = t.getPrice();

        // ========== ASSERT ==========
        // Verifico che il prezzo sia corretto (100 km × €0.10 = €10.00)
        assert(price == 10.0);
    }

    @Test
    void testIsValid()
    {
        // ========== ARRANGE ==========
        // Creo un biglietto con valori predefiniti (tutti a 0)
        Ticket t = new Ticket();
        
        // ========== ASSERT #1 ==========
        // Verifico che il biglietto non sia valido (km = 0, level = 0)
        assertFalse(t.isValid());
        
        // ========== ARRANGE #2 ==========
        // Imposto valori validi
        t.level = 1;       // Prima classe
        t.km = 1;          // 1 chilometro
        
        // ========== ASSERT #2 ==========
        // Verifico che ora il biglietto sia valido
        assertTrue(t.isValid());
    }
}