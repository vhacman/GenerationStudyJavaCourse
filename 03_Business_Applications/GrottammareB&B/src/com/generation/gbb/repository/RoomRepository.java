package com.generation.gbb.repository;

import java.util.List;

import com.generation.gbb.model.entities.Room;

/**
 * Interface for Room persistence management.
 * Defines CRUD operations for Room entity.
 */
public interface RoomRepository
{
    // ==================== READ ====================

    /**
     * R - Retrieves all rooms available in the system.
     *
     * @return List of all rooms
     */
    List<Room> findAll();

    /**
     * R - Retrieves single room by its unique identifier.
     *
     * @param id Room identifier to search
     * @return Found room, null if not exists
     */
    Room findById(int id);

    /**
     * R - Finds rooms whose name OR description contains specified string.
     * Case-insensitive partial match search.
     *
     * @param part String to search in name or description
     * @return List of matching rooms, empty list if no matches
     */
    List<Room> findByNameOrDescriptionContaining(String part);

    /**
     * R - Finds all rooms under specified budget.
     * Compares nightly price against budget parameter.
     *
     * @param budget Maximum nightly price budget
     * @return List of rooms with price < budget, empty list if none
     */
    List<Room> findByPriceLesserThan(int budget);

    // ==================== CREATE ====================

    /**
     * C - Inserts new room into the system.
     * Validates data before insertion.
     *
     * @param newRoom Room to insert with all required data
     * @return Inserted room with assigned ID, null if validation fails
     */
    Room insert(Room newRoom);

    // ==================== UPDATE ====================

    /**
     * U - Updates existing room data.
     * Replaces room with matching ID with new version.
     *
     * @param newVersion Updated room (must have valid existing ID)
     * @return Updated room, null if fails or room not found
     */
    Room update(Room newVersion);

    // ==================== DELETE ====================

    /**
     * D - Deletes room from system by identifier.
     *
     * @param id Room identifier to delete
     * @return true if deletion successful, false otherwise
     */
    boolean delete(int id);
}
