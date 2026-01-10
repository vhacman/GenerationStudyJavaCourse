package com.generation.lm.model.entities;

/**
 * Un batch può essere conforme, non conforme o ancora da verificare
 */
public enum BatchStatus 
{
	VALIDATED,
	CORRUPT,
	PENDING	
}
