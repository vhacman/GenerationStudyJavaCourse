package com.generation.product.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ReviewDTO
{
	private int				id;
	private String			title;
	private String			author;
	private int				score;
	private int				productId;
	private List<String>	errors = new ArrayList<>();

	public int			getId()				        { return id;            }
	public void			setId(int id)		        { this.id = id;         }

	public String		getTitle()			        { return title;         }
	public void			setTitle(String t)	        { this.title = t;       }

	public String		getAuthor()			        { return author;        }
	public void			setAuthor(String a)	        { this.author = a;      }

	public int			getScore()			        { return score;         }
	public void			setScore(int s)		        { this.score = s;       }

	public int			getProductId()		        { return productId;     }
	public void			setProductId(int i)	        { this.productId = i;   }

	public List<String>	getErrors()					{ return errors;        }
	public void			setErrors(List<String> e)	{ this.errors = e;      }

	@Override
	public String toString()
	{
		return "ReviewDTO{" + "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + ", score=" + score + ", productId=" + productId + ", errors=" + errors + '}';
	}
}
