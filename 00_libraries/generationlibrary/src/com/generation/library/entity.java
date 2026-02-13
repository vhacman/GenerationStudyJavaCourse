package com.generation.library;

import java.util.List;

/**
 * Classe base astratta per tutte le entità del dominio.
 * Definisce stato e comportamento comuni (ID, validazione).
 */
public abstract class Entity
{

	protected int  id;


	/**
	 * Restituisce l'identificativo dell'entità.
	 * @return ID dell'entità
	 */
	public int getId() { return id; }


	/**
	 * Imposta l'identificativo dell'entità.
	 * @param id Nuovo ID
	 */
	public void setId(int id) { this.id = id; }


	/**
	 * Metodo astratto per ottenere gli errori di validazione.
	 * Ogni sottoclasse implementa le proprie regole di validazione.
	 *
	 * @return Lista di messaggi di errore (vuota se valido)
	 */
	public abstract List<String> getErrors();


	/**
	 * Verifica se l'entità è in uno stato valido.
	 *
	 * @return true se non ci sono errori di validazione
	 */
	public boolean isValid()
	{
		/*
		 * Template Method Pattern:
		 * → isValid() fornisce l'algoritmo uniforme
		 * → getErrors() è delegato alle sottoclassi
		 * → Lista vuota = entità valida
		 */
		return getErrors().isEmpty();
	}


	/**
	 * Verifica se una stringa ha un valore valido (non null e non vuota).
	 *
	 * @param s Stringa da verificare
	 * @return true se la stringa è valida
	 */
	protected boolean notMissing(String s)
	{
		/*
		 * Protected utility method:
		 * → Visibile alle sottoclassi
		 * → Incapsula logica di validazione riutilizzabile
		 */
		return s != null && !s.isBlank();
	}


	/**
	 * Verifica se una stringa è null o vuota.
	 *
	 * @param s Stringa da verificare
	 * @return true se la stringa manca
	 */
	protected boolean isMissing(String s)
	{
		/*
		 * Complemento semantico di notMissing():
		 * → if(isMissing(title)) → lettura più naturale
		 */
		return s == null || s.isBlank();
	}

}
