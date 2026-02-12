package com.generation.javaeat.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Dish implements Validable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int price;
    String name, description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id")
    Delivery delivery;

    public Delivery getDelivery() { return delivery; }
    public void setDelivery(Delivery delivery) { this.delivery = delivery; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (name == null || name.trim().isEmpty())
            errors.add("Name cannot be null or empty");
        if (price < 0)
            errors.add("Price cannot be negative");
        if (restaurant == null)
            errors.add("Restaurant cannot be null");

        return errors;
    }
}
