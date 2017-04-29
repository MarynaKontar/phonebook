package com.getjavajob.kovarnevm.phonebook.dao;

import com.getjavajob.kovarnevm.phonebook.common.BaseEntity;
import org.springframework.core.GenericTypeResolver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDAO<T extends BaseEntity> implements CrudDAO<T> {

    @PersistenceContext
    private EntityManager eManager;

    private Class<T> entityType;

    public AbstractDAO() {
        //noinspection unchecked
        this.entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractDAO.class);
    }

    @Override
    public T add(T entity) throws DAOException {
        T value = null;
        try {
            value = eManager.merge(entity);
        } catch (Exception e) {
            throw new DAOException("exception in DAO add", e);
        }
        return value;
    }

    @Override
    public T update(T entity) throws DAOException {
        T value = null;
        try {
            value = eManager.merge(entity);
        } catch (Exception e) {
            throw new DAOException("exception in DAO update", e);
        }
        return value;
    }

    @Override
    public void delete(T entity) throws DAOException {
        try {
            eManager.remove(eManager.contains(entity) ? entity : eManager.merge(entity));
        } catch (Exception e) {
            throw new DAOException("exception in DAO delete", e);
        }
    }

    @Override
    public T get(int id) throws DAOException {
        T value = null;
        try {
            value = eManager.find(entityType, id);
        } catch (Exception e) {
            throw new DAOException("exception in DAO get", e);
        }
        return value;
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> value = null;
        try {
            CriteriaBuilder cb = eManager.getCriteriaBuilder();
            CriteriaQuery<T> select = cb.createQuery(entityType);
            Root<T> e = select.from(entityType);
            value = eManager.createQuery(select.select(e)).getResultList();
        } catch (Exception e) {
            throw new DAOException("exception in DAO getAll", e);
        }
        return value;

    }
}
