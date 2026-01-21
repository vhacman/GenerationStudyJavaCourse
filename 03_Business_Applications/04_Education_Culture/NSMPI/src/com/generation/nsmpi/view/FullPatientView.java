package com.generation.nsmpi.view;

import com.generation.library.Template;
import com.generation.nsmpi.model.entities.Patient;

/**
 * TemplatePatientView è un sottotipo di PatientView
 * Renderizza Patient completo usando template con tutti i campi.
 */
class FullPatientView implements PatientView
{
    protected String		filename;

    public 			FullPatientView () 					{}
    public 			FullPatientView(String filename) 	{ this.filename = filename; }

    public String 	getFilename() 						{ return filename; 			}
    public void 	setFilename(String filename) 		{ this.filename = filename; }

    @Override
    public String render(Patient p)
    {
        String res = Template.load(filename);
        
        // Base da Person
        res = res.replace("[id]", 		 String.valueOf(p.getId()))
                 .replace("[firstName]", p.getFirstName())
                 .replace("[lastName]",  p.getLastName())
                 .replace("[dob]",       p.getDob() + "")
                 .replace("[gender]",    p.getGender() + "")
                 .replace("[history]",   p.getHistory() + "");                 
        // RIDUZIONE
        /**
         * utilizzato tutti gli elementi di una lista e sintetizzato in una stringa.
         */
        String allergiesString = "";
        for(int i = 0; i < p.getAllergies().size(); i++)
        {
            String allergy = p.getAllergies().get(i);
            allergiesString += allergy + (i < p.getAllergies().size() - 1 ? ", " : "");
        }
        res = res.replace("[allergies]", allergiesString);
        
        return res;
    }
}
