package com.generation.lmdb.model.entities;

/**
 * Un batch può essere conforme, non conforme o ancora da verificare
 */
public enum BatchStatus
{
	VALIDATED,	// Lotto conforme, qualità verificata e approvata
	CORRUPT,	// Lotto non conforme, problemi di qualità
	PENDING		// Lotto in attesa di verifica
}
