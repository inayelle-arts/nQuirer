package com.inayelle.model;

import com.inayelle.model.dao.DAOException;
import com.inayelle.model.dao.TestDAO;
import com.inayelle.model.entity.Test;

import java.util.Set;

public class Model
{
	public Set<Test> getLatestTests()
	{
		try
		{
			return new TestDAO().getAll();
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
