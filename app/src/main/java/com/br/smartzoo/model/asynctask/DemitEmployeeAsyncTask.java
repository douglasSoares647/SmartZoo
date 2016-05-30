package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.EmployeeBusiness;
import com.br.smartzoo.util.ProgressDialogUtil;

/**
 * Created by adenilson on 29/05/16.
 */
public class DemitEmployeeAsyncTask extends AsyncTask<Long, Integer, Integer> {
    private ProgressDialog mProgressDialog;
    private Activity mContext;
    private OnDemit mOnDemit;


    public DemitEmployeeAsyncTask(Activity context, OnDemit onDemit){
        this.mContext = context;
        this.mOnDemit = onDemit;
    }


    @Override
    protected Integer doInBackground(Long... params) {
        return EmployeeBusiness.delete(params[0]);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_demit),
                        mContext.getString(R.string.title_demission));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer result) {

        if(result != 0){
            mOnDemit.onDemitSuccess();
        }else{
            mOnDemit.onDemitFailed();
        }

        mProgressDialog.dismiss();
    }

    public interface OnDemit {

        void onDemitSuccess();

        void onDemitFailed();


    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }

}
