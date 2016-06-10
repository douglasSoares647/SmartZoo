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
public class Veterinary extends Employee {
    private Integer maxStamina = 20;

    public String status;
    private HashMap<Integer,Integer> animalsTreatedThisMonth;
    private int clock = 0;


    private Animal currentAnimal;
    private Integer timeToTreatAnimal;
    private Boolean isTreating = false;

    public Veterinary(){

    }

    public Veterinary(List<Animal> animals) {
        status = ApplicationUtil.applicationContext.getString(R.string.veterinary_idle);
    }

    public Veterinary(String image, String name, Integer age, Date startDate, Date endDate
            , Double salary, String profession, String status) {
        super(image, name, age, startDate, endDate, salary, profession, status);
        status = ApplicationUtil.applicationContext.getString(R.string.veterinary_idle);
    }


    public void treat(final Animal animal) {

        if(getStamina()>animal.getStaminaToBeCured()) {
            status = ApplicationUtil.applicationContext.getString(R.string.treating_animal) + animal.getName();
            clock = 0;

            currentAnimal = animal;
            isTreating = true;
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
        clock++;

        if(isTreating) {
            if (clock == Clock.timeToTreat) {
                currentAnimal.setIsHealthy(true);
                status = ApplicationUtil.applicationContext.getString(R.string.animal_treatment) + currentAnimal.getName() + ApplicationUtil.applicationContext.getString(R.string.done);
                setStamina(getStamina()-currentAnimal.getStaminaToBeCured());
            }
        }
        else {
            status = ApplicationUtil.applicationContext.getString(R.string.ready);
        }

        if(clock== Clock.timeToRest){
            if(getStamina()<maxStamina)
                setStamina(getStamina()+1);
            clock = 0;
        }

    }

    public Integer getNumberAnimalTreated(){
       return animalsTreatedThisMonth != null ? animalsTreatedThisMonth.size() : 0;
    }
}
