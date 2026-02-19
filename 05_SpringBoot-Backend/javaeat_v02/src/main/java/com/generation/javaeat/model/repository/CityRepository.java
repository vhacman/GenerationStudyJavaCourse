package com.generation.javaeat.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javaeat.model.entities.City;

public interface CityRepository extends JpaRepository<City, Integer>
{
    List<City> 		findByProvince		(String province);
    List<City> 		findByNameContaining(String name	);

}
