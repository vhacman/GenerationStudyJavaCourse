package com.generation.nsmpi.view;

import com.generation.library.Template;
import com.generation.nsmpi.model.entities.Patient;

/**
 * TemplatePatientView è un sottotipo di PatientView
 * Renderizza Patient completo usando template con tutti i campi.
 */
class ClerkPatientView extends FullPatientView
{
    protected String		filename;

    public 			ClerkPatientView () 				{}
    public 			ClerkPatientView(String filename) 	{ this.filename = filename; }

    public String 	getFilename() 						{ return filename; 			}
    public void 	setFilename(String filename) 		{ this.filename = filename; }

    @Override
    public String render(Patient p)
    {
        String res = Template.load(filename);

        res = res.replace("[id]", 		 String.valueOf(p.getId()))
                 .replace("[firstName]", p.getFirstName())
                 .replace("[lastName]",  p.getLastName())
                 .replace("[dob]",       p.getDob() + "");
       
        return res;
    }
}
