package com.generation.demo;

import java.time.LocalDate;

/**
 * Rappresenta una persona con varie proprietà come nome, cognome, città, titolo di studio, ecc.
 * Questa classe viene utilizzata per dimostrare operazioni su collezioni e strutture dati.
 */
public class Person
{
    String     			firstName, lastName;
    String      			species;
    String      			city;
    String					qualification;
    LocalDate   		dob;
    int         				height;

    /**
     * Costruisce una Person con nome e altezza.
     *
     * @param firstName il nome della persona
     * @param height l'altezza della persona in centimetri
     */
    public Person(String firstName, int height)
    {
        this.firstName = firstName;
        this.height = height;
    }

    /**
     * Costruisce una Person con nome, cognome e specie.
     *
     * @param firstName il nome della persona
     * @param lastName il cognome della persona
     * @param species la specie della persona
     */
    public Person(String firstName, String lastName, String species)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.species = species;
    }

    /**
     * Costruisce una Person con nome e città.
     *
     * @param firstName il nome della persona
     * @param city la città di residenza
     */
    public Person(String firstName, String city)
    {
        this.firstName = firstName;
        this.city = city;
    }

    /**
     * Costruisce una Person con nome, città e titolo di studio.
     *
     * @param firstName il nome della persona
     * @param city la città di residenza
     * @param qualification il titolo di studio (es. "Laurea Magistrale", "Diploma", ecc.)
     * @param isQualificationConstructor flag per distinguere questo costruttore da altri con 3 parametri String
     */
    public Person(String firstName, String city, String qualification, boolean isQualificationConstructor)
    {
        this.firstName = firstName;
        this.city = city;
        this.qualification = qualification;
    }

    /**
     * Costruisce una Person con nome, cognome, data di nascita e specie.
     *
     * @param firstName il nome della persona
     * @param lastName il cognome della persona
     * @param dob la data di nascita in formato ISO (yyyy-MM-dd)
     * @param species la specie della persona
     */
    public Person(String firstName, String lastName, String dob, String species) 
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = LocalDate.parse(dob);
        this.species = species;
    }

    /**
     * Calcola l'età della persona basandosi sulla data di nascita.
     *
     * @return l'età in anni
     */
    public int getAge()
    {
        return LocalDate.now().getYear() - dob.getYear();
    }

    /**
     * Restituisce una rappresentazione testuale della Person.
     *
     * @return una stringa che descrive la persona
     */
    @Override
    public String toString() 
    {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + ", species=" + species + ", city=" + city + ", dob=" + dob + ", height=" + height + "]";
    }

    /** @return il nome della persona */
    public String       		getFirstName()  									{ return firstName; }

    /** @return il cognome della persona */
    public String       		getLastName()   									{ return lastName; }

    /** @return la specie della persona */
    public String       		getSpecies()    										{ return species; }

    /** @return la città di residenza */
    public String       		getCity()       											{ return city; }

    /** @return il titolo di studio */
    public String       		getQualification()  							{ return qualification; }

    /** @return la data di nascita */
    public LocalDate    	getDob()        										{ return dob; }

    /** @return l'altezza in centimetri */
    public int         			getHeight()     										{ return height; }

    /** @param firstName il nome da impostare */
    public void 				setFirstName(String firstName) 		{ this.firstName = firstName; }

    /** @param lastName il cognome da impostare */
    public void 				setLastName(String lastName)    	{ this.lastName = lastName; }

    /** @param species la specie da impostare */
    public void 				setSpecies(String species)      			{ this.species = species; }

    /** @param city la città da impostare */
    public void 				setCity(String city)            					{ this.city = city; }

    /** @param qualification il titolo di studio da impostare */
    public void 				setQualification(String qualification)	{ this.qualification = qualification; }

    /** @param dob la data di nascita da impostare */
    public void 				setDob(LocalDate dob)           			{ this.dob = dob; }

    /** @param height l'altezza da impostare in centimetri */
    public void 				setHeight(int height)           				{ this.height = height; }
}
