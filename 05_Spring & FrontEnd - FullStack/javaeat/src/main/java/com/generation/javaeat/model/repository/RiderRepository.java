package com.generation.javaeat.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.javaeat.model.entities.Rider;

public interface RiderRepository extends JpaRepository<Rider, Integer>
{
}
