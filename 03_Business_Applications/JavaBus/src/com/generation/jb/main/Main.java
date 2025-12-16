package com.generation.jb.main;

import com.generation.jb.view.TicketView;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.jb.model.entities.Ticket;

/**
 * Classe principale per la gestione dei biglietti ferroviari.
 * Permette all'utente di creare biglietti e calcolarne il prezzo.
 */
public class Main
{
    /**
     * Metodo principale che avvia il programma.
     *
     * Processo:
     * 1. Chiede all'utente di inserire i dati del biglietto
     * 2. Mostra un'anteprima in formato testo
     * 3. Offre la possibilità di salvare in formato HTML
     *
     * @param args Argomenti da linea di comando (non utilizzati)
     */
	public static void main(String[] args)
	{
        Ticket t = askTicket();

        TicketView viewTXT = new TicketView("template/template.txt");
        TicketView viewHTML = new TicketView("print/template.html");

        Console.print(viewTXT.render(t));
        Console.print("\nSi vuole salvare in formato HTML? (S/N): ");
        if (Console.readString().equalsIgnoreCase("S")) {
            String filenameHTML = "print/" + t.id + "_biglietto" + ".html";
            FileWriter.writeTo(filenameHTML, viewHTML.render(t));
            Console.print("File HTML salvato: " + filenameHTML + "\n");
        }
        else
            Console.print("Salvataggio annullato.\n");
	}

	/**
	 * Acquisisce i dati di un biglietto dall'utente con validazione.
	 * 
	 * La funzione richiede all'utente di inserire:
	 * - ID del biglietto (0-1000)
	 * - Chilometri della tratta (1-1000)
	 * - Livello di classe (1=prima, 2=seconda)
	 * 
	 * Il ciclo continua finché i dati inseriti non superano
	 * la validazione del metodo isValid().
	 * 
	 * @return Un oggetto Ticket validato con i dati inseriti dall'utente
	 */
	private static Ticket askTicket() 
	{
        Ticket res = new Ticket();
        do
        {
            res.id = Console.readIntBetween("Inserire id: ", "Id non valido", 0, 1000);
            res.km = Console.readIntBetween("Inserire km: ", "Tratta troppo lunga", 1, 1000);
            res.level = Console.readIntBetween("Inserire livello (1=prima classe, 2=seconda classe): ", "Livello non valido", 1, 2);  
            if(!res.isValid()) {
                Console.print("Dati non validi! Riprova.\n");
            }

        } while(!res.isValid());
        return res;			
	}

}
