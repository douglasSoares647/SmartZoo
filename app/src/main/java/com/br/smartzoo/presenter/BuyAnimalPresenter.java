package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.AnimalBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.enums.AnimalEnum;
import com.br.smartzoo.ui.view.BuyAnimalView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adenilson on 16/05/16.
 */
public class BuyAnimalPresenter {


    private Activity mActivity;
    private BuyAnimalView mBuyAnimalView;

    public BuyAnimalPresenter(Activity activity) {
        this.mActivity = activity;
    }

    public void attachView(BuyAnimalView buyAnimalView) {
        this.mBuyAnimalView = buyAnimalView;

    }

    public void detachView() {
        this.mBuyAnimalView = null;

    }

    public List<Animal> createAnimalList() {

        List<Animal> animals = new ArrayList<>();

        for (AnimalEnum animalEnum : AnimalEnum.values()) {
            for (int i = 0; i < 4; i++) {
                Animal animal = new Animal();
                animal.setType(mActivity.getString(animalEnum.getType()));

                Random random = new Random();

                animal.setWeight((double) random.nextInt(20));
                animal.setAge(random.nextInt(10));
                animal.setStatus(animalEnum.getStatus());
                animal.setIsHealthy(random.nextBoolean());
                animal.setImage(animalEnum.getImage());
                animal.setPopularity(animalEnum.getPopularity());
                animal.setPrice(animalEnum.getPrice());
                animal.setResistance(random.nextInt(7));
                animal.setSex(!random.nextBoolean() ? mActivity.getString(R.string.male) : mActivity.getString(R.string.female));

                animals.add(animal);

            }

        }

        return animals;
    }


    public void finishAnimalCreation(Cage cage,Animal animal){
        animal.setCage(cage);
        AnimalBusiness.save(animal);

        ZooInfoBusiness.takeMoney(animal.getPrice());
    }
}
