package com.generation.gbb.controller;

import java.util.List;

import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.repository.GuestRepository;
import com.generation.gbb.repository.GuestRepositoryFactory;
import com.generation.library.Console;

public class Main
{

	//appartiene alla classe e non all'oggetto.
	//le variabili statiche non vengono ereditate
	static GuestRepository 	guestRepo = GuestRepositoryFactory.make();

	public static void main(String[] args)
	{
		if (guestRepo == null)
		{
			Console.print("Missing dependency GuestRepository");
			return;
		}
		Console.print("Benvenuti a Grottammare B&B");

		String 	cmd;
		do
		{
			Console.print("\n=== MENU PRINCIPALE ===");
			Console.print("1 - Lista tutti gli ospiti (GUESTLIST)");
			Console.print("2 - Cerca ospite per ID (FINDGUESTBYID)");
			Console.print("3 - Cerca ospite per Codice Fiscale (FINDGUESTBYSSN)");
			Console.print("4 - Cerca ospiti per Cognome (FINDGUESTBYSURNAMECONTAINING)");
			Console.print("5 - Cerca ospiti per Città (FINDGUESTBYCITY)");
			Console.print("Q - Esci");
			Console.print("\nInserisci comando:");
			cmd = Console.readString().toUpperCase().trim();

			switch (cmd)
			{
				case "1":
				case "GUESTLIST":
					guestList();
				break;

				case "2":
				case "FINDGUESTBYID":
					findGuestById();
				break;

				case "3":
				case "FINDGUESTBYSSN":
					findGuestBySSN();
				break;

				case "4":
				case "FINDGUESTBYSURNAMECONTAINING":
					findGuestBySurnameContaining();
				break;

				case "5":
				case "FINDGUESTBYCITY":
					findGuestByCity();
				break;

				case "Q":
					Console.print("Goodbye");
				break;

				default:
					Console.print("Comando non riconosciuto");
			}

		} while (!cmd.equals("Q"));

	}

	private static void guestList()
	{
		List<Guest> 	all = guestRepo.findAll();
		for(Guest g : all)
			Console.print(g.getId() + " - " + g.getFirstName() + " " + g.getLastName());
	}

	private static void findGuestById()
	{
		Console.print("Inserire id ospite:");
		int 	id = Console.readInt();
		Guest 	g  = guestRepo.findById(id);
		if (g == null)
			Console.print("Non trovato");
		else
			Console.print(g.getId() + " - " + g.getFirstName() + " " + g.getLastName());
	}

	private static void findGuestBySSN()
	{
		Console.print("Inserire Codice Fiscale:");
		String 	ssn   = Console.readString().trim();
		Guest 	guest = guestRepo.findBySSN(ssn);
		if (guest == null)
			Console.print("Non trovato");
		else
			Console.print(guest.getId() + " - " + guest.getFirstName() + " " + guest.getLastName());
	}

	private static void findGuestBySurnameContaining()
	{
		Console.print("Inserire parte del cognome:");
		String 			part  = Console.readString().trim();
		List<Guest> 	found = guestRepo.findBySurnameContaining(part);
		if (found.isEmpty())
			Console.print("Nessun ospite trovato con cognome contenente '" + part + "'");
		else
		{
			Console.print("\n=== " + found.size() + " Ospiti Trovati ===");
			for (Guest gst : found)
				Console.print(gst.getId() + " - " + gst.getFirstName() + " " + gst.getLastName() + " (" + gst.getCity() + ")");
		}
	}

	private static void findGuestByCity()
	{
		Console.print("Inserire nome città:");
		String 			city         = Console.readString().trim();
		List<Guest> 	foundByCity  = guestRepo.findByCity(city);
		if (foundByCity.isEmpty())
			Console.print("Nessun ospite trovato nella città '" + city + "'");
		else
		{
			Console.print("\n=== " + foundByCity.size() + " Ospiti Trovati ===");
			for (Guest gst : foundByCity)
				Console.print(gst.getId() + " - " + gst.getFirstName() + " " + gst.getLastName() + " (" + gst.getCity() + ")");
		}
	}

}
