package com.generation.library.view;

import java.util.List;

import com.generation.library.Entity;

public interface EntityView<X extends Entity> 
{

	String render(X x);
	
	default String render(List<X> xs)
	{
		String res = "";
		for(X x:xs)
			res+=render(x);
		return res;
	}
	
}
