package com.br.smartzoo.presenter;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.br.smartzoo.model.entity.Cage;
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
        cages.add(new Cage(CageEnum.Cage1.getName(), null,  null, CageEnum.Cage1.getPrice(), true
                , false, CageEnum.Cage1.getCapacity()));
        cages.add(new Cage(CageEnum.Cage2.getName(), null,  null, CageEnum.Cage2.getPrice(), true
                , false, CageEnum.Cage2.getCapacity()));
        cages.add(new Cage(CageEnum.Cage3.getName(), null,  null, CageEnum.Cage3.getPrice(), true
                , false, CageEnum.Cage3.getCapacity()));
        cages.add(new Cage(CageEnum.Cage4.getName(), null,  null, CageEnum.Cage4.getPrice(), true
                , false, CageEnum.Cage4.getCapacity()));
        cages.add(new Cage(CageEnum.Cage5.getName(), null,  null, CageEnum.Cage5.getPrice(), true
                , false, CageEnum.Cage5.getCapacity()));
        cages.add(new Cage(CageEnum.Cage1.getName(), null,  null, CageEnum.Cage1.getPrice(), true
                , false, CageEnum.Cage1.getCapacity()));
        cages.add(new Cage(CageEnum.Cage1.getName(), null,  null, CageEnum.Cage1.getPrice(), true
                , false, CageEnum.Cage1.getCapacity()));
        cages.add(new Cage(CageEnum.Cage1.getName(), null,  null, CageEnum.Cage1.getPrice(), true
                , false, CageEnum.Cage1.getCapacity()));
        cages.add(new Cage(CageEnum.Cage1.getName(), null,  null, CageEnum.Cage1.getPrice(), true
                , false, CageEnum.Cage1.getCapacity()));

        return cages;
    }
}
