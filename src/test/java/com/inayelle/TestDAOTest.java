package com.inayelle;

import com.inayelle.model.dao.TestDAO;
import com.inayelle.model.dao.exception.DAOException;
import org.junit.Before;
import org.junit.Test;


public class TestDAOTest
{
	private TestDAO dao;
	
	@Before
	public void before()
	{
		dao = new TestDAO();
	}
	
	@Test
	public void getAll()
	{
		try
		{
			var tests = dao.getAll();
			System.out.println(tests);
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void getById()
	{
		try
		{
			var test = dao.getById(1);
			System.out.println(test);
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void insert()
	{
//		var questions = new HashSet<Question>();
//
//		var ans11 = new Answer(1, 1, "Пидр1", true);
//		var ans12 = new Answer(1, 1, "Пидр2", false);
//
//		var answrs1 = new HashSet<Answer>();
//		answrs1.add(ans11);
//		answrs1.add(ans12);
//
//		var ans21 = new Answer(1, 2, "Лох1", false);
//		var ans22 = new Answer(1, 2, "Лох2", true);
//
//		var answrs2 = new HashSet<Answer>();
//		answrs1.add(ans21);
//		answrs1.add(ans22);
//
//		questions.add(new Question(1,1, "Кто ты 1?", answrs1));
//		questions.add(new Question(1,1, "Кто ты 2?", answrs2));
//
//		var test = new com.inayelle.model.entity.Test(1, "TITLE", "DESCRIPTION", questions);
//		try
//		{
//			dao.insert(test);
//		}
//		catch (DAOException e)
//		{
//			e.printStackTrace();
//		}
	}
}
