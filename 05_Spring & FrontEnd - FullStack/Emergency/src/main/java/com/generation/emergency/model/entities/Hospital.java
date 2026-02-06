package com.generation.emergency.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hospital
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     id;
    private String  name;
    private String  address;
    private String  city;
    private int     queue;

    public int     getId()      { return id; }
    public String  getName()    { return name; }
    public String  getAddress() { return address; }
    public String  getCity()    { return city; }
    public int     getQueue()   { return queue; }

    public void setId(int id)               { this.id = id; }
    public void setName(String name)        { this.name = name; }
    public void setAddress(String address)  { this.address = address; }
    public void setCity(String city)        { this.city = city; }
    public void setQueue(int queue)         { this.queue = queue; }
}