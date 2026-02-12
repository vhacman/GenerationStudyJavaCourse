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

/**
 * La classe City rappresenta un'entità del database che mappa la tabella delle città.
 * L'annotazione @Entity indica a JPA che questa classe describe una tabella del database
 * e che le sue istanze possono essere gestite attraverso operazioni di persistenza.
 * Ogni città è identificata univocamente da un ID generato automaticamente secondo
 * la strategia IDENTITY, che corrisponde all'auto-increment del database.
 *
 * L'implementazione dell'interfaccia Validable garantisce che ogni città possa
 * essere sottoposta a validazione secondo regole definite, permettendo di verificare
 * l'integrità dei dati prima del salvataggio nel database.
 */
@Entity
public class City implements Validable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int      			id;
    private String    			name;
    private String    			province;    
    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Restaurant> 	restaurants;

    // GETTERS

    public int    			getId()       		{ return id; 			}
    public String 			getName()     		{ return name; 			}
    public String 			getProvince() 		{ return province; 		}
    public List<Restaurant> getRestaurants() 	{ return restaurants; 	}


    // SETTERS

    public void setId(int id)                 { this.id = id; }
    public void setName(String name)          { this.name = name; }
    public void setProvince(String province)  { this.province = province; }
    public void setRestaurants(List<Restaurant> restaurants) { this.restaurants = restaurants; }


    /**
     * Il metodo getErrors implementa la logica di validazione per l'entità City,
     * controllando che i campi obbligatori siano presenti e contengano valori validi.
     * La validazione è un processo fondamentale per garantire la qualità dei dati
     * nel sistema, prevenendo l'inserimento di informazioni incomplete o errate.
     * Ogni campo viene verificato secondo criteri specifici e gli errori vengono
     * raccolti in una lista che viene restituita al chiamante.
     *
     * @return Una lista contenente i messaggi di errore per ogni campo non valido.
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
