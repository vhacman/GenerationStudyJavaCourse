package com.generation.gbb.model.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.generation.library.Entity;

/**
 * Rappresenta un ospite del B&B con dati anagrafici e di residenza.
 * Estende Entity per ereditare validazione e gestione ID.
 */
public class Guest extends Entity
{
	protected String firstName;
	protected String lastName;
	protected String ssn;
	protected LocalDate dob;
	protected String address;
	protected String city;

	public Guest() {}

	/**
	 * Costruttore completo per creare un ospite.
	 *
	 * @param id Identificativo univoco
	 * @param firstName Nome
	 * @param lastName Cognome
	 * @param ssn Codice Fiscale
	 * @param dob Data di nascita
	 * @param address Indirizzo
	 * @param city Città
	 */
	public Guest(int id, String firstName, String lastName, String ssn, String dob, String address, String city)
	{
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.dob = LocalDate.parse(dob);
		this.address = address;
		this.city = city;
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
	 * Verifica se il codice fiscale è valido usando regex.
	 * Pattern del codice fiscale italiano:
	 * - 6 lettere maiuscole (cognome e nome)
	 * - 2 cifre (anno di nascita)
	 * - 1 lettera maiuscola (mese)
	 * - 2 cifre (giorno e sesso)
	 * - 1 lettera maiuscola (codice comune)
	 * - 3 cifre (codice comune)
	 * - 1 lettera maiuscola (carattere di controllo)
	 *
	 * @return true se il codice fiscale è valido, false altrimenti
	 */
	public boolean hasValidSSN()
	{
		if (ssn == null || ssn.isEmpty())
			return false;

		// Il codice fiscale deve rispettare questo pattern
		// [A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]
		// Regex: sta per "regular expression"
		String patternString = "^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z][0-9]{3}[A-Z]$";
		//                  ^ => inizio stringa $ => fine stringa
		// Questo pattern è in grado di riconoscere se una stringa rispetta la struttura oppure no
		return Pattern.matches(patternString, ssn.toUpperCase().trim());
	}

	/**
	 * Valida i dati dell'ospite.
	 * Verifica presenza di nome, cognome, codice fiscale, data di nascita, indirizzo e città.
	 * Controlla che l'ospite sia maggiorenne (età >= 18) e che il codice fiscale sia valido.
	 *
	 * @return Lista di errori (vuota se tutto valido)
	 */
	@Override
	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();

		if (isMissing(firstName))
			errors.add("Nome mancante");
		if (isMissing(lastName))
			errors.add("Cognome mancante");
		if (isMissing(ssn))
			errors.add("Codice Fiscale mancante");
		else if (!hasValidSSN())
			errors.add("Codice Fiscale non valido");
		if (dob == null)
			errors.add("Data di nascita mancante");
		else if (getAge() < 18)
			errors.add("L'ospite deve essere maggiorenne (almeno 18 anni)");
		if (isMissing(address))
			errors.add("Indirizzo mancante");
		if (isMissing(city))
			errors.add("Città mancante");
		return errors;
	}

	@Override
	public String toString()
	{
		return "Guests [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
				", ssn=" + ssn + ", dob=" + dob + ", age=" + getAge() +
				", address=" + address + ", city=" + city + ", valido=" + isValid() + "]";
	}
	
	public String    getFirstName()                    { return firstName;                      }
	public String    getLastName()                     { return lastName;                       }
	public String    getSsn()                          { return ssn;                            }
	public LocalDate getDob()                          { return dob;                            }
	public String    getAddress()                      { return address;                        }
	public String    getCity()                         { return city;                           }

	public void      setFirstName(String firstName)    { this.firstName = firstName;            }
	public void      setLastName(String lastName)      { this.lastName = lastName;              }
	public void      setSsn(String ssn)                { this.ssn = ssn;                        }
	public void      setDob(LocalDate dob)             { this.dob = dob;                        }
	public void      setDob(String dob)                { this.dob = LocalDate.parse(dob);       }
	public void      setAddress(String address)        { this.address = address;                }
	public void      setCity(String city)              { this.city = city;                      }
	
}
