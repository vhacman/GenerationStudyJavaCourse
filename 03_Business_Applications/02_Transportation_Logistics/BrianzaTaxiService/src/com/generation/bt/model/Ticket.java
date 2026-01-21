package com.generation.bt.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ticket: Rappresenta una ricevuta/biglietto di una corsa taxi
 * RESPONSABILITÀ: Gestire tutti i dati relativi a una singola corsa
 */
public class Ticket {

    // Dati identificativi
    private String ticketId;
    private LocalDateTime createdAt;

    // Dati temporali della corsa
    private int departureHour;
    private int departureMinutes;
    private int arrivalHour;
    private int arrivalMinutes;
    private int totalMinutes;

    // Dati tariffari
    private int serviceLevel; // 1 o 2
    private double basePrice;
    private double nightSurcharge;
    private double discountAmount;
    private double finalPrice;

    // Dati utente (riferimento)
    private String userId;
    private String userName;
    private boolean vipDiscount;

    // Costruttore
    public Ticket(int departureHour, int departureMinutes, int arrivalHour,
                  int arrivalMinutes, int totalMinutes, int serviceLevel) {
        this.ticketId = generateTicketId();
        this.createdAt = LocalDateTime.now();
        this.departureHour = departureHour;
        this.departureMinutes = departureMinutes;
        this.arrivalHour = arrivalHour;
        this.arrivalMinutes = arrivalMinutes;
        this.totalMinutes = totalMinutes;
        this.serviceLevel = serviceLevel;
        this.nightSurcharge = 0.0;
        this.discountAmount = 0.0;
        this.vipDiscount = false;
    }

    // Genera un ID ticket univoco
    private String generateTicketId() {
        return "TKT" + System.currentTimeMillis();
    }

    // Calcola il prezzo totale
    public void calculatePrice(double pricePerMinute, double nightSurcharge, double discount) {
        this.basePrice = totalMinutes * pricePerMinute;
        this.nightSurcharge = nightSurcharge;
        this.discountAmount = discount;
        this.finalPrice = basePrice + nightSurcharge - discountAmount;
    }

    // Imposta i dati dell'utente
    public void setUserInfo(String userId, String userName, boolean hasVipDiscount) {
        this.userId = userId;
        this.userName = userName;
        this.vipDiscount = hasVipDiscount;
    }

    // Formatta l'ora di partenza
    public String getFormattedDepartureTime() {
        return String.format("%02d:%02d", departureHour, departureMinutes);
    }

    // Formatta l'ora di arrivo
    public String getFormattedArrivalTime() {
        return String.format("%02d:%02d", arrivalHour, arrivalMinutes);
    }

    // Formatta la data di creazione
    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return createdAt.format(formatter);
    }

    // Verifica se è una corsa notturna
    public boolean isNightRide() {
        return nightSurcharge > 0;
    }

    // Ottiene il nome del livello di servizio
    public String getServiceLevelName() {
        return serviceLevel == 1 ? "Classe 1 (Premium)" : "Classe 2 (Standard)";
    }

    // Getters
    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getDepartureHour() {
        return departureHour;
    }

    public int getDepartureMinutes() {
        return departureMinutes;
    }

    public int getArrivalHour() {
        return arrivalHour;
    }

    public int getArrivalMinutes() {
        return arrivalMinutes;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public int getServiceLevel() {
        return serviceLevel;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getNightSurcharge() {
        return nightSurcharge;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean hasVipDiscount() {
        return vipDiscount;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", departure=" + getFormattedDepartureTime() +
                ", arrival=" + getFormattedArrivalTime() +
                ", duration=" + totalMinutes + " min" +
                ", serviceLevel=" + getServiceLevelName() +
                ", finalPrice=€" + String.format("%.2f", finalPrice) +
                ", user='" + userName + '\'' +
                ", vip=" + vipDiscount +
                '}';
    }
}
