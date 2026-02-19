package com.generation.fooddelivery.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.fooddelivery.api.dto.CustomerDTO;
import com.generation.fooddelivery.model.entities.Customer;

/**
 * Mapper per convertire tra Customer e CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = { DeliveryDTOMapper.class })
public interface CustomerDTOMapper
{
    /**
     * Converte Entity a DTO.
     * Ignora la password per non esporla nelle risposte API.
     */
    @Mapping(target = "password", ignore = true)
    CustomerDTO toDto(Customer customer);

    /**
     * Converte DTO a Entity.
     */
    Customer toEntity(CustomerDTO customerDto);
}
