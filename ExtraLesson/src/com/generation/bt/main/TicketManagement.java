package com.generation.bt.main;

import java.time.LocalDateTime;

import com.generation.bt.model.entities.Ticket;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

public class TicketManagement 
{
	

	public static void main(String[] args) 
	{
		// questa classe gestirà i Ticket. La classe Ticket.java RAPPRESENTA uno STAMPO per creare variabili di tipo Ticket
		String goOn;
		do
		{
			insertTicket();
			Console.print("Si vuole continuare?");
			goOn= Console.readString();
		}while(goOn.equalsIgnoreCase("Y"));

		
	}
	
	// questo è un sottoprogramma della classe TicketManagement che viene usato dal metodo main della stessa classe
	// il metodo main lo chiamerà tutte le volte necessarie.
	private static void insertTicket()
	{
		// la variabile t è un biglietto.
		Ticket t = new Ticket();
		
		Console.print("Inserire id biglietto");
		t.id = Console.readString();
		// NON chiedo la data e l'ora attuali
		//       classe => now è un sottoprogramma che produce la data e il tempo attuali
		t.time = LocalDateTime.now(); // assegno automaticamente data e tempo attuali

		Console.print("Inserire partenza");
		t.from = Console.readString();
		Console.print("Inserire destinazione");
		t.to = Console.readString();
		
		Console.print("Inserire km");
		t.km = Console.readInt();
		
		Console.print("Inserire classe");
		t.level = Console.readInt();
		
		// NON è il main che fa i calcoli!! E' il biglietto stesso, è t che contiene i propri dati
		// e i propri sottoprogrammi
		if(t.isValid())
		{
			Console.print("Riepilogo biglietto");
			Console.print(t.toString());
			Console.print("Si vuole stampare? Y/N");
			String print = Console.readString();
			if(print.equals("Y"))
			{
				// ho caricato l'intero template HTML
				String docHTML = Template.load("template/template.html");
				// rimpiazzo tutti i segnaposti 
				docHTML = docHTML.replace("[id]", t.id);
				docHTML = docHTML.replace("[from]", t.from);
				docHTML = docHTML.replace("[to]", t.to);
				docHTML = docHTML.replace("[time]", t.time+"");
				docHTML = docHTML.replace("[price]", t.getPrice()+"");
				docHTML = docHTML.replace("[level]", t.level+"");
				docHTML = docHTML.replace("[km]", t.km+"");
				// dove lo vado a salvare?
				String filename = "ticket/ticket"+t.id+".html";
				// stampo la stringa docHTML con i segnaposti sostituiti in un file nuovo
				FileWriter.writeTo(filename, docHTML);
				Console.print("Salvataggio riuscito");				
			}
			else
				Console.print("Operazione annullata");
		}
		else
			Console.print("Errore nei dati");
	}

}
