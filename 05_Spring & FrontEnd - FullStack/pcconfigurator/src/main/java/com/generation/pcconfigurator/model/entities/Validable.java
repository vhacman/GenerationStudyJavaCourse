package com.generation.pcconfigurator.model.entities;

import java.util.List;

public interface Validable 
{
	List<String> getErrors();
	
	default boolean isValid()
	{
		return getErrors().size()==0;
	}
	
	
}