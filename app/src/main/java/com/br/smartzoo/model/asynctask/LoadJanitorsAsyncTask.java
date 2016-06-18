package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.JanitorBusiness;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.util.ProgressDialogUtil;

import java.util.List;

/**
 * Created by adenilson on 06/06/16.
 */

public class LoadJanitorsAsyncTask extends AsyncTask<Void, Integer, List<Janitor>> {
    private Activity mContext;
    private OnLoadJanitorsList mCallBack;
    private ProgressDialog mProgressDialog;

    public LoadJanitorsAsyncTask(Activity context, OnLoadJanitorsList onLoadJanitorsList) {
        this.mContext = context;
        this.mCallBack = onLoadJanitorsList;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_load_veterinaries),
                        mContext.getString(R.string.title_feeders));
        //mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(List<Janitor> janitors) {
        if (janitors == null) {
            mCallBack.onLoadJanitorsListFail();
        } else if (janitors.isEmpty()) {
            mCallBack.onLoadJanitorsListEmpty();
        } else {
            mCallBack.onLoadJanitorListSuccess(janitors);
        }

        mProgressDialog.dismiss();
    }


    @Override
    protected List<Janitor> doInBackground(Void... params) {
        return JanitorBusiness.getJanitors();
    }

    public interface OnLoadJanitorsList {

        void onLoadJanitorListSuccess(List<Janitor> veterinaries);

        void onLoadJanitorsListEmpty();

        void onLoadJanitorsListFail();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }

}
