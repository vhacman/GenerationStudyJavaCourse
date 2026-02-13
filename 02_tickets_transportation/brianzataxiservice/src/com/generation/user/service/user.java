package com.generation.user.service;

/**
 * User: Rappresenta un utente del servizio taxi
 * RESPONSABILITÀ: Gestire i dati anagrafici e di contatto dell'utente
 */
public class User {

    // Dati anagrafici
    private String name;
    private String surname;
    private String fiscalCode;
    private String phoneNumber;
    private String email;

    // Dati indirizzo
    private String address;
    private String city;
    private String zipCode;

    // Dati account
    private String userId;
    private boolean isVip;
    private int ridesCount;
    private double totalSpent;

    // Costruttore completo
    public User(String name, String surname, String fiscalCode, String phoneNumber,
                String email, String address, String city, String zipCode) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.userId = generateUserId();
        this.isVip = false;
        this.ridesCount = 0;
        this.totalSpent = 0.0;
    }

    public User(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.userId = generateUserId();
        this.isVip = false;
        this.ridesCount = 0;
        this.totalSpent = 0.0;
    }

    // Genera un ID utente univoco basato sul timestamp
    private String generateUserId() {
        return "USR" + System.currentTimeMillis();
    }

    // Metodo per registrare una nuova corsa
    public void registerRide(double price) {
        this.ridesCount++;
        this.totalSpent += price;
        checkVipStatus();
    }

    // Promuove a VIP se ha fatto almeno 10 corse o speso più di 200€
    private void checkVipStatus() {
        if (ridesCount >= 10 || totalSpent >= 200.0) {
            this.isVip = true;
        }
    }

    // Ottiene lo sconto VIP (10% se VIP)
    public double getDiscountPercentage() {
        return isVip ? 0.10 : 0.0;
    }

    // Calcola il prezzo finale con sconto VIP applicato
    public double applyDiscount(double originalPrice) {
        if (isVip) {
            return originalPrice * (1 - getDiscountPercentage());
        }
        return originalPrice;
    }

    // Restituisce il nome completo
    public String getFullName() {
        return name + " " + surname;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }

    public int getRidesCount() {
        return ridesCount;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isVip=" + isVip +
                ", ridesCount=" + ridesCount +
                ", totalSpent=€" + String.format("%.2f", totalSpent) +
                '}';
    }
}
