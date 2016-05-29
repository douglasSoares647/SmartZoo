package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.enums.FoodEnum;
import com.br.smartzoo.model.environment.Player;
import com.br.smartzoo.model.environment.Visitor;
import com.br.smartzoo.model.environment.ZooInfo;

import java.util.Random;
import java.util.Timer;

/**
 * Created by douglas on 03/05/16.
 */
public class BusinessRules {


    public static Timer timer = new Timer();
    private static Double idealPrice;

    public static void generateVisitor(){

        Random random = new Random();
        if(ZooInfo.price >=idealPrice*1.8){
            if(random.nextInt(10)- Player.dificultity>8){
                createVisitor();
            }
        }
        else if(ZooInfo.price >=idealPrice*1.7){
            if(random.nextInt(10)- Player.dificultity>7){
                createVisitor();
            }
        }
        else if(ZooInfo.price >=idealPrice*1.6){
            if(random.nextInt(10)- Player.dificultity>6){
                createVisitor();
            }
        }
        else if(ZooInfo.price >=idealPrice*1.5){
            if(random.nextInt(10)- Player.dificultity>5){
                createVisitor();
            }
        }
        else if(ZooInfo.price >=idealPrice*1.4){
            if(random.nextInt(10)- Player.dificultity>4){
                createVisitor();
            }
        }
        else if(ZooInfo.price >=idealPrice*1.3){
            if(random.nextInt(10)- Player.dificultity>3){
                createVisitor();
            }
        }
        else if(ZooInfo.price >=idealPrice*1.2){
            if(random.nextInt(10)- Player.dificultity>2){
                createVisitor();
            }
        }
        else if(ZooInfo.price >=idealPrice*1.1){
            if(random.nextInt(10)- Player.dificultity>1){
                createVisitor();
            }
        }
        else{
            if(random.nextInt(10)- Player.dificultity>0) {
                createVisitor();
            }
        }

    }

    private static void createVisitor(){
        Visitor visitor = new Visitor();
        ZooInfo.visitors.add(visitor);
        visitor.visit();
        ZooInfo.money += ZooInfo.price;
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

       // price = price;//30 dias contando que 100 pessoas visitem o zoo

        idealPrice = price;

    }


    public static boolean haveMoneyToBuyAnimal(Animal animal){
        if(ZooInfo.money>animal.getPrice()) {
            return true;
        }
        return false;
    }


    public static Boolean haveMoneyToBuyCage(Cage cage){
        if(ZooInfo.money>cage.getPrice()) {
            return true;
        }
        else{
            return false;
        }
    }



    public static Boolean haveMoneyToBuyEmployee(Employee employee){
        return ZooInfo.money > employee.getPrice();
    }

}

