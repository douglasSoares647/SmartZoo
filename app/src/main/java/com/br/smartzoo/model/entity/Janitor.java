package com.br.smartzoo.model.entity;


import android.os.CountDownTimer;

import com.br.smartzoo.model.interfaces.Observer;
import com.br.smartzoo.util.TimeUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by adenilson on 18/04/16.
 */
public class Janitor extends Employee implements Observer{

    private String status;

    private Long id;
    private HashMap<Integer,Integer> cagesCleanedThisMonth;
    private Timer tasks;

    public Janitor(List<Cage> cages, int expedient) {
        this.tasks = new Timer();
    }

    public Janitor(String name, Integer age, String cpf, Date startDate, Date endDate, Double salary) {
        super(name, age, cpf, startDate, endDate, salary);
        this.tasks = new Timer();
    }

    public Janitor(){
        this.tasks = new Timer();
    }


    @Override
    public Double calculateSalary() {
        if(cagesCleanedThisMonth.isEmpty()){
            return super.getSalary();
        }
        else {
            int sum = 0;
            for(Map.Entry<Integer,Integer> entry : cagesCleanedThisMonth.entrySet()){
                Integer cageId = entry.getKey();
                Integer quantity = entry.getValue();
                sum += quantity;
            }

            return super.getSalary()* + 10*sum;
        }
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public void clear(final Cage cage){
        long timeToCleanCage = cage.getDirtyFactor() * TimeUtil.timeToCleanEachDirty;

        //Limpando jaula
        new CountDownTimer(TimeUtil.timeToCleanEachDirty,timeToCleanCage){
            int dirtyCleaned = 0;
            @Override
            public void onTick(long millisUntilFinished) {
               dirtyCleaned++;
            }

            @Override
            public void onFinish() {
                cage.setClean(true);
                status = "Descansando";
            }
        }.start();


        //Descansando
        tasks.schedule(new TimerTask() {
            @Override
            public void run() {
              status = "Pronto";
            }
        }, TimeUtil.timeToRest);
    }

    @Override
    public void onTick() {

    }
}
