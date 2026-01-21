package com.generation.lm.model.repository;

import com.generation.library.Console;
import com.generation.library.FileReader;
import com.generation.library.FileWriter;
import com.generation.lm.model.entities.Product;

/**
 * Permanenza dei prodotti
 */
public class ProductRepository 
{
	final String archivePath = "archive/product";
	
	/**
	 * Salvo un prodotto. Se esisteva già lo sovrascrivo
	 * Questo metodo si può usare sia per CREARE prodotti nuovi su hard disk
	 * sia per AGGIORNARLI
	 * si dice in gergo che fa sia CREATE che UPDATE
	 * @param p
	 */
	public void save(Product p)
	{
		// TODO: Verificare validità del Product prima di salvare
		if (!p.isValid())
	    {
	        Console.print("Errore: product non valido");
	        return;
	    }
		String 		filename 	= archivePath + "/"+p.getId()+".txt";
		FileWriter 	writer 		= new FileWriter(filename);
		writer.print(p.getId()+"\n");
		writer.print(p.getName()+"\n");
		writer.print(p.getDescription()+"\n");
		writer.print(p.getUnitPrice());
		writer.close();
	}
	
	/**
	 * Entra un id di un prodotto esce il prodotto caricato da file
	 * oppure il valore null se non l'ho trovato o se ho problemi
	 * @param id
	 * @return
	 */
	public Product load(int id)
	{
		String 	filename = archivePath + "/"+id+".txt";
		
		try
		{
			FileReader 	reader 	= new FileReader(filename);
			Product 	p 		= new Product();
			p.setId(reader.readInt());
			p.setName(reader.readString());
			p.setDescription(reader.readString());
			p.setUnitPrice(reader.readInt());
			reader.close();
			return p;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
	
	
}
