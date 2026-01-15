package com.generation.gbb.view;

import java.util.List;

import com.generation.gbb.model.entities.Expense;
import com.generation.library.Template;

/**
 * Abstract base class for expense views.
 * Defines the contract for rendering expense entities in different formats.
 * Provides a partial implementation with template loading and list rendering.
 */
public abstract class ExpenseView
{
	protected String template;
	protected String templateContent;

	public ExpenseView(String template)
	{
		this.template = template;
		this.templateContent = Template.load(template);
	}

	/**
	 * Renders a single expense entity.
	 * Must be implemented by concrete subclasses.
	 * @param e The expense to render
	 * @return Formatted string representation of the expense
	 */
	public abstract String render(Expense e);

	/**
	 * Renders a list of expenses by calling render() on each element.
	 * @param expenses List of expenses to render
	 * @return Formatted string with all expenses
	 */
	public String render(List<Expense> expenses)
	{
		String res = "";
		for(Expense expense : expenses)
			res += render(expense) + "\n";
		return res;
	}
}
