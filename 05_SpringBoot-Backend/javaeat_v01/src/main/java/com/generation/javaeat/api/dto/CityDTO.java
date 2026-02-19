package com.generation.javaeat.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe CityDTO (Data Transfer Object) rappresenta un oggetto utilizzato
 * per trasferire dati tra il backend e il frontend dell'applicazione. Il pattern
 * DTO viene utilizzato per encapsulare dati e ridurre il numero di chiamate
 * necessarie per recuperare informazioni dal server, migliorando le performance
 * delle comunicazioni di rete. Inoltre, i DTO permettono di nascondere la
 * struttura interna delle entità del database, exponendo solo i dati necessari
 * al client e isolando così il modello di dominio dalla rappresentazione esterna.
 *
 * L'implementazione dell'interfaccia Validable consente di validare i dati
 * contenuti nel DTO prima di elaborarli o salvarli nel sistema.
 */
public class CityDTO
{
    private int      			id;
    private String    			name;
    private String    			province;
    private List<RestaurantDTO> 	restaurants = new ArrayList<>();
    private List<String>		errors;
    public List<RestaurantDTO> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<RestaurantDTO> restaurants) {
		this.restaurants = restaurants;
	}
	// GETTERS
    public int    getId()       				{ return id; }
    public String getName()     				{ return name; }
    public String getProvince() 				{ return province; }
    // SETTERS
    public void setId(int id)                 	{ this.id = id; }
    public void setName(String name)          	{ this.name = name; }
    public void setProvince(String province)  	{ this.province = province; }

}
