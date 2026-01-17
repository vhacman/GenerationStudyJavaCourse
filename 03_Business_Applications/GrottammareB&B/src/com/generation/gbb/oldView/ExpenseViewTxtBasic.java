package com.generation.gbb.oldView;

import com.generation.gbb.model.entities.Expense;

/**
 * Concrete implementation of ExpenseView for text-based rendering.
 * Uses template placeholders to format expense data.
 */
public class ExpenseViewTxtBasic extends ExpenseView
{
	public ExpenseViewTxtBasic(String template)
	{
		super(template);
	}

	@Override
	public String render(Expense e)
	{
		return templateContent
				.replace("[id]", e.getId() + "")
				.replace("[date]", e.getDate() + "")
				.replace("[description]", e.getDescription())
				.replace("[value]", e.getValue() + "")
				.replace("[category]", e.getCategory() + "");
	}
}
