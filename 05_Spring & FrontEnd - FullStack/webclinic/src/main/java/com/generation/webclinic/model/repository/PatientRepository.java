package com.generation.webclinic.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.webclinic.model.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer>{

}