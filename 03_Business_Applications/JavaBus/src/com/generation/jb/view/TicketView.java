package com.generation.jb.view;

import com.generation.library.Template;
import com.generation.jb.model.entities.Ticket;

/**
 * Classe responsabile della visualizzazione dei biglietti.
 * Utilizzata per creare formattatori di biglietti e presentare
 * le informazioni in modo chiaro e leggibile all'utente.
 *
 * Questa classe segue il pattern MVC (Model-View-Controller):
 * - Model: Ticket (contiene i dati)
 * - View: TicketView (visualizza i dati)
 * - Controller: Main (gestisce la logica)
 * 
 * Utilizza il sistema di template per caricare un file HTML/testo
 * e sostituire i placeholder con i dati effettivi del biglietto.
 */
public class TicketView
{
	String filename;
	
	public	TicketView(String filename)
	{
		this.filename = filename;
	}
	
	 /**
     * Renderizza un biglietto sostituendo i placeholder nel template
     * con i dati effettivi del biglietto.
     * 
     * Il metodo carica il template dal file specificato e sostituisce:
     * - [id] con l'ID del biglietto
     * - [km] con i chilometri
     * - [level] con il livello/classe
     * - [price] con il prezzo calcolato
     * 
     * @param ticket Il biglietto da renderizzare
     * @return Una stringa contenente il template compilato con i dati del biglietto
     */
    public String render(Ticket ticket) 
    {
        return Template
                .load(filename)                              // Carico il file template
                .replace("[id]", "" + ticket.id)             // Sostituisco [id] con l'id del biglietto
                .replace("[km]", "" + ticket.km)             // Sostituisco [km] con i km
                .replace("[level]", "" + ticket.level)       // Sostituisco [level] con il livello
                .replace("[price]", "" + ticket.getPrice()); // Sostituisco [price] con il prezzo
    }
}
