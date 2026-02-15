package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.javaeat.model.entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>
{
    List<Delivery> findByRestaurantId(int restaurantId);
    List<Delivery> findByCostumerId(int costumerId);
    List<Delivery> findByRiderId(int riderId);

    List<Delivery> findByCostumerIdAndStatus(int costumerId, String status);
    List<Delivery> findByRestaurantIdAndStatus(int restaurantId, String status);
    List<Delivery> findByRiderIdAndStatus(int riderId, String status);

    int 			countByCostumerId(int costumerId);
    int 			countByRestaurantId(int restaurantId);
    int 			countByRiderId(int riderId);
    int 			countByStatus(String status);
}
