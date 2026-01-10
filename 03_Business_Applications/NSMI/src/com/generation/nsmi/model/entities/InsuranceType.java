package com.generation.nsmi.model.entities;
/**
 * InsuranceType rappresenta i tipi di copertura assicurativa per un paziente.
 *
 * Valori possibili:
 * - FULL: Copertura assicurativa completa (100%)
 * - PARTIAL: Copertura assicurativa parziale (es. 50-80%)
 * - NONE: Nessuna copertura assicurativa
 */
public enum InsuranceType
{
	FULL,		// Copertura completa
	PARTIAL,	// Copertura parziale
	NONE;		// Nessuna copertura
}
