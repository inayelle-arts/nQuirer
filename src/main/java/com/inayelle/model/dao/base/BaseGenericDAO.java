package com.inayelle.model.dao.base;

import com.inayelle.model.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseGenericDAO<EntityT> implements IGenericDAO<EntityT>
{
	@Override
	public EntityT getById(int id) throws DAOException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(this.getByIdQuery());
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			return resultSet.next() ? parseOne(resultSet) : null;
		}
		catch (SQLException exception)
		{
			throw new DAOException("BaseGenericDAO::getById", exception);
		}
		finally
		{
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}
	
	@Override
	public Set<EntityT> getAll() throws DAOException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Set<EntityT> result = new HashSet<>();
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(this.getAllQuery());
			resultSet = statement.executeQuery();
			
			while (resultSet.next())
				result.add(this.parseOne(resultSet));
			
			return result;
		}
		catch (SQLException exception)
		{
			throw new DAOException("BaseGenericDAO::getById", exception);
		}
		finally
		{
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}
	
	@Override
	public Set<EntityT> getByOwnerId(int id) throws DAOException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Set<EntityT> result = new HashSet<>();
		
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(this.getByOwnerIdQuery());
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next())
				result.add(parseOne(resultSet));
			
			return result;
		}
		catch (SQLException exception)
		{
			throw new DAOException("BaseGenericDAO::getByOwnerId", exception);
		}
		finally
		{
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}
	
	protected abstract EntityT parseOne(ResultSet resultSet) throws SQLException, DAOException;
	
	protected abstract String getByIdQuery();
	protected abstract String getByOwnerIdQuery();
	protected abstract String getAllQuery();
	protected abstract String insertQuery();
}
