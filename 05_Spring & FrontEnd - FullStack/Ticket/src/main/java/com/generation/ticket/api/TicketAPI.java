package com.generation.ticket.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ticket.model.entities.Ticket;
import com.generation.ticket.model.repository.TicketRepository;

// questa classe è ESPOSTA
// ha dei metodi mappati, raggiungibili pubblicamente
@RestController
@RequestMapping("ticketservice/api/tickets")
public class TicketAPI 
{
	// quando ho definito il repository
	// spring ha automaticamente creato la classe che lo implementa
	// l'oggetto di quella classe
	// e l'ha messo nel context
	
	// autowired vuol dire "DOMANDA". DAMMI UN TicketRepository dal tuo Context
	@Autowired
	TicketRepository repo;
	
	
	// quando un utente chiamerà questo indirizzo (/api/tickets col VERBO Get)
	// produce JSON - OGGETTI Javascript
	// Io con le API parlo in JSON
	// le API mi restituiscono JSON, io gli invio JSON!
	@GetMapping
	public List<Ticket> findOpen()
	{
		return repo.findByStatusOrderByOpenOn("Open");
	}
	
	// requestbody vuol dire che il client mi manderà lo stato di un ticket
	// sotto forma di json. RequestBody vuol dire che la request sarà
	// convertita in un Ticket. La request conterrà json e verrà convertita in un ticket
	// per comodità non specifico la data di apertura, prendo il tempo attuale
	@PostMapping
	public Ticket newTicket(@RequestBody Ticket ticket)
	{
		ticket.setOpenOn(LocalDateTime.now());
		ticket.setStatus("Open");
		return repo.save(ticket);
	}
	
	@PutMapping
	public Ticket updateTicket(@RequestBody Ticket newVersion)
	{
		// carico dal db
		Optional<Ticket> oldVersionOpt = repo.findById(newVersion.getId());
		/*
		 * era una notte buia e tempestosa
		 * un designer si era reso conto che findBYId potrebbe restituire null
		 * quindi questo genio assoluto disse
		 * creiamo una classe che potrebbe contenere o meno un oggetto
		 * Optional<X> potrebbe o meno contenere un oggetto di tipo X
		 * e costringerò il programmatore a vedere se dentro Optional<X> c'è qualcosa oppure no
		 * che è molto meglio che verificare che sia diverso da null
		 * almeno nella mente di questo genio che ha sviluppato Optional
		 */
		
		Ticket oldVersion = oldVersionOpt.get(); // prendo il contenuto della scatola
		
		oldVersion.setStatus(newVersion.getStatus());
		oldVersion.setClosedOn(newVersion.getClosedOn());
		oldVersion.setClosure(newVersion.getClosure());
		
		return repo.save(oldVersion);
	}
	
	/**
	 * Il metodo get non invia un corpo di solito
	 * i parametri col metodo get si prendono dall'indirizzo
	 * PathVariable => collega questa parte dell'indirizzo ({id}) alla variabile id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Ticket findTicket(@PathVariable("id") int id)
	{
		return repo.findById(id).get();
	}
	
	

}
