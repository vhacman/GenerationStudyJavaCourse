package com.generation.jb.model.entities;

public class Ticket
{
    private final static double PRICEPERKMFIRSTCLASS = 0.2;
    private final static double PRICEPERKMSECONDCLASS = 0.1;
    private final static double PRICEPERKMTHIRDCLASS = 0.05;

	public int			km;
	public int			level;
	public boolean 		greenCard;
	public int			id;
	
	//costruttore di default
	public Ticket() {}
	
	public Ticket(int km, int level, boolean greenCard, int id)
	{
		this.km = km;
		this.level = level;
		this.greenCard = greenCard;
		this.id = id;
	}
	
	public double getPrice()
	{
		double price = 0;
		switch (level)
		{
			case 1:
				price = km * PRICEPERKMFIRSTCLASS;
			break;
			case 2:
				price = km * PRICEPERKMSECONDCLASS;
			break;
			case 3:
				price = km * PRICEPERKMTHIRDCLASS;
			break;
		}
		
		//se hanno la green card hanno 50% di sconto
		if (greenCard)
			price *= 0.5;
		return price;
	}
	
	public boolean isValid()
	{
		if (id <= 0)
			return false;
		if (km <= 0)
			return false;
		if (level < 1 || level > 3)
			return false;
		return true;
		
	}
}
