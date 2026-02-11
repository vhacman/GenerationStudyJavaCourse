package com.generation.javaeat.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Delivery implements Validable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int              id;
    private String           description;
    private String           status;
    private double           price;
    private LocalDateTime    deliveryTimeOpen;

    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_DELIVERED = "DELIVERED";
    public static final String STATUS_CANCELED = "CANCELED";


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant      restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer         customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rider_id")
    private Rider            rider;

    // GETTERS

    public int              getId()           { return id; }
    public String           getDescription()  { return description; }
    public String           getStatus()       { return status; }
    public double           getPrice()        { return price; }
    public LocalDateTime    getDeliveryTimeOpen() { return deliveryTimeOpen; }
    public Restaurant       getRestaurant()   { return restaurant; }
    public Customer         getCustomer()     { return customer; }
    public Rider            getRider()         { return rider; }

    // SETTERS

    public void setId(int id)                         { this.id = id; }
    public void setDescription(String description)    { this.description = description; }
    public void setStatus(String status)              { this.status = status; }
    public void setPrice(double price)               { this.price = price; }
    public void setDeliveryTimeOpen(LocalDateTime time)   { this.deliveryTimeOpen = time; }
    public void setRestaurant(Restaurant restaurant)  { this.restaurant = restaurant; }
    public void setCustomer(Customer customer)        { this.customer = customer; }
    public void setRider(Rider rider)                  { this.rider = rider; }

    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (deliveryTimeOpen == null)
            errors.add("DeliveryTimeOpen cannot be null");

        if (status == null || status.trim().isEmpty())
            errors.add("Status cannot be null or empty");

        if (restaurant == null)
            errors.add("Restaurant cannot be null");

        if (customer == null)
            errors.add("Customer cannot be null");

        if (rider == null)
            errors.add("Rider cannot be null");

        if (status != null && !status.equals(STATUS_OPEN) && !status.equals(STATUS_DELIVERED) && !status.equals(STATUS_CANCELED))
            errors.add("Status must be OPEN, DELIVERED, or CANCELED");

        if (price < 0)
            errors.add("Price cannot be negative");

        return errors;
    }
}
