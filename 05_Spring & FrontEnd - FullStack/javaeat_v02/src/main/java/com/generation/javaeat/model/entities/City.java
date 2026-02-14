package com.generation.javaeat.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class City implements Validable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int              id;
    private String           name;
    private String           province;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Costumer>   costumers;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rider>      riders;

    public int              getId()       { return id; }
    public String           getName()     { return name; }
    public String           getProvince() { return province; }
    public List<Restaurant> getRestaurants() { return restaurants; }
    public List<Costumer>   getCostumers()   { return costumers; }
    public List<Rider>      getRiders()      { return riders; }

    public void setId(int id)                     { this.id = id; }
    public void setName(String name)              { this.name = name; }
    public void setProvince(String province)      { this.province = province; }
    public void setRestaurants(List<Restaurant> r) { this.restaurants = r; }
    public void setCostumers(List<Costumer> c)    { this.costumers = c; }
    public void setRiders(List<Rider> r)          { this.riders = r; }

    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (id <= 0)
            errors.add("Id must be a positive number");
        if (name == null || name.trim().isEmpty())
            errors.add("Name cannot be null or empty");
        if (province == null || province.trim().isEmpty())
            errors.add("Province cannot be null or empty");
        if (restaurants == null || restaurants.isEmpty())
            errors.add("Restaurants list cannot be null or empty");
        if (costumers == null || costumers.isEmpty())
            errors.add("Costumers list cannot be null or empty");
        if (riders == null || riders.isEmpty())
            errors.add("Riders list cannot be null or empty");

        return errors;
    }

    @Override
    public String toString()
    {
        return "City [id=" + id + ", name=" + name + ", province=" + province + "]";
    }
}
