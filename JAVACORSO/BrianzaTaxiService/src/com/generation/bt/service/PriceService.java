package com.generation.bt.service;

import com.generation.bt.model.Ticket;
import com.generation.library.Console;

/**
 * PriceService: Gestisce il calcolo del prezzo della corsa
 * RESPONSABILITÀ: Calcolare il costo della corsa, applicare sconti e gestire il Ticket
 */
public class PriceService
{
    // Costanti per le tariffe
    private static final double PRICE_PER_MINUTE_CLASS_1 = 0.50;
    private static final double PRICE_PER_MINUTE_CLASS_2 = 0.30;
    private static final double NIGHT_SURCHARGE = 10.0;
    private static final int 	NIGHT_HOUR_THRESHOLD = 5;

    /**
     * Calcola il prezzo totale della corsa (senza sconti)
     * Logica:
     * - Classe 1: 0.50€ al minuto
     * - Classe 2: 0.30€ al minuto
     * - Se ora < 5:00: aggiunge 10€ di supplemento notturno
     */
    public static double calculatePrice(int minutes, int level, int hour)
    {
        double pricePerMinute = getPricePerMinute(level);
        double basePrice = minutes * pricePerMinute;

        if (isNightHour(hour)) {
            basePrice += NIGHT_SURCHARGE;
        }

        return basePrice;
    }

    /**
     * Calcola il prezzo e popola il Ticket con tutti i dettagli
     * @param ticket il ticket da popolare
     * @param user l'utente (può essere null)
     * @return il prezzo finale
     */
    public static double calculatePriceWithTicket(Ticket ticket, User user)
    {
        double pricePerMinute = getPricePerMinute(ticket.getServiceLevel());
        double nightSurcharge = isNightHour(ticket.getDepartureHour()) ? NIGHT_SURCHARGE : 0.0;

        // Calcola sconto se c'è un utente VIP
        double discountAmount = 0.0;
        if (user != null && user.isVip()) {
            double baseTotal = (ticket.getTotalMinutes() * pricePerMinute) + nightSurcharge;
            discountAmount = baseTotal * user.getDiscountPercentage();
        }

        // Popola il ticket con i dettagli del prezzo
        ticket.calculatePrice(pricePerMinute, nightSurcharge, discountAmount);

        // Aggiunge info utente al ticket se presente
        if (user != null) {
            ticket.setUserInfo(user.getUserId(), user.getFullName(), user.isVip());
        }

        return ticket.getFinalPrice();
    }

    /**
     * Stampa i dettagli completi del prezzo
     */
    public static void printTicketDetails(Ticket ticket)
    {
        Console.print("\n=== DETTAGLIO PREZZO ===");
        Console.print("Tariffa: " + ticket.getServiceLevelName());
        Console.print("Durata: " + ticket.getTotalMinutes() + " minuti");
        Console.print("Prezzo base: €" + String.format("%.2f", ticket.getBasePrice()));

        if (ticket.getNightSurcharge() > 0) {
            Console.print("Supplemento notturno: €" + String.format("%.2f", ticket.getNightSurcharge()));
        }

        if (ticket.getDiscountAmount() > 0) {
            Console.print("Sconto VIP (10%): -€" + String.format("%.2f", ticket.getDiscountAmount()));
        }

        Console.print("------------------------");
        Console.print("TOTALE: €" + String.format("%.2f", ticket.getFinalPrice()));
        Console.print("========================\n");
    }

    public static void printTicket(double price)
    {
        Console.print("Il costo della corsa è: €" + String.format("%.2f", price));
    }

    // Metodi helper pubblici per accedere alle costanti
    public static double getPricePerMinute(int level)
    {
        return level == 1 ? PRICE_PER_MINUTE_CLASS_1 : PRICE_PER_MINUTE_CLASS_2;
    }

    public static boolean isNightHour(int hour)
    {
        return hour < NIGHT_HOUR_THRESHOLD;
    }

    public static double getNightSurcharge()
    {
        return NIGHT_SURCHARGE;
    }
}