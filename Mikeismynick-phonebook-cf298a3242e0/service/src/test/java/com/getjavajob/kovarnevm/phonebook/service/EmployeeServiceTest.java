package com.getjavajob.kovarnevm.phonebook.service;

import com.getjavajob.kovarnevm.phonebook.common.Employee;
import com.getjavajob.kovarnevm.phonebook.dao.DAOException;
import com.getjavajob.kovarnevm.phonebook.dao.EmployeeDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private Employee vasya;

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeDAO employeeDAO;

    @Before
    public void setUpEntity() {
        vasya = new Employee();
        vasya.setFirstName("Vasya");
        vasya.setLastName("Petrov");
    }

    @Test
    public void saveTest() throws ServiceException, DAOException {
        when(employeeService.save(vasya)).thenReturn(vasya);
        employeeService.save(vasya);
        verify(employeeDAO).add(vasya);
    }

    @Test
    public void getTest() throws ServiceException, DAOException {
        when(employeeService.get(1)).thenReturn(vasya);
        employeeService.get(1);
        verify(employeeDAO).get(1);

    }

    @Test
    public void getAllTest() throws ServiceException, DAOException {
        List<Employee> excepted = new ArrayList<>(Arrays.asList(vasya));
        when(employeeService.getAll()).thenReturn(excepted);
        employeeService.getAll();
        verify(employeeDAO).getAll();
    }

    @Test
    public void updateTest() throws ServiceException, DAOException {
        vasya.setId(1);
        when(employeeService.save(vasya)).thenReturn(vasya);
        employeeService.save(vasya);
        verify(employeeDAO).update(vasya);
    }

    @Test
    public void removeTest() throws ServiceException, DAOException {
        employeeService.remove(vasya);
        verify(employeeDAO).delete(vasya);
    }
}