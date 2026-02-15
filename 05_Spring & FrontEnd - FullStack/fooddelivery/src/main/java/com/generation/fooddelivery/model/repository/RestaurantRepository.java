package com.generation.fooddelivery.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.fooddelivery.model.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>
{
}
