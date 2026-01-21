package com.generation.ba.model.entities;

/**
 * Enumerazione dei paesi supportati dall'applicazione bancaria.
 * Ogni paese ha associato il proprio formato di visualizzazione della data.
 */
public enum Country {

	ITALY("dd/MM/yyyy"),
	USA("MM/dd/yyyy"),
	UK("dd/MM/yyyy"),
	FRANCE("dd/MM/yyyy"),
	GERMANY("dd.MM.yyyy"),
	UNIVERSAL("yyyy-MM-dd");
	
	/**
	 * Formato della data per il paese specifico.
	 */
	private String dateFormat;
	
	/**
	 * Costruttore dell'enumerazione.
	 * 
	 * @param dateFormat il formato della data per il paese
	 */
	private Country(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	/**
	 * Ottiene il formato data del paese.
	 * 
	 * @return il formato della data (es. "dd/MM/yyyy")
	 */
	public String getDateFormat() {
		return dateFormat;
	}
}