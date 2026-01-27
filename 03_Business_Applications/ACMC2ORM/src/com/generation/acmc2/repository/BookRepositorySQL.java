package com.generation.acmc2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.generation.acmc2.model.entities.Book;
import com.generation.acmc2.model.entities.Review;
import com.generation.library.repository.SQLEntityRepository;

public class BookRepositorySQL extends SQLEntityRepository<Book> implements BookRepository
{
	public BookRepositorySQL(String table, Connection connection)
	{
		super(table, connection);
	}

	@Override
	public Book findById(int id, boolean complete) throws SQLException 
	{
		if(!complete)
			return findById(id);			// se non lo voglio completo uso il metodo di base

		Book res = findById(id);			// lo carico "nudo"
		if(res == null)
			return null;

		try									// devo caricargli le review
		{
			Statement readLinkedReviews = connection.createStatement();
			ResultSet rows = readLinkedReviews.executeQuery("select * from review where bookid=" + id);

			while(rows.next())				// sto caricando N RIGHE... e devo convertirle una per una in Review
			{
				Review r = new Review();
				r.setId(rows.getInt("id"));
				r.setAuthor(rows.getString("author"));
				r.setText(rows.getString("text"));
				r.setScore(rows.getInt("score"));
				res.addReview(r);			// collego il figlio al padre e il padre al figlio
			}

			readLinkedReviews.close();
			rows.close();
			return res;
		}
		catch(SQLException e)
		{
			throw new RuntimeException("Error reading " + e.getMessage());
		}
	}

	@Override
	public Book rowToX(ResultSet rows) throws SQLException
	{
		Book b = new Book();
		b.setId(rows.getInt("id"));
		b.setTitle(rows.getString("title"));
		b.setAuthor(rows.getString("author"));
		b.setGenre(rows.getString("genre")); 
		return b;
	}

	@Override
	protected PreparedStatement getUpdateCmd(Book newVersion) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement getInsertCmd(Book x) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
}