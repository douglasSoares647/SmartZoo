package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.FeederBusiness;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.util.ProgressDialogUtil;

import java.util.List;

/**
 * Created by adenilson on 29/05/16.
 */
public class LoadFeedersAsyncTask extends AsyncTask<Void, Integer, List<Feeder>> {
    private Activity mContext;
    private ProgressDialog mProgressDialog;
    private OnLoadFeedersList mCallBack;


    public LoadFeedersAsyncTask(Activity context, OnLoadFeedersList callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_load_veterinaries),
                        mContext.getString(R.string.title_feeders));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }


    @Override
    protected List<Feeder> doInBackground(Void... params) {
        return FeederBusiness.getFeeders();
    }


    @Override
    protected void onPostExecute(List<Feeder> feeders) {
        if (feeders == null) {
            mCallBack.onLoadFeedersListFail();
        } else if (feeders.isEmpty()) {
            mCallBack.onLoadFeedersListEmpty();
        } else {
            mCallBack.onLoadFeederListSuccess(feeders);
        }

        mProgressDialog.dismiss();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }


    public interface OnLoadFeedersList {

        void onLoadFeederListSuccess(List<Feeder> veterinaries);

        void onLoadFeedersListEmpty();

        void onLoadFeedersListFail();
    }
}
