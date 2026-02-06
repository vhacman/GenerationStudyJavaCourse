package com.generation.pcconfigurator.model.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Configuration implements Validable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName, lastName;
	private String processor;
	private int ram, ssd;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getSsd() {
		return ssd;
	}
	public void setSsd(int ssd) {
		this.ssd = ssd;
	}
	
	public int getPrice()
    {
        if(!this.isValid())
            return 0;

        int res = 0;
        switch(this.processor)
        {
            case "i3":
                res+=100;
            break;
            case "i5":
                res+=150;
            break;
            case "i7":
                res+=300;
            break;
        }

        res+=(this.ram * 10); // 10 euro x gb
        
        res+=(this.ssd * 0.1);

        return res;
    }
	
	// i calcoli si RIFANNO SUL SERVER. NON VI POTETE FIDARE DEL CLIENT
	// i calcoli del client sono ANTEPRIME. I calcoli si rifanno sul server
	// perchÃ¨ non esiste sicurezza lato client
	
	@Override
	public List<String> getErrors() 
	{
        List<String> errors = new ArrayList<>();

        // Validazione Nome
        if (this.firstName == null || this.firstName.trim().isEmpty()) {
            errors.add("Missing first name of the customer");
        }

        // Validazione Cognome
        if (this.lastName == null || this.lastName.trim().isEmpty()) {
            errors.add("Missing last name for the customer");
        }

        // Validazione Processore
        List<String> validProcessors = Arrays.asList("i3", "i5", "i7");
        if (this.processor == null || !validProcessors.contains(this.processor)) {
            errors.add("Invalid or missing processor");
        }

        // Validazione RAM (8, 16, 32)
        List<Integer> validRam = Arrays.asList(8, 16, 32);
        if (!validRam.contains(this.ram)) {
            errors.add("Invalid RAM value. Allowed: 8, 16, 32.");
        }

        // Validazione SSD (1000, 2000)
        List<Integer> validSsd = Arrays.asList(1000, 2000);
        if (!validSsd.contains(this.ssd)) {
            errors.add("Invalid SSD value. Allowed: 1000, 2000.");
        }

        return errors;
    }
	
	
}