package com.generation.lm.controller;

import java.time.LocalDate;
import com.generation.library.Console;
import com.generation.lm.model.entities.Batch;
import com.generation.lm.model.entities.BatchStatus;
import com.generation.lm.model.entities.Producer;
import com.generation.lm.model.entities.Product;
import com.generation.lm.model.repository.BatchRepository;
import com.generation.lm.model.repository.ProducerRepository;
import com.generation.lm.model.repository.ProductRepository;

public class BatchController 
{
    private BatchRepository 	batchRepo;
    private ProductRepository 	productRepo;
    private ProducerRepository 	producerRepo;
    
    public BatchController(BatchRepository batchRepo, 
    						ProductRepository productRepo, ProducerRepository producerRepo)
    {
        this.batchRepo = batchRepo;
        this.productRepo = productRepo;
        this.producerRepo = producerRepo;
    }
    
    public void addBatch()
    {
        Console.print("Inserire id produttore");
        int 		producerId 	= Console.readInt();
        Producer 	producer 	= producerRepo.load(producerId);        
        if(producer == null)
        {
            Console.print("Produttore non trovato");
            return;
        }        
        if(!producer.isActive())
        {
            Console.print("ERRORE: Fornitore non attivo!");
            return;
        }      
        Console.print("Selezionato " + producer.getLegalName());
        Console.print("Inserire id prodotto");
        int 	productId 	= Console.readInt();
        Product product 	= productRepo.load(productId);     
        if(product == null)
        {
            Console.print("Prodotto non trovato");
            return;
        }
        Console.print("Selezionato "+ product.getName());
        Console.print("Prezzo ad oggi "+ product.getUnitPrice());
        
        Batch 	b = new Batch();
        
        Console.print("Inserire id batch");
        b.setId(Console.readInt());
        b.setProduct(product);
        b.setProducer(producer);
        b.setUnitPrice(product.getUnitPrice());
        
        Console.print("Inserire quantità");
        b.setQuantity(Console.readInt());
        b.setDate(LocalDate.now());
        b.setNotes("NESSUNA");
        b.setStatus(BatchStatus.PENDING);
        
        if(!b.isValid())
        {
            Console.print("ERRORE: Batch non valido");
            return;
        }
        batchRepo.save(b);
        Console.print("Salvato con successo");
    }
    
    public void revokeBatch()
    {
        Console.print("Inserire id batch");
        int 	id = Console.readInt();
        Batch 	batch = batchRepo.load(id);
        
        if(batch.getStatus() == BatchStatus.CORRUPT)
        {
            Console.print("ERRORE: Batch già revocato!");
            return;
        }
        
        printBatchDetails(batch);
        Console.print("Si vuole revocare questo batch?");        
        if(Console.readString().equals("S"))
        {
            Console.print("Inserire note di revoca");
            batch.setNotes(Console.readString());
            batch.setStatus(BatchStatus.CORRUPT);
            batchRepo.save(batch);
            Console.print("Salvato");
        }
        else
            Console.print("Annullato");
    }
    
    public void approveBatch()
    {
        Console.print("Inserire ID batch da approvare: ");
        int 	batchID 		= Console.readInt();
        Batch 	batchToApprove 	= batchRepo.load(batchID);
        
        if (batchToApprove == null)
        {
            Console.print("ERRORE ID");
            return; 
        }       
        if (batchToApprove.getStatus() == BatchStatus.VALIDATED)
        {
            Console.print("ERRORE: Batch già approvato!");
            return;
        }       
        printBatchDetails(batchToApprove);
        Console.print("Si vuole approvare questo batch?");
        if(Console.readString().equals("S"))
        {
            batchToApprove.setStatus(BatchStatus.VALIDATED);
            if(!batchToApprove.isValid())
            {
                Console.print("Errore: Batch non valido.");
                return; 
            }
            batchRepo.save(batchToApprove);
            Console.print("Salvato");
        }
        else
            Console.print("Annullato");
    }
    
    public void addBatchCustomPrice()
    {
        Console.print("Inserire ID produttore: ");
        int 		producerID 	= Console.readInt();
        Producer 	producer 	= producerRepo.load(producerID);
        
        if(producer == null)
        {
            Console.print("ERRORE: produttore non trovato!");
            return;
        }      
        if(!producer.isActive())
        {
            Console.print("ERRORE: produttore non attivo!");
            return;
        }       
        ProducerController.printProducerDetails(producer);
        Console.print("Inserisci ID prodotto: ");
        int productID = Console.readInt();
        Product product = productRepo.load(productID);        
        if(product == null)
        {
            Console.print("ERRORE: prodotto non trovato!");
            return;            
        }      
        ProductController.printProductDetails(product);      
        Batch 	batch = new Batch();
        Console.print("Inserire ID batch: ");
        batch.setId(Console.readInt());
        
        batch.setProduct(product);
        batch.setProducer(producer);
        
        Console.print("Inserire nuovo prezzo unitario: ");
        batch.setUnitPrice(Console.readInt());
        
        Console.print("Inserire quantità: ");
        batch.setQuantity(Console.readInt());
        
        batch.setDate(LocalDate.now());
        batch.setNotes("NESSUNA");
        batch.setStatus(BatchStatus.PENDING);
        
        if(!batch.isValid())
        {
            Console.print("ERRORE: Batch non valido");
            return;
        }
        
        batchRepo.save(batch);
        Console.print("Salvato con successo");
    }
    
    public static void printBatchDetails(Batch b)
    {
        Console.print("Batch con id "      + b.getId());
        Console.print("Nome prodotto "     + b.getProduct().getName());
        Console.print("Fornitore "         + b.getProducer().getLegalName());
        Console.print("Quantità "          + b.getQuantity());
        Console.print("Prezzo unitario "   + b.getUnitPrice());
        Console.print("Prezzo attuale "    + b.getProduct().getUnitPrice());
        Console.print("Note "              + b.getNotes());
        Console.print("Costo totale "      + b.getPrice());
        Console.print("Stato attuale "     + b.getStatus());
    }
}