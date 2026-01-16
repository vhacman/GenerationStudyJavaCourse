package com.generation.gbb.repository;

/**
 * Factory for creating GuestRepository instances.
 * Implements Factory Method pattern to centralize repository creation.
 */
public class GuestRepositoryFactory
{
    /**
     * Singleton instance of dummy repository for development/testing.
     * Lazy initialization not needed for simplicity.
     */
    static GuestRepository dummyGuestRepo = new DummyGuestRepository();
    
    static GuestRepository sqlRepo = new SQLGuestRepository();

    /**
     * Creates and returns a GuestRepository instance.
     * Currently always returns dummy repository for testing.
     * Easy to extend for database repositories later.
     *
     * @return Configured GuestRepository instance
     */
    public static GuestRepository make()
    { 
        return sqlRepo;
    }
}
