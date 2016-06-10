package com.br.smartzoo.presenter;

import android.app.Activity;
import android.app.Dialog;

import com.br.smartzoo.R;
import com.br.smartzoo.model.asynctask.CleanCageAsyncTask;
import com.br.smartzoo.model.asynctask.DestroyCageAsyncTask;
import com.br.smartzoo.model.asynctask.LoadCagesAsyncTask;
import com.br.smartzoo.model.asynctask.LoadJanitorsRestedAsyncTask;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnJanitorsRestedSelected;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.view.CageListView;
import com.br.smartzoo.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 07/06/16.
 */

public class CageListPresenter implements OnJanitorsRestedSelected {

    private Activity mContext;
    private CageListView mCageListView;
    private Dialog mDialogRested;

    public CageListPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(CageListView cageListView) {
        this.mCageListView = cageListView;
    }

    public void loadCageList() {
        new LoadCagesAsyncTask(mContext, new LoadCagesAsyncTask.OnLoadCagesList() {
            @Override
            public void onLoadCagesListSuccess(List<Cage> cages) {
                ((MainActivity) mContext).showSnackBar(
                        mContext.getString(R.string.messa_load_cages_successful));
                mCageListView.onLoadCageList(cages);
            }

            @Override
            public void onLoadCagesListEmpty() {
                ((MainActivity) mContext).showSnackBar(
                        mContext.getString(R.string.messa_load_cages_successful));
                mCageListView.onLoadCageList(new ArrayList<Cage>());
            }

            @Override
            public void onLoadCagesListFail() {

            }
        }).execute();
    }

    public void destroyCage(final Cage cage) {
        new DestroyCageAsyncTask(mContext, new DestroyCageAsyncTask.OnCageDestroy() {
            @Override
            public void onDestroyCageSuccess() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_destroy_success));
                mCageListView.onCageDestroyed(cage);
            }

            @Override
            public void onDestroyCageFail() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_destroy_success));
            }
        }).execute(cage.getId());
    }

    public void cleanCage(final Cage cage) {
        new LoadJanitorsRestedAsyncTask(mContext, new LoadJanitorsRestedAsyncTask.OnLoadJanitorsRested() {
            @Override
            public void onLoadJanitorsRestedSuccess(List<Janitor> janitors) {
                createDialogJanitorsRested(janitors, cage);
            }

            @Override
            public void onLoadJanitorsRestedEmpty() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_there_arent_rested));
            }

            @Override
            public void onLoadJanitorsRestedFail() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_error_rested));

            }
        }).execute();
    }

    private void createDialogJanitorsRested(List<Janitor> janitors, Cage cage) {
        mDialogRested = DialogUtil.makeDialogRestedJanitors(mContext, janitors, this, cage);
        mDialogRested.show();
    }

    @Override
    public void onSelected(Janitor janitor, Cage cage) {
        new CleanCageAsyncTask(mContext, new CleanCageAsyncTask.OnCleanCage() {
            @Override
            public void onCleanCageSuccess() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_cleaned_successful));
                mDialogRested.dismiss();
            }

            @Override
            public void onCleanCageFail() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_cleaned_failed));
                mDialogRested.dismiss();

            }
        }).execute(janitor.getId(), cage.getId());
    }
}
