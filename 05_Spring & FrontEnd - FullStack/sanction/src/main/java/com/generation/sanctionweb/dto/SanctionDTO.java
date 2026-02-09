package com.generation.sanctionweb.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object per l'entità Sanction.
 * Utilizzato per trasferire dati tra livelli dell'applicazione,
 * separando il modello di dominio dalla rappresentazione esterna.
 */
public class SanctionDTO
{
    /*
     * ARCHITETTURA OOP - PATTERN E PRINCIPI:
     * 
     *     DTO Pattern      →  Trasferimento dati senza logica di business
     *     Incapsulamento  →  Campi privati con accesso controllato
     *     Astrazione      →  Nasconde la complessità del modello entities
     *     Separation      →  Domain Model ↔ Presentation Layer
     */

    private int        id;
    private String     firstName;
    private String     lastName;
    private LocalDate  date;
    private LocalDate paidOn;
    private int        price;
    private String     citizen;
    private int        citizen_id;
    private String     status;
    private String     reason;

    /**
     * Restituisce l'identificativo della sanzione.
     * @return identificativo
     */
    public int        getId()         { return id; }
    /**
     * Restituisce il nome.
     * @return nome
     */
    public String     getFirstName() { return firstName; }
    /**
     * Restituisce il cognome.
     * @return cognome
     */
    public String     getLastName()  { return lastName; }
    /**
     * Restituisce la data della sanzione.
     * @return data
     */
    public LocalDate  getDate()      { return date; }
    /**
     * Restituisce la data di pagamento.
     * @return data pagamento
     */
    public LocalDate getPaidOn()   { return paidOn; }
    /**
     * Restituisce l'importo.
     * @return prezzo
     */
    public int        getPrice()     { return price; }
    /**
     * Restituisce il nome del cittadino.
     * @return nome cittadino
     */
    public String     getCitizen()   { return citizen; }
    /**
     * Restituisce l'ID del cittadino.
     * @return ID cittadino
     */
    public int        getCitizen_id(){ return citizen_id; }
    /**
     * Restituisce lo stato.
     * @return stato
     */
    public String     getStatus()    { return status; }
    /**
     * Restituisce il motivo.
     * @return motivo
     */
    public String     getReason()    { return reason; }

    /**
     * Imposta l'identificativo.
     * @param id identificativo da impostare
     */
    public void setId(int id)                     { this.id = id; }
    /**
     * Imposta il nome.
     * @param firstName nome da impostare
     */
    public void setFirstName(String firstName)    { this.firstName = firstName; }
    /**
     * Imposta il cognome.
     * @param lastName cognome da impostare
     */
    public void setLastName(String lastName)      { this.lastName = lastName; }
    /**
     * Imposta la data.
     * @param date data da impostare
     */
    public void setDate(LocalDate date)           { this.date = date; }
    /**
     * Imposta la data di pagamento.
     * @param paidOn data pagamento da impostare
     */
    public void setPaidOn(LocalDate paidOn)     { this.paidOn = paidOn; }
    /**
     * Imposta il prezzo.
     * @param price prezzo da impostare
     */
    public void setPrice(int price)               { this.price = price; }
    /**
     * Imposta il nome del cittadino.
     * @param citizen nome cittadino da impostare
     */
    public void setCitizen(String citizen)        { this.citizen = citizen; }
    /**
     * Imposta l'ID del cittadino.
     * @param citizen_id ID cittadino da impostare
     */
    public void setCitizen_id(int citizen_id)     { this.citizen_id = citizen_id; }
    /**
     * Imposta lo stato.
     * @param status stato da impostare
     */
    public void setStatus(String status)          { this.status = status; }
    /**
     * Imposta il motivo.
     * @param reason motivo da impostare
     */
    public void setReason(String reason)          { this.reason = reason; }
}
