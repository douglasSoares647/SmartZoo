package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.persistence.EmployeeRepository;
import com.br.smartzoo.model.persistence.FeederRepository;

import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class FeederBusiness {

    public static void save(Feeder feeder){

        EmployeeBusiness.save(feeder);
        FeederRepository.save(feeder);
    }


    public static void saveCages(Feeder feeder){
        FeederRepository.saveCages(feeder);
    }


    public static List<Feeder> getFeeders(){
        return FeederRepository.getAllFeeders();
    }


}
