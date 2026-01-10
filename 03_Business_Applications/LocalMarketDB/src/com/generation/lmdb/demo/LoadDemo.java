package com.generation.lmdb.demo;

import com.generation.library.Console;
import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Batch;
import com.generation.lmdb.model.entities.Producer;
import com.generation.lmdb.model.entities.Product;
import com.generation.lmdb.model.repository.BatchRepository;
import com.generation.lmdb.model.repository.ProducerRepository;
import com.generation.lmdb.model.repository.ProductRepository;

public class LoadDemo {

	public static void main(String[] args)
	{
		ProductRepository productRepo = (ProductRepository) Context.getDependency(ProductRepository.class);

		Product	product = productRepo.findById(1);
		Console.print(product.toString());
		
		
		ProducerRepository producerRepo = (ProducerRepository) Context.getDependency(ProducerRepository.class);

		Producer	producer = producerRepo.findById(1);
		Console.print(producer.toString());
		
		BatchRepository batchRepo = (BatchRepository) Context.getDependency(BatchRepository.class);

		Batch	batch = batchRepo.findById(1);
		batch.setProducer(producer);
		batch.setProduct(product);
		Console.print(batch.toString());
		
		
	}

}
