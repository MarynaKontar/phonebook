package com.getjavajob.kovarnevm.phonebook.service;

import com.getjavajob.kovarnevm.phonebook.common.Employee;
import com.getjavajob.kovarnevm.phonebook.dao.CrudDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends AbstractService<Employee> {

    @Autowired
    public EmployeeService(CrudDAO<Employee> crudDAO) {
        this.crudDAO = crudDAO;
    }
}
