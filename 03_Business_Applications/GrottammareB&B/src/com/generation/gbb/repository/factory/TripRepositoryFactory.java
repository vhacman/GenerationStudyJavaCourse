package com.generation.gbb.repository.factory;

import com.generation.gbb.DUMMY.DummyTripRepository;
import com.generation.gbb.repository.SQLTripRepository;
import com.generation.gbb.repository.interfaces.TripRepository;

/**
 * Factory for creating TripRepository instances.
 * Implements Factory Method pattern to centralize repository creation.
 */
public class TripRepositoryFactory
{
    /**
     * Singleton instance of dummy repository for development/testing.
     * Lazy initialization not needed for simplicity.
     */
    static  TripRepository  dummyTripRepo = new DummyTripRepository();
    static  TripRepository  sqlTripRepo   = new SQLTripRepository();

    /**
     * Creates and returns a TripRepository instance.
     * Currently returns SQL repository for production.
     * Easy to switch to dummy repository for testing.
     *
     * @return Configured TripRepository instance
     */
    public static TripRepository make()
    {
        return sqlTripRepo;
    }
}
