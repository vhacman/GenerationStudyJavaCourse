package com.generation.ticket.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.ticket.model.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>
{

	/**
	 * Spring genera questo metodo per noi
	 * interpretando il nome
	 * non carica TUTTI i ticket, carica dal db solo quelli per cui 
	 * status = status
	 * findWhere("status='"+status+"'");
	 * @param status
	 * @return
	 */
	List<Ticket> findByStatusOrderByOpenOn(String status);
	
}
