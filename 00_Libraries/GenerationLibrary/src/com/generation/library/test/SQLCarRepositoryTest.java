package com.generation.library.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generation.library.model.entities.Car;
import com.generation.library.repository.SQLCarRepository;

class SQLCarRepositoryTest
{
    private Connection connection;
    private SQLCarRepository repository;

    @BeforeEach
    void setUp() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:sqlite:car.db");
        repository = new SQLCarRepository("car", connection);
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
        Car newCar = new Car("Tesla Model 3", "AB123CD", 45000.0);
        int countBefore = repository.findAll().size();
        repository.insert(newCar);
        assertTrue(newCar.getId() > 0, "L'ID dovrebbe essere assegnato dopo l'insert");
        int countAfter = repository.findAll().size();
        assertEquals(countBefore + 1, countAfter, "Il numero di record dovrebbe essere aumentato di 1");
    }
}
