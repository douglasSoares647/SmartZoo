package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.AnimalBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.util.ProgressDialogUtil;

import java.util.List;

/**
 * Created by adenilson on 01/06/16.
 */
public class LoadAnimalAsyncTask extends AsyncTask<Void, Integer, List<Animal>> {

    private Activity mContext;
    private ProgressDialog mProgressDialog;
    private OnLoadAnimalList mCallBack;


    public LoadAnimalAsyncTask(Activity context, OnLoadAnimalList callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_load_animals),
                        mContext.getString(R.string.title_animals));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }


    @Override
    protected void onPostExecute(List<Animal> animals) {
        if (animals == null) {
            mCallBack.onLoadAnimalsListFail();
        } else if (animals.isEmpty()) {
            mCallBack.onLoadAnimalsListEmpty();
        } else {
            mCallBack.onLoadAnimalsListSuccess(animals);
        }

        mProgressDialog.dismiss();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }


    public interface OnLoadAnimalList {

        void onLoadAnimalsListSuccess(List<Animal> animals);

        void onLoadAnimalsListEmpty();

        void onLoadAnimalsListFail();
    }

    @Override
    protected List<Animal> doInBackground(Void... params) {
        return AnimalBusiness.getAllAnimals();
    }
}
