package com.generation.pcw.model.entities;

/**
 * Enumerazione che rappresenta i diversi tipi di storage disponibili nel sistema.
 * Ogni storage è caratterizzato da quattro proprietà principali:
 * - SIZE: capacità di archiviazione espressa in gigabyte
 * - BENCHMARK: indica le prestazioni dello storage attraverso un punteggio numerico
 * - BRAND: identifica il produttore dello storage
 * - PRICE: rappresenta il costo dello storage
 *
 * Gli storage disponibili sono:
 * - SSDBASIC: SSD base da 256GB, marca Scrausus
 * - SSDLARGE: SSD grande da 1024GB (1TB), marca Scrausus
 * - SSDSCAMOSCIUS: SSD di fascia alta da 2048GB (2TB), marca Tesla
 */
public enum Storage
{
	SSDBASIC(256, 100, "Scrausus", 20),
	SSDLARGE(1024, 100, "Scrausus", 30),
	SSDSCAMOSCIUS(2048, 300, "Tesla", 200);

	private final int 		SIZE;
	private final int 		BENCHMARK;
	private final String 	BRAND;
	private final int 		PRICE;


	/**
	 * Costruttore privato dell'enum.
	 * Inizializza i valori finali per ciascun tipo di storage.
	 *
	 * @param s     Dimensione dello storage in GB
	 * @param b     Valore del benchmark
	 * @param bra   Marca del produttore
	 * @param price Prezzo dello storage
	 */
	private Storage(int s, int b, String bra, int price)
	{
		SIZE = s;
		BENCHMARK = b;
		BRAND = bra;
		PRICE = price;
	}

	/**
	 * Restituisce la dimensione dello storage in GB.
	 *
	 * @return La capacità dello storage
	 */
	public int getSize() {
		return SIZE;
	}

	/**
	 * Restituisce il valore del benchmark dello storage.
	 *
	 * @return Il punteggio benchmark
	 */
	public int getBenchmark() {
		return BENCHMARK;
	}

	/**
	 * Restituisce la marca dello storage.
	 *
	 * @return Il produttore dello storage
	 */
	public String getBrand() {
		return BRAND;
	}

	/**
	 * Restituisce il prezzo dello storage.
	 *
	 * @return Il prezzo dello storage
	 */
	public int getPrice() {
		return PRICE;
	}
}