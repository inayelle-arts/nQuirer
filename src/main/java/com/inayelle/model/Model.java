package com.inayelle.model;

import com.inayelle.model.dao.TestDAO;
import com.inayelle.model.dao.exception.DAOException;
import com.inayelle.model.dao.exception.TestAlreadyExistsException;
import com.inayelle.model.dao.exception.TestNotFoundException;
import com.inayelle.model.entity.Answer;
import com.inayelle.model.entity.Points;
import com.inayelle.model.entity.Question;
import com.inayelle.model.entity.Test;

import java.util.*;

public class Model
{
	public Set<Test> getTests()
	{
		var testDAO = new TestDAO();
		try
		{
			return testDAO.getAll();
		}
		catch (DAOException exception)
		{
			return new HashSet<>();
		}
	}
	
	public void createTest(Test test) throws TestAlreadyExistsException
	{
		var testDAO = new TestDAO();
		try
		{
			testDAO.insert(test);
		}
		catch (DAOException ex)
		{
			throw new TestAlreadyExistsException("", test);
		}
	}
	
	public Test getTestById(int id) throws TestNotFoundException
	{
		var testDAO = new TestDAO();
		try
		{
			return testDAO.getById(id);
		}
		catch (DAOException e)
		{
			throw new TestNotFoundException(id);
		}
	}
	
	public Points checkUserTest(Test userTest) throws TestNotFoundException
	{
		int testId = userTest.getId();
		
		var testDAO = new TestDAO();
		Test validTest = null;
		
		try
		{
			validTest = testDAO.getById(testId);
		}
		catch (DAOException ex)
		{
			throw new TestNotFoundException(testId);
		}
		
		int maxPoints = 0;
		int userPoints = 0;
		
		for (var userQuestion : userTest.getQuestions())
		{
			var validQuestion = validTest.getQuestionById(userQuestion.getId());
			userQuestion.setContent(validQuestion.getContent());
			for (var userAnswer : userQuestion.getAnswers())
			{
				var validAnswer = validQuestion.getAnswerById(userAnswer.getId());
				userAnswer.setCorrect(validAnswer.getIsCorrect());
				userAnswer.setContent(validAnswer.getContent());
				
				if (userAnswer.getIsCorrect() && userAnswer.getIsUserCorrect())
					++userPoints;
				
				if (validAnswer.getIsCorrect())
					++maxPoints;
			}
		}
		
		return new Points(userPoints, maxPoints);
	}
	
	public boolean testWithTitleExists(String title)
	{
		var testDAO = new TestDAO();
		boolean response;
		
		try
		{
			response = testDAO.testWithTitleExists(title);
		}
		catch (DAOException e)
		{
			response = false;
		}
		return response;
	}
}
