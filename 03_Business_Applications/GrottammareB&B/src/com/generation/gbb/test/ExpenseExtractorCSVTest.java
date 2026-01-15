package com.generation.gbb.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.gbb.etl.ExpenseExtractor;
import com.generation.gbb.etl.ExpenseExtractorCSV;
import com.generation.gbb.model.entities.Expense;

/**
 * Test class for ExpenseExtractorCSV.
 * Validates CSV file extraction and parsing functionality.
 */
class ExpenseExtractorCSVTest
{
    /**
     * Tests expense data loading from CSV file.
     * Verifies that extractor correctly reads and parses all expense records.
     * 
     * @throws Exception if file not found or parsing fails
     */
    @Test
    void testLoadData() throws Exception
    {
        ExpenseExtractor extractor = new ExpenseExtractorCSV();
        
        List<Expense> loaded = extractor.extractFrom("importData/expenses.csv");
        
        assert(loaded.size() == 3);
    }
}
