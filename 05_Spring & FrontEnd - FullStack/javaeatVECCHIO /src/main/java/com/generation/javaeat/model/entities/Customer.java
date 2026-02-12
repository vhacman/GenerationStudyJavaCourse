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

@Entity
public class Customer implements Validable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int          id;

    private String        email;
    private String        pw;
    private String        legalName;
    private String        address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City         city;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Delivery> deliveries;

    // GETTERS

    public int    getId()          { return id; }
    public String getEmail()       { return email; }
    public String getPw()          { return pw; }
    public String getLegalName()   { return legalName; }
    public String getAddress()     { return address; }
    public City   getCity()        { return city; }
    public List<Delivery> getDeliveries() { return deliveries; }

    // SETTERS

    public void setId(int id)                 { this.id = id; }
    public void setEmail(String email)        { this.email = email; }
    public void setPw(String pw)              { this.pw = pw; }
    public void setLegalName(String legalName){ this.legalName = legalName; }
    public void setAddress(String address)    { this.address = address; }
    public void setCity(City city)            { this.city = city; }
    public void setDeliveries(List<Delivery> deliveries) { this.deliveries = deliveries; }

    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (!emailIsValid(email))
            errors.add("Email is not valid");
        if (pw == null || pw.trim().isEmpty())
            errors.add("Password cannot be null or empty");
        if (legalName == null || legalName.trim().isEmpty())
            errors.add("LegalName cannot be null or empty");
        if (address == null || address.trim().isEmpty())
            errors.add("Address cannot be null or empty");
        if (city == null)
            errors.add("City cannot be null");
        if (deliveries == null)
            errors.add("Deliveries cannot be null");
        return errors;
    }
}
