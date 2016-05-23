package com.br.smartzoo.model.entity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.util.ApplicationUtil;
import com.br.smartzoo.util.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adenilson on 18/04/16.
 */
public class Veterinary extends Employee {

    public String status;

    private HashMap<Integer,Integer> animalsTreatedThisMonth;
    private int clock = 0;

    public Veterinary(){

    }

    public Veterinary(List<Animal> animals) {
        status = ApplicationUtil.applicationContext.getString(R.string.veterinary_idle);
    }

    public Veterinary(int image, String name, Integer age, Date startDate, Date endDate
            , Double salary, String profession) {
        super(image, name, age, startDate, endDate, salary, profession);
        status = ApplicationUtil.applicationContext.getString(R.string.veterinary_idle);
    }


    public void treat(final Animal animal) {
        status = ApplicationUtil.applicationContext.getString(R.string.treating_animal) + animal.getName();
        clock = 0;

        while (clock <= TimeUtil.timeToTreat) {
            if (clock == TimeUtil.timeToTreat) {
                animal.setIsHealthy(true);
                status = ApplicationUtil.applicationContext.getString(R.string.animal_treatment) + animal.getName() + ApplicationUtil.applicationContext.getString(R.string.done);
            }

        }
    }

    public void treat(List<Animal> animals) {
        status = ApplicationUtil.applicationContext.getString(R.string.treating_animals);
        for (final Animal animal : animals) {

            clock = 0;

            while (clock <= TimeUtil.timeToTreat) {
                if (clock == TimeUtil.timeToTreat) {
                    animal.setIsHealthy(true);
                    status = ApplicationUtil.applicationContext.getString(R.string.animal_treatment) + animal.getName() + ApplicationUtil.applicationContext.getString(R.string.done);
                }

            }
        }
    }
    
    @Override
    public Double calculateSalary() {
        if(animalsTreatedThisMonth.isEmpty()){
            return super.getSalary();
        }
        else {
            int sum = 0;
            for(Map.Entry<Integer,Integer> entry : animalsTreatedThisMonth.entrySet()){
                Integer animalId = entry.getKey();
                Integer quantity = entry.getValue();
                sum += quantity;
            }

            return super.getSalary()* + 20*sum;
        }
    }


    public String getStatus() {
        return status;
    }

    @Override
    public void onTick() {

    }
}
