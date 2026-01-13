package com.generation.nsmpi.etl;

public class PatientExtractorFactory
{
	static PatientExtractor dummy = new DummyPatientExtractor();
	
	/**
	 * Gli passo il nome del file
	 * creo un estrattore. 
	 * @param filename
	 * @return
	 */
	public static PatientExtractor make (String filename)
	{
		return dummy;
	}
}
