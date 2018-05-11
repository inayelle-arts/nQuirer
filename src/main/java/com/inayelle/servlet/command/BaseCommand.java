package com.inayelle.servlet.command;

import com.inayelle.model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseCommand
{
	protected Model model;
	
	protected BaseCommand(Model model)
	{
		this.model = model;
	}
	
	public abstract BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
	
	public final void executeRecursive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		var nextCommand = this.execute(req, resp);
		while (nextCommand != null)
			nextCommand = nextCommand.execute(req, resp);
	}
}
