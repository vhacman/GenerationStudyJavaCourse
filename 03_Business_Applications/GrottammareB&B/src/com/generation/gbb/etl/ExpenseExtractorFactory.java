package com.generation.gbb.etl;
/**
 * Factory class for creating appropriate ExpenseExtractor implementations.
 * Selects extractor based on file extension (csv, json, xml).
 * Implements Factory Method pattern for extractor instantiation.
 */
public class ExpenseExtractorFactory
{
    /** Singleton CSV extractor instance for reuse. */
    static ExpenseExtractor extractorCSV = new ExpenseExtractorCSV();

    /**
     * Creates appropriate ExpenseExtractor based on filename extension.
     * Determines file type from extension and returns matching extractor.
     * 
     * @param filename Name of file to extract from (must include extension)
     * @return ExpenseExtractor implementation for file type, null if unsupported format
     */
    public static ExpenseExtractor make(String filename)
    {
        if (filename.endsWith(".csv"))
            return extractorCSV;
        
        return null;
    }
}
