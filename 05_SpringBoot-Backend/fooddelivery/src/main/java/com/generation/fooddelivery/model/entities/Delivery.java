package com.generation.fooddelivery.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delivery
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int               id;

    // Data/ora in cui è prevista la consegna
    LocalDateTime     deliveryTime;
    // Descrizione dell'ordine (es: "2 pizza margherita, 1 coca cola")
    String            description;
    // Prezzo totale dell'ordine
    int               price;
    // Stato della consegna: "OPEN" = in corso, "CLOSED" = completata, etc.
    String            status;

    // Relazione many-to-one: ogni Delivery ha un Rider associato
    // FetchType.EAGER = carica sempre il rider insieme alla delivery
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rider_id")
    Rider             rider;

    // Relazione many-to-one: ogni Delivery ha un Restaurant associato
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    Restaurant        restaurant;

    // Relazione many-to-one: ogni Delivery ha un Customer (cliente) associato
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    Customer          customer;
}
