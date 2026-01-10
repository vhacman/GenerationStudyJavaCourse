package com.generation.lmdb.demo;

import com.generation.library.Console;
import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Producer;
import com.generation.lmdb.model.repository.ProducerRepository;

/*
 * CONCETTI TEORICI - CONVERSIONE STRINGHE E BOOLEAN
 *
 * Questa demo inserisce un Producer (produttore) nel database.
 * È simile a InsertProductDemo, ma gestisce anche un campo boolean (active).
 */

public class InsertProducerDemo
{
	public static void main(String[] args)
	{
		ProducerRepository 	producerRepo 	= (ProducerRepository) Context.getDependency(ProducerRepository.class);
		Producer 			producer		= new Producer();

		Console.print("Inserire nome legale: ");
		producer.setLegalName(Console.readString());
		Console.print("Inserire indirizzo: ");
		producer.setAddress(Console.readString());
		Console.print("Inserire storia: ");
		producer.setHistory(Console.readString());
		Console.print("È attivo? (S/N): ");
		producer.setActive(Console.readString().equals("S"));

		producerRepo.insert(producer);

		Console.print("Producer inserito con successo!");
	}
}