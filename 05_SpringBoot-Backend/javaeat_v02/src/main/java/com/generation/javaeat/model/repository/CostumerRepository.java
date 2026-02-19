package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javaeat.model.entities.Costumer;

public interface CostumerRepository extends JpaRepository<Costumer, Integer>
{
    List<Costumer> findByCityId(int cityId);
    List<Costumer> findByLegalNameContaining(String legalName);
}
