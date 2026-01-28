package com.generation.acmc2.model.entities;

import java.util.ArrayList;
import java.util.List;
import com.generation.library.Entity;

/**
 * Book è una entità padre
 * Un Book -> n Review
 * Un Book contiene una LISTA di Review
 * Book è entità padre, Review è entità figlia
 */
public class Book extends Entity
{
	protected String					title;
	protected String					author;
	protected String					genre;
	protected List<Review>		reviews = new ArrayList<Review>();

	/**
	 * Aggiunge una review al libro
	 * collegamento BIDIREZIONALE
	 * @param r
	 */
	public void addReview(Review r)
	{
		r.book = this;				// collegamento fra figlio (r) e padre (this)
		reviews.add(r);				// aggiungo la review alla mia lista
	}

	/**
	 * Calcola la media dei punteggi di tutte le review
	 * @return
	 */
	public double getAverageScore()
	{
		if(reviews.isEmpty())
			return 0.0;
		double sum = 0;
		for(Review r : reviews)
			sum += r.score;
		return sum / reviews.size();
	}

	public String						getTitle()													{ return title; }
	public void						setTitle(String title)									{ this.title = title; }

	public String						getAuthor()												{ return author; }
	public void						setAuthor(String author)						{ this.author = author; }

	public String						getGenre()												{ return genre; }
	public void						setGenre(String genre)							{ this.genre = genre; }

	public List<Review>		getReviews()												{ return reviews; }
	public void						setReviews(List<Review> reviews)		{ this.reviews = reviews; }

	@Override
	public List<String> getErrors()
	{
		// TODO Auto-generated method stub
		return null;
	}
}