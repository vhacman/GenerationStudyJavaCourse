package com.generation.nsmpi.view;

import java.time.LocalDate;
import com.generation.library.Console;
import com.generation.nsmpi.model.entities.Gender;
import com.generation.nsmpi.model.entities.Patient;

public class DemoPatient {
    public static void main(String[] args)
    {
        // 1. Crea Patient valido coi setter
        Patient patient1 = new Patient();
        patient1.setId(1);
        patient1.setFirstName("Mario");
        patient1.setLastName("Rossi");
        patient1.setDob(LocalDate.of(1975, 3, 15));
        patient1.setGender(Gender.M);
        patient1.setHistory("Ipertensione controllata, no allergie note");
        
        patient1.getAllergies().add("Verdure");
        patient1.getAllergies().add("Mezzaluna");
        
        
        Console.print("=== FULL PATIENT VIEW ===");
        FullPatientView view = new FullPatientView("template/patientTemplateFULL.txt");
        Console.print(view.render(patient1)); 
        
        
        Console.print("\n=== ANONYMOUS PATIENT VIEW ===");
        Patient patient2 = new Patient();
        patient2.setId(2);
        patient2.setFirstName("Luigi");
        patient2.setLastName("Verdi");
        patient2.setDob(LocalDate.of(1980, 6, 20));
        patient2.setGender(Gender.M);
        patient2.setHistory("Diabete tipo 2");
        
        patient2.getAllergies().add("Penicillina");
        patient2.getAllergies().add("Lattosio");
        AnonymousPatientView view2 = new AnonymousPatientView("template/patientTemplateForExternal.txt");
        Console.print(view2.render(patient2)); 
        
        
        Console.print("\n=== CLERK PATIENT VIEW ===");
        Patient patient3 = new Patient();
        patient3.setId(3);
        patient3.setFirstName("Anna");
        patient3.setLastName("Bianchi");
        patient3.setDob(LocalDate.of(1990, 12, 5));
        patient3.setGender(Gender.F);
        patient3.setHistory("Asma lieve");
        
        patient3.getAllergies().add("Polline");
        ClerkPatientView view3 = new ClerkPatientView("template/patientTemplateForClerk.txt");
        Console.print(view3.render(patient3));
    }
}