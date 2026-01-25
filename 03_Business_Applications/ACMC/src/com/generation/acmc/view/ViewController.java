package com.generation.acmc.view;

import java.time.LocalDate;
import java.util.List;
import com.generation.acmc.model.entities.Donation;
import com.generation.acmc.model.entities.Expense;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.library.Template;
import com.generation.library.view.EntityView;

/**
 * ViewController: centralizza la logica di presentazione con Lambda Expression.
 *
 * REFACTORING FATTO:
 * - Le view semplici (member detail, welcome card, liste) sono state MIGRATE a ViewFactory
 * - ViewFactory usa Reflection automatica = più veloce e meno codice ripetitivo
 * - Qui tengo SOLO le view che richiedono logica custom o parametri dinamici
 *
 * QUANDO USO ViewController (invece di ViewFactory):
 * View con parametri extra (es. promotion card con oldLevel, newLevel)
 * Gestione null complessa (es. donation con member nullable)
 * View inline per console log rapidi
 *
 * METODI ATTIVI:
 * - createPromotionCardView() / createPromotionCardViewHTML() → parametri dinamici
 * - renderPromotionCard() → shortcut per promotion card
 *
 * METODI COMMENTATI (sostituiti da ViewFactory):
 * - memberDetailView → ViewFactory.getMemberDetailTxt()
 * - memberWelcomeCardView → ViewFactory.getMemberWelcomeCardHtml()
 * - renderGrayMembersList...() → ViewFactory.renderGrayMembersList...()
 */
public class ViewController
{
    // METODI ATTIVI - LOGICA CUSTOM CON PARAMETRI DINAMICI

    /**
     * Crea dinamicamente una view per la promotion card HTML con parametri extra.
     *
     * PERCHÉ QUESTO METODO RESTA QUI (non migrato a ViewFactory):
     * - Richiede PARAMETRI EXTRA: oldLevel e newLevel non sono getter di Member
     * - ViewFactory con Reflection può mappare solo i getter dell'entità
     * - Qui uso una factory che crea una lambda che "cattura" oldLevel e newLevel
     *
     * COME FUNZIONA:
     * - Ricevo oldLevel e newLevel come parametri
     * - Ritorno una EntityView<Member> (lambda) che li usa nella sostituzione
     * - La lambda viene eseguita quando chiamo .render(member)
     *
     * USATO IN: ReportController.printPromotionCard()
     */
    public static EntityView<Member> createPromotionCardViewHTML(MembershipLevel oldLevel, MembershipLevel newLevel)
    {
        return member ->
            Template.load("template/html/member_promotion_card.html")
                .replace("[id]", member.getId() + "")
                .replace("[firstName]", member.getFirstName())
                .replace("[lastName]", member.getLastName())
                .replace("[oldLevel]", "" + oldLevel)      // Parametro extra catturato
                .replace("[newLevel]", "" + newLevel)      // Parametro extra catturato
                .replace("[promotionDate]", "" + LocalDate.now());
    }

    /**
     * Shortcut per renderizzare direttamente una promotion card HTML.
     * Combina createPromotionCardViewHTML() + render() in un solo metodo.
     *
     * USATO IN: ReportController.printPromotionCard()
     */
    public static String renderPromotionCard(Member member, MembershipLevel oldLevel, MembershipLevel newLevel)
    {
        return createPromotionCardViewHTML(oldLevel, newLevel).render(member);
    }

    /**
     * Crea dinamicamente una view per la promotion card TXT con parametri extra.
     * Stesso comportamento di createPromotionCardViewHTML() ma per formato TXT.
     *
     * USATO IN: ReportController.confirmPromotion(), savePromotionAndUpdate()
     */
    public static EntityView<Member> createPromotionCardView(MembershipLevel oldLevel, MembershipLevel newLevel)
    {
        return member ->
            Template.load("template/txt/member_promotion_card.txt")
                .replace("[id]", member.getId() + "")
                .replace("[firstName]", member.getFirstName())
                .replace("[lastName]", member.getLastName())
                .replace("[oldLevel]", "" + oldLevel)      // Parametro extra catturato
                .replace("[newLevel]", "" + newLevel)      // Parametro extra catturato
                .replace("[promotionDate]", "" + LocalDate.now());
    }

    // ========== METODI COMMENTATI - SOSTITUITI DA ViewFactory ==========
    // Ho migrato questi metodi a ViewFactory perché sono view semplici
    // che mappano solo getter senza logica custom.
    // ViewFactory usa Reflection automatica = migliori performance

    /*
    // Trasformo i soci in un formato testuale semplice per i log rapidi in console
    public static EntityView<Member> memberBasicView = member ->
        member.getId() + " - " + member.getFirstName() + " " + member.getLastName() +
        " [" + member.getLevel() + "]\n";
    */

