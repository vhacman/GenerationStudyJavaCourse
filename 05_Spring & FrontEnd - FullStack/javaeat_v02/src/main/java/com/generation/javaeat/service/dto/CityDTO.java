package com.generation.javaeat.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CityDTO
{
    private int      				id;
    private String    				name;
    private String    				province;
    
    private List<String>			errors = new ArrayList<>();
    
    // City 1:M Restaurant - lista dei ristoranti appartenenti a questa città
    private List<RestaurantDTO> 	restaurants = new ArrayList<>();
    // City 1:M Costumer - lista dei clienti appartenenti a questa città
    private List<CostumerDTO> 		costumer = new ArrayList<>();
    // Rimosso: List<RiderDTO> riders - Rider non ha relazione con City nel diagramma

    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public List<RestaurantDTO> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<RestaurantDTO> restaurants) {
		this.restaurants = restaurants;
	}
	public List<CostumerDTO> getCostumer() {
		return costumer;
	}
	public void setCostumer(List<CostumerDTO> costumer) {
		this.costumer = costumer;
	}

    
}