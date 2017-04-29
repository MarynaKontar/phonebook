package com.getjavajob.kovarnevm.phonebook.service;

import com.getjavajob.kovarnevm.phonebook.common.BaseEntity;

import java.util.List;

public interface CrudService<T extends BaseEntity> {

    T save(T entity) throws ServiceException;

    void remove(T entity) throws ServiceException;

    T get(int id) throws ServiceException;

    List<T> getAll() throws ServiceException;
}
