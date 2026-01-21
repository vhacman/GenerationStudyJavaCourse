package com.generation.pcw.util;

public class Template 
{
	public static String load(String filename)
	{
		try
		{
			String res = "";
			FileReader reader = new FileReader(filename);
			while(reader.hasNext())
				res+=reader.readString()+"\n";
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	
}
