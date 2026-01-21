package com.generation.ba.service;

public class SsnService
{
		
	public static boolean validateSSN(String ssn)
	{
		
        if (ssn == null || ssn.isEmpty()) {
            return false;
        }
        ssn = ssn.toUpperCase().trim();
        
        return checkLength(ssn) && 
                checkAlphaNum(ssn) && 
                checkAlpha(ssn) && 
                checkDigits(ssn);
	}
	public static boolean checkLength(String ssn) { return ssn.length() == 16 ? true : false; }	
	public static boolean checkAlphaNum(String ssn)
	{
        for (int i = 0; i < ssn.length(); i++) {
            if (!Character.isLetterOrDigit(ssn.charAt(i))) {
                return false;
            }
        }
        return true;
    }	
	public static boolean checkAlpha(String ssn){
		for (int i = 0; i < 6; i++)	{
			if (!Character.isLetter(ssn.charAt(i))) return false;
		}
		
		return Character.isLetter(ssn.charAt(8)) 	&&
				Character.isLetter(ssn.charAt(11)) 	&&
				Character.isLetter(ssn.charAt(15)) ? true : false;
	}
	
	public static boolean checkDigits(String ssn) {
		for(int i = 6; i <= 7; i++) {
			if(!Character.isDigit(ssn.charAt(i))) return false;
		}
		
		for(int i = 9; i <= 10; i++) {
			if(!Character.isDigit(ssn.charAt(i))) return false;
		}
		
		for(int i = 12; i <= 14; i++) {
			if(!Character.isDigit(ssn.charAt(i))) return false;
		}
		
		return true;
	}	
}
