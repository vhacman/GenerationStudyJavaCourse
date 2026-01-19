package com.generation.library.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

import com.generation.library.model.entities.Plant;
import com.generation.library.repository.SQLPlantRepository;

public class DemoPlantRepository {

	public static void main(String[] args) throws Exception
	{
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:plant.db");	

		SQLPlantRepository plantRepo = new SQLPlantRepository("plant", connection);
		
		Plant p = new Plant();
		p.setSpecies("Dracaena");
		p.setLength(200);
		p.setCost(10);
		plantRepo.insert(p);
		
		System.out.println(plantRepo.findAll());
		System.out.println(plantRepo.findWhere("length>100"));
		
		
		Plant l = plantRepo.findById(1);
		l.setLength(l.getLength()+10);
		plantRepo.update(l);
		System.out.println(plantRepo.findAll());
		
	}

}
