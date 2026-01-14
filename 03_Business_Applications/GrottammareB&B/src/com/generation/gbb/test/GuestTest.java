package com.generation.gbb.test;

import org.junit.jupiter.api.Test;

import com.generation.gbb.model.entities.Guest;

class GuestTest
{

	@Test
	void testValidazioneSSN()
	{
		Guest g = new Guest();

		g.setSsn("HCMVCG98R61Z129");
		assert !g.hasValidSSN();

		g.setSsn("HCGVCG98R61Z129E");
		assert g.hasValidSSN();
	}

	@Test
	void testGetAge()
	{
		Guest g = new Guest();

		// Test con data di nascita null
		assert g.getAge() == 0;

		// Test con persona nata il 15 marzo 2000 (25-26 anni nel 2026)
		g.setDob("2000-03-15");
		assert g.getAge() == 25 || g.getAge() == 26;

		// Test con persona nata il 10 gennaio 2006 (19-20 anni nel 2026)
		g.setDob("2006-01-10");
		assert g.getAge() == 19 || g.getAge() == 20;

		// Test con persona nata il 5 settembre 2008 (minorenne, 17-18 anni nel 2026)
		g.setDob("2008-09-05");
		assert g.getAge() == 17 || g.getAge() == 18;

		// Test con persona nata il 20 giugno 1945 (anziana, 80-81 anni nel 2026)
		g.setDob("1945-06-20");
		assert g.getAge() == 80 || g.getAge() == 81;

		// Test con persona nata il 1 gennaio 1998 (28 anni nel 2026)
		g.setDob("1998-01-01");
		assert g.getAge() == 28;
	}

	@Test
	void testValidazioneEtaMaggiorenne()
	{
		Guest g = new Guest();
		g.setFirstName("Mario");
		g.setLastName("Rossi");
		g.setSsn("RSSMRA85M01H501A");
		g.setAddress("Via Roma 1");
		g.setCity("Milano");

		// Test con minorenne nato nel 2010 (14-15 anni nel 2026)
		g.setDob("2010-05-20");
		assert !g.isValid();
		assert g.getErrors().contains("L'ospite deve essere maggiorenne (almeno 18 anni)");

		// Test con maggiorenne nato nel 2007 (18-19 anni nel 2026)
		g.setDob("2007-03-15");
		assert g.isValid();

		// Test con maggiorenne nato nel 2000 (25-26 anni nel 2026)
		g.setDob("2000-11-08");
		assert g.isValid();
	}

	@Test
	void testValidazioneCompleta()
	{
		Guest g = new Guest();

		// Test con tutti i campi mancanti
		assert !g.isValid();
		assert g.getErrors().size() == 6;

		// Aggiungiamo i campi uno alla volta
		g.setFirstName("Mario");
		assert g.getErrors().size() == 5;

		g.setLastName("Rossi");
		assert g.getErrors().size() == 4;

		g.setSsn("RSSMRA85M01H501A");
		assert g.getErrors().size() == 3;

		g.setDob("1995-07-12"); // Nato nel 1995, 30-31 anni nel 2026
		assert g.getErrors().size() == 2;

		g.setAddress("Via Roma 1");
		assert g.getErrors().size() == 1;

		g.setCity("Milano");
		assert g.getErrors().size() == 0;
		assert g.isValid();
	}

}
