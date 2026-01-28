package com.generation.cp.context;

import java.sql.Connection;

import com.generation.cp.cypher.Cypher;
import com.generation.library.Console;

/**
 * Classe di test per verificare il funzionamento del contenitore IoC Context.
 */
public class ContextTest
{

	/**
	 * Metodo main per testare il recupero delle dipendenze e la cifratura.
	 *
	 * @param args Argomenti da linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Dependency Injection in azione
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Context (IoC Container) → Client riceve dipendenze → Usa servizi
		 *
		 * Questo test dimostra il pattern Dependency Injection in azione:
		 *
		 * 1. ASTRAZIONE: Richiesta per Cypher, non per XORCypher specifico
		 *    → Connection c = Context.getDependency(Connection.class);
		 *    → Cypher cypher = Context.getDependency(Cypher.class);
		 *
		 * 2. POLIMORFISMO: Context restituisce XORCypher ma client usa interfaccia
		 *    → Il client non sa quale implementazione riceve
		 *    → Può essere XORCypher, AESCypher, RSACypher...
		 *
		 * 3. LOOSE COUPLING: Il client non fa "new XORCypher()"
		 *    → Non dipende dall'implementazione concreta
		 *    → Cambiare algoritmo richiede solo modifica in Context
		 *
		 * Vantaggi per il testing:
		 * → Mock objects possono essere inseriti in Context per testing
		 * → Il client non deve essere modificato per usare mock
		 *
		 * Principi applicati:
		 * → Inversion of Control: Context controlla le dipendenze
		 * → Dependency Inversion Principle: Client dipende da astrazioni
		 * → Open/Closed: Aperto a nuove implementazioni, chiuso a modifiche
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */

		// Esempio commentato di dipendenza non esistente
		// DaBere dabere = Context.getDependency(DaBere.class);

		// Recupera la connessione al database dal Context
		Connection c = Context.getDependency(Connection.class);

		// Dammi un oggetto che rispetti il contratto Cypher
		Cypher cypher = Context.getDependency(Cypher.class);
		// Mi restituisce un oggetto di una classe che implementa l'interfaccia Cypher
		// Sarà il context a decidere quale tipo di oggetto restituirmi

		String cifrato   = cypher.cypher("Ciao mamma guarda come mi diverto");
		String decifrato = cypher.decypher(cifrato);

		Console.print(decifrato);
	}

}
