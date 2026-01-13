package com.generation.nsmpi.view;

import com.generation.library.Template;
import com.generation.nsmpi.model.entities.Doctor;
import com.generation.nsmpi.model.entities.Specialty;

/**
 * FullDoctorView è un sottotipo di DoctorView.
 * Renderizza Doctor completo usando template con tutti i campi.
 */
class FullDoctorView implements DoctorView
{
    protected String filename;

    public          FullDoctorView()                    {}
    public          FullDoctorView(String filename)     { this.filename = filename; }

    public String   getFilename()                       { return filename;          }
    public void     setFilename(String filename)        { this.filename = filename; }

    @Override
    public String render(Doctor d)
    {
        String res = Template.load(filename);

        res = res.replace("[id]",        String.valueOf(d.getId()))
                 .replace("[firstName]", d.getFirstName())
                 .replace("[lastName]",  d.getLastName())
                 .replace("[dob]",       d.getDob() + "")
                 .replace("[gender]",    d.getGender() + "")
                 .replace("[salary]", String.valueOf(d.getSalary()));

        String specialtiesString = "";
        for (int i = 0; i < d.getSpecialties().size(); i++)
        {
            Specialty specialty = d.getSpecialties().get(i);
            specialtiesString += specialty + (i < d.getSpecialties().size() - 1 ? ", " : "");
        }
        res = res.replace("[specialties]", specialtiesString);

        return res;
    }
}