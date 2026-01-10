package com.generation.lm.controller;


import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;
import com.generation.lm.model.entities.Batch;
import com.generation.lm.model.repository.BatchRepository;
import com.generation.lm.model.repository.ProducerRepository;
import com.generation.lm.model.repository.ProductRepository;
import com.generation.lm.view.BatchView;

public class Main
{
	private static BatchRepository batchRepo =
			new BatchRepository();

	private static ProductRepository productRepo =
			new ProductRepository();

	private static ProducerRepository producerRepo =
			new ProducerRepository();

	private static BatchController batchController =
			new BatchController(batchRepo, productRepo, producerRepo);

	private static ProductController productController =
			new ProductController(productRepo);

	private static ProducerController producerController =
			new ProducerController(producerRepo);

	private static BatchView batchView =
			new BatchView("template");
	
	
	public static void main(String[] args)
	{
		String cmd;
		String menu = Template.load("template/txt/menu.txt");

		do
		{
			Console.print(menu);
			Console.print("\nInserire comando: ");
			cmd = Console.readString();
			
			/*
			 * esigenze operative
			 * - 1: inserire nuovi produttori
			 * - 2: inserire nuovi prodotti
			 * - 3: reimpostare lo stato di un batch a approvato
			 * - 4: cambiare il PREZZO di un prodotto esistente
			 *      prima mostrate il prezzo e poi mi date il nuovo valore
			 * - 5: cambiare indirizzo di un fornitore
			 * - 6: cambiare descrizione di un prodotto
			 * - 7: fare un altro inserimento di batch
			 *      che chieda il prezzo unitario, che non usi subito
			 *      quello standard
			 * - 8: funzione per bannare un fornitore
			 * - 9: funzione per sbannare un fornitore
			 * - 10: stampa scheda HTML del batch
			 * che deve riportare anche i dati del prodotto
			 * e del fornitore
			 * 
			 * altre richieste:
			 * - se l'utente inserisce lotti
			 * per un fornitore non attivo, dare errore
			 * e non procedere
			 * 
			 * 
			 * - se l'utente prova a revocare un lotto già revocato
			 * dare errore e non procedere
			 * 
			 * mettere isValid su tutte le entità
			 * e rifiutarsi di salvare se l'entità non è valida
			 * 
			 * strutturare tutto usando le viste
			 */
			
			switch(cmd)
			{
			    case "1":
			        Console.print("ADDBATCH");
			        batchController.addBatch();
			    break;
			    case "2":
			        Console.print("REVOKEBATCH");
			        batchController.revokeBatch();
			    break;
			    case "3":
			        Console.print("ADDPRODUCER");
			        producerController.addProducer();
			    break;
			    case "4":
			        Console.print("ADDPRODUCT");
			        productController.addProduct();
			    break;
			    case "5":
			        Console.print("APPROVEBATCH");
			        batchController.approveBatch();
			    break;
			    case "6":
			        Console.print("CHANGEPRODUCTPRICE");
			        productController.changeProductPrice();
			    break;
			    case "7":
			        Console.print("CHANGEPRODUCERADDRESS");
			        producerController.changeProducerAddress();
			    break;
			    case "8":
			        Console.print("CHANGEPRODUCTDESCRIPTION");
			        productController.changeProductDescription();
			    break;
			    case "9":
			        Console.print("ADDBATCHCUSTOMPRICE");
			        batchController.addBatchCustomPrice();
			    break;
			    case "10":
			        Console.print("BANPRODUCER");
			        producerController.banProducer();
			    break;
			    case "11":
			        Console.print("UNBANPRODUCER");
			        producerController.unBanProducer();
			    break;
			    case "12":
			        Console.print("PRINTHTMLBATCH");
			        printHTMLBatch();
			    break;
			    default:
			        Console.print("Non riconosciuto");
			}
		}while(!cmd.equals("Q"));
		
	}
	private static void printHTMLBatch()
	{
		Console.print("Inserire ID batch da stampare: ");
		int batchID = Console.readInt();

		Batch batch = batchRepo.load(batchID);

		if (batch == null)
		{
			Console.print("ERRORE: Batch non trovato!");
			return;
		}

		if (!batch.isValid())
		{
			Console.print("ERRORE: Batch non valido");
			return;
		}

		String html = batchView.renderBatch(batch);

		try
		{
			String filename = "print/batch_" + batch.getId() + "_" +
							  LocalDate.now().toString() + ".html";
			FileWriter writer = new FileWriter(filename);
			writer.print(html);
			writer.close();
			Console.print("File HTML salvato: " + filename);
		}
		catch (Exception e)
		{
			Console.print("ERRORE durante il salvataggio del file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
