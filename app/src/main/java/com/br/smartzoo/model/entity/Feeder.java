package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.interfaces.Manageable;
import com.br.smartzoo.model.singleton.Stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by adenilson on 18/04/16.
 */
public class Feeder extends Employee implements Manageable {

    private List<Cage> cages;
    private Stock stock;
    
    public Feeder(){
    	cages = new ArrayList<Cage>();
    	stock = Stock.getInstance();
    }
    
    

    public Feeder(String name, Integer age, String cpf, Date startDate, Date endDate, Double salary, List<Cage> cages) {
		super(name, age, cpf, startDate, endDate, salary);
		this.cages = cages;
		stock = Stock.getInstance();
	}



	@Override
    public Double calculateSalary() {
        return cages.isEmpty() ? super.getSalary() : super.getSalary() * cages.size();
    }

  
   
    @Override
    public void prepareFoodAndFillCage(List<Food> foods , Cage cage) {
    		List<Food> foodsToAliment = new ArrayList<Food>();
       		for(Food food : foods){
    			if(food.getWeight()>5.0){
    				Integer quantity = 0;

    					quantity = (int)(food.getWeight()/5.0);

    				for(int i=0; i<quantity; i++){
    					Food minorFood = new Food();
    					minorFood.setName(food.getName());
    					minorFood.setExpirationDate(food.getExpirationDate());
    					
    					food.setWeight(food.getWeight()-5);
    					minorFood.setWeight(5.0);
    					
    					foodsToAliment.add(minorFood);
    				}
    			}
    		   if(food.getWeight()>0){
    				Food minorFood = new Food();
					minorFood.setName(food.getName());
					minorFood.setExpirationDate(food.getExpirationDate());
					minorFood.setWeight(food.getWeight());
					
					food.setWeight(0.0);
					foodsToAliment.add(minorFood);
    				}
    		}
       		cage.getFoods().addAll(foodsToAliment);
    		cage.setIsSupplied(true);
    		cages.add(cage);
       		
    }

    @Override
    public void toRetain(Cage cage) {
    	if(!cage.getFoods().isEmpty()){
    		stock.putFoods(cage.getFoods());
    		cage.getFoods().clear();
    		cages.add(cage);
    	}
    }

	public List<Cage> getCages() {
		return cages;
	}



	public void setCages(List<Cage> cages) {
		this.cages = cages;
	}



	public Stock getStock() {
		return stock;
	}



	public void setStock(Stock stock) {
		this.stock = stock;
	}



	
    

	
}
