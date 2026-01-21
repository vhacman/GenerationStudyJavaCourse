package com.generation.lm.demo;

import com.generation.library.Console;
import com.generation.lm.model.entities.Batch;
import com.generation.lm.model.repository.BatchRepository;

public class DemoLoadBatch 
{

	public static void main(String[] args) 
	{
	
		BatchRepository		repo 	= new BatchRepository();
		Batch				b 		= repo.load(1);
		
		Console.print("Batch con id "+  	b.getId());
		Console.print("Nome prodotto "+ 	b.getProduct().getName());
		Console.print("Fornitore "+			b.getProducer().getLegalName());
		Console.print("Quantità "+			b.getQuantity());
		Console.print("Prezzo unitario "+	b.getUnitPrice());
		Console.print("Prezzo attuale "+	b.getProduct().getUnitPrice());
		Console.print("Costo totale "+		b.getPrice());
		
		
	}

}
