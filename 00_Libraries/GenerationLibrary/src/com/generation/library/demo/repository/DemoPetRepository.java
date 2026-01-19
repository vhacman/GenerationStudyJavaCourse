package com.generation.library.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

import com.generation.library.model.entities.Pet;
import com.generation.library.repository.SQLPetRepository;

public class DemoPetRepository
{

    public static void main(String[] args) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        
        Connection       connection = DriverManager.getConnection("jdbc:sqlite:pet.db");
        SQLPetRepository petRepo    = new SQLPetRepository("pet", connection);
        
        Pet p = new Pet();
        p.setName("Bulbasaur");
        p.setSpecies("Rana verde");
        petRepo.insert(p);
        
        System.out.println(petRepo.findAll());
        System.out.println(petRepo.findWhere("species='Cane'"));
        System.out.println(petRepo.findById(2));
        
        Pet b = petRepo.findById(1);
        b.setName("fido modificato");
        petRepo.update(b);
        System.out.println(petRepo.findAll());
    }

}
