package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.ui.view.StatusZooView;

/**
 * Created by adenilson on 29/05/16.
 */
public class StatusZooPresenter {

    private Activity mContext;
    private  StatusZooView mStatusZooView;

    public StatusZooPresenter(Activity context){
        this.mContext = context;
    }

    public void attachView(StatusZooView statusZooView){
        this.mStatusZooView = statusZooView;
    }

    public void detachView(){
        this.mStatusZooView = null;
    }
}
