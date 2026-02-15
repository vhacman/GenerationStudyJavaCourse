package com.generation.fooddelivery.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO per l'entità Restaurant.
 * Espone solo cityName (non l'intero oggetto City) per semplificare il JSON.
 */
@Getter
@Setter
public class RestaurantDTO
{
    int               id;
    String            name;
    String            cityName;   // Nome della città (mappato da city.name)
    String            address;
    int               restaurantPositionX;
    int               restaurantPositionY;

    List<DeliveryDTO> deliveries;  // Lista delle consegne per questo ristorante
}
