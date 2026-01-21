package com.generation.sbbb.repository;

import com.generation.library.database.ConnectionFactory;

/**
 * Factory per la creazione di repository di camere.
 * Implementa il pattern Singleton per garantire una singola istanza del repository.
 */
public class RoomRepositoryFactory
{

	static RoomRepository	dbRepo = new SQLRoomRepository("room", ConnectionFactory.make());

	/**
	 * Restituisce l'istanza del repository delle camere.
	 * @return il repository configurato
	 */
	public static RoomRepository make()
	{
		/*
		 ******************************************
		 * Restituisce sempre la stessa istanza
		 * del repository configurata con cache completa
		 ******************************************
		 */
		return dbRepo;
	}

}
