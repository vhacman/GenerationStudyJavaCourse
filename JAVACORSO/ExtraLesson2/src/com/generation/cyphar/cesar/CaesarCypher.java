package com.generation.cyphar.cesar;

public class CaesarCypher {
	
	public static String cypher(String msg, int k)
	{
		String	result = "";
		for(int i = 0; i < msg.length(); i++) {
			
			char original = msg.charAt(i);
			char cypher = ( char)((int)original +k);
			result = result + cypher;	
		}
		return result; 
	}

	public static String decypher(String msg, int k)
	{
		String 	result = "";
		for(int i = 0; i < msg.length(); i++)
		{
			
			char original = msg.charAt(i);
			char decypher = ( char)((int)original -k);
			result = result + decypher;	
		}
		return result; 
	}
}