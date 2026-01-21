package com.generation.lmdb.controller;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Batch;
import com.generation.lmdb.model.entities.BatchStatus;
import com.generation.lmdb.model.entities.Producer;
import com.generation.lmdb.model.entities.Product;
import com.generation.lmdb.model.repository.BatchRepository;
import com.generation.lmdb.model.repository.ProducerRepository;
import com.generation.lmdb.model.repository.ProductRepository;

/**
 * BatchController gestisce tutte le operazioni relative ai Batch (lotti di acquisto).
 *
 * OPERAZIONI PRINCIPALI:
 * - addBatch(): registra un nuovo lotto acquistato
 * - addBatchCustomPrice(): come addBatch() ma con prezzo personalizzato
 * - approveBatch(): approva un lotto (PENDING → VALIDATED)
 * - revokeBatch(): revoca un lotto (qualsiasi stato → CORRUPT)
 *
 */
public class BatchController
{
	private static BatchRepository 		batchRepo 		= (BatchRepository) Context.getDependency(BatchRepository.class);
	private static ProductRepository 	productRepo 	= (ProductRepository) Context.getDependency(ProductRepository.class);
	private static ProducerRepository 	producerRepo 	= (ProducerRepository) Context.getDependency(ProducerRepository.class);

	/**
	 * AGGIUNGI BATCH
	 *
	 * Registra un nuovo lotto di acquisto.
	 * Il prezzo viene preso dal Product (prezzo di mercato attuale).
	 *
	 * FLUSSO:
	 * 1. Chiedi data di acquisto
	 * 2. Chiedi ID Producer e cercalo nel database
	 * 3. Chiedi ID Product e cercalo nel database
	 * 4. Chiedi quantità
	 * 5. Usa il prezzo unitario dal Product
	 * 6. Chiedi note
	 * 7. Imposta status = PENDING (da verificare)
	 * 8. Salva il Batch
	 */
	public static void addBatch()
	{
		Batch 	batch = new Batch();
		Console.print("Inserisci data di acquisto (YYYY-MM-DD): ");
		String 	dateString = Console.readString();
		try
		{
			batch.setDate(LocalDate.parse(dateString));
		}
		catch (Exception e)
		{
			Console.print("\nERRORE: Formato data non valido! Usa YYYY-MM-DD (es: 2024-01-10)");
			return;
		}
		Console.print("Inserisci ID del produttore: ");
		int 		producerId = Console.readInt();
		Producer 	producer = producerRepo.findById(producerId);
		if (producer == null)
		{
			Console.print("\nERRORE: Produttore con ID " + producerId + " non trovato!");
			return;
		}
		if (!producer.isActive())
		{
			Console.print("\nERRORE: Il produttore è BANNATO! Non puoi acquistare da lui.");
			Console.print("Motivo: " + producer.getHistory());
			return;
		}
		batch.setProducer(producer);
		Console.print("Inserisci ID del prodotto: ");
		int 	productId = Console.readInt();
		Product product = productRepo.findById(productId);
		if (product == null)
		{
			Console.print("\nERRORE: Prodotto con ID " + productId + " non trovato!");
			return;
		}
		batch.setProduct(product);
		Console.print("Inserisci quantità: ");
		int quantity = Console.readInt();
		if (quantity <= 0)
		{
			Console.print("\nERRORE: La quantità deve essere maggiore di 0!");
			return;
		}
		batch.setQuantity(quantity);
		batch.setUnitPrice(product.getUnitPrice());
		Console.print("\nPrezzo unitario dal catalogo: " + product.getUnitPrice() + " centesimi");
		Console.print("Prezzo totale batch: " + batch.getPrice() + " centesimi");
		Console.print("\nInserisci note (opzionale): ");
		String notes = Console.readString();
		batch.setNotes(notes.isBlank() ? "Nessuna nota" : notes);
		batch.setStatus(BatchStatus.PENDING);	
		if (!batch.isValid())
		{
			Console.print("\nERRORE: Batch non valido!");
			return;
		}
		batchRepo.insert(batch);
		Console.print("\n Batch aggiunto con successo!");
		Console.print("Status: PENDING (in attesa di verifica)");
	}

