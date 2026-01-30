package com.generation.oc.view;

import java.time.LocalDate;

import com.generation.library.Template;
import com.generation.oc.model.entities.HealthService;
import com.generation.oc.model.entities.Patient;
import com.generation.oc.model.entities.Prestation;
/**
 * Contenitore per viste di uso comune
 */
public class Views 
{
	// una vista semplice per ricapitolare i pazienti da testo
	public final static EntityView<Patient> PATIENTRECAPVIEW = 
			p->p.getId()+" "+p.getFirstName()+" "+p.getLastName()+" "+p.getSsn()+" "+p.getDob()+"\n";
	
	// una vista semplice per ricapitolare gli health service da testo		
	public static final EntityView<HealthService> HEALTHSERVICERECAPVIEW =
			h->h.getId()+" "+h.getName()+" "+h.getPrice()+"\n";

	public static final EntityView<Prestation> PRESTATIONVIEW = 
			p-> 	"Prestazione "+p.getId()+" " +
					p.getHealthService().getName()+ " "+ p.getId()+" del "+ p.getDate()+ " per "+p.getPatient().getFullname()
					+" per euro "+p.getPrice()+" "+"\n";
			
	// niente dati paziente
	public static final EntityView<Prestation> PRESTATIONVIEWSHORT = 
			p-> 	"Prestazione "+p.getId()+" " +
					p.getHealthService().getName()+ " del "+ p.getDate() + "("+p.getPrice() + "euro)"
					+"\n";
					
			
	public static final EntityView<Patient> FULLPATIENTVIEW = 
			p-> Template.load("template/txt/patientfull.txt")
							.replace("[id]", p.getId()+"")
							.replace("[fullname]", p.getFullname())
							.replace("[dob]", p.getDob()+"")
							.replace("[ssn]", p.getSsn())
							.replace("[date]", LocalDate.now().toString())
							.replace("[prestations]", PRESTATIONVIEWSHORT.render(p.getPrestations()));
	
	
}
