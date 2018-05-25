package com.inayelle.model.dao.base;

import com.inayelle.auxiliary.Logger;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public final class ConnectionFactory
{
	private ConnectionFactory() {}
	
	private static final String url;
	private static final String user;
	private static final String pass;
	
	static
	{
		url = "jdbc:mysql://192.168.1.218:3306/nquirer_db" +
				"?verifyServerCertificate=false" +
				"&useSSL=false" +
				"&requireSSL=false" +
				"&useLegacyDatetimeCode=false" +
				"&amp" +
				"&serverTimezone=UTC";
		user = "root";
		pass = "";
		
		try
		{
			DriverManager.registerDriver(new Driver());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		try
		{
			return DriverManager.getConnection(url, user, pass);
		}
		catch (SQLException ex)
		{
			var error = new InternalError("Connection to database failed", ex);
			Logger.error(error, "ConnectionFactory", "Creating connection failed", ex.getMessage());
			throw error;
		}
	}
	
	public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
	{
		try
		{
			if (connection != null)
				connection.close();
			if (statement != null)
				statement.close();
			if (resultSet != null)
				resultSet.close();
		}
		catch (SQLException ex)
		{
			Logger.warning("ConnectionFactory", "Connection closing failed");
		}
	}
}
