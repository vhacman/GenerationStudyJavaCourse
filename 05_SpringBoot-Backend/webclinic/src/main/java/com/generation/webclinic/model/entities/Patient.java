package com.generation.webclinic.model.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.webclinic.validator.CommonValidator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Un paziente
 */
@Entity
public class Patient implements Validable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String firstName, lastName, ssn, gender, address,city,email;
    private LocalDate dob;
    private String history; // storia clinica

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
    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getHistory() {
        return history;
    }
    public void setHistory(String history) {
        this.history = history;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public List<String> getErrors()
    {
        CommonValidator validator = CommonValidator.getInstance();

        List<String> errors = new ArrayList<String>();

        // 1. Validazione FirstName
        if (validator.emptyString(firstName))
            errors.add("Missing value for first name");

        // 2. Validazione LastName
        if (validator.emptyString(lastName))
            errors.add("Missing value for last name");

        // 3. Validazione SSN (Codice Fiscale)
        if (validator.emptyString(ssn))
            errors.add("Missing value for SSN");
        else if (!validator.validSSN(ssn))
            errors.add("Invalid SSN format");

        // 4. Validazione Gender (es. M, F o O)
        if (validator.emptyString(gender))
            errors.add("Gender is required");

        // 5. Validazione Address e City
        if (validator.emptyString(address))
            errors.add("Address is required");

        if (validator.emptyString(city))
            errors.add("City is required");


        // 6. Validazione Email
        if (validator.emptyString(email))
            errors.add("Email is required");
        else if (!validator.validEmail(email))
            errors.add("Invalid email format");

        // 7. Validazione Date of Birth (LocalDate)
        if (dob == null)
            errors.add("Date of birth is required");
        else if (dob.isAfter(LocalDate.now()))
            errors.add("Date of birth cannot be in the future");

        // 8. Validazione History (opzionale, ma controlliamo se nulla)
        if (history == null) {
            history = ""; // Inizializziamo per evitare problemi futuri, ma non lanciamo errore
        }

        return errors;
    }





}
