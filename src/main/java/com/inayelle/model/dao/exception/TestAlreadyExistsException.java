package com.inayelle.model.dao.exception;

import com.inayelle.model.entity.Test;

public class TestAlreadyExistsException extends Exception
{
	private Test test;
	
	public TestAlreadyExistsException(String message, Test test)
	{
		super(message);
		this.test = test;
	}
	
	public Test getTest()
	{
		return this.test;
	}
}
