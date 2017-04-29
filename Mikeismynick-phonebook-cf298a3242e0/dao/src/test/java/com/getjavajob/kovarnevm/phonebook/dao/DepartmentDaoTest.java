package com.getjavajob.kovarnevm.phonebook.dao;


import com.getjavajob.kovarnevm.phonebook.common.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DepartmentDaoTest extends GenericDAOTest {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Test
    public void add() throws DAOException {
        it = departmentDAO.add(it);
        assertNotNull(departmentDAO.get(it.getId()));
    }

    @Test
    public void get() throws DAOException {
        it = departmentDAO.add(it);
        Department receivedDepartment = departmentDAO.get(it.getId());
        assertEquals("IT", receivedDepartment.getName());
    }

    @Test
    public void getAll() throws DAOException {
        departmentDAO.add(it);
        departmentDAO.add(accounting);
        assertEquals(2, departmentDAO.getAll().size());
    }

    @Test
    public void update() throws DAOException {
        it = departmentDAO.add(it);
        it.setName("Accounting");
        departmentDAO.update(it);
        assertEquals("Accounting", departmentDAO.get(it.getId()).getName());
    }

    @Test
    public void delete() throws DAOException {
        it = departmentDAO.add(it);
        departmentDAO.delete(it);
        assertNull(departmentDAO.get(it.getId()));
    }
}
