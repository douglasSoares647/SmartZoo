package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.persistence.CageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class CageBusiness {

    public static void save(Cage cage){

        CageRepository.save(cage);

    }


    public static List<Cage> getAllCages(){
        List<Cage> cages = new ArrayList<>();

        cages = CageRepository.getAllCages();

        return cages;
    }


    public static Cage getCageById(Long id){
        Cage cage = new Cage();
        cage = CageRepository.getCageById(id);

        return cage;
    }
}
