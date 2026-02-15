package com.generation.javaeat.service.dto;

import java.util.List;

public class DishDTO
{
    private int id;
    private int price;
    private String name;
    private String description;
    // Dish M:1 Restaurant - id del ristorante proprietario del piatto
    private int restaurantId;
    // Cambiato: da int deliveryId (singolo, relazione M:1) a List<Integer> deliveryIds (relazione M:M)
    // Un piatto puo' comparire in molte consegne e una consegna contiene molti piatti
    private List<Integer> deliveryIds;

    public int getId() { return id; }
    public int getPrice() { return price; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getRestaurantId() { return restaurantId; }
    public List<Integer> getDeliveryIds() { return deliveryIds; }

    public void setId(int id) { this.id = id; }
    public void setPrice(int price) { this.price = price; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    public void setDeliveryIds(List<Integer> deliveryIds) { this.deliveryIds = deliveryIds; }
}
