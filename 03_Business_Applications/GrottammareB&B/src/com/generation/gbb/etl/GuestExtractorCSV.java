package com.generation.gbb.etl;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.entities.Guest;
import com.generation.library.FileReader;

/*
 * TEORIA: Implementazione Extractor per CSV
 * ==========================================
 * 
 * RESPONSABILITÀ:
 * - Lettura file CSV riga per riga
 * - Parsing campi separati da delimitatore (virgola, punto e virgola)
 * - Conversione stringhe in tipi appropriati (int, LocalDate, ecc.)
 * - Gestione errori formato e validazione base
 * 
 * FORMATO CSV ATTESO:
 * id,firstName,lastName,ssn,dob,address,city
 * 1,Mario,Rossi,RSSMRA80A01H501Z,1980-01-01,Via Roma 1,Milano
 * 
 * GESTIONE ERRORI:
 * - FileNotFoundException: file non trovato (propagata al chiamante)
 * - Parsing errors: righe malformate saltate o loggate
 * - Validazione: delegata a Guest.isValid()
 */

/**
 * CSV file implementation of GuestExtractor.
 * Reads guest data from comma-separated values (CSV) files.
 * Expects header row with field names matching Guest attributes.
 */
public class GuestExtractorCSV implements GuestExtractor
{
    /**
     * Extracts guest entities from CSV file.
     * Reads file line by line, skips header, and converts each row to Guest object.
     * 
     * @param filename Path to CSV file
     * @return List of Guest objects extracted from file
     * @throws FileNotFoundException if file doesn't exist
     */
    @Override
    public List<Guest> extractFrom(String filename) throws FileNotFoundException
    {
        List<Guest> res = new ArrayList<>();
        FileReader reader = new FileReader(filename);

        while (reader.hasNext())
        {
            String line = reader.readString();
            Guest g = convertToObject(line);
            res.add(g);
        }        
        reader.close();
        return res;
    }

    /**
     * Converts CSV line to Guest object.
     * Splits line by comma and maps fields to Guest attributes.
     * 
     * Expected format: id,firstName,lastName,ssn,dob,address,city
     * 
     * @param line CSV line with comma-separated values
     * @return Guest object, null if parsing fails
     */
    private Guest convertToObject(String line)
    {
    	String[] fields = line.split(",");
    	Guest g = new Guest();
    	g.setFirstName(fields[0].trim());
     	g.setLastName(fields[1].trim());
     	g.setDob(LocalDate.parse(fields[2]));
    	g.setSsn(fields[3]);
     	g.setCity(fields[4]);
     	g.setAddress(fields[5]);            
      	return g;
    }
}
