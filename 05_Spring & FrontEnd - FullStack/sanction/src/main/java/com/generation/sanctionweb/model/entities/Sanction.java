package com.generation.sanctionweb.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entità che rappresenta una sanzione amministrativa nel sistema.
 * Ogni sanzione è associata a un singolo cittadino.
 */
@Entity
public class Sanction
{
    /*
     * ARCHITETTURA OOP - RELAZIONI TRA CONCETTI:
     * 
     *     Sanction        →  Entità di dominio del problema
     *     ManyToOne       →  Sanction (Molti) → Citizen (Uno)
     *     Incapsulamento  →  Stato interno protetto da getter/setter
     *     LocalDate       →  Astrazione per gestione date
     *     CascadeType.ALL →  Propagazione operazioni al cittadino
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;
    private String  firstName;
    private String  lastName;
    private LocalDate date;
    private LocalDate paidOn;
    private String  status;
    private String  reason;
    private int     price;

    /**
     * Relazione Molti-a-Uno: più sanzioni appartengono a un singolo cittadino.
     * - FetchType.EAGER: carica i dati del cittadino immediatamente insieme alla sanzione.
     * - @JoinColumn: definisce 'citizen_id' come colonna della Foreign Key nel database.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citizen_id")
    Citizen citizen;
    /**
     * Restituisce l'identificativo univoco della sanzione.
     * @return identificativo numerico della sanzione
     */
    public int     getId()        { return id; }
    /**
     * Restituisce il nome del soggetto sanzionato.
     * @return nome associato alla sanzione
     */
    public String  getFirstName() { return firstName; }
    /**
     * Restituisce il cognome del soggetto sanzionato.
     * @return cognome associato alla sanzione
     */
    public String  getLastName()  { return lastName; }
    /**
     * Restituisce la data di emissione della sanzione.
     * @return data della sanzione
     */
    public LocalDate getDate()    { return date; }
    /**
     * Restituisce la data di pagamento della sanzione.
     * @return data di pagamento (può essere null se non pagata)
     */
    public LocalDate getPaidOn() { return paidOn; }
    /**
     * Restituisce lo stato corrente della sanzione.
     * @return stato della sanzione (es. "PAID", "UNPAID")
     */
    public String  getStatus()   { return status; }
    /**
     * Restituisce il motivo/descrizione della sanzione.
     * @return motivo della sanzione
     */
    public String  getReason()   { return reason; }
    /**
     * Restituisce l'importo della sanzione.
     * @return prezzo in unità monetarie
     */
    public int     getPrice()     { return price; }
    /**
     * Restituisce il cittadino associato alla sanzione.
     * @return oggetto Citizen associato
     */
    public Citizen getCitizen()  { return citizen; }

    /**
     * Imposta l'identificativo della sanzione.
     * @param id identificativo da assegnare
     */
    public void setId(int id)                 { this.id = id; }
    /**
     * Imposta il nome del soggetto sanzionato.
     * @param firstName nome da assegnare
     */
    public void setFirstName(String firstName){ this.firstName = firstName; }
    /**
     * Imposta il cognome del soggetto sanzionato.
     * @param lastName cognome da assegnare
     */
    public void setLastName(String lastName)  { this.lastName = lastName; }
    /**
     * Imposta la data di emissione della sanzione.
     * @param date data da assegnare
     */
    public void setDate(LocalDate date)       { this.date = date; }
    /**
     * Imposta la data di pagamento della sanzione.
     * @param paidOn data di pagamento
     */
    public void setPaidOn(LocalDate paidOn) { this.paidOn = paidOn; }
    /**
     * Imposta lo stato della sanzione.
     * @param status stato da assegnare
     */
    public void setStatus(String status)      { this.status = status; }
    /**
     * Imposta il motivo della sanzione.
     * @param reason motivo da assegnare
     */
    public void setReason(String reason) { this.reason = reason; }
    /**
     * Imposta l'importo della sanzione.
     * @param price importo da assegnare
     */
    public void setPrice(int price)           { this.price = price; }
    /**
     * Imposta il cittadino associato alla sanzione.
     * @param citizen cittadino da associare
     */
    public void setCitizen(Citizen citizen)    { this.citizen = citizen; }
}
