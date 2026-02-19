package com.generation.fooddelivery.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.fooddelivery.model.entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>
{
}
