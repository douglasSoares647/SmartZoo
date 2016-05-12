package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.persistence.EmployeeRepository;
import com.br.smartzoo.model.persistence.FeederRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class FeederBusiness {

    public static void save(Feeder feeder){

        EmployeeBusiness.save(feeder);
        FeederRepository.save(feeder);
    }


    public static void saveCagesOnHistory(Feeder feeder,Cage cage){
        FeederRepository.saveCageOnHistory(feeder,cage);
    }

    public static HashMap<Integer,Integer> getCagesFilledHistory(Feeder feeder){
        return FeederRepository.getCagesHistoryOfFeeder(feeder);
    }


    public static HashMap<Integer,Integer> getCagesFilledHistoryByDate(Feeder feeder, Date startDate, Date endDate){
        return FeederRepository.getCagesHistoryOfFeeder(feeder,startDate,endDate);
    }



    public static List<Feeder> getFeeders(){
        return FeederRepository.getAllFeeders();
    }


}
