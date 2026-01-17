package com.generation.gbb.model.entities;

import java.time.LocalDate;
import java.util.List;

import com.generation.library.Entity;

public class Trip extends Entity
{
	protected String 		city;
	protected LocalDate 	date;
	protected String 		review;
	protected int 			score;
	
	
	@Override
	public List<String> getErrors()
	{
		return null;
	}

}
