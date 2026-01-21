package com.generation.pcw.model.entities;

/**
 * Enumerazione che rappresenta i diversi tipi di processori disponibili nel sistema.
 * NON OTTIMALE
 * Ogni processore è caratterizzato da tre proprietà principali:
 * - BENCHMARK: indica le prestazioni del processore attraverso un punteggio numerico
 * - PRICE: rappresenta il costo del processore
 * - PLATFORM: identifica l'architettura (ARM o INTEL)
 *
 * I processori disponibili sono:
 * - M1: processore Apple di prima generazione, fascia entry-level
 * - M4: processore Apple di quarta generazione, fascia alta
 * - I714: processore Intel i7 di 14a generazione, prestazioni elevate
 * - I515: processore Intel i5 di 15a generazione, fascia media
 */
public enum Processor
{
	M1(1000, 100, "ARM"),
	M4(5000, 600, "ARM"),
	I714(6000, 600, "INTEL"),
	I515(5000, 400, "INTEL");

	private final int		BENCHMARK;
	private final int		PRICE;
	private final String 	PLATFORM;


	/**
	 * Costruttore privato dell'enum.
	 * Inizializza i valori finali per ciascun processore.
	 *
	 * @param b   Valore del benchmark
	 * @param p   Prezzo del processore
	 * @param pla Piattaforma del processore
	 */
	private Processor(int b, int p, String pla) {
		this.BENCHMARK = b;
		this.PRICE = p;
		this.PLATFORM = pla;
	}

	/**
	 * Restituisce il valore del benchmark del processore.
	 *
	 * @return Il punteggio benchmark
	 */
	public int getBenchmark() {
		return BENCHMARK;
	}

	/**
	 * Restituisce il prezzo del processore.
	 *
	 * @return Il prezzo del processore
	 */
	public int getPrice() {
		return PRICE;
	}

	/**
	 * Restituisce la piattaforma del processore.
	 *
	 * @return La piattaforma (ARM o INTEL)
	 */
	public String getPlatform() {
		return PLATFORM;
	}
}