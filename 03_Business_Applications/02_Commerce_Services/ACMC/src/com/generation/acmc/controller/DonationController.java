package com.generation.acmc.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Donation;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.repository.DonationRepository;
import com.generation.acmc.model.repository.SQLDonationRepository;
import com.generation.library.Console;
import com.generation.library.Template;

/**
 * Controller per la gestione delle donazioni
 * Gestisce tutte le operazioni CRUD e le query specifiche per le donazioni
 */
public class DonationController
{
    // Inietto la dipendenza tramite Context
    // In questo modo il controller non è accoppiato all'implementazione specifica del repo
    private static DonationRepository donationRepo = Context.getDependency(SQLDonationRepository.class);

    /**
     * Menu principale per la gestione delle donazioni
     * Loop classico con flag booleano per controllare l'uscita
     */
    public static void manageDonationsMenu()
    {
        boolean back = false;
        while (!back)
        {
            displayDonationsMenu();
            Console.print("Scegli un'opzione: ");
            String choice = Console.readString();
            switch (choice)
            {
                case "1":   registerNewDonation();             break;
                case "2":   searchDonationsByPeriod();         break;
                case "3":   searchDonationsByMember();         break;
                case "4":   searchDonationsByMemberLastYear(); break;
                case "0":   back = true;                       break;
                default:    Console.print("Opzione non valida\n");
            }
        }
    }

    /**
     * Carica e mostra il template del menu da file
     * Separazione tra logica e presentazione - il menu è in un file esterno
     */
    private static void displayDonationsMenu()
    {
        String menu = Template.load("template/menu/donations_menu.txt");
        if (menu == null || menu.isEmpty())
            Console.print("Menu non trovato");
        else
            Console.print(menu);
    }

