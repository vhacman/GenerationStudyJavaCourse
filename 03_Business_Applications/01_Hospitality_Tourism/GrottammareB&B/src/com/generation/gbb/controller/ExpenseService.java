package com.generation.gbb.controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import com.generation.gbb.etl.ExpenseExtractor;
import com.generation.gbb.etl.ExpenseExtractorFactory;
import com.generation.gbb.model.entities.Expense;
import com.generation.gbb.model.entities.ExpenseCategory;
import com.generation.gbb.repository.SQLExpenseRepository;
import com.generation.gbb.repository.SQLTripRepository;
import com.generation.gbb.repository.interfaces.ExpenseRepository;
import com.generation.gbb.view.EntityViewBasic;
import com.generation.gbb.view.ViewFactory;
import com.generation.library.Console;

public class ExpenseService
{
	private ExpenseRepository expenseRepo;

	public ExpenseService(ExpenseRepository expenseRepo)
	{
		this.expenseRepo = expenseRepo;
	}

	public void insertExpenseFromConsole()
	{
		Expense e = new Expense();
		try
		{
			Console.print("Inserire data spesa (YYYY-MM-DD):");
			e.setDate(LocalDate.parse(Console.readString()));
			Console.print("Inserire descrizione:");
			e.setDescription(Console.readString());
			Console.print("Inserire valore (€):");
			e.setValue(Console.readInt());
			Console.print("Inserire categoria (FOOD, SERVICES, SALARIES):");
			e.setCategory(ExpenseCategory.valueOf(Console.readString().toUpperCase()));

			expenseRepo.insert(e);
			Console.print("Spesa inserita con successo!");
		}
		catch(RuntimeException ex)
		{
			Console.print("Salvataggio non riuscito");
			Console.print(ex.getMessage());
			for (String error : e.getErrors())
				Console.print(error);
		}
	}

	public void importExpenses()
	{
		Console.print("Inserire nome del file con le spese da importare:");
		String filename = Console.readString();
		ExpenseExtractor extractor = ExpenseExtractorFactory.make(filename);

		if (extractor == null)
		{
			Console.print("Formato di file non supportato");
			return;
		}

		try
		{
			List<Expense> loaded = extractor.extractFrom(filename);
			int nSuccesses = 0, nFailures = 0;

			for (Expense e : loaded)
			{
				try
				{
					Console.print("Provo a importare spesa: " + e.getDescription());
					Expense inserted = expenseRepo.insert(e);
					Console.print("Salvata con successo con id " + inserted.getId());
					nSuccesses++;
				}
				catch (RuntimeException ex)
				{
					nFailures++;
					Console.print("Inserimento fallito: " + ex.getMessage());
				}
			}

			Console.print("Numero successo: " + nSuccesses);
			Console.print("Numero fallimenti: " + nFailures);
		}
		catch (FileNotFoundException e)
		{
			Console.print("File non trovato");
		}
	}

	/**
	 * Mostra lista di tutte le spese.
	 * @param format Formato output: "txt" o "html"
	 */
	public void expenseList(String format)
	{
		List<Expense> all = expenseRepo.findAll();
		EntityViewBasic<Expense> view = ViewFactory.makeView("expense", "summary", format);
		Console.print("\n=== Lista Spese (" + all.size() + ") ===");
		Console.print(view.render(all));
	}

	/**
	 * Overload con formato TXT di default.
	 */
	public void expenseList()
	{
		expenseList("txt");
	}

	public void findExpenseByCategory()
	{
		Console.print("Inserire categoria (FOOD, SERVICES, SALARIES):");
		String catInput = Console.readString().toUpperCase().trim();
		ExpenseCategory category = ExpenseCategory.valueOf(catInput);
		List<Expense> found = expenseRepo.findByCategory(category);

		if (found.isEmpty())
			Console.print("Nessuna spesa trovata per categoria " + category);
		else
		{
			Console.print("\n=== " + found.size() + " Spese - Categoria " + category + " ===");
			EntityViewBasic<Expense> view = ViewFactory.makeView("expense", "summary", "txt");
			Console.print(view.render(found));
		}
	}

