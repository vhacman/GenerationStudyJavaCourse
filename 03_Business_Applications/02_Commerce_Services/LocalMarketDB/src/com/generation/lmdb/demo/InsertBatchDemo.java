package com.generation.lmdb.demo;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Batch;
import com.generation.lmdb.model.entities.Producer;
import com.generation.lmdb.model.entities.Product;
import com.generation.lmdb.model.repository.BatchRepository;
import com.generation.lmdb.model.repository.ProducerRepository;
import com.generation.lmdb.model.repository.ProductRepository;

/*
 * CONCETTI TEORICI - GESTIONE RELAZIONI E VALIDAZIONE
 *
 * Questa demo è PIÙ COMPLESSA perché Batch ha RELAZIONI con Producer e Product.
 * Dobbiamo:
 * 1. Caricare il Producer esistente dal database (tramite ID)
 * 2. Caricare il Product esistente dal database (tramite ID)
 * 3. Assegnarli al Batch
 * 4. Salvare il Batch
 *
 * IMPORTANTE: Producer e Product DEVONO GIÀ ESISTERE nel database!
 * Se non esistono, il programma mostra un errore e termina.
 */

public class InsertBatchDemo
{
	public static void main(String[] args)
	{
		/*
		 * INIZIALIZZAZIONE DI TRE REPOSITORY
		 *
		 * Abbiamo bisogno di 3 repository perché lavoriamo con 3 entità:
		 * - batchRepo → per salvare il Batch
		 * - productRepo → per cercare il Product esistente
		 * - producerRepo → per cercare il Producer esistente
		 */
		BatchRepository 	batchRepo 		= (BatchRepository) 	Context.getDependency(BatchRepository.class);
		ProductRepository 	productRepo 	= (ProductRepository) 	Context.getDependency(ProductRepository.class);
		ProducerRepository 	producerRepo	= (ProducerRepository) 	Context.getDependency(ProducerRepository.class);
		Batch 				batch 			= new Batch();
		Console.print("Inserire data (formato YYYY-MM-DD): ");
		String 		dateString 		= Console.readString();
		batch.setDate(LocalDate.parse(dateString));
		Console.print("Inserire ID Producer: ");
		int 		producerId 		= Console.readInt();
		Producer 	producer	 	= producerRepo.findById(producerId);
		if(producer == null)
		{
			Console.print("ERRORE: Producer non trovato!");
			return;
		}
		batch.setProducer(producer);
		Console.print("Inserire ID Product: ");
		int 		productId 	= Console.readInt();
		Product 	product 	= productRepo.findById(productId);
		if(product == null)
		{
			Console.print("ERRORE: Product non trovato!");
			return;
		}
		batch.setProduct(product);
		Console.print("Inserire quantità: ");
		batch.setQuantity(Console.readInt());
		Console.print("Inserire prezzo unitario: ");
		batch.setUnitPrice(Console.readInt());
		Console.print("Inserire note: ");
		batch.setNotes(Console.readString());
		Console.print("Inserire status: ");
		batch.setStatus(Console.readString());
		batchRepo.insert(batch);
		Console.print("Batch inserito con successo!");
	}
}
