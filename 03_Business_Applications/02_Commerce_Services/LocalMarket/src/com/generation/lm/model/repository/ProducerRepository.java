package com.generation.lm.model.repository;

import com.generation.library.Console;
import com.generation.library.FileReader;
import com.generation.library.FileWriter;
import com.generation.lm.model.entities.Producer;

/**
 * Permanenza dei Producer
 */
public class	ProducerRepository 
{
	final String 			archivePath = "archive/producer";
		
	public void 	save(Producer p)
	{
		// TODO: Verificare validità del Producer prima di salvare
		if (!p.isValid())
	    {
	        Console.print("Errore: producer non valido");
	        return;
	    }
		String 		filename = archivePath + "/"+p.getId()+".txt";
		FileWriter 	writer = new FileWriter(filename);
		writer.print(p.getId() + "\n");
		writer.print(p.getLegalName() + "\n");
		writer.print(p.getAddress() + "\n");
		writer.print(p.getHistory() + "\n");
		writer.print(p.isActive() ? "ACTIVE" : "INACTIVE");
		writer.close();
	}
	
	public Producer		load(int id)
	{
		String filename = archivePath + "/"+id+".txt";
		try
		{
			FileReader 	reader 	= new FileReader(filename);
			Producer 	p 		= new Producer();
			p.setId(reader.readInt());
			p.setLegalName(reader.readString());
			p.setAddress(reader.readString());
			p.setHistory(reader.readString());
			p.setActive(reader.readString().equals("ACTIVE"));
			reader.close();
			return p;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
}
