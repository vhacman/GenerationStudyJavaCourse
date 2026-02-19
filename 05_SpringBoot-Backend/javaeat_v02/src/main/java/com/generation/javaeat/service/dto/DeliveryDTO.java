package com.generation.javaeat.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryDTO
{

    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_DELIVERED = "DELIVERED";
    public static final String STATUS_CANCELED = "CANCELED";

    private int id;
    private String description;
    private String status;
    private double price;
    private LocalDateTime day;
    // Delivery M:1 Restaurant - id del ristorante da cui proviene la consegna
    private int restaurantId;
    // Delivery M:1 Costumer - id del cliente che ha effettuato l'ordine
    private int costumerId;
    // Delivery M:1 Rider - id del rider che esegue la consegna
    private int riderId;
    // Aggiunto: Delivery M:M Dish - lista dei piatti contenuti nella consegna
    // Una consegna puo' contenere più piatti, e lo stesso piatto può comparire in più consegne
    private List<DishDTO> dishes;

    public DeliveryDTO() {}

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public double getPrice() { return price; }
    public LocalDateTime getDay() { return day; }
    public int getRestaurantId() { return restaurantId; }
    public int getCostumerId() { return costumerId; }
    public int getRiderId() { return riderId; }
    public List<DishDTO> getDishes() { return dishes; }

    public void setId(int id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setPrice(double price) { this.price = price; }
    public void setDay(LocalDateTime day) { this.day = day; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    public void setCostumerId(int costumerId) { this.costumerId = costumerId; }
    public void setRiderId(int riderId) { this.riderId = riderId; }
    public void setDishes(List<DishDTO> dishes) { this.dishes = dishes; }
}
