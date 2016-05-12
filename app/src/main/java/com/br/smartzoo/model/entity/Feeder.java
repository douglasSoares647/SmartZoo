package com.br.smartzoo.model.entity;

import com.br.smartzoo.model.interfaces.Manageable;
import com.br.smartzoo.model.singleton.Stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by adenilson on 18/04/16.
 */
public class Feeder extends Employee implements Manageable {

    private HashMap<Integer,Integer> cagesFeededThisMonth;
    private Stock stock;
    
    public Feeder(){
		cagesFeededThisMonth = new HashMap<Integer,Integer>();
    	stock = Stock.getInstance();
    }
    
    

    public Feeder(String name, Integer age, String cpf, Date startDate, Date endDate, Double salary) {
		super(name, age, cpf, startDate, endDate, salary);
		stock = Stock.getInstance();
	}



	@Override
    public Double calculateSalary() {
		if(cagesFeededThisMonth.isEmpty()){
			return super.getSalary();
		}
		else {
			int sum = 0;
			for(Map.Entry<Integer,Integer> entry : cagesFeededThisMonth.entrySet()){
				Integer cageId = entry.getKey();
				Integer quantity = entry.getValue();
				sum += quantity;
			}

			return super.getSalary()* + 10*sum;
		}

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
       		
    }

    @Override
    public void toRetain(Cage cage) {
    	if(!cage.getFoods().isEmpty()){
    		stock.putFoods(cage.getFoods());
    		cage.getFoods().clear();
    	}
    }


	public HashMap<Integer, Integer> getCagesFeededThisMonth() {
		return cagesFeededThisMonth;
	}

	public void setCagesFeededThisMonth(HashMap<Integer, Integer> cagesFeededThisMonth) {
		this.cagesFeededThisMonth = cagesFeededThisMonth;
	}

	public Stock getStock() {
		return stock;
	}



	public void setStock(Stock stock) {
		this.stock = stock;
	}



	
    

	
}
