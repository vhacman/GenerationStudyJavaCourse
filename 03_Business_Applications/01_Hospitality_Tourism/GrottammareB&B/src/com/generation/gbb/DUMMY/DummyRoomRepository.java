package com.generation.gbb.DUMMY;

import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.model.entities.Room;
import com.generation.gbb.repository.interfaces.RoomRepository;

/**
 * In-memory repository implementation for Room management. Uses mockData list
 * to simulate database persistence.
 */
public class DummyRoomRepository implements RoomRepository
{
	/** List containing all rooms in memory (database mock). */
	List<Room> mockData = new ArrayList<>();

	/**
	 * Initializes repository with test data. Populates mockData with sample rooms
	 * to simulate initial dataset.
	 */
	public DummyRoomRepository()
	{
		mockData.add(new Room(1, "Singola Economy", "Single economy room", 12.0, 1, 40.0));
		mockData.add(new Room(2, "Doppia Standard", "Standard double room", 18.0, 2, 70.0));
		mockData.add(new Room(3, "Matrimoniale Deluxe", "Deluxe matrimonial room with sea view", 22.0, 3, 110.0));
		mockData.add(new Room(4, "Suite Family", "Family suite up to 4 people", 35.0, 4, 150.0));
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
	 * Finds room by unique identifier. Iterates through list until ID match found.
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

		if (part == null || part.isBlank())
			return res;
		String target = part.toLowerCase();

		for (Room r : mockData)
			if (r.getName().toLowerCase().contains(target) || r.getDescription().toLowerCase().contains(target))
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
	 * Inserts new room into repository. Auto-generates incremental ID and validates
	 * data. Ensures uniqueness of object equality.
	 *
	 * @param newRoom Room to insert with complete data
	 * @return Inserted room with assigned ID
	 * @throws RuntimeException if validation fails, room is null, has previous ID,
	 *                          or is duplicate
	 */
	@Override
	public Room insert(Room newRoom)
	{
		if (newRoom == null)
			throw new RuntimeException("Cannot insert null room");
		if (!newRoom.isValid())
			throw new RuntimeException("Invalid room data");
		if (newRoom.getId() != 0)
			throw new RuntimeException("Cannot save a room with previous id");
		if (mockData.contains(newRoom))
			throw new RuntimeException("Duplicate room");

		newRoom.setId(newId());
		mockData.add(newRoom);
		return newRoom;
	}

	/**
	 * Computes a new id based on the existing ones. Reduces the List<Room> to a
	 * single integer representing the first free id.
	 *
	 * @return Next available id (current maximum + 1)
	 */
	private int newId()
	{
		int max = 0;

		// Iterate over all rooms and keep track of the highest id found
		for (Room r : mockData)
			if (r.getId() >= max)
				max = r.getId();

		// The new id is the next integer after the current maximum
		return max + 1;
	}

	// ==================== UPDATE ====================

	/**
	 * Updates an existing room in the repository.
	 * Copies all mutable fields from the provided version into the stored one.
	 *
	 * @param newVersion Room containing updated data; must have a valid existing id
	 * @return The updated room instance
	 * @throws RuntimeException if room does not exist or data is invalid
	 */
	@Override
	public Room update(Room newVersion)
	{
	    Room oldVersion = findById(newVersion.getId());
	    if (oldVersion == null)
	        throw new RuntimeException("Cannot update non existing room");
	    if (!newVersion.isValid())
	        throw new RuntimeException("Invalid room");

	    oldVersion.setName(newVersion.getName());
	    oldVersion.setDescription(newVersion.getDescription());
	    oldVersion.setSize(newVersion.getSize());
	    oldVersion.setFloor(newVersion.getFloor());
	    oldVersion.setPrice(newVersion.getPrice());

	    return oldVersion;
	}

	// ==================== DELETE ====================
	/**
	 * Deletes a guest from the repository by ID.
	 * Performs lookup first, then removes if found.
	 * 
	 * @param id The unique identifier of the guest to delete
	 * @return true if guest was found and deleted, false if guest doesn't exist
	 */
	@Override
	public boolean delete(int id)
	{
		Room room = findById(id);
		if(room == null)
			return false;
		mockData.remove(room);
		return true;
	}
}
