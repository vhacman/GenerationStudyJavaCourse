package com.generation.lm.model.repository;

import com.generation.library.Console;
import com.generation.library.FileReader;
import com.generation.library.FileWriter;
import com.generation.lm.model.entities.Batch;
import com.generation.lm.model.entities.Producer;
import com.generation.lm.model.entities.Product;

/**
 * Permanenza dei Batch
 */
public class BatchRepository 
{
	String 				archivePath 			= "archive/batch/";
	ProductRepository 	productRepo 			= new ProductRepository();
	ProducerRepository 	producerRepo	 		= new ProducerRepository();
	
	public void save(Batch batch)
	{
		// TODO: Verificare validità del Batch prima di salvare
		if (!batch.isValid())
	    {
	        Console.print("Errore: batch non valido");
	        return;
	    }
	    
	    String 		filename 	= archivePath + batch.getId() + ".txt";
	    FileWriter 	writer 		= new FileWriter(filename);
	    
	    writer.print(batch.getId() + "\n");
	    writer.print(batch.getProducer().getId() + "\n");
	    writer.print(batch.getProduct().getId() + "\n");
	    writer.print(batch.getQuantity() + "\n");
	    writer.print(batch.getUnitPrice() + "\n");
	    writer.print(batch.getDate() + "\n");
	    writer.print(batch.getStatus() + "\n");
	    writer.print(batch.getNotes() + "\n");
	    writer.close();
	}
	
	public Batch	load(int id)
	{
		String		filename = archivePath+id+".txt";
		
		try
		{
			FileReader 			reader 	= new FileReader(filename);
			Batch 				b 		= new Batch();
			b.setId(reader.readInt());
			
			int producerId = reader.readInt(); // prendo l'id del produttore
			Producer producer = producerRepo.load(producerId);
			b.setProducer(producer); // collego il produttore al batch
			
			int productId = reader.readInt(); // leggo id prodotto dal file del batch
			Product product = productRepo.load(productId); // tramite id carico il prodotto
			b.setProduct(product);	// collego il prodotto al batch
			
			b.setQuantity(reader.readInt());
			b.setUnitPrice(reader.readInt());
			b.setDate(reader.readString());
			
			b.setStatus(reader.readString());
			b.setNotes(reader.readString());
			reader.close();
			
			return b;
		}
		catch(Exception e)
		{
			e.printStackTrace(); // stampo dettaglio errore
			return null;
		}
	}
	
	
}
