package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 18/04/16.
 */
public class Veterinary extends Employee {

    private String credential;
    private List<Animal> animals;

    public Veterinary(){
    	animals = new ArrayList<>();
    }

    public Veterinary(String credential, List<Animal> animals) {
        this.credential = credential;
        this.animals = animals;
    }

    public Veterinary(Long id, String name, Integer age, String cpf, String startDate, String endDate, Double salary, String credential, List<Animal> animals) {
        super(id, name, age, cpf, startDate, endDate, salary);
        this.credential = credential;
        this.animals = animals;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    
    public void treat(Animal animal){
    	animal.setIsHealthy(true);
    	animals.add(animal);
    }

    public void treat(List<Animal> animals){
    	for(Animal animal : animals){
    		animal.setIsHealthy(true);
    		
    	}
    	this.animals.addAll(animals);
    }
    
    @Override
    public Double calculateSalary() {
        return animals.isEmpty() ? super.getSalary() : super.getSalary() * animals.size();
    }
    
   
    
    
}
