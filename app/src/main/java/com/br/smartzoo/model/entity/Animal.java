package com.br.smartzoo.model.entity;


import android.os.CountDownTimer;

import com.br.smartzoo.model.interfaces.Observer;
import com.br.smartzoo.util.TimeUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by taibic on 12/04/16.
 */
public class Animal implements Observer {


    private int image;
    private String status;
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String sex;
    private Cage cage;
    private boolean isHealthy;
    private Double foodToBeSatisfied;
    private Timer biologicalClock;
    private Integer resistance;
    private Integer popularity;
    private Double price;



    public Animal(int image, Long id, String name, Integer age,Double price, Double weight
            , Cage cage, Integer resistance,  boolean isHealthy) {
        this.name = name;
        this.image= image;
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.price = price;
        this.cage = cage;
        this.cage.getAnimals().add(this);
        this.isHealthy = isHealthy;
        this.resistance = resistance;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getResistance() {
        return resistance;
    }

    public void setResistance(Integer resistance) {
        this.resistance = resistance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void  eat() {
        Double foodEaten = 0.0;
        final List<Food> cageFoods = cage.getFoods();
        List<Food> foodsToRemove = new ArrayList<>();


        if(cageFoods.isEmpty()){
            status = "Faminto";
            Thread starving = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (cageFoods.isEmpty()) {
                            try {
                                Thread.sleep(TimeUtil.starvingTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            setWeight(weight*0.99);
                            status = "Perdendo peso";
                        }
                        eat();
                    }
                });
                 starving.start();
        }
        else {
            status = "Comendo";
            //SE O ANIMAL COMEÇAR A COMER O FOOD E FICAR SATISFEITO ENQUANTO ESTIVER COMENDO, ELE COME O FOOD ATÉ O FIM
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
                            if (i > resistance) {
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
            final Double foodEaten2 = foodEaten * 0.95;
            status = "Digerindo";


            //Digerindo
            new CountDownTimer(TimeUtil.digestingInterval, TimeUtil.timeToDigest) {
                @Override
                public void onTick(long l) {
                    weight = weight - foodEaten2 / 30;
                }

                @Override
                public void onFinish() {
                    setWeight(weight - foodEaten2);
                    afterDigest();

                }
            }.start();
        }

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
        },TimeUtil.timeToFeelHungry);

    }

    @Override
    public String toString(){
    	
		return "Animal: " + name + "\nIdade: " + age + "\nPeso: "+ weight +"\n Esta saudavel? : "+ isHealthy;
    	
    }

    @Override
    public void onTick() {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }
}
