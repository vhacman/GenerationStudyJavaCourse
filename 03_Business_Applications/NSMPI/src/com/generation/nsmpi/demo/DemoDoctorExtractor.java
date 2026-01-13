package com.generation.nsmpi.demo;

import java.util.List;
import com.generation.library.Console;
import com.generation.nsmpi.etl.DoctorExtractor;
import com.generation.nsmpi.etl.DoctorExtractorFactory;
import com.generation.nsmpi.model.entities.Doctor;
import com.generation.nsmpi.view.DoctorView;
import com.generation.nsmpi.view.DoctorViewFactory;

public class DemoDoctorExtractor
{
    public static void main(String[] args)
    {
        DoctorExtractor      dummyExtractor       = DoctorExtractorFactory.make("dummy");
        DoctorView           fullView             = DoctorViewFactory     .make("full");

        List<Doctor> doctorList = dummyExtractor.getDoctorsFromFile("testdata/doctors.csv");

        for (Doctor currentDoctor : doctorList)
        {
            String             renderedDoctor      = fullView.render(currentDoctor);
            Console.print(renderedDoctor);
        }
    }
}
