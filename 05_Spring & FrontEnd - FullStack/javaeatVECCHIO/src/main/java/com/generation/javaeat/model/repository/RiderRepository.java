package com.generation.javaeat.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.javaeat.model.entities.Rider;

public interface RiderRepository extends JpaRepository<Rider, Integer>
{
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Delivery d WHERE d.rider.id = :riderId")
    boolean hasDeliveries(@Param("riderId") int riderId);
}
