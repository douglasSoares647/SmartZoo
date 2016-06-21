package com.br.smartzoo.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.asynctask.LoadAnimalAsyncTask;
import com.br.smartzoo.model.asynctask.SellAnimalAsynkTask;
import com.br.smartzoo.model.business.AnimalBusiness;
import com.br.smartzoo.model.business.CageBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.BuyAnimalListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.ListCageAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.fragment.BuyCageFragment;
import com.br.smartzoo.ui.fragment.DetailsAnimalFragment;
import com.br.smartzoo.ui.view.AnimalListView;
import com.br.smartzoo.util.AlertDialogUtil;
import com.br.smartzoo.util.RecyclerItemClickListener;
import com.br.smartzoo.util.SortArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 01/06/16.
 */
public class AnimalListPresenter {


    private static final int VERTICAL_ITEM_SPACE = 30;
    private Activity mContext;
    private AnimalListView mAnimalListView;
    private Dialog selectCageDialog;

    public AnimalListPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(AnimalListView animalListView) {
        this.mAnimalListView = animalListView;
    }

    public void loadAnimalList() {

        new LoadAnimalAsyncTask(mContext, new LoadAnimalAsyncTask.OnLoadAnimalList() {
            @Override
            public void onLoadAnimalsListSuccess(List<Animal> animals) {
                mAnimalListView.onAnimalListLoad(animals);
            }

            @Override
            public void onLoadAnimalsListEmpty() {
                mAnimalListView.onAnimalListEmpty();

            }

            @Override
            public void onLoadAnimalsListFail() {

            }
        }).execute();

    }

    public void sellAnimal(final Animal animal) {
        new SellAnimalAsynkTask(mContext, new SellAnimalAsynkTask.OnSellAnimal() {
            @Override
            public void onSellAnimalSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string
                        .message_sell_success));
                ZooInfoBusiness.addMoney(animal.getPrice());
                ZooInfoBusiness.removeAnimal(animal);
                mAnimalListView.updateList(animal);
            }

            @Override
            public void onSellAnimalFailed() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string
                        .message_sell_failed));
            }
        }).execute(animal.getId());
    }


    public void sortAnimalList(String attribute, List<Animal> animals) {
        if (animals != null) {

            List<Animal> sortedAnimals = SortArrayUtil.sortAnimalList(attribute, animals, mContext);
            mAnimalListView.onAnimalListLoad(sortedAnimals);

        }
    }

    public void startTransaction(Animal animal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailsAnimalFragment.SELECTED_ANIMAL, animal);
        DetailsAnimalFragment detailsAnimalFragment = new DetailsAnimalFragment();
        detailsAnimalFragment.setArguments(bundle);
        ((MainActivity) mContext).startTransaction(detailsAnimalFragment);
    }

    public void putAnimalInCage(Animal animal) {
        List<Cage> cagesByAnimalType = CageBusiness.getCagesByAnimalType(animal);
        if (cagesByAnimalType.isEmpty()) {
            showBuyNewCageDialog(animal);
        } else {
            showSelectCageDialog(cagesByAnimalType, animal);
        }
    }

    private void showBuyNewCageDialog(final Animal animal) {
        AlertDialog.Builder dialog = AlertDialogUtil.makeConfirmationDialog(mContext,
                mContext.getString(R.string.title_no_cages), mContext.getString(R
                        .string.msg_buy_new_cage_for_animal_type),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buyCageForCurrentAnimalType(animal.getType());
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void buyCageForCurrentAnimalType(String type) {

        Bundle bundle = new Bundle();
        bundle.putString(BuyCageFragment.SELECTED_ANIMAL_TYPE, type);
        BuyCageFragment buyCageFragment = new BuyCageFragment();
        buyCageFragment.setArguments(bundle);
        ((MainActivity)mContext).startTransaction(buyCageFragment);
    }

    private void showSelectCageDialog(List<Cage> cagesByAnimalType, final Animal animal) {
        selectCageDialog = new Dialog(mContext);
        selectCageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        selectCageDialog.setContentView(R.layout.dialog_animal_cage);


        final RecyclerView recyclerViewCages = (RecyclerView) selectCageDialog.findViewById(R.id
                .recycler_view_avaible_cages_to_put_animal);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCages.setLayoutManager(layoutManager);
        recyclerViewCages.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerViewCages.addItemDecoration(
                new DividerItemDecoration(mContext, R.drawable.divider_recycler_view));
        recyclerViewCages.setItemViewCacheSize(ZooInfo.cages.size());


        ListCageAdapter listCageAdapter = new ListCageAdapter(cagesByAnimalType, mContext);
        recyclerViewCages.setAdapter(listCageAdapter);


        recyclerViewCages.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new
                RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Cage cage = ((ListCageAdapter) recyclerViewCages.getAdapter()).getCage(position);

                if (cage.checkCapacity()) {
                    selectCageDialog.dismiss();

                    AnimalBusiness.putAnimalInCage(cage, animal);
                    animal.setCage(cage);

                    Toast.makeText(mContext, R.string.msg_animal_succesfully_bought, Toast
                            .LENGTH_SHORT).show();

                    mAnimalListView.onAnimalPutted(animal);

                } else {
                    Toast.makeText(mContext, R.string.msg_cage_full, Toast.LENGTH_SHORT).show();
                }
            }
        }));

        Button buttonCancel = (Button) selectCageDialog.findViewById(R.id.button_cancel_dialog);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCageDialog.dismiss();
            }
        });

        selectCageDialog.show();

    }
}
