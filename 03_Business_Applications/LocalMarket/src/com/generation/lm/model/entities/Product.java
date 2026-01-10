package com.generation.lm.model.entities;

/**
 * Un prodotto è qualcosa che compro da un Supplier per rivenderlo
 * 
 */
public class Product extends Entity
{
	protected String 	name, description;
	protected int 		unitPrice; // in CENTESIMI..., ATTUALE PREZZO DI MERCATO!
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public int getUnitPrice() 
	{
		return unitPrice;
	}
	
	public void setUnitPrice(int unitPrice)
	{
		this.unitPrice = unitPrice;
	}

	// TODO: Override isValid() per Product
	// Verificare che:
	// - id > 0
	// - name non sia null o vuoto
	// - description non sia null o vuoto
	// - unitPrice > 0
	@Override
	public boolean isValid()
	{
		if(!super.isValid())
			return false;
		return name != null 			&&
				!name.isEmpty() 		&&
				!name.isBlank() 		&&
				description 	!= null &&
				!description.isBlank() 	&&
				!description.isEmpty() 	&&
				unitPrice 		> 0;
	}
}
