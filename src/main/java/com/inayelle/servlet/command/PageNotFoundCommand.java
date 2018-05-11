package com.inayelle.servlet.command;

import com.inayelle.model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageNotFoundCommand extends BaseCommand
{
	private static final String PAGE = "/pages/404.jsp";
	
	public PageNotFoundCommand(Model model)
	{
		super(model);
	}
	
	@Override
	public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		req.setAttribute("page", req.getRequestURL());
		req.getRequestDispatcher(PAGE).forward(req, resp);
		return null;
	}
}
