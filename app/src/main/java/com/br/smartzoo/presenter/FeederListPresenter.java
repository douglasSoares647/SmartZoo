package com.br.smartzoo.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.asynctask.DemitEmployeeAsyncTask;
import com.br.smartzoo.model.asynctask.UpdateSalaryAsyncTask;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.fragment.DetailsAnimalFragment;
import com.br.smartzoo.ui.fragment.DetailsFeederFragment;
import com.br.smartzoo.ui.view.FeederListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 29/05/16.
 */
public class FeederListPresenter {

    private Activity mContext;
    private FeederListView mFeederListView;

    public FeederListPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(FeederListView feederListView) {
        this.mFeederListView = feederListView;
    }

    public void detachView() {
        this.mFeederListView = null;
    }

    public void loadFeederList() {
        List<Feeder> feeders = new ArrayList<>();
        for (Employee employee : ZooInfo.employees) {
            if (employee instanceof Feeder)
                feeders.add((Feeder) employee);
        }

        mFeederListView.onLoadFeederSuccess(feeders);
    }

    public void demitFeeder(final Employee feeder) {
        new DemitEmployeeAsyncTask(mContext, new DemitEmployeeAsyncTask.OnDemit() {
            @Override
            public void onDemitSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string.message_demit));
                ZooInfoBusiness.removeEmployee(feeder);
            }

            @Override
            public void onDemitFailed() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_demit_failed));
            }
        }).execute(feeder.getId());
    }

    public void updateSalaryFeeder(Employee employee, Double value) {
        new UpdateSalaryAsyncTask(mContext, new UpdateSalaryAsyncTask.onSalaryChanged() {
            @Override
            public void onSalaryChangedSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_success));
                loadFeederList();

            }

            @Override
            public void onSalaryChangedFailed() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_failed));
            }
        }).execute(employee, value);
    }

    public void startTransaction(Feeder feeder) {

        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailsFeederFragment.SELECTED_FEEDER, feeder);
        DetailsFeederFragment detailsFeederFragment = new DetailsFeederFragment();
        detailsFeederFragment.setArguments(bundle);
        ((MainActivity) mContext).startTransaction(detailsFeederFragment);

    }
}
