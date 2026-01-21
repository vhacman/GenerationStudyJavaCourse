package com.generation.gbb.cache;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class TotalCache<X extends Entity>
{
	List<X>		content		= new ArrayList<X>();
	public TotalCache() {}
	
	public TotalCache(List<X> content)
	{
		this.content = content;
	}
	public X findById(int id)
	{
		for(X x: content)
			if(x.getId() == id)
				return x;
		
		return null;
	}
	public List<X> findAll()
	{
		return content;
	}
	
	// Getter e Setter
	public List<X> 		getContent()					{ return content; 			}
	public void 		setContent(List<X> content)		{ this.content = content; 	}
}
