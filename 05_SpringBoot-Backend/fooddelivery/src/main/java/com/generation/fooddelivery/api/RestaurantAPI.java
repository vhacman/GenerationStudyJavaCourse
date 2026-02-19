package com.generation.fooddelivery.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.fooddelivery.api.dto.RestaurantDTO;
import com.generation.fooddelivery.service.RestaurantService;

/**
 * REST Controller per le operazioni sui Ristoranti.
 */
@RestController
@RequestMapping("/fooddelivery/api/restaurants")
public class RestaurantAPI
{
    @Autowired
    RestaurantService restaurantService;

    /**
     * Endpoint GET per ottenere tutti i ristoranti.
     * @return Lista di tutti i ristoranti
     */
    @GetMapping
    public List<RestaurantDTO> findAll()
    {
        return restaurantService.findAll();
    }
}
