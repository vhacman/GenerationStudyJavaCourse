package com.generation.product.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review implements Validable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int			id;
	private String		title;
	private String		author;
	private int			score;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product	product;

	public int		getId()							{ return id; 				}
	public void		setId(int id)					{ this.id = id; 			}

	public String	getTitle()						{ return title; 			}
	public void		setTitle(String title)			{ this.title = title;		}

	public String	getAuthor()						{ return author; 			}
	public void		setAuthor(String author)		{ this.author = author; 	}

	public int		getScore()						{ return score; 			}
	public void		setScore(int score)			    { this.score = score; 		}

	public Product	getProduct()					{ return product; 			}
	public void		setProduct(Product product)		{ this.product = product; 	}

	@Override
	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();
		if (title == null || title.trim().isEmpty())
			errors.add("Title is required");
		else if (title.length() < 2 || title.length() > 100)
			errors.add("Title must be between 2 and 100 characters");
		if (author == null || author.trim().isEmpty())
			errors.add("Author is required");
		else if (author.length() < 2 || author.length() > 100)
			errors.add("Author must be between 2 and 100 characters");
		if (score < 1 || score > 5)
			errors.add("Score must be between 1 and 5");
		if (product == null)
			errors.add("Product is required");
		return errors;
	}

	@Override
	public String toString()
	{
		return "Review{" + "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + ", score=" + score + '}';
	}
}
