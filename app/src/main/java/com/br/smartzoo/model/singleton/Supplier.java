package com.br.smartzoo.model.singleton;

import com.br.smartzoo.model.entity.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhb_s on 4/28/2016.
 */
public class Supplier {

    public Supplier(){
    }


    private Food createFood(String foodName, Integer quantity) {

            Food food = new Food();
            food.setName(foodName);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,3);
            food.setExpirationDate(calendar.getTime());
            food.setWeight(Double.valueOf(quantity));

        return food;

    }


    public HashMap<String,Food> buyFoods (HashMap<String,Integer> foodsToBuy){

        HashMap<String,Food> foods = new HashMap<>();

        for(Map.Entry<String,Integer> item : foodsToBuy.entrySet()){
            String foodName = item.getKey();
            Integer foodQuantity = item.getValue();
            Food createdFood =  createFood(foodName,foodQuantity);
            foods.put(foodName,createdFood);
        }
        return foods;
    }
}
