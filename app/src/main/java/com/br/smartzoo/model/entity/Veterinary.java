package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adenilson on 18/04/16.
 */
public class Veterinary extends Employee {

    private String credential;
    private HashMap<Integer,Integer> animalsTreatedThisMonth;

    public Veterinary(){
    }

    public Veterinary(String credential, List<Animal> animals) {
        this.credential = credential;
    }

    public Veterinary(String name, Integer age, String cpf, Date startDate, Date endDate, Double salary, String credential) {
        super(name, age, cpf, startDate, endDate, salary);
        this.credential = credential;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    
    public void treat(Animal animal){
    	animal.setIsHealthy(true);

    }

    public void treat(List<Animal> animals){
    	for(Animal animal : animals){
    		animal.setIsHealthy(true);
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
    
   
    
    
}
