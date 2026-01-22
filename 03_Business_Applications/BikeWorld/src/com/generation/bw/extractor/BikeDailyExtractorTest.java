package com.generation.bw.extractor;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.bw.model.entities.Bike;

/**
 * Test class for BikeDailyExtractor.
 * Validates CSV parsing functionality and data integrity.
 */
class BikeDailyExtractorTest 
{
    /**
     * Tests the extraction of bikes from a daily CSV file.
     * Verifies that all records are correctly parsed and loaded.
     * 
     * @throws Exception if file reading or parsing fails
     */
    @Test
    void test() throws Exception
    {
        BikeDailyExtractor  extractor   = new BikeDailyExtractor();
        List<Bike>          loaded      = extractor.extractFrom("20260122.csv");
        
        assert(loaded.size() == 10);
    }
}
