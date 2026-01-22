package com.generation.bw.extractor;

import com.generation.bw.model.entities.Bike;
import com.generation.bw.model.entities.BikeStatus;
import com.generation.library.extractor.CSVExtractor;

/**
 * Extractor implementation for parsing CSV data into Bike entities.
 * Handles daily bike import files with validation and error handling.
 */
public class BikeDailyExtractor implements CSVExtractor<Bike>
{
    /**
     * Converts a CSV row string into a Bike entity.
     * 
     * @param row the CSV row with comma-separated bike data (9 fields expected)
     * @return fully populated Bike entity
     * @throws RuntimeException if row format is invalid or data parsing fails
     */
    @Override
    public Bike rowToX(String row) throws RuntimeException 
    {
        /*
         * ═════════════════════════════════════════════════════════════════
         *  Pattern: Data Transformer (CSV to Object Mapping)
         * ═════════════════════════════════════════════════════════════════
         * 
         *  CSV String  →  String[] (split)  →  Bike object (parsing)
         *  
         *  Implementa la separazione tra formato di input (CSV) e
         *  rappresentazione interna (Bike entity). Il pattern Strategy
         *  permette di sostituire facilmente l'extractor per altri
         *  formati (JSON, XML) senza modificare il logic.
         * ═════════════════════════════════════════════════════════════════
         */
        String[] parts = row.split(",", -1);
        
        // Verifica che ci siano almeno 9 colonne nella riga
        if (parts.length < 9) 
        {
            throw new RuntimeException("Row has insufficient columns: " + row);
        }

        try 
        {
            Bike b = new Bike();
            
            b.setId     (Integer.parseInt(parts[0].trim()));
            b.setBrand  (parts[1].trim());
            b.setModel  (parts[2].trim());
            b.setPlate  (parts[3].trim());
            b.setPower  (Integer.parseInt(parts[4].trim()));
            b.setCost   (Integer.parseInt(parts[5].trim()));
            b.setWork   (Integer.parseInt(parts[6].trim()));
            b.setPrice  (Integer.parseInt(parts[7].trim()));
            
            String statusStr = parts[8].trim();
            if (!statusStr.isEmpty()) 
            {
                b.setStatus(BikeStatus.valueOf(statusStr));
            }

            return b;
        } 
        catch (NumberFormatException e) 
        {
            throw new RuntimeException("Error parsing numeric value in row: " + row, e);
        } 
        catch (IllegalArgumentException e) 
        {
            throw new RuntimeException("Invalid BikeStatus in row: " + row, e);
        }
    }
}
