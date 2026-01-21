package com.generation.vr.model.entities;

/**
 * Enum delle guide turistiche disponibili
 * Contiene: nome, valutazione (1-5 stelle)
 */
public enum TourGuide
{
	// Costanti enum: ogni guida turistica è un'istanza pre-definita con i suoi valori
	// Sintassi: NOME_COSTANTE(parametri del costruttore)
	MARCO("Marco Rossi", 5),
	GIULIA("Giulia Bianchi", 4),
	ALESSANDRO("Alessandro Verdi", 5),
	FRANCESCA("Francesca Neri", 3);

	// Attributi: ogni istanza dell'enum ha questi campi
	private String 	name;		// Nome della guida turistica
	private int 	rating;		// Valutazione da 1 a 5 stelle

	// Costruttore: chiamato automaticamente per ogni costante enum dichiarata sopra
	// È SEMPRE privato negli enum (non può essere pubblico)
	TourGuide(String name, int rating) {
		this.name = name;
		this.rating = rating;
	}

	// Getter: metodi per accedere agli attributi privati
	public String getName() {return name;}
	public int getRating() {return rating;}

	/**
	 * Valida che tutti i campi della guida turistica siano corretti
	 * @return true se la guida ha dati validi, false altrimenti
	 */
	public boolean isValid()
	{
		// Controllo nome: non null e non vuoto (trim() rimuove spazi)
		return name != null && !name.trim().isEmpty() &&
			   // Controllo rating: deve essere compreso tra 1 e 5 (inclusi)
			   rating >= 1 && rating <= 5;
	}
}