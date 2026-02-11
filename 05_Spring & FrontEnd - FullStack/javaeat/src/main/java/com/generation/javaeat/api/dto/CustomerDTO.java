package com.generation.javaeat.api.dto;

import java.util.ArrayList;
import java.util.List;
import com.generation.javaeat.model.entities.Validable;

public class CustomerDTO implements Validable
{
    private int        id;
    private String     email;
    private String     pw;
    private String     legalName;
    private String     address;
    private CityDTO    city;
    private List<DeliveryDTO> deliveries = new ArrayList<>();

    public int      getId()        { return id; }
    public String   getEmail()     { return email; }
    public String   getPw()        { return pw; }
    public String   getLegalName() { return legalName; }
    public String   getAddress()   { return address; }
    public CityDTO  getCity()      { return city; }
    public List<DeliveryDTO> getDeliveries() { return deliveries; }

    public void setId(int id)                  { this.id = id; }
    public void setEmail(String email)        { this.email = email; }
    public void setPw(String pw)              { this.pw = pw; }
    public void setLegalName(String name)    { this.legalName = name; }
    public void setAddress(String address)    { this.address = address; }
    public void setCity(CityDTO city)         { this.city = city; }
    public void setDeliveries(List<DeliveryDTO> deliveries) { this.deliveries = deliveries; }

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
