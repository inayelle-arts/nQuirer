package com.inayelle.servlet.command;

import com.inayelle.model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexCommand extends BaseCommand
{
	private static final String PAGE = "/pages/index.jsp";
	
	public IndexCommand(Model model)
	{
		super(model);
	}
	
	@Override
	public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		var tests = model.getLatestTests();
		req.setAttribute("tests", tests);
		
		req.getRequestDispatcher(PAGE).forward(req, resp);
		return null;
	}
}
