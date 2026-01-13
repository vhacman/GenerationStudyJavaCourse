package com.generation.nsmpi.view;


//la natura abolisce doppioni: non dovrei mai avere due oggetti guuali. 
public class DoctorViewFactory
{
	static DoctorView fullViewTXT      	    = new FullDoctorView      ("template/doctorTemplateFULL.txt"     );
	static DoctorView financialViewTXT   	= new FinancialDoctorView ("template/doctorTemplateFINANCIAL.txt");

	//clerkId --> id, nome, cognome, dob SOLO

	
	public static DoctorView make(String role)
	{
		switch (role)
		{
			case "full":
				return fullViewTXT;
			case "financial":
				return financialViewTXT;
			default:
				return null;
		}
	}
}
