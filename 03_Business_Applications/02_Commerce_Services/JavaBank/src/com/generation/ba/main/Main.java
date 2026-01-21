package com.generation.ba.main;

import com.generation.ba.model.entities.BankAccount;
import com.generation.ba.model.entities.Client;
import com.generation.ba.service.UserInputService;
import com.generation.ba.view.BankView;
import com.generation.library.Console;
import com.generation.library.FileWriter;

//TODO IMPLEMENTAZIONE ACCOUNTSERVICE IN MAIN.JAVA
//
//1. IMPORT E DICHIARAZIONI
// - Aggiungere import: com.generation.ba.service.AccountService
// - RIMUOVERE la dichiarazione: private static BankAccount account = null;
//
//2. METODO insertNewClient()
// - Dopo aver creato l'istanza BankAccount e impostato tutti i dati (id, client, balance)
// - Chiamare: AccountService.addAccount(account);
// - Aggiungere stampa finale: Console.print("Totale clienti registrati: " + AccountService.getAccountCount() + "\n");
//
//3. METODO displayClient()
// - Cambiare controllo iniziale da "if (account == null)" a "if (AccountService.isEmpty())"
// - Cambiare titolo da "VISUALIZZAZIONE CLIENTE" a "VISUALIZZAZIONE CLIENTI"
// - SOSTITUIRE Console.print(viewTXT.render(account)); con un ciclo for:
//   for (int i = 0; i < AccountService.getAllAccounts().size(); i++) {
//       BankAccount account = AccountService.getAllAccounts().get(i);
//       Console.print("\n--- Cliente #" + (i + 1) + " ---");
//       Console.print(viewTXT.render(account));
//   }
// - Cambiare domanda HTML in "Vuoi salvare tutti i clienti in formato HTML?"
// - SOSTITUIRE il salvataggio singolo con un ciclo for-each:
//   for (BankAccount account : AccountService.getAllAccounts()) {
//       String filenameHTML = "print/" + account.getId() + "_conto.html";
//       FileWriter.writeTo(filenameHTML, viewHTML.render(account));
//       Console.print("✓ File HTML salvato: " + filenameHTML);
//   }
//
//4. METODO showBalance()
// - Cambiare controllo da "if (account == null)" a "if (AccountService.getCurrentAccount() == null)"
// - Cambiare messaggio errore in "Nessun cliente selezionato"
// - Aggiungere SUBITO DOPO il controllo: BankAccount account = AccountService.getCurrentAccount();
// - Il resto del codice rimane uguale (usa la variabile locale account)
//
//5. METODO makeDeposit()
// - Cambiare controllo da "if (account == null)" a "if (AccountService.getCurrentAccount() == null)"
// - Cambiare messaggio errore in "Nessun cliente selezionato. Selezionare prima un cliente."
// - Aggiungere SUBITO DOPO il controllo: BankAccount account = AccountService.getCurrentAccount();
// - Il resto del codice rimane uguale (usa la variabile locale account)
//
//6. METODO makeWithdrawal()
// - Cambiare controllo da "if (account == null)" a "if (AccountService.getCurrentAccount() == null)"
// - Cambiare messaggio errore in "Nessun cliente selezionato. Selezionare prima un cliente."
// - Aggiungere SUBITO DOPO il controllo: BankAccount account = AccountService.getCurrentAccount();
// - Il resto del codice rimane uguale (usa la variabile locale account)
//
//7. METODI main() e internalMenu()
// - NON richiedono modifiche, rimangono identici

public class Main
{
    private static BankAccount account = null;

    public static void main(String[] args)
    {
        boolean running = true;
        
        while (running)
        {
            int choice = BankView.showMainMenu();
            
            switch (choice)
            {
                case 1:
                    insertNewClient();
                    break;     
                case 2:
                    if (displayClient()) {
                        internalMenu();
                    }
                    break;
                case 3:
                    makeDeposit();
                    break;
                case 4:
                    makeWithdrawal();
                    break;
                case 5:
                    Console.print("\nGrazie per aver usato il sistema bancario. Arrivederci!");
                    running = false;
                    break;
                default:
                    Console.print("Opzione non valida!");
            }
        }
    }

