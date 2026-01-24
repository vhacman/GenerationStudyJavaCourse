package com.generation.acmc.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.acmc.view.ViewController;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;
import com.generation.library.view.EntityView;

/**
 * Controller dedicato alla gestione di report e stampe
 * Gestisce la generazione di report HTML, schede soci e carte di benvenuto/promozione
 * 
 * - Ho estratto metodi helper privati per eliminare duplicazione
 * - Ogni metodo pubblico è più leggibile e testabile
 */
public class ReportController
{
    public static void manageReportsMenu()
    {
        boolean back = false;
        
        while (!back)
        {
            displayReportsMenu();
            Console.print("Scegli un'opzione: ");
            String choice = Console.readString();
            
            switch (choice)
            {
                case "1": generateGrayMembersHTMLList();    break;
                case "2": generateMemberProfile();          break;
                case "3": printWelcomeCard();               break; 
                case "4": printPromotionCard();             break; 
                case "0": back = true;                      break;
                default:  Console.print("Opzione non valida\n");
            }
        }
    }

    /**
     * Carica template menu report da file esterno
     */
    private static void displayReportsMenu()
    {
        String menu = Template.load("template/menu/reports_menu.txt");
        
        if (menu == null || menu.isEmpty())
            Console.print("Menu non trovato");
        else
            Console.print(menu);
    }

