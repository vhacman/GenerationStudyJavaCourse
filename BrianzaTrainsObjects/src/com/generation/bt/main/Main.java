package com.generation.bt.main;

import com.generation.bt.view.TicketView;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.model.entities.Ticket;

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
        // ========== ACQUISIZIONE DATI ==========
        // Chiedo all'utente di inserire i dati del biglietto
        Ticket t = askTicket();

        // ========== CREAZIONE VIEW ==========
        // Creo due view: una per il formato testo e una per HTML
        TicketView viewTXT = new TicketView("template/template.txt");
        TicketView viewHTML = new TicketView("print/template.html");

        // ========== ANTEPRIMA TXT ==========
        // Mostro l'anteprima solo in formato testo (leggibile in console)
        Console.print("\n=== ANTEPRIMA BIGLIETTO ===\n");
        Console.print(viewTXT.render(t));

        // ========== SALVATAGGIO HTML ==========
        // Chiedo se salvare in formato HTML (senza mostrare il codice HTML in console)
        Console.print("\nSi vuole salvare in formato HTML? (S/N): ");

        if (Console.readString().equalsIgnoreCase("S"))
        {
            // Costruisco il nome del file usando l'ID del biglietto
            String filenameHTML = "print/" + t.id + ".html";
            FileWriter.writeTo(filenameHTML, viewHTML.render(t));
            Console.print("✅ File HTML salvato: " + filenameHTML + "\n");
            Console.print("   Apri il file nel browser per visualizzarlo.\n");
        }
        else
        {
            Console.print("Salvataggio annullato.\n");
        }
        
        // ========== CHIUSURA ==========
        Console.print("\nGrazie per aver usato Brianza Trains!\n");
    }

    /**
     * Chiede all'utente di inserire i dati per creare un biglietto valido.
     * Il metodo continua a richiedere i dati finché l'utente non inserisce
     * valori corretti (km > 0 e level = 1 o 2).
     *
     * @return Un oggetto Ticket con dati validi inseriti dall'utente
     */
    private static Ticket askTicket()
    {
        // ========== INIZIALIZZAZIONE ==========
        Ticket res = new Ticket();

        // ========== CICLO DI ACQUISIZIONE DATI ==========
        // Continuo a chiedere i dati finché non sono validi
        do
        {
            Console.print("Inserire id: ");
            res.id = Console.readInt();  // Leggo l'id e lo assegno all'oggetto res
            Console.print("Inserire km: ");
            res.km = Console.readInt();  // Leggo i km e li assegno all'oggetto res
            Console.print("Inserire livello (1=prima classe, 2=seconda classe): ");
            res.level = Console.readInt();  // Leggo il livello e lo assegno all'oggetto res

            // ========== VALIDAZIONE ==========
            // Verifico se i dati inseriti sono validi usando il metodo isValid()
            // Il metodo isValid() è stato scritto UNA volta nella classe Ticket
            // e lo sto richiamando DUE volte su res (qui nell'if e nella condizione del while)
            // res è una variabile di tipo Ticket, quindi può chiamare i metodi di Ticket
            if(!res.isValid()) {
                Console.print("Dati non validi! Riprova.\n");
            }

        } while(!res.isValid());  // Ripeto il ciclo finché i dati non sono validi

        // ========== RESTITUZIONE ==========
        // Esco dal ciclo solo quando res.isValid() restituisce true
        // A questo punto il biglietto contiene dati validi e può essere restituito
        return res;
    }
}