package com.br.smartzoo.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.asynctask.CleanCageAsyncTask;
import com.br.smartzoo.model.asynctask.DestroyCageAsyncTask;
import com.br.smartzoo.model.asynctask.LoadJanitorsRestedAsyncTask;
import com.br.smartzoo.model.business.CageBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnJanitorsRestedSelected;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.AnimalTypeListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.CageListView;
import com.br.smartzoo.util.DialogUtil;
import com.br.smartzoo.util.RecyclerItemClickListener;

import java.util.List;

/**
 * Created by adenilson on 07/06/16.
 */

public class CageListPresenter implements OnJanitorsRestedSelected {

    private static final int VERTICAL_SPACE = 30;
    private Activity mContext;
    private CageListView mCageListView;
    private Dialog mDialogRested;
    private Dialog dialogSelectAnimalType;

    public CageListPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(CageListView cageListView) {
        this.mCageListView = cageListView;
    }

    public void loadCageList() {
        mCageListView.onLoadCageList(ZooInfo.cages);
    }

    public void destroyCage(final Cage cage) {
        new DestroyCageAsyncTask(mContext, new DestroyCageAsyncTask.OnCageDestroy() {
            @Override
            public void onDestroyCageSuccess() {
                ((MainActivity) mContext)
                        .showSnackBar(mContext.getString(R.string.message_destroy_success));
                ZooInfoBusiness.removeCage(cage);
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
        new LoadJanitorsRestedAsyncTask(mContext, new LoadJanitorsRestedAsyncTask
                .OnLoadJanitorsRested() {
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

    public void changeAnimalType(final Cage cage) {
        dialogSelectAnimalType = new Dialog(mContext);
        dialogSelectAnimalType.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSelectAnimalType.setContentView(R.layout.dialog_select_animal_type);


        final RecyclerView recyclerViewAnimalType = (RecyclerView) dialogSelectAnimalType
                .findViewById(R.id.recycler_view_cage_animal_type);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAnimalType.setLayoutManager(layoutManager);
        recyclerViewAnimalType.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_SPACE));
        recyclerViewAnimalType.addItemDecoration(
                new DividerItemDecoration(mContext, R.drawable.divider_recycler_view));
        recyclerViewAnimalType.setItemViewCacheSize(Animal.AnimalEnum.values().length);

        AnimalTypeListAdapter animalTypeListAdapter = new AnimalTypeListAdapter(mContext,
                Animal.AnimalEnum.values());
        recyclerViewAnimalType.setAdapter(animalTypeListAdapter);


        recyclerViewAnimalType.addOnItemTouchListener(new RecyclerItemClickListener(mContext,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String animalType = ((AnimalTypeListAdapter) recyclerViewAnimalType
                                .getAdapter())
                                .getAnimalType(position);
                        cage.setAnimalType(animalType);

                        CageBusiness.updateTypeAnimal(cage);
                        cage.getAnimals().clear();


                        Toast.makeText(mContext, mContext.getString(R.string
                                .msg_animal_type_changed),
                                Toast.LENGTH_SHORT).show();

                        dialogSelectAnimalType.dismiss();

                        mCageListView.onAnimalTypeChanged(cage);
                    }
                }));

        Button buttonCancel = (Button) dialogSelectAnimalType.findViewById(R.id
                .button_cancel_dialog);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSelectAnimalType.dismiss();
            }
        });

        dialogSelectAnimalType.show();


    }


}
