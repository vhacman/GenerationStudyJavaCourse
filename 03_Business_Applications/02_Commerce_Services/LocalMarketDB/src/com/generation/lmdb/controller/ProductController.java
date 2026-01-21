package com.generation.lmdb.controller;

import com.generation.library.Console;
import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Producer;
import com.generation.lmdb.model.entities.Product;
import com.generation.lmdb.model.repository.ProductRepository;

/**

 * ProductController è un CONTROLLER nel pattern MVC.
 *
 * RESPONSABILITÀ DEL CONTROLLER:
 * - Gestire l'INTERAZIONE con l'utente per le operazioni sui Product
 * - Validare l'input dell'utente
 * - Chiamare i metodi del Repository (MODEL) per salvare/leggere dati
 * - Mostrare messaggi all'utente (VIEW semplificata con Console)
 *
 * SEPARAZIONE DELLE RESPONSABILITÀ:
 * - ProductController → gestisce Product
 * - ProducerController → gestisce Producer
 * - BatchController → gestisce Batch
 * - Main → solo il menu principale e il routing
 *
 * VANTAGGI:
 * 1. ORGANIZZAZIONE: ogni controller ha una responsabilità chiara
 * 2. MANUTENIBILITÀ: modifiche ai Product non toccano Producer o Batch
 * 3. RIUSABILITÀ: posso chiamare i metodi del controller da più punti
 * 4. TESTABILITÀ: posso testare ogni controller separatamente
 */

public class ProductController
{
	/*
	 * static → condiviso da tutti i metodi del controller
	 * private → solo questo controller può accedervi
	 *
	 * ProductRepository productRepo → gestisce la persistenza dei Product
	 */
	private static ProductRepository productRepo = (ProductRepository) Context.getDependency(ProductRepository.class);

	/**
	 *  AGGIUNGI PRODOTTO
	 *
	 * Questo metodo guida l'utente nell'inserimento di un nuovo prodotto.
	 *
	 * FLUSSO:
	 * 1. Crea un oggetto Product vuoto
	 * 2. Chiede all'utente di inserire i dati
	 * 3. Valida il prodotto con isValid()
	 * 4. Se valido → salva nel database
	 * 5. Se non valido → mostra errore e esce
	 */
	public static void addProduct()
	{
		Product product = new Product();
		Console.print("Inserisci nome prodotto: ");
		product.setName(Console.readString());
		Console.print("Inserisci descrizione prodotto: ");
		product.setDescription(Console.readString());
		Console.print("Inserisci prezzo unitario (in centesimi): ");
		product.setUnitPrice(Console.readInt());
		Console.print("\nProdotto creato:");
		Console.print(product.toString());
		Console.print("Valido: " + product.isValid());
		if (!product.isValid())
		{
			Console.print("\nERRORE: Prodotto non valido!");
			Console.print("Controlla che:");
			Console.print("- Nome non sia vuoto");
			Console.print("- Descrizione non sia vuota");
			Console.print("- Prezzo sia maggiore di 0");
			return;
		}
		productRepo.insert(product);
		Console.print("\n✓ Prodotto aggiunto con successo!");
	}

	/**
	 *  CAMBIA PREZZO PRODOTTO
	 *
	 * Questo metodo permette di modificare il prezzo di un prodotto esistente.
	 *
	 * FLUSSO:
	 * 1. Chiedi l'ID del prodotto da modificare
	 * 2. Cerca il prodotto nel database
	 * 3. Se trovato → chiedi il nuovo prezzo
	 * 4. Aggiorna il prodotto nel database
	 * 5. Se non trovato → mostra errore
	 */
	public static void changeProductPrice()
	{
		Console.print("Inserisci ID del prodotto: ");
		int productId = Console.readInt();
		Product product = productRepo.findById(productId);
		if (product == null)
		{
			Console.print("\nERRORE: Prodotto con ID " + productId + " non trovato!");
			return;
		}
		Console.print("\nProdotto trovato:");
		Console.print("Nome: " + product.getName());
		Console.print("Prezzo attuale: " + product.getUnitPrice() + " centesimi");
		Console.print("\nInserisci nuovo prezzo (in centesimi): ");
		int newPrice = Console.readInt();
		if (newPrice <= 0)
		{
			Console.print("\nERRORE: Il prezzo deve essere maggiore di 0!");
			return;
		}
		product.setUnitPrice(newPrice);
		productRepo.update(product);
		Console.print("\n✓ Prezzo aggiornato con successo!");
	}

	/**
	 * CAMBIA DESCRIZIONE PRODOTTO
	 *
	 * Permette di modificare la descrizione di un prodotto esistente.
	 */
	public static void changeProductDescription()
	{
		Console.print("Inserisci ID del prodotto: ");
		int productId = Console.readInt();
		Product product = productRepo.findById(productId);
		if (product == null)
		{
			Console.print("\nERRORE: Prodotto con ID " + productId + " non trovato!");
			return;
		}
		Console.print("\nProdotto trovato:");
		Console.print("Nome: " + product.getName());
		Console.print("Descrizione attuale: " + product.getDescription());
		Console.print("\nInserisci nuova descrizione: ");
		String newDescription = Console.readString();
		if (newDescription == null || newDescription.isBlank())
		{
			Console.print("\nERRORE: La descrizione non può essere vuota!");
			return;
		}
		product.setDescription(newDescription);
		productRepo.update(product);
		Console.print("\n✓ Descrizione aggiornata con successo!");
	}

    public static void printProductDetails(Product p)
    {
        Console.print("ID Prodotto: "      + p.getId());
        Console.print("Nome: "             + p.getName());
        Console.print("Descrizione: "      + p.getDescription());
        Console.print("Prezzo unitario: "  + p.getUnitPrice() + " centesimi");
    }
}
