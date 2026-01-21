package com.generation.cp.cypher;

/**
 * Implementazione dell'algoritmo di cifratura XOR.
 *
 * XOR (exclusive OR) è un algoritmo di cifratura simmetrico che
 * utilizza la stessa chiave per cifrare e decifrare.
 */
public class XORCypher implements Cypher
{

	private String  key;


	/**
	 * Costruisce un cifratore XOR con la chiave specificata.
	 *
	 * @param key Chiave segreta per cifratura/decifratura
	 */
	public XORCypher(String key)
	{
		this.key = key;
	}


	/**
	 * Cifra il testo in chiaro utilizzando l'algoritmo XOR con la chiave impostata.
	 *
	 * @param plainText Testo da cifrare
	 * @return Testo cifrato, null se l'input è null
	 */
	@Override
	public String cypher(String plainText)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Strategy Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Interfaccia (Cypher) → Implementazione algoritmo (XORCypher)
		 *
		 * Questo metodo implementa il pattern Strategy dove:
		 * - L'interfaccia Cypher definisce il contratto per la cifratura
		 * - XORCypher fornisce un algoritmo specifico (XOR)
		 * - Il client può sostituire XORCypher con altre implementazioni (AES, RSA, ecc.)
		 *
		 * L'algoritmo XOR è simmetrico: la stessa operazione cifra e decifra.
		 * La chiave viene ripetuta ciclicamente se il testo è più lungo.
		 *
		 * Principi OOP applicati:
		 * → Astrazione: Il client usa Cypher senza conoscere l'algoritmo
		 * → Polimorfismo: Algoritmi diversi implementano la stessa interfaccia
		 * → Incapsulamento: La chiave è privata e protetta
		 * → Open/Closed: Nuovi algoritmi possono essere aggiunti senza modificare il codice esistente
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		if (plainText == null)
			return null;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < plainText.length(); i++)
		{
			// Eseguiamo lo XOR tra il carattere del testo e quello della chiave
			// Usiamo l'operatore modulo (%) per far ricominciare la chiave se il testo è più lungo
			char c = (char) (plainText.charAt(i) ^ key.charAt(i % key.length()));
			sb.append(c);
		}

		return sb.toString();
	}


	/**
	 * Decifra il testo cifrato utilizzando la stessa chiave usata per cifrare.
	 *
	 * @param crypted Testo cifrato
	 * @return Testo in chiaro originale
	 */
	@Override
	public String decypher(String crypted)
	{
		return cypher(crypted);
	}

}
