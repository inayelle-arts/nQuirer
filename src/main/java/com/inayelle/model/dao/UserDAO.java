package com.inayelle.model.dao;

import com.inayelle.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDAO implements IGenericDAO<User>
{
	@Override
	public User getById(int id) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		ResultSet resultSet;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.GET_BY_ID.toString());
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
				return parseOne(resultSet);
			
			return null;
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	@Override
	public User getFirst() throws DAOException
	{
		return null;
	}
	
	@Override
	public Set<User> getAll() throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		ResultSet resultSet;
		
		Set<User> result = new HashSet<>();
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.GET_ALL.toString());
			resultSet = statement.executeQuery();
			
			while (resultSet.next())
				result.add(parseOne(resultSet));
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	@Override
	public void update(User entity) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.UPDATE.toString());
			statement.setString(1, entity.getLogin());
			statement.setString(2, entity.getPassword());
			statement.setBoolean(3, entity.isAdmin());
			statement.setInt(4, entity.getId());
			
			statement.execute();
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	@Override
	public void delete(User entity) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.DELETE.toString());
			statement.setInt(1, entity.getId());

			statement.execute();
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	@Override
	public void insert(User entity) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.INSERT.toString());
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getLogin());
			statement.setString(3, entity.getPassword());
			statement.setBoolean(4, entity.isAdmin());
			
			statement.execute();
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	private User parseOne(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("id");
		String
				login = resultSet.getString("login"),
				password = resultSet.getString("pass");
		boolean isAdmin = resultSet.getBoolean("isAdmin");
		
		return new User(id, login, password, isAdmin);
	}
	
	private enum SQL
	{
		GET_BY_ID("SELECT* FROM `users` WHERE id = ?"),
		GET_ALL("SELECT* FROM `users`"),
		DELETE("DELETE FROM `users` WHERE id = ?"),
		INSERT("INSERT INTO `users` (id, login, pass, isAdmin) VALUES (?, ?, ?, ?)"),
		UPDATE("UPDATE `users` SET login = ?, pass = ?, isAdmin = ? WHERE id = ?");
		
		private String value;
		
		SQL(String value)
		{
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return value;
		}
	}
}
