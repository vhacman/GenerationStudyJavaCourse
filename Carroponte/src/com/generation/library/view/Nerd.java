package com.generation.library.view;

import java.util.List;

import com.generation.library.Entity;

public class Nerd extends Entity
{
	String name;
	int level;
	String weakness;
	
	
	public Nerd(String name, int level, String weakness) {
		super();
		this.name = name;
		this.level = level;
		this.weakness = weakness;
	}



	@Override
	public List<String> getErrors() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
