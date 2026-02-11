package com.generation.product.model.entities;

import java.util.List;

public interface Validable
{
	List<String> getErrors();

	default boolean isValid()
	{
		return getErrors().isEmpty();
	}
}
