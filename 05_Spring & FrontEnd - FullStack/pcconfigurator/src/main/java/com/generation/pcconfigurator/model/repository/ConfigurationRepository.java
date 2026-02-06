package com.generation.pcconfigurator.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.pcconfigurator.model.entities.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration,Integer>
{

}