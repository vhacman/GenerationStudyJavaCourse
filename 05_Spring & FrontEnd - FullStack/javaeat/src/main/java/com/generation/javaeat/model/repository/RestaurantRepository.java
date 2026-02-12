package com.generation.javaeat.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.javaeat.model.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{
    List<Restaurant> findByNameContaining(String name);
    List<Restaurant> findByCityId(int cityId);
}
