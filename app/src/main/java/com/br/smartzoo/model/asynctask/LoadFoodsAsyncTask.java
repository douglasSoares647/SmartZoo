package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.FoodBusiness;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.util.ProgressDialogUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by adenilson on 24/05/16.
 */
public class LoadFoodsAsyncTask extends AsyncTask<Void, Integer, HashMap<String, List<Food>>> {

    private OnLoadFoodList mCallBack;
    private Activity mContext;
    private ProgressDialog mProgressDialog;


    public LoadFoodsAsyncTask(Activity context, OnLoadFoodList mCallBack) {
        this.mCallBack = mCallBack;
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_load_foods),
                        mContext.getString(R.string.title_foods));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }

    @Override
    protected HashMap<String, List<Food>> doInBackground(Void... params) {
        HashMap<String, List<Food>> allFoods = FoodBusiness.getAllFoods();
        return allFoods;
    }

    @Override
    protected void onPostExecute(HashMap<String, List<Food>> foodList) {
        if (foodList == null) {
            mCallBack.onLoadFoodListFail();
        } else if (foodList.isEmpty()) {
            mCallBack.onLoadFoodListEmpty();
        } else {
            mCallBack.onLoadFoodListSuccess();
        }

        mProgressDialog.dismiss();

        super.onPostExecute(foodList);
    }

    public interface OnLoadFoodList {

        void onLoadFoodListSuccess();

        void onLoadFoodListFail();

        void onLoadFoodListEmpty();

    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }
}
