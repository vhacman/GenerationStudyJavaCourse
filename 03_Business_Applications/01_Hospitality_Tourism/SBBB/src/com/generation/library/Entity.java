package com.generation.library;

import java.util.List;

/**
 * Base per tutte le altre entità.
 * Questa classe definisce una base di stato
 * e una base di comportamento per tutte le entità.
 */
public abstract class Entity
{
	protected int	id;


	/**
	 * Restituisce un insieme ordinato di stringhe contenente gli errori di validazione.
	 * @return lista di errori
	 */
	public abstract List<String> getErrors();

	/**
	 * Verifica se l'entità è valida.
	 * @return true se non ci sono errori, false altrimenti
	 */
	public boolean isValid()
	{
		/*
		 ******************************************
		 * Un'entità è valida se la lista degli
		 * errori restituita da getErrors() è vuota
		 ******************************************
		 */
		return getErrors().isEmpty();
	}

	/**
	 * Verifica se una stringa non è null né vuota.
	 * @param s la stringa da verificare
	 * @return true se la stringa contiene dati validi
	 */
	protected boolean notMissing(String s)
	{
		/*
		 ******************************************
		 * Controlla che la stringa non sia null
		 * e che contenga almeno un carattere
		 * non blank (spazio, tab, newline)
		 ******************************************
		 */
		return 	s != null 		&& !s.isBlank()	;
	}

	/**
	 * Verifica se una stringa è null o vuota.
	 * @param s la stringa da verificare
	 * @return true se la stringa è null o blank
	 */
	protected boolean isMissing(String s)
	{
		/*
		 ******************************************
		 * Controlla che la stringa sia null
		 * oppure che sia vuota o contenga solo
		 * caratteri blank (spazio, tab, newline)
		 ******************************************
		 */
		return 	s == null 		||  s.isBlank()		;
	}

	public int  getId()         { return id; }
	public void setId(int id)   { this.id = id; }

}
