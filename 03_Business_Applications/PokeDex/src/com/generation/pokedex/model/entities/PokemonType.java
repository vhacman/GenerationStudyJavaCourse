package com.generation.pokedex.model.entities;

/**
 * Questa classe corrisponde alla tabella pokemontype del db
 * un oggetto di questa classe corrisponde a UNA riga della tabella
 */
public class PokemonType extends Entity
{
	protected String name, description;

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	@Override
	public boolean isValid() 
	{
		return notBlank(name) && notBlank(description);
	}

	@Override
	public String toString() {
		return "PokemonType [id "+id+", name=" + name + ", description=" + description + "]";
	}
	
	
	
	
}
