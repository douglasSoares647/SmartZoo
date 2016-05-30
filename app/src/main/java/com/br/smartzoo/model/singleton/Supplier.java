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


    private static List<Food> createFood(Food food, Integer quantity) {

            List<Food> foods = new ArrayList<>();
            for(int i=0; i<quantity; i++){
                Food createdfood = new Food();
                createdfood.setName(food.getName());
                createdfood.setImage(food.getImage());

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH,3);
                createdfood.setExpirationDate(calendar.getTime());
                createdfood.setWeight(1.0);
                createdfood.setPrice(food.getPrice());

                foods.add(createdfood);
            }

        return foods;

    }


    public static HashMap<String,List<Food>> buyFoods (HashMap<Food,Integer> foodsToBuy){

        HashMap<String,List<Food>> foods = new HashMap<>();

        for(Map.Entry<Food,Integer> item : foodsToBuy.entrySet()){
            Food food = item.getKey();
            Integer foodQuantity = item.getValue();
            List<Food> createdFoods =  createFood(food,foodQuantity);
            foods.put(food.getName(),createdFoods);
        }
        return foods;
    }
}
