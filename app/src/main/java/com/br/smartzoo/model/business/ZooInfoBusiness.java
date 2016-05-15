package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.persistence.ZooInfoRepository;

/**
 * Created by dhb_s on 5/12/2016.
 */
public class ZooInfoBusiness {


    public static void save(){
        ZooInfoRepository.save();
    }


    public static void load(){
        ZooInfoRepository.LoadZooInfo();
        ZooInfo.employees.addAll(FeederBusiness.getFeeders());
        ZooInfo.employees.addAll(JanitorBusiness.getJanitors());
        ZooInfo.employees.addAll(VeterinaryBusiness.getVeterinaries());

        ZooInfo.cages.addAll(CageBusiness.getAllCages());
    }



}
