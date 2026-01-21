package com.generation.pokedex.model.entities;

/**
 * Con pokemon non si intende un singolo pokemon
 * ma la sua razza
 */
public class Pokemon extends Entity
{
	String name;
	PokemonType primary;			// è come quando abbiamo salvato l'id del prodotto
	PokemonType secondary;			// e l'id del produttore
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PokemonType getPrimary() {
		return primary;
	}
	public void setPrimary(PokemonType primary) {
		this.primary = primary;
	}
	public PokemonType getSecondary() {
		return secondary;
	}
	public void setSecondary(PokemonType secondary) {
		this.secondary = secondary;
	}
	
	public boolean isValid()
	{
		return notBlank(name) && primary!=null;
	}

}
