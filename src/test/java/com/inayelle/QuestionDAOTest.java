package com.inayelle;

import com.inayelle.model.dao.QuestionDAO;
import com.inayelle.model.dao.exception.DAOException;
import com.inayelle.model.dao.base.IGenericDAO;
import com.inayelle.model.entity.Question;
import org.junit.Before;
import org.junit.Test;


public class QuestionDAOTest
{
	private IGenericDAO<Question> dao;
	
	@Before
	public void before()
	{
		dao = new QuestionDAO();
	}
	
	@Test
	public void getById()
	{
		try
		{
			var question = dao.getById(1);
			System.out.println(question);
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByOwnerId()
	{
		try
		{
			var question = dao.getByOwnerId(1);
			System.out.println(question);
		}
		catch (DAOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void insert() throws DAOException
	{
//		var answers = new HashSet<Answer>();
//		answers.add(new Answer(123, 4, "Удалить отца", true));
//		answers.add(new Answer(124, 4, "Удалить отца отца", true));
//		answers.add(new Answer(124, 4, "Удалить жизнь", false));
//
//		var question = new Question(4, 1,"Как жить без отца?", answers);
//
//		dao.insert(question);
	}
}
