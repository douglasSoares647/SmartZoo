package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.EmployeeBusiness;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.util.ProgressDialogUtil;

/**
 * Created by adenilson on 29/05/16.
 */
public class UpdateSalaryAsyncTask extends AsyncTask<Object, Integer, Integer> {


    private ProgressDialog mProgressDialog;
    private Activity mContext;
    private onSalaryChanged mOnSalaryChanged;


    public UpdateSalaryAsyncTask(Activity context, onSalaryChanged onSalaryChanged) {
        this.mContext = context;
        this.mOnSalaryChanged = onSalaryChanged;
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

        if (result != 0) {
            mOnSalaryChanged.onSalaryChangedSuccess();
        } else {
            mOnSalaryChanged.onSalaryChangedFailed();
        }

        mProgressDialog.dismiss();
    }

    public interface onSalaryChanged {

        void onSalaryChangedSuccess();

        void onSalaryChangedFailed();


    }

    private void setProgress(Integer progress) {
        mProgressDialog.setProgress(progress);
    }


    @Override
    protected Integer doInBackground(Object... params) {
        Employee employee = (Employee) params[0];
        Double salary = (Double) params[1];

        return EmployeeBusiness.updateSalary(employee.getId(), salary);
    }


}
