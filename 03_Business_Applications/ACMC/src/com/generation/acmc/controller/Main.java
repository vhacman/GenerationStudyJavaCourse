package com.generation.acmc.controller;

import com.generation.library.Console;
import com.generation.library.Template;

/**
 * Classe Main per la gestione dell'associazione ACMC
 * Entry point dell'applicazione - coordina i controller specializzati
 * 
 * il main è solo un orchestratore
 * Tutta la business logic è delegata ai controller, il main fa solo routing
 */
public class Main
{
    public static void main(String[] args)
    {
        boolean running = true;        
        // Loop principale dell'applicazione - menu-driven console app
        while (running)
        {
            try 
            {
                // Mostro menu principale
                displayMainMenu();            
                Console.print("Scegli un'opzione: ");
                String choice = Console.readString();            
                // Routing della scelta - delego tutto ai controller
                switch (choice)
                {
                    case "1": MemberController.manageMembersMenu();     break; // Gestione soci
                    case "2": DonationController.manageDonationsMenu(); break; // Gestione donazioni
                    case "3": ExpenseController.manageExpensesMenu();   break; // Gestione spese
                    case "4": ReportController.manageReportsMenu();     break; // Report e stampe
                    case "0": running = false;                          break; 
                    default: Console.print("Opzione non valida\n");            
                }
            }
            catch (NumberFormatException e) 
            {
                // Gestisco input numerici non validi senza crashare l'app
                Console.print("\nErrore: Input non valido. Riprova.\n\n");
            }
            catch (Exception e) 
            {
                // Safety net per qualsiasi altra eccezione non prevista
                Console.print("\nErrore imprevisto: " + e.getMessage() + "\n\n");
            }
        }        
        Console.print("Arrivederci!\n");
    }

    /**
     * Carica e mostra il menu principale da file esterno
     * 
     * PATTERN: Template Method - separo presentazione (file txt) da logica (codice Java)
     * Vantaggi:
     * - Posso modificare il menu senza ricompilare
     * - Facile internazionalizzazione (menu_it.txt, menu_en.txt, etc.)
     * - Separazione of concerns: il codice non sa come è fatto il menu
     */
    private static void displayMainMenu()
    {
        // Template.load() legge il file e restituisce String (null se errore)
        String menu = Template.load("template/menu/main_menu.txt");
        if (menu == null || menu.isEmpty())
            Console.print("Menu non trovato");
        else
            Console.print(menu); // Stampo il menu caricato
    }
}
