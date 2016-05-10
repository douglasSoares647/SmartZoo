package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.persistence.EmployeeRepository;

/**
 * Created by Douglas on 5/10/2016.
 */
public class EmployeeBusiness {

    public static void save(Employee employee){

        EmployeeRepository.save(employee);
    }
}
