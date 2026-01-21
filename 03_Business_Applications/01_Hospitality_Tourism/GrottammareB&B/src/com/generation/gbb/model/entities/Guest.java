package com.generation.gbb.model.entities;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import com.generation.library.Entity;

/**
 * Rappresenta un ospite del B&B con dati anagrafici e di residenza.
 * Estende Entity per ereditare validazione e gestione ID.
 */
public class Guest extends Entity
{
	protected String 		firstName;
	protected String 		lastName;
	protected String 		ssn;
	protected LocalDate 	dob;
	protected String 		address;
	protected String 		city;

	public Guest() {}

	/**
     * Parameterized constructor.
     * Creates a fully initialized room with all attributes.
     * 
     * @param id The unique identifier of the room (primary key)
     * @param name The name of the room (mandatory)
     * @param description A brief description of the room (mandatory)
     * @param size The size of the room in square meters (> 0)
     * @param floor The floor number where the room is located (0-6)
     * @param price The price per night for the room (> 0)
     */
	public Guest(int id, String firstName, String lastName, String ssn, String dob, String address, String city)
	{
		super(id);
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.ssn 		= ssn;
		this.dob 		= LocalDate.parse(dob);
		this.address 	= address;
		this.city 		= city;
	}

	/**
	 * Calcola l'età dell'ospite in anni.
	 *
	 * @return Età in anni, 0 se data di nascita non valida
	 */
	public int getAge()
	{
		if (dob == null)
			return 0;
		return (int) ChronoUnit.YEARS.between(dob, LocalDate.now());
	}

	/**
	 * Validates Italian Tax Code (Codice Fiscale) using regex pattern.
	 * Italian Tax Code Structure (16 characters):
	 * - 6 uppercase letters (surname + name)
	 * - 2 digits (birth year % 100)
	 * - 1 uppercase letter (birth month: A=Jan, B=Feb, ..., L=Dec)
	 * - 2 digits (birth day + gender: odd=male, even=female)
	 * - 1 uppercase letter (municipality code)
	 * - 3 digits (municipality code)
	 * - 1 uppercase letter (control character)
	 * 
	 * Regex pattern: ^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$
	 *
	 * @return true if SSN matches Italian Tax Code format exactly, false otherwise
	 */
	public boolean hasValidSSN()
	{
		if (ssn == null || ssn.isEmpty())
			return false;

		// Il codice fiscale deve rispettare questo pattern
		// [A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]
		// Regex: sta per "regular expression"
		String	patternString = "^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z][0-9]{3}[A-Z]$";
		//                  ^ => inizio stringa $ => fine stringa
		// Questo pattern è in grado di riconoscere se una stringa rispetta la struttura oppure no
		return Pattern.matches(patternString, ssn.toUpperCase().trim());
	}


    /**
     * Validates all room attributes according to business rules.
     * Checks for missing mandatory fields and invalid values.
     * 
     * @return List&lt;String&gt; containing validation error messages.
     *         Returns empty list if validation passes.
     */
	@Override
	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();

