package com.inayelle.auxiliary;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger
{
	private Logger() {}
	
	private static final String ENDLINE;
	private static final String SEPARATOR_LINE;
	private static final SimpleDateFormat DATE_FORMAT;
	
	static
	{
		ENDLINE = System.getProperty("line.separator");
		SEPARATOR_LINE = "-------------------------------------";
		DATE_FORMAT = new SimpleDateFormat("E dd.MM.yyyy hh:mm:ss");
	}
	
	private enum ConsoleColor
	{
		DEFAULT("\u001B[0m"),
		RED("\u001B[31m"),
		GREEN("\u001B[32m"),
		BLUE("\u001B[34m"),
		WHITE("\u001B[37m"),
		YELLOW("\u001B[33m"),
		PURPLE("\u001B[35m"),
		CYAN("\u001B[36m");
		
		private final String value;
		
		ConsoleColor(String value)
		{
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return value;
		}
	}
	
	private static String wrapCaller(String caller, String messageType)
	{
		var now = new Date();
		return "[" + DATE_FORMAT.format(now) + " | " + caller + " | " + messageType.toUpperCase() + "]";
	}
	
	public static void status(String callerName, String... description)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(wrapCaller(callerName, "status")).append(ENDLINE);
		
		for (var string : description)
			builder.append(string).append(ENDLINE);
		
		String string = builder.toString();
		
		setColor(ConsoleColor.GREEN);
		System.out.print(string);
		resetColor();
	}
	
	public static void warning(String callerName, String... description)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(wrapCaller(callerName, "warning")).append(ENDLINE);
		
		for (var string : description)
			builder.append(string).append(ENDLINE);
		
		String string = builder.toString();
		
		setColor(ConsoleColor.YELLOW);
		System.out.print(string);
		resetColor();
	}
	
	public static void exception(Exception exception, String callerName, String... description)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(wrapCaller(callerName, "exception")).append(ENDLINE);
		
		for (var string : description)
			builder.append(string).append(ENDLINE);
		
		builder.append("Stacktrace:").append(ENDLINE);
		
		for (var string : exception.getStackTrace())
			builder.append(string).append(ENDLINE);
		
		String string = builder.toString();
		
		setColor(ConsoleColor.YELLOW);
		System.out.print(string);
		resetColor();
	}
	
	public static void error(Throwable error, String callerName, String... description)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(wrapCaller(callerName, "error")).append(ENDLINE);
		
		for (var string : description)
			builder.append(string).append(ENDLINE);
		
		builder.append("Stacktrace:").append(ENDLINE);
		
		for (var string : error.getStackTrace())
			builder.append(string).append(ENDLINE);
		
		String string = builder.toString();
		
		setColor(ConsoleColor.YELLOW);
		System.out.print(string);
		resetColor();
	}
	
	private static void setColor(ConsoleColor color)
	{
		System.out.print(color);
	}
	
	private static void resetColor()
	{
		System.out.print(ConsoleColor.DEFAULT);
	}
}
