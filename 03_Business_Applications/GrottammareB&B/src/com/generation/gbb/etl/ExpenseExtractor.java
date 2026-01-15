package com.generation.gbb.etl;

import java.io.FileNotFoundException;
import java.util.List;

import com.generation.gbb.model.entities.Expense;

public interface ExpenseExtractor
{
	
	/**
     * Extracts guest entities from specified file.
     * Reads and parses file content into structured Guest objects.
     * 
     * @param filename Path to the source file (relative or absolute)
     * @return List of Guest objects extracted from file, empty list if file is empty
     * @throws FileNotFoundException if specified file does not exist or is not accessible
     */
	List<Expense> extractFrom(String filename) throws FileNotFoundException;	
}
