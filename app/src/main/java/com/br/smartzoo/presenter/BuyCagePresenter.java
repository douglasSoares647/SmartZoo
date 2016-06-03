package com.br.smartzoo.presenter;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.enums.CageEnum;
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
        cages.add(new Cage(CageEnum.Cage1.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage1.getPrice(), true
                , false, CageEnum.Cage1.getCapacity()));
        cages.add(new Cage(CageEnum.Cage2.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage2.getPrice(), true
                , false, CageEnum.Cage2.getCapacity()));
        cages.add(new Cage(CageEnum.Cage3.getName(),new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage3.getPrice(), true
                , false, CageEnum.Cage3.getCapacity()));
        cages.add(new Cage(CageEnum.Cage4.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage4.getPrice(), true
                , false, CageEnum.Cage4.getCapacity()));
        cages.add(new Cage(CageEnum.Cage5.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage5.getPrice(), true
                , false, CageEnum.Cage5.getCapacity()));
        cages.add(new Cage(CageEnum.Cage6.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage6.getPrice(), true
                , false, CageEnum.Cage6.getCapacity()));
        cages.add(new Cage(CageEnum.Cage7.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage7.getPrice(), true
                , false, CageEnum.Cage7.getCapacity()));
        cages.add(new Cage(CageEnum.Cage8.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage8.getPrice(), true
                , false, CageEnum.Cage8.getCapacity()));
        cages.add(new Cage(CageEnum.Cage9.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage9.getPrice(), true
                , false, CageEnum.Cage9.getCapacity()));
        cages.add(new Cage(CageEnum.Cage10.getName(), new ArrayList<Animal>(),  new ArrayList<Food>(), CageEnum.Cage10.getPrice(), true
                , false, CageEnum.Cage10.getCapacity()));


        return cages;
    }
}
