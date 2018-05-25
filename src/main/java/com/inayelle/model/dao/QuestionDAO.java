package com.inayelle.model.dao;

import com.inayelle.model.dao.base.BaseGenericDAO;
import com.inayelle.model.dao.base.ConnectionFactory;
import com.inayelle.model.dao.exception.DAOException;
import com.inayelle.model.entity.Answer;
import com.inayelle.model.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class QuestionDAO extends BaseGenericDAO<Question>
{
	@Override
	public void update(Question entity) throws DAOException
	{
	
	}
	
	@Override
	public void delete(Question entity) throws DAOException
	{
	
	}
	
	@Override
	public void insert(Question entity) throws DAOException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		String content = entity.getContent();
		int ownerId = entity.getOwnerId();
		Set<Answer> answers = entity.getAnswers();
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(this.insertQuery(), new String[]{"id"});
			statement.setString(1, content);
			statement.setInt(2, ownerId);
			statement.execute();
			
			var generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();
			int newId = generatedKeys.getInt(1);
			entity.setId(newId);
			System.out.println("New question id: " + newId);
			generatedKeys.close();
			
			var answersDAO = new AnswerDAO();
			for (var answer : answers)
				answersDAO.insert(answer);
		}
		catch (SQLException exception)
		{
			throw new DAOException("QuestionDAO::insert", exception);
		}
		finally
		{
			ConnectionFactory.closeConnection(connection, statement, null);
		}
	}
	
	@Override
	protected Question parseOne(ResultSet resultSet) throws SQLException, DAOException
	{
		int
				id = resultSet.getInt("id"),
				ownerId = resultSet.getInt("owner_id");
		String content = resultSet.getString("content");
		
		var answersDAO = new AnswerDAO();
		var answers = answersDAO.getByOwnerId(id);
		
		return new Question(id, ownerId, content, answers);
	}
	
	@Override
	protected String getByIdQuery()
	{
		return SQL.GET_BY_ID.toString();
	}
	
	@Override
	protected String getByOwnerIdQuery()
	{
		return SQL.GET_BY_OWNER_ID.toString();
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
	
	private enum SQL
	{
		GET_BY_ID("SELECT* FROM `questions` WHERE id = ?"),
		GET_ALL("SELECT* FROM `questions`"),
		GET_BY_OWNER_ID("SELECT* FROM `questions` WHERE owner_id = ?"),
		INSERT("INSERT INTO `questions` (content, owner_id) VALUES (?,?)");
		
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