    /*
    // Inietto i dati del socio nel template TXT per generare la scheda di dettaglio
    // SOSTITUITO DA: ViewFactory.getMemberDetailTxt().render(member)
    public static EntityView<Member> memberDetailView = member ->
        Template.load("template/txt/member_detail.txt")
            .replace("[id]", "" + member.getId())
            .replace("[firstName]", member.getFirstName())
            .replace("[lastName]", member.getLastName())
            .replace("[gender]", member.getGender())
            .replace("[dob]", "" + member.getDob())
            .replace("[incomeEst]", "" + member.getIncomeEst())
            .replace("[level]", "" + member.getLevel());
    */

    /*
    // Genero la card di benvenuto in HTML caricando il relativo template
    // SOSTITUITO DA: ViewFactory.getMemberWelcomeCardHtml().render(member)
    public static EntityView<Member> memberWelcomeCardView = member ->
        Template.load("template/html/member_card_welcome.html")
            .replace("[id]", member.getId() + "")
            .replace("[firstName]", member.getFirstName())
            .replace("[lastName]", member.getLastName())
            .replace("[level]", "" + member.getLevel())
            .replace("[dob]", "" + member.getDob());
    */

    /*
    // Gestisco la visualizzazione delle righe per le tabelle (sia in TXT che in HTML)
    // SOSTITUITO DA: ViewFactory (usate internamente in renderGrayMembersList...())
    public static EntityView<Member> grayMemberRowViewTXT = member ->
        Template.load("template/txt/gray_member_row.txt")
            .replace("[id]", "" + member.getId())
            .replace("[firstName]", member.getFirstName())
            .replace("[lastName]", member.getLastName())
            .replace("[dob]", "" + member.getDob())
            .replace("[incomeEst]", "" + member.getIncomeEst());

    public static EntityView<Member> grayMemberRowViewHTML = member ->
        Template.load("template/html/gray_member_row.html")
            .replace("[id]", "" + member.getId())
            .replace("[firstName]", member.getFirstName())
            .replace("[lastName]", member.getLastName())
            .replace("[dob]", "" + member.getDob())
            .replace("[incomeEst]", "" + member.getIncomeEst());
    */

    /*
    // Applico il pattern Composite: ciclo la lista e delego il rendering della riga alla rowView ricevuta
    // SOSTITUITO DA: ViewFactory.renderGrayMembersListTXT() / renderGrayMembersListHTML()
    public static String renderGrayMembersList(List<Member> grayMembers, String templatePath, EntityView<Member> rowView)
    {
        StringBuilder rows = new StringBuilder();
        for (Member member : grayMembers)
            rows.append(rowView.render(member));

        return Template.load(templatePath)
            .replace("[memberRows]", rows.toString())
            .replace("[totalMembers]", "" + grayMembers.size());
    }
    */

    /*
    // Shortcut che utilizzavo per stampare rapidamente le liste Gray in TXT o HTML
    // SOSTITUITO DA: ViewFactory.renderGrayMembersListTXT() / renderGrayMembersListHTML()
    public static String renderGrayMembersListTXT(List<Member> grayMembers)
    {
        return renderGrayMembersList(grayMembers, "template/txt/gray_members_list.txt", grayMemberRowViewTXT);
    }

    public static String renderGrayMembersListHTML(List<Member> grayMembers)
    {
        return renderGrayMembersList(grayMembers, "template/html/gray_members_list.html", grayMemberRowViewHTML);
    }
    */

    /*
    // Per le donazioni, uso l'operatore ternario per gestire i soci anonimi ed evitare NullPointerException
    public static EntityView<Donation> donationBasicView = donation ->
        "€" + donation.getAmount() + " - " + donation.getDate() + " (" +
        (donation.getMember() != null ? donation.getMember().getLastName() : "N/A") + ")\n";
    */

    /*
    // Uso un blocco lambda {} per preparare il nome completo prima di caricarlo nella ricevuta
    // MANTENUTO QUI (non in ViewFactory) perché richiede logica custom:
    // - Null-check su member
    // - Concatenazione firstName + lastName
    // - Null-check su notes
    public static EntityView<Donation> donationReceiptView = donation -> {
        String memberName = donation.getMember() != null
            ? donation.getMember().getFirstName() + " " + donation.getMember().getLastName()
            : "N/A";

        return Template.load("template/txt/donation_receipt.txt")
            .replace("[id]", "" + donation.getId())
            .replace("[member]", memberName)
            .replace("[amount]", "" + donation.getAmount())
            .replace("[date]", "" + donation.getDate())
            .replace("[notes]", donation.getNotes() != null ? donation.getNotes() : "Nessuna nota");
    };
    */

    /*
    // Definisco il rendering delle spese e dei relativi report amministrativi
    public static EntityView<Expense> expenseBasicView = expense ->
        "€" + expense.getCost() + " - " + expense.getReason() +
        " [" + (expense.getDate() != null ? expense.getDate() : "N/A") + "]\n";
    */

    /*
    public static EntityView<Expense> expenseReportView = expense ->
        Template.load("template/txt/expense_report.txt")
            .replace("[id]", "" + expense.getId())
            .replace("[reason]", expense.getReason())
            .replace("[date]", expense.getDate() != null ? "" + expense.getDate() : "Non specificata")
            .replace("[cost]", "" + expense.getCost());
    */
}