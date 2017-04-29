package com.getjavajob.kovarnevm.phonebook.dto.converters;

import com.getjavajob.kovarnevm.phonebook.common.Employee;

import java.util.List;

/**
 * Created by Zen on 01.05.2016.
 */
public interface ConverterDTO<T> {

    T toDTO(Employee entity);

    List<T> listToDTO(List<Employee> entities);
}
