package com.br.smartzoo.presenter;

import android.app.Activity;
import android.content.res.Resources;

import com.br.smartzoo.model.asynctask.SaveFoodAsyncTask;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.enums.FoodEnum;
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

    private Activity mContext;
    private BuyFoodView mBuyFragmentView;

    public BuyFoodPresenter(Activity activity) {
        this.mContext = activity;
    }

    public void attachView(BuyFoodView buyFoodFragmentView) {
        this.mBuyFragmentView = buyFoodFragmentView;
    }

    public void detachView() {
        this.mBuyFragmentView = null;
    }


    public List<Food> createListFood() {
        List<Food> foods = new ArrayList<>();
        Resources resources = mContext.getResources();
        FoodEnum.Fruit apple = FoodEnum.Fruit.Apple;
        Food apple1 = new Food(resources.getResourceEntryName(apple.getImage()), mContext.getString(apple.getName()), apple.getPrice()
                , apple.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Fruit banana = FoodEnum.Fruit.Banana;
        Food banana1 = new Food(resources.getResourceEntryName(banana.getImage()), mContext.getString(banana.getName())
                , banana.getPrice(), banana.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Fruit grape = FoodEnum.Fruit.Grape;
        Food grape1 = new Food(resources.getResourceEntryName(grape.getImage()), mContext.getString(grape.getName())
                , grape.getPrice(), grape.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Meat beef = FoodEnum.Meat.Beef;
        Food beef1 = new Food(resources.getResourceEntryName(beef.getImage()), mContext.getString(beef.getName())
                , beef.getPrice(), beef.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Meat chicken = FoodEnum.Meat.Chicken;
        Food chicken1 = new Food(resources.getResourceEntryName(chicken.getImage()), mContext.getString(chicken.getName())
                , chicken.getPrice(), chicken.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Meat pork = FoodEnum.Meat.Pork;
        Food pork1 = new Food(resources.getResourceEntryName(pork.getImage()), mContext.getString(pork.getName())
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
