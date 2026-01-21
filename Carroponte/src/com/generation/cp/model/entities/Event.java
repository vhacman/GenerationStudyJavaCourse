package com.generation.cp.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * Concerto, evento, sagra...
 */
public class Event extends Entity
{

	protected String     title;
	protected String     description;
	protected LocalDate  date;
	protected int        price;


	/**
	 * Costruttore completo per creare un evento.
	 *
	 * @param id Identificativo univoco dell'evento
	 * @param title Titolo dell'evento
	 * @param description Descrizione dettagliata
	 * @param date Data dell'evento
	 * @param price Prezzo del biglietto
	 */
	public Event(int id, String title, String description, LocalDate date, int price)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Ereditarietà e Incapsulamento
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Classe base (Entity) → Classe derivata (Event)
		 *
		 * Questo costruttore dimostra l'ereditarietà in azione:
		 * - Event eredita l'attributo protetto 'id' da Entity
		 * - Il costruttore inizializza sia gli attributi ereditati che quelli specifici
		 * - L'allineamento verticale migliora la leggibilità delle assegnazioni
		 *
		 * Principi OOP applicati:
		 * → Ereditarietà: Event riutilizza la struttura di Entity
		 * → Incapsulamento: Attributi protected accessibili solo nella gerarchia
		 * → Coesione: Tutti i dati necessari vengono inizializzati in un punto
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		this.id          = id;
		this.title       = title;
		this.description = description;
		this.date        = date;
		this.price       = price;
	}


	/**
	 * Converte l'evento in una rappresentazione testuale.
	 *
	 * @return Stringa contenente tutti i dettagli dell'evento
	 */
	@Override
	public String toString()
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Override e Polimorfismo
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Contratto Object.toString() → Implementazione Event.toString()
		 *
		 * L'override di toString() è un esempio di polimorfismo:
		 * - Object definisce il contratto generale
		 * - Event fornisce la sua rappresentazione specifica
		 * - Quando stampiamo un Event, Java usa questa versione automaticamente
		 *
		 * Principi OOP applicati:
		 * → Polimorfismo: Stesso metodo, comportamento diverso per ogni tipo
		 * → Overriding: Ridefinizione del comportamento della classe base
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		return "Event [id+"+id+" title=" + title + ", description=" + description + ", date=" + date + ", price=" + price + "]";
	}


	/**
	 * Valida lo stato interno dell'evento e restituisce una lista di errori di validazione.
	 *
	 * @return Lista di messaggi di errore, vuota se l'entità è valida
	 */
	@Override
	public List<String> getErrors()
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Pattern Template Method
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Contratto (Entity.getErrors()) → Implementazione specifica (Event.getErrors())
		 *
		 * Questo metodo implementa il pattern Template Method definendo la logica
		 * specifica di validazione per Event, mentre la classe base Entity definisce
		 * il contratto astratto e il metodo isValid() che usa getErrors().
		 *
		 * Principi OOP applicati:
		 * → Astrazione: Entity definisce il contratto senza conoscere i dettagli
		 * → Polimorfismo: Ogni sottoclasse fornisce la propria implementazione
		 * → Incapsulamento: Le regole di validazione sono contenute nell'entità stessa
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		List<String> res = new ArrayList<String>();

		if(isMissing(title))
			res.add("Missing title");

		if(isMissing(description))
			res.add("Missing description");

		if(date == null)
			res.add("Missing date");

		if(price < 0)
			res.add("Invalid price");

		return res;
	}


	// ═════════════════
	// GETTER E SETTER 
	// ═════════════════

	public String     getTitle()                     { return title; }
	public void       setTitle(String title)         { this.title = title; }

	public String     getDescription()               { return description; }
	public void       setDescription(String desc)    { this.description = desc; }

	public LocalDate  getDate()                      { return date; }
	public void       setDate(LocalDate date)        { this.date = date; }

	public int        getPrice()                     { return price; }
	public void       setPrice(int price)            { this.price = price; }

}
