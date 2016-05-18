package com.br.smartzoo.model.entity;


import android.os.CountDownTimer;

import com.br.smartzoo.R;
import com.br.smartzoo.model.interfaces.Observer;
import com.br.smartzoo.util.ApplicationUtil;
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
public class Janitor extends Employee{

    private String status;
    private int clock = 0;

    private Long id;
    private HashMap<Integer,Integer> cagesCleanedThisMonth;

    public Janitor(List<Cage> cages, int expedient) {
    }

    public Janitor(String name, Integer age, String cpf, Date startDate, Date endDate, Double salary) {
        super(name, age, cpf, startDate, endDate, salary);
    }

    public Janitor(){
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
    
    public void clear(final Cage cage) {
        long timeToCleanCage = cage.getDirtyFactor() * TimeUtil.timeToCleanEachDirty;


        int dirtyCleaned = 0;
        status = ApplicationUtil.applicationContext.getString(R.string.cleaning_cage) + cage.getName();
        while (clock < timeToCleanCage) {
            if (clock % TimeUtil.timeToCleanEachDirty == 0)
                dirtyCleaned++;
        }
        cage.setClean(true);
        status = ApplicationUtil.applicationContext.getString(R.string.resting);

        clock  = 0;
        while(clock<TimeUtil.timeToRest)
        status = ApplicationUtil.applicationContext.getString(R.string.ready);

    }

    @Override
    public void onTick() {

        clock++;
    }
}
