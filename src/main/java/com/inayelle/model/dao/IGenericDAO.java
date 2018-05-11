package com.inayelle.model.dao;

import java.util.Set;

public interface IGenericDAO<EntityT>
{
	EntityT getById(int id) throws DAOException;
	EntityT getFirst() throws DAOException;
	Set<EntityT> getAll() throws DAOException;
	
	void update(EntityT entity) throws DAOException;
	void delete(EntityT entity) throws DAOException;
	void insert(EntityT entity) throws DAOException;
}