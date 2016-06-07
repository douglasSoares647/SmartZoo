package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.persistence.FoodRepository;
import com.br.smartzoo.model.singleton.Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adenilson on 24/05/16.
 */
public class FoodBusiness {

    public static void save(Food food) {
        FoodRepository.save(food);
    }

    public static void saveAll(List<Food> foodList){
        if(foodList != null){
            for (Food food : foodList){
                FoodRepository.save(food);
            }
        }
    }

    public static void saveAll(HashMap<String,List<Food>> foodMap){
        for(Map.Entry<String,List<Food>> entry : foodMap.entrySet()){
            saveAll(entry.getValue());
        }
    }

    public static HashMap<String,List<Food>>  getAllFoods() {
        List<Food> foods = FoodRepository.getAllFoods();

        HashMap<String,List<Food>> stockFoods = new HashMap<>();

        for(Food food : foods){
            if(stockFoods.get(food.getName())==null) {
                stockFoods.put(food.getName(), new ArrayList<Food>());
            }
            stockFoods.get(food.getName()).add(food);
        }

        Stock.getInstance().putFoods(stockFoods);

        return stockFoods;
    }

    public static void deleteFood(Food food){
        FoodRepository.delete(food.getId());
    }



}
