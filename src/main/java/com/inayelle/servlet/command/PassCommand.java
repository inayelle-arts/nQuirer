package com.inayelle.servlet.command;

import com.google.gson.Gson;
import com.inayelle.model.Model;
import com.inayelle.model.dao.exception.TestNotFoundException;
import com.inayelle.model.entity.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

public class PassCommand extends BaseCommand
{
	private static final String PAGE = "/pages/pass.jsp";
	
	public PassCommand(Model model)
	{
		super(model);
	}
	
	@Override
	public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		get(req, resp);
		return null;
	}
	
	private void get(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		Function<String, Boolean> intTryParse = (string) ->
		{
			try
			{
				Integer.valueOf(string);
				return true;
			}
			catch (NumberFormatException ex)
			{
				return false;
			}
		};
		
		String testIdString = req.getParameter("id");
		if (testIdString == null || !intTryParse.apply(testIdString))
			req.setAttribute("not_found", true);
		else
		{
			int testId = Integer.valueOf(testIdString);
			try
			{
				var test = model.getTestById(testId);
				req.setAttribute("not_found", false);
				req.setAttribute("test", test);
			}
			catch (TestNotFoundException ex)
			{
				req.setAttribute("not_found", true);
			}
		}
		
		req.getRequestDispatcher(PAGE).forward(req, resp);
	}
}
