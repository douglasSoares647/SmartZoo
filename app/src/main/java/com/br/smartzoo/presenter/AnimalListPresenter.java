package com.br.smartzoo.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.asynctask.SellAnimalAsynkTask;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.fragment.DetailsAnimalFragment;
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
        List<Animal> animals = new ArrayList<>();

        for(Cage cage : ZooInfo.cages){
            animals.addAll(cage.getAnimals());
        }
        mAnimalListView.onAnimalListLoad(animals);
    }

    public void sellAnimal(final Animal animal) {
        new SellAnimalAsynkTask(mContext, new SellAnimalAsynkTask.OnSellAnimal() {
            @Override
            public void onSellAnimalSuccess() {
                ((MainActivity) mContext).showSnackBar(mContext.getString(R.string.message_sell_success));
                ZooInfoBusiness.addMoney(animal.getPrice());
                ZooInfoBusiness.removeAnimal(animal);
                mAnimalListView.updateList(animal);
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

    public void startTransaction(Animal animal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailsAnimalFragment.SELECTED_ANIMAL, animal);
        DetailsAnimalFragment detailsAnimalFragment = new DetailsAnimalFragment();
        detailsAnimalFragment.setArguments(bundle);
        ((MainActivity)mContext).startTransaction(detailsAnimalFragment);
    }
}
