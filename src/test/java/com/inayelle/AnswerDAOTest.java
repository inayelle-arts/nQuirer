package com.inayelle;

import com.inayelle.model.dao.AnswerDAO;
import com.inayelle.model.dao.exception.DAOException;
import org.junit.Test;

public class AnswerDAOTest
{
	@Test
	public void getById()
	{
		var dao = new AnswerDAO();
		try
		{
			var answer = dao.getById(1);
			System.out.println(answer);
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByOwnerId()
	{
		var dao = new AnswerDAO();
		try
		{
			var answers = dao.getByOwnerId(1);
			System.out.println(answers);
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void insert()
	{
//		var dao = new AnswerDAO();
//		try
//		{
//			var answers = new Answer(99, 1,"test", false);
//			dao.insert(answers);
//		}
//		catch (DAOException e)
//		{
//			e.printStackTrace();
//		}
	}
}
