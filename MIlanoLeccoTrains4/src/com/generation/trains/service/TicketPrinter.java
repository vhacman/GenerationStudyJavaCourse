package com.generation.trains.service;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

/**
 * Classe responsabile della visualizzazione e del salvataggio dei biglietti ferroviari.
 * 
 * Questa classe implementa il pattern "Printer" o "Formatter", separando la logica
 * di presentazione dei dati dalla logica di business del biglietto stesso.
 * 
 * Funzionalità principali:
 * - Stampa del biglietto a console in formato testuale
 * - Esportazione del biglietto in formato HTML
 * - Utilizzo di template esterni per una presentazione personalizzabile
 * 
 * Design Pattern utilizzato: Strategy Pattern (per diversi formati di output)
 */
public class TicketPrinter
{
	
	// ===== ATTRIBUTO DI ISTANZA =====
	
	/**
	* Riferimento al biglietto da stampare/esportare.
	* Questo oggetto contiene tutti i dati necessari per la visualizzazione:
	* nome passeggero, tratta, prezzo, ecc.
	*/
	private Ticket	ticket;

	/**
	 * Costruttore che inizializza il printer con un biglietto specifico.
	 * 
	 * Questo approccio permette di:
	 * 1. Creare un printer dedicato per ogni biglietto
	 * 2. Riutilizzare lo stesso printer per multiple operazioni sullo stesso biglietto
	 * 3. Mantenere il codice più pulito evitando di passare il biglietto a ogni metodo
	 * 
	 * @param ticket il biglietto da associare a questo printer
	 */
	public TicketPrinter(Ticket ticket)
	{
		this.ticket = ticket;
	}
	
	// ===== METODI DI STAMPA =====
	
		/**
		 * Stampa il biglietto a console utilizzando un template testuale.
		 * 
		 * PROCESSO:
		 * 1. Carica il template da file (print/template.txt)
		 * 2. Sostituisce i placeholder [nomeVariabile] con i valori reali del biglietto
		 * 3. Visualizza il risultato finale a console
		 * 
		 * 
		 * GESTIONE ERRORI:
		 * Se il template non viene trovato, stampa un messaggio di avviso e termina
		 * senza causare crash dell'applicazione.
		 */
	public	void	printToConsole()
	{
		String	stampaTestuale = Template.load("print/template.txt");
		
		if (stampaTestuale.isEmpty())
		{
			Console.print("ATTENZIONE: template.txt non trovato");
			return;
		}
		
		stampaTestuale = stampaTestuale
					.replace("[fullName]", ticket.getFullName())
					.replace("[age]", ticket.getAge() + "")
					.replace("[departure]", ticket.getDeparture().toUpperCase())
					.replace("[arrival]", ticket.getArrival().toUpperCase())
					.replace("[km]", ticket.getDistance() + "")
					.replace("[date]", ticket.getDate())
					.replace("[hour]", ticket.getHour())
					.replace("[level]", ticket.getLevel() == 1 ? "Prima" : "Seconda")
					.replace("[greenCard]", ticket.hasGreenCard() ? "Sì" : "No")
					.replace("[price]", ticket.getPrice() + "");
		 Console.print(stampaTestuale);
	}
	
	/**
	 * Esporta il biglietto in formato HTML e lo salva su file.
	 * 
	 * PROCESSO:
	 * 1. Carica il template HTML da file (print/template.html)
	 * 2. Sostituisce i placeholder con i valori reali (stesso meccanismo di printToConsole)
	 * 3. Salva il risultato in un file HTML nel filesystem
	 * 4. Conferma il salvataggio all'utente
	 * 
	 * 
	 * Il template HTML può includere CSS per lo styling e rendere
	 * il biglietto più professionale e visivamente accattivante.
	 */
	public void printToHTML()
	{
		String template = Template.load("print/template.html");
		
		if (template.isEmpty())
		{
			Console.print("ATTENZIONE: template.html non trovato");
			return;
		}
		template = template
			.replace("[fullName]", ticket.getFullName())
			.replace("[age]", ticket.getAge() + "")
			.replace("[departure]", ticket.getDeparture().toUpperCase())
			.replace("[arrival]", ticket.getArrival().toUpperCase())
			.replace("[km]", ticket.getDistance() + "")
			.replace("[date]", ticket.getDate())
			.replace("[hour]", ticket.getHour())
			.replace("[level]", ticket.getLevel() == 1 ? "Prima" : "Seconda")
			.replace("[greenCard]", ticket.hasGreenCard() ? "Sì" : "No")
			.replace("[price]", ticket.getPrice() + "");
		
		String filename = "biglietto.html";
		FileWriter.writeTo(filename, template);
		Console.print("Biglietto salvato in " + filename);
	}
	
}
