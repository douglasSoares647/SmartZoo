package com.br.smartzoo.model.business;

import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.persistence.AnimalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class AnimalBusiness {

    public static void save(Animal animal) {

        AnimalRepository.save(animal);
    }


    public static List<Animal> getAllAnimals() {
        List<Animal> animals = AnimalRepository.getAllAnimals();

        return animals;

    }

    public static int sell(Long animalId) {
        return AnimalRepository.delete(animalId);
    }


    public static List<Animal> getAnimalsToTreat() {

        List<Animal> animalsToTreat = new ArrayList<>();

        for (Cage cage : ZooInfo.cages) {
            for (Animal animal : cage.getAnimals()) {
                if (!animal.isHealthy()) {
                    animalsToTreat.add(animal);
                }
            }
        }
        return animalsToTreat;
    }

    public static void removeAnimalsFromCage(Long idCage) {
        List<Animal> animalsByCage = getAnimalsByCage(idCage);
        AnimalRepository.removeAnimalsFromCage(animalsByCage);
    }

    private static List<Animal> getAnimalsByCage(Long idCage) {
        return AnimalRepository.getAnimalsByCage(idCage);
    }

    public static void putAnimalInCage(Cage cage, Animal animal) {
        AnimalRepository.putAnimalInCage(cage, animal);
    }
}
