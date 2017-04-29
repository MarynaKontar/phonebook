package com.getjavajob.kovarnevm.phonebook.dao;

import com.getjavajob.kovarnevm.phonebook.common.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AddressDAOTest extends GenericDAOTest {

    @Autowired
    private AddressDAO addressDAO;

    @Test
    public void add() throws DAOException {
        addressMoscow = addressDAO.add(addressMoscow);
        assertNotNull(addressDAO.get(addressMoscow.getId()));
    }

    @Test
    public void get() throws DAOException {
        addressMoscow = addressDAO.add(addressMoscow);
        Address receivedAddress = addressDAO.get(addressMoscow.getId());
        assertEquals("Moscow", receivedAddress.getCity());
    }

    @Test
    public void getAll() throws DAOException {
        addressDAO.add(addressMoscow);
        addressDAO.add(addressVladivostok);
        assertEquals(2, addressDAO.getAll().size());
    }

    @Test
    public void update() throws DAOException {
        addressMoscow = addressDAO.add(addressMoscow);
        addressMoscow.setCity("London");
        addressDAO.update(addressMoscow);
        assertEquals("London", addressDAO.get(addressMoscow.getId()).getCity());
    }

    @Test
    public void delete() throws DAOException {
        addressMoscow = addressDAO.add(addressMoscow);
        addressDAO.delete(addressMoscow);
        assertNull(addressDAO.get(addressMoscow.getId()));
    }
}
