package com.inayelle.model.dao;

import com.inayelle.model.entity.Test;
import com.inayelle.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TestDAO implements IGenericDAO<Test>
{
	@Override
	public Test getById(int id) throws DAOException
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
			throw new DAOException("TestDAO::getAll", ex);
		}
	}
	
	@Override
	public Test getFirst() throws DAOException
	{
		return null;
	}
	
	@Override
	public Set<Test> getAll() throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		ResultSet resultSet;
		
		Set<Test> result = new HashSet<>();
		
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
			throw new DAOException("Test::getAll", ex);
		}
	}
	
	@Override
	public void update(Test entity) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.UPDATE.toString());
			statement.setInt(1, entity.getDuration());
			statement.setString(2, entity.getName());
			statement.setInt(3, entity.getTotalQuestions());
			statement.setInt(4, entity.getId());
			
			statement.execute();
		}
		catch (SQLException ex)
		{
			throw new DAOException("TestDAO::getAll", ex);
		}
	}
	
	@Override
	public void delete(Test entity) throws DAOException
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
	public void insert(Test entity) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.INSERT.toString());
			statement.setInt(1, entity.getDuration());
			statement.setString(2, entity.getName());
			statement.setInt(3, entity.getTotalQuestions());
			
			statement.execute();
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	private enum SQL
	{
		GET_BY_ID("SELECT* FROM `tests` WHERE id = ?"),
		GET_ALL("SELECT* FROM `tests`"),
		DELETE("DELETE FROM `tests` WHERE id = ?"),
		INSERT("INSERT INTO `tests` (duration, name, description, totalQuestions) VALUES (?, ?, ?, ?)"),
		UPDATE("UPDATE `tests` SET duration = ?, name = ?, description = ?, totalQuestions = ? WHERE id = ?");
		
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
	
	private Test parseOne(ResultSet resultSet) throws SQLException
	{
		int
				id = resultSet.getInt("id"),
				duration = resultSet.getInt("duration"),
				totalQuestions = resultSet.getInt("totalQuestions");
		String
				name = resultSet.getString("name"),
				description = resultSet.getString("description");
		
		return new Test(id, duration, name, description, totalQuestions);
	}
}
