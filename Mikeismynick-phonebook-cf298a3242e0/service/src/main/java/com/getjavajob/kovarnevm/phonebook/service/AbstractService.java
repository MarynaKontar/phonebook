package com.getjavajob.kovarnevm.phonebook.service;

import com.getjavajob.kovarnevm.phonebook.common.BaseEntity;
import com.getjavajob.kovarnevm.phonebook.dao.CrudDAO;
import com.getjavajob.kovarnevm.phonebook.dao.DAOException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

abstract public class AbstractService<T extends BaseEntity> implements CrudService<T> {

    protected CrudDAO<T> crudDAO;

    @Override
    @Transactional
    public T save(T entity) throws ServiceException {
        T focusEntity = null;
        try {
            if (entity.getId() != 0) {
                focusEntity = crudDAO.update(entity);
            } else {
                focusEntity = crudDAO.add(entity);
            }
            return focusEntity;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void remove(T entity) throws ServiceException {
        try {
            crudDAO.delete(entity);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public T get(int id) throws ServiceException {
        try {
            return crudDAO.get(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> getAll() throws ServiceException {
        try {
            return crudDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
