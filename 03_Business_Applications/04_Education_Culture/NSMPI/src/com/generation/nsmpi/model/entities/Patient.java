package com.generation.nsmpi.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Paziente del sistema sanitario con anamnesi clinica.
 * Estende Person aggiungendo lo storico clinico (history).
 */
public class Patient extends Person
{
	protected String        history;
	protected List<String>  allergies = new ArrayList<>();
	protected double		height;
	protected double		weight;

	public Patient() {}

	/**
	 * Inizializza un paziente con anagrafica completa, anamnesi e allergie.
	 *
	 * @param firstName Nome
	 * @param lastName Cognome
	 * @param dob Data di nascita
	 * @param gender Genere
	 * @param history Anamnesi clinica
	 * @param allergies Lista di allergie
	 * @param height Altezza in metri
	 * @param weight Peso in kg
	 */
    public Patient(int id, String firstName, String lastName, String dob ,String gender, String history, String allergies, double height, double weight)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.setGender(gender);
        this.setDob(dob);
        this.setHistory(history);
        this.height = height;
        this.weight = weight;
        for(int i=0;i<allergies.split(",").length;i++)
            this.getAllergies().add(allergies.split(",")[i]);
    }

	/**
	 * Inizializza un paziente con anagrafica completa e anamnesi (senza allergie).
	 *
	 * @param firstName Nome
	 * @param lastName Cognome
	 * @param dob Data di nascita
	 * @param gender Genere
	 * @param history Anamnesi clinica
	 * @param height Altezza in metri
	 * @param weight Peso in kg
	 */
	public Patient(String firstName, String lastName, LocalDate dob, Gender gender, String history, double height, double weight)
	{
		super(firstName, lastName, dob, gender);
		this.history = history;
		this.height = height;
		this.weight = weight;
	}

	public String        getHistory()                      		{ return history;                 }
	public List<String>  getAllergies()                    		{ return allergies;               }
	public double        getHeight()                       		{ return height;                  }
	public double        getWeight()                       		{ return weight;                  }

	public void          setHistory(String history)        		{ this.history = history;         }
	public void          setAllergies(List<String> allergies) 	{ this.allergies = allergies;     }
	public void          setHeight(double height)          		{ this.height = height;           }
	public void          setWeight(double weight)          		{ this.weight = weight;           }
	
	public void          addAllergy(String allergy)       		{ this.allergies.add(allergy);    }
	public void          removeAllergy(String allergy)    		{ this.allergies.remove(allergy); }

	/**
	 * Calcola il Body Mass Index (BMI) del paziente.
	 * Formula: BMI = peso (kg) / (altezza (m))²
	 *
	 * @return BMI del paziente, 0 se altezza non valida
	 */
	public double getBMI()
	{
		if (height <= 0)
			return 0;
		return weight / (height * height);
	}

	/**
	 * Valida anagrafica e anamnesi del paziente.
	 * Estende la validazione di Person verificando la presenza di history.
	 *
	 * @return Lista di errori (vuota se valido)
	 */
	@Override
	public List<String> getErrors()
	{
		/*
		 * Ereditarietà e Estensione del Comportamento:
		 *      * super.getErrors()     →  Riusa la validazione anagrafica di Person
		 *      * Validazione Specifica →  Aggiunge controlli per il dominio Patient
		 *
		 * Questo approccio segue il principio DRY: non ripetiamo i controlli già
		 * implementati nella classe padre, ma li estendiamo con logica specifica.
		 * La lista errors viene modificata direttamente (reference type).
		 * Principio OCP: Person è aperto all'estensione (Patient aggiunge validazione).
		 */
		List<String> errors = super.getErrors();

		if (isMissing(history))
			errors.add("Missing value for field history");
		return errors;
	}

	/**
	 * Rappresentazione testuale contenente l'anamnesi.
	 *
	 * @return Stringa formattata con id, anagrafica, history e stato validità
	 */
	@Override
	public String toString()
	{
		return super.toString() + " Patient [history=" + history + ", allergies=" + allergies + ", height=" + height + "m, weight=" + weight + "kg, BMI=" + String.format("%.2f", getBMI()) + ", valido=" + isValid() + "]";
	}

}
