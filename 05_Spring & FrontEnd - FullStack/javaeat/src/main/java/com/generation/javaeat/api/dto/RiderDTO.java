package com.generation.javaeat.api.dto;

import java.util.ArrayList;
import java.util.List;
import com.generation.javaeat.model.entities.Validable;

public class RiderDTO implements Validable
{
    private int      id;
    private String   email;
    private String   pw;
    private String   legalName;
    private int		 serviceCost;
    private List<DeliveryDTO> deliveries = new ArrayList<>();

    public int     getId()         								{ return id; 						}
    public String  getEmail()      								{ return email; 					}
    public String  getLegalName() 								{ return legalName; 				}
    public String  getPw()        								{ return pw; 						}
    public int  getServiceCost()								{ return serviceCost; 				}
    public List<DeliveryDTO> getDeliveries() 					{ return deliveries; 				}

    public void setId(int id)                 					{ this.id = id; 					}
    public void setEmail(String email)        					{ this.email = email; 				}
    public void setLegalName(String legalName) 					{ this.legalName = legalName; 		}
    public void setPw(String pw)              					{ this.pw = pw;						}
    public void setServiceCost(int cost)  						{ this.serviceCost = cost; 			}
    public void setDeliveries(List<DeliveryDTO> deliveries) 	{ this.deliveries = deliveries; 	}

    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();

        if (!emailIsValid(email))
            errors.add("Email is not valid");

        if (pw == null || pw.trim().isEmpty())
            errors.add("Password cannot be null or empty");

        if (legalName == null || legalName.trim().isEmpty())
            errors.add("LegalName cannot be null or empty");

        if (serviceCost < 0)
            errors.add("ServiceCost cannot be negative");

        if (deliveries == null)
            errors.add("Deliveries cannot be null");

        return errors;
    }
}
