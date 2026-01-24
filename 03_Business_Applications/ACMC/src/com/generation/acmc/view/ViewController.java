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
 * ViewController: Ho deciso di centralizzare qui tutta la logica di presentazione.
 * Utilizzo le EntityView (interfacce funzionali) per mappare i miei oggetti 
 * in stringhe o HTML tramite Lambda Expression.
 */
public class ViewController
{
    // Trasformo i soci in un formato testuale semplice per i log rapidi in console
    public static EntityView<Member> memberBasicView = member ->
        member.getId() + " - " + member.getFirstName() + " " + member.getLastName() +
        " [" + member.getLevel() + "]\n";

    // Inietto i dati del socio nel template TXT per generare la scheda di dettaglio
    public static EntityView<Member> memberDetailView = member ->
        Template.load("template/txt/member_detail.txt")
            .replace("[id]", "" + member.getId())     
            .replace("[firstName]", member.getFirstName())
            .replace("[lastName]", member.getLastName())
            .replace("[gender]", member.getGender())
            .replace("[dob]", "" + member.getDob())         
            .replace("[incomeEst]", "" + member.getIncomeEst())
            .replace("[level]", "" + member.getLevel());

    // Genero la card di benvenuto in HTML caricando il relativo template
    public static EntityView<Member> memberWelcomeCardView = member ->
        Template.load("template/html/member_card_welcome.html")
            .replace("[id]", member.getId() + "")   
            .replace("[firstName]", member.getFirstName())
            .replace("[lastName]", member.getLastName())
            .replace("[level]", "" + member.getLevel())
            .replace("[dob]", "" + member.getDob());

    // Gestisco la visualizzazione delle righe per le tabelle (sia in TXT che in HTML)
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

    // Per le donazioni, uso l'operatore ternario per gestire i soci anonimi ed evitare NullPointerException
    public static EntityView<Donation> donationBasicView = donation ->
        "€" + donation.getAmount() + " - " + donation.getDate() + " (" +
        (donation.getMember() != null ? donation.getMember().getLastName() : "N/A") + ")\n";

    // Uso un blocco lambda {} per preparare il nome completo prima di caricarlo nella ricevuta
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

    // Definisco il rendering delle spese e dei relativi report amministrativi
    public static EntityView<Expense> expenseBasicView = expense -> 
        "€" + expense.getCost() + " - " + expense.getReason() +
        " [" + (expense.getDate() != null ? expense.getDate() : "N/A") + "]\n";

    public static EntityView<Expense> expenseReportView = expense ->
        Template.load("template/txt/expense_report.txt")
            .replace("[id]", "" + expense.getId())
            .replace("[reason]", expense.getReason())
            .replace("[date]", expense.getDate() != null ? "" + expense.getDate() : "Non specificata")
            .replace("[cost]", "" + expense.getCost());

    // Creo dinamicamente la card di promozione iniettando i livelli passati come parametri
    public static EntityView<Member> createPromotionCardViewHTML(MembershipLevel oldLevel, MembershipLevel newLevel)
    {
        return member ->
            Template.load("template/html/member_promotion_card.html")
                .replace("[id]", member.getId() + "")
                .replace("[firstName]", member.getFirstName())
                .replace("[lastName]", member.getLastName())
                .replace("[oldLevel]", "" + oldLevel)
                .replace("[newLevel]", "" + newLevel)
                .replace("[promotionDate]", "" + LocalDate.now());
    }

    // Metodo di utilità che ho aggiunto per renderizzare direttamente una card
    public static String renderPromotionCard(Member member, MembershipLevel oldLevel, MembershipLevel newLevel)
    {
        return createPromotionCardViewHTML(oldLevel, newLevel).render(member);
    }

    public static EntityView<Member> createPromotionCardView(MembershipLevel oldLevel, MembershipLevel newLevel)
    {
        return member ->
            Template.load("template/txt/member_promotion_card.txt")
                .replace("[id]", member.getId() + "")
                .replace("[firstName]", member.getFirstName())
                .replace("[lastName]", member.getLastName())
                .replace("[oldLevel]", "" + oldLevel)
                .replace("[newLevel]", "" + newLevel)
                .replace("[promotionDate]", "" + LocalDate.now());  
    }

    // Applico il pattern Composite: ciclo la lista e delego il rendering della riga alla rowView ricevuta
    public static String renderGrayMembersList(List<Member> grayMembers, String templatePath, EntityView<Member> rowView)
    {
        StringBuilder rows = new StringBuilder(); 
        for (Member member : grayMembers) 
            rows.append(rowView.render(member));

        return Template.load(templatePath)
            .replace("[memberRows]", rows.toString())               
            .replace("[totalMembers]", "" + grayMembers.size());   
    }

    // Shortcut che utilizzo per stampare rapidamente le liste Gray in TXT o HTML
    public static String renderGrayMembersListTXT(List<Member> grayMembers)
    {
        return renderGrayMembersList(grayMembers, "template/txt/gray_members_list.txt", grayMemberRowViewTXT);
    }

    public static String renderGrayMembersListHTML(List<Member> grayMembers)
    {
        return renderGrayMembersList(grayMembers, "template/html/gray_members_list.html", grayMemberRowViewHTML);
    }
}