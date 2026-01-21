package com.generation.cp.cypher;

/**
 * Contratto per algoritmi di cifratura e decifratura.
 *
 * Questa interfaccia definisce il pattern Strategy per algoritmi
 * di crittografia intercambiabili (XOR, AES, RSA, etc.).
 */
public interface Cypher
{

	/**
	 * Cifra il testo in chiaro.
	 *
	 * @param plainText Testo da cifrare
	 * @return Testo cifrato
	 */
	String cypher(String plainText);


	/**
	 * Decifra il testo criptato.
	 *
	 * @param crypted Testo cifrato
	 * @return Testo in chiaro originale
	 */
	String decypher(String crypted);

}
