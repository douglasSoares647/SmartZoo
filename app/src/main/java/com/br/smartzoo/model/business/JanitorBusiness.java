package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.persistence.JanitorRepository;

import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class JanitorBusiness {


    public static void save(Janitor janitor){
        EmployeeBusiness.save(janitor);
        JanitorRepository.save(janitor);
    }


    public static void saveCages(Janitor janitor){
        JanitorRepository.saveCages(janitor);
    }


    public static List<Janitor> getJanitors(){
       return JanitorRepository.getJanitors();
    }
}
