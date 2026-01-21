package com.generation.ba.view;

import com.generation.ba.model.entities.BankAccount;
import com.generation.library.Console;
import com.generation.library.Template;

public class BankView
{
	String filename;

	public BankView(String filename)
	{
		this.filename = filename;
	}

	public static int showMainMenu()
	{
		String menu = Template.load("template/menu.txt");
		if(menu == null || menu.isEmpty())
		{
			return 0;
		}
		Console.print(menu);
	    return Console.readIntBetween("Scegli un'opzione: ", "Opzione non valida!", 1, 5);
	}

	public static int showInternalMenu()
	{
		String menu = Template.load("template/internalMenu.txt");
		if(menu == null || menu.isEmpty())
		{
			return 0;
		}
		Console.print(menu);
	    return Console.readIntBetween("Scegli un'opzione: ", "Opzione non valida!", 1, 4);
	}

	public String render(BankAccount account)
	{
	    return Template
	            .load(filename)
	            .replace("[accountNumber]", "" + account.getId())
	            .replace("[name]", "" + account.getClient().getName())
	            .replace("[surname]", "" + account.getClient().getSurname())
	            .replace("[dob]", "" + account.getClient().getDob())
	            .replace("[ssn]", "" + account.getClient().getSsn())
	            .replace("[balance]", "" + account.getBalance());
	}
}