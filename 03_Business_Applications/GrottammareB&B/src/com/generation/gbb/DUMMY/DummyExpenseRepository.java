package com.generation.gbb.DUMMY;

import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.entities.Expense;
import com.generation.gbb.model.entities.ExpenseCategory;
import com.generation.gbb.repository.interfaces.ExpenseRepository;

public class DummyExpenseRepository implements ExpenseRepository
{

    List<Expense> mockData = new ArrayList<>();

	
    /**
     * Initializes repository with test data.
     * Populates mockData with sample expenses to simulate initial dataset.
     */
    public DummyExpenseRepository()
    {
        mockData.add(new Expense(1, "2024-01-15", "Acquisto verdure", 150, ExpenseCategory.FOOD));
        mockData.add(new Expense(2, "2024-01-16", "Carne e pesce", 320, ExpenseCategory.FOOD));
        mockData.add(new Expense(3, "2024-01-18", "Bolletta elettricità", 450, ExpenseCategory.SERVICES));
        mockData.add(new Expense(4, "2024-01-20", "Pulizie settimanali", 180, ExpenseCategory.SERVICES));
        mockData.add(new Expense(5, "2024-01-25", "Stipendio gennaio", 2500, ExpenseCategory.SALARIES));
        mockData.add(new Expense(6, "2024-02-01", "Forniture cucina", 275, ExpenseCategory.FOOD));
        mockData.add(new Expense(7, "2024-02-05", "Manutenzione impianto", 650, ExpenseCategory.SERVICES));
    }
    
    /**
     * Retrieves all expenses from repository.
     *
     * @return Complete list of expenses in memory
     */
    @Override
    public List<Expense> findAll()
    {
        return mockData;
    }
    
    /**
     * Finds expense by unique identifier.
     * Iterates through list until ID match is found.
     *
     * @param id Expense numeric identifier
     * @return Found expense, null if not exists
     */
    @Override
    public Expense findById(int id)
    {
    	for(Expense e : mockData)
    		if(e.getId() == id)
    			return e;
    	return null;
    }

    /**
     * Finds expenses by category.
     * Exact match search for expense classification.
     *
     * @param category Expense category to filter
     * @return List of matching expenses, empty list if no results
     */
    @Override
    public List<Expense> findByCategory(ExpenseCategory category)
    {
    	List<Expense> result = new ArrayList<>();
    	if (category == null)
    		return result;
    	for(Expense e : mockData)
    		if(e.getCategory() == category)
    			result.add(e);
    	return result;
    }

    /**
     * Inserts new expense into repository.
     * Auto-generates incremental ID and validates data.
     *
     * @param newExpense Expense to insert with complete data
     * @return Inserted expense with assigned ID
     * @throws RuntimeException if validation fails, expense is null, has previous ID, or is duplicate
     */
    @Override
    public Expense insert(Expense newExpense)
    {
    	if(newExpense == null)
            throw new RuntimeException("Cannot insert null expense");
    	if(!newExpense.isValid())
            throw new RuntimeException("Cannot save an expense with previous id");
    	if (mockData.contains(newExpense))
            throw new RuntimeException("Duplicate expense");
    	newExpense.setId(newId());
    	mockData.add(newExpense);
    	return newExpense;
    		
    }

    /**
     * Computes a new id based on the existing ones.
     * Reduces the List<Expense> to a single integer representing the first free id.
     *
     * @return Next available id (current maximum + 1)
     */
    private int newId()
    {
        int max = 0;
        // Iterate over all expenses and keep track of the highest id found
        for (Expense e : mockData)
            if (e.getId() >= max)
                max = e.getId();
        // The new id is the next integer after the current maximum
        return max + 1;
    }

    /**
     * Updates an existing expense in the repository.
     * Copies all mutable fields from the provided version into the stored one.
     *
     * @param newVersion Expense containing updated data; must have a valid existing id
     * @return The updated expense instance
     * @throws RuntimeException if expense does not exist or data is invalid
     */
    @Override
    public Expense update(Expense newVersion)
    {
        Expense oldVersion = findById(newVersion.getId());
        if (oldVersion == null)
            throw new RuntimeException("Cannot update non existing expense");
        if (!newVersion.isValid())
            throw new RuntimeException("Invalid expense");

        oldVersion.setDate(newVersion.getDate());
        oldVersion.setDescription(newVersion.getDescription());
        oldVersion.setValue(newVersion.getValue());
        oldVersion.setCategory(newVersion.getCategory());

        return oldVersion;
    }

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
		Expense expense = findById(id);
		if(expense == null)
			return false;
		mockData.remove(expense);
		return true;
	}
}
