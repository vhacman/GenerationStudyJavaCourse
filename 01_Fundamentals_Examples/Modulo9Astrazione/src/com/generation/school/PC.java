package com.generation.school;

public class PC implements Validable
{
	protected String 	SSN, model;
	protected int 		price;
	
	public PC() {};
	
	public String 		getSSN() 				{ return SSN; 	}
	public String 		getModel() 				{ return model; }
	public int 			getPrice() 				{ return price; }
	
	public void 		setSSN(String sSN) 		{ SSN = sSN; 	}
	public void 		setPrice(int price) 	{ this.price = price; }
	public void 		setModel(String model) 	{ this.model = model; }

	public PC(String sSN, String model, int price)
	{
		super();
		SSN = sSN;
		this.model = model;
		this.price = price;
	}
	
	public boolean isValid()
	{
		return SSN != null 			&&
				!SSN.isBlank() 		&&
				!SSN.isEmpty() 		&& 
				price >= 0 			&& 
				model != null 		&&
				!model.isBlank() 	&& 
				!model.isEmpty();
	}
}
