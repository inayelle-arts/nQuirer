package com.inayelle.model.dao;

import com.inayelle.model.dao.base.BaseGenericDAO;
import com.inayelle.model.dao.base.ConnectionFactory;
import com.inayelle.model.dao.exception.DAOException;
import com.inayelle.model.entity.Answer;

import java.sql.*;

public class AnswerDAO extends BaseGenericDAO<Answer>
{
	@Override
	public void update(Answer entity) throws DAOException
	{
	
	}
	
	@Override
	public void delete(Answer entity) throws DAOException
	{
	
	}
	
	@Override
	public void insert(Answer entity) throws DAOException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		String content = entity.getContent();
		int ownerId = entity.getOwnerId();
		boolean isCorrect = entity.getIsCorrect();
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(SQL.INSERT.toString(), new String[]{"id"});
			statement.setString(1, content);
			statement.setInt(2, ownerId);
			statement.setBoolean(3, isCorrect);
			statement.execute();
			
			var generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();
			int newId = generatedKeys.getInt(1);
			entity.setId(newId);
			generatedKeys.close();
		}
		catch (SQLException exception)
		{
			throw new DAOException("AnswerDAO::insert", exception);
		}
		finally
		{
			ConnectionFactory.closeConnection(connection, statement, null);
		}
	}
	
	@Override
	protected Answer parseOne(ResultSet resultSet) throws SQLException
	{
		int
				id = resultSet.getInt("id"),
				ownerId = resultSet.getInt("owner_id");
		String content = resultSet.getString("content");
		boolean isCorrect = resultSet.getBoolean("is_correct");
		
		return new Answer(id, ownerId, content, isCorrect);
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
		GET_BY_ID("SELECT* FROM `answers` WHERE id = ?"),
		GET_ALL("SELECT* FROM `answers`"),
		GET_BY_OWNER_ID("SELECT* FROM `answers` WHERE owner_id = ?"),
		INSERT("INSERT INTO `answers` (content, owner_id, is_correct) VALUES (?,?,?)");
		
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
