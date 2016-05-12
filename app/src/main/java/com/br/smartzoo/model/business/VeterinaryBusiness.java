package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.persistence.VeterinaryRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class VeterinaryBusiness {

    public static void save(Veterinary veterinary){
        EmployeeBusiness.save(veterinary);
        VeterinaryRepository.save(veterinary);
    }


    public static void saveAnimalTreatedByVetOnHistory(Veterinary veterinary, Animal animal){
        VeterinaryRepository.saveAnimalHistory(veterinary,animal);
    }


    public static HashMap<Integer, Integer> getTreatmentsHistory(Veterinary veterinary){
        return VeterinaryRepository.getAnimalsOnHistory(veterinary);
    }

    public static HashMap<Integer,Integer> getTreatmentsHistoryByDate(Veterinary veterinary, Date startDate, Date endDate){
        return VeterinaryRepository.getAnimalsOnHistory(veterinary,startDate,endDate);
    }

    public static List<Veterinary> getVeterinaries(){

        return VeterinaryRepository.getVeterinaries();
    }
}
