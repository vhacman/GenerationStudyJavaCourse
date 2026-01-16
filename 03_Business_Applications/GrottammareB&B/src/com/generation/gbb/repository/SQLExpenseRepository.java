package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Expense;
import com.generation.gbb.model.entities.ExpenseCategory;
import com.generation.gbb.model.entities.Room;

/**
 * Implementazione SQL del repository per la gestione delle spese.
 * Utilizza JDBC per l'accesso ai dati e implementa il pattern Repository.
 *
 * IMPORTANTE: Gestione ENUM ↔ Database
 * - JAVA → SQL: ExpenseCategory.FOOD → "FOOD" usando .name()
 * - SQL → JAVA: "FOOD" → ExpenseCategory.FOOD usando .valueOf(string)
 *
 * RIFERIMENTO TEMPLATE: SQLGuestRepository.java
 */
public class SQLExpenseRepository implements ExpenseRepository
{
	Connection connection = ConnectionFactory.make();

	/**
	 * Recupera tutte le spese dal database.
	 * @return lista contenente tutte le spese presenti nel database
	 */
	public List<Expense> findAll()
	{
	    try
	    {
	        String              sqlString               = "SELECT * FROM expense";
	        PreparedStatement   readCmdFromSqlString    = connection.prepareStatement(sqlString);
	        List<Expense>       result                  = new ArrayList<>();
	        ResultSet           expenseRow              = readCmdFromSqlString.executeQuery();
	        
	        while(expenseRow.next())
	        {
	            Expense intoExpenseObject = new Expense();
	            intoExpenseObject.setId(expenseRow.getInt("id"));
	            intoExpenseObject.setDate(expenseRow.getString("date"));
	            intoExpenseObject.setDescription(expenseRow.getString("description"));
	            intoExpenseObject.setValue(expenseRow.getDouble("value"));
	            intoExpenseObject.setCategory(ExpenseCategory.valueOf(expenseRow.getString("category")));
	            
	            result.add(intoExpenseObject);
	        }
	        
	        readCmdFromSqlString.close();
	        expenseRow.close();
	        return result;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error reading");
	    }
	}

	/**
	 * Cerca una spesa specifica tramite identificativo.
	 * @param id l'identificativo univoco della spesa
	 * @return l'oggetto Expense se trovato, null altrimenti
	 */
	@Override
	public Expense findById(int id)
	{
	    try
	    {
	        String              sqlString               = "SELECT * FROM expense WHERE id = ?";
	        PreparedStatement   readCmdFromSQLString    = connection.prepareStatement(sqlString);
	        
	        readCmdFromSQLString.setInt(1, id);
	        
	        ResultSet           expenseRow              = readCmdFromSQLString.executeQuery();
	        Expense             result                  = null;

	        if(expenseRow.next())
	        {
	            result = new Expense();
	            result.setId(expenseRow.getInt("id"));
	            result.setDate(expenseRow.getString("date"));
	            result.setDescription(expenseRow.getString("description"));
	            result.setValue(expenseRow.getDouble("value"));
	            result.setCategory(ExpenseCategory.valueOf(expenseRow.getString("category")));
	        }

	        readCmdFromSQLString.close();
	        expenseRow.close();

	        return result;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error reading");
	    }
	}

	/**
	 * Cerca spese appartenenti a una specifica categoria.
	 * @param category la categoria delle spese (FOOD, SERVICES, SALARIES)
	 * @return lista di spese della categoria specificata
	 */
	@Override
	public List<Expense> findByCategory(ExpenseCategory category)
	{
	    try
	    {
	        String              sqlString               = "SELECT * FROM expense WHERE category = ?";
	        PreparedStatement   readCmdFromSQLString    = connection.prepareStatement(sqlString);
	        
	        // L'enum viene salvato come stringa nel database
	        readCmdFromSQLString.setString(1, category.name());
	        
	        List<Expense>       result                  = new ArrayList<>();
	        ResultSet           expenseRow              = readCmdFromSQLString.executeQuery();

	        while(expenseRow.next())
	        {
	            Expense intoExpenseObject = new Expense();
	            intoExpenseObject.setId(expenseRow.getInt("id"));
	            intoExpenseObject.setDate(expenseRow.getString("date"));
	            intoExpenseObject.setDescription(expenseRow.getString("description"));
	            intoExpenseObject.setValue(expenseRow.getDouble("value"));
	            intoExpenseObject.setCategory(ExpenseCategory.valueOf(expenseRow.getString("category")));
	            
	            result.add(intoExpenseObject);
	        }
	        readCmdFromSQLString.close();
	        expenseRow.close();

	        return result;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error reading");
	    }
	}

