package com.br.smartzoo.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.view.NewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 05/06/16.
 */
public class NewsPresenter {

    private Activity mContext;
    private NewsView mNewsView;

    public NewsPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(NewsView newsView){
        this.mNewsView = newsView;
    }

    public void loadNews() {
        //GORDINHO, PRECISAMOS DSICUTIR COMO SERAO SALVAS AS NEWS DO ZOO, ABRAX
        mNewsView.onLoadNews(new ArrayList<New>());
    }

    public void startTransaction(String fragment) {
        //string fragment irá verificar qual tag é a do new
        ((MainActivity)mContext).startTransaction(new Fragment());
    }
}
