package com.generation.javaeat.service.dto;

import java.util.ArrayList;
import java.util.List; 

 
public class RestaurantDTO 
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
 
}
