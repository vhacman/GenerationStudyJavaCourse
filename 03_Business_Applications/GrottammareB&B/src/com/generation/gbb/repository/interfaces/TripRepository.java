package com.generation.gbb.repository.interfaces;

import java.util.List;

import com.generation.gbb.model.entities.Trip;

/**
 * Interface for Trip persistence management.
 * Defines CRUD operations for Trip entity.
 */
public interface TripRepository
{
    // ==================== READ ====================

    /**
     * R - Retrieves all trips available in the system.
     *
     * @return List of all trips
     */
    List<Trip> findAll();

    /**
     * R - Retrieves single trip by its unique identifier.
     *
     * @param id Trip identifier to search
     * @return Found trip, null if not exists
     */
    Trip findById(int id);

    /**
     * R - Finds trips whose city contains specified string.
     * Case-insensitive partial match search.
     *
     * @param city String to search in city name
     * @return List of matching trips, empty list if no matches
     */
    List<Trip> findByCity(String city);

    /**
     * R - Finds all trips in the specified year.
     * Compares trip date year against year parameter.
     *
     * @param year Year to filter trips (e.g., 2024)
     * @return List of trips in the specified year, empty list if none
     */
    List<Trip> findByYear(int year);

    // ==================== CREATE ====================

    /**
     * C - Inserts new trip into the system.
     * Validates data before insertion.
     *
     * @param newTrip Trip to insert with all required data
     * @return Inserted trip with assigned ID, null if validation fails
     */
    Trip insert(Trip newTrip);

    // ==================== UPDATE ====================

    /**
     * U - Updates existing trip data.
     * Replaces trip with matching ID with new version.
     *
     * @param newVersion Updated trip (must have valid existing ID)
     * @return Updated trip, null if fails or trip not found
     */
    Trip update(Trip newVersion);

    // ==================== DELETE ====================

    /**
     * D - Deletes trip from system by identifier.
     *
     * @param id Trip identifier to delete
     * @return true if deletion successful, false otherwise
     */
    boolean delete(int id);
    
	public void initTable();

}
