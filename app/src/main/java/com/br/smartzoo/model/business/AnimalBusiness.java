package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.animal.Animal;
import com.br.smartzoo.model.persistence.AnimalRepository;

import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class AnimalBusiness {

    public static void save (Animal animal){

        AnimalRepository.save(animal);
    }



    public static List<Animal> getAllAnimals(){
        List<Animal> animals = AnimalRepository.getAllAnimals();



        return animals;

    }
}
