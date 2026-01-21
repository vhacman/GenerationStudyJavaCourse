package com.generation.pc.model.repository;

import com.generation.library.database.ConnectionFactory;

/*
 * ==================================================================================
 * TEORIA: Factory Pattern per HealthService Repository
 * ==================================================================================
 * Questa factory segue gli stessi principi di PatientRepositoryFactory:
 * - Factory Pattern: nasconde i dettagli di creazione dell'implementazione
 * - Singleton Pattern: garantisce una sola istanza del repository
 * - Static method: make() può essere chiamato senza istanziare la factory
 *
 * SCOPO:
 * Centralizzare la creazione del repository dei servizi sanitari,
 * permettendo al Main di usare:
 *   HealthServiceRepository repo = HealthServiceRepositoryFactory.make();
 * invece di:
 *   HealthServiceRepository repo = new SQLHealthServiceRepository(ConnectionFactory.make());
 *
 * VANTAGGI:
 * - Il Main non conosce l'implementazione concreta (SQL)
 * - Facile cambiare implementazione (da SQL a NoSQL)
 * - Una sola istanza condivisa = una connessione + una cache condivisa
 */
/**
 * Factory per la creazione di repository di servizi sanitari.
 * Implementa il pattern Singleton per garantire una singola istanza del repository.
 */
public class HealthServiceRepositoryFactory
{

	/*
	 * Singleton: istanza unica del repository, inizializzata staticamente.
	 * Tutti i chiamanti riceveranno la stessa istanza con:
	 * - Stessa connessione al database
	 * - Stessa cache parziale (dimensione 50)
	 */
	static HealthServiceRepository sqlRepo = new SQLHealthServiceRepository(ConnectionFactory.make());

	/**
	 * Restituisce l'istanza del repository dei servizi sanitari.
	 * @return il repository configurato
	 */
	public static HealthServiceRepository make()
	{
		/*
		 ******************************************
		 * Factory method che restituisce sempre
		 * la stessa istanza Singleton del repository.
		 *
		 * Garantisce:
		 * - Riutilizzo della connessione
		 * - Cache condivisa per migliori performance
		 * - Semplicità per il client (Main)
		 ******************************************
		 */
		return sqlRepo;
	}

}
