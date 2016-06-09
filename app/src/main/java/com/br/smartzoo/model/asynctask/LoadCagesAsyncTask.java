package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.CageBusiness;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.util.ProgressDialogUtil;

import java.util.List;

/**
 * Created by adenilson on 07/06/16.
 */

public class LoadCagesAsyncTask extends AsyncTask<Void, Integer, List<Cage>> {
    private Activity mContext;
    private ProgressDialog mProgressDialog;
    private OnLoadCagesList mCallBack;


    public LoadCagesAsyncTask(Activity context, OnLoadCagesList callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_load_cages),
                        mContext.getString(R.string.title_cage));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(List<Cage> cages) {
        if (cages == null) {
            mCallBack.onLoadCagesListFail();
        } else if (cages.isEmpty()) {
            mCallBack.onLoadCagesListEmpty();
        } else {
            mCallBack.onLoadCagesListSuccess(cages);
        }

        mProgressDialog.dismiss();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }


    public interface OnLoadCagesList {

        void onLoadCagesListSuccess(List<Cage> cages);

        void onLoadCagesListEmpty();

        void onLoadCagesListFail();
    }


    @Override
    protected List<Cage> doInBackground(Void... params) {
        return CageBusiness.getAllCages();
    }
}
