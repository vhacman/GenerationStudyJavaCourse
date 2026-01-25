package com.generation.acmc.view;

import java.util.List;
import com.generation.acmc.model.entities.Member;
import com.generation.library.Template;

/**
 * ViewFactory: classe Factory che ho creato per centralizzare la gestione delle view.
 *
 * PROBLEMA che risolvevo:
 * - Nel mio ViewController, ogni volta che renderizzavo chiamavo Template.load()
 * - Questo significa leggere il file dal disco OGNI VOLTA
 * - Inefficiente quando renderizzo molte entità (es. 100 membri Gray)
 *
 * SOLUZIONE con Factory Pattern + Singleton-like:
 * - Carico tutti i template UNA VOLTA SOLA come campi statici
 * - Le istanze di ReflectionView vengono create all'avvio dell'applicazione
 * - Quando serve una view, la recupero già pronta 
 *
 * PATTERN USATI:
 * - Factory Pattern: il metodo make() decide quale view restituire
 * - Lazy Initialization (via static): le view vengono create solo quando la classe viene caricata
 * - Registry Pattern: tengo un "registro" di view già pronte
 *
 * ISPIRAZIONE:
 * - GuestViewFactory.java del corso (pattern base)
 * - Adattato per le mie entità ACMC (Member, Donation, Expense)
 *
 * COME LO USO:
 * - View semplici: ViewFactory.getMemberDetailTxt().render(member)
 * - View dinamiche: uso ancora ViewController (es. promotion card con parametri)
 * - Liste: ViewFactory.renderGrayMembersListHTML(members)
 */
public class ViewFactory
{
    // MEMBER VIEWS 
    // Pre-carico tutte le view Member all'avvio (static initialization)
    // Performance: template letto dal disco UNA VOLTA SOLA
    // TXT views per Member
    static ReflectionView   memberDetailTxt           				= new ReflectionView("template/txt/member_detail.txt");
    static ReflectionView   memberPromotionCardTxt    	= new ReflectionView("template/txt/member_promotion_card.txt");
    static ReflectionView   grayMemberRowTxt          			= new ReflectionView("template/txt/gray_member_row.txt");
    // HTML views per Member
    static ReflectionView   memberWelcomeCardHtml     	= new ReflectionView("template/html/member_card_welcome.html");
    static ReflectionView   memberPromotionCardHtml   	= new ReflectionView("template/html/member_promotion_card.html");
    static ReflectionView   grayMemberRowHtml        			= new ReflectionView("template/html/gray_member_row.html");
    // DONATION VIEWS 
    // TXT views per Donation
    static ReflectionView   donationReceiptTxt        				= new ReflectionView("template/txt/donation_receipt.txt");
    // EXPENSE VIEWS
    // TXT views per Expense
    static ReflectionView   expenseReportTxt          				= new ReflectionView("template/txt/expense_report.txt");

       /**
     * Factory method: decide quale view restituire in base ai parametri.
     *
     * FACTORY PATTERN:
     * - Centralizzo la logica di creazione degli oggetti
     * - Il chiamante chiede "dammi una view per X" senza sapere quale istanza specifica riceverà
     * - Facilita la manutenzione: se cambio i template, modifico solo qui
     *
     * MA È PIÙ LEGGIBILE:
     * ViewFactory.getMemberWelcomeCardHtml().render(member)
     *
     * @param entity tipo di entità ("member", "donation", "expense")
     * @param format formato di output ("txt", "html")
     * @param purpose scopo della view ("detail", "welcome", "promotion", "receipt", "report", "row")
     * @return l'istanza di ReflectionView appropriata
     * @throws IllegalArgumentException se la combinazione non è supportata
     */
    public static ReflectionView make(String entity, String format, String purpose)
    {
        // MEMBER views
        if ("member".equals(entity))
        {
            if ("txt".equals(format))
            {
                switch (purpose)
                {
                    case "detail":      return memberDetailTxt;
                    case "promotion":   return memberPromotionCardTxt;
                    case "row":         return grayMemberRowTxt;
                }
            }
            if ("html".equals(format))
            {
                switch (purpose)
                {
                    case "welcome":     return memberWelcomeCardHtml;
                    case "promotion":   return memberPromotionCardHtml;
                    case "row":         return grayMemberRowHtml;
                }
            }
        }
        // DONATION views
        if ("donation".equals(entity))
        {
            if ("txt".equals(format))
            {
                switch (purpose)
                {
                    case "receipt":     return donationReceiptTxt;
                }
            }
        }
        // EXPENSE views
        if ("expense".equals(entity))
        {
            if ("txt".equals(format))
            {
                switch (purpose)
                {
                    case "report":      return expenseReportTxt;
                }
            }
        }
        // Se arrivo qui, la combinazione non è supportata
        throw new IllegalArgumentException("Combinazione entity/format/purpose non supportata: " + entity + " / " + format + " / " + purpose );
    }

    // Per liste complete con wrapper (header + rows + footer)

    /**
     * Renderizza una lista completa di membri Gray in formato TXT.
     *
     * COMPOSITE PATTERN:
     * - Combino due template: uno per le righe, uno per il wrapper
     * - Template riga: gray_member_row.txt → [id], [firstName], [lastName], [dob], [incomeEst]
     * - Template wrapper: gray_members_list.txt → [memberRows], [totalMembers]
     *
     * COME FUNZIONA:
     * 1. Uso ReflectionView (grayMemberRowTxt) per renderizzare ogni riga tramite Reflection
     * 2. Concateno tutte le righe in StringBuilder (efficiente)
     * 3. Carico il template wrapper che contiene header + [memberRows] + footer
     * 4. Sostituisco [memberRows] con le righe generate e [totalMembers] con il conteggio
     *
     * PERCHÉ NON USO ViewController:
     * - ViewController.renderGrayMembersListTXT() fa la stessa cosa MA carica il template ogni volta
     * - ViewFactory usa ReflectionView con template pre-caricati = più veloce
     *
     * USATO IN: ReportController.generateGrayMembersHTMLList()
     *
     * @param grayMembers lista di membri Gray
     * @return stringa TXT formattata con template completo
     */
    public static String renderGrayMembersListTXT(List<Member> grayMembers)
    {
        StringBuilder rows = new StringBuilder();

        // Uso ReflectionView per renderizzare ogni riga (Reflection automatica!)
        for (Member member : grayMembers)
            rows.append(grayMemberRowTxt.render(member));

        // Carico il template wrapper e sostituisco i placeholder manualmente
        // (qui la Reflection non può aiutarmi perché [memberRows] e [totalMembers]
        //  non sono getter di Member, sono dati aggregati della lista)
        return Template.load("template/txt/gray_members_list.txt")
            .replace("[memberRows]", rows.toString())
            .replace("[totalMembers]", "" + grayMembers.size());
    }

    /**
     * Renderizza una lista completa di membri Gray in formato HTML.
     * Stesso comportamento di renderGrayMembersListTXT() ma con template HTML.
     *
     * @param grayMembers lista di membri Gray
     * @return stringa HTML formattata con template completo
     */
    public static String renderGrayMembersListHTML(List<Member> grayMembers)
    {
        StringBuilder rows = new StringBuilder();

        // Uso ReflectionView per renderizzare ogni riga
        for (Member member : grayMembers)
            rows.append(grayMemberRowHtml.render(member));

        // Carico il template wrapper HTML e sostituisco i placeholder
        return Template.load("template/html/gray_members_list.html")
            .replace("[memberRows]", rows.toString())
            .replace("[totalMembers]", "" + grayMembers.size());
    }
}

