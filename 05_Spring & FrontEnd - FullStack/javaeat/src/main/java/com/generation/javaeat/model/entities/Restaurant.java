package com.generation.javaeat.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Restaurant rappresenta un'entità del dominio applicativo che mappa
 * una tabella nel database relazionale. L'annotazione @Entity indica a JPA (Jakarta
 * Persistence API, precedentemente Java Persistence API) che questa classe describe
 * una tabella del database e che le sue istanze possono essere gestite attraverso
 * operazioni di persistenza. JPA, insieme alla sua implementazione più diffusa
 * Hibernate, permette di lavorare con oggetti Java mentre si interagisce con
 * il database, nascondendo la complessità delle query SQL e della gestione delle
 * transazioni.
 *
 * La strategia di mappatura oggetto-relazionale (ORM) utilizzata da JPA permette
 * di definire relazioni tra entità attraverso annotazioni dedicate, traducendo
 * automaticamente le operazioni tra oggetti e righe del database.
 */
@Entity
public class Restaurant implements Validable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int          id;

    private String        email;
    private String        pw;
    private String        name;
    private String        address;
    private int           capacity;

    /**
     * La relazione ManyToOne tra Restaurant e City implementa un'associazione
     * nel modello relazionale dove più ristoranti possono appartenere alla stessa
     città. L'annotazione @ManyToOne configura questa relazione, specificando
     che il caricamento della città avverrà in modo EAGER, cioè immediatamente
     insieme al ristorante, per garantire che i dati correlati siano disponibili
     senza necessità di ulteriori query. L'annotazione @JoinColumn definisce
     il nome della colonna nel database che funge da chiave esterna.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City         city;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Delivery> deliveries;


    // GETTERS

    public int    getId()      { return id; }
    public String getEmail()   { return email; }
    public String getPw()      { return pw; }
    public String getName()    { return name; }
    public String getAddress() { return address; }
    public City   getCity()    { return city; }
    public int getCapacity()    { return capacity; }
    public List<Delivery> getDeliveries() { return deliveries; }

    // SETTERS

    public void setId(int id)                 { this.id = id; }
    public void setEmail(String email)        { this.email = email; }
    public void setPw(String pw)              { this.pw = pw; }
    public void setName(String name)          { this.name = name; }
    public void setAddress(String address)    { this.address = address; }
    public void setCity(City city)            { this.city = city; }
    public void setCapacity(int capacity)     { this.capacity = capacity; }
    public void setDeliveries(List<Delivery> deliveries) { this.deliveries = deliveries; }


    /**
     * Il metodo getErrors implementa la logica di validazione specifica per l'entità
     * Restaurant, restituendo una lista di eventuali errori riscontrati. La validazione
     * è un processo fondamentale per garantire l'integrità dei dati nel sistema.
     * Ogni campo viene controllato secondo regole definite, e gli errori vengono
     * accumulati in una lista che verrà poi utilizzata per comunicare i problemi
     * all'utente o al chiamante del sistema.
     *
     * @return Una lista di stringhe contenente tutti i messaggi di errore rilevati.
     */
    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (name == null || name.trim().isEmpty())
            errors.add("Name cannot be null or empty");

        if (!emailIsValid(email))
            errors.add("Email is not valid");

        if (pw == null || pw.trim().isEmpty())
            errors.add("Password cannot be null or empty");

        if (address == null || address.trim().isEmpty())
            errors.add("Address cannot be null or empty");

        if (capacity < 0)
            errors.add("Capacity cannot be negative");

        if (city == null)
            errors.add("City cannot be null");

        if (deliveries == null)
            errors.add("Deliveries cannot be null");

        return errors;
    }
}
