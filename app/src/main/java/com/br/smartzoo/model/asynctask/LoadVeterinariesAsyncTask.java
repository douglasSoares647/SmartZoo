package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.VeterinaryBusiness;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.util.ProgressDialogUtil;

import java.util.List;

/**
 * Created by adenilson on 29/05/16.
 */
public class LoadVeterinariesAsyncTask extends AsyncTask<Void, Integer, List<Veterinary>> {
    private Activity mContext;
    private ProgressDialog mProgressDialog;
    private OnLoadVeterinariesList mCallBack;


    public LoadVeterinariesAsyncTask(Activity context, OnLoadVeterinariesList callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext, mContext.getString(R.string.message_load_veterinaries),
                        mContext.getString(R.string.title_veterinaries));
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        setProgress(values[0]);
    }


    @Override
    protected List<Veterinary> doInBackground(Void... params) {
        return VeterinaryBusiness.getVeterinaries();
    }


    @Override
    protected void onPostExecute(List<Veterinary> veterinaries) {
        if (veterinaries == null) {
            mCallBack.onLoadVeterinariesListFail();
        } else if (veterinaries.isEmpty()) {
            mCallBack.onLoadVeterinariesListEmpty();
        } else {
            mCallBack.onLoadVeterinariesListSuccess(veterinaries);
        }

        mProgressDialog.dismiss();
    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }


    public interface OnLoadVeterinariesList {

        void onLoadVeterinariesListSuccess(List<Veterinary> veterinaries);

        void onLoadVeterinariesListEmpty();

        void onLoadVeterinariesListFail();
    }
}
