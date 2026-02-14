package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.javaeat.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    List<Customer> findByCityId(int cityId);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Delivery d WHERE d.customer.id = :customerId")
    boolean hasDeliveries(@Param("customerId") int customerId);
}
