package com.generation.gbb.view;

/**
 * Factory class for creating ExpenseView instances.
 * Provides pre-configured view objects for different display formats.
 */
public class ExpenseViewFactory
{
	static ExpenseView summaryView = new ExpenseViewTxtBasic("template/expensesummary.txt");
	static ExpenseView fullView    = new ExpenseViewTxtBasic("template/fullexpenseview.txt");

	/**
	 * Creates an ExpenseView instance based on the requested type.
	 * @param type The view type ("full" or "summary")
	 * @return ExpenseView instance (full view if type is "full", summary otherwise)
	 */
	public static ExpenseView make(String type)
	{
		return type.equals("full") ? fullView : summaryView;
	}
}
