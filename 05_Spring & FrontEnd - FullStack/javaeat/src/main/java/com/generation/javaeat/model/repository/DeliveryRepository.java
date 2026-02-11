package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.javaeat.model.entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>
{
    List<Delivery> findByRestaurantId(int restaurantId);
    List<Delivery> findByCustomerId(int customerId);
    List<Delivery> findByRiderId(int riderId);

    List<Delivery> findByCustomerIdAndStatus(int customerId, String status);
    List<Delivery> findByRestaurantIdAndStatus(int restaurantId, String status);
    List<Delivery> findByRiderIdAndStatus(int riderId, String status);
}
