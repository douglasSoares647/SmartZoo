package com.br.smartzoo.model.environment;

import com.br.smartzoo.model.entity.animal.Animal;
import com.br.smartzoo.model.entity.jail.Cage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by douglas on 03/05/16.
 */
public class Visitor {

    private String status;
    private Date arrivalDate;
    private Double reputationGenerated = 0.0;

    public Visitor() {
        arrivalDate = Calendar.getInstance().getTime();
        visit();
    }

    public void visit(){
        List<Cage> cagesToVisit = ZooInfo.cages;
        Integer timeToEndTheVisit = 1800000;

        Double reputationGeneratedByPrice = 10/ZooInfo.priceToVisit;
        reputationGenerated += reputationGeneratedByPrice;
        status = "Visitando";

        for(Cage cage : cagesToVisit){
            for(Animal animal : cage.getAnimals()){
                Random random = new Random();
                if(random.nextInt(11)+1 < animal.getPopularity()){
                    reputationGenerated +=  animal.getPopularity()*0.01;
                    timeToEndTheVisit+= animal.getPopularity() * 60000;
                }
            }

            if(!cage.isClean()){
                reputationGenerated -= cage.getDirtyFactor()*0.01;
            }


        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                status = "Indo embora";
                ZooInfo.visitors.remove(Visitor.this);
                ZooInfo.reputation += reputationGenerated;
            }
        }, timeToEndTheVisit);

    }




}
