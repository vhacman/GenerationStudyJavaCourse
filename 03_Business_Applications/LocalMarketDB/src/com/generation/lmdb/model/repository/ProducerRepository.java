package com.generation.lmdb.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Producer;

/**
 *  REPOSITORY PER PRODUCER
 *
 * Questa classe gestisce la PERSISTENZA (salvataggio permanente) dei Producer nel database.
 * È strutturata come ProductRepository, ma gestisce i dati specifici di Producer.
 *
 * DIFFERENZE CON PRODUCTREPOSITORY:
 * - Ha 4 campi invece di 3: legalName, address, history, active
 * - Usa setBoolean() per il campo active (tipo boolean)
 * - Tabella database: "producer" invece di "product"
 */

/**
 * Permanenza dei Producer
 */
public class	ProducerRepository
{
	Connection connection = (Connection) Context.getDependency(Connection.class);
	public void insert(Producer producer)
	{
		try
		{
			/*
			 * QUERY SQL PER PRODUCER
			 *
			 * INSERT INTO producer(legalName, address, history, active)
			 * VALUES (?, ?, ?, ?)
			 *
			 * COLONNE:
			 * 1. legalName → nome legale del produttore/fornitore
			 * 2. address → indirizzo fisico
			 * 3. history → storico/note (es: motivi di ban)
			 * 4. active → boolean, true se attivo, false se bannato
			 *
			 * NOTA: l'ID non viene inserito perché è AUTOINCREMENT
			 * Il database lo genera automaticamente.
			 */
			//java vede come stringhe ma in realtà sono comandi in linguaggio sql. quando scrivo insert into --> sto dicendo al
			//database dove inserire quello che io voglio, in questo caso in producer.
																															//?,?,?,? --> segnaposti (4 valori)
			PreparedStatement	sqlCmd = connection.prepareStatement("insert into producer(legalName, address, history, active) values(?,?,?,?);");

			/*
			 * BINDING DEI PARAMETRI
			 *
			 * ORDINE IMPORTANTE:
			 * I numeri (1, 2, 3, 4) devono corrispondere all'ordine dei ? nella query
			 * 1 → primo ?  (legalName)
			 * 2 → secondo ? (address)
			 * 3 → terzo ?   (history)
			 * 4 → quarto ?  (active)
			 *
			 * METODI DIVERSI PER TIPI DIVERSI:
			 * - setString() → per String (legalName, address, history)
			 * - setBoolean() → per boolean (active)
			 * - setInt() → per int
			 * - setDate() → per Date
			 */
			sqlCmd.setString(1,  producer.getLegalName());
			sqlCmd.setString(2,  producer.getAddress());
			sqlCmd.setString(3,  producer.getHistory());
			sqlCmd.setBoolean(4, producer.isActive());
			sqlCmd.execute(); //per eseguire
			sqlCmd.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * LETTURA PRODUCER DAL DATABASE
	 *
	 * LETTURA DEL BOOLEAN DA SQL:
	 * - row.getBoolean("active") → legge il valore dalla colonna "active"
	 * - Nel database è salvato come INTEGER (0 o 1)
	 * - JDBC lo converte automaticamente in boolean (false o true)
	 *
	 * CONVERSIONE AUTOMATICA:
	 * - Database: 0 → Java: false
	 * - Database: 1 → Java: true
	 * - Database: NULL → Java: false (default)
	 *
	 * MAPPING COMPLETO DATABASE → OGGETTO JAVA:
	 * ┌─────────────────────┬─────────────────────────────┐
	 * │  Colonna Database   │   Attributo Java            │
	 * ├─────────────────────┼───────────────────────── ───┤
	 * │ id (INTEGER)        │ producer.id (int)           │
	 * │ legalName (TEXT)    │ producer.legalName (String) │
	 * │ address (TEXT)      │ producer.address (String)   │
	 * │ history (TEXT)      │ producer.history (String)   │
	 * │ active (INTEGER)    │ producer.active (boolean)   │
	 * └─────────────────────┴─────────────────────────────┘
	 *
	 * Questo è il processo di DESERIALIZZAZIONE:
	 * da formato database → oggetto Java
	 */
	public Producer findById(int id)
	{
		String sql = "select * from producer where id = ?";

		try (PreparedStatement sqlCmd = connection.prepareStatement(sql))
		{
			sqlCmd.setInt(1, id);

			try (ResultSet row = sqlCmd.executeQuery())
			{
				if(row.next())
				{
					/*
					 * COSTRUZIONE OGGETTO PRODUCER
					 *
					 * 1. Creiamo un nuovo Producer vuoto
					 * 2. Popoliamo ogni campo leggendo dal ResultSet
					 * 3. Ritorniamo l'oggetto completo
					 *
					 * NOTA: row.getBoolean("active")
					 * - Legge la colonna "active" dal database
					 * - Converte INTEGER → boolean
					 * - Assegna al campo producer.active
					 */
					Producer res = new Producer();
					res.setId(row.getInt("id"));
					res.setLegalName(row.getString("legalName"));
					res.setAddress(row.getString("address"));
					res.setHistory(row.getString("history"));
					res.setActive(row.getBoolean("active"));
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
	 * DIFFERENZA TRA INSERT E UPDATE:
	 * - INSERT: crea una NUOVA riga nel database
	 * - UPDATE: modifica una riga ESISTENTE
	 *
	 * QUERY SQL UPDATE:
	 * UPDATE producer SET campo1 = ?, campo2 = ? WHERE id = ?
	 *
	 * CLAUSOLA WHERE:
	 * - WHERE id = ? → identifica QUALE producer modificare
	 * - SENZA WHERE → modificherebbe TUTTI i producer! (PERICOLOSO!)
	 *
	 * QUANDO SI USA:
	 * - Per modificare dati di un producer esistente
	 * - Per esempio: cambiare address, bannare/sbannare (active)
	 *
	 * NOTA: L'ID non cambia mai! È la chiave primaria.
	 */
	public void update(Producer producer)
	{
		try
		{
			/*
			 * QUERY UPDATE PER PRODUCER
			 *
			 * UPDATE producer
			 * SET legalName = ?, address = ?, history = ?, active = ?
			 * WHERE id = ?
			 *
			 * PARAMETRI:
			 * 1-4: nuovi valori da salvare (legalName, address, history, active)
			 * 5: ID del producer da modificare (clausola WHERE)
			 *
			 * ORDINE IMPORTANTE:
			 * I parametri nella clausola SET vengono prima,
			 * poi il parametro della clausola WHERE
			 *
			 * ESEMPIO:
			 * Se producer.id = 5, legalName = "Nuovo Nome"
			 * → Aggiorna SOLO il producer con id = 5
			 * → Gli altri producer NON vengono toccati
			 */
			PreparedStatement	sqlCmd = connection.prepareStatement("update producer set legalName = ?, address = ?, history = ?, active = ? where id = ?;");
			
			/*
			 * BINDING DEI PARAMETRI PER UPDATE
			 *
			 * PARAMETRI 1-4: valori da aggiornare (clausola SET)
			 * 1 → legalName (nuovo nome legale)
			 * 2 → address (nuovo indirizzo)
			 * 3 → history (nuova storia/note)
			 * 4 → active (nuovo stato: true = attivo, false = bannato)
			 *
			 * PARAMETRO 5: identificatore (clausola WHERE)
			 * 5 → id (quale producer modificare)
			 *
			 * ATTENZIONE ALL'ORDINE:
			 * L'ordine dei setXXX() deve corrispondere all'ordine dei ?
			 * nella query SQL!
			 */
			sqlCmd.setString(1,  producer.getLegalName());
			sqlCmd.setString(2,  producer.getAddress());
			sqlCmd.setString(3,  producer.getHistory());
			sqlCmd.setBoolean(4, producer.isActive());
			sqlCmd.setInt(5,  producer.getId());  // WHERE id = ?
			
			sqlCmd.execute();
			sqlCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}


