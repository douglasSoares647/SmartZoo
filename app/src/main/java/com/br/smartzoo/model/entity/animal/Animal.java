package com.br.smartzoo.model.entity.animal;


import android.os.CountDownTimer;

import com.br.smartzoo.model.entity.food.Food;
import com.br.smartzoo.model.entity.jail.Cage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by taibic on 12/04/16.
 */
public class Animal {

    private int timeToFeelHungry = 10800000;// 30 minutes
    private int timeToDigest = 1800000; // 30 minutes
    private int digestingInterval = 60000; // 1 minute
    private String status;


    private String name;
    private Integer age;
    private Double weight;
    private String sex;
    private Cage cage;
    private boolean isHealthy;
    private Double foodToBeSatisfied;
    private Timer biologicalClock;
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
        biologicalClock = new Timer();
    }

    public Animal(){
        biologicalClock = new Timer();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Integer getResistence() {
        return resistence;
    }

    public void setResistence(Integer resistence) {
        this.resistence = resistence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public void  eat() {
        Double foodEaten = 0.0;
        List<Food> cageFoods = cage.getFoods();
        List<Food> foodsToRemove = new ArrayList<>();
        //SE O ANIMAL COMEÇAR A COMER O FOOD E FICAR SATISFEITO ENQUANTO ESTIVER COMENDO, ELE COME O FOOD ATÉ O FIM
        status = "Comendo";
        for (Food food : cageFoods) {
            if (foodEaten < foodToBeSatisfied) {
                foodEaten += food.getWeight();
                foodsToRemove.add(food);


                //Checa validade da comida e se estiver estragada o animal tem chance de ficar doente
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
                        }
                    }
                }

            } else {
                break;
            }
        }

        cageFoods.removeAll(foodsToRemove);



        weight = weight + foodEaten;
        final Double foodEaten2 = foodEaten *0.9;
        status = "Digerindo";


        //Digerindo
        new CountDownTimer(digestingInterval, timeToDigest) {
            @Override
            public void onTick(long l) {
                weight = weight - foodEaten2/30;
            }

            @Override
            public void onFinish() {
                setWeight(weight - foodEaten2);
                afterDigest();

            }
        }.start();


    }

    private void afterDigest() {
        cage.setDirtyFactor(cage.getDirtyFactor()+1);
        status = "Digestão finalizada";

        //Tempo para sentir fome novamente
        biologicalClock.schedule(new TimerTask() {
            @Override
            public void run() {
                status = "Faminto";
            }
        },timeToFeelHungry);

    }

    @Override
    public String toString(){
    	
		return "Animal: " + name + "\nIdade: " + age + "\nPeso: "+ weight +"\n Esta saudavel? : "+ isHealthy;
    	
    }

}
