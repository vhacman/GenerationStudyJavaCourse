package com.generation.lmdb.controller;

import com.generation.library.Console;
import com.generation.library.Template;

/*
 * Main è l'ENTRY POINT (punto di ingresso) dell'applicazione.
 * La sua UNICA responsabilità è:
 * - Mostrare il menu
 * - Leggere il comando dell'utente
 * - DELEGARE l'esecuzione ai controller appropriati
 * STRUTTURA:
 * Main (routing)
 *   ├─> ProductController (gestisce Product)
 *   ├─> ProducerController (gestisce Producer)
 *   └─> BatchController (gestisce Batch)
 */

public class Main
{
	/*
	 * METODO MAIN - ENTRY POINT
	 * LOOP PRINCIPALE:
	 * do {
	 *     1. Mostra menu
	 *     2. Leggi comando utente
	 *     3. Esegui azione corrispondente (DELEGA ai controller)
	 *     4. Ripeti
	 * } while (comando != "Q");
	 */
	public static void main(String[] args)
	{
		String cmd;
		String menu = Template.load("template/txt/menu.txt");

		do
		{
			Console.print(menu);
			Console.print("\nInserire comando: ");
			cmd = Console.readString();

			switch(cmd)
			{
			    case "1":
			        Console.print(">>> AGGIUNGI BATCH <<<\n");
			        BatchController.addBatch();
			    break;

			    case "2":
			        Console.print(">>> REVOCA BATCH <<<\n");
			        BatchController.revokeBatch();
			    break;

			    case "3":
			        Console.print(">>> AGGIUNGI PRODUTTORE <<<\n");
			        ProducerController.addProducer();
			    break;

			    case "4":
			        Console.print(">>> AGGIUNGI PRODOTTO <<<\n");
			        ProductController.addProduct();
			    break;

			    case "5":
			        Console.print(">>> APPROVA BATCH <<<\n");
			        BatchController.approveBatch();
			    break;

			    case "6":
			        Console.print(">>> CAMBIA PREZZO PRODOTTO <<<\n");
			        ProductController.changeProductPrice();
			    break;

			    case "7":
			        Console.print(">>> CAMBIA INDIRIZZO PRODUTTORE <<<\n");
			        ProducerController.changeProducerAddress();
			    break;

			    case "8":
			        Console.print(">>> CAMBIA DESCRIZIONE PRODOTTO <<<\n");
			        ProductController.changeProductDescription();
			    break;

			    case "9":
			        Console.print(">>> CAMBIA PREZZO UNITARIO BATCH  <<<\n");
			        BatchController.changeBatchCustomPrice();
			    break;

			    case "10":
			        Console.print(">>> BANNA PRODUTTORE <<<\n");
			        ProducerController.banProducer();
			    break;

			    case "11":
			        Console.print(">>> RIATTIVA PRODUTTORE <<<\n");
			        ProducerController.unBanProducer();
			    break;

			    case "Q":
			    case "q":
			        Console.print("\n>>> USCITA <<<");
			        Console.print("Grazie per aver usato Local Market DB!");
			    break;

			    default:
			        Console.print("\n⚠ Comando non riconosciuto!");
			        Console.print("Usa i numeri 1-11 oppure Q per uscire.");
			}

			// Separatore visivo tra un comando e il prossimo
			Console.print("\n" + "=".repeat(50) + "\n");

		} while(!cmd.equals("Q") && !cmd.equals("q"));
	}
}
