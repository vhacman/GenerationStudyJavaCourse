package com.generation.bw.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * Represents a motorcycle arriving from transport and awaiting processing
 * through various stages before final delivery.
 */
public class Bike extends Entity
{
    protected String        brand, model, plate;
    protected int           power;                  // i cc del motore
    protected int           cost, work, price;
    protected BikeStatus    status;

    /**
     * Constructs a fully initialized Bike with all operational parameters.
     * 
     * @param brand the motorcycle brand
     * @param model the motorcycle model
     * @param plate the license plate number
     * @param power the engine displacement in cc
     * @param cost the purchase cost
     * @param work the processing/labor cost
     * @param price the final selling price
     * @param status the current status in the workflow
     */
    public Bike(String brand, String model, String plate, int power, int cost, int work, int price, BikeStatus status)
    {   super();
        this.brand  = brand;
        this.model  = model;
        this.plate  = plate;
        this.power  = power;
        this.cost   = cost;
        this.work   = work;
        this.price  = price;
        this.status = status;
    }

    public Bike() {}

    
    
    /**
     * Advances the bike to the next status in the workflow pipeline.
     * If the bike is already in the final state (DELIVERED), it remains there.
     */
    public void advance()
    {
        this.status = status.getNext();
    }
    
    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();
        if (isMissing(brand))
            errors.add("Brand cannot be empty");
        if (isMissing(model))
            errors.add("Model cannot be empty");
        if (isMissing(plate))
            errors.add("Plate cannot be empty");
        if (power <= 0)
            errors.add("Power must be greater than 0");
        if (cost <= 0)
            errors.add("Cost must be greater than 0");
        if (work < 0)
            errors.add("Work cost cannot be negative");
        if (price <= 0)
            errors.add("Price must be greater than 0");
        if (price < (cost + work))
            errors.add("Price must be greater than or equal to cost + work (" + (cost + work) + ")");
        if (status == null)
            errors.add("Status cannot be null");
        
        // Return the list directly instead of returning null
        return errors;
    }


    @Override
    public String toString()
    {
        return "Bike [brand=" + brand + ", model=" + model + ", plate=" + plate
                + ", power=" + power + ", cost=" + cost + ", work=" + work
                + ", price=" + price + ", status=" + status + ", id=" + id + "]";
    }

    public String getBrand()                                    { return brand; }
    public void setBrand(String brand)                          { this.brand = brand; }
    public String getModel()                                    { return model; }
    public void setModel(String model)                          { this.model = model; }
    public String getPlate()                                    { return plate; }
    public void setPlate(String plate)                          { this.plate = plate; }
    public int getPower()                                       { return power; }
    public void setPower(int power)                             { this.power = power; }
    public int getCost()                                        { return cost; }
    public void setCost(int cost)                               { this.cost = cost; }
    public int getWork()                                        { return work; }
    public void setWork(int work)                               { this.work = work; }
    public int getPrice()                                       { return price; }
    public void setPrice(int price)                             { this.price = price; }
    public BikeStatus getStatus()                               { return status; }
    public void setStatus(BikeStatus status)                    { this.status = status; }


}
