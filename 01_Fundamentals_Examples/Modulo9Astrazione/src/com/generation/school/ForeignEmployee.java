package com.generation.school;

public class ForeignEmployee extends Employee
{
	protected String	nationality;
	protected String	visaType;
	protected String	nativeLanguage;

	public ForeignEmployee()
	{
		this.nationality = "";
		this.visaType = "";
		this.nativeLanguage = "";
	}

	public ForeignEmployee(String name, String surname, String dateOfBirth, String gender,
			   double salary, String nationality, String visaType, String nativeLanguage)
	{
		super(name, surname, dateOfBirth, gender, salary);

		this.nationality = nationality;
		this.visaType = visaType;
		this.nativeLanguage = nativeLanguage;
	}

	public String 	getNationality() 							{return nationality == null ? "UNKNOWN" : nationality;}
	public String 	getVisaType() 								{return visaType == null ? "UNKNOWN" : visaType;}
	public String 	getNativeLanguage() 						{return nativeLanguage == null ? "UNKNOWN" : nativeLanguage;}
	public void 	setNationality(String nationality) 			{this.nationality = nationality == null ? "UNKNOWN" : nationality;}
	public void 	setVisaType(String visaType) 				{this.visaType = visaType == null ? "UNKNOWN" : visaType;}
	public void 	setNativeLanguage(String nativeLanguage) 	{this.nativeLanguage = nativeLanguage == null ? "UNKNOWN" : nativeLanguage;}

	@Override
	public double getYearlyRetribution()
	{
		return salary * 13;
	}

	@Override
	public String toString()
	{
		return super.toString() + " Nationality: " +
					getNationality() + " VisaType: " +
					getVisaType() + " NativeLanguage: " +
					getNativeLanguage();
	}
	
	public boolean isValid()
	{
	    if (nationality == null || nationality.isEmpty()) return false;
	    if (visaType == null || visaType.isEmpty()) return false;
	    if (nativeLanguage == null || nativeLanguage.isEmpty()) return false;
	    return true; 
	}

	
}
