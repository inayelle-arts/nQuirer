package com.inayelle.model.dao;

import com.inayelle.model.dao.base.BaseGenericDAO;
import com.inayelle.model.dao.base.ConnectionFactory;
import com.inayelle.model.dao.exception.DAOException;
import com.inayelle.model.entity.Question;
import com.inayelle.model.entity.Test;

import java.sql.*;
import java.util.Set;

public class TestDAO extends BaseGenericDAO<Test>
{
	@Override
	protected Test parseOne(ResultSet resultSet) throws SQLException, DAOException
	{
		int id = resultSet.getInt("id");
		String
				title = resultSet.getString("title"),
				description = resultSet.getString("description");
		
		var questionDAO = new QuestionDAO();
		
		Set<Question> questions = questionDAO.getByOwnerId(id);
		
		return new Test(id, title, description, questions);
	}
	
	@Override
	protected String getByIdQuery()
	{
		return SQL.GET_BY_ID.toString();
	}
	
	@Override
	protected String getByOwnerIdQuery()
	{
		return null;
	}
	
	@Override
	protected String getAllQuery()
	{
		return SQL.GET_ALL.toString();
	}
	
	@Override
	protected String insertQuery()
	{
		return SQL.INSERT.toString();
	}
	
	@Override
	public void update(Test entity) throws DAOException
	{
	
	}
	
	@Override
	public void delete(Test entity) throws DAOException
	{
	
	}
	
	@Override
	public void insert(Test entity) throws DAOException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		String title = entity.getTitle();
		String description = entity.getDescription();
		Set<Question> questions = entity.getQuestions();
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(this.insertQuery(), new String[]{"id"});
			statement.setString(1, title);
			statement.setString(2, description);
			statement.execute();
			
			var generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();
			int newId = generatedKeys.getInt(1);
			entity.setId(newId);
			generatedKeys.close();
			
			var questionDAO = new QuestionDAO();
			for (var question : questions)
				questionDAO.insert(question);
		}
		catch (SQLException exception)
		{
			throw new DAOException("TestDAO::insert", exception);
		}
		finally
		{
			ConnectionFactory.closeConnection(connection, statement, null);
		}
	}
	
	public boolean testWithTitleExists(String title) throws DAOException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(this.getCountByLoginQuery());
			statement.setString(1, title);
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
				return resultSet.getInt(1) != 0;
			
			return false;
		}
		catch (SQLException exception)
		{
			throw new DAOException("TestDAO::insert", exception);
		}
		finally
		{
			ConnectionFactory.closeConnection(connection, statement, null);
		}
	}
	
	private String getCountByLoginQuery()
	{
		return SQL.COUNT_BY_LOGIN.toString();
	}
	
	private enum SQL
	{
		GET_BY_ID("SELECT* FROM `tests` WHERE id = ?"),
		GET_ALL("SELECT* FROM `tests`"),
		GET_BY_OWNER_ID("SELECT* FROM `tests` WHERE owner_id = ?"),
		COUNT_BY_LOGIN("SELECT COUNT(*) FROM `tests` WHERE `title` = ?"),
		INSERT("INSERT INTO `tests` (title, description) VALUES (?, ?)");
		
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
