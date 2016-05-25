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


    private static List<Food> createFood(String foodName, Integer quantity) {

            List<Food> foods = new ArrayList<>();
            for(int i=0; i<quantity; i++){
                Food food = new Food();
                food.setName(foodName);

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH,3);
                food.setExpirationDate(calendar.getTime());
                food.setWeight(1.0);

                foods.add(food);
            }

        return foods;

    }


    public static HashMap<String,List<Food>> buyFoods (HashMap<String,Integer> foodsToBuy){

        HashMap<String,List<Food>> foods = new HashMap<>();

        for(Map.Entry<String,Integer> item : foodsToBuy.entrySet()){
            String foodName = item.getKey();
            Integer foodQuantity = item.getValue();
            List<Food> createdFoods =  createFood(foodName,foodQuantity);
            foods.put(foodName,createdFoods);
        }
        return foods;
    }
}
