package com.inayelle.servlet.command;

import com.google.gson.Gson;
import com.inayelle.model.Model;
import com.inayelle.model.dao.exception.TestAlreadyExistsException;
import com.inayelle.model.entity.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCommand extends BaseCommand
{
	private static final String PAGE = "/pages/create.jsp";
	
	
	public CreateCommand(Model model)
	{
		super(model);
	}
	
	@Override
	public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		if (req.getMethod().equals("GET"))
		{
			req.getRequestDispatcher(PAGE).forward(req, resp);
			return null;
		}
		
		String json = req.getParameter("json");
		
		var gson = new Gson();
		
		Test test = gson.fromJson(json, Test.class);
		
		try
		{
			model.createTest(test);
		}
		catch (TestAlreadyExistsException e)
		{
			System.out.println(e.getMessage());
		}
		
		req.getRequestDispatcher(PAGE).forward(req, resp);
		return null;
	}
}
