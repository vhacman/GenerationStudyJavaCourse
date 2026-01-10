package com.generation.lm.controller;

import com.generation.library.Console;
import com.generation.lm.model.entities.Product;
import com.generation.lm.model.repository.ProductRepository;

public class ProductController 
{
    private ProductRepository productRepo;
    
    public ProductController(ProductRepository productRepo)
    {
        this.productRepo = productRepo;
    }
    
    public void addProduct()
    {
        Console.print("Inserisci ID: ");
        int checkID = Console.readInt();
        Product product = productRepo.load(checkID);
        
        if (product != null) 
        {
            Console.print("ERRORE: Questo ID è già usato!");
            return;
        }
        
        product = new Product();
        product.setId(checkID);
        
        Console.print("Inserisci nome prodotto: ");
        product.setName(Console.readString());
        
        Console.print("Inserisci descrizione prodotto: ");
        product.setDescription(Console.readString());
        
        Console.print("Inserisci prezzo unitario: ");
        product.setUnitPrice(Console.readInt());
        
        if (!product.isValid())
        {
            Console.print("ERRORE: Prodotto non valido");
            return;
        } 
        productRepo.save(product);
        Console.print("Prodotto aggiunto con successo!");
    }
    
    public void changeProductPrice()
    {
        Console.print("Inserire ID prodotto: ");
        int productID = Console.readInt();

        Product product = productRepo.load(productID);

        if(product == null)
        {
            Console.print("ERRORE: Prodotto non trovato!");
            return;
        }

        printProductDetails(product);

        Console.print("Inserire nuovo prezzo unitario: ");
        int newPrice = Console.readInt();

        product.setUnitPrice(newPrice);

        if(!product.isValid())
        {
            Console.print("ERRORE: Prodotto non valido");
            return;
        }

        productRepo.save(product);
        Console.print("Salvato");
    }
    
    public void changeProductDescription()
    {
        Console.print("Inserire ID prodotto: ");
        int productID = Console.readInt();
        
        Product product = productRepo.load(productID);
        
        if(product == null)
        {
            Console.print("ERRORE: Prodotto non trovato!");
            return;
        }
        
        printProductDetails(product);
        
        Console.print("Inserire nuova descrizione: ");
        String newDescription = Console.readString();
        
        product.setDescription(newDescription);
        
        if(!product.isValid())
        {
            Console.print("ERRORE: Prodotto non valido");
            return;
        }
        
        productRepo.save(product);
        Console.print("Salvato");
    }
    
    public static void printProductDetails(Product p)
    {
        Console.print("ID Prodotto: "      + p.getId());
        Console.print("Nome: "             + p.getName());
        Console.print("Descrizione: "      + p.getDescription());
        Console.print("Prezzo unitario: "  + p.getUnitPrice());
    }
}