package com.generation.bt.service;

import com.generation.library.Console;

/**
 * UserService: Gestisce le operazioni relative agli utenti
 * RESPONSABILITÀ: Creare e gestire utenti, calcolare sconti VIP
 */
public class UserService {

    /**
     * Crea un nuovo utente con dati completi
     */
    public static User createUser(String name, String surname, String fiscalCode,
                                   String phoneNumber, String email, String address,
                                   String city, String zipCode) {
        User user = new User(name, surname, fiscalCode, phoneNumber, email, address, city, zipCode);
        Console.print("Utente creato: " + user.getFullName() + " (ID: " + user.getUserId() + ")");
        return user;
    }

    /**
     * Crea un nuovo utente con dati minimi
     */
    public static User createUser(String name, String surname, String phoneNumber) {
        User user = new User(name, surname, phoneNumber);
        Console.print("Utente creato: " + user.getFullName() + " (ID: " + user.getUserId() + ")");
        return user;
    }

    /**
     * Registra una corsa per l'utente e aggiorna le statistiche
     */
    public static void registerRide(User user, double price) {
        boolean wasVip = user.isVip();
        user.registerRide(price);

        if (!wasVip && user.isVip()) {
            Console.print("Congratulazioni! " + user.getFullName() + " è ora un cliente VIP!");
            Console.print("Sconto del 10% applicato su tutte le corse future.");
        }
    }

    /**
     * Calcola il prezzo finale applicando eventuali sconti VIP
     */
    public static double calculateFinalPrice(User user, double originalPrice) {
        if (user.isVip()) {
            double discount = originalPrice * user.getDiscountPercentage();
            double finalPrice = user.applyDiscount(originalPrice);
            Console.print("Sconto VIP applicato: -€" + String.format("%.2f", discount));
            return finalPrice;
        }
        return originalPrice;
    }

    /**
     * Stampa le statistiche dell'utente
     */
    public static void printUserStats(User user) {
        Console.print("\n=== STATISTICHE UTENTE ===");
        Console.print("Nome: " + user.getFullName());
        Console.print("Telefono: " + user.getPhoneNumber());
        Console.print("Status: " + (user.isVip() ? "VIP" : "Standard"));
        Console.print("Corse effettuate: " + user.getRidesCount());
        Console.print("Totale speso: €" + String.format("%.2f", user.getTotalSpent()));
        if (user.isVip()) {
            Console.print("Sconto disponibile: 10%");
        } else {
            int ridesToVip = Math.max(0, 10 - user.getRidesCount());
            double spendToVip = Math.max(0, 200.0 - user.getTotalSpent());
            Console.print("Per diventare VIP: " + ridesToVip + " corse o €" + String.format("%.2f", spendToVip));
        }
        Console.print("==========================\n");
    }

    /**
     * Verifica se l'utente può ottenere uno sconto
     */
    public static boolean canApplyDiscount(User user) {
        return user.isVip();
    }

    /**
     * Ottiene la percentuale di sconto applicabile
     */
    public static double getDiscountPercentage(User user) {
        return user.getDiscountPercentage();
    }
}
