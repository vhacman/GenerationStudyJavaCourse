package com.generation.acmc2.repository;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import com.generation.acmc2.context.Context;
import com.generation.acmc2.model.entities.Book;

class TestBookRepository 
{

	@Test
	void test() throws SQLException 
	{
		Connection connection = Context.getDependency(Connection.class);
		
		BookRepository repo = new BookRepositorySQL("book", connection);
		
		// il libro b è il signore degli anelli
		Book b = repo.findById(1, true);
		
		// Book è entità padre, Review è entità figlia
		assert(b.getReviews().size()==4);
		
		assert(b.getAverageScore()==10.0);
		
		assert(b.getReviews().get(0).getAuthor().equals("Adriano"));
	
		// Book padre, Review figlia
		// tempo per farlo fino a domattina ore 11
		
	}

}
