package com.generation.bw.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.bw.model.entities.Bike;

/**
 * Repository interface providing persistence services for Bike entities.
 * Defines the contract for CRUD operations and custom queries.
 */
public interface BikeRepository 
{
    /**
     * Retrieves all bikes from the data store.
     * 
     * @return list of all Bike entities
     */
    List<Bike> findAll();
    
    /**
     * Finds a bike by its unique identifier.
     * 
     * @param id the bike's unique identifier
     * @return the Bike entity, or null if not found
     */
    Bike findById(int id);
    
    /**
     * Finds a bike by its license plate number.
     * 
     * @param plate the license plate to search for
     * @return the Bike entity, or null if not found
     */
    Bike findByPlate(String plate);
    
    /**
     * Retrieves the most recently inserted bikes.
     * 
     * @return list of latest Bike entities ordered by insertion date
     */
    List<Bike> findProcessing();
    
    /**
     * Persists a new bike to the data store.
     * 
     * @param bike the Bike entity to insert
     * @return the inserted Bike with generated ID
     * @throws SQLException if database operation fails
     */
    Bike insert(Bike bike) throws SQLException;
    
    /**
     * Updates an existing bike in the data store.
     * 
     * @param bike the Bike entity with updated values
     * @return the updated Bike entity
     * @throws SQLException if database operation fails
     */
    Bike update(Bike bike) throws SQLException;
    
    /**
     * Removes a bike from the data store.
     * 
     * @param id the unique identifier of the bike to delete
     * @return true if deletion was successful, false otherwise
     * @throws SQLException if database operation fails
     */
    boolean delete(int id) throws SQLException;
}
