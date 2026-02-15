package com.generation.fooddelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.fooddelivery.api.dto.RestaurantDTO;
import com.generation.fooddelivery.api.dto.mapper.RestaurantDTOMapper;
import com.generation.fooddelivery.model.repository.RestaurantRepository;

/**
 * Service per la gestione dei Ristoranti.
 */
@Service
public class RestaurantService
{
    @Autowired
    RestaurantRepository restaurantRepo;

    @Autowired
    RestaurantDTOMapper mapper;

    /**
     * Restituisce tutti i ristoranti.
     * @return Lista di DTO dei ristoranti
     */
    public List<RestaurantDTO> findAll()
    {
        return mapper.toNakedDto(restaurantRepo.findAll());
    }
}
