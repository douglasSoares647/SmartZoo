package com.br.smartzoo.model.entity.employee;




import com.br.smartzoo.model.entity.jail.Cage;

import java.util.List;

/**
 * Created by adenilson on 18/04/16.
 */
public class Janitor extends Employee {

    private List<Cage> cages;
    private int expedient;

    public Janitor(List<Cage> cages, int expedient) {
        this.cages = cages;
        this.expedient = expedient;
    }

    public Janitor(String name, Integer age, String cpf, String startDate, String endDate, Double salary, List<Cage> cages, int expedient) {
        super(name, age, cpf, startDate, endDate, salary);
        this.cages = cages;
        this.expedient = expedient;
    }

    @Override
    public Double calculateSalary() {
        return cages.isEmpty() && expedient == 0 ? super.getSalary() : super.getSalary() +(cages.size() * expedient);
    }

    public List<Cage> getCages() {
        return cages;
    }

    public void setCages(List<Cage> cages) {
        this.cages = cages;
    }

    public int getExpedient() {
        return expedient;
    }

    public void setExpedient(int expedient) {
        this.expedient = expedient;
    }
    
    public void clear(Cage cage){
    	cage.setClean(true);
    	if(!cages.contains(cage)){
    		cages.add(cage);
    	}
    }
}
