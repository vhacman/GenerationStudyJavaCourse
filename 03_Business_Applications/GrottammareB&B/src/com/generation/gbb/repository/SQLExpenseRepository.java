package com.generation.gbb.repository;

import java.sql.Connection;
import java.util.List;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Expense;
import com.generation.gbb.model.entities.ExpenseCategory;

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
	 * RIFERIMENTO: SQLGuestRepository.java:29-91
	 */
	@Override
	public List<Expense> findAll()
	{
		// TODO: try-catch
		// TODO: String sql = "select * from expense";
		// TODO: PreparedStatement readCmd = connection.prepareStatement(sql);
		// TODO: List<Expense> res = new ArrayList<Expense>();
		// TODO: ResultSet expenseRows = readCmd.executeQuery();
		// TODO: while(expenseRows.next()) {
		//       Expense e = new Expense();
		//       e.setId(expenseRows.getInt("id"));
		//       e.setDate(LocalDate.parse(expenseRows.getString("date")));  // String → LocalDate
		//       e.setDescription(expenseRows.getString("description"));
		//       e.setValue(expenseRows.getDouble("value"));
		//       e.setCategory(ExpenseCategory.valueOf(expenseRows.getString("category")));  // String → Enum
		//       res.add(e);
		//     }
		// TODO: readCmd.close(); expenseRows.close();
		// TODO: return res;
		// TODO: catch → throw new RuntimeException("Error reading");
		return null;
	}

	/**
	 * Cerca una spesa specifica tramite identificativo.
	 * @param id l'identificativo univoco della spesa
	 * @return l'oggetto Expense se trovato, null altrimenti
	 * RIFERIMENTO: SQLGuestRepository.java:100-142
	 */
	@Override
	public Expense findById(int id)
	{
		// TODO: try-catch
		// TODO: String sql = "select * from expense where id = ?";
		// TODO: PreparedStatement readCmd = connection.prepareStatement(sql);
		// TODO: readCmd.setInt(1, id);
		// TODO: ResultSet expenseRow = readCmd.executeQuery();
		// TODO: Expense res = null;
		// TODO: if(expenseRow.next()) {
		//       res = new Expense();
		//       res.setId(expenseRow.getInt("id"));
		//       res.setDate(LocalDate.parse(expenseRow.getString("date")));
		//       res.setDescription(expenseRow.getString("description"));
		//       res.setValue(expenseRow.getDouble("value"));
		//       res.setCategory(ExpenseCategory.valueOf(expenseRow.getString("category")));
		//     }
		// TODO: readCmd.close(); expenseRow.close();
		// TODO: return res;
		// TODO: catch → throw new RuntimeException("Error reading");
		return null;
	}

	/**
	 * Cerca spese appartenenti a una specifica categoria.
	 * @param category la categoria delle spese (FOOD, SERVICES, SALARIES)
	 * @return lista di spese della categoria specificata
	 * RIFERIMENTO: SQLGuestRepository.java:196-211
	 */
	@Override
	public List<Expense> findByCategory(ExpenseCategory category)
	{
		// TODO: List<Expense> res = new ArrayList<Expense>();
		// TODO: for(Expense e : findAll()) {
		//       if(e.getCategory() == category)  // ENUM usa ==
		//          res.add(e);
		//     }
		// TODO: return res;
		return null;
	}

	/**
	 * Inserisce una nuova spesa nel database.
	 * @param newExpense l'oggetto Expense da persistere
	 * @return l'oggetto Expense con l'id generato dal database
	 * @throws RuntimeException se la spesa non è valida o ha già un id
	 * RIFERIMENTO: SQLGuestRepository.java:220-266
	 */
	@Override
	public Expense insert(Expense newExpense)
	{
		// TODO: if(!newExpense.isValid()) throw new RuntimeException("Invalid expense");
		// TODO: if(newExpense.getId() != 0) throw new RuntimeException("Cannot save an expense with a previous id");
		// TODO: try-catch
		// TODO: String sql = "insert into expense (date, description, value, category) values(?,?,?,?)";
		// TODO: PreparedStatement insertCmd = connection.prepareStatement(sql);
		// TODO: insertCmd.setString(1, newExpense.getDate() + "");  // LocalDate → String
		// TODO: insertCmd.setString(2, newExpense.getDescription());
		// TODO: insertCmd.setDouble(3, newExpense.getValue());
		// TODO: insertCmd.setString(4, newExpense.getCategory().name());  // Enum → String
		// TODO: insertCmd.execute();
		// TODO: insertCmd.close();
		// TODO: newExpense.setId(getNewId());
		// TODO: return newExpense;
		// TODO: catch → throw new RuntimeException("Error saving");
		return null;
	}

	/**
	 * Aggiorna i dati di una spesa esistente.
	 * @param newVersion l'oggetto Expense con i dati aggiornati
	 * @return l'oggetto Expense aggiornato
	 * RIFERIMENTO: SQLGuestRepository.java:310-346
	 */
	@Override
	public Expense update(Expense newVersion)
	{
		// TODO: try-catch
		// TODO: String sql = "update expense set date=?, description=?, value=?, category=? where id=?";
		// TODO: PreparedStatement updateCmd = connection.prepareStatement(sql);
		// TODO: updateCmd.setString(1, newVersion.getDate() + "");  // LocalDate → String
		// TODO: updateCmd.setString(2, newVersion.getDescription());
		// TODO: updateCmd.setDouble(3, newVersion.getValue());
		// TODO: updateCmd.setString(4, newVersion.getCategory().name());  // Enum → String
		// TODO: updateCmd.setInt(5, newVersion.getId());  // WHERE id=? (parametro 5)
		// TODO: updateCmd.execute();
		// TODO: updateCmd.close();
		// TODO: return newVersion;
		// TODO: catch → throw new RuntimeException("Error saving");
		return null;
	}

	/**
	 * Elimina una spesa dal database.
	 * @param id l'identificativo della spesa da eliminare
	 * @return true se l'eliminazione ha successo, false altrimenti
	 * RIFERIMENTO: SQLGuestRepository.java:354-382
	 */
	@Override
	public boolean delete(int id)
	{
		// TODO: try-catch
		// TODO: String sql = "delete from expense where id = ?";
		// TODO: PreparedStatement deleteCmd = connection.prepareStatement(sql);
		// TODO: deleteCmd.setInt(1, id);
		// TODO: deleteCmd.execute();
		// TODO: deleteCmd.close();
		// TODO: return true;
		// TODO: catch → return false;
		return false;
	}

	/**
	 * Recupera l'ultimo ID generato dal database.
	 * Metodo PRIVATE di supporto per sincronizzare l'id dopo un inserimento.
	 * @return l'ID massimo presente nella tabella expense
	 * RIFERIMENTO: SQLGuestRepository.java:274-302
	 */
	private int getNewId()
	{
		// TODO: try-catch
		// TODO: String sql = "select max(id) as m from expense";
		// TODO: PreparedStatement readCmd = connection.prepareStatement(sql);
		// TODO: ResultSet rs = readCmd.executeQuery();
		// TODO: rs.next();
		// TODO: int res = rs.getInt("m");
		// TODO: readCmd.close(); rs.close();
		// TODO: return res;
		// TODO: catch → throw new RuntimeException("Error reading");
		return 0;
	}
}
