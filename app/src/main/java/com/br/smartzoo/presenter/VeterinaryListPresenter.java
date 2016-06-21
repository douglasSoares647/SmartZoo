package com.br.smartzoo.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.asynctask.DemitEmployeeAsyncTask;
import com.br.smartzoo.model.asynctask.UpdateSalaryAsyncTask;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.fragment.DetailsVeterinaryFragment;
import com.br.smartzoo.ui.view.VeterinaryListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 29/05/16.
 */
public class VeterinaryListPresenter {
    private Activity mContext;
    private VeterinaryListView mVeterinaryListView;


    public VeterinaryListPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(VeterinaryListView veterinaryListView) {
        this.mVeterinaryListView = veterinaryListView;
    }

    public void detachView() {
        this.mVeterinaryListView = null;
    }

    public void loadVeterinaries() {
        List<Veterinary> veterinaries = new ArrayList<>();

        for (Employee employee : ZooInfo.employees) {
            if (employee instanceof Veterinary)
                veterinaries.add((Veterinary) employee);
        }

        if (veterinaries.isEmpty()) {
            mVeterinaryListView.onLoadVeterinaryListEmpty();
        } else {
            mVeterinaryListView.onLoadVeterinaryList(veterinaries);
        }
    }

    public void demitVeterinary(final Employee veterinary) {

        new DemitEmployeeAsyncTask(mContext, new DemitEmployeeAsyncTask.OnDemit() {
            @Override
            public void onDemitSuccess() {
                updateZooInfo(veterinary);
            }


            @Override
            public void onDemitFailed() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_demit_failed));

            }
        }).execute(veterinary.getId());

    }

    private void updateZooInfo(Employee veterinary) {
        ZooInfoBusiness.removeEmployee(veterinary);
    }

    public void updateSalary(final Employee veterinary, Double value) {
        new UpdateSalaryAsyncTask(mContext, new UpdateSalaryAsyncTask.onSalaryChanged() {
            @Override
            public void onSalaryChangedSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_success));
                loadVeterinaries();
            }

            @Override
            public void onSalaryChangedFailed() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_failed));
            }
        }).execute(veterinary, value);
    }

    public void openVeterinaryDetails(Employee employee) {

        startTransaction(employee);
    }


    public void startTransaction(Employee employee) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailsVeterinaryFragment.SELECTED_VETERINARY, employee);
        DetailsVeterinaryFragment detailsVeterinaryFragment = new DetailsVeterinaryFragment();
        detailsVeterinaryFragment.setArguments(bundle);
        ((MainActivity) mContext).startTransaction(detailsVeterinaryFragment);
    }
}
