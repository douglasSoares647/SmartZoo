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
 * Created by adenilson on 08/06/16.
 */

public class LoadJanitorsRestedAsyncTask extends AsyncTask<Void, Integer, List<Janitor>>{
    private Activity mContext;
    private OnLoadJanitorsRested mCallBack;
    private ProgressDialog mProgressDialog;

    public LoadJanitorsRestedAsyncTask(Activity context, OnLoadJanitorsRested onLoadJanitorsList){
        this.mContext = context;
        this.mCallBack = onLoadJanitorsList;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_load_janitor_list),
                        mContext.getString(R.string.title_janitors));
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
            mCallBack.onLoadJanitorsRestedFail();
        } else if (janitors.isEmpty()) {
            mCallBack.onLoadJanitorsRestedEmpty();
        } else {
            mCallBack.onLoadJanitorsRestedSuccess(janitors);
        }

        mProgressDialog.dismiss();
    }


    @Override
    protected List<Janitor> doInBackground(Void... params) {
        return JanitorBusiness.getJanitorsRested();
    }

    public interface OnLoadJanitorsRested {

        void onLoadJanitorsRestedSuccess(List<Janitor> janitors);

        void onLoadJanitorsRestedEmpty();

        void onLoadJanitorsRestedFail();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }
}