	/**
	 * MODIFICA PREZZO BATCH
	 *
	 * Questo metodo permette di modificare il prezzo unitario di un batch esistente.
	 *
	 * FLUSSO CORRETTO:
	 * 1. Chiedi ID del batch da modificare
	 * 2. CERCA il batch nel database con findById()
	 * 3. Se trovato → mostra dettagli e permetti modifica
	 * 4. Se non trovato → errore
	 *
	 * ERRORE PRECEDENTE:
	 * - Creava un nuovo Batch invece di cercare quello esistente
	 * - Settava l'ID manualmente (SBAGLIATO!)
	 */
	public static void changeBatchCustomPrice()
    {
		Console.print("Inserire ID batch da modificare: ");
		int batchId = Console.readInt();
		Batch batch = batchRepo.findById(batchId);
		if(batch == null)
		{
			Console.print("\nERRORE: Batch con ID " + batchId + " non trovato!");
			return;
		}
		Console.print("\n>>> BATCH TROVATO <<<");
		printBatchDetails(batch);
		Console.print("\n>>> PRODUTTORE <<<");
		ProducerController.printProducerDetails(batch.getProducer());
		if(!batch.getProducer().isActive())
		{
			Console.print("\nATTENZIONE: Il produttore è BANNATO!");
			Console.print("Non dovresti modificare questo batch.");
			Console.print("Vuoi continuare comunque? (S/N): ");
			String confirm = Console.readString();
			if(!confirm.equalsIgnoreCase("S"))
			{
				Console.print("Operazione annullata.");
				return;
			}
		}
		Console.print("\n>>> PRODOTTO <<<");
		ProductController.printProductDetails(batch.getProduct());
		Console.print("\nPrezzo unitario attuale: " + batch.getUnitPrice() + " centesimi");
		Console.print("Inserire nuovo prezzo unitario (in centesimi): ");
		int newPrice = Console.readInt();
		if(newPrice <= 0)
		{
			Console.print("\nERRORE: Il prezzo deve essere maggiore di 0!");
			return;
		}
		batch.setUnitPrice(newPrice);
		Console.print("\nQuantità attuale: " + batch.getQuantity());
		Console.print("Vuoi modificare anche la quantità? (S/N): ");
		if(Console.readString().equalsIgnoreCase("S"))
		{
			Console.print("Inserire nuova quantità: ");
			int newQuantity = Console.readInt();

			if(newQuantity <= 0)
			{
				Console.print("\nERRORE: La quantità deve essere maggiore di 0!");
				return;
			}

			batch.setQuantity(newQuantity);
		}
		Console.print("\nNote attuali: " + batch.getNotes());
		Console.print("Inserire motivo della modifica (aggiunto alle note): ");
		String reason = Console.readString();
		String newNotes = batch.getNotes() + "\n[MODIFICATO] " + reason;
		batch.setNotes(newNotes);
		if(!batch.isValid())
		{
			Console.print("\nERRORE: Batch non valido dopo le modifiche!");
			return;
		}
		batchRepo.update(batch);
		Console.print("\n Batch aggiornato con successo!");
		Console.print("Nuovo prezzo totale: " + batch.getPrice() + " centesimi");
    }

	/**
	 * APPROVA BATCH
	 *
	 * Cambia lo stato di un Batch da PENDING a VALIDATED.
	 *
	 * QUANDO USARLO:
	 * - Hai verificato la qualità del lotto
	 * - La merce è conforme alle aspettative
	 * - Puoi metterla in vendita
	 *
	 * TRANSIZIONE DI STATO:
	 * PENDING → VALIDATED
	 */
	public static void approveBatch()
	{
		Console.print("Inserisci ID del batch da approvare: ");
		int batchId = Console.readInt();
		Batch batch = batchRepo.findById(batchId);
		if (batch == null)
		{
			Console.print("\nERRORE: Batch con ID " + batchId + " non trovato!");
			return;
		}
		Console.print("\nBatch trovato:");
		Console.print("Prodotto: " + batch.getProduct().getName());
		Console.print("Produttore: " + batch.getProducer().getLegalName());
		Console.print("Quantità: " + batch.getQuantity());
		Console.print("Status attuale: " + batch.getStatus());
		if (batch.getStatus() == BatchStatus.VALIDATED)
		{
			Console.print("\nATTENZIONE: Questo batch è già approvato!");
			return;
		}
		batch.setStatus(BatchStatus.VALIDATED);
		batchRepo.update(batch);
		Console.print("\n✓ Batch approvato con successo!");
		Console.print("La merce può essere messa in vendita.");
	}

	/**
	 * REVOCA BATCH
	 *
	 * Cambia lo stato di un Batch a CORRUPT (corrotto/non conforme).
	 *
	 * TRANSIZIONE DI STATO:
	 * PENDING/VALIDATED → CORRUPT
	 */
	public static void revokeBatch()
	{
		Console.print("Inserisci ID del batch da revocare: ");
		int batchId = Console.readInt();
		Batch batch = batchRepo.findById(batchId);
		if (batch == null)
		{
			Console.print("\nERRORE: Batch con ID " + batchId + " non trovato!");
			return;
		}
		Console.print("\nBatch trovato:");
		Console.print("Prodotto: " + batch.getProduct().getName());
		Console.print("Produttore: " + batch.getProducer().getLegalName());
		Console.print("Status attuale: " + batch.getStatus());
		if (batch.getStatus() == BatchStatus.CORRUPT)
		{
			Console.print("\nATTENZIONE: Questo batch è già revocato!");
			return;
		}
		Console.print("\nInserisci motivo della revoca: ");
		String reason = Console.readString();
		String newNotes = batch.getNotes() + "\n[REVOCATO] " + reason;
		batch.setNotes(newNotes);
		batch.setStatus(BatchStatus.CORRUPT);
		batchRepo.update(batch);
		Console.print("\n✓ Batch revocato con successo!");
		Console.print("La merce non può essere venduta.");
	}
	
	public static void printBatchDetails(Batch b)
    {
        Console.print("Batch con id "      + b.getId());
        Console.print("Nome prodotto "     + b.getProduct().getName());
        Console.print("Fornitore "         + b.getProducer().getLegalName());
        Console.print("Quantità "          + b.getQuantity());
        Console.print("Prezzo unitario "   + b.getUnitPrice());
        Console.print("Prezzo attuale "    + b.getProduct().getUnitPrice());
        Console.print("Note "              + b.getNotes());
        Console.print("Costo totale "      + b.getPrice());
        Console.print("Stato attuale "     + b.getStatus());
    }
	
}
