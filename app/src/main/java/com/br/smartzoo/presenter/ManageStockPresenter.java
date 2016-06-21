package com.br.smartzoo.presenter;


import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.asynctask.LoadFoodsAsyncTask;
import com.br.smartzoo.model.business.FoodBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.ui.view.ManageStockView;

/**
 * Created by adenilson on 24/05/16.
 */
public class ManageStockPresenter {

    private Activity mContext;
    private ManageStockView mManageStockView;

    public ManageStockPresenter(Activity context){
        this.mContext = context;

    }

    public void attachView(ManageStockView manageStockView){
        this.mManageStockView = manageStockView;
    }

    public void detachView(){
        this.mManageStockView = null;
    }

    public void getAllFoods() {

        new LoadFoodsAsyncTask(mContext, new LoadFoodsAsyncTask.OnLoadFoodList() {
            @Override
            public void onLoadFoodListSuccess() {
                mManageStockView.onFoodListLoaded();
                mManageStockView.showSnackBar(mContext
                        .getString(R.string.message_load_list_food_successful));
            }

            @Override
            public void onLoadFoodListFail() {
                mManageStockView.showSnackBar(mContext
                        .getString(R.string.message_load_food_list_failed));
            }

            @Override
            public void onLoadFoodListEmpty() {
                mManageStockView.showSnackBar(mContext
                        .getString(R.string.message_load_food_list_empty));
                mManageStockView.onFoodListEmpty();
            }
        }).execute();

    }

    public void sellFood(Food food) {
        FoodBusiness.deleteFood(food);
        ZooInfoBusiness.addMoney(food.getPrice());
        mManageStockView.showSnackBar(mContext.getString(R.string.message_food_sold));       
    }
}
