package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javaeat.model.entities.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer>
{
    List<Dish> findByRestaurantId(int restaurantId);
    List<Dish> findByDeliveries_Id(int deliveryId);
    List<Dish> findByNameContaining(String name);
}
