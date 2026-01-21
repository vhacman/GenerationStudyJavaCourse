package com.generation.jia.main;

import java.time.LocalDate;
import java.time.LocalTime;

import com.generation.jia.model.entities.AirlineTicket;
import com.generation.jia.model.entities.MembershipType;
import com.generation.jia.model.entities.ServiceClass;
import com.generation.jia.view.JavaitaView;
import com.generation.library.Console;
import com.generation.library.FileWriter;

public class Main
{
	static AirlineTicket ticket;
	
	public static void main(String[] args)
	{
		JavaitaView.showMainMenu();
		ticket = askTicket();
		
        JavaitaView viewTXT = new JavaitaView("template/template.txt");
        JavaitaView viewHTML = new JavaitaView("template/template.html");
        Console.print(viewTXT.render(ticket));
        Console.print("\nSi vuole salvare in formato HTML? (S/N): ");
        if (Console.readString().equalsIgnoreCase("S")) {
            String filenameHTML = "print/" + ticket.id + "_biglietto" + ".html";
            FileWriter.writeTo(filenameHTML, viewHTML.render(ticket));
            Console.print("File HTML salvato: " + filenameHTML + "\n");
        }
        else
            Console.print("Salvataggio annullato.\n");
	}

	
	private static AirlineTicket askTicket() 
	{
        do
        {	ticket = new AirlineTicket();
			askInformation();
			askDateTime();			
            if(!ticket.isValid()) {
                Console.print("Dati non validi! Riprova.\n");
            }

        } while(!ticket.isValid());
        return ticket;			
	}
	
	public static void askInformation()
	{

		ticket.id = Console.askNotEmptyString("Inserire ID: ", "ID non valido");
        ticket.km = Console.readIntBetween("Inserire km: ", "Tratta troppo lunga", 1, 1000);
        
    	Console.print("\nClasse di servizio:");
		Console.print("1 = BASIC (€0.10/km)");
		Console.print("2 = SILVER (€0.20/km)");
		Console.print("3 = GOLD (€0.50/km)");
		int serviceLevel = Console.readIntBetween("Inserire classe di servizio: ", "Classe non valida", 1, 3);		
		switch(serviceLevel)
		{
			case 1:
				ticket.serviceClass = ServiceClass.BASIC;
				break;
			case 2:
				ticket.serviceClass = ServiceClass.SILVER;
				break;
			case 3:
				ticket.serviceClass = ServiceClass.GOLD;
				break;
		}			
		Console.print("\nTipo membership:");
		Console.print("1 = NONE (nessuno sconto)");
		Console.print("2 = SILVER (20% sconto)");
		Console.print("3 = GOLD (30% sconto)");
		int membershipLevel = Console.readIntBetween("Inserire membership: ", "Membership non valida", 1, 3);
		switch(membershipLevel)
		{
			case 1:
				ticket.membershipType = MembershipType.NONE;
				break;
			case 2:
				ticket.membershipType = MembershipType.SILVER;
				break;
			case 3:
				ticket.membershipType = MembershipType.GOLD;
				break;
		}
	}
	
	public static void askDateTime()
	{

		// Data
		Console.print("Inserire data partenza (formato YYYY-MM-DD): ");
		ticket.date = LocalDate.parse(Console.readString());
					
		// Orario partenza
		Console.print("Inserire orario partenza (formato HH:MM): ");
		ticket.start = LocalTime.parse(Console.readString());
					
		// Orario arrivo
		Console.print("Inserire orario arrivo (formato HH:MM): ");
		ticket.end = LocalTime.parse(Console.readString());		
	}
}
