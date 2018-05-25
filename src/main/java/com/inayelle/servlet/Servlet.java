package com.inayelle.servlet;

import com.inayelle.auxiliary.Logger;
import com.inayelle.model.Model;
import com.inayelle.servlet.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Servlet extends HttpServlet
{
	private Model model;
	private Map<String, BaseCommand> commands;
	
	private static final String PAGE_ENCODING = "utf8";
	private static final String COMMAND_REGEX = "^(?<command>/[a-z0-9]*)(\\?.{2,})?$";
	
	@Override
	public void init()
	{
		var context = getServletContext();
		Object maybeModel = context.getAttribute("model");
		if (!(maybeModel instanceof Model))
		{
			var error = new InternalError("Model corrupted");
			Logger.error(error, "Servlet");
			throw error;
		}
		
		model = (Model) maybeModel;
		commands = new HashMap<>();
		commands.put("/", new IndexCommand(model));
		commands.put("/create", new CreateCommand(model));
		commands.put("/pass", new PassCommand(model));
		commands.put("/check", new CheckCommand(model));
		commands.put("/validatetitlename", new ValidateTitleNameCommand(model));
		Logger.status("Servlet", "initialized");
	}
	
	@Override
	public void destroy()
	{
		model = null;
		Logger.status("Servlet", "destroyed");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setCharacterEncoding(PAGE_ENCODING);
		
		String uri = req.getRequestURI();
		BaseCommand command = getCommandFromURI(uri);
		if (command == null)
			command = new PageNotFoundCommand(model);
		
		command.executeRecursive(req, resp);
	}
	
	private BaseCommand getCommandFromURI(String uri)
	{
		Pattern commandPattern = Pattern.compile(COMMAND_REGEX);
		Matcher matcher = commandPattern.matcher(uri);
		if (!matcher.matches())
			return null;
		
		var commandString = matcher.group("command");
		
		return commands.get(commandString);
	}
}
