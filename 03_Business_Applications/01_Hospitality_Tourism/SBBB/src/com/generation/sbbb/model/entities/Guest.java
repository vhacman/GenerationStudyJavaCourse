package com.generation.sbbb.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.generation.library.Entity;

/**
 * Rappresenta un ospite dell'hotel.
 * Contiene informazioni anagrafiche complete incluso il codice fiscale italiano.
 */
public class Guest extends Entity
{

	protected String		firstName, lastName, SSN;
	protected LocalDate		dob;
	protected String		address, city;


	public Guest() {}

	public Guest(int id, String firstName, String lastName, String SSN, String dob, String address, String city)
	{
		/*
		 ******************************************
		 * Costruttore che inizializza tutti i campi
		 * dell'ospite convertendo la data di nascita
		 * da stringa a LocalDate
		 ******************************************
		 */
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = SSN;
		this.dob = LocalDate.parse(dob);
		this.address = address;
		this.city = city;
	}

	/**
	 * Verifica se il codice fiscale rispetta il formato italiano.
	 * @return true se il formato è valido
	 */
	public boolean hasValidSSN()
	{
		/*
		 ******************************************
		 * Utilizza regex per verificare il pattern
		 * del codice fiscale italiano:
		 * 6 lettere maiuscole + 2 numeri + 1 lettera
		 * + 2 numeri + 1 lettera + 3 numeri + 1 lettera
		 ******************************************
		 */
		String patternString = "^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z][0-9]{3}[A-Z]$";
		return Pattern.matches(patternString, SSN);

	}

	/**
	 * Calcola l'età dell'ospite.
	 * @return età in anni
	 */
	public int getAge()
	{
		/*
		 ******************************************
		 * Calcola l'età considerando se il compleanno
		 * è già trascorso nell'anno corrente.
		 * Crea una data fittizia del compleanno nell'anno
		 * corrente e la confronta con oggi
		 ******************************************
		 */
		LocalDate birthDay =
				LocalDate.of(LocalDate.now().getYear(), dob.getMonthValue(), dob.getDayOfMonth());

		return 	birthDay.isBefore(LocalDate.now()) || birthDay.isEqual(LocalDate.now()) 		?
				LocalDate.now().getYear() - dob.getYear()  										:
				LocalDate.now().getYear() - dob.getYear() -1									;
	}

	/**
	 * Valida tutti i campi dell'ospite.
	 * @return lista di errori di validazione
	 */
	@Override
	public List<String> getErrors()
	{
		/*
		 ******************************************
		 * Controlla tutti i campi obbligatori,
		 * il formato del codice fiscale e l'età minima.
		 * Restituisce una lista con tutti gli errori
		 * riscontrati durante la validazione
		 ******************************************
		 */
		List<String> res = new ArrayList<String>();

		if(isMissing(firstName))
			res.add("Missing value for field first name");

		if(isMissing(lastName))
			res.add("Missing value for field last name");

		if(isMissing(address))
			res.add("Missing value for field address");

		if(dob == null)
			res.add("Value invalid for field date of birth");

		if(!hasValidSSN())
			res.add("Invalid value for SSN");

		if(getAge()<18)
			res.add("Invalid age value: cannot be underage");

		return res;

	}

	/**
	 * Confronta questo ospite con un altro oggetto.
	 * @param o l'oggetto da confrontare
	 * @return true se i campi significativi sono uguali
	 */
	@Override
	public boolean equals(Object o)
	{
		/*
		 ******************************************
		 * Implementa l'uguaglianza logica tra ospiti.
		 * Due ospiti sono uguali se hanno gli stessi
		 * dati anagrafici (nome, cognome, data di nascita,
		 * codice fiscale, città e indirizzo).
		 * Gestisce casi particolari: null, stesso riferimento
		 * e tipo non compatibile
		 ******************************************
		 */
		if(o==null)
			return false;

		if(this==o)
			return true;


		if(!(o instanceof Guest))
			return false;

		Guest other = (Guest) o;


		return		this.firstName.equals(other.firstName) 			&&
					this.lastName.equals(other.lastName) 			&&
					this.dob.equals(other.dob) 						&&
					this.SSN.equals(other.SSN)  					&&
					this.city.equals(other.city) 					&&
					this.address.equals(other.address) 				;

	}




	public String     getFirstName()                    { return firstName; }
	public void       setFirstName(String firstName)    { this.firstName = firstName; }
	public String     getLastName()                     { return lastName; }
	public void       setLastName(String lastName)      { this.lastName = lastName; }
	public String     getSSN()                          { return SSN; }
	public void       setSSN(String sSN)                { SSN = sSN; }
	public LocalDate  getDob()                          { return dob; }
	public void       setDob(LocalDate dob)             { this.dob = dob; }
	public String     getAddress()                      { return address; }
	public void       setAddress(String address)        { this.address = address; }
	public String     getCity()                         { return city; }
	public void       setCity(String city)              { this.city = city; }

}
