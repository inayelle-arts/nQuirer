package com.inayelle.servlet;

import com.inayelle.auxiliary.Logger;
import com.inayelle.model.Model;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		var context = servletContextEvent.getServletContext();
		var model = new Model();
		
		context.setAttribute("model", model);
		Logger.status("ContextListener", "initialized");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		Logger.status("ContextListener", "destroyed");
	}
}
