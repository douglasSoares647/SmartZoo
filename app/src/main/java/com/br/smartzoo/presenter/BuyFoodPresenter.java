package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.model.asynctask.SaveFoodAsyncTask;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.singleton.Stock;
import com.br.smartzoo.model.singleton.Supplier;
import com.br.smartzoo.ui.view.BuyFoodView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adenilson on 15/05/16.
 */
public class BuyFoodPresenter {

    private Activity mActivity;
    private BuyFoodView mBuyFragmentView;

    public BuyFoodPresenter(Activity activity) {
        this.mActivity = activity;
    }

    public void attachView(BuyFoodView buyFoodFragmentView) {
        this.mBuyFragmentView = buyFoodFragmentView;
    }

    public void detachView() {
        this.mBuyFragmentView = null;
    }


    public List<Food> createListFood() {
        List<Food> foods = new ArrayList<>();

        Food.FoodEnum.Fruit apple = Food.FoodEnum.Fruit.Apple;
        Food apple1 = new Food(apple.getImage(), mActivity.getString(apple.getName()), apple.getPrice()
                , apple.getWeight(), Calendar.getInstance().getTime());

        Food.FoodEnum.Fruit banana = Food.FoodEnum.Fruit.Banana;
        Food banana1 = new Food(banana.getImage(), mActivity.getString(banana.getName())
                , banana.getPrice(), banana.getWeight(), Calendar.getInstance().getTime());

        Food.FoodEnum.Fruit grape = Food.FoodEnum.Fruit.Grape;
        Food grape1 = new Food(grape.getImage(), mActivity.getString(grape.getName())
                , grape.getPrice(), grape.getWeight(), Calendar.getInstance().getTime());

        Food.FoodEnum.Meat beef = Food.FoodEnum.Meat.Beef;
        Food beef1 = new Food(beef.getImage(), mActivity.getString(beef.getName())
                , beef.getPrice(), beef.getWeight(), Calendar.getInstance().getTime());

        Food.FoodEnum.Meat chicken = Food.FoodEnum.Meat.Chicken;
        Food chicken1 = new Food(chicken.getImage(), mActivity.getString(chicken.getName())
                , chicken.getPrice(), chicken.getWeight(), Calendar.getInstance().getTime());

        Food.FoodEnum.Meat pork = Food.FoodEnum.Meat.Pork;
        Food pork1 = new Food(pork.getImage(), mActivity.getString(pork.getName())
                , pork.getPrice(), pork.getWeight(), Calendar.getInstance().getTime());


        foods = new ArrayList<>();
        foods.add(apple1);
        foods.add(banana1);
        foods.add(grape1);
        foods.add(beef1);
        foods.add(chicken1);
        foods.add(pork1);


        return foods;
    }

    public void saveFoods(HashMap<Food, Integer> totalFoods, Double totalPrice) {
        HashMap<String, List<Food>> foodMap = Supplier.buyFoods(totalFoods);
        Stock.getInstance().putFoods(foodMap);
        new SaveFoodAsyncTask().execute(foodMap);
        ZooInfoBusiness.takeMoney(totalPrice);

    }

}
