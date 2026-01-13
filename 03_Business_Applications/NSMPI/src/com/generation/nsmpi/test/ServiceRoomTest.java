package com.generation.nsmpi.test;

import org.junit.jupiter.api.Test;

import com.generation.library.Console;
import com.generation.nsmpi.model.entities.ServiceRoom;

/**
 * Test suite per la classe ServiceRoom.
 * Verifica i vincoli di integrità e la logica di validazione dell'entità ambulatorio.
 * Applica metodologia TDD (Test-Driven Development) per garantire correttezza funzionale.
 */
class ServiceRoomTest
{

	@Test
	void testGetErrors()
	{
		ServiceRoom room = new ServiceRoom();

		Console.print(room.getErrors());
		assert(room.getErrors().size() == 1);
		assert(!room.isValid());

		room.setDescription("Ambulatorio oculistico");
		Console.print(room.getErrors());
		assert(room.getErrors().size() == 0);
		assert(room.isValid());
	}

}
