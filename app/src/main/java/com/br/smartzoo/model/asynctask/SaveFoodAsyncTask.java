package com.br.smartzoo.model.asynctask;

import android.os.AsyncTask;

import com.br.smartzoo.model.business.FoodBusiness;
import com.br.smartzoo.model.entity.Food;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Taibic on 6/6/2016.
 */
public class SaveFoodAsyncTask extends AsyncTask<HashMap<String,List<Food>>,Void,Void> {
    @Override
    protected Void doInBackground(HashMap<String, List<Food>>... params) {
        FoodBusiness.saveAll(params[0]);

        return null;
    }
}
