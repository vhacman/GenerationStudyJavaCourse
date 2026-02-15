package com.generation.fooddelivery.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO per l'entità Delivery.
 * Utilizzato per le operazioni CRUD sulle consegne.
 */
@Getter
@Setter
public class DeliveryDTO
{
    private int               id;
    private LocalDateTime     deliveryTime;  // Data/ora previsione consegna
    private String            description;    // Descrizione ordine
    private String            status;         // Stato: "OPEN", "CLOSED", etc.
    private int               price;          // Prezzo totale

    // ID delle entità correlate (mappati dagli oggetti nel mapper)
    private int               riderId;
    private int               restaurantId;

    // Info testuali delle entità correlate (costruite con getInfo())
    private String            restaurantInfo;
    private String            riderInfo;
    private String            customerInfo;

    // Dati per autenticazione cliente (solo in input, non esposti in output)
    private String            customerEmail;
    private String            customerHash;  // Hash password (NON viene tradotta dal mapper)
}
