package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.ui.view.AnimalListView;

/**
 * Created by adenilson on 01/06/16.
 */
public class AnimalListPresenter  {

    private Activity mContext;
    private AnimalListView mAnimalListView;

    public AnimalListPresenter(Activity context){
        this.mContext = context;
    }

    public void attachView(AnimalListView animalListView){
        this.mAnimalListView = animalListView;
    }
}
