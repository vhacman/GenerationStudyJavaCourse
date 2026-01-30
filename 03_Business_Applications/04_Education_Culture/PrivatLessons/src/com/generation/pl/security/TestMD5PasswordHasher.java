package com.generation.pl.security;

import org.junit.jupiter.api.Test;

import com.generation.library.Console;

class TestMD5PasswordHasher 
{

	@Test
	void testSimilarity() 
	{
		
		PasswordHasher hasher = new MD5PasswordHasher();
		
		String hash1 = hasher.hash("pippo");
		String hash2 = hasher.hash("Pippo");
		
		Console.print(hash1);
		Console.print(hash2);
		
		assert(!hash1.equals(hash2));
		
	}

}
