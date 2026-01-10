package com.generation.lmdb.controller;

import com.generation.library.Console;
import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Producer;
import com.generation.lmdb.model.repository.ProducerRepository;

/*
 * ProducerController gestisce tutte le operazioni relative ai Producer (fornitori).
 *
 * OPERAZIONI PRINCIPALI:
 * - addProducer(): aggiunge un nuovo fornitore
 * - changeProducerAddress(): cambia l'indirizzo di un fornitore
 * - banProducer(): disattiva un fornitore (soft delete)
 * - unBanProducer(): riattiva un fornitore precedentemente bannato
 *
 */
public class ProducerController
{
	private static ProducerRepository 	producerRepo = (ProducerRepository) Context.getDependency(ProducerRepository.class);

	/**
	 * AGGIUNGI PRODUCER
	 *
	 * Crea un nuovo fornitore nel sistema.
	 *
	 * CAMPI DA INSERIRE:
	 * - legalName: nome legale dell'azienda
	 * - address: indirizzo fisico
	 * - history: note/storico (può essere vuoto inizialmente)
	 * - active: true per default (nuovo fornitore è sempre attivo)
	 **/
	public static void addProducer()
	{
		Producer producer = new Producer();
		Console.print("Inserisci nome legale del produttore: ");
		producer.setLegalName(Console.readString());
		Console.print("Inserisci indirizzo: ");
		producer.setAddress(Console.readString());
		Console.print("Inserisci storico/note (opzionale): ");
		String history = Console.readString();
		producer.setHistory(history.isBlank() ? "Nessuno storico" : history);
		producer.setActive(true);
		Console.print("\nProduttore creato:");
		Console.print("Nome: " + producer.getLegalName());
		Console.print("Indirizzo: " + producer.getAddress());
		Console.print("Attivo: " + producer.isActive());
		if (!producer.isValid())
		{
			Console.print("\nERRORE: Produttore non valido!");
			return;
		}
		producerRepo.insert(producer);
		Console.print("\n✓ Produttore aggiunto con successo!");
	}

	/**
	 * CAMBIA INDIRIZZO PRODUCER
	 *
	 * Aggiorna l'indirizzo di un fornitore esistente.
	 * Utile se il fornitore si trasferisce.
	 **/
	public static void changeProducerAddress()
	{
		Console.print("Inserisci ID del produttore: ");
		int producerId = Console.readInt();
		Producer producer = producerRepo.findById(producerId);
		if (producer == null)
		{
			Console.print("\nERRORE: Produttore con ID " + producerId + " non trovato!");
			return;
		}
		Console.print("\nProduttore trovato:");
		Console.print("Nome: " + producer.getLegalName());
		Console.print("Indirizzo attuale: " + producer.getAddress());
		Console.print("\nInserisci nuovo indirizzo: ");
		String newAddress = Console.readString();
		if (newAddress == null || newAddress.isBlank())
		{
			Console.print("\nERRORE: L'indirizzo non può essere vuoto!");
			return;
		}
		producer.setAddress(newAddress);
		producerRepo.update(producer);
		Console.print("\n✓ Indirizzo aggiornato con successo!");
	}

	/**
	 *  BAN PRODUCER (SOFT DELETE)
	 *
	 * Disattiva un fornitore senza cancellarlo dal database.
	 *
	 * PROCESSO:
	 * 1. Cerca il Producer
	 * 2. Imposta active = false
	 * 3. Aggiunge la motivazione del ban nell'history
	 * 4. Aggiorna il database
	 **/
	public static void banProducer()
	{
		Console.print("Inserisci ID del produttore da bannare: ");
		int producerId = Console.readInt();
		Producer producer = producerRepo.findById(producerId);
		if (producer == null)
		{
			Console.print("\nERRORE: Produttore con ID " + producerId + " non trovato!");
			return;
		}
		if (!producer.isActive())
		{
			Console.print("\nATTENZIONE: Questo produttore è già bannato!");
			return;
		}
		Console.print("\nProduttore trovato:");
		Console.print("Nome: " + producer.getLegalName());
		Console.print("Indirizzo: " + producer.getAddress());
		Console.print("\nInserisci motivo del ban: ");
		String reason = Console.readString();
		String newHistory = producer.getHistory() + "\n[BAN] " + reason;
		producer.setHistory(newHistory);
		producer.setActive(false);
		producerRepo.update(producer);
		Console.print("\n Produttore bannato con successo!");
		Console.print("Il produttore non potrà più essere usato per nuovi acquisti.");
	}

	/**
	 *  UNBAN PRODUCER
	 *
	 * Riattiva un fornitore precedentemente bannato.
	 *
	 * QUANDO USARLO:
	 * - Il fornitore ha risolto i problemi che avevano causato il ban
	 * - Vogliamo ricominciare a comprare da lui
	 *
	 * PROCESSO:
	 * 1. Cerca il Producer
	 * 2. Verifica che sia effettivamente bannato (active = false)
	 * 3. Imposta active = true
	 * 4. Aggiunge una nota nell'history
	 * 5. Aggiorna il database
	 */
	public static void unBanProducer()
	{
		Console.print("Inserisci ID del produttore da riattivare: ");
		int producerId = Console.readInt();
		Producer producer = producerRepo.findById(producerId);
		if (producer == null)
		{
			Console.print("\nERRORE: Produttore con ID " + producerId + " non trovato!");
			return;
		}
		if (producer.isActive())
		{
			Console.print("\nATTENZIONE: Questo produttore è già attivo!");
			return;
		}
		Console.print("\nProduttore trovato:");
		Console.print("Nome: " + producer.getLegalName());
		Console.print("Storico: " + producer.getHistory());

		Console.print("\nInserisci motivo della riattivazione: ");
		String reason = Console.readString();
		String newHistory = producer.getHistory() + "\n[UNBAN] " + reason;
		producer.setHistory(newHistory);
		producer.setActive(true);
		producerRepo.update(producer);
		Console.print("\n Produttore riattivato con successo!");
		Console.print("Il produttore può essere nuovamente usato per acquisti.");
	}

	
    public static void printProducerDetails(Producer p)
    {
        Console.print("ID Produttore "  + p.getId());
        Console.print("Nome legale "    + p.getLegalName());
        Console.print("Indirizzo "      + p.getAddress());
        Console.print("Storia/Note "    + p.getHistory());
        Console.print("Attivo "         + (p.isActive() ? "Sì" : "No"));
    }
}
