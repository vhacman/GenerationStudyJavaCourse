package com.generation.javaeat.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CityDTO
{
    private int      				id;
    private String    				name;
    private String    				province;
    
    private List<String>			errors = new ArrayList<>();
    
    private List<RestaurantDTO> 	restaurants = new ArrayList<>();
    private List<RiderDTO> 			riders = new ArrayList<>();
    private List<CostumerDTO> 		costumer = new ArrayList<>();
    
    
    
    
    
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
	public List<RiderDTO> getRiders() {
		return riders;
	}
	public void setRiders(List<RiderDTO> riders) {
		this.riders = riders;
	}
	public List<CostumerDTO> getCostumer() {
		return costumer;
	}
	public void setCostumer(List<CostumerDTO> costumer) {
		this.costumer = costumer;
	}

    
}