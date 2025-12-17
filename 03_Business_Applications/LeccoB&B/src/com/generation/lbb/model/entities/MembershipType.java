package com.generation.lbb.model.entities;

/**
 * ENUM che rappresenta i tipi di abbonamento (membership) disponibili.
 * Ogni tipo di abbonamento ha uno sconto percentuale associato.
 */

public enum MembershipType
{
	// ═══════════════════════════════════════════════════════════════
	// COSTANTI PREDEFINITE (livelli di membership)
	// ═══════════════════════════════════════════════════════════════
	// Questi sono i 3 livelli di abbonamento possibili.
	// Il valore tra parentesi è lo sconto percentuale.
	
	NONE(0),
	SILVER(0.1),
	GOLD(0.2);
	
	double discount;
	
	private MembershipType(double d)
	{
		this.discount = d;
	}
}
