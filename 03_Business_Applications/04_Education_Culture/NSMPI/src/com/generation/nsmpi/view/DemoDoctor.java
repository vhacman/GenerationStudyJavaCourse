package com.generation.nsmpi.view;

import java.time.LocalDate;
import java.util.Arrays;
import com.generation.library.Console;
import com.generation.nsmpi.model.entities.Doctor;
import com.generation.nsmpi.model.entities.Gender;
import com.generation.nsmpi.model.entities.Specialty;

public class DemoDoctor {
    public static void main(String[] args)
    {
        // 1. Crea Doctor valido coi setter
        Doctor doctor1 = new Doctor();
        doctor1.setId(1);
        doctor1.setFirstName("Mario");
        doctor1.setLastName("Rossi");
        doctor1.setDob(LocalDate.of(1975, 3, 15));
        doctor1.setGender(Gender.M);
        doctor1.setSalary(75000);
        doctor1.setSpecialties(Arrays.asList(Specialty.CARDIOLOGY, Specialty.INTERNAL_MEDICINE));


        Console.print("=== FULL DOCTOR VIEW ===");
        FullDoctorView view = new FullDoctorView("template/doctorTemplateFULL.txt");
        Console.print(view.render(doctor1));


        Console.print("\n=== FINANCIAL DOCTOR VIEW ===");
        Doctor doctor2 = new Doctor();
        doctor2.setId(2);
        doctor2.setFirstName("Luigi");
        doctor2.setLastName("Verdi");
        doctor2.setDob(LocalDate.of(1980, 6, 20));
        doctor2.setGender(Gender.M);
        doctor2.setSalary(82000);
        doctor2.setSpecialties(Arrays.asList(Specialty.GENERAL_SURGERY));

        FinancialDoctorView view2 = new FinancialDoctorView("template/doctorTemplateFINANCIAL.txt");
        Console.print(view2.render(doctor2));
    }
}
