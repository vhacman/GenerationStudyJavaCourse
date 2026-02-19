package com.generation.fooddelivery.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.fooddelivery.model.entities.Customer;

/**
 * Repository per l'entità Customer.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    // Query per il login: cerca un cliente per email e password (hash)
    @Query("SELECT c FROM Customer c WHERE c.email = :email AND c.password = :hash")
    Customer login(
        @Param("email") String email,
        @Param("hash")  String hash
    );
}
