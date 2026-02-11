package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.javaeat.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    List<Customer> findByCityId(int cityId);
}
