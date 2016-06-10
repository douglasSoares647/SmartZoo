package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.asynctask.DemitEmployeeAsyncTask;
import com.br.smartzoo.model.asynctask.LoadVeterinariesAsyncTask;
import com.br.smartzoo.model.asynctask.UpdateSalaryAsyncTask;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.ui.activity.MainActivity;
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

        new LoadVeterinariesAsyncTask(mContext
                , new LoadVeterinariesAsyncTask.OnLoadVeterinariesList() {
            @Override
            public void onLoadVeterinariesListSuccess(List<Veterinary> veterinaries) {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_load_list_veterinary_successful));
                mVeterinaryListView.onLoadVeterinaryList(veterinaries);
            }

            @Override
            public void onLoadVeterinariesListEmpty() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_load_veterinary_list_empty));
                mVeterinaryListView.onLoadVeterinaryList(new ArrayList<Veterinary>());
            }

            @Override
            public void onLoadVeterinariesListFail() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_load_veterinary_list_failed));
            }
        }).execute();
    }

    public void demitVeterinary(final Employee veterinary) {

        new DemitEmployeeAsyncTask(mContext, new DemitEmployeeAsyncTask.OnDemit() {
            @Override
            public void onDemitSuccess() {
                loadVeterinaries();
                updateZooInfo(veterinary);
            }



            @Override
            public void onDemitFailed() {
                ((MainActivity)mContext)
                        .showSnackBar(mContext.getString(R.string.message_demit_failed));

            }
        }).execute(veterinary.getId());

    }

    private void updateZooInfo(Employee veterinary) {
        ZooInfo.employees.remove(veterinary);
    }

    public void updateSalary(final Employee veterinary, Double value) {
        new UpdateSalaryAsyncTask(mContext, new UpdateSalaryAsyncTask.onSalaryChanged() {
            @Override
            public void onSalaryChangedSuccess() {
                ((MainActivity)mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_success));
                loadVeterinaries();
            }

            @Override
            public void onSalaryChangedFailed() {
                ((MainActivity)mContext).showSnackBar(mContext
                        .getString(R.string.message_update_salary_failed));
            }
        }).execute(veterinary, value);
    }
}