    /**
     * Registra una nuova donazione nel sistema
     * Flow: validazione socio -> input dati -> validazione entity -> insert DB
     */
    private static void registerNewDonation()
    {
        try
        {
            // Step 1: Verifico che il socio esista prima di procedere
            Console.print("Inserisci l'ID del socio donatore: ");
            int memberId = Console.readInt();
            Member member = MemberController.findMemberById(memberId);
            if (member == null)
            {
                Console.print("Errore: socio non trovato.\n");
                return; // Early return - evito nesting eccessivo
            }
            Console.print("Socio: " + member.getFirstName() + " " + member.getLastName() + "\n");            
            // Step 2: Creo l'oggetto Donation e popolo i campi
            Donation donation = new Donation();
            donation.setMember(member);            
            // InputValidator personalizzato - valida che l'importo sia >= 0.01
            donation.setAmount(InputValidator.readValidNumeric("Inserire importo donazione: ","Importo non valido.", new BigDecimal("0.01")));            
            // Step 3: Gestione data - se vuota uso data odierna
            Console.print("Inserire data della donazione (YYYY-MM-DD) [invio per data odierna]: ");
            String dateInput = Console.readString();            
            if (dateInput != null && !dateInput.trim().isEmpty())
            {
                String validDate = InputValidator.readValidDateOptional(dateInput);
                if (validDate != null)
                    donation.setDate(validDate);
                else
                {
                    // Fallback a oggi se il formato è sbagliato
                    Console.print("Formato data non valido. Uso data odierna.\n");
                    donation.setDate(LocalDate.now());
                }
            }
            else
                donation.setDate(LocalDate.now());         
            // Step 4: Note opzionali - gestisco stringa vuota vs null
            Console.print("Inserire note (opzionale): ");
            String notes = Console.readString();
            donation.setNotes(notes != null && !notes.trim().isEmpty() ? notes : "");            
            // Step 5: Validazione dell'entity - pattern Validation in Entity
            // L'oggetto Donation conosce le proprie regole di validazione
            List<String> errors = donation.getErrors();
            if (!errors.isEmpty())
            {
                Console.print("Errori di validazione:\n");                
                errors.forEach(error -> Console.print("- " + error + "\n"));
                return;
            }            
            // Step 6: Insert nel DB tramite repository
            Donation insertedDonation = donationRepo.insert(donation);
            
            // Feedback all'utente
            Console.print("Donazione ID " + insertedDonation.getId() + " inserita con successo!\n");
            Console.print("Importo: " + insertedDonation.getAmount() + "\n");
            Console.print("Data: " + insertedDonation.getDate() + "\n");
            Console.print("Note: " + insertedDonation.getNotes() + "\n");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    /**
     * Ricerca donazioni in un intervallo di date
     * Query con due parametri: startDate e endDate
     */
    private static void searchDonationsByPeriod()
    {
        // Input delle date
        Console.print("Inserisci data inizio (YYYY-MM-DD): ");
        String startStr = Console.readString();
        Console.print("Inserisci data fine (YYYY-MM-DD): ");
        String endStr = Console.readString();        
        // Validazione preliminare - controllo che non siano vuote
        if (startStr == null || endStr == null || startStr.trim().isEmpty() || endStr.trim().isEmpty())
        {
            Console.print("Date obbligatorie. Operazione annullata.\n");
            return;
        }        
        try
        {
            // Parsing delle date - può lanciare DateTimeParseException
            LocalDate start = LocalDate.parse(startStr.trim());
            LocalDate end   = LocalDate.parse(endStr.trim());            
            // Validazione logica - inizio deve precedere fine
            if (start.isAfter(end))
            {
                Console.print("Data inizio deve essere prima di data fine.\n");
                return;
            }            
            // Query al repository - il WHERE è gestito nel repo
            List<Donation> donations = donationRepo.findDateBetween(start, end);            
            if (donations.isEmpty())
                Console.print("Nessuna donazione trovata.\n");
            else
            {
                Console.print("Donazioni trovate:\n");                
                // Method reference :: - più pulito della lambda esplicita
                // TEORIA: Il :: è compilato più efficiente della lambda, anche se la differenza
                // è minima. È preferibile per leggibilità quando ho già un metodo pronto.
                // Equivale a: donations.forEach(d -> DonationController.printDonationWithMember(d))
                donations.forEach(DonationController::printDonationWithMember);                
                // Calcolo totale - uso for classico perché BigDecimal è immutabile
                // TEORIA: BigDecimal.add() non modifica l'oggetto ma restituisce un nuovo oggetto
                // Devo riassegnare total ogni volta: total = total.add(...)
                // Non posso fare total += perché BigDecimal non supporta operatori
                BigDecimal total = BigDecimal.ZERO; // Costante predefinita - meglio di new BigDecimal(0)
                for (Donation donation : donations)
                    total = total.add(donation.getAmount());                
                Console.print("Totale: " + total + ", Numero donazioni: " + donations.size() + "\n");
            }
        }
        catch (DateTimeParseException e)
        {
            Console.print("Formato data non valido. Usa YYYY-MM-DD.\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
        catch (Exception e)
        {
            // Catch generico per sicurezza - non voglio che il programma crashi
            Console.print("Errore: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Ricerca tutte le donazioni di un socio specifico
     * Query filtrata per memberId
     */
    private static void searchDonationsByMember()
    {
        try
        {
            // Input e validazione socio
            Console.print("Inserisci l'ID del socio: ");
            int memberId = Console.readInt();
            Member member = MemberController.findMemberById(memberId);            
            if (member == null)
            {
                Console.print("Errore: socio non trovato.\n");
                return;
            }            
            // Query al repository
            List<Donation> donations = donationRepo.findByMemberId(memberId);            
            if (donations.isEmpty())
            {
                Console.print("Nessuna donazione trovata per questo socio.\n");
                return;
            }            
            Console.print("Donazioni per il socio ID " + memberId + " (" + member.getFirstName() + " " + member.getLastName() +")\n");
            // La JVM non deve creare un metodo sintetico per la lambda
            // Equivale a: for (Donation d : donations) { printDonationSimple(d); }
            donations.forEach(DonationController::printDonationSimple);      
            BigDecimal total = BigDecimal.ZERO;
            for (Donation donation : donations)
                total = total.add(donation.getAmount());                
            Console.print("Totale donato: " + total + "\n");
            Console.print("Numero di donazioni: " + donations.size() + "\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    /**
     * Ricerca donazioni di un socio nell'ultimo anno
     * Usa una query specifica con filtro temporale nel repository
     */
    private static void searchDonationsByMemberLastYear()
    {
        try
        {
            Console.print("Inserisci l'ID del socio: ");
            int memberId = Console.readInt();
            Member member = MemberController.findMemberById(memberId);            
            if (member == null)
            {
                Console.print("Errore: socio non trovato.\n");
                return;
            }            
            List<Donation> donations = donationRepo.findByMemberIdLastYear(memberId);            
            if (donations.isEmpty())
            {
                Console.print("Nessuna donazione nell'ultimo anno.\n");
                return;
            }
            Console.print("Donazioni nell'ultimo anno per il socio ID " + memberId + " (" +
                    member.getFirstName() + " " + member.getLastName() + ")\n");       
            donations.forEach(DonationController::printDonationSimple);                   
            BigDecimal total = BigDecimal.ZERO;
            for (Donation donation : donations)
                total = total.add(donation.getAmount());
            
            Console.print("Totale donato nell'ultimo anno: " + total + "\n");
            Console.print("Numero di donazioni nell'ultimo anno: " + donations.size() + "\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    /**
     * Utility method - stampa donazione con info complete del socio
     * Usato quando il contesto non ha già le info del socio (es. ricerca per periodo)
     */
    private static void printDonationWithMember(Donation d)
    {
        Console.print("- Data: " + d.getDate() + 
                      ", Socio: " + d.getMember().getFirstName() + " " + d.getMember().getLastName() + 
                      ", Importo: " + d.getAmount() + 
                      ", Note: " + d.getNotes() + "\n");
    }

    /**
     * Utility method - stampa donazione in formato semplificato
     * Usato quando il contesto ha già le info del socio (es. ricerca per socio)
     */
    private static void printDonationSimple(Donation d)
    {
        Console.print("- ID: " + d.getId() + 
                      ", Data: " + d.getDate() + 
                      ", Importo: " + d.getAmount().doubleValue() + 
                      ", Note: " + d.getNotes() + "\n");
    }

    /**
     * Metodo pubblico per ottenere tutte le donazioni
     * Esposto per essere chiamato da altri controller
     * Delego completamente al repository - il controller è solo un intermediario
     */
    public static List<Donation> findAllDonations() throws SQLException
    {
        return donationRepo.findAll();
    }
}
