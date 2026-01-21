package com.generation.gbb.demo;

import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.repository.factory.GuestRepositoryFactory;
import com.generation.gbb.repository.interfaces.GuestRepository;
import com.generation.library.Console;

public class InsertGuestDemo
{
    public static void main(String[] args)
    {
    	esempioOk();
    	esempioKO();
    }

	private static void esempioOk()
    {
    	Console.print("Esempio di salvataggio riuscito");
        GuestRepository repo = GuestRepositoryFactory.make();
        Console.print("Per ora ho " + repo.findAll().size() + " guests");
        Guest g = new Guest();
        g.setFirstName("Mario");
        g.setLastName("Verdi");
        g.setSsn("VRDMRA85M01H501Z");
        g.setDob("1985-08-01");
        g.setAddress("Via Roma 10");
        g.setCity("Milano");
        repo.insert(g);
        Console.print("Salvato con id " + g.getId());
    }

    private static void esempioKO()
    {
    	Console.print("Esempio di salvataggio non riuscito");
        GuestRepository repo = GuestRepositoryFactory.make();
        Console.print("Per ora ho " + repo.findAll().size() + " guests");
        Guest g = new Guest();
        g.setFirstName("Mario");
        g.setLastName("Verdi");
        g.setSsn("");
        g.setDob("1985-08-01");
        g.setAddress("Via Roma 10");
        g.setCity("Milano");
        repo.insert(g);
        Console.print("Salvato con id " + g.getId());
	}
}
