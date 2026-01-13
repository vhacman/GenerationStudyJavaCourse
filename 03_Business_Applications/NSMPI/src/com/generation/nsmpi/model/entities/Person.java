package com.generation.nsmpi.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.generation.library.Entity;

/**
 * Persona con anagrafica base (firstName, lastName, dob, gender).
 * Classe astratta estesa da pazienti, medici e staff.
 */
public abstract class Person extends Entity
{
	protected String     firstName;
	protected String     lastName;
	protected LocalDate  dob;
	protected Gender     gender;


	public Person() {}

	/**
	 * Inizializza l'anagrafica completa della persona.
	 *
	 * @param firstName Nome
	 * @param lastName Cognome
	 * @param dob Data di nascita
	 * @param gender Genere
	 */
	public Person(String firstName, String lastName, LocalDate dob, Gender gender)
	{
		super();
		this.firstName = firstName;
		this.lastName  = lastName;
		this.dob       = dob;
		this.gender    = gender;
	}


	public String     getFirstName()                  { return firstName;                      }
	public String     getLastName()                   { return lastName;                       }
	public LocalDate  getDob()                        { return dob;                            }
	public Gender     getGender()                     { return gender;                         }


	public void       setFirstName(String firstName)  { this.firstName = firstName;            }
	public void       setLastName(String lastName)    { this.lastName = lastName;              }
	public void       setGender(Gender gender)        { this.gender = gender;                  }
	public void       setGender(String gender)        { this.gender = Gender.valueOf(gender);  }
	public void       setDob(LocalDate dob)           { this.dob = dob;                        }
	public void       setDob(String dob)              { this.dob = LocalDate.parse(dob);       }

	/**
	 * Valida l'anagrafica verificando presenza di tutti i campi obbligatori.
	 * Implementa il contratto astratto definito da Entity.
	 *
	 * @return Lista di errori (vuota se valido)
	 */
	@Override
	public List<String> getErrors()
	{
		/*
		 * Pattern Template Method + Ereditarietà:
		 *      * Classe Astratta (Entity)  →  Definisce il contratto (getErrors)
		 *      * Classe Astratta (Person)  →  Implementa validazione anagrafica base
		 *      * Sottoclassi Concrete      →  Estenderanno con validazioni specifiche
		 *
		 * Questo metodo fornisce la validazione comune a tutte le persone (pazienti, medici).
		 * Le sottoclassi chiameranno super.getErrors() e aggiungeranno i propri controlli.
		 * Principio di Liskov Substitution: Person sostituisce Entity mantenendo il contratto.
		 */
		List<String> errors = new ArrayList<>();

		if (isMissing(firstName))
			errors.add("Missing value for field First Name");
		if (isMissing(lastName))
			errors.add("Missing value for field Last Name");
		if (dob == null)
			errors.add("Missing value for field Date of Birth");
		if (gender == null)
			errors.add("Missing value for field Gender");

		return errors;
	}

	/**
	 * Rappresentazione testuale dell'anagrafica.
	 *
	 * @return Stringa formattata con id, firstName, lastName, dob e gender
	 */
	@Override
	public String toString()
	{
		return super.toString() + " Person [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender + "]";
	}

}
