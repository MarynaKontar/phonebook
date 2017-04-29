package com.getjavajob.kovarnevm.phonebook.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class EmployeeDAOTest extends GenericDAOTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    public void add() throws DAOException {
        vasya = employeeDAO.add(vasya);
        assertNotNull(employeeDAO.get(vasya.getId()));
    }

    @Test
    public void get() throws DAOException {
        vasya = employeeDAO.add(vasya);
        assertEquals(vasya, employeeDAO.get(vasya.getId()));
    }

    @Test
    public void update() throws DAOException {
        vasya = employeeDAO.add(vasya);
        vasya.setLastName("Sidorov");
        employeeDAO.update(vasya);
        assertEquals("Sidorov", employeeDAO.get(vasya.getId()).getLastName());
    }

    @Test
    public void getAll() throws DAOException {
        employeeDAO.add(vasya);
        employeeDAO.add(petya);
        assertEquals(2, employeeDAO.getAll().size());
    }

    @Test
    public void delete() throws DAOException {
        vasya = employeeDAO.add(vasya);
        employeeDAO.delete(vasya);
        assertNull(employeeDAO.get(vasya.getId()));
    }
}
