package com.generation.fooddelivery.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.fooddelivery.api.dto.FeedbackDTO;
import com.generation.fooddelivery.model.entities.Feedback;

/**
 * Mapper per convertire tra Feedback e FeedbackDTO.
 */
@Mapper(componentModel = "spring")
public interface FeedbackDTOMapper
{
    /**
     * Converte Entity a DTO.
     * Mappa rider.id in riderId (rider è un oggetto, quindi .id accede all'id).
     */
    @Mapping(source = "rider.id", target = "riderId")
    FeedbackDTO toDto(Feedback feedback);

    /**
     * Converte DTO a Entity.
     */
    Feedback toEntity(FeedbackDTO dto);
}
