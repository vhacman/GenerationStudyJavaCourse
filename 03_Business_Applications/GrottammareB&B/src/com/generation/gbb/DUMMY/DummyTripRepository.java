package com.generation.gbb.DUMMY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.entities.Trip;
import com.generation.gbb.repository.interfaces.TripRepository;

/**
 * In-memory repository implementation for Trip management. Uses mockData list
 * to simulate database persistence.
 */
public class DummyTripRepository implements TripRepository
{
    /** List containing all trips in memory (database mock). */
    List<Trip> mockData = new ArrayList<>();

    /**
     * Initializes repository with test data. Populates mockData with sample trips
     * to simulate initial dataset.
     */
    public DummyTripRepository()
    {
        mockData.add(new Trip(1, "Roma", LocalDate.of(2025, 3, 15), "Città meravigliosa, ricca di storia", 9));
        mockData.add(new Trip(2, "Firenze", LocalDate.of(2025, 5, 20), "Arte e cultura straordinarie", 10));
        mockData.add(new Trip(3, "Venezia", LocalDate.of(2024, 8, 10), "Romantica e unica", 8));
        mockData.add(new Trip(4, "Milano", LocalDate.of(2024, 11, 5), "Moderna e dinamica", 7));
    }

    // ==================== READ ====================

    /**
     * Retrieves all trips from repository.
     *
     * @return Complete list of trips in memory
     */
    @Override
    public List<Trip> findAll()
    {
        return mockData;
    }

    /**
     * Finds trip by unique identifier. Iterates through list until ID match found.
     *
     * @param id Trip numeric identifier
     * @return Found trip, null if not exists
     */
    @Override
    public Trip findById(int id)
    {
        for (Trip t : mockData)
        {
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    /**
     * Searches trips whose city contains specified substring.
     * Case-insensitive search. Returns empty list if city is null or blank.
     *
     * @param city String to search in city name
     * @return List of Trip objects matching search criteria
     */
    @Override
    public List<Trip> findByCity(String city)
    {
        List<Trip> res = new ArrayList<>();

        if (city == null || city.isBlank())
            return res;
        String target = city.toLowerCase();

        for (Trip t : mockData)
            if (t.getCity().toLowerCase().contains(target))
                res.add(t);

        return res;
    }

    /**
     * Filters trips by year.
     *
     * @param year Year to filter
     * @return List of Trip objects in the specified year
     */
    @Override
    public List<Trip> findByYear(int year)
    {
        List<Trip> result = new ArrayList<>();

        for (Trip t : mockData)
            if (t.getDate().getYear() == year)
                result.add(t);

        return result;
    }

    // ==================== CREATE ====================

    /**
     * Inserts new trip into repository. Auto-generates incremental ID and validates
     * data. Ensures uniqueness of object equality.
     *
     * @param newTrip Trip to insert with complete data
     * @return Inserted trip with assigned ID
     * @throws RuntimeException if validation fails, trip is null, has previous ID,
     *                          or is duplicate
     */
    @Override
    public Trip insert(Trip newTrip)
    {
        if (newTrip == null)
            throw new RuntimeException("Cannot insert null trip");
        if (!newTrip.isValid())
            throw new RuntimeException("Invalid trip data");
        if (newTrip.getId() != 0)
            throw new RuntimeException("Cannot save a trip with previous id");
        if (mockData.contains(newTrip))
            throw new RuntimeException("Duplicate trip");

        newTrip.setId(newId());
        mockData.add(newTrip);
        return newTrip;
    }

    /**
     * Computes a new id based on the existing ones. Reduces the List<Trip> to a
     * single integer representing the first free id.
     *
     * @return Next available id (current maximum + 1)
     */
    private int newId()
    {
        int max = 0;

        // Iterate over all trips and keep track of the highest id found
        for (Trip t : mockData)
            if (t.getId() >= max)
                max = t.getId();

        // The new id is the next integer after the current maximum
        return max + 1;
    }

    // ==================== UPDATE ====================

    /**
     * Updates an existing trip in the repository.
     * Copies all mutable fields from the provided version into the stored one.
     *
     * @param newVersion Trip containing updated data; must have a valid existing id
     * @return The updated trip instance
     * @throws RuntimeException if trip does not exist or data is invalid
     */
    @Override
    public Trip update(Trip newVersion)
    {
        Trip oldVersion = findById(newVersion.getId());
        if (oldVersion == null)
            throw new RuntimeException("Cannot update non existing trip");
        if (!newVersion.isValid())
            throw new RuntimeException("Invalid trip");

        oldVersion.setCity(newVersion.getCity());
        oldVersion.setDate(newVersion.getDate());
        oldVersion.setReview(newVersion.getReview());
        oldVersion.setScore(newVersion.getScore());

        return oldVersion;
    }

    // ==================== DELETE ====================
    /**
     * Deletes a trip from the repository by ID.
     * Performs lookup first, then removes if found.
     *
     * @param id The unique identifier of the trip to delete
     * @return true if trip was found and deleted, false if trip doesn't exist
     */
    @Override
    public boolean delete(int id)
    {
        Trip trip = findById(id);
        if(trip == null)
            return false;
        mockData.remove(trip);
        return true;
    }
}
