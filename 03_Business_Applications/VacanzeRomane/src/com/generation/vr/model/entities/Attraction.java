package com.generation.vr.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;

/**
 * Enum delle attrazioni turistiche visitabili a Roma
 * Contiene: nome, prezzo biglietto, orari apertura/chiusura
 */
public enum Attraction
{
	// Costanti enum: ogni attrazione è un'istanza pre-definita con i suoi valori
	// Sintassi: NOME_COSTANTE(parametri del costruttore)
	COLOSSEO("Colosseo", new BigDecimal("5.50"), LocalTime.of(9, 0), LocalTime.of(18, 0)),
	FORO("Foro Romano", new BigDecimal("24.00"), LocalTime.of(9, 30), LocalTime.of(17, 0)),
	PANTHEON("Pantheon", new BigDecimal("10.99"), LocalTime.of(8, 0), LocalTime.of(17, 30)),
	CATTEDRALEDISANPIETRO("Cattedrale di S. Pietro", new BigDecimal("89.99"), LocalTime.of(10, 0), LocalTime.of(19, 0)),
	VITTORIANO("Monumento a Vittorio Emanuele II", new BigDecimal("41.23"), LocalTime.of(9, 30), LocalTime.of(19, 30));

	// Attributi: ogni istanza dell'enum ha questi campi
	private String 			name;              // Nome leggibile dell'attrazione
	private BigDecimal 		ticketPrice;   // Prezzo del biglietto (BigDecimal per precisione monetaria)
	private LocalTime 		openingTime;    // Orario apertura (LocalTime per gestione orari senza data)
	private LocalTime 		closingTime;    // Orario chiusura

	// Costruttore: chiamato automaticamente per ogni costante enum dichiarata sopra
	// È SEMPRE privato negli enum (non può essere pubblico)
	Attraction(String name, BigDecimal ticketPrice, LocalTime openingTime, LocalTime closingTime) {
		this.name = name;
		this.ticketPrice = ticketPrice;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	// Getter: metodi per accedere agli attributi privati
	public String getName() { return name; }
	public BigDecimal getTicketPrice() { return ticketPrice; }
	public LocalTime getOpeningTime() { return openingTime; }
	public LocalTime getClosingTime() { return closingTime; }

	/**
	 * Valida che tutti i campi dell'attrazione siano corretti
	 * @return true se l'attrazione ha dati validi, false altrimenti
	 */
	public boolean 	isValid() {
		// Controllo nome: non null e non vuoto (trim() rimuove spazi)
		return name != null && !name.trim().isEmpty() &&
			   // Controllo prezzo: non null e >= 0 (compareTo restituisce -1, 0 o 1)
			   ticketPrice != null && ticketPrice.compareTo(BigDecimal.ZERO) >= 0 &&
			   // Controllo orari: entrambi non null
			   openingTime != null && closingTime != null;
	}

	/**
	 * 
	 * uso: setScale() -->  è un metodo di BigDecimal che controlla quante cifre decimali vuoi nel numero.
	 * 
	 * 
	 * Restituisce il prezzo formattato con 2 decimali
	 * Esempio: 5.5 diventa "5.50"
	 * @return prezzo come stringa con esattamente 2 decimali
	 */
	public String 	getFormattedPrice() {
		// setScale(2, ...) = imposta 2 cifre decimali
		// RoundingMode.HALF_UP = arrotonda per eccesso se necessario (es: 5.555 -> 5.56)
		return ticketPrice.setScale(2, RoundingMode.HALF_UP).toString();
	}
}