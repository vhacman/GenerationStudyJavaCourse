package com.generation.ba.model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a bank client with personal information.
 * This model class defines the basic properties of a bank customer
 * including name, surname, SSN, and date of birth.
 */
public class Client
{
	private final static String DEFAULTNAME = "XXX";
	private String				name;
	private String				surname;
	private String				ssn;
	private LocalDate			dob;

	/**
	 * Default constructor.
	 * Creates a new Client instance with all fields uninitialized.
	 */
	public Client() {}

	/**
	 * Gets the client's first name in uppercase.
	 * Returns a default value if the name has not been set.
	 *
	 * @return the client's name in uppercase, or "XXX" if not set
	 */
	public String getName() {
		return name == null ? DEFAULTNAME : name.toUpperCase();
	}

	/**
	 * Sets the client's first name after validation and sanitization.
	 * The name is trimmed of leading/trailing whitespace before storage.
	 *
	 * @param newFirstName the new first name to set
	 * @return true if the name was set successfully, false if the input was null or blank
	 */
	public boolean setName(String newFirstName) {
		if(newFirstName == null || newFirstName.isBlank())
			return false;
		this.name = newFirstName.trim();
		return true;
	}

	/**
	 * Gets the client's surname.
	 *
	 * @return the client's surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the client's surname after validation and sanitization.
	 * The surname is trimmed of leading/trailing whitespace before storage.
	 *
	 * @param newSurname the new surname to set
	 * @return true if the surname was set successfully, false if the input was null or blank
	 */
	public boolean setSurname(String newSurname) {
		if(newSurname == null || newSurname.isBlank())
			return false;
		this.surname = newSurname.trim();
		return true;
	}

	/**
	 * Gets the client's Social Security Number.
	 *
	 * @return the client's SSN
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * Sets the client's Social Security Number.
	 *
	 * @param ssn the Social Security Number to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * Sets the client's date of birth from individual day, month, and year components.
	 *
	 * @param day the day of the month (1-31)
	 * @param month the month of the year (1-12)
	 * @param year the year
	 */
	public void setDob(int day, int month, int year) {
		this.dob = LocalDate.of(year, month, day);
	}

	/**
	 * Sets the client's date of birth by parsing a date string.
	 * The date string must be in ISO-8601 format (yyyy-MM-dd).
	 *
	 * @param date the date string to parse (format: yyyy-MM-dd)
	 * @throws DateTimeParseException if the date string cannot be parsed
	 */
	public void setDob(String date) {
		this.dob = LocalDate.parse(date);
	}

	/**
	 * 
	 * Gets the client's date of birth formatted according to the configured country.
	 * - Italy, UK, France: dd/MM/yyyy format
	 * - USA: MM/dd/yyyy format
	 * - Germany: dd.MM.yyyy format
	 * - Other countries: ISO-8601 format (yyyy-MM-dd)
	 *
	 * @return the formatted date of birth string, or null if date of birth is not set
	 */
	public String getDob()
	{
		if (dob == null) return null;
		
		int year = dob.getYear();
		int month = dob.getMonthValue();
		int day = dob.getDayOfMonth();
		
		switch(Config.COUNTRY)
		{
		case ITALY:
		case UK:
		case FRANCE:
			return day + "/" + month + "/" + year;	
		case USA:
			return month + "/" + day + "/" + year;
		case GERMANY:
			return day + "." + month + "." + year;
		case UNIVERSAL:
			return year + "-" + month + "-" + day;
		default:
			return dob.toString();
		}
	}
}