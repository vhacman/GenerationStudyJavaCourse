package com.generation.pl.security;

public interface PasswordHasher 
{

	String hash(String plainPassword);
	
	
}
