package com.generation.pl.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import com.generation.library.Console;
import com.generation.pl.model.entities.Admin;
import com.generation.pl.model.entities.Student;
import com.generation.pl.model.entities.Subject;
import com.generation.pl.model.entities.Teacher;
import com.generation.pl.model.entities.UserStatus;

public class Collect 
{
    public static String[] collectUserBaseData(String entityType)
    {
        Console.print("\\n=== INSERT " + entityType.toUpperCase() + " ===");
        String name = "";
        while (name.trim().isEmpty())
        {
            Console.print("Inserire nome:");
            name = Console.readString().trim();
            if (name.isEmpty())
                Console.print("Il nome non puo essere vuoto!");
        }
        String lastname = "";
        while (lastname.trim().isEmpty())
        {
            Console.print("Inserire cognome:");
            lastname = Console.readString().trim();
            if (lastname.isEmpty())
                Console.print("Il cognome non puo essere vuoto!");
        }
        String ssn = "";
        while (ssn.length() != 16)
        {
            Console.print("Inserire SSN (16 caratteri):");
            ssn = Console.readString().trim().toUpperCase();
            if (ssn.length() != 16)
                Console.print("SSN deve essere lungo 16 caratteri!");
        }
        String email = "";
        while (!email.contains("@") || !email.contains("."))
        {
            Console.print("Inserire email:");
            email = Console.readString().trim();
            if (!email.contains("@") || !email.contains("."))
                Console.print("Email non valida! Deve contenere @ e un dominio (es: .com)");
        }
        return new String[] {name, lastname, ssn, email};
    }

    public static String collectPasswordWithConfirmation()
    {
        String password;
        String confirmPassword;
        do
        {
            do
            {
                Console.print("Inserire nuova Password (minimo 8 caratteri):");
                password = Console.readString();
                if (password.length() < 8)
                    Console.print("Password troppo corta! Minimo 8 caratteri.");
            }
            while (password.length() < 8);
            Console.print("Conferma nuova password:");
            confirmPassword = Console.readString();
            if(!password.equals(confirmPassword))
                Console.print("Le password non corrispondono! Riprova.");
        }
        while (!password.equals(confirmPassword));
        return password;
    }

    public static String[] collectLoginCredentials()
    {
        Console.print("Inserire email:");
        String email = Console.readString();
        Console.print("Inserire password:");
        String password = Console.readString();
        return new String[]{email, password};
    }

    public static UserStatus collectStatus()
    {
        while (true)
        {
            Console.print("\\n=== STATUS DISPONIBILI ===");
            Console.print("- ACTIVE");
            Console.print("- INACTIVE");
            Console.print("- SUSPENDED");
            Console.print("\\nNuovo status:");
            String statusStr = Console.readString().trim().toUpperCase();
            try
            {
                return UserStatus.valueOf(statusStr);
            }
            catch (IllegalArgumentException e)
            {
                Console.print("Status non valido! Scegli tra: ACTIVE, INACTIVE, SUSPENDED");
            }
        }
    }

    public static Teacher collectTeacherData()
    {
        Teacher teacher = new Teacher();
        String[] baseData = collectUserBaseData("Teacher");
        String password = collectPasswordWithConfirmation();
        String bio = "";
        while (bio.trim().isEmpty())
        {
            Console.print("Bio:");
            bio = Console.readString().trim();
            if (bio.isEmpty())
                Console.print("La bio non puo essere vuota!");
        }
        int pricePerLesson = 0;
        while (pricePerLesson < 20)
        {
            Console.print("Prezzo per lezione (minimo 20):");
            try
            {
                pricePerLesson = Console.readInt();
                if (pricePerLesson < 20)
                    Console.print("Il prezzo deve essere almeno 20!");
            }
            catch (Exception e)
            {
                Console.print("Inserire un numero valido!");
                Console.readString();
            }
        }
        String subjects = "";
        boolean validSubjects = false;
        while (!validSubjects)
        {
            Console.print("\\n=== MATERIE DISPONIBILI ===");
            for (Subject s : Subject.values())
                Console.print("- " + s.name());
            Console.print("\\nInserire materie (separate da virgola, es: JAVA,SQL,PROGRAMMING):");
            subjects = Console.readString().trim().toUpperCase();
            String[] subjectArray = subjects.split(",");
            validSubjects = true;
            for (String subject : subjectArray)
            {
                try
                {
                    Subject.valueOf(subject.trim());
                }
                catch (IllegalArgumentException e)
                {
                    Console.print("Materia non valida: " + subject.trim());
                    validSubjects = false;
                    break;
                }
            }
        }
        teacher.setFirstName(baseData[0]);
        teacher.setLastName(baseData[1]);
        teacher.setSsn(baseData[2]);
        teacher.setEmail(baseData[3]);
        teacher.setPassword(password);
        teacher.setBio(bio);
        teacher.setPricePerLesson(pricePerLesson);
        teacher.setSubjects(subjects);
        teacher.setStatus(UserStatus.ACTIVE);
        return teacher;
    }

    public static Student collectStudentData()
    {
        String[] baseData = collectUserBaseData("Student");
        String password = collectPasswordWithConfirmation();
        LocalDate dob = null;
        while (dob == null)
        {
            Console.print("Data di nascita (formato YYYY-MM-DD):");
            String dobString = Console.readString().trim();
            try
            {
                dob = LocalDate.parse(dobString);
                if (dob.isAfter(LocalDate.now()))
                {
                    Console.print("La data di nascita non puo essere nel futuro!");
                    dob = null;
                    continue;
                }
                if (dob.isAfter(LocalDate.now().minusYears(5)))
                {
                    Console.print("Lo studente deve avere almeno 5 anni!");
                    dob = null;
                    continue;
                }
                if (dob.isBefore(LocalDate.now().minusYears(100)))
                {
                    Console.print("Data di nascita non valida!");
                    dob = null;
                }
            }
            catch (DateTimeParseException e)
            {
                Console.print("Formato data non valido! Usa YYYY-MM-DD (es: 2000-01-15)");
            }
        }
        Student student = new Student();
        student.setFirstName(baseData[0]);
        student.setLastName(baseData[1]);
        student.setSsn(baseData[2]);
        student.setEmail(baseData[3]);
        student.setPassword(password);
        student.setDob(dob.toString());
        student.setStatus(UserStatus.ACTIVE);
        return student;
    }

    public static Admin collectAdminData()
    {
        String[] baseData = collectUserBaseData("Admin");
        String password = collectPasswordWithConfirmation();
        Admin admin = new Admin();
        admin.setFirstName(baseData[0]);
        admin.setLastName(baseData[1]);
        admin.setSsn(baseData[2]);
        admin.setEmail(baseData[3]);
        admin.setPassword(password);
        admin.setStatus(UserStatus.ACTIVE);
        admin.setDateLastPasswordChange(LocalDate.now().toString());
        return admin;
    }
}