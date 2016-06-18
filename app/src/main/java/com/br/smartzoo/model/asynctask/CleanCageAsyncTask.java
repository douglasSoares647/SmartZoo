package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.CageBusiness;
import com.br.smartzoo.model.business.EmployeeBusiness;
import com.br.smartzoo.util.ProgressDialogUtil;

/**
 * Created by adenilson on 09/06/16.
 */

public class CleanCageAsyncTask extends AsyncTask<Long, Integer, Integer> {
    private Activity mContext;
    private ProgressDialog mProgressDialog;
    private OnCleanCage mCallBack;


    public CleanCageAsyncTask(Activity context, OnCleanCage callBack) {
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
    protected void onPostExecute(Integer result) {
        if (result.equals(-1)) {
            mCallBack.onCleanCageFail();
        } else {
            mCallBack.onCleanCageSuccess();
        }


        mProgressDialog.dismiss();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }


    public interface OnCleanCage {

        void onCleanCageSuccess();

        void onCleanCageFail();
    }


    @Override
    protected Integer doInBackground(Long... params) {
        int result;

        Long idJanitor = params[0];
        Long idCage = params[1];

        result = EmployeeBusiness.decreaseStamina(idJanitor);
        result = CageBusiness.clearCage(idCage);


        return result;
    }


}
