package com.generation.sanctionweb.model.repository;

import com.generation.sanctionweb.model.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer>
{

}
