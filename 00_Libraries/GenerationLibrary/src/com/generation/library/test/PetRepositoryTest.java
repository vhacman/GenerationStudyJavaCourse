package com.generation.library.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generation.library.model.entities.Pet;
import com.generation.library.repository.SQLPetRepository;

class PetRepositoryTest
{
    private Connection connection;
    private SQLPetRepository repository;

    @BeforeEach
    void setUp() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:sqlite:pet.db");
        repository = new SQLPetRepository("pet", connection);
    }

    @AfterEach
    void tearDown() throws SQLException
    {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    @Test
    void testInsert() throws SQLException
    {
        Pet newPet = new Pet("Fido", "Dog");
        int countBefore = repository.findAll().size();
        repository.insert(newPet);
        assertTrue(newPet.getId() > 0, "L'ID dovrebbe essere assegnato dopo l'insert");
        int countAfter = repository.findAll().size();
        assertEquals(countBefore + 1, countAfter, "Il numero di record dovrebbe essere aumentato di 1");
    }
}
