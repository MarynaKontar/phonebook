package com.getjavajob.kovarnevm.phonebook.service;

import com.getjavajob.kovarnevm.phonebook.common.Department;
import com.getjavajob.kovarnevm.phonebook.dao.CrudDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends AbstractService<Department> {

    @Autowired
    public DepartmentService(CrudDAO<Department> crudDAO) {
        this.crudDAO = crudDAO;
    }

}
