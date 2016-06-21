package com.br.smartzoo.ui.view;

import com.br.smartzoo.model.entity.Animal;

import java.util.List;

/**
 * Created by adenilson on 01/06/16.
 */
public interface AnimalListView {

    void onAnimalListLoad(List<Animal> animalList);

    void updateList(Animal animal);

    void onAnimalPutted(Animal animal);

    void onAnimalListEmpty();
}