	/**
	 * Updates an existing expense from console input.
	 * Prompts for expense ID, displays current data, and allows field modification.
	 */
	public void updateExpenseFromConsole()
	{
		Console.print("Inserire ID spesa da modificare:");
		int id = Console.readInt();
		Expense e = expenseRepo.findById(id);

		if (e == null)
		{
			Console.print("Spesa non trovata");
			return;
		}

		EntityViewBasic<Expense> view = ViewFactory.makeView("expense", "full", "txt");
		Console.print("Spesa attuale:");
		Console.print(view.render(e));

		try
		{
			Console.print("Nuova data YYYY-MM-DD (invio per mantenere):");
			String date = Console.readString();
			if (!date.trim().isEmpty()) e.setDate(LocalDate.parse(date));

			Console.print("Nuova descrizione (invio per mantenere):");
			String description = Console.readString();
			if (!description.trim().isEmpty()) e.setDescription(description);

			Console.print("Nuovo valore € (0 per mantenere):");
			int value = Console.readInt();
			if (value > 0) e.setValue(value);

			Console.print("Nuova categoria FOOD/SERVICES/SALARIES (invio per mantenere):");
			String cat = Console.readString().toUpperCase().trim();
			if (!cat.isEmpty()) e.setCategory(ExpenseCategory.valueOf(cat));

			expenseRepo.update(e);
			Console.print("Spesa aggiornata con successo!");
		}
		catch(RuntimeException ex)
		{
			Console.print("Aggiornamento non riuscito");
			Console.print(ex.getMessage());
			for (String error : e.getErrors())
				Console.print(error);
		}
	}

	/**
	 * Deletes an expense from repository by ID.
	 * Prompts for expense ID, displays details, and confirms deletion.
	 */
	public void deleteExpense()
	{
		Console.print("Inserire ID spesa da eliminare:");
		int id = Console.readInt();
		Expense e = expenseRepo.findById(id);

		if (e == null)
		{
			Console.print("Spesa non trovata");
			return;
		}

		EntityViewBasic<Expense> view = ViewFactory.makeView("expense", "full", "txt");
		Console.print(view.render(e));
		Console.print("Si vuole cancellare questa spesa?");

		if (Console.readString().equals("S"))
		{
			boolean result = expenseRepo.delete(id);
			Console.print(result ? "Cancellato" : "Spesa non trovata");
		}
	}

	/**
	 * Modifies the price of an existing expense.
	 * Prompts for expense ID and new price value.
	 */
	public void modifyExpensePrice()
	{
		Console.print("Inserire ID spesa:");
		int id = Console.readInt();
		Expense e = expenseRepo.findById(id);

		if (e == null)
		{
			Console.print("Spesa non trovata");
			return;
		}

		EntityViewBasic<Expense> view = ViewFactory.makeView("expense", "full", "txt");
		Console.print("Spesa attuale:");
		Console.print(view.render(e));
		Console.print("Inserire nuovo prezzo (€):");
		int newPrice = Console.readInt();

		try
		{
			e.setValue(newPrice);
			expenseRepo.update(e);
			Console.print("Prezzo spesa aggiornato con successo!");
		}
		catch(RuntimeException ex)
		{
			Console.print("Aggiornamento non riuscito");
			Console.print(ex.getMessage());
			for (String error : e.getErrors())
				Console.print(error);
		}
	}

	/**
	 * Crea la tabella trip nel database.
	 * Delega l'operazione DDL al repository.
	 */
	public void createTable()
	{
		try
		{
			if (expenseRepo instanceof SQLExpenseRepository)
			{
				((SQLExpenseRepository) expenseRepo).initTable();
				Console.print("Tabella 'expense' creata con successo!");
			}
			else
			{
				Console.print("Creazione tabella disponibile solo con SQL repository");
			}
		}
		catch (RuntimeException e)
		{
			Console.print("Errore nella creazione della tabella trip");
			Console.print(e.getMessage());
		}
	}
}
