package com.br.smartzoo.model.entity.animal;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.br.smartzoo.model.entity.food.Food;
import com.br.smartzoo.model.entity.jail.Cage;
import com.br.smartzoo.model.exception.InvalidWeightException;
import com.br.smartzoo.model.timer.DigestionTimer;

/**
 * Created by taibic on 12/04/16.
 */
public class Animal {


    private String name;
    private Integer age;
    private Double weight;
    private Cage cage;
    private boolean isHealthy;
    private Double foodToBeSatisfied;
    private DigestionTimer digestionTimer;

    //fragilidade do animal a contrair doenças ao comer
    private Integer resistence;



    public Animal(String name, Integer age, Double weight, Cage cage, Integer resistence,  boolean isHealthy) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.cage = cage;
        this.cage.getAnimals().add(this);
        this.isHealthy = isHealthy;
        this.resistence = resistence;
        foodToBeSatisfied = weight*0.15;
        digestionTimer = new DigestionTimer();
    }

    public Animal(){
    	digestionTimer = new DigestionTimer();
    }


    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
        this.cage.getAnimals().add(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
        foodToBeSatisfied = weight*0.15;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setIsHealthy(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }


    public Double getFoodToBeSatisfied() {
        return foodToBeSatisfied;
    }

    public void setFoodToBeSatisfied(Double foodToBeSatisfied) {
        this.foodToBeSatisfied = foodToBeSatisfied;
    }

    public void  eat() {
    if(!digestionTimer.getIsDigesting()) {
        Double foodEaten = 0.0;
        List<Food> cageFoods = cage.getFoods();
        List<Food> foodsToRemove = new ArrayList<>();
        //SE O ANIMAL COME�AR A COMER O FOOD E FICAR SATISFEITO ENQUANTO ESTIVER COMENDO, ELE COME O FOOD AT� O FIM
        for (Food food : cageFoods) {
            if (foodEaten < foodToBeSatisfied) {
                foodEaten += food.getWeight();
                foodsToRemove.add(food);

                Calendar expirationDate = Calendar.getInstance();
                expirationDate.setTime(food.getExpirationDate());


                Calendar currentDate = Calendar.getInstance();
                currentDate.set(Calendar.MONTH, currentDate.get(Calendar.MONTH) + 1);

                if (currentDate.after(expirationDate)) {
                    Random random = new Random();
                    int i = random.nextInt(8) + 1;
                    if (this.isHealthy) {
                        if (i > resistence) {
                            isHealthy = false;
                            System.out.println("Animal " + name + " ficou doente pois comeu comida estragada");
                        } else {
                            System.out.println("Animal " + name + " comeu comida estragada mas não ficou doente");
                        }
                    }
                }

            } else {
                System.out.println("Animal está satisfeito! Iniciando a digestão");
                break;
            }
        }

        cageFoods.removeAll(foodsToRemove);

        weight = weight + foodEaten;

        final Double foodEaten2 = foodEaten;


        digestionTimer.schedule(new TimerTask() {

            @Override
            public void run() {

                try {
                    digestionTimer.setIsDigesting(false);
                    if (weight < 0) {
                        throw new InvalidWeightException("Invalid weight, please check your datas");
                    }
                } catch (InvalidWeightException e) {
                    e.printStackTrace();

                }

                setWeight(weight - (foodEaten2 * 0.8));

                System.out.println("\n\n Peso do animal após digestão : " + weight);
                digestionTimer.cancel();
            }
        }, 10000);
       
       
         
        
       /* final Double foodEaten2 = foodEaten;
        new CountDownTimer(10000, 10000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                setWeight(weight - (foodEaten2 * 0.8));
                Log.i("Peso", weight.toString());
            }
        }.start();*/

        }

    }
    
    @Override
    public String toString(){
    	
		return "Animal: " + name + "\nIdade: " + age + "\nPeso: "+ weight +"\n Esta saudavel? : "+ isHealthy;
    	
    }

}
