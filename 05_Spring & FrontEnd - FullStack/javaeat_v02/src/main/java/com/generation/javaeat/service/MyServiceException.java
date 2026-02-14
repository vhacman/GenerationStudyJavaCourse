package com.generation.javaeat.service;

import java.util.List;

public class MyServiceException extends Exception
{
	private static final long serialVersionUID = 1L;
	private List<String> errors;
	
	public MyServiceException(String message)
	{
		super(message);
		this.errors = List.of(message);
	}
	
	public MyServiceException(List<String> errors)
	{
		super(errors.toString());
		this.errors = errors;
	}
	
	public List<String> getErrors()
	{
		return errors;
	}

}
