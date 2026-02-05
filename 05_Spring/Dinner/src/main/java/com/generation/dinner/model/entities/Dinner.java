 package com.generation.dinner.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dinner
{	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String description;
    private int    cost;
    private String status;
    private int    tip;

    public Dinner() {}

    public int    	getId()          { return id; }
    public String 	getDescription() { return description; }
    public int    	getCost()        { return cost; }
    public String 	getStatus()      { return status; }
    public int    	getTip()         { return tip; }

    public void 	setId(int id)                   { this.id = id; }
    public void		setDescription(String desc)     { this.description = desc; }
    public void 	setCost(int cost)               { this.cost = cost; }
    public void 	setStatus(String status)        { this.status = status; }
    public void 	setTip(int tip)                 { this.tip = tip; }
}