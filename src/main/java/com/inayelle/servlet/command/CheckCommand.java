package com.inayelle.servlet.command;

import com.google.gson.Gson;
import com.inayelle.model.Model;
import com.inayelle.model.dao.exception.TestNotFoundException;
import com.inayelle.model.entity.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckCommand extends BaseCommand
{
	private static final String PAGE = "/pages/result.jsp";
	
	public CheckCommand(Model model)
	{
		super(model);
	}
	
	@Override
	public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		String json = req.getParameter("json");
		
		var gson = new Gson();
		
		Test userTest = gson.fromJson(json, Test.class);
		
		int
				rightAnswers,
				totalAnswers;
		
		try
		{
			var testPoints = model.checkUserTest(userTest);
			rightAnswers = testPoints.getUserPoints();
			totalAnswers = testPoints.getMaxPoints();
		}
		catch (TestNotFoundException e)
		{
			throw new InternalError("Existing test search failure");
		}
		
		req.setAttribute("user_points", rightAnswers);
		req.setAttribute("max_points", totalAnswers);
		req.setAttribute("test", userTest);
		
		req.getRequestDispatcher(PAGE).forward(req, resp);
		return null;
	}
}
