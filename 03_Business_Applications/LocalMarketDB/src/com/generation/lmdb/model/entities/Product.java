package com.generation.lmdb.model.entities;

/**
 * Un prodotto è qualcosa che compro da un Supplier per rivenderlo
 *
 */
public class Product extends Entity
{

	protected String 	name, description;
	protected int 		unitPrice; // in CENTESIMI..., ATTUALE PREZZO DI MERCATO!

	// Getter e Setter
	public String 	getName() 							{ return name; }
	public void 	setName(String name) 				{ this.name = name; }
	public String 	getDescription() 					{ return description; }
	public void 	setDescription(String description) 	{ this.description = description; }
	public int 		getUnitPrice() 						{ return unitPrice; }
	public void 	setUnitPrice(int unitPrice) 		{ this.unitPrice = unitPrice; }

	@Override
	public boolean isValid()
	{
		return name != null 			&&
				!name.isEmpty() 		&&
				!name.isBlank() 		&&
				description 	!= null &&
				!description.isBlank() 	&&
				!description.isEmpty() 	&&
				unitPrice 		> 0;
	}
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", unitPrice=" + unitPrice + "]";
	}

}
