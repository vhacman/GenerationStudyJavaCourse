package com.generation.javaeat.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rider implements Validable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int      id;

    private String    email;
    private String    pw;
    private String    legalName;
    private int    serviceCost;

    @OneToMany(mappedBy = "rider", fetch = FetchType.LAZY)
    private List<Delivery> deliveries;

    // GETTERS

    public int     getId()         { return id; }
    public String  getEmail()      { return email; }
    public String  getLegalName()  { return legalName; }
    public String  getPw()        { return pw; }
    public int  getServiceCost(){ return serviceCost; }
    public List<Delivery> getDeliveries() { return deliveries; }

    // SETTERS

    public void setId(int id)                 { this.id = id; }
    public void setEmail(String email)        { this.email = email; }
    public void setLegalName(String legalName) { this.legalName = legalName; }
    public void setPw(String pw)              { this.pw = pw; }
    public void setServiceCost(int cost)  { this.serviceCost = cost; }
    public void setDeliveries(List<Delivery> deliveries) { this.deliveries = deliveries; }

    @Override
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
