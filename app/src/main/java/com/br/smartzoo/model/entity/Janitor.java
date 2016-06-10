package com.br.smartzoo.model.entity;


import com.br.smartzoo.R;
import com.br.smartzoo.model.environment.Clock;
import com.br.smartzoo.util.ApplicationUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adenilson on 18/04/16.
 */
public class Janitor extends Employee {
    private Integer maxStamina=50;
    private int clock = 0;

    private Long id;
    private String status;
    private HashMap<Integer, Integer> cagesCleanedThisMonth;


    private Integer currentDirtyCleaned = 0;
    private Integer timeToCleanCage = 0;
    private Boolean isCleaning = false;
    private Cage currentCage;

    public Janitor(List<Cage> cages, int expedient) {
    }

    public Janitor(String image, String name, Integer age, Date startDate, Date endDate, Double salary
            , String profession,String status) {
        super(image, name, age, startDate, endDate, salary, profession, status);
    }

    public Janitor() {
    }


    @Override
    public Double calculateSalary() {
        if (cagesCleanedThisMonth.isEmpty()) {
            return super.getSalary();
        } else {
            int sum = 0;
            for (Map.Entry<Integer, Integer> entry : cagesCleanedThisMonth.entrySet()) {
                Integer cageId = entry.getKey();
                Integer quantity = entry.getValue();
                sum += quantity;
            }

            return super.getSalary() * +10 * sum;
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

        if(getStamina()>cage.getDirtyFactor()) {
            clock = 0;
            timeToCleanCage = cage.getDirtyFactor() * Clock.timeToCleanEachDirty;

            status = ApplicationUtil.applicationContext.getString(R.string.cleaning_cage) + cage.getName();

            currentCage = cage;

            isCleaning = true;
        }

    }

    @Override
    public void onTick() {
        clock++;


        if(isCleaning) {
            if (clock < timeToCleanCage) {
                if (clock % Clock.timeToCleanEachDirty == 0) {
                    currentDirtyCleaned++;
                    setStamina(getStamina() - 1);
                }
            }
            else {
                currentCage.setClean(true);
                clock = 0;
                isCleaning = false;
            }
        }

        else {
            status = ApplicationUtil.applicationContext.getString(R.string.ready);
        }

        if(clock== Clock.timeToRest){
            if(getStamina()<maxStamina)
            setStamina(getStamina()+1);
            clock =0;
        }

    }
}
