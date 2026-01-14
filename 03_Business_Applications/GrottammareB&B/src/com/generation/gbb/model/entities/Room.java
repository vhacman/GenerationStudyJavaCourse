package com.generation.gbb.model.entities;

public class Room {
    private String name;
    private String description;
    private double size; // size in mq
    private int floor; // piano
    private double price; // prezzo per notte

    // Default constructor
    public Room() {}

    // Constructor with parameters
    public Room(String name, String description, double size, int floor, double price) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.floor = floor;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", floor=" + floor +
                ", price=" + price +
                '}';
    }
}
