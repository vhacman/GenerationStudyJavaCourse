package com.generation.sbbb.repository;

import com.generation.library.database.ConnectionFactory;

/**
 * Factory per la creazione di repository di ospiti.
 * Implementa il pattern Singleton per garantire una singola istanza del repository.
 */
public class GuestRepositoryFactory
{

	static GuestRepository	sqlRepo = new SQLGuestRepository("guest", ConnectionFactory.make(), 500);

	/**
	 * Restituisce l'istanza del repository degli ospiti.
	 * @return il repository configurato
	 */
	public static GuestRepository make()
	{
		/*
		 ******************************************
		 * Restituisce sempre la stessa istanza
		 * del repository configurata con cache
		 * parziale di dimensione 500
		 ******************************************
		 */
		return sqlRepo;
	}


}
