package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.enums.FoodEnum;
import com.br.smartzoo.ui.view.BuyFoodFragmentView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by adenilson on 15/05/16.
 */
public class BuyFoodPresenter {

    private Activity mActivity;
    private BuyFoodFragmentView mBuyFragmentView;

    public BuyFoodPresenter(Activity activity) {
        this.mActivity = activity;
    }

    public void attachView(BuyFoodFragmentView buyFoodFragmentView) {
        this.mBuyFragmentView = buyFoodFragmentView;
    }

    public void detachView() {
        this.mBuyFragmentView = null;
    }


    public List<Food> createListFood() {
        List<Food> foods = new ArrayList<>();

        FoodEnum.Fruit apple = FoodEnum.Fruit.Apple;
        Food apple1 = new Food(apple.getImage(), mActivity.getString(apple.getName()), apple.getPrice()
                , apple.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Fruit banana = FoodEnum.Fruit.Banana;
        Food banana1 = new Food(banana.getImage(), mActivity.getString(banana.getName())
                , banana.getPrice(), banana.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Fruit grape = FoodEnum.Fruit.Grape;
        Food grape1 = new Food(grape.getImage(), mActivity.getString(grape.getName())
                , grape.getPrice(), grape.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Meat beef = FoodEnum.Meat.Beef;
        Food beef1 = new Food(beef.getImage(), mActivity.getString(beef.getName())
                , beef.getPrice(), beef.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Meat chicken = FoodEnum.Meat.Chicken;
        Food chicken1 = new Food(chicken.getImage(), mActivity.getString(chicken.getName())
                , chicken.getPrice(), chicken.getWeight(), Calendar.getInstance().getTime());

        FoodEnum.Meat pork = FoodEnum.Meat.Pork;
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

}
