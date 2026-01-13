package com.generation.nsmpi.demo;

import java.util.List;
import com.generation.library.Console;
import com.generation.nsmpi.etl.PatientExtractor;
import com.generation.nsmpi.etl.PatientExtractorFactory;
import com.generation.nsmpi.model.entities.Patient;
import com.generation.nsmpi.view.PatientView;
import com.generation.nsmpi.view.PatientViewFactory;

public class DemoPatientExtractor 
{
    public static void main(String[] args) 
    {
        PatientExtractor 	csvExtractor 		= PatientExtractorFactory.make("csv");
        PatientView      	doctorView   		= PatientViewFactory     .make("doctor");

        List<Patient> patientList = csvExtractor.getPatientsFromFile("testdata/patients.csv");

        for (Patient currentPatient : patientList) 
        {
            String 			renderedPatient 	= doctorView.render(currentPatient);
            Console.print(renderedPatient);
        }
    }
}