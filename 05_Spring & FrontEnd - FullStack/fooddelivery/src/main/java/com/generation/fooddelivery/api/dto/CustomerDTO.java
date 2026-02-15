package com.generation.fooddelivery.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO per l'entità Customer.
 */
@Getter
@Setter
public class CustomerDTO
{
    int               id;
    String            firstName;
    String            lastName;
    String            email;
    String            password;  // Hash della password (ignorato nelle risposte)
    String            address;
    int               x;         // Coordinata X del cliente
    int               y;         // Coordinata Y del cliente
    int               cityId;    // ID della città

    List<DeliveryDTO> deliveries;  // Lista ordini del cliente
}
