package com.generation.javaeat.api.dto;

import java.util.ArrayList;
import java.util.List;
import com.generation.javaeat.model.entities.Validable;

/**
 * La classe RestaurantDTO (Data Transfer Object) rappresenta un oggetto utilizzato
 * per trasferire dati relativi ai ristoranti tra il backend e il frontend.
 * Il pattern DTO è particolarmente utile nelle applicazioni web dove le
 * comunicazioni avvengono attraverso reti, poiché permette di strutturare
 * i dati in modo ottimale per la trasmissione e di limitare le informazioni
 * exposta al client.
 */
public class RestaurantDTO implements Validable
{

    private int          		id;
    private String        		name;
    private String        		email;
    private String        		pw;
    private String        		address;
    private int           		capacity;
    private CityDTO       		city;
    private List<DeliveryDTO> 	deliveries = new ArrayList<>();

    public int      			getId()      				{ return id; 			}
    public String   			getName()    				{ return name;			}
    public String   			getEmail()   				{ return email; 		}
    public String   			getPw()      				{ return pw; 			}
    public String   			getAddress() 				{ return address; 		}
    public CityDTO  			getCity()    				{ return city; 			}
    public int 					getCapacity()      			{ return capacity; 		}
    public List<DeliveryDTO> 	getDeliveries() 			{ return deliveries; 	}


    // SETTERS

    public void setId(int id)                  					{ this.id = id; 					}
    public void setName(String name)           					{ this.name = name; 				}
    public void setEmail(String email)        					{ this.email = email; 				}
    public void setPw(String pw)              					{ this.pw = pw; 					}
    public void setAddress(String address)    					{ this.address = address; 			}
    public void setCity(CityDTO city)         					{ this.city = city; 				}
    public void setCapacity(int capacity)    					{ this.capacity = capacity; 		}
    public void setDeliveries(List<DeliveryDTO> deliveries) 	{ this.deliveries = deliveries; 	}

    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (!emailIsValid(email))
            errors.add("Email is not valid");
        if (name == null || name.trim().isEmpty())
            errors.add("Name cannot be null or empty");
        if (pw == null || pw.trim().isEmpty())
            errors.add("Password cannot be null or empty");
        if (address == null || address.trim().isEmpty())
            errors.add("Address cannot be null or empty");
        if (capacity < 0)
            errors.add("Capacity cannot be negative");
        if (city == null)
            errors.add("City cannot be null");
        if (deliveries == null)
            errors.add("Deliveries cannot be null");
        return errors;
    }
}
