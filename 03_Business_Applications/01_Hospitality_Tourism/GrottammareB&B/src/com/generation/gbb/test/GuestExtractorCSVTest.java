package com.generation.gbb.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.gbb.etl.GuestExtractor;
import com.generation.gbb.etl.GuestExtractorCSV;
import com.generation.gbb.model.entities.Guest;

class GuestExtractorCSVTest 
{

	@Test
	void testLoadData() throws Exception 
	{
		GuestExtractor extractor = 
				new GuestExtractorCSV();
		
		List<Guest> loaded = 
				extractor.extractFrom("importdata/guesthotelcentrale.csv");
		
		assert(loaded.size()==5);
		
	}

}
