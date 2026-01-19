package com.generation.gbb.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Asset extends Entity
{
	protected String		name;
	protected String		description;
	protected int			cost;
	protected int			value;

	public Asset() {}

	public Asset(int id, String name, String description, int cost, int value)
	{
		this.id				= id;
		this.name			= name;
		this.description	= description;
		this.cost			= cost;
		this.value			= value;
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<String>();

		if(isMissing(name))
			res.add("missing value name");

		if(isMissing(description))
			res.add("missing value description");

		if(cost < 0)
			res.add("cost cannot be negative");

		if(value < 0)
			res.add("value cannot be negative");

		return res;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;

		if(this == o)
			return true;

		if(!(o instanceof Asset))
			return false;

		Asset other = (Asset) o;

		return this.name.equals(other.name)				&&
		       this.description.equals(other.description)	&&
		       this.cost == other.cost					&&
		       this.value == other.value;
	}

	// Getters e Setters allineati
	public String	getName()						{ return name;					}
	public String	getDescription()				{ return description;			}
	public int		getCost()						{ return cost;					}
	public int		getValue()						{ return value;					}
	public void		setName(String name)			{ this.name = name;				}
	public void		setDescription(String description)	{ this.description = description;	}
	public void		setCost(int cost)				{ this.cost = cost;				}
	public void		setValue(int value)				{ this.value = value;			}
}