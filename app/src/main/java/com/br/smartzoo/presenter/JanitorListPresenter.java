package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.asynctask.DemitEmployeeAsyncTask;
import com.br.smartzoo.model.asynctask.LoadJanitorsAsyncTask;
import com.br.smartzoo.model.asynctask.UpdateSalaryAsyncTask;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.view.JanitorListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 06/06/16.
 */

public class JanitorListPresenter {
    private Activity mContext;
    private JanitorListView mJanitorListView;

    public JanitorListPresenter(Activity context){
        this.mContext = context;
    }

    public void attachView(JanitorListView janitorListView){
        this.mJanitorListView = janitorListView;
    }

    public void loadJanitors(){
        new LoadJanitorsAsyncTask(mContext, new LoadJanitorsAsyncTask.OnLoadJanitorsList() {
            @Override
            public void onLoadJanitorListSuccess(List<Janitor> janitors) {
                ((MainActivity)mContext)
                        .showSnackBar(mContext.getString(R.string.message_load_janitor_list));
                mJanitorListView.onLoadJanitorList(janitors);

            }

            @Override
            public void onLoadJanitorsListEmpty() {
                mJanitorListView.onLoadJanitorList(new ArrayList<Janitor>());
            }

            @Override
            public void onLoadJanitorsListFail() {

            }
        }).execute();

    }

    public void demitJanitor(Employee janitor) {
        new DemitEmployeeAsyncTask(mContext, new DemitEmployeeAsyncTask.OnDemit() {
            @Override
            public void onDemitSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string.message_demit));
                loadJanitors();
            }

            @Override
            public void onDemitFailed() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_demit_failed));
            }
        }).execute(janitor.getId());
    }

    public void updateSalaryJanitor(Employee janitor, Double value) {
        new UpdateSalaryAsyncTask(mContext, new UpdateSalaryAsyncTask.onSalaryChanged() {
            @Override
            public void onSalaryChangedSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_success));
                loadJanitors();

            }

            @Override
            public void onSalaryChangedFailed() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_failed));
            }
        }).execute(janitor, value);
    }


}
