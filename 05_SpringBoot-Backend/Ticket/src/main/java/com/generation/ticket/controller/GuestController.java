package com.generation.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Produco pagine web per l'ospite
 */
@Controller
public class GuestController 
{
	// sto renderizzando newTicket.html della cartella template
	// la String che produco è il nome del template da usare
	@RequestMapping("/ticketservice/portal/newticket")
	public String insertTicket()
	{
		// spring è andato a renderizzare la pagina newticket.html nella cartella template
		// e l'ha inviata al browser
		return "newTicket";
	}

}
