package com.generation.oc.view;

import java.util.List;
import java.util.Set;

import com.generation.library.Entity;
import com.generation.oc.model.entities.Patient;

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

	default String render(Set<X> xs)
	{
		String res = "";
		for(X x:xs)
			res+=render(x);
		return res;
	}

	
}
