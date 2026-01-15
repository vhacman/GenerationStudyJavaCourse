package com.generation.library;

import java.util.List;

/**
 * Entità base del dominio con identificativo e validazione.
 * Classe astratta che delega getErrors() alle sottoclassi (Template Method).
 */
public abstract class Entity
{
	protected int id;

	public Entity() {}

	/**
	 * Inizializza l'entità con un identificativo specifico.
	 *
	 * @param id Identificativo univoco dell'entità
	 */
	public Entity(int id)
	{
		super();
		this.id = id;
	}

	public int  getId()       { return id;     }
	public void setId(int id) { this.id = id;  }


	public abstract List<String> getErrors();

	/**
	 * Verifica se l'entità è valida (nessun errore).
	 * Implementa il pattern Template Method delegando la validazione specifica a getErrors().
	 *
	 * @return true se la lista di errori è vuota, false altrimenti
	 */
	public boolean isValid()
	{
		/*
		 * Pattern Template Method:
		 *      * Classe Astratta (Entity)  →  Definisce l'algoritmo di validazione
		 *      * Sottoclassi Concrete      →  Implementano i criteri specifici (getErrors)
		 *
		 * Questo metodo fornisce un'interfaccia uniforme per verificare la validità,
		 * delegando la logica specifica di validazione alle sottoclassi tramite getErrors().
		 * Principio OCP (Open-Closed): aperto all'estensione, chiuso alla modifica.
		 */
		return getErrors().isEmpty();
	}

	/**
	 * Verifica presenza di una stringa (non-null e non-vuota).
	 * Metodo di utilità per la validazione comune a tutte le entità.
	 *
	 * @param s Stringa da verificare
	 * @return true se presente, false altrimenti
	 */
	protected boolean notMissing(String s)
	{
		/*
		 * Incapsulamento e Riuso:
		 *      * Logica Comune        →  Centralizzata nella classe base
		 *      * Protected Visibility →  Accessibile solo alle sottoclassi
		 *
		 * Questo metodo evita duplicazione di codice nelle sottoclassi,
		 * fornendo una validazione standardizzata per campi stringa.
		 * Principio DRY (Don't Repeat Yourself).
		 */
		return s != null && !s.isBlank();
	}

	/**
	 * Verifica assenza di una stringa (null o vuota).
	 * Metodo di utilità per la validazione comune a tutte le entità.
	 *
	 * @param s Stringa da verificare
	 * @return true se mancante, false altrimenti
	 */
	protected boolean isMissing(String s)
	{
		/*
		 * Incapsulamento e Riuso:
		 *      * Logica Comune        →  Centralizzata nella classe base
		 *      * Protected Visibility →  Accessibile solo alle sottoclassi
		 *
		 * Metodo complementare a notMissing(), segue il principio di coesione
		 * mantenendo la logica di validazione in un unico punto della gerarchia.
		 * Principio DRY (Don't Repeat Yourself).
		 */
		return s == null || s.isBlank();
	}


	@Override
	public String toString()
	{
		return "Entity [id=" + id + "]";
	}
}
