package com.br.smartzoo.model.environment;

import android.os.CountDownTimer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by douglas on 03/05/16.
 */
public class BusinessRules {


    public static Timer timer = new Timer();

    public static void generateVisitor(){
        Long generateFactor = ((Double)(ZooInfo.reputation/(Player.dificultity*ZooInfo.priceToVisit))).longValue() * 1000;

        new CountDownTimer(1000, generateFactor) {
            @Override
            public void onTick(long millisUntilFinished) {
                Visitor visitor = new Visitor();
                ZooInfo.visitors.add(visitor);
                ZooInfo.money += ZooInfo.priceToVisit;
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }



    public static String calculateExpensivePriceIndicator(Double choosenPrice){
        //Ideia a ser implementada
        //Calcular com base na reputação do Zoo + quantidade de animais e seus respectivos foodToBeSatisfied
        // + média de preço dos foods (ainda necessita ser feito)
        // + salário dos empregados
        // + 10% de lucro;

        Double price = 0.0;


        if(choosenPrice < (price*1.2) && choosenPrice > price){
            return "Good";
        }
        else if(choosenPrice>(price*1.2)){
            return "Expensive";
        }
        else {
            return "Low price";
        }
    }

}

