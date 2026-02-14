package com.generation.javaeat.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Delivery implements Validable
{
    public static final String STATUS_OPEN      = "OPEN";
    public static final String STATUS_DELIVERED = "DELIVERED";
    public static final String STATUS_CANCELED   = "CANCELED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int              id;
    private String           description;
    private String           status;
    private double           price;
    private LocalDateTime    deliveryTimeOpen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant       restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "costumer_id")
    private Costumer         costumer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rider_id")
    private Rider            rider;

    public Delivery() {}

    public int              getId()             { return id; }
    public String           getDescription()    { return description; }
    public String           getStatus()         { return status; }
    public double           getPrice()          { return price; }
    public LocalDateTime    getDeliveryTimeOpen() { return deliveryTimeOpen; }
    public Restaurant       getRestaurant()     { return restaurant; }
    public Costumer         getCostumer()       { return costumer; }
    public Rider            getRider()          { return rider; }

    public void setId(int id)                         { this.id = id; }
    public void setDescription(String description)   { this.description = description; }
    public void setStatus(String status)              { this.status = status; }
    public void setPrice(double price)                { this.price = price; }
    public void setDeliveryTimeOpen(LocalDateTime dt) { this.deliveryTimeOpen = dt; }
    public void setRestaurant(Restaurant restaurant)  { this.restaurant = restaurant; }
    public void setCostumer(Costumer costumer)        { this.costumer = costumer; }
    public void setRider(Rider rider)                 { this.rider = rider; }

    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (deliveryTimeOpen == null)
            errors.add("DeliveryTimeOpen cannot be null");
        if (status == null || status.trim().isEmpty())
            errors.add("Status cannot be null or empty");
        if (restaurant == null)
            errors.add("Restaurant cannot be null");
        if (costumer == null)
            errors.add("Costumer cannot be null");
        if (rider == null)
            errors.add("Rider cannot be null");
        if (status != null
            && !status.equals(STATUS_OPEN)
            && !status.equals(STATUS_DELIVERED)
            && !status.equals(STATUS_CANCELED))
            errors.add("Status must be OPEN, DELIVERED, or CANCELED");
        if (price < 0)
            errors.add("Price cannot be negative");

        return errors;
    }
}
