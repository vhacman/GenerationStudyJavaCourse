package com.generation.lmdb.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import com.generation.lmdb.context.Context;
/*
 * CONCETTI TEORICI - GESTIONE DELLE RELAZIONI NEL DATABASE
 *
 * Batch ha relazioni con Producer e Product.
 * Nel database NON salviamo gli OGGETTI completi, ma solo gli ID (chiavi esterne).
 *
 * TABELLA BATCH NEL DATABASE:
 * ┌────┬──────┬────────────┬───────────┬──────────┬───────────┬───────┬──────────┐
 * │ id │ date │ producerId │ productId │ quantity │ unitPrice │ notes │  status  │
 * ├────┼──────┼────────────┼───────────┼──────────┼───────────┼───────┼──────────┤
 * │ 1  │ ...  │     5      │     3     │   100    │   1050    │  ...  │ VALIDATED│
 * └────┴──────┴────────────┴───────────┴──────────┴───────────┴───────┴──────────┘
 *                    ↓            ↓
 *           Riferimento a    Riferimento a
 *           Producer(5)      Product(3)
 *
 * FOREIGN KEY (CHIAVE ESTERNA):
 * - producerId è una FOREIGN KEY che punta alla tabella Producer
 * - productId è una FOREIGN KEY che punta alla tabella Product
 * - Salvare solo l'ID invece dell'oggetto completo si chiama NORMALIZZAZIONE
 *
 * QUANDO INSERIAMO:
 * - batch.getProducer() → ritorna un OGGETTO Producer completo
 * - batch.getProducer().getId() → estraiamo solo l'ID da salvare nel database
 *
 * QUANDO LEGGIAMO:
 * - Il database ci dà producerId = 5
 * - Dobbiamo fare una QUERY SEPARATA per ottenere i dati completi del Producer(5)
 * - Questo si chiama JOIN o EAGER LOADING (vedi findById)
 */
import com.generation.lmdb.model.entities.Batch;


public class BatchRepository
{
	Connection connection = (Connection) Context.getDependency(Connection.class);

