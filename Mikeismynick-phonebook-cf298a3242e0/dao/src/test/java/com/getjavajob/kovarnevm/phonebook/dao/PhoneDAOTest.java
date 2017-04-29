package com.getjavajob.kovarnevm.phonebook.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PhoneDAOTest extends GenericDAOTest {

    @Autowired
    private PhoneDAO phoneDAO;

    @Test
    public void add() throws DAOException {
        personalPhone = phoneDAO.add(personalPhone);
        assertNotNull(phoneDAO.get(personalPhone.getId()));
    }

    @Test
    public void get() throws DAOException {
        personalPhone = phoneDAO.add(personalPhone);
        assertEquals("+79265771212", phoneDAO.get(personalPhone.getId()).getPhone());
    }

    @Test
    public void getAll() throws DAOException {
        phoneDAO.add(personalPhone);
        phoneDAO.add(workPhone);
        assertEquals(2, phoneDAO.getAll().size());
    }

    @Test
    public void update() throws DAOException {
        personalPhone = phoneDAO.add(personalPhone);
        personalPhone.setPhone("+79265770277");
        phoneDAO.update(personalPhone);
        assertEquals("+79265770277", phoneDAO.get(personalPhone.getId()).getPhone());
    }

    @Test
    public void delete() throws DAOException {
        personalPhone = phoneDAO.add(personalPhone);
        phoneDAO.delete(personalPhone);
        assertNull(phoneDAO.get(personalPhone.getId()));
    }
}
