package com.generation.oc.controller;

// utility varie
public class Util 
{
	public static boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	

}
