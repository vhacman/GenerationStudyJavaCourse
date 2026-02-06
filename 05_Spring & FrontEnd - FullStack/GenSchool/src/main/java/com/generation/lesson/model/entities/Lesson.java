package com.generation.lesson.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lesson
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String student;
	private int hours;
	private String subject;

	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();
        if(student == null || student.isEmpty())
            errors.add("Missing student name");
        if(hours <= 0)
            errors.add("Invalid hours value (must be > 0)");
        if(subject == null || subject.isEmpty())
            errors.add("Missing subject");
        return errors;
	}
	
    /**
     * Valida l'integrità dell'entità verificando assenza di errori.
     * 
     * @return true se tutti i campi obbligatori sono valorizzati, false altrimenti
     */
    public boolean isValid()
    {        
        return getErrors().isEmpty();
    }
    
    public int getPrice()
    {
    	int hourlyRate = 0;
    	
    	if(!isValid())
    		return 0;
    	switch(subject)
    	{
    		case "JAVA":
    			hourlyRate = 50;
    			break;
    		case "SQL":
    			hourlyRate = 30;
    			break;
    		case "ITALIAN":
    			hourlyRate = 20;
    			break;
    		default: 
    			System.out.println("Value not valid");   		
    	}
    	return hours * hourlyRate;
    }

    // Getter e Setter con allineamento verticale
    public int getId()                      { return id; }
    public void setId(int id)               { this.id = id; }
    public String getStudent()              { return student; }
    public void setStudent(String student)  { this.student = student; }
    public int getHours()                   { return hours; }
    public void setHours(int hours)         { this.hours = hours; }
    public String getSubject()              { return subject; }
    public void setSubject(String subject)  { this.subject = subject; }
}
