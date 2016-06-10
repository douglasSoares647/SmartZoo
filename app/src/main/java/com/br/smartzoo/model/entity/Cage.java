package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 18/04/16.
 */
public class Cage implements Comparable<Cage>{

    private int dirtyFactor;

    private Long id;
    private String name;
    private String animalType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setDirtyFactor(int dirtyFactor) {
        this.dirtyFactor = dirtyFactor;
        if (dirtyFactor > animals.size() * 2 / 3) {
            isClean = false;
        }
    }


    public boolean checkCapacity() {
        return animals.size() < capacity;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Cage another) {
        if(this.getId()>another.getId())return 1;
        else if(this.getId()<another.getId()) return -1;
        return 0;

    }



    public enum CageEnum {

        Cage1("Jaula para 1 animal",100.0,1 ),
        Cage2("Jaula para 2 animais",200.0,2 ),
        Cage3("Jaula para 3 animais",300.0,3 ),
        Cage4("Jaula para 4 animais",400.0,4 ),
        Cage5("Jaula para 5 animais",500.0,5 ),
        Cage6("Jaula para 6 animais",600.0,6 ),
        Cage7("Jaula para 7 animais",700.0,7 ),
        Cage8("Jaula para 8 animais",800.0,8 ),
        Cage9("Jaula para 9 animais", 900.0,9),
        Cage10("Jaula para 10 animais",1000.0, 10 )
        ;


        private String name;
        private Double price;
        private Integer capacity;


        CageEnum(String name, Double price, Integer capacity) {
            this.name = name;
            this.price = price;
            this.capacity = capacity;
        }


        public String getName() {
            return name;
        }

        public Double getPrice() {
            return price;
        }

        public Integer getCapacity() {
            return capacity;
        }
    }
}
