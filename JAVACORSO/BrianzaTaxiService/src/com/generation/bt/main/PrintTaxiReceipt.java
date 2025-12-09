package com.generation.bt.main;

import com.generation.bt.model.Ticket;
import com.generation.bt.service.InputService;
import com.generation.bt.service.PriceService;
import com.generation.bt.service.TemplateService;
import com.generation.bt.service.User;
import com.generation.bt.service.UserService;
import com.generation.library.Console;

/**
 * PrintTaxiReceipt: Main della applicazione
 * RESPONSABILITÀ: Orchestrare il flusso dell'applicazione coordinando i vari service
 *
 * Flusso migliorato:
 * 1. Raccoglie input utente (opzionale)
 * 2. Raccoglie dati della corsa
 * 3. Crea un Ticket
 * 4. Calcola il prezzo con eventuali sconti VIP
 * 5. Registra la corsa per l'utente
 * 6. Genera e salva la ricevuta HTML
 */
public class PrintTaxiReceipt
{
    public static void main(String[] args)
    {
        Console.print("=== BRIANZA TAXI SERVICE ===\n");

        // STEP 1: GESTIONE UTENTE (opzionale)
        User user = null;
        if (InputService.askCreateUser()) {
            Console.print("\n--- DATI UTENTE ---");
            String name = InputService.askUserName();
            String surname = InputService.askUserSurname();
            String phone = InputService.askPhoneNumber();

            // Verifica se si vogliono inserire dati completi
            if (InputService.askFullUserData()) {
                String fiscalCode = InputService.askFiscalCode();
                String email = InputService.askEmail();
                String address = InputService.askAddress();
                String city = InputService.askCity();
                String zipCode = InputService.askZipCode();
                user = UserService.createUser(name, surname, fiscalCode, phone, email, address, city, zipCode);
            } else {
                user = UserService.createUser(name, surname, phone);
            }
            Console.print("");
        }

        // STEP 2: RACCOLTA DATI CORSA
        Console.print("--- DATI CORSA ---");
        int	minutes = InputService.askMinutes();
        int	level = InputService.askLevel();
        int	hour = InputService.askHour();
        int minutesStart = InputService.askMinutesStart();
        int hourArrival = InputService.askHourArrival();
        int minutesArrival = InputService.askMinutesArrival();

        // STEP 3: CREAZIONE TICKET
        Ticket ticket = new Ticket(hour, minutesStart, hourArrival, minutesArrival, minutes, level);

        // STEP 4: CALCOLO PREZZO CON SCONTI VIP
        double finalPrice = PriceService.calculatePriceWithTicket(ticket, user);
        PriceService.printTicketDetails(ticket);

        // STEP 5: REGISTRAZIONE CORSA PER UTENTE
        if (user != null) {
            UserService.registerRide(user, finalPrice);
            UserService.printUserStats(user);
        }

        // STEP 6: GESTIONE TEMPLATE E SALVATAGGIO
        Console.print("--- SALVATAGGIO RICEVUTA ---");
        String template = TemplateService.loadTemplate();
        String filledTemplate = TemplateService.fillTemplateWithTicket(template, ticket, user);

        String outputFileName = InputService.askOutputFileName();
        TemplateService.saveReceipt(outputFileName, filledTemplate);

        Console.print("\n=== OPERAZIONE COMPLETATA ===");
        Console.print("Ticket ID: " + ticket.getTicketId());
        if (user != null) {
            Console.print("Cliente: " + user.getFullName() + " (" + (user.isVip() ? "VIP" : "Standard") + ")");
        }
        Console.print("Importo totale: €" + String.format("%.2f", finalPrice));
    }
}