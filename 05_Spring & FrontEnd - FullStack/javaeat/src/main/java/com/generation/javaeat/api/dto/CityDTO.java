package com.generation.javaeat.api.dto;

import com.generation.javaeat.model.entities.Validable;
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
public class CityDTO implements Validable 
{
    private int      			id;
    private String    			name;
    private String    			province;
    private List<RestaurantDTO> 	restaurants = new ArrayList<>();
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
    /**
     * Il metodo getErrors valida il contenuto del DTO, verificando che i campi
     * obbligatori siano presenti e contengano valori significativi. La validazione
     * lato server è fondamentale per garantire l'integrità dei dati, poiché le
     * validazioni lato client possono essere facilmente bypassate. Questo metodo
     * restituisce una lista di messaggi di errore che descrivono tutti i problemi
     * riscontrati durante la validazione.
     *
     * @return Una lista di stringhe contenente tutti gli errori di validazione.
     */
    @Override
    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (name == null || name.trim().isEmpty())
            errors.add("Name cannot be null or empty");

        if (province == null || province.trim().isEmpty())
            errors.add("Province cannot be null or empty");

        if (restaurants == null)
            errors.add("Restaurants cannot be null");

        return errors;
    }
}
