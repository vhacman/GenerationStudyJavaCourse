package com.generation.library.utils;

import java.util.regex.Pattern;

public class ssnUtil
{
	public boolean hasValidSSN(String ssn)
	{
		if (ssn == null || ssn.isEmpty())
			return false;

		// Il codice fiscale deve rispettare questo pattern
		// [A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]
		// Regex: sta per "regular expression"
		String	patternString = "^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z][0-9]{3}[A-Z]$";
		//                  ^ => inizio stringa $ => fine stringa
		// Questo pattern è in grado di riconoscere se una stringa rispetta la struttura oppure no
		return Pattern.matches(patternString, ssn.toUpperCase().trim());
	}
}
