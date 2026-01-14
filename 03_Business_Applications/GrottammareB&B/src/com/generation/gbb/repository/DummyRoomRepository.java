package com.generation.gbb.repository;

import java.util.ArrayList;
import java.util.List;
import com.generation.gbb.model.entities.Room;

/**
 * In-memory repository implementation for Room management.
 * Uses mockData list to simulate database persistence.
 */
public class DummyRoomRepository implements RoomRepository
{
    /** List containing all rooms in memory (database mock). */
    List<Room> mockData = new ArrayList<>();

    /**
     * Initializes repository with test data.
     * Populates mockData with sample rooms to simulate initial dataset.
     */
    public DummyRoomRepository()
    {
        mockData.add(new Room(1, "Singola Economy",    "Single economy room",                 12.0, 1,  40.0));
        mockData.add(new Room(2, "Doppia Standard",    "Standard double room",                18.0, 2,  70.0));
        mockData.add(new Room(3, "Matrimoniale Deluxe", "Deluxe matrimonial room with sea view", 22.0, 3, 110.0));
        mockData.add(new Room(4, "Suite Family",       "Family suite up to 4 people",         35.0, 4, 150.0));
    }

    // ==================== READ ====================

    /**
     * Retrieves all rooms from repository.
     *
     * @return Complete list of rooms in memory
     */
    @Override
    public List<Room> findAll()
    {
        return mockData;
    }

    /**
     * Finds room by unique identifier.
     * Iterates through list until ID match found.
     *
     * @param id Room numeric identifier
     * @return Found room, null if not exists
     */
    @Override
    public Room findById(int id)
    {
        for (Room r : mockData)
        {
            if (r.getId() == id)
                return r;
        }
        return null;
    }

    /**
     * Searches rooms whose name OR description contains specified substring.
     * Case-insensitive search. Returns empty list if part is null or blank.
     * 
     * @param part String to search in name or description
     * @return List of Room objects matching search criteria
     */
    @Override
    public List<Room> findByNameOrDescriptionContaining(String part)
    {
        List<Room> res = new ArrayList<>();

        if (part == null || part.isBlank()) return res;
        String target = part.toLowerCase();
        
        for (Room r : mockData)
            if (r.getName().toLowerCase().contains(target) || 
                r.getDescription().toLowerCase().contains(target))
                res.add(r);
                
        return res;
    }

    /**
     * Filters rooms with price strictly less than specified budget.
     * 
     * @param budget Maximum price threshold (exclusive)
     * @return List of Room objects with price < budget
     */
    @Override
    public List<Room> findByPriceLesserThan(int budget)
    {
        List<Room> result = new ArrayList<>();

        for (Room r : mockData)
            if (r.getPrice() < budget)
                result.add(r);
                
        return result;
    }

    // ==================== CREATE ====================

    /**
     * Inserts new room into repository.
     * Auto-generates incremental ID and validates data.
     *
     * @param newRoom Room to insert with all required data
     * @return Inserted room with assigned ID, null if validation fails
     */
    @Override
    public Room insert(Room newRoom)
    {
        if (newRoom == null || !newRoom.isValid())
            return null;
        int maxId = 0;
        for (Room r : mockData)
        {
            if (r.getId() > maxId)
                maxId = r.getId();
        }
        newRoom.setId(maxId + 1);

        mockData.add(newRoom);
        return newRoom;
    }

    // ==================== UPDATE ====================

    /**
     * Updates existing room in repository.
     * Replaces room with matching ID with new version.
     *
     * @param newVersion Updated room (must have valid existing ID)
     * @return Updated room, null if fails or room not found
     */
    @Override
    public Room update(Room newVersion)
    {
        if (newVersion == null || !newVersion.isValid())
            return null;

        int id = newVersion.getId();
        for (int i = 0; i < mockData.size(); i++)
        {
            if (mockData.get(i).getId() == id)
            {
                mockData.set(i, newVersion);
                return newVersion;
            }
        }
        return null;
    }

    // ==================== DELETE ====================

    /**
     * Deletes room from repository by identifier.
     * Removes room from list if found.
     *
     * @param id Room identifier to delete
     * @return true if successfully deleted, false if not found
     */
    @Override
    public boolean delete(int id)
    {
        for (int i = 0; i < mockData.size(); i++)
        {
            if (mockData.get(i).getId() == id)
            {
                mockData.remove(i);
                return true;
            }
        }
        return false;
    }
}
