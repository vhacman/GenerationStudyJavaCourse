package com.generation.gbb.repository;

/**
 * Factory for creating RoomRepository instances.
 * Implements Factory Method pattern to centralize repository creation.
 */
public class RoomRepositoryFactory
{
    /**
     * Singleton instance of dummy repository for development/testing.
     * Lazy initialization not needed for simplicity.
     */
    static	RoomRepository	dummyRoomRepo = new DummyRoomRepository();
    static	RoomRepository	sqlRoomRepo   = new SQLRoomRepository();

    /**
     * Creates and returns a RoomRepository instance.
     * Currently always returns dummy repository for testing.
     * Easy to switch to real database repository later.
     *
     * @return Configured RoomRepository instance
     */
    public static RoomRepository make()
    {
        return sqlRoomRepo;
    }
}
