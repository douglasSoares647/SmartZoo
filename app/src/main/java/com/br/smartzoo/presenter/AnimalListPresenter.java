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


    private Activity mContext;
    private AnimalListView mAnimalListView;

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
                ((MainActivity) mContext).showSnackBar(mContext
                        .getString(R.string.message_load_animal_list_successful));
                mAnimalListView.onAnimalListLoad(animals);

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
                ZooInfoBusiness.addMoney(animal.getPrice());
                ZooInfoBusiness.removeAnimal(animal);
                mAnimalListView.updateList();
            }

            @Override
            public void onSellAnimalFailed() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string.message_sell_failed));
            }
        }).execute(animal.getId());
    }


    public void sortAnimalList(String attribute,List<Animal> animals) {
        if (animals != null) {

            List<Animal> sortedAnimals = SortArrayUtil.sortAnimalList(attribute,animals, mContext);
            mAnimalListView.onAnimalListLoad(sortedAnimals);

        }
    }
}
