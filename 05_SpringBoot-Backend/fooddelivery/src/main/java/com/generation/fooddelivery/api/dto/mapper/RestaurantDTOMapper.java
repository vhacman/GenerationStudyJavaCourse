package com.generation.fooddelivery.api.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.fooddelivery.api.dto.RestaurantDTO;
import com.generation.fooddelivery.model.entities.Restaurant;

/**
 * Mapper per convertire tra Restaurant e RestaurantDTO.
 */
@Mapper(componentModel = "spring", uses = { DeliveryDTOMapper.class })
public interface RestaurantDTOMapper
{
    /**
     * Converte Entity a DTO (completo, include deliveries).
     * Mappa city.name in cityName.
     */
    @Mapping(source = "city.name", target = "cityName")
    RestaurantDTO toDto(Restaurant entity);

    /**
     * Converte Entity a DTO "naked" (senza deliveries).
     * Versione leggera per listare tutti i ristoranti senza le consegne.
     */
    @Mapping(source = "city.name", target = "cityName")
    @Mapping(target = "deliveries", ignore = true)
    RestaurantDTO toNakedDto(Restaurant entity);

    /**
     * Converte DTO a Entity.
     */
    Restaurant toEntity(RestaurantDTO dto);

    /**
     * Metodo helper per convertire una lista di Entity in lista di DTO "naked".
     * Necessario perché i metodi di default in interface non possono essere generic.
     */
    default List<RestaurantDTO> toNakedDto(List<Restaurant> restaurants)
    {
        List<RestaurantDTO> dtos = new ArrayList<RestaurantDTO>();
        for (Restaurant restaurant : restaurants)
            dtos.add(toNakedDto(restaurant));
        return dtos;
    }
}
