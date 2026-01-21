package com.generation.pc.model.repository;

import com.generation.library.database.ConnectionFactory;

/*
 * ==================================================================================
 * TEORIA: Factory Pattern
 * ==================================================================================
 * DEFINIZIONE:
 * Il Factory pattern è un design pattern creazionale che fornisce un'interfaccia
 * per creare oggetti, nascondendo la logica di istanziazione al client.
 *
 * SCOPO:
 * - Centralizzare la creazione di oggetti complessi
 * - Nascondere i dettagli di costruzione (quale implementazione, quali parametri)
 * - Facilitare il cambio di implementazione senza modificare il codice client
 *
 * PERCHÉ LO FACCIAMO:
 * Invece di fare nel Main:
 *   PatientRepository repo = new SQLPatientRepository(ConnectionFactory.make());
 * facciamo:
 *   PatientRepository repo = PatientRepositoryFactory.make();
 *
 * VANTAGGI:
 * 1. Main non sa che stiamo usando SQL (potrebbe essere MongoDB, in-memory, etc.)
 * 2. Se cambiamo implementazione, modifichiamo solo la factory
 * 3. La logica di configurazione (cache size, connection) è centralizzata
 * 4. Rispettiamo il principio "Dependency Inversion": Main dipende dall'interfaccia
 *
 * ==================================================================================
 * TEORIA: Singleton Pattern (variante lazy)
 * ==================================================================================
 * DEFINIZIONE:
 * Il Singleton è un pattern che garantisce che esista una sola istanza di una classe
 * in tutta l'applicazione.
 *
 * IMPLEMENTAZIONE:
 * - Campo static: condiviso tra tutte le istanze della classe
 * - Inizializzazione al momento della dichiarazione (eager initialization)
 * - Metodo static make() restituisce sempre la stessa istanza
 *
 * SCOPO:
 * Condividere la stessa istanza del repository (e quindi la stessa connessione
 * e cache) in tutto il programma.
 *
 * PERCHÉ LO FACCIAMO:
 * Se creassimo un nuovo repository ogni volta:
 * - Sprecheremmo connessioni al database
 * - La cache non sarebbe condivisa
 * - Performance peggiori
 *
 * Con Singleton:
 * - Una sola connessione
 * - Cache condivisa tra tutte le chiamate
 * - Migliori performance
 *
 * ==================================================================================
 * TEORIA: Modificatore "static"
 * ==================================================================================
 * DEFINIZIONE:
 * - Campo static: appartiene alla CLASSE, non alle istanze
 * - Metodo static: può essere chiamato senza creare un'istanza (ClassName.method())
 *
 * DIFFERENZA:
 * - Campo normale: ogni oggetto ha la sua copia
 * - Campo static: esiste una sola copia condivisa tra tutti gli oggetti
 *
 * ESEMPIO:
 * class Counter {
 *     static int sharedCount = 0;  // Condiviso
 *     int instanceCount = 0;        // Per istanza
 * }
 * Counter c1 = new Counter();
 * Counter c2 = new Counter();
 * c1.sharedCount++;    // Entrambi vedono 1
 * c1.instanceCount++;  // Solo c1 vede 1, c2 vede 0
 */
/**
 * Factory per la creazione di repository di pazienti.
 * Implementa il pattern Singleton per garantire una singola istanza del repository.
 */
public class PatientRepositoryFactory
{

	/*
	 * ==================================================================================
	 * Campo static che contiene l'UNICA istanza del repository.
	 * Viene inizializzato quando la classe viene caricata (eager initialization).
	 *
	 * ConnectionFactory.make() crea la connessione al database.
	 * SQLPatientRepository è l'implementazione concreta che usa SQL + cache parziale.
	 * ==================================================================================
	 */
	static PatientRepository sqlRepo = new SQLPatientRepository(ConnectionFactory.make());

	/**
	 * Restituisce l'istanza del repository dei pazienti.
	 * @return il repository configurato
	 */
	public static PatientRepository make()
	{
		/*
		 ******************************************
		 * Restituisce sempre la STESSA istanza (Singleton).
		 *
		 * Ogni chiamata a PatientRepositoryFactory.make()
		 * restituisce lo stesso oggetto repository con:
		 * - Stessa connessione al database
		 * - Stessa cache (ultimi 50 pazienti)
		 *
		 * Questo garantisce:
		 * 1. Efficienza: non sprechiamo connessioni
		 * 2. Coerenza: la cache è condivisa
		 * 3. Semplicità: il client non gestisce la connessione
		 ******************************************
		 */
		return sqlRepo;
	}

}
