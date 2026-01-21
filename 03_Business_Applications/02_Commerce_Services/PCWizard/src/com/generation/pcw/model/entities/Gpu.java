package com.generation.pcw.model.entities;

/**
 * Enumerazione che rappresenta i diversi tipi di GPU disponibili nel sistema.
 * Ogni GPU è caratterizzata da tre proprietà principali:
 * - BENCHMARK: indica le prestazioni della GPU attraverso un punteggio numerico
 * - BRAND: identifica il produttore della GPU (AMD o NVIDIA)
 * - PRICE: rappresenta il costo della GPU
 *
 * Le GPU disponibili sono:
 * - GPU6750: GPU AMD di fascia base
 * - GPU4060: GPU NVIDIA di fascia base
 * - GPU4070: GPU NVIDIA di fascia media
 * - GPUS4070S: GPU NVIDIA Super di fascia alta
 */
public enum Gpu
{
	GPU6750(100, "AMD", 100),
	GPU4060(100, "NVIDIA", 100),
	GPU4070(200, "NVIDIA", 200),
	GPUS4070S(300, "NVIDIA", 300);


	final int		BENCHMARK;
	final String	BRAND;
	final int		PRICE;


	/**
	 * Costruttore privato dell'enum.
	 * Inizializza i valori finali per ciascuna GPU.
	 *
	 * @param benchmark Valore del benchmark
	 * @param brand     Marca del produttore
	 * @param price     Prezzo della GPU
	 */
	private Gpu(int benchmark, String brand, int price) {
		this.BENCHMARK = benchmark;
		this.BRAND = brand;
		this.PRICE = price;
	}


	/**
	 * Restituisce il valore del benchmark della GPU.
	 *
	 * @return Il punteggio benchmark
	 */
	public int getBenchmark() {
		return BENCHMARK;
	}


	/**
	 * Restituisce la marca della GPU.
	 *
	 * @return Il produttore della GPU
	 */
	public String getBrand() {
		return BRAND;
	}


	/**
	 * Restituisce il prezzo della GPU.
	 *
	 * @return Il prezzo della GPU
	 */
	public int getPrice() {
		return PRICE;
	}

}