    // GENERAZIONE REPORT  
    /**
     * Produce un elenco con tutti i soci di livello Gray e lo stampa in HTML
     * Use case: lista per evento speciale "Grande Raduno Spaziale Area 51"
     */
    private static void generateGrayMembersHTMLList()
    {
        try 
        {
            List<Member> 		grayMembers = MemberController.findMembersByLevel(MembershipLevel.GRAY);            
            if (grayMembers.isEmpty())
            {
                Console.print("Nessun socio Gray trovato.\n");
                return;
            }
            String txtOutput = ViewController.renderGrayMembersListTXT(grayMembers);
            Console.print(txtOutput);
            Console.print("\nSalvare report in formato TXT? (S/N): ");
            String 					saveTxt = Console.readString();            
            if (saveTxt.equalsIgnoreCase("S")) 
            {
                String 				filenameTxt = "print/txt/gray_members_" + LocalDate.now() + ".txt";
                FileWriter.writeTo(filenameTxt, txtOutput);
                Console.print("File TXT salvato: " + filenameTxt + "\n");
            }
            Console.print("\nSalvare report in formato HTML? (S/N): ");
            String 					saveHtml = Console.readString();            
            if (saveHtml.equalsIgnoreCase("S")) 
            {
                String					 htmlOutput  = ViewController.renderGrayMembersListHTML(grayMembers);
                String 				filenameHtml = "print/html/gray_members_" + LocalDate.now() + ".html";
                FileWriter.writeTo(filenameHtml, htmlOutput);
                Console.print("File HTML salvato: " + filenameHtml + "\n");
            }            
            if (!saveTxt.equalsIgnoreCase("S") && !saveHtml.equalsIgnoreCase("S")) 
                Console.print("Nessun salvataggio effettuato.\n");
        } 
        catch (Exception e)
        {
            Console.print("Errore nella generazione del report: " + e.getMessage() + "\n");
        }
    }
    /**
     * Produce una scheda del socio con i soli dati anagrafici
     * 
     * REFACTORING: Ho estratto promptAndFindMember() come helper method
     */
    private static void 			generateMemberProfile()
    {
        try
        {
            Member member = promptAndFindMember("Inserisci l'ID del socio: ");
            if (member == null) return;            
            Console.print(ViewController.memberDetailView.render(member));
        }
        catch (SQLException e) 
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    /**
     * Stampa scheda di invito a unirsi all'associazione
     * Genera file HTML con carta di benvenuto personalizzata
     */
    private static void printWelcomeCard()
    {
        try
        {
            Member member = promptAndFindMember("Inserisci l'ID del nuovo socio: ");
            if (member == null) return;            
            // Genera l'output HTML
            String htmlOutput = ViewController.memberWelcomeCardView.render(member);  
            Console.print("\n=== Preview Carta di Benvenuto ===\n");
            Console.print("Socio: " + member.getFirstName() + " " + member.getLastName() + "\n");
            Console.print("Livello: " + member.getLevel() + "\n");
            Console.print("Codice: ACMC-" + member.getId() + "\n");
            Console.print("\nSalvare carta di benvenuto in formato HTML? (S/N): ");
            String saveHtml = Console.readString();
            if (saveHtml.equalsIgnoreCase("S")) 
            {
                String filename = "print/html/welcome_card_" + member.getId() + "_" + LocalDate.now() + ".html";
                FileWriter.writeTo(filename, htmlOutput);
                Console.print("File HTML salvato: " + filename + "\n");
            }
            else
                Console.print("Nessun salvataggio effettuato.\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
        catch (Exception e)
        {
            Console.print("Errore nella generazione della carta: " + e.getMessage() + "\n");
        }
    }

    /**
     * Stampa scheda di comunicazione di promozione
     * Flow complesso: validazione -> preview -> conferma -> salvataggio + update DB
     */
    private static void printPromotionCard()
    {
        try
        {
            Member member = promptAndFindMember("Inserisci l'ID del socio da promuovere: ");
            if (member == null) return;            
            MembershipLevel currentLevel = member.getLevel();
            if (!isPromotable(currentLevel, member)) return;            
            MembershipLevel newLevel = getNextLevel(currentLevel);        
            Console.print("\n=== Preview Promozione ===\n");
            Console.print("Socio: " + member.getFirstName() + " " + member.getLastName() + "\n");
            Console.print("Livello attuale: " + currentLevel + "\n");
            Console.print("Nuovo livello: " + newLevel + "\n");            
            if (!confirmPromotion(member, currentLevel, newLevel)) return;            
            // 4. Genera output HTML
            String htmlOutput = ViewController.renderPromotionCard(member, currentLevel, newLevel);     
            Console.print("\nSalvare carta di promozione in formato HTML? (S/N): ");
            String saveHtml = Console.readString();            
            if (saveHtml.equalsIgnoreCase("S")) 
            {
                String filename = "print/html/promotion_card_" + member.getId() + "_" + 
                                currentLevel + "_to_" + newLevel + "_" + LocalDate.now() + ".html";
                FileWriter.writeTo(filename, htmlOutput);
                Console.print("File HTML salvato: " + filename + "\n");
            }
            updateMemberLevel(member, newLevel);
            Console.print("\nPromozione completata con successo!\n");
            Console.print("  ID socio: " + member.getId() + "\n");
            Console.print("  Nuovo livello: " + newLevel + "\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
        catch (Exception e)
        {
            Console.print("Errore nella generazione della carta di promozione: " + e.getMessage() + "\n");
        }
    }

    /**
     * Aggiorna il livello del socio nel database
     */
    private static void updateMemberLevel(Member member, MembershipLevel newLevel) throws SQLException
    {
        member.setLevel(newLevel);
        MemberController.updateMember(member);
    }


    // HELPER METHODS
    /**
     * Richiede l'ID del socio e lo recupera dal database
     */
    private static Member				 promptAndFindMember(String prompt) throws SQLException
    {
        Console.print(prompt);
        int    memberId = Console.readInt();
        Member member   = MemberController.findMemberById(memberId);        
        if (member == null)
        {
            Console.print("Errore: socio non trovato.\n");
            return null;
        }        
        return member;
    }

    /**
     * Verifica se un socio è promovibile
     */
    private static boolean				 isPromotable(MembershipLevel level, Member member)
    {
        if (level == MembershipLevel.BANNED)
        {
            Console.print("Errore: il socio è bannato e non può essere promosso.\n");
            return false;
        }
        if (level == MembershipLevel.GRAY)
        {
            Console.print("Il socio è già al livello massimo (GRAY).\n");
            return false;
        }        
        return true;
    }

    /**
     * Determina il livello successivo
     * 
     * TEORIA: Incapsula la gerarchia dei livelli in un unico punto
     * Hierarchy: BRONZE -> SILVER -> GOLD -> GRAY
     * 
     * Se dovessi aggiungere un livello PLATINUM tra GOLD e GRAY,
     * modifico solo questo metodo senza toccare il resto del codice
     */
    private static MembershipLevel				 getNextLevel(MembershipLevel current)
    {
        return switch(current)
        {
            case BRONZE -> MembershipLevel.SILVER;
            case SILVER -> MembershipLevel.GOLD;
            case GOLD   -> MembershipLevel.GRAY;
            default     -> current; 
        };
    }

    /**
     * Mostra preview e richiede conferma per la promozione
     * 
     * Non sa come si renderizza la card (ViewController) né come si salva (savePromotionAndUpdate)
     */
    private static boolean confirmPromotion(Member member, MembershipLevel oldLevel, MembershipLevel newLevel)
    {
        EntityView<Member> 	promotionView 		= ViewController.createPromotionCardView(oldLevel, newLevel);
        String            						 promotionCard	 	= promotionView.render(member);
        Console.print("\n--- PREVIEW CARTA DI PROMOZIONE ---\n");
        Console.print(promotionCard);
        Console.print("\nConfermi la promozione? (S/N): ");
        String confirm = Console.readString();
        return confirm != null && confirm.trim().equalsIgnoreCase("S");
    }

    /**
     * Salva la carta di promozione e aggiorna il database
     * 
     * - Genero carta
     * - Salvo file
     * - Aggiorno Member in memoria
     * - Persisto nel DB
     */
    private static void 				savePromotionAndUpdate(Member member, MembershipLevel oldLevel, MembershipLevel newLevel) throws SQLException
    {
        EntityView<Member> 		promotionView 	= ViewController.createPromotionCardView(oldLevel, newLevel);
        String             						promotionCard 	= promotionView.render(member);
        String 									filename 				= "print/txt/promotion_card_" + member.getId() + ".txt";
        FileWriter.writeTo(filename, promotionCard);
        member.setLevel(newLevel);
        MemberController.updateMember(member);
        Console.print("\nPromozione confermata e registrata nel database!\n");
        Console.print("  Carta di promozione salvata in: " + filename + "\n");
    }
}
