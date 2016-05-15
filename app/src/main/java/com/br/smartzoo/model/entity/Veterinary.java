package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Employee;
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

    private String credential;
    private HashMap<Integer,Integer> animalsTreatedThisMonth;
    private Timer treatmentTime;

    public Veterinary(){
        treatmentTime = new Timer();
    }

    public Veterinary(String credential, List<Animal> animals) {
        this.credential = credential;
        treatmentTime = new Timer();
        status = " Sem serviço ";
    }

    public Veterinary(String name, Integer age, String cpf, Date startDate, Date endDate, Double salary, String credential) {
        super(name, age, cpf, startDate, endDate, salary);
        treatmentTime = new Timer();
        this.credential = credential;
        treatmentTime = new Timer();
        status = " Sem serviço ";
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    
    public void treat(final Animal animal){
        status = "Tratando animal " + animal.getName();
        treatmentTime.schedule(new TimerTask() {
            @Override
            public void run() {
                animal.setIsHealthy(true);
                status = "Tratamento do animal " + animal.getName() + " finalizado!";
            }
        }, TimeUtil.timeToTreat);


    }

    public void treat(List<Animal> animals){
        status = "Tratando dos animais";
    	for(final Animal animal : animals){

            treatmentTime.schedule(new TimerTask() {

                @Override
                public void run() {
                    animal.setIsHealthy(true);
                    status = "Animal " + animal.getName() + " curado!";
                }
            },TimeUtil.timeToTreat);

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
}
