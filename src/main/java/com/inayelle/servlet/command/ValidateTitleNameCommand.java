package com.inayelle.servlet.command;

import com.inayelle.model.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateTitleNameCommand extends BaseCommand
{
	public ValidateTitleNameCommand(Model model)
	{
		super(model);
	}
	
	@Override
	public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String title = req.getParameter("title");
		var writer = resp.getWriter();
		resp.setContentType("text");
		resp.setCharacterEncoding("utf8");
		
		if (title == null || model.testWithTitleExists(title))
			writer.print("bad");
		else
			writer.print("good");
		
		return null;
	}
}
