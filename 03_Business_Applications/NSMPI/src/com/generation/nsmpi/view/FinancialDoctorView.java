package com.generation.nsmpi.view;

import com.generation.library.Template;
import com.generation.nsmpi.model.entities.Doctor;
import com.generation.nsmpi.model.entities.Specialty;

/**
 * FullDoctorView è un sottotipo di DoctorView.
 * Renderizza Doctor completo usando template con tutti i campi.
 */
class FinancialDoctorView extends FullDoctorView
{
    protected String filename;

    public          FinancialDoctorView()                    {}
    public          FinancialDoctorView(String filename)     { this.filename = filename; }

    public String   getFilename()                      		 { return filename;          }
    public void     setFilename(String filename)        	 { this.filename = filename; }

    @Override
    public String render(Doctor d)
    {
        String res = Template.load(filename);

        res = res.replace("[id]",       	String.valueOf(d.getId()))
                 .replace("[firstName]", 	d.getFirstName())
                 .replace("[lastName]",  	d.getLastName())
                 .replace("[salary]", 		String.valueOf(d.getSalary()));

        return res;
    }
}