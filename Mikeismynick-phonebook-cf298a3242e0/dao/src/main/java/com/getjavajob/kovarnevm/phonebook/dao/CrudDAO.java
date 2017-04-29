package com.getjavajob.kovarnevm.phonebook.dao;

import com.getjavajob.kovarnevm.phonebook.common.BaseEntity;

import java.util.List;

public interface CrudDAO<T extends BaseEntity> {

    T add(T entity) throws DAOException;

    T update(T entity) throws DAOException;

    void delete(T entity) throws DAOException;

    T get(int id) throws DAOException;

    List<T> getAll() throws DAOException;
}
