package com.generation.javaeat.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Rider implements Validable
{

    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_NOTAVAILABLE = "NOTAVAILABLE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int                id;
    private String             email;
    private String             pw;
    private String             legalName;
    private int                serviceCost;
    private String             status;

    @OneToMany(mappedBy = "rider", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Delivery>     deliveries;

    public Rider() {}

    public int                getId()                  { return id; }
    public String             getEmail()               { return email; }
    public String             getLegalName()           { return legalName; }
    public String             getPw()                  { return pw; }
    public int                getServiceCost()         { return serviceCost; }
    public List<Delivery>     getDeliveries()          { return deliveries; }
    public String             getStatus()              { return status; }

    public void               setId(int id)                                { this.id = id; }
    public void               setEmail(String email)                       { this.email = email; }
    public void               setLegalName(String legalName)               { this.legalName = legalName; }
    public void               setPw(String pw)                             { this.pw = pw; }
    public void               setServiceCost(int cost)                     { this.serviceCost = cost; }
    public void               setDeliveries(List<Delivery> deliveries)     { this.deliveries = deliveries; }
    public void               setStatus(String status)                     { this.status = status; }

    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (!emailIsValid(email))
            errors.add("Email is not valid");
        if (pw == null || pw.trim().isEmpty())
            errors.add("Password cannot be null or empty");
        if (serviceCost < 0)
            errors.add("ServiceCost cannot be null or negative");
        if (legalName == null || legalName.trim().isEmpty())
            errors.add("LegalName cannot be null or empty");
        if (deliveries == null)
            errors.add("Deliveries cannot be null");
        return errors;
    }
}
