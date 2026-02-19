package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.javaeat.model.entities.Rider;

public interface RiderRepository extends JpaRepository<Rider, Integer>
{
    List<Rider> findByStatus(String status);
    List<Rider> findByLegalNameContaining(String legalName);
}
