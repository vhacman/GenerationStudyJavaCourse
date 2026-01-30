package com.generation.oc.model.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.generation.library.Entity;

public class HealthService extends Entity
{
	protected String		name;
	protected int			price;

	public HealthService(){}

	public HealthService(String name, int price)
	{
		this.name = name;
		this.price = price;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name, price);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)					return true;
		if (obj == null)					return false;
		if (getClass() != obj.getClass())	return false;
		HealthService other = (HealthService) obj;
		return Objects.equals(name, other.name) && price == other.price;
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<>();
		if(isMissing(name))		res.add("Missing name");
		if(price < 0)			res.add("Invalid price");
		return res;
	}

	@Override
	public String toString()
	{
		return "HealthService [name=" + name + ", price=" + price + "]";
	}

	public String	getName()				{ return name; }
	public int		getPrice()				{ return price; }
	public void		setName(String name)	{ this.name = name; }
	public void		setPrice(int price)		{ this.price = price; }
}
