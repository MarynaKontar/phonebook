package com.getjavajob.kovarnevm.phonebook.dto.converters;

import com.getjavajob.kovarnevm.phonebook.common.Employee;
import com.getjavajob.kovarnevm.phonebook.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeToDTO implements ConverterDTO<EmployeeDTO> {

    @Override
    public EmployeeDTO toDTO(Employee entity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(entity.getId());
        employeeDTO.setFirstName(entity.getFirstName());
        employeeDTO.setLastName(entity.getLastName());
        employeeDTO.setMiddleName(entity.getMiddleName());
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> listToDTO(List<Employee> entities) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee entity : entities) {
            employeeDTOList.add(toDTO(entity));
        }
        return employeeDTOList;
    }
}
