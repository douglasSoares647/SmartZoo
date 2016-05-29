package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.persistence.EmployeeRepository;

/**
 * Created by Douglas on 5/10/2016.
 */
public class EmployeeBusiness {

    public static void save(Employee employee){

        employee.setId(EmployeeRepository.save(employee));

        if(employee instanceof Veterinary){
            VeterinaryBusiness.save((Veterinary)employee);
        }else if(employee instanceof Janitor){
           JanitorBusiness.save((Janitor)employee);
        }else if(employee instanceof Feeder){
            FeederBusiness.save((Feeder) employee);
        }


    }

    public static Integer delete(Long idEmployee){
        return EmployeeRepository.delete(idEmployee);
    }

    public static Integer updateSalary(Long id, Double salary) {
        return EmployeeRepository.updateSalary(id, salary);
    }

    public static boolean existsEmployee(Employee employee) {
        return EmployeeRepository.existsEmployee(employee);
    }
}
