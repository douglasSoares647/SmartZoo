package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.persistence.CageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class CageBusiness {

    public static long save(Cage cage) {

        return CageRepository.save(cage);

    }


    public static List<Cage> getAllCages() {
        List<Cage> cages;

        cages = CageRepository.getAllCages();

        return cages;
    }


    public static Cage getCageById(Long id) {
        Cage cage;
        cage = CageRepository.getCageById(id);

        return cage;
    }

    public static List<Cage> getCagesByAnimalType(Animal animal) {
        List<Cage> cagesByAnimalType = new ArrayList<>();

        for (Cage cage : ZooInfo.cages) {
            if (cage.getAnimalType().equals(animal.getType()))
                cagesByAnimalType.add(cage);
        }

        return cagesByAnimalType;
    }

    public static int destroyCage(Long idCage) {
        return CageRepository.delete(idCage);

    }

    public static int clearCage(Long idCage) {
        return CageRepository.clearCage(idCage);
    }
}
