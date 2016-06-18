package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.ZooInfoBusiness;

/**
 * Created by Taibic on 6/17/2016.
 */
public class LoadZooInfoAsyncTask extends AsyncTask<Void, Void, Void> {

    private Activity mContext;
    private ProgressDialog mProgressDialog;

    public LoadZooInfoAsyncTask(Activity mActivity) {
        this.mContext = mActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle(mContext.getString(R.string.title_loading));
        mProgressDialog.setMessage(mContext.getString(R.string.msg_loading_zoo));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    @Override
    protected Void doInBackground(Void... params) {
        ZooInfoBusiness.getFromPreferences();

        if (!ZooInfoBusiness.isLoaded) {
            ZooInfoBusiness.load();
            ZooInfoBusiness.isLoaded = true;
        }

        return null;
    }
}
