package com.generation.nsmpi.view;

import com.generation.library.Template;
import com.generation.nsmpi.model.entities.Patient;

class AnonymousPatientView extends FullPatientView
{
	
	public AnonymousPatientView(String filename) { super(filename); }
	
	@Override
    public String render(Patient p)
    {
        String		res = Template.load(filename);
        res = res.replace("[year]", 	p.getDob().getYear() + "")
                 .replace("[gender]",	p.getGender()  		 + "")
                 .replace("[history]", 	p.getHistory() 		 + "");     
        
        String 		allergiesString = "";
        for(int i = 0; i < p.getAllergies().size(); i++)
        {
            String		allergy  = p.getAllergies().get(i);
            allergiesString 	+= allergy + (i < p.getAllergies().size() - 1 ? ", " : "");
        }
        res = res.replace("[allergies]", allergiesString);
        return res;
    }
}
