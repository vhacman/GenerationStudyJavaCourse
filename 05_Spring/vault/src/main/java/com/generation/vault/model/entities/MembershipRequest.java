package com.generation.vault.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MembershipRequest 
{
    // e con id sopra int id sa che id è la chiave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int     id;

    String  firstName;
    String  lastName;
    String  gender;
    int     income;
    String  status;
    String  vault;

    // GETTERS
    public int     getId()        { return id; }
    public String  getFirstName() { return firstName; }
    public String  getLastName()  { return lastName; }
    public String  getGender()    { return gender; }
    public int     getIncome()    { return income; }
    public String  getStatus()    { return status; }
    public String  getVault()     { return vault; }

    // SETTERS
    public void setId(int id)                  { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName)   { this.lastName = lastName; }
    public void setGender(String gender)       { this.gender = gender; }
    public void setIncome(int income)          { this.income = income; }
    public void setStatus(String status)       { this.status = status; }
    public void setVault(String vault)         { this.vault = vault; }
}
