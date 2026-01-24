package com.generation.acmc.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Donation;
import com.generation.acmc.model.entities.Expense;
import com.generation.acmc.model.repository.ExpenseRepository;
import com.generation.acmc.model.repository.SQLExpenseRepository;
import com.generation.library.Console;
import com.generation.library.Template;

/**
 * Controller per la gestione delle spese
 * Gestisce registrazione spese e calcolo margini di profitto dell'associazione
 */
public class ExpenseController
{
    // Dependency Injection tramite Context - disaccoppiamento dall'implementazione
    private static ExpenseRepository expenseRepo = Context.getDependency(SQLExpenseRepository.class);
    /**
     * Menu principale gestione spese
     * Loop con flag booleano - pattern standard per menu navigabili
     */
    public static void manageExpensesMenu()
    {
        boolean back = false;
        while (!back)
        {
            displayExpensesMenu();
            Console.print("Scegli un'opzione: ");
            String choice = Console.readString();
            switch (choice)
            {
                case "1":   registerNewExpense();       break;
                case "2":   calculateAnnualProfit();    break;
                case "0":   back = true;                break;
                default:    Console.print("Opzione non valida\n");
            }
        }
    }
    /**
     * Carica template menu da file esterno
     * Separazione presentazione/logica - facilita modifiche UI senza toccare codice
     */
    private static void displayExpensesMenu()
    {
        String menu = Template.load("template/menu/expenses_menu.txt");
        if (menu == null || menu.isEmpty())
            Console.print("Menu non trovato");
        else
            Console.print(menu);
    }
    /**
     * Registra una nuova spesa per l'associazione
     * Flow: input dati -> validazione -> insert DB -> feedback
     */
    private static void registerNewExpense()
    {
        Expense expense = new Expense();        
        // Input motivo spesa - campo obbligatorio
        // InputValidator gestisce retry automatico se input non valido
        expense.setReason(InputValidator.readRequiredInput( "Inserire descrizione/motivo della spesa: ", "Descrizione obbligatoria." ));
        // Input importo con validazione numerica >= 0
        // BigDecimal.ZERO come minimo - le spese non possono essere negative
        expense.setCost(InputValidator.readValidNumeric( "Inserire importo della spesa: ", "Importo non valido.",  BigDecimal.ZERO));
        Console.print("Inserire data della spesa (YYYY-MM-DD) [invio per data odierna]: ");
        String dateInput = Console.readString();
        if (dateInput != null && !dateInput.trim().isEmpty())
        {
            String validDate = InputValidator.readValidDateOptional(dateInput);
            if (validDate != null)
                expense.setDate(validDate);
            else
            {
                // Se la data è invalida, annullo l'operazione invece di usare fallback
                Console.print("Formato data non valido. Operazione annullata.\n");
                return; // Early return - esco senza insert
            }
        }
        // Se l'utente preme invio senza inserire nulla, la data resta null
        // Validazione entity - l'oggetto conosce le proprie regole
        List<String> errors = expense.getErrors();
        if (!errors.isEmpty())
        {
            Console.print("Errori di validazione:\n");
            errors.forEach(error -> Console.print("- " + error + "\n"));
            return;
        }
        try
        {
            // Insert nel DB e stampa conferma
            Expense insertedExpense = expenseRepo.insert(expense);
            printExpenseDetails(insertedExpense);
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
            // Gestione errore DB - mostro messaggio senza crash
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }
    /**
     * Stampa i dettagli di una spesa appena inserita
     * Utility method - separo logica di stampa per riusabilità
     */
    private static void printExpenseDetails(Expense expense)
    {
        Console.print("Spesa ID " + expense.getId() + " inserita!\n");
        Console.print("Descrizione: " + expense.getReason() + "\n");
        Console.print("Importo: " + expense.getCost() + "\n");
        Console.print("Data: " + (expense.getDate() != null ? expense.getDate() : "non specificata") + "\n");
    }
    /**
     * Calcola il margine di profitto annuale (donazioni - spese)
     * Questa è la funzionalità core per il bilancio dell'associazione
     */
    private static void calculateAnnualProfit()
    {
        try
        {
            Console.print("Inserisci l'anno per cui calcolare il margine: ");
            int year = Console.readInt();
            if (year < 1900 || year > 2100)
            {
                Console.print("Anno non valido.\n");
                return;
            }
            // Recupero tutti i dati - faccio il filtering in memoria
            List<Donation>  donations   = DonationController.findAllDonations();
            List<Expense>   expenses    = expenseRepo.findAll();
            // Calcolo totali per l'anno specifico
            BigDecimal totalDonations   = calculateYearlyDonations(donations, year);
            BigDecimal totalExpenses    = calculateYearlyExpenses(expenses, year);            
            // Calcolo margine: donazioni - spese
            // TEORIA: BigDecimal.subtract() restituisce nuovo oggetto, non modifica l'originale
            BigDecimal profit = totalDonations.subtract(totalExpenses);
            // Stampa report formattato
            printProfitReport(year, totalDonations, totalExpenses, profit);
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }
    /**
     * Calcola il totale delle donazioni per un anno specifico
     * Filtro in memoria usando LocalDate.getYear()
     */
    private static BigDecimal calculateYearlyDonations(List<Donation> donations, int year)
    {
        BigDecimal total = BigDecimal.ZERO;
         for (Donation donation : donations)
            if (donation.getDate().getYear() == year)
                total = total.add(donation.getAmount());
        return total;
    }

    /**
     * Calcola il totale delle spese per un anno specifico
     * Filtro in memoria con null-check (le spese possono non avere data)
     */
    private static BigDecimal calculateYearlyExpenses(List<Expense> expenses, int year)
    {
        BigDecimal total = BigDecimal.ZERO;
        
        for (Expense expense : expenses)
        {
            // Null-check necessario - expense.getDate() può essere null
            // Se è null, salto quella spesa (non viene conteggiata)
            if (expense.getDate() != null && expense.getDate().getYear() == year)
                total = total.add(expense.getCost());
        }
        return total;
    }

    /**
     * Stampa report formattato del margine di profitto annuale
     * Usa String.format per formattazione valuta con separatori migliaia
     */
    private static void printProfitReport(int year, BigDecimal totalDonations, 
                                          BigDecimal totalExpenses, BigDecimal profit)
    {
        Console.print("\n========== MARGINE ANNO " + year + " ==========\n");
        Console.print("Totale donazioni: € " 	+  totalDonations 	+ ""							);
        Console.print("Totale spese: € " 			+  totalExpenses 	+ ""							);
        Console.print("Margine: € " 					+  profit					+ ""							);

        // Alert se il margine è negativo (perdita)
        // TEORIA: BigDecimal.compareTo() confronta valori numerici ignorando la scale
        // Restituisce: -1 se minore, 0 se uguale, 1 se maggiore
        // compareTo(ZERO) < 0 equivale a "profit è negativo"
        if (profit.compareTo(BigDecimal.ZERO) < 0)
            Console.print("ATTENZIONE: Perdita registrata per l'anno " + year + "\n");
        Console.print("==========================================\n");
    }
}
