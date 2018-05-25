package com.inayelle.model.dao.base;

import com.inayelle.model.dao.exception.DAOException;

import java.util.Set;

public interface IGenericDAO<EntityT>
{
	EntityT getById(int id) throws DAOException;
	Set<EntityT> getByOwnerId(int id) throws DAOException;
	Set<EntityT> getAll() throws DAOException;
	
	void update(EntityT entity) throws DAOException;
	void delete(EntityT entity) throws DAOException;
	void insert(EntityT entity) throws DAOException;
}