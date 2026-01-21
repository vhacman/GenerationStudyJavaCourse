package com.generation.nsmpi.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Medico del sistema sanitario con specializzazione e salario.
 * Estende Person utilizzando enum Specialty invece di String per type-safety.
 */
public class Doctor extends Person {
    protected List<Specialty> 	specialties = new ArrayList<>();
    protected int 				salary;

    public Doctor() {}

    /**
     * Inizializza un medico con anagrafica completa, specializzazioni e salario.
     */
    public Doctor(String firstName, String lastName, LocalDate dob, Gender gender, 
                  List<Specialty> specialties, int salary)
    {
        super(firstName, lastName, dob, gender);
        if (specialties != null) {
            this.specialties = new ArrayList<>(specialties);
        }
        this.salary = salary;
    }

    // Getter e Setter
    public List<Specialty> 	getSpecialties() 					{ return new ArrayList<>(specialties);	}
    public int 				getSalary() 						{ return salary; }
    
    public void 			setSpecialties(List<Specialty> specialties) 	{ this.specialties 
    																			= (specialties != null) 
    																			? new ArrayList<>(specialties) 
    																			: new ArrayList<>();}
    
    public void 			setSalary(int salary) 							{ this.salary = salary;		}

    // Metodi per gestire single specialty con enum
    public void addSpecialty(Specialty specialty) { 
        if (specialty != null && !specialties.contains(specialty)) {
            this.specialties.add(specialty); 
        }
    }
    public void removeSpecialty(Specialty specialty) { 
        this.specialties.remove(specialty); 
    }
    public boolean hasSpecialty(Specialty specialty) { 
        return specialties.contains(specialty); 
    }

    /**
     * Valida anagrafica, specializzazioni e salario del medico.
     */
    @Override
    public List<String> getErrors() {
        List<String> errors = super.getErrors();

        if (specialties.isEmpty()) errors.add("Doctor must have at least one specialty");
        if (salary < 0)            errors.add("Salary must be non-negative: " + salary);
        return errors;
    }

    /**
     * Rappresentazione testuale del medico.
     */
    @Override
    public String toString() {
        return super.toString() + " Doctor [specialties=" + specialties + 
               ", salary=€" + salary + "]";
    }
}
