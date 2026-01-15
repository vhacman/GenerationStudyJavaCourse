package com.generation.gbb.repository;

import java.util.List;
import com.generation.gbb.model.entities.Expense;
import com.generation.gbb.model.entities.ExpenseCategory;

/**
 * Interface for Expense persistence management.
 * Defines CRUD operations for Expense entity.
 * Supports financial expense tracking and reporting.
 */
public interface ExpenseRepository
{
    // ==================== READ ====================

    /**
     * R - Retrieves all expenses available in the system.
     *
     * @return List of all expenses
     */
    List<Expense> findAll();

    /**
     * R - Retrieves single expense by its unique identifier.
     *
     * @param id Expense identifier to search
     * @return Found expense, null if not exists
     */
    Expense findById(int id);

    /**
     * R - Finds all expenses belonging to specific category.
     * Exact match search for expense classification.
     *
     * @param category Expense category to filter (FOOD, SERVICES, SALARIES)
     * @return List of expenses in category, empty list if no matches
     */
    List<Expense> findByCategory(ExpenseCategory category);

    // ==================== CREATE ====================

    /**
     * C - Inserts new expense into the system.
     *
     * @param newExpense Expense to insert with all required data
     * @return Inserted expense with assigned ID, null if insertion fails
     */
    Expense insert(Expense newExpense);

    // ==================== UPDATE ====================

    /**
     * U - Updates existing expense data.
     *
     * @param newVersion Updated expense (must have valid existing ID)
     * @return Updated expense, null if update fails or expense not found
     */
    Expense update(Expense newVersion);

    // ==================== DELETE ====================

    /**
     * D - Deletes expense from system by identifier.
     *
     * @param id Expense identifier to delete
     * @return true if deletion successful, false otherwise
     */
    boolean delete(int id);
}
