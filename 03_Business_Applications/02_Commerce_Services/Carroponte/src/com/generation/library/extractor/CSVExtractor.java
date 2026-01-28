package com.generation.library.extractor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;
import com.generation.library.FileReader;

public interface CSVExtractor<X extends Entity> 
{

	// trasformo una riga del file CSV in una entità X
	X rowToX(String row) throws RuntimeException;
	
	
	default List<X> extractFrom(String filename) throws FileNotFoundException, RuntimeException
	{
		FileReader reader = new FileReader(filename);
		List<X> res = new ArrayList<X>();
		while(reader.hasNext())
			res.add(rowToX(reader.readString()));
		reader.close();
		return res;
	}
	
	
}
