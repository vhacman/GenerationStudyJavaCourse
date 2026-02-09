package com.generation.sanctionweb.model.entities;

import jakarta.persistence.*;

import java.util.List;

/**
 * Entità che rappresenta un cittadino nel sistema di sanzioni.
 * Un cittadino può avere associate molteplici sanzioni.
 */
@Entity
public class Citizen
{
    /*
     * ARCHITETTURA OOP - RELAZIONI TRA CONCETTI:
     * 
     *     Incapsulamento  →  Campi privati con accesso via getter/setter
     *     Entity          →  Mappatura diretta su tabella database
     *     OneToMany       →  Citizen (Uno) → Sanctions (Molti)
     *     Astrazione      →  Nasconde la complessità della persistenza
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String firstName;
    private String lastName;
    private String SSN;

    /**
     * Relazione Uno-a-Molti: un cittadino può avere più sanzioni.
     * - mappedBy: la relazione è gestita dal campo 'citizen' nell'entità figlia.
     * - CascadeType.ALL: le operazioni (salvataggio/cancellazione) si propagano alle sanzioni.
     * - orphanRemoval: elimina dal DB le sanzioni rimosse dalla lista.
     */
    @OneToMany(
            mappedBy = "citizen",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Sanction> sanctions;

    /**
     * Restituisce l'identificativo univoco del cittadino.
     * @return identificativo numerico del cittadino
     */
    public int    getId()         { return id; }
    /**
     * Restituisce il nome del cittadino.
     * @return nome del cittadino
     */
    public String getFirstName()   { return firstName; }
    /**
     * Restituisce il cognome del cittadino.
     * @return cognome del cittadino
     */
    public String getLastName()    { return lastName; }
    /**
     * Restituisce il codice fiscale del cittadino.
     * @return codice fiscale
     */
    public String getSSN()         { return SSN; }
    /**
     * Restituisce la lista di tutte le sanzioni associate al cittadino.
     * @return lista di oggetti Sanction
     */
    public List<Sanction> getSanctions() { return sanctions; }

    /**
     * Imposta l'identificativo univoco del cittadino.
     * @param id identificativo da assegnare
     */
    public void setId(int id)                    { this.id = id; }
    /**
     * Imposta il nome del cittadino.
     * @param firstName nome da assegnare
     */
    public void setFirstName(String firstName)  { this.firstName = firstName; }
    /**
     * Imposta il cognome del cittadino.
     * @param lastName cognome da assegnare
     */
    public void setLastName(String lastName)    { this.lastName = lastName; }
    /**
     * Imposta il codice fiscale del cittadino.
     * @param SSN codice fiscale da assegnare
     */
    public void setSSN(String SSN)              { this.SSN = SSN; }
    /**
     * Imposta la lista delle sanzioni associate al cittadino.
     * @param sanctions lista di sanzioni da associare
     */
    public void setSanctions(List<Sanction> sanctions) { this.sanctions = sanctions; }
}
