package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.persistence.FoodRepository;

import java.util.List;

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


    public static List<Food> getAllFoods() {
        List<Food> foods = FoodRepository.getAllFoods();

        return foods;
    }

    public static void deleteFood(Food food){
        FoodRepository.delete(food.getId());
    }
}
