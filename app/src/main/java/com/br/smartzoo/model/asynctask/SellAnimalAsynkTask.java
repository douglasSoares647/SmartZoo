package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.AnimalBusiness;
import com.br.smartzoo.util.ProgressDialogUtil;

/**
 * Created by adenilson on 01/06/16.
 */
public class SellAnimalAsynkTask extends AsyncTask<Long, Integer, Integer> {
    private Activity mContext;
    private ProgressDialog mProgressDialog;
    private OnSellAnimal mCallBack;


    public SellAnimalAsynkTask(Activity context, OnSellAnimal callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_sure),
                        mContext.getString(R.string.title_sell));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }


    @Override
    protected void onPostExecute(Integer result) {
        if (result == -1) {
            mCallBack.onSellAnimalFailed();
        } else {
            mCallBack.onSellAnimalSuccess();
        }

        mProgressDialog.dismiss();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }


    public interface OnSellAnimal {

        void onSellAnimalSuccess();

        void onSellAnimalFailed();

    }

    @Override
    protected Integer doInBackground(Long... params) {
        return AnimalBusiness.sell(params[0]);
    }
}
