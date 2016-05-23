package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 18/04/16.
 */
public class Cage {

    private int dirtyFactor;

    private Long id;
    private String name;
    private Double price;
    private List<Animal> animals;
    private List<Food> foods;
    private Integer capacity;
    private boolean isClean;
    private boolean isSupplied;

    public Cage() {
        foods = new ArrayList<>();
        animals = new ArrayList<>();
        isClean = true;
        isSupplied = false;
    }

    public Cage(String name, List<Animal> animals, List<Food> foods, Double price, boolean isClean
            , boolean isSupplied, Integer capacity) {
        this.name = name;
        this.animals = animals;
        this.capacity = capacity;
        this.price = price;
        this.foods = foods;
        this.isClean = isClean;
        this.isSupplied = isSupplied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public void setFoods(Food food) {
        foods.add(food);
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public void setSupplied(boolean supplied) {
        isSupplied = supplied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setIsClean(boolean isClean) {
        this.isClean = isClean;
    }

    public boolean isSupplied() {
        return isSupplied;
    }

    public void setIsSupplied(boolean isSupplied) {
        this.isSupplied = isSupplied;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public int getDirtyFactor() {
        return dirtyFactor;
    }

    public void setDirtyFactor(int dirtyFactor) {
        this.dirtyFactor = dirtyFactor;
        if (dirtyFactor > animals.size() * 2 / 3) {
            isClean = false;
        }
    }

    public String toString() {
        StringBuilder info = new StringBuilder();

        info.append("Nome da Jaula:" + name);
        info.append("\nAnimais na jaula:");
        for (Animal animal : animals) {
            info.append("\n" + animal.getName());
        }
        info.append("\nJaula está limpa?: " + isClean);
        info.append("Jaula possui comida?:" + isSupplied);
        return info.toString();
    }


    public boolean checkCapacity() {
        return animals.size() <= capacity;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