	public void insert(Batch batch)
	{
		try
		{
			PreparedStatement	sqlCmd = connection.prepareStatement("insert into batch(date, "
					+ "																	producerId, productId, "
					+ "																	quantity,"
					+ "																	unitPrice,"
					+ "																	notes,"
					+ "																	status) "
					+ "												values(?,?,?,?,?,?,?);");
			sqlCmd.setString(1,  batch.getDate().toString());
			sqlCmd.setInt(2,  batch.getProducer().getId());
			sqlCmd.setInt(3,  batch.getProduct().getId());
			sqlCmd.setInt(4, batch.getQuantity());
			sqlCmd.setInt(5, batch.getUnitPrice());
			sqlCmd.setString(6, batch.getNotes());
			sqlCmd.setString(7,  batch.getStatus().toString());
			sqlCmd.execute(); //per eseguire
			sqlCmd.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 *  EAGER LOADING E COMPOSIZIONE DI OGGETTI
	 *
	 * PROBLEMA:
	 * - Nel database abbiamo solo producerId e productId (int)
	 * - Nell'oggetto Batch vogliamo producer e product COMPLETI (oggetti)
	 *
	 * SOLUZIONE - EAGER LOADING:
	 * 1. Leggiamo il Batch dal database
	 * 2. Troviamo producerId e productId
	 * 3. Chiamiamo producerRepo.findById() e productRepo.findById()
	 * 4. Otteniamo gli oggetti completi Producer e Product
	 * 5. Li assegniamo al Batch
	 *
	 * DEPENDENCY INJECTION DEI REPOSITORY:
	 * - Abbiamo bisogno di ProductRepository e ProducerRepository
	 * - Li prendiamo dal Context (invece di creare nuove istanze)
	 * - Questo garantisce che usiamo SEMPRE le stesse istanze condivise
	 *
	 * CHIAMATE ANNIDATE:
	 * row.getInt("producer") → ottiene producerId (es: 5)
	 * producerRepo.findById(5) → fa una SELECT sul database per Producer(5)
	 * → ritorna un oggetto Producer completo
	 * res.setProducer(...) → assegna l'oggetto Producer al Batch
	 *
	 */
	public Batch findById(int id)
	{
		ProductRepository productRepo = (ProductRepository) Context.getDependency(ProductRepository.class);
		ProducerRepository producerRepo = (ProducerRepository) Context.getDependency(ProducerRepository.class);

		String sql = "select * from batch where id = ?";

		try (PreparedStatement sqlCmd = connection.prepareStatement(sql))
		{
			sqlCmd.setInt(1, id);

			try (ResultSet row = sqlCmd.executeQuery())
			{
				if(row.next())
				{
					Batch res = new Batch();
					res.setId(row.getInt("id"));
					res.setDate(LocalDate.parse(row.getString("date")));
					res.setProducer(producerRepo.findById(row.getInt("producerid")));
					res.setProduct(productRepo.findById(row.getInt("productid")));
					res.setQuantity(row.getInt("quantity"));
					res.setUnitPrice(row.getInt("unitPrice"));
					res.setNotes(row.getString("notes"));
					res.setStatus(row.getString("status"));
					return res;
				}
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * UPDATE CON FOREIGN KEY:
	 * Quando aggiorniamo un Batch, possiamo cambiare:
	 * - La data
	 * - Il Producer associato (producerId)
	 * - Il Product associato (productId)
	 * - Quantity, unitPrice, notes, status
	 *
	 * GESTIONE DELLE RELAZIONI NELL'UPDATE:
	 * - batch.getProducer().getId() → estraiamo l'ID del nuovo Producer
	 * - batch.getProduct().getId() → estraiamo l'ID del nuovo Product
	 * - Salviamo solo gli ID nel database (non gli oggetti completi)
	 *
	 * ESEMPI DI USO:
	 * - Correggere errori di inserimento
	 * - Cambiare stato (es: PENDING → VALIDATED)
	 * - Riassegnare batch a un altro Producer
	 * - Aggiornare quantità o prezzo
	 *
	 * ATTENZIONE:
	 * Cambiare producerId o productId significa associare il batch
	 * a entità diverse! Assicurati che abbia senso logicamente.
	 */
	public void update(Batch batch)
	{
		try
		{
			/*
			 * QUERY UPDATE PER BATCH
			 *
			 * UPDATE batch
			 * SET date = ?, producerId = ?, productId = ?, quantity = ?,
			 *     unitPrice = ?, notes = ?, status = ?
			 * WHERE id = ?
			 *
			 * PARAMETRI (8 totali):
			 * 1-7: nuovi valori da salvare (clausola SET)
			 * 8: ID del batch da modificare (clausola WHERE)
			 *
			 * ORDINE IMPORTANTE:
			 * I parametri SET vengono prima, poi il parametro WHERE.
			 * Devono corrispondere esattamente all'ordine dei ? nella query.
			 *
			 * FOREIGN KEY NELL'UPDATE:
			 * - producerId (parametro 2): riferimento a Producer
			 * - productId (parametro 3): riferimento a Product
			 * - Il database verifica che questi ID esistano nelle tabelle
			 *   producer e product (se hai FOREIGN KEY constraints)
			 */
			PreparedStatement	sqlCmd = connection.prepareStatement("update batch set date = ?, producerId = ?, productId = ?, quantity = ?, unitPrice = ?, notes = ?, status = ? where id = ?;");
			
			/*
			 * BINDING DEI PARAMETRI PER UPDATE
			 *
			 * PARAMETRI 1-7: valori da aggiornare (clausola SET)
			 * 1 → date (nuova data del batch)
			 * 2 → producerId (ID del Producer, FOREIGN KEY)
			 * 3 → productId (ID del Product, FOREIGN KEY)
			 * 4 → quantity (nuova quantità)
			 * 5 → unitPrice (nuovo prezzo unitario)
			 * 6 → notes (nuove note)
			 * 7 → status (nuovo stato: es. PENDING, VALIDATED, CANCELLED)
			 *
			 * PARAMETRO 8: identificatore (clausola WHERE)
			 * 8 → id (quale batch modificare)
			 *
			 * ESTRAZIONE DEGLI ID DALLE RELAZIONI:
			 * - batch.getProducer() → ritorna l'OGGETTO Producer completo
			 * - .getId() → estraiamo solo l'ID da salvare
			 * - Lo stesso per batch.getProduct().getId()
			 *
			 * CONVERSIONE DATA:
			 * - batch.getDate() → ritorna un LocalDate
			 * - .toString() → converte in String formato "YYYY-MM-DD"
			 * - SQLite salva le date come TEXT
			 */
			sqlCmd.setString(1,  batch.getDate().toString());
			sqlCmd.setInt(2,  batch.getProducer().getId());  // FOREIGN KEY
			sqlCmd.setInt(3,  batch.getProduct().getId());   // FOREIGN KEY
			sqlCmd.setInt(4,  batch.getQuantity());
			sqlCmd.setInt(5,  batch.getUnitPrice());
			sqlCmd.setString(6,  batch.getNotes());
			sqlCmd.setString(7,  batch.getStatus().toString());
			sqlCmd.setInt(8,  batch.getId());  // WHERE id = ?
			
			sqlCmd.execute();
			sqlCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