	/**
	 * Inserisce una nuova spesa nel database.
	 * @param newExpense l'oggetto Expense da persistere
	 * @return l'oggetto Expense con l'id generato dal database
	 * @throws RuntimeException se la spesa non è valida o ha già un id
	 */
	@Override
	public Expense insert(Expense newExpense)
	{
	    if(!newExpense.isValid())
	        throw new RuntimeException("Invalid expense");
	    if(newExpense.getId() != 0)
	        throw new RuntimeException("Cannot save an expense with a previous id");

	    try
	    {
	        String              sqlString   = "INSERT INTO expense (date, description, value, category) VALUES (?,?,?,?)";
	        PreparedStatement   insertCmd   = connection.prepareStatement(sqlString);
	        
	        insertCmd.setString(1, newExpense.getDate().toString());    // LocalDate → String
	        insertCmd.setString(2, newExpense.getDescription());
	        insertCmd.setDouble(3, newExpense.getValue());
	        insertCmd.setString(4, newExpense.getCategory().name());    // Enum → String

	        insertCmd.execute();
	        insertCmd.close();

	        newExpense.setId(getNewId());
	        return newExpense;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error saving");
	    }
	}
	
	/**
	 * Recupera l'ultimo ID generato dal database.
	 * Metodo PRIVATE di supporto per sincronizzare l'id dopo un inserimento.
	 * @return l'ID massimo presente nella tabella room
	 */
	private int getNewId()
	{
		try
	    {
	        // Recupera il valore massimo della colonna id
	        // "as m" crea un alias per leggere facilmente il risultato
	        PreparedStatement   readCmd = connection.prepareStatement("SELECT MAX(id) AS m FROM room");
	        ResultSet           rs      = readCmd.executeQuery();
	        
	        // Legge il valore dalla colonna con alias "m"
	        int                 res     = rs.getInt("m");
	        
	        // Rilascio risorse JDBC
	        readCmd.close();
	        rs.close();
	        
	        return res;
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error reading");
	    }
	}
	

	/**
	 * Aggiorna i dati di una spesa esistente.
	 * @param newVersion l'oggetto Expense con i dati aggiornati
	 * @return l'oggetto Expense aggiornato
	 */
	@Override
	public Expense update(Expense newVersion)
	{
	    try
	    {
	        String              sqlString = "UPDATE expense SET date=?, description=?, value=?, category=? WHERE id=?";
	        PreparedStatement   updateCmd = connection.prepareStatement(sqlString);

	        updateCmd.setString(1, newVersion.getDate().toString());
	        updateCmd.setString(2, newVersion.getDescription());
	        updateCmd.setDouble(3, newVersion.getValue());
	        updateCmd.setString(4, newVersion.getCategory().name());
	        updateCmd.setInt(5, newVersion.getId());

	        updateCmd.execute();
	        updateCmd.close();
	        return newVersion;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error saving");
	    }
	}

	/**
	 * Elimina una spesa dal database.
	 * @param id l'identificativo della spesa da eliminare
	 * @return true se l'eliminazione ha successo, false altrimenti
	 */
	@Override
	public boolean delete(int id)
	{
	    try
	    {
	        String              sqlString = "DELETE FROM expense WHERE id = ?";
	        PreparedStatement   deleteCmd = connection.prepareStatement(sqlString);
	        
	        deleteCmd.setInt(1, id);
	        deleteCmd.execute();
	        deleteCmd.close();
	        return true;
	    }
	    catch(Exception e)
	    {
	        return false;
	    }
	}

}
