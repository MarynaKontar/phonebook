package com.getjavajob.kovarnevm.phonebook.service;

import com.getjavajob.kovarnevm.phonebook.common.Department;
import com.getjavajob.kovarnevm.phonebook.dao.DAOException;
import com.getjavajob.kovarnevm.phonebook.dao.DepartmentDAO;
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
public class DepartmentServiceTest {

    private Department it;

    @InjectMocks
    private DepartmentService departmentService;
    @Mock
    private DepartmentDAO departmentDAO;

    @Before
    public void setUpEntity() {
        it = new Department();
        it.setName("IT");
    }

    @Test
    public void saveTest() throws ServiceException, DAOException {
        when(departmentService.save(it)).thenReturn(it);
        departmentService.save(it);
        verify(departmentDAO).add(it);
    }

    @Test
    public void getTest() throws ServiceException, DAOException {
        when(departmentService.get(1)).thenReturn(it);
        departmentService.get(1);
        verify(departmentDAO).get(1);
    }

    @Test
    public void getAllTest() throws ServiceException, DAOException {
        List<Department> excepted = new ArrayList<>(Arrays.asList(it));
        when(departmentService.getAll()).thenReturn(excepted);
        departmentService.getAll();
        verify(departmentDAO).getAll();
    }

    @Test
    public void updateTest() throws ServiceException, DAOException {
        it.setId(1);
        when(departmentService.save(it)).thenReturn(it);
        departmentService.save(it);
        verify(departmentDAO).update(it);
    }

    @Test
    public void removeTest() throws ServiceException, DAOException {
        departmentService.remove(it);
        verify(departmentDAO).delete(it);
    }
}