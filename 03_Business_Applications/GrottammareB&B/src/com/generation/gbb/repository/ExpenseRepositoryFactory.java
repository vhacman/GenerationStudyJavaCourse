package com.generation.gbb.repository;

/**
 * Factory for creating ExpenseRepository instances.
 * Implements Factory Method pattern to centralize repository creation.
 * Provides abstraction layer between application and data persistence implementation.
 */
public class ExpenseRepositoryFactory
{
    /**
     * Singleton instance of dummy repository for development/testing.
     * Lazy initialization not needed for simplicity.
     */
    static ExpenseRepository dummyExpenseRepo = new DummyExpenseRepository();

    static ExpenseRepository sqlExpenseRepo = new SQLExpenseRepository();

    /**
     * Creates and returns an ExpenseRepository instance.
     * Currently always returns dummy repository for testing.
     * Easy to switch to real database repository later.
     *
     * @return Configured ExpenseRepository instance
     */
    public static ExpenseRepository make()
    {
        return sqlExpenseRepo;
    }
}
