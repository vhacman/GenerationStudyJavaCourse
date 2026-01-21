package com.generation.lmdb.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.generation.lmdb.context.Context;
import com.generation.lmdb.model.entities.Product;

/*
 * CONCETTI TEORICI - PATTERN REPOSITORY
 *
 * Il REPOSITORY è un DESIGN PATTERN che separa la logica di accesso ai dati
 * dalla logica di business.
 *
 * RESPONSABILITÀ DEL REPOSITORY:
 * - Nascondere i dettagli di come i dati vengono salvati (database, file, API)
 * - Fornire metodi semplici per CRUD operations:
 *   - CREATE (insert)
 *   - READ (findById, findAll)
 *   - UPDATE (update)
 *   - DELETE (delete)
 * OGGETTI SQL USATI:
 * - Connection: 		rappresenta la connessione al database
 * - PreparedStatement: comando SQL preparato (sicuro contro SQL injection)
 * - ResultSet: 		risultato di una query (come una tabella di risultati)
 *
 * DEPENDENCY INJECTION (Context):
 * - Context.getDependency() è un sistema per ottenere oggetti condivisi
 * - Invece di creare ogni volta una nuova Connection, la prendiamo dal Context
 * - Il Context gestisce UNA SOLA istanza condivisa (pattern Singleton)
 */

public class	ProductRepository
{
	/*
	 * ATTRIBUTO CONNECTION
	 *
	 * Connection è l'oggetto che rappresenta il collegamento al database SQLite.
	 * Tutti i metodi di questo repository useranno questa connection.
	 *
	 * CASTING (Connection):
	 * - Context.getDependency() ritorna Object (tipo generico)
	 * - Dobbiamo dire a Java che è una Connection → (Connection) è il CAST
	 * - È come dire: "fidati, questo Object è in realtà una Connection"
	 */
	Connection connection = (Connection) Context.getDependency(Connection.class);

	/*
	 * CONCETTI TEORICI - PREPARED STATEMENT E SQL INJECTION
	 *
	 * PREPARED STATEMENT:
	 * Un PreparedStatement è un comando SQL "preparato" dove i valori sono inseriti
	 * in modo sicuro tramite segnaposto (?).
	 *
	 *
	 * CON PREPARED STATEMENT (SICURO):
	 *   String sql = "insert into product(name, description, unitPrice) values(?,?,?)";
	 *   sqlCmd.setString(1, name); → Java ESCAPE automaticamente i caratteri pericolosi
	 *
	 * I SEGNAPOSTO ? :
	 * - Il primo ? corrisponde a setString(1, ...)
	 * - Il secondo ? corrisponde a setString(2, ...)
	 * - Il terzo ? corrisponde a setInt(3, ...)
	 * - ATTENZIONE: si parte da 1, NON da 0!
	 *
	 * METODI setXxx:
	 * - setString(index, value) → per colonne VARCHAR/TEXT
	 * - setInt(index, value) → per colonne INTEGER
	 * - setBoolean(index, value) → per colonne BOOLEAN
	 * - setDate(index, value) → per colonne DATE
	 *
	 * CHIUSURA RISORSE:
	 * - sqlCmd.close() → libera le risorse del PreparedStatement
	 * - È importante chiudere per evitare memory leak
	 * - In Java moderno si usa try-with-resources (vedi metodo findById)
	 */
	public void insert(Product product)
	{
		try
		{
			PreparedStatement sqlCmd = connection.prepareStatement(
				"insert into product(name, description, unitPrice) values(?,?,?);"
			);
			sqlCmd.setString(1, product.getName());
			sqlCmd.setString(2, product.getDescription());
			sqlCmd.setInt(3, product.getUnitPrice());
			sqlCmd.execute();
			sqlCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * CONCETTI TEORICI - RESULTSET E TRY-WITH-RESOURCES
	 *
	 * RESULTSET:
	 * - È il risultato di una query SELECT
	 * - Si comporta come un CURSORE che scorre le righe del risultato
	 * - row.next() → sposta il cursore alla riga successiva e ritorna true se esiste
	 *
	 * COME FUNZIONA row.next():
	 * - All'inizio il cursore è PRIMA della prima riga
	 * - row.next() lo sposta alla prima riga e ritorna true
	 * - Se non ci sono righe, ritorna false
	 * - Per scorrere tutte le righe: while(row.next()) { ... }
	 * - Per prendere solo la prima: if(row.next()) { ... }
	 *
	 * METODI getXxx DEL RESULTSET:
	 * - row.getInt("id") → legge la colonna "id" come int
	 * - row.getString("name") → legge la colonna "name" come String
	 * - Il nome tra parentesi DEVE corrispondere al nome della colonna nel database
	 *
	 */
	public Product findById(int id)
	{
		String sql = "select * from product where id = ?";

		try (PreparedStatement sqlCmd = connection.prepareStatement(sql))
		{
			sqlCmd.setInt(1, id);

			try (ResultSet row = sqlCmd.executeQuery())
			{
				if(row.next())
				{
					Product res = new Product();
					res.setId(row.getInt("id"));
					res.setName(row.getString("name"));
					res.setDescription(row.getString("description"));
					res.setUnitPrice(row.getInt("unitPrice"));
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
	
	
	public void update(Product product)
	{
		try
		{
			PreparedStatement	sqlCmd = connection.prepareStatement("update product set name = ?, description = ?, unitPrice = ? where id = ?;");
			sqlCmd.setString(1,  product.getName());
			sqlCmd.setString(2,  product.getDescription());
			sqlCmd.setInt(3,  product.getUnitPrice());
			sqlCmd.setInt(4,  product.getId());
			sqlCmd.execute();
			sqlCmd.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}