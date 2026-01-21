package com.generation.bt.service;

import com.generation.bt.model.Ticket;
import com.generation.library.Template;
import com.generation.library.FileWriter;
import com.generation.library.Console;

/**
 * TemplateService: Gestisce il caricamento, la personalizzazione e il salvataggio del template
 * RESPONSABILITÀ: Caricare il template HTML, sostituire i placeholder e salvare il file
 */
public class TemplateService
{
    private static final String TEMPLATE_PATH = "print/template.html";
    private static final String OUTPUT_DIRECTORY = "print/";


    public static String loadTemplate() {
        return Template.load(TEMPLATE_PATH);
    }

    /**
     * Sostituisce tutti i placeholder nel template con i dati della corsa (versione legacy)
     * @param template template da personalizzare
     * @param minutes minuti della corsa
     * @param level classe del taxi
     * @param hour ora di partenza
     * @param minutesStart minuti di partenza
     * @param hourArrival ora di arrivo
     * @param minutesArrival minuti di arrivo
     * @param price prezzo della corsa
     * @return template personalizzato
     */
    public static String fillTemplate(String template, int minutes, int level, int hour,
                                      int minutesStart, int hourArrival, int minutesArrival,
                                      double price) {
        return template
                .replace("[minutes]", String.valueOf(minutes))
                .replace("[level]", String.valueOf(level))
                .replace("[hour]", String.format("%02d", hour))
                .replace("[minutesStart]", String.format("%02d", minutesStart))
                .replace("[hourArrival]", String.format("%02d", hourArrival))
                .replace("[minutesArrival]", String.format("%02d", minutesArrival))
                .replace("[price]", String.format("%.2f", price));
    }

    /**
     * Sostituisce tutti i placeholder usando un Ticket e User (versione avanzata)
     * @param template template da personalizzare
     * @param ticket il ticket con tutti i dati della corsa
     * @param user l'utente (può essere null)
     * @return template personalizzato
     */
    public static String fillTemplateWithTicket(String template, Ticket ticket, User user) {
        String result = template
                // Dati temporali
                .replace("[ticketId]", ticket.getTicketId())
                .replace("[date]", ticket.getFormattedCreatedAt())
                .replace("[departureTime]", ticket.getFormattedDepartureTime())
                .replace("[arrivalTime]", ticket.getFormattedArrivalTime())
                .replace("[minutes]", String.valueOf(ticket.getTotalMinutes()))
                .replace("[hour]", String.format("%02d", ticket.getDepartureHour()))
                .replace("[minutesStart]", String.format("%02d", ticket.getDepartureMinutes()))
                .replace("[hourArrival]", String.format("%02d", ticket.getArrivalHour()))
                .replace("[minutesArrival]", String.format("%02d", ticket.getArrivalMinutes()))

                // Dati tariffari
                .replace("[level]", String.valueOf(ticket.getServiceLevel()))
                .replace("[serviceLevelName]", ticket.getServiceLevelName())
                .replace("[basePrice]", String.format("%.2f", ticket.getBasePrice()))
                .replace("[nightSurcharge]", String.format("%.2f", ticket.getNightSurcharge()))
                .replace("[discount]", String.format("%.2f", ticket.getDiscountAmount()))
                .replace("[price]", String.format("%.2f", ticket.getFinalPrice()))
                .replace("[finalPrice]", String.format("%.2f", ticket.getFinalPrice()));

        // Dati utente (se presente)
        if (user != null) {
            result = result
                    .replace("[userName]", user.getFullName())
                    .replace("[userPhone]", user.getPhoneNumber() != null ? user.getPhoneNumber() : "N/A")
                    .replace("[userEmail]", user.getEmail() != null ? user.getEmail() : "N/A")
                    .replace("[userAddress]", user.getAddress() != null ? user.getAddress() : "N/A")
                    .replace("[userCity]", user.getCity() != null ? user.getCity() : "N/A")
                    .replace("[userZipCode]", user.getZipCode() != null ? user.getZipCode() : "N/A")
                    .replace("[userFiscalCode]", user.getFiscalCode() != null ? user.getFiscalCode() : "N/A")
                    .replace("[vipStatus]", user.isVip() ? "VIP" : "Standard")
                    .replace("[ridesCount]", String.valueOf(user.getRidesCount()))
                    .replace("[totalSpent]", String.format("%.2f", user.getTotalSpent()));
        } else {
            // Se non c'è utente, rimuove i placeholder
            result = result
                    .replace("[userName]", "Cliente")
                    .replace("[userPhone]", "N/A")
                    .replace("[userEmail]", "N/A")
                    .replace("[userAddress]", "N/A")
                    .replace("[userCity]", "N/A")
                    .replace("[userZipCode]", "N/A")
                    .replace("[userFiscalCode]", "N/A")
                    .replace("[vipStatus]", "N/A")
                    .replace("[ridesCount]", "0")
                    .replace("[totalSpent]", "0.00");
        }

        return result;
    }

    /**
     * Salva il template personalizzato in un file HTML
     * @param filename nome del file di output
     * @param content contenuto da salvare
     */
    public static void saveReceipt(String filename, String content)
    {
        FileWriter.writeTo(OUTPUT_DIRECTORY + filename, content);
        Console.print("L'etichetta è stata salvata nel file " + filename + " nella cartella " + OUTPUT_DIRECTORY);
    }
}