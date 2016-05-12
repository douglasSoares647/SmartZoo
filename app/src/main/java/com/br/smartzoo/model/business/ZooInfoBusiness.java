package com.br.smartzoo.model.business;

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
    }



}
