package com.generation.product.model.repository;

import com.generation.product.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
	boolean				existsByName(String name);
}
