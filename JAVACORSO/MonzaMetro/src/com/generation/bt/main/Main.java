package com.generation.bt.main;

import java.time.format.DateTimeFormatter;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;
import com.generation.ticketGenerator.Ticket;
import com.generation.ticketGenerator.TicketService;

public class Main 
{
	public static void main(String[] args) 
	{
		Console.print("=== SISTEMA DI GENERAZIONE BIGLIETTI ===\n");
		
		// Genera il biglietto
		Ticket ticket = TicketService.generateTicket();
		
		// Stampa i dettagli del biglietto
		TicketService.printTicket(ticket);
		TicketService.printTemplate(ticket);
	}
	
	
}