package com.br.smartzoo.model.environment;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;

import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by douglas on 03/05/16.
 */
public class Visitor{

    private String status;

    private String name;
    private Date arrivalDate;
    private Double reputationGenerated = 0.0;
    private Cage currentCage;

    private HashMap<Cage,Integer> timeToVisitEachCage;

    public Visitor() {
        arrivalDate = Calendar.getInstance().getTime();
        timeToVisitEachCage = new HashMap<>();
        visit();
    }

    public void visit(){
        List<Cage> cagesToVisit = ZooInfo.cages;
        ZooInfo.visitors.add(this);

        Double reputationGeneratedByPrice = 10/ZooInfo.price;
        reputationGenerated += reputationGeneratedByPrice;
        status = "Visitando";

        for(Cage cage : cagesToVisit){
            Integer timeToVisitCage = 0;
            for(Animal animal : cage.getAnimals()){
                Random random = new Random();
                int chance = random.nextInt(11)+1;
                if( chance < animal.getPopularity()){
                    reputationGenerated +=  animal.getPopularity()*0.01;
                    timeToVisitCage+= chance*60000;
                }
            }

            timeToVisitEachCage.put(cage,timeToVisitCage);

            if(!cage.isClean()){
                reputationGenerated -= cage.getDirtyFactor()*0.01;
            }
        }

        Timer timerToControlVisits = new Timer();
        for(Map.Entry<Cage,Integer> entry :timeToVisitEachCage.entrySet()) {
            final Cage cage = entry.getKey();
            Integer timeToVisitCage = entry.getValue();
            timerToControlVisits.schedule(new TimerTask() {
                @Override
                public void run() {
                    status = "Visitando jaula " + cage.getName();
                    currentCage = cage;

                }
            }, timeToVisitCage);
        }

        timerToControlVisits.schedule(new TimerTask() {
            @Override
            public void run() {
                status = "Visitante " + name + " indo embora do zoo!";
                ZooInfo.visitors.remove(Visitor.this);
                ZooInfo.reputation += reputationGenerated;
            }
        }, 300000);
    }


    public Cage getCurrentCage() {
        return currentCage;
    }

    public void setCurrentCage(Cage currentCage) {
        this.currentCage = currentCage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
