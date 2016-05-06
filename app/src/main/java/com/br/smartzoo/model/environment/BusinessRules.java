package com.br.smartzoo.model.environment;

import android.os.CountDownTimer;

import com.br.smartzoo.model.entity.animal.Animal;
import com.br.smartzoo.model.entity.employee.Employee;
import com.br.smartzoo.model.entity.jail.Cage;
import com.br.smartzoo.model.enums.FoodEnum;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by douglas on 03/05/16.
 */
public class BusinessRules {


    public static Timer timer = new Timer();
    private static Double idealPrice;

    public static void generateVisitor(){

        Random random = new Random();
        if(ZooInfo.priceToVisit>=idealPrice*1.8){
            if(random.nextInt(10)>8){
                createVisitor();
            }
        }
        else if(ZooInfo.priceToVisit>=idealPrice*1.7){
            if(random.nextInt(10)>7){
                createVisitor();
            }
        }
        else if(ZooInfo.priceToVisit>=idealPrice*1.6){
            if(random.nextInt(10)>6){
                createVisitor();
            }
        }
        else if(ZooInfo.priceToVisit>=idealPrice*1.5){
            if(random.nextInt(10)>5){
                createVisitor();
            }
        }
        else if(ZooInfo.priceToVisit>=idealPrice*1.4){
            if(random.nextInt(10)>4){
                createVisitor();
            }
        }
        else if(ZooInfo.priceToVisit>=idealPrice*1.3){
            if(random.nextInt(10)>3){
                createVisitor();
            }
        }
        else if(ZooInfo.priceToVisit>=idealPrice*1.2){
            if(random.nextInt(10)>2){
                createVisitor();
            }
        }
        else if(ZooInfo.priceToVisit>=idealPrice*1.1){
            if(random.nextInt(10)>1){
                createVisitor();
            }
        }
        else{
            if(random.nextInt(10)>0) {
                createVisitor();
            }
        }

    }

    private static void createVisitor(){
        Visitor visitor = new Visitor();
        ZooInfo.visitors.add(visitor);
        ZooInfo.money += ZooInfo.priceToVisit;
    }



    public static String calculatePriceIndicator(Double choosenPrice){
        if(choosenPrice < (idealPrice*1.2) && choosenPrice >= idealPrice){
            return "Good";
        }
        else if(choosenPrice>(idealPrice*1.2)){
            return "Expensive";
        }
        else {
            return "Low price";
        }
    }


    public static void calculateIdealPrice(){
        //Calcular com base na reputação do Zoo + quantidade de animais e seus respectivos foodToBeSatisfied
        // + média de preço dos foods (ainda necessita ser feito)
        // + salário dos empregados
        // + 10% de lucro;
        Double price = 0.0;

        for(Cage cage : ZooInfo.cages){
            for(Animal animal : cage.getAnimals()){
                price += animal.getFoodToBeSatisfied() * 120 * FoodEnum.Meat.Beef.getPrice();
            }
        }

        for(Employee employee : ZooInfo.employees){
            price+=employee.getSalary();
        }

        price +=ZooInfo.reputation*0.05;
        price = price*1.1;

        idealPrice = price;

    }

}

