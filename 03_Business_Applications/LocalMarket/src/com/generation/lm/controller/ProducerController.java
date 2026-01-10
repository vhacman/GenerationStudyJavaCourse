package com.generation.lm.controller;

import com.generation.library.Console;
import com.generation.lm.model.entities.Producer;
import com.generation.lm.model.repository.ProducerRepository;

public class ProducerController 
{
    private ProducerRepository 	producerRepo;
    
    public ProducerController(ProducerRepository producerRepo)
    {
        this.producerRepo = producerRepo;
    }
    
    public void addProducer()
    {
        Console.print("Inserisci ID: ");
        int 		checkID 	= Console.readInt();
        Producer	producer 	= producerRepo.load(checkID);       
        if (producer != null) 
        {
            Console.print("ERRORE: Questo ID è già usato!");
            return;
        }        
        producer = new Producer();
        producer.setId(checkID);        
        Console.print("Inserisci nome legale: ");
        producer.setLegalName(Console.readString());       
        Console.print("Inserisci indirizzo: ");
        producer.setAddress(Console.readString());        
        Console.print("Inserisci storia/note: ");
        producer.setHistory(Console.readString());
        producer.setActive(true);        
        if (!producer.isValid())
        {
            Console.print("ERRORE: Producer non valido");
            return;
        }
        producerRepo.save(producer);
        Console.print("Produttore aggiunto con successo!");
    }
    
    public void banProducer()
    {
        Console.print("Inserire ID produttore da bannare: ");
        int 		producerID 		= Console.readInt();        
        Producer 	producerToBann 	= producerRepo.load(producerID);
        if (producerToBann == null)
        {
            Console.print("ERRORE: Produttore non trovato!");
            return; 
        }
        if (!producerToBann.isActive())
        {
            Console.print("ERRORE: Produttore già bannato!");
            return; 
        }
        printProducerDetails(producerToBann);
        Console.print("Si vuole bannare questo produttore?");
        if(Console.readString().equals("S"))
        {
            producerToBann.setActive(false);          
            if(!producerToBann.isValid())
            {
                Console.print("ERRORE: Produttore non valido");
                return;
            }
            producerRepo.save(producerToBann);
            Console.print("Salvato");
        }
        else
            Console.print("Annullato");
    }
    
    public void unBanProducer()
    {
        Console.print("Inserire ID produttore da sbannare: ");
        int 		producerID 			= Console.readInt();        
        Producer 	producerToUnBann 	= producerRepo.load(producerID);
        if (producerToUnBann == null)
        {
            Console.print("ERRORE: Produttore non trovato!");
            return; 
        }
        if (producerToUnBann.isActive())
        {
            Console.print("ERRORE: Produttore già sbannato!");
            return; 
        }
        printProducerDetails(producerToUnBann);
        Console.print("Si vuole sbannare questo produttore?");
        if(Console.readString().equals("S"))
        {
            producerToUnBann.setActive(true);           
            if(!producerToUnBann.isValid())
            {
                Console.print("ERRORE: Produttore non valido");
                return;
            }            
            producerRepo.save(producerToUnBann);
            Console.print("Salvato");
        }
        else
            Console.print("Annullato");
    }
    
    public void changeProducerAddress()
    {
        Console.print("Inserire ID produttore: ");
        int 		producerID 	= Console.readInt();        
        Producer 	producer	= producerRepo.load(producerID);        
        if(producer == null)
        {
            Console.print("ERRORE: Produttore non trovato!");
            return;
        } 
        printProducerDetails(producer);        
        Console.print("Inserire nuovo indirizzo: ");
        String 	newAddress = Console.readString();        
        producer.setAddress(newAddress);        
        if(!producer.isValid())
        {
            Console.print("ERRORE: Produttore non valido");
            return;
        }        
        producerRepo.save(producer);
        Console.print("Salvato");
    }
    
    public static void printProducerDetails(Producer p)
    {
        Console.print("ID Produttore "  + p.getId());
        Console.print("Nome legale "    + p.getLegalName());
        Console.print("Indirizzo "      + p.getAddress());
        Console.print("Storia/Note "    + p.getHistory());
        Console.print("Attivo "         + (p.isActive() ? "Sì" : "No"));
    }
}