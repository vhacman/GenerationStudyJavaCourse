package com.generation.vr.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Enum degli hotel convenzionati
 * Contiene: nome, indirizzo, prezzo per notte
 */
public enum Hotel 
{
	// Costanti enum: ogni hotel è un'istanza pre-definita con i suoi valori
	// Sintassi: NOME_COSTANTE(parametri del costruttore)
	MIRASOLE("Hotel Mirasole", "Via Milano 1", new BigDecimal("78.42")),
	NAIADI("Anantara Palazzo Naiadi", "Piazza della Repubblica, 48-49", new BigDecimal("579.17"));
	
	// Attributi: ogni istanza dell'enum ha questi campi
	private String 		name;		// Nome dell'hotel
	private String 		address;	// Indirizzo dell'hotel
	private BigDecimal 	price;		// Prezzo per una notte (BigDecimal per precisione monetaria)
	
	// Costruttore: chiamato automaticamente per ogni costante enum dichiarata sopra
	// È SEMPRE privato negli enum (non può essere pubblico)
	Hotel(String name, String address, BigDecimal price) 
	{
		this.name = name;
		this.address = address;
		this.price = price;
	}
	
	// Getter: metodi per accedere agli attributi privati
	public String 		getName() {return name; }
	public String 		getAddress() {return address;}
	public BigDecimal 	getPrice() {return price;}
	
	/**
	 * Valida che tutti i campi dell'hotel siano corretti
	 * @return true se l'hotel ha dati validi, false altrimenti
	 */
	public boolean	isValid()
	{
		// Controllo nome: non null e non vuoto (trim() rimuove spazi)
		return name != null && !name.trim().isEmpty() &&
			   // Controllo indirizzo: non null e non vuoto
			   address != null && !address.trim().isEmpty() &&
			   // Controllo prezzo: non null e >= 0 (compareTo restituisce -1, 0 o 1)
			   price != null && price.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	/**
	 * uso: setScale() --> è un metodo di BigDecimal che controlla quante cifre decimali vuoi nel numero.
	 * 
	 * Restituisce il prezzo formattato con 2 decimali
	 * Esempio: 78.4 diventa "78.40"
	 * @return prezzo come stringa con esattamente 2 decimali
	 */
	public String	getFormattedPrice()
	{
		// setScale(2, ...) = imposta 2 cifre decimali
		// RoundingMode.HALF_UP = arrotonda per eccesso se necessario (es: 78.426 -> 78.43)
		return price.setScale(2, RoundingMode.HALF_UP).toString();
	}
}