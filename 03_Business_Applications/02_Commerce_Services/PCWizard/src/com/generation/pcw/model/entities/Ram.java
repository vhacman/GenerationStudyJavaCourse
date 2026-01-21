package com.generation.pcw.model.entities;

/**
 * Enumerazione che rappresenta i diversi tipi di RAM disponibili nel sistema.
 * Ogni RAM è caratterizzata da quattro proprietà principali:
 * - SIZE: capacità della RAM espressa in GB
 * - SPEED: velocità della RAM espressa in MHz
 * - BRAND: identifica il produttore della RAM
 * - PRICE: rappresenta il costo della RAM
 *
 * Le RAM disponibili sono:
 * - RAM8GB: RAM da 8GB, velocità base
 * - RAM16GB: RAM da 16GB, velocità media
 * - RAM32GB: RAM da 32GB, velocità alta
 * - RAM64GB: RAM da 64GB, velocità molto alta
 */
public enum Ram
{
	RAM8GB(8, 2400, "Corsair", 40),
	RAM16GB(16, 3200, "Kingston", 80),
	RAM32GB(32, 3600, "G.Skill", 150),
	RAM64GB(64, 4800, "Corsair", 300);

	private final int 		SIZE;
	private final int 		SPEED;
	private final String 	BRAND;
	private final int 		PRICE;


	/**
	 * Costruttore privato dell'enum.
	 * Inizializza i valori finali per ciascun tipo di RAM.
	 *
	 * @param size  Dimensione della RAM in GB
	 * @param speed Velocità della RAM in MHz
	 * @param brand Marca del produttore
	 * @param price Prezzo della RAM
	 */
	private Ram(int size, int speed, String brand, int price)
	{
		this.SIZE = size;
		this.SPEED = speed;
		this.BRAND = brand;
		this.PRICE = price;
	}

	/**
	 * Restituisce la dimensione della RAM in GB.
	 *
	 * @return La capacità della RAM
	 */
	public int getSize() {
		return SIZE;
	}

	/**
	 * Restituisce la velocità della RAM in MHz.
	 *
	 * @return La velocità della RAM
	 */
	public int getSpeed() {
		return SPEED;
	}

	/**
	 * Restituisce la marca della RAM.
	 *
	 * @return Il produttore della RAM
	 */
	public String getBrand() {
		return BRAND;
	}

	/**
	 * Restituisce il prezzo della RAM.
	 *
	 * @return Il prezzo della RAM
	 */
	public int getPrice() {
		return PRICE;
	}

}