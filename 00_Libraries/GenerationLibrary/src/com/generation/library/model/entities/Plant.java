package com.generation.library.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Plant extends Entity
{
    String  species;
    int     length;
    double  cost;

    public Plant() {}

    public Plant(String species, int length, double cost)
    {
        super();
        this.species = species;
        this.length = length;
        this.cost = cost;
    }

    @Override
    public List<String> getErrors()
    {
        List<String> res = new ArrayList<String>();

        if(isMissing(species))
            res.add("Missing species");

        if(length <= 0)
            res.add("Invalid length");

        if(cost <= 0)
            res.add("Invalid cost");

        return res;
    }

    @Override
    public String toString()
    {
        return "Plant [species=" + species + ", length=" + length + ", cost=" + cost + "]";
    }

    public String getSpecies()              { return species; }
    public void setSpecies(String species)  { this.species = species; }

    public int getLength()                  { return length; }
    public void setLength(int length)       { this.length = length; }

    public double getCost()                 { return cost; }
    public void setCost(double cost)        { this.cost = cost; }
}
