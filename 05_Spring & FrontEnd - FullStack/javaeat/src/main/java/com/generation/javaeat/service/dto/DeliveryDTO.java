package com.generation.javaeat.service.dto;

import java.time.LocalDateTime;

public class DeliveryDTO {

    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_DELIVERED = "DELIVERED";
    public static final String STATUS_CANCELED = "CANCELED";

    private int id;
    private String description;
    private String status;
    private double price;
    private LocalDateTime day;
    private int restaurantId;
    private int costumerId;
    private int riderId;

    public DeliveryDTO() {}

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public double getPrice() { return price; }
    public LocalDateTime getDay() { return day; }
    public int getRestaurantId() { return restaurantId; }
    public int getCostumerId() { return costumerId; }
    public int getRiderId() { return riderId; }

    public void setId(int id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setPrice(double price) { this.price = price; }
    public void setDay(LocalDateTime day) { this.day = day; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    public void setCostumerId(int costumerId) { this.costumerId = costumerId; }
    public void setRiderId(int riderId) { this.riderId = riderId; }
}
