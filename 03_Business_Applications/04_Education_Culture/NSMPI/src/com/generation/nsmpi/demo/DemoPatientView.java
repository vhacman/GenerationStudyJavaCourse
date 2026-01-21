package com.generation.nsmpi.demo;

import com.generation.library.Console;
import com.generation.nsmpi.model.entities.*;
import com.generation.nsmpi.view.PatientView;
import com.generation.nsmpi.view.PatientViewFactory;

import java.time.LocalDate;

public class DemoPatientView
{
    public static void main(String[] args)
    {
        Patient p = new Patient();
        p.setId(1);
        p.setFirstName("Vlad");
        p.setLastName("Țepeș");
        p.setDob(LocalDate.parse("1920-01-01"));
        p.setGender(Gender.M);
        p.setHistory("Grave emofilia");

        p.getAllergies().add("Verdure");
        // Patient->List<String>->add serve per aggiungere alla lista
        p.getAllergies().add("Mezzaluna");

        Console.print("Quale è il tuo ruolo? (doctor, teacher, student, clerk)");
        String role = Console.readString();

        PatientView view = PatientViewFactory.make(role);

        Console.print(view.render(p));
    }
}