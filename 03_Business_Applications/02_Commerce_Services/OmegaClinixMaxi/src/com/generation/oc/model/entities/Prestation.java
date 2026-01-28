package com.generation.oc.model.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Prestation extends Entity
{

	protected LocalDate           		date;
    protected int                 				price;
    protected Patient             			patient;
    protected HealthService       	healthService;

    public Prestation(){}

    public Prestation(LocalDate date, int price, Patient patient, HealthService healthService)
    {
        this.date = date;
        this.price = price;
        this.patient = patient;
        this.healthService = healthService;
    }

    @Override
    public List<String> getErrors()
    {
        List<String> res = new ArrayList<>();
        if(date == null)
			res.add("Missing date");
        if(price < 0)
			res.add("Invalid price");
        if(patient == null)
			res.add("Missing patient");
        if(healthService == null)
			res.add("Missing health service");
        return res;
    }

    public LocalDate            		getDate()           			{ return date; }
    public int                  				getPrice()          			{ return price; }
    public Patient              		getPatient()        			{ return patient; }
    public HealthService     		getHealthService()  	{ return healthService; }

    public void                 setDate(LocalDate date)                     						{ this.date = date; }
    public void                 setPrice(int price)                         									{ this.price = price; }
    public void                 setPatient(Patient patient)                 						{ this.patient = patient; }
    public void                 setHealthService(HealthService healthService)	{ this.healthService = healthService; }
    
	@Override
	public String toString()
	{
		String patientInfo = (patient != null) ? patient.getFirstName() + " " + patient.getLastName() : "N/A";
		String serviceInfo = (healthService != null) ? healthService.getName() : "N/A";
		return "Prestation [id=" + id + ", date=" + date + ", price=" + price
				+ ", patient=" + patientInfo + ", healthService=" + serviceInfo + "]";
	}
}
