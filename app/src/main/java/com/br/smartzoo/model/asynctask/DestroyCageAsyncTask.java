package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.CageBusiness;
import com.br.smartzoo.util.ProgressDialogUtil;

/**
 * Created by adenilson on 08/06/16.
 */

public class DestroyCageAsyncTask extends AsyncTask<Long, Integer, Integer> {
    private Activity mContext;
    private ProgressDialog mProgressDialog;
    private OnCageDestroy mCallBack;


    public DestroyCageAsyncTask(Activity context, OnCageDestroy callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }


    @Override
    protected Integer doInBackground(Long... params) {
        return CageBusiness.destroyCage(params[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.messa_destroy_cage),
                        mContext.getString(R.string.title_cage));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer id) {
        if (id != -1) {
            mCallBack.onDestroyCageSuccess();
        }else{
            mCallBack.onDestroyCageFail();
        }
            mProgressDialog.dismiss();
        }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }

    public interface OnCageDestroy {
        void onDestroyCageSuccess();

        void onDestroyCageFail();
    }
}


