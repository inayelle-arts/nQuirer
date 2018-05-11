package com.inayelle.model.dao;

import com.inayelle.model.entity.Question;
import com.inayelle.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class QuestionDAO implements IGenericDAO<Question>
{
	@Override
	public Question getById(int id) throws DAOException
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
			throw new DAOException("qwe::getAll", ex);
		}
	}
	
	@Override
	public Question getFirst() throws DAOException
	{
		return null;
	}
	
	@Override
	public Set<Question> getAll() throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		ResultSet resultSet;
		
		Set<Question> result = new HashSet<>();
		
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
	public void update(Question entity) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.UPDATE.toString());
			statement.setString(1, entity.getContent());
			statement.setString(2, entity.getExplanation());
			statement.setInt(3, entity.getAnswerHash());
			statement.setInt(4, entity.getTestId());
			statement.setInt(5, entity.getId());
			
			statement.execute();
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	@Override
	public void delete(Question entity) throws DAOException
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
			throw new DAOException("UserDAO::er", ex);
		}
	}
	
	@Override
	public void insert(Question entity) throws DAOException
	{
		Connection connection;
		PreparedStatement statement;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.INSERT.toString());
			statement.setString(1, entity.getContent());
			statement.setString(2, entity.getExplanation());
			statement.setInt(3, entity.getAnswerHash());
			statement.setInt(4, entity.getTestId());
			
			statement.execute();
		}
		catch (SQLException ex)
		{
			throw new DAOException("UserDAO::getAll", ex);
		}
	}
	
	private Question parseOne(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("id");
		String
				content = resultSet.getString("content"),
				explanation = resultSet.getString("explanation");
		int answerHash = resultSet.getInt("answerHash");
		int testId = resultSet.getInt("testId");
		
		return new Question(id, content, explanation, answerHash, testId);
	}
	
	private enum SQL
	{
		GET_BY_ID("SELECT* FROM `questions` WHERE id = ?"),
		GET_ALL("SELECT* FROM `questions`"),
		DELETE("DELETE FROM `questions` WHERE id = ?"),
		INSERT("INSERT INTO `questions` (content, explanation, answerHash, testId) VALUES (?, ?, ?, ?)"),
		UPDATE("UPDATE `questions` SET content = ?, explanation = ?, answerHash = ?, testId = ? WHERE id = ?");
		
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
