package com.generation.javaeat.model.entities;

import jakarta.persistence.CascadeType;
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
 * Entità che rappresenta un ristorante.
 * Contiene il profilo del ristorante, la localizzazione e lo storico delle consegne.
 */
@Entity
public class Restaurant implements Validable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int           id;
    private String        email;
    private String        pw;
    private String        name;
    private String        address;
    private int           capacity;

    // Restaurant M:1 City - ogni ristorante appartiene a una città
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City         city;

    // Restaurant 1:M Delivery - un ristorante è la fonte di molte consegne
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Delivery> deliveries;

    // Restaurant 1:M Dish - un ristorante ha un menu con piu' piatti
    // La relazione è definita anche lato Dish (M:1), qui abbiamo il lato inverso
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Dish> dishes;

    public int    getId()                   { return id; }
    public String getEmail()                { return email; }
    public String getPw()                   { return pw; }
    public String getName()                 { return name; }
    public String getAddress()              { return address; }
    public City   getCity()                 { return city; }
    public int    getCapacity()             { return capacity; }
    public List<Delivery> getDeliveries()   { return deliveries; }
    public List<Dish> getDishes()           { return dishes; }

    public void setId(int id)                     { this.id = id; }
    public void setEmail(String email)            { this.email = email; }
    public void setPw(String pw)                  { this.pw = pw; }
    public void setName(String name)              { this.name = name; }
    public void setAddress(String address)        { this.address = address; }
    public void setCity(City city)                { this.city = city; }
    public void setCapacity(int capacity)         { this.capacity = capacity; }
    public void setDeliveries(List<Delivery> d)   { this.deliveries = d; }
    public void setDishes(List<Dish> d)           { this.dishes = d; }

    /**
     * Valida i campi del ristorante.
     * @return lista di errori di validazione
     */
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
