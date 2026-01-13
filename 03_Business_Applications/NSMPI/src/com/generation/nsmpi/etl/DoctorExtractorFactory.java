package com.generation.nsmpi.etl;

public class DoctorExtractorFactory
{
	static DoctorExtractor dummy = new DummyDoctorExtractor();

	/**
	 * Gli passo il nome del file
	 * creo un estrattore.
	 * @param filename
	 * @return
	 */
	public static DoctorExtractor make (String filename)
	{
		return dummy;
	}
}
