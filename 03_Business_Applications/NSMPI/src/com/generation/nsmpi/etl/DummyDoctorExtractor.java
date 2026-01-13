package com.generation.nsmpi.etl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.generation.nsmpi.model.entities.Doctor;
import com.generation.nsmpi.model.entities.Gender;
import com.generation.nsmpi.model.entities.Specialty;

/**
 * ESTRATTORE FARLOCCO PER DOTTORI
 */
public class DummyDoctorExtractor implements DoctorExtractor
{

    @Override
    public List<Doctor> getDoctorsFromFile(String filename)
    {
    	List<Doctor> res = new ArrayList<Doctor>();

        res.add(new Doctor("Gregory",    "House",     LocalDate.of(1959, 6, 11),  Gender.M, Arrays.asList(Specialty.INTERNAL_MEDICINE),                                      85000));
        res.add(new Doctor("Meredith",   "Grey",      LocalDate.of(1978, 3, 10),  Gender.F, Arrays.asList(Specialty.GENERAL_SURGERY),                                        75000));
        res.add(new Doctor("John",       "Watson",    LocalDate.of(1852, 8, 7),   Gender.M, Arrays.asList(Specialty.GENERAL_SURGERY, Specialty.EMERGENCY_MEDICINE),          45000));
        res.add(new Doctor("Beverly",    "Crusher",   LocalDate.of(2324, 10, 13), Gender.F, Arrays.asList(Specialty.EMERGENCY_MEDICINE, Specialty.INTERNAL_MEDICINE),        92000));
        res.add(new Doctor("Leonard",    "McCoy",     LocalDate.of(2227, 1, 20),  Gender.M, Arrays.asList(Specialty.EMERGENCY_MEDICINE, Specialty.INTERNAL_MEDICINE),        88000));
        res.add(new Doctor("Stephen",    "Strange",   LocalDate.of(1930, 11, 18), Gender.M, Arrays.asList(Specialty.NEUROLOGY, Specialty.GENERAL_SURGERY),                  120000));
        res.add(new Doctor("Temperance", "Brennan",   LocalDate.of(1976, 7, 4),   Gender.F, Arrays.asList(Specialty.ORTHOPEDICS_TRAUMA),                                     70000));
        res.add(new Doctor("Hannibal",   "Lecter",    LocalDate.of(1933, 1, 20),  Gender.M, Arrays.asList(Specialty.PSYCHIATRY),                                             95000));
        res.add(new Doctor("Cristina",   "Yang",      LocalDate.of(1980, 5, 12),  Gender.F, Arrays.asList(Specialty.CARDIOLOGY, Specialty.GENERAL_SURGERY),                  82000));
        res.add(new Doctor("Derek",      "Shepherd",  LocalDate.of(1966, 4, 13),  Gender.M, Arrays.asList(Specialty.NEUROLOGY, Specialty.GENERAL_SURGERY),                   95000));
        res.add(new Doctor("Miranda",    "Bailey",    LocalDate.of(1969, 9, 14),  Gender.F, Arrays.asList(Specialty.GENERAL_SURGERY, Specialty.EMERGENCY_MEDICINE),          78000));
        res.add(new Doctor("Mindy",      "Lahiri",    LocalDate.of(1979, 9, 1),   Gender.F, Arrays.asList(Specialty.GYNECOLOGY_OBSTETRICS),                                  72000));
        res.add(new Doctor("Douglas",    "Ross",      LocalDate.of(1967, 3, 26),  Gender.M, Arrays.asList(Specialty.PEDIATRICS, Specialty.EMERGENCY_MEDICINE),               68000));
        res.add(new Doctor("Michaela",   "Quinn",     LocalDate.of(1833, 2, 15),  Gender.F, Arrays.asList(Specialty.INTERNAL_MEDICINE, Specialty.GENERAL_SURGERY),           42000));
        res.add(new Doctor("Mark",       "Greene",    LocalDate.of(1958, 5, 8),   Gender.M, Arrays.asList(Specialty.EMERGENCY_MEDICINE),                                     74000));
        res.add(new Doctor("Lisa",       "Cuddy",     LocalDate.of(1968, 11, 12), Gender.F, Arrays.asList(Specialty.INTERNAL_MEDICINE, Specialty.GYNECOLOGY_OBSTETRICS),     98000));
        res.add(new Doctor("Ethan",      "Choi",      LocalDate.of(1980, 6, 22),  Gender.M, Arrays.asList(Specialty.EMERGENCY_MEDICINE, Specialty.ANESTHESIOLOGY),           76000));
        res.add(new Doctor("Claire",     "Browne",    LocalDate.of(1988, 12, 5),  Gender.F, Arrays.asList(Specialty.GENERAL_SURGERY),                                        62000));

        return res;
    }
}
