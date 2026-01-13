package com.generation.nsmpi.view;


//la natura abolisce doppioni: non dovrei mai avere due oggetti guuali. 
public class PatientViewFactory
{
	static PatientView fullViewTXT      = new FullPatientView("template/patientTemplateFULL.txt");
	static PatientView anonymousViewTXT = new AnonymousPatientView("template/patientTemplateForExternal.txt");
	static PatientView clerkViewTXT     = new ClerkPatientView("template/patientTemplateForClerk.txt");

	//clerkId --> id, nome, cognome, dob SOLO

	
	public static PatientView make(String role)
	{
		switch (role)
		{
			case "doctor":
				return fullViewTXT;
			case "student", "teacher":
				return anonymousViewTXT;
			case "clerk":
				return clerkViewTXT;
			default:
				return null;
		}
	}

}
