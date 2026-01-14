package com.generation.gbb.model.entities;

import java.util.List;
import java.util.ArrayList;

import com.generation.library.Entity;

public class Room extends Entity
{
    private String 	name;
    private String  description;
    private double  size; 
    private int     floor; 
    private double  price; 

    public Room() {}

    /**
     * Parameterized constructor.
     * Creates a fully initialized room with all attributes.
     * 
     * @param id The unique identifier of the room (primary key)
     * @param name The name of the room (mandatory)
     * @param description A brief description of the room (mandatory)
     * @param size The size of the room in square meters (> 0)
     * @param floor The floor number where the room is located (0-6)
     * @param price The price per night for the room (> 0)
     */
    public Room(int id, String name, String description, double size, int floor, double price)
    {
    	super(id);
        this.name        = name;
        this.description = description;
        this.size        = size;
        this.floor       = floor;
        this.price       = price;
    }

    
    /**
     * Validates all room attributes according to business rules.
     * Checks for missing mandatory fields and invalid values.
     * 
     * @return List&lt;String&gt; containing validation error messages.
     *         Returns empty list if validation passes.
     */
    @Override
    public List<String> getErrors()
    {
        List<String>	errors = new ArrayList<>();
        if (isMissing(name)) 			{ errors.add("Nome stanza obbligatorio"							); }
        if (isMissing(description)) 	{ errors.add("Descrizione stanza obbligatoria"					); }
        if (size <= 0) 					{ errors.add("Dimensione deve essere positiva (mq)"				); }
        if (floor < 0 || floor > 6) 	{ errors.add("Piano non può essere negativo o maggiore di 6"	); }
        if (price <= 0) 				{ errors.add("Prezzo per notte deve essere positivo"			); }
        return errors;
    }

    /**
     * Generates a human-readable string representation of the room.
     * Useful for logging, debugging, and display purposes.
     * 
     * @return Formatted string with all room attributes
     */
    @Override
    public String toString()
    {
        return "Room{" 				+
               "name='" 			+ name 			+ '\'' +
               ", description='" 	+ description 	+ '\'' +
               ", size=" 			+ size 			+
               ", floor=" 			+ floor 		+
               ", price=" 			+ price 		+
               '}';
    }
    
    /** 
     * Returns the name of the room. 
     * @return The room name as String.
     */
    public String  getName()        { return name;        }
    
    /** 
     * Returns the room description. 
     * @return The room description as String.
     */
    public String  getDescription() { return description; }
    
    /** 
     * Returns the room size in square meters. 
     * @return The room size as double (mq).
     */
    public double  getSize()        { return size;        }
    
    /** 
     * Returns the floor number where the room is located. 
     * @return The floor number as int (0-6).
     */
    public int     getFloor()       { return floor;       }
    
    /** 
     * Returns the price per night of the room. 
     * @return The room price as double (€).
     */
    public double  getPrice()       { return price;       }

    /**
     * Sets the name of the room.
     * @param name The new room name to assign.
     */
    public void    setName(String name) { this.name = name;  }
    
    /**
     * Sets the description of the room.
     * @param description The new room description to assign.
     */
    public void    setDescription(String description) { this.description = description; }
    
    /**
     * Sets the size of the room in square meters.
     * @param size The new room size to assign (must be > 0).
     */
    public void    setSize(double size) { this.size = size; }
    
    /**
     * Sets the floor number where the room is located.
     * @param floor The new floor number to assign (0-6).
     */
    public void    setFloor(int floor)  { this.floor = floor; }
    
    /**
     * Sets the price per night of the room.
     * @param price The new room price to assign (must be > 0).
     */
    public void    setPrice(double price) { this.price = price; }


}
