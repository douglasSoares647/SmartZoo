package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.ui.view.BuyCageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 17/05/16.
 */
public class BuyCagePresenter {

    private Activity mActivity;
    private BuyCageView mBuyCageView;


    public BuyCagePresenter(Activity context){
        this.mActivity = context;
    }


    public void attachView(BuyCageView buyCageView){
        this.mBuyCageView = buyCageView;
    }

    public void detachView(){
        this.mBuyCageView = null;
    }


    public List<Cage> createCages() {
        List<Cage> cages = new ArrayList<>();
        cages.add(new Cage(Cage.CageEnum.Cage1.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage1.getPrice(), true
                , false, Cage.CageEnum.Cage1.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage2.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage2.getPrice(), true
                , false, Cage.CageEnum.Cage2.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage3.getName(),new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage3.getPrice(), true
                , false, Cage.CageEnum.Cage3.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage4.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage4.getPrice(), true
                , false, Cage.CageEnum.Cage4.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage5.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage5.getPrice(), true
                , false, Cage.CageEnum.Cage5.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage6.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage6.getPrice(), true
                , false, Cage.CageEnum.Cage6.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage7.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage7.getPrice(), true
                , false, Cage.CageEnum.Cage7.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage8.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage8.getPrice(), true
                , false, Cage.CageEnum.Cage8.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage9.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage9.getPrice(), true
                , false, Cage.CageEnum.Cage9.getCapacity()));
        cages.add(new Cage(Cage.CageEnum.Cage10.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), Cage.CageEnum.Cage10.getPrice(), true
                , false, Cage.CageEnum.Cage10.getCapacity()));


        return cages;
    }
}