		if (isMissing(firstName)) 		errors.add("Nome mancante");
		if (isMissing(lastName)) 		errors.add("Cognome mancante");
		if (isMissing(ssn)) 			errors.add("Codice Fiscale mancante");
		else if (!hasValidSSN()) 		errors.add("Codice Fiscale non valido");
		if (dob == null) 				errors.add("Data di nascita mancante");
		else if (getAge() < 18)			errors.add("L'ospite deve essere maggiorenne (almeno 18 anni)");
		if (isMissing(address)) 		errors.add("Indirizzo mancante");
		if (isMissing(city)) 			errors.add("Città mancante");
		return errors;
	}

	
	/**
	 * Generates a hash code for this guest based on its attributes.
	 * Consistent with equals(): if two guests are equal, they must have the same hash code.
	 * Used by hash-based collections like HashMap and HashSet.
	 * 
	 * @return Hash code value for this guest
	 */
	@Override
	public int hashCode()
	{
	    return Objects.hash(address, city, dob, firstName, lastName, ssn);
	}


	/*
	 * TEORIA: Metodo equals() - Confronto per Uguaglianza
	 * =====================================================
	 * 
	 * DEFINIZIONE:
	 * Il metodo equals() è definito nella classe Object e deve essere sovrascritto per confrontare
	 * il contenuto logico di due oggetti anziché i loro riferimenti in memoria.
	 * 
	 * CONTRATTO equals():
	 * - Riflessivo: x.equals(x) deve essere true
	 * - Simmetrico: se x.equals(y) è true, allora y.equals(x) deve essere true
	 * - Transitivo: se x.equals(y) e y.equals(z) sono true, allora x.equals(z) deve essere true
	 * - Consistente: invocazioni multiple devono restituire lo stesso risultato
	 * - Null-safe: x.equals(null) deve essere false
	 * 
	 * PATTERN DI IMPLEMENTAZIONE:
	 * 1. Controllo identità (this == obj): ottimizzazione per stesso riferimento
	 * 2. Controllo null: prevenzione NullPointerException
	 * 3. Controllo tipo (instanceof): verifica compatibilità tipo
	 * 4. Cast sicuro: conversione a tipo specifico
	 * 5. Confronto attributi: uguaglianza campo per campo
	 * 
	 * RELAZIONE CON hashCode():
	 * Se equals() viene sovrascritto, hashCode() DEVE essere sovrascritto coerentemente:
	 * - Se a.equals(b) è true, allora a.hashCode() == b.hashCode()
	 * - Necessario per corretto funzionamento di HashMap, HashSet, Hashtable
	 * 
	 * CONFRONTO STRINGHE:
	 * - Operatore ==: confronta riferimenti in memoria (identity)
	 * - Metodo equals(): confronta contenuto caratteri (equality)
	 * - Per tipi primitivi usare ==, per oggetti usare equals()
	 */

	/**
	 * Compares this guest with another object for equality.
	 * Two guests are considered equal if all their attributes match.
	 * 
	 * @param obj The object to compare with this guest
	 * @return true if the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj)
	{
	    // FASE 1: Controllo identità
	    // Se this e obj puntano allo stesso indirizzo di memoria, sono identici
	    // Ottimizzazione: evita confronti inutili se è lo stesso oggetto
	    if(this == obj)
	        return true;
	    
	    // FASE 2: Controllo null
	    // Prevenzione NullPointerException: equals() non deve lanciare eccezioni con null
	    // Per contratto, nessun oggetto è uguale a null
	    if(obj == null)
	        return false;
	    
	    // FASE 3: Controllo tipo
	    // Verifica che obj sia effettivamente un'istanza di Guest
	    // instanceof restituisce false se obj è null o tipo incompatibile
	    if(!(obj instanceof Guest))
	        return false;
	    
	    // FASE 4: Cast sicuro
	    // Dopo instanceof, il cast è garantito sicuro (no ClassCastException)
	    // Convertiamo Object in Guest per accedere ai campi specifici
	    Guest other = (Guest) obj;
	    
	    // FASE 5: Confronto attributi
	    // Uguaglianza semantica: tutti i campi devono corrispondere
	    // String.equals() confronta il contenuto, non i riferimenti
	    // Allineamento verticale migliora leggibilità operatori logici
	    return this.firstName.equals(other.firstName)  &&
	           this.lastName.equals(other.lastName)    &&
	           this.ssn.equals(other.ssn)              &&
	           this.dob.equals(other.dob)              &&
	           this.address.equals(other.address)      &&
	           this.city.equals(other.city);
	}


	/**
	 * Generates a human-readable string representation of the guest.
	 * Includes all guest attributes, calculated age, and validation status.
	 * Useful for logging, debugging, and display purposes.
	 * 
	 * @return Formatted string with all guest attributes
	 */
	@Override
	public String toString()
	{
	    return "Guest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
	           ", ssn=" + ssn + ", dob=" + dob + ", age=" + getAge() +
	           ", address=" + address + ", city=" + city + ", valid=" + isValid() + "]";
	}

	/**
	 * Returns the guest's first name.
	 * @return First name as String.
	 */
	public String    getFirstName()                    { return firstName;                      }
	
	/**
	 * Returns the guest's last name.
	 * @return Last name as String.
	 */
	public String    getLastName()                     { return lastName;                       }
	
	/**
	 * Returns the Italian Tax Code.
	 * @return SSN/Codice Fiscale as String.
	 */
	public String    getSsn()                          { return ssn;                            }
	
	/**
	 * Returns the date of birth.
	 * @return Date of birth as LocalDate.
	 */
	public LocalDate getDob()                          { return dob;                            }
	
	/**
	 * Returns the full street address.
	 * @return Address as String.
	 */
	public String    getAddress()                      { return address;                        }
	
	/**
	 * Returns the city of residence.
	 * @return City as String.
	 */
	public String    getCity()                         { return city;                           }

	/**
	 * Sets the guest's first name.
	 * @param firstName New first name to assign (mandatory field).
	 */
	public void      setFirstName(String firstName)    { this.firstName = firstName;            }
	
	/**
	 * Sets the guest's last name.
	 * @param lastName New last name to assign (mandatory field).
	 */
	public void      setLastName(String lastName)      { this.lastName = lastName;              }
	
	/**
	 * Sets the Italian Tax Code.
	 * @param ssn New SSN/Codice Fiscale (16 chars, uppercase, valid format).
	 */
	public void      setSsn(String ssn)                { this.ssn = ssn;                        }
	
	/**
	 * Sets date of birth using LocalDate object.
	 * @param dob New date of birth (guest must be 18+).
	 */
	public void      setDob(LocalDate dob)             { this.dob = dob;                        }
	
	/**
	 * Sets date of birth parsing String (ISO format: yyyy-MM-dd).
	 * @param dob Date string in "yyyy-MM-dd" format.
	 * @throws DateTimeParseException if format is invalid.
	 */
	public void      setDob(String dob)                { this.dob = LocalDate.parse(dob);       }
	
	/**
	 * Sets the full street address.
	 * @param address New complete address (mandatory field).
	 */
	public void      setAddress(String address)        { this.address = address;                }
	
	/**
	 * Sets the city of residence.
	 * @param city New city name (mandatory field).
	 */
	public void      setCity(String city)              { this.city = city;                      }

	
}
