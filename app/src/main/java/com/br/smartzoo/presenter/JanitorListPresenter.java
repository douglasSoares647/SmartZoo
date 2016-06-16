package com.br.smartzoo.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;

import com.br.smartzoo.R;
import com.br.smartzoo.model.asynctask.DemitEmployeeAsyncTask;
import com.br.smartzoo.model.asynctask.LoadJanitorsAsyncTask;
import com.br.smartzoo.model.asynctask.UpdateSalaryAsyncTask;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.fragment.DetailsAnimalFragment;
import com.br.smartzoo.ui.fragment.DetailsJanitorFragment;
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
        List<Janitor> janitors = new ArrayList<>();
        for(Employee employee : ZooInfo.employees){
            if(employee instanceof Janitor)
                janitors.add((Janitor)employee);
        }

        mJanitorListView.onLoadJanitorList(janitors);
    }

    public void demitJanitor(final Employee janitor) {
        new DemitEmployeeAsyncTask(mContext, new DemitEmployeeAsyncTask.OnDemit() {
            @Override
            public void onDemitSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string.message_demit));
                updateZooInfo(janitor);
            }

            @Override
            public void onDemitFailed() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_demit_failed));
            }
        }).execute(janitor.getId());
    }

    private void updateZooInfo(Employee janitor) {
        ZooInfoBusiness.removeEmployee(janitor);
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


    public void openJanitorDetails(Employee employee) {
        startTransaction(employee);
    }



    public void startTransaction(Employee employee) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailsJanitorFragment.SELECTED_JANITOR, employee);
        DetailsJanitorFragment detailsJanitorFragment = new DetailsJanitorFragment();
        detailsJanitorFragment.setArguments(bundle);
        ((MainActivity)mContext).startTransaction(detailsJanitorFragment);
    }
}
