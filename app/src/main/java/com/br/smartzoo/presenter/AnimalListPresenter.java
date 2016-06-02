package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.asynctask.LoadAnimalAsyncTask;
import com.br.smartzoo.model.asynctask.SellAnimalAsynkTask;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.view.AnimalListView;
import com.br.smartzoo.util.SortArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 01/06/16.
 */
public class AnimalListPresenter {

    private static Long sActualSelect;

    private Activity mContext;
    private AnimalListView mAnimalListView;

    public AnimalListPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(AnimalListView animalListView) {
        this.mAnimalListView = animalListView;
    }

    public void loadAnimalList(final Long select) {
        sActualSelect = select;

        new LoadAnimalAsyncTask(mContext, new LoadAnimalAsyncTask.OnLoadAnimalList() {
            @Override
            public void onLoadAnimalsListSuccess(List<Animal> animals) {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_load_animal_list_successful));
                List<Animal> sortedAnimals = SortArrayUtil.sortAnimalList(select, animals);
                mAnimalListView.onAnimalListLoad(sortedAnimals);

            }

            @Override
            public void onLoadAnimalsListEmpty() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_load_animal_list_empty));
                mAnimalListView.onAnimalListLoad(new ArrayList<Animal>());
            }

            @Override
            public void onLoadAnimalsListFail() {
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_load_animal_list_failed));
            }
        }).execute();
    }

    public void sellAnimal(final Animal animal) {
        new SellAnimalAsynkTask(mContext, new SellAnimalAsynkTask.OnSellAnimal() {
            @Override
            public void onSellAnimalSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string.message_sell_success));
                updateZooInfo(animal);
                loadAnimalList(sActualSelect);
            }

            @Override
            public void onSellAnimalFailed() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string.message_sell_failed));
            }
        }).execute(animal.getId());
    }

    private void updateZooInfo(Animal animal) {
        ZooInfo.money += animal.getPrice();
    }

    public void loadAnimalList(String select) {
        if (select.equals(mContext.getString(R.string.select_name))) {

        } else if (select.equals(mContext.getString(R.string.select_age))) {

        } else if (select.equals(mContext.getString(R.string.select_popularity))) {

        } else if (select.equals(mContext.getString(R.string.select_weight))) {

        } else if (select.equals(mContext.getString(R.string.select_satisfaction))) {

        } else if (select.equals(mContext.getString(R.string.select_sex))) {

        } else if (select.equals(mContext.getString(R.string.select_status))) {

        } else if (select.equals(mContext.getString(R.string.select_resistance))) {

        }
    }
}
