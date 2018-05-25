package com.inayelle.model.dao.exception;

public class TestNotFoundException extends Exception
{
	private int id;
	
	public TestNotFoundException(int id)
	{
		this.id = id;
	}
}
