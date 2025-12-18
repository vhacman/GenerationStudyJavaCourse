package com.generation.jia.view;

import com.generation.jia.model.entities.AirlineTicket;
import com.generation.library.Console;
import com.generation.library.FileReader;
import com.generation.library.Template;

public class JavaitaView
{
	String filename;
	
	public JavaitaView(String filename)
	{
		this.filename = filename;
	}
	
	public static int showMainMenu()
	{
		String menu = Template.load("template/menu.txt");
		if(menu == null || menu.isEmpty())
		{
			return 0;
		}
		Console.print(menu);
	    return Console.readIntBetween("Scegli un'opzione: ", "Opzione non valida!", 1, 3);
	}

	public String render(AirlineTicket ticket)
	{
		return Template
				.load(filename)                                           
				.replace("[id]", "" + ticket.id)                             
				.replace("[km]", "" + ticket.km)            
				.replace("[serviceClass]", "" + ticket.serviceClass)     
				.replace("[membershipType]", "" + ticket.membershipType)    
				.replace("[date]", "" + ticket.date)                    
				.replace("[start]", "" + ticket.start)                       
				.replace("[end]", "" + ticket.end)                          
				.replace("[duration]", "" + ticket.getDuration())           
				.replace("[price]", "" + String.format("%.2f", ticket.getPrice()))
				.replace("[isValid]", "" + (ticket.isValid() ? "VALIDO" : "NON VALIDO"));  
	}
}
