package com.generation.lmdb.demo;

import com.generation.library.Console;
import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Product;
import com.generation.lmdb.model.repository.ProductRepository;


public class InsertProductDemo {

	public static void main(String[] args)
	{
		ProductRepository 	productRepo = (ProductRepository) Context.getDependency(ProductRepository.class);
		Product 			product 	= new Product();
		Console.print("Inserire nome: ");
		product.setName(Console.readString());
		Console.print("Inserire descrizione: ");
		product.setDescription(Console.readString());
		Console.print("Inserire Prezzo Unitario: ");
		product.setUnitPrice(Console.readInt());

		productRepo.insert(product);

	}

}
