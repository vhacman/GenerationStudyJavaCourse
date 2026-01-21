package com.generation.lm.model.entities;

import java.time.LocalDate;

/**
 * LOTTO DI ACQUISTO
 * ho comprato un batch di un prodotto p da un produttore prod
 * composto da n pezzi a un costo unitario u in data d
 */
public class Batch extends Entity
{
	protected 	LocalDate 	date; 		// data di acquisto
	protected 	Producer 	producer; 	// COLLEGAMENTO a un oggetto PRODUTTORE, da chi compro
	protected 	Product 	product;	// COLLEGAMENTO a un oggetto PRODOTTO, cosa compro
	protected	int			quantity;	// quanti pezzi sto comprando
	protected	int			unitPrice;	// prezzo al momento dell'acquisto
	protected	String		notes;		// fondamentale... se ci sono problemi lo devo sapere
	protected	BatchStatus	status;		// stato del lotto: conforme, non conforme, da valutare
	
	public LocalDate getDate() 
	{
		return date;
	}
	
	public void setDate(LocalDate date) 
	{
		this.date = date;
	}
	
	public void setDate(String date) 
	{
		this.date = LocalDate.parse(date);
	}
	
	
	public Producer getProducer() 
	{
		return producer;
	}
	
	public void setProducer(Producer producer) 
	{
		this.producer = producer;
	}
	
	public Product getProduct() 
	{
		return product;
	}
	
	public void setProduct(Product product) 
	{
		this.product = product;
	}
	
	public int getQuantity() 
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	public int getUnitPrice() 
	{
		return unitPrice;
	}
	
	public void setUnitPrice(int unitPrice) 
	{
		this.unitPrice = unitPrice;
	}
	
	public String getNotes() 
	{
		return notes;
	}
	
	public void setNotes(String notes) 
	{
		this.notes = notes;
	}
	
	public BatchStatus getStatus() 
	{
		return status;
	}
	
	public void setStatus(BatchStatus status) 
	{
		this.status = status;
	}
	
	public void setStatus(String status)
	{
		this.status = BatchStatus.valueOf(status);
	}
	
	/**
	 * Prezzo del batch al momento dell'acquisto
	 * @return
	 */
	public int getPrice()
	{
		return unitPrice * quantity;
	}

	// TODO: Override isValid() per Batch
	// Verificare che:
	// - id > 0
	// - producer non sia null
	// - product non sia null
	// - quantity > 0
	// - unitPrice > 0
	// - date non sia null
	// - notes non sia null
	// - status non sia null
	
	public boolean isValid() {

		return super.isValid() 		&&
				producer != null 	&&
				producer.isValid() 	&&
				product != null 	&&
				product.isValid() 	&&
				quantity > 0 		&&
				unitPrice > 0 		&&
				date != null 		&&
				notes != null 		&&
				status != null;
				
		
	}
	
	
	
	
	
	
	
}
