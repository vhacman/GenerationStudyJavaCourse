package com.generation.fooddelivery.api.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.fooddelivery.api.dto.RiderDTO;
import com.generation.fooddelivery.model.entities.Rider;

/**
 * Mapper per convertire tra Rider e RiderDTO.
 * Usa FeedbackDTOMapper e DeliveryDTOMapper per mappare le liste annidate.
 */
@Mapper(componentModel = "spring", uses = { FeedbackDTOMapper.class, DeliveryDTOMapper.class })
public interface RiderDTOMapper
{
    /**
     * Converte Entity a DTO.
     * Ignora la password per non esporla nelle risposte API.
     */
    @Mapping(target = "password", ignore = true)
    RiderDTO toDto(Rider rider);

    /**
     * Converte DTO a Entity.
     */
    Rider toEntity(RiderDTO riderDto);

    /**
     * Converte una lista di Entity in lista di DTO.
     */
    @Mapping(target = "password", ignore = true)
    List<RiderDTO> toDto(List<Rider> riders);
}
