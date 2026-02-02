package com.generation.emergency.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * ANNOTAZIONE @Entity:
 * Indica a Spring/JPA che questa classe rappresenta una tabella del database.
 * Ogni oggetto di questa classe corrisponde a una riga della tabella.
 */
@Entity
public class Hospital
{
    /** @Id: Indica la chiave primaria della tabella
     *  @GeneratedValue: Il valore viene generato automaticamente dal database
     *  IDENTITY: Strategia di generazione AUTO_INCREMENT (come in MySQL) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;          // Identificativo univoco dell'ospedale
    private String  name;        // Nome dell'ospedale
    private String  address;     // Indirizzo completo
    private String  city;        // Città dove si trova l'ospedale
    private int     queue;       // Numero di persone in coda al pronto soccorso

    /** METODI GETTER: Permettono di leggere i valori degli attributi privati */
    public int     getId()      { return id; }
    public String  getName()    { return name; }
    public String  getAddress() { return address; }
    public String  getCity()    { return city; }
    public int     getQueue()   { return queue; }

    /** METODI SETTER: Permettono di modificare i valori degli attributi privati */
    public void setId(int id)               { this.id = id; }
    public void setName(String name)        { this.name = name; }
    public void setAddress(String address)  { this.address = address; }
    public void setCity(String city)        { this.city = city; }
    public void setQueue(int queue)         { this.queue = queue; }
}