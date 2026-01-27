package com.generation.acmc2.model.entities;

import java.util.List;

import com.generation.library.Entity;

/**
 * Review è sottoclasse di Entità
 * Review è entità figlia di Book
 * Review è collegata a UN SOLO LIBRO
 */
public class Review extends Entity
{
	protected String			author;
	protected String			text;
	protected int				score;
	protected Book			book;		// COLLEGAMENTO A OGGETTO PADRE

	public String		getAuthor()								{ return author; }
	public void		setAuthor(String author)		{ this.author = author; }

	public String		getText()									{ return text; }
	public void		setText(String text)					{ this.text = text; }

	public int			getScore()									{ return score; }
	public void		setScore(int score)					{ this.score = score; }

	public Book		getBook()									{ return book; }
	public void		setBook(Book book)				{ this.book = book; }

	@Override
	public List<String> getErrors()
	{
		// TODO Auto-generated method stub
		return null;
	}
}