package com.generation.product.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product implements Validable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;
	private String			name;
	private int				price;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review>	reviews = new ArrayList<>();

	public int			getId()										{ return id; 				}
	public void			setId(int id)								{ this.id = id; 			}

	public String		getName()									{ return name; 				}
	public void			setName(String name)						{ this.name = name; 		}

	public int			getPrice()									{ return price; 			}
	public void			setPrice(int price)							{ this.price = price; 		}

	public List<Review>	getReviews()								{ return reviews; 			}
	public void			setReviews(List<Review> reviews)			{ this.reviews = reviews; 	}

	@Override
	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();
		if (name == null || name.trim().isEmpty())
			errors.add("Name is required");
		else if (name.length() < 2 || name.length() > 100)
			errors.add("Name must be between 2 and 100 characters");
		if (price < 0)
			errors.add("Price cannot be negative");
		return errors;
	}

	@Override
	public String toString()
	{
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
	}
}
