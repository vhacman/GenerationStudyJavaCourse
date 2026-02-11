package com.generation.javaeat.api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.generation.javaeat.model.entities.Validable;

public class DeliveryDTO implements Validable
{
    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_DELIVERED = "DELIVERED";
    public static final String STATUS_CANCELED = "CANCELED";
    
    private int              id;
    private String           description;
    private String           status;
    private double           price;
    private LocalDateTime    day;
    private int              restaurantId;
    private int              customerId;
    private int              riderId;

    public int              getId()           { return id; }
    public String           getDescription()  { return description; }
    public String           getStatus()       { return status; }
    public double           getPrice()        { return price; }
    public LocalDateTime    getDay()          { return day; }
    public int              getRestaurantId() { return restaurantId; }
    public int              getCustomerId()   { return customerId; }
    public int              getRiderId()      { return riderId; }

    public void setId(int id)                         { this.id = id; }
    public void setDescription(String description)    { this.description = description; }
    public void setStatus(String status)              { this.status = status; }
    public void setPrice(double price)               { this.price = price; }
    public void setDay(LocalDateTime day)            { this.day = day; }
    public void setRestaurantId(int restaurantId)     { this.restaurantId = restaurantId; }
    public void setCustomerId(int customerId)        { this.customerId = customerId; }
    public void setRiderId(int riderId)              { this.riderId = riderId; }

    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (description == null || description.trim().isEmpty())
            errors.add("Description cannot be null or empty");

        if (day == null)
            errors.add("Day cannot be null");

        if (status == null || (!status.equals(STATUS_OPEN) && !status.equals(STATUS_DELIVERED) && !status.equals(STATUS_CANCELED)))
            errors.add("Status must be OPEN, DELIVERED, or CANCELED");

        if (restaurantId <= 0)
            errors.add("RestaurantId must be greater than 0");

        if (customerId <= 0)
            errors.add("CustomerId must be greater than 0");

        if (price < 0)
            errors.add("Price cannot be negative");

        return errors;
    }
}
