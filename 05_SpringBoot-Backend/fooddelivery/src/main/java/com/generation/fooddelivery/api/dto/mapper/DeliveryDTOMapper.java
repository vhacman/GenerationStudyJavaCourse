package com.generation.fooddelivery.api.dto.mapper;

import com.generation.fooddelivery.api.dto.DeliveryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.fooddelivery.model.entities.Delivery;

/**
 * Mapper per convertire tra Delivery e DeliveryDTO.
 * MapStruct genera automaticamente l'implementazione in fase di compilazione.
 */
@Mapper(componentModel = "spring")
public interface DeliveryDTOMapper
{
    /**
     * Converte da Entity a DTO.
     * Mappa gli ID delle entità correlate (rider, restaurant, customer).
     * Mappa anche le info testuali usando i metodi getInfo() delle entità.
     */
    @Mapping(source = "rider.id", target = "riderId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    @Mapping(source = "restaurant.info", target = "restaurantInfo")
    @Mapping(source = "rider.info", target = "riderInfo")
    @Mapping(source = "customer.info", target = "customerInfo")
    @Mapping(source = "customer.email", target = "customerEmail")
    DeliveryDTO toDto(Delivery delivery);

    /**
     * Converte da DTO a Entity.
     */
    Delivery toEntity(DeliveryDTO dto);
}
