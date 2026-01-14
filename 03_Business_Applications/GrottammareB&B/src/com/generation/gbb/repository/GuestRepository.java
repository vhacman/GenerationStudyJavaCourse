package com.generation.gbb.repository;

import java.util.List;
import com.generation.gbb.model.entities.Guest;

/**
 * Interface for Guest persistence management.
 * Defines CRUD operations for Guest entity.
 */
public interface GuestRepository
{
    // ==================== READ ====================

    /**
     * R - Retrieves all guests available in the system.
     *
     * @return List of all guests
     */
    List<Guest> findAll();

    /**
     * R - Retrieves single guest by its unique identifier.
     *
     * @param id Guest identifier to search
     * @return Found guest, null if not exists
     */
    Guest findById(int id);

    /**
     * R - Retrieves single guest by Tax Code.
     *
     * @param ssn Guest Tax Code to search
     * @return Found guest, null if not exists
     */
    Guest findBySSN(String ssn);

    /**
     * R - Finds all guests whose surname contains specified string.
     * Case-insensitive partial match search.
     *
     * @param part String part to search in surname
     * @return List of guests with matching surname, empty list if no matches
     */
    List<Guest> findBySurnameContaining(String part);

    /**
     * R - Finds all guests residing in specific city.
     * Case-insensitive exact match search.
     *
     * @param city City name to search
     * @return List of guests from city, empty list if no matches
     */
    List<Guest> findByCity(String city);

    // ==================== CREATE ====================

    /**
     * C - Inserts new guest into the system.
     *
     * @param newGuest Guest to insert with all required data
     * @return Inserted guest with assigned ID, null if insertion fails
     */
    Guest insert(Guest newGuest);

    // ==================== UPDATE ====================

    /**
     * U - Updates existing guest data.
     *
     * @param newVersion Updated guest (must have valid existing ID)
     * @return Updated guest, null if update fails or guest not found
     */
    Guest update(Guest newVersion);

    // ==================== DELETE ====================

    /**
     * D - Deletes guest from system by identifier.
     *
     * @param id Guest identifier to delete
     * @return true if deletion successful, false otherwise
     */
    boolean delete(int id);
}
