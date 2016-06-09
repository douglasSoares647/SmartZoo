package com.br.smartzoo.util;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by adenilson on 01/06/16.
 */
public class SortArrayUtil {

    public static List<Animal> sortAnimalList(String attribute, List<Animal> animals, Activity context){

        if (attribute.equals(context.getString(R.string.select_name))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getName().compareToIgnoreCase(animal2.getName());
                }
            });

        } else if (attribute.equals(context.getString(R.string.select_age))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getAge().compareTo(animal2.getAge());
                }
            });

        } else if (attribute.equals(context.getString(R.string.select_popularity))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getPopularity().compareTo(animal2.getPopularity());
                }
            });

        } else if (attribute.equals(context.getString(R.string.select_weight))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getWeight().compareTo(animal2.getWeight());
                }
            });

        } else if (attribute.equals(context.getString(R.string.select_satisfaction))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getFoodToBeSatisfied().compareTo(animal2.getFoodToBeSatisfied());
                }
            });

        } else if (attribute.equals(context.getString(R.string.select_sex))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getSex().compareTo(animal2.getSex());
                }
            });

        } else if (attribute.equals(context.getString(R.string.select_status))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getStatus().compareToIgnoreCase(animal2.getStatus());
                }
            });

        } else if (attribute.equals(context.getString(R.string.select_resistance))) {

            Collections.sort(animals, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getResistance().compareTo(animal2.getResistance());
                }
            });

        }
        return animals;
    }
}
