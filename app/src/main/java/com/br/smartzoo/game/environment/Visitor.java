package com.br.smartzoo.game.environment;

import android.os.Handler;

import com.br.smartzoo.model.business.NewsFeedBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.model.interfaces.Observer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by douglas on 03/05/16.
 */
public class Visitor implements Observer{

    private String status;
    private int timeInZoo = 0;

    private String name;
    private Date arrivalDate;
    private Double reputationGenerated = 0.0;
    private Cage currentCage;

    private TreeMap<Cage,Integer> timeToVisitEachCage;
    private Boolean timeCalculated = false;

    public Visitor() {
        arrivalDate = Calendar.getInstance().getTime();
        timeToVisitEachCage = new TreeMap<>();
    }

    public void visit(){
        List<Cage> cagesToVisit = new ArrayList<>();
        cagesToVisit.addAll(ZooInfo.cages);


    //    Double reputationGeneratedByPrice = 10/ZooInfo.price;
        // reputationGenerated += reputationGeneratedByPrice;


        for(Cage cage : cagesToVisit){
            Integer timeToVisitCage = 0;
            for(Animal animal : cage.getAnimals()){
                Random random = new Random();


                if(!animal.isHealthy()){
                    reputationGenerated-= animal.getPopularity()*0.01;
                }
                else{
                    int chance = random.nextInt(11)+1;
                    if( chance < animal.getPopularity()){
                        reputationGenerated +=  animal.getPopularity()*0.01;
                        timeToVisitCage+= chance*60;
                    }
                    else{
                        timeToVisitCage+= 60;
                    }
                }
            }

            timeToVisitEachCage.put(cage,timeToVisitCage);

            if(!cage.isClean()){
                reputationGenerated -= cage.getDirtyFactor()*0.01;
            }
        }

        timeCalculated = true;


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

    public Double getReputationGenerated() {
        return reputationGenerated;
    }


    @Override
    public void onTick() {
        timeInZoo++;
        calculateTimeToVisit();
    }

    private void calculateTimeToVisit() {

            if(timeToVisitEachCage.isEmpty()){
               Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(timeCalculated) {
                            ZooInfoBusiness.removeVisitor(Visitor.this);
                            ZooInfoBusiness.addReputation(reputationGenerated);
                            NewsFeedBusiness.addNew(New.TagEnum.VISITOR_LEAVING.getTag(),Visitor.this);
                        }
                    }
                });


            }
            else {
                final Cage cage = timeToVisitEachCage.firstKey();
                Integer timeToVisitCage = timeToVisitEachCage.get(timeToVisitEachCage.firstKey());
                status = "Visitando jaula " + cage.getName();
                currentCage = cage;
                if (timeInZoo > timeToVisitCage) {
                    timeInZoo = 0;
                    timeToVisitEachCage.remove(timeToVisitEachCage.firstKey());
                }
            }

    }

}