    /**
     * Menu interno per operazioni dopo visualizzazione cliente
     */
    public static void internalMenu()
    {
        boolean inInternal = true;
        
        while (inInternal)
        {
            int choice = BankView.showInternalMenu();
            
            switch (choice)
            {
                case 1:
                    makeDeposit();
                    break;
                case 2:
                    makeWithdrawal();
                    break;
                case 3:
                    showBalance();
                    break;
                case 4:
                    Console.print("\nTornando al menu principale...\n");
                    inInternal = false;
                    break;
                default:
                    Console.print("Opzione non valida!");
            }
        }
    }

    /**
     * Inserisce un nuovo cliente e crea il conto bancario
     */
    public static void insertNewClient()
    {
        Console.print("\n=== INSERIMENTO NUOVO CLIENTE ===\n");
        
        Client client = new Client();
        account = new BankAccount();

        account.setId(UserInputService.requestId());
        client.setName(UserInputService.requestName());
        client.setSurname(UserInputService.requestSurname());
        client.setDob(UserInputService.requestDateOfBirth());
        client.setSsn(UserInputService.requestValidSSN());
        account.setClient(client);
        account.setBalance(UserInputService.requestInitialBalance());

        Console.print("\n✓ Cliente inserito con successo!");
        Console.print("Numero conto: " + account.getId());
        Console.print("Saldo iniziale: € " + account.getBalance() + "\n");
    }

    /**
     * Visualizza le informazioni del cliente e offre salvataggio HTML
     * @return true se il cliente esiste, false altrimenti
     */
    public static boolean displayClient()
    {
    	
        if (account == null)
        {
            Console.print("\n⚠ Nessun cliente presente. Inserire prima un cliente.\n");
            return false;
        }

        Console.print("\n=== VISUALIZZAZIONE CLIENTE ===\n");
        
        BankView viewTXT = new BankView("template/template.txt");
        BankView viewHTML = new BankView("template/template.html");

        Console.print(viewTXT.render(account));
        
        Console.print("\nVuoi salvare in formato HTML? (S/N): ");
        if (Console.readString().equalsIgnoreCase("S"))
        {
            String filenameHTML = "print/" + account.getId() + "_conto.html";
            FileWriter.writeTo(filenameHTML, viewHTML.render(account));
            Console.print("✓ File HTML salvato: " + filenameHTML + "\n");
        } else {
            Console.print("Salvataggio annullato.\n");
        }
        
        return true;
    }

    /**
     * Visualizza solo il balance del conto
     */
    public static void showBalance()
    {
        if (account == null)
        {
            Console.print("\n⚠ Nessun cliente presente.\n");
            return;
        }

        Console.print("\n=== SALDO CORRENTE ===");
        Console.print("Numero conto: " + account.getId());
        Console.print("Intestatario: " + account.getClient().getName() + " " + account.getClient().getSurname());
        Console.print("Saldo: € " + account.getBalance() + "\n");
    }

    /**
     * Effettua un deposito sul conto
     */
    public static void makeDeposit()
    {
        if (account == null)
        {
            Console.print("\n⚠ Nessun cliente presente. Inserire prima un cliente.\n");
            return;
        }

        Console.print("\nSaldo attuale: € " + account.getBalance());
        
        int[] depositAmount = UserInputService.requestAmount("DEPOSITO");
        account.deposit(depositAmount[0], depositAmount[1]);
        
        Console.print("✓ Deposito effettuato con successo!");
        Console.print("Nuovo saldo: € " + account.getBalance() + "\n");
    }

    /**
     * Effettua un prelievo dal conto
     */
    public static void makeWithdrawal()
    {
        if (account == null)
        {
            Console.print("\n⚠ Nessun cliente presente. Inserire prima un cliente.\n");
            return;
        }

        Console.print("\nSaldo attuale: € " + account.getBalance());
        
        int[] withdrawAmount = UserInputService.requestAmount("PRELIEVO");
        account.withdrawl(withdrawAmount[0], withdrawAmount[1]);
        
        Console.print("✓ Prelievo effettuato con successo!");
        Console.print("Nuovo saldo: € " + account.getBalance() + "\n");
    }
